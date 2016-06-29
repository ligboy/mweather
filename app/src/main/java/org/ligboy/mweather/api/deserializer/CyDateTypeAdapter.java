package org.ligboy.mweather.api.deserializer;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.ligboy.mweather.api.util.CyDate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class CyDateTypeAdapter extends TypeAdapter<CyDate> {

    public static final TypeAdapter INSTANCE = new CyDateTypeAdapter();

    private static final String FORMAT_PATTERN = "yyyy-MM-dd hh:mm";

    @SuppressWarnings("SimpleDateFormat")
    private static final DateFormat CY_DATE_FORMAT = new SimpleDateFormat(FORMAT_PATTERN);
    private static final DateFormat DEFAULT_DATE_FORMAT
            = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);

    static {
        TimeZone cstTimeZone = TimeZone.getTimeZone("GMT+0800");
        CY_DATE_FORMAT.setTimeZone(cstTimeZone);
        DEFAULT_DATE_FORMAT.setTimeZone(cstTimeZone);
    }

    @Override
    public void write(JsonWriter out, CyDate value) throws IOException {
        synchronized (CY_DATE_FORMAT) {
            out.value(CY_DATE_FORMAT.format(value));
        }
    }

    @Override
    public CyDate read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case STRING: {
                Date date = deserializeToDate(in.nextString());
                return date != null ? new CyDate(date.getTime()) : null;
            }
            case NUMBER:
                return new CyDate(in.nextLong() * 1000L);
            }
        return null;
    }

    private Date deserializeToDate(String date) {
        synchronized (CY_DATE_FORMAT) {
            try {
                return CY_DATE_FORMAT.parse(date);
            } catch (ParseException ignored) {}
            try {
                return DEFAULT_DATE_FORMAT.parse(date);
            } catch (ParseException ignored) {}
            try {
                return ISO8601Utils.parse(date, new ParsePosition(0));
            } catch (ParseException e) {
                throw new JsonSyntaxException(date, e);
            }
        }
    }
}
