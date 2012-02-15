package com.android.opengl.utils;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class BaseGLUnit {
    public static BaseGLUnit NORMALFLOAT = new BaseGLUnit();
    public static BaseGLUnit NORMALSHORT = new ShortUnit();
    public static BaseGLUnit CENTEREDHALF = BaseGLUnit.getCenteredUnit(0.5f);
    protected FloatBuffer bufferV;
    protected ShortBuffer bufferT;
    protected ShortBuffer bufferI;

    protected BaseGLUnit() {
        initVertexBufferFloatUnit();
        initTexAndIdxBufferUnit();
    }

    protected BaseGLUnit(float height) {
        initVertexBufferFloatUnit(height);
        initTexAndIdxBufferUnit();
    }

    protected BaseGLUnit(float a, float height) {
        initVertexBufferFloatUnit(height);
        initTexAndIdxBufferUnit();
    }

    protected void initVertexBufferFloatUnit() {
        // Log.d("BaseGLUnit", "initVertexBufferFloatUnit");
        float arrayV[] = { 0f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 0f, 1f, 0f };
        bufferV = OpenGLUtils.initBuffer(arrayV);
    }

    protected void initVertexBufferFloatUnit(float height) {
        // Log.d("BaseGLUnit", "initVertexBufferFloatUnit with height");
        float arrayV[] = { 0.5f, 0f, 0f, 1f, 0.5f * height, 0f, 0.5f, 1f * height, 0f, 0f, 0.5f * height, 0f };
        bufferV = OpenGLUtils.initBuffer(arrayV);
    }

    protected void initVertexBufferFloatUnit(float a, float height) {
        // Log.d("BaseGLUnit", "initVertexBufferFloatUnit with height");
        float arrayV[] = { 0f, 0f, 0f, 1f, 0f, 0f, 1f, 1f * height, 0f, 0f, 1f * height, 0f };
        bufferV = OpenGLUtils.initBuffer(arrayV);
    }

    protected void initTexAndIdxBufferUnit() {
        short arrayT[] = { 0, 1, 1, 1, 1, 0, 0, 0 };
        bufferT = OpenGLUtils.initBuffer(arrayT);

        short arrayI[] = { 0, 1, 3, 2 };
        bufferI = OpenGLUtils.initBuffer(arrayI);
    }

    public void drawUnit(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufferV);
        gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, bufferT);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_SHORT, bufferI);
    }

    /**
     * @param height
     *            from 0 to 1
     * @return
     */
    public static BaseGLUnit getCenteredUnit(float height) {
        return new BaseGLUnit(height);
    }

    /**
     * @param a
     *            angle in degree
     * @param height
     *            height from 0 to 1
     * @return
     */
    public static BaseGLUnit getRightSlopedUnit(float a, float height) {
        return new BaseGLUnit(height);
    }

    protected static class ShortUnit extends BaseGLUnit {
        protected ShortBuffer bufferV;

        ShortUnit() {
            super();
            initVertexBufferShortUnit();
        }

        protected void initVertexBufferShortUnit() {
            // Log.d("ShortUnit", "initVertexShortBufferUnit");
            short arrayV[] = { 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0 };
            bufferV = OpenGLUtils.initBuffer(arrayV);
        }

        @Override
        public void drawUnit(GL10 gl) {
            gl.glVertexPointer(3, GL10.GL_SHORT, 0, bufferV);
            gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, bufferT);
            gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_SHORT, bufferI);
        }
    }

}
