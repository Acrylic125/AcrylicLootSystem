package com.acrylic.universalloot.lootopener;

import com.acrylic.universal.animations.Animation;
import com.acrylic.universalloot.Loot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface LootProcess {

    @NotNull
    List<ItemStack> getRewards();

    @NotNull
    Player getPlayer();

    @Nullable
    Animation getAnimation();

    @NotNull
    Loot getLoot();

    default void reward() {
        Inventory inventory = getPlayer().getInventory();
        for (ItemStack reward : getRewards())
            inventory.addItem(reward);
    }

}
