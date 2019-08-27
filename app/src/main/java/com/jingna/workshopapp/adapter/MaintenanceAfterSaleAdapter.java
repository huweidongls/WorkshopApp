package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.AfterSaleOrderListBean;
import com.jingna.workshopapp.bean.CommissionIncomeBean;
import com.jingna.workshopapp.dialog.DialogCustom;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/8/23.
 */

public class MaintenanceAfterSaleAdapter extends RecyclerView.Adapter<MaintenanceAfterSaleAdapter.ViewHolder>{
    private Context context;
    private List<AfterSaleOrderListBean.DataBean> data;
    private ClickListener listener;
    public MaintenanceAfterSaleAdapter(List<AfterSaleOrderListBean.DataBean> data,ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }
    @Override
    public MaintenanceAfterSaleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_maintenance_after_sale, parent, false);
        MaintenanceAfterSaleAdapter.ViewHolder holder = new MaintenanceAfterSaleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaintenanceAfterSaleAdapter.ViewHolder holder, final int position) {
        holder.tv_order_sn.setText(data.get(position).getId());
        holder.tv_goods_name.setText(data.get(position).getDeviceName());
        holder.tv_address.setText(data.get(position).getAddresName());
        holder.tv_tel.setText(data.get(position).getAddresPhone());
        holder.tv_count_size.setText("共"+data.size()+"件维修");
        if (data.get(position).getOrderStatus().equals("1")){
            holder.tv_status.setText("未分配到工程师");
            holder.btn_order.setVisibility(View.VISIBLE);
            holder.del_order.setVisibility(View.GONE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.GONE);
        }else if(data.get(position).getOrderStatus().equals("0")){
            holder.tv_status.setText("订单已取消");
            holder.btn_order.setVisibility(View.GONE);
            holder.del_order.setVisibility(View.VISIBLE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.GONE);
        }else if(data.get(position).getOrderStatus().equals("4")){
            holder.tv_status.setText("¥"+data.get(position).getOrderRealPrice());
            holder.btn_order.setVisibility(View.GONE);
            holder.del_order.setVisibility(View.VISIBLE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.GONE);
        }else if(data.get(position).getOrderStatus().equals("2")){
            holder.tv_status.setText("维修中");
            holder.btn_order.setVisibility(View.GONE);
            holder.del_order.setVisibility(View.GONE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.VISIBLE);
        }else if(data.get(position).getOrderStatus().equals("3")){
            holder.tv_status.setText("维修中");
            holder.btn_order.setVisibility(View.GONE);
            holder.del_order.setVisibility(View.GONE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.VISIBLE);
        }else if(data.get(position).getOrderStatus().equals("5")){
            holder.tv_status.setText("¥"+data.get(position).getOrderRealPrice());
            holder.btn_order.setVisibility(View.GONE);
            holder.del_order.setVisibility(View.VISIBLE);
            holder.to_pay.setVisibility(View.GONE);
            holder.radio_order.setVisibility(View.GONE);
        }
        holder.del_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确定删除?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET(NetUrl.AfterSaleOrdertoDeleteRepairOrder)
                                .addParam("orderId",data.get(position).getId())
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
        holder.btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确定取消订单?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET(NetUrl.AfterSaleOrdergetByOrderRepairId)
                                .addParam("orderId",data.get(position).getId())
                                .addParam("orderStatus","0")
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if (jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "取消订单成功!");
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
        holder.to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "去支付?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        listener.onPay(position);
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
        private TextView tv_order_sn;
        private TextView tv_goods_name;
        private TextView tv_address;
        private TextView tv_tel;
        private TextView tv_count_size;
        private TextView tv_status;
        private Button btn_order;
        private Button del_order;
        private Button to_pay;
        private Button radio_order;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_sn = itemView.findViewById(R.id.tv_order_sn);
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_tel = itemView.findViewById(R.id.tv_tel);
            tv_count_size = itemView.findViewById(R.id.tv_count_size);
            tv_status = itemView.findViewById(R.id.tv_status);
            btn_order = itemView.findViewById(R.id.btn_order);
            del_order = itemView.findViewById(R.id.del_order);
            to_pay = itemView.findViewById(R.id.to_pay);
            radio_order = itemView.findViewById(R.id.radio_order);
        }
    }
    public interface ClickListener{
        void onPay(int pos);
    }
}
