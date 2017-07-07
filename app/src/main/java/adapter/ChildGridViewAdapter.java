package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkucollege.kidlauncher.R;

import java.util.List;

import bean.ApplicationBean;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ChildGridViewAdapter extends BaseAdapter{
    private Context context;
    private List<ApplicationBean> lists;
    private LayoutInflater inflater;

    public ChildGridViewAdapter(Context context, List<ApplicationBean> lists) {
        this.context = context;
        this.lists = lists;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView==null){
            holder=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.activity_child_grid_item,parent,false);
            holder.app_icon= (ImageView) convertView.findViewById(R.id.child_app_icon);
            holder.app_name= (TextView) convertView.findViewById(R.id.child_app_name);
            convertView.setTag(holder);
        }else{
            holder= (ChildViewHolder) convertView.getTag();
        }
        holder.app_icon.setImageDrawable(lists.get(position).getApp_icon());
        holder.app_name.setText(lists.get(position).getApp_name());
        return convertView;
    }

    class ChildViewHolder{
        ImageView app_icon;
        TextView app_name;
    }
}
