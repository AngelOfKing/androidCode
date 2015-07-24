package com.qianfeng.viewpagetest1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.qianfeng.viewpagetest1.adapters.NewsFragmentAdapter;
import com.qianfeng.viewpagetest1.fragments.NewsFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/8
 * Email: vhly@163.com
 */
public class EnterActivity extends FragmentActivity {

    private ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        pager = (ViewPager) findViewById(R.id.enter_pager);

        List<Fragment> fragments = new LinkedList<>();

        // 指定显示的Fragment
        for (int i = 0; i < 4; i++) {
            NewsFragment fragment = new NewsFragment();

            // Fragment 可以通过参数的传递，来实现显示不同的内容
            // 大部分场景在与格式相同，但显示内容不同的情况

            Bundle args = new Bundle();

            // 传递一个整数给Fragment，用来在内部
            // 显示不同内容
            args.putInt("position", i);

            fragment.setArguments(args);


            fragments.add(fragment);
        }


        // ! 关于 FragmentManager的设置
        NewsFragmentAdapter adapter =
                new NewsFragmentAdapter(
                        getSupportFragmentManager(),
                        fragments
                );


        pager.setAdapter(adapter);
    }
}