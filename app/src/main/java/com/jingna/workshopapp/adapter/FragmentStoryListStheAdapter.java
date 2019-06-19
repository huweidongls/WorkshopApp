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

public class FragmentStoryListStheAdapter extends RecyclerView.Adapter<FragmentStoryListStheAdapter.ViewHolder>{

    private Context context;
    private List<String> data;

    public FragmentStoryListStheAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public FragmentStoryListStheAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_story_list, parent, false);
        FragmentStoryListStheAdapter.ViewHolder holder = new FragmentStoryListStheAdapter.ViewHolder(view);
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

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
