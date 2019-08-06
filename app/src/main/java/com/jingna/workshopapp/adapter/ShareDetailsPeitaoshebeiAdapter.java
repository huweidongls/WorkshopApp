package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.ShareDetailsBean;
import com.jingna.workshopapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/8/6.
 */

public class ShareDetailsPeitaoshebeiAdapter extends RecyclerView.Adapter<ShareDetailsPeitaoshebeiAdapter.ViewHolder> {

    private Context context;
    private List<ShareDetailsBean.DataBean.SupportingEquipmentsBean> data;

    public ShareDetailsPeitaoshebeiAdapter(List<ShareDetailsBean.DataBean.SupportingEquipmentsBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_sharedetails_peitaoshebei, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getEquipmentName());
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getEquipmentIconApp()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
