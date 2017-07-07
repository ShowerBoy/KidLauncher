package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pkucollege.kidlauncher.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19.
 */
public class SettingListAdapter extends BaseAdapter{
    private List<String> text;
    private LayoutInflater inflater;
    private Context context;

    public SettingListAdapter(Context context, List<String> text) {
        this.context = context;
        this.text = text;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object getItem(int position) {
        return text.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SettingViewHolder holder;
        if (convertView==null){
            holder=new SettingViewHolder();
            convertView=inflater.inflate(R.layout.activity_setting_list_item,parent,false);
            holder.textView= (TextView) convertView.findViewById(R.id.setting_name);
            convertView.setTag(holder);
        }else{
            holder= (SettingViewHolder) convertView.getTag();
        }
        holder.textView.setText(text.get(position));
        return convertView;
    }
    class SettingViewHolder{
        TextView textView;
    }
}
