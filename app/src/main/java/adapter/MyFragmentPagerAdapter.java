package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments;
    private String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.context=context;
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
