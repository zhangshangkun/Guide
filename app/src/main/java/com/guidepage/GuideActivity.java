package com.guidepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.guidepage.adapter.ViewPagerAdapter;
import com.guidepage.application.AppApplication;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: GuideActivity
 * Author: luocen
 * Date: 2017/11/14  16:21
 * Email: zsk559521@163.com
 */
public class GuideActivity extends Activity {
    private ViewPager mPager;
    private LinearLayout mDotsLayout;
    private Button mBtnClose;
    private List<View> viewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
    }
    private void initViews() {
        mPager = findViewById(R.id.guide_viewpager);
        mDotsLayout =  findViewById(R.id.guide_dots);
        mBtnClose = findViewById(R.id.btn_guide);
        initPager();
        mPager.setAdapter(new ViewPagerAdapter(viewList));
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                pageSelected(arg0);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转MainActivity
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                AppApplication.getInstance().setIsLogin(LauncherActivity.RESULT_LOGIN);
                startActivity(intent);
                finish();
            }
        });
    }
    private void pageSelected(int sp){
        if(mDotsLayout != null && mDotsLayout.getChildCount() > 0){
            for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
                if (i == sp) {
                    mDotsLayout.getChildAt(i).setSelected(true);
                } else {
                    mDotsLayout.getChildAt(i).setSelected(false);
                }
            }
        }
        if (sp == viewList.size() - 1) {
            updateBtnVisible(View.VISIBLE);
        } else {
            updateBtnVisible(View.GONE);
        }
    }

    private void updateBtnVisible(int visibility) {
        mBtnClose.setVisibility(visibility);

    }
    private int[] getWelcomePagesByVersion(){
        return new int[] { R.mipmap.guide_bg1, R.mipmap.guide_bg2,R.mipmap.guide_bg3,R.mipmap.guide_bg4};
    }
    private void initPager() {
        viewList = new ArrayList<View>();
        int[] images = getWelcomePagesByVersion();
        for (int i = 0; i < images.length; i++) {
            viewList.add(initView(images[i]));
        }
    }
    private View initView(int res) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guide, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iguide_img);
        imageView.setImageResource(res);
        return view;
    }
}
