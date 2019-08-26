package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class AfterSaleEquipmentAdapter extends RecyclerView.Adapter<AfterSaleEquipmentAdapter.ViewHolder>{
    private Context context;
    private List<MaintenancEequipmentBean.DataBean> data;

    public AfterSaleEquipmentAdapter(List<MaintenancEequipmentBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public AfterSaleEquipmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_repair_list, parent, false);
        AfterSaleEquipmentAdapter.ViewHolder holder = new AfterSaleEquipmentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AfterSaleEquipmentAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getEquipmentName());
        holder.tv.setText(data.get(position).getEquipmentModel());
        holder.tvNum.setText("1");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tv;
        private RelativeLayout rlJian;
        private RelativeLayout rlJia;
        private TextView tvNum;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv = itemView.findViewById(R.id.tv);
            rlJian = itemView.findViewById(R.id.rl_jian);
            rlJia = itemView.findViewById(R.id.rl_jia);
            tvNum = itemView.findViewById(R.id.tv_num);
        }
    }
}
