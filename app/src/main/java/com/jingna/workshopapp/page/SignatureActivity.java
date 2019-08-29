package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.widget.LinePathView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignatureActivity extends BaseActivity {

    private Context context = SignatureActivity.this;

    @BindView(R.id.view)
    LinePathView linePathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        StatusBarUtils.setStatusBar(SignatureActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SignatureActivity.this);
        initData();

    }

    private void initData() {

        //修改背景、笔宽、颜色
        linePathView.setBackColor(Color.WHITE);
        linePathView.setPaintWidth(20);
        linePathView.setPenColor(Color.BLACK);

    }

    @OnClick({R.id.rl_back, R.id.tv_cancel, R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_cancel:
                linePathView.clear();
                linePathView.setBackColor(Color.WHITE);
                linePathView.setPaintWidth(20);
                linePathView.setPenColor(Color.BLACK);
                break;
            case R.id.tv_save:
                if (linePathView.getTouched()) {
                    try {
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        File dir = new File(path+"/workshop/");
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        String s = dir.getAbsolutePath()+"/"+System.currentTimeMillis()+".jpg";
                        linePathView.save(s, true, 10);
                        Intent intent = new Intent();
                        intent.putExtra("pash", s);
                        setResult(1002, intent);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.showShort(context, "您没有签名~");
                }
                break;
        }
    }

}
