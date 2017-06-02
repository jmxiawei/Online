package cn.samir.online.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;

import cn.samir.online.ui.base.ToolBarListActivity;
import cn.samir.online.util.LogUtil;

import cn.samir.online.R;
import cn.samir.online.http.ApiService;

/**
 * 接手actionurl
 */
public class AllPgcsActivity extends ToolBarListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri data = Uri.parse(getIntent().getDataString());
        String host = data.getHost();
        String path = data.getPath();
        LogUtil.e("AllPgcsActivity host=" + host + ",path=" + path);
        setToolbar(getString(R.string.str_all_author));
        setUrl(ApiService.baseUrl + ApiService.url_api_v4 + host + path);
    }
}
