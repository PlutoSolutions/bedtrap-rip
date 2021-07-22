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
    public static class_310 mc;
    private static final Random random;
    public static boolean isReleasingTrident;
    public static final Color WHITE;
    private static final DecimalFormat df;
    public static boolean firstTimeTitleScreen;

    public static int unpackLong2(long l) {
        return (int)(l >> 32 & 0xFFFFL);
    }

    public static boolean isThrowable(class_1792 class_17922) {
        return class_17922 instanceof class_1779 || class_17922 instanceof class_1753 || class_17922 instanceof class_1764 || class_17922 instanceof class_1823 || class_17922 instanceof class_1771 || class_17922 instanceof class_1776 || class_17922 instanceof class_1828 || class_17922 instanceof class_1803 || class_17922 instanceof class_1787 || class_17922 instanceof class_1835;
    }

    public static boolean isShulker(class_1792 class_17922) {
        return class_17922 == class_1802.field_8545 || class_17922 == class_1802.field_8722 || class_17922 == class_1802.field_8380 || class_17922 == class_1802.field_8050 || class_17922 == class_1802.field_8829 || class_17922 == class_1802.field_8271 || class_17922 == class_1802.field_8548 || class_17922 == class_1802.field_8520 || class_17922 == class_1802.field_8627 || class_17922 == class_1802.field_8451 || class_17922 == class_1802.field_8213 || class_17922 == class_1802.field_8816 || class_17922 == class_1802.field_8350 || class_17922 == class_1802.field_8584 || class_17922 == class_1802.field_8461 || class_17922 == class_1802.field_8676 || class_17922 == class_1802.field_8268;
    }

    public static int unpackLong4(long l) {
        return (int)(l & 0xFFFFL);
    }

    public static float clamp(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        if (f > f3) {
            return f3;
        }
        return f;
    }

    public static boolean canUpdate() {
        return mc != null && Utils.mc.field_1687 != null && Utils.mc.field_1724 != null;
    }

    public static class_243 vec3d(class_2338 class_23382) {
        return new class_243((double)class_23382.method_10263(), (double)class_23382.method_10264(), (double)class_23382.method_10260());
    }

    public static int search(String string, String string2) {
        String[] arrstring;
        int n = 0;
        for (String string3 : arrstring = string2.split(" ")) {
            if (!StringUtils.containsIgnoreCase((CharSequence)string, (CharSequence)string3)) continue;
            ++n;
            if (-1 < 2) continue;
            return 0;
        }
        return n;
    }

    public static int clamp(int n, int n2, int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
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

    public static double distance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d4 - d;
        double d8 = d5 - d2;
        double d9 = d6 - d3;
        return Math.sqrt(d7 * d7 + d8 * d8 + d9 * d9);
    }

    public static Object2IntMap<class_1291> createStatusEffectMap() {
        Object2IntArrayMap object2IntArrayMap = new Object2IntArrayMap(class_2378.field_11159.method_10235().size());
        class_2378.field_11159.forEach(arg_0 -> Utils.lambda$createStatusEffectMap$0((Object2IntMap)object2IntArrayMap, arg_0));
        return object2IntArrayMap;
    }

    public static double distanceToCamera(class_1297 class_12972) {
        return Utils.distanceToCamera(class_12972.method_23317(), class_12972.method_23318(), class_12972.method_23321());
    }

    public static void rightClick() {
        ((IMinecraftClient)mc).rightClick();
    }

    @SafeVarargs
    public static <T> Object2BooleanOpenHashMap<T> asObject2BooleanOpenHashMap(T ... arrT) {
        HashMap<T, Boolean> hashMap = new HashMap<T, Boolean>();
        for (T t : arrT) {
            hashMap.put(t, true);
            if (!false) continue;
            return null;
        }
        return new Object2BooleanOpenHashMap(hashMap);
    }

    public static byte[] readBytes(File file) {
        try {
            int n;
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] arrby = new byte[256];
            while ((n = ((InputStream)fileInputStream).read(arrby)) > 0) {
                byteArrayOutputStream.write(arrby, 0, n);
            }
            ((InputStream)fileInputStream).close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return new byte[0];
        }
    }

    public static String getKeyName(int n) {
        switch (n) {
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
        String string = GLFW.glfwGetKeyName((int)n, (int)0);
        if (string == null) {
            return "Unknown";
        }
        return StringUtils.capitalize((String)string);
    }

    public static String doubleToString(double d) {
        if (d % 1.0 == 0.0) {
            return Integer.toString((int)d);
        }
        return df.format(d);
    }

    public static String getEnchantSimpleName(class_1887 class_18872, int n) {
        return class_18872.method_8179(0).getString().substring(0, n);
    }

    public static double clamp(double d, double d2, double d3) {
        if (d < d2) {
            return d2;
        }
        if (d > d3) {
            return d3;
        }
        return d;
    }

    public static String getButtonName(int n) {
        switch (n) {
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
        return String.valueOf(new StringBuilder().append("Mouse ").append(n));
    }

    public static String floatToString(float f) {
        if (f % 1.0f == 0.0f) {
            return Integer.toString((int)f);
        }
        return Float.toString(f);
    }

    public static long packLong(int n, int n2, int n3, int n4) {
        return ((long)n << 48) + ((long)n2 << 32) + ((long)n3 << 16) + (long)n4;
    }

    public static double distanceToCamera(double d, double d2, double d3) {
        class_4184 class_41842 = Utils.mc.field_1773.method_19418();
        return Math.sqrt(class_41842.method_19326().method_1028(d, d2, d3));
    }

    public static double squaredDistance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d4 - d;
        double d8 = d5 - d2;
        double d9 = d6 - d3;
        return d7 * d7 + d8 * d8 + d9 * d9;
    }

    private static void lambda$createStatusEffectMap$0(Object2IntMap object2IntMap, class_1291 class_12912) {
        object2IntMap.put((Object)class_12912, 0);
    }

    public static String getWorldName() {
        if (mc.method_1542()) {
            File file = ((MinecraftServerAccessor)mc.method_1576()).getSession().method_27424(Utils.mc.field_1687.method_27983());
            if (file.toPath().relativize(Utils.mc.field_1697.toPath()).getNameCount() != 2) {
                file = file.getParentFile();
            }
            return file.getName();
        }
        if (mc.method_1558() != null) {
            String string;
            String string2 = string = mc.method_1589() ? "realms" : Utils.mc.method_1558().field_3761;
            if (SystemUtils.IS_OS_WINDOWS) {
                string = string.replace(":", "_");
            }
            return string;
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
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public static void addMeteorPvpToServerList() {
        class_641 class_6412 = new class_641(mc);
        class_6412.method_2981();
        boolean bl = false;
        for (int i = 0; i < class_6412.method_2984(); ++i) {
            class_642 class_6422 = class_6412.method_2982(i);
            if (!class_6422.field_3761.contains("pvp.meteorclient.com")) continue;
            bl = true;
            break;
        }
        if (!bl) {
            class_6412.method_2988(new class_642("Meteor Pvp", "pvp.meteorclient.com", false));
            class_6412.method_2987();
        }
    }

    public static double random(double d, double d2) {
        return d + (d2 - d) * random.nextDouble();
    }

    public static int getRenderDistance() {
        return Math.max(Utils.mc.field_1690.field_1870, ((ClientPlayNetworkHandlerAccessor)mc.method_1562()).getChunkLoadDistance());
    }

    public static String nameToTitle(String string) {
        return Arrays.stream(string.split("-")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static int unpackLong1(long l) {
        return (int)(l >> 48 & 0xFFFFL);
    }

    public static int unpackLong3(long l) {
        return (int)(l >> 16 & 0xFFFFL);
    }

    public static int getWindowWidth() {
        return mc.method_22683().method_4489();
    }

    public static int random(int n, int n2) {
        return random.nextInt(n2 - n) + n;
    }

    public static void sendMessage(String string, Object ... arrobject) {
        if (Utils.mc.field_1724 == null) {
            return;
        }
        string = String.format(string, arrobject);
        string = string.replaceAll("#yellow", class_124.field_1054.toString());
        string = string.replaceAll("#white", class_124.field_1068.toString());
        string = string.replaceAll("#red", class_124.field_1061.toString());
        string = string.replaceAll("#blue", class_124.field_1078.toString());
        string = string.replaceAll("#pink", class_124.field_1076.toString());
        string = string.replaceAll("#gray", class_124.field_1080.toString());
        Utils.mc.field_1724.method_7353((class_2561)new class_2585(string), false);
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

