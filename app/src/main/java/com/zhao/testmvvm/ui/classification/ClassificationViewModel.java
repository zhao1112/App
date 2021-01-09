package com.zhao.testmvvm.ui.classification;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/9
 * Time: 16:35
 */
public class ClassificationViewModel extends BaseViewModel<TestRepository> {

    public ClassificationViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
    }

}
