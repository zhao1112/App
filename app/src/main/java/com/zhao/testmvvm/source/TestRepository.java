package com.zhao.testmvvm.source;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.zhao.testmvvm.bean.ShopBean;
import com.zhao.testmvvm.http.data.HttpDataSource;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/31
 * Time: 14:15
 */
public class TestRepository extends BaseModel implements HttpDataSource {

    private volatile static TestRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    private TestRepository(@NonNull HttpDataSource httpDataSource) {
        this.mHttpDataSource = httpDataSource;
    }

    public static TestRepository getInstance(HttpDataSource httpDataSource) {
        if (INSTANCE == null) {
            synchronized (TestRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TestRepository(httpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<BaseResponse<ShopBean>> demoGet(String shopId) {
        return mHttpDataSource.demoGet(shopId);
    }
}
