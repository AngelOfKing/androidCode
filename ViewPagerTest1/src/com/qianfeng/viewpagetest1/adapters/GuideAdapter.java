package com.qianfeng.viewpagetest1.adapters;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/8
 * Email: vhly@163.com
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.qianfeng.viewpagetest1.R;

import java.util.List;

/**
 * 继承PagerAdapter，用于程序第一次启动的教程页面显示
 * 左右切换用的
 * 显示的都是图片
 */
public class GuideAdapter extends PagerAdapter {

    public static final String TAG = "Guide";
    private Context context;

    private List<Integer> images;
    private LayoutInflater inflater;

    private View.OnClickListener onClickListener;

    public GuideAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;

        inflater = LayoutInflater.from(context);

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 返回显示多少页
     *
     * @return
     */
    @Override
    public int getCount() {
        // 根据需求来返回
        int ret = 0;

        if (images != null) {
            ret = images.size();
        }

        Log.d(TAG, "getCount " + ret);

        return ret;
    }

    /**
     * 用于检查 参数view 是否是从 object 对象转换而来的。
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // !!! PagerAdapter 必须要重写两个方法：
    //     1) 创建视图的方法
    //     2) 销毁视图的方法

    /**
     * instantiateItem 必须要重写的
     * 并且不能够调用 super的方法
     *
     * @param container
     * @param position  当前是第几页
     * @return Object 返回的，通常就是创建的View，
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        // TODO 返回加载的View

        Log.d(TAG, "instantiateItem " + position);


        // 实现引导页 最后一个页面包含 图片和按钮的功能

        // 1. 判断 position 是不是最后一个

        if (position == images.size() - 1) {
            // TODO 加载最后一个

            // PagerAdapter 加载布局的时候，先加进来，采用三个参数 最后一个 false
            // 加载的布局最后采用 container.addView 就可以添加了。
            ret = inflater.inflate(R.layout.page_guide_last, container, false);

            ImageView imageView = (ImageView) ret.findViewById(R.id.guide_last_image);

            imageView.setImageResource(images.get(position));

            Button btn = (Button) ret.findViewById(R.id.guide_last_button);

            btn.setText("开始体验");

            btn.setOnClickListener(onClickListener);

            // 对于加载的布局，还是采用ListView相似的方式，先加载，后添加
            container.addView(ret);

        } else {
            // 关于View的加载，可以通过:
            // 1. 代码创建控件的方式;
            // 2. LayoutInflater 加载布局的方式.
            // 代码创建控件使用的构造是一个参数的方法，参数为上下文 Context
            ImageView imageView = new ImageView(context);

            int resId = images.get(position);
            imageView.setImageResource(resId);

            ret = imageView;

            // !!! 需要显示的UI 必须手动添加到 container容器中，
            //     否则不能够显示
            container.addView(imageView);
            // addView 将控件添加到 container容器中
        }

        return ret;
    }

    /**
     * 销毁指定位置的Object 或者说 销毁View
     *
     * @param container
     * @param position
     * @param object    就是 instantiateItem 方法的返回值
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        Log.d(TAG, "destroyItem " + position);

        // 不要调用 super.destroyItem
        // 相当于从ViewPager内部清除界面UI控件
        container.removeView((View) object);

    }
}
