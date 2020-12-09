package com.acrylic.universalloot.animationframes;

import com.acrylic.universal.animations.Animation;
import com.acrylic.universal.animations.IndexedAnimation;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> The animation.
 * @deprecated This will be moved to the AcrylicMinecraftLib in
 *             the future.
 */
@Deprecated
@FunctionalInterface
public interface AnimationFrameAction<T extends IndexedAnimation> {

    void applyTo(T animation, @NotNull AnimationFrame<T> frame);

}
