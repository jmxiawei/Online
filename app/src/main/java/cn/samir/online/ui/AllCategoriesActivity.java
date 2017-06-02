package cn.samir.online.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.samir.online.R;
import cn.samir.online.http.ApiService;
import cn.samir.online.ui.base.ToolBarListActivity;
import cn.samir.online.ui.fragment.AllCategoriesFragment;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class AllCategoriesActivity extends ToolBarListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri data = Uri.parse(getIntent().getDataString());
        String host = data.getHost();
        String path = data.getPath();
        setToolbar(getString(R.string.str_all_cate));
        setUrl(ApiService.baseUrl + ApiService.url_api_v4 + host + path);
    }

    @Override
    protected Fragment newInstance(String url) {
        return AllCategoriesFragment.newInstance(url);
    }
}
