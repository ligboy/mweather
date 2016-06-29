package org.ligboy.mweather.api.util;

import java.util.Date;

/**
 * @author Ligboy.Liu ligboy@gmail.com.
 */

public class CyDate extends Date {

    public CyDate() {
    }

    public CyDate(long date) {
        super(date);
    }

    @Deprecated
    public CyDate(int year, int month, int date) {
        super(year, month, date);
    }

    @Deprecated
    public CyDate(int year, int month, int date, int hrs, int min) {
        super(year, month, date, hrs, min);
    }

    @Deprecated
    public CyDate(int year, int month, int date, int hrs, int min, int sec) {
        super(year, month, date, hrs, min, sec);
    }

    @Deprecated
    public CyDate(String s) {
        super(s);
    }
}
