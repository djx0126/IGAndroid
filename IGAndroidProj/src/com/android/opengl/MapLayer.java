package com.android.opengl;

import com.android.game.IG.Map;
import com.android.object.drawable.IDrawable;

public class MapLayer extends BaseLayer {
    protected IDrawable[][] map;
    public int mapWidth;
    public int mapHeight;

    public MapLayer(BaseGLSurfaceView pView) {
        super(pView);

    }

    public void initMap() {
        int[][] mapRaw = Map.map;
        mapWidth = mapRaw[0].length;
        mapHeight = mapRaw.length;

    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertDrawable(IDrawable drawableObj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearDrawable() {
        // TODO Auto-generated method stub

    }

}
