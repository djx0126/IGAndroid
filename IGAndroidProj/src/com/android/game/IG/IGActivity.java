package com.android.game.IG;

import android.view.Window;
import android.view.WindowManager;

import com.android.test.BaseOpenGLActivity;

public class IGActivity extends BaseOpenGLActivity {

    @Override
    public void initView() {
        // 去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myGLView = new Loading(this).setViewWidthHeight(800, 480);
        myGLView.createRenderer().initView();

        setView(myGLView);
    }
}
