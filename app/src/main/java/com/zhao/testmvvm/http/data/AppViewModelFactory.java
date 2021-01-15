package com.zhao.testmvvm.http.data;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zhao.testmvvm.source.TestRepository;
import com.zhao.testmvvm.ui.classification.ClassificationViewModel;
import com.zhao.testmvvm.ui.home.HomeViewModel;
import com.zhao.testmvvm.ui.home.homepage.HomePageViewModel;
import com.zhao.testmvvm.ui.main.MainViewModel;
import com.zhao.testmvvm.ui.mine.MineViewModel;
import com.zhao.testmvvm.ui.shoppingcart.ShoppingCartViewModel;
import com.zhao.testmvvm.ui.splash.SplashViewModel;

/**
 * Created by goldze on 2019/3/26.
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final TestRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, TestRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ClassificationViewModel.class)) {
            return (T) new ClassificationViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(ShoppingCartViewModel.class)) {
            return (T) new ShoppingCartViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(MineViewModel.class)) {
            return (T) new MineViewModel(mApplication, mRepository);
        } else if (modelClass.isAssignableFrom(HomePageViewModel.class)) {
            return (T) new HomePageViewModel(mApplication, mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
