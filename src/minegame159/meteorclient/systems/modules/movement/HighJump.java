/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.JumpVelocityMultiplierEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class HighJump
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Double> multiplier;

    public HighJump() {
        super(Categories.Movement, "high-jump", "Makes you jump higher than normal.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.multiplier = this.sgGeneral.add(new DoubleSetting.Builder().name("jump-multiplier").description("Jump height multiplier.").defaultValue(1.0).min(0.0).build());
    }

    @EventHandler
    private void onJumpVelocityMultiplier(JumpVelocityMultiplierEvent jumpVelocityMultiplierEvent) {
        jumpVelocityMultiplierEvent.multiplier = (float)((double)jumpVelocityMultiplierEvent.multiplier * this.multiplier.get());
    }
}

