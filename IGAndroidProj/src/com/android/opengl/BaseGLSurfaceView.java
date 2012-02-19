package com.android.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.test.BaseOpenGLActivity;

public class BaseGLSurfaceView extends GLSurfaceView {
    public static final int MSG_SWITCHVIEW = 2;
    public Context myContext;
    public BaseRenderer mRenderer;
    public boolean viewCreated = false;
    public int viewWidth;
    public int viewHeight;
    public int leftPad = 50;
    public int rightPad = 50;
    public int bottomPad = 100;
    public int topPad = 50;
    public BaseGLSurfaceView nextView;

    public BaseGLSurfaceView(Context context) {
        super(context);
        this.myContext = context;

        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        viewWidth = dm.widthPixels;
        viewHeight = dm.heightPixels;

        requestFocus();
        setFocusableInTouchMode(true);
        Log.d("BaseGLSurfaceView", "created:" + Thread.currentThread().toString() + "/"
                + Thread.currentThread().getId());
    }

    /**
     * @param pWidth
     *            int the width for the view
     * @param pHeight
     *            int the height for the view this function should be called
     *            before the renderer is created, which means it should be the
     *            1st thing to do after the view is created. if not set
     *            manually, default value--screen pixel size will be used
     */
    public BaseGLSurfaceView setViewWidthHeight(int pWidth, int pHeight) {
        if (pWidth <= 0 || pHeight <= 0) {
            DisplayMetrics dm = this.getResources().getDisplayMetrics();
            viewWidth = dm.widthPixels;
            viewHeight = dm.heightPixels;
        } else {
            viewWidth = pWidth;
            viewHeight = pHeight;
        }

        Log.d("viewWidth", String.valueOf(viewWidth));
        Log.d("viewHeight", String.valueOf(viewHeight));
        return this;
    }

    /**
     * A new BaseRenderer should be created and returned. by default, the
     * initViewAsyncHandler will be set as the CreatedHook to the renderer
     * created.
     * 
     * @return the this view.
     */
    public BaseGLSurfaceView createRenderer() {

        createRenderer(new BaseRenderer(viewWidth, viewHeight).setCreatedTask(new OnRendererCreated()));
        return this;
    }

    public BaseGLSurfaceView createRenderer(BaseRenderer pRenderer) {
        // System.out.println("[BaseGLSurfaceView]to createRender:" +
        // Thread.currentThread().toString() + "/"
        // + Thread.currentThread().getId());
        mRenderer = pRenderer;
        if (mRenderer != null) {
            setRenderer(mRenderer);
        }
        return this;

    }

    /**
     * this function will be run right after initRenderer. at this time the
     * renderer may not be created successfully which means all functions depend
     * on gl should not be call in this function.
     * 
     * any drawable objects on this view should be created here and use this
     * function to the top level drawable.
     * mRenderer.setDrawable(myLoadingScene);
     * 
     * @return
     */
    public BaseGLSurfaceView initView() {
        Log.d("BaseGLSurfaceView", "initView");
        return this;
    }

    /**
     * this function will be run in a new thread after the renderer is OK. this
     * function can be override, if something need to be done after the renderer
     * is created(gl is ready), like initializing texture.
     */
    protected void initViewAsync() {
        Log.d("BaseGLSurfaceView", "initViewAsync");
    }

    protected void switchTo(BaseGLSurfaceView nextView) {
        ((BaseOpenGLActivity) myContext).nextView = nextView;
        Message msg = new Message();
        msg.what = MSG_SWITCHVIEW;
        ((BaseOpenGLActivity) myContext).myHandler.sendMessage(msg);
    }

    class OnRendererCreated extends AsyncTask {

        @Override
        protected Object doInBackground(Object... params) {
            Looper.prepare();
            initViewAsync();
            // if Looper.loop is used, it will loop until the Looper.quit is
            // called. Or the thread will not end.
            // Looper.loop();
            return null;
        }

    }

}
