package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.child.entity.Child;
import com.pkucollege.kidlauncher.ChildActivity;
import com.pkucollege.kidlauncher.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/19.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<Child> childList;
    private LayoutInflater inflater;

    public MyRecyclerViewAdapter(Context context, List<Child> childList) {
        this.context = context;
        this.childList = childList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_recycler_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.child_name.setText(childList.get(position).getName());
        holder.child_head_pic.setImageResource(childList.get(position).getHead_pic());
        holder.child_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ChildActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("child",childList.get(position));
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView child_head_pic;
        TextView child_name;
        LinearLayout child_linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            child_head_pic= (CircleImageView) itemView.findViewById(R.id.child_head_pic);
            child_name= (TextView) itemView.findViewById(R.id.child_name);
            child_linearLayout= (LinearLayout) itemView.findViewById(R.id.child_linearLayout);
        }
    }
}
