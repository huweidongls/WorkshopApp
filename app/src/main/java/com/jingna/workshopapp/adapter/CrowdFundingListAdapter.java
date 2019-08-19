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
import com.jingna.workshopapp.bean.CrowdTuijianBean;
import com.jingna.workshopapp.bean.LikeGoodsBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.ZhongchouDetailsActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/8/15.
 */

public class CrowdFundingListAdapter extends RecyclerView.Adapter<CrowdFundingListAdapter.ViewHolder>{

    private Context context;
    private List<LikeGoodsBean.DataBean.ListBean.CrowdFundingsBean> data;

    public CrowdFundingListAdapter(List<LikeGoodsBean.DataBean.ListBean.CrowdFundingsBean> data) {
        this.data = data;
    }
    @Override
    public CrowdFundingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_search_goods_san, parent, false);
        CrowdFundingListAdapter.ViewHolder holder = new CrowdFundingListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CrowdFundingListAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGearPictureApp()).into(holder.iv);
        holder.tv_title.setText(data.get(position).getGearTitle()+"");
        holder.tv_price.setText("¥"+data.get(position).getGearMoney());
        holder.tv_all_price.setText(data.get(position).getAllMoney()+"");
        holder.tv_people.setText(data.get(position).getAllPeople()+"");
        holder.tv_persent.setText(data.get(position).getPercentage()+"");
        holder.pro.setProgress(data.get(position).getPercentageNum());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv_title;
        private TextView tv_price;
        private TextView tv_persent;
        private ProgressBar pro;
        private TextView tv_people;
        private TextView tv_all_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv =itemView.findViewById(R.id.iv);
            tv_title =itemView.findViewById(R.id.tv_title);
            tv_price =itemView.findViewById(R.id.tv_price);
            tv_persent =itemView.findViewById(R.id.tv_persent);
            pro =itemView.findViewById(R.id.pro);
            tv_people =itemView.findViewById(R.id.tv_people);
            tv_all_price =itemView.findViewById(R.id.tv_all_price);
        }
    }
}
