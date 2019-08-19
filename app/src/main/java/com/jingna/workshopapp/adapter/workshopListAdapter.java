package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.LikeGoodsBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.ShareDetailsActivity;
import com.jingna.workshopapp.util.DensityTool;

import java.util.List;

/**
 * Created by Administrator on 2019/8/15.
 */

public class workshopListAdapter extends RecyclerView.Adapter<workshopListAdapter.ViewHolder>{
    private Context context;
    private List<LikeGoodsBean.DataBean.ShopCategoriesBean.ShopCategorysBean> data;

    public workshopListAdapter(List<LikeGoodsBean.DataBean.ShopCategoriesBean.ShopCategorysBean> data) {
        this.data = data;
    }
    @Override
    public workshopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_search_goods_list, parent, false);
        workshopListAdapter.ViewHolder holder = new workshopListAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(workshopListAdapter.ViewHolder holder, final int position) {
        holder.img_for.removeAllViews();
        ImageView imageView;
        int a = DensityTool.dp2px(context, 12);
        for (int i = 0; i<data.get(position).getIntEvalute(); i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.lvxing);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.img_for.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.lvxing);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 10;
                holder.img_for.addView(imageView, layoutParams);
            }
        }
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getAppCategoryPic()).into(holder.iv_img1);
        holder.pingfen.setText(data.get(position).getEvaluate()+"");
        holder.tv_title1.setText(data.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ShareDetailsActivity.class);
                intent.putExtra("type", "1");
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
        private TextView pingfen;
        private LinearLayout img_for;
        private TextView tv_title1;
        private ImageView iv_img1;
        public ViewHolder(View itemView) {
            super(itemView);
            pingfen =itemView.findViewById(R.id.pingfen);
            img_for =itemView.findViewById(R.id.img_for);
            tv_title1 =itemView.findViewById(R.id.tv_title1);
            iv_img1 =itemView.findViewById(R.id.iv_img1);
        }
    }
}
