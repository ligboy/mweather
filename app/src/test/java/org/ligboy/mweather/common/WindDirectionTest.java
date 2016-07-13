package org.ligboy.mweather.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */
public class WindDirectionTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void valueOf() throws Exception {
        Assert.assertEquals(WindDirection.SouthWest, WindDirection.valueOf(219.63F));
        Assert.assertEquals(WindDirection.NorthEast, WindDirection.valueOf(383.63F));
        Assert.assertEquals(WindDirection.NorthWest, WindDirection.valueOf(-383.63F));
        Assert.assertEquals(WindDirection.South, WindDirection.valueOf(-180.63F));
    }

}