/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1708
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1842
 *  net.minecraft.class_1844
 *  net.minecraft.class_1847
 */
package minegame159.meteorclient.systems.modules.world;

import minegame159.meteorclient.settings.PotionSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.MyPotion;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1708;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1842;
import net.minecraft.class_1844;
import net.minecraft.class_1847;

public class AutoBrewer
extends Module {
    private /* synthetic */ int ingredientI;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean first;
    private /* synthetic */ int timer;
    private final /* synthetic */ Setting<MyPotion> potion;

    private boolean insertIngredient(class_1708 llIlIIIllllIIl, class_1792 llIlIIIlllIlII) {
        AutoBrewer llIlIIIllllIlI;
        int llIlIIIlllIlll = -1;
        for (int llIlIIIllllIll = 5; llIlIIIllllIll < llIlIIIllllIIl.field_7761.size(); ++llIlIIIllllIll) {
            if (((class_1735)llIlIIIllllIIl.field_7761.get(llIlIIIllllIll)).method_7677().method_7909() != llIlIIIlllIlII) continue;
            llIlIIIlllIlll = llIlIIIllllIll;
            break;
        }
        if (llIlIIIlllIlll == -1) {
            llIlIIIllllIlI.error("You do not have any %s left in your inventory... disabling.", llIlIIIlllIlII.method_7848().getString());
            llIlIIIllllIlI.toggle();
            return true;
        }
        llIlIIIllllIlI.moveOneItem(llIlIIIllllIIl, llIlIIIlllIlll, 3);
        return false;
    }

    private boolean takePotions(class_1708 llIlIIIlIIIllI) {
        for (int llIlIIIlIIlIII = 0; llIlIIIlIIlIII < 3; ++llIlIIIlIIlIII) {
            AutoBrewer llIlIIIlIIIlll;
            InvUtils.quickMove().slotId(llIlIIIlIIlIII);
            if (((class_1735)llIlIIIlIIIllI.field_7761.get(llIlIIIlIIlIII)).method_7677().method_7960()) continue;
            llIlIIIlIIIlll.error("You do not have a sufficient amount of inventory space... disabling.", new Object[0]);
            llIlIIIlIIIlll.toggle();
            return true;
        }
        return false;
    }

    public AutoBrewer() {
        super(Categories.World, "auto-brewer", "Automatically brews specified potions.");
        AutoBrewer llIlIIlIIIllIl;
        llIlIIlIIIllIl.sgGeneral = llIlIIlIIIllIl.settings.getDefaultGroup();
        llIlIIlIIIllIl.potion = llIlIIlIIIllIl.sgGeneral.add(new PotionSetting.Builder().name("potion").description("The type of potion to brew.").defaultValue(MyPotion.Strength).build());
    }

    @Override
    public void onActivate() {
        llIlIIlIIIlIll.first = false;
    }

    private void moveOneItem(class_1708 llIlIIIllIIIlI, int llIlIIIllIIIIl, int llIlIIIlIllllI) {
        InvUtils.move().fromId(llIlIIIllIIIIl).toId(llIlIIIlIllllI);
    }

    public void tick(class_1708 llIlIIlIIIIIll) {
        AutoBrewer llIlIIlIIIIIlI;
        ++llIlIIlIIIIIlI.timer;
        if (!llIlIIlIIIIIlI.first) {
            llIlIIlIIIIIlI.first = true;
            llIlIIlIIIIIlI.ingredientI = -2;
            llIlIIlIIIIIlI.timer = 0;
        }
        if (llIlIIlIIIIIll.method_17378() != 0 || llIlIIlIIIIIlI.timer < 5) {
            return;
        }
        if (llIlIIlIIIIIlI.ingredientI == -2) {
            if (llIlIIlIIIIIlI.takePotions(llIlIIlIIIIIll)) {
                return;
            }
            ++llIlIIlIIIIIlI.ingredientI;
            llIlIIlIIIIIlI.timer = 0;
        } else if (llIlIIlIIIIIlI.ingredientI == -1) {
            if (llIlIIlIIIIIlI.insertWaterBottles(llIlIIlIIIIIll)) {
                return;
            }
            ++llIlIIlIIIIIlI.ingredientI;
            llIlIIlIIIIIlI.timer = 0;
        } else if (llIlIIlIIIIIlI.ingredientI < llIlIIlIIIIIlI.potion.get().ingredients.length) {
            if (llIlIIlIIIIIlI.checkFuel(llIlIIlIIIIIll)) {
                return;
            }
            if (llIlIIlIIIIIlI.insertIngredient(llIlIIlIIIIIll, llIlIIlIIIIIlI.potion.get().ingredients[llIlIIlIIIIIlI.ingredientI])) {
                return;
            }
            ++llIlIIlIIIIIlI.ingredientI;
            llIlIIlIIIIIlI.timer = 0;
        } else {
            llIlIIlIIIIIlI.ingredientI = -2;
            llIlIIlIIIIIlI.timer = 0;
        }
    }

    public void onBrewingStandClose() {
        llIlIIlIIIIlll.first = false;
    }

    private boolean insertWaterBottles(class_1708 llIlIIIlIlIIlI) {
        for (int llIlIIIlIlIlII = 0; llIlIIIlIlIlII < 3; ++llIlIIIlIlIlII) {
            int llIlIIIlIlIlIl = -1;
            for (int llIlIIIlIlIllI = 5; llIlIIIlIlIllI < llIlIIIlIlIIlI.field_7761.size(); ++llIlIIIlIlIllI) {
                class_1842 llIlIIIlIlIlll;
                if (((class_1735)llIlIIIlIlIIlI.field_7761.get(llIlIIIlIlIllI)).method_7677().method_7909() != class_1802.field_8574 || (llIlIIIlIlIlll = class_1844.method_8063((class_1799)((class_1735)llIlIIIlIlIIlI.field_7761.get(llIlIIIlIlIllI)).method_7677())) != class_1847.field_8991) continue;
                llIlIIIlIlIlIl = llIlIIIlIlIllI;
                break;
            }
            if (llIlIIIlIlIlIl == -1) {
                AutoBrewer llIlIIIlIlIIIl;
                llIlIIIlIlIIIl.error("You do not have a sufficient amount of water bottles to complete this brew... disabling.", new Object[0]);
                llIlIIIlIlIIIl.toggle();
                return true;
            }
            InvUtils.move().fromId(llIlIIIlIlIlIl).toId(llIlIIIlIlIlII);
        }
        return false;
    }

    private boolean checkFuel(class_1708 llIlIIIllIlIII) {
        if (llIlIIIllIlIII.method_17377() == 0) {
            AutoBrewer llIlIIIllIlIIl;
            int llIlIIIllIllII = -1;
            for (int llIlIIIllIllIl = 5; llIlIIIllIllIl < llIlIIIllIlIII.field_7761.size(); ++llIlIIIllIllIl) {
                if (((class_1735)llIlIIIllIlIII.field_7761.get(llIlIIIllIllIl)).method_7677().method_7909() != class_1802.field_8183) continue;
                llIlIIIllIllII = llIlIIIllIllIl;
                break;
            }
            if (llIlIIIllIllII == -1) {
                llIlIIIllIlIIl.error("You do not have a sufficient amount of blaze powder to use as fuel for the brew... disabling.", new Object[0]);
                llIlIIIllIlIIl.toggle();
                return true;
            }
            llIlIIIllIlIIl.moveOneItem(llIlIIIllIlIII, llIlIIIllIllII, 4);
        }
        return false;
    }
}

