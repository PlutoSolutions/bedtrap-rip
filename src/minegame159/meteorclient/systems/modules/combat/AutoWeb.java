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
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<Boolean> doubles;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<SortPriority> priority;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> range;

    public AutoWeb() {
        super(Categories.Combat, "auto-web", "Automatically places webs on other players.");
        AutoWeb llllllllllllllllIlIllllIIIIIlIII;
        llllllllllllllllIlIllllIIIIIlIII.sgGeneral = llllllllllllllllIlIllllIIIIIlIII.settings.getDefaultGroup();
        llllllllllllllllIlIllllIIIIIlIII.range = llllllllllllllllIlIllllIIIIIlIII.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The maximum distance to target players.").defaultValue(4.0).min(0.0).build());
        llllllllllllllllIlIllllIIIIIlIII.priority = llllllllllllllllIlIllllIIIIIlIII.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestDistance).build());
        llllllllllllllllIlIllllIIIIIlIII.doubles = llllllllllllllllIlIllllIIIIIlIII.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Places webs in the target's upper hitbox as well as the lower hitbox.").defaultValue(false).build());
        llllllllllllllllIlIllllIIIIIlIII.rotate = llllllllllllllllIlIllllIIIIIlIII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards the webs when placing.").defaultValue(true).build());
        llllllllllllllllIlIllllIIIIIlIII.target = null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIlIllllIIIIIIlII) {
        AutoWeb llllllllllllllllIlIllllIIIIIIIll;
        if (TargetUtils.isBadTarget(llllllllllllllllIlIllllIIIIIIIll.target, llllllllllllllllIlIllllIIIIIIIll.range.get())) {
            llllllllllllllllIlIllllIIIIIIIll.target = TargetUtils.getPlayerTarget(llllllllllllllllIlIllllIIIIIIIll.range.get(), llllllllllllllllIlIllllIIIIIIIll.priority.get());
        }
        if (TargetUtils.isBadTarget(llllllllllllllllIlIllllIIIIIIIll.target, llllllllllllllllIlIllllIIIIIIIll.range.get())) {
            return;
        }
        BlockUtils.place(llllllllllllllllIlIllllIIIIIIIll.target.method_24515(), InvUtils.findInHotbar(class_1802.field_8786), llllllllllllllllIlIllllIIIIIIIll.rotate.get(), 0, false);
        if (llllllllllllllllIlIllllIIIIIIIll.doubles.get().booleanValue()) {
            BlockUtils.place(llllllllllllllllIlIllllIIIIIIIll.target.method_24515().method_10069(0, 1, 0), InvUtils.findInHotbar(class_1802.field_8786), llllllllllllllllIlIllllIIIIIIIll.rotate.get(), 0, false);
        }
    }
}

