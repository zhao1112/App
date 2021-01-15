package com.zhao.testmvvm.ui.home.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.FragmentHomepageBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;
import com.zhao.testmvvm.ui.home.adapter.IconAdapter;
import com.zhao.testmvvm.ui.home.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/14
 * Time: 14:25
 */
public class HomePageFragment extends BaseFragment<FragmentHomepageBinding, HomePageViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_homepage;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public HomePageViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HomePageViewModel.class);
    }

    @Override
    public void initData() {
        super.initData();
        initBanner();
    }

    private void initBanner() {
        List<String> list = new ArrayList<>();
        list.add("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/8b82b9014a90f6030add233a3b12b31bb051ed5a.jpg");
        list.add("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/1b4c510fd9f9d72ad1ddb8e3d52a2834359bbb97.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2609208484,1506564659&fm=26&gp=0.jpg");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fdc37603e2e9bf226cf06968d8cc5e70dc3260e1c.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613269564&t=f67c22e09fe2edacb40c1e35c6625f5c");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F11%2F7601%2F7f58a0d55d176948.jpeg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613269587&t=0d92a673a5b2a4f1e3f7dc8bdabf15a1");

        ImageAdapter imageAdapter = new ImageAdapter(list);
        binding.banner.setAdapter(imageAdapter)
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        ToastUtils.showShort((String) data + "---" + position);
                    }
                });

        IconAdapter iconAdapter = new IconAdapter();
        binding.view.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        binding.view.setAdapter(iconAdapter);
        iconAdapter.addData(list);
        iconAdapter.addData(list);
    }
}
