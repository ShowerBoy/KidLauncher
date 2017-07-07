package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/25.
 */
public class JTMessage implements Serializable{
    public int what;
    public Object obj;

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
