package com.android.opengl.texture;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.opengl.BaseRenderer;
import com.android.opengl.utils.BaseGLUnit;
import com.android.opengl.utils.BitmapUtils;

public class BitmapTextureArrayHolder extends BaseTextureHolder {
    public BitmapTextureHolder[] holders;
    private final Bitmap[] bitmaps;

    /**
     * @param pRenderer
     * @param context
     * @param resourceId
     *            like R.drawable.icon
     * @param nInRow
     *            the number of sub pics in a row
     * @param length
     *            how many sub pics in all (in all rows)
     */
    public BitmapTextureArrayHolder(BaseRenderer pRenderer, Context context, int resourceId, int nInRow, int length) {
        super(pRenderer);
        bitmaps = BitmapUtils.splitBitmap(BitmapUtils.createFromResource(context, resourceId), nInRow, length);
        holders = new BitmapTextureHolder[length];
        for (int i = 0; i < length; i++) {
            holders[i] = new BitmapTextureHolder(pRenderer, bitmaps[i]);
        }
    }

    @Override
    public void draw() {
        myRenderer.gl.glEnable(GL10.GL_BLEND);
        myRenderer.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        myRenderer.gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        BaseGLUnit.drawUnit(myRenderer.gl);
        myRenderer.gl.glDisable(GL10.GL_BLEND);
    }

}
