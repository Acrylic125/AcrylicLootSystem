package com.acrylic.universalloot.pseudoloot;

import com.acrylic.universal.gui.PrivateGUIBuilder;
import com.acrylic.universalloot.GUILoot;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface GUIPseudoLoot<T extends GUILoot> extends PseudoLoot<T> {

    @NotNull
    PrivateGUIBuilder getGUI();

    default void open(@NotNull Player player) {
        getGUI().open(player);
    }

}
