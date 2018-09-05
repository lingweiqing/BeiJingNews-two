package com.example.ling.beijingnews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ling.beijingnews.R;
import com.example.ling.beijingnews.fragment.ContentFragment;
import com.example.ling.beijingnews.fragment.LeftMenuFragment;
import com.example.ling.beijingnews.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //主页面
        setContentView(R.layout.activity_main);
        //设置侧滑菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        //slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);

        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页被占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dipToPix(MainActivity.this,200));

        initFragment();

    }

    /**
     * 初始化Fragment
     */

    private void initFragment() {

        //1.得到FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //2.开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //3.替换
        fragmentTransaction.replace(R.id.fl_main_content,new ContentFragment() , MAIN_CONTENT_TAG);//主页
        fragmentTransaction.replace(R.id.fl_leftmenu,new LeftMenuFragment() , LEFTMENU_TAG);//左侧菜单

        //4.提交

        fragmentTransaction.commit();
    }
}
