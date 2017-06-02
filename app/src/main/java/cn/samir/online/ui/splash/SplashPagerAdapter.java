package cn.samir.online.ui.splash;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.samir.online.R;
import cn.samir.online.views.FlowCustomTextView;

/**
 * Created by Administrator on 2017/3/19.
 */

public class SplashPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private Context context;
    private ViewPager viewPager;

    private String[] textEn;
    private String[] textCn;

    private SparseArray<View> viewCaches = new SparseArray<>();

    public SplashPagerAdapter(Context context, ViewPager viewPager) {
        this.context = context;
        this.viewPager = viewPager;
        textCn = context.getResources().getStringArray(R.array.splash_text_cn);
        textEn = context.getResources().getStringArray(R.array.splash_text_en);
        this.viewPager.addOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return textCn.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_splash_view_pager, container, false);
        container.addView(view);
        viewCaches.append(position, view);
        if (position == 0) {
            onPageSelected(0);
        }
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        View view = viewCaches.get(position);
        FlowCustomTextView tv_cn = (FlowCustomTextView) view.findViewById(R.id.cft_cn);
        tv_cn.setFlowText(textCn[position]);
        FlowCustomTextView tv_en = (FlowCustomTextView) view.findViewById(R.id.cft_en);
        tv_en.setFlowText(textEn[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
