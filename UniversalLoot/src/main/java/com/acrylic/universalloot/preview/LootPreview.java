package com.acrylic.universalloot.preview;

import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.MiddleGUITemplate;
import com.acrylic.universalloot.Loot;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public interface LootPreview {

    @NotNull
    PrivateGUIBuilder getGUI();

    void apply(@NotNull Loot loot);

}
