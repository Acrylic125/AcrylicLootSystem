package com.acrylic.candy;

import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.animations.Animation;
import com.acrylic.universal.animations.IndexedAnimation;
import com.acrylic.universal.renderer.PacketRenderer;
import com.acrylic.universal.shapes.lines.QuadraticYLine;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.entityanimator.NMSArmorStandAnimator;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CandyAnimation extends Animation {

    private final NMSArmorStandAnimator animator;
    private final int lastingIndex;
    private final QuadraticYLine line;
    private final Location location;

    public CandyAnimation(@NotNull PacketRenderer packetRenderer, @NotNull NMSArmorStandAnimator animator, int lastingIndex, float minDistance, float maxDistance) {
        this(packetRenderer, animator, ItemBuilder.of(Material.WOOL).damage((short) ProbabilityKt.getRandom(0,14)).build(), lastingIndex, minDistance, maxDistance);
    }

    public CandyAnimation(@NotNull PacketRenderer packetRenderer, @NotNull NMSArmorStandAnimator animator, @NotNull ItemStack item, int lastingIndex, float minDistance, float maxDistance) {
        animator.small(true).asAnimator().setEquipment(new EntityEquipmentBuilder().setHelmet(item));
        animator.setRenderer(packetRenderer);
        animator.show();
        this.animator = animator;
        this.lastingIndex = lastingIndex;
        this.location = animator.getLocation();
        line = new QuadraticYLine(location, 4);
        line.setScalar(-0.4f);
        line.setTo(animator.getLocation().add(ProbabilityKt.getPositiveNegativeRandom(minDistance, maxDistance), 0, ProbabilityKt.getPositiveNegativeRandom(minDistance, maxDistance)));
        line.calculate();
    }

    public void update() {
        animator.teleport(line.getLocation(location));
    }

    public boolean hasEnded() {
        return line.getIndex() > lastingIndex;
    }

    @Override
    public void delete() {
        animator.delete();
    }
}
