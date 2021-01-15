package com.zhao.testmvvm.ui.home.homepage;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/14
 * Time: 14:27
 */
public class HomePageViewModel extends BaseViewModel<TestRepository> {

    public HomePageViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);

    }
}
