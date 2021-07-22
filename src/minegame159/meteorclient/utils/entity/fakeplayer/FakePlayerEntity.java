/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_745
 */
package minegame159.meteorclient.utils.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_745;

public class FakePlayerEntity
extends class_745 {
    public FakePlayerEntity(String lllllllllllllllllIIIllIlIlIlIIlI, float lllllllllllllllllIIIllIlIlIlIIIl, boolean lllllllllllllllllIIIllIlIlIlIlIl) {
        super(Utils.mc.field_1687, new GameProfile(Utils.mc.field_1724.method_5667(), lllllllllllllllllIIIllIlIlIlIIlI));
        FakePlayerEntity lllllllllllllllllIIIllIlIlIlIIll;
        lllllllllllllllllIIIllIlIlIlIIll.method_5719((class_1297)Utils.mc.field_1724);
        lllllllllllllllllIIIllIlIlIlIIll.field_6241 = Utils.mc.field_1724.field_6241;
        lllllllllllllllllIIIllIlIlIlIIll.field_6283 = Utils.mc.field_1724.field_6283;
        Byte lllllllllllllllllIIIllIlIlIlIlII = (Byte)Utils.mc.field_1724.method_5841().method_12789(class_1657.field_7518);
        lllllllllllllllllIIIllIlIlIlIIll.field_6011.method_12778(class_1657.field_7518, (Object)lllllllllllllllllIIIllIlIlIlIlII);
        lllllllllllllllllIIIllIlIlIlIIll.method_6127().method_26846(Utils.mc.field_1724.method_6127());
        lllllllllllllllllIIIllIlIlIlIIll.field_7500 = lllllllllllllllllIIIllIlIlIlIIll.method_23317();
        lllllllllllllllllIIIllIlIlIlIIll.field_7521 = lllllllllllllllllIIIllIlIlIlIIll.method_23318();
        lllllllllllllllllIIIllIlIlIlIIll.field_7499 = lllllllllllllllllIIIllIlIlIlIIll.method_23321();
        if (lllllllllllllllllIIIllIlIlIlIIIl <= 20.0f) {
            lllllllllllllllllIIIllIlIlIlIIll.method_6033(lllllllllllllllllIIIllIlIlIlIIIl);
        } else {
            lllllllllllllllllIIIllIlIlIlIIll.method_6033(lllllllllllllllllIIIllIlIlIlIIIl);
            lllllllllllllllllIIIllIlIlIlIIll.method_6073(lllllllllllllllllIIIllIlIlIlIIIl - 20.0f);
        }
        if (lllllllllllllllllIIIllIlIlIlIlIl) {
            lllllllllllllllllIIIllIlIlIlIIll.field_7514.method_7377(Utils.mc.field_1724.field_7514);
        }
        lllllllllllllllllIIIllIlIlIlIIll.spawn();
    }

    public void despawn() {
        FakePlayerEntity lllllllllllllllllIIIllIlIlIIlIlI;
        Utils.mc.field_1687.method_2945(lllllllllllllllllIIIllIlIlIIlIlI.method_5628());
        lllllllllllllllllIIIllIlIlIIlIlI.field_5988 = true;
    }

    private void spawn() {
        FakePlayerEntity lllllllllllllllllIIIllIlIlIIllIl;
        lllllllllllllllllIIIllIlIlIIllIl.field_5988 = false;
        Utils.mc.field_1687.method_2942(lllllllllllllllllIIIllIlIlIIllIl.method_5628(), (class_1297)lllllllllllllllllIIIllIlIlIIllIl);
    }
}

