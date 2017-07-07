package com.pkucollege.kidlauncher;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016/9/22.
 */
public class DownLoadProgressBar extends AppCompatActivity{
    static ProgressBar progressBar;
    ImageView bear_bar,butterfly_bar;
    AnimationDrawable bear,butterfly;
    static int total;
    static int current;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar_item);
        progressBar= (ProgressBar) findViewById(R.id.setting_progress_bar);
        bear_bar= (ImageView) findViewById(R.id.bear_bar);
        butterfly_bar= (ImageView) findViewById(R.id.butterfly_bar);
        bear= (AnimationDrawable) bear_bar.getDrawable();
        butterfly= (AnimationDrawable) butterfly_bar.getDrawable();
        bear.setOneShot(false);
        butterfly.setOneShot(false);
        bear.start();
        butterfly.start();
    }

    public static void refreshProgressBar(int totalSize,int currentSize){
        total=totalSize;
        current=currentSize;
        setProgressBar();
    }

    private static void setProgressBar() {
        progressBar.setMax(total);
        progressBar.setProgress(current);
    }
}
