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
    private int ingredientI;
    private final SettingGroup sgGeneral;
    private boolean first;
    private int timer;
    private final Setting<MyPotion> potion;

    private boolean insertIngredient(class_1708 class_17082, class_1792 class_17922) {
        int n = -1;
        for (int i = 5; i < class_17082.field_7761.size(); ++i) {
            if (((class_1735)class_17082.field_7761.get(i)).method_7677().method_7909() != class_17922) continue;
            n = i;
            break;
        }
        if (n == -1) {
            this.error("You do not have any %s left in your inventory... disabling.", class_17922.method_7848().getString());
            this.toggle();
            return true;
        }
        this.moveOneItem(class_17082, n, 3);
        return false;
    }

    private boolean takePotions(class_1708 class_17082) {
        for (int i = 0; i < 3; ++i) {
            InvUtils.quickMove().slotId(i);
            if (((class_1735)class_17082.field_7761.get(i)).method_7677().method_7960()) continue;
            this.error("You do not have a sufficient amount of inventory space... disabling.", new Object[0]);
            this.toggle();
            return true;
        }
        return false;
    }

    public AutoBrewer() {
        super(Categories.World, "auto-brewer", "Automatically brews specified potions.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.potion = this.sgGeneral.add(new PotionSetting.Builder().name("potion").description("The type of potion to brew.").defaultValue(MyPotion.Strength).build());
    }

    @Override
    public void onActivate() {
        this.first = false;
    }

    private void moveOneItem(class_1708 class_17082, int n, int n2) {
        InvUtils.move().fromId(n).toId(n2);
    }

    public void tick(class_1708 class_17082) {
        ++this.timer;
        if (!this.first) {
            this.first = true;
            this.ingredientI = -2;
            this.timer = 0;
        }
        if (class_17082.method_17378() != 0 || this.timer < 5) {
            return;
        }
        if (this.ingredientI == -2) {
            if (this.takePotions(class_17082)) {
                return;
            }
            ++this.ingredientI;
            this.timer = 0;
        } else if (this.ingredientI == -1) {
            if (this.insertWaterBottles(class_17082)) {
                return;
            }
            ++this.ingredientI;
            this.timer = 0;
        } else if (this.ingredientI < this.potion.get().ingredients.length) {
            if (this.checkFuel(class_17082)) {
                return;
            }
            if (this.insertIngredient(class_17082, this.potion.get().ingredients[this.ingredientI])) {
                return;
            }
            ++this.ingredientI;
            this.timer = 0;
        } else {
            this.ingredientI = -2;
            this.timer = 0;
        }
    }

    public void onBrewingStandClose() {
        this.first = false;
    }

    private boolean insertWaterBottles(class_1708 class_17082) {
        for (int i = 0; i < 3; ++i) {
            int n = -1;
            for (int j = 5; j < class_17082.field_7761.size(); ++j) {
                class_1842 class_18422;
                if (((class_1735)class_17082.field_7761.get(j)).method_7677().method_7909() != class_1802.field_8574 || (class_18422 = class_1844.method_8063((class_1799)((class_1735)class_17082.field_7761.get(j)).method_7677())) != class_1847.field_8991) continue;
                n = j;
                break;
            }
            if (n == -1) {
                this.error("You do not have a sufficient amount of water bottles to complete this brew... disabling.", new Object[0]);
                this.toggle();
                return true;
            }
            InvUtils.move().fromId(n).toId(i);
            if (3 <= 4) continue;
            return false;
        }
        return false;
    }

    private boolean checkFuel(class_1708 class_17082) {
        if (class_17082.method_17377() == 0) {
            int n = -1;
            for (int i = 5; i < class_17082.field_7761.size(); ++i) {
                if (((class_1735)class_17082.field_7761.get(i)).method_7677().method_7909() != class_1802.field_8183) continue;
                n = i;
                break;
            }
            if (n == -1) {
                this.error("You do not have a sufficient amount of blaze powder to use as fuel for the brew... disabling.", new Object[0]);
                this.toggle();
                return true;
            }
            this.moveOneItem(class_17082, n, 4);
        }
        return false;
    }
}

