package com.pkucollege.kidlauncher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import adapter.MyRecyclerViewAdapter;
import bean.JTMessage;
import dbManager.DBUtils;
import fragment.ParentGameFragment;
import utils.SystemUtils;

/**
 * 主界面
 * Created by wanglei on 2016/8/18.
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button parent_button, end_button;
    private MyRecyclerViewAdapter adapter;
    private DBUtils dbUtils;
    private ProgressDialog dialog;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        dbUtils = new DBUtils(this);
        SystemUtils.dbUtils = dbUtils;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        parent_button = (Button) findViewById(R.id.parent_button);
        end_button = (Button) findViewById(R.id.end_button);
        dialog = SystemUtils.getProgressDialog(this);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemUtils.appLists = new ArrayList<>();
                SystemUtils.appLists.addAll(SystemUtils.getApplist(MainActivity.this));
                mHandler.sendEmptyMessage(0);
            }
        });
        if (SystemUtils.getMainList().size() == 0) {
            initData();
        }
        adapter = new MyRecyclerViewAdapter(this, SystemUtils.getMainList());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        parent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemUtils.getMainList().size() == 0) {
                    startActivity(new Intent(MainActivity.this, AddChildActivity.class));
                } else {
                    Intent intent = new Intent(MainActivity.this, EndActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("flag", 20);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        end_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EndActivity.class);
                intent.setFlags(21);
                startActivity(intent);
            }
        });
        thread.start();
        dialog.show();
    }

    /**
     * 从数据库中读取所有数据
     */
    private void initData() {
        SystemUtils.MainList.addAll(dbUtils.listAll());
    }

    /**
     * {@link ParentGameFragment#initChild()}
     * @param message
     */
    @Subscribe
    public void onEventMainThread(JTMessage message) {
        if (message != null) {
            if (message.what == 1) {
                refreshData();
            }
            if (message.what == 010) {
                Reload();
            }
        }
    }

    private void Reload() {
        SystemUtils.MainList.clear();
        SystemUtils.MainList.addAll(dbUtils.listAll());
    }

    /**
     * 刷新界面
     */
    private void refreshData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
            super.handleMessage(msg);
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

}
