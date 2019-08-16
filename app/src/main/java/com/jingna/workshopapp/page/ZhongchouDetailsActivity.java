package com.jingna.workshopapp.page;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhongchouDetailsActivity extends BaseActivity {

    private Context context = ZhongchouDetailsActivity.this;

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhongchou_details);

        StatusBarUtils.setStatusBar(ZhongchouDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhongchouDetailsActivity.this);
        initWebView();

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

        webview.loadUrl("http://192.168.2.121/detail.html");

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
