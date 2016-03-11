package com.yuchenhou.skynow.data;

import com.yuchenhou.skynow.model.Event;

import java.util.Date;

import rx.Observable;

/**
 * Created by yuchen on 3/10/16.
 */
public class EventData {
    public static Observable<Event> getLatestEvent() {
        return Observable.just(new Event("Fake", "Fake event", Event.EventType.EARTH, new Date(), new Date()));
    }
}
