/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2828
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.PlayerMoveC2SPacketAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_243;
import net.minecraft.class_2828;

public class Flight
extends Module {
    private /* synthetic */ long lastModifiedTime;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ float lastYaw;
    private /* synthetic */ double lastY;
    private final /* synthetic */ Setting<Integer> offTime;
    private /* synthetic */ int offLeft;
    private final /* synthetic */ Setting<Boolean> verticalSpeedMatch;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int delayLeft;
    private final /* synthetic */ SettingGroup sgAntiKick;
    private /* synthetic */ boolean flip;
    private final /* synthetic */ Setting<AntiKickMode> antiKickMode;

    @EventHandler
    private void onPreTick(TickEvent.Pre llllllllllllllllllllIllllllIIIIl) {
        Flight llllllllllllllllllllIllllllIIIlI;
        float llllllllllllllllllllIllllllIIIII = llllllllllllllllllllIllllllIIIlI.mc.field_1724.field_6031;
        if (llllllllllllllllllllIllllllIIIlI.mc.field_1724.field_6017 >= 3.0f && llllllllllllllllllllIllllllIIIII == llllllllllllllllllllIllllllIIIlI.lastYaw && llllllllllllllllllllIllllllIIIlI.mc.field_1724.method_18798().method_1033() < 0.003) {
            llllllllllllllllllllIllllllIIIlI.mc.field_1724.field_6031 += llllllllllllllllllllIllllllIIIlI.flip ? 1.0f : -1.0f;
            llllllllllllllllllllIllllllIIIlI.flip = !llllllllllllllllllllIllllllIIIlI.flip;
        }
        llllllllllllllllllllIllllllIIIlI.lastYaw = llllllllllllllllllllIllllllIIIII;
    }

    @EventHandler
    private void onPostTick(TickEvent.Post llllllllllllllllllllIlllllIllIIl) {
        Flight llllllllllllllllllllIlllllIllIlI;
        if (llllllllllllllllllllIlllllIllIlI.antiKickMode.get() == AntiKickMode.Normal && llllllllllllllllllllIlllllIllIlI.delayLeft > 0) {
            --llllllllllllllllllllIlllllIllIlI.delayLeft;
        } else {
            if (llllllllllllllllllllIlllllIllIlI.antiKickMode.get() == AntiKickMode.Normal && llllllllllllllllllllIlllllIllIlI.delayLeft <= 0 && llllllllllllllllllllIlllllIllIlI.offLeft > 0) {
                --llllllllllllllllllllIlllllIllIlI.offLeft;
                if (llllllllllllllllllllIlllllIllIlI.mode.get() == Mode.Abilities) {
                    llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7479 = false;
                    llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.method_7248(0.05f);
                    if (llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7477) {
                        return;
                    }
                    llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7478 = false;
                }
                return;
            }
            if (llllllllllllllllllllIlllllIllIlI.antiKickMode.get() == AntiKickMode.Normal && llllllllllllllllllllIlllllIllIlI.delayLeft <= 0 && llllllllllllllllllllIlllllIllIlI.offLeft <= 0) {
                llllllllllllllllllllIlllllIllIlI.delayLeft = llllllllllllllllllllIlllllIllIlI.delay.get();
                llllllllllllllllllllIlllllIllIlI.offLeft = llllllllllllllllllllIlllllIllIlI.offTime.get();
            }
        }
        if (llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_6031 != llllllllllllllllllllIlllllIllIlI.lastYaw) {
            llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_6031 = llllllllllllllllllllIlllllIllIlI.lastYaw;
        }
        switch (llllllllllllllllllllIlllllIllIlI.mode.get()) {
            case Velocity: {
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7479 = false;
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_6281 = llllllllllllllllllllIlllllIllIlI.speed.get().floatValue() * (llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_5624() ? 15.0f : 10.0f);
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_18800(0.0, 0.0, 0.0);
                class_243 llllllllllllllllllllIlllllIllIll = llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_18798();
                if (llllllllllllllllllllIlllllIllIlI.mc.field_1690.field_1903.method_1434()) {
                    llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_18799(llllllllllllllllllllIlllllIllIll.method_1031(0.0, llllllllllllllllllllIlllllIllIlI.speed.get() * (double)(llllllllllllllllllllIlllllIllIlI.verticalSpeedMatch.get() != false ? 10.0f : 5.0f), 0.0));
                }
                if (!llllllllllllllllllllIlllllIllIlI.mc.field_1690.field_1832.method_1434()) break;
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_18799(llllllllllllllllllllIlllllIllIll.method_1023(0.0, llllllllllllllllllllIlllllIllIlI.speed.get() * (double)(llllllllllllllllllllIlllllIllIlI.verticalSpeedMatch.get() != false ? 10.0f : 5.0f), 0.0));
                break;
            }
            case Abilities: {
                if (llllllllllllllllllllIlllllIllIlI.mc.field_1724.method_7325()) {
                    return;
                }
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.method_7248(llllllllllllllllllllIlllllIllIlI.speed.get().floatValue());
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7479 = true;
                if (llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7477) {
                    return;
                }
                llllllllllllllllllllIlllllIllIlI.mc.field_1724.field_7503.field_7478 = true;
            }
        }
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send llllllllllllllllllllIlllllIlIIII) {
        Flight llllllllllllllllllllIlllllIlIIIl;
        if (!(llllllllllllllllllllIlllllIlIIII.packet instanceof class_2828) || llllllllllllllllllllIlllllIlIIIl.antiKickMode.get() != AntiKickMode.Packet) {
            return;
        }
        class_2828 llllllllllllllllllllIlllllIIllll = (class_2828)llllllllllllllllllllIlllllIlIIII.packet;
        long llllllllllllllllllllIlllllIIlllI = System.currentTimeMillis();
        double llllllllllllllllllllIlllllIIllIl = llllllllllllllllllllIlllllIIllll.method_12268(Double.MAX_VALUE);
        if (llllllllllllllllllllIlllllIIllIl != Double.MAX_VALUE) {
            if (llllllllllllllllllllIlllllIIlllI - llllllllllllllllllllIlllllIlIIIl.lastModifiedTime > 1000L && llllllllllllllllllllIlllllIlIIIl.lastY != Double.MAX_VALUE && llllllllllllllllllllIlllllIlIIIl.mc.field_1687.method_8320(llllllllllllllllllllIlllllIlIIIl.mc.field_1724.method_24515().method_10074()).method_26215()) {
                ((PlayerMoveC2SPacketAccessor)llllllllllllllllllllIlllllIIllll).setY(llllllllllllllllllllIlllllIlIIIl.lastY - 0.0313);
                llllllllllllllllllllIlllllIlIIIl.lastModifiedTime = llllllllllllllllllllIlllllIIlllI;
            } else {
                llllllllllllllllllllIlllllIlIIIl.lastY = llllllllllllllllllllIlllllIIllIl;
            }
        }
    }

    @Override
    public void onDeactivate() {
        Flight llllllllllllllllllllIllllllIIlIl;
        if (llllllllllllllllllllIllllllIIlIl.mode.get() == Mode.Abilities && !llllllllllllllllllllIllllllIIlIl.mc.field_1724.method_7325()) {
            llllllllllllllllllllIllllllIIlIl.mc.field_1724.field_7503.field_7479 = false;
            llllllllllllllllllllIllllllIIlIl.mc.field_1724.field_7503.method_7248(0.05f);
            if (llllllllllllllllllllIllllllIIlIl.mc.field_1724.field_7503.field_7477) {
                return;
            }
            llllllllllllllllllllIllllllIIlIl.mc.field_1724.field_7503.field_7478 = false;
        }
    }

    @Override
    public void onActivate() {
        Flight llllllllllllllllllllIllllllIlIII;
        if (llllllllllllllllllllIllllllIlIII.mode.get() == Mode.Abilities && !llllllllllllllllllllIllllllIlIII.mc.field_1724.method_7325()) {
            llllllllllllllllllllIllllllIlIII.mc.field_1724.field_7503.field_7479 = true;
            if (llllllllllllllllllllIllllllIlIII.mc.field_1724.field_7503.field_7477) {
                return;
            }
            llllllllllllllllllllIllllllIlIII.mc.field_1724.field_7503.field_7478 = true;
        }
    }

    public Flight() {
        super(Categories.Movement, "flight", "FLYYYY! No Fall is recommended with this module.");
        Flight llllllllllllllllllllIllllllIllII;
        llllllllllllllllllllIllllllIllII.sgGeneral = llllllllllllllllllllIllllllIllII.settings.getDefaultGroup();
        llllllllllllllllllllIllllllIllII.sgAntiKick = llllllllllllllllllllIllllllIllII.settings.createGroup("Anti Kick");
        llllllllllllllllllllIllllllIllII.mode = llllllllllllllllllllIllllllIllII.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode for Flight.").defaultValue(Mode.Abilities).build());
        llllllllllllllllllllIllllllIllII.speed = llllllllllllllllllllIllllllIllII.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Your speed when flying.").defaultValue(0.1).min(0.0).build());
        llllllllllllllllllllIllllllIllII.verticalSpeedMatch = llllllllllllllllllllIllllllIllII.sgGeneral.add(new BoolSetting.Builder().name("vertical-speed-match").description("Matches your vertical speed to your horizontal speed, otherwise uses vanilla ratio.").defaultValue(false).build());
        llllllllllllllllllllIllllllIllII.antiKickMode = llllllllllllllllllllIllllllIllII.sgAntiKick.add(new EnumSetting.Builder().name("mode").description("The mode for anti kick.").defaultValue(AntiKickMode.Packet).build());
        llllllllllllllllllllIllllllIllII.delay = llllllllllllllllllllIllllllIllII.sgAntiKick.add(new IntSetting.Builder().name("delay").description("The amount of delay, in ticks, between toggles in normal mode.").defaultValue(80).min(1).max(5000).sliderMax(200).visible(() -> {
            Flight llllllllllllllllllllIlllllIIIIlI;
            return llllllllllllllllllllIlllllIIIIlI.antiKickMode.get() == AntiKickMode.Normal;
        }).build());
        llllllllllllllllllllIllllllIllII.offTime = llllllllllllllllllllIllllllIllII.sgAntiKick.add(new IntSetting.Builder().name("off-time").description("The amount of delay, in ticks, that Flight is toggled off for in normal mode.").defaultValue(5).min(1).max(20).sliderMax(10).visible(() -> {
            Flight llllllllllllllllllllIlllllIIIlIl;
            return llllllllllllllllllllIlllllIIIlIl.antiKickMode.get() == AntiKickMode.Normal;
        }).build());
        llllllllllllllllllllIllllllIllII.delayLeft = llllllllllllllllllllIllllllIllII.delay.get();
        llllllllllllllllllllIllllllIllII.offLeft = llllllllllllllllllllIllllllIllII.offTime.get();
        llllllllllllllllllllIllllllIllII.lastModifiedTime = 0L;
        llllllllllllllllllllIllllllIllII.lastY = Double.MAX_VALUE;
    }

    public static enum AntiKickMode {
        Normal,
        Packet,
        None;


        private AntiKickMode() {
            AntiKickMode lIllIIlIlIllll;
        }
    }

    public static enum Mode {
        Abilities,
        Velocity;


        private Mode() {
            Mode llllllllllllllllllllIlllIlllIIIl;
        }
    }
}

