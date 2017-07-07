package dbManager;

import android.content.Context;
import android.util.Log;

import com.child.entity.Child;

import java.util.List;

/**
 * 完成对某一张表的具体操作,ORM 操作的是对象 Child
 * Created by wanglei on 2016/8/26.
 */
public class DBUtils {
    private DaoManager manager;

    public DBUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 插入单条数据
     * @param child
     * @return
     */
    public boolean insertChild(Child child) {
        boolean flag = false;
        flag = manager.getDaoSession().insertOrReplace(child) != -1 ? true : false;
        Log.i("AddChildActivity","insert child---->"+flag);
        return flag;
    }

    /**
     * 插入多条数据，开启线程
     * @param list
     * @return
     */
    public boolean insertMultChild(final List<Child> list){
        boolean flag=false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Child child:list){
                        manager.getDaoSession().insertOrReplace(child);
                    }
                }
            });
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改单条数据
     * @param child
     * @return
     */
    public boolean updateChild(Child child){
        boolean flag=false;
        try {
            manager.getDaoSession().update(child);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条数据
     * @param child
     * @return
     */
    public boolean deleteChild(Child child){
        boolean flag=false;
        try {
            manager.getDaoSession().delete(child);
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有数据
     * @return
     */
    public void deleteAll(){
        manager.getDaoSession().deleteAll(Child.class);
    }

    /**
     * 返回多条数据
     * @return
     */
    public List<Child> listAll(){
        return manager.getDaoSession().loadAll(Child.class);
    }

    /**
     * 根据key返回单条数据
     * @param key
     * @return
     */
    public Child listOneChild(Object key){
        return manager.getDaoSession().load(Child.class,key);
    }
}
