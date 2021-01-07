package com.zhao.testmvvm.http.service;

import com.zhao.testmvvm.bean.ShopBean;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:19
 */
public interface ApiService {
    @GET("api/shopshowmodel/shopshowmodel/getshopmodelmodule")
    Observable<BaseResponse<ShopBean>> demoGet(@Query("shopId") String shopId);
}
