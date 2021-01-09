package com.zhao.testmvvm.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
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

    private String[] permissionArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA
    };
    private int timer = 0;

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
        StatusBarUtil.setStatusBar(SplashActivity.this, true, R.color.white, true);
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
        rxPermissions.request(permissionArray)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        handler.sendEmptyMessageDelayed(0, 1000);
                    }
                });
    }

    @Override
    public SplashViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    timer++;
                    if (timer >= 3) {
                        handler.removeMessages(0);
                        startActivity(MainActivity.class);
                        finish();
                    } else {
                        handler.sendEmptyMessageDelayed(0, 1000);
                    }
                    break;
            }
        }
    };
    ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
