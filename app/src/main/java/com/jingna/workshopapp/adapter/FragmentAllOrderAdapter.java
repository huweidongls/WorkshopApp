package com.jingna.workshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Until;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.bean.OrderListBean;
import com.jingna.workshopapp.dialog.DialogCustom;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.page.AcceptanceActivity;
import com.jingna.workshopapp.page.CrowdDetailsActivity;
import com.jingna.workshopapp.page.CrowdDetailsSupportActivity;
import com.jingna.workshopapp.page.OrderDetailsActivity;
import com.jingna.workshopapp.page.SubmissionEvaluateActivity;
import com.jingna.workshopapp.util.Logger;
import com.jingna.workshopapp.util.StringUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2019/4/16.
 */

public class FragmentAllOrderAdapter extends RecyclerView.Adapter<FragmentAllOrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderListBean.DataBean> data;
    private ClickListener listener;

    public FragmentAllOrderAdapter(List<OrderListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_all_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getGoodsPictureApp()).into(holder.iv_title);
        holder.tv_title.setText(data.get(position).getGoodsTitle());
        holder.tv_price.setText("¥"+ StringUtils.roundByScale(data.get(position).getOrderRealPrice(), 2)+"");
        if(data.get(position).getGoodsNum()==null){
            holder.tv_goods_num.setText("共1件商品 应付款：");
        }else {
            holder.tv_goods_num.setText("共"+data.get(position).getGoodsNum()+"件商品 应付款：");
        }
        if(data.get(position).getOrderStatus().equals("0")){
            holder.tv_to_pay.setVisibility(View.VISIBLE);
            holder.tk_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.VISIBLE);
            holder.tv_order_status.setText("等待付款");
        }else if(data.get(position).getOrderStatus().equals("1") || data.get(position).getOrderStatus().equals("2")){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.VISIBLE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.GONE);
            holder.tv_order_status.setText("等待发货");
        }else if(data.get(position).getOrderStatus().equals("3")){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.VISIBLE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.GONE);
            holder.tv_order_status.setText("待收货");
        }else if(data.get(position).getOrderStatus().equals("4")){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.VISIBLE);
            holder.del_order_to.setVisibility(View.GONE);
            holder.tv_order_status.setText("待评价");
        }else if(data.get(position).getOrderStatus().equals("5")){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.VISIBLE);
            holder.tv_order_status.setText("已评价");
        }else if(data.get(position).getOrderStatus().equals("6") ){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.VISIBLE);
            holder.tv_order_status.setText("已完成");
        }else if(data.get(position).getOrderStatus().equals("7")){
            holder.tv_to_pay.setVisibility(View.GONE);
            holder.qx_to.setVisibility(View.GONE);
            holder.qrsh_to.setVisibility(View.GONE);
            holder.qpj_to.setVisibility(View.GONE);
            holder.tk_to.setVisibility(View.GONE);
            holder.del_order_to.setVisibility(View.VISIBLE);
            holder.tv_order_status.setText("已取消");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,OrderDetailsActivity.class);
                intent.putExtra("id",data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tv_to_pay.setOnClickListener(new View.OnClickListener() {

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
        holder.qx_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "是否取消订单", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.POST(NetUrl.AppOrdercancellationOrder)
                                .addParam("goodsOrderId",data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if (jsonObject.optString("data").equals("Success")){
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
        holder.del_order_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确认删除订单?", new DialogCustom.OnYesListener() {
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
        holder.qrsh_to.setOnClickListener(new View.OnClickListener() {//确认收货跳转
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, AcceptanceActivity.class);
                intent.putExtra("id", data.get(position).getId());
                intent.putExtra("imgUrl", data.get(position).getGoodsPictureApp());
                intent.putExtra("title", data.get(position).getGoodsTitle());
                context.startActivity(intent);
            }
        });
        holder.qpj_to.setOnClickListener(new View.OnClickListener() {//去评价跳转
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, SubmissionEvaluateActivity.class);
                intent.putExtra("id", data.get(position).getId());
                intent.putExtra("imgUrl", data.get(position).getGoodsPictureApp());
                intent.putExtra("title", data.get(position).getGoodsTitle());
                context.startActivity(intent);

            }
        });
        holder.tk_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "确认申请退款?", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        listener.onReturnPrice(position);
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

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_order_status;
        private ImageView iv_title;
        private TextView tv_title;
        private TextView tv_price;
        private Button del_order_to;
        private Button qpj_to;
        private Button qrsh_to;
        private Button qx_to;
        private Button tv_to_pay;
        private TextView tv_goods_num;
        private Button tk_to;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            iv_title = itemView.findViewById(R.id.iv_title);
            tv_title = itemView.findViewById(R.id.tv_title_gs);
            tv_price = itemView.findViewById(R.id.tv_prices);
            del_order_to = itemView.findViewById(R.id.del_order_to);
            qpj_to = itemView.findViewById(R.id.qpj_to);
            qrsh_to = itemView.findViewById(R.id.qrsh_to);
            qx_to = itemView.findViewById(R.id.qx_to);
            tv_to_pay = itemView.findViewById(R.id.tv_to_pay);
            tv_goods_num = itemView.findViewById(R.id.tv_goods_nums);
            tk_to=itemView.findViewById(R.id.tk_to);
        }
    }

    public interface ClickListener{
        void onPay(int pos);
        void onReturnPrice(int pos);
    }

}
