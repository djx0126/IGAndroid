package com.android.game.IG;

import android.content.Context;
import android.util.Log;

import com.android.object.drawable.IDrawable;
import com.android.opengl.BaseGLSurfaceView;

public class Game extends BaseGLSurfaceView {
    IDrawable myScene;
    IDrawable myLoadingScene;

    public Game(Context context) {
        super(context);
    }

    protected void initTexture() {

    }

    @Override
    public BaseGLSurfaceView initView() {
        Log.d("Game", "initView");
        myScene = new Scene(this);

        mRenderer.setDrawable(myScene);

        return this;
    }

    @Override
    public void initViewAsync() {
        Log.d("Game", "initViewAsync");

    }

}
