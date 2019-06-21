package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/6/21.
 */

public class ShareDetailsCalendarAdapter extends RecyclerView.Adapter<ShareDetailsCalendarAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public ShareDetailsCalendarAdapter(List<String> data) {
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
        ShareDetailsCalendarItemAdapter itemAdapter = new ShareDetailsCalendarItemAdapter(data);
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

        public ViewHolder(View itemView) {
            super(itemView);
            rvCalendar = itemView.findViewById(R.id.rv_calendar);
        }
    }

}
