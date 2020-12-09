package com.acrylic.universalloot.animationframes;

import com.acrylic.universal.animations.IndexedAnimation;
import org.jetbrains.annotations.NotNull;

/**
 * A part of the animation that will be acted out
 * from the initial index to the maximum index.
 *
 * @param <T> The animation.
 * @deprecated This will be moved to the AcrylicMinecraftLib in
 *             the future.
 */
@Deprecated
public final class AnimationFrame<T extends IndexedAnimation> {

    private final AnimationFrameAction<T> action;
    private final int initialIndex;
    private final int maxIndex;

    public AnimationFrame(int initialIndex, int maxIndex, @NotNull AnimationFrameAction<T> action) {
        this.initialIndex = initialIndex;
        this.maxIndex = maxIndex;
        this.action = action;
    }

    public AnimationFrameAction<T> getAction() {
        return action;
    }

    public int getInitialIndex() {
        return initialIndex;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

}
