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
import java.util.Iterator;
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
    private final Random random;
    private final Setting<Boolean> visualRangeIgnoreFriends;
    private final SettingGroup sgVisualRange;
    private final Object2IntMap<UUID> totemPopMap;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Boolean> totemsIgnoreOwn;
    private final Object2IntMap<UUID> chatIdMap;
    private final Setting<Boolean> totemsIgnoreOthers;
    private final Setting<Boolean> totemsIgnoreFriends;
    private final Setting<Boolean> visualRangeIgnoreFakes;
    private final Setting<Event> event;
    private final Setting<Boolean> visualRange;
    private final Setting<Boolean> totemPops;
    private final SettingGroup sgTotemPops;

    @Override
    public void onActivate() {
        this.totemPopMap.clear();
        this.chatIdMap.clear();
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent entityRemovedEvent) {
        class_1297 class_12972 = entityRemovedEvent.entity;
        if (class_12972.equals((Object)this.mc.field_1724) || !this.entities.get().getBoolean((Object)entityRemovedEvent.entity.method_5864()) || !this.visualRange.get().booleanValue() || this.event.get() == Event.Spawn) {
            return;
        }
        if (class_12972 instanceof class_1657) {
            if (!(this.visualRangeIgnoreFriends.get().booleanValue() && Friends.get().isFriend((class_1657)class_12972) || this.visualRangeIgnoreFakes.get().booleanValue() && class_12972 instanceof FakePlayerEntity)) {
                ChatUtils.sendMsg(entityRemovedEvent.entity.method_5628() + 100, class_124.field_1080, "(highlight)%s(default) has left your visual range!", entityRemovedEvent.entity.method_5820());
            }
        } else {
            class_5250 class_52502 = new class_2585(entityRemovedEvent.entity.method_5864().method_5897().getString()).method_27692(class_124.field_1068);
            class_52502.method_10852((class_2561)new class_2585(" has despawned at ").method_27692(class_124.field_1080));
            class_52502.method_10852((class_2561)ChatUtils.formatCoords(entityRemovedEvent.entity.method_19538()));
            class_52502.method_10852((class_2561)new class_2585(".").method_27692(class_124.field_1080));
            this.info((class_2561)class_52502);
        }
    }

    @EventHandler
    private void onGameJoin(GameJoinedEvent gameJoinedEvent) {
        this.totemPopMap.clear();
        this.chatIdMap.clear();
    }

    public Notifier() {
        super(Categories.Misc, "notifier", "Notifies you of different events.");
        this.sgTotemPops = this.settings.createGroup("Totem Pops");
        this.sgVisualRange = this.settings.createGroup("Visual Range");
        this.totemPops = this.sgTotemPops.add(new BoolSetting.Builder().name("totem-pops").description("Notifies you when a player pops a totem.").defaultValue(true).build());
        this.totemsIgnoreOwn = this.sgTotemPops.add(new BoolSetting.Builder().name("ignore-own").description("Notifies you of your own totem pops.").defaultValue(false).build());
        this.totemsIgnoreFriends = this.sgTotemPops.add(new BoolSetting.Builder().name("ignore-friends").description("Ignores friends totem pops.").defaultValue(false).build());
        this.totemsIgnoreOthers = this.sgTotemPops.add(new BoolSetting.Builder().name("ignore-others").description("Ignores other players totem pops.").defaultValue(false).build());
        this.visualRange = this.sgVisualRange.add(new BoolSetting.Builder().name("visual-range").description("Notifies you when an entity enters your render distance.").defaultValue(false).build());
        this.event = this.sgVisualRange.add(new EnumSetting.Builder().name("event").description("When to log the entities.").defaultValue(Event.Both).build());
        this.entities = this.sgVisualRange.add(new EntityTypeListSetting.Builder().name("entities").description("Which entities to nofity about.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        this.visualRangeIgnoreFriends = this.sgVisualRange.add(new BoolSetting.Builder().name("ignore-friends").description("Ignores friends.").defaultValue(true).build());
        this.visualRangeIgnoreFakes = this.sgVisualRange.add(new BoolSetting.Builder().name("ignore-fake-players").description("Ignores fake players.").defaultValue(true).build());
        this.totemPopMap = new Object2IntOpenHashMap();
        this.chatIdMap = new Object2IntOpenHashMap();
        this.random = new Random();
    }

    private int getChatId(class_1297 class_12972) {
        return this.chatIdMap.computeIntIfAbsent((Object)class_12972.method_5667(), this::lambda$getChatId$0);
    }

    private int lambda$getChatId$0(UUID uUID) {
        return this.random.nextInt();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (!this.totemPops.get().booleanValue()) {
            return;
        }
        Object2IntMap<UUID> object2IntMap = this.totemPopMap;
        synchronized (object2IntMap) {
            Iterator iterator = this.mc.field_1687.method_18456().iterator();
            while (iterator.hasNext()) {
                class_1657 class_16572 = (class_1657)iterator.next();
                if (!this.totemPopMap.containsKey((Object)class_16572.method_5667()) || class_16572.field_6213 <= 0 && !(class_16572.method_6032() <= 0.0f)) continue;
                int n = this.totemPopMap.removeInt((Object)class_16572.method_5667());
                ChatUtils.sendMsg(this.getChatId((class_1297)class_16572), class_124.field_1080, "(highlight)%s (default)died after popping (highlight)%d (default)%s.", class_16572.method_5820(), n, n == 1 ? "totem" : "totems");
                this.chatIdMap.removeInt((Object)class_16572.method_5667());
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onReceivePacket(PacketEvent.Receive receive) {
        if (!this.totemPops.get().booleanValue()) {
            return;
        }
        if (!(receive.packet instanceof class_2663)) {
            return;
        }
        class_2663 class_26632 = (class_2663)receive.packet;
        if (class_26632.method_11470() != 35) {
            return;
        }
        class_1297 class_12972 = class_26632.method_11469((class_1937)this.mc.field_1687);
        if (class_12972 == null) return;
        if (class_12972.equals((Object)this.mc.field_1724)) {
            if (this.totemsIgnoreOwn.get() != false) return;
        }
        if (Friends.get().isFriend((class_1657)class_12972)) {
            if (this.totemsIgnoreOthers.get() != false) return;
        }
        if (!Friends.get().isFriend((class_1657)class_12972) && this.totemsIgnoreFriends.get().booleanValue()) {
            return;
        }
        Object2IntMap<UUID> object2IntMap = this.totemPopMap;
        synchronized (object2IntMap) {
            int n = this.totemPopMap.getOrDefault((Object)class_12972.method_5667(), 0);
            this.totemPopMap.put((Object)class_12972.method_5667(), ++n);
            ChatUtils.sendMsg(this.getChatId(class_12972), class_124.field_1080, "(highlight)%s (default)popped (highlight)%d (default)%s.", ((class_1657)class_12972).method_5820(), n, n == 1 ? "totem" : "totems");
            return;
        }
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent entityAddedEvent) {
        class_1297 class_12972 = entityAddedEvent.entity;
        if (class_12972.equals((Object)this.mc.field_1724) || !this.entities.get().getBoolean((Object)entityAddedEvent.entity.method_5864()) || !this.visualRange.get().booleanValue() || this.event.get() == Event.Despawn) {
            return;
        }
        if (class_12972 instanceof class_1657) {
            if (!(this.visualRangeIgnoreFriends.get().booleanValue() && Friends.get().isFriend((class_1657)class_12972) || this.visualRangeIgnoreFakes.get().booleanValue() && class_12972 instanceof FakePlayerEntity)) {
                ChatUtils.sendMsg(entityAddedEvent.entity.method_5628() + 100, class_124.field_1080, "(highlight)%s(default) has entered your visual range!", entityAddedEvent.entity.method_5820());
            }
        } else {
            class_5250 class_52502 = new class_2585(entityAddedEvent.entity.method_5864().method_5897().getString()).method_27692(class_124.field_1068);
            class_52502.method_10852((class_2561)new class_2585(" has spawned at ").method_27692(class_124.field_1080));
            class_52502.method_10852((class_2561)ChatUtils.formatCoords(entityAddedEvent.entity.method_19538()));
            class_52502.method_10852((class_2561)new class_2585(".").method_27692(class_124.field_1080));
            this.info((class_2561)class_52502);
        }
    }

    public static enum Event {
        Spawn,
        Despawn,
        Both;

    }
}

