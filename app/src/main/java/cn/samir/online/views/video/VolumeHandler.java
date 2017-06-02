package cn.samir.online.views.video;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.LogUtil;

/**
 * Created by xiaw on 2017/5/26 0026.
 */

public class VolumeHandler implements ITouchHandler {


    private float mInitDownX;
    private float mInitDownY;

    private float mLastDownX;
    private float mLastDownY;


    private int mTouchSlop;

    private Context context;

    private boolean startVolume = false;

    private int mScreenWidth;
    private int mScreenHeight;

    private AudioManager mAudioManager;


    private int mMaxVolume;
    private int mMinVolume = 0;


    private long mLastUpdateTime;

    public VolumeHandler(Context context) {
        this.context = context;
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScreenWidth = DensityUtil.getDisplayWidth(context);
        mScreenHeight = DensityUtil.getDisplayHeight(context);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        LogUtil.e("mMaxVolume=" + mMaxVolume + ",current = " + mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }


    public void updateVolume(int delta) {

        /**
         * FLAG_PLAY_SOUND 调整音量时播放声音
         FLAG_SHOW_UI 调整时显示音量条,就是按音量键出现的界面
         */
        if (System.currentTimeMillis() - mLastUpdateTime < 40) {
            return;
        }
        // int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (delta > 0) {
            //mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, ajustVolume(current, delta), AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
            mAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
        } else if (delta == 0) {
            mAudioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
        } else {
            mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
        }
        mLastUpdateTime = System.currentTimeMillis();

    }


    public int ajustVolume(int current, int delta) {

        int rs = current + getDelta(delta);
        if (rs > mMaxVolume) {
            rs = mMaxVolume;
        } else if (rs < mMinVolume) {
            rs = mMinVolume;
        }


        LogUtil.e(String.format(" ajustVolume current=%d , delta =%d ,rs = %d ", current, delta, rs));
        return rs;


    }

    public int getDelta(int delta) {
        return (int) (delta * 1.0f / mScreenHeight * 1.0f * mMaxVolume);
    }

    /**
     * 处理触摸事件，在左半边的话，则音量控制
     *
     * @param orientation
     * @param e
     */
    @Override
    public void handleTouch(int orientation, MotionEvent e) {

        //竖屏不能控制音量
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mInitDownX = mLastDownX = e.getX();
                    mInitDownY = mLastDownY = e.getY();

                    if (mInitDownX < mScreenWidth / 2) {
                        updateVolume(0);
                        startVolume = true;
                    } else {
                        startVolume = false;
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (startVolume) {
                        float currentY = e.getY();
                        int delta = (int) (currentY - mLastDownY);
                        updateVolume(-delta);
                        mLastDownY = currentY;
                    }
                    break;

                default:
                    startVolume = false;
                    break;
            }
        }
    }


}
