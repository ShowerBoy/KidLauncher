package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.child.entity.Child;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import bean.JTMessage;
import dbManager.DBUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import utils.SystemUtils;

/**
 * 添加孩子界面
 * Created by wanglei on 2016/8/24.
 */
public class AddChildActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText child_name;
    private RadioGroup child_sex;
    private RadioButton boy, girl;
    private Spinner child_age_year, child_age_month;
    private CircleImageView child_headPic;
    private Button cancel, confirm;
    private Child child;
    private TextView add_child_title;
    String name, year, month;
    boolean change = false;     //判断是添加false还是修改true
    int sex = 1, head_pic;
    int position = -1;   //修改的孩子的序号
    List<String> years, months;
    private ArrayAdapter<String> yearAdapter, monthAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        initView();
        initData();
        yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        child_age_year.setAdapter(yearAdapter);
        child_age_month.setAdapter(monthAdapter);
        child_age_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = years.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        child_age_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = months.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        child_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == boy.getId()) {
                    sex = 1;
                } else if (checkedId == girl.getId()) {
                    sex = 0;
                }
            }
        });
        if (change) {
            add_child_title.setText("修 改 信 息");
            child_name.setText(child.getName());
            setSpinnerItemSelectedByValue(child_age_year, child.getBirthday().split("-")[0]);
            setSpinnerItemSelectedByValue(child_age_month, child.getBirthday().split("-")[1]);
            if (child.getSex() == 1) {
                boy.setChecked(true);
            } else {
                girl.setChecked(true);
            }
            child_headPic.setImageResource(child.getHead_pic());
            head_pic=child.getHead_pic();
            confirm.setText("修改");
        }
    }

    private void initData() {
        int time = Integer.parseInt(SystemUtils.getCurrentTime()) - 15;
        years = new ArrayList<>();
        months = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            years.add(i, (time + i + 1) + "");
        }
        for (int i = 0; i < 12; i++) {
            months.add(i, (i + 1) + "");
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            change = true;
            position = bundle.getInt("position");
            child = SystemUtils.MainList.get(position);
        }
    }

    private void initView() {
        child_name = (EditText) findViewById(R.id.add_name);
        child_sex = (RadioGroup) findViewById(R.id.add_child_sex);
        boy = (RadioButton) findViewById(R.id.add_child_boy);
        girl = (RadioButton) findViewById(R.id.add_child_girl);
        child_headPic = (CircleImageView) findViewById(R.id.add_child_headPic);
        cancel = (Button) findViewById(R.id.add_child_cancel);
        confirm = (Button) findViewById(R.id.add_child_confirm);
        add_child_title = (TextView) findViewById(R.id.add_child_title);
        child_age_year = (Spinner) findViewById(R.id.child_age_year);
        child_age_month = (Spinner) findViewById(R.id.child_age_month);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_child_cancel:
                finish();
                break;
            case R.id.add_child_confirm:
                if (check()) {
                    if (change) {
                        child.setName(name);
                        child.setSex(sex);
                        child.setBirthday(year + "-" + month);
                        child.setHead_pic(head_pic);
                        SystemUtils.dbUtils.updateChild(child);
                        JTMessage jtMessage = new JTMessage();
                        jtMessage.what = 1;
                        jtMessage.obj = child;
                        EventBus.getDefault().post(jtMessage);
                        /**
                         * {@link MemberActivity#onEventMainThread(JTMessage)}
                         * {@link MainActivity#onEventMainThread(JTMessage)}
                         */
                        SystemUtils.show(AddChildActivity.this, "修改成功");
                        finish();
                    } else {
                        child = new Child();
                        child.setName(name);
                        child.setSex(sex);
                        child.setBirthday(year + "-" + month);
                        child.setHead_pic(head_pic);
                        SystemUtils.dbUtils.insertChild(child);
                        SystemUtils.getMainList().add(child);
                        JTMessage jtMessage = new JTMessage();
                        jtMessage.what = 1;
                        jtMessage.obj = child;
                        EventBus.getDefault().post(jtMessage);
                        /**
                         * {@link MemberActivity#onEventMainThread(JTMessage)}
                         * {@link MainActivity#onEventMainThread(JTMessage)}
                         */
                        SystemUtils.show(AddChildActivity.this, "添加成功");
                        Log.i("AddChildActivity", SystemUtils.dbUtils.listAll().size() + "");
                        finish();
                    }
                } else {
                    SystemUtils.show(AddChildActivity.this, "信息不完整，请重新填写");
                }
                break;
            case R.id.add_child_headPic:
                startActivityForResult(new Intent(AddChildActivity.this, ChooseHeaderPicActivity.class), 110);
                break;
        }
    }

    public boolean check() {
        name = child_name.getText().toString().trim();
        if (name == null || name.length() == 0) {
            return false;
        }
        if (sex != 0 && sex != 1) {
            return false;
        }
        if (head_pic == 0) {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 111) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                head_pic = bundle.getInt("image");
                child_headPic.setImageResource(head_pic);
            }
        }
    }

    public void setSpinnerItemSelectedByValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter(); //得到SpinnerAdapter对象
        int k = apsAdapter.getCount();
        for (int i = 0; i < k; i++) {
            if (value.equals(apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i, true);// 默认选中项
                break;
            }
        }
    }

}
