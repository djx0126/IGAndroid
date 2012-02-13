package com.android.opengl.utils;

import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class BaseGLUnit {
    public static BaseGLUnit myInstance = new BaseGLUnit();
    protected static ShortBuffer bufferV;
    protected static ShortBuffer bufferT;
    protected static ShortBuffer bufferI;

    protected BaseGLUnit() {
        initTexBufferShortUnit();
    }

    public static BaseGLUnit getInstance() {
        return myInstance;
    }

    protected static void initTexBufferShortUnit() {
        Log.d("BaseGLUnit", "initTexBufferShortUnit");
        short arrayV[] = { 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0 };
        bufferV = OpenGLUtils.initBuffer(arrayV);

        short arrayT[] = { 0, 1, 1, 1, 1, 0, 0, 0 };
        bufferT = OpenGLUtils.initBuffer(arrayT);

        short arrayI[] = { 0, 1, 3, 2 };
        bufferI = OpenGLUtils.initBuffer(arrayI);
    }

    public static void drawUnit(GL10 gl) {
        gl.glVertexPointer(3, GL10.GL_SHORT, 0, bufferV);
        gl.glTexCoordPointer(2, GL10.GL_SHORT, 0, bufferT);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_SHORT, bufferI);
    }

}
