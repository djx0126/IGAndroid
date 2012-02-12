package com.android.object.drawable;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;


public abstract class BaseDrawableObject implements IDrawable {
    public int posX;
    public int posY;
    public boolean initiated = false;
    public boolean active = true;
    public GL10 gl;
    protected final Context context;

    public BaseDrawableObject(Context context) {
        this.context = context;

    }

    public final void draw() {
        if (initiated && active) {
            onDraw();
        }
    }

    protected abstract void onDraw();

    public final void initDrawable(GL10 gl) {
        if (gl != null) {
            this.gl = gl;
            initDrawable();
            initiated = true;
        }
    }

    protected abstract void initDrawable();
}
