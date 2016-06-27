package org.ligboy.mweather.core;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.content.Loader;
import android.support.v4.os.OperationCanceledException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit Loader
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public abstract class RetrofitLoader<T> extends Loader<Response<T>> {

    private Call<T> mCall;
    private Call<T> mCancellingTask;
    private Response<T> mResult;
    private Throwable mRequestThrowable;
    private long mLastLoadCompleteTime = -10000;

    private static final Handler HANDLER = new Handler(Looper.myLooper());

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public RetrofitLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        if (mCall == null || mCall.isExecuted() || mCall.isCanceled()) {
            mCall = newCall();
        }
        mCall.enqueue(new CallCallback());
    }

    private void dispatchOnLoadComplete(Call<T> call, Response<T> response) {
        if (mCall != call) {
            dispatchOnCancelled(call, response);
        } else {
            if (isAbandoned()) {
                onCanceled(response);
            } else {
                commitContentChanged();
                mLastLoadCompleteTime = SystemClock.uptimeMillis();
                mCall = null;
                deliverResult(response);
            }
        }
    }

    /**
     * Called if the task was canceled before it was completed.  Gives the class a chance
     * to clean up post-cancellation and to properly dispose of the result.
     *
     * @param result The value that was returned by requesting, or null
     *               if the task threw {@link OperationCanceledException}.
     */
    protected void onCanceled(Response<T> result) {
    }

    private void dispatchOnCancelled(Call<T> call, Response<T> response) {
        onCanceled(response);
        if (mCancellingTask == call) {
            rollbackContentChanged();
            mLastLoadCompleteTime = SystemClock.uptimeMillis();
            mCancellingTask = null;

            deliverCancellation();
        }
    }

    @Override
    public void deliverResult(Response<T> data) {
        if (isReset()) {
            // An async query came in while the loader is stopped
            if (mResult != null) {
                mResult = null;
            }
            return;
        }

        mResult = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    /**
     * 创建一个新的Call
     */
    protected abstract Call<T> newCall();

    @Override
    protected boolean onCancelLoad() {
        if (mCall != null && mCall.isExecuted() && !mCall.isCanceled()) {
            mCall.cancel();

            return true;
        }
        return false;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        mCall = null;
        mResult = null;
    }

    public Throwable getRequestThrowable() {
        return mRequestThrowable;
    }

    final class CallCallback implements Callback<T> {

        /**
         * Invoked for a received HTTP response.
         * <p/>
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call {@link Response#isSuccessful()} to determine if the response indicates success.
         */
        @Override
        public void onResponse(final Call<T> call, final Response<T> response) {
            mRequestThrowable = null;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                dispatchOnLoadComplete(call, response);
            } else {
                HANDLER.post(new Runnable() {
                    @Override
                    public void run() {
                        dispatchOnLoadComplete(call, response);
                    }
                });
            }
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected
         * exception occurred creating the request or processing the response.
         */
        @Override
        public void onFailure(final Call<T> call, final Throwable t) {
            mRequestThrowable = t;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (!call.isCanceled()) {
                    dispatchOnCancelled(call, null);
                } else {
                    dispatchOnLoadComplete(call, null);
                }
            } else {
                HANDLER.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!call.isCanceled()) {
                            dispatchOnCancelled(call, null);
                        } else {
                            dispatchOnLoadComplete(call, null);
                        }
                    }
                });
            }
        }
    }
}
