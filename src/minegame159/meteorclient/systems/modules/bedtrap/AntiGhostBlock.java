/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2622
 *  net.minecraft.class_2626
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import java.util.ArrayList;
import java.util.HashMap;
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
    private /* synthetic */ long lastRequest;
    private final /* synthetic */ Setting<Integer> sendDelay;
    private final /* synthetic */ Setting<Integer> requestDelay;
    private /* synthetic */ boolean lock;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ HashMap<class_2338, Long> blocks;

    @EventHandler
    private void onBlockBreak(BreakBlockEvent llllllllllllllllllIlIlIIlIllIllI) {
        AntiGhostBlock llllllllllllllllllIlIlIIlIlllIIl;
        llllllllllllllllllIlIlIIlIlllIIl.blocks.put(llllllllllllllllllIlIlIIlIllIllI.blockPos.method_10062(), llllllllllllllllllIlIlIIlIlllIIl.mc.field_1687.method_8510());
    }

    public AntiGhostBlock() {
        super(Categories.BedTrap, "anti-ghost-blocks", "Automatically remove ghost blocks.");
        AntiGhostBlock llllllllllllllllllIlIlIIlIllllIl;
        llllllllllllllllllIlIlIIlIllllIl.sgGeneral = llllllllllllllllllIlIlIIlIllllIl.settings.getDefaultGroup();
        llllllllllllllllllIlIlIIlIllllIl.requestDelay = llllllllllllllllllIlIlIIlIllllIl.sgGeneral.add(new IntSetting.Builder().name("delay").description("delay between updating block and sending request").defaultValue(3).min(1).sliderMin(1).sliderMax(200).build());
        llllllllllllllllllIlIlIIlIllllIl.sendDelay = llllllllllllllllllIlIlIIlIllllIl.sgGeneral.add(new IntSetting.Builder().name("delay-between").description("delay between requests").defaultValue(5).min(1).sliderMin(1).sliderMax(200).build());
        llllllllllllllllllIlIlIIlIllllIl.blocks = new HashMap();
        llllllllllllllllllIlIlIIlIllllIl.lock = false;
        llllllllllllllllllIlIlIIlIllllIl.lastRequest = 0L;
    }

    @EventHandler
    private void onGameDisconnect(GameLeftEvent llllllllllllllllllIlIlIIlIlIllIl) {
        AntiGhostBlock llllllllllllllllllIlIlIIlIlIllII;
        llllllllllllllllllIlIlIIlIlIllII.blocks.clear();
        llllllllllllllllllIlIlIIlIlIllII.lastRequest = 0L;
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIlIlIIlIIlllIl) {
        AntiGhostBlock llllllllllllllllllIlIlIIlIIllllI;
        long llllllllllllllllllIlIlIIlIIlllII = llllllllllllllllllIlIlIIlIIllllI.mc.field_1687.method_8510();
        if (llllllllllllllllllIlIlIIlIIllllI.blocks.isEmpty() || llllllllllllllllllIlIlIIlIIllllI.mc.field_1761 == null || llllllllllllllllllIlIlIIlIIllllI.mc.field_1761.method_2923() || llllllllllllllllllIlIlIIlIIlllII - llllllllllllllllllIlIlIIlIIllllI.lastRequest < (long)llllllllllllllllllIlIlIIlIIllllI.sendDelay.get().intValue() || llllllllllllllllllIlIlIIlIIllllI.lock) {
            return;
        }
        ArrayList llllllllllllllllllIlIlIIlIIllIll = new ArrayList();
        llllllllllllllllllIlIlIIlIIllllI.blocks.forEach((llllllllllllllllllIlIlIIlIIIllll, llllllllllllllllllIlIlIIlIIIlllI) -> {
            AntiGhostBlock llllllllllllllllllIlIlIIlIIIllIl;
            if (llllllllllllllllllIlIlIIlIIllIll.isEmpty() && llllllllllllllllllIlIlIIlIIlllII - llllllllllllllllllIlIlIIlIIIlllI >= (long)llllllllllllllllllIlIlIIlIIIllIl.requestDelay.get().intValue()) {
                llllllllllllllllllIlIlIIlIIIllIl.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, llllllllllllllllllIlIlIIlIIIllll, class_2350.field_11036));
                llllllllllllllllllIlIlIIlIIllIll.add(llllllllllllllllllIlIlIIlIIIllll.method_10062());
                llllllllllllllllllIlIlIIlIIIllIl.lastRequest = llllllllllllllllllIlIlIIlIIlllII;
            }
        });
        llllllllllllllllllIlIlIIlIIllIll.forEach(llllllllllllllllllIlIlIIlIIllllI.blocks::remove);
    }

    @EventHandler
    private void onPacket(PacketEvent.Receive llllllllllllllllllIlIlIIlIlIIlIl) {
        class_2622 llllllllllllllllllIlIlIIlIlIIlll;
        class_2626 llllllllllllllllllIlIlIIlIlIlIII;
        AntiGhostBlock llllllllllllllllllIlIlIIlIlIIlII;
        if (llllllllllllllllllIlIlIIlIlIIlIl.packet instanceof class_2626 && llllllllllllllllllIlIlIIlIlIIlII.blocks.containsKey((Object)(llllllllllllllllllIlIlIIlIlIlIII = (class_2626)llllllllllllllllllIlIlIIlIlIIlIl.packet).method_11309())) {
            llllllllllllllllllIlIlIIlIlIIlII.blocks.remove((Object)llllllllllllllllllIlIlIIlIlIlIII.method_11309());
        }
        if (llllllllllllllllllIlIlIIlIlIIlIl.packet instanceof class_2622 && llllllllllllllllllIlIlIIlIlIIlII.blocks.containsKey((Object)(llllllllllllllllllIlIlIIlIlIIlll = (class_2622)llllllllllllllllllIlIlIIlIlIIlIl.packet).method_11293())) {
            llllllllllllllllllIlIlIIlIlIIlII.blocks.remove((Object)llllllllllllllllllIlIlIIlIlIIlll.method_11293());
        }
    }

    @EventHandler
    private void onBlockPlace(PlaceBlockEvent llllllllllllllllllIlIlIIlIllIIlI) {
        AntiGhostBlock llllllllllllllllllIlIlIIlIllIIIl;
        llllllllllllllllllIlIlIIlIllIIIl.blocks.put(llllllllllllllllllIlIlIIlIllIIlI.blockPos.method_10062(), llllllllllllllllllIlIlIIlIllIIIl.mc.field_1687.method_8510());
    }
}

