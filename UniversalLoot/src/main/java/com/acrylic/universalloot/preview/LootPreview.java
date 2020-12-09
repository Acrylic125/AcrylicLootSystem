package com.acrylic.universalloot.preview;

import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universalloot.Loot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface LootPreview {

    @NotNull
    PrivateGUIBuilder getGUI();

    void apply(@NotNull Loot loot);

    default void open(@NotNull Player player) {
        Bukkit.broadcastMessage(getGUI() + "");
        getGUI().open(player);
    }

}
