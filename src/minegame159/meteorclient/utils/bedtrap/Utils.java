/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2IntArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.class_124
 *  net.minecraft.class_1291
 *  net.minecraft.class_1297
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1771
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1787
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_1803
 *  net.minecraft.class_1823
 *  net.minecraft.class_1828
 *  net.minecraft.class_1835
 *  net.minecraft.class_1887
 *  net.minecraft.class_2338
 *  net.minecraft.class_2378
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_442
 *  net.minecraft.class_500
 *  net.minecraft.class_526
 *  net.minecraft.class_641
 *  net.minecraft.class_642
 *  org.apache.commons.io.output.ByteArrayOutputStream
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.commons.lang3.SystemUtils
 *  org.lwjgl.glfw.GLFW
 */
package minegame159.meteorclient.utils.bedtrap;

import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import minegame159.meteorclient.mixin.ClientPlayNetworkHandlerAccessor;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.mixin.MinecraftServerAccessor;
import minegame159.meteorclient.mixininterface.IMinecraftClient;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1297;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1787;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1803;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_1887;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_442;
import net.minecraft.class_500;
import net.minecraft.class_526;
import net.minecraft.class_641;
import net.minecraft.class_642;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.glfw.GLFW;

public class Utils {
    public static /* synthetic */ class_310 mc;
    private static final /* synthetic */ Random random;
    public static /* synthetic */ boolean isReleasingTrident;
    public static final /* synthetic */ Color WHITE;
    private static final /* synthetic */ DecimalFormat df;
    public static /* synthetic */ boolean firstTimeTitleScreen;

    public static int unpackLong2(long lllllllllllllllllIllIIlIllllIlIl) {
        return (int)(lllllllllllllllllIllIIlIllllIlIl >> 32 & 0xFFFFL);
    }

    public static boolean isThrowable(class_1792 lllllllllllllllllIllIIllIIllIlll) {
        return lllllllllllllllllIllIIllIIllIlll instanceof class_1779 || lllllllllllllllllIllIIllIIllIlll instanceof class_1753 || lllllllllllllllllIllIIllIIllIlll instanceof class_1764 || lllllllllllllllllIllIIllIIllIlll instanceof class_1823 || lllllllllllllllllIllIIllIIllIlll instanceof class_1771 || lllllllllllllllllIllIIllIIllIlll instanceof class_1776 || lllllllllllllllllIllIIllIIllIlll instanceof class_1828 || lllllllllllllllllIllIIllIIllIlll instanceof class_1803 || lllllllllllllllllIllIIllIIllIlll instanceof class_1787 || lllllllllllllllllIllIIllIIllIlll instanceof class_1835;
    }

    public static boolean isShulker(class_1792 lllllllllllllllllIllIIllIIlllIlI) {
        return lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8545 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8722 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8380 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8050 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8829 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8271 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8548 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8520 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8627 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8451 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8213 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8816 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8350 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8584 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8461 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8676 || lllllllllllllllllIllIIllIIlllIlI == class_1802.field_8268;
    }

    public static int unpackLong4(long lllllllllllllllllIllIIlIllllIIII) {
        return (int)(lllllllllllllllllIllIIlIllllIIII & 0xFFFFL);
    }

    public static float clamp(float lllllllllllllllllIllIIllIIlIIlII, float lllllllllllllllllIllIIllIIlIIIll, float lllllllllllllllllIllIIllIIIlllll) {
        if (lllllllllllllllllIllIIllIIlIIlII < lllllllllllllllllIllIIllIIlIIIll) {
            return lllllllllllllllllIllIIllIIlIIIll;
        }
        if (lllllllllllllllllIllIIllIIlIIlII > lllllllllllllllllIllIIllIIIlllll) {
            return lllllllllllllllllIllIIllIIIlllll;
        }
        return lllllllllllllllllIllIIllIIlIIlII;
    }

    public static boolean canUpdate() {
        return mc != null && Utils.mc.field_1687 != null && Utils.mc.field_1724 != null;
    }

    public static class_243 vec3d(class_2338 lllllllllllllllllIllIIllllIlIIll) {
        return new class_243((double)lllllllllllllllllIllIIllllIlIIll.method_10263(), (double)lllllllllllllllllIllIIllllIlIIll.method_10264(), (double)lllllllllllllllllIllIIllllIlIIll.method_10260());
    }

    public static int search(String lllllllllllllllllIllIIlllIllllll, String lllllllllllllllllIllIIlllIlllllI) {
        String[] lllllllllllllllllIllIIlllIllllII;
        int lllllllllllllllllIllIIlllIllllIl = 0;
        for (String lllllllllllllllllIllIIllllIIIIII : lllllllllllllllllIllIIlllIllllII = lllllllllllllllllIllIIlllIlllllI.split(" ")) {
            if (!StringUtils.containsIgnoreCase((CharSequence)lllllllllllllllllIllIIlllIllllll, (CharSequence)lllllllllllllllllIllIIllllIIIIII)) continue;
            ++lllllllllllllllllIllIIlllIllllIl;
        }
        return lllllllllllllllllIllIIlllIllllIl;
    }

    public static int clamp(int lllllllllllllllllIllIIllIIlIllIl, int lllllllllllllllllIllIIllIIlIllII, int lllllllllllllllllIllIIllIIlIlIll) {
        if (lllllllllllllllllIllIIllIIlIllIl < lllllllllllllllllIllIIllIIlIllII) {
            return lllllllllllllllllIllIIllIIlIllII;
        }
        if (lllllllllllllllllIllIIllIIlIllIl > lllllllllllllllllIllIIllIIlIlIll) {
            return lllllllllllllllllIllIIllIIlIlIll;
        }
        return lllllllllllllllllIllIIllIIlIllIl;
    }

    public static void leftClick() {
        Utils.mc.field_1690.field_1886.method_23481(true);
        ((MinecraftClientAccessor)mc).leftClick();
        Utils.mc.field_1690.field_1886.method_23481(false);
    }

    public static Dimension getDimension() {
        switch (class_310.method_1551().field_1687.method_27983().method_29177().method_12832()) {
            case "the_nether": {
                return Dimension.Nether;
            }
            case "the_end": {
                return Dimension.End;
            }
        }
        return Dimension.Overworld;
    }

    public static double distance(double lllllllllllllllllIllIIlllIIIllll, double lllllllllllllllllIllIIlllIIIlllI, double lllllllllllllllllIllIIlllIIIIIll, double lllllllllllllllllIllIIlllIIIIIlI, double lllllllllllllllllIllIIlllIIIIIIl, double lllllllllllllllllIllIIlllIIIlIlI) {
        double lllllllllllllllllIllIIlllIIIlIIl = lllllllllllllllllIllIIlllIIIIIlI - lllllllllllllllllIllIIlllIIIllll;
        double lllllllllllllllllIllIIlllIIIlIII = lllllllllllllllllIllIIlllIIIIIIl - lllllllllllllllllIllIIlllIIIlllI;
        double lllllllllllllllllIllIIlllIIIIlll = lllllllllllllllllIllIIlllIIIlIlI - lllllllllllllllllIllIIlllIIIIIll;
        return Math.sqrt(lllllllllllllllllIllIIlllIIIlIIl * lllllllllllllllllIllIIlllIIIlIIl + lllllllllllllllllIllIIlllIIIlIII * lllllllllllllllllIllIIlllIIIlIII + lllllllllllllllllIllIIlllIIIIlll * lllllllllllllllllIllIIlllIIIIlll);
    }

    public static Object2IntMap<class_1291> createStatusEffectMap() {
        Object2IntArrayMap lllllllllllllllllIllIIllllIlIIII = new Object2IntArrayMap(class_2378.field_11159.method_10235().size());
        class_2378.field_11159.forEach(arg_0 -> Utils.lambda$createStatusEffectMap$0((Object2IntMap)lllllllllllllllllIllIIllllIlIIII, arg_0));
        return lllllllllllllllllIllIIllllIlIIII;
    }

    public static double distanceToCamera(class_1297 lllllllllllllllllIllIIllIlIIllll) {
        return Utils.distanceToCamera(lllllllllllllllllIllIIllIlIIllll.method_23317(), lllllllllllllllllIllIIllIlIIllll.method_23318(), lllllllllllllllllIllIIllIlIIllll.method_23321());
    }

    public static void rightClick() {
        ((IMinecraftClient)mc).rightClick();
    }

    @SafeVarargs
    public static <T> Object2BooleanOpenHashMap<T> asObject2BooleanOpenHashMap(T ... lllllllllllllllllIllIIllIIIIllII) {
        HashMap<T, Boolean> lllllllllllllllllIllIIllIIIIllIl = new HashMap<T, Boolean>();
        for (T lllllllllllllllllIllIIllIIIIllll : lllllllllllllllllIllIIllIIIIllII) {
            lllllllllllllllllIllIIllIIIIllIl.put(lllllllllllllllllIllIIllIIIIllll, true);
        }
        return new Object2BooleanOpenHashMap(lllllllllllllllllIllIIllIIIIllIl);
    }

    public static byte[] readBytes(File lllllllllllllllllIllIIllIllIIIll) {
        try {
            int lllllllllllllllllIllIIllIllIIlIl;
            FileInputStream lllllllllllllllllIllIIllIllIlIII = new FileInputStream(lllllllllllllllllIllIIllIllIIIll);
            ByteArrayOutputStream lllllllllllllllllIllIIllIllIIlll = new ByteArrayOutputStream();
            byte[] lllllllllllllllllIllIIllIllIIllI = new byte[256];
            while ((lllllllllllllllllIllIIllIllIIlIl = ((InputStream)lllllllllllllllllIllIIllIllIlIII).read(lllllllllllllllllIllIIllIllIIllI)) > 0) {
                lllllllllllllllllIllIIllIllIIlll.write(lllllllllllllllllIllIIllIllIIllI, 0, lllllllllllllllllIllIIllIllIIlIl);
            }
            ((InputStream)lllllllllllllllllIllIIllIllIlIII).close();
            return lllllllllllllllllIllIIllIllIIlll.toByteArray();
        }
        catch (IOException lllllllllllllllllIllIIllIllIIlII) {
            lllllllllllllllllIllIIllIllIIlII.printStackTrace();
            return new byte[0];
        }
    }

    public static String getKeyName(int lllllllllllllllllIllIIllIlllIIlI) {
        switch (lllllllllllllllllIllIIllIlllIIlI) {
            case -1: {
                return "Unknown";
            }
            case 256: {
                return "Esc";
            }
            case 96: {
                return "Grave Accent";
            }
            case 161: {
                return "World 1";
            }
            case 162: {
                return "World 2";
            }
            case 283: {
                return "Print Screen";
            }
            case 284: {
                return "Pause";
            }
            case 260: {
                return "Insert";
            }
            case 261: {
                return "Delete";
            }
            case 268: {
                return "Home";
            }
            case 266: {
                return "Page Up";
            }
            case 267: {
                return "Page Down";
            }
            case 269: {
                return "End";
            }
            case 258: {
                return "Tab";
            }
            case 341: {
                return "Left Control";
            }
            case 345: {
                return "Right Control";
            }
            case 342: {
                return "Left Alt";
            }
            case 346: {
                return "Right Alt";
            }
            case 340: {
                return "Left Shift";
            }
            case 344: {
                return "Right Shift";
            }
            case 265: {
                return "Arrow Up";
            }
            case 264: {
                return "Arrow Down";
            }
            case 263: {
                return "Arrow Left";
            }
            case 262: {
                return "Arrow Right";
            }
            case 39: {
                return "Apostrophe";
            }
            case 259: {
                return "Backspace";
            }
            case 280: {
                return "Caps Lock";
            }
            case 348: {
                return "Menu";
            }
            case 343: {
                return "Left Super";
            }
            case 347: {
                return "Right Super";
            }
            case 257: {
                return "Enter";
            }
            case 282: {
                return "Num Lock";
            }
            case 32: {
                return "Space";
            }
            case 290: {
                return "F1";
            }
            case 291: {
                return "F2";
            }
            case 292: {
                return "F3";
            }
            case 293: {
                return "F4";
            }
            case 294: {
                return "F5";
            }
            case 295: {
                return "F6";
            }
            case 296: {
                return "F7";
            }
            case 297: {
                return "F8";
            }
            case 298: {
                return "F9";
            }
            case 299: {
                return "F10";
            }
            case 300: {
                return "F11";
            }
            case 301: {
                return "F12";
            }
            case 302: {
                return "F13";
            }
            case 303: {
                return "F14";
            }
            case 304: {
                return "F15";
            }
            case 305: {
                return "F16";
            }
            case 306: {
                return "F17";
            }
            case 307: {
                return "F18";
            }
            case 308: {
                return "F19";
            }
            case 309: {
                return "F20";
            }
            case 310: {
                return "F21";
            }
            case 311: {
                return "F22";
            }
            case 312: {
                return "F23";
            }
            case 313: {
                return "F24";
            }
            case 314: {
                return "F25";
            }
        }
        String lllllllllllllllllIllIIllIlllIlII = GLFW.glfwGetKeyName((int)lllllllllllllllllIllIIllIlllIIlI, (int)0);
        if (lllllllllllllllllIllIIllIlllIlII == null) {
            return "Unknown";
        }
        return StringUtils.capitalize((String)lllllllllllllllllIllIIllIlllIlII);
    }

    public static String doubleToString(double lllllllllllllllllIllIIllIIllIIlI) {
        if (lllllllllllllllllIllIIllIIllIIlI % 1.0 == 0.0) {
            return Integer.toString((int)lllllllllllllllllIllIIllIIllIIlI);
        }
        return df.format(lllllllllllllllllIllIIllIIllIIlI);
    }

    public static String getEnchantSimpleName(class_1887 lllllllllllllllllIllIIllllIIlIlI, int lllllllllllllllllIllIIllllIIlIIl) {
        return lllllllllllllllllIllIIllllIIlIlI.method_8179(0).getString().substring(0, lllllllllllllllllIllIIllllIIlIIl);
    }

    public static double clamp(double lllllllllllllllllIllIIllIIIllIII, double lllllllllllllllllIllIIllIIIllIlI, double lllllllllllllllllIllIIllIIIlIllI) {
        if (lllllllllllllllllIllIIllIIIllIII < lllllllllllllllllIllIIllIIIllIlI) {
            return lllllllllllllllllIllIIllIIIllIlI;
        }
        if (lllllllllllllllllIllIIllIIIllIII > lllllllllllllllllIllIIllIIIlIllI) {
            return lllllllllllllllllIllIIllIIIlIllI;
        }
        return lllllllllllllllllIllIIllIIIllIII;
    }

    public static String getButtonName(int lllllllllllllllllIllIIllIllIllll) {
        switch (lllllllllllllllllIllIIllIllIllll) {
            case -1: {
                return "Unknown";
            }
            case 0: {
                return "Mouse Left";
            }
            case 1: {
                return "Mouse Right";
            }
            case 2: {
                return "Mouse Middle";
            }
        }
        return String.valueOf(new StringBuilder().append("Mouse ").append(lllllllllllllllllIllIIllIllIllll));
    }

    public static String floatToString(float lllllllllllllllllIllIIllIIllIlIl) {
        if (lllllllllllllllllIllIIllIIllIlIl % 1.0f == 0.0f) {
            return Integer.toString((int)lllllllllllllllllIllIIllIIllIlIl);
        }
        return Float.toString(lllllllllllllllllIllIIllIIllIlIl);
    }

    public static long packLong(int lllllllllllllllllIllIIllIIIIIIlI, int lllllllllllllllllIllIIlIllllllIl, int lllllllllllllllllIllIIlIllllllII, int lllllllllllllllllIllIIlIllllllll) {
        return ((long)lllllllllllllllllIllIIllIIIIIIlI << 48) + ((long)lllllllllllllllllIllIIlIllllllIl << 32) + ((long)lllllllllllllllllIllIIlIllllllII << 16) + (long)lllllllllllllllllIllIIlIllllllll;
    }

    public static double distanceToCamera(double lllllllllllllllllIllIIllIlIllIIl, double lllllllllllllllllIllIIllIlIlIlII, double lllllllllllllllllIllIIllIlIlIlll) {
        class_4184 lllllllllllllllllIllIIllIlIlIllI = Utils.mc.field_1773.method_19418();
        return Math.sqrt(lllllllllllllllllIllIIllIlIlIllI.method_19326().method_1028(lllllllllllllllllIllIIllIlIllIIl, lllllllllllllllllIllIIllIlIlIlII, lllllllllllllllllIllIIllIlIlIlll));
    }

    public static double squaredDistance(double lllllllllllllllllIllIIlllIlIIIIl, double lllllllllllllllllIllIIlllIIlllll, double lllllllllllllllllIllIIlllIlIlIII, double lllllllllllllllllIllIIlllIIlllIl, double lllllllllllllllllIllIIlllIIlllII, double lllllllllllllllllIllIIlllIIllIll) {
        double lllllllllllllllllIllIIlllIlIIlII = lllllllllllllllllIllIIlllIIlllIl - lllllllllllllllllIllIIlllIlIIIIl;
        double lllllllllllllllllIllIIlllIlIIIll = lllllllllllllllllIllIIlllIIlllII - lllllllllllllllllIllIIlllIIlllll;
        double lllllllllllllllllIllIIlllIlIIIlI = lllllllllllllllllIllIIlllIIllIll - lllllllllllllllllIllIIlllIlIlIII;
        return lllllllllllllllllIllIIlllIlIIlII * lllllllllllllllllIllIIlllIlIIlII + lllllllllllllllllIllIIlllIlIIIll * lllllllllllllllllIllIIlllIlIIIll + lllllllllllllllllIllIIlllIlIIIlI * lllllllllllllllllIllIIlllIlIIIlI;
    }

    private static /* synthetic */ void lambda$createStatusEffectMap$0(Object2IntMap lllllllllllllllllIllIIlIlllIllII, class_1291 lllllllllllllllllIllIIlIlllIlIll) {
        lllllllllllllllllIllIIlIlllIllII.put((Object)lllllllllllllllllIllIIlIlllIlIll, 0);
    }

    public Utils() {
        Utils lllllllllllllllllIllIIlllllIIllI;
    }

    public static String getWorldName() {
        if (mc.method_1542()) {
            File lllllllllllllllllIllIIllIlllllII = ((MinecraftServerAccessor)mc.method_1576()).getSession().method_27424(Utils.mc.field_1687.method_27983());
            if (lllllllllllllllllIllIIllIlllllII.toPath().relativize(Utils.mc.field_1697.toPath()).getNameCount() != 2) {
                lllllllllllllllllIllIIllIlllllII = lllllllllllllllllIllIIllIlllllII.getParentFile();
            }
            return lllllllllllllllllIllIIllIlllllII.getName();
        }
        if (mc.method_1558() != null) {
            String lllllllllllllllllIllIIllIllllIll;
            String string = lllllllllllllllllIllIIllIllllIll = mc.method_1589() ? "realms" : Utils.mc.method_1558().field_3761;
            if (SystemUtils.IS_OS_WINDOWS) {
                lllllllllllllllllIllIIllIllllIll = lllllllllllllllllIllIIllIllllIll.replace(":", "_");
            }
            return lllllllllllllllllIllIIllIllllIll;
        }
        return "";
    }

    public static int getWindowHeight() {
        return mc.method_22683().method_4506();
    }

    static {
        WHITE = new Color(255, 255, 255);
        random = new Random();
        firstTimeTitleScreen = true;
        df = new DecimalFormat("0");
        df.setMaximumFractionDigits(340);
        DecimalFormatSymbols lllllllllllllllllIllIIlIlllIIlll = new DecimalFormatSymbols();
        lllllllllllllllllIllIIlIlllIIlll.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(lllllllllllllllllIllIIlIlllIIlll);
    }

    public static void addMeteorPvpToServerList() {
        class_641 lllllllllllllllllIllIIllllIllllI = new class_641(mc);
        lllllllllllllllllIllIIllllIllllI.method_2981();
        boolean lllllllllllllllllIllIIllllIlllIl = false;
        for (int lllllllllllllllllIllIIllllIlllll = 0; lllllllllllllllllIllIIllllIlllll < lllllllllllllllllIllIIllllIllllI.method_2984(); ++lllllllllllllllllIllIIllllIlllll) {
            class_642 lllllllllllllllllIllIIlllllIIIII = lllllllllllllllllIllIIllllIllllI.method_2982(lllllllllllllllllIllIIllllIlllll);
            if (!lllllllllllllllllIllIIlllllIIIII.field_3761.contains("pvp.meteorclient.com")) continue;
            lllllllllllllllllIllIIllllIlllIl = true;
            break;
        }
        if (!lllllllllllllllllIllIIllllIlllIl) {
            lllllllllllllllllIllIIllllIllllI.method_2988(new class_642("Meteor Pvp", "pvp.meteorclient.com", false));
            lllllllllllllllllIllIIllllIllllI.method_2987();
        }
    }

    public static double random(double lllllllllllllllllIllIIllIlIIIllI, double lllllllllllllllllIllIIllIlIIIIll) {
        return lllllllllllllllllIllIIllIlIIIllI + (lllllllllllllllllIllIIllIlIIIIll - lllllllllllllllllIllIIllIlIIIllI) * random.nextDouble();
    }

    public static int getRenderDistance() {
        return Math.max(Utils.mc.field_1690.field_1870, ((ClientPlayNetworkHandlerAccessor)mc.method_1562()).getChunkLoadDistance());
    }

    public static String nameToTitle(String lllllllllllllllllIllIIllIllllIII) {
        return Arrays.stream(lllllllllllllllllIllIIllIllllIII.split("-")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static int unpackLong1(long lllllllllllllllllIllIIlIlllllIII) {
        return (int)(lllllllllllllllllIllIIlIlllllIII >> 48 & 0xFFFFL);
    }

    public static int unpackLong3(long lllllllllllllllllIllIIlIllllIIll) {
        return (int)(lllllllllllllllllIllIIlIllllIIll >> 16 & 0xFFFFL);
    }

    public static int getWindowWidth() {
        return mc.method_22683().method_4489();
    }

    public static int random(int lllllllllllllllllIllIIllIlIIllII, int lllllllllllllllllIllIIllIlIIlIIl) {
        return random.nextInt(lllllllllllllllllIllIIllIlIIlIIl - lllllllllllllllllIllIIllIlIIllII) + lllllllllllllllllIllIIllIlIIllII;
    }

    public static void sendMessage(String lllllllllllllllllIllIIllIIlllllI, Object ... lllllllllllllllllIllIIllIIllllIl) {
        if (Utils.mc.field_1724 == null) {
            return;
        }
        lllllllllllllllllIllIIllIIlllllI = String.format(lllllllllllllllllIllIIllIIlllllI, lllllllllllllllllIllIIllIIllllIl);
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#yellow", class_124.field_1054.toString());
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#white", class_124.field_1068.toString());
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#red", class_124.field_1061.toString());
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#blue", class_124.field_1078.toString());
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#pink", class_124.field_1076.toString());
        lllllllllllllllllIllIIllIIlllllI = lllllllllllllllllIllIIllIIlllllI.replaceAll("#gray", class_124.field_1080.toString());
        Utils.mc.field_1724.method_7353((class_2561)new class_2585(lllllllllllllllllIllIIllIIlllllI), false);
    }

    public static boolean isWhitelistedScreen() {
        if (Utils.mc.field_1755 instanceof class_442) {
            return true;
        }
        if (Utils.mc.field_1755 instanceof class_500) {
            return true;
        }
        return Utils.mc.field_1755 instanceof class_526;
    }
}

