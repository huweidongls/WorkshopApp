package com.jingna.workshopapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CommentAddPicAdapter;
import com.jingna.workshopapp.adapter.MaintenanceAfterSaleAdapter;
import com.jingna.workshopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaintenanceAfterSaleActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private List<String> mList;
    private MaintenanceAfterSaleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_after_sale);
        StatusBarUtils.setStatusBar(MaintenanceAfterSaleActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MaintenanceAfterSaleActivity.this);
        initData();
    }
    private void initData(){
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        adapter = new MaintenanceAfterSaleAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(MaintenanceAfterSaleActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
