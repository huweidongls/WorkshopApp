package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.CommissionIncomeBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */

public class MaintenanceAfterSaleAdapter extends RecyclerView.Adapter<MaintenanceAfterSaleAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public MaintenanceAfterSaleAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public MaintenanceAfterSaleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_maintenance_after_sale, parent, false);
        MaintenanceAfterSaleAdapter.ViewHolder holder = new MaintenanceAfterSaleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaintenanceAfterSaleAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
