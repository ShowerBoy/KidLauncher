package fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/22.
 */
public class FragmentController {
    int containerId;
    ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private static FragmentController controller;

    private FragmentController(int containerId, Activity activity) {
        this.containerId = containerId;
        fm = activity.getFragmentManager();
        if (fragments==null){
            initFragment();
        }
    }

    public static FragmentController getInstance(int containerId, Activity activity) {
        if (controller == null) {
            controller = new FragmentController(containerId, activity);
        }
        return controller;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ChildGameFragment());
        fragments.add(new ChildBookFragment());
        fragments.add(new ChildAnimationFragment());
        fragments.add(new ChildLearnFragment());
        fragments.add(new ChildRecordFragment());
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    public void showFragment(int position) {
        hideFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragments.get(position));
        ft.commit();
    }

    public void hideFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null)
                ft.hide(fragment);
        }
        ft.commit();
    }
    public void destory(){
        controller=null;
    }
}
