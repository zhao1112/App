package com.zhao.testmvvm.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/8
 * Time: 17:19
 */
public class SplashViewModel extends BaseViewModel<TestRepository> {

    //使用Observable
    public SingleLiveEvent<Boolean> requestCameraPermissions = new SingleLiveEvent<>();

    public SplashViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
        requestCameraPermissions.call();
    }
}
