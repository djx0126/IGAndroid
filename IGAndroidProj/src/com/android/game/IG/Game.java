package com.android.game.IG;

import android.content.Context;
import android.util.Log;

import com.android.object.drawable.IDrawable;
import com.android.opengl.BaseGLSurfaceView;

public class Game extends BaseGLSurfaceView{

	public Game(Context context) {
		super(context);
	}
	
	@Override
	public void initView() {
        Log.d("Game", "initView");
        IDrawable myScene = new Scene(context);
        mRenderer.setDrawable(myScene);
    }

}
