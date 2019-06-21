package com.jingna.workshopapp.page;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.BackCardListAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionActivity extends BaseActivity {
    private List<String> mList;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.btn_mone)
    Button btn_mone;
    RecyclerView recyclerView;
    private PopupWindow popupWindow;
    private BackCardListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);
        StatusBarUtils.setStatusBar(CommissionActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommissionActivity.this);
        initData();
    }
    private void initData() {
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String message = s.toString();
                if(TextUtils.isEmpty(message)){
                    btn_mone.setBackgroundColor(Color.parseColor("#9CE6BF"));
                    ToastUtil.showShort(CommissionActivity.this,"请填写提现金额");
                }else{
                    btn_mone.setBackgroundColor(Color.parseColor("#02C160"));
                }

            }
        });
    }
    private  void init_back(){
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new BackCardListAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(CommissionActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    @OnClick({R.id.btn_mone,R.id.all})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_mone:
                String msg = money.getText().toString();
                if (TextUtils.isEmpty(msg)){
                    ToastUtil.showShort(CommissionActivity.this,"请填写提现金额");
                }else{
                    showBankPop();
                }
                break;
            case R.id.all:
                money.setText("5000");
                money.setSelection(money.getText().toString().length());
                break;
        }
    }
    private void showBankPop(){
        View view = LayoutInflater.from(CommissionActivity.this).inflate(R.layout.popupwindow_bankcardlist, null);

        recyclerView = view.findViewById(R.id.rv_bankcard);
        init_back();
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
}
