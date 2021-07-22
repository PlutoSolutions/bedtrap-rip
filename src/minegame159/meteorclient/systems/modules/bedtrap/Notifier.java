/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2554
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2620
 *  net.minecraft.class_2673
 *  net.minecraft.class_2703
 *  net.minecraft.class_2703$class_2704
 *  net.minecraft.class_2703$class_2705
 *  net.minecraft.class_2777
 *  net.minecraft.class_310
 *  org.apache.commons.io.IOUtils
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2554;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2620;
import net.minecraft.class_2673;
import net.minecraft.class_2703;
import net.minecraft.class_2777;
import net.minecraft.class_310;
import org.apache.commons.io.IOUtils;

public class Notifier
extends Module {
    private /* synthetic */ class_2338 prevBreakPos;
    private final /* synthetic */ Setting<Integer> minDistance;
    private final /* synthetic */ Setting<Boolean> portal;
    private /* synthetic */ boolean bLeggings;
    private final /* synthetic */ Setting<Boolean> wither;
    private final /* synthetic */ Setting<Boolean> antiVanish;
    private final /* synthetic */ Setting<Boolean> dragon;
    private final /* synthetic */ SettingGroup sExploit;
    private final /* synthetic */ List<class_1657> burrowedPlayers;
    private static final /* synthetic */ class_310 mc;
    private final /* synthetic */ Setting<Integer> msgNum;
    private final /* synthetic */ SettingGroup sVanish;
    private final /* synthetic */ SettingGroup sSurround;
    /* synthetic */ Boolean obb;
    private /* synthetic */ long lastTick;
    private final /* synthetic */ Setting<Boolean> tpExploit;
    private final /* synthetic */ Setting<Boolean> armorDurability;
    private final /* synthetic */ Setting<Boolean> coordExploit;
    private /* synthetic */ boolean bBoots;
    private final /* synthetic */ Setting<Boolean> surroundBreak;
    private final /* synthetic */ Setting<Integer> damage;
    private final /* synthetic */ SettingGroup sGroup;
    private /* synthetic */ int num;
    private /* synthetic */ boolean bHead;
    private /* synthetic */ boolean bArmor;
    private final /* synthetic */ Queue<UUID> toLookup;

    @EventHandler
    public void coordExploit(PacketEvent.Receive llllllllllllllllIllIIlllIlIlIIIl) {
        if (Notifier.mc.field_1724 != null) {
            class_2673 llllllllllllllllIllIIlllIlIlIlII;
            PacketEvent.Receive llllllllllllllllIllIIlllIlIlIIll = llllllllllllllllIllIIlllIlIlIIIl;
            if (llllllllllllllllIllIIlllIlIlIIll.packet instanceof class_2673 && (llllllllllllllllIllIIlllIlIlIlII = (class_2673)llllllllllllllllIllIIlllIlIlIIll.packet).method_11533()) {
                System.out.println(llllllllllllllllIllIIlllIlIlIlII.method_11532());
                switch (llllllllllllllllIllIIlllIlIlIlII.method_11532()) {
                    case 1023: {
                        Notifier llllllllllllllllIllIIlllIlIlIIlI;
                        if (!llllllllllllllllIllIIlllIlIlIIlI.wither.get().booleanValue()) break;
                        ChatUtils.info(String.valueOf(new StringBuilder().append("Wither spawned at: ").append(llllllllllllllllIllIIlllIlIlIlII.method_11531().method_23854())), new Object[0]);
                        break;
                    }
                    case 1038: {
                        Notifier llllllllllllllllIllIIlllIlIlIIlI;
                        if (!llllllllllllllllIllIIlllIlIlIIlI.portal.get().booleanValue()) break;
                        ChatUtils.info(String.valueOf(new StringBuilder().append("End Portal Activated at: ").append(llllllllllllllllIllIIlllIlIlIlII.method_11531().method_23854())), new Object[0]);
                        break;
                    }
                    case 1028: {
                        Notifier llllllllllllllllIllIIlllIlIlIIlI;
                        if (!llllllllllllllllIllIIlllIlIlIIlI.dragon.get().booleanValue()) break;
                        ChatUtils.info(String.valueOf(new StringBuilder().append("Ender Dragon killed at: ").append(llllllllllllllllIllIIlllIlIlIlII.method_11531().method_23854())), new Object[0]);
                        break;
                    }
                    default: {
                        ChatUtils.info(String.valueOf(new StringBuilder().append("Unknown global event at: ").append(llllllllllllllllIllIIlllIlIlIlII.method_11531().method_23854())), new Object[0]);
                    }
                }
            }
        }
    }

    static {
        mc = class_310.method_1551();
    }

    @Override
    public void onDeactivate() {
        Notifier llllllllllllllllIllIIlllllIIIIIl;
        if (llllllllllllllllIllIIlllllIIIIIl.antiVanish.get().booleanValue()) {
            llllllllllllllllIllIIlllllIIIIIl.toLookup.clear();
        }
    }

    public Notifier() {
        super(Categories.BedTrap, "notifier+", "Notifies you on triggered event.");
        Notifier llllllllllllllllIllIIlllllIIIllI;
        llllllllllllllllIllIIlllllIIIllI.sGroup = llllllllllllllllIllIIlllllIIIllI.settings.createGroup("Armor Break");
        llllllllllllllllIllIIlllllIIIllI.sVanish = llllllllllllllllIllIIlllllIIIllI.settings.createGroup("Anti Vanish");
        llllllllllllllllIllIIlllllIIIllI.sSurround = llllllllllllllllIllIIlllllIIIllI.settings.createGroup("Surround Break");
        llllllllllllllllIllIIlllllIIIllI.sExploit = llllllllllllllllIllIIlllllIIIllI.settings.createGroup("Tp Exploit");
        llllllllllllllllIllIIlllllIIIllI.armorDurability = llllllllllllllllIllIIlllllIIIllI.sGroup.add(new BoolSetting.Builder().name("armor-durability").description("yes").defaultValue(true).build());
        llllllllllllllllIllIIlllllIIIllI.damage = llllllllllllllllIllIIlllllIIIllI.sGroup.add(new IntSetting.Builder().name("damage").description("What damage should an armor item have to be notified.").defaultValue(100).visible(llllllllllllllllIllIIlllllIIIllI.armorDurability::get).build());
        llllllllllllllllIllIIlllllIIIllI.msgNum = llllllllllllllllIllIIlllllIIIllI.sGroup.add(new IntSetting.Builder().name("messages").description("What damage should an armor item have to be notified.").defaultValue(5).visible(llllllllllllllllIllIIlllllIIIllI.armorDurability::get).build());
        llllllllllllllllIllIIlllllIIIllI.antiVanish = llllllllllllllllIllIIlllllIIIllI.sVanish.add(new BoolSetting.Builder().name("anti-vanish").description("Place obsidian on TNT position.").defaultValue(true).build());
        llllllllllllllllIllIIlllllIIIllI.surroundBreak = llllllllllllllllIllIIlllllIIIllI.sSurround.add(new BoolSetting.Builder().name("surround-break").description("Place obsidian on TNT position.").defaultValue(true).build());
        llllllllllllllllIllIIlllllIIIllI.coordExploit = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new BoolSetting.Builder().name("coord-exploit").description("Place obsidian on TNT position.").defaultValue(true).build());
        llllllllllllllllIllIIlllllIIIllI.wither = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new BoolSetting.Builder().name("wither-boss").description("Place obsidian on TNT position.").defaultValue(true).visible(llllllllllllllllIllIIlllllIIIllI.coordExploit::get).build());
        llllllllllllllllIllIIlllllIIIllI.portal = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new BoolSetting.Builder().name("end-portal").description("Place obsidian on TNT position.").defaultValue(true).visible(llllllllllllllllIllIIlllllIIIllI.coordExploit::get).build());
        llllllllllllllllIllIIlllllIIIllI.dragon = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new BoolSetting.Builder().name("end-dragon").description("Place obsidian on TNT position.").defaultValue(true).visible(llllllllllllllllIllIIlllllIIIllI.coordExploit::get).build());
        llllllllllllllllIllIIlllllIIIllI.tpExploit = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new BoolSetting.Builder().name("tp-exploit").description("Place obsidian on TNT position.").defaultValue(true).visible(llllllllllllllllIllIIlllllIIIllI.coordExploit::get).build());
        llllllllllllllllIllIIlllllIIIllI.minDistance = llllllllllllllllIllIIlllllIIIllI.sExploit.add(new IntSetting.Builder().name("min-distance").description("Minimal distance for tp exploit.").defaultValue(6).min(0).max(10000).sliderMax(10000).visible(llllllllllllllllIllIIlllllIIIllI.tpExploit::get).build());
        llllllllllllllllIllIIlllllIIIllI.num = 0;
        llllllllllllllllIllIIlllllIIIllI.bBoots = false;
        llllllllllllllllIllIIlllllIIIllI.bLeggings = false;
        llllllllllllllllIllIIlllllIIIllI.bArmor = false;
        llllllllllllllllIllIIlllllIIIllI.bHead = false;
        llllllllllllllllIllIIlllllIIIllI.toLookup = new ConcurrentLinkedQueue<UUID>();
        llllllllllllllllIllIIlllllIIIllI.burrowedPlayers = new ArrayList<class_1657>();
        llllllllllllllllIllIIlllllIIIllI.lastTick = 0L;
    }

    @Override
    public void onActivate() {
        llllllllllllllllIllIIlllllIIIlII.num = 1;
        llllllllllllllllIllIIlllllIIIlII.bBoots = false;
        llllllllllllllllIllIIlllllIIIlII.bLeggings = false;
        llllllllllllllllIllIIlllllIIIlII.bArmor = false;
        llllllllllllllllIllIIlllllIIIlII.bHead = false;
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIllIIllllIllIIIl) {
        Notifier llllllllllllllllIllIIllllIllIIlI;
        assert (Notifier.mc.field_1724 != null);
        assert (Notifier.mc.field_1687 != null);
        class_1799 llllllllllllllllIllIIllllIllIIII = Notifier.mc.field_1724.field_7514.method_7372(0);
        int llllllllllllllllIllIIllllIlIllll = llllllllllllllllIllIIllllIllIIII.method_7936() - llllllllllllllllIllIIllllIllIIII.method_7919();
        class_1799 llllllllllllllllIllIIllllIlIlllI = Notifier.mc.field_1724.field_7514.method_7372(1);
        int llllllllllllllllIllIIllllIlIllIl = llllllllllllllllIllIIllllIlIlllI.method_7936() - llllllllllllllllIllIIllllIlIlllI.method_7919();
        class_1799 llllllllllllllllIllIIllllIlIllII = Notifier.mc.field_1724.field_7514.method_7372(2);
        int llllllllllllllllIllIIllllIlIlIll = llllllllllllllllIllIIllllIlIllII.method_7936() - llllllllllllllllIllIIllllIlIllII.method_7919();
        class_1799 llllllllllllllllIllIIllllIlIlIlI = Notifier.mc.field_1724.field_7514.method_7372(3);
        int llllllllllllllllIllIIllllIlIlIIl = llllllllllllllllIllIIllllIlIlIlI.method_7936() - llllllllllllllllIllIIllllIlIlIlI.method_7919();
        if (llllllllllllllllIllIIllllIlIllll < llllllllllllllllIllIIllllIllIIlI.damage.get() && !llllllllllllllllIllIIllllIllIIlI.bBoots) {
            if (Notifier.mc.field_1724.field_7514.method_7372(0).method_7960()) {
                ChatUtils.warning("Your Boots slot is empty!", new Object[0]);
                llllllllllllllllIllIIllllIllIIlI.bBoots = true;
                llllllllllllllllIllIIllllIllIIlI.num = 0;
                return;
            }
            ChatUtils.warning("Your Boots is about to break.", new Object[0]);
            if (llllllllllllllllIllIIllllIllIIlI.num < llllllllllllllllIllIIllllIllIIlI.msgNum.get()) {
                ++llllllllllllllllIllIIllllIllIIlI.num;
                return;
            }
            llllllllllllllllIllIIllllIllIIlI.bBoots = true;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        } else if (llllllllllllllllIllIIllllIlIllll > llllllllllllllllIllIIllllIllIIlI.damage.get() && llllllllllllllllIllIIllllIllIIlI.bBoots) {
            llllllllllllllllIllIIllllIllIIlI.bBoots = false;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        }
        if (llllllllllllllllIllIIllllIlIllIl < llllllllllllllllIllIIllllIllIIlI.damage.get() && !llllllllllllllllIllIIllllIllIIlI.bLeggings) {
            if (Notifier.mc.field_1724.field_7514.method_7372(1).method_7960()) {
                ChatUtils.warning("Your Leggings slot is empty!", new Object[0]);
                llllllllllllllllIllIIllllIllIIlI.bLeggings = true;
                llllllllllllllllIllIIllllIllIIlI.num = 0;
                return;
            }
            ChatUtils.warning("Your Leggings is about to break.", new Object[0]);
            if (llllllllllllllllIllIIllllIllIIlI.num < llllllllllllllllIllIIllllIllIIlI.msgNum.get()) {
                ++llllllllllllllllIllIIllllIllIIlI.num;
                return;
            }
            llllllllllllllllIllIIllllIllIIlI.bLeggings = true;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        } else if (llllllllllllllllIllIIllllIlIllIl > llllllllllllllllIllIIllllIllIIlI.damage.get() && llllllllllllllllIllIIllllIllIIlI.bLeggings) {
            llllllllllllllllIllIIllllIllIIlI.bLeggings = false;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        }
        if (llllllllllllllllIllIIllllIlIlIll < llllllllllllllllIllIIllllIllIIlI.damage.get() && !llllllllllllllllIllIIllllIllIIlI.bArmor) {
            if (Notifier.mc.field_1724.field_7514.method_7372(2).method_7960()) {
                ChatUtils.warning("Your Chestplate slot is empty!", new Object[0]);
                llllllllllllllllIllIIllllIllIIlI.bArmor = true;
                llllllllllllllllIllIIllllIllIIlI.num = 0;
                return;
            }
            ChatUtils.warning("Your Chestplate is about to break.", new Object[0]);
            if (llllllllllllllllIllIIllllIllIIlI.num < llllllllllllllllIllIIllllIllIIlI.msgNum.get()) {
                ++llllllllllllllllIllIIllllIllIIlI.num;
                return;
            }
            llllllllllllllllIllIIllllIllIIlI.bArmor = true;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        } else if (llllllllllllllllIllIIllllIlIlIll > llllllllllllllllIllIIllllIllIIlI.damage.get() && llllllllllllllllIllIIllllIllIIlI.bArmor) {
            llllllllllllllllIllIIllllIllIIlI.bArmor = false;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        }
        if (llllllllllllllllIllIIllllIlIlIIl < llllllllllllllllIllIIllllIllIIlI.damage.get() && !llllllllllllllllIllIIllllIllIIlI.bHead) {
            if (Notifier.mc.field_1724.field_7514.method_7372(3).method_7960()) {
                ChatUtils.warning("Your Helmet slot is empty!", new Object[0]);
                llllllllllllllllIllIIllllIllIIlI.bHead = true;
                llllllllllllllllIllIIllllIllIIlI.num = 0;
                return;
            }
            ChatUtils.warning("Your Helmet is about to break.", new Object[0]);
            if (llllllllllllllllIllIIllllIllIIlI.num < llllllllllllllllIllIIllllIllIIlI.msgNum.get()) {
                ++llllllllllllllllIllIIllllIllIIlI.num;
                return;
            }
            llllllllllllllllIllIIllllIllIIlI.bHead = true;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        } else if (llllllllllllllllIllIIllllIlIlIIl > llllllllllllllllIllIIllllIllIIlI.damage.get() && llllllllllllllllIllIIllllIllIIlI.bHead) {
            llllllllllllllllIllIIllllIllIIlI.bHead = false;
            llllllllllllllllIllIIllllIllIIlI.num = 1;
        }
    }

    @EventHandler
    public void onBreakPacket(PacketEvent.Receive llllllllllllllllIllIIlllIllIllIl) {
        Notifier llllllllllllllllIllIIlllIlllIIII;
        if (llllllllllllllllIllIIlllIlllIIII.surroundBreak.get().booleanValue()) {
            assert (Notifier.mc.field_1687 != null);
            assert (Notifier.mc.field_1724 != null);
            if (llllllllllllllllIllIIlllIllIllIl.packet instanceof class_2620) {
                class_2620 llllllllllllllllIllIIlllIlllIlII = (class_2620)llllllllllllllllIllIIlllIllIllIl.packet;
                class_2338 llllllllllllllllIllIIlllIlllIIll = llllllllllllllllIllIIlllIlllIlII.method_11277();
                if (llllllllllllllllIllIIlllIlllIIll.equals((Object)llllllllllllllllIllIIlllIlllIIII.prevBreakPos) && llllllllllllllllIllIIlllIlllIlII.method_11278() > 0) {
                    return;
                }
                class_1657 llllllllllllllllIllIIlllIlllIIlI = (class_1657)Notifier.mc.field_1687.method_8469(llllllllllllllllIllIIlllIlllIlII.method_11280());
                class_2338 llllllllllllllllIllIIlllIlllIIIl = Notifier.mc.field_1724.method_24515();
                llllllllllllllllIllIIlllIlllIIII.obb = Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlllIIll).method_26204() == class_2246.field_10540;
                if (llllllllllllllllIllIIlllIlllIIlI.equals((Object)Notifier.mc.field_1724)) {
                    return;
                }
                if (llllllllllllllllIllIIlllIlllIIII.obb.booleanValue() && llllllllllllllllIllIIlllIlllIIll.equals((Object)llllllllllllllllIllIIlllIlllIIIl.method_10095())) {
                    llllllllllllllllIllIIlllIlllIIII.notifySurroundBreak(class_2350.field_11043, llllllllllllllllIllIIlllIlllIIlI);
                } else if (llllllllllllllllIllIIlllIlllIIII.obb.booleanValue() && llllllllllllllllIllIIlllIlllIIll.equals((Object)llllllllllllllllIllIIlllIlllIIIl.method_10078())) {
                    llllllllllllllllIllIIlllIlllIIII.notifySurroundBreak(class_2350.field_11034, llllllllllllllllIllIIlllIlllIIlI);
                } else if (llllllllllllllllIllIIlllIlllIIII.obb.booleanValue() && llllllllllllllllIllIIlllIlllIIll.equals((Object)llllllllllllllllIllIIlllIlllIIIl.method_10072())) {
                    llllllllllllllllIllIIlllIlllIIII.notifySurroundBreak(class_2350.field_11035, llllllllllllllllIllIIlllIlllIIlI);
                } else if (llllllllllllllllIllIIlllIlllIIII.obb.booleanValue() && llllllllllllllllIllIIlllIlllIIll.equals((Object)llllllllllllllllIllIIlllIlllIIIl.method_10067())) {
                    llllllllllllllllIllIIlllIlllIIII.notifySurroundBreak(class_2350.field_11039, llllllllllllllllIllIIlllIlllIIlI);
                }
                llllllllllllllllIllIIlllIlllIIII.prevBreakPos = llllllllllllllllIllIIlllIlllIIll;
            }
        }
    }

    public static String getPlayerNameFromUUID(UUID llllllllllllllllIllIIlllIllllllI) {
        try {
            NameLookup llllllllllllllllIllIIllllIIIIIIl = new NameLookup(llllllllllllllllIllIIlllIllllllI);
            Thread llllllllllllllllIllIIllllIIIIIII = new Thread(llllllllllllllllIllIIllllIIIIIIl);
            llllllllllllllllIllIIllllIIIIIII.start();
            llllllllllllllllIllIIllllIIIIIII.join();
            return llllllllllllllllIllIIllllIIIIIIl.getName();
        }
        catch (Exception llllllllllllllllIllIIlllIlllllll) {
            return null;
        }
    }

    public class_2554 formatMessage(String llllllllllllllllIllIIlllIIlIlIIl, class_2338 llllllllllllllllIllIIlllIIlIlIll) {
        Notifier llllllllllllllllIllIIlllIIlIlIlI;
        return llllllllllllllllIllIIlllIIlIlIlI.formatMessage(llllllllllllllllIllIIlllIIlIlIIl, new class_243((double)llllllllllllllllIllIIlllIIlIlIll.method_10263(), (double)llllllllllllllllIllIIlllIIlIlIll.method_10264(), (double)llllllllllllllllIllIIlllIIlIlIll.method_10260()));
    }

    private void notifySurroundBreak(class_2350 llllllllllllllllIllIIlllIllIIIIl, class_1657 llllllllllllllllIllIIlllIllIIIll) {
        Notifier llllllllllllllllIllIIlllIllIIlIl;
        if (llllllllllllllllIllIIlllIllIIlIl.surroundBreak.get().booleanValue()) {
            switch (llllllllllllllllIllIIlllIllIIIIl) {
                case field_11043: {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Your surround is being broken by ").append(llllllllllllllllIllIIlllIllIIIll.method_5820())), new Object[0]);
                    break;
                }
                case field_11034: {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Your surround is being broken by ").append(llllllllllllllllIllIIlllIllIIIll.method_5820())), new Object[0]);
                    break;
                }
                case field_11035: {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Your surround is being broken by ").append(llllllllllllllllIllIIlllIllIIIll.method_5820())), new Object[0]);
                    break;
                }
                case field_11039: {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append("Your surround is being broken by ").append(llllllllllllllllIllIIlllIllIIIll.method_5820())), new Object[0]);
                }
            }
        }
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive llllllllllllllllIllIIlllIIllllll) {
        if (llllllllllllllllIllIIlllIIllllll.packet instanceof class_2777) {
            class_2777 llllllllllllllllIllIIlllIlIIIIll = (class_2777)llllllllllllllllIllIIlllIIllllll.packet;
            try {
                Notifier llllllllllllllllIllIIlllIlIIIIlI;
                class_1297 llllllllllllllllIllIIlllIlIIIlII = Notifier.mc.field_1687.method_8469(llllllllllllllllIllIIlllIlIIIIll.method_11916());
                if (llllllllllllllllIllIIlllIlIIIlII.method_5864().equals((Object)class_1299.field_6097) && llllllllllllllllIllIIlllIlIIIIlI.tpExploit.get().booleanValue()) {
                    class_243 llllllllllllllllIllIIlllIlIIIllI = new class_243(llllllllllllllllIllIIlllIlIIIIll.method_11917(), llllllllllllllllIllIIlllIlIIIIll.method_11919(), llllllllllllllllIllIIlllIlIIIIll.method_11918());
                    class_243 llllllllllllllllIllIIlllIlIIIlIl = llllllllllllllllIllIIlllIlIIIlII.method_19538();
                    if (llllllllllllllllIllIIlllIlIIIlIl.method_1022(llllllllllllllllIllIIlllIlIIIllI) >= (double)llllllllllllllllIllIIlllIlIIIIlI.minDistance.get().intValue()) {
                        llllllllllllllllIllIIlllIlIIIIlI.info((class_2561)llllllllllllllllIllIIlllIlIIIIlI.formatMessage(String.valueOf(new StringBuilder().append("Player '").append(llllllllllllllllIllIIlllIlIIIlII.method_5820()).append("' has teleported to ")), llllllllllllllllIllIIlllIlIIIllI));
                        return;
                    }
                }
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
    }

    public class_2554 formatMessage(String llllllllllllllllIllIIlllIIllIllI, class_243 llllllllllllllllIllIIlllIIllIIlI) {
        class_2585 llllllllllllllllIllIIlllIIllIlII = new class_2585(llllllllllllllllIllIIlllIIllIllI);
        llllllllllllllllIllIIlllIIllIlII.method_10852((class_2561)ChatUtils.formatCoords(llllllllllllllllIllIIlllIIllIIlI));
        llllllllllllllllIllIIlllIIllIlII.method_27693(String.valueOf(new StringBuilder().append(class_124.field_1080.toString()).append(".")));
        return llllllllllllllllIllIIlllIIllIlII;
    }

    @EventHandler
    public void onLeave(GameLeftEvent llllllllllllllllIllIIllllIllllIl) {
        Notifier llllllllllllllllIllIIllllIllllII;
        if (llllllllllllllllIllIIllllIllllII.antiVanish.get().booleanValue()) {
            llllllllllllllllIllIIllllIllllII.toLookup.clear();
        }
    }

    private boolean isInBurrow(class_1657 llllllllllllllllIllIIlllIlIlllII) {
        class_2338 llllllllllllllllIllIIlllIlIllIll = new class_2338(llllllllllllllllIllIIlllIlIlllII.method_23317(), llllllllllllllllIllIIlllIlIlllII.method_23318(), llllllllllllllllIllIIlllIlIlllII.method_23321());
        return Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_10540 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_10443 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_22423 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_22108 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_22109 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_23152 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_10485 || Notifier.mc.field_1687.method_8320(llllllllllllllllIllIIlllIlIllIll).method_26204() == class_2246.field_10535;
    }

    @EventHandler
    public void onPacket(PacketEvent.Receive llllllllllllllllIllIIllllIIlIlll) {
        class_2703 llllllllllllllllIllIIllllIIllIIl;
        Notifier llllllllllllllllIllIIllllIIllIII;
        if (llllllllllllllllIllIIllllIIllIII.antiVanish.get().booleanValue() && llllllllllllllllIllIIllllIIlIlll.packet instanceof class_2703 && (llllllllllllllllIllIIllllIIllIIl = (class_2703)llllllllllllllllIllIIllllIIlIlll.packet).method_11723() == class_2703.class_2704.field_12371) {
            try {
                for (class_2703.class_2705 llllllllllllllllIllIIllllIIllIlI : llllllllllllllllIllIIllllIIllIIl.method_11722()) {
                    if (mc.method_1562().method_2871(llllllllllllllllIllIIllllIIllIlI.method_11726().getId()) != null) continue;
                    llllllllllllllllIllIIllllIIllIII.toLookup.add(llllllllllllllllIllIIllllIIllIlI.method_11726().getId());
                }
            }
            catch (Exception llllllllllllllllIllIIllllIIlIIll) {
                // empty catch block
            }
        }
    }

    @EventHandler
    public void antiVanesh(TickEvent.Post llllllllllllllllIllIIllllIIIlIIl) {
        UUID llllllllllllllllIllIIllllIIIlIll;
        long llllllllllllllllIllIIllllIIIllII;
        Notifier llllllllllllllllIllIIllllIIIlIII;
        if (llllllllllllllllIllIIllllIIIlIII.antiVanish.get().booleanValue() && Math.abs(llllllllllllllllIllIIllllIIIlIII.lastTick - (llllllllllllllllIllIIllllIIIllII = Notifier.mc.field_1687.method_8510())) > 100L && (llllllllllllllllIllIIllllIIIlIll = llllllllllllllllIllIIllllIIIlIII.toLookup.poll()) != null) {
            try {
                String llllllllllllllllIllIIllllIIIllIl = Notifier.getPlayerNameFromUUID(llllllllllllllllIllIIllllIIIlIll);
                if (llllllllllllllllIllIIllllIIIllIl != null) {
                    ChatUtils.warning(String.valueOf(new StringBuilder().append(llllllllllllllllIllIIllllIIIllIl).append(" has gone into vanish.")), new Object[0]);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            llllllllllllllllIllIIllllIIIlIII.lastTick = llllllllllllllllIllIIllllIIIllII;
        }
    }

    public static class NameLookup
    implements Runnable {
        private final /* synthetic */ String uuidstr;
        private final /* synthetic */ UUID uuid;
        private volatile /* synthetic */ String name;

        public NameLookup(UUID lIIlIlIlIlllll) {
            NameLookup lIIlIlIllIIIlI;
            lIIlIlIllIIIlI.uuid = lIIlIlIlIlllll;
            lIIlIlIllIIIlI.uuidstr = lIIlIlIlIlllll.toString();
        }

        public String getName() {
            NameLookup lIIlIlIIlllllI;
            return lIIlIlIIlllllI.name;
        }

        public String lookUpName() {
            NameLookup lIIlIlIlIIlIII;
            class_1657 lIIlIlIlIIlIIl = null;
            if (mc.field_1687 != null) {
                lIIlIlIlIIlIIl = mc.field_1687.method_18470(lIIlIlIlIIlIII.uuid);
            }
            if (lIIlIlIlIIlIIl == null) {
                String lIIlIlIlIIlIll = String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(lIIlIlIlIIlIII.uuidstr.replace("-", "")).append("/names"));
                try {
                    JsonParser lIIlIlIlIlIIlI = new JsonParser();
                    String lIIlIlIlIlIIIl = IOUtils.toString((URL)new URL(lIIlIlIlIIlIll), (Charset)StandardCharsets.UTF_8);
                    JsonElement lIIlIlIlIlIIII = lIIlIlIlIlIIlI.parse(lIIlIlIlIlIIIl);
                    JsonArray lIIlIlIlIIllll = lIIlIlIlIlIIII.getAsJsonArray();
                    String lIIlIlIlIIlllI = lIIlIlIlIIllll.get(lIIlIlIlIIllll.size() - 1).toString();
                    JsonObject lIIlIlIlIIllIl = lIIlIlIlIlIIlI.parse(lIIlIlIlIIlllI).getAsJsonObject();
                    return lIIlIlIlIIllIl.get("name").toString();
                }
                catch (Exception lIIlIlIlIIllII) {
                    return null;
                }
            }
            return lIIlIlIlIIlIIl.method_5477().method_10851();
        }

        @Override
        public void run() {
            NameLookup lIIlIlIlIlllIl;
            lIIlIlIlIlllIl.name = lIIlIlIlIlllIl.lookUpName();
        }

        public NameLookup(String lIIlIlIllIIlll) {
            NameLookup lIIlIlIllIlIII;
            lIIlIlIllIlIII.uuidstr = lIIlIlIllIIlll;
            lIIlIlIllIlIII.uuid = UUID.fromString(lIIlIlIllIIlll);
        }
    }
}

