package cn.samir.online.events;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import cn.samir.domains.model.Video;

/**
 * Created by xiaw on 2017/5/16 0016.
 */

public class VideoEvent implements Event {


    public static final String NAME = "cn.samir.domains.VideoEvent";

    private ArrayList<Video> nextVideos;
    private int currentIndex;
    private Video mVideo;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(nextVideos);
        dest.writeInt(this.currentIndex);
        dest.writeParcelable(this.mVideo, 0);
    }

    public ArrayList<Video> getNextVideos() {
        return nextVideos;
    }

    public void setNextVideos(ArrayList<Video> nextVideos) {
        this.nextVideos = nextVideos;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Video getmVideo() {
        return mVideo;
    }

    public void setmVideo(Video mVideo) {
        this.mVideo = mVideo;
    }

    public VideoEvent(ArrayList<Video> nextVideos, int currentIndex, Video mVideo) {
        this.nextVideos = nextVideos;
        this.currentIndex = currentIndex;
        this.mVideo = mVideo;
    }

    public VideoEvent() {
    }

    protected VideoEvent(Parcel in) {
        this.nextVideos = in.createTypedArrayList(Video.CREATOR);
        this.currentIndex = in.readInt();
        this.mVideo = in.readParcelable(Video.class.getClassLoader());
    }

    public static final Creator<VideoEvent> CREATOR = new Creator<VideoEvent>() {
        public VideoEvent createFromParcel(Parcel source) {
            return new VideoEvent(source);
        }

        public VideoEvent[] newArray(int size) {
            return new VideoEvent[size];
        }
    };

    @Override
    public Parcelable getData() {
        return mVideo;
    }
}
