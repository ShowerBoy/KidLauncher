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
 * 选择app添加适配器
 * Created by Administrator on 2016/8/29.
 */
public class AddAppGridViewAdapter extends BaseAdapter {
    private Context context;
    private boolean[] isChoose;
    private List<ApplicationBean> lists;
    private LayoutInflater inflater;

    public AddAppGridViewAdapter(List<ApplicationBean> lists, Context context,boolean[] isChoose) {
        this.lists = lists;
        this.context = context;
        this.isChoose=isChoose;
        inflater = LayoutInflater.from(context);
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
        MyAddAppViewHolder holder;
        if (convertView==null){
            holder=new MyAddAppViewHolder();
            convertView=inflater.inflate(R.layout.activity_add_app_item,parent,false);
            holder.app_icon= (ImageView) convertView.findViewById(R.id.add_app_icon);
            holder.choose_image= (ImageView) convertView.findViewById(R.id.add_app_isChoose);
            holder.app_name= (TextView) convertView.findViewById(R.id.add_app_name);
            convertView.setTag(holder);
        }else{
            holder= (MyAddAppViewHolder) convertView.getTag();
        }
        if (isChoose[position]){
            holder.choose_image.setImageResource(R.drawable.checkbox_c);
        }else{
            holder.choose_image.setImageResource(R.drawable.checkbox_u);
        }
        holder.app_icon.setImageDrawable(lists.get(position).getApp_icon());
        holder.app_name.setText(lists.get(position).getApp_name());
        return convertView;
    }

    class MyAddAppViewHolder {
        ImageView app_icon,choose_image;
        TextView app_name;
    }

    public void choose(int position){
        isChoose[position]=!isChoose[position];
    }
}
