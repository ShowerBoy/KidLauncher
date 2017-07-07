package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import adapter.MyGridViewAdapter;
import utils.SystemUtils;

/**
 * 选择头像
 * Created by Administrator on 2016/8/25.
 */
public class ChooseHeaderPicActivity extends AppCompatActivity{
    private Button choose_back;
    private GridView gridView;
    private MyGridViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_header_pic);
        choose_back= (Button) findViewById(R.id.choose_back);
        gridView= (GridView) findViewById(R.id.gridView);
        choose_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter=new MyGridViewAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("image",SystemUtils.image.get(position));
                setResult(111,intent);
                finish();
            }
        });
    }
}
