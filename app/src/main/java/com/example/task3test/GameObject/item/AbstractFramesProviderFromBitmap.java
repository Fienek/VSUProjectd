package com.example.task3test.GameObject.item;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class AbstractFramesProviderFromBitmap implements FramesProvider {
    protected Bitmap textures;
    private int frameWidth;
    private int frameHeight;

    public AbstractFramesProviderFromBitmap(Bitmap textures, int frameWidth, int frameHeight) {
        this.textures = textures;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    protected abstract Rect getFrameRect(int n);

    private static final Paint p = new Paint();
    @Override
    public void drawFrame(int n, Canvas c, int x, int y) {
        c.drawBitmap(textures, getFrameRect(n),
                new Rect(x, y, (int) x + getFrameWidth(), (int)y + getFrameHeight()), p);
    }
}
