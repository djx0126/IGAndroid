package com.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.opengl.BaseGLSurfaceView;

public class BaseOpenGLActivity extends Activity {
    protected BaseGLSurfaceView myGLView;
    public BaseGLSurfaceView nextView;

    public static BaseOpenGLActivity context;
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("BaseOpenGLActivity", "handleMessage:" + msg.what);
            switch (msg.what) {
            case BaseGLSurfaceView.MSG_SWITCHVIEW:
                if (nextView != null) {
                    setView(nextView);
                }
                break;
            }
        }

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.main);

        Log.d("Android3DBasicActivity", "onCreate");

        context = this;

        initView();
    }

    public void initView() {
        // 去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myGLView = new BaseGLSurfaceView(this);
        myGLView.createRenderer().initView();
        setView(myGLView);
        System.gc();
    }

    public void setView(BaseGLSurfaceView pView) {
        this.myGLView = pView;
        setContentView(myGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Android3DBasicActivity", "onPause");

        // de-allocate objects
        myGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Android3DBasicActivity", "onResume");

        // re-allocate objects;
        myGLView.onResume();
    }
}