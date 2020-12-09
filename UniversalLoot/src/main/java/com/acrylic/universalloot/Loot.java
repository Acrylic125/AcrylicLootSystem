package com.acrylic.universalloot;

import com.acrylic.universalloot.lootopener.LootOpener;
import com.acrylic.universalloot.loottable.LootTable;
import com.acrylic.universalloot.preview.LootPreview;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Loot {

    @NotNull
    LootTable getLootTable();

    @NotNull
    LootOpener getLootOpener();

    @Nullable
    LootPreview getPreview();

    @NotNull
    String getName();

    int getAmountOfRewards();

    /**
     *
     * @param player The viewer.
     * @return Returns true if there is a loot preview.
     */
    default boolean openPreview(@NotNull Player player) {
        LootPreview lootPreview = getPreview();
        if (lootPreview == null)
            return false;
        lootPreview.apply(this);
        lootPreview.open(player);
        return true;
    }

}
