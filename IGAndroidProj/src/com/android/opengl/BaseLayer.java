package com.android.opengl;

import java.util.PriorityQueue;

import com.android.object.drawable.IDrawable;

public class BaseLayer implements Comparable<BaseLayer> {
    protected int priority = 0;
    protected BaseGLSurfaceView myView;
    protected PriorityQueue<IDrawable> drawableList;// smaller pri will be out
                                                    // first

    public BaseLayer(BaseGLSurfaceView pView) {
        myView = pView;
        drawableList = new PriorityQueue<IDrawable>();

    }

    public void insertDrawable(IDrawable drawableObj) {
        drawableList.add(drawableObj);
    }

    public int compareTo(BaseLayer layerB) {
        if (this.priority > layerB.priority) {
            return 1;
        } else if (this.priority < layerB.priority) {
            return -1;
        }
        return 0;
    }

}
