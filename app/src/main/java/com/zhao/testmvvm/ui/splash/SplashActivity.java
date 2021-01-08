package com.zhao.testmvvm.ui.splash;

import android.Manifest;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.ActivitySplashBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;
import com.zhao.testmvvm.ui.main.MainActivity;
import com.zhao.testmvvm.utils.StatusBarUtil;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/7
 * Time: 17:03
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setStatusBar(SplashActivity.this, false, R.color.white, true);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        //注册监听相机权限的请求
        viewModel.requestCameraPermissions.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                requestCameraPermissions();
            }
        });
    }

    /**
     * 请求相机权限
     */
    private void requestCameraPermissions() {
        //请求打开相机权限
        RxPermissions rxPermissions = new RxPermissions(SplashActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            startActivity(MainActivity.class);
                        } else {
                            startActivity(MainActivity.class);
                        }
                    }
                });
    }

    @Override
    public SplashViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }
}
