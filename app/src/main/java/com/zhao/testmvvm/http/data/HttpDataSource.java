package com.zhao.testmvvm.http.data;

import com.zhao.testmvvm.bean.ShopBean;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:34
 */
public interface HttpDataSource {
    Observable<BaseResponse<ShopBean>> demoGet(String shopId);
}
