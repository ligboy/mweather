package org.ligboy.mweather.core.cache;

import java.util.List;

/**
 * @author Vlad Sumtsov
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface DiskCache<T> {

    /**
     * Sets the value to {@code value}.
     */
    void set(String key, T value);

    /**
     * Returns a value by {@code key}, or null if it doesn't
     * exist is not currently readable. If a value is returned, it is moved to
     * the head of the LRU queue.
     */
    T get(String key);

    /**
     * Drops the entry for {@code key} if it exists and can be removed. Entries
     * actively being edited cannot be removed.
     *
     * @return true if an entry was removed.
     */
    boolean remove(String key);

    /**
     * Returns all values from cache directory if all files are same type
     *
     * @return List
     */
    List<T> getAll();

    /**
     * Deletes all file from cache directory
     */
    void clear();

    /**
     * Returns true if there is file by {@code key} in cache folder
     *
     * @param key Key
     * @return Returns true if there is file by {@code key} in cache folder
     */
    boolean exists(String key);

    /**
     * Closes this cache. Stored values will remain on the filesystem.
     */
    void close();

}
