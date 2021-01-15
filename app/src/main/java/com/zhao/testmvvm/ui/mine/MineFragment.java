package com.zhao.testmvvm.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.FragmentMineBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/13
 * Time: 14:39
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MineViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(MineViewModel.class);
    }
}
