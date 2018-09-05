package com.example.ling.beijingnews.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ling.beijingnews.R;
import com.example.ling.beijingnews.basic.BasePager;
import com.example.ling.beijingnews.basic.basicFragment;
import com.example.ling.beijingnews.pager.GovaffairPager;
import com.example.ling.beijingnews.pager.HomePager;
import com.example.ling.beijingnews.pager.NewsCenterPager;
import com.example.ling.beijingnews.pager.SettingPager;
import com.example.ling.beijingnews.pager.SmartServicePager;
import com.example.ling.beijingnews.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * @author ling
 * @des
 * @updateAuthor
 * @updataDes
 */
public class ContentFragment extends basicFragment {

    private TextView textView;
    //2.初始化控件
    @ViewInject(R.id.viewpager)
    private NoScrollViewPager  viewpager;
    @ViewInject((R.id.rg_main))
    private RadioGroup rg_main;
    //视图，代表各个不同的页面
    private ArrayList<BasePager> mBasePagers;




    @Override
    public void initData() {
        super.initData();
        //初始化五个页面，并且放入集合中
        mBasePagers = new ArrayList<>();
        mBasePagers.add(new HomePager(context));
        mBasePagers.add(new NewsCenterPager(context));
        mBasePagers.add(new SmartServicePager(context));
        mBasePagers.add(new GovaffairPager(context));
        mBasePagers.add(new SettingPager(context));

        //textView.setText("左侧页面");
        //设置默认选中选项
        rg_main.check(R.id.rb_home);
        //设置viewpager的适配器
        viewpager.setAdapter(new contentFragmentAdapter(mBasePagers));

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        /**
         *
         * @param radioGroup  RadioGroup
         * @param checkedId 被选中的RadioGrouButton的ID
         */

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

            switch (checkedId){

                case R.id.rb_home:
                    viewpager.setCurrentItem(0,false);
                    break;
                case R.id.rb_newscenter:
                    viewpager.setCurrentItem(1,false);
                    break;
                case R.id.rb_smatservice:
                    viewpager.setCurrentItem(2,false);//false参数：无动画
                    break;
                case R.id.rb_govaffair:
                    viewpager.setCurrentItem(3,false);
                    break;
                case R.id.rb_setting:
                    viewpager.setCurrentItem(4,false);
                    break;
            }

        }
    }

    class contentFragmentAdapter extends PagerAdapter{
        private final ArrayList<BasePager> mBasePagers;
        public contentFragmentAdapter(ArrayList<BasePager> BasePagers){
            this.mBasePagers = BasePagers;
        }
        @Override
        public int getCount() {
            return mBasePagers.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager BasePager =  mBasePagers.get(position);//各个页面的实例
            View rootView = BasePager.rootView;//各个子页面
            //调用各个页面的initData()
            BasePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    @Override
    public View initView() {
//        textView = new TextView(context);
//        textView.setTextSize(23);
//        textView.setTextColor(Color.RED);
//        textView.setGravity(Gravity.CENTER);

        View view = View.inflate(context, R.layout.content_frafment,null);
//        viewpager = (ViewPager)view.findViewById(R.id.viewpager);
//        rg_main = (RadioGroup)view.findViewById(R.id.rg_main);
        //1.把视图注入到框架中，让contentFragment.this和View关联起来
        x.view().inject(ContentFragment.this,view);
        return  view;
    }
}
