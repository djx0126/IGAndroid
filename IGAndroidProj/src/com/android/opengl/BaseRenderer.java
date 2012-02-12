package com.android.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.util.Log;

import com.android.object.drawable.IDrawable;
import com.android.utils.FPS;

public class BaseRenderer implements GLSurfaceView.Renderer {
    // private final Context context;
    public GL10 gl;
    public static final float Z = -1f;
    public static final float TARGETFPS = 60f;
    public int viewWidth = 0;
    public int viewHeight = 0;
    protected IDrawable myDrawable;
    protected Runnable createdHook;

    protected final FPS myFPS = new FPS();

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        if (myDrawable != null) {
            myDrawable.draw();
        }
        myFPS.tick(TARGETFPS);
    }

    /**
     * @param pDrawable
     *            the IDrawable to be draw the IDrawable should be initialized.
     *            if the IDrawable is set before the renderer is Created, the
     *            IDrawable can be initialize at the end of creating function.
     */
    public void setDrawable(IDrawable pDrawable) {
        myDrawable = pDrawable;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d("MyRenderer", "onSurfaceChanged");
        Log.d("width", String.valueOf(width));
        Log.d("height", String.valueOf(height));

        if (viewWidth == 0) {
            viewWidth = width;
        }
        if (viewHeight == 0) {
            viewHeight = height;
        }

        gl.glViewport(0, 0, width, height);

        // make adjustments for screen ratio
        // float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
        gl.glLoadIdentity(); // reset the matrix to its default state
        // set leftdown(0,0) rightup(viewWidth,viewHeight)
        gl.glFrustumf(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2, 1f, 3f); // apply
                                                                                               // the
        // projection
        // matrix

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onSurfaceCreated(GL10 pGl, EGLConfig config) {
        Log.d("MyRenderer", "onSurfaceCreated");
        gl = pGl;

        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        gl.glEnable(GL10.GL_LINE_SMOOTH);
        gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glEnable(GL10.GL_TEXTURE_2D); // ///////////////////////////////////////
                                         // Enable 2D Textrue
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        if (myDrawable != null) {
            myDrawable.initDrawable(gl);
        }

        if (createdHook != null) {
            new Thread(createdHook).start();
        }
        Log.d("MyRenderer", "after onSurfaceCreated");
    }

    public BaseRenderer() {
        Log.d("MyRenderer", "Constructor");
    }

    public BaseRenderer(int pWidth, int pHeight) {
        Log.d("MyRenderer", "Constructor");
        viewWidth = pWidth;
        viewHeight = pHeight;
    }

    /**
     * @param task
     *            the runnable to be executed after the renderer is created.
     * @return this for the SET pattern
     */
    public BaseRenderer setCreatedHook(Runnable task) {
        createdHook = task;
        return this;
    }

    public void loadIdentity() {
        gl.glLoadIdentity();
        gl.glTranslatef(-viewWidth / 2, -viewHeight / 2, 0f);
    }

}
