package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.AfterSaleOrderDetailsBean;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/28.
 */

public class AfterSaleOrderDetailsAdapter extends RecyclerView.Adapter<AfterSaleOrderDetailsAdapter.ViewHolder>{
    private Context context;
    private List<AfterSaleOrderDetailsBean.DataBean.AppAfterSaleEquipmentsBean> data;
    public AfterSaleOrderDetailsAdapter(List<AfterSaleOrderDetailsBean.DataBean.AppAfterSaleEquipmentsBean> data) {
        this.data = data;
    }
    @Override
    public AfterSaleOrderDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_after_sale_order_details, parent, false);
        AfterSaleOrderDetailsAdapter.ViewHolder holder = new AfterSaleOrderDetailsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AfterSaleOrderDetailsAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getEquipmentName());
        holder.tv.setText(data.get(position).getEquipmentModel());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
