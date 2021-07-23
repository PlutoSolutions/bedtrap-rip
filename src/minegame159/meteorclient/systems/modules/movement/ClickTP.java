/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Double> maxDistance;
    private final SettingGroup sgGeneral;

    public ClickTP() {
        super(Categories.Movement, "click-tp", "Teleports you to the block you click on.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.maxDistance = this.sgGeneral.add(new DoubleSetting.Builder().name("max-distance").description("The maximum distance you can teleport.").defaultValue(5.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1724.method_6115()) {
            return;
        }
        if (this.mc.field_1690.field_1904.method_1434()) {
            class_239 class_2392 = this.mc.field_1724.method_5745(this.maxDistance.get().doubleValue(), 0.05f, false);
            if (class_2392.method_17783() == class_239.class_240.field_1331 && this.mc.field_1724.method_7287(((class_3966)class_2392).method_17782(), class_1268.field_5808) != class_1269.field_5811) {
                return;
            }
            if (class_2392.method_17783() == class_239.class_240.field_1332) {
                class_2338 class_23382 = ((class_3965)class_2392).method_17777();
                class_2350 class_23502 = ((class_3965)class_2392).method_17780();
                if (this.mc.field_1687.method_8320(class_23382).method_26174((class_1937)this.mc.field_1687, (class_1657)this.mc.field_1724, class_1268.field_5808, (class_3965)class_2392) != class_1269.field_5811) {
                    return;
                }
                class_2680 class_26802 = this.mc.field_1687.method_8320(class_23382);
                class_265 class_2652 = class_26802.method_26220((class_1922)this.mc.field_1687, class_23382);
                if (class_2652.method_1110()) {
                    class_2652 = class_26802.method_26218((class_1922)this.mc.field_1687, class_23382);
                }
                double d = class_2652.method_1110() ? 1.0 : class_2652.method_1105(class_2350.class_2351.field_11052);
                this.mc.field_1724.method_5814((double)class_23382.method_10263() + 0.5 + (double)class_23502.method_10148(), (double)class_23382.method_10264() + d, (double)class_23382.method_10260() + 0.5 + (double)class_23502.method_10165());
            }
        }
    }
}

