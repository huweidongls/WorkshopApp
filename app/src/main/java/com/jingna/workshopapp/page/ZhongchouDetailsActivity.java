package com.jingna.workshopapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.adapter.ZhongchouPopAdapter;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.bean.ZhongchouPopBean;
import com.jingna.workshopapp.net.NetUrl;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongchouDetailsActivity extends BaseActivity {

    private Context context = ZhongchouDetailsActivity.this;

    @BindView(R.id.webview)
    WebView webview;

    private String id = "";

    private PopupWindow popupWindow;
    private View view;

    private int popNum = 1;
    private TextView tvJian;
    private TextView tvNum;
    private TextView tvJia;
    private ImageView ivPop;
    private TextView tvTitle;
    private RecyclerView rvPop;
    private TextView tvYunfei;
    private TextView tvFahuo;
    private TextView tvQuzhichi;
    private ZhongchouPopAdapter popAdapter;
    private List<ZhongchouPopBean.DataBean> mList;
    private String dangweiId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhongchou_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(ZhongchouDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhongchouDetailsActivity.this);
        initWebView();
        initPopView();

    }

    private void initPopView() {

        view = LayoutInflater.from(context).inflate(R.layout.pop_zhongchou_details, null);
        tvJian = view.findViewById(R.id.tv_jian);
        tvNum = view.findViewById(R.id.tv_num);
        tvJia = view.findViewById(R.id.tv_jia);
        ivPop = view.findViewById(R.id.iv_pop);
        tvTitle = view.findViewById(R.id.tv_title);
        rvPop = view.findViewById(R.id.rv_dangwei);
        tvYunfei = view.findViewById(R.id.tv_yunfei);
        tvFahuo = view.findViewById(R.id.tv_fahuo);
        tvQuzhichi = view.findViewById(R.id.tv_quzhichi);

        ViseHttp.GET(NetUrl.AppCrowdFundinggetGearPositionById)
                .addParam("id", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")) {
                                Gson gson = new Gson();
                                ZhongchouPopBean popBean = gson.fromJson(data, ZhongchouPopBean.class);
                                mList = popBean.getData();
                                popAdapter = new ZhongchouPopAdapter(mList, new ZhongchouPopAdapter.ClickListener() {
                                    @Override
                                    public void onClick(int pos) {
                                        Glide.with(context).load(NetUrl.BASE_URL + mList.get(pos).getGearPictureApp()).into(ivPop);
                                        tvTitle.setText(mList.get(pos).getGearSubtitle());
                                        dangweiId = mList.get(pos).getId()+"";
                                        if(mList.get(pos).getFreight() == 0){
                                            tvYunfei.setText("配送运费：免运费");
                                        }else {
                                            tvYunfei.setText("配送运费："+mList.get(pos).getFreight()+"元");
                                        }
                                        tvFahuo.setText("预计发货时间：众筹成功后"+mList.get(pos).getDeliveryTime()+"天");
                                    }
                                });
                                GridLayoutManager manager = new GridLayoutManager(context, 4) {
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                rvPop.setLayoutManager(manager);
                                rvPop.setAdapter(popAdapter);
                                if (mList.size() > 0) {
                                    Glide.with(context).load(NetUrl.BASE_URL + mList.get(0).getGearPictureApp()).into(ivPop);
                                    tvTitle.setText(mList.get(0).getGearSubtitle());
                                    dangweiId = mList.get(0).getId()+"";
                                    if(mList.get(0).getFreight() == 0){
                                        tvYunfei.setText("配送运费：免运费");
                                    }else {
                                        tvYunfei.setText("配送运费："+mList.get(0).getFreight()+"元");
                                    }
                                    tvFahuo.setText("预计发货时间：众筹成功后"+mList.get(0).getDeliveryTime()+"天");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        tvJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popNum > 1) {
                    popNum = popNum - 1;
                    tvNum.setText(popNum + "");
                }
            }
        });
        tvJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popNum = popNum + 1;
                tvNum.setText(popNum + "");
            }
        });
        tvQuzhichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CrowdDetailsSupportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("num", popNum);
                startActivity(intent);
            }
        });

    }

    private void showPop() {

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

    private void initWebView() {

        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setTextZoom(100);
        webview.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setSupportZoom(true);
//        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(
                webview.getSettings().LOAD_NO_CACHE); // 缓存设置
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有网站的证书
            }
        });

        webview.addJavascriptInterface(new JsInterface(), "android");

        webview.loadUrl("http://192.168.2.121/detail.html?id=" + id);

    }

    public class JsInterface {
        //JS中调用Android中的方法 和返回值处理的一种方法

        /****
         * Html中的点击事件 onclick
         * <input type="button" value="结算" onclick="showToast('12')">
         *
         * @param
         */
        @JavascriptInterface
        public void toDetail(String targetid) {
            ToastUtil.showShort(context, targetid);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.rl_back, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
//                webview.evaluateJavascript("javascript:getDetailId(123)", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//                        //此处为 js 返回的结果
//                    }
//                });
                break;
            case R.id.tv_commit:
                showPop();
                break;
        }
    }

}
