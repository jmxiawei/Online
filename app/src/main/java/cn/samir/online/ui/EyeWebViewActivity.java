package cn.samir.online.ui;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.AppInfo;
import cn.samir.online.R;
import cn.samir.online.ui.base.BaseToolBarActivity;
import cn.samir.online.util.LogUtil;
import cn.samir.online.views.EyeWebChromeClient;

public class EyeWebViewActivity extends BaseToolBarActivity {


    @BindView(R.id.webview)
    WebView webView;

    private String title;
    private String url;


    private boolean shareable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_web_view);
        ButterKnife.bind(this);

        Uri dataUri = Uri.parse(getIntent().getDataString());
        
        title = dataUri.getQueryParameter("title");
        url = dataUri.getQueryParameter("url");
        shareable = "true".equalsIgnoreCase(dataUri.getQueryParameter("share"));
        setToolbar(title);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(url);
                }
                return true;
            }


        });

        webView.setWebChromeClient(new EyeWebChromeClient());
        WebSettings webSettings = this.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ((WebSettings) webSettings).setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String str = ((WebSettings) webSettings).getUserAgentString();
        ((WebSettings) webSettings).setUserAgentString(str + " Eyepetizer" + File.separator + AppInfo.instance().getVn());
        if (Build.VERSION.SDK_INT >= 17) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        LogUtil.e("url=" + url);
        webView.addJavascriptInterface(this, "NativeCallback");
        webView.loadUrl(url);
        registerForContextMenu(webView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            ((ViewGroup) this.webView.getParent()).removeView(this.webView);
            this.webView.removeAllViews();
            this.webView.destroy();
            this.webView = null;
        }
    }

    @TargetApi(11)
    public void onPause() {
        super.onPause();
        if (this.webView != null) {
            this.webView.onPause();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.webView != null) {
            this.webView.onResume();
        }
    }


    @JavascriptInterface
    public void share(String paramString) {
        LogUtil.e("share paramString=" + paramString);
    }


    @JavascriptInterface
    public void shareVideo(String paramString) {
        LogUtil.e("shareVideo paramString=" + paramString);
    }


    @JavascriptInterface
    public boolean shareable() {
        LogUtil.e(" shareable");
        return true;
    }

    @JavascriptInterface
    public void showShareView() {
        LogUtil.e(" showShareView");
    }
}
