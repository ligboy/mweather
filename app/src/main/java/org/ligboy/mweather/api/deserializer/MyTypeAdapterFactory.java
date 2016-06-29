package org.ligboy.mweather.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import org.ligboy.mweather.api.util.CyDate;
import org.ligboy.mweather.model.RadarImage;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class MyTypeAdapterFactory implements TypeAdapterFactory {

    public static final TypeAdapterFactory INSTANCE = new MyTypeAdapterFactory();

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (RadarImage.class.equals(rawType)) {
            return (TypeAdapter<T>) RadarImageTypeAdapter.INSTANCE;
        } else if (CyDate.class.equals(rawType)) {
            return (TypeAdapter<T>) CyDateTypeAdapter.INSTANCE;
        }
        return null;
    }

}
