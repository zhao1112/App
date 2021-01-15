package com.zhao.testmvvm.ui.home.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/15
 * Time: 10:18
 */
public class ImageHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ImageHolder(@NonNull View view) {
        super(view);
        this.imageView = (ImageView) view;
    }
}
