package com.yuchenhou.skynow.presenter;

import android.os.Bundle;

import com.yuchenhou.skynow.data.EventData;
import com.yuchenhou.skynow.fragment.ViewEventFragment;

import nucleus.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuchen on 3/10/16.
 */
public class ViewEventPresenter extends RxPresenter<ViewEventFragment> {

    private static final int REQUEST_LATEST_EVENT = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableLatestCache(REQUEST_LATEST_EVENT,
                () -> EventData.getLatestEvent()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread()),
                ViewEventFragment::setEvent,
                (fragment, throwable) -> fragment.setEvent(null));
    }

    public void requestEvent() {
        start(REQUEST_LATEST_EVENT);
    }
}
