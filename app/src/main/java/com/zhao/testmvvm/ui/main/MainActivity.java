package com.zhao.testmvvm.ui.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.ActivityMainBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;

import me.goldze.mvvmhabit.base.BaseActivity;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }
}
