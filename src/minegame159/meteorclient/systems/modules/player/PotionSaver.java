/*
 * Decompiled with CFR 0.151.
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
    public final Setting<Boolean> onlyWhenStationary;
    private final SettingGroup sgGeneral;

    public PotionSaver() {
        super(Categories.Player, "potion-saver", "Stops potion effects ticking when you stand still.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.onlyWhenStationary = this.sgGeneral.add(new BoolSetting.Builder().name("only-when-stationary").description("Only freezes effects when you aren't moving.").defaultValue(true).build());
    }

    public boolean shouldFreeze() {
        if (!Utils.canUpdate()) {
            return false;
        }
        return this.isActive() && (this.onlyWhenStationary.get() != false && !PlayerUtils.isMoving() || this.onlyWhenStationary.get() == false) && !this.mc.field_1724.method_6026().isEmpty();
    }
}

