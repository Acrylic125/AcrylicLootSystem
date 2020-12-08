package com.acrylic.candy;

import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.templates.GUITemplate;
import com.acrylic.universalloot.condensedcrate.CondensedCrate;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class CandyCondensedCrate extends CondensedCrate<CandyLootCrate> {

    public CandyCondensedCrate() {
        this(PrivateGUIBuilder.create(
                InventoryBuilder.create()
                .title("&5&lCandy&d&lCrate")
                .rows(3)
                )
                .template(new GUITemplate())
                .clickListener(EventBuilder
                        .listen(InventoryClickEvent.class)
                        .handle(event -> {
                            event.setCancelled(true);
                        })
                )
        );
    }

    public CandyCondensedCrate(@NotNull PrivateGUIBuilder gui) {
        super(gui);
    }
}
