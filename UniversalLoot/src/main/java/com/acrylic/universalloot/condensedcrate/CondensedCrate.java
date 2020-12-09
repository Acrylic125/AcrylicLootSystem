package com.acrylic.universalloot.condensedcrate;

import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.buttons.Button;
import com.acrylic.universalloot.GUILoot;
import com.acrylic.universalloot.Loot;
import com.acrylic.universalloot.preview.LootPreview;
import com.acrylic.universalloot.pseudoloot.GUIPseudoLoot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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

    public void addMenuRefToPreview(@NotNull Loot loot, @NotNull ItemStack item, int slot) {
        LootPreview lootPreview = loot.getPreview();
        if (lootPreview != null)
            addMenuRefToPreview(lootPreview, item, slot);
    }

    /**
     * Adds a button of item, item in slot, slot to the loot preview, lootPreview.
     *
     * @param lootPreview THe preview you want to add the button to.
     * @param item The display item.
     * @param slot The slot.
     */
    public void addMenuRefToPreview(@NotNull LootPreview lootPreview, @NotNull ItemStack item, int slot) {
        lootPreview.getGUI().getButtons()
                .addItem(new Button(slot, item, (itemStack, inventoryClickEvent, abstractGUIBuilder) -> {
                    Player player = (Player) inventoryClickEvent.getView().getPlayer();
                    open(player);
                }));
    }

    public void addLootButton(@NotNull T loot, int slot) {
        gui.getButtons()
                .addItem(new Button(slot, loot.getDisplayItem(), (itemStack, inventoryClickEvent, abstractGUIBuilder) -> {
                    Player player = (Player) inventoryClickEvent.getView().getPlayer();
                    if (inventoryClickEvent.getClick().equals(ClickType.RIGHT) && loot.openPreview(player))
                        return;
                    loot.getLootOpener().open(player);
                }));
    }

    public void addLootAndButton(@NotNull T loot, int slot) {
        addLoot(loot);
        addLootButton(loot, slot);
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
