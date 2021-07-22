/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
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
 *  net.minecraft.class_639
 *  net.minecraft.class_642
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
import net.minecraft.class_639;
import net.minecraft.class_642;

public class ServerCommand
extends Command {
    private Integer ticks = 0;
    private static final List<String> ANTICHEAT_LIST = Arrays.asList("nocheatplus", "negativity", "warden", "horizon", "illegalstack", "coreprotect", "exploitsx");

    private String formatName(String string) {
        if (ANTICHEAT_LIST.contains(string)) {
            return String.format("%s%s(default)", new Object[]{class_124.field_1061, string});
        }
        if (string.contains("exploit") || string.contains("cheat") || string.contains("illegal")) {
            return String.format("%s%s(default)", new Object[]{class_124.field_1061, string});
        }
        return String.format("(highlight)%s(default)", string);
    }

    private int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        this.basicInfo();
        return 1;
    }

    private int lambda$build$3(CommandContext commandContext) throws CommandSyntaxException {
        float f = TickRate.INSTANCE.getTickRate();
        class_124 class_1242 = f > 17.0f ? class_124.field_1060 : (f > 12.0f ? class_124.field_1054 : class_124.field_1061);
        this.info("Current TPS: %s%.2f(default).", new Object[]{class_1242, Float.valueOf(f)});
        return 1;
    }

    @EventHandler
    public void onReadPacket(PacketEvent.Receive receive) {
        try {
            if (receive.packet instanceof class_2639) {
                class_2639 class_26392 = (class_2639)receive.packet;
                ArrayList<String> arrayList = new ArrayList<String>();
                Suggestions suggestions = class_26392.method_11397();
                if (suggestions == null) {
                    this.error("Invalid Packet.", new Object[0]);
                    return;
                }
                for (Suggestion suggestion : suggestions.getList()) {
                    String string;
                    String[] arrstring = suggestion.getText().split(":");
                    if (arrstring.length <= 1 || arrayList.contains(string = arrstring[0].replace("/", ""))) continue;
                    arrayList.add(string);
                }
                Collections.sort(arrayList);
                for (int i = 0; i < arrayList.size(); ++i) {
                    arrayList.set(i, this.formatName((String)arrayList.get(i)));
                    if (!false) continue;
                    return;
                }
                if (!arrayList.isEmpty()) {
                    this.info("Plugins (%d): %s ", arrayList.size(), Strings.join((String[])arrayList.toArray(new String[0]), (String)", "));
                } else {
                    this.error("No plugins found.", new Object[0]);
                }
                this.ticks = 0;
                MeteorClient.EVENT_BUS.unsubscribe(this);
            }
        }
        catch (Exception exception) {
            this.error("An error occurred while trying to find plugins", new Object[0]);
            this.ticks = 0;
            MeteorClient.EVENT_BUS.unsubscribe(this);
        }
    }

    public ServerCommand() {
        super("server", "Prints server information", new String[0]);
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(this::lambda$build$0);
        literalArgumentBuilder.then(ServerCommand.literal("info").executes(this::lambda$build$1));
        literalArgumentBuilder.then(ServerCommand.literal("plugins").executes(this::lambda$build$2));
        literalArgumentBuilder.then(ServerCommand.literal("tps").executes(this::lambda$build$3));
    }

    @EventHandler
    public void onTick(TickEvent.Post post) {
        Integer n = this.ticks;
        Integer n2 = this.ticks = Integer.valueOf(this.ticks + 1);
        if (this.ticks >= 5000) {
            this.error("Plugins check timed out", new Object[0]);
            MeteorClient.EVENT_BUS.unsubscribe(this);
            this.ticks = 0;
        }
    }

    private int lambda$build$2(CommandContext commandContext) throws CommandSyntaxException {
        this.ticks = 0;
        MeteorClient.EVENT_BUS.subscribe(this);
        ServerCommand.mc.field_1724.field_3944.method_2883((class_2596)new class_2805(0, "/"));
        return 1;
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        this.basicInfo();
        return 1;
    }

    private void basicInfo() {
        int n;
        class_2585 class_25852;
        class_2585 class_25853;
        if (mc.method_1496()) {
            class_1132 class_11322 = mc.method_1576();
            this.info("Singleplayer", new Object[0]);
            if (class_11322 != null) {
                this.info("Version: %s", class_11322.method_3827());
            }
            return;
        }
        class_642 class_6422 = mc.method_1558();
        if (class_6422 == null) {
            this.info("Couldn't obtain any server information.", new Object[0]);
            return;
        }
        String string = "";
        try {
            string = InetAddress.getByName(class_6422.field_3761).getHostAddress();
        }
        catch (UnknownHostException unknownHostException) {
            // empty catch block
        }
        if (string.isEmpty()) {
            class_25853 = new class_2585(String.valueOf(new StringBuilder().append((Object)class_124.field_1080).append(class_6422.field_3761)));
            class_25853.method_10862(class_25853.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, class_6422.field_3761)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
        } else {
            class_25853 = new class_2585(String.valueOf(new StringBuilder().append((Object)class_124.field_1080).append(class_6422.field_3761)));
            class_25853.method_10862(class_25853.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, class_6422.field_3761)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
            class_25852 = new class_2585(String.format("%s (%s)", new Object[]{class_124.field_1080, string}));
            class_25852.method_10862(class_25853.method_10866().method_10958(new class_2558(class_2558.class_2559.field_21462, string)).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Copy to clipboard"))));
            class_25853.method_10852((class_2561)class_25852);
        }
        this.info((class_2561)new class_2585(String.format("%sIP: ", new Object[]{class_124.field_1080})).method_10852((class_2561)class_25853));
        this.info("Port: %d", class_639.method_2950((String)class_6422.field_3761).method_2954());
        this.info("Type: %s", ServerCommand.mc.field_1724.method_3135() != null ? ServerCommand.mc.field_1724.method_3135() : "unknown");
        this.info("Motd: %s", class_6422.field_3757 != null ? class_6422.field_3757.getString() : "unknown");
        this.info("Version: %s", class_6422.field_3760.getString());
        this.info("Protocol version: %d", class_6422.field_3756);
        this.info("Difficulty: %s", ServerCommand.mc.field_1687.method_8407().method_5463().getString());
        class_25852 = mc.method_1562().method_2875();
        for (n = 5; n > 0 && !class_25852.method_9259(n); --n) {
            if (3 >= -1) continue;
            return;
        }
        this.info("Permission level: %d", n);
    }
}

