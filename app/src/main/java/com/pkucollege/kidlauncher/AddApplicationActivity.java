package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.AddAppGridViewAdapter;
import utils.SystemUtils;

/**
 * Created by Administrator on 2016/8/29.
 */
public class AddApplicationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button cancel_btn, confirm_btn;
    private GridView add_app_gridView;
    private AddAppGridViewAdapter adapter;
    public List<Integer> ChooseAppPosition;
    public boolean[] isChoose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);
        cancel_btn = (Button) findViewById(R.id.add_app_cancel_btn);
        confirm_btn = (Button) findViewById(R.id.add_app_confirm_btn);
        add_app_gridView = (GridView) findViewById(R.id.add_app_gridView);
        initData();
        adapter = new AddAppGridViewAdapter(SystemUtils.appLists, this, isChoose);
        add_app_gridView.setAdapter(adapter);
        add_app_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.choose(position);
                adapter.notifyDataSetChanged();
            }
        });
        cancel_btn.setOnClickListener(this);
        confirm_btn.setOnClickListener(this);
    }

    private void initData() {
        isChoose = new boolean[SystemUtils.appLists.size()];
        for (int i = 0; i < SystemUtils.appLists.size(); i++) {
            isChoose[i] = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_app_cancel_btn:
                finish();
                break;
            case R.id.add_app_confirm_btn:
                ChooseAppPosition = new ArrayList<>();
                Intent intent=new Intent();
                for (int i = 0; i < isChoose.length; i++) {
                    if (isChoose[i]) {
                        ChooseAppPosition.add(i);
                    }
                }
                intent.putExtra("choose", (Serializable) ChooseAppPosition);
                setResult(121,intent);
                finish();
                break;
        }
    }
}
