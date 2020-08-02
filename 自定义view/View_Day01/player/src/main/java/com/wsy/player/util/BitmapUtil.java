package com.wsy.player.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.wsy.player.R;
import com.wsy.player.ui.UIUtils;

public class BitmapUtil {
    public static Drawable getMusicItemDrawable(Context context, int resId) {
        int outPicSize = UIUtils.getInstance().getWidth(813);
        int inPicSize = UIUtils.getInstance().getWidth(533);
        //外面的圆盘
        Bitmap bitmapDisc = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_disc), outPicSize, outPicSize, false);
        //里面的图片  转换成正方形
        Bitmap bitmapMusicPic = getMusicPicBitmap(context, inPicSize, resId);
        BitmapDrawable disBitmapDrawable = new BitmapDrawable(bitmapDisc);
        // 再转换为圆形
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmapMusicPic);

        disBitmapDrawable.setAntiAlias(true);
        roundedBitmapDrawable.setAntiAlias(true);
        Drawable[] drawables = new Drawable[2];
        drawables[0] = roundedBitmapDrawable;
        drawables[1] = disBitmapDrawable;

        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        int musicPicMargin = (int) ((outPicSize - inPicSize) / 2);
        layerDrawable.setLayerInset(0, musicPicMargin, musicPicMargin, musicPicMargin, musicPicMargin);
        return layerDrawable;

    }

    private static Bitmap getMusicPicBitmap(Context context, int inPicSize, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        int imageWidth = options.outWidth;
        int sample = imageWidth / inPicSize;
        int dstSample = 1;
        if (sample > dstSample) {
            dstSample = sample;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = dstSample;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap resultBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), resId, options), inPicSize, inPicSize, true);
        return resultBitmap;
    }
}
