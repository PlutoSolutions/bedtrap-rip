/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.player;

import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1268;

public class FindItemResult {
    public final int slot;
    public final int count;

    public FindItemResult(int n, int n2) {
        this.slot = n;
        this.count = n2;
    }

    public boolean isHotbar() {
        return this.slot >= 0 && this.slot <= 8;
    }

    public int getSlot() {
        return this.slot;
    }

    public class_1268 getHand() {
        if (this.slot == 45) {
            return class_1268.field_5810;
        }
        if (this.slot == Utils.mc.field_1724.field_7514.field_7545) {
            return class_1268.field_5808;
        }
        return null;
    }

    public boolean isMain() {
        return this.slot >= 9 && this.slot <= 35;
    }

    public boolean isOffhand() {
        return this.getHand() == class_1268.field_5810;
    }

    public boolean isMainHand() {
        return this.getHand() == class_1268.field_5808;
    }

    public boolean isArmor() {
        return this.slot >= 36 && this.slot <= 39;
    }

    public boolean found() {
        return this.slot != -1;
    }

    public int getCount() {
        return this.count;
    }
}

