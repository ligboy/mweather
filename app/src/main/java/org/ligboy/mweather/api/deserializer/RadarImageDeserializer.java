package org.ligboy.mweather.api.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.ligboy.mweather.model.RadarImage;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class RadarImageDeserializer implements JsonDeserializer {

    @Override
    public RadarImage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            RadarImage radarImage = new RadarImage();
            JsonArray jsonArray = json.getAsJsonArray();
            JsonElement jsonElement0 = jsonArray.get(0);
            if (jsonElement0 != null) {
                radarImage.image = jsonElement0.getAsString();
            }
            JsonElement jsonElement1 = jsonArray.get(1);
            if (jsonElement1 != null) {
                radarImage.datetime = new Date(jsonElement1.getAsLong());
            }
            JsonElement jsonElement2 = jsonArray.get(2);
            JsonArray jsonArray1;
            if (jsonElement2 != null && (jsonArray1 = jsonElement2.getAsJsonArray()) != null) {
                final int size = jsonArray1.size();
                if (size > 0) {
                    final double[] lonlats = new double[size];
                    for (int i = 0; i < size; i++) {
                        lonlats[i] = jsonArray1.get(i).getAsFloat();
                    }
                    radarImage.lonlats = lonlats;
                }
            }
            return radarImage;
        } catch (Exception ignored) {
        }
        return null;
    }

}
