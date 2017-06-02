package cn.samir.online.adapter;

/**
 * Created by xiaw on 2017/4/21 0021.
 */

public class ItemSettings {

    public static final int BACKGROUND_DARK = 1;
    public static final int BACKGROUND_LIGHT = 0;
    public static final int INVALID = -1;
    public int background = BACKGROUND_LIGHT;

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public ItemSettings(int background) {
        this.background = background;
    }

    public ItemSettings(int background, boolean mSnapHelper) {
        this.background = background;
        this.mSnapHelper = mSnapHelper;
    }

    public ItemSettings() {

    }

    public ItemSettings(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width = INVALID;
    public int height = INVALID;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 是否需要加一个白色背景
     */
    public boolean needWhiteBackground = true;

    public boolean isNeedWhiteBackground() {
        return needWhiteBackground;
    }

    public void setNeedWhiteBackground(boolean needWhiteBackground) {
        this.needWhiteBackground = needWhiteBackground;
    }

    public boolean isSnapHelper() {
        return mSnapHelper;
    }

    public void setSnapHelper(boolean mSnapHelper) {
        this.mSnapHelper = mSnapHelper;
    }

    private boolean mSnapHelper = true;


    public ItemSettings(boolean mSnapHelper) {
        this.mSnapHelper = mSnapHelper;
    }

}
