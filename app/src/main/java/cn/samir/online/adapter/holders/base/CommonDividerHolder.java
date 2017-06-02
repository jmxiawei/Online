package cn.samir.online.adapter.holders.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.samir.online.R;
import cn.samir.online.http.converts.TypeProvider;
import cn.samir.online.util.LogUtil;

/**
 * 处理分割线
 * Created by xiaw on 2017/5/17 0017.
 */

public abstract class CommonDividerHolder<T> extends CommonViewHolder<T> {


    private LinearLayout headerDivider;


    static final boolean DEBUG = false;

    public CommonDividerHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
    }

    @Override
    public void setNextPreType(String pre, String me, Object... params) {

        if (headerDivider == null) {
            headerDivider = (LinearLayout) itemView.findViewById(R.id.header_divider);
        }


        if (pre == null) {
            hideHeaderDivider();
        } else {
            /**
             * 上面是集合，自己是集合,显示自己的上分割线,否则隐藏
             */

            hideHeaderDivider();
            if (TypeProvider.getInstance().isHeaderCollectionHolder(me)) {
                if (TypeProvider.getInstance().isHeaderCollectionHolder(pre)) {
                    if (hasHeaderDivider()) {
                        showHeaderDivider();

                    }
                }
            }
        }

        if (DEBUG) {
            StringBuilder info = new StringBuilder();

            if (params != null) {
                for (Object p :
                        params) {
                    info.append(p.toString() + "---");
                }
            }
            info.append("pre=" + pre + ",me=" + me);
            LogUtil.e(info.toString());
        }


    }

    private void hideHeaderDivider() {
        if (headerDivider != null) {
            headerDivider.setVisibility(View.GONE);
        }
    }

    private void showHeaderDivider() {
        if (headerDivider != null) {
            headerDivider.setVisibility(View.VISIBLE);
        }
    }

    private boolean hasHeaderDivider() {
        return headerDivider != null;
    }

}
