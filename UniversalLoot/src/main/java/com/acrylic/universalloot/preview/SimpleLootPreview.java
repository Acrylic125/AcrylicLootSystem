package com.acrylic.universalloot.preview;

import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.buttons.Button;
import com.acrylic.universal.gui.buttons.PageButton;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.MiddleGUITemplate;
import com.acrylic.universalloot.Loot;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleLootPreview implements LootPreview {

    public final static SimpleLootPreview COMMON_INSTANCE;

    static {
        PaginatedGUI ui = (PaginatedGUI) PaginatedGUI
                .create(InventoryBuilder.create().title("Loot Preview").rows(6))
                .template(new MiddleGUITemplate(2, 5))
                .clickListener(EventBuilder.listen(InventoryClickEvent.class).handle(event -> {
                    event.setCancelled(true);
                }));
        ui.getButtons().addItem(new PageButton(0, ItemBuilder.of(Material.REDSTONE_BLOCK).name("&c&lLast Page <<< {PAGE}").lore("&7Page &f{PAGE} &7/ &8{MAX_PAGE}").build()));
        ui.getButtons().addItem(new PageButton(8, ItemBuilder.of(Material.EMERALD_BLOCK).name("&a&lNext Page >>> {PAGE}").lore("&7Page &f{PAGE} &7/ &8{MAX_PAGE}").build()));
        COMMON_INSTANCE = new SimpleLootPreview(ui);
    }

    private final PaginatedGUI gui;

    public SimpleLootPreview(PaginatedGUI gui) {
        this.gui = gui;
    }

    @NotNull
    @Override
    public PaginatedGUI getGUI() {
        return gui;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void apply(@NotNull Loot loot) {
        ArrayList<ItemStack> items = gui.getTemplate().getSubCollection();
        items.clear();
        items.addAll((Collection<? extends ItemStack>) loot.getLootTable().getContents());
    }
}
