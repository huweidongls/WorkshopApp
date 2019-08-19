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

public class EquipmentListAdapter extends RecyclerView.Adapter<EquipmentListAdapter.ViewHolder>{

    private Context context;
    private List<LikeGoodsBean.DataBean.ShopCategoriesWtjgBean.ShopCategorysBeanX> data;

    public EquipmentListAdapter(List<LikeGoodsBean.DataBean.ShopCategoriesWtjgBean.ShopCategorysBeanX> data) {
        this.data = data;
    }
    @Override
    public EquipmentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_search_goods_er, parent, false);
        EquipmentListAdapter.ViewHolder holder = new EquipmentListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EquipmentListAdapter.ViewHolder holder, final int position) {
        holder.for_img.removeAllViews();
        ImageView imageView;
        int a = DensityTool.dp2px(context, 12);
        for (int i = 0; i<data.get(position).getIntEvalute(); i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.lvxing);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.for_img.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.lvxing);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 10;
                holder.for_img.addView(imageView, layoutParams);
            }
        }
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getAppCategoryPic()).into(holder.iv_img2);
        holder.tv_title2.setText(data.get(position).getCategoryName());
        holder.pingfen.setText(data.get(position).getEvaluate()+"");//R.mipmap.lvxing
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ShareDetailsActivity.class);
                intent.putExtra("type", "2");
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

        private ImageView iv_img2;
        private TextView tv_title2;
        private TextView pingfen;
        //private ImageView imageView;
        private LinearLayout for_img;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_img2 =itemView.findViewById(R.id.iv_img2);
            tv_title2 =itemView.findViewById(R.id.tv_title2);
            pingfen =itemView.findViewById(R.id.pingfen);
            for_img =itemView.findViewById(R.id.for_img);
        }
    }
}
