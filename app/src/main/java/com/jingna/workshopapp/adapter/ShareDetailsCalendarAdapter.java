package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.ShareDetailsBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/21.
 */

public class ShareDetailsCalendarAdapter extends RecyclerView.Adapter<ShareDetailsCalendarAdapter.ViewHolder> {

    private Context context;
    private List<ShareDetailsBean.DataBean.TimesBean> data;

    public ShareDetailsCalendarAdapter(List<ShareDetailsBean.DataBean.TimesBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_share_details_calendar, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvMonth.setText(data.get(position).getMonth());
        List<String> list = data.get(position).getTime();
        ShareDetailsCalendarItemAdapter itemAdapter = new ShareDetailsCalendarItemAdapter(data.get(position).getMonth(), list);
        GridLayoutManager manager = new GridLayoutManager(context, 7);
        holder.rvCalendar.setLayoutManager(manager);
        holder.rvCalendar.setAdapter(itemAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvCalendar;
        private TextView tvMonth;

        public ViewHolder(View itemView) {
            super(itemView);
            rvCalendar = itemView.findViewById(R.id.rv_calendar);
            tvMonth = itemView.findViewById(R.id.tv_month);
        }
    }

}
