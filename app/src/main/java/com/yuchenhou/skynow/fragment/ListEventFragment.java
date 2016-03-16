package com.yuchenhou.skynow.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.model.Event;
import com.yuchenhou.skynow.presenter.ListEventPresenter;
import com.yuchenhou.skynow.view.adapter.ListingAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusSupportFragment;

@RequiresPresenter(ListEventPresenter.class)
public class ListEventFragment extends NucleusSupportFragment<ListEventPresenter> {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ListingAdapter mAdapter;

    public ListEventFragment() {
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
        View v = inflater.inflate(R.layout.fragment_listing, container, false);
        ButterKnife.bind(this, v);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ListingAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    public void addEvent(Event event) {
        mAdapter.addEvent(event);
    }
}
