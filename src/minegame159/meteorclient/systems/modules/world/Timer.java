/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.world;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Timer
extends Module {
    private double override;
    public static final double OFF = 1.0;
    private final Setting<Double> multiplier;
    private final SettingGroup sgGeneral;

    public double getMultiplier() {
        return this.override != 1.0 ? this.override : (this.isActive() ? this.multiplier.get() : 1.0);
    }

    public Timer() {
        super(Categories.World, "timer", "Changes the speed of everything in your game.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.multiplier = this.sgGeneral.add(new DoubleSetting.Builder().name("multiplier").description("The timer multiplier amount.").defaultValue(1.0).min(0.1).sliderMin(0.1).sliderMax(10.0).build());
        this.override = 1.0;
    }

    public void setOverride(double d) {
        this.override = d;
    }
}

