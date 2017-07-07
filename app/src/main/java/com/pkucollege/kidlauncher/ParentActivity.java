package com.pkucollege.kidlauncher;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.child.entity.Child;

import utils.SystemUtils;

/**
 * 家长界面
 * Created by wanglei on 2016/8/18.
 */
public class ParentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back_button;
    private LinearLayout application_layout, book_layout, member_layout, lock_layout, time_layout, setting_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        initView();
        back_button.setOnClickListener(this);
        application_layout.setOnClickListener(this);
        book_layout.setOnClickListener(this);
        member_layout.setOnClickListener(this);
        lock_layout.setOnClickListener(this);
        time_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
    }

    private void initView() {
        back_button = (Button) findViewById(R.id.back_button);
        application_layout = (LinearLayout) findViewById(R.id.application_layout);
        book_layout = (LinearLayout) findViewById(R.id.book_layout);
        member_layout = (LinearLayout) findViewById(R.id.member_layout);
        lock_layout = (LinearLayout) findViewById(R.id.lock_layout);
        time_layout = (LinearLayout) findViewById(R.id.time_layout);
        setting_layout = (LinearLayout) findViewById(R.id.setting_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.application_layout:
                if (getChildListSize() == 0) {
                    SystemUtils.show(this, "请添加孩子");
                } else if (getChildListSize() == 1) {
                    Child child = SystemUtils.getMainList().get(0);
                    Intent intent = new Intent(ParentActivity.this, ManageApplication.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("child", child);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    SystemUtils.ChooseChildDialog(this, "用于哪个子女?").setSingleChoiceItems(SystemUtils.getChildName(), 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Child child = SystemUtils.getMainList().get(which);
                            Intent intent = new Intent(ParentActivity.this, ManageApplication.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("child", child);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", null).show();
                }
                break;
            case R.id.book_layout:

                break;
            case R.id.member_layout:
                startActivity(new Intent(ParentActivity.this, MemberActivity.class));
                break;
            case R.id.lock_layout:
                Intent intent1=new Intent();
                intent1.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri= Uri.fromParts("package",getApplication().getPackageName(),null);
                intent1.setData(uri);
                startActivity(intent1);
                break;
            case R.id.time_layout:

                break;
            case R.id.setting_layout:
                startActivity(new Intent(ParentActivity.this,SettingActivity.class));
                break;
        }
    }

    public int getChildListSize() {
        return SystemUtils.getMainList().size();
    }
}
