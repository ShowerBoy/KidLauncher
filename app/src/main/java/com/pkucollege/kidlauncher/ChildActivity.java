package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.child.entity.Child;

import de.hdodenhof.circleimageview.CircleImageView;
import fragment.FragmentController;

/**
 * Created by Administrator on 2016/8/19.
 */
public class ChildActivity extends AppCompatActivity{
    private RadioGroup radioGroup;
    FragmentController controller;
    private CircleImageView child_back_button;
    private static Child child;
    private static int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        radioGroup= (RadioGroup) findViewById(R.id.child_radioGroup);
        child_back_button= (CircleImageView) findViewById(R.id.child_back_button);
        initChild();
        child_back_button.setImageResource(child.getHead_pic());
        child_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        controller=FragmentController.getInstance(R.id.fragment_container,this);
        controller.showFragment(0);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.game_button:
                        controller.showFragment(0);
                        break;
                    case R.id.book_button:
                        controller.showFragment(1);
                        break;
                    case R.id.animation_button:
                        controller.showFragment(2);
                        break;
                    case R.id.learn_button:
                        controller.showFragment(3);
                        break;
                    case R.id.record_button:
                        controller.showFragment(4);
                        break;
                }
            }
        });
    }

    private void initChild() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        child= (Child) bundle.getSerializable("child");
        position=bundle.getInt("position");
    }

    public static Child getKid(){
        return child;
    }

    public static int getPosition(){
        return position;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.destory();
    }
}
