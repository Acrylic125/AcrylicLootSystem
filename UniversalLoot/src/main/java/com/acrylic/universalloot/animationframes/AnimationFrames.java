package com.acrylic.universalloot.animationframes;

import com.acrylic.universal.animations.IndexedAnimation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> The animation.
 * @deprecated This will be moved to the AcrylicMinecraftLib in
 *             the future.
 */
@Deprecated
public final class AnimationFrames<T extends IndexedAnimation> {

    private final List<AnimationFrame<T>> animationFrames = new ArrayList<>();

    public AnimationFrames<T> addFrame(@NotNull AnimationFrame<T> frame) {
        this.animationFrames.add(frame);
        return this;
    }

    @NotNull
    public List<AnimationFrame<T>> getAnimationFrames() {
        return animationFrames;
    }

    public void handle(@NotNull T animation) {
        int index = animation.getIndex();
        for (AnimationFrame<T> animationFrame : animationFrames) {
            if (animationFrame.getInitialIndex() < index && animationFrame.getMaxIndex() >= index)
                animationFrame.getAction().applyTo(animation, animationFrame);
        }
    }

}
