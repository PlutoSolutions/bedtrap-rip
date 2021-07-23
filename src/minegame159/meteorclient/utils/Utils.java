/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.mixin.ClientPlayNetworkHandlerAccessor;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.mixin.MinecraftServerAccessor;
import minegame159.meteorclient.mixininterface.IMinecraftClient;
import minegame159.meteorclient.systems.modules.render.BetterTooltips;
import minegame159.meteorclient.utils.player.EChestMemory;
import minegame159.meteorclient.utils.render.PeekScreen;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1291;
import net.minecraft.class_1747;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1767;
import net.minecraft.class_1771;
import net.minecraft.class_1772;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1787;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1803;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_1887;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_243;
import net.minecraft.class_2480;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_437;
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
    private static final DecimalFormat df;
    public static final Color WHITE;
    public static boolean firstTimeTitleScreen;
    private static final Random random;
    public static boolean isReleasingTrident;
    public static class_310 mc;

    private static void lambda$createStatusEffectMap$1(Object2IntMap object2IntMap, class_1291 class_12912) {
        object2IntMap.put((Object)class_12912, 0);
    }

    public static Color getShulkerColor(class_1799 class_17992) {
        if (!(class_17992.method_7909() instanceof class_1747)) {
            return WHITE;
        }
        class_2248 class_22482 = ((class_1747)class_17992.method_7909()).method_7711();
        if (class_22482 == class_2246.field_10443) {
            return BetterTooltips.ECHEST_COLOR;
        }
        if (!(class_22482 instanceof class_2480)) {
            return WHITE;
        }
        class_2480 class_24802 = (class_2480)class_2480.method_9503((class_1792)class_17992.method_7909());
        class_1767 class_17672 = class_24802.method_10528();
        if (class_17672 == null) {
            return WHITE;
        }
        float[] fArray = class_17672.method_7787();
        return new Color(fArray[0], fArray[1], fArray[2], 1.0f);
    }

    static {
        random = new Random();
        firstTimeTitleScreen = true;
        WHITE = new Color(255, 255, 255);
        df = new DecimalFormat("0");
        df.setMaximumFractionDigits(340);
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    @SafeVarargs
    public static <T> Object2BooleanOpenHashMap<T> asObject2BooleanOpenHashMap(T ... TArray) {
        HashMap<T, Boolean> hashMap = new HashMap<T, Boolean>();
        for (T t : TArray) {
            hashMap.put(t, true);
            if (-1 <= 0) continue;
            return null;
        }
        return new Object2BooleanOpenHashMap(hashMap);
    }

    public static boolean hasItems(class_1799 class_17992) {
        class_2487 class_24872 = class_17992.method_7941("BlockEntityTag");
        return class_24872 != null && class_24872.method_10573("Items", 9);
    }

    public static int getRenderDistance() {
        return Math.max(Utils.mc.field_1690.field_1870, ((ClientPlayNetworkHandlerAccessor)mc.method_1562()).getChunkLoadDistance());
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

    public static float clamp(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return Math.min(f, f3);
    }

    public static void unscaledProjection() {
        RenderSystem.matrixMode((int)5889);
        RenderSystem.loadIdentity();
        RenderSystem.ortho((double)0.0, (double)mc.method_22683().method_4489(), (double)mc.method_22683().method_4506(), (double)0.0, (double)1000.0, (double)3000.0);
        RenderSystem.matrixMode((int)5888);
        RenderSystem.loadIdentity();
        RenderSystem.translatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    public static void getItemsInContainerItem(class_1799 class_17992, class_1799[] class_1799Array) {
        class_2487 class_24872;
        if (class_17992.method_7909() == class_1802.field_8466) {
            for (int i = 0; i < EChestMemory.ITEMS.size(); ++i) {
                class_1799Array[i] = (class_1799)EChestMemory.ITEMS.get(i);
                if (-4 < 0) continue;
                return;
            }
            return;
        }
        Arrays.fill(class_1799Array, class_1799.field_8037);
        class_2487 class_24873 = class_17992.method_7969();
        if (class_24873 != null && class_24873.method_10545("BlockEntityTag") && (class_24872 = class_24873.method_10562("BlockEntityTag")).method_10545("Items")) {
            class_2499 class_24992 = (class_2499)class_24872.method_10580("Items");
            for (int i = 0; i < class_24992.size(); ++i) {
                class_1799Array[class_24992.method_10602((int)i).method_10571((String)"Slot")] = class_1799.method_7915((class_2487)class_24992.method_10602(i));
                if (1 > 0) continue;
                return;
            }
        }
    }

    public static double distance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d4 - d;
        double d8 = d5 - d2;
        double d9 = d6 - d3;
        return Math.sqrt(d7 * d7 + d8 * d8 + d9 * d9);
    }

    public static int clamp(int n, int n2, int n3) {
        if (n < n2) {
            return n2;
        }
        return Math.min(n, n3);
    }

    public static boolean isShulker(class_1792 class_17922) {
        return class_17922 == class_1802.field_8545 || class_17922 == class_1802.field_8722 || class_17922 == class_1802.field_8380 || class_17922 == class_1802.field_8050 || class_17922 == class_1802.field_8829 || class_17922 == class_1802.field_8271 || class_17922 == class_1802.field_8548 || class_17922 == class_1802.field_8520 || class_17922 == class_1802.field_8627 || class_17922 == class_1802.field_8451 || class_17922 == class_1802.field_8213 || class_17922 == class_1802.field_8816 || class_17922 == class_1802.field_8350 || class_17922 == class_1802.field_8584 || class_17922 == class_1802.field_8461 || class_17922 == class_1802.field_8676 || class_17922 == class_1802.field_8268;
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

    public static int getWindowWidth() {
        return mc.method_22683().method_4489();
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

    public static void rightClick() {
        ((IMinecraftClient)mc).rightClick();
    }

    public static Object2IntMap<class_1291> createStatusEffectMap() {
        Object2IntArrayMap object2IntArrayMap = new Object2IntArrayMap(class_2378.field_11159.method_10235().size());
        class_2378.field_11159.forEach(arg_0 -> Utils.lambda$createStatusEffectMap$1((Object2IntMap)object2IntArrayMap, arg_0));
        return object2IntArrayMap;
    }

    public static void leftClick() {
        Utils.mc.field_1690.field_1886.method_23481(true);
        ((MinecraftClientAccessor)mc).leftClick();
        Utils.mc.field_1690.field_1886.method_23481(false);
    }

    public static void getEnchantments(class_1799 class_17992, Object2IntMap<class_1887> object2IntMap) {
        object2IntMap.clear();
        if (!class_17992.method_7960()) {
            class_2499 class_24992 = class_17992.method_7909() == class_1802.field_8598 ? class_1772.method_7806((class_1799)class_17992) : class_17992.method_7921();
            for (int i = 0; i < class_24992.size(); ++i) {
                class_2487 class_24872 = class_24992.method_10602(i);
                class_2378.field_11160.method_17966(class_2960.method_12829((String)class_24872.method_10558("id"))).ifPresent(arg_0 -> Utils.lambda$getEnchantments$0(object2IntMap, class_24872, arg_0));
                if (true) continue;
                return;
            }
        }
    }

    public static byte[] readBytes(File file) {
        try {
            int n;
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] byArray = new byte[256];
            while ((n = ((InputStream)fileInputStream).read(byArray)) > 0) {
                byteArrayOutputStream.write(byArray, 0, n);
            }
            ((InputStream)fileInputStream).close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return new byte[0];
        }
    }

    public static void addEnchantment(class_1799 class_17992, class_1887 class_18872, int n) {
        class_2499 class_24992;
        class_2487 class_24872 = class_17992.method_7948();
        if (!class_24872.method_10573("Enchantments", 9)) {
            class_24992 = new class_2499();
            class_24872.method_10566("Enchantments", (class_2520)class_24992);
        } else {
            class_24992 = class_24872.method_10554("Enchantments", 10);
        }
        String string = class_2378.field_11160.method_10221((Object)class_18872).toString();
        for (class_2520 class_25202 : class_24992) {
            class_2487 class_24873 = (class_2487)class_25202;
            if (!class_24873.method_10558("id").equals(string)) continue;
            class_24873.method_10575("lvl", (short)n);
            return;
        }
        Iterator iterator = new class_2487();
        iterator.method_10582("id", string);
        iterator.method_10575("lvl", (short)n);
        class_24992.add((Object)iterator);
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

    public static double squaredDistance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d4 - d;
        double d8 = d5 - d2;
        double d9 = d6 - d3;
        return d7 * d7 + d8 * d8 + d9 * d9;
    }

    private static void lambda$getEnchantments$0(Object2IntMap object2IntMap, class_2487 class_24872, class_1887 class_18872) {
        object2IntMap.put((Object)class_18872, class_24872.method_10550("lvl"));
    }

    public static double clamp(double d, double d2, double d3) {
        if (d < d2) {
            return d2;
        }
        return Math.min(d, d3);
    }

    public static String nameToTitle(String string) {
        return Arrays.stream(string.split("-")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static int search(String string, String string2) {
        String[] stringArray;
        int n = 0;
        for (String string3 : stringArray = string2.split(" ")) {
            if (!StringUtils.containsIgnoreCase((CharSequence)string, (CharSequence)string3)) continue;
            ++n;
            if (2 > 1) continue;
            return 0;
        }
        return n;
    }

    public static class_243 vec3d(class_2338 class_23382) {
        return new class_243((double)class_23382.method_10263(), (double)class_23382.method_10264(), (double)class_23382.method_10260());
    }

    public static boolean openContainer(class_1799 class_17992, class_1799[] class_1799Array, boolean bl) {
        if (Utils.hasItems(class_17992) || class_17992.method_7909() == class_1802.field_8466) {
            Utils.getItemsInContainerItem(class_17992, class_1799Array);
            if (bl) {
                MeteorClient.INSTANCE.screenToOpen = new PeekScreen(class_17992, class_1799Array);
            } else {
                mc.method_1507((class_437)new PeekScreen(class_17992, class_1799Array));
            }
            return true;
        }
        return false;
    }

    public static int getWindowHeight() {
        return mc.method_22683().method_4506();
    }

    public static boolean isThrowable(class_1792 class_17922) {
        return class_17922 instanceof class_1779 || class_17922 instanceof class_1753 || class_17922 instanceof class_1764 || class_17922 instanceof class_1823 || class_17922 instanceof class_1771 || class_17922 instanceof class_1776 || class_17922 instanceof class_1828 || class_17922 instanceof class_1803 || class_17922 instanceof class_1787 || class_17922 instanceof class_1835;
    }

    public static double random(double d, double d2) {
        return d + (d2 - d) * random.nextDouble();
    }

    public static int random(int n, int n2) {
        return random.nextInt(n2 - n) + n;
    }

    public static String getEnchantSimpleName(class_1887 class_18872, int n) {
        return class_18872.method_8179(0).getString().substring(0, n);
    }

    public static void scaledProjection() {
        RenderSystem.matrixMode((int)5889);
        RenderSystem.loadIdentity();
        RenderSystem.ortho((double)0.0, (double)((double)mc.method_22683().method_4489() / mc.method_22683().method_4495()), (double)((double)mc.method_22683().method_4506() / mc.method_22683().method_4495()), (double)0.0, (double)1000.0, (double)3000.0);
        RenderSystem.matrixMode((int)5888);
        RenderSystem.loadIdentity();
        RenderSystem.translatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    public static boolean canUpdate() {
        return mc != null && Utils.mc.field_1687 != null && Utils.mc.field_1724 != null;
    }
}

