/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1802;

public class AutoWeb
extends Module {
    private class_1657 target;
    private final Setting<Boolean> doubles;
    private final Setting<Boolean> rotate;
    private final Setting<SortPriority> priority;
    private final SettingGroup sgGeneral;
    private final Setting<Double> range;

    public AutoWeb() {
        super(Categories.Combat, "auto-web", "Automatically places webs on other players.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The maximum distance to target players.").defaultValue(4.0).min(0.0).build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestDistance).build());
        this.doubles = this.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Places webs in the target's upper hitbox as well as the lower hitbox.").defaultValue(false).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards the webs when placing.").defaultValue(true).build());
        this.target = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (TargetUtils.isBadTarget(this.target, this.range.get())) {
            this.target = TargetUtils.getPlayerTarget(this.range.get(), this.priority.get());
        }
        if (TargetUtils.isBadTarget(this.target, this.range.get())) {
            return;
        }
        BlockUtils.place(this.target.method_24515(), InvUtils.findInHotbar(class_1802.field_8786), this.rotate.get(), 0, false);
        if (this.doubles.get().booleanValue()) {
            BlockUtils.place(this.target.method_24515().method_10069(0, 1, 0), InvUtils.findInHotbar(class_1802.field_8786), this.rotate.get(), 0, false);
        }
    }
}

