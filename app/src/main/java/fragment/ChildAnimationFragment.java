package fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.child.entity.Child;
import com.pkucollege.kidlauncher.ChildActivity;
import com.pkucollege.kidlauncher.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ChildGridViewAdapter;
import bean.ApplicationBean;
import utils.SystemUtils;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ChildAnimationFragment extends Fragment {
    private GridView child_gridView;
    private List<ApplicationBean> lists;
    private Child child;
    private ChildGridViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_game, container, false);
        child_gridView = (GridView) view.findViewById(R.id.child_gridView);
        child = SystemUtils.dbUtils.listOneChild((long) (ChildActivity.getPosition() + 1));
        initData();
        adapter=new ChildGridViewAdapter(this.getActivity(),lists);
        child_gridView.setAdapter(adapter);
        child_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getActivity().getPackageManager().getLaunchIntentForPackage(lists.get(position).getPackage_name());
                if (intent != null) {
                    startActivity(intent);
                }else{
                    SystemUtils.show(getContext(),"App not found");
                }
            }
        });
        return view;
    }
    private void initData() {
        lists=new ArrayList<>();
        if (child.getAnimation_app()!=null){
            String[] animation_app = SystemUtils.string2array(child.getAnimation_app());
            for (int i = 0; i < animation_app.length; i++) {
                for (int j = 0; j < SystemUtils.appLists.size(); j++) {
                    if (SystemUtils.appLists.get(j).getApp_name().equals(animation_app[i])){
                        lists.add(SystemUtils.appLists.get(j));
                    }
                }
            }
        }
    }
}
