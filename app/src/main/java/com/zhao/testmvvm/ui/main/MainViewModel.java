package com.zhao.testmvvm.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zhao.testmvvm.bean.ShopBean;
import com.zhao.testmvvm.source.TestRepository;

import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:13
 */
public class MainViewModel extends BaseViewModel<TestRepository> {

    public MainViewModel(@NonNull Application application, TestRepository model) {
        super(application, model);
    }

    public BindingCommand imageOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getDemoGet();
        }
    });

    public void getDemoGet() {
        model.demoGet("739072031093424128")
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                .doOnSubscribe(this)//请求与ViewModel周期同步
                .subscribe(new DisposableObserver<BaseResponse<ShopBean>>() {
                    @Override
                    public void onNext(BaseResponse<ShopBean> stringBaseResponse) {
                        if (stringBaseResponse.getCode() == 0) {
                            ToastUtils.showShort("-----" + stringBaseResponse.getResult().getShopModel().getSId());
                        } else {
                            ToastUtils.showShort("請求錯誤");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        dismissDialog();
                    }
                });
    }
}
