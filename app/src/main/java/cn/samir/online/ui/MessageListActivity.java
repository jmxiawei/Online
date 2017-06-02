package cn.samir.online.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.samir.online.R;
import cn.samir.online.ui.base.ToolBarListActivity;
import cn.samir.online.ui.fragment.MessageListFragment;

public class MessageListActivity extends ToolBarListActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar("我的消息");
        MessageListFragment recyclerFragment = new MessageListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, recyclerFragment).show(recyclerFragment).commit();
    }

    @Override
    protected void initData() {
        // super.initData();
    }

    @Override
    protected void setUrl(String url) {

    }

}
