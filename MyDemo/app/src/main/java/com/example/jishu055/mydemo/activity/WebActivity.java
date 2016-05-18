package com.example.jishu055.mydemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.jishu055.mydemo.R;

public class WebActivity extends BaseActivity {

    //web url 标识
    public static final String WEB_URL = "WEB_URL";
    //webview
    private WebView webView;
    //web url
    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra(WEB_URL);
        Toast.makeText(WebActivity.this, url, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webview);
        setWebView();
    }


    /**
     * 设置webview
     */
    private void setWebView() {
        // 设置setWebChromeClient对象
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Toast.makeText(WebActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        };
        webView.setWebChromeClient(webChromeClient);

        //点击后退按钮,让WebView后退一页
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {//和 onBackPressed 一样
                        webView.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        //设置WebSettings对象
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
