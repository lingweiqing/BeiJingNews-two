package com.example.ling.beijingnews.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ling
 * @des  基本的Fragment,contentfragment和left menufragment会继承他
 * @updateAuthor
 * @updataDes
 */
public abstract class basicFragment extends Fragment {

    public Activity context;//MainActivity,上下文

    /**
     * 当Fragment被系统创建时被回调
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    //当视图view创建时被回调

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    //让孩子实现自己的视图，实现特有的结果
    public abstract View initView() ;

    //当活动被创建后被回调

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 如果页面没有数据，联网请求数据，并且绑定到initView初始化的视图
     * 否则直接
     */

    public void initData(){

    }
}
