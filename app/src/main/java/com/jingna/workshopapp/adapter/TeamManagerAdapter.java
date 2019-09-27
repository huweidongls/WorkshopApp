package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.TeamManagementBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20.
 */

public class TeamManagerAdapter extends RecyclerView.Adapter<TeamManagerAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<TeamManagementBean.DataBean> data;

    private List<TeamManagementBean.DataBean> mFilterList = new ArrayList<>();

    public TeamManagerAdapter(List<TeamManagementBean.DataBean> data) {
        this.data = data;
        this.mFilterList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_team_manager, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+mFilterList.get(position).getHeadPhoto()).into(holder.ivAvatar);
        holder.tvName.setText(mFilterList.get(position).getMemName());
        holder.tvTime.setText(mFilterList.get(position).getNewTime());
    }

    @Override
    public int getItemCount() {
        return mFilterList == null ? 0 : mFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            //执行过滤操作
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    mFilterList = data;
                } else {
                    List<TeamManagementBean.DataBean> filteredList = new ArrayList<>();
//                    for (String str : mSourceList) {
//                        //这里根据需求，添加匹配规则
//                        if (str.contains(charString)) {
//                            filteredList.add(str.);
//                        }
//                    }
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getMemName().contains(charString)) {
                            filteredList.add(data.get(i));
                            Logger.e("321321", "对了");
                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            //把过滤后的值返回出来
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (List<TeamManagementBean.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
