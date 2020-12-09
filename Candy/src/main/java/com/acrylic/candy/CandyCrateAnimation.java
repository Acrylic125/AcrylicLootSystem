package com.acrylic.candy;

import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.IndexedAnimation;
import com.acrylic.universal.renderer.PlayerRenderer;
import com.acrylic.universalloot.animationframes.AnimationFrame;
import com.acrylic.universalloot.animationframes.AnimationFrames;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.entityanimator.NMSArmorStandAnimator;
import com.acrylic.version_1_8.items.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public abstract class CandyCrateAnimation extends IndexedAnimation {

    private static final AnimationFrames<CandyCrateAnimation> animations = new AnimationFrames<CandyCrateAnimation>()
            .addFrame(new AnimationFrame<>(0, 10, (animation, frame) -> animation.addToLocation(0, animation.calculateDeltaAccelerate(frame.getMaxIndex(), 0.1f, 4.5f, frame.getMaxIndex() - frame.getInitialIndex()), 0)))
            .addFrame(new AnimationFrame<>(10, 20, (animation, frame) -> {
                animation.addToLocation(0, animation.calculateDeltaDecelerate(frame.getMaxIndex(), -0.1f, -4.5f, frame.getMaxIndex() - frame.getInitialIndex()), 0);
                animation.playChestLand1(frame);
            }))
            .addFrame(new AnimationFrame<>(20, 40, (animation, frame) -> {
                animation.playChestShake();
                animation.handleHeadShift(frame.getInitialIndex(), (frame.getMaxIndex() - frame.getInitialIndex()) / 4, 0.15f);
            }))
            .addFrame(new AnimationFrame<>(40, 47, (animation, frame) -> animation.addToLocation(0, animation.calculateDeltaAccelerate(frame.getMaxIndex(), 0.05f, 2.5f, frame.getMaxIndex() - frame.getInitialIndex()), 0)))
            .addFrame(new AnimationFrame<>(47, 54, (animation, frame) -> {
                animation.addToLocation(0, animation.calculateDeltaDecelerate(frame.getMaxIndex(), -0.05f, -2.5f, frame.getMaxIndex() - frame.getInitialIndex()), 0);
                animation.playChestLand1(frame);
            }))
            .addFrame(new AnimationFrame<>(58, 74, (animation, frame) -> {
                animation.playChestShake();
                animation.handleHeadShift(58, (frame.getMaxIndex() - frame.getInitialIndex()) / 4, 0.15f);
            }))
            .addFrame(new AnimationFrame<>(90, 140, (animation, frame) -> {
                animation.playChestShakeAggressive(frame);
                int index = animation.getIndex();
                int iIndex = frame.getInitialIndex();
                float f = (float) Math.floor((index - iIndex) / 4f);
                int a = iIndex + 4 * (int) f;
                animation.addToLocation(0, animation.handleShake(a, (0.1f + (0.01f * f * f))), 0);
                if (index == 140)
                    animation.setHeadPose(EulerAngle.ZERO);
            }))
            .addFrame(new AnimationFrame<>(140, 145, (animation, frame) -> animation.addToLocation(0, animation.calculateDeltaAccelerate(frame.getMaxIndex(), 0.2f, 7, frame.getMaxIndex() - frame.getInitialIndex()), 0)))
            .addFrame(new AnimationFrame<>(155, 160, (animation, frame) -> {
                animation.addToLocation(0, animation.calculateDeltaDecelerate(frame.getMaxIndex(), -0.2f, -7, frame.getMaxIndex() - frame.getInitialIndex()), 0);
                animation.playChestLand1(frame);
            }))
            ;

    private boolean hasEnded = false;
    private final NMSArmorStandAnimator animator;
    private final CandyCrateProcess candyCrateProcess;

    public CandyCrateAnimation(@NotNull CandyCrateProcess candyCrateProcess, @NotNull NMSArmorStandAnimator animator, @NotNull Player player) {
        this.candyCrateProcess = candyCrateProcess;
        this.animator = animator;
        animator.asAnimator();
        animator.setEquipment(new EntityEquipmentBuilder().setHelmet(ItemBuilder.of(Material.CHEST).build()));
        PlayerRenderer renderer = new PlayerRenderer(player);
        animator.setRenderer(renderer);
        animator.teleport(animator.getLocation().add(0, -1.4, 0));
        animator.show();
        run();
    }

    public CandyCrateProcess getCandyCrateProcess() {
        return candyCrateProcess;
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasEnded())
                    cancel();
                else
                    update();
            }
        }.runTaskTimer(Universal.getPlugin(), 1, 1);
    }

    public void update() {
        increaseIndex();
        endCheck();
        if (!hasEnded())
            animations.handle(this);
    }

    private void setHeadPose(@NotNull EulerAngle eulerAngle) {
        animator.headPose(eulerAngle);
        animator.sendPacketsViaRenderer(animator.getDisplayPackets().getEntityMetaDataPacket());
    }

    /**
     *
     * @param min The minimum delta.
     * @param y The y offset.
     * @param ticks The amount of ticks.
     * @return The acceleration.
     */
    private float calculateDelta(float min, float y, int ticks) {
        return (((2 * y) / ticks) - (2 * min)) / ticks;
    }

    private float calculateDeltaAccelerate(int toIndex, float baseV, float y, int ticks) {
        return (toIndex - getIndex()) * (calculateDelta(baseV, y, ticks));
    }

    private float calculateDeltaDecelerate(int toIndex, float baseV, float y, int ticks) {
        return (ticks - (toIndex - getIndex()) - 1) * (calculateDelta(baseV, y, ticks));
    }

    private void addToLocation(double x, double y, double z) {
        Location location = animator.getBukkitEntity().getLocation();
        location.add(x, y, z);
        animator.teleport(location);
    }

    /**
     * First, it will shift -radians z for indexToNextShift ticks.
     * Then, it will shift +radians z for 2*indexToNextShift ticks.
     * Finally, it will shift -radians z for indexToNextShift ticks.
     *
     * @param fromIndex The initial index.
     * @param indexToNextShift The amount of ticks to alternate shift.
     * @param radians The degrees to shift by every tick.
     */
    private void handleHeadShift(int fromIndex, int indexToNextShift, float radians) {
        int index = getIndex();
        float shiftFactor = (index > (fromIndex + indexToNextShift) && index <= (fromIndex + (3 * indexToNextShift))) ? 1 : -1;
        setHeadPose(animator.getBukkitEntity().getHeadPose().add(0, 0, shiftFactor * radians));
    }

    private float handleShake(int fromTick, float power) {
        int index = getIndex();
        if (fromTick % 2 != 0 || index % 2 != 0) {
            setHeadPose(EulerAngle.ZERO);
            return 0;
        } else {
            float factor = (index == fromTick + 2) ? 1 : -1;
            if (power > 0.4)
                power = 0.4f;
            setHeadPose(new EulerAngle(0, 0, factor * power));
            return (factor * power * 1f);
        }
    }

    private void playChestLand1(@NotNull AnimationFrame<CandyCrateAnimation> frame) {
        int index = getIndex();
        Player player = getCandyCrateProcess().getPlayer();
        if (index == frame.getMaxIndex()) {
            player.playSound(player.getLocation(), Sound.ZOMBIE_WOODBREAK, 1f, 1.2f);
        }
    }

    private void playChestShake() {
        int index = getIndex();
        Player player = getCandyCrateProcess().getPlayer();
        if (index % 3 == 0)
            player.playSound(player.getLocation(), Sound.ZOMBIE_WOOD, 1f, 0.2f);
    }

    private void playChestShakeAggressive(@NotNull AnimationFrame<CandyCrateAnimation> frame) {
        int index = getIndex();
        Player player = getCandyCrateProcess().getPlayer();
        if (index % 3 == 0) {
            float pitch = (getIndex() - frame.getInitialIndex()) / (float) frame.getMaxIndex();
            player.playSound(player.getLocation(), Sound.ZOMBIE_WOOD, 1f, 0.2f + (pitch * 1.6f));
        }
        if (index == frame.getMaxIndex()) {
            player.playSound(player.getLocation(), Sound.WITHER_SHOOT, 1f, 0.6f);
        }
    }

    @Override
    public void endCheck() {
        if (!hasEnded && getIndex() > 165) {
            hasEnded = true;
            delete();
        }
    }

    @Override
    public boolean hasEnded() {
        return hasEnded;
    }

    @Override
    public void delete() {
        animator.delete();
    }
}
