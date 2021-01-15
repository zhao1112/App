package com.zhao.testmvvm.ui.home.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/15
 * Time: 10:13
 */
public class ImageAdapter extends BannerAdapter<String, ImageHolder> {


    public ImageAdapter(List<String> datas) {
        super(datas);
    }

    //更新数据
    public void updateData(List<String> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, String data, int position, int size) {
        Glide.with(holder.itemView)
                .load(data)
                .into(holder.imageView);
    }
}
