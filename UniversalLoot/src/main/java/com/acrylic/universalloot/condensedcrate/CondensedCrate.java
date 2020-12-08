package com.acrylic.universalloot.condensedcrate;

import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.buttons.Button;
import com.acrylic.universalloot.GUILoot;
import com.acrylic.universalloot.Loot;
import com.acrylic.universalloot.pseudoloot.GUIPseudoLoot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CondensedCrate<T extends GUILoot> implements GUIPseudoLoot<T> {

    private final List<T> loot = new ArrayList<>();
    private final PrivateGUIBuilder gui;

    public CondensedCrate(@NotNull PrivateGUIBuilder gui) {
        this.gui = gui;
    }

    public void addLootButton(@NotNull Loot loot, int slot, @NotNull ItemStack item) {
        gui.getButtons()
                .addItem(new Button(slot, item, (itemStack, inventoryClickEvent, abstractGUIBuilder) -> {

                }));
    }

    @NotNull
    @Override
    public PrivateGUIBuilder getGUI() {
        return gui;
    }

    @Override
    public List<T> getLoots() {
        return loot;
    }
}
