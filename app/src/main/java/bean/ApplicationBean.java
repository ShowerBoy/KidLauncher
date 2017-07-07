package bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ApplicationBean implements Serializable{
    String app_name;
    String package_name;
    Drawable app_icon;

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public Drawable getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon) {
        this.app_icon = app_icon;
    }
}
