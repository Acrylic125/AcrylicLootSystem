package com.acrylic.universalloot.lootitem;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SimpleLootItem implements LootItem {

    private final float weight;
    private final ItemStack item;

    public SimpleLootItem(float weight, ItemStack item) {
        this.weight = weight;
        this.item = item;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @NotNull
    @Override
    public ItemStack getItem() {
        return item;
    }

}
