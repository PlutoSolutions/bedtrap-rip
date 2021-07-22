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
    private boolean lastOnGround;
    private final Setting<Boolean> sprint;
    private boolean ignorePacket;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> onGround;
    private boolean sendOnGroundTruePacket;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1724.method_24828() && !this.lastOnGround && !this.sendOnGroundTruePacket) {
            this.sendOnGroundTruePacket = true;
        }
        if (this.mc.field_1724.method_24828() && this.sendOnGroundTruePacket && this.onGround.get().booleanValue()) {
            this.ignorePacket = true;
            this.mc.method_1562().method_2883((class_2596)new class_2828(true));
            this.ignorePacket = false;
            this.sendOnGroundTruePacket = false;
        }
        this.lastOnGround = this.mc.field_1724.method_24828();
    }

    @Override
    public void onActivate() {
        this.lastOnGround = this.mc.field_1724.method_24828();
        this.sendOnGroundTruePacket = true;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        class_2848.class_2849 class_28492;
        if (this.ignorePacket) {
            return;
        }
        if (send.packet instanceof class_2848 && this.sprint.get().booleanValue() && ((class_28492 = ((class_2848)send.packet).method_12365()) == class_2848.class_2849.field_12981 || class_28492 == class_2848.class_2849.field_12985)) {
            send.cancel();
        }
        if (send.packet instanceof class_2828 && this.onGround.get().booleanValue() && this.mc.field_1724.method_24828() && (double)this.mc.field_1724.field_6017 <= 0.0 && !this.mc.field_1761.method_2923()) {
            ((PlayerMoveC2SPacketAccessor)send.packet).setOnGround(false);
        }
    }

    public AntiHunger() {
        super(Categories.Player, "anti-hunger", "Reduces (does NOT remove) hunger consumption.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sprint = this.sgGeneral.add(new BoolSetting.Builder().name("sprint").description("Spoofs sprinting packets.").defaultValue(true).build());
        this.onGround = this.sgGeneral.add(new BoolSetting.Builder().name("on-ground").description("Spoofs the onGround flag.").defaultValue(true).build());
    }
}

