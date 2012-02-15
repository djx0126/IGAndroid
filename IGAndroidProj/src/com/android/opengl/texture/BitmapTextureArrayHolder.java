package com.android.opengl.texture;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.opengl.BaseRenderer;
import com.android.opengl.utils.BaseGLUnit;
import com.android.opengl.utils.BitmapUtils;

public class BitmapTextureArrayHolder extends BaseTextureHolder {
    // public BitmapTextureHolder[] holders;
    // private final Bitmap[] bitmaps;
    private final List<Bitmap> bitmapList = new ArrayList<Bitmap>();
    private final List<BitmapTextureHolder> textureHolders = new ArrayList<BitmapTextureHolder>();

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
        // bitmaps = BitmapUtils.splitBitmap(
        // BitmapUtils.createFromResource(context, resourceId), nInRow,
        // length);
        bitmapList.addAll(BitmapUtils.splitBitmap(BitmapUtils.createFromResource(context, resourceId), nInRow, length));
        for (Bitmap bitmap : bitmapList) {
            textureHolders.add(new BitmapTextureHolder(pRenderer, bitmap));
        }
        // holders = new BitmapTextureHolder[length];
        // for (int i = 0; i < length; i++) {
        // holders[i] = new BitmapTextureHolder(pRenderer, bitmaps[i]);
        // }
    }

    public BitmapTextureHolder item(int i) {
        BitmapTextureHolder holder = null;
        if (i >= 0 && i < textureHolders.size()) {
            holder = textureHolders.get(i);
        }
        return holder;
    }

    @Override
    public void draw() {
        myRenderer.gl.glEnable(GL10.GL_BLEND);
        myRenderer.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        myRenderer.gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        BaseGLUnit.NORMALFLOAT.drawUnit(myRenderer.gl);
        myRenderer.gl.glDisable(GL10.GL_BLEND);
    }

}
