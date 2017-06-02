package cn.samir.online.ui.video;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.elvin.customlib.L;
import cn.samir.domains.model.Video;
import cn.samir.ijkplayer.widget.media.IRenderView;
import cn.samir.online.R;
import cn.samir.online.events.VideoEvent;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.util.Constant;
import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.LogUtil;
import cn.samir.online.views.video.EyeMediaController;
import cn.samir.online.views.video.EyeVideoView;
import cn.samir.online.views.video.ISwitchDefinitionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * 视频详情界面
 */
public class VideoDetailActivity extends BaseActivity {
    private static final String TYPE_NAME = "TYPE_NAME";
    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_VIDEO_EVENT = 1;
    @BindView(R.id.video_view)
    EyeVideoView mVideoView;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.fl_video)
    FrameLayout flVideo;
    @BindView(R.id.sdv_banner)
    SimpleDraweeView sdvBanner;
    @BindView(R.id.media_controller)
    EyeMediaController mediaController;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    private RelativeVideoFragment relativeVideoFragment;

    private boolean mBackPressed = false;
    private Video mVideo;

    private ArrayList<Video> nextVideos;
    private int currentIndex = 0;


    private int mPlayType = TYPE_VIDEO;
    private int mScreenWidth;
    private int screenHeight;
    private int mCurrentAspectRatio = IRenderView.AR_16_9_FIT_PARENT;
    private int mCurrentScreenOrientation = Configuration.ORIENTATION_PORTRAIT;


    public static Intent newIntent(Context context, Video video) {
        Intent to = new Intent(context, VideoDetailActivity.class);
        to.putExtra(Video.NAME, video);
        to.putExtra(TYPE_NAME, TYPE_VIDEO);
        return to;
    }

    public static Intent newIntent(Context context, VideoEvent event) {
        Intent to = new Intent(context, VideoDetailActivity.class);
        to.putExtra(VideoEvent.NAME, event);
        to.putExtra(TYPE_NAME, TYPE_VIDEO_EVENT);
        return to;
    }

    public static Intent newIntent(Context context, Video video, ArrayList<Video> videos, int currentIndex) {
        return newIntent(context, new VideoEvent(videos, currentIndex, video));
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {


        mPlayType = intent.getIntExtra(TYPE_NAME, TYPE_VIDEO);

        if (mPlayType == TYPE_VIDEO) {
            mVideo = intent.getParcelableExtra(Video.NAME);
            nextVideos = null;
            currentIndex = 0;
        } else {
            VideoEvent videoEvent = intent.getParcelableExtra(VideoEvent.NAME);
            mVideo = videoEvent.getmVideo();
            nextVideos = videoEvent.getNextVideos();
            currentIndex = videoEvent.getCurrentIndex();
            if (nextVideos != null && nextVideos.size() > currentIndex) {
                mVideo = nextVideos.get(currentIndex);
            }
        }

        sdvBanner.setImageURI(Uri.parse(mVideo.getCover().getDetail()));


        // init player
        EyeVideoView.init();
        mVideoView.setMediaController(mediaController);
        mediaController.setBannerView(sdvBanner);
        mediaController.setActionCloseListener(closeListener);
        mediaController.setFullScreenListener(fullScreen);
        mediaController.setVideo(this.mVideo);
        mediaController.setISwitchDefinitionListener(switchDefinitionListener);
        //mediaController.addFrame(1, new BrightnessView(getBaseContext()));
        setNextPreListeners();
        // prefer mVideoPath
        if (mVideo.getPlayUrl() != null || mVideo.getDownloadUri() != null) {
            if (mVideo.getDownloadUri() != null) {
                LogUtil.e(" play video =" + mVideo.getDownloadUri());
                mVideoView.setVideoURI(mVideo.getDownloadUri());
            } else {
                LogUtil.e("play video =" + mVideo.getPlayUrl());
                mVideoView.setVideoPath(mVideo.getPlayUrl());
            }
            mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                    if (iMediaPlayer != null) {
                        iMediaPlayer.stop();
                    }
                    return false;
                }
            });
            mVideoView.start();
        } else {
            L.e(" no play url");
            finish();
        }
        //mVideoView.start();
        relativeVideoFragment = RelativeVideoFragment.newInstance(mVideo);
        getSupportFragmentManager().beginTransaction().add(R.id.container, relativeVideoFragment).show(relativeVideoFragment).commit();
    }


    private final ISwitchDefinitionListener switchDefinitionListener = new ISwitchDefinitionListener() {
        @Override
        public void switchTo(String url) {
            mVideoView.setVideoPath(url);
        }
    };


    /**
     * 设置
     */
    private void setNextPreListeners() {
        if (nextVideos == null || nextVideos.size() == 0) {
            mediaController.setPrevNextListeners(null, null);
        } else {
            if (currentIndex == 0) {
                mediaController.setPrevNextListeners(next, null);
            } else if (currentIndex == nextVideos.size() - 1) {
                mediaController.setPrevNextListeners(null, prev);
            } else {
                mediaController.setPrevNextListeners(next, prev);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        onConfigurationChanged(Configuration.ORIENTATION_PORTRAIT);
        handleIntent(getIntent());
    }

    void onConfigurationChanged(int orientation) {
        initScreenSize();
        mCurrentScreenOrientation = orientation;
        mediaController.setCurrentScreenOrientation(mCurrentScreenOrientation);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {//切换为横屏
            ViewGroup.LayoutParams lp = flVideo.getLayoutParams();
            lp.height = screenHeight;
            lp.width = mScreenWidth;
            flVideo.setLayoutParams(lp);
            mCurrentAspectRatio = IRenderView.AR_ASPECT_FILL_PARENT;
            mVideoView.setCurrentAspectRatio(mCurrentAspectRatio);
        } else {
            ViewGroup.LayoutParams lp = flVideo.getLayoutParams();
            lp.height = mScreenWidth * 9 / 16;
            lp.width = mScreenWidth;
            flVideo.setLayoutParams(lp);
            mCurrentAspectRatio = IRenderView.AR_16_9_FIT_PARENT;
            mVideoView.setCurrentAspectRatio(mCurrentAspectRatio);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.e("newConfig" + newConfig.orientation);
        onConfigurationChanged(newConfig.orientation);
    }


    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initScreenSize() {
        screenHeight = DensityUtil.getDisplayHeight(this);
        mScreenWidth = DensityUtil.getDisplayWidth(this);
    }

    private final View.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private final View.OnClickListener fullScreen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //横屏竖屏切换
            if (mCurrentScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                //横屏切换到竖屏
                llContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                //onConfigurationChanged(Configuration.ORIENTATION_PORTRAIT);
            } else {
                // 竖屏切换到横屏
                llContainer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                // onConfigurationChanged(Configuration.ORIENTATION_LANDSCAPE);
            }
        }
    };

    /**
     * 下一个
     */
    private final View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (currentIndex < nextVideos.size() - 1) {
                currentIndex++;
                startActivity(newIntent(getBaseContext(), nextVideos.get(currentIndex), nextVideos, currentIndex));
            }
        }
    };

    private final View.OnClickListener prev = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentIndex > 0) {
                currentIndex--;
                startActivity(newIntent(getBaseContext(), nextVideos.get(currentIndex), nextVideos, currentIndex));
            }
        }
    };

}
