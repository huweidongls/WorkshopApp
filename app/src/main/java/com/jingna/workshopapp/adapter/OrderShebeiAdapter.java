package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/7.
 */

public class OrderShebeiAdapter extends RecyclerView.Adapter<OrderShebeiAdapter.ViewHolder> {

    private Context context;
    private List<PeitaoshebeiBean.DataBean> data;

    public OrderShebeiAdapter(List<PeitaoshebeiBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ordershebei, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvName.setText(data.get(position).getName());
        holder.tv.setText(data.get(position).getDayMoney()+"元/"+data.get(position).getCompany());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}