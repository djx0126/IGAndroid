package com.android.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class BaseGLSurfaceView extends GLSurfaceView {
    protected final Context context;
    protected final BaseRenderer mRenderer;

    public BaseGLSurfaceView(Context context) {
        super(context);
        this.context = context;
        mRenderer = new BaseRenderer();
        initView();
        setRenderer(mRenderer);

        requestFocus();
        setFocusableInTouchMode(true);
    }

    public void initView() {
        Log.d("BaseGLSurfaceView", "initView");
    }

}
