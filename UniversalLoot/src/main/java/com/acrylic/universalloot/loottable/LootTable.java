package com.acrylic.universalloot.loottable;

import com.acrylic.universalloot.lootitem.LootItem;
import com.acrylic.weights.AbstractWeigher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface LootTable extends Iterable<LootItem> {

    AbstractWeigher<LootItem> getWeigher();

    default LootItem generateReward() {
        return getWeigher().get();
    }

    default LootItem[] generateRewards(int amount) {
        LootItem[] rewards = new LootItem[amount];
        for (int i = 0; i < amount; i++)
            rewards[i] = generateReward();
        return rewards;
    }

    default ArrayList<LootItem> getContents() {
        return getWeigher().list();
    }

    default void addItem(LootItem crateItem) {
        getWeigher().add(crateItem);
    }

    default void removeItem(LootItem crateItem) {
        getWeigher().remove(crateItem);
    }

    @NotNull
    @Override
    default Iterator<LootItem> iterator() {
        return getContents().iterator();
    }

    @Override
    default void forEach(Consumer<? super LootItem> action) {
        getContents().forEach(action);
    }

    @Override
    default Spliterator<LootItem> spliterator() {
        return getContents().spliterator();
    }
}
