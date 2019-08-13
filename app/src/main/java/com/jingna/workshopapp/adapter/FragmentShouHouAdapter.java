package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.workshopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/8/13.
 */

public class FragmentShouHouAdapter extends RecyclerView.Adapter<FragmentShouHouAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public FragmentShouHouAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FragmentShouHouAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_to_be_investigated, parent, false);
        FragmentShouHouAdapter.ViewHolder holder = new FragmentShouHouAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentShouHouAdapter.ViewHolder holder, int position) {

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
