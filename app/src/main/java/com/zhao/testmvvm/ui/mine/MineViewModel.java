package com.zhao.testmvvm.ui.mine;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/13
 * Time: 14:40
 */
public class MineViewModel extends BaseViewModel<TestRepository> {

    public MineViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
    }
}
