package com.acrylic.universalloot.lootopener;

import com.acrylic.universalloot.Loot;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LootOpener {

    @NotNull
    Loot getLoot();

    boolean canOpen(@NotNull Player player);

    void open(@NotNull Player player);

    @Nullable
    LootProcess generateLootProcess(@NotNull Player player);

}
