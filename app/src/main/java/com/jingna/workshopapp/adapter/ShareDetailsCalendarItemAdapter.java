package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.util.Logger;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2019/6/21.
 */

public class ShareDetailsCalendarItemAdapter extends RecyclerView.Adapter<ShareDetailsCalendarItemAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private String month;
    private boolean is366 = false;

    public ShareDetailsCalendarItemAdapter(String month, List<String> data) {
        this.data = data;
        this.month = month;
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        ca.set(year, Calendar.DECEMBER, 31);
        if (ca.get(Calendar.DAY_OF_YEAR) == 366) {
            is366 = true;
        } else {
            is366 = false;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_share_details_calendar_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        boolean isSelect = false;
        for (int i = 0; i<data.size(); i++){
            if (data.get(i).equals(buL(position+1))){
                isSelect = true;
            }
        }
        if(isSelect){
            holder.tv.setTextColor(Color.parseColor("#999999"));
            holder.tv.setBackgroundResource(R.drawable.bg_999999_11dp_bord);
        }else {
            holder.tv.setTextColor(Color.parseColor("#33a190"));
            holder.tv.setBackgroundResource(R.drawable.bg_33a190_11dp_bord);
        }
        holder.tv.setText(position+1+"");
    }

    private String buL(int p){
        if(p<10){
            return "0"+p;
        }else {
            return p+"";
        }
    }

    @Override
    public int getItemCount() {
        if(month.equals("一月")){
            return 31;
        }else if(month.equals("二月")){
            if(is366){
                return 29;
            }else {
                return 28;
            }
        }else if(month.equals("三月")){
            return 31;
        }else if(month.equals("四月")){
            return 30;
        }else if(month.equals("五月")){
            return 31;
        }else if(month.equals("六月")){
            return 30;
        }else if(month.equals("七月")){
            return 31;
        }else if(month.equals("八月")){
            return 31;
        }else if(month.equals("九月")){
            return 30;
        }else if(month.equals("十月")){
            return 31;
        }else if(month.equals("十一月")){
            return 30;
        }else if(month.equals("十二月")){
            return 31;
        }
        return 31;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
