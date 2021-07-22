/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2394
 *  net.minecraft.class_2396
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ParticleTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2394;
import net.minecraft.class_2396;

public class Trail
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<List<class_2396<?>>> particles;
    private final /* synthetic */ Setting<Boolean> pause;

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIlllllllIllIllIl) {
        Trail llllllllllllllllIlllllllIllIllII;
        if (llllllllllllllllIlllllllIllIllII.pause.get().booleanValue() && llllllllllllllllIlllllllIllIllII.mc.field_1724.field_3913.field_3905 == 0.0f && llllllllllllllllIlllllllIllIllII.mc.field_1724.field_3913.field_3907 == 0.0f && !llllllllllllllllIlllllllIllIllII.mc.field_1690.field_1903.method_1434()) {
            return;
        }
        for (class_2396<?> llllllllllllllllIlllllllIllIllll : llllllllllllllllIlllllllIllIllII.particles.get()) {
            llllllllllllllllIlllllllIllIllII.mc.field_1687.method_8406((class_2394)llllllllllllllllIlllllllIllIllll, llllllllllllllllIlllllllIllIllII.mc.field_1724.method_23317(), llllllllllllllllIlllllllIllIllII.mc.field_1724.method_23318(), llllllllllllllllIlllllllIllIllII.mc.field_1724.method_23321(), 0.0, 0.0, 0.0);
        }
    }

    public Trail() {
        super(Categories.Render, "trail", "Renders a customizable trail behind your player.");
        Trail llllllllllllllllIlllllllIlllIlII;
        llllllllllllllllIlllllllIlllIlII.sgGeneral = llllllllllllllllIlllllllIlllIlII.settings.getDefaultGroup();
        llllllllllllllllIlllllllIlllIlII.particles = llllllllllllllllIlllllllIlllIlII.sgGeneral.add(new ParticleTypeListSetting.Builder().name("particles").description("Particles to draw.").defaultValue(new ArrayList(0)).build());
        llllllllllllllllIlllllllIlllIlII.pause = llllllllllllllllIlllllllIlllIlII.sgGeneral.add(new BoolSetting.Builder().name("pause-when-stationary").description("Whether or not to add particles when you are not moving.").defaultValue(true).build());
    }
}

