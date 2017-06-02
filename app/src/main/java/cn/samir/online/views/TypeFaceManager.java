package cn.samir.online.views;

import android.content.Context;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import bolts.Task;
import cn.samir.online.util.L;

/**
 * Created by xiawei on 2017/3/8.
 */

public class TypeFaceManager {

    private static TypeFaceManager instance = null;
    String[] names = new String[]{
            "fonts/FZLanTingHeiS-L-GB-Regular.TTF",//细体
            "fonts/Futura-CondensedMedium.ttf",//细体
            "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF",//更粗体
            "fonts/Lobster-1.4.otf"//标题的字体，英文
    };

    String[] fonts = new String[]{
            "thin",
            "bold",
            "moreBold",
            "lobster"
    };


    private ArrayList<Typeface> typefaces = new ArrayList<>(4);


    private Context context;

    private TypeFaceManager(Context context) {
        this.context = context;
        Task.callInBackground(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                loadTypeFaceRunnable.run();
                return null;
            }
        });
    }


    private Runnable loadTypeFaceRunnable = new Runnable() {
        @Override
        public void run() {
            int l = names.length;
            for (int i = 0; i < l; i++) {
                typefaces.add(Typeface.createFromAsset(context.getAssets(), names[i]));
            }
            L.e("load Typeface end");
        }
    };


    public Typeface getTypeFace(String font) {
        for (int i = 0; i < fonts.length; i++) {
            if (fonts[i].equals(font)) {
                return getTypeFace(i);
            }
        }
        return null;
    }

    public Typeface getTypeFace(int index) {
        if (index < typefaces.size()) {
            return typefaces.get(index);
        } else {
            Typeface tf = Typeface.createFromAsset(context.getAssets(), names[index]);
            return tf;
        }
    }

    public static TypeFaceManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new TypeFaceManager(context);
    }


}
