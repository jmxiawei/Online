package cn.samir.online.ui.splash;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.Task;
import butterknife.BindView;
import cn.elvin.customlib.IndicatorViewPager;
import cn.elvin.customlib.PullUpFinishLayout;
import cn.samir.ijkplayer.widget.media.IRenderView;
import cn.samir.ijkplayer.widget.media.IjkVideoView;
import cn.samir.online.OnlineApplication;
import cn.samir.online.R;
import cn.samir.online.ui.MainActivity;
import cn.samir.online.ui.base.BaseActivity;
import cn.samir.online.util.Constant;
import cn.samir.online.util.DensityUtil;
import cn.samir.online.util.L;
import cn.samir.online.util.SharePrenenceUtils;
import cn.samir.online.views.video.EyeVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 首次启动播放视频页面
 */
public class SplashVideoActivity extends BaseActivity implements PullUpFinishLayout.OnFinishListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.video_view)
    IjkVideoView videoView;
    @BindView(R.id.indicatorViewPager)
    IndicatorViewPager indicatorViewPager;
    @BindView(R.id.fl_pull_finish)
    PullUpFinishLayout flPullFinish;
    private boolean isFirstLoad = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_ad);

        flPullFinish.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE);

        ViewGroup.LayoutParams lp = flPullFinish.getLayoutParams();
        lp.height = DensityUtil.getDisplayHeight(this);
        lp.width = DensityUtil.getDisplayWidth(this);
        flPullFinish.setLayoutParams(lp);
        isFirstLoad = SharePrenenceUtils.getPrefBoolean(this, Constant.SP_IS_FIRST_LOAD, true);
        if (isFirstLoad) {
            moveToSd();
            indicatorViewPager.setAdapter(new SplashPagerAdapter(this, indicatorViewPager));
            flPullFinish.setOnFinishListener(this);
            indicatorViewPager.addOnPageChangeListener(this);
            SharePrenenceUtils.setPrefBoolean(this, Constant.SP_IS_FIRST_LOAD, false);
        } else {
            //runRobust();
            startActivity(new Intent(this, SplashPictureActivity.class));
            finish();
        }

        // startActivity(new Intent(this, MainActivity.class));
    }


    //move_to_sd_card\landing.mp4
    public void moveToSd() {
        Task.call(new Callable<File>() {
            @Override
            public File call() throws Exception {
                boolean rs = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                if (rs) {
                    boolean prs = requestPermission(REQ_CODE_SD_CARD);
                    if (prs) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/" + Constant.FOLDER_ROOT + "/move_to_sd_card/");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        return new File(file, "landing.mp4");
                    }
                }
                throw new Exception();
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation<File, File>() {
            @Override
            public File then(Task<File> task) throws Exception {
                File file = task.getResult();
                if (file != null) {
                    FileOutputStream fos = null;
                    InputStream in = null;
                    try {
                        AssetManager am = OnlineApplication.getInstance().getAssets();
                        in = am.open("move_to_sd_card/landing.mp4");
                        fos = new FileOutputStream(file);
                        int l = 0;
                        byte[] buffer = new byte[1024 * 16];
                        while ((l = in.read(buffer)) != -1) {
                            fos.write(buffer, 0, l);
                        }
                        fos.flush();
                    } catch (Exception e) {

                    } finally {
                        if (fos != null) {
                            try {
                                fos.close();
                            } catch (Exception e) {
                            }
                        }
                        if (in != null) {
                            try {
                                in.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                return file;
            }
        }, Task.BACKGROUND_EXECUTOR).onSuccess(new Continuation<File, Object>() {
            @Override
            public Object then(Task<File> task) throws Exception {
                EyeVideoView.init();
                videoView.setHudView(null);
                videoView.setVideoPath(task.getResult().getAbsolutePath());
                videoView.setCurrentAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
                videoView.setOnCompletionListener(onComleleteListener);
                videoView.start();
                return null;
            }
        }, Task.UI_THREAD_EXECUTOR);
    }


    IjkMediaPlayer.OnCompletionListener onComleleteListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                iMediaPlayer.start();
            }
        }
    };


    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {

    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        moveToSd();
    }

    @Override
    public void onFinish() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
        finish();
        overridePendingTransition(R.anim.im_dialog_enter, R.anim.im_dialog_exit);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        L.e("onPageScrolled=" + position);

    }

    @Override
    protected void onStop() {
        super.onStop();
        videoView.stopPlayback();
        videoView.release(true);
        videoView.stopBackgroundPlay();
        IjkMediaPlayer.native_profileEnd();
    }


    @Override
    public void onPageSelected(int position) {
        L.e("position=" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
