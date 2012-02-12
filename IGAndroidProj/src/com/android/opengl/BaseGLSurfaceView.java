package com.android.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.game.IG.IGActivity;

public class BaseGLSurfaceView extends GLSurfaceView {
    public static final int SWITCHVIEW = 1;
    public Context myContext;
    public BaseRenderer mRenderer;
    public boolean viewCreated = false;
    public int viewWidth;
    public int viewHeight;
    public BaseGLSurfaceView nextView;

    public Handler myHandler;

    public BaseGLSurfaceView(Context context) {
        super(context);
        this.myContext = context;
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        viewWidth = dm.widthPixels;
        viewHeight = dm.heightPixels;
        Log.d("viewWidth", String.valueOf(viewWidth));
        Log.d("viewHeight", String.valueOf(viewHeight));

        mRenderer = createRenderer();

        initView();

        if (mRenderer != null) {
            setRenderer(mRenderer);
        }

        requestFocus();
        setFocusableInTouchMode(true);
    }

    /**
     * A new BaseRenderer should be created and returned. by default, the
     * initViewAsyncHandler will be set as the CreatedHook to the renderer
     * created.
     * 
     * @return the BaseRenderer created.
     */
    public BaseRenderer createRenderer() {
        return new BaseRenderer(viewWidth, viewHeight).setCreatedHook(new initViewAsyncHandler());

    }

    /**
     * this function will be run right after initRenderer. at this time the
     * renderer may not be created successfully which means all functions depend
     * on gl should not be call in this function.
     */
    public void initView() {
        Log.d("BaseGLSurfaceView", "initView");
    }

    /**
     * this function will be run in a new thread after the renderer is OK. this
     * function can be override, if something need to be done after the renderer
     * is created(gl is ready), like initializing texture.
     */
    public void initViewAsync() {
        Log.d("BaseGLSurfaceView", "initViewAsync");
    }

    public void switchTo(BaseGLSurfaceView nextView) {
        ((IGActivity) myContext).nextView = nextView;
        Message msg = new Message();
        msg.what = SWITCHVIEW;
        ((IGActivity) myContext).myHandler.sendMessage(msg);
    }

    class initViewAsyncHandler implements Runnable {
        public void run() {
            Looper.prepare();
            initViewAsync();
            Looper.loop();

        }
    }

}
