package com.yuchenhou.skynow.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuchenhou.skynow.BR;
import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.activity.ViewEventActivity;
import com.yuchenhou.skynow.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchen on 3/16/16.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.BindingHolder> {
    private List<Event> mEvents;

    public interface OnListingClickListener {
        void onClick(Event event);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewDataBinding binding;
        private OnListingClickListener mListener;

        public BindingHolder(View rowView, OnListingClickListener listener) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
            mListener = listener;
            binding.getRoot().setOnClickListener(this);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            if(v.getTag() instanceof Event) {
                mListener.onClick((Event) v.getTag());
            }
        }
    }

    public ListingAdapter() {
        this.mEvents = new ArrayList<>();
    }

    public void addEvent(Event event) {
        this.mEvents.add(event);
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_listing_event, parent, false);
        BindingHolder holder = new BindingHolder(v, event -> openEventInActivity(event, parent.getContext()));
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Event event = mEvents.get(position);
        holder.getBinding().setVariable(BR.event, event);
        holder.getBinding().executePendingBindings();
        holder.itemView.setTag(event);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    private void openEventInActivity(Event ev, Context context) {
        Intent view = new Intent(context, ViewEventActivity.class);
        view.putExtra(ViewEventActivity.EVENT, ev);
        context.startActivity(view);
    }
}
