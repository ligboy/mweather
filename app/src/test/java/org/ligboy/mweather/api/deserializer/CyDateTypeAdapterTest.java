package org.ligboy.mweather.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ligboy.mweather.api.util.CyDate;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class CyDateTypeAdapterTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void write() throws Exception {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("date1", new CyDate(145616622000L));
        map.put("date2", new CyDate(145612222000L));
        map.put("date3", new CyDate(145212222000L));

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(MyTypeAdapterFactory.INSTANCE)
                .create();
        String json = gson.toJson(map);
        assertNotNull(json);
        String expectedJson = "{\"date1\":\"1974-08-13 05:03\",\"date3\":\"1974-08-09 12:4" +
                "3\",\"date2\":\"1974-08-13 03:50\"}";
        assertEquals(expectedJson, json);
    }

    @Test
    public void read() throws Exception {

    }

}