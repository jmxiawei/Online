package cn.samir.online.views.video;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.samir.online.R;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/26 0026.
 */

public class VideoControllerPlayButton extends LinearLayout {


    @BindView(R.id.pause)
    ImageButton pause;
    @BindView(R.id.bottom_progressBar)
    ProgressBar bottomProgressBar;
    @BindView(R.id.tv_description)
    CustomFontTextView tvDescription;

    public VideoControllerPlayButton(Context context) {
        this(context, null);
    }

    public VideoControllerPlayButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoControllerPlayButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_video_controller_botton, this, true);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.pause)
    public void onClick() {


    }
}
