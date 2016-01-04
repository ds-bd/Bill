package com.mm.bill.image;

import java.util.HashSet;
import java.util.Set;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import android.content.Context;
import android.net.Uri;

/**
 * 
 * read more from http://frescolib.org/docs/index.html <br>
 * 所有Fresco图片库的调用都封装在该类中，以便后面统一处理<br>
 * 
 * 当前引入版本为<br>
 * https://github.com/facebook/fresco/releases/download/v0.7.0/frescolib-v0.7.0.zip
 *
 */
public class FrescoWrapper {

    public static void initialize(Context context) {
        Set<RequestListener> listeners = new HashSet<RequestListener>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context).setRequestListeners(listeners)
                // .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(context, config);
    }

    public static void displayFileImg(SimpleDraweeView draweeView, String diskPath) {
        Uri uri = Uri.parse(String.format("file://%1$s/%2$s", draweeView.getContext().getPackageName(), diskPath));
        DraweeController animatedWebpController =
                Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setUri(uri).build();
        draweeView.setController(animatedWebpController);
    }

}
