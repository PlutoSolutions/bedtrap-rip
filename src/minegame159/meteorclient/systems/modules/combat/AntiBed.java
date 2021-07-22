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
    private final /* synthetic */ Setting<Boolean> placeStringMiddle;
    private final /* synthetic */ Setting<Boolean> placeStringBottom;
    private /* synthetic */ boolean breaking;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> placeStringTop;
    private final /* synthetic */ Setting<Boolean> onlyInHole;

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIllIlIIlIllIllll) {
        AntiBed llllllllllllllllIllIlIIlIllIllIl;
        if (llllllllllllllllIllIlIIlIllIllIl.onlyInHole.get().booleanValue() && !PlayerUtils.isInHole(true)) {
            return;
        }
        class_2338 llllllllllllllllIllIlIIlIllIlllI = llllllllllllllllIllIlIIlIllIllIl.mc.field_1724.method_24515().method_10084();
        if (llllllllllllllllIllIlIIlIllIllIl.mc.field_1687.method_8320(llllllllllllllllIllIlIIlIllIlllI).method_26204() instanceof class_2244 && !llllllllllllllllIllIlIIlIllIllIl.breaking) {
            Rotations.rotate(Rotations.getYaw(llllllllllllllllIllIlIIlIllIlllI), Rotations.getPitch(llllllllllllllllIllIlIIlIllIlllI), 50, () -> {
                AntiBed llllllllllllllllIllIlIIlIlIIllll;
                llllllllllllllllIllIlIIlIlIIllll.sendMinePackets(llllllllllllllllIllIlIIlIllIlllI);
            });
            llllllllllllllllIllIlIIlIllIllIl.breaking = true;
        } else if (llllllllllllllllIllIlIIlIllIllIl.breaking) {
            Rotations.rotate(Rotations.getYaw(llllllllllllllllIllIlIIlIllIlllI), Rotations.getPitch(llllllllllllllllIllIlIIlIllIlllI), 50, () -> {
                AntiBed llllllllllllllllIllIlIIlIlIlIlIl;
                llllllllllllllllIllIlIIlIlIlIlIl.sendStopPackets(llllllllllllllllIllIlIIlIllIlllI);
            });
            llllllllllllllllIllIlIIlIllIllIl.breaking = false;
        }
        if (llllllllllllllllIllIlIIlIllIllIl.placeStringTop.get().booleanValue()) {
            llllllllllllllllIllIlIIlIllIllIl.place(llllllllllllllllIllIlIIlIllIllIl.mc.field_1724.method_24515().method_10086(2));
        }
        if (llllllllllllllllIllIlIIlIllIllIl.placeStringMiddle.get().booleanValue()) {
            llllllllllllllllIllIlIIlIllIllIl.place(llllllllllllllllIllIlIIlIllIllIl.mc.field_1724.method_24515().method_10086(1));
        }
        if (llllllllllllllllIllIlIIlIllIllIl.placeStringBottom.get().booleanValue()) {
            llllllllllllllllIllIlIIlIllIllIl.place(llllllllllllllllIllIlIIlIllIllIl.mc.field_1724.method_24515());
        }
    }

    public AntiBed() {
        super(Categories.Combat, "anti-bed", "Places string to prevent beds being placed on you.");
        AntiBed llllllllllllllllIllIlIIlIlllIlII;
        llllllllllllllllIllIlIIlIlllIlII.sgGeneral = llllllllllllllllIllIlIIlIlllIlII.settings.getDefaultGroup();
        llllllllllllllllIllIlIIlIlllIlII.placeStringTop = llllllllllllllllIllIlIIlIlllIlII.sgGeneral.add(new BoolSetting.Builder().name("place-string-top").description("Places string above you.").defaultValue(false).build());
        llllllllllllllllIllIlIIlIlllIlII.placeStringMiddle = llllllllllllllllIllIlIIlIlllIlII.sgGeneral.add(new BoolSetting.Builder().name("place-string-middle").description("Places string in your upper hitbox.").defaultValue(true).build());
        llllllllllllllllIllIlIIlIlllIlII.placeStringBottom = llllllllllllllllIllIlIIlIlllIlII.sgGeneral.add(new BoolSetting.Builder().name("place-string-bottom").description("Places string at your feet.").defaultValue(false).build());
        llllllllllllllllIllIlIIlIlllIlII.onlyInHole = llllllllllllllllIllIlIIlIlllIlII.sgGeneral.add(new BoolSetting.Builder().name("only-in-hole").description("Only functions when you are standing in a hole.").defaultValue(true).build());
    }

    private void place(class_2338 llllllllllllllllIllIlIIlIllIlIII) {
        AntiBed llllllllllllllllIllIlIIlIllIlIIl;
        if (llllllllllllllllIllIlIIlIllIlIIl.mc.field_1687.method_8320(llllllllllllllllIllIlIIlIllIlIII).method_26204().method_8389() != class_1802.field_8276) {
            BlockUtils.place(llllllllllllllllIllIlIIlIllIlIII, InvUtils.findInHotbar(class_1802.field_8276), 50, false);
        }
    }

    private void sendStopPackets(class_2338 llllllllllllllllIllIlIIlIlIlllII) {
        AntiBed llllllllllllllllIllIlIIlIlIllIll;
        llllllllllllllllIllIlIIlIlIllIll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, llllllllllllllllIllIlIIlIlIlllII, class_2350.field_11036));
        llllllllllllllllIllIlIIlIlIllIll.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
    }

    private void sendMinePackets(class_2338 llllllllllllllllIllIlIIlIllIIIII) {
        AntiBed llllllllllllllllIllIlIIlIllIIIll;
        llllllllllllllllIllIlIIlIllIIIll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, llllllllllllllllIllIlIIlIllIIIII, class_2350.field_11036));
        llllllllllllllllIllIlIIlIllIIIll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, llllllllllllllllIllIlIIlIllIIIII, class_2350.field_11036));
    }
}

