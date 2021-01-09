package com.zhao.testmvvm.ui.shoppingcart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.FragmentShoppingcartBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/9
 * Time: 16:41
 */
public class ShoppingCartFragment extends BaseFragment<FragmentShoppingcartBinding, ShoppingCartViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ShoppingCartViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(ShoppingCartViewModel.class);
    }
}
