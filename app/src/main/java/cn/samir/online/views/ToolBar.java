package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;

/**
 * Created by xiawei on 2017/5/2.
 */

public class ToolBar extends LinearLayout implements IToolBar {


    @BindView(R.id.btn_left)
    ImageButton btnLeft;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.btn_right)
    CustomFontTextView btnRight;

    public ToolBar(Context context) {
        this(context, null);
    }

    public ToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true);
        ButterKnife.bind(this, this);
    }


    @Override
    public void setTitle(CharSequence title) {
        tvTitle.setText(title);
    }

    @Override
    public void setNavigationIcon(int leftIcon) {
        btnLeft.setImageResource(leftIcon);
    }

    @Override
    public void setNavigationOnClickListener(OnClickListener l) {
        btnLeft.setOnClickListener(l);
    }

    @Override
    public void setRightNavigationIcon(int leftIcon, CharSequence content) {
        btnRight.setText(content);
    }

    @Override
    public void setRightNavigationOnClickListener(OnClickListener l) {
        btnRight.setOnClickListener(l);
    }



}
