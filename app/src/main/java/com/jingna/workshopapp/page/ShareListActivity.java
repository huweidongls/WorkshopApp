package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.ShareListAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareListActivity extends BaseActivity {

    private Context context = ShareListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ShareListAdapter adapter;
    private List<String> mList;

    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_list);

        name = getIntent().getStringExtra("name");
        StatusBarUtils.setStatusBar(ShareListActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ShareListActivity.this);
        initData();

    }

    private void initData() {

        tvTitle.setText(name);
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        adapter = new ShareListAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

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