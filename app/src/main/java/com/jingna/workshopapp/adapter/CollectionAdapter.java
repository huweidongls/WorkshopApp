package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.CollectionListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.AcceptanceActivity;
import com.jingna.workshopapp.page.ShareDetailsActivity;
import com.jingna.workshopapp.page.ZhongchouDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/15.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private Context context;
    private List<CollectionListBean.DataBean> data;

    public CollectionAdapter(List<CollectionListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_collection, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getPicture()).into(holder.iv);
        holder.tv.setText(data.get(position).getName());

        if (data.get(position).getType().equals("0")){//车间
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, ShareDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId());
                    intent.putExtra("type", "1");
                    context.startActivity(intent);
                }
            });
        }else{//众筹
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, ZhongchouDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
