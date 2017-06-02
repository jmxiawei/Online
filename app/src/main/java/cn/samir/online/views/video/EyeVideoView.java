package cn.samir.online.views.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

import cn.samir.ijkplayer.widget.media.IjkVideoView;
import cn.samir.online.util.Constant;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by xiaw on 2017/4/19 0019.
 */

public class EyeVideoView extends IjkVideoView {

    MediaController c;

    public EyeVideoView(Context context) {
        this(context, null);
    }

    public EyeVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EyeVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public static void init() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin(Constant.IJK_LIB_NAME);
    }

}
