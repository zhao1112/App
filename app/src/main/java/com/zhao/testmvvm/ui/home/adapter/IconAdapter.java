package com.zhao.testmvvm.ui.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhao.testmvvm.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/15
 * Time: 11:05
 */
public class IconAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public IconAdapter() {
        super(R.layout.item_home_icon);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        Glide.with(getContext())
                .load(s)
                .into((ImageView) baseViewHolder.findView(R.id.iv_icon));
    }
}
