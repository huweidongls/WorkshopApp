package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/6/19.
 */

public class CommissionIncomeAdapter extends RecyclerView.Adapter<CommissionIncomeAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public CommissionIncomeAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_commission_income, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }

}
