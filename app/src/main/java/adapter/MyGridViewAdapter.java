package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pkucollege.kidlauncher.R;

import java.util.List;

import utils.SystemUtils;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MyGridViewAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> images= SystemUtils.image;
    public MyGridViewAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGridViewHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.activity_gridview_item,parent,false);
            holder=new MyGridViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.choose_imageView);
            convertView.setTag(holder);
        }else{
            holder= (MyGridViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(images.get(position));
        return convertView;
    }
    class MyGridViewHolder{
        ImageView imageView;
    }
}
