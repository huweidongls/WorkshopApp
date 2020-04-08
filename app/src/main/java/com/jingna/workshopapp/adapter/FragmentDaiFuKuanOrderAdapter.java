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
import com.jingna.workshopapp.page.AfterServiceActivity;
import com.jingna.workshopapp.page.CrowdDetailsSupportActivity;
import com.jingna.workshopapp.page.OrderDetailsActivity;
import com.jingna.workshopapp.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentDaiFuKuanOrderAdapter extends RecyclerView.Adapter<FragmentDaiFuKuanOrderAdapter.ViewHolder>{
    private Context context;
    private List<OrderListBean.DataBean> data;
    private ClickListener listener;

    public FragmentDaiFuKuanOrderAdapter(List<OrderListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
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
        if(data.get(position).getGoodsNum()==null){
            holder.tv_goods_num.setText("共1件商品 应付款：");
        }else {
            holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        }
        holder.tv_price.setText("¥"+ StringUtils.roundByScale(data.get(position).getOrderRealPrice(), 2)+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,OrderDetailsActivity.class);
                intent.putExtra("id",data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tv_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "去支付?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        listener.onPay(position);
                    }
                });
                dialogCustom.show();
            }
        });
        holder.tv_to_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "取消订单?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        listener.onReturnPrice(position);
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
        private TextView tv_order_status;
        private ImageView iv_title_img;
        private TextView tv_title;
        private TextView tv_goods_num;
        private TextView tv_price;
        private Button tv_to_pay;
        private Button tv_to_quxiao;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            iv_title_img = itemView.findViewById(R.id.iv_title_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_to_pay = itemView.findViewById(R.id.tv_to_pay);
            tv_to_quxiao = itemView.findViewById(R.id.tv_to_quxiao);
        }
    }

    public interface ClickListener {
        void onPay(int pos);
        void onReturnPrice(int pos);
    }
}
