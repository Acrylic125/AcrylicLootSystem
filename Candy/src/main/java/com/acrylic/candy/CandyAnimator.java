package com.acrylic.candy;

import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.Animation;
import com.acrylic.universal.renderer.PacketRenderer;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CandyAnimator extends Animation {

    private final List<CandyAnimation> animations = new ArrayList<>();

    public CandyAnimator(@NotNull PacketRenderer packetRenderer,@NotNull Location location, int amount) {
        this(packetRenderer, location, amount, 20, 30, 2, 6);
    }

    public CandyAnimator(@NotNull PacketRenderer packetRenderer, @NotNull Location location, int amount, int lastingDurationMin, int lastingDurationMax, float minDistance, float maxDistance) {
        for (int i = 0; i < amount; i++)
            animations.add(new CandyAnimation_1_8(packetRenderer, location, ProbabilityKt.getRandom(lastingDurationMin, lastingDurationMax), minDistance, maxDistance));
        run();
    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (animations.isEmpty()) {
                    cancel();
                } else {
                    for (Iterator<CandyAnimation> iterator = animations.iterator(); iterator.hasNext();) {
                        CandyAnimation candyAnimation = iterator.next();
                        candyAnimation.update();
                        if (candyAnimation.hasEnded()) {
                            candyAnimation.delete();
                            iterator.remove();
                        }
                    }
                }
            }
        }.runTaskTimer(Universal.getPlugin(), 1, 1);
    }

    @Override
    public void delete() {
        for (CandyAnimation animation : animations)
            animation.delete();
    }
}
