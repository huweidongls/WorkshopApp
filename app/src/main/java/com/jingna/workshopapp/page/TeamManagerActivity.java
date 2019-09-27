package com.jingna.workshopapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.TeamManagerAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.TeamManagementBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.SpUtils;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamManagerActivity extends BaseActivity {

    private Context context = TeamManagerActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;

    private TeamManagerAdapter adapter;
    private List<TeamManagementBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_manager);

        StatusBarUtils.setStatusBar(TeamManagerActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(TeamManagerActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET(NetUrl.MemUserteamManagement)
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                TeamManagementBean bean = gson.fromJson(data, TeamManagementBean.class);
                                mList = bean.getData();
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                adapter = new TeamManagerAdapter(mList);
                                recyclerView.setAdapter(adapter);
                                etSearch.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                                        adapter.getFilter().filter(sequence.toString());
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
