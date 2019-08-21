package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.dialog.DialogCustom;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.SubmissionEvaluateActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentDaiShouHuoOrderAdapter extends RecyclerView.Adapter<FragmentDaiShouHuoOrderAdapter.ViewHolder>{
    private Context context;
    private List<OrderListBean.DataBean> data;

    public FragmentDaiShouHuoOrderAdapter(List<OrderListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public FragmentDaiShouHuoOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_daishouhuo_order, parent, false);
        FragmentDaiShouHuoOrderAdapter.ViewHolder holder = new FragmentDaiShouHuoOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentDaiShouHuoOrderAdapter.ViewHolder holder, final int position) {
        if (data.get(position).getOrderStatus().equals("4")){
            holder.tv_order_status.setText("待评价");
        }
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGoodsPictureApp()).into(holder.iv_title);
        holder.tv_title.setText(data.get(position).getGoodsTitle());
        holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        holder.tv_price.setText("¥"+data.get(position).getOrderPrice());
        holder.tv_to_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "去评价?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        Intent intent = new Intent();
                        intent.setClass(context, SubmissionEvaluateActivity.class);
                        intent.putExtra("id", data.get(position).getId());
                        intent.putExtra("imgUrl", data.get(position).getGoodsPictureApp());
                        intent.putExtra("title", data.get(position).getGoodsTitle());
                        context.startActivity(intent);
                    }
                });
                dialogCustom.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_title;
        private TextView tv_title;
        private TextView tv_goods_num;
        private TextView tv_price;
        private TextView tv_order_status;
        private Button tv_to_pj;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_title = itemView.findViewById(R.id.iv_title);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_to_pj = itemView.findViewById(R.id.tv_to_pj);
        }
    }
}
