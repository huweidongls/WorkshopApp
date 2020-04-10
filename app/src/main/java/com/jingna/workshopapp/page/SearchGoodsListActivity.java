package com.jingna.workshopapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.CrowdFundingListAdapter;
import com.jingna.workshopapp.adapter.EquipmentListAdapter;
import com.jingna.workshopapp.adapter.MyBankCardAdapter;
import com.jingna.workshopapp.adapter.workshopListAdapter;
import com.jingna.workshopapp.bean.AddressBean;
import com.jingna.workshopapp.bean.LikeGoodsBean;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.jingna.workshopapp.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodsListActivity extends AppCompatActivity {

    private Context context = SearchGoodsListActivity.this;

    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;
    @BindView(R.id.rv3)
    RecyclerView rv3;
    private String LikeName = "";
    private workshopListAdapter adapter1;
    private EquipmentListAdapter adapter2;
    private CrowdFundingListAdapter adapter3;
    private List<LikeGoodsBean.DataBean.ShopCategoriesBean.ShopCategorysBean> ShopmList;
    private List<LikeGoodsBean.DataBean.ShopCategoriesWtjgBean.ShopCategorysBeanX> EquipmentmList;
    private List<LikeGoodsBean.DataBean.ListBean.CrowdFundingsBean> CrowdmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_list);
        LikeName = getIntent().getStringExtra("goodsName");
        StatusBarUtils.setStatusBar(SearchGoodsListActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SearchGoodsListActivity.this);
        initData();
    }

    private void initData() {
        String url = "/AppShopCategory/findByName";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", LikeName);
        ViseUtil.Get(context, url, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                LikeGoodsBean bean = gson.fromJson(s, LikeGoodsBean.class);
                ShopmList = bean.getData().getShopCategories().getShopCategorys();
                EquipmentmList = bean.getData().getShopCategoriesWtjg().getShopCategorys();
                CrowdmList = bean.getData().getList().getCrowdFundings();
                adapter1 = new workshopListAdapter(ShopmList);
                adapter2 = new EquipmentListAdapter(EquipmentmList);
                adapter3 = new CrowdFundingListAdapter(CrowdmList);
                LinearLayoutManager manager = new LinearLayoutManager(SearchGoodsListActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv1.setLayoutManager(manager);
                rv1.setAdapter(adapter1);
                LinearLayoutManager manager2 = new LinearLayoutManager(SearchGoodsListActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager2.setOrientation(LinearLayoutManager.VERTICAL);
                rv2.setLayoutManager(manager2);
                rv2.setAdapter(adapter2);
                LinearLayoutManager manager3 = new LinearLayoutManager(SearchGoodsListActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager3.setOrientation(LinearLayoutManager.VERTICAL);
                rv3.setLayoutManager(manager3);
                rv3.setAdapter(adapter3);
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
