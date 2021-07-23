/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class AntiLevitation
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> applyGravity;

    public AntiLevitation() {
        super(Categories.Movement, "anti-levitation", "Prevents the levitation effect from working.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.applyGravity = this.sgGeneral.add(new BoolSetting.Builder().name("gravity").description("Applies gravity.").defaultValue(false).build());
    }

    public boolean isApplyGravity() {
        return this.applyGravity.get();
    }
}

