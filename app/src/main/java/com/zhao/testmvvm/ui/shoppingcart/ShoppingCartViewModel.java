package com.zhao.testmvvm.ui.shoppingcart;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.source.TestRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/9
 * Time: 16:42
 */
public class ShoppingCartViewModel extends BaseViewModel<TestRepository> {

    public ShoppingCartViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
    }

}
