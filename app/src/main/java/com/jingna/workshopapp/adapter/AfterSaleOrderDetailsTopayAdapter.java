package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.AfterSaleOrderDetailsToPayBean;
import com.jingna.workshopapp.bean.AfterSaleOrderListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/28.
 */

public class AfterSaleOrderDetailsTopayAdapter extends RecyclerView.Adapter<AfterSaleOrderDetailsTopayAdapter.ViewHolder>{
    private Context context;
    private List<AfterSaleOrderDetailsToPayBean.DataBean.AfterSaleOrderItemsBean> data;
    public AfterSaleOrderDetailsTopayAdapter(List<AfterSaleOrderDetailsToPayBean.DataBean.AfterSaleOrderItemsBean> data) {
        this.data = data;
    }
    @Override
    public AfterSaleOrderDetailsTopayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_after_order_details_topay, parent, false);
        AfterSaleOrderDetailsTopayAdapter.ViewHolder holder = new AfterSaleOrderDetailsTopayAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AfterSaleOrderDetailsTopayAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getName());
        holder.tv.setText(data.get(position).getMoney()+"/"+data.get(position).getCompany());
        holder.tv_num.setText("x"+data.get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv;
        private TextView tv_num;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv = itemView.findViewById(R.id.tv);
            tv_num = itemView.findViewById(R.id.tv_num);
        }
    }
}
