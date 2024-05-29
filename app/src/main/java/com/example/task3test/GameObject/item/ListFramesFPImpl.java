package com.example.task3test.GameObject.item;

import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public abstract class ListFramesFPImpl extends AbstractFramesProviderFromBitmap{
    private List<Rect> rects = new ArrayList<>();
    public ListFramesFPImpl(Bitmap textures, Rect first) {
        super(textures, first.width(), first.height());
        rects = createFrames(first);
    }

    protected abstract List<Rect> createFrames(Rect first);

    @Override
    protected Rect getFrameRect(int n) {
        return rects.get(n);
    }

    @Override
    public int framesAmount() {
        return rects.size();
    }

}
