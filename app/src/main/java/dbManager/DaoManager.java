package dbManager;

import android.content.Context;

import com.child.dao.DaoMaster;
import com.child.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/8/26.
 */
public class DaoManager {
    private static final String DB_NAME="childdb.sqlite";
    private volatile static DaoManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;

    /**
     * 单例获得操作数据库对象
     * @return
     */
    public static DaoManager getInstance(){
        DaoManager instance=null;
        if (manager==null){
            synchronized (DaoManager.class){
                if (instance==null){
                    instance=new DaoManager();
                    manager=instance;
                }
            }
        }else{
            instance=manager;
        }
        return instance;
    }

    /**
     * 判断是否存在数据库，没有就创建
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (daoMaster==null){
            helper=new DaoMaster.DevOpenHelper(context,DB_NAME,null);
            daoMaster=new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession(){
        if (daoSession==null){
            if (daoMaster==null){
                daoMaster=getDaoMaster();
            }
            daoSession=daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
    }

    /**
     *  关闭所有操作
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }


    public void closeDaoSession(){
        if (daoSession!=null){
            daoSession.clear();
            daoSession=null;
        }
    }
    public void closeHelper(){
        if (helper!=null){
            helper.close();
            helper=null;
        }
    }

    public void init(Context context){
        this.context=context;
    }
}
