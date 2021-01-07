package com.zhao.testmvvm.http.data;

import com.zhao.testmvvm.http.RetrofitClient;
import com.zhao.testmvvm.http.service.ApiService;
import com.zhao.testmvvm.source.TestRepository;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:38
 */
public class Injection {
    public static TestRepository provideDemoRepository() {
        //网络API服务
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        return TestRepository.getInstance(httpDataSource);
    }
}
