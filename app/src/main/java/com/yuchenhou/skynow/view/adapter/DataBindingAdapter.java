package com.yuchenhou.skynow.view.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by yuchen on 3/14/16.
 */
public class DataBindingAdapter {
    @BindingAdapter("imageResource")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
