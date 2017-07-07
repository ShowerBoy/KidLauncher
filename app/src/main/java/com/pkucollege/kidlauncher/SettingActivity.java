package com.pkucollege.kidlauncher;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import adapter.SettingListAdapter;
import okhttp3.Call;
import utils.SystemUtils;

/**
 * 设置界面
 * Created by wanglei on 2016/9/19.
 */
public class SettingActivity extends AppCompatActivity {
    private ListView setting_listView;
    private Button back_button;
    private SettingListAdapter adapter;
    private List<String> text;
    String versionName;
    int versionCode;
    RequestQueue requestQueue;
    Thread thread;
    String url;
    LayoutInflater inflater;
    View layout;
    AnimationDrawable bear, butterfly;
    ProgressBar setting_progress_bar;
    RelativeLayout progress_layout, null_layout;
    TextView split, progress_tv;
    ImageView bear_bar, butterfly_bar;
    boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setting_listView = (ListView) findViewById(R.id.setting_listView);
        back_button = (Button) findViewById(R.id.setting_back_button);
        split = (TextView) findViewById(R.id.split);
        progress_tv = (TextView) findViewById(R.id.progress_tv);
        progress_layout = (RelativeLayout) findViewById(R.id.progress_bar_relative_layout);
        null_layout = (RelativeLayout) findViewById(R.id.null_layout);
        null_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        inflater = getLayoutInflater();
        text = new ArrayList<>();
        text.add("系统设置");
        text.add("检查更新");
        OkHttpUtils.init(getApplication());
        adapter = new SettingListAdapter(this, text);
        setting_listView.setAdapter(adapter);
        setting_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                } else {
                    try {
                        PackageManager manager = getPackageManager();
                        PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
                        versionName = info.versionName;
                        versionCode = info.versionCode;
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (!mWifi.isConnected()) {
                        SystemUtils.show(SettingActivity.this, "当前无网络");
                    } else {
                        thread = new Thread(myRunnable);
                        thread.start();
                    }
                }
            }
        });
        initProgressBar();
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getHttpService() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SystemUtils.HttpPath + versionCode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject result = jsonObject.getJSONObject("result");
                    url = result.getString("url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (url.length() == 0 || url.equals("")) {
                    SystemUtils.show(SettingActivity.this, "当前已是最新版本");
                } else {
                    new AlertDialog.Builder(SettingActivity.this).setTitle("下载文件")
                            .setMessage("检测到更新，确定下载吗?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    OkHttpUtils.get(url)
                                            .tag(SettingActivity.this)
                                            .execute(new FileCallback(Environment.getExternalStorageDirectory() + "/kidlauncher", "kid.apk") {
                                                @Override
                                                public void onResponse(boolean isFromCache, File file, okhttp3.Request request, @Nullable okhttp3.Response response) {
                                                    SystemUtils.show(SettingActivity.this, "下载成功");
                                                    null_layout.setVisibility(View.GONE);
                                                    bear.stop();
                                                    butterfly.stop();
                                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                                                    if (flag) {
                                                        doit();
                                                    }
                                                    progress_tv.setText("下载中:"+(int) (progress * 100)+"%");
                                                    setting_progress_bar.setMax((int) totalSize);
                                                    setting_progress_bar.setProgress((int) currentSize);
                                                }

                                                @Override
                                                public void onError(boolean isFromCache, Call call, @Nullable okhttp3.Response response, @Nullable Exception e) {
                                                    super.onError(isFromCache, call, response, e);
                                                    SystemUtils.show(SettingActivity.this, "下载失败");
                                                    flag = true;
                                                    null_layout.setVisibility(View.GONE);
                                                    bear.stop();
                                                    butterfly.stop();
                                                }
                                            });
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                SystemUtils.show(SettingActivity.this, "请求失败");
            }
        });
        requestQueue.add(stringRequest);
        requestQueue.start();
    }

    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            getHttpService();
        }
    };

    public void initProgressBar() {
//        layout=inflater.inflate(R.layout.progress_bar_item, null);
        setting_progress_bar = (ProgressBar) findViewById(R.id.setting_progress_bar);
        bear_bar = (ImageView) findViewById(R.id.bear_bar);
        butterfly_bar = (ImageView) findViewById(R.id.butterfly_bar);
        bear = (AnimationDrawable) bear_bar.getDrawable();
        butterfly = (AnimationDrawable) butterfly_bar.getDrawable();
    }

    public void doit() {
        null_layout.setVisibility(View.VISIBLE);
        bear.start();
        butterfly.start();
        flag = false;
    }
}
