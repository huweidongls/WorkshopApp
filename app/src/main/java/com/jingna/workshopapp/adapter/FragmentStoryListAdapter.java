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
import com.jingna.workshopapp.bean.StoryListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.StoreDetailsActivity;
import com.jingna.workshopapp.util.Logger;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class FragmentStoryListAdapter extends RecyclerView.Adapter<FragmentStoryListAdapter.ViewHolder> {

    private Context context;
    private List<StoryListBean.DataBean> data;

    public FragmentStoryListAdapter(List<StoryListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public FragmentStoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_story_list, parent, false);
        FragmentStoryListAdapter.ViewHolder holder = new FragmentStoryListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getAppPic()).into(holder.iv);
        int type = data.get(position).getStoryType();
        if(type == 0){
            holder.tvTitle.setText("公司·动态");
        }else if(type == 1){
            holder.tvTitle.setText("员工·动态");
        }
        holder.tvContent.setText(data.get(position).getStoryTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, StoreDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
