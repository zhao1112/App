package com.zhao.testmvvm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.androidkun.xtablayout.XTabLayout;
import com.zhao.testmvvm.BR;
import com.zhao.testmvvm.R;
import com.zhao.testmvvm.databinding.FragmentHomeBinding;
import com.zhao.testmvvm.http.data.AppViewModelFactory;
import com.zhao.testmvvm.ui.home.homepage.HomePageFragment;
import com.zhao.testmvvm.utils.CommonUtil;
import com.zhao.testmvvm.widget.AnimationNestedScrollView;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/9
 * Time: 11:48
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private float LL_SEARCH_MIN_TOP_MARGIN, LL_SEARCH_MAX_TOP_MARGIN, LL_SEARCH_MAX_WIDTH, LL_SEARCH_MIN_WIDTH, TV_TITLE_MAX_TOP_MARGIN;
    private ViewGroup.MarginLayoutParams searchLayoutParams, titleLayoutParams;
    private String[] pageTitle = new String[]{"首页", "手机", "电脑办公", "酒水饮料", "生鲜", "男装", "家居厨具", "母婴童装", "内衣配饰", "家装", "个护清洁", "运动", "美妆", "家电", "图书", "女装", "医药健康", "礼品鲜花", "男鞋", "玩具乐器", "宠物", "爱车", "数码", "箱包", "腕表珠宝", "女鞋", "二手", "工业品", "奢侈品", "生活旅行", "房产", "食品"};

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        initMarqueeView();
        initPage();
    }

    private void initPage() {
        for (int i = 0; i < pageTitle.length; i++) {
            binding.xlClass.addTab(binding.xlClass.newTab().setText(pageTitle[i]));
        }
        binding.vpPager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        binding.vpPager.setOffscreenPageLimit(3);
        binding.xlClass.setupWithViewPager(binding.vpPager);
    }

    private void initMarqueeView() {
        List<String> messages = new ArrayList<>();
        messages.add("大家好，我是孙福生。");
        messages.add("欢迎大家关注我哦！");
        messages.add("GitHub帐号：sunfusheng");
        messages.add("新浪微博：孙福生微博");
        messages.add("个人博客：sunfusheng.com");
        messages.add("微信公众号：孙福生");
        binding.marqueeView.startWithList(messages);
    }

    public void initView() {
        LL_SEARCH_MIN_TOP_MARGIN = CommonUtil.dp2px(getActivity(), 0f);//布局关闭时顶部距离
        LL_SEARCH_MAX_TOP_MARGIN = CommonUtil.dp2px(getActivity(), 40f);//布局默认展开时顶部距离
        LL_SEARCH_MAX_WIDTH = CommonUtil.getScreenWidth(getActivity()) - CommonUtil.dp2px(getActivity(), 30f);//布局默认展开时的宽度
        LL_SEARCH_MIN_WIDTH = CommonUtil.getScreenWidth(getActivity()) - CommonUtil.dp2px(getActivity(), 150);//布局关闭时的宽度
        TV_TITLE_MAX_TOP_MARGIN = CommonUtil.dp2px(getActivity(), 11.5f);

        searchLayoutParams = (ViewGroup.MarginLayoutParams) binding.searchLlSearch.getLayoutParams();
        titleLayoutParams = (ViewGroup.MarginLayoutParams) binding.tvTitle.getLayoutParams();

        binding.searchSvView.setOnAnimationScrollListener(new AnimationNestedScrollView.OnAnimationScrollChangeListener() {
            @Override
            public void onScrollChanged(float dy) {
                float searchLayoutNewTopMargin = LL_SEARCH_MAX_TOP_MARGIN - dy;
                float searchLayoutNewWidth = LL_SEARCH_MAX_WIDTH - dy * 3.0f;//此处 * 1.3f 可以设置搜索框宽度缩放的速率

                float titleNewTopMargin = (float) (TV_TITLE_MAX_TOP_MARGIN - dy * 0.5);

                //处理布局的边界问题
                searchLayoutNewWidth = searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH ? LL_SEARCH_MIN_WIDTH : searchLayoutNewWidth;

                if (searchLayoutNewTopMargin < LL_SEARCH_MIN_TOP_MARGIN) {
                    searchLayoutNewTopMargin = LL_SEARCH_MIN_TOP_MARGIN;
                }

                if (searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH) {
                    searchLayoutNewWidth = LL_SEARCH_MIN_WIDTH;
                }

                float titleAlpha = 255 * titleNewTopMargin / TV_TITLE_MAX_TOP_MARGIN;
                if (titleAlpha < 0) {
                    titleAlpha = 0;
                }

                //设置相关控件的LayoutParams  此处使用的是MarginLayoutParams，便于设置params的topMargin属性
                binding.tvTitle.setTextColor(binding.tvTitle.getTextColors().withAlpha((int) titleAlpha));
                titleLayoutParams.topMargin = (int) titleNewTopMargin;
                binding.tvTitle.setLayoutParams(titleLayoutParams);

                searchLayoutParams.topMargin = (int) searchLayoutNewTopMargin;
                searchLayoutParams.width = (int) searchLayoutNewWidth;
                binding.searchLlSearch.setLayoutParams(searchLayoutParams);
            }
        });
    }

    @Override
    public HomeViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HomeViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.marqueeView.stopFlipping();
    }

    public class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return pageTitle.length;
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return new HomePageFragment();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }
    }
}
