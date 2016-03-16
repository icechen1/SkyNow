package com.yuchenhou.skynow.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuchenhou.skynow.BR;
import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchen on 3/16/16.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.BindingHolder> {
    private List<Event> mEvents;

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        public ViewDataBinding getBinding() {
            return binding;
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
        BindingHolder holder = new BindingHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final Event event = mEvents.get(position);
        holder.getBinding().setVariable(BR.event, event);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}
