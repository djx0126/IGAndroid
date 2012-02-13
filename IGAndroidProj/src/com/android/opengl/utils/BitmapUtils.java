package com.android.opengl.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class BitmapUtils {
    private static Canvas canvas = new Canvas();
    private static Paint paint = new Paint();
    private static int bitmapW;
    private static int bitmapH;
    private static int bitmapW2N;
    private static int bitmapH2N;

    public static Bitmap create2NBitmapFromResource(Context context, int resourceId) {
        Bitmap bitmap = createFromResource(context, resourceId);
        if (bitmap != null) {
            bitmap = expandBitmapTo2N(bitmap);
        }
        return bitmap;
    }

    public static Bitmap expandBitmapTo2N(Bitmap pBitmap) {
        Bitmap bitmap = null;
        bitmapW = pBitmap.getWidth();
        bitmapH = pBitmap.getHeight();
        bitmapW2N = OpenGLUtils.getNext2N(bitmapW);
        bitmapH2N = OpenGLUtils.getNext2N(bitmapH);

        bitmap = Bitmap.createBitmap(bitmapW2N, bitmapH2N, Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        paint.setAlpha(255);
        canvas.drawBitmap(pBitmap, 0, bitmapH2N - bitmapH, paint);
        return bitmap;
    }

    public static Bitmap[] splitBitmap(Bitmap pBitmap, int nInRow, int length) {
        Bitmap[] bitmaps = new Bitmap[length];
        bitmapW = pBitmap.getWidth();
        bitmapH = pBitmap.getHeight();
        Log.d("BitmapUtils.splitBitmap", "width=" + String.valueOf(bitmapW) + ",height=" + String.valueOf(bitmapH));
        int subW = bitmapW / nInRow;
        Log.d("BitmapUtils.splitBitmap", "subW=" + String.valueOf(subW));
        for (int i = 0; i < length; i++) {
            Log.d("BitmapUtils.splitBitmap", "i=" + String.valueOf(i) + ",subX=" + String.valueOf((i % nInRow) * subW)
                    + ",subY=" + String.valueOf(subW * (i / nInRow)));
            bitmaps[i] = Bitmap.createBitmap(pBitmap, (i % nInRow) * subW, subW * (i / nInRow), subW, subW);
        }
        return bitmaps;
    }

    public static Bitmap createFromResource(Context context, int resourceId) {
        Bitmap bitmap = null;
        InputStream is = null;
        is = context.getResources().openRawResource(resourceId);

        try {
            bitmap = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // Ignore.
            }
        }

        return bitmap;
    }
}
