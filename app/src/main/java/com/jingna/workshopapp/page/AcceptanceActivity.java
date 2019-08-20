package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommentAddPicAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcceptanceActivity extends BaseActivity {

    private Context context = AcceptanceActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private CommentAddPicAdapter adapter;
    private List<String> mList;

    private int REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance);

        StatusBarUtils.setStatusBar(AcceptanceActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(AcceptanceActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        adapter = new CommentAddPicAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CommentAddPicAdapter.ClickListener() {
            @Override
            public void addImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9-mList.size()) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(AcceptanceActivity.this, REQUEST_CODE); // 打开相册
            }

            @Override
            public void deleteImg(int i) {
                mList.remove(i);
                adapter.notifyDataSetChanged();
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
