package com.example.ling.beijingnews.basic;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ling.beijingnews.R;

/**
 * @author ling
 * @des
 * @updateAuthor
 * @updataDes
 */
public class BasePager {
    //上下文
    public final Context context;
    //视图，代表各个不同的页面
    public View rootView;

    //显示标题
    public TextView tv_title;
    //点击弹出侧滑菜单
    public ImageButton ib_menu;
    //加载各个子页面
    public FrameLayout fl_content;

    public BasePager(Context context){

        this.context=context;
        rootView = initView();

    }

    /**
     * 用于初始化公共部分视图，并且初始化加载子视图的FrameLayout
     * @return
     */
    private View initView() {
        View view = View.inflate(context, R.layout.base_pager,null);
        tv_title = view.findViewById(R.id.tv_title);
        ib_menu = view.findViewById(R.id.ib_menu);
        fl_content=view.findViewById(R.id.fl_content);
        return  view;
    }

    /**
     * 初始化数据，当孩子需要初始化数据，或者绑定数据，联网请求数据并且绑定的时候，重写该方法
     */
    public void initData(){


    }
}
