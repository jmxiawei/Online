package cn.samir.online.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.online.R;
import cn.samir.online.interfaces.Markable;

/**
 * Created by xiawei on 2017/3/9.
 */

public class NavigationMenuView extends FrameLayout implements Markable {

    BottomNavigationView.MenuItem menuItem;

    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.tv_text)
    CustomFontTextView tvText;
    @BindView(R.id.tv_mark)
    CustomFontTextView tvMark;

    public NavigationMenuView(Context context) {
        this(context, null);
    }

    public NavigationMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_navifation_view, this, true);
        ButterKnife.bind(this);
    }

    public BottomNavigationView.MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(BottomNavigationView.MenuItem menuItem) {
        this.menuItem = menuItem;
        tvText.setText(menuItem.text);
        if (!menuItem.isSelect()) {
            imgMenu.setImageResource(menuItem.getNormalResId());
            tvText.setTextColor(getResources().getColor(R.color.text_color_gray));
        } else {
            imgMenu.setImageResource(menuItem.getSelectedResId());
            tvText.setTextColor(getResources().getColor(R.color.text_color_black));
        }

        if (menuItem.isHasMark()) {
            tvMark.setVisibility(VISIBLE);
        } else {
            tvMark.setVisibility(INVISIBLE);
        }

    }

    @Override
    public void onMark(String key, int count) {

    }
}
