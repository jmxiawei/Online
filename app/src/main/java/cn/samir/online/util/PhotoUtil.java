package cn.samir.online.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by xiaw on 2017/3/17 0017.
 */

public class PhotoUtil {
    public static  final int DEFAULT_ICON_SIZE = 100;

    public static void showPhoto(SimpleDraweeView sdv, String path) {

        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path));
        ImageRequest request = builder
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .build();
        sdv.setController(controller);

    }

    public static void showPhoto(SimpleDraweeView sdv, String path, int w, int h) {

        w = DensityUtil.getDisplayWidth(sdv.getContext());
        h = w * 2 / 3;
        ResizeOptions resizeOptions = new ResizeOptions(w / 2, h / 2);
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path));
        ImageRequest request = builder.setResizeOptions(resizeOptions)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .build();
        sdv.setController(controller);

    }

    public static void simpleShowPhoto(SimpleDraweeView sdv, String path, int w, int h) {

        ResizeOptions resizeOptions = new ResizeOptions(w, h);
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path));
        ImageRequest request = builder.setResizeOptions(resizeOptions)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .build();
        sdv.setController(controller);

    }

    private static void showDefault(SimpleDraweeView sdv){

    }

    public static void showPhotoMaybeGif(SimpleDraweeView sdv, String path) {

        //int w = DensityUtil.getDisplayWidth(sdv.getContext());
        //int h = w * 2 / 3;
        //ResizeOptions resizeOptions = new ResizeOptions(w / 2, h / 2);
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path));
        ImageRequest request = builder
                //.setResizeOptions(resizeOptions)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .setAutoPlayAnimations(true)
                .build();
        sdv.setController(controller);

    }


    public static int getSimpleDraweeViewWidthForDisplay(SimpleDraweeView sdv) {
        return sdv.getMeasuredWidth();
    }

    public static int getSimpleDraweeViewHeightForDisplay(SimpleDraweeView sdv) {
        return sdv.getMeasuredHeight();
    }
}
