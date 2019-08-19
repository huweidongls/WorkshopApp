package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.ZhongchouPopBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/19.
 */

public class ZhongchouPopAdapter extends RecyclerView.Adapter<ZhongchouPopAdapter.ViewHolder> {

    private Context context;
    private List<ZhongchouPopBean.DataBean> data;
    private int isSelect = 0;
    private ClickListener listener;

    public ZhongchouPopAdapter(List<ZhongchouPopBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_zhongchou_pop, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (isSelect == position){
            holder.tv.setBackgroundResource(R.mipmap.zc_pop_select);
        }else {
            holder.tv.setBackgroundResource(R.drawable.bg_999999_2dp_bord);
        }
        holder.tv.setText("¥"+data.get(position).getGearMoney());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelect = position;
                notifyDataSetChanged();
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public interface ClickListener{
        void onClick(int pos);
    }

}
