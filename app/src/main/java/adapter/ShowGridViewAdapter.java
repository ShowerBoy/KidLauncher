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
 * Created by Administrator on 2016/8/29.
 */
public class ShowGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ApplicationBean> app_list;
    private LayoutInflater inflater;

    public ShowGridViewAdapter(Context context, List<ApplicationBean> app_list) {
        this.context = context;
        this.app_list = app_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return app_list.size();
    }

    @Override
    public Object getItem(int position) {
        return app_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAppViewHolder holder;
        if (convertView == null) {
            holder = new MyAppViewHolder();
            convertView = inflater.inflate(R.layout.activity_showapp_item, parent, false);
            holder.app_icon= (ImageView) convertView.findViewById(R.id.app_icon_imageView);
            holder.app_name= (TextView) convertView.findViewById(R.id.app_name_textView);
            convertView.setTag(holder);
        } else {
            holder= (MyAppViewHolder) convertView.getTag();
        }
        holder.app_icon.setImageDrawable(app_list.get(position).getApp_icon());
        holder.app_name.setText(app_list.get(position).getApp_name());
        return convertView;
    }

    class MyAppViewHolder {
        ImageView app_icon;
        TextView app_name;
    }
}
