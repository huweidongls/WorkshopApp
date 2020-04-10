package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.bean.MemberCommissionAuditsBean;
import com.jingna.workshopapp.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class CommissionIncomeItemAdapter extends RecyclerView.Adapter<CommissionIncomeItemAdapter.ViewHolder> {

    private Context context;
    private List<CommissionIncomeBean.DataBean.CommissionRevenuesBean> data;
    private List<MemberCommissionAuditsBean.DataBean.MemberCommissionAudits> data1;
    private int type = 0;

    public CommissionIncomeItemAdapter(List<CommissionIncomeBean.DataBean.CommissionRevenuesBean> data) {
        this.data = data;
    }

    public CommissionIncomeItemAdapter(List<MemberCommissionAuditsBean.DataBean.MemberCommissionAudits> data1, int type) {
        this.data1 = data1;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_commission_income_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(type == 1){
            holder.tv.setText("转出");
            holder.tvTime.setText(data1.get(position).getCreateTime());
            holder.tvMoney.setText(StringUtils.roundByScale(data1.get(position).getAuditMoney(), 2)+"");
        }else {
            holder.tv.setText(data.get(position).getType());
            holder.tvTime.setText(data.get(position).getCreateTime());
            holder.tvMoney.setText(StringUtils.roundByScale(data.get(position).getMoney(), 2)+"");
        }
    }

    @Override
    public int getItemCount() {
        if(type == 1){
            return data1 == null ? 0 : data1.size();
        }else {
            return data == null ? 0 : data.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private TextView tvTime;
        private TextView tvMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvMoney = itemView.findViewById(R.id.tv_money);
        }
    }

}
