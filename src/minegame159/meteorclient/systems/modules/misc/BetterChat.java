/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_2583;
import net.minecraft.class_2585;
import net.minecraft.class_303;
import net.minecraft.class_5481;

public class BetterChat
extends Module {
    private final Setting<Boolean> antiSpam;
    private final Setting<Integer> antiSpamDepth;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> suffixSmallCaps;
    private final SimpleDateFormat dateFormat;
    private final Setting<Boolean> longerChatHistory;
    private final Setting<Boolean> annoy;
    private final Char2CharMap SMALL_CAPS;
    private final SettingGroup sgPrefix;
    private final Setting<String> suffixText;
    private final Setting<Boolean> infiniteChatBox;
    private final Setting<Boolean> prefixRandom;
    private final Setting<Boolean> prefixSmallCaps;
    private final Setting<Boolean> coordsProtection;
    private final Setting<Boolean> suffix;
    private final Setting<Boolean> timestamps;
    private final Setting<Boolean> fancy;
    private final SettingGroup sgSuffix;
    private final Setting<Boolean> suffixRandom;
    private final Setting<Integer> longerChatLines;
    private final Setting<Boolean> prefix;
    private final Setting<String> prefixText;

    private boolean lambda$new$0() {
        return this.prefixRandom.get() == false;
    }

    private static boolean lambda$onMessageRecieve$5(ReceiveMessageEvent receiveMessageEvent, class_303 class_3032) {
        return class_3032.method_1413() == receiveMessageEvent.id && receiveMessageEvent.id != 0;
    }

    private String applyFancy(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (this.SMALL_CAPS.containsKey(c)) {
                stringBuilder.append(this.SMALL_CAPS.get(c));
                continue;
            }
            stringBuilder.append(c);
            if (!false) continue;
            return null;
        }
        return String.valueOf(stringBuilder);
    }

    private boolean lambda$new$1() {
        return this.prefixRandom.get() == false;
    }

    private String getSuffix() {
        return this.suffix.get() != false ? this.getAffix(this.suffixText.get(), this.suffixSmallCaps.get(), this.suffixRandom.get()) : "";
    }

    private String applyAnnoy(String string) {
        StringBuilder stringBuilder = new StringBuilder(string.length());
        boolean bl = true;
        for (int n : string.codePoints().toArray()) {
            if (bl) {
                stringBuilder.appendCodePoint(Character.toUpperCase(n));
            } else {
                stringBuilder.appendCodePoint(Character.toLowerCase(n));
            }
            bl = !bl;
            if (true) continue;
            return null;
        }
        string = String.valueOf(stringBuilder);
        return string;
    }

    private class_2554 getSendButton(String string, String string2) {
        class_2585 class_25852 = new class_2585("[SEND ANYWAY]");
        class_2585 class_25853 = new class_2585("");
        class_2585 class_25854 = new class_2585(string2);
        class_25854.method_10862(class_25853.method_10866().method_27706(class_124.field_1080));
        class_25853.method_10852((class_2561)class_25854);
        class_25853.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append('\n').append(string))));
        class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1079).method_10958(new class_2558(class_2558.class_2559.field_11750, Commands.get().get(SayCommand.class).toString(string))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)class_25853)));
        return class_25852;
    }

    @EventHandler
    private void onMessageRecieve(ReceiveMessageEvent receiveMessageEvent) {
        class_2561 class_25612;
        ((ChatHudAccessor)this.mc.field_1705.method_1743()).getVisibleMessages().removeIf(arg_0 -> BetterChat.lambda$onMessageRecieve$4(receiveMessageEvent, arg_0));
        ((ChatHudAccessor)this.mc.field_1705.method_1743()).getMessages().removeIf(arg_0 -> BetterChat.lambda$onMessageRecieve$5(receiveMessageEvent, arg_0));
        class_2561 class_25613 = receiveMessageEvent.message;
        if (this.timestamps.get().booleanValue()) {
            Matcher matcher = Pattern.compile("^(<[0-9]{2}:[0-9]{2}>\\s)").matcher(class_25613.getString());
            if (matcher.matches()) {
                class_25613.method_10855().subList(0, 8).clear();
            }
            class_25612 = new class_2585(String.valueOf(new StringBuilder().append("<").append(this.dateFormat.format(new Date())).append("> "))).method_27692(class_124.field_1080);
            class_25613 = new class_2585("").method_10852(class_25612).method_10852(class_25613);
        }
        if (this.antiSpam.get().booleanValue()) {
            for (int i = 0; i < this.antiSpamDepth.get(); ++i) {
                class_25612 = this.appendAntiSpam(class_25613, i);
                if (class_25612 == null) continue;
                class_25613 = class_25612;
                ((ChatHudAccessor)this.mc.field_1705.method_1743()).getMessages().remove(i);
                ((ChatHudAccessor)this.mc.field_1705.method_1743()).getVisibleMessages().remove(i);
                if (3 != -1) continue;
                return;
            }
        }
        receiveMessageEvent.cancel();
        ((IChatHud)this.mc.field_1705.method_1743()).add(class_25613, receiveMessageEvent.id, this.mc.field_1705.method_1738(), false);
    }

    private static boolean lambda$onMessageRecieve$4(ReceiveMessageEvent receiveMessageEvent, class_303 class_3032) {
        return class_3032.method_1413() == receiveMessageEvent.id && receiveMessageEvent.id != 0;
    }

    public int getChatLength() {
        return this.longerChatLines.get();
    }

    private static boolean lambda$appendAntiSpam$6(class_2585 class_25852, int n, class_2583 class_25832, int n2) {
        class_25852.method_10852((class_2561)new class_2585(new String(Character.toChars(n2))).method_10862(class_25832));
        return true;
    }

    public BetterChat() {
        super(Categories.Misc, "better-chat", "Improves your chat experience in various ways.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgPrefix = this.settings.createGroup("Prefix");
        this.sgSuffix = this.settings.createGroup("Suffix");
        this.annoy = this.sgGeneral.add(new BoolSetting.Builder().name("annoy").description("Makes your messages aNnOyInG.").defaultValue(false).build());
        this.fancy = this.sgGeneral.add(new BoolSetting.Builder().name("fancy-chat").description("Makes your messages \u0493\u1d00\u0274\u1d04\u028f!").defaultValue(false).build());
        this.timestamps = this.sgGeneral.add(new BoolSetting.Builder().name("timestamps").description("Adds client side time stamps to the beginning of chat messages.").defaultValue(false).build());
        this.antiSpam = this.sgGeneral.add(new BoolSetting.Builder().name("anti-spam").description("Blocks duplicate messages from filling your chat.").defaultValue(true).build());
        this.antiSpamDepth = this.sgGeneral.add(new IntSetting.Builder().name("depth").description("How many messages to check for duplicate messages.").defaultValue(20).min(1).sliderMin(1).visible(this.antiSpam::get).build());
        this.coordsProtection = this.sgGeneral.add(new BoolSetting.Builder().name("coords-protection").description("Prevents you from sending messages in chat that may contain coordinates.").defaultValue(true).build());
        this.infiniteChatBox = this.sgGeneral.add(new BoolSetting.Builder().name("infinite-chat-box").description("Lets you type infinitely long messages.").defaultValue(true).build());
        this.longerChatHistory = this.sgGeneral.add(new BoolSetting.Builder().name("longer-chat-history").description("Extends chat length.").defaultValue(true).build());
        this.longerChatLines = this.sgGeneral.add(new IntSetting.Builder().name("extra-lines").description("The amount of extra chat lines.").defaultValue(1000).min(100).sliderMax(1000).visible(this.longerChatHistory::get).build());
        this.prefix = this.sgPrefix.add(new BoolSetting.Builder().name("prefix").description("Adds a prefix to your chat messages.").defaultValue(false).build());
        this.prefixRandom = this.sgPrefix.add(new BoolSetting.Builder().name("random").description("Uses a random number as your prefix.").defaultValue(false).build());
        this.prefixText = this.sgPrefix.add(new StringSetting.Builder().name("text").description("The text to add as your prefix.").defaultValue("> ").visible(this::lambda$new$0).build());
        this.prefixSmallCaps = this.sgPrefix.add(new BoolSetting.Builder().name("small-caps").description("Uses small caps in the prefix.").defaultValue(false).visible(this::lambda$new$1).build());
        this.suffix = this.sgSuffix.add(new BoolSetting.Builder().name("suffix").description("Adds a suffix to your chat messages.").defaultValue(false).build());
        this.suffixRandom = this.sgSuffix.add(new BoolSetting.Builder().name("random").description("Uses a random number as your suffix.").defaultValue(false).build());
        this.suffixText = this.sgSuffix.add(new StringSetting.Builder().name("text").description("The text to add as your suffix.").defaultValue(" | meteor on crack!").visible(this::lambda$new$2).build());
        this.suffixSmallCaps = this.sgSuffix.add(new BoolSetting.Builder().name("small-caps").description("Uses small caps in the suffix.").defaultValue(true).visible(this::lambda$new$3).build());
        this.SMALL_CAPS = new Char2CharArrayMap();
        this.dateFormat = new SimpleDateFormat("HH:mm");
        String[] stringArray = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] stringArray2 = "\u1d00\u0299\u1d04\u1d05\u1d07\ua730\u0262\u029c\u026a\u1d0a\u1d0b\u029f\u1d0d\u0274\u1d0f\u1d29q\u0280\ua731\u1d1b\u1d1c\u1d20\u1d21xy\u1d22".split("");
        for (int i = 0; i < stringArray.length; ++i) {
            this.SMALL_CAPS.put(stringArray[i].charAt(0), stringArray2[i].charAt(0));
        }
    }

    private boolean lambda$new$3() {
        return this.suffixRandom.get() == false;
    }

    public boolean isInfiniteChatBox() {
        return this.isActive() && this.infiniteChatBox.get() != false;
    }

    @EventHandler
    private void onMessageSend(SendMessageEvent sendMessageEvent) {
        String string = sendMessageEvent.message;
        if (this.annoy.get().booleanValue()) {
            string = this.applyAnnoy(string);
        }
        if (this.fancy.get().booleanValue()) {
            string = this.applyFancy(string);
        }
        string = String.valueOf(new StringBuilder().append(this.getPrefix()).append(string).append(this.getSuffix()));
        if (this.coordsProtection.get().booleanValue() && this.containsCoordinates(string)) {
            class_2585 class_25852 = new class_2585("It looks like there are coordinates in your message! ");
            class_2554 class_25542 = this.getSendButton(string, "Send your message to the global chat even if there are coordinates:");
            class_25852.method_10852((class_2561)class_25542);
            ChatUtils.sendMsg((class_2561)class_25852);
            sendMessageEvent.cancel();
            return;
        }
        sendMessageEvent.message = string;
    }

    private boolean containsCoordinates(String string) {
        return string.matches(".*(?<x>-?\\d{3,}(?:\\.\\d*)?)(?:\\s+(?<y>\\d{1,3}(?:\\.\\d*)?))?\\s+(?<z>-?\\d{3,}(?:\\.\\d*)?).*");
    }

    private boolean lambda$new$2() {
        return this.suffixRandom.get() == false;
    }

    private String getAffix(String string, boolean bl, boolean bl2) {
        if (bl2) {
            return String.format("(%03d) ", Utils.random(0, 1000));
        }
        if (bl) {
            return this.applyFancy(string);
        }
        return string;
    }

    public boolean isLongerChat() {
        return this.isActive() && this.longerChatHistory.get() != false;
    }

    private class_2561 appendAntiSpam(class_2561 class_25612, int n) {
        List<class_303<class_5481>> list = ((ChatHudAccessor)this.mc.field_1705.method_1743()).getVisibleMessages();
        if (list.isEmpty() || n < 0 || n > list.size() - 1) {
            return null;
        }
        class_303<class_5481> class_3032 = list.get(n);
        class_2585 class_25852 = new class_2585("");
        ((class_5481)class_3032.method_1412()).accept((arg_0, arg_1, arg_2) -> BetterChat.lambda$appendAntiSpam$6(class_25852, arg_0, arg_1, arg_2));
        String string = class_25852.getString();
        String string2 = class_25612.getString();
        if (string.equals(string2)) {
            return class_25852.method_10852((class_2561)new class_2585(" (2)").method_27692(class_124.field_1080));
        }
        Matcher matcher = Pattern.compile(".*(\\([0-9]+\\)$)").matcher(string);
        if (!matcher.matches()) {
            return null;
        }
        String string3 = matcher.group(matcher.groupCount());
        int n2 = Integer.parseInt(string3.substring(1, string3.length() - 1));
        String string4 = String.valueOf(new StringBuilder().append(" (").append(n2).append(")"));
        if (string.substring(0, string.length() - string4.length()).equals(string2)) {
            for (int i = 0; i < string4.length(); ++i) {
                class_25852.method_10855().remove(class_25852.method_10855().size() - 1);
                if (-1 < 4) continue;
                return null;
            }
            return class_25852.method_10852((class_2561)new class_2585(String.valueOf(new StringBuilder().append(" (").append(n2 + 1).append(")"))).method_27692(class_124.field_1080));
        }
        return null;
    }

    private String getPrefix() {
        return this.prefix.get() != false ? this.getAffix(this.prefixText.get(), this.prefixSmallCaps.get(), this.prefixRandom.get()) : "";
    }
}

