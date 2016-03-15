package com.yuchenhou.skynow.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.databinding.FragmentEventBinding;
import com.yuchenhou.skynow.model.Event;
import com.yuchenhou.skynow.presenter.ViewEventPresenter;
import com.yuchenhou.skynow.utils.TimeUtil;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

import static humanize.Humanize.naturalTime;

@RequiresPresenter(ViewEventPresenter.class)
public class ViewEventFragment extends NucleusSupportFragment<ViewEventPresenter> {

    @Bind(R.id.countdown)
    TextView mCountDownTimer;

    private FragmentEventBinding mBinding;

    public ViewEventFragment() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getPresenter().requestEvent();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false);
        ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    public FragmentEventBinding getViewDataBinding() {
        return mBinding;
    }

    public void setEvent(Event event) {
        mBinding.setEvent(event);
        mBinding.invalidateAll();

        long timeLeft = event.getStartDate().getTime() - new Date().getTime();
        if(timeLeft < 0) {
            timeLeft = 0;
        }

        new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                mCountDownTimer.setText(naturalTime(new Date(), event.getStartDate(), TimeUtil.getPrecision(millisUntilFinished)));
            }

            public void onFinish() {
                mCountDownTimer.setText(getString(R.string.happening_now));
            }
        }.start();
    }
}
