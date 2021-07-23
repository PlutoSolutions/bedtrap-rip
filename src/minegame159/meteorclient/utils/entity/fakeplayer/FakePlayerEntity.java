/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.entity.fakeplayer;

import com.mojang.authlib.GameProfile;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_745;

public class FakePlayerEntity
extends class_745 {
    public FakePlayerEntity(String string, float f, boolean bl) {
        super(Utils.mc.field_1687, new GameProfile(Utils.mc.field_1724.method_5667(), string));
        this.method_5719((class_1297)Utils.mc.field_1724);
        this.field_6241 = Utils.mc.field_1724.field_6241;
        this.field_6283 = Utils.mc.field_1724.field_6283;
        Byte by = (Byte)Utils.mc.field_1724.method_5841().method_12789(class_1657.field_7518);
        this.field_6011.method_12778(class_1657.field_7518, (Object)by);
        this.method_6127().method_26846(Utils.mc.field_1724.method_6127());
        this.field_7500 = this.method_23317();
        this.field_7521 = this.method_23318();
        this.field_7499 = this.method_23321();
        if (f <= 20.0f) {
            this.method_6033(f);
        } else {
            this.method_6033(f);
            this.method_6073(f - 20.0f);
        }
        if (bl) {
            this.field_7514.method_7377(Utils.mc.field_1724.field_7514);
        }
        this.spawn();
    }

    public void despawn() {
        Utils.mc.field_1687.method_2945(this.method_5628());
        this.field_5988 = true;
    }

    private void spawn() {
        this.field_5988 = false;
        Utils.mc.field_1687.method_2942(this.method_5628(), (class_1297)this);
    }
}

