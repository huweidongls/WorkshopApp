package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.MaintenancEequipmentBean;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/26.
 */

public class MaintenancEequipmentAdapter extends RecyclerView.Adapter<MaintenancEequipmentAdapter.ViewHolder> {
    private Context context;
    private List<MaintenancEequipmentBean.DataBean> data;
    private int is_select=0;
    public MaintenancEequipmentAdapter(List<MaintenancEequipmentBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public MaintenancEequipmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_maintenanc_eequipment, parent, false);
        MaintenancEequipmentAdapter.ViewHolder holder = new MaintenancEequipmentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaintenancEequipmentAdapter.ViewHolder holder, final int position) {
        if(data.get(position).getIsSelect() == 0){
            Glide.with(context).load(R.mipmap.dh_null).into(holder.iv);
        }else {
            Glide.with(context).load(R.mipmap.dh).into(holder.iv);
        }
        holder.tvName.setText(data.get(position).getEquipmentName());
        holder.tv.setText(data.get(position).getEquipmentModel());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getIsSelect() == 0){
                    data.get(position).setIsSelect(1);
                    notifyDataSetChanged();
                }else {
                    data.get(position).setIsSelect(0);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tv;
        private ImageView iv;
        private RelativeLayout rl;
        public ViewHolder(View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
