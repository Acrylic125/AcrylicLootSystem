package com.acrylic.universalloot;

import com.acrylic.universalloot.lootopener.LootOpener;
import com.acrylic.universalloot.loottable.LootTable;
import com.acrylic.universalloot.preview.LootPreview;
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

}
