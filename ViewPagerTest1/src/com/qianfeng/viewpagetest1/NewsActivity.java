package com.qianfeng.viewpagetest1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.RadioGroup;
import com.qianfeng.viewpagetest1.adapters.NewsFragmentAdapter;
import com.qianfeng.viewpagetest1.fragments.NewsFragment;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/8
 * Email: vhly@163.com
 */

/**
 * 模拟网易新闻，实现ViewPager + RadioGroup 的联动
 */
public class NewsActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager pager;

    private RadioGroup tabBar;
    private NewsFragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // 联动的操作：
        // 1. 必须先初始化两个对象 pager, tabBar
        // 2. 设置 RadioGroup 选中的事件
        // 3. 设置 ViewPage 页面切换的事件

        pager = (ViewPager) findViewById(R.id.news_pager);

        tabBar = (RadioGroup) findViewById(R.id.news_tab_bar);

        // 2.1 RadioGroup的事件
        tabBar.setOnCheckedChangeListener(this);

        FragmentManager manager = getSupportFragmentManager();
        LinkedList<Fragment> fragments = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            NewsFragment fragment = new NewsFragment();

            Bundle args = new Bundle();

            args.putInt("position", i);

            fragment.setArguments(args);

            fragments.add(fragment);
        }

        adapter = new NewsFragmentAdapter(manager, fragments);



        // 设置 ViewPager 滚动，动态切换RadioGroup

        pager.addOnPageChangeListener(this);

        //////////////////////////////////

        pager.setAdapter(adapter);


        ///////////////////////////////////

        // 注意： ViewPager 在第一次显示的时候
        // 并没有调用 onPageSelected 回调
        // 因此，RadioGroup 需要手动选中第一个条目
        tabBar.check(R.id.tab_home);


    }

    /**
     * RadioGroup 的切换点击回调
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = 0;
        switch (checkedId) {
            case R.id.tab_home: // 新闻
                index = 0;
                break;
            case R.id.tab_read:  // 阅读
                index = 1;
                break;
            case R.id.tab_video: // 视听
                index = 2;
                break;
            case R.id.tab_discover: // 发现
                index = 3;
                break;
            case R.id.tab_personal:  // 我
                index = 4;
                break;
        }

        // 切换当前ViewPager显示第几页，从0开始
        pager.setCurrentItem(index);

    }

    //////////////////////////////////////

    /**
     * 当页面拖拽滚动的时候，会频繁的调用这个方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 当页面切换完成的时候，进行的回调
     * @param position 显示第几页
     */
    @Override
    public void onPageSelected(int position) {

        Log.d("Pager", "onPageSelected " + position);

        // TODO 进行联动，更新RadioGroup (又叫做指示器 Indicator)
        int checkId = R.id.tab_home;
        switch (position){
            case 0:
                checkId = R.id.tab_home;
                break;
            case 1:
                checkId = R.id.tab_read;
                break;
            case 2:
                checkId = R.id.tab_video;
                break;
            case 3:
                checkId = R.id.tab_discover;
                break;
            case 4:
                checkId = R.id.tab_personal;
                break;
        }
        tabBar.check(checkId);
    }

    /**
     * 页面滚动的状态
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}