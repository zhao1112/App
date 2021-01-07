package com.zhao.testmvvm.http.data;

import com.zhao.testmvvm.bean.ShopBean;
import com.zhao.testmvvm.http.service.ApiService;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:27
 */
public class HttpDataSourceImpl implements HttpDataSource {

    private ApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(ApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    private HttpDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<BaseResponse<ShopBean>> demoGet(String  shopId) {
        return apiService.demoGet(shopId);
    }
}
