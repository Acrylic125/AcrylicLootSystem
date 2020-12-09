package com.acrylic.acrylicloot;

import com.acrylic.candy.CandyAnimator;
import com.acrylic.candy.CandyCondensedCrate;
import com.acrylic.candy.CandyLootCrate;
import com.acrylic.candy.CandyLootItem;
import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.renderer.PredicateRenderer;
import com.acrylic.universal.shapes.lines.BaseLine;
import com.acrylic.universal.shapes.lines.QuadraticYLine;
import com.acrylic.universalloot.PlayerRenderer;
import com.acrylic.universalloot.preview.SimpleLootPreview;
import com.acrylic.version_1_8.EntityRegistry;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcrylicLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Universal.setPlugin(this);
        UniversalNMS.setEntityRegistry(new EntityRegistry());
        CandyCondensedCrate candyLootCrates = new CandyCondensedCrate();
        for (int i = 1; i <= 5; i++) {
            CandyLootCrate lootCrate = new CandyLootCrate("Crate-" + i, ItemBuilder.of(Material.CHEST).name("&" + i + "&lCrate Tier " + i).build());
            candyLootCrates.addMenuRefToPreview(lootCrate, ItemBuilder.of(Material.ARROW).name("&eBack to Menu").build(), 1);
            int id = 0;
            for (int j = 0; j < ProbabilityKt.getRandom(25,100); j++) {
                id++;
                float weight = ProbabilityKt.getRandom(0.001f,200f);
                lootCrate.getLootTable().addItem(new CandyLootItem(weight, ItemBuilder.of(Material.DIAMOND).name("ID: " + i + " " + id).lore("&7Weight: " + weight).build()));
            }
            candyLootCrates.addLootAndButton(lootCrate, (i * 3) + 6);
        }
        Universal.setPlugin(this);
        SimpleLootPreview.COMMON_INSTANCE.getGUI().clickListener(EventBuilder.listen(InventoryClickEvent.class).handle(event -> {
            event.setCancelled(true);
        }));
        CommandBuilder.create("loot")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    candyLootCrates.open(sender);
        }).register();
        CommandBuilder.create("candy")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(abstractCommandExecuted -> {
                    Player sender = (Player) abstractCommandExecuted.getSender();
                    CandyAnimator candyAnimator = new CandyAnimator(new PlayerRenderer(sender), sender.getLocation(), 10);
                }).register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
