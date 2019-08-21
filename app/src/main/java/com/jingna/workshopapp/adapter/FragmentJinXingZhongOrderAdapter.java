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
import com.jingna.workshopapp.page.AcceptanceActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentJinXingZhongOrderAdapter extends RecyclerView.Adapter<FragmentJinXingZhongOrderAdapter.ViewHolder>{
    private Context context;
    private List<OrderListBean.DataBean> data;
    private ClickListener listener;
    public FragmentJinXingZhongOrderAdapter(List<OrderListBean.DataBean> data,ClickListener listener) {
        this.data = data;
        this.listener=listener;
    }
    @Override
    public FragmentJinXingZhongOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_jinxingzhong_order, parent, false);
        FragmentJinXingZhongOrderAdapter.ViewHolder holder = new FragmentJinXingZhongOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentJinXingZhongOrderAdapter.ViewHolder holder, final int position) {
        if (data.get(position).getOrderStatus().equals("1") || data.get(position).getOrderStatus().equals("2")){
            holder.tv_order_status.setText("等待发货");
            holder.tv_to_tuikuan.setVisibility(View.VISIBLE);
            holder.tv_to_shouhuo.setVisibility(View.GONE);
        }else if(data.get(position).getOrderStatus().equals("3")){
            holder.tv_order_status.setText("已发货");
            holder.tv_to_tuikuan.setVisibility(View.GONE);
            holder.tv_to_shouhuo.setVisibility(View.VISIBLE);
        }
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGoodsPictureApp()).into(holder.iv_title);
        holder.tv_title.setText(data.get(position).getGoodsTitle());
        holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        holder.tv_price.setText("¥"+data.get(position).getOrderPrice()+"");
        holder.tv_to_tuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确认退款?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        listener.onReturnPrice(position);
                    }
                });
                dialogCustom.show();
            }
        });
        holder.tv_to_shouhuo.setOnClickListener(new View.OnClickListener() {//确认收货跳转
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确认收货?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        Intent intent = new Intent();
                        intent.setClass(context, AcceptanceActivity.class);
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
        private Button tv_to_tuikuan;
        private Button tv_to_shouhuo;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_title = itemView.findViewById(R.id.iv_title);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_to_shouhuo = itemView.findViewById(R.id.tv_to_shouhuo);
            tv_to_tuikuan = itemView.findViewById(R.id.tv_to_tuikuan);
        }
    }

    public interface ClickListener {
        void onReturnPrice(int pos);
    }
}
