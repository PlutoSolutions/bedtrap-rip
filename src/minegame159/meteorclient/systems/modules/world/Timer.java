/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.world;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Timer
extends Module {
    private /* synthetic */ double override;
    public static final /* synthetic */ double OFF = 1.0;
    private final /* synthetic */ Setting<Double> multiplier;
    private final /* synthetic */ SettingGroup sgGeneral;

    public double getMultiplier() {
        Timer llllIIllIIlIIl;
        return llllIIllIIlIIl.override != 1.0 ? llllIIllIIlIIl.override : (llllIIllIIlIIl.isActive() ? llllIIllIIlIIl.multiplier.get() : 1.0);
    }

    public Timer() {
        super(Categories.World, "timer", "Changes the speed of everything in your game.");
        Timer llllIIllIIllII;
        llllIIllIIllII.sgGeneral = llllIIllIIllII.settings.getDefaultGroup();
        llllIIllIIllII.multiplier = llllIIllIIllII.sgGeneral.add(new DoubleSetting.Builder().name("multiplier").description("The timer multiplier amount.").defaultValue(1.0).min(0.1).sliderMin(0.1).sliderMax(10.0).build());
        llllIIllIIllII.override = 1.0;
    }

    public void setOverride(double llllIIllIIIlII) {
        llllIIllIIIIll.override = llllIIllIIIlII;
    }
}

