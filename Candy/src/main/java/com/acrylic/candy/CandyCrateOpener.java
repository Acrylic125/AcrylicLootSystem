package com.acrylic.candy;

import com.acrylic.universalloot.Loot;
import com.acrylic.universalloot.lootopener.LootOpener;
import com.acrylic.universalloot.lootopener.LootProcess;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CandyCrateOpener implements LootOpener {

    private final CandyLootCrate loot;

    public CandyCrateOpener(@NotNull CandyLootCrate loot) {
        this.loot = loot;
    }

    @NotNull
    @Override
    public CandyLootCrate getLoot() {
        return loot;
    }

    @Override
    public boolean canOpen(@NotNull Player player) {
        return true;
    }

    @Override
    public void open(@NotNull Player player) {

    }

    @Nullable
    @Override
    public LootProcess generateLootProcess(@NotNull Player player) {
        return new CandyCrateProcess(player, loot);
    }
}
