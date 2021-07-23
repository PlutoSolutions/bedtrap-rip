/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.BreakBlockEvent;
import minegame159.meteorclient.events.entity.player.PlaceBlockEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2622;
import net.minecraft.class_2626;
import net.minecraft.class_2846;

public class AntiGhostBlock
extends Module {
    private long lastRequest;
    private final Setting<Integer> sendDelay;
    private final Setting<Integer> requestDelay;
    private boolean lock;
    private final SettingGroup sgGeneral;
    private final HashMap<class_2338, Long> blocks;

    private void lambda$onTick$0(List list, long l, class_2338 class_23382, Long l2) {
        if (list.isEmpty() && l - l2 >= (long)this.requestDelay.get().intValue()) {
            this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, class_23382, class_2350.field_11036));
            list.add(class_23382.method_10062());
            this.lastRequest = l;
        }
    }

    @EventHandler
    private void onBlockBreak(BreakBlockEvent breakBlockEvent) {
        this.blocks.put(breakBlockEvent.blockPos.method_10062(), this.mc.field_1687.method_8510());
    }

    public AntiGhostBlock() {
        super(Categories.BedTrap, "anti-ghost-blocks", "Automatically remove ghost blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.requestDelay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("delay between updating block and sending request").defaultValue(3).min(1).sliderMin(1).sliderMax(200).build());
        this.sendDelay = this.sgGeneral.add(new IntSetting.Builder().name("delay-between").description("delay between requests").defaultValue(5).min(1).sliderMin(1).sliderMax(200).build());
        this.blocks = new HashMap();
        this.lock = false;
        this.lastRequest = 0L;
    }

    @EventHandler
    private void onGameDisconnect(GameLeftEvent gameLeftEvent) {
        this.blocks.clear();
        this.lastRequest = 0L;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        long l = this.mc.field_1687.method_8510();
        if (this.blocks.isEmpty() || this.mc.field_1761 == null || this.mc.field_1761.method_2923() || l - this.lastRequest < (long)this.sendDelay.get().intValue() || this.lock) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        this.blocks.forEach((arg_0, arg_1) -> this.lambda$onTick$0(arrayList, l, arg_0, arg_1));
        arrayList.forEach(this.blocks::remove);
    }

    @EventHandler
    private void onPacket(PacketEvent.Receive receive) {
        class_2626 class_26262;
        if (receive.packet instanceof class_2626 && this.blocks.containsKey((class_26262 = (class_2626)receive.packet).method_11309())) {
            this.blocks.remove(class_26262.method_11309());
        }
        if (receive.packet instanceof class_2622 && this.blocks.containsKey((class_26262 = (class_2622)receive.packet).method_11293())) {
            this.blocks.remove(class_26262.method_11293());
        }
    }

    @EventHandler
    private void onBlockPlace(PlaceBlockEvent placeBlockEvent) {
        this.blocks.put(placeBlockEvent.blockPos.method_10062(), this.mc.field_1687.method_8510());
    }
}

