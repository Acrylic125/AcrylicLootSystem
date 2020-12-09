package com.acrylic.candy;

import com.acrylic.universal.animations.IndexedAnimation;

public class CandyAnimation extends IndexedAnimation {
    @Override
    public void endCheck() {

    }

    @Override
    public boolean hasEnded() {
        return getIndex();
    }

    @Override
    public void delete() {

    }
}
