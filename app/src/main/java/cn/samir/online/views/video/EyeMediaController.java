package cn.samir.online.views.video;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import cn.samir.domains.model.Playinfo;
import cn.samir.domains.model.Video;
import cn.samir.ijkplayer.widget.media.IMediaController;
import cn.samir.online.R;
import cn.samir.online.views.CustomFontTextView;
import cn.samir.online.views.DefinitionPopWindow;

/**
 * Created by xiaw on 2017/4/21 0021.
 */

public class EyeMediaController extends FrameLayout implements IMediaController {


    private MediaController.MediaPlayerControl mPlayer;
    private final Context mContext;
    private View mAnchor;
    private View mRoot;
    private ProgressBar mProgress;

    private TextView mTvDescription;
    //private TextView mEndTime, mCurrentTime;
    private boolean mShowing;
    private boolean mDragging;
    private static final int sDefaultTimeout = 2000;
    private final boolean mUseFastForward;
    private boolean mFromXml;
    private boolean mListenersSet;
    private OnClickListener mNextListener,
            mPrevListener,
            mActionCloseListener,
            mFullScreenListener;

    private ISwitchDefinitionListener mISwitchDefinitionListener;

    StringBuilder mFormatBuilder;
    Formatter mFormatter;


    private Video mVideo;

    private ImageButton actionClose;
    private CustomFontTextView videoTitle;
    private ImageButton btnFav;
    private ImageButton btnShare;
    private CustomFontTextView btnDefinition;//清晰度
    private ProgressBar bottomProgressBar;
    private CustomFontTextView tvDescription;
    private ImageButton btnNext;
    private ImageButton btnFullscreen;


    private ProgressBar mLoadingProgressBar;
    private ImageButton mPauseButton;
    private ImageButton mFfwdButton;
    private ImageButton mRewButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private CharSequence mPlayDescription;
    private CharSequence mPauseDescription;
    //private final AccessibilityManager mAccessibilityManager;

    //  private AlphaAnimation fadeIn;

    //private AlphaAnimation fadeOut;


    private int mCurrentScreenOrientation;

    private View mBannerView;
    private DefinitionPopWindow definitionPopWindow;

    private ArrayList<ITouchHandler> mTouchHandlers = new ArrayList<>();

    /**
     *
     */
    private SparseArray<View> allFrames = new SparseArray<>();


    public EyeMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRoot = this;
        mContext = context;
        mUseFastForward = true;
        mFromXml = true;
        setOnTouchListener(mTouchListener);
        addTouchHandler(new VolumeHandler(context));

    }

    public void setCurrentScreenOrientation(int mCurrentScreenOrientation) {
        this.mCurrentScreenOrientation = mCurrentScreenOrientation;

        setTitle(this.mCurrentScreenOrientation == Configuration.ORIENTATION_LANDSCAPE);

        if (btnFullscreen != null) {
            btnFullscreen.setImageResource(this.mCurrentScreenOrientation == Configuration.ORIENTATION_LANDSCAPE ?
                    R.mipmap.ic_cancel_full_srceen : R.mipmap.ic_enter_full_screen);
        }
    }


    public void addTouchHandler(ITouchHandler iTouchHandler) {
        mTouchHandlers.add(iTouchHandler);
    }

    public void setTitle(boolean isshow) {
        if (videoTitle != null) {
            videoTitle.setText(isshow ? mVideo.getTitle() : "");
        }
    }


    public void addFrame(int key, View view) {
        allFrames.put(key, view);

        if (mRoot != null) {
            LayoutParams frameParams = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            addView(view, frameParams);
        }
    }


    public void showFrame(int key) {

    }

    public void setISwitchDefinitionListener(ISwitchDefinitionListener mISwitchDefinitionListener) {
        this.mISwitchDefinitionListener = mISwitchDefinitionListener;
    }

    /**
     * 切换清晰度
     */
    private final AdapterView.OnItemClickListener onDefinitionItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Playinfo playinfo = (Playinfo) parent.getAdapter().getItem(position);

            if (definitionPopWindow.isShowing()) {
                definitionPopWindow.dismiss();
            }

            if (mISwitchDefinitionListener != null) {
                mISwitchDefinitionListener.switchTo(playinfo.getUrl());
            }

            hide();
        }
    };


    /**
     * 清晰度监听
     */
    private final View.OnClickListener onDefinitionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (definitionPopWindow == null) {
                definitionPopWindow = new DefinitionPopWindow(getContext());
            }
            if (definitionPopWindow.isShowing()) {
                definitionPopWindow.dismiss();
                hide();
            } else {
                definitionPopWindow.setOnItemClickListener(onDefinitionItemClickListener);
                definitionPopWindow.setAnchorView(v);
                definitionPopWindow.setData(mVideo);
                definitionPopWindow.show(v);
            }
        }
    };

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        if (mRoot != null)
            initControllerView(mRoot);
    }

    public EyeMediaController(Context context, boolean useFastForward) {
        super(context);
        mContext = context;
        mUseFastForward = useFastForward;

        addTouchHandler(new VolumeHandler(context));
    }

    public EyeMediaController(Context context) {
        this(context, true);
    }

    // This is called whenever mAnchor's layout bound changes
    private final OnLayoutChangeListener mLayoutChangeListener =
            new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right,
                                           int bottom, int oldLeft, int oldTop, int oldRight,
                                           int oldBottom) {

//                    if (mShowing) {
//                        mWindowManager.updateViewLayout(mDecor, mDecorLayoutParams);
//                    }
                }
            };

    private final OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (mShowing) {
                    hide();
                }
            }
            return false;
        }
    };

    public void setMediaPlayer(MediaController.MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();

    }

    /**
     * Set the view that acts as the anchor for the control view.
     * This can for example be a VideoView, or your Activity's main view.
     * When VideoView calls this method, it will use the VideoView's parent
     * as the anchor.
     *
     * @param view The view to which to anchor the controller when it is visible.
     */
    public void setAnchorView(View view) {
        if (mAnchor != null) {
            mAnchor.removeOnLayoutChangeListener(mLayoutChangeListener);
        }
        mAnchor = view;
        if (mAnchor != null) {
            mAnchor.addOnLayoutChangeListener(mLayoutChangeListener);
        }

        LayoutParams frameParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        removeAllViews();
        View v = makeControllerView();
        addView(v, frameParams);
    }

    /**
     * Create the view that holds the widgets that control playback.
     * Derived classes can override this to create their own.
     *
     * @return The controller view.
     * @hide This doesn't work as advertised
     */
    protected View makeControllerView() {
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRoot = inflate.inflate(R.layout.eye_media_controller, this, false);
        initControllerView(mRoot);
        return mRoot;
    }


    private OnClickListener mFavListener = new OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    private OnClickListener mShareListener = new OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    private void initControllerView(View v) {


        btnDefinition = (CustomFontTextView) v.findViewById(R.id.btn_definition);
        updateDefinitionListener();
        mLoadingProgressBar = (ProgressBar) v.findViewById(R.id.bottom_progressBar);


        actionClose = (ImageButton) v.findViewById(R.id.action_close);
        if (actionClose != null) {
            actionClose.setOnClickListener(this.mActionCloseListener);
        }

        btnFav = (ImageButton) v.findViewById(R.id.btn_fav);
        if (btnFav != null) {
            btnFav.setOnClickListener(mFavListener);
        }

        btnShare = (ImageButton) v.findViewById(R.id.btn_share);
        if (btnShare != null) {
            btnShare.setOnClickListener(mShareListener);
        }

        btnFullscreen = (ImageButton) v.findViewById(R.id.btn_fullscreen);
        if (btnFullscreen != null) {
            btnFullscreen.setOnClickListener(mFullScreenListener);
        }

        videoTitle = (CustomFontTextView) v.findViewById(R.id.video_title);

        Resources res = mContext.getResources();
        mPlayDescription = "";
        //   res.getText(com.android.internal.R.string.lockscreen_transport_play_description);
        mPauseDescription = "";
        //res.getText(com.android.internal.R.string.lockscreen_transport_pause_description);
        mPauseButton = (ImageButton) v.findViewById(R.id.pause);
        if (mPauseButton != null) {
            mPauseButton.requestFocus();
            mPauseButton.setOnClickListener(mPauseListener);
        }

//        mFfwdButton = (ImageButton) v.findViewById(R.id.ffwd);
//        if (mFfwdButton != null) {
//            mFfwdButton.setOnClickListener(mFfwdListener);
//            if (!mFromXml) {
//                mFfwdButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
//            }
//        }
//
//        mRewButton = (ImageButton) v.findViewById(R.id.rew);
//        if (mRewButton != null) {
//            mRewButton.setOnClickListener(mRewListener);
//            if (!mFromXml) {
//                mRewButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
//            }
//        }
//
        // By default these are hidden. They will be enabled when setPrevNextListeners() is called
        mNextButton = (ImageButton) v.findViewById(R.id.next);
        if (mNextButton != null && !mFromXml && !mListenersSet) {
            mNextButton.setVisibility(View.GONE);
        }
//        mPrevButton = (ImageButton) v.findViewById(R.id.prev);
//        if (mPrevButton != null && !mFromXml && !mListenersSet) {
//            mPrevButton.setVisibility(View.GONE);
//        }

        mProgress = (SeekBar) v.findViewById(R.id.mediacontroller_progress);
        if (mProgress != null) {
            if (mProgress instanceof SeekBar) {
                SeekBar seeker = (SeekBar) mProgress;
                seeker.setOnSeekBarChangeListener(mSeekListener);
            }
            mProgress.setMax(1000);
        }
        mTvDescription = (TextView) v.findViewById(R.id.tv_description);
        //mEndTime = (TextView) v.findViewById(R.id.time);
        //mCurrentTime = (TextView) v.findViewById(R.id.time_current);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        installPrevNextListeners();
    }

    private void updateDefinitionListener() {
        if (btnDefinition != null) {
            btnDefinition.setOnClickListener(this.onDefinitionListener);
            if (getVideo() != null) {
                ArrayList<Playinfo> playinfos = (ArrayList<Playinfo>) getVideo().getPlayInfo();
                if (playinfos != null && playinfos.size() > 0) {
                    Playinfo playinfo = playinfos.get(playinfos.size() - 1);
                    btnDefinition.setText(playinfo.getName());
                }
            }
        }
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 3 seconds of inactivity.
     */
    public void show() {
        show(sDefaultTimeout);
    }

    @Override
    public void showOnce(View view) {

    }

    @Override
    public void onPrepare() {

        show(sDefaultTimeout);
        if (mLoadingProgressBar != null) {
            mLoadingProgressBar.setVisibility(INVISIBLE);
        }

        updatePausePlay();

    }

    public OnClickListener getFullScreenListener() {
        return mFullScreenListener;
    }

    public void setFullScreenListener(OnClickListener mFullScreenListener) {
        this.mFullScreenListener = mFullScreenListener;
        if (btnFullscreen != null) {
            btnFullscreen.setOnClickListener(this.mFullScreenListener);
        }
    }

    /**
     * Disable pause or seek buttons if the stream cannot be paused or seeked.
     * This requires the control interface to be a MediaPlayerControlExt
     */
    private void disableUnsupportedButtons() {
        try {
            if (mPauseButton != null && !mPlayer.canPause()) {
                mPauseButton.setEnabled(false);
            }
            if (mRewButton != null && !mPlayer.canSeekBackward()) {
                mRewButton.setEnabled(false);
            }
            if (mFfwdButton != null && !mPlayer.canSeekForward()) {
                mFfwdButton.setEnabled(false);
            }
            // TODO What we really should do is add a canSeek to the MediaPlayerControl interface;
            // this scheme can break the case when applications want to allow seek through the
            // progress bar but disable forward/backward buttons.
            //
            // However, currently the flags SEEK_BACKWARD_AVAILABLE, SEEK_FORWARD_AVAILABLE,
            // and SEEK_AVAILABLE are all (un)set together; as such the aforementioned issue
            // shouldn't arise in existing applications.
            if (mProgress != null && !mPlayer.canSeekBackward() && !mPlayer.canSeekForward()) {
                mProgress.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError ex) {
            // We were given an old version of the interface, that doesn't have
            // the canPause/canSeekXYZ methods. This is OK, it just means we
            // assume the media can be paused and seeked, and so we don't disable
            // the buttons.
        }
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 'timeout' milliseconds of inactivity.
     *
     * @param timeout The timeout in milliseconds. Use 0 to show
     *                the controller until hide() is called.
     */
    public void show(int timeout) {
        if (!mShowing) {
            setProgress();
            if (mPauseButton != null) {
                mPauseButton.requestFocus();
            }
            disableUnsupportedButtons();
            setVisibility(VISIBLE);
            mShowing = true;
        }
        updatePausePlay();

        // cause the progress bar to be updated even if mShowing
        // was already true.  This happens, for example, if we're
        // paused with the progress bar showing the user hits play.
        post(mShowProgress);

        if (timeout != 0) {
            removeCallbacks(mFadeOut);
            postDelayed(mFadeOut, timeout);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    /**
     * Remove the controller from the screen.
     */
    public void hide() {

        if (mShowing) {
            try {
                removeCallbacks(mShowProgress);
                setVisibility(INVISIBLE);

            } catch (IllegalArgumentException ex) {
                Log.w("MediaController", "already removed");
            }
            mShowing = false;
        }


        if (definitionPopWindow != null) {
            definitionPopWindow.dismiss();
        }

    }


    private final Runnable mFadeOut = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private final Runnable mShowProgress = new Runnable() {
        @Override
        public void run() {
            int pos = setProgress();
            if (!mDragging && mShowing && mPlayer.isPlaying()) {
                postDelayed(mShowProgress, 1000 - (pos % 1000));
            }
        }
    };

    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    private int setProgress() {
        if (mPlayer == null || mDragging) {
            return 0;
        }
        int position = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        if (mProgress != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * position / duration;
                mProgress.setProgress((int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mProgress.setSecondaryProgress(percent * 10);
        }

//        if (mEndTime != null)
//            mEndTime.setText(stringForTime(duration));
//        if (mCurrentTime != null)
//            mCurrentTime.setText(stringForTime(position));

        mTvDescription.setText(stringForTime(position) + "/" + stringForTime(duration));

        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                show(0); // show until hide is called
                break;
            case MotionEvent.ACTION_UP:
                show(sDefaultTimeout); // start timeout
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            case MotionEvent.ACTION_MOVE:
                show(sDefaultTimeout); // start timeout
                break;
            default:
                break;
        }
        handleTouch(event);
        return true;
    }

    private void handleTouch(MotionEvent event) {

        for (ITouchHandler t :
                mTouchHandlers) {
            if (t != null) {
                t.handleTouch(mCurrentScreenOrientation, event);
            }
        }
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_SPACE) {
            if (uniqueDown) {
                doPauseResume();
                show(sDefaultTimeout);
                if (mPauseButton != null) {
                    mPauseButton.requestFocus();
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
                || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // don't show the controls for volume adjustment
            return super.dispatchKeyEvent(event);
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            if (uniqueDown) {
                hide();
            }
            return true;
        }

        show(sDefaultTimeout);
        return super.dispatchKeyEvent(event);
    }

    private Animation.AnimationListener fadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            setVisibility(VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    private Animation.AnimationListener fadeOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setVisibility(INVISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    private final OnClickListener mPauseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            doPauseResume();
            show(sDefaultTimeout);
        }
    };

    private void updatePausePlay() {
        if (mRoot == null || mPauseButton == null)
            return;

        int percent = mPlayer.getBufferPercentage();

        if (percent == 0) {

            if (mPauseButton.getVisibility() != INVISIBLE) {
                mPauseButton.setVisibility(INVISIBLE);
            }

            if (mBannerView != null && mBannerView.getVisibility() != INVISIBLE) {
                mBannerView.setVisibility(INVISIBLE);
            }

        } else {

            if (mLoadingProgressBar != null) {
                mLoadingProgressBar.setVisibility(INVISIBLE);
            }

            if (mPauseButton.getVisibility() != VISIBLE) {
                mPauseButton.setVisibility(VISIBLE);
            }

            if (mPlayer.isPlaying()) {
                mPauseButton.setImageResource(R.mipmap.ic_player_pause);
                mPauseButton.setContentDescription(mPauseDescription);
            } else {
                mPauseButton.setImageResource(R.mipmap.ic_player_play);
                mPauseButton.setContentDescription(mPlayDescription);
            }

            if (mBannerView != null && mBannerView.getVisibility() == VISIBLE) {
                mBannerView.setVisibility(INVISIBLE);
            }
        }
    }

    public void setBannerView(View mBannerView) {
        this.mBannerView = mBannerView;
    }

    private void doPauseResume() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        updatePausePlay();
    }

    // There are two scenarios that can trigger the seekbar listener to trigger:
    //
    // The first is the user using the touchpad to adjust the posititon of the
    // seekbar's thumb. In this case onStartTrackingTouch is called followed by
    // a number of onProgressChanged notifications, concluded by onStopTrackingTouch.
    // We're setting the field "mDragging" to true for the duration of the dragging
    // session to avoid jumps in the position in case of ongoing playback.
    //
    // The second scenario involves the user operating the scroll ball, in this
    // case there WON'T BE onStartTrackingTouch/onStopTrackingTouch notifications,
    // we will simply apply the updated position without suspending regular updates.
    private final SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStartTrackingTouch(SeekBar bar) {
            show(3600000);

            mDragging = true;

            // By removing these pending progress messages we make sure
            // that a) we won't update the progress while the user adjusts
            // the seekbar and b) once the user is done dragging the thumb
            // we will post one of these messages to the queue again and
            // this ensures that there will be exactly one message queued up.
            removeCallbacks(mShowProgress);
        }

        @Override
        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
            if (!fromuser) {
                // We're not interested in programmatically generated changes to
                // the progress bar's position.
                return;
            }

            long duration = mPlayer.getDuration();
            long newposition = (duration * progress) / 1000L;
            mPlayer.seekTo((int) newposition);
//            if (mCurrentTime != null)
//                mCurrentTime.setText(stringForTime((int) newposition));
        }

        @Override
        public void onStopTrackingTouch(SeekBar bar) {
            mDragging = false;
            setProgress();
            updatePausePlay();
            show(sDefaultTimeout);

            // Ensure that progress is properly updated in the future,
            // the call to show() does not guarantee this because it is a
            // no-op if we are already showing.
            post(mShowProgress);
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if (mPauseButton != null) {
            mPauseButton.setEnabled(enabled);
        }
        if (mFfwdButton != null) {
            mFfwdButton.setEnabled(enabled);
        }
        if (mRewButton != null) {
            mRewButton.setEnabled(enabled);
        }
        if (mNextButton != null) {
            mNextButton.setEnabled(enabled && mNextListener != null);
        }
        if (mPrevButton != null) {
            mPrevButton.setEnabled(enabled && mPrevListener != null);
        }
        if (mProgress != null) {
            mProgress.setEnabled(enabled);
        }
        disableUnsupportedButtons();
        super.setEnabled(enabled);
    }


    @Override
    public CharSequence getAccessibilityClassName() {
        return MediaController.class.getName();
    }

    private final OnClickListener mRewListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = mPlayer.getCurrentPosition();
            pos -= 5000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };

    private final OnClickListener mFfwdListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = mPlayer.getCurrentPosition();
            pos += 15000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };

    private void installPrevNextListeners() {
        if (mNextButton != null) {
            mNextButton.setOnClickListener(mNextListener);
            mNextButton.setEnabled(mNextListener != null);
            mNextButton.setVisibility(mNextListener == null ? INVISIBLE : VISIBLE);
        }

        if (mPrevButton != null) {
            mPrevButton.setOnClickListener(mPrevListener);
            mPrevButton.setEnabled(mPrevListener != null);
            mPrevButton.setVisibility(mPrevListener == null ? INVISIBLE : VISIBLE);
        }
    }

    public void setPrevNextListeners(OnClickListener next, OnClickListener prev) {
        mNextListener = next;
        mPrevListener = prev;
        mListenersSet = true;

        if (mRoot != null) {
            installPrevNextListeners();
        }
    }


    public Video getVideo() {
        return mVideo;
    }

    public void setVideo(Video mVideo) {
        this.mVideo = mVideo;
        updateDefinitionListener();
    }


    public void setActionCloseListener(OnClickListener mActionCloseListener) {
        this.mActionCloseListener = mActionCloseListener;
        if (actionClose != null) {
            actionClose.setOnClickListener(this.mActionCloseListener);
        }
    }


}
