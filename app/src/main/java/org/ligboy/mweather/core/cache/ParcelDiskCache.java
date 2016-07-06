package org.ligboy.mweather.core.cache;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jakewharton.disklrucache.DiskLruCache;

import org.ligboy.mweather.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vlad Sumtsov
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class ParcelDiskCache<T extends Parcelable> implements DiskCache<Parcelable> {

    private static final String LIST = "list";
    private static final String PARCELABLE = "parcelable";
    private static final String VALIDATE_KEY_REGEX = "[a-z0-9_-]{1,5}";
    private static final int MAX_KEY_SYMBOLS = 62;
    private ClassLoader mClassLoader;
    private DiskLruCache mCache;
    private Executor mStoreExecutor;
    private boolean mSaveInUI = true;

    private ParcelDiskCache(Context context, ClassLoader classLoader, String name, long maxSize)
            throws IOException {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null || cacheDir.canWrite()) {
            cacheDir = context.getCacheDir();
        }
        this.mClassLoader = classLoader;
        mStoreExecutor = Executors.newSingleThreadExecutor();
        File dir = new File(cacheDir, name);
        int version = getVersionCode(context) + (Build.VERSION.SDK_INT << 21);
        this.mCache = DiskLruCache.open(dir, version, 1, maxSize);
    }

    public static ParcelDiskCache open(Context context, ClassLoader classLoader, String name,
                                       long maxSize) throws IOException {
        return new ParcelDiskCache(context, classLoader, name, maxSize);
    }

    public void set(String key, Parcelable value) {
        key = validateKey(key);
        Parcel parcel = Parcel.obtain();
        parcel.writeString(PARCELABLE);
        parcel.writeParcelable(value, 0);
        if (mSaveInUI) {
            saveValue(mCache, parcel, key);
        } else {
            mStoreExecutor.execute(new StoreParcelableValueTask(mCache, parcel, key));
        }
    }

    public void set(String key, List<T> values) {
        key = validateKey(key);
        Parcel parcel = Parcel.obtain();
        parcel.writeString(LIST);
        parcel.writeList(values);
        if (mSaveInUI) {
            saveValue(mCache, parcel, key);
        } else {
            mStoreExecutor.execute(new StoreParcelableValueTask(mCache, parcel, key));
        }
    }

    public T get(String key) {
        key = validateKey(key);
        Parcel parcel = getParcel(key);
        if (parcel != null) {
            try {
                String type = parcel.readString();
                if (LIST.equals(type)) {
                    throw new IllegalAccessError("get list data with getList method");
                }
                if (!PARCELABLE.equals(type)) {
                    throw new IllegalAccessError("Parcel doesn't contain parcelable data");
                }
                return parcel.readParcelable(mClassLoader);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                parcel.recycle();
            }
        }
        return null;
    }

    private Parcel getParcel(String key) {
        key = validateKey(key);
        byte[] value = null;
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mCache.get(key);
            if (snapshot == null) {
                return null;
            }
            value = getBytesFromStream(snapshot.getInputStream(0));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(value, 0, value != null ? value.length : 0);
        parcel.setDataPosition(0);
        return parcel;
    }

    private String validateKey(String key) {
        Matcher keyMatcher = getPattern(VALIDATE_KEY_REGEX).matcher(key);
        StringBuilder newKey = new StringBuilder();
        while (keyMatcher.find()) {
            String group = keyMatcher.group();
            if (newKey.length() + group.length() > MAX_KEY_SYMBOLS) {
                break;
            }

            newKey.append(group);
        }
        return newKey.toString().toLowerCase();
    }

    public Pattern getPattern(String bodyRegex) {
        int flags = Pattern.MULTILINE | Pattern.DOTALL | Pattern.CASE_INSENSITIVE;
        return Pattern.compile(bodyRegex, flags);
    }

    public List<T> getList(String key, Class itemClass) {
        key = validateKey(key);
        ArrayList<T> res = new ArrayList<>();
        Parcel parcel = getParcel(key);
        if (parcel != null) {
            try {
                String type = parcel.readString();
                if (PARCELABLE.equals(type)) {
                    throw new IllegalAccessError("Get not a list data with get method");
                }
                if (!LIST.equals(type)) {
                    throw new IllegalAccessError("Parcel doesn't contain list data");
                }
                parcel.readList(res, itemClass != null ? itemClass.getClassLoader()
                        : ArrayList.class.getClassLoader());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                parcel.recycle();
            }
        }
        return res;
    }

    public List<T> getList(String key) {
        return getList(key, null);
    }

    public boolean remove(String key) {
        key = validateKey(key);
        try {
            return mCache.remove(key.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List getAll() {
        return getAll(null);
    }

    public List<T> getAll(String prefix) {
        List<T> list = new ArrayList<>(1);
        File dir = mCache.getDirectory();
        File[] files = dir.listFiles();
        if (files != null) {
            list = new ArrayList<>(files.length);
            for (File file : files) {
                String fileName = file.getName();
                if ((!TextUtils.isEmpty(prefix) && fileName.startsWith(prefix)
                        && fileName.indexOf(".") > 0)
                        || (TextUtils.isEmpty(prefix) && fileName.indexOf(".") > 0)) {
                    String key = fileName.substring(0, fileName.indexOf("."));
                    T value = get(key);
                    list.add(value);
                }
            }
        }
        return list;
    }

    public void clear() {
        try {
            mCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String key) {
        key = validateKey(key);
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = mCache.get(key.toLowerCase());
            return snapshot != null && snapshot.getLength(0) > 0;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
        return false;
    }

    @Override
    public void close() {
        try {
            mCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shouldSaveInUI() {
        this.mSaveInUI = true;
    }

    private static class StoreParcelableValueTask implements Runnable {

        private final DiskLruCache cache;
        private final Parcel value;
        private final String key;

        @SuppressWarnings("WeakerAccess")
        public StoreParcelableValueTask(DiskLruCache cache, Parcel value, String key) {
            this.value = value;
            this.key = key;
            this.cache = cache;
        }

        @Override
        public void run() {
            saveValue(cache, value, key);
        }
    }

    private static void saveValue(DiskLruCache cache, Parcel value, @NonNull String key) {
        if (cache == null) return;
        key = key.toLowerCase();
        try {
            final String sKey = key.intern();
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (sKey) {
                DiskLruCache.Editor editor = cache.edit(key);
                OutputStream outputStream = editor.newOutputStream(0);
                writeBytesToStream(outputStream, value.marshall());
                editor.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            value.recycle();
        }
    }

    public static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            byte[] data = new byte[1024];
            int count;
            while ((count = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, count);
            }
            buffer.flush();
            return buffer.toByteArray();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
            try {
                buffer.close();
            } catch (Exception ignored) {
            }

        }
    }

    public static void writeBytesToStream(OutputStream outputStream, byte[] bytes)
            throws IOException {
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    public static int getVersionCode(Context context) {
        return BuildConfig.VERSION_CODE;
    }
}