/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1802
 *  net.minecraft.class_2244
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2879
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2879;

public class AntiBed
extends Module {
    private final Setting<Boolean> placeStringMiddle;
    private final Setting<Boolean> placeStringBottom;
    private boolean breaking;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> placeStringTop;
    private final Setting<Boolean> onlyInHole;

    private void lambda$onTick$1(class_2338 class_23382) {
        this.sendStopPackets(class_23382);
    }

    private void lambda$onTick$0(class_2338 class_23382) {
        this.sendMinePackets(class_23382);
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.onlyInHole.get().booleanValue() && !PlayerUtils.isInHole(true)) {
            return;
        }
        class_2338 class_23382 = this.mc.field_1724.method_24515().method_10084();
        if (this.mc.field_1687.method_8320(class_23382).method_26204() instanceof class_2244 && !this.breaking) {
            Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 50, () -> this.lambda$onTick$0(class_23382));
            this.breaking = true;
        } else if (this.breaking) {
            Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 50, () -> this.lambda$onTick$1(class_23382));
            this.breaking = false;
        }
        if (this.placeStringTop.get().booleanValue()) {
            this.place(this.mc.field_1724.method_24515().method_10086(2));
        }
        if (this.placeStringMiddle.get().booleanValue()) {
            this.place(this.mc.field_1724.method_24515().method_10086(1));
        }
        if (this.placeStringBottom.get().booleanValue()) {
            this.place(this.mc.field_1724.method_24515());
        }
    }

    public AntiBed() {
        super(Categories.Combat, "anti-bed", "Places string to prevent beds being placed on you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.placeStringTop = this.sgGeneral.add(new BoolSetting.Builder().name("place-string-top").description("Places string above you.").defaultValue(false).build());
        this.placeStringMiddle = this.sgGeneral.add(new BoolSetting.Builder().name("place-string-middle").description("Places string in your upper hitbox.").defaultValue(true).build());
        this.placeStringBottom = this.sgGeneral.add(new BoolSetting.Builder().name("place-string-bottom").description("Places string at your feet.").defaultValue(false).build());
        this.onlyInHole = this.sgGeneral.add(new BoolSetting.Builder().name("only-in-hole").description("Only functions when you are standing in a hole.").defaultValue(true).build());
    }

    private void place(class_2338 class_23382) {
        if (this.mc.field_1687.method_8320(class_23382).method_26204().method_8389() != class_1802.field_8276) {
            BlockUtils.place(class_23382, InvUtils.findInHotbar(class_1802.field_8276), 50, false);
        }
    }

    private void sendStopPackets(class_2338 class_23382) {
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, class_23382, class_2350.field_11036));
        this.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
    }

    private void sendMinePackets(class_2338 class_23382) {
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, class_23382, class_2350.field_11036));
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, class_23382, class_2350.field_11036));
    }
}

