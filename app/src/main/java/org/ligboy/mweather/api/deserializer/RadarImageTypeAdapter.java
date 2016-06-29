package org.ligboy.mweather.api.deserializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.ligboy.mweather.model.RadarImage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class RadarImageTypeAdapter extends TypeAdapter<RadarImage> {

    public static final TypeAdapter INSTANCE = new RadarImageTypeAdapter();

    @Override
    public void write(JsonWriter out, RadarImage value) throws IOException {
        out.beginArray();
        if (value != null) {
            if (value.image != null) {
                out.value(value.image);
            } else {
                out.nullValue();
            }
            out.value(value.datetime);
            if (value.lonlats != null && value.lonlats.length > 0) {
                out.beginArray();
                for (double lonlat : value.lonlats) {
                    out.value(lonlat);
                }
                out.endArray();
            }
        }
        out.endArray();
    }

    @Override
    public RadarImage read(JsonReader in) throws IOException {
        RadarImage radarImage = new RadarImage();
        in.beginArray();
        radarImage.image = in.nextString();
        JsonToken dateToken = in.peek();
        switch (dateToken) {
            case NUMBER:
                radarImage.datetime = in.nextLong();
                break;
        }
        JsonToken lonlatsToken = in.peek();
        if (lonlatsToken == JsonToken.BEGIN_ARRAY) {
            in.beginArray();
            ArrayList<Double> doubles = new ArrayList<>();
            while (in.hasNext()) {
                doubles.add(in.nextDouble());
            }
            if (doubles.size() > 0) {
                radarImage.lonlats = new double[doubles.size()];
                for (int i = 0; i < doubles.size(); i++) {
                    radarImage.lonlats[i] = doubles.get(i);
                }
            }
            in.endArray();
        }
        in.endArray();
        return radarImage;
    }
}
