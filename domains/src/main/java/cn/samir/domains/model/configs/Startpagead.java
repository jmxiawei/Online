/**
 * Copyright 2017 bejson.com
 */
package cn.samir.domains.model.configs;

import java.util.List;

/**
 * Auto-generated: 2017-05-03 16:25:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Startpagead {


    private int displayTimeDuration;

    private String imageUrl;
    private boolean countdown;

    private String actionUrl;

    private String blurredImageUrl;

    private long startTime;

    private boolean canSkip;
    private long endTime;
    private String version;

    private List<String> adTrack;

    public int getDisplayTimeDuration() {
        return displayTimeDuration;
    }

    public void setDisplayTimeDuration(int displayTimeDuration) {
        this.displayTimeDuration = displayTimeDuration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCountdown() {
        return countdown;
    }

    public void setCountdown(boolean countdown) {
        this.countdown = countdown;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getBlurredImageUrl() {
        return blurredImageUrl;
    }

    public void setBlurredImageUrl(String blurredImageUrl) {
        this.blurredImageUrl = blurredImageUrl;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isCanSkip() {
        return canSkip;
    }

    public void setCanSkip(boolean canSkip) {
        this.canSkip = canSkip;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(List<String> adTrack) {
        this.adTrack = adTrack;
    }
}