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
    private /* synthetic */ int step;
    private /* synthetic */ boolean waitingForItemsToSmelt;
    private /* synthetic */ boolean first;
    private /* synthetic */ int timer;

    public void onFurnaceClose() {
        llIlIIlllIlIlI.first = true;
        llIlIIlllIlIlI.waitingForItemsToSmelt = false;
    }

    public void tick(class_1720 llIlIIlllIIIll) {
        AutoSmelter llIlIIlllIIllI;
        ++llIlIIlllIIllI.timer;
        if (!llIlIIlllIIllI.first) {
            llIlIIlllIIllI.first = true;
            llIlIIlllIIllI.step = 0;
            llIlIIlllIIllI.timer = 0;
        }
        if (llIlIIlllIIllI.checkFuel(llIlIIlllIIIll)) {
            return;
        }
        if (llIlIIlllIIIll.method_17363() != 0 || llIlIIlllIIllI.timer < 5) {
            return;
        }
        if (llIlIIlllIIllI.step == 0) {
            if (llIlIIlllIIllI.takeResults(llIlIIlllIIIll)) {
                return;
            }
            ++llIlIIlllIIllI.step;
            llIlIIlllIIllI.timer = 0;
        } else if (llIlIIlllIIllI.step == 1) {
            if (llIlIIlllIIllI.waitingForItemsToSmelt) {
                if (((class_1735)llIlIIlllIIIll.field_7761.get(0)).method_7677().method_7960()) {
                    llIlIIlllIIllI.step = 0;
                    llIlIIlllIIllI.timer = 0;
                    llIlIIlllIIllI.waitingForItemsToSmelt = false;
                }
                return;
            }
            if (llIlIIlllIIllI.insertItems(llIlIIlllIIIll)) {
                return;
            }
            llIlIIlllIIllI.waitingForItemsToSmelt = true;
        }
    }

    private boolean takeResults(class_1720 llIlIIllIIIlll) {
        InvUtils.quickMove().slotId(2);
        if (!((class_1735)llIlIIllIIIlll.field_7761.get(2)).method_7677().method_7960()) {
            AutoSmelter llIlIIllIIIllI;
            llIlIIllIIIllI.error("Your inventory is full... disabling.", new Object[0]);
            llIlIIllIIIllI.toggle();
            return true;
        }
        return false;
    }

    private boolean insertItems(class_1720 llIlIIllIlllII) {
        if (!((class_1735)llIlIIllIlllII.field_7761.get(0)).method_7677().method_7960()) {
            return true;
        }
        int llIlIIllIllIll = -1;
        for (int llIlIIllIllllI = 3; llIlIIllIllllI < llIlIIllIlllII.field_7761.size(); ++llIlIIllIllllI) {
            if (!((AbstractFurnaceScreenHandlerAccessor)llIlIIllIlllII).isSmeltable(((class_1735)llIlIIllIlllII.field_7761.get(llIlIIllIllllI)).method_7677())) continue;
            llIlIIllIllIll = llIlIIllIllllI;
            break;
        }
        if (llIlIIllIllIll == -1) {
            AutoSmelter llIlIIllIllIlI;
            llIlIIllIllIlI.error("You do not have any items in your inventory that can be smelted... disabling.", new Object[0]);
            llIlIIllIllIlI.toggle();
            return true;
        }
        InvUtils.move().fromId(llIlIIllIllIll).toId(0);
        return false;
    }

    public AutoSmelter() {
        super(Categories.World, "auto-smelter", "Automatically smelts all items in your inventory that can be smelted.");
        AutoSmelter llIlIIllllIIII;
    }

    private boolean checkFuel(class_1720 llIlIIllIIllIl) {
        if (llIlIIllIIllIl.method_17364() <= 1 && !((AbstractFurnaceScreenHandlerAccessor)llIlIIllIIllIl).isFuel(((class_1735)llIlIIllIIllIl.field_7761.get(1)).method_7677())) {
            AutoSmelter llIlIIllIIlllI;
            if (!((class_1735)llIlIIllIIllIl.field_7761.get(1)).method_7677().method_7960()) {
                InvUtils.quickMove().slotId(1);
                if (!((class_1735)llIlIIllIIllIl.field_7761.get(1)).method_7677().method_7960()) {
                    llIlIIllIIlllI.error("Your inventory is currently full... disabling.", new Object[0]);
                    llIlIIllIIlllI.toggle();
                    return true;
                }
            }
            int llIlIIllIlIIIl = -1;
            for (int llIlIIllIlIIlI = 3; llIlIIllIlIIlI < llIlIIllIIllIl.field_7761.size(); ++llIlIIllIlIIlI) {
                if (!((AbstractFurnaceScreenHandlerAccessor)llIlIIllIIllIl).isFuel(((class_1735)llIlIIllIIllIl.field_7761.get(llIlIIllIlIIlI)).method_7677())) continue;
                llIlIIllIlIIIl = llIlIIllIlIIlI;
                break;
            }
            if (llIlIIllIlIIIl == -1) {
                llIlIIllIIlllI.error("You do not have any fuel in your inventory... disabling.", new Object[0]);
                llIlIIllIIlllI.toggle();
                return true;
            }
            InvUtils.move().fromId(llIlIIllIlIIIl).toId(1);
        }
        return false;
    }

    @Override
    public void onActivate() {
        llIlIIlllIllII.first = true;
        llIlIIlllIllII.waitingForItemsToSmelt = false;
    }
}

