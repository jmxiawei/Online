package cn.samir.online.views;

import android.view.View;

/**
 * Created by xiawei on 2017/5/2.
 */

public interface IToolBar {

    void setTitle(CharSequence title);

    void setNavigationIcon(int leftIcon);

    void setNavigationOnClickListener(View.OnClickListener l);


    void setRightNavigationIcon(int leftIcon,CharSequence content);

    void setRightNavigationOnClickListener(View.OnClickListener l);

}
