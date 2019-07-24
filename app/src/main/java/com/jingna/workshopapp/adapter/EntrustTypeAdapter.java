package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.EntrustTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */

public class EntrustTypeAdapter extends RecyclerView.Adapter<EntrustTypeAdapter.ViewHolder> {

    private Context context;
    private List<EntrustTypeBean.DataBean> data;
    private int select = 0;
    private ClickListener listener;

    public EntrustTypeAdapter(List<EntrustTypeBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_entrust_type, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getName());
        if(select == position){
            holder.tv.setBackgroundColor(Color.parseColor("#008488"));
            holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
        }else {
            holder.tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tv.setTextColor(Color.parseColor("#000000"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = position;
                notifyDataSetChanged();
                listener.onItemClick(position);
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
        void onItemClick(int pos);
    }

}
