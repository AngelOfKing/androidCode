package com.qianfeng.viewpagetest1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qianfeng.viewpagetest1.R;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/8
 * Email: vhly@163.com
 */
public class NewsFragment extends Fragment {

    private int position;

    public NewsFragment() {
        // Default constructor do not delete this method.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 通过参数来设置内容

        // 这个方法能够获取通过
        // setArguments(Bundle) 设置的参数
        // !! 可能返回 null
        Bundle arguments = getArguments();

        if (arguments != null) {
            position = arguments.getInt("position");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("Life", "Fragment onCreateView " + position);

        View ret = inflater.inflate(R.layout.fragment_news, container, false);

        TextView txt = (TextView) ret.findViewById(R.id.news_title);

        if (txt != null) {

            txt.setText("新闻分类" + position);

        }

        return ret;
    }


}