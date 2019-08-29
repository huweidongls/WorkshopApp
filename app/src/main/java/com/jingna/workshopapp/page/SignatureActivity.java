package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
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
                        linePathView.save(dir.getAbsolutePath()+System.currentTimeMillis()+".jpg", true, 10);
                        setResult(100);
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
