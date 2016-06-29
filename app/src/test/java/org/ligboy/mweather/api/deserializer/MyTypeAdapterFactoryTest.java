package org.ligboy.mweather.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.ligboy.mweather.model.RadarImage;
import org.ligboy.mweather.model.RadarImageResult;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class MyTypeAdapterFactoryTest {
    @Test
    public void deserialize() throws Exception {
        String jsonString = "{\n" +
                "  \"status\": \"ok\",\n" +
                "  \"radar_img\": [\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270907.png\",\n" +
                "      1466989627.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270918.png\",\n" +
                "      1466990300.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270930.png\",\n" +
                "      1466991029.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270942.png\",\n" +
                "      1466991774.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270954.png\",\n" +
                "      1466992468.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271002.png\",\n" +
                "      1466992947.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271018.png\",\n" +
                "      1466993880.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271030.png\",\n" +
                "      1466994638.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271042.png\",\n" +
                "      1466995321.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271054.png\",\n" +
                "      1466996067.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271106.png\",\n" +
                "      1466996798.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271118.png\",\n" +
                "      1466997497.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271130.png\",\n" +
                "      1466998222.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271142.png\",\n" +
                "      1466998921.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271154.png\",\n" +
                "      1466999648.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271206.png\",\n" +
                "      1467000360.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271218.png\",\n" +
                "      1467001080.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271230.png\",\n" +
                "      1467001811.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271242.png\",\n" +
                "      1467002521.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ],\n" +
                "    [\n" +
                "      \"http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606271254.png\",\n" +
                "      1467003241.0,\n" +
                "      [\n" +
                "        3.9079000000000015,\n" +
                "        71.9281517704939,\n" +
                "        57.9079,\n" +
                "        134.86564822950612\n" +
                "      ]\n" +
                "    ]\n" +
                "  ]\n" +
                "}";
        final Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(MyTypeAdapterFactory.INSTANCE)
                .create();
        RadarImageResult result = gson.fromJson(jsonString, RadarImageResult.class);
        assertNotNull(result);
        assertEquals("ok", result.status);
        assertNotNull(result.radarImages);
        assertEquals(20, result.radarImages.length);
        assertNotNull(result.radarImages[0]);
        assertEquals("http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270907.png", result.radarImages[0].image);
        assertNotNull(result.radarImages[0].lonlats);
        assertEquals(4, result.radarImages[0].lonlats.length);
        assertEquals(3.9079000000000015D, result.radarImages[0].lonlats[0], 0.00000001D);
        assertEquals(71.9281517704939D, result.radarImages[0].lonlats[1], 0.000000001D);
        assertEquals(57.9079D, result.radarImages[0].lonlats[2], 0.0001D);
        assertEquals(134.86564822950612D, result.radarImages[0].lonlats[3], 0.000000001D);
        assertNotNull(result.radarImages[19]);


    }

    @Test
    public void serialize() {
        RadarImageResult result = new RadarImageResult();
        result.status = "ok";
        result.radarImages = new RadarImage[3];
        RadarImage radarImage0 = new RadarImage();
        radarImage0.lonlats = new double[] {13.90790000015D, 81.9281517704939D, 57.92323079D,
                134.86564822950612D};
        radarImage0.datetime = System.currentTimeMillis() / 1000L;
        radarImage0.image = "http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606270907.png";
        result.radarImages[0] = radarImage0;
        RadarImage radarImage1 = new RadarImage();
        radarImage1.lonlats = new double[] {18.90790000015D, 98.9281517704939D, 76.92323079D,
                194.86564822950612D};
        radarImage1.datetime = (System.currentTimeMillis() - 60 * 24 * 3600 * 1000L) / 1000L;
        radarImage1.image = "http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201896270907.png";
        result.radarImages[1] = radarImage1;
        RadarImage radarImage2 = new RadarImage();
        radarImage2.lonlats = new double[] {63.90790000015D, 48.9281517704939D, 95.92323079D,
                178.86564822950612D};
        radarImage2.datetime = (System.currentTimeMillis() - 30 * 24 * 3600 * 1000L) / 1000L;
        radarImage2.image = "http://cdn.caiyunapp.com/res/storm_radar/radar_CN01_nmc_fast/" +
                "201606289907.png";
        result.radarImages[2] = radarImage2;

        final Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(MyTypeAdapterFactory.INSTANCE)
                .create();
        String jsonString = gson.toJson(result);

    }

}