package com.zhao.testmvvm.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/9
 * Time: 11:49
 */
public class HomeViewModel extends BaseViewModel<TestRepository> {

    public HomeViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
    }

}
