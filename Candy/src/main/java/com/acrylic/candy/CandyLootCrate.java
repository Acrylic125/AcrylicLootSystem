package com.acrylic.candy;

import com.acrylic.universalloot.GUILoot;
import com.acrylic.universalloot.loottable.SimpleLootTable;
import com.acrylic.universalloot.preview.LootPreview;
import com.acrylic.universalloot.preview.SimpleLootPreview;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CandyLootCrate implements GUILoot {

    private final CandyCrateOpener opener = new CandyCrateOpener(this);
    private final SimpleLootTable lootTable = new SimpleLootTable();
    private final String name;
    private final ItemStack display;
    private int amountOfItems = 1;

    public CandyLootCrate(@NotNull String name, @NotNull ItemStack display) {
        this.name = name;
        this.display = display;
    }

    @Override
    public ItemStack getDisplayItem() {
        return display;
    }

    @NotNull
    @Override
    public SimpleLootTable getLootTable() {
        return lootTable;
    }

    @NotNull
    @Override
    public CandyCrateOpener getLootOpener() {
        return opener;
    }

    @Nullable
    @Override
    public LootPreview getPreview() {
        return SimpleLootPreview.COMMON_INSTANCE;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    public void setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
    }

    @Override
    public int getAmountOfRewards() {
        return amountOfItems;
    }
}
