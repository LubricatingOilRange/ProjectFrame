package com.frame.projectframe.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.frame.projectframe.module.glide.BlurTransformation;
import com.frame.projectframe.module.glide.GlideCircleTransform;
import com.frame.projectframe.module.glide.GlideRoundTransform;
import com.frame.projectframe.module.glide.RotateTransformation;

public class GlideUtil {

    private final static String LOAD_BITMAP = "GLIDE_LOAD_BITMAP";//加载bitmap，如果是GIF则显示第一帧
    private final static String LOAD_GIF = "GLIDE_LOAD_GIF";//加载gif动画


    /**
     * @param context (Application,activity)
     */
    public static void LoadContextBitmap(Context context, String path, ImageView imageView, int placeId, int errorId, String bitmapOrGif) {
        if (bitmapOrGif == null || bitmapOrGif.equals(LOAD_BITMAP)) {
            Glide.with(context).load(path)
                    .placeholder(placeId)
                    .error(errorId).crossFade().into(imageView);
        } else if (bitmapOrGif.equals(LOAD_GIF)) {
            Glide.with(context).load(path).asGif().crossFade().into(imageView);
        }
    }

    /**
     * @param fragment (android.app.Fragment)
     */
    public static void LoadAppFragmentBitmap(android.app.Fragment fragment, String path, ImageView imageView, int placeId, int errorId, String bitmapOrGif) {
        if (bitmapOrGif == null || bitmapOrGif.equals(LOAD_BITMAP)) {
            Glide.with(fragment).load(path).
                    placeholder(placeId).
                    error(errorId).crossFade().into(imageView);
        } else if (bitmapOrGif.equals(LOAD_GIF)) {
            Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
        }
    }

    /**
     * @param fragment (android.support.v4.app.Fragment)
     */
    public static void LoadSupportV4FragmentBitmap(android.support.v4.app.Fragment fragment, String path, ImageView imageView, int placeId, int errorId, String bitmapOrGif) {
        if (bitmapOrGif == null || bitmapOrGif.equals(LOAD_BITMAP)) {
            Glide.with(fragment).load(path).placeholder(placeId).error(errorId).crossFade().into(imageView);
        } else if (bitmapOrGif.equals(LOAD_GIF)) {
            Glide.with(fragment).load(path).asGif().crossFade().into(imageView);
        }
    }

    //---------------------圆形图片-------------------

    /**
     * @param context (all,区域不一样)
     */
    @SuppressWarnings("unchecked")
    public static void LoadContextCircleBitmap(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).bitmapTransform(new GlideCircleTransform(context)).into(imageView);
    }

    //-----------------------圆角图片----------------------

    /**
     * @param context (all,区域不一样)
     */
    @SuppressWarnings("unchecked")
    public static void LoadContextRoundBitmap(Context context, String path, ImageView imageView, int roundRadius) {
        if (roundRadius < 0) {
            Glide.with(context).load(path).bitmapTransform(new GlideRoundTransform(context)).into(imageView);
        } else {
            Glide.with(context).load(path).bitmapTransform(new GlideRoundTransform(context, roundRadius)).into(imageView);
        }
    }

    //-----------------------加载模糊图片----------------------

    /**
     * @param context (all,区域不一样)
     */
    @SuppressWarnings("unchecked")
    public static void LoadContextBlurBitmap(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).bitmapTransform(new BlurTransformation(context)).into(imageView);
    }

    /**
     * @param context (all,区域不一样)
     */
    //------------------------旋转图片---------------------------------
    @SuppressWarnings("unchecked")
    public static void LoadContextRotateBitmap(Context context, String path, ImageView imageView, Float rotateRotationAngle) {
        Glide.with(context).load(path).bitmapTransform(new RotateTransformation(context, rotateRotationAngle)).into(imageView);
    }
}
