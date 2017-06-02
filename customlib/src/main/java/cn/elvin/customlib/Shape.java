package cn.elvin.customlib;

import android.graphics.Point;

/**
 * Created by xiaw on 2017/3/20 0020.
 */

public class Shape {

    public static final int SHAPE_ARROW = 1;
    public int arrowWidth = 30;
    public int type = SHAPE_ARROW;
    /**
     *
     *
     * a                        b
     * -------------------------|
     * |                        |
     * |                        |
     * |                        |
     * -------------------------|c
     * d
     */

    /**
     * 起始点
     */
    public Point pa;
    /**
     * 终结点
     */
    public Point pb;

    /**
     * 与pb平行
     */
    public Point pc;
    /**
     * 与pa平行
     */
    public Point pd;


    /**
     * ab长度
     */
    public int abl;

    public Point getPa() {
        return pa;
    }

    public Shape() {
    }

    public Shape(int arrowWidth) {
        this.arrowWidth = arrowWidth;
    }

    /**
     * 是否显示
     *
     * @return
     */
    public boolean needDraw() {


        L.e("abl=" + abl + ",arrowWidth=" + arrowWidth);
        return abl > arrowWidth;
    }

    public void setPa(int x, int y) {
        this.pa = new Point(x, y);
    }

    public Point getPb() {
        return pb;
    }

    public void setPb(int x, int y) {
        this.pb = new Point(x, y);

        abl = (int) Math.sqrt((pb.y - pa.y) * (pb.y - pa.y) + (pb.x - pa.x) * (pb.x - pa.x));
    }

    public Point getPc() {
        return pc;
    }

    public void setPc(int x, int y) {
        this.pc = new Point(x, y);
    }

    public Point getPd() {
        return pd;
    }

    public void setPd(int x, int y) {
        this.pd = new Point(x, y);
    }


    public void setPa(Point pa) {
        this.pa = pa;
    }

    public void setPb(Point pb) {
        this.pb = pb;
        abl = (int) Math.sqrt((pb.y - pa.y) * (pb.y - pa.y) + (pb.x - pa.x) * (pb.x - pa.x));
        calPoints();
    }

    public void setPc(Point pc) {
        this.pc = pc;
    }

    public void setPd(Point pd) {
        this.pd = pd;
    }

    public int getAbl() {
        return abl;
    }

    public void setAbl(int abl) {
        this.abl = abl;
    }


    private void calPoints() {

        int mDownX = pa.x;
        int mDownY = pa.y;
        int mLastX = pb.x;
        int mLastY = pb.y;


        //D点坐标
        int pdx = mDownX + (int) (((mLastY - mDownY) * arrowWidth)
                / Math.sqrt((mLastY - mDownY) * (mLastY - mDownY) + (mLastX - mDownX) * (mLastX - mDownX)));
        int pdy = mDownY - (int) (((mLastX - mDownX) * arrowWidth)
                / Math.sqrt((mLastY - mDownY) * (mLastY - mDownY) + (mLastX - mDownX) * (mLastX - mDownX)));
        setPd(pdx, pdy);
        //C点坐标
        int pcx = mLastX + (int) (((mLastY - mDownY) * arrowWidth)
                / Math.sqrt((mLastY - mDownY) * (mLastY - mDownY) + (mLastX - mDownX) * (mLastX - mDownX)));
        int pcy = mLastY - (int) (((mLastX - mDownX) * arrowWidth)
                / Math.sqrt((mLastY - mDownY) * (mLastY - mDownY) + (mLastX - mDownX) * (mLastX - mDownX)));
        setPc(pcx, pcy);
    }

}
