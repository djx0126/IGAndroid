package com.android.opengl.texture;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.opengl.BaseRenderer;
import com.android.opengl.utils.BitmapUtils;

public class BitmapTextureHolder extends BaseTextureHolder {
    protected Bitmap bitmap = null;

    public BitmapTextureHolder(BaseRenderer pRenderer, Context context, int resourceId) {
        super(pRenderer);
        bitmap = BitmapUtils.create2NBitmapFromResource(context, resourceId);
        bindTexture(bitmap);
    }

    @Override
    public void draw() {
        myRenderer.gl.glEnable(GL10.GL_BLEND);
        myRenderer.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        myRenderer.gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        myRenderer.gl.glVertexPointer(3, GL10.GL_SHORT, 0, bufferV);
        myRenderer.gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, bufferT);
        myRenderer.gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_SHORT, bufferI);
        myRenderer.gl.glDisable(GL10.GL_BLEND);
    }

}
