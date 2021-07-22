/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1671
 *  net.minecraft.class_1781
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.InteractItemEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.KeybindSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Keybind;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1671;
import net.minecraft.class_1781;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_3417;
import net.minecraft.class_3419;

public class ElytraBoost
extends Module {
    private final /* synthetic */ Setting<Boolean> playSound;
    private final /* synthetic */ Setting<Keybind> keybind;
    private final /* synthetic */ Setting<Boolean> dontConsumeFirework;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ List<class_1671> fireworks;
    private final /* synthetic */ Setting<Integer> fireworkLevel;

    @Override
    public void onDeactivate() {
        ElytraBoost llllllllllllllllllIlIIIllIlIlIII;
        llllllllllllllllllIlIIIllIlIlIII.fireworks.clear();
    }

    private void boost() {
        ElytraBoost llllllllllllllllllIlIIIllIIlIIll;
        if (!Utils.canUpdate()) {
            return;
        }
        if (llllllllllllllllllIlIIIllIIlIIll.mc.field_1724.method_6128() && llllllllllllllllllIlIIIllIIlIIll.mc.field_1755 == null) {
            class_1799 llllllllllllllllllIlIIIllIIlIllI = class_1802.field_8639.method_7854();
            llllllllllllllllllIlIIIllIIlIllI.method_7911("Fireworks").method_10567("Flight", llllllllllllllllllIlIIIllIIlIIll.fireworkLevel.get().byteValue());
            class_1671 llllllllllllllllllIlIIIllIIlIlIl = new class_1671((class_1937)llllllllllllllllllIlIIIllIIlIIll.mc.field_1687, llllllllllllllllllIlIIIllIIlIllI, (class_1309)llllllllllllllllllIlIIIllIIlIIll.mc.field_1724);
            llllllllllllllllllIlIIIllIIlIIll.fireworks.add(llllllllllllllllllIlIIIllIIlIlIl);
            if (llllllllllllllllllIlIIIllIIlIIll.playSound.get().booleanValue()) {
                llllllllllllllllllIlIIIllIIlIIll.mc.field_1687.method_8449((class_1657)llllllllllllllllllIlIIIllIIlIIll.mc.field_1724, (class_1297)llllllllllllllllllIlIIIllIIlIlIl, class_3417.field_14702, class_3419.field_15256, 3.0f, 1.0f);
            }
            llllllllllllllllllIlIIIllIIlIIll.mc.field_1687.method_2942(llllllllllllllllllIlIIIllIIlIlIl.method_5628(), (class_1297)llllllllllllllllllIlIIIllIIlIlIl);
        }
    }

    public ElytraBoost() {
        super(Categories.Movement, "elytra-boost", "Boosts your elytra as if you used a firework.");
        ElytraBoost llllllllllllllllllIlIIIllIlIlIlI;
        llllllllllllllllllIlIIIllIlIlIlI.sgGeneral = llllllllllllllllllIlIIIllIlIlIlI.settings.getDefaultGroup();
        llllllllllllllllllIlIIIllIlIlIlI.dontConsumeFirework = llllllllllllllllllIlIIIllIlIlIlI.sgGeneral.add(new BoolSetting.Builder().name("anti-consume").description("Prevents fireworks from being consumed when using Elytra Boost.").defaultValue(true).build());
        llllllllllllllllllIlIIIllIlIlIlI.fireworkLevel = llllllllllllllllllIlIIIllIlIlIlI.sgGeneral.add(new IntSetting.Builder().name("firework-duration").description("The duration of the firework.").defaultValue(0).min(0).max(255).build());
        llllllllllllllllllIlIIIllIlIlIlI.playSound = llllllllllllllllllIlIIIllIlIlIlI.sgGeneral.add(new BoolSetting.Builder().name("play-sound").description("Plays the firework sound when a boost is triggered.").defaultValue(true).build());
        llllllllllllllllllIlIIIllIlIlIlI.keybind = llllllllllllllllllIlIIIllIlIlIlI.sgGeneral.add(new KeybindSetting.Builder().name("keybind").description("The keybind to boost.").action(llllllllllllllllllIlIIIllIlIlIlI::boost).build());
        llllllllllllllllllIlIIIllIlIlIlI.fireworks = new ArrayList<class_1671>();
    }

    public boolean isFirework(class_1671 llllllllllllllllllIlIIIllIIIlIll) {
        ElytraBoost llllllllllllllllllIlIIIllIIIllII;
        return llllllllllllllllllIlIIIllIIIllII.isActive() && llllllllllllllllllIlIIIllIIIllII.fireworks.contains((Object)llllllllllllllllllIlIIIllIIIlIll);
    }

    @EventHandler
    private void onInteractItem(InteractItemEvent llllllllllllllllllIlIIIllIlIIIlI) {
        ElytraBoost llllllllllllllllllIlIIIllIlIIIll;
        class_1799 llllllllllllllllllIlIIIllIlIIIIl = llllllllllllllllllIlIIIllIlIIIll.mc.field_1724.method_5998(llllllllllllllllllIlIIIllIlIIIlI.hand);
        if (llllllllllllllllllIlIIIllIlIIIIl.method_7909() instanceof class_1781 && llllllllllllllllllIlIIIllIlIIIll.dontConsumeFirework.get().booleanValue()) {
            llllllllllllllllllIlIIIllIlIIIlI.toReturn = class_1269.field_5811;
            llllllllllllllllllIlIIIllIlIIIll.boost();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIlIIIllIIllIll) {
        ElytraBoost llllllllllllllllllIlIIIllIIllIlI;
        llllllllllllllllllIlIIIllIIllIlI.fireworks.removeIf(llllllllllllllllllIlIIIllIIIlIIl -> llllllllllllllllllIlIIIllIIIlIIl.field_5988);
    }
}

