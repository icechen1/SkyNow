package com.yuchenhou.skynow.model;

import java.util.Date;

/**
 * Created by yuchen on 3/10/16.
 */
public class Event {
    public enum EventType {
        LUNAR, SOLAR, METEOR, EARTH, SATELITE, PLANETS
    }

    public final String name;
    public final EventType type;
    public final Date startDate;
    public final Date endDate;
    public final String description;

    public Event(String name, String description, EventType type, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
