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
import com.jingna.workshopapp.bean.CrowdDetailsBean;
import com.jingna.workshopapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */

public class CrowdCommentAdapter extends RecyclerView.Adapter<CrowdCommentAdapter.ViewHolder> {

    private Context context;
    private List<CrowdDetailsBean.DataBean.ShopGoodsEvaluatesBean> data;

    public CrowdCommentAdapter(List<CrowdDetailsBean.DataBean.ShopGoodsEvaluatesBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_crowd_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadPhoto()).into(holder.ivAvatar);
        holder.tvName.setText(data.get(position).getMemName());
        holder.tvContent.setText(data.get(position).getGoodsEvaluate());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

}
