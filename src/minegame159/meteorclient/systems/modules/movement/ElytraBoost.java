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
    private final Setting<Boolean> playSound;
    private final Setting<Keybind> keybind;
    private final Setting<Boolean> dontConsumeFirework;
    private final SettingGroup sgGeneral;
    private final List<class_1671> fireworks;
    private final Setting<Integer> fireworkLevel;

    @Override
    public void onDeactivate() {
        this.fireworks.clear();
    }

    private static boolean lambda$onTick$0(class_1671 class_16712) {
        return class_16712.field_5988;
    }

    private void boost() {
        if (!Utils.canUpdate()) {
            return;
        }
        if (this.mc.field_1724.method_6128() && this.mc.field_1755 == null) {
            class_1799 class_17992 = class_1802.field_8639.method_7854();
            class_17992.method_7911("Fireworks").method_10567("Flight", this.fireworkLevel.get().byteValue());
            class_1671 class_16712 = new class_1671((class_1937)this.mc.field_1687, class_17992, (class_1309)this.mc.field_1724);
            this.fireworks.add(class_16712);
            if (this.playSound.get().booleanValue()) {
                this.mc.field_1687.method_8449((class_1657)this.mc.field_1724, (class_1297)class_16712, class_3417.field_14702, class_3419.field_15256, 3.0f, 1.0f);
            }
            this.mc.field_1687.method_2942(class_16712.method_5628(), (class_1297)class_16712);
        }
    }

    public ElytraBoost() {
        super(Categories.Movement, "elytra-boost", "Boosts your elytra as if you used a firework.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.dontConsumeFirework = this.sgGeneral.add(new BoolSetting.Builder().name("anti-consume").description("Prevents fireworks from being consumed when using Elytra Boost.").defaultValue(true).build());
        this.fireworkLevel = this.sgGeneral.add(new IntSetting.Builder().name("firework-duration").description("The duration of the firework.").defaultValue(0).min(0).max(255).build());
        this.playSound = this.sgGeneral.add(new BoolSetting.Builder().name("play-sound").description("Plays the firework sound when a boost is triggered.").defaultValue(true).build());
        this.keybind = this.sgGeneral.add(new KeybindSetting.Builder().name("keybind").description("The keybind to boost.").action(this::boost).build());
        this.fireworks = new ArrayList<class_1671>();
    }

    public boolean isFirework(class_1671 class_16712) {
        return this.isActive() && this.fireworks.contains((Object)class_16712);
    }

    @EventHandler
    private void onInteractItem(InteractItemEvent interactItemEvent) {
        class_1799 class_17992 = this.mc.field_1724.method_5998(interactItemEvent.hand);
        if (class_17992.method_7909() instanceof class_1781 && this.dontConsumeFirework.get().booleanValue()) {
            interactItemEvent.toReturn = class_1269.field_5811;
            this.boost();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.fireworks.removeIf(ElytraBoost::lambda$onTick$0);
    }
}

