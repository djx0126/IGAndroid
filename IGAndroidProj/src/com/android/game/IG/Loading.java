package com.android.game.IG;

import android.content.Context;
import android.util.Log;

import com.android.object.drawable.IDrawable;
import com.android.opengl.BaseGLSurfaceView;

public class Loading extends BaseGLSurfaceView {
    IDrawable myScene;
    IDrawable myLoadingScene;

    public Loading(Context context) {
        super(context);
    }

    @Override
    public BaseGLSurfaceView initView() {
        Log.d("Loading", "initView:" + Thread.currentThread().toString() + "/" + Thread.currentThread().getId());
        myLoadingScene = new LoadingScene(this);
        mRenderer.setDrawable(myLoadingScene);
        return this;
    }

    @Override
    public void initViewAsync() {
        Log.d("Loading", "initViewAsync");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("[Loading]", "initViewAsync:" + Thread.currentThread().toString() + "/" + Thread.currentThread().getId());
        BaseGLSurfaceView myGame = new Game(myContext).createRenderer().initView();
        this.switchTo(myGame);
    }
}
