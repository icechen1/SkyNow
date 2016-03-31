package com.yuchenhou.skynow.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.yuchenhou.skynow.SkyNowApp;
import com.yuchenhou.skynow.activity.ListEventActivity;
import com.yuchenhou.skynow.data.EventData;
import com.yuchenhou.skynow.model.Event;

import nucleus.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuchen on 3/10/16.
 */
public class NextEventPresenter extends RxPresenter<ListEventActivity> {
    private static final int REQUEST_LATEST_EVENT = 1;
    private static final String EVENT = "EVENT";

    private Event mEvent;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        // Recover our event if saved
        if (savedState != null) {
            mEvent = savedState.getParcelable(EVENT);
        }

        restartableLatestCache(REQUEST_LATEST_EVENT,
                () -> EventData.getInstance(SkyNowApp.getAppContext()).getLatestEvent()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .take(1),
                (activity, event) -> activity.setNextEvent(event),
                (activity, throwable) -> activity.setNextEvent(null));

    }

    @Override
    protected void onSave(@NonNull Bundle state) {
        super.onSave(state);
        state.putParcelable(EVENT, mEvent);
    }

    public void requestEvent() {
        start(REQUEST_LATEST_EVENT);
    }

}
