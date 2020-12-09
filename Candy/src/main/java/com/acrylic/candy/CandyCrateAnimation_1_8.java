package com.acrylic.candy;

import com.acrylic.version_1_8.entityanimator.NMSArmorStandAnimator;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CandyCrateAnimation_1_8 extends CandyCrateAnimation {

    public CandyCrateAnimation_1_8(@NotNull CandyCrateProcess candyCrateProcess, @NotNull Location location, @NotNull Player player) {
        super(candyCrateProcess, new NMSArmorStandAnimator(location), player);
    }

}
