package com.example.ling.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.ling.beijingnews.basic.BasePager;

/**
 * @author ling
 * @des
 * @updateAuthor
 * @updataDes
 */
public class GovaffairPager extends BasePager {

    public GovaffairPager(Context context ){

        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //1设置标题
        tv_title.setText("政要指南");
        //联网请求得到数据，创建视图
        TextView textView = new TextView(context);

        textView.setGravity(Color.RED);
        textView.setTextSize(25);
        //3，把子视图添加到basePager的FrameLayout中
        fl_content.addView(textView);

        //绑定数据
        textView.setText("新闻内容");
    }
}
