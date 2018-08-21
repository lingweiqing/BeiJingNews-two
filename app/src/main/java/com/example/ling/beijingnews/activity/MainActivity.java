package com.example.ling.beijingnews.activity;

import android.os.Bundle;

import com.example.ling.beijingnews.R;
import com.example.ling.beijingnews.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //主页面
        setContentView(R.layout.activity_main);
        //设置侧滑菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.activity_rightmenu);

        //设置显示模式
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页被占据的宽度
        slidingMenu.setBehindOffset(DensityUtil.dipToPix(MainActivity.this,200));

    }
}
