package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.dialog.DialogCustom;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/8/8.
 */

public class FragmentYiQuXiaoAdapter extends RecyclerView.Adapter<FragmentYiQuXiaoAdapter.ViewHolder>{
    private Context context;
    private List<OrderListBean.DataBean> data;

    public FragmentYiQuXiaoAdapter(List<OrderListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public FragmentYiQuXiaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_yiquxiao_order, parent, false);
        FragmentYiQuXiaoAdapter.ViewHolder holder = new FragmentYiQuXiaoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentYiQuXiaoAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGoodsPictureApp()).into(holder.iv_title);
        holder.tv_order_status.setText("已完成");
        holder.tv_title.setText(data.get(position).getGoodsTitle());
        holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        holder.tv_price.setText("¥"+data.get(position).getOrderPrice()+"");
        holder.tv_to_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确认删除?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET(NetUrl.AppOrdertoDelete)
                                .addParam("goodsOrderId",data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if (jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "删除订单成功!");
                                                data.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void onFail(int errCode, String errMsg) {
                                    }
                                });
                    }
                });
                dialogCustom.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_title;
        private TextView tv_order_status;
        private TextView tv_title;
        private TextView tv_goods_num;
        private TextView tv_price;
        private Button tv_to_del;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_title = itemView.findViewById(R.id.iv_title);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_to_del = itemView.findViewById(R.id.tv_to_del);
        }
    }
}
