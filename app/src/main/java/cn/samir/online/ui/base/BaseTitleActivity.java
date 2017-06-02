package cn.samir.online.ui.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import cn.samir.online.R;
import cn.samir.online.ui.fragment.BaseObserverNetActivity;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/10 0010.
 */

public class BaseTitleActivity extends BaseObserverNetActivity {


    @BindView(R.id.tv_left)
    protected CustomFontTextView tvLeft;
    @BindView(R.id.tv_center)
    protected CustomFontTextView tvCenter;
    @BindView(R.id.img_search)
    protected ImageView imgSearch;
    @BindView(R.id.bottom_line)
    protected View bottomLine;
    @BindView(R.id.toolbar)
    protected LinearLayout tooBar;

    protected TitleMode mMode;

    public void setLeftText(String text, View.OnClickListener onClickListener) {
        tvLeft.setText(text);
        tvLeft.setOnClickListener(onClickListener);
    }

    public void setCenterText(String text) {
        tvCenter.setText(text);
    }

    public void setLineVisible(boolean visible) {
        bottomLine.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setImageSearchVisible(boolean visible, int... res) {
        imgSearch.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);

        if (res != null && res.length > 0) {
            imgSearch.setImageResource(res[0]);
        } else {
            imgSearch.setImageResource(R.mipmap.guide_search);
        }

        imgSearch.setOnClickListener(onSearchClick);
    }

    private final View.OnClickListener onSearchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onSearchClick();
        }
    };


    public void onSearchClick() {

    }

    /**
     * @param mode
     */
    public void initHeaderTitle(TitleMode mode, Object... params) {
        mMode = mode;
        if (mode == TitleMode.ONLY_SEARCH) {
            setLineVisible(false);
            setImageSearchVisible(true);
            setCenterText("");
            setLeftText("", null);
        } else if (mode == TitleMode.LEFT_CENTER_SEARCH) {
            setLineVisible(false);
            setImageSearchVisible(true);
            setCenterText(params[0].toString());
            setLeftText(params[1].toString(), (View.OnClickListener) params[2]);
        } else if (mode == TitleMode.LEFT_CENTER_SEARCH_LINE) {
            setLineVisible(true);
            setImageSearchVisible(true);
            setCenterText(params[0].toString());
            setLeftText(params[1].toString(), (View.OnClickListener) params[1]);
        } else if (mode == TitleMode.MORE_SETTINGS) {
            setLineVisible(false);
            setImageSearchVisible(true, R.mipmap.ic_menu_more);
            setCenterText("");
            setLeftText("", null);
        } else {
            setLineVisible(false);
            setImageSearchVisible(false);
            setCenterText("");
            setLeftText("", null);
        }
    }


    protected enum TitleMode {
        ONLY_SEARCH, LEFT_CENTER_SEARCH, LEFT_CENTER_SEARCH_LINE, HIDE, MORE_SETTINGS
    }

}
