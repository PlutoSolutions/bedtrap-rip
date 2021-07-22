/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 */
package minegame159.meteorclient.utils.player;

import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1268;

public class FindItemResult {
    public final /* synthetic */ int slot;
    public final /* synthetic */ int count;

    public FindItemResult(int lllllllllllllllllIllIIIIlllIIIlI, int lllllllllllllllllIllIIIIlllIIIIl) {
        FindItemResult lllllllllllllllllIllIIIIlllIIIII;
        lllllllllllllllllIllIIIIlllIIIII.slot = lllllllllllllllllIllIIIIlllIIIlI;
        lllllllllllllllllIllIIIIlllIIIII.count = lllllllllllllllllIllIIIIlllIIIIl;
    }

    public boolean isHotbar() {
        FindItemResult lllllllllllllllllIllIIIIllIIlIIl;
        return lllllllllllllllllIllIIIIllIIlIIl.slot >= 0 && lllllllllllllllllIllIIIIllIIlIIl.slot <= 8;
    }

    public int getSlot() {
        FindItemResult lllllllllllllllllIllIIIIllIllIll;
        return lllllllllllllllllIllIIIIllIllIll.slot;
    }

    public class_1268 getHand() {
        FindItemResult lllllllllllllllllIllIIIIllIlIIll;
        if (lllllllllllllllllIllIIIIllIlIIll.slot == 45) {
            return class_1268.field_5810;
        }
        if (lllllllllllllllllIllIIIIllIlIIll.slot == Utils.mc.field_1724.field_7514.field_7545) {
            return class_1268.field_5808;
        }
        return null;
    }

    public boolean isMain() {
        FindItemResult lllllllllllllllllIllIIIIllIIIllI;
        return lllllllllllllllllIllIIIIllIIIllI.slot >= 9 && lllllllllllllllllIllIIIIllIIIllI.slot <= 35;
    }

    public boolean isOffhand() {
        FindItemResult lllllllllllllllllIllIIIIllIIllIl;
        return lllllllllllllllllIllIIIIllIIllIl.getHand() == class_1268.field_5810;
    }

    public boolean isMainHand() {
        FindItemResult lllllllllllllllllIllIIIIllIlIIII;
        return lllllllllllllllllIllIIIIllIlIIII.getHand() == class_1268.field_5808;
    }

    public boolean isArmor() {
        FindItemResult lllllllllllllllllIllIIIIllIIIlII;
        return lllllllllllllllllIllIIIIllIIIlII.slot >= 36 && lllllllllllllllllIllIIIIllIIIlII.slot <= 39;
    }

    public boolean found() {
        FindItemResult lllllllllllllllllIllIIIIllIlIllI;
        return lllllllllllllllllIllIIIIllIlIllI.slot != -1;
    }

    public int getCount() {
        FindItemResult lllllllllllllllllIllIIIIllIllIII;
        return lllllllllllllllllIllIIIIllIllIII.count;
    }
}

