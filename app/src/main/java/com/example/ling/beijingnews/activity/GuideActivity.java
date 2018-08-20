package com.example.ling.beijingnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.ling.beijingnews.R;
import com.example.ling.beijingnews.SplashActivity;
import com.example.ling.beijingnews.utils.CacheUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private static final String TAG = GuideActivity.class.getSimpleName();
    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageviews;
    private ImageView iv_red_point;
    private int leftmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager)findViewById(R.id.viewpager);
        btn_start_main = (Button)findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout)findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView)findViewById(R.id.iv_red_point);

        //准备数据
        int [] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        imageviews = new ArrayList<>();
        for (int i = 0 ;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            //添加到集合中
            imageviews.add(imageView);
            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            //单位是像素，需要做适配
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            if (i!=0){
                //不包括第0个点，所有的点距离左边有十个像素
                params.leftMargin=10;
            }
            point.setLayoutParams(params);
            // 添加到线性布局里面

            ll_point_group.addView(point);
        }
        //设置viewPager
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageviews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return object==view;
            }

            /**
             *
             * @param container viewpager
             * @param position  要创建页面的位置
             * @return  返回和创建当前页面的值
             */
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                //添加到容器中
                ImageView imageView = imageviews.get(position);
                //return super.instantiateItem(container, position);
                container.addView(imageView);
                return imageView;
            }

            /**
             *
             * @param container  viewpager
             * @param position  要销毁的页面的位置
             * @param object    要销毁的页面
             */

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });
        
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                
                //执行不止一次
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                //间距=第1个点距离左边的距离-第0个点距离左边的距离
                leftmax = ll_point_group.getChildAt(1).getLeft()-ll_point_group.getChildAt(0).getLeft();
                //得到屏幕滑动的百分比
                viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    /**
                     *
                     * @param position 当前滑动页面的位置
                     * @param positionOffset    页面滑动的百分比
                     * @param positionOffsetPixels  滑动的像素
                     */
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        Log.e(TAG,"position=="+position+",positionOffset=="+positionOffset+"positionOffsetPixels"+positionOffsetPixels);

                        int leftmargin = (int) (position*leftmax+(positionOffset*leftmax));
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
                        params.leftMargin=leftmargin;
                        iv_red_point.setLayoutParams(params);


                    }

                    /**
                     *
                     * @param position 当前被选中的页面的位置
                     */

                    @Override
                    public void onPageSelected(int position) {

                        if (position==imageviews.size()-1){
                            //最后一个页面
                            btn_start_main.setVisibility(View.VISIBLE);

                        }else{

                            //其他界面
                            btn_start_main.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                btn_start_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //1.保存曾经进入过主页面
                        CacheUtil.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);
                        //2.跳转到主页面
                        Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                        startActivity(intent);
                        //3.关闭引导界面
                    }
                });



            }
        });
    }
}
