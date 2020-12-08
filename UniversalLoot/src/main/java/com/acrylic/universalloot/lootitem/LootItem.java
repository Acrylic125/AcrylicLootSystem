package com.acrylic.universalloot.lootitem;

import com.acrylic.weights.WeightObject;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface LootItem extends WeightObject {

    @NotNull
    ItemStack getItem();

}
