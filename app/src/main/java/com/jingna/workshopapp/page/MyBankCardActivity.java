package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.MyBankCardAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.BankCardListBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyBankCardActivity extends BaseActivity {

    private Context context = MyBankCardActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MyBankCardAdapter adapter;
    private List<BankCardListBean.DataBean> mList;

    private String userId = "";

    private PopupWindow popupWindow;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);

        type = getIntent().getStringExtra("type");
        userId = SpUtils.getUserId(context);
        StatusBarUtils.setStatusBar(MyBankCardActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MyBankCardActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("memberId", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.MemBankCardqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                BankCardListBean bean = gson.fromJson(s, BankCardListBean.class);
                mList = bean.getData();
                adapter = new MyBankCardAdapter(mList, new MyBankCardAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int pos, String bankName, String card) {
                        if(type.equals("my")){
                            showDelPop(pos, bankName, card);
                        }else {
                            Intent intent = new Intent();
                            intent.putExtra("bean", mList.get(pos));
                            setResult(1000, intent);
                            finish();
                        }
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    private void showDelPop(final int pos, String bankName, String card){

        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_bank_delete, null);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvDel = view.findViewById(R.id.tv_del);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);

        tvTitle.setText("您可对"+bankName+"尾号"+card+"的储蓄卡进行操作");

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map1 = new LinkedHashMap<>();
                map1.put("cardId", mList.get(pos).getId()+"");
                ViseUtil.Get(context, NetUrl.MemBankCardtoDelete, map1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        popupWindow.dismiss();
                        ToastUtil.showShort(context, "删除成功");
                        mList.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
