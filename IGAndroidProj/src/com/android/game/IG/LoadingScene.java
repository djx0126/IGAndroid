package com.android.game.IG;

import android.graphics.Typeface;
import android.util.Log;

import com.android.object.drawable.BaseDrawableObject;
import com.android.opengl.BaseGLSurfaceView;
import com.android.opengl.texture.BitmapTextureHolder;
import com.android.opengl.texture.text.StringTextureHolder;

public class LoadingScene extends BaseDrawableObject {

    private StringTextureHolder textTexture;
    private BitmapTextureHolder bitmapTexture;

    @Override
    public void onDraw() {
        textTexture.draw(100, 50);

    }

    @Override
    public void initDrawable() {
        Log.d("LoadingScene", "initTexture");

        textTexture = new StringTextureHolder(myView.mRenderer, "Loading", 64, Typeface.DEFAULT, 128, 0, 255, 0);

    }

    public LoadingScene(BaseGLSurfaceView pView) {
        super(pView);
    }
}
