package com.yuchenhou.skynow.model;

import android.util.Log;

import com.yuchenhou.skynow.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yuchen on 3/10/16.
 */
public class Event {
    public enum EventType {
        LUNAR, SOLAR, METEOR, EARTH, SATELITE, PLANETS
    }

    public final static int DURATION_DAY = 1000 * 60 * 60 * 24;

    public final String name;
    public final EventType type;
    public final int year;
    public final String date;
    public final String description;

    public Event(String name, String description, EventType type, String date, int year) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.date = date;
        this.year = year;
    }

    public int getIcon() {
        if(name == null) {
            return 0;
        }

        if(name.contains("New Moon")){
            return R.drawable.icon_new_moon_96;
        } else if(name.contains("Full Moon")) {
            return R.drawable.icon_full_moon_96;
        } else if(name.contains("Lunar Eclipse")) {
            return R.drawable.icon_moon_96;
        } else if(name.contains("Solar Eclipse")) {
            return R.drawable.icon_sun_96;
        } else if(name.contains("Meteor")) {
            return R.drawable.icon_asteroid_96;
        }

        // TODO: handle planets

        return R.drawable.icon_calendar_96;
    }

    public Date getStartDate() {
        if(date == null || year == 0) {
            return null;
        }

        if(date.contains(",")) {
            // More than 1 day
            String[] parts = date.split(" ");
            return parseDate(year, parts[0] + " " + parts[1].replace(",",""));
        } else {
            return parseDate(year, date);
        }
    }

    public Date getEndDate() {
        if(date == null || year == 0) {
            return null;
        }

        Date end;
        if(date.contains(",")) {
            // More than 1 day
            String[] parts = date.split(" ");
            end = parseDate(year, parts[0] + " " + parts[2].replace(" ", ""));
        } else {
            end = parseDate(year, date);
        }

        if(end != null) {
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(end);
            endCal.set(Calendar.HOUR, 23);
            endCal.set(Calendar.MINUTE, 59);
            endCal.set(Calendar.SECOND, 59);

            return endCal.getTime();
        } else {
            return null;
        }
    }

    public static Date parseDate(int year, String date) {
        DateFormat df = new SimpleDateFormat("yyyy MMM dd", Locale.ENGLISH);
        try {
            return df.parse(year + " " + date);
        } catch (ParseException e) {
            Log.e("SkyNow", "Error parsing time: " + e);
            return null;
        }
    }

    public String getDateDuration() {
        Date startDate = getStartDate();
        Date endDate = getEndDate();

        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.ENGLISH);
        String start = df.format(startDate);

        if(startDate.compareTo(endDate) <= DURATION_DAY){
            String end = df.format(endDate);
            return start + " - " + end;
        } else {
            return start;
        }
    }
}
