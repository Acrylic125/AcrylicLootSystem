package com.acrylic.acrylicloot;

import com.acrylic.universal.Universal;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcrylicLoot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Universal.setPlugin(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
