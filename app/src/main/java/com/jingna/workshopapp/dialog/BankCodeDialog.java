package com.jingna.workshopapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.app.MyApplication;

/**
 * Created by Administrator on 2019/6/19.
 */

public class BankCodeDialog extends Dialog {

    private Context context;
    private RelativeLayout rlGetCode;
    private TextView tvGetCode;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;

    public BankCodeDialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.bankCodeTimeCount.setActivity(this);
        init();
    }

    public TextView getCode_btn(){
        return tvGetCode;
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bank_code, null);
        setContentView(view);

        rlGetCode = view.findViewById(R.id.rl_get_code);
        tvGetCode = view.findViewById(R.id.tv_get_code);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onSure();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rlGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.bankCodeTimeCount.start();
            }
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
