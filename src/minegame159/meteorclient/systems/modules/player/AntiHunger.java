/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.PlayerMoveC2SPacketAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class AntiHunger
extends Module {
    private /* synthetic */ boolean lastOnGround;
    private final /* synthetic */ Setting<Boolean> sprint;
    private /* synthetic */ boolean ignorePacket;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> onGround;
    private /* synthetic */ boolean sendOnGroundTruePacket;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllIIlllIIllIIlI) {
        AntiHunger lllllllllllllllllllIIlllIIllIIIl;
        if (lllllllllllllllllllIIlllIIllIIIl.mc.field_1724.method_24828() && !lllllllllllllllllllIIlllIIllIIIl.lastOnGround && !lllllllllllllllllllIIlllIIllIIIl.sendOnGroundTruePacket) {
            lllllllllllllllllllIIlllIIllIIIl.sendOnGroundTruePacket = true;
        }
        if (lllllllllllllllllllIIlllIIllIIIl.mc.field_1724.method_24828() && lllllllllllllllllllIIlllIIllIIIl.sendOnGroundTruePacket && lllllllllllllllllllIIlllIIllIIIl.onGround.get().booleanValue()) {
            lllllllllllllllllllIIlllIIllIIIl.ignorePacket = true;
            lllllllllllllllllllIIlllIIllIIIl.mc.method_1562().method_2883((class_2596)new class_2828(true));
            lllllllllllllllllllIIlllIIllIIIl.ignorePacket = false;
            lllllllllllllllllllIIlllIIllIIIl.sendOnGroundTruePacket = false;
        }
        lllllllllllllllllllIIlllIIllIIIl.lastOnGround = lllllllllllllllllllIIlllIIllIIIl.mc.field_1724.method_24828();
    }

    @Override
    public void onActivate() {
        AntiHunger lllllllllllllllllllIIlllIIllllll;
        lllllllllllllllllllIIlllIIllllll.lastOnGround = lllllllllllllllllllIIlllIIllllll.mc.field_1724.method_24828();
        lllllllllllllllllllIIlllIIllllll.sendOnGroundTruePacket = true;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send lllllllllllllllllllIIlllIIllIllI) {
        class_2848.class_2849 lllllllllllllllllllIIlllIIlllIlI;
        AntiHunger lllllllllllllllllllIIlllIIlllIIl;
        if (lllllllllllllllllllIIlllIIlllIIl.ignorePacket) {
            return;
        }
        if (lllllllllllllllllllIIlllIIllIllI.packet instanceof class_2848 && lllllllllllllllllllIIlllIIlllIIl.sprint.get().booleanValue() && ((lllllllllllllllllllIIlllIIlllIlI = ((class_2848)lllllllllllllllllllIIlllIIllIllI.packet).method_12365()) == class_2848.class_2849.field_12981 || lllllllllllllllllllIIlllIIlllIlI == class_2848.class_2849.field_12985)) {
            lllllllllllllllllllIIlllIIllIllI.cancel();
        }
        if (lllllllllllllllllllIIlllIIllIllI.packet instanceof class_2828 && lllllllllllllllllllIIlllIIlllIIl.onGround.get().booleanValue() && lllllllllllllllllllIIlllIIlllIIl.mc.field_1724.method_24828() && (double)lllllllllllllllllllIIlllIIlllIIl.mc.field_1724.field_6017 <= 0.0 && !lllllllllllllllllllIIlllIIlllIIl.mc.field_1761.method_2923()) {
            ((PlayerMoveC2SPacketAccessor)lllllllllllllllllllIIlllIIllIllI.packet).setOnGround(false);
        }
    }

    public AntiHunger() {
        super(Categories.Player, "anti-hunger", "Reduces (does NOT remove) hunger consumption.");
        AntiHunger lllllllllllllllllllIIlllIlIIIIlI;
        lllllllllllllllllllIIlllIlIIIIlI.sgGeneral = lllllllllllllllllllIIlllIlIIIIlI.settings.getDefaultGroup();
        lllllllllllllllllllIIlllIlIIIIlI.sprint = lllllllllllllllllllIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("sprint").description("Spoofs sprinting packets.").defaultValue(true).build());
        lllllllllllllllllllIIlllIlIIIIlI.onGround = lllllllllllllllllllIIlllIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("on-ground").description("Spoofs the onGround flag.").defaultValue(true).build());
    }
}

