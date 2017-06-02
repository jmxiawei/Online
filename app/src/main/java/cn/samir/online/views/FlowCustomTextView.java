package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/3/19.
 */

public class FlowCustomTextView extends CustomFontTextView {

    private static final String MASK_CHAR = " ";
    private String text;
    private int currentLength = 0;
    private static final int delayMils = 1000;

    public FlowCustomTextView(Context context) {
        super(context);
    }

    public FlowCustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowCustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setFlowText(String text) {
        this.text = text;


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(MASK_CHAR);
        }
        setText(sb.toString());
        currentLength = 0;
        postDelayed(flowRunnable, 100 + delayMils / text.length());
    }


    private Runnable flowRunnable = new Runnable() {
        @Override
        public void run() {
            ++currentLength;
            if (currentLength <= text.length()) {
                String showText = text.substring(0, currentLength);
                StringBuilder sb = new StringBuilder(showText);
                for (int i = currentLength - 1; i < text.length(); i++) {
                    sb.append(MASK_CHAR);
                }
                setText(sb.toString());
                postDelayed(this, delayMils / text.length());
            }
        }
    };
}
