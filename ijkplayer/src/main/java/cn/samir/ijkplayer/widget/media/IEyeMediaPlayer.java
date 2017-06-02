package cn.samir.ijkplayer.widget.media;

import android.widget.MediaController;

/**
 * Created by xiaw on 2017/4/27 0027.
 */

public interface IEyeMediaPlayer extends MediaController.MediaPlayerControl {


     int getCurrentAspectRatio();
     void setCurrentAspectRatio(int mCurrentAspectRatio);

}
