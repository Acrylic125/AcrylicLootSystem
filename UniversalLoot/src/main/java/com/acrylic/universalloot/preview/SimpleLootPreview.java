package com.acrylic.universalloot.preview;

import com.acrylic.universal.gui.InventoryBuilder;
import com.acrylic.universal.gui.buttons.PageButton;
import com.acrylic.universal.gui.paginated.PaginatedGUI;
import com.acrylic.universal.gui.templates.MiddleGUITemplate;
import com.acrylic.universalloot.Loot;
import com.acrylic.universalloot.lootitem.LootItem;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SimpleLootPreview implements LootPreview {

    public final static SimpleLootPreview COMMON_INSTANCE;

    static {
        PaginatedGUI ui = (PaginatedGUI) new PaginatedGUI(InventoryBuilder.create().title("Loot Preview").rows(6))
                .template(new MiddleGUITemplate(2, 5));
        ui.getButtons().addItem(new PageButton(0, ItemBuilder.of(Material.REDSTONE_BLOCK).name("&c&lLast Page <<< {PAGE}").lore("&7Page &f{PAGE} &7/ &8{LAST_PAGE}").build(), -1));
        ui.getButtons().addItem(new PageButton(8, ItemBuilder.of(Material.EMERALD_BLOCK).name("&a&lNext Page >>> {PAGE}").lore("&7Page &f{PAGE} &7/ &8{LAST_PAGE}").build(), 1));
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
        for (LootItem content : loot.getLootTable().getContents()) {
            items.add(content.getItem());
        }
    }
}
