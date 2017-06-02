package cn.samir.online.ui.base;


import android.view.View;

import butterknife.BindView;
import cn.samir.online.R;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.views.ToolBar;

/**
 * Created by xiaw on 2017/5/2 0002.
 */

public class BaseToolBarActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    protected ToolBar toolbar;


    /**
     * @param title
     */
    protected void setToolbar(String title) {
        setToolbar(R.mipmap.ic_action_back, finishClick, title);
    }

    /**
     * @param iconleft
     * @param leftClick
     * @param title
     */
    protected void setToolbar(int iconleft, View.OnClickListener leftClick, String title) {
        setLeftIcon(iconleft, leftClick);
        toolbar.setTitle(title);
    }


    View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 左边按钮
     *
     * @param leftIcon
     * @param onClickListener
     */
    protected void setLeftIcon(int leftIcon, View.OnClickListener onClickListener) {
        toolbar.setNavigationIcon(leftIcon);
        toolbar.setNavigationOnClickListener(onClickListener);
    }


    protected void setRightNavigationIcon(int leftIcon, CharSequence content) {
        toolbar.setRightNavigationIcon(leftIcon, content);
    }

    protected void setRightNavigationOnClickListener(View.OnClickListener l) {
        toolbar.setRightNavigationOnClickListener(l);
    }


}
