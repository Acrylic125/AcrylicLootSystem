package com.acrylic.candy;

import com.acrylic.universal.renderer.PacketRenderer;
import com.acrylic.version_1_8.entityanimator.NMSArmorStandAnimator;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CandyAnimation_1_8 extends CandyAnimation {

    public CandyAnimation_1_8(@NotNull PacketRenderer packetRenderer, @NotNull Location location, int lastingIndex, float minDistance, float maxDistance) {
        super(packetRenderer, new NMSArmorStandAnimator(location), lastingIndex, minDistance, maxDistance);
    }

    public CandyAnimation_1_8(@NotNull PacketRenderer packetRenderer,@NotNull Location location, @NotNull ItemStack item, int lastingIndex, float minDistance, float maxDistance) {
        super(packetRenderer, new NMSArmorStandAnimator(location), item, lastingIndex, minDistance, maxDistance);
    }
}
