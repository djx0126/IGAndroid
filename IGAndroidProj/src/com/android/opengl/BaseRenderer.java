package com.android.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.android.object.drawable.IDrawable;
import com.android.utils.FPS;

public class BaseRenderer implements GLSurfaceView.Renderer {
    //private final Context context;
    public static GL10 gl;
    public static final float Z = -1f;
    public static final float TARGETFPS = 60f;
    public static int viewWidth = 0;
    public static int viewHeight = 0;
    private IDrawable myDrawable;

    private final FPS myFPS = new FPS();

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        if (myDrawable != null) {
            myDrawable.draw();
        }
        myFPS.tick(TARGETFPS);
    }

    public void setDrawable(IDrawable pDrawable) {
        this.myDrawable = pDrawable;
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
        gl.glFrustumf(-viewWidth/2, viewWidth/2, -viewHeight/2, viewHeight/2, 1f, 3f); // apply the
                                                             // projection
                                                             // matrix

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d("MyRenderer", "onSurfaceCreated");
        BaseRenderer.gl = gl;

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
    }
    
    public BaseRenderer() {
        Log.d("MyRenderer", "Constructor");
    }

    public BaseRenderer(Context pContext) {
        Log.d("MyRenderer", "Constructor");
        //context = pContext;
    }

    public BaseRenderer(Context pContext, int pWidth, int pHeight) {
        Log.d("MyRenderer", "Constructor");
        //context = pContext;
        viewWidth = pWidth;
        viewHeight = pHeight;
    }
    
    public static void loadIdentity(){
    	gl.glLoadIdentity();
        gl.glTranslatef(-viewWidth/2, -viewHeight/2, 0f);
    }

}
