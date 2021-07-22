/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_243;

public class Spider
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> speed;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllllllIIIIIllII) {
        Spider lllllllllllllllllllllllIIIIIlIlI;
        if (!lllllllllllllllllllllllIIIIIlIlI.mc.field_1724.field_5976) {
            return;
        }
        class_243 lllllllllllllllllllllllIIIIIlIll = lllllllllllllllllllllllIIIIIlIlI.mc.field_1724.method_18798();
        if (lllllllllllllllllllllllIIIIIlIll.field_1351 >= 0.2) {
            return;
        }
        lllllllllllllllllllllllIIIIIlIlI.mc.field_1724.method_18800(lllllllllllllllllllllllIIIIIlIll.field_1352, lllllllllllllllllllllllIIIIIlIlI.speed.get().doubleValue(), lllllllllllllllllllllllIIIIIlIll.field_1350);
    }

    public Spider() {
        super(Categories.Movement, "spider", "Allows you to climb walls like a spider.");
        Spider lllllllllllllllllllllllIIIIlIIII;
        lllllllllllllllllllllllIIIIlIIII.sgGeneral = lllllllllllllllllllllllIIIIlIIII.settings.getDefaultGroup();
        lllllllllllllllllllllllIIIIlIIII.speed = lllllllllllllllllllllllIIIIlIIII.sgGeneral.add(new DoubleSetting.Builder().name("climb-speed").description("The speed you go up blocks.").defaultValue(0.2).min(0.0).build());
    }
}

