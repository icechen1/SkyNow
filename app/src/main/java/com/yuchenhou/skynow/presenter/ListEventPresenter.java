package com.yuchenhou.skynow.presenter;

import android.os.Bundle;

import com.yuchenhou.skynow.SkyNowApp;
import com.yuchenhou.skynow.data.EventData;
import com.yuchenhou.skynow.fragment.ListEventFragment;

import java.util.concurrent.TimeUnit;

import nucleus.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuchen on 3/10/16.
 */
public class ListEventPresenter extends RxPresenter<ListEventFragment> {

    private static final int REQUEST_EVENTS = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableReplay(REQUEST_EVENTS,
                () -> EventData.getInstance(SkyNowApp.getAppContext()).getUpcomingEvents()
                        .filter((event) -> event.getEndDate().getTime() - System.currentTimeMillis() > 0)
                        .delay(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread()),
                ListEventFragment::addEvent,
                (fragment, throwable) -> fragment.addEvent(null));

    }

    public void requestEvent() {
        start(REQUEST_EVENTS);
    }
}
