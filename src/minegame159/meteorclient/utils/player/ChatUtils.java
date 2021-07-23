/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.player;

import minegame159.meteorclient.mixin.ChatHudAccessor;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.RainbowColor;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import net.minecraft.class_124;
import net.minecraft.class_243;
import net.minecraft.class_2554;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2585;
import net.minecraft.class_5251;
import org.jetbrains.annotations.Nullable;

public class ChatUtils {
    private static final RainbowColor RAINBOW = new RainbowColor();

    public static void sendMsg(int n, class_124 class_1242, String string, Object ... objectArray) {
        ChatUtils.sendMsg(n, null, null, class_1242, string, objectArray);
    }

    public static void sendMsg(int n, @Nullable String string, @Nullable class_124 class_1242, class_124 class_1243, String string2, Object ... objectArray) {
        ChatUtils.sendMsg(n, string, class_1242, ChatUtils.formatMsg(string2, class_1243, objectArray), class_1243);
    }

    public static void sendMsg(class_2561 class_25612) {
        ChatUtils.sendMsg(null, class_25612);
    }

    public static void sendMsg(int n, @Nullable String string, @Nullable class_124 class_1242, String string2, class_124 class_1243) {
        class_2585 class_25852 = new class_2585(string2);
        class_25852.method_10862(class_25852.method_10866().method_27706(class_1243));
        ChatUtils.sendMsg(n, string, class_1242, (class_2561)class_25852);
    }

    public static void sendMsg(int n, @Nullable String string, @Nullable class_124 class_1242, class_2561 class_25612) {
        if (Utils.mc.field_1687 == null) {
            return;
        }
        class_2585 class_25852 = new class_2585("");
        class_25852.method_10852((class_2561)ChatUtils.getMeteorPrefix());
        if (string != null) {
            class_25852.method_10852((class_2561)ChatUtils.getCustomPrefix(string, class_1242));
        }
        class_25852.method_10852(class_25612);
        if (!Config.get().deleteChatCommandsInfo) {
            n = 0;
        }
        ((ChatHudAccessor)Utils.mc.field_1705.method_1743()).add((class_2561)class_25852, n);
    }

    private static String formatMsg(String string, class_124 class_1242, Object ... objectArray) {
        String string2 = String.format(string, objectArray);
        string2 = string2.replaceAll("\\(default\\)", class_1242.toString());
        string2 = string2.replaceAll("\\(highlight\\)", class_124.field_1068.toString());
        string2 = string2.replaceAll("\\(underline\\)", class_124.field_1073.toString());
        return string2;
    }

    public static void sendMsg(class_124 class_1242, String string, Object ... objectArray) {
        ChatUtils.sendMsg(0, null, null, class_1242, string, objectArray);
    }

    public static class_2554 formatCoords(class_243 class_2432) {
        String string = String.format("(highlight)(underline)%.0f, %.0f, %.0f(default)", class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
        string = ChatUtils.formatMsg(string, class_124.field_1080, new Object[0]);
        class_2585 class_25852 = new class_2585(string);
        class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1067).method_10958(new class_2558(class_2558.class_2559.field_11750, String.format("%sb goto %d %d %d", Config.get().prefix, (int)class_2432.field_1352, (int)class_2432.field_1351, (int)class_2432.field_1350))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Set as Baritone goal"))));
        return class_25852;
    }

    public static void sendMsg(String string, class_2561 class_25612) {
        ChatUtils.sendMsg(0, string, class_124.field_1076, class_25612);
    }

    public static void error(String string, Object ... objectArray) {
        ChatUtils.sendMsg(class_124.field_1061, string, objectArray);
    }

    public static void info(String string, String string2, Object ... objectArray) {
        ChatUtils.sendMsg(0, string, class_124.field_1076, class_124.field_1080, string2, objectArray);
    }

    private static class_2554 getCustomPrefix(String string, class_124 class_1242) {
        class_2585 class_25852 = new class_2585("");
        class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1080));
        class_25852.method_27693("[");
        class_2585 class_25853 = new class_2585(string);
        class_25853.method_10862(class_25853.method_10866().method_27706(class_1242));
        class_25852.method_10852((class_2561)class_25853);
        class_25852.method_27693("] ");
        return class_25852;
    }

    public static void error(String string, String string2, Object ... objectArray) {
        ChatUtils.sendMsg(0, string, class_124.field_1076, class_124.field_1061, string2, objectArray);
    }

    public static void info(String string, Object ... objectArray) {
        ChatUtils.sendMsg(class_124.field_1080, string, objectArray);
    }

    public static void warning(String string, Object ... objectArray) {
        ChatUtils.sendMsg(class_124.field_1054, string, objectArray);
    }

    public static void warning(String string, String string2, Object ... objectArray) {
        ChatUtils.sendMsg(0, string, class_124.field_1076, class_124.field_1054, string2, objectArray);
    }

    private static class_2554 getMeteorPrefix() {
        class_2585 class_25852 = new class_2585("");
        class_2585 class_25853 = new class_2585("");
        RAINBOW.setSpeed(RainbowColors.GLOBAL.getSpeed());
        if (Config.get().rainbowPrefix) {
            class_25852.method_10852((class_2561)new class_2585("B").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("e").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("d").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("T").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("r").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("a").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            class_25852.method_10852((class_2561)new class_2585("p").method_10862(class_25852.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
        } else {
            class_25852 = new class_2585("BedTrap");
            class_25852.method_10862(class_25852.method_10866().method_27706(class_124.field_1061));
        }
        class_25853.method_10862(class_25853.method_10866().method_27706(class_124.field_1061));
        class_25853.method_27693("[");
        class_25853.method_10852((class_2561)class_25852);
        class_25853.method_27693("] ");
        return class_25853;
    }
}

