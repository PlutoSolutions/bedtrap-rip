/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_243
 *  net.minecraft.class_2554
 *  net.minecraft.class_2558
 *  net.minecraft.class_2558$class_2559
 *  net.minecraft.class_2561
 *  net.minecraft.class_2568
 *  net.minecraft.class_2568$class_5247
 *  net.minecraft.class_2585
 *  net.minecraft.class_5251
 *  org.jetbrains.annotations.Nullable
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
    private static final /* synthetic */ RainbowColor RAINBOW;

    public static void sendMsg(int lIIIllllIlIl, class_124 lIIIllllIIII, String lIIIllllIIll, Object ... lIIIllllIIlI) {
        ChatUtils.sendMsg(lIIIllllIlIl, null, null, lIIIllllIIII, lIIIllllIIll, lIIIllllIIlI);
    }

    public static void sendMsg(int lIIIlllIIIIl, @Nullable String lIIIlllIIIII, @Nullable class_124 lIIIlllIIlIl, class_124 lIIIlllIIlII, String lIIIlllIIIll, Object ... lIIIlllIIIlI) {
        ChatUtils.sendMsg(lIIIlllIIIIl, lIIIlllIIIII, lIIIlllIIlIl, ChatUtils.formatMsg(lIIIlllIIIll, lIIIlllIIlII, lIIIlllIIIlI), lIIIlllIIlII);
    }

    public static void sendMsg(class_2561 lIIlIIIIlIIl) {
        ChatUtils.sendMsg(null, lIIlIIIIlIIl);
    }

    public static void sendMsg(int lIIIllIIllll, @Nullable String lIIIllIlIlII, @Nullable class_124 lIIIllIlIIll, String lIIIllIIllII, class_124 lIIIllIlIIIl) {
        class_2585 lIIIllIlIIII = new class_2585(lIIIllIIllII);
        lIIIllIlIIII.method_10862(lIIIllIlIIII.method_10866().method_27706(lIIIllIlIIIl));
        ChatUtils.sendMsg(lIIIllIIllll, lIIIllIlIlII, lIIIllIlIIll, (class_2561)lIIIllIlIIII);
    }

    public static void sendMsg(int lIIIlIllllll, @Nullable String lIIIllIIIIll, @Nullable class_124 lIIIlIllllIl, class_2561 lIIIlIllllII) {
        if (Utils.mc.field_1687 == null) {
            return;
        }
        class_2585 lIIIllIIIIII = new class_2585("");
        lIIIllIIIIII.method_10852((class_2561)ChatUtils.getMeteorPrefix());
        if (lIIIllIIIIll != null) {
            lIIIllIIIIII.method_10852((class_2561)ChatUtils.getCustomPrefix(lIIIllIIIIll, lIIIlIllllIl));
        }
        lIIIllIIIIII.method_10852(lIIIlIllllII);
        if (!Config.get().deleteChatCommandsInfo) {
            lIIIlIllllll = 0;
        }
        ((ChatHudAccessor)Utils.mc.field_1705.method_1743()).add((class_2561)lIIIllIIIIII, lIIIlIllllll);
    }

    private static String formatMsg(String lIIIlIlIIlII, class_124 lIIIlIIlllll, Object ... lIIIlIIllllI) {
        String lIIIlIlIIIIl = String.format(lIIIlIlIIlII, lIIIlIIllllI);
        lIIIlIlIIIIl = lIIIlIlIIIIl.replaceAll("\\(default\\)", lIIIlIIlllll.toString());
        lIIIlIlIIIIl = lIIIlIlIIIIl.replaceAll("\\(highlight\\)", class_124.field_1068.toString());
        lIIIlIlIIIIl = lIIIlIlIIIIl.replaceAll("\\(underline\\)", class_124.field_1073.toString());
        return lIIIlIlIIIIl;
    }

    public static void sendMsg(class_124 lIIIllllllll, String lIIIlllllIll, Object ... lIIIllllllIl) {
        ChatUtils.sendMsg(0, null, null, lIIIllllllll, lIIIlllllIll, lIIIllllllIl);
    }

    public static class_2554 formatCoords(class_243 lIIIlIIlIllI) {
        String lIIIlIIllIII = String.format("(highlight)(underline)%.0f, %.0f, %.0f(default)", lIIIlIIlIllI.field_1352, lIIIlIIlIllI.field_1351, lIIIlIIlIllI.field_1350);
        lIIIlIIllIII = ChatUtils.formatMsg(lIIIlIIllIII, class_124.field_1080, new Object[0]);
        class_2585 lIIIlIIlIlll = new class_2585(lIIIlIIllIII);
        lIIIlIIlIlll.method_10862(lIIIlIIlIlll.method_10866().method_27706(class_124.field_1067).method_10958(new class_2558(class_2558.class_2559.field_11750, String.format("%sb goto %d %d %d", Config.get().prefix, (int)lIIIlIIlIllI.field_1352, (int)lIIIlIIlIllI.field_1351, (int)lIIIlIIlIllI.field_1350))).method_10949(new class_2568(class_2568.class_5247.field_24342, (Object)new class_2585("Set as Baritone goal"))));
        return lIIIlIIlIlll;
    }

    public ChatUtils() {
        ChatUtils lIIlIIlllIlI;
    }

    public static void sendMsg(String lIIlIIIIIlII, class_2561 lIIlIIIIIlIl) {
        ChatUtils.sendMsg(0, lIIlIIIIIlII, class_124.field_1076, lIIlIIIIIlIl);
    }

    public static void error(String lIIlIIIllIII, Object ... lIIlIIIlIlIl) {
        ChatUtils.sendMsg(class_124.field_1061, lIIlIIIllIII, lIIlIIIlIlIl);
    }

    public static void info(String lIIlIIlIllII, String lIIlIIlIlIll, Object ... lIIlIIlIllIl) {
        ChatUtils.sendMsg(0, lIIlIIlIllII, class_124.field_1076, class_124.field_1080, lIIlIIlIlIll, lIIlIIlIllIl);
    }

    private static class_2554 getCustomPrefix(String lIIIlIllIllI, class_124 lIIIlIllIIIl) {
        class_2585 lIIIlIllIlII = new class_2585("");
        lIIIlIllIlII.method_10862(lIIIlIllIlII.method_10866().method_27706(class_124.field_1080));
        lIIIlIllIlII.method_27693("[");
        class_2585 lIIIlIllIIll = new class_2585(lIIIlIllIllI);
        lIIIlIllIIll.method_10862(lIIIlIllIIll.method_10866().method_27706(lIIIlIllIIIl));
        lIIIlIllIlII.method_10852((class_2561)lIIIlIllIIll);
        lIIIlIllIlII.method_27693("] ");
        return lIIIlIllIlII;
    }

    public static void error(String lIIlIIIlIIIl, String lIIlIIIIllIl, Object ... lIIlIIIIllII) {
        ChatUtils.sendMsg(0, lIIlIIIlIIIl, class_124.field_1076, class_124.field_1061, lIIlIIIIllIl, lIIlIIIIllII);
    }

    public static void info(String lIIlIIllIllI, Object ... lIIlIIllIlIl) {
        ChatUtils.sendMsg(class_124.field_1080, lIIlIIllIllI, lIIlIIllIlIl);
    }

    public static void warning(String lIIlIIlIIlll, Object ... lIIlIIlIIllI) {
        ChatUtils.sendMsg(class_124.field_1054, lIIlIIlIIlll, lIIlIIlIIllI);
    }

    static {
        RAINBOW = new RainbowColor();
    }

    public static void warning(String lIIlIIIlllIl, String lIIlIIIlllll, Object ... lIIlIIIllIll) {
        ChatUtils.sendMsg(0, lIIlIIIlllIl, class_124.field_1076, class_124.field_1054, lIIlIIIlllll, lIIlIIIllIll);
    }

    private static class_2554 getMeteorPrefix() {
        class_2585 lIIIlIlIllII = new class_2585("");
        class_2585 lIIIlIlIlIll = new class_2585("");
        RAINBOW.setSpeed(RainbowColors.GLOBAL.getSpeed());
        if (Config.get().rainbowPrefix) {
            lIIIlIlIllII.method_10852((class_2561)new class_2585("B").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("e").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("d").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("T").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("r").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("a").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
            lIIIlIlIllII.method_10852((class_2561)new class_2585("p").method_10862(lIIIlIlIllII.method_10866().method_27703(new class_5251(RAINBOW.getNext().getPacked()))));
        } else {
            lIIIlIlIllII = new class_2585("BedTrap");
            lIIIlIlIllII.method_10862(lIIIlIlIllII.method_10866().method_27706(class_124.field_1061));
        }
        lIIIlIlIlIll.method_10862(lIIIlIlIlIll.method_10866().method_27706(class_124.field_1061));
        lIIIlIlIlIll.method_27693("[");
        lIIIlIlIlIll.method_10852((class_2561)lIIIlIlIllII);
        lIIIlIlIlIll.method_27693("] ");
        return lIIIlIlIlIll;
    }
}

