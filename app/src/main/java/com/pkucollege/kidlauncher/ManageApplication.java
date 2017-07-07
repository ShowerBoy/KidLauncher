package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.child.entity.Child;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPagerAdapter;
import fragment.ParentAnimationFragment;
import fragment.ParentBookFragment;
import fragment.ParentGameFragment;
import fragment.ParentLearnFragment;

/**
 * 管理应用界面
 * Created by Administrator on 2016/8/24.
 */
public class ManageApplication extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    static Child child;//  操作的孩子对象
    String[] titles = new String[]{"游戏", "图书", "动画", "学习"};
    List<Fragment> fragments = new ArrayList<>();
    private ParentGameFragment fragment1;
    private ParentBookFragment fragment2;
    private ParentAnimationFragment fragment3;
    private ParentLearnFragment fragment4;
    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_application);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.manage_viewPager);
        initData();
        initFragment();
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this, fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        child= (Child) bundle.getSerializable("child");
    }

    private void initFragment() {
        fragment1=new ParentGameFragment();
        fragment2=new ParentBookFragment();
        fragment3=new ParentAnimationFragment();
        fragment4=new ParentLearnFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
    }

    public static Child getHandleChild(){
        return child;
    }

    public static long childId(){
        return child.getId();
    }

}

