package org.ligboy.mweather.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import org.ligboy.mweather.model.RadarImage;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class MyTypeAdaterFactory implements TypeAdapterFactory {

    public static final TypeAdapterFactory INSTANCE = new MyTypeAdaterFactory();

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (RadarImage.class.equals(type.getRawType())) {
            return (TypeAdapter<T>) RadarImageTypeAdapter.INSTANCE;
        }
        return null;
    }

}
