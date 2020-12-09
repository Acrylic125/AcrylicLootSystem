package com.acrylic.universalloot;

import com.acrylic.universal.packets.PacketSender;
import com.acrylic.universal.renderer.PacketRenderer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PlayerRenderer implements PacketRenderer {

    private final Player player;

    public PlayerRenderer(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public void send(PacketSender packetSender) {
        packetSender.send(player);
    }

    @Override
    public void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> consumer) {
        packetSender.sendWithAction(player, consumer);
    }
}
