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
    public void initView() {
        Log.d("Loading", "initView");
        myLoadingScene = new LoadingScene(this);
        mRenderer.setDrawable(myLoadingScene);
    }

    @Override
    public void initViewAsync() {
        Log.d("Loading", "initViewAsync");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BaseGLSurfaceView myGame = new Game(myContext);
        this.switchTo(myGame);
    }
}
