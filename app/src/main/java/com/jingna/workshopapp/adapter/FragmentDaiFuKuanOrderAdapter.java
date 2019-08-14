package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.page.AfterServiceActivity;
import com.jingna.workshopapp.page.CrowdDetailsSupportActivity;
import com.jingna.workshopapp.page.OrderDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentDaiFuKuanOrderAdapter extends RecyclerView.Adapter<FragmentDaiFuKuanOrderAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public FragmentDaiFuKuanOrderAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FragmentDaiFuKuanOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_daifukuan_order, parent, false);
        FragmentDaiFuKuanOrderAdapter.ViewHolder holder = new FragmentDaiFuKuanOrderAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentDaiFuKuanOrderAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,CrowdDetailsSupportActivity.class);
                intent.putExtra("id", "1");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
