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
import com.jingna.workshopapp.bean.CrowdPopularBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.ZhongchouDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/6/18.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<CrowdPopularBean.DataBean> data;

    public IndexAdapter(List<CrowdPopularBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGearPictureApp()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getGearTitle());
        holder.tvPrice.setText("¥"+data.get(position).getGearMoney());
        holder.tvPercent.setText(data.get(position).getPercentage());
        String percent = data.get(position).getPercentage();
        percent = percent.substring(0, percent.length()-1);
        holder.pro.setProgress(Integer.valueOf(percent));
        holder.tvPeople.setText(data.get(position).getAllPeople()+"");
        holder.tvAllPrice.setText(data.get(position).getAllMoney()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,ZhongchouDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId()+"");
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
        private TextView tvPrice;
        private TextView tvPercent;
        private ProgressBar pro;
        private TextView tvPeople;
        private TextView tvAllPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPercent = itemView.findViewById(R.id.tv_persent);
            pro = itemView.findViewById(R.id.pro);
            tvPeople = itemView.findViewById(R.id.tv_people);
            tvAllPrice = itemView.findViewById(R.id.tv_all_price);
        }
    }

}
