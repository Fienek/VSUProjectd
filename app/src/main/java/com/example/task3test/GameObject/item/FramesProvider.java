package com.example.task3test.GameObject.item;

import android.graphics.Canvas;

public interface FramesProvider {
    int framesAmount();
    void drawFrame(int n, Canvas c, int x, int y);
    public int getFrameWidth();
    public int getFrameHeight();
}
