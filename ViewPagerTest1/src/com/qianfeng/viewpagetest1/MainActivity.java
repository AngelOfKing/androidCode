package com.qianfeng.viewpagetest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qianfeng.viewpagetest1.adapters.GuideAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private ViewPager pager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        pager = (ViewPager) findViewById(R.id.pager);

        // ViewPager 设置 PagerAdapter

        List<Integer> images = new LinkedList<Integer>();

        for (int i = 0; i < 4; i++) {
            images.add(R.drawable.ic_launcher);
        }

        GuideAdapter adapter = new GuideAdapter(this, images);

        adapter.setOnClickListener(this);

        pager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.guide_last_button:
                Intent intent = new Intent(this, EnterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
