package cn.samir.online.ui.base;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.domains.model.ParcelableString;
import cn.samir.domains.model.Tab;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.events.HomeProfileEvent;
import cn.samir.online.events.VideoEvent;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.ui.FeedIssueActivity;
import cn.samir.online.ui.MessageListActivity;
import cn.samir.online.ui.TabViewPagerActivity;
import cn.samir.online.ui.video.VideoDetailActivity;
import cn.samir.online.util.Constant;
import cn.samir.online.util.LogUtil;
import cn.samir.online.util.Utils;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by xiaw on 2017/3/17 0017.
 */

public class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {


    public int REQ_CODE_SD_CARD = 1;

    private BaseActivity mBaseContext;

    private BaseHttpHandler mBaseHttpHandler;

    private BroadcastReceiver EventReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (Constant.ACTION_EVENT.equals(intent.getAction())) {
                Parcelable data = intent.getParcelableExtra(Constant.ACTION_EVENT_DATA);
                if (data instanceof Video || data instanceof VideoEvent) {
                    if (data instanceof Video) {
                        Intent intent1 = VideoDetailActivity.newIntent(mBaseContext, (Video) data);
                        startActivity(intent1);
                    } else {
                        Intent intent1 = VideoDetailActivity.newIntent(mBaseContext, (VideoEvent) data);
                        startActivity(intent1);
                    }
                } else if (data instanceof ParcelableString) {
                    Intent intent1 = startIntentByActionUrl(((ParcelableString) data).getData());
                    if (intent1 == null) {
                        Intent actionIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((ParcelableString) data).getData()));
                        startActivity(actionIntent);
                    } else {
                        startActivity(intent1);
                    }
                } else if (data instanceof IssueNavigationCard) {
                    Intent issueIntent = FeedIssueActivity.toThis(mBaseContext, (IssueNavigationCard) data);
                    startActivity(issueIntent);
                } else if (data instanceof HomeProfileEvent) {
                    startActivityHomeProfilePage((HomeProfileEvent) data);
                }
            }
        }

    };

    private void startActivityHomeProfilePage(HomeProfileEvent data) {

        switch (data.getId()) {
            case R.string.text_profiles_msg:
                Intent toMessage = new Intent(this, MessageListActivity.class);
                startActivity(toMessage);
                break;
        }


    }

    private int getIntValue(Uri uri, String key) {
        if (Utils.isEmpty(key)) {
            return 0;
        }
        String v = uri.getQueryParameter(key);
        if (Utils.isEmpty(v) || !TextUtils.isDigitsOnly(v)) {
            return 0;
        }
        return Integer.parseInt(v);
    }

    /**
     * get intent by actionUrl
     *
     * @param actionUrl
     * @return
     */
    public Intent startIntentByActionUrl(String actionUrl) {


        Uri uri = Uri.parse(actionUrl);
        String host = uri.getHost();
        if (ApiService.action_rankList.equalsIgnoreCase(host)) {
            // ranklist 排行榜
            String url = ApiService.baseUrl + ApiService.url_api_v4 + ApiService.action_rankList;
            int tab = getIntValue(uri, "tabIndex");
            Intent toRankList = TabViewPagerActivity.newIntent(this, getString(R.string.str_hot_ranklist), url, tab);
            return toRankList;
        } else if (ApiService.action_campaign.equals(host)) {
            // 精选热门专题
            String path = uri.getPath();
            if ("list".equals(path)) {
                String url = ApiService.baseUrl + ApiService.url_api_v3 + "specialTopics";
                Intent toCampaign = ToolBarListActivity.newIntent(this, getString(R.string.str_hot_album), url);
                return toCampaign;
            }
        } else if (ApiService.action_tag.equals(host)) {
            // rectageCard
            String path = uri.getPath().replace("/", "");

            String urlDate = ApiService.baseUrl + ApiService.url_api_v3 + ApiService.action_tag + "/videos?tagId=" + path + "&strategy=shareCount";

            String urlShareCount = ApiService.baseUrl + ApiService.url_api_v3 + ApiService.action_tag + "/videos?tagId=" + path + "&strategy=shareCount";
            String title = uri.getQueryParameter("title");

            Tab tabDate = new Tab();
            tabDate.setApiUrl(urlDate);
            tabDate.setName(getString(R.string.str_order_by_time));

            Tab tabShareCount = new Tab();
            tabShareCount.setApiUrl(urlShareCount);
            tabShareCount.setName(getString(R.string.str_order_by_share));

            ArrayList<Tab> tabs = new ArrayList<>();
            tabs.add(tabDate);
            tabs.add(tabShareCount);
            //tabs.add(tabShareCount1);
            Intent toRankList = TabViewPagerActivity.newWithData(this, title, tabs, 0);
            return toRankList;


        } else if (ApiService.action_category.equals(host)) {

            //Intent toCategory = new Intent(this,TabViewPagerActivity.class);
        }

        return null;

    }

    public void startActivitySchema(String schema) {
        Intent action = new Intent(Intent.ACTION_VIEW, Uri.parse(schema));
        startActivity(action);
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter actionFileter = new IntentFilter(Constant.ACTION_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(EventReceive, actionFileter);


        LogUtil.e(getClass().getSimpleName() + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(EventReceive);

        LogUtil.e(getClass().getSimpleName() + " onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e(getClass().getSimpleName() + " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(getClass().getSimpleName() + " onDestroy");

    }

    public BaseHttpHandler getBaseHttpHandler() {
        return mBaseHttpHandler;
    }

    public boolean requestPermission(int code) {

        String[] permissions = generatePermissions(code);
        if (!EasyPermissions.hasPermissions(this, permissions)) {

            String desc = "";
            if (code == REQ_CODE_SD_CARD) {
                desc = "浏览图片需要缓存内存卡，节约流量";
            }
            EasyPermissions.requestPermissions(this, desc,
                    code, permissions);
            return false;
        }
        return true;
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);


        LogUtil.e(getClass().getSimpleName() + " setContentView");
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseContext = this;
        mBaseHttpHandler = new BaseHttpHandler();

        LogUtil.e(getClass().getSimpleName() + " onCreate");

    }

    /**
     * @param code
     * @return
     */
    private String[] generatePermissions(int code) {
        String[] ps = null;
        if (code == REQ_CODE_SD_CARD) {
            ps = new String[2];
            ps[0] = Manifest.permission.READ_EXTERNAL_STORAGE;
            ps[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        }
        return ps;
    }


    //    String[] perms = {Manifest.permission.CAMERA, Manifest.permission.CHANGE_WIFI_STATE};
//if (EasyPermissions.hasPermissions(this, perms)) {
//        //...
//    } else {
//        //...
//    }
//2 申请权限
//
//EasyPermissions.requestPermissions(this, "拍照需要摄像头权限",
//    RC_CAMERA_AND_WIFI, perms);
//3 实现EasyPermissions.PermissionCallbacks接口，直接处理权限是否成功申请
//
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
    }
}
