/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.player;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Reach
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> reach;

    public Reach() {
        super(Categories.Player, "reach", "Gives you super long arms.");
        Reach lllIllIIlIIl;
        lllIllIIlIIl.sgGeneral = lllIllIIlIIl.settings.getDefaultGroup();
        lllIllIIlIIl.reach = lllIllIIlIIl.sgGeneral.add(new DoubleSetting.Builder().name("reach").description("Your reach modifier.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
    }

    public float getReach() {
        Reach lllIllIIIllI;
        return lllIllIIIllI.reach.get().floatValue();
    }
}

