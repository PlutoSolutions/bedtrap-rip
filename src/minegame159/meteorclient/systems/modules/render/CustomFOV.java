/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class CustomFOV
extends Module {
    private final /* synthetic */ Setting<Integer> fovSetting;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ double fov;

    @Override
    public void onActivate() {
        CustomFOV lllllllllllllllllIlIIlIIIIIIIlll;
        lllllllllllllllllIlIIlIIIIIIIlll.fov = lllllllllllllllllIlIIlIIIIIIIlll.mc.field_1690.field_1826;
        lllllllllllllllllIlIIlIIIIIIIlll.mc.field_1690.field_1826 = lllllllllllllllllIlIIlIIIIIIIlll.fovSetting.get().intValue();
    }

    public CustomFOV() {
        super(Categories.Render, "custom-fov", "Allows your FOV to be more customizable.");
        CustomFOV lllllllllllllllllIlIIlIIIIIIlIll;
        lllllllllllllllllIlIIlIIIIIIlIll.sgGeneral = lllllllllllllllllIlIIlIIIIIIlIll.settings.getDefaultGroup();
        lllllllllllllllllIlIIlIIIIIIlIll.fovSetting = lllllllllllllllllIlIIlIIIIIIlIll.sgGeneral.add(new IntSetting.Builder().name("fov").description("Your custom fov.").defaultValue(100).sliderMin(1).sliderMax(179).build());
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIlIIlIIIIIIIlII) {
        CustomFOV lllllllllllllllllIlIIlIIIIIIIlIl;
        if ((double)lllllllllllllllllIlIIlIIIIIIIlIl.fovSetting.get().intValue() != lllllllllllllllllIlIIlIIIIIIIlIl.mc.field_1690.field_1826) {
            lllllllllllllllllIlIIlIIIIIIIlIl.mc.field_1690.field_1826 = lllllllllllllllllIlIIlIIIIIIIlIl.fovSetting.get().intValue();
        }
    }

    @Override
    public void onDeactivate() {
        CustomFOV lllllllllllllllllIlIIlIIIIIIIIII;
        lllllllllllllllllIlIIlIIIIIIIIII.mc.field_1690.field_1826 = lllllllllllllllllIlIIlIIIIIIIIII.fov;
    }
}

