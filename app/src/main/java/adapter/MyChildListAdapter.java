package adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.child.entity.Child;
import com.pkucollege.kidlauncher.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bean.JTMessage;
import de.hdodenhof.circleimageview.CircleImageView;
import utils.SystemUtils;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyChildListAdapter extends BaseAdapter{
    private Context context;
    private List<Child> childList= SystemUtils.getMainList();
    private LayoutInflater inflater;

    public MyChildListAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int position) {
        return childList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyChildListViewHolder holder;
        if (convertView==null){
            holder=new MyChildListViewHolder();
            convertView=inflater.inflate(R.layout.activity_add_child_item,parent,false);
            holder.delete= (ImageButton) convertView.findViewById(R.id.delete_child);
            holder.headPic= (CircleImageView) convertView.findViewById(R.id.add_child_head_pic);
            holder.childName= (TextView) convertView.findViewById(R.id.add_child_name);
            convertView.setTag(holder);
        }else{
            holder= (MyChildListViewHolder) convertView.getTag();
        }
        holder.headPic.setImageResource(childList.get(position).getHead_pic());
        holder.childName.setText(childList.get(position).getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemUtils.ChooseChildDialog(context,"删除孩子").setMessage("确定删除吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JTMessage jtMessage=new JTMessage();
                        jtMessage.what=1;
                        jtMessage.obj=childList.get(position);
                        SystemUtils.dbUtils.deleteChild(childList.get(position));
                        SystemUtils.getMainList().remove(position);
                        notifyDataSetChanged();
                        EventBus.getDefault().post(jtMessage);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
        return convertView;
    }
    public class MyChildListViewHolder{
        ImageButton delete;
        CircleImageView headPic;
        TextView childName;
    }
}
