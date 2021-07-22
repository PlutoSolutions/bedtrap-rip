/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_2825
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2879
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IPlayerMoveC2SPacket;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.KillAura;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2879;

public class Criticals
extends Module {
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int sendTimer;
    private /* synthetic */ class_2824 attackPacket;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ class_2879 swingPacket;
    private /* synthetic */ boolean sendPackets;
    private final /* synthetic */ Setting<Boolean> ka;

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIllIIlIlIlllIlI) {
        Criticals lllllllllllllllllIllIIlIlIlllIll;
        if (lllllllllllllllllIllIIlIlIlllIll.sendPackets) {
            if (lllllllllllllllllIllIIlIlIlllIll.sendTimer <= 0) {
                lllllllllllllllllIllIIlIlIlllIll.sendPackets = false;
                if (lllllllllllllllllIllIIlIlIlllIll.attackPacket == null || lllllllllllllllllIllIIlIlIlllIll.swingPacket == null) {
                    return;
                }
                lllllllllllllllllIllIIlIlIlllIll.mc.method_1562().method_2883((class_2596)lllllllllllllllllIllIIlIlIlllIll.attackPacket);
                lllllllllllllllllIllIIlIlIlllIll.mc.method_1562().method_2883((class_2596)lllllllllllllllllIllIIlIlIlllIll.swingPacket);
                lllllllllllllllllIllIIlIlIlllIll.attackPacket = null;
                lllllllllllllllllIllIIlIlIlllIll.swingPacket = null;
            } else {
                --lllllllllllllllllIllIIlIlIlllIll.sendTimer;
            }
        }
    }

    private boolean skipCrit() {
        Criticals lllllllllllllllllIllIIlIlIlIIlII;
        return !lllllllllllllllllIllIIlIlIlIIlII.mc.field_1724.method_24828() || lllllllllllllllllIllIIlIlIlIIlII.mc.field_1724.method_5869() || lllllllllllllllllIllIIlIlIlIIlII.mc.field_1724.method_5771() || lllllllllllllllllIllIIlIlIlIIlII.mc.field_1724.method_6101();
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send lllllllllllllllllIllIIlIlIlllllI) {
        Criticals lllllllllllllllllIllIIlIlIllllll;
        if (lllllllllllllllllIllIIlIlIlllllI.packet instanceof class_2824 && ((class_2824)lllllllllllllllllIllIIlIlIlllllI.packet).method_12252() == class_2824.class_2825.field_12875) {
            if (lllllllllllllllllIllIIlIlIllllll.skipCrit()) {
                return;
            }
            class_1297 lllllllllllllllllIllIIlIllIIIIlI = ((class_2824)lllllllllllllllllIllIIlIlIlllllI.packet).method_12248((class_1937)lllllllllllllllllIllIIlIlIllllll.mc.field_1687);
            if (!(lllllllllllllllllIllIIlIllIIIIlI instanceof class_1309) || lllllllllllllllllIllIIlIllIIIIlI != Modules.get().get(KillAura.class).getTarget() && lllllllllllllllllIllIIlIlIllllll.ka.get().booleanValue()) {
                return;
            }
            switch (lllllllllllllllllIllIIlIlIllllll.mode.get()) {
                case Packet: {
                    lllllllllllllllllIllIIlIlIllllll.sendPacket(0.0625);
                    lllllllllllllllllIllIIlIlIllllll.sendPacket(0.0);
                    break;
                }
                case Bypass: {
                    lllllllllllllllllIllIIlIlIllllll.sendPacket(0.11);
                    lllllllllllllllllIllIIlIlIllllll.sendPacket(0.1100013579);
                    lllllllllllllllllIllIIlIlIllllll.sendPacket(1.3579E-6);
                    break;
                }
                default: {
                    if (!lllllllllllllllllIllIIlIlIllllll.sendPackets) {
                        lllllllllllllllllIllIIlIlIllllll.sendPackets = true;
                        lllllllllllllllllIllIIlIlIllllll.sendTimer = lllllllllllllllllIllIIlIlIllllll.mode.get() == Mode.Jump ? 6 : 4;
                        lllllllllllllllllIllIIlIlIllllll.attackPacket = (class_2824)lllllllllllllllllIllIIlIlIlllllI.packet;
                        if (lllllllllllllllllIllIIlIlIllllll.mode.get() == Mode.Jump) {
                            lllllllllllllllllIllIIlIlIllllll.mc.field_1724.method_6043();
                        } else {
                            ((IVec3d)lllllllllllllllllIllIIlIlIllllll.mc.field_1724.method_18798()).setY(0.25);
                        }
                        lllllllllllllllllIllIIlIlIlllllI.cancel();
                        break;
                    } else {
                        break;
                    }
                }
            }
        } else if (lllllllllllllllllIllIIlIlIlllllI.packet instanceof class_2879 && lllllllllllllllllIllIIlIlIllllll.mode.get() != Mode.Packet) {
            if (lllllllllllllllllIllIIlIlIllllll.skipCrit()) {
                return;
            }
            if (lllllllllllllllllIllIIlIlIllllll.sendPackets && lllllllllllllllllIllIIlIlIllllll.swingPacket == null) {
                lllllllllllllllllIllIIlIlIllllll.swingPacket = (class_2879)lllllllllllllllllIllIIlIlIlllllI.packet;
                lllllllllllllllllIllIIlIlIlllllI.cancel();
            }
        }
    }

    public Criticals() {
        super(Categories.Combat, "criticals", "Performs critical attacks when you hit your target.");
        Criticals lllllllllllllllllIllIIlIllIIlIIl;
        lllllllllllllllllIllIIlIllIIlIIl.sgGeneral = lllllllllllllllllIllIIlIllIIlIIl.settings.getDefaultGroup();
        lllllllllllllllllIllIIlIllIIlIIl.mode = lllllllllllllllllIllIIlIllIIlIIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode on how Criticals will function.").defaultValue(Mode.Packet).build());
        lllllllllllllllllIllIIlIllIIlIIl.ka = lllllllllllllllllIllIIlIllIIlIIl.sgGeneral.add(new BoolSetting.Builder().name("only-killaura").description("Only performs crits when using killaura.").defaultValue(false).build());
    }

    @Override
    public void onActivate() {
        lllllllllllllllllIllIIlIllIIIllI.attackPacket = null;
        lllllllllllllllllIllIIlIllIIIllI.swingPacket = null;
        lllllllllllllllllIllIIlIllIIIllI.sendPackets = false;
        lllllllllllllllllIllIIlIllIIIllI.sendTimer = 0;
    }

    @Override
    public String getInfoString() {
        Criticals lllllllllllllllllIllIIlIlIlIIIlI;
        return lllllllllllllllllIllIIlIlIlIIIlI.mode.get().name();
    }

    private void sendPacket(double lllllllllllllllllIllIIlIlIllIIIl) {
        Criticals lllllllllllllllllIllIIlIlIlIllII;
        double lllllllllllllllllIllIIlIlIllIIII = lllllllllllllllllIllIIlIlIlIllII.mc.field_1724.method_23317();
        double lllllllllllllllllIllIIlIlIlIllll = lllllllllllllllllIllIIlIlIlIllII.mc.field_1724.method_23318();
        double lllllllllllllllllIllIIlIlIlIlllI = lllllllllllllllllIllIIlIlIlIllII.mc.field_1724.method_23321();
        class_2828.class_2829 lllllllllllllllllIllIIlIlIlIllIl = new class_2828.class_2829(lllllllllllllllllIllIIlIlIllIIII, lllllllllllllllllIllIIlIlIlIllll + lllllllllllllllllIllIIlIlIllIIIl, lllllllllllllllllIllIIlIlIlIlllI, false);
        ((IPlayerMoveC2SPacket)lllllllllllllllllIllIIlIlIlIllIl).setTag(1337);
        lllllllllllllllllIllIIlIlIlIllII.mc.field_1724.field_3944.method_2883((class_2596)lllllllllllllllllIllIIlIlIlIllIl);
    }

    public static enum Mode {
        Packet,
        Bypass,
        Jump,
        MiniJump;


        private Mode() {
            Mode llllIIllIIIIlll;
        }
    }
}

