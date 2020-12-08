package com.acrylic.candy;

import com.acrylic.universal.animations.Animation;
import com.acrylic.universalloot.Loot;
import com.acrylic.universalloot.lootitem.LootItem;
import com.acrylic.universalloot.lootopener.LootProcess;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CandyCrateProcess implements LootProcess {

    private final Player player;
    private final Loot loot;
    private final List<ItemStack> rewards;

    public CandyCrateProcess(@NotNull Player player, @NotNull Loot loot) {
        this.player = player;
        this.loot = loot;
        this.rewards = new ArrayList<>();
        for (LootItem lootItem : loot.getLootTable().generateRewards(loot.getAmountOfRewards()))
            this.rewards.add(lootItem.getItem());
    }

    @NotNull
    @Override
    public List<ItemStack> getRewards() {
        return rewards;
    }

    @NotNull
    @Override
    public Player getPlayer() {
        return player;
    }

    @Nullable
    @Override
    public Animation getAnimation() {
        return null;
    }

    @NotNull
    @Override
    public Loot getLoot() {
        return loot;
    }
}
