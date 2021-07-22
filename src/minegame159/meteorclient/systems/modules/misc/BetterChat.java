/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.chars.Char2CharArrayMap
 *  it.unimi.dsi.fastutil.chars.Char2CharMap
 *  net.minecraft.class_124
 *  net.minecraft.class_2554
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_303
 *  net.minecraft.class_5250
 *  net.minecraft.class_5481
 */
package minegame159.meteorclient.systems.modules.misc;

import it.unimi.dsi.fastutil.chars.Char2CharArrayMap;
import it.unimi.dsi.fastutil.chars.Char2CharMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.ReceiveMessageEvent;
import minegame159.meteorclient.events.game.SendMessageEvent;
import minegame159.meteorclient.mixin.ChatHudAccessor;
import minegame159.meteorclient.mixininterface.IChatHud;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.systems.commands.commands.SayCommand;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_2554;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_303;
import net.minecraft.class_5250;
import net.minecraft.class_5481;

public class BetterChat
extends Module {
    private final /* synthetic */ Setting<Boolean> antiSpam;
    private final /* synthetic */ Setting<Integer> antiSpamDepth;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> suffixSmallCaps;
    private final /* synthetic */ SimpleDateFormat dateFormat;
    private final /* synthetic */ Setting<Boolean> longerChatHistory;
    private final /* synthetic */ Setting<Boolean> annoy;
    private final /* synthetic */ Char2CharMap SMALL_CAPS;
    private final /* synthetic */ SettingGroup sgPrefix;
    private final /* synthetic */ Setting<String> suffixText;
    private final /* synthetic */ Setting<Boolean> infiniteChatBox;
    private final /* synthetic */ Setting<Boolean> prefixRandom;
    private final /* synthetic */ Setting<Boolean> prefixSmallCaps;
    private final /* synthetic */ Setting<Boolean> coordsProtection;
    private final /* synthetic */ Setting<Boolean> suffix;
    private final /* synthetic */ Setting<Boolean> timestamps;
    private final /* synthetic */ Setting<Boolean> fancy;
    private final /* synthetic */ SettingGroup sgSuffix;
    private final /* synthetic */ Setting<Boolean> suffixRandom;
    private final /* synthetic */ Setting<Integer> longerChatLines;
    private final /* synthetic */ Setting<Boolean> prefix;
    private final /* synthetic */ Setting<String> prefixText;

    private String applyFancy(String llllllllllllllllIlllIllIlllIlIlI) {
        StringBuilder llllllllllllllllIlllIllIlllIlIIl = new StringBuilder();
        for (char llllllllllllllllIlllIllIlllIllII : llllllllllllllllIlllIllIlllIlIlI.toCharArray()) {
            BetterChat llllllllllllllllIlllIllIlllIlIII;
            if (llllllllllllllllIlllIllIlllIlIII.SMALL_CAPS.containsKey(llllllllllllllllIlllIllIlllIllII)) {
                llllllllllllllllIlllIllIlllIlIIl.append(llllllllllllllllIlllIllIlllIlIII.SMALL_CAPS.get(llllllllllllllllIlllIllIlllIllII));
                continue;
            }
            llllllllllllllllIlllIllIlllIlIIl.append(llllllllllllllllIlllIllIlllIllII);
        }
        return String.valueOf(llllllllllllllllIlllIllIlllIlIIl);
    }

    private String getSuffix() {
        BetterChat llllllllllllllllIlllIllIllIlllII;
        return llllllllllllllllIlllIllIllIlllII.suffix.get() != false ? llllllllllllllllIlllIllIllIlllII.getAffix(llllllllllllllllIlllIllIllIlllII.suffixText.get(), llllllllllllllllIlllIllIllIlllII.suffixSmallCaps.get(), llllllllllllllllIlllIllIllIlllII.suffixRandom.get()) : "";
    }

    private String applyAnnoy(String llllllllllllllllIlllIllIllllllIl) {
        StringBuilder llllllllllllllllIlllIllIllllllII = new StringBuilder(llllllllllllllllIlllIllIllllllIl.length());
        boolean llllllllllllllllIlllIllIlllllIll = true;
        for (int llllllllllllllllIlllIllIllllllll : llllllllllllllllIlllIllIllllllIl.codePoints().toArray()) {
            if (llllllllllllllllIlllIllIlllllIll) {
                llllllllllllllllIlllIllIllllllII.appendCodePoint(Character.toUpperCase(llllllllllllllllIlllIllIllllllll));
            } else {
                llllllllllllllllIlllIllIllllllII.appendCodePoint(Character.toLowerCase(llllllllllllllllIlllIllIllllllll));
            }
            llllllllllllllllIlllIllIlllllIll = !llllllllllllllllIlllIllIlllllIll;
        }
        llllllllllllllllIlllIllIllllllIl = String.valueOf(llllllllllllllllIlllIllIllllllII);
        return llllllllllllllllIlllIllIllllllIl;
    }

    private class_2554 getSendButton(String llllllllllllllllIlllIllIllIIIIII, String llllllllllllllllIlllIllIlIllllll) {
        class_2585 llllllllllllllllIlllIllIllIIIIll = new class_2585("[SEND ANYWAY]");
        class_2585 llllllllllllllllIlllIllIllIIIIlI = new class_2585("");
        class_2585 llllllllllllllllIlllIllIllIIIIIl = new class_2585(llllllllllllllllIlllIllIlIllllll);
        llllllllllllllllIlllIllIllIIIIIl.method_10862(llllllllllllllllIlllIllIllIIIIlI.method_10866().method_27706(class_124.field_1080));
        llllllllllllllllIlllIllIllIIIIlI.method_10852((class_2561)llllllllllllllllIlllIllIllIIIIIl);
        llllllllllllllllIlllIllIllIIIIlI.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append('\n').append(llllllllllllllllIlllIllIllIIIIII))));
        llllllllllllllllIlllIllIllIIIIll.method_10862(llllllllllllllllIlllIllIllIIIIll.method_10866().method_27706(class_124.field_1079).method_10958(new class_2558(class_2558.class_2559.field_11750, Commands.get().get(SayCommand.class).toString(llllllllllllllllIlllIllIllIIIIII))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)llllllllllllllllIlllIllIllIIIIlI)));
        return llllllllllllllllIlllIllIllIIIIll;
    }

    @EventHandler
    private void onMessageRecieve(ReceiveMessageEvent llllllllllllllllIlllIlllIlIIlIIl) {
        BetterChat llllllllllllllllIlllIlllIlIIlIlI;
        ((ChatHudAccessor)llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1743()).getVisibleMessages().removeIf(llllllllllllllllIlllIllIlIlIlIII -> llllllllllllllllIlllIllIlIlIlIII.method_1413() == llllllllllllllllIlllIllIlIlIIlll.id && llllllllllllllllIlllIllIlIlIIlll.id != 0);
        ((ChatHudAccessor)llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1743()).getMessages().removeIf(llllllllllllllllIlllIllIlIlIlllI -> llllllllllllllllIlllIllIlIlIlllI.method_1413() == llllllllllllllllIlllIllIlIlIllll.id && llllllllllllllllIlllIllIlIlIllll.id != 0);
        class_2561 llllllllllllllllIlllIlllIlIIlIll = llllllllllllllllIlllIlllIlIIlIIl.message;
        if (llllllllllllllllIlllIlllIlIIlIlI.timestamps.get().booleanValue()) {
            Matcher llllllllllllllllIlllIlllIlIlIIIl = Pattern.compile("^(<[0-9]{2}:[0-9]{2}>\\s)").matcher(llllllllllllllllIlllIlllIlIIlIll.getString());
            if (llllllllllllllllIlllIlllIlIlIIIl.matches()) {
                llllllllllllllllIlllIlllIlIIlIll.method_10855().subList(0, 8).clear();
            }
            class_5250 llllllllllllllllIlllIlllIlIlIIII = new class_2585(String.valueOf(new StringBuilder().append("<").append(llllllllllllllllIlllIlllIlIIlIlI.dateFormat.format(new Date())).append("> "))).method_27692(class_124.field_1080);
            llllllllllllllllIlllIlllIlIIlIll = new class_2585("").method_10852((class_2561)llllllllllllllllIlllIlllIlIlIIII).method_10852(llllllllllllllllIlllIlllIlIIlIll);
        }
        if (llllllllllllllllIlllIlllIlIIlIlI.antiSpam.get().booleanValue()) {
            for (int llllllllllllllllIlllIlllIlIIlllI = 0; llllllllllllllllIlllIlllIlIIlllI < llllllllllllllllIlllIlllIlIIlIlI.antiSpamDepth.get(); ++llllllllllllllllIlllIlllIlIIlllI) {
                class_2561 llllllllllllllllIlllIlllIlIIllll = llllllllllllllllIlllIlllIlIIlIlI.appendAntiSpam(llllllllllllllllIlllIlllIlIIlIll, llllllllllllllllIlllIlllIlIIlllI);
                if (llllllllllllllllIlllIlllIlIIllll == null) continue;
                llllllllllllllllIlllIlllIlIIlIll = llllllllllllllllIlllIlllIlIIllll;
                ((ChatHudAccessor)llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1743()).getMessages().remove(llllllllllllllllIlllIlllIlIIlllI);
                ((ChatHudAccessor)llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1743()).getVisibleMessages().remove(llllllllllllllllIlllIlllIlIIlllI);
            }
        }
        llllllllllllllllIlllIlllIlIIlIIl.cancel();
        ((IChatHud)llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1743()).add(llllllllllllllllIlllIlllIlIIlIll, llllllllllllllllIlllIlllIlIIlIIl.id, llllllllllllllllIlllIlllIlIIlIlI.mc.field_1705.method_1738(), false);
    }

    public int getChatLength() {
        BetterChat llllllllllllllllIlllIlllIIIIlIII;
        return llllllllllllllllIlllIlllIIIIlIII.longerChatLines.get();
    }

    public BetterChat() {
        super(Categories.Misc, "better-chat", "Improves your chat experience in various ways.");
        BetterChat llllllllllllllllIlllIlllIlIllIlI;
        llllllllllllllllIlllIlllIlIllIlI.sgGeneral = llllllllllllllllIlllIlllIlIllIlI.settings.getDefaultGroup();
        llllllllllllllllIlllIlllIlIllIlI.sgPrefix = llllllllllllllllIlllIlllIlIllIlI.settings.createGroup("Prefix");
        llllllllllllllllIlllIlllIlIllIlI.sgSuffix = llllllllllllllllIlllIlllIlIllIlI.settings.createGroup("Suffix");
        llllllllllllllllIlllIlllIlIllIlI.annoy = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("annoy").description("Makes your messages aNnOyInG.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.fancy = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("fancy-chat").description("Makes your messages \u0493\u1d00\u0274\u1d04\u028f!").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.timestamps = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("timestamps").description("Adds client side time stamps to the beginning of chat messages.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.antiSpam = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("anti-spam").description("Blocks duplicate messages from filling your chat.").defaultValue(true).build());
        llllllllllllllllIlllIlllIlIllIlI.antiSpamDepth = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new IntSetting.Builder().name("depth").description("How many messages to check for duplicate messages.").defaultValue(20).min(1).sliderMin(1).visible(llllllllllllllllIlllIlllIlIllIlI.antiSpam::get).build());
        llllllllllllllllIlllIlllIlIllIlI.coordsProtection = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("coords-protection").description("Prevents you from sending messages in chat that may contain coordinates.").defaultValue(true).build());
        llllllllllllllllIlllIlllIlIllIlI.infiniteChatBox = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("infinite-chat-box").description("Lets you type infinitely long messages.").defaultValue(true).build());
        llllllllllllllllIlllIlllIlIllIlI.longerChatHistory = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new BoolSetting.Builder().name("longer-chat-history").description("Extends chat length.").defaultValue(true).build());
        llllllllllllllllIlllIlllIlIllIlI.longerChatLines = llllllllllllllllIlllIlllIlIllIlI.sgGeneral.add(new IntSetting.Builder().name("extra-lines").description("The amount of extra chat lines.").defaultValue(1000).min(100).sliderMax(1000).visible(llllllllllllllllIlllIlllIlIllIlI.longerChatHistory::get).build());
        llllllllllllllllIlllIlllIlIllIlI.prefix = llllllllllllllllIlllIlllIlIllIlI.sgPrefix.add(new BoolSetting.Builder().name("prefix").description("Adds a prefix to your chat messages.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.prefixRandom = llllllllllllllllIlllIlllIlIllIlI.sgPrefix.add(new BoolSetting.Builder().name("random").description("Uses a random number as your prefix.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.prefixText = llllllllllllllllIlllIlllIlIllIlI.sgPrefix.add(new StringSetting.Builder().name("text").description("The text to add as your prefix.").defaultValue("> ").visible(() -> {
            BetterChat llllllllllllllllIlllIllIlIIllIll;
            return llllllllllllllllIlllIllIlIIllIll.prefixRandom.get() == false;
        }).build());
        llllllllllllllllIlllIlllIlIllIlI.prefixSmallCaps = llllllllllllllllIlllIlllIlIllIlI.sgPrefix.add(new BoolSetting.Builder().name("small-caps").description("Uses small caps in the prefix.").defaultValue(false).visible(() -> {
            BetterChat llllllllllllllllIlllIllIlIIllllI;
            return llllllllllllllllIlllIllIlIIllllI.prefixRandom.get() == false;
        }).build());
        llllllllllllllllIlllIlllIlIllIlI.suffix = llllllllllllllllIlllIlllIlIllIlI.sgSuffix.add(new BoolSetting.Builder().name("suffix").description("Adds a suffix to your chat messages.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.suffixRandom = llllllllllllllllIlllIlllIlIllIlI.sgSuffix.add(new BoolSetting.Builder().name("random").description("Uses a random number as your suffix.").defaultValue(false).build());
        llllllllllllllllIlllIlllIlIllIlI.suffixText = llllllllllllllllIlllIlllIlIllIlI.sgSuffix.add(new StringSetting.Builder().name("text").description("The text to add as your suffix.").defaultValue(" | meteor on crack!").visible(() -> {
            BetterChat llllllllllllllllIlllIllIlIlIIIII;
            return llllllllllllllllIlllIllIlIlIIIII.suffixRandom.get() == false;
        }).build());
        llllllllllllllllIlllIlllIlIllIlI.suffixSmallCaps = llllllllllllllllIlllIlllIlIllIlI.sgSuffix.add(new BoolSetting.Builder().name("small-caps").description("Uses small caps in the suffix.").defaultValue(true).visible(() -> {
            BetterChat llllllllllllllllIlllIllIlIlIIlII;
            return llllllllllllllllIlllIllIlIlIIlII.suffixRandom.get() == false;
        }).build());
        llllllllllllllllIlllIlllIlIllIlI.SMALL_CAPS = new Char2CharArrayMap();
        llllllllllllllllIlllIlllIlIllIlI.dateFormat = new SimpleDateFormat("HH:mm");
        String[] llllllllllllllllIlllIlllIlIlllII = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] llllllllllllllllIlllIlllIlIllIll = "\u1d00\u0299\u1d04\u1d05\u1d07\ua730\u0262\u029c\u026a\u1d0a\u1d0b\u029f\u1d0d\u0274\u1d0f\u1d29q\u0280\ua731\u1d1b\u1d1c\u1d20\u1d21xy\u1d22".split("");
        for (int llllllllllllllllIlllIlllIlIllllI = 0; llllllllllllllllIlllIlllIlIllllI < llllllllllllllllIlllIlllIlIlllII.length; ++llllllllllllllllIlllIlllIlIllllI) {
            llllllllllllllllIlllIlllIlIllIlI.SMALL_CAPS.put(llllllllllllllllIlllIlllIlIlllII[llllllllllllllllIlllIlllIlIllllI].charAt(0), llllllllllllllllIlllIlllIlIllIll[llllllllllllllllIlllIlllIlIllllI].charAt(0));
        }
    }

    public boolean isInfiniteChatBox() {
        BetterChat llllllllllllllllIlllIlllIIIIlllI;
        return llllllllllllllllIlllIlllIIIIlllI.isActive() && llllllllllllllllIlllIlllIIIIlllI.infiniteChatBox.get() != false;
    }

    @EventHandler
    private void onMessageSend(SendMessageEvent llllllllllllllllIlllIlllIIIlIIll) {
        BetterChat llllllllllllllllIlllIlllIIIlIlII;
        String llllllllllllllllIlllIlllIIIlIlIl = llllllllllllllllIlllIlllIIIlIIll.message;
        if (llllllllllllllllIlllIlllIIIlIlII.annoy.get().booleanValue()) {
            llllllllllllllllIlllIlllIIIlIlIl = llllllllllllllllIlllIlllIIIlIlII.applyAnnoy(llllllllllllllllIlllIlllIIIlIlIl);
        }
        if (llllllllllllllllIlllIlllIIIlIlII.fancy.get().booleanValue()) {
            llllllllllllllllIlllIlllIIIlIlIl = llllllllllllllllIlllIlllIIIlIlII.applyFancy(llllllllllllllllIlllIlllIIIlIlIl);
        }
        llllllllllllllllIlllIlllIIIlIlIl = String.valueOf(new StringBuilder().append(llllllllllllllllIlllIlllIIIlIlII.getPrefix()).append(llllllllllllllllIlllIlllIIIlIlIl).append(llllllllllllllllIlllIlllIIIlIlII.getSuffix()));
        if (llllllllllllllllIlllIlllIIIlIlII.coordsProtection.get().booleanValue() && llllllllllllllllIlllIlllIIIlIlII.containsCoordinates(llllllllllllllllIlllIlllIIIlIlIl)) {
            class_2585 llllllllllllllllIlllIlllIIIllIIl = new class_2585("It looks like there are coordinates in your message! ");
            class_2554 llllllllllllllllIlllIlllIIIllIII = llllllllllllllllIlllIlllIIIlIlII.getSendButton(llllllllllllllllIlllIlllIIIlIlIl, "Send your message to the global chat even if there are coordinates:");
            llllllllllllllllIlllIlllIIIllIIl.method_10852((class_2561)llllllllllllllllIlllIlllIIIllIII);
            ChatUtils.sendMsg((class_2561)llllllllllllllllIlllIlllIIIllIIl);
            llllllllllllllllIlllIlllIIIlIIll.cancel();
            return;
        }
        llllllllllllllllIlllIlllIIIlIIll.message = llllllllllllllllIlllIlllIIIlIlIl;
    }

    private boolean containsCoordinates(String llllllllllllllllIlllIllIllIIllIl) {
        return llllllllllllllllIlllIllIllIIllIl.matches(".*(?<x>-?\\d{3,}(?:\\.\\d*)?)(?:\\s+(?<y>\\d{1,3}(?:\\.\\d*)?))?\\s+(?<z>-?\\d{3,}(?:\\.\\d*)?).*");
    }

    private String getAffix(String llllllllllllllllIlllIllIllIlIIlI, boolean llllllllllllllllIlllIllIllIlIIIl, boolean llllllllllllllllIlllIllIllIlIIII) {
        if (llllllllllllllllIlllIllIllIlIIII) {
            return String.format("(%03d) ", Utils.random(0, 1000));
        }
        if (llllllllllllllllIlllIllIllIlIIIl) {
            BetterChat llllllllllllllllIlllIllIllIlIIll;
            return llllllllllllllllIlllIllIllIlIIll.applyFancy(llllllllllllllllIlllIllIllIlIIlI);
        }
        return llllllllllllllllIlllIllIllIlIIlI;
    }

    public boolean isLongerChat() {
        BetterChat llllllllllllllllIlllIlllIIIIlIll;
        return llllllllllllllllIlllIlllIIIIlIll.isActive() && llllllllllllllllIlllIlllIIIIlIll.longerChatHistory.get() != false;
    }

    private class_2561 appendAntiSpam(class_2561 llllllllllllllllIlllIlllIIllIIlI, int llllllllllllllllIlllIlllIIlIlIIl) {
        BetterChat llllllllllllllllIlllIlllIIllIIll;
        List<class_303<class_5481>> llllllllllllllllIlllIlllIIllIIII = ((ChatHudAccessor)llllllllllllllllIlllIlllIIllIIll.mc.field_1705.method_1743()).getVisibleMessages();
        if (llllllllllllllllIlllIlllIIllIIII.isEmpty() || llllllllllllllllIlllIlllIIlIlIIl < 0 || llllllllllllllllIlllIlllIIlIlIIl > llllllllllllllllIlllIlllIIllIIII.size() - 1) {
            return null;
        }
        class_303<class_5481> llllllllllllllllIlllIlllIIlIllll = llllllllllllllllIlllIlllIIllIIII.get(llllllllllllllllIlllIlllIIlIlIIl);
        class_2585 llllllllllllllllIlllIlllIIlIlllI = new class_2585("");
        ((class_5481)llllllllllllllllIlllIlllIIlIllll.method_1412()).accept((llllllllllllllllIlllIllIlIllIlll, llllllllllllllllIlllIllIlIllIIll, llllllllllllllllIlllIllIlIllIlIl) -> {
            llllllllllllllllIlllIlllIIlIlllI.method_10852((class_2561)new class_2585(new String(Character.toChars(llllllllllllllllIlllIllIlIllIlIl))).method_10862(llllllllllllllllIlllIllIlIllIIll));
            return true;
        });
        String llllllllllllllllIlllIlllIIlIllIl = llllllllllllllllIlllIlllIIlIlllI.getString();
        String llllllllllllllllIlllIlllIIlIllII = llllllllllllllllIlllIlllIIllIIlI.getString();
        if (llllllllllllllllIlllIlllIIlIllIl.equals(llllllllllllllllIlllIlllIIlIllII)) {
            return llllllllllllllllIlllIlllIIlIlllI.method_10852((class_2561)new class_2585(" (2)").method_27692(class_124.field_1080));
        }
        Matcher llllllllllllllllIlllIlllIIllIlll = Pattern.compile(".*(\\([0-9]+\\)$)").matcher(llllllllllllllllIlllIlllIIlIllIl);
        if (!llllllllllllllllIlllIlllIIllIlll.matches()) {
            return null;
        }
        String llllllllllllllllIlllIlllIIllIllI = llllllllllllllllIlllIlllIIllIlll.group(llllllllllllllllIlllIlllIIllIlll.groupCount());
        int llllllllllllllllIlllIlllIIllIlIl = Integer.parseInt(llllllllllllllllIlllIlllIIllIllI.substring(1, llllllllllllllllIlllIlllIIllIllI.length() - 1));
        String llllllllllllllllIlllIlllIIllIlII = String.valueOf(new StringBuilder().append(" (").append(llllllllllllllllIlllIlllIIllIlIl).append(")"));
        if (llllllllllllllllIlllIlllIIlIllIl.substring(0, llllllllllllllllIlllIlllIIlIllIl.length() - llllllllllllllllIlllIlllIIllIlII.length()).equals(llllllllllllllllIlllIlllIIlIllII)) {
            for (int llllllllllllllllIlllIlllIIlllIII = 0; llllllllllllllllIlllIlllIIlllIII < llllllllllllllllIlllIlllIIllIlII.length(); ++llllllllllllllllIlllIlllIIlllIII) {
                llllllllllllllllIlllIlllIIlIlllI.method_10855().remove(llllllllllllllllIlllIlllIIlIlllI.method_10855().size() - 1);
            }
            return llllllllllllllllIlllIlllIIlIlllI.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append(" (").append(llllllllllllllllIlllIlllIIllIlIl + 1).append(")"))).method_27692(class_124.field_1080));
        }
        return null;
    }

    private String getPrefix() {
        BetterChat llllllllllllllllIlllIllIlllIIIII;
        return llllllllllllllllIlllIllIlllIIIII.prefix.get() != false ? llllllllllllllllIlllIllIlllIIIII.getAffix(llllllllllllllllIlllIllIlllIIIII.prefixText.get(), llllllllllllllllIlllIllIlllIIIII.prefixSmallCaps.get(), llllllllllllllllIlllIllIlllIIIII.prefixRandom.get()) : "";
    }
}

