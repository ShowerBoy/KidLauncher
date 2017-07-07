package com.pkucollege.kidlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by wanglei on 2015/12/29.
 * 开机自启动
 */
public class BootReceiver extends BroadcastReceiver {
    static final String action_boot ="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action_boot)){
            Intent ootStartIntent=new Intent(context,MainActivity.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent);
        }
    }
}
