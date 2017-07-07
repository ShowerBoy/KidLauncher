package fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.child.entity.Child;
import com.pkucollege.kidlauncher.AddApplicationActivity;
import com.pkucollege.kidlauncher.ManageApplication;
import com.pkucollege.kidlauncher.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import adapter.ShowGridViewAdapter;
import bean.ApplicationBean;
import bean.JTMessage;
import utils.SystemUtils;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ParentGameFragment extends Fragment {
    private Button add_application;
    private GridView application_gridView;
    private ShowGridViewAdapter adapter;
    private List<ApplicationBean> chooseList = new ArrayList<>();
    Child child;
    String[] app_Name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_game, container, false);
        add_application = (Button) view.findViewById(R.id.add_application);
        application_gridView = (GridView) view.findViewById(R.id.application_gridView);
        add_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addApp();
            }
        });
        adapter = new ShowGridViewAdapter(getContext(), chooseList != null ? chooseList : new ArrayList<ApplicationBean>());
        application_gridView.setAdapter(adapter);
        application_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=getContext().getPackageManager().getLaunchIntentForPackage(chooseList.get(position).getPackage_name());
                if (intent != null) {
                    startActivity(intent);
                }else{
                    SystemUtils.show(getContext(),"App not found");
                }
            }
        });
        application_gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                SystemUtils.ChooseChildDialog(getContext(),"删除应用").setMessage("确认删除吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chooseList.remove(position);
                        initChild();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        child = ManageApplication.getHandleChild();
        initChooseList();
    }

    private void initChooseList() {
        if (child.getGame_app() != null) {
            String[] str = child.getGame_app().split(",");
            for (int i = 0; i < str.length; i++) {
                for (int j = 0; j < SystemUtils.appLists.size(); j++) {
                    if (SystemUtils.appLists.get(j).getApp_name().equals(str[i])) {
                        if (chooseList == null) {
                            chooseList = new ArrayList<>();
                        }
                        chooseList.add(SystemUtils.appLists.get(j));
                    }
                }
            }
        }
    }

    private void addApp() {
        startActivityForResult(new Intent(getContext(), AddApplicationActivity.class), 120);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 121) {
            Bundle bundle = data.getExtras();
            List<Integer> position = null;
            List<ApplicationBean> app_lists = new ArrayList<>();
            if (bundle != null) {
                position = (List<Integer>) bundle.getSerializable("choose");
            }
            if (chooseList == null) {
                chooseList = new ArrayList<>();
                for (int i = 0; i < position.size(); i++) {
                    chooseList.add(SystemUtils.appLists.get(position.get(i)));
                }
            } else {
                for (int i = 0; i < position.size(); i++) {
                    app_lists.add(SystemUtils.appLists.get(position.get(i)));
                }
                for (int i = 0; i < chooseList.size(); i++) {
                    for (int j = 0; j < app_lists.size(); j++) {
                        if (app_lists.get(j).equals(chooseList.get(i))) {
                            app_lists.remove(j);
                        }
                    }
                }
                chooseList.addAll(app_lists);
            }
            initChild();
            adapter.notifyDataSetChanged();
        }
    }

    private void initChild() {
        String appName = "";
        app_Name = new String[chooseList.size()];
        for (int i = 0; i < chooseList.size(); i++) {
            app_Name[i] = chooseList.get(i).getApp_name();
        }
        for (int i = 0; i < app_Name.length; i++) {
            if (i == (app_Name.length - 1)) {
                appName = appName + app_Name[i];
            } else {
                appName = appName + app_Name[i] + ",";
            }
        }
        child.setGame_app(appName);
        SystemUtils.dbUtils.updateChild(child);
        /**
         * {@link com.pkucollege.kidlauncher.MainActivity#onEventMainThread(JTMessage)}
         */
        JTMessage message=new JTMessage();
        message.what=010;
        message.obj=child;
        EventBus.getDefault().post(message);
    }

}
