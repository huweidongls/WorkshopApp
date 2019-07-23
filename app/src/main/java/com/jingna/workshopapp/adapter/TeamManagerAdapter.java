package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.TeamManagementBean;
import com.jingna.workshopapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/6/20.
 */

public class TeamManagerAdapter extends RecyclerView.Adapter<TeamManagerAdapter.ViewHolder> {

    private Context context;
    private List<TeamManagementBean.DataBean> data;

    public TeamManagerAdapter(List<TeamManagementBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_team_manager, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadPhoto()).into(holder.ivAvatar);
        holder.tvName.setText(data.get(position).getMemName());
        holder.tvTime.setText(data.get(position).getNewTime());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
