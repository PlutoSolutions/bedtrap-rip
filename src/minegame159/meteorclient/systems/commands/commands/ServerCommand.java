/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.suggestion.Suggestion
 *  com.mojang.brigadier.suggestion.Suggestions
 *  joptsimple.internal.Strings
 *  net.minecraft.class_1132
 *  net.minecraft.class_124
 *  net.minecraft.class_2172
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_2596
 *  net.minecraft.class_2639
 *  net.minecraft.class_2805
 *  net.minecraft.class_637
 *  net.minecraft.class_639
 *  net.minecraft.class_642
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import joptsimple.internal.Strings;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.utils.world.TickRate;
import net.minecraft.class_1132;
import net.minecraft.class_124;
import net.minecraft.class_2172;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_2596;
import net.minecraft.class_2639;
import net.minecraft.class_2805;
import net.minecraft.class_637;
import net.minecraft.class_639;
import net.minecraft.class_642;

public class ServerCommand
extends Command {
    private /* synthetic */ Integer ticks;
    private static final /* synthetic */ List<String> ANTICHEAT_LIST;

    private String formatName(String lllllllllllllllllIlIlIlIllllIlIl) {
        if (ANTICHEAT_LIST.contains(lllllllllllllllllIlIlIlIllllIlIl)) {
            return String.format("%s%s(default)", new Object[]{class_124.field_1061, lllllllllllllllllIlIlIlIllllIlIl});
        }
        if (lllllllllllllllllIlIlIlIllllIlIl.contains("exploit") || lllllllllllllllllIlIlIlIllllIlIl.contains("cheat") || lllllllllllllllllIlIlIlIllllIlIl.contains("illegal")) {
            return String.format("%s%s(default)", new Object[]{class_124.field_1061, lllllllllllllllllIlIlIlIllllIlIl});
        }
        return String.format("(highlight)%s(default)", lllllllllllllllllIlIlIlIllllIlIl);
    }

    @EventHandler
    public void onReadPacket(PacketEvent.Receive lllllllllllllllllIlIlIllIIIIIIlI) {
        ServerCommand lllllllllllllllllIlIlIllIIIIIIll;
        try {
            if (lllllllllllllllllIlIlIllIIIIIIlI.packet instanceof class_2639) {
                class_2639 lllllllllllllllllIlIlIllIIIIIlll = (class_2639)lllllllllllllllllIlIlIllIIIIIIlI.packet;
                ArrayList<String> lllllllllllllllllIlIlIllIIIIIllI = new ArrayList<String>();
                Suggestions lllllllllllllllllIlIlIllIIIIIlIl = lllllllllllllllllIlIlIllIIIIIlll.method_11397();
                if (lllllllllllllllllIlIlIllIIIIIlIl == null) {
                    lllllllllllllllllIlIlIllIIIIIIll.error("Invalid Packet.", new Object[0]);
                    return;
                }
                for (Suggestion lllllllllllllllllIlIlIllIIIIlIIl : lllllllllllllllllIlIlIllIIIIIlIl.getList()) {
                    String lllllllllllllllllIlIlIllIIIIlIll;
                    String[] lllllllllllllllllIlIlIllIIIIlIlI = lllllllllllllllllIlIlIllIIIIlIIl.getText().split(":");
                    if (lllllllllllllllllIlIlIllIIIIlIlI.length <= 1 || lllllllllllllllllIlIlIllIIIIIllI.contains(lllllllllllllllllIlIlIllIIIIlIll = lllllllllllllllllIlIlIllIIIIlIlI[0].replace("/", ""))) continue;
                    lllllllllllllllllIlIlIllIIIIIllI.add(lllllllllllllllllIlIlIllIIIIlIll);
                }
                Collections.sort(lllllllllllllllllIlIlIllIIIIIllI);
                for (int lllllllllllllllllIlIlIllIIIIlIII = 0; lllllllllllllllllIlIlIllIIIIlIII < lllllllllllllllllIlIlIllIIIIIllI.size(); ++lllllllllllllllllIlIlIllIIIIlIII) {
                    lllllllllllllllllIlIlIllIIIIIllI.set(lllllllllllllllllIlIlIllIIIIlIII, lllllllllllllllllIlIlIllIIIIIIll.formatName((String)lllllllllllllllllIlIlIllIIIIIllI.get(lllllllllllllllllIlIlIllIIIIlIII)));
                }
                if (!lllllllllllllllllIlIlIllIIIIIllI.isEmpty()) {
                    lllllllllllllllllIlIlIllIIIIIIll.info("Plugins (%d): %s ", lllllllllllllllllIlIlIllIIIIIllI.size(), Strings.join((String[])lllllllllllllllllIlIlIllIIIIIllI.toArray(new String[0]), (String)", "));
                } else {
                    lllllllllllllllllIlIlIllIIIIIIll.error("No plugins found.", new Object[0]);
                }
                lllllllllllllllllIlIlIllIIIIIIll.ticks = 0;
                MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIlIlIllIIIIIIll);
            }
        }
        catch (Exception lllllllllllllllllIlIlIllIIIIIlII) {
            lllllllllllllllllIlIlIllIIIIIIll.error("An error occurred while trying to find plugins", new Object[0]);
            lllllllllllllllllIlIlIllIIIIIIll.ticks = 0;
            MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIlIlIllIIIIIIll);
        }
    }

    public ServerCommand() {
        super("server", "Prints server information", new String[0]);
        ServerCommand lllllllllllllllllIlIlIllIIlllIIl;
        lllllllllllllllllIlIlIllIIlllIIl.ticks = 0;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> lllllllllllllllllIlIlIllIIllIIlI) {
        ServerCommand lllllllllllllllllIlIlIllIIllIlIl;
        lllllllllllllllllIlIlIllIIllIIlI.executes(lllllllllllllllllIlIlIlIllIllllI -> {
            ServerCommand lllllllllllllllllIlIlIlIllIlllll;
            lllllllllllllllllIlIlIlIllIlllll.basicInfo();
            return 1;
        });
        lllllllllllllllllIlIlIllIIllIIlI.then(ServerCommand.literal("info").executes(lllllllllllllllllIlIlIlIlllIIIlI -> {
            ServerCommand lllllllllllllllllIlIlIlIlllIIIll;
            lllllllllllllllllIlIlIlIlllIIIll.basicInfo();
            return 1;
        }));
        lllllllllllllllllIlIlIllIIllIIlI.then(ServerCommand.literal("plugins").executes(lllllllllllllllllIlIlIlIlllIIllI -> {
            ServerCommand lllllllllllllllllIlIlIlIlllIIlll;
            lllllllllllllllllIlIlIlIlllIIlll.ticks = 0;
            MeteorClient.EVENT_BUS.subscribe(lllllllllllllllllIlIlIlIlllIIlll);
            ServerCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2805(0, "/"));
            return 1;
        }));
        lllllllllllllllllIlIlIllIIllIIlI.then(ServerCommand.literal("tps").executes(lllllllllllllllllIlIlIlIlllIlllI -> {
            ServerCommand lllllllllllllllllIlIlIlIlllIlIll;
            class_124 lllllllllllllllllIlIlIlIlllIllII;
            float lllllllllllllllllIlIlIlIlllIllIl = TickRate.INSTANCE.getTickRate();
            if (lllllllllllllllllIlIlIlIlllIllIl > 17.0f) {
                class_124 lllllllllllllllllIlIlIlIllllIIIl = class_124.field_1060;
            } else if (lllllllllllllllllIlIlIlIlllIllIl > 12.0f) {
                class_124 lllllllllllllllllIlIlIlIllllIIII = class_124.field_1054;
            } else {
                lllllllllllllllllIlIlIlIlllIllII = class_124.field_1061;
            }
            lllllllllllllllllIlIlIlIlllIlIll.info("Current TPS: %s%.2f(default).", new Object[]{lllllllllllllllllIlIlIlIlllIllII, Float.valueOf(lllllllllllllllllIlIlIlIlllIllIl)});
            return 1;
        }));
    }

    @EventHandler
    public void onTick(TickEvent.Post lllllllllllllllllIlIlIllIIIllIII) {
        ServerCommand lllllllllllllllllIlIlIllIIIllIIl;
        Integer lllllllllllllllllIlIlIllIIIlIllI = lllllllllllllllllIlIlIllIIIllIIl.ticks;
        Integer lllllllllllllllllIlIlIllIIIlIlIl = lllllllllllllllllIlIlIllIIIllIIl.ticks = Integer.valueOf(lllllllllllllllllIlIlIllIIIllIIl.ticks + 1);
        if (lllllllllllllllllIlIlIllIIIllIIl.ticks >= 5000) {
            lllllllllllllllllIlIlIllIIIllIIl.error("Plugins check timed out", new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(lllllllllllllllllIlIlIllIIIllIIl);
            lllllllllllllllllIlIlIllIIIllIIl.ticks = 0;
        }
    }

    static {
        ANTICHEAT_LIST = Arrays.asList("nocheatplus", "negativity", "warden", "horizon", "illegalstack", "coreprotect", "exploitsx");
    }

    private void basicInfo() {
        int lllllllllllllllllIlIlIllIIlIIIll;
        class_2585 lllllllllllllllllIlIlIllIIlIIlIl;
        ServerCommand lllllllllllllllllIlIlIllIIlIlIII;
        if (mc.method_1496()) {
            class_1132 lllllllllllllllllIlIlIllIIlIlIll = mc.method_1576();
            lllllllllllllllllIlIlIllIIlIlIII.info("Singleplayer", new Object[0]);
            if (lllllllllllllllllIlIlIllIIlIlIll != null) {
                lllllllllllllllllIlIlIllIIlIlIII.info("Version: %s", lllllllllllllllllIlIlIllIIlIlIll.method_3827());
            }
            return;
        }
        class_642 lllllllllllllllllIlIlIllIIlIIlll = mc.method_1558();
        if (lllllllllllllllllIlIlIllIIlIIlll == null) {
            lllllllllllllllllIlIlIllIIlIlIII.info("Couldn't obtain any server information.", new Object[0]);
            return;
        }
        String lllllllllllllllllIlIlIllIIlIIllI = "";
        try {
            lllllllllllllllllIlIlIllIIlIIllI = InetAddress.getByName(lllllllllllllllllIlIlIllIIlIIlll.field_3761).getHostAddress();
        }
        catch (UnknownHostException lllllllllllllllllIlIlIllIIIlllll) {
            // empty catch block
        }
        if (lllllllllllllllllIlIlIllIIlIIllI.isEmpty()) {
            class_2585 lllllllllllllllllIlIlIllIIlIlIlI = new class_2585(String.valueOf(new StringBuilder().append((Object)class_124.field_1080).append(lllllllllllllllllIlIlIllIIlIIlll.field_3761)));
            lllllllllllllllllIlIlIllIIlIlIlI.method_10862(lllllllllllllllllIlIlIllIIlIlIlI.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, lllllllllllllllllIlIlIllIIlIIlll.field_3761)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
        } else {
            lllllllllllllllllIlIlIllIIlIIlIl = new class_2585(String.valueOf(new StringBuilder().append((Object)class_124.field_1080).append(lllllllllllllllllIlIlIllIIlIIlll.field_3761)));
            lllllllllllllllllIlIlIllIIlIIlIl.method_10862(lllllllllllllllllIlIlIllIIlIIlIl.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, lllllllllllllllllIlIlIllIIlIIlll.field_3761)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
            class_2585 lllllllllllllllllIlIlIllIIlIlIIl = new class_2585(String.format("%s (%s)", new Object[]{class_124.field_1080, lllllllllllllllllIlIlIllIIlIIllI}));
            lllllllllllllllllIlIlIllIIlIlIIl.method_10862(lllllllllllllllllIlIlIllIIlIIlIl.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, lllllllllllllllllIlIlIllIIlIIllI)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
            lllllllllllllllllIlIlIllIIlIIlIl.method_10852((class_2561)lllllllllllllllllIlIlIllIIlIlIIl);
        }
        lllllllllllllllllIlIlIllIIlIlIII.info((class_2561)new class_2585(String.format("%sIP: ", new Object[]{class_124.field_1080})).method_10852((class_2561)lllllllllllllllllIlIlIllIIlIIlIl));
        lllllllllllllllllIlIlIllIIlIlIII.info("Port: %d", class_639.method_2950((String)lllllllllllllllllIlIlIllIIlIIlll.field_3761).method_2954());
        lllllllllllllllllIlIlIllIIlIlIII.info("Type: %s", ServerCommand.mc.field_1724.method_3135() != null ? ServerCommand.mc.field_1724.method_3135() : "unknown");
        lllllllllllllllllIlIlIllIIlIlIII.info("Motd: %s", lllllllllllllllllIlIlIllIIlIIlll.field_3757 != null ? lllllllllllllllllIlIlIllIIlIIlll.field_3757.getString() : "unknown");
        lllllllllllllllllIlIlIllIIlIlIII.info("Version: %s", lllllllllllllllllIlIlIllIIlIIlll.field_3760.getString());
        lllllllllllllllllIlIlIllIIlIlIII.info("Protocol version: %d", lllllllllllllllllIlIlIllIIlIIlll.field_3756);
        lllllllllllllllllIlIlIllIIlIlIII.info("Difficulty: %s", ServerCommand.mc.field_1687.method_8407().method_5463().getString());
        class_637 lllllllllllllllllIlIlIllIIlIIlII = mc.method_1562().method_2875();
        for (lllllllllllllllllIlIlIllIIlIIIll = 5; lllllllllllllllllIlIlIllIIlIIIll > 0 && !lllllllllllllllllIlIlIllIIlIIlII.method_9259(lllllllllllllllllIlIlIllIIlIIIll); --lllllllllllllllllIlIlIllIIlIIIll) {
        }
        lllllllllllllllllIlIlIllIIlIlIII.info("Permission level: %d", lllllllllllllllllIlIlIllIIlIIIll);
    }
}

