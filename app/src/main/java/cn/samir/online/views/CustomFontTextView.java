package cn.samir.online.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import cn.samir.online.R;

/**
 * Created by xiawei on 2017/3/8.
 */

public class CustomFontTextView extends android.support.v7.widget.AppCompatTextView {

    private int font_type = 0;

    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setFont(String font) {
        Typeface typeface = TypeFaceManager.getInstance().getTypeFace(font);
        if (typeface != null) {
            setTypeface(typeface);
        }
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
        if (a != null) {
            font_type = a.getInt(R.styleable.CustomFontTextView_font_type, 0);
        }
        Typeface typeface = TypeFaceManager.getInstance().getTypeFace(font_type);
        if (typeface != null) {
            setTypeface(typeface);
        }
        if (a != null) {
            a.recycle();
        }
    }
}
