package com.ns.yc.lifehelper.ui.find.contract;


import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.ns.yc.lifehelper.base.BaseDelegateAdapter;
import com.ns.yc.lifehelper.base.BasePresenter;
import com.ns.yc.lifehelper.base.BaseView;
import com.ns.yc.lifehelper.ui.main.view.activity.MainActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/11/21
 * 描    述：数据页面
 * 修订历史：
 * ================================================
 */
public interface FindFragmentContract {

    //View(activity/fragment)继承，需要实现的方法
    interface View extends BaseView {
        //设置轮播图
        void setBanner(Banner mBanner, ArrayList<String> arrayList);
        void setOnclick(int position);
        void setMarqueeClick(int position);
        void setGridClick(int position);
        void setGridClickThird(int position);
        void setGridClickFour(int position);
    }

    //Presenter控制器
    interface Presenter extends BasePresenter {
        void setActivity(MainActivity activity);
        //初始化
        DelegateAdapter initRecyclerView(RecyclerView recyclerView);
        BaseDelegateAdapter initBannerAdapter();
        BaseDelegateAdapter initGvMenu();
        BaseDelegateAdapter initMarqueeView();
        BaseDelegateAdapter initTitle(String title);
        BaseDelegateAdapter initList1();
        BaseDelegateAdapter initList2();
        BaseDelegateAdapter initList3();
        BaseDelegateAdapter initList4();
        BaseDelegateAdapter initList5();
    }


}
