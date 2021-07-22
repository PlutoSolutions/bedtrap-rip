/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class ClickTP
extends Module {
    private final /* synthetic */ Setting<Double> maxDistance;
    private final /* synthetic */ SettingGroup sgGeneral;

    public ClickTP() {
        super(Categories.Movement, "click-tp", "Teleports you to the block you click on.");
        ClickTP lllllllllllllllllllIIlIIIIlIlIIl;
        lllllllllllllllllllIIlIIIIlIlIIl.sgGeneral = lllllllllllllllllllIIlIIIIlIlIIl.settings.getDefaultGroup();
        lllllllllllllllllllIIlIIIIlIlIIl.maxDistance = lllllllllllllllllllIIlIIIIlIlIIl.sgGeneral.add(new DoubleSetting.Builder().name("max-distance").description("The maximum distance you can teleport.").defaultValue(5.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllIIlIIIIIllIIl) {
        ClickTP lllllllllllllllllllIIlIIIIIllIlI;
        if (lllllllllllllllllllIIlIIIIIllIlI.mc.field_1724.method_6115()) {
            return;
        }
        if (lllllllllllllllllllIIlIIIIIllIlI.mc.field_1690.field_1904.method_1434()) {
            class_239 lllllllllllllllllllIIlIIIIIllIll = lllllllllllllllllllIIlIIIIIllIlI.mc.field_1724.method_5745(lllllllllllllllllllIIlIIIIIllIlI.maxDistance.get().doubleValue(), 0.05f, false);
            if (lllllllllllllllllllIIlIIIIIllIll.method_17783() == class_239.class_240.field_1331 && lllllllllllllllllllIIlIIIIIllIlI.mc.field_1724.method_7287(((class_3966)lllllllllllllllllllIIlIIIIIllIll).method_17782(), class_1268.field_5808) != class_1269.field_5811) {
                return;
            }
            if (lllllllllllllllllllIIlIIIIIllIll.method_17783() == class_239.class_240.field_1332) {
                class_2338 lllllllllllllllllllIIlIIIIlIIIII = ((class_3965)lllllllllllllllllllIIlIIIIIllIll).method_17777();
                class_2350 lllllllllllllllllllIIlIIIIIlllll = ((class_3965)lllllllllllllllllllIIlIIIIIllIll).method_17780();
                if (lllllllllllllllllllIIlIIIIIllIlI.mc.field_1687.method_8320(lllllllllllllllllllIIlIIIIlIIIII).method_26174((class_1937)lllllllllllllllllllIIlIIIIIllIlI.mc.field_1687, (class_1657)lllllllllllllllllllIIlIIIIIllIlI.mc.field_1724, class_1268.field_5808, (class_3965)lllllllllllllllllllIIlIIIIIllIll) != class_1269.field_5811) {
                    return;
                }
                class_2680 lllllllllllllllllllIIlIIIIIllllI = lllllllllllllllllllIIlIIIIIllIlI.mc.field_1687.method_8320(lllllllllllllllllllIIlIIIIlIIIII);
                class_265 lllllllllllllllllllIIlIIIIIlllIl = lllllllllllllllllllIIlIIIIIllllI.method_26220((class_1922)lllllllllllllllllllIIlIIIIIllIlI.mc.field_1687, lllllllllllllllllllIIlIIIIlIIIII);
                if (lllllllllllllllllllIIlIIIIIlllIl.method_1110()) {
                    lllllllllllllllllllIIlIIIIIlllIl = lllllllllllllllllllIIlIIIIIllllI.method_26218((class_1922)lllllllllllllllllllIIlIIIIIllIlI.mc.field_1687, lllllllllllllllllllIIlIIIIlIIIII);
                }
                double lllllllllllllllllllIIlIIIIIlllII = lllllllllllllllllllIIlIIIIIlllIl.method_1110() ? 1.0 : lllllllllllllllllllIIlIIIIIlllIl.method_1105(class_2350.class_2351.field_11052);
                lllllllllllllllllllIIlIIIIIllIlI.mc.field_1724.method_5814((double)lllllllllllllllllllIIlIIIIlIIIII.method_10263() + 0.5 + (double)lllllllllllllllllllIIlIIIIIlllll.method_10148(), (double)lllllllllllllllllllIIlIIIIlIIIII.method_10264() + lllllllllllllllllllIIlIIIIIlllII, (double)lllllllllllllllllllIIlIIIIlIIIII.method_10260() + 0.5 + (double)lllllllllllllllllllIIlIIIIIlllll.method_10165());
            }
        }
    }
}

