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
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.AfterServiceActivity;
import com.jingna.workshopapp.page.CrowdDetailsSupportActivity;
import com.jingna.workshopapp.page.OrderDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentDaiFuKuanOrderAdapter extends RecyclerView.Adapter<FragmentDaiFuKuanOrderAdapter.ViewHolder>{
    private Context context;
    private List<OrderListBean.DataBean> data;

    public FragmentDaiFuKuanOrderAdapter(List<OrderListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public FragmentDaiFuKuanOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_daifukuan_order, parent, false);
        FragmentDaiFuKuanOrderAdapter.ViewHolder holder = new FragmentDaiFuKuanOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentDaiFuKuanOrderAdapter.ViewHolder holder, final int position) {
        holder.tv_order_status.setText("等待付款");
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGoodsPictureApp()).into(holder.iv_title_img);
        holder.tv_title.setText(data.get(position).getGoodsTitle());
        holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        holder.tv_price.setText("¥"+data.get(position).getOrderPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,OrderDetailsActivity.class);
                intent.putExtra("id",data.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_order_status;
        private ImageView iv_title_img;
        private TextView tv_title;
        private TextView tv_goods_num;
        private TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            iv_title_img = itemView.findViewById(R.id.iv_title_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_num);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
