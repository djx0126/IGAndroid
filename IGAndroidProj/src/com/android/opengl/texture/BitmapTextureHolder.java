package com.android.opengl.texture;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.opengl.utils.BitmapUtils;

public class BitmapTextureHolder extends BaseTextureHolder {
    protected Bitmap bitmap = null;

    public BitmapTextureHolder(GL10 gl, Context context, int resourceId) {
        super(gl);
        bitmap = BitmapUtils.create2NBitmapFromResource(context, resourceId);
        bindTexture(bitmap);
    }

    @Override
    public void draw() {
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glVertexPointer(3, GL10.GL_SHORT, 0, bufferV);
        gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, bufferT);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_SHORT,
                bufferI);
        gl.glDisable(GL10.GL_BLEND);
    }

}
