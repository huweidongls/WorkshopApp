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
import com.jingna.workshopapp.bean.CategoryQueryChildListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.ShareDetailsActivity;
import com.jingna.workshopapp.util.DensityTool;

import java.util.List;

/**
 * Created by Administrator on 2019/6/20.
 */

public class ShareListAdapter extends RecyclerView.Adapter<ShareListAdapter.ViewHolder> {

    private Context context;
    private List<CategoryQueryChildListBean.DataBean> data;
    private String type;

    public ShareListAdapter(List<CategoryQueryChildListBean.DataBean> data, String type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_share_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.llXing.removeAllViews();
        int commentLevel = 4;
        int a = DensityTool.dp2px(context, 12);
        ImageView imageView;
        for (int i = 0; i<commentLevel; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.llXing.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 10;
                holder.llXing.addView(imageView, layoutParams);
            }
        }

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getAppCategoryPic()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(type.equals("4")){
                    intent.putExtra("type", "2");
                }else {
                    intent.putExtra("type", "1");
                }
                intent.setClass(context, ShareDetailsActivity.class);
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

        private LinearLayout llXing;
        private ImageView iv;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
