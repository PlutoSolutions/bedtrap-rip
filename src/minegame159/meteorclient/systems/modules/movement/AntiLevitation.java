/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class AntiLevitation
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> applyGravity;

    public AntiLevitation() {
        super(Categories.Movement, "anti-levitation", "Prevents the levitation effect from working.");
        AntiLevitation lllIIIIllIlIII;
        lllIIIIllIlIII.sgGeneral = lllIIIIllIlIII.settings.getDefaultGroup();
        lllIIIIllIlIII.applyGravity = lllIIIIllIlIII.sgGeneral.add(new BoolSetting.Builder().name("gravity").description("Applies gravity.").defaultValue(false).build());
    }

    public boolean isApplyGravity() {
        AntiLevitation lllIIIIllIIlIl;
        return lllIIIIllIIlIl.applyGravity.get();
    }
}

