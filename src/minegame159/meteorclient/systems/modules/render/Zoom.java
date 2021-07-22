/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.MouseScrollEvent;
import minegame159.meteorclient.events.render.GetFovEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Zoom
extends Module {
    private /* synthetic */ double lastFov;
    private final /* synthetic */ Setting<Boolean> cinematic;
    private /* synthetic */ double value;
    private final /* synthetic */ Setting<Double> scrollSensitivity;
    private final /* synthetic */ Setting<Double> zoom;
    private /* synthetic */ double preMouseSensitivity;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean preCinematic;

    @Override
    public void onActivate() {
        Zoom llllllllllllllllllIlllIIIlIllIlI;
        llllllllllllllllllIlllIIIlIllIlI.preCinematic = llllllllllllllllllIlllIIIlIllIlI.mc.field_1690.field_1914;
        llllllllllllllllllIlllIIIlIllIlI.preMouseSensitivity = llllllllllllllllllIlllIIIlIllIlI.mc.field_1690.field_1843;
        llllllllllllllllllIlllIIIlIllIlI.value = llllllllllllllllllIlllIIIlIllIlI.zoom.get();
        llllllllllllllllllIlllIIIlIllIlI.lastFov = llllllllllllllllllIlllIIIlIllIlI.mc.field_1690.field_1826;
    }

    @EventHandler
    private void onGetFov(GetFovEvent llllllllllllllllllIlllIIIlIIlIII) {
        Zoom llllllllllllllllllIlllIIIlIIlIIl;
        llllllllllllllllllIlllIIIlIIlIII.fov /= llllllllllllllllllIlllIIIlIIlIIl.value;
        if (llllllllllllllllllIlllIIIlIIlIIl.lastFov != llllllllllllllllllIlllIIIlIIlIII.fov) {
            llllllllllllllllllIlllIIIlIIlIIl.mc.field_1769.method_3292();
        }
        llllllllllllllllllIlllIIIlIIlIIl.lastFov = llllllllllllllllllIlllIIIlIIlIII.fov;
    }

    public Zoom() {
        super(Categories.Render, "zoom", "Zooms your view.");
        Zoom llllllllllllllllllIlllIIIlIlllIl;
        llllllllllllllllllIlllIIIlIlllIl.sgGeneral = llllllllllllllllllIlllIIIlIlllIl.settings.getDefaultGroup();
        llllllllllllllllllIlllIIIlIlllIl.zoom = llllllllllllllllllIlllIIIlIlllIl.sgGeneral.add(new DoubleSetting.Builder().name("zoom").description("How much to zoom.").defaultValue(6.0).min(1.0).build());
        llllllllllllllllllIlllIIIlIlllIl.scrollSensitivity = llllllllllllllllllIlllIIIlIlllIl.sgGeneral.add(new DoubleSetting.Builder().name("scroll-sensitivity").description("Allows you to change zoom value using scroll wheel. 0 to disable.").defaultValue(1.0).min(0.0).build());
        llllllllllllllllllIlllIIIlIlllIl.cinematic = llllllllllllllllllIlllIIIlIlllIl.sgGeneral.add(new BoolSetting.Builder().name("cinematic").description("Enables cinematic camera.").defaultValue(false).build());
    }

    @EventHandler
    private void onMouseScroll(MouseScrollEvent llllllllllllllllllIlllIIIlIIllII) {
        Zoom llllllllllllllllllIlllIIIlIIllll;
        if (llllllllllllllllllIlllIIIlIIllll.scrollSensitivity.get() > 0.0) {
            llllllllllllllllllIlllIIIlIIllll.value += llllllllllllllllllIlllIIIlIIllII.value * 0.25 * (llllllllllllllllllIlllIIIlIIllll.scrollSensitivity.get() * llllllllllllllllllIlllIIIlIIllll.value);
            if (llllllllllllllllllIlllIIIlIIllll.value < 1.0) {
                llllllllllllllllllIlllIIIlIIllll.value = 1.0;
            }
            llllllllllllllllllIlllIIIlIIllII.cancel();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIlllIIIlIlIIll) {
        Zoom llllllllllllllllllIlllIIIlIlIIlI;
        llllllllllllllllllIlllIIIlIlIIlI.mc.field_1690.field_1914 = llllllllllllllllllIlllIIIlIlIIlI.cinematic.get();
        if (!llllllllllllllllllIlllIIIlIlIIlI.cinematic.get().booleanValue()) {
            llllllllllllllllllIlllIIIlIlIIlI.mc.field_1690.field_1843 = llllllllllllllllllIlllIIIlIlIIlI.preMouseSensitivity / Math.max(llllllllllllllllllIlllIIIlIlIIlI.value * 0.5, 1.0);
        }
    }

    @Override
    public void onDeactivate() {
        Zoom llllllllllllllllllIlllIIIlIlIllI;
        llllllllllllllllllIlllIIIlIlIllI.mc.field_1690.field_1914 = llllllllllllllllllIlllIIIlIlIllI.preCinematic;
        llllllllllllllllllIlllIIIlIlIllI.mc.field_1690.field_1843 = llllllllllllllllllIlllIIIlIlIllI.preMouseSensitivity;
        llllllllllllllllllIlllIIIlIlIllI.mc.field_1769.method_3292();
    }
}

