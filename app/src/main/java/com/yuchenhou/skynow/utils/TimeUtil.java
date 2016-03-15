package com.yuchenhou.skynow.utils;

import humanize.time.TimeMillis;

/**
 * Created by yuchen on 3/15/16.
 */
public class TimeUtil {
    private final static long HOUR = 1000 * 60 * 60;
    private final static long DAY = 24 * HOUR;
    private final static long WEEK = 7 * DAY;
    private final static long MONTH = 30 * WEEK;
    private final static long YEAR = 365 * DAY;

    public static TimeMillis getPrecision(long millisLeft) {
        if(millisLeft <= HOUR) {
            // We're not good enough to get to minute precision (eg. solar eclipse)
            // Need to use a better data source
            return TimeMillis.DAY;
        } else if(millisLeft <= HOUR) {
            // Ditto
            return TimeMillis.DAY;
        } else if(millisLeft <= WEEK) {
            return TimeMillis.DAY;
        } else if(millisLeft <= MONTH) {
            return TimeMillis.WEEK;
        } else if(millisLeft <= YEAR) {
            return TimeMillis.MONTH;
        }

        return TimeMillis.MONTH;
    }
}
