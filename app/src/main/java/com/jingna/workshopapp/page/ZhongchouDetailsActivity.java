package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.util.ToastUtil;

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
        tvJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popNum > 1){
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

    }

    private void showPop(){

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

        webview.loadUrl("http://192.168.2.121/detail.html?id="+id);

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
    public void onClick(View view){
        switch (view.getId()){
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
