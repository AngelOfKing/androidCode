package com.qianfeng.viewpagetest1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/8
 * Email: vhly@163.com
 */

/**
 * 用于给ViewPager 设置Fragment的，构造方法必须要有FragmentManager
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {

    /**
     * 存储了需要显示的所有的Fragment
     */
    private List<Fragment> fragments;

    /**
     * 构造方法，必须要写
     *
     * @param fm
     */
    public NewsFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * FragmentPagerAdapter 扩展了PagerAdapter，通过这个方法
     * getItem 来返回实际的内容，在自身实现的内部，getItem调用之后
     * 就会把界面显示出来。
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Log.d("Life", "Adapter getItem " + position);

        return fragments.get(position);
    }

    /**
     * 获取页数
     *
     * @return
     */
    @Override
    public int getCount() {
        int ret = 0;

        if (fragments != null) {
            ret = fragments.size();
        }

        return ret;
    }
}
