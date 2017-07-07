package com.pkucollege.kidlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * 计算器弹出窗口界面
 * Created by wanglei on 2016/8/18.
 */
public class EndActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout layout;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnBackspace,btnConfirm;
    private TextView tvResult,mul1,mul2;
    private int num1,num2;      //0-9随机整数
    private int flag;       //判断进入"家长"界面(20)还是"退出"(21)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        layout=(LinearLayout)findViewById(R.id.exit_layout);
        initView();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){
            flag=bundle.getInt("flag");
        }else{
            flag=21;
        }
        mul1.setText(num1+"");
        mul2.setText(num2+"");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

    }

    private void initView() {
        btn0= (Button) findViewById(R.id.btn0);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btnBackspace=(Button)findViewById(R.id.btnBackspace);
        btnConfirm= (Button) findViewById(R.id.btnConfirm);
        tvResult= (TextView) findViewById(R.id.tvResult);
        mul1= (TextView) findViewById(R.id.mul_textView1);
        mul2= (TextView) findViewById(R.id.mul_textView2);
        num1=new Random().nextInt(10);
        num2=new Random().nextInt(10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                String myString=tvResult.getText().toString();
                myString+="0";
                tvResult.setText(myString);
                break;
            case R.id.btn1:
                String myString1=tvResult.getText().toString();
                myString1+="1";
                tvResult.setText(myString1);
                break;
            case R.id.btn2:
                String myString2=tvResult.getText().toString();
                myString2+="2";
                tvResult.setText(myString2);
                break;
            case R.id.btn3:
                String myString3=tvResult.getText().toString();
                myString3+="3";
                tvResult.setText(myString3);
                break;
            case R.id.btn4:
                String myString4=tvResult.getText().toString();
                myString4+="4";
                tvResult.setText(myString4);
                break;
            case R.id.btn5:
                String myString5=tvResult.getText().toString();
                myString5+="5";
                tvResult.setText(myString5);
                break;
            case R.id.btn6:
                String myString6=tvResult.getText().toString();
                myString6+="6";
                tvResult.setText(myString6);
                break;
            case R.id.btn7:
                String myString7=tvResult.getText().toString();
                myString7+="7";
                tvResult.setText(myString7);
                break;
            case R.id.btn8:
                String myString8=tvResult.getText().toString();
                myString8+="8";
                tvResult.setText(myString8);
                break;
            case R.id.btn9:
                String myString9=tvResult.getText().toString();
                myString9+="9";
                tvResult.setText(myString9);
                break;
            case R.id.btnBackspace:
                String myStr=tvResult.getText().toString();
                try {
                    tvResult.setText(myStr.substring(0, myStr.length()-1));
                } catch (Exception e) {
                    tvResult.setText("");
                }
                break;
            case R.id.btnConfirm:
                if (isRight()){
                    if (flag==20){
                        finish();
                        startActivity(new Intent(EndActivity.this,ParentActivity.class));
                    }else{
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        System.exit(0);
                    }
                }else{
                    Toast.makeText(this,"答案错误",Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                break;
        }
    }
    public boolean isRight(){
        String myStr=tvResult.getText().toString();
        if (myStr.length()==0){
            return false;
        }
        int aaa=Integer.parseInt(myStr);
        int result=num1*num2;
        if (aaa==result){
            return true;
        }else{
            return false;
        }
    }
}
