package com.android.opengl.texture;

import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.android.opengl.BaseRenderer;
import com.android.opengl.utils.OpenGLUtils;

public abstract class BaseTextureHolder {
    public static final float Z = BaseRenderer.Z;
    protected GL10 gl;
    protected ShortBuffer bufferV;
    protected ShortBuffer bufferT;
    protected ShortBuffer bufferI;
    protected int texture;

    /*
     * default draw at (0,0) after loadIdentity and translate and scale
     */
    public abstract void draw();

    /*
     * draw at (posX,posY) by default at (0,0)
     */
    public void draw(int posX, int posY) {
        gl.glLoadIdentity();
        BaseRenderer.loadIdentity();
        gl.glTranslatef(posX, posY, Z);
        draw();
    }

    public void draw(int posX, int posY, float scale) {
        draw(posX, posY, scale, scale);
    }

    /*
     * draw at (posX,posY) by default at (0,0), no scale 1,loadIdentify
     * 2,translatef 3,scalef 4,draw
     */
    public void draw(int posX, int posY, float scaleX, float scaleY) {
        gl.glLoadIdentity();
        BaseRenderer.loadIdentity();
        gl.glTranslatef(posX, posY, Z);
        
        gl.glScalef(scaleX, scaleY, 1.0f);
        draw();
    }

    /**
     * @param texture
     * @param bitmap
     * @param recycleAfterBind
     *            if need to recycle the bitmap after the bind? default is
     *            recycle *
     */
    protected void bindTexture(int texture, Bitmap bitmap,
            boolean recycleAfterBind) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        if (recycleAfterBind) {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }

    protected void bindTexture(int texture, Bitmap bitmap) {
        bindTexture(texture, bitmap, true);
    }

    protected void bindTexture(Bitmap bitmap, boolean recycleAfterBind) {
        bindTexture(texture, bitmap, recycleAfterBind);
    }

    protected void bindTexture(Bitmap bitmap) {
        bindTexture(texture, bitmap, true);
    }

    protected int initTexture(GL10 gl) {
        // //IntBuffer intBuffer=IntBuffer.allocate(1);
        // gl.glGenTextures(1, intBuffer);
        // texture = intBuffer.get();
        int texTemp[] = new int[1];
        gl.glGenTextures(1, texTemp, 0);
        int texture = texTemp[0];
        return texture;
    }

    protected void initTexBufferShortUnit() {
        short arrayV[] = { 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0 };
        bufferV = OpenGLUtils.initBuffer(arrayV);

        short arrayT[] = { 0, 1, 1, 1, 1, 0, 0, 0 };
        bufferT = OpenGLUtils.initBuffer(arrayT);

        short arrayI[] = { 0, 1, 3, 2 };
        bufferI = OpenGLUtils.initBuffer(arrayI);
    }

    public BaseTextureHolder(GL10 gl) {
        this.gl = gl;
        initTexBufferShortUnit();
        texture = initTexture(gl);
    }
}
