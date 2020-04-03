package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.PeitaoshebeiBean;
import com.jingna.workshopapp.util.StringUtils;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(data.get(position).getName());
        holder.tv.setText(StringUtils.roundByScale(data.get(position).getDayMoney(), 2)+"元/"+data.get(position).getCompany());
        holder.tvNum.setText(data.get(position).getNum()+"");

        holder.rlJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = data.get(position).getNum();
                if(num > 1){
                    num = num - 1;
                    data.get(position).setNum(num);
                    holder.tvNum.setText(num+"");
                }
            }
        });

        holder.rlJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = data.get(position).getNum();
                num = num + 1;
                data.get(position).setNum(num);
                holder.tvNum.setText(num+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

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