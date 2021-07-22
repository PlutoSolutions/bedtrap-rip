/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1738
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.player;

import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1304;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class ChestSwap
extends Module {
    private final /* synthetic */ Setting<Boolean> stayOn;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Chestplate> chestplate;

    @Override
    public void sendToggledMsg() {
        ChestSwap lllllllllllllllllIIlIlllIIlIlIlI;
        if (lllllllllllllllllIIlIlllIIlIlIlI.stayOn.get().booleanValue()) {
            super.sendToggledMsg();
        } else if (Config.get().chatCommandsInfo) {
            lllllllllllllllllIIlIlllIIlIlIlI.info("Triggered (highlight)%s(default).", lllllllllllllllllIIlIlllIIlIlIlI.title);
        }
    }

    public ChestSwap() {
        super(Categories.Player, "chest-swap", "Automatically swaps between a chestplate and an elytra.");
        ChestSwap lllllllllllllllllIIlIlllIlIlIllI;
        lllllllllllllllllIIlIlllIlIlIllI.sgGeneral = lllllllllllllllllIIlIlllIlIlIllI.settings.getDefaultGroup();
        lllllllllllllllllIIlIlllIlIlIllI.chestplate = lllllllllllllllllIIlIlllIlIlIllI.sgGeneral.add(new EnumSetting.Builder().name("chestplate").description("Which type of chestplate to swap to.").defaultValue(Chestplate.PreferNetherite).build());
        lllllllllllllllllIIlIlllIlIlIllI.stayOn = lllllllllllllllllIIlIlllIlIlIllI.sgGeneral.add(new BoolSetting.Builder().name("stay-on").description("Stays on and activates when you turn it off.").defaultValue(false).build());
    }

    @Override
    public void onDeactivate() {
        ChestSwap lllllllllllllllllIIlIlllIlIlIIII;
        if (lllllllllllllllllIIlIlllIlIlIIII.stayOn.get().booleanValue()) {
            lllllllllllllllllIIlIlllIlIlIIII.swap();
        }
    }

    private boolean equipChestplate() {
        ChestSwap lllllllllllllllllIIlIlllIIlllllI;
        int lllllllllllllllllIIlIlllIlIIIIII = -1;
        boolean lllllllllllllllllIIlIlllIIllllll = false;
        for (int lllllllllllllllllIIlIlllIlIIIIlI = 0; lllllllllllllllllIIlIlllIlIIIIlI < lllllllllllllllllIIlIlllIIlllllI.mc.field_1724.field_7514.field_7547.size(); ++lllllllllllllllllIIlIlllIlIIIIlI) {
            class_1792 lllllllllllllllllIIlIlllIlIIIIll = ((class_1799)lllllllllllllllllIIlIlllIIlllllI.mc.field_1724.field_7514.field_7547.get(lllllllllllllllllIIlIlllIlIIIIlI)).method_7909();
            switch (lllllllllllllllllIIlIlllIIlllllI.chestplate.get()) {
                case Diamond: {
                    if (lllllllllllllllllIIlIlllIlIIIIll != class_1802.field_8058) break;
                    lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                    lllllllllllllllllIIlIlllIIllllll = true;
                    break;
                }
                case Netherite: {
                    if (lllllllllllllllllIIlIlllIlIIIIll != class_1802.field_22028) break;
                    lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                    lllllllllllllllllIIlIlllIIllllll = true;
                    break;
                }
                case PreferDiamond: {
                    if (lllllllllllllllllIIlIlllIlIIIIll == class_1802.field_8058) {
                        lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                        lllllllllllllllllIIlIlllIIllllll = true;
                        break;
                    }
                    if (lllllllllllllllllIIlIlllIlIIIIll != class_1802.field_22028) break;
                    lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                    break;
                }
                case PreferNetherite: {
                    if (lllllllllllllllllIIlIlllIlIIIIll == class_1802.field_8058) {
                        lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                        break;
                    }
                    if (lllllllllllllllllIIlIlllIlIIIIll != class_1802.field_22028) break;
                    lllllllllllllllllIIlIlllIlIIIIII = lllllllllllllllllIIlIlllIlIIIIlI;
                    lllllllllllllllllIIlIlllIIllllll = true;
                }
            }
            if (lllllllllllllllllIIlIlllIIllllll) break;
        }
        if (lllllllllllllllllIIlIlllIlIIIIII != -1) {
            lllllllllllllllllIIlIlllIIlllllI.equip(lllllllllllllllllIIlIlllIlIIIIII);
        }
        return lllllllllllllllllIIlIlllIlIIIIII != -1;
    }

    private void equipElytra() {
        ChestSwap lllllllllllllllllIIlIlllIIllIIll;
        for (int lllllllllllllllllIIlIlllIIllIlIl = 0; lllllllllllllllllIIlIlllIIllIlIl < lllllllllllllllllIIlIlllIIllIIll.mc.field_1724.field_7514.field_7547.size(); ++lllllllllllllllllIIlIlllIIllIlIl) {
            class_1792 lllllllllllllllllIIlIlllIIllIllI = ((class_1799)lllllllllllllllllIIlIlllIIllIIll.mc.field_1724.field_7514.field_7547.get(lllllllllllllllllIIlIlllIIllIlIl)).method_7909();
            if (lllllllllllllllllIIlIlllIIllIllI != class_1802.field_8833) continue;
            lllllllllllllllllIIlIlllIIllIIll.equip(lllllllllllllllllIIlIlllIIllIlIl);
            break;
        }
    }

    public void swap() {
        ChestSwap lllllllllllllllllIIlIlllIlIIlIlI;
        class_1792 lllllllllllllllllIIlIlllIlIIlIll = lllllllllllllllllIIlIlllIlIIlIlI.mc.field_1724.method_6118(class_1304.field_6174).method_7909();
        if (lllllllllllllllllIIlIlllIlIIlIll == class_1802.field_8833) {
            lllllllllllllllllIIlIlllIlIIlIlI.equipChestplate();
        } else if (lllllllllllllllllIIlIlllIlIIlIll instanceof class_1738 && ((class_1738)lllllllllllllllllIIlIlllIlIIlIll).method_7685() == class_1304.field_6174) {
            lllllllllllllllllIIlIlllIlIIlIlI.equipElytra();
        } else if (!lllllllllllllllllIIlIlllIlIIlIlI.equipChestplate()) {
            lllllllllllllllllIIlIlllIlIIlIlI.equipElytra();
        }
    }

    @Override
    public void onActivate() {
        ChestSwap lllllllllllllllllIIlIlllIlIlIIll;
        lllllllllllllllllIIlIlllIlIlIIll.swap();
        if (!lllllllllllllllllIIlIlllIlIlIIll.stayOn.get().booleanValue()) {
            lllllllllllllllllIIlIlllIlIlIIll.toggle();
        }
    }

    private void equip(int lllllllllllllllllIIlIlllIIlIllIl) {
        InvUtils.move().from(lllllllllllllllllIIlIlllIIlIllIl).toArmor(2);
    }

    public static enum Chestplate {
        Diamond,
        Netherite,
        PreferDiamond,
        PreferNetherite;


        private Chestplate() {
            Chestplate llllllllllllllllllIlIIllIIlIIlll;
        }
    }
}

