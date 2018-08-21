package com.example.ling.beijingnews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.ling.beijingnews.basic.basicFragment;

/**
 * @author ling
 * @des  左侧菜单的fragment
 * @updateAuthor
 * @updataDes
 */
public class LeftMenuFragment extends basicFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(23);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();

        textView.setText("左侧页面");
    }
}
