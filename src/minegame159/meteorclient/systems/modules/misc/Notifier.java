/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2663
 *  net.minecraft.class_5250
 */
package minegame159.meteorclient.systems.modules.misc;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Random;
import java.util.UUID;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.entity.EntityRemovedEvent;
import minegame159.meteorclient.events.game.GameJoinedEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2663;
import net.minecraft.class_5250;

public class Notifier
extends Module {
    private final /* synthetic */ Random random;
    private final /* synthetic */ Setting<Boolean> visualRangeIgnoreFriends;
    private final /* synthetic */ SettingGroup sgVisualRange;
    private final /* synthetic */ Object2IntMap<UUID> totemPopMap;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Boolean> totemsIgnoreOwn;
    private final /* synthetic */ Object2IntMap<UUID> chatIdMap;
    private final /* synthetic */ Setting<Boolean> totemsIgnoreOthers;
    private final /* synthetic */ Setting<Boolean> totemsIgnoreFriends;
    private final /* synthetic */ Setting<Boolean> visualRangeIgnoreFakes;
    private final /* synthetic */ Setting<Event> event;
    private final /* synthetic */ Setting<Boolean> visualRange;
    private final /* synthetic */ Setting<Boolean> totemPops;
    private final /* synthetic */ SettingGroup sgTotemPops;

    @Override
    public void onActivate() {
        Notifier lllllllllllllllllllIIIllIlIlllII;
        lllllllllllllllllllIIIllIlIlllII.totemPopMap.clear();
        lllllllllllllllllllIIIllIlIlllII.chatIdMap.clear();
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent lllllllllllllllllllIIIllIllIIIIl) {
        Notifier lllllllllllllllllllIIIllIllIIIlI;
        class_1297 lllllllllllllllllllIIIllIllIIIll = lllllllllllllllllllIIIllIllIIIIl.entity;
        if (lllllllllllllllllllIIIllIllIIIll.equals((Object)lllllllllllllllllllIIIllIllIIIlI.mc.field_1724) || !lllllllllllllllllllIIIllIllIIIlI.entities.get().getBoolean((Object)lllllllllllllllllllIIIllIllIIIIl.entity.method_5864()) || !lllllllllllllllllllIIIllIllIIIlI.visualRange.get().booleanValue() || lllllllllllllllllllIIIllIllIIIlI.event.get() == Event.Spawn) {
            return;
        }
        if (lllllllllllllllllllIIIllIllIIIll instanceof class_1657) {
            if (!(lllllllllllllllllllIIIllIllIIIlI.visualRangeIgnoreFriends.get().booleanValue() && Friends.get().isFriend((class_1657)lllllllllllllllllllIIIllIllIIIll) || lllllllllllllllllllIIIllIllIIIlI.visualRangeIgnoreFakes.get().booleanValue() && lllllllllllllllllllIIIllIllIIIll instanceof FakePlayerEntity)) {
                ChatUtils.sendMsg(lllllllllllllllllllIIIllIllIIIIl.entity.method_5628() + 100, class_124.field_1080, "(highlight)%s(default) has left your visual range!", lllllllllllllllllllIIIllIllIIIIl.entity.method_5820());
            }
        } else {
            class_5250 lllllllllllllllllllIIIllIllIIllI = new class_2585(lllllllllllllllllllIIIllIllIIIIl.entity.method_5864().method_5897().getString()).method_27692(class_124.field_1068);
            lllllllllllllllllllIIIllIllIIllI.method_10852((class_2561)new class_2585(" has despawned at ").method_27692(class_124.field_1080));
            lllllllllllllllllllIIIllIllIIllI.method_10852((class_2561)ChatUtils.formatCoords(lllllllllllllllllllIIIllIllIIIIl.entity.method_19538()));
            lllllllllllllllllllIIIllIllIIllI.method_10852((class_2561)new class_2585(".").method_27692(class_124.field_1080));
            lllllllllllllllllllIIIllIllIIIlI.info((class_2561)lllllllllllllllllllIIIllIllIIllI);
        }
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent lllllllllllllllllllIIIllIlIllIIl) {
        Notifier lllllllllllllllllllIIIllIlIllIlI;
        lllllllllllllllllllIIIllIlIllIlI.totemPopMap.clear();
        lllllllllllllllllllIIIllIlIllIlI.chatIdMap.clear();
    }

    public Notifier() {
        super(Categories.Misc, "notifier", "Notifies you of different events.");
        Notifier lllllllllllllllllllIIIllIllllIII;
        lllllllllllllllllllIIIllIllllIII.sgTotemPops = lllllllllllllllllllIIIllIllllIII.settings.createGroup("Totem Pops");
        lllllllllllllllllllIIIllIllllIII.sgVisualRange = lllllllllllllllllllIIIllIllllIII.settings.createGroup("Visual Range");
        lllllllllllllllllllIIIllIllllIII.totemPops = lllllllllllllllllllIIIllIllllIII.sgTotemPops.add(new BoolSetting.Builder().name("totem-pops").description("Notifies you when a player pops a totem.").defaultValue(true).build());
        lllllllllllllllllllIIIllIllllIII.totemsIgnoreOwn = lllllllllllllllllllIIIllIllllIII.sgTotemPops.add(new BoolSetting.Builder().name("ignore-own").description("Notifies you of your own totem pops.").defaultValue(false).build());
        lllllllllllllllllllIIIllIllllIII.totemsIgnoreFriends = lllllllllllllllllllIIIllIllllIII.sgTotemPops.add(new BoolSetting.Builder().name("ignore-friends").description("Ignores friends totem pops.").defaultValue(false).build());
        lllllllllllllllllllIIIllIllllIII.totemsIgnoreOthers = lllllllllllllllllllIIIllIllllIII.sgTotemPops.add(new BoolSetting.Builder().name("ignore-others").description("Ignores other players totem pops.").defaultValue(false).build());
        lllllllllllllllllllIIIllIllllIII.visualRange = lllllllllllllllllllIIIllIllllIII.sgVisualRange.add(new BoolSetting.Builder().name("visual-range").description("Notifies you when an entity enters your render distance.").defaultValue(false).build());
        lllllllllllllllllllIIIllIllllIII.event = lllllllllllllllllllIIIllIllllIII.sgVisualRange.add(new EnumSetting.Builder().name("event").description("When to log the entities.").defaultValue(Event.Both).build());
        lllllllllllllllllllIIIllIllllIII.entities = lllllllllllllllllllIIIllIllllIII.sgVisualRange.add(new EntityTypeListSetting.Builder().name("entities").description("Which entities to nofity about.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        lllllllllllllllllllIIIllIllllIII.visualRangeIgnoreFriends = lllllllllllllllllllIIIllIllllIII.sgVisualRange.add(new BoolSetting.Builder().name("ignore-friends").description("Ignores friends.").defaultValue(true).build());
        lllllllllllllllllllIIIllIllllIII.visualRangeIgnoreFakes = lllllllllllllllllllIIIllIllllIII.sgVisualRange.add(new BoolSetting.Builder().name("ignore-fake-players").description("Ignores fake players.").defaultValue(true).build());
        lllllllllllllllllllIIIllIllllIII.totemPopMap = new Object2IntOpenHashMap();
        lllllllllllllllllllIIIllIllllIII.chatIdMap = new Object2IntOpenHashMap();
        lllllllllllllllllllIIIllIllllIII.random = new Random();
    }

    private int getChatId(class_1297 lllllllllllllllllllIIIllIIlIllll) {
        Notifier lllllllllllllllllllIIIllIIllIIII;
        return lllllllllllllllllllIIIllIIllIIII.chatIdMap.computeIntIfAbsent((Object)lllllllllllllllllllIIIllIIlIllll.method_5667(), lllllllllllllllllllIIIllIIlIllII -> {
            Notifier lllllllllllllllllllIIIllIIlIllIl;
            return lllllllllllllllllllIIIllIIlIllIl.random.nextInt();
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllIIIllIIlllIll) {
        Notifier lllllllllllllllllllIIIllIIlllIlI;
        if (!lllllllllllllllllllIIIllIIlllIlI.totemPops.get().booleanValue()) {
            return;
        }
        Object2IntMap<UUID> lllllllllllllllllllIIIllIIlllIIl = lllllllllllllllllllIIIllIIlllIlI.totemPopMap;
        synchronized (lllllllllllllllllllIIIllIIlllIIl) {
            for (class_1657 lllllllllllllllllllIIIllIIllllIl : lllllllllllllllllllIIIllIIlllIlI.mc.field_1687.method_18456()) {
                if (!lllllllllllllllllllIIIllIIlllIlI.totemPopMap.containsKey((Object)lllllllllllllllllllIIIllIIllllIl.method_5667()) || lllllllllllllllllllIIIllIIllllIl.field_6213 <= 0 && !(lllllllllllllllllllIIIllIIllllIl.method_6032() <= 0.0f)) continue;
                int lllllllllllllllllllIIIllIIlllllI = lllllllllllllllllllIIIllIIlllIlI.totemPopMap.removeInt((Object)lllllllllllllllllllIIIllIIllllIl.method_5667());
                ChatUtils.sendMsg(lllllllllllllllllllIIIllIIlllIlI.getChatId((class_1297)lllllllllllllllllllIIIllIIllllIl), class_124.field_1080, "(highlight)%s (default)died after popping (highlight)%d (default)%s.", lllllllllllllllllllIIIllIIllllIl.method_5820(), lllllllllllllllllllIIIllIIlllllI, lllllllllllllllllllIIIllIIlllllI == 1 ? "totem" : "totems");
                lllllllllllllllllllIIIllIIlllIlI.chatIdMap.removeInt((Object)lllllllllllllllllllIIIllIIllllIl.method_5667());
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onReceivePacket(PacketEvent.Receive lllllllllllllllllllIIIllIlIIlIlI) {
        Notifier lllllllllllllllllllIIIllIlIIlIll;
        if (!lllllllllllllllllllIIIllIlIIlIll.totemPops.get().booleanValue()) {
            return;
        }
        if (!(lllllllllllllllllllIIIllIlIIlIlI.packet instanceof class_2663)) {
            return;
        }
        class_2663 lllllllllllllllllllIIIllIlIIllIl = (class_2663)lllllllllllllllllllIIIllIlIIlIlI.packet;
        if (lllllllllllllllllllIIIllIlIIllIl.method_11470() != 35) {
            return;
        }
        class_1297 lllllllllllllllllllIIIllIlIIllII = lllllllllllllllllllIIIllIlIIllIl.method_11469((class_1937)lllllllllllllllllllIIIllIlIIlIll.mc.field_1687);
        if (lllllllllllllllllllIIIllIlIIllII == null || lllllllllllllllllllIIIllIlIIllII.equals((Object)lllllllllllllllllllIIIllIlIIlIll.mc.field_1724) && lllllllllllllllllllIIIllIlIIlIll.totemsIgnoreOwn.get() != false || Friends.get().isFriend((class_1657)lllllllllllllllllllIIIllIlIIllII) && lllllllllllllllllllIIIllIlIIlIll.totemsIgnoreOthers.get() != false || !Friends.get().isFriend((class_1657)lllllllllllllllllllIIIllIlIIllII) && lllllllllllllllllllIIIllIlIIlIll.totemsIgnoreFriends.get().booleanValue()) {
            return;
        }
        Object2IntMap<UUID> lllllllllllllllllllIIIllIlIIIlll = lllllllllllllllllllIIIllIlIIlIll.totemPopMap;
        synchronized (lllllllllllllllllllIIIllIlIIIlll) {
            int lllllllllllllllllllIIIllIlIlIIII = lllllllllllllllllllIIIllIlIIlIll.totemPopMap.getOrDefault((Object)lllllllllllllllllllIIIllIlIIllII.method_5667(), 0);
            lllllllllllllllllllIIIllIlIIlIll.totemPopMap.put((Object)lllllllllllllllllllIIIllIlIIllII.method_5667(), ++lllllllllllllllllllIIIllIlIlIIII);
            ChatUtils.sendMsg(lllllllllllllllllllIIIllIlIIlIll.getChatId(lllllllllllllllllllIIIllIlIIllII), class_124.field_1080, "(highlight)%s (default)popped (highlight)%d (default)%s.", ((class_1657)lllllllllllllllllllIIIllIlIIllII).method_5820(), lllllllllllllllllllIIIllIlIlIIII, lllllllllllllllllllIIIllIlIlIIII == 1 ? "totem" : "totems");
        }
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent lllllllllllllllllllIIIllIlllIIII) {
        Notifier lllllllllllllllllllIIIllIlllIIIl;
        class_1297 lllllllllllllllllllIIIllIllIllll = lllllllllllllllllllIIIllIlllIIII.entity;
        if (lllllllllllllllllllIIIllIllIllll.equals((Object)lllllllllllllllllllIIIllIlllIIIl.mc.field_1724) || !lllllllllllllllllllIIIllIlllIIIl.entities.get().getBoolean((Object)lllllllllllllllllllIIIllIlllIIII.entity.method_5864()) || !lllllllllllllllllllIIIllIlllIIIl.visualRange.get().booleanValue() || lllllllllllllllllllIIIllIlllIIIl.event.get() == Event.Despawn) {
            return;
        }
        if (lllllllllllllllllllIIIllIllIllll instanceof class_1657) {
            if (!(lllllllllllllllllllIIIllIlllIIIl.visualRangeIgnoreFriends.get().booleanValue() && Friends.get().isFriend((class_1657)lllllllllllllllllllIIIllIllIllll) || lllllllllllllllllllIIIllIlllIIIl.visualRangeIgnoreFakes.get().booleanValue() && lllllllllllllllllllIIIllIllIllll instanceof FakePlayerEntity)) {
                ChatUtils.sendMsg(lllllllllllllllllllIIIllIlllIIII.entity.method_5628() + 100, class_124.field_1080, "(highlight)%s(default) has entered your visual range!", lllllllllllllllllllIIIllIlllIIII.entity.method_5820());
            }
        } else {
            class_5250 lllllllllllllllllllIIIllIlllIIlI = new class_2585(lllllllllllllllllllIIIllIlllIIII.entity.method_5864().method_5897().getString()).method_27692(class_124.field_1068);
            lllllllllllllllllllIIIllIlllIIlI.method_10852((class_2561)new class_2585(" has spawned at ").method_27692(class_124.field_1080));
            lllllllllllllllllllIIIllIlllIIlI.method_10852((class_2561)ChatUtils.formatCoords(lllllllllllllllllllIIIllIlllIIII.entity.method_19538()));
            lllllllllllllllllllIIIllIlllIIlI.method_10852((class_2561)new class_2585(".").method_27692(class_124.field_1080));
            lllllllllllllllllllIIIllIlllIIIl.info((class_2561)lllllllllllllllllllIIIllIlllIIlI);
        }
    }

    public static enum Event {
        Spawn,
        Despawn,
        Both;


        private Event() {
            Event llllllllllllllllIlIlllIIllIlIIIl;
        }
    }
}

