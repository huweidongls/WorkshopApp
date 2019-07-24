package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.EntrustListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.DensityTool;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class EntrustListAdapter extends RecyclerView.Adapter<EntrustListAdapter.ViewHolder> {

    private Context context;
    private List<EntrustListBean.DataBean> data;

    public EntrustListAdapter(List<EntrustListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_entrust_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.llXing.removeAllViews();
        int commentLevel = 4;
        int a = DensityTool.dp2px(context, 7);
        ImageView imageView;
        for (int i = 0; i<commentLevel; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_green);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.llXing.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_green);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 8;
                holder.llXing.addView(imageView, layoutParams);
            }
        }

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getAppCategoryPic()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getCategoryName());
        holder.tvPrice.setText("¥ "+data.get(position).getProcessingMoney());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llXing;
        private ImageView iv;
        private TextView tvTitle;
        private TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

}
