package com.acrylic.universalloot.loottable;

import com.acrylic.universalloot.lootitem.LootItem;
import com.acrylic.weights.AbstractWeigher;
import com.acrylic.weights.Weigher;

public class SimpleLootTable implements LootTable {

    private final AbstractWeigher<LootItem> weigher = new Weigher<>();

    @Override
    public AbstractWeigher<LootItem> getWeigher() {
        return weigher;
    }
}
