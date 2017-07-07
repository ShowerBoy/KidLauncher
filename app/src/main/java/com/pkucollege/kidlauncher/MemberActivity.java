package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import adapter.MyChildListAdapter;
import bean.JTMessage;

/**
 * 家庭成员界面
 * Created by wanglei on 2016/8/23.
 */
public class MemberActivity extends AppCompatActivity{
    private ListView child_listView;
    private MyChildListAdapter adapter;
    private Button member_back_button;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        EventBus.getDefault().register(this);
        child_listView= (ListView) findViewById(R.id.child_listView);
        member_back_button= (Button) findViewById(R.id.member_back_button);
        adapter=new MyChildListAdapter(this);
        child_listView.addHeaderView(getHeaderView());
        child_listView.setAdapter(adapter);
        child_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    startActivity(new Intent(MemberActivity.this,AddChildActivity.class));
                }else{
                    Intent intent=new Intent(MemberActivity.this,AddChildActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("position",position-1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        member_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public View getHeaderView(){
        inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.activity_add_child_header,null,false);
        ImageButton button= (ImageButton) view.findViewById(R.id.add_child);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberActivity.this,AddChildActivity.class));
            }
        });
        return view;
    }

    @Subscribe
    public void onEventMainThread(JTMessage message){
        if (message!=null){
            if (message.what==1){
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
