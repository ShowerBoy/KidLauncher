package utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.widget.Toast;

import com.child.entity.Child;
import com.pkucollege.kidlauncher.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.ApplicationBean;
import dbManager.DBUtils;

/**
 * 系统工具类
 * Created by wanglei on 2016/8/24.
 */
public class SystemUtils {
    public static List<Child> MainList = new ArrayList<>();
    public static List<ApplicationBean> appLists = new ArrayList<>();
    public static List<Integer> image = getImage();
    public static String HttpPath = "http://crm.pkucollege.com/pad/book/children?L=";
    public final static String FILE_PHYSICALPATH = "/storage/emulated/legacy/kidlauncher/";

    public final static String FILE_URL = "/kidlauncher";
    public static List<Child> getMainList() {
        return MainList;
    }

    public static DBUtils dbUtils;

    public static void setMainList(List<Child> mainList) {
        MainList = mainList;
    }

    public static AlertDialog.Builder ChooseChildDialog(Context context, String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title).setIcon(R.mipmap.ic_launcher);
        return dialog;
    }

    public static String[] getChildName() {
        String[] child = new String[MainList.size()];
        String name = null;
        for (int i = 0; i < MainList.size(); i++) {
            name = MainList.get(i).getName();
            child[i] = name;
        }
        return child;
    }

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static List<Integer> getImage() {
        List<Integer> image = new ArrayList<>();
        image.add(0, R.drawable.bear_a);
        image.add(1, R.drawable.rabbit);
        image.add(2, R.drawable.butterfly);
        image.add(3, R.drawable.deer);
        image.add(4, R.drawable.dragon_boy);
        image.add(5, R.drawable.dragon_girl);
        image.add(6, R.drawable.fish);
        image.add(7, R.drawable.lion);
        return image;
    }

    public static List<ApplicationBean> getApplist(Context context) {
        List<ApplicationBean> applist = new ArrayList<>();
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo info = packageInfos.get(i);
            ApplicationBean bean = new ApplicationBean();
            bean.setApp_name(info.applicationInfo.loadLabel(context.getPackageManager()).toString());
            bean.setApp_icon(info.applicationInfo.loadIcon(context.getPackageManager()));
            bean.setPackage_name(info.packageName);
//            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            applist.add(bean);
//            }
        }
        return applist;
    }

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("加载中");
        dialog.setMessage("努力加载中，请稍后...");
        return dialog;
    }

    public static ProgressDialog getLoadDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("下载中");
        dialog.setMessage("努力下载中，请稍后...");
        return dialog;
    }

    public static String[] string2array(String name) {
        String[] str = name.split(",");
        return str;
    }

    public static String getExtraPath(Context context) {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
            String[] paths = getVolumePaths(context);
            if (paths.length > 0) {
                path = paths[0];
            }

        } else
            path = Environment.getExternalStorageDirectory().getPath();

        if (path == null)
            path = FILE_PHYSICALPATH;
        else
            path = path + FILE_URL;

        return path;
    }

    public static String[] getVolumePaths(Context context) {
        StorageManager mStorageManager = null;
        Method mMethodGetPaths = null;
        String[] paths = null;
        if (context != null) {
            mStorageManager = (StorageManager) context
                    .getSystemService(context.STORAGE_SERVICE);
            try {
                mMethodGetPaths = mStorageManager.getClass()
                        .getMethod("getVolumePaths");
                paths = (String[]) mMethodGetPaths.invoke(mStorageManager);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return paths;
    }
}
