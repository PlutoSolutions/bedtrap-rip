/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1810
 */
package minegame159.meteorclient.systems.modules.player;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1810;

public class NoMiningTrace
extends Module {
    private final /* synthetic */ Setting<Boolean> onlyWhenHoldingPickaxe;
    private final /* synthetic */ SettingGroup sgGeneral;

    public NoMiningTrace() {
        super(Categories.Player, "no-mining-trace", "Allows you to mine blocks through entities.");
        NoMiningTrace lIlIIIIlIIIlIlI;
        lIlIIIIlIIIlIlI.sgGeneral = lIlIIIIlIIIlIlI.settings.getDefaultGroup();
        lIlIIIIlIIIlIlI.onlyWhenHoldingPickaxe = lIlIIIIlIIIlIlI.sgGeneral.add(new BoolSetting.Builder().name("only-when-holding-a-pickaxe").description("Whether or not to work only when holding a pickaxe.").defaultValue(true).build());
    }

    public boolean canWork() {
        NoMiningTrace lIlIIIIlIIIlIII;
        if (!lIlIIIIlIIIlIII.isActive()) {
            return false;
        }
        if (lIlIIIIlIIIlIII.onlyWhenHoldingPickaxe.get().booleanValue()) {
            return lIlIIIIlIIIlIII.mc.field_1724.method_6047().method_7909() instanceof class_1810 || lIlIIIIlIIIlIII.mc.field_1724.method_6079().method_7909() instanceof class_1810;
        }
        return true;
    }
}

