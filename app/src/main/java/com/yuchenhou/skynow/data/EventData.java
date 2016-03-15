package com.yuchenhou.skynow.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.yuchenhou.skynow.model.Event;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import rx.Observable;

/**
 * Created by yuchen on 3/10/16.
 */
public class EventData {

    private static EventData sInstance;
    private Event[] mParsedEvents;

    EventData(Context c) {
        loadAllEvents(c);
    }

    public static EventData getInstance(Context c) {
        if(sInstance == null) {
            sInstance = new EventData(c);
        }
        return sInstance;
    }

    private void loadAllEvents(Context c) {
        AssetManager assetManager = c.getAssets();
        InputStream ims = null;
        try {
            ims = assetManager.open("items.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Reader reader = new InputStreamReader(ims);

        mParsedEvents = gson.fromJson(reader, Event[].class);
    }

    public Observable<Event> getLatestEvent() {
        return Observable.just(mParsedEvents[9]);
    }
}
