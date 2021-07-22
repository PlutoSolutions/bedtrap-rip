/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.player;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.PlayerUtils;

public class PotionSaver
extends Module {
    public final /* synthetic */ Setting<Boolean> onlyWhenStationary;
    private final /* synthetic */ SettingGroup sgGeneral;

    public PotionSaver() {
        super(Categories.Player, "potion-saver", "Stops potion effects ticking when you stand still.");
        PotionSaver llllllllllllllllIllIIIIlIIIlIlll;
        llllllllllllllllIllIIIIlIIIlIlll.sgGeneral = llllllllllllllllIllIIIIlIIIlIlll.settings.getDefaultGroup();
        llllllllllllllllIllIIIIlIIIlIlll.onlyWhenStationary = llllllllllllllllIllIIIIlIIIlIlll.sgGeneral.add(new BoolSetting.Builder().name("only-when-stationary").description("Only freezes effects when you aren't moving.").defaultValue(true).build());
    }

    public boolean shouldFreeze() {
        PotionSaver llllllllllllllllIllIIIIlIIIlIlII;
        if (!Utils.canUpdate()) {
            return false;
        }
        return llllllllllllllllIllIIIIlIIIlIlII.isActive() && (llllllllllllllllIllIIIIlIIIlIlII.onlyWhenStationary.get() != false && !PlayerUtils.isMoving() || llllllllllllllllIllIIIIlIIIlIlII.onlyWhenStationary.get() == false) && !llllllllllllllllIllIIIIlIIIlIlII.mc.field_1724.method_6026().isEmpty();
    }
}

