package com.zhao.testmvvm.app;

import com.zhao.testmvvm.R;
import com.zhao.testmvvm.ui.main.MainActivity;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.crash.CaocConfig;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:12
 */
public class TestApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true);
        //初始化全局异常崩溃
        initCrash();
    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MainActivity.class) //重新启动后的activity
                .apply();
    }

}
