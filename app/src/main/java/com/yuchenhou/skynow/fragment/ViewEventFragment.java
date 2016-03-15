package com.yuchenhou.skynow.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.databinding.FragmentEventBinding;
import com.yuchenhou.skynow.model.Event;
import com.yuchenhou.skynow.presenter.ViewEventPresenter;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

@RequiresPresenter(ViewEventPresenter.class)
public class ViewEventFragment extends NucleusSupportFragment<ViewEventPresenter> {

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
        return mBinding.getRoot();
    }

    public FragmentEventBinding getViewDataBinding() {
        return mBinding;
    }

    public void setEvent(Event event) {
        mBinding.setEvent(event);
        mBinding.invalidateAll();
    }
}
