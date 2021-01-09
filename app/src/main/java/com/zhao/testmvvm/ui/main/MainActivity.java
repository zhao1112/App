package com.zhao.testmvvm.ui.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.ActivityMainBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;
import com.zhao.testmvvm.ui.classification.ClassificationFragment;
import com.zhao.testmvvm.ui.home.HomeFragment;
import com.zhao.testmvvm.ui.shoppingcart.ShoppingCartFragment;
import com.zhao.testmvvm.utils.StatusBarUtil;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil.setStatusBar(MainActivity.this, true, R.color.white, true);
    }

    @Override
    public void initData() {
        super.initData();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {

    }

    private void initBottomTab() {
        NavigationController navigationController = binding.tab.custom()
                .addItem(newItem(R.mipmap.icon_home_false, R.mipmap.icon_home, "首页"))
                .addItem(newItem(R.mipmap.icon_class_false, R.mipmap.icon_class, "分类"))
                .addItem(newItem(R.mipmap.icon_cart_false, R.mipmap.icon_cart, "购物车"))
                .build();
        binding.viewPager.setAdapter(new TestViewPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount()));
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);

        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                switch (index) {
                    case 0:
                        StatusBarUtil.setStatusBar(MainActivity.this, true, R.color.white, true);
                        break;
                    case 1:
                    case 2:
                        StatusBarUtil.setStatusBar(MainActivity.this, true, R.color.white, false);
                        break;
                }
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(Color.parseColor("#FFA715"));
        return normalItemView;
    }

    public class TestViewPagerAdapter extends FragmentPagerAdapter {

        private int size;

        public TestViewPagerAdapter(FragmentManager fm, int size) {
            super(fm);
            this.size = size;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ClassificationFragment();
                case 2:
                    return new ShoppingCartFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return size;
        }
    }

    @Override
    public MainViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }
}
