package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.RaiseListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.CrowdDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class FragmentCrowdTuijianAdapter extends RecyclerView.Adapter<FragmentCrowdTuijianAdapter.ViewHolder>{
    private Context context;
    private List<RaiseListBean.DataBean> data;

    public FragmentCrowdTuijianAdapter(List<RaiseListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_crowd_goods_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGearPictureApp()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getGearTitle());
        holder.tvSubTitle.setText(data.get(position).getGearSubtitle());
        holder.tvPrice.setText(data.get(position).getGearMoney()+"");
        holder.tvPercent.setText(data.get(position).getPercentage());
        holder.tvAllPeople.setText(data.get(position).getAllPeople()+"");
        holder.tvAllPrice.setText(data.get(position).getAllMoney()+"");
        String percent = data.get(position).getPercentage();
        percent = percent.substring(0, percent.length()-1);
        holder.progressBar.setProgress(Integer.valueOf(percent));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,CrowdDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvSubTitle;
        private TextView tvPrice;
        private TextView tvPercent;
        private TextView tvAllPeople;
        private TextView tvAllPrice;
        private ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubTitle = itemView.findViewById(R.id.tv_sub_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPercent = itemView.findViewById(R.id.tv_percent);
            tvAllPeople = itemView.findViewById(R.id.tv_all_people);
            tvAllPrice = itemView.findViewById(R.id.tv_all_price);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }
}
