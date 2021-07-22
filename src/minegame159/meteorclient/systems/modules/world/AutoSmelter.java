/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1720
 *  net.minecraft.class_1735
 */
package minegame159.meteorclient.systems.modules.world;

import minegame159.meteorclient.mixin.AbstractFurnaceScreenHandlerAccessor;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1720;
import net.minecraft.class_1735;

public class AutoSmelter
extends Module {
    private int step;
    private boolean waitingForItemsToSmelt;
    private boolean first;
    private int timer;

    public void onFurnaceClose() {
        this.first = true;
        this.waitingForItemsToSmelt = false;
    }

    public void tick(class_1720 class_17202) {
        ++this.timer;
        if (!this.first) {
            this.first = true;
            this.step = 0;
            this.timer = 0;
        }
        if (this.checkFuel(class_17202)) {
            return;
        }
        if (class_17202.method_17363() != 0 || this.timer < 5) {
            return;
        }
        if (this.step == 0) {
            if (this.takeResults(class_17202)) {
                return;
            }
            ++this.step;
            this.timer = 0;
        } else if (this.step == 1) {
            if (this.waitingForItemsToSmelt) {
                if (((class_1735)class_17202.field_7761.get(0)).method_7677().method_7960()) {
                    this.step = 0;
                    this.timer = 0;
                    this.waitingForItemsToSmelt = false;
                }
                return;
            }
            if (this.insertItems(class_17202)) {
                return;
            }
            this.waitingForItemsToSmelt = true;
        }
    }

    private boolean takeResults(class_1720 class_17202) {
        InvUtils.quickMove().slotId(2);
        if (!((class_1735)class_17202.field_7761.get(2)).method_7677().method_7960()) {
            this.error("Your inventory is full... disabling.", new Object[0]);
            this.toggle();
            return true;
        }
        return false;
    }

    private boolean insertItems(class_1720 class_17202) {
        if (!((class_1735)class_17202.field_7761.get(0)).method_7677().method_7960()) {
            return true;
        }
        int n = -1;
        for (int i = 3; i < class_17202.field_7761.size(); ++i) {
            if (!((AbstractFurnaceScreenHandlerAccessor)class_17202).isSmeltable(((class_1735)class_17202.field_7761.get(i)).method_7677())) continue;
            n = i;
            break;
        }
        if (n == -1) {
            this.error("You do not have any items in your inventory that can be smelted... disabling.", new Object[0]);
            this.toggle();
            return true;
        }
        InvUtils.move().fromId(n).toId(0);
        return false;
    }

    public AutoSmelter() {
        super(Categories.World, "auto-smelter", "Automatically smelts all items in your inventory that can be smelted.");
    }

    private boolean checkFuel(class_1720 class_17202) {
        if (class_17202.method_17364() <= 1 && !((AbstractFurnaceScreenHandlerAccessor)class_17202).isFuel(((class_1735)class_17202.field_7761.get(1)).method_7677())) {
            if (!((class_1735)class_17202.field_7761.get(1)).method_7677().method_7960()) {
                InvUtils.quickMove().slotId(1);
                if (!((class_1735)class_17202.field_7761.get(1)).method_7677().method_7960()) {
                    this.error("Your inventory is currently full... disabling.", new Object[0]);
                    this.toggle();
                    return true;
                }
            }
            int n = -1;
            for (int i = 3; i < class_17202.field_7761.size(); ++i) {
                if (!((AbstractFurnaceScreenHandlerAccessor)class_17202).isFuel(((class_1735)class_17202.field_7761.get(i)).method_7677())) continue;
                n = i;
                break;
            }
            if (n == -1) {
                this.error("You do not have any fuel in your inventory... disabling.", new Object[0]);
                this.toggle();
                return true;
            }
            InvUtils.move().fromId(n).toId(1);
        }
        return false;
    }

    @Override
    public void onActivate() {
        this.first = true;
        this.waitingForItemsToSmelt = false;
    }
}

