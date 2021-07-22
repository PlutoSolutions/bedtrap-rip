/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  it.unimi.dsi.fastutil.objects.Object2IntArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.class_1291
 *  net.minecraft.class_1747
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1767
 *  net.minecraft.class_1771
 *  net.minecraft.class_1772
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1787
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1803
 *  net.minecraft.class_1823
 *  net.minecraft.class_1828
 *  net.minecraft.class_1835
 *  net.minecraft.class_1887
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2378
 *  net.minecraft.class_243
 *  net.minecraft.class_2480
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_437
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
    private static final /* synthetic */ DecimalFormat df;
    public static final /* synthetic */ Color WHITE;
    public static /* synthetic */ boolean firstTimeTitleScreen;
    private static final /* synthetic */ Random random;
    public static /* synthetic */ boolean isReleasingTrident;
    public static /* synthetic */ class_310 mc;

    private static /* synthetic */ void lambda$createStatusEffectMap$1(Object2IntMap llIIIIIlIlll, class_1291 llIIIIIlIllI) {
        llIIIIIlIlll.put((Object)llIIIIIlIllI, 0);
    }

    public static Color getShulkerColor(class_1799 llIIllllIlll) {
        if (!(llIIllllIlll.method_7909() instanceof class_1747)) {
            return WHITE;
        }
        class_2248 llIIllllIllI = ((class_1747)llIIllllIlll.method_7909()).method_7711();
        if (llIIllllIllI == class_2246.field_10443) {
            return BetterTooltips.ECHEST_COLOR;
        }
        if (!(llIIllllIllI instanceof class_2480)) {
            return WHITE;
        }
        class_2480 llIIllllIlIl = (class_2480)class_2480.method_9503((class_1792)llIIllllIlll.method_7909());
        class_1767 llIIllllIlII = llIIllllIlIl.method_10528();
        if (llIIllllIlII == null) {
            return WHITE;
        }
        float[] llIIllllIIll = llIIllllIlII.method_7787();
        return new Color(llIIllllIIll[0], llIIllllIIll[1], llIIllllIIll[2], 1.0f);
    }

    static {
        random = new Random();
        firstTimeTitleScreen = true;
        WHITE = new Color(255, 255, 255);
        df = new DecimalFormat("0");
        df.setMaximumFractionDigits(340);
        DecimalFormatSymbols llIIIIIIlIll = new DecimalFormatSymbols();
        llIIIIIIlIll.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(llIIIIIIlIll);
    }

    @SafeVarargs
    public static <T> Object2BooleanOpenHashMap<T> asObject2BooleanOpenHashMap(T ... llIIIIlIIIIl) {
        HashMap<T, Boolean> llIIIIlIIIlI = new HashMap<T, Boolean>();
        for (T llIIIIlIIlII : llIIIIlIIIIl) {
            llIIIIlIIIlI.put(llIIIIlIIlII, true);
        }
        return new Object2BooleanOpenHashMap(llIIIIlIIIlI);
    }

    public static boolean hasItems(class_1799 llIIlllIlIll) {
        class_2487 llIIlllIlIlI = llIIlllIlIll.method_7941("BlockEntityTag");
        return llIIlllIlIlI != null && llIIlllIlIlI.method_10573("Items", 9);
    }

    public static int getRenderDistance() {
        return Math.max(Utils.mc.field_1690.field_1870, ((ClientPlayNetworkHandlerAccessor)mc.method_1562()).getChunkLoadDistance());
    }

    public Utils() {
        Utils llIlIIllIlll;
    }

    public static String getWorldName() {
        if (mc.method_1542()) {
            File llIIlIIlIIlI = ((MinecraftServerAccessor)mc.method_1576()).getSession().method_27424(Utils.mc.field_1687.method_27983());
            if (llIIlIIlIIlI.toPath().relativize(Utils.mc.field_1697.toPath()).getNameCount() != 2) {
                llIIlIIlIIlI = llIIlIIlIIlI.getParentFile();
            }
            return llIIlIIlIIlI.getName();
        }
        if (mc.method_1558() != null) {
            String llIIlIIlIIIl;
            String string = llIIlIIlIIIl = mc.method_1589() ? "realms" : Utils.mc.method_1558().field_3761;
            if (SystemUtils.IS_OS_WINDOWS) {
                llIIlIIlIIIl = llIIlIIlIIIl.replace(":", "_");
            }
            return llIIlIIlIIIl;
        }
        return "";
    }

    public static float clamp(float llIIIlIlIlIl, float llIIIlIlIIIl, float llIIIlIlIIII) {
        if (llIIIlIlIlIl < llIIIlIlIIIl) {
            return llIIIlIlIIIl;
        }
        return Math.min(llIIIlIlIlIl, llIIIlIlIIII);
    }

    public static void unscaledProjection() {
        RenderSystem.matrixMode((int)5889);
        RenderSystem.loadIdentity();
        RenderSystem.ortho((double)0.0, (double)mc.method_22683().method_4489(), (double)mc.method_22683().method_4506(), (double)0.0, (double)1000.0, (double)3000.0);
        RenderSystem.matrixMode((int)5888);
        RenderSystem.loadIdentity();
        RenderSystem.translatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    public static void getItemsInContainerItem(class_1799 llIlIIIIIlIl, class_1799[] llIlIIIIIlII) {
        class_2487 llIlIIIIIllI;
        if (llIlIIIIIlIl.method_7909() == class_1802.field_8466) {
            for (int llIlIIIIlIIl = 0; llIlIIIIlIIl < EChestMemory.ITEMS.size(); ++llIlIIIIlIIl) {
                llIlIIIIIlII[llIlIIIIlIIl] = (class_1799)EChestMemory.ITEMS.get(llIlIIIIlIIl);
            }
            return;
        }
        Arrays.fill((Object[])llIlIIIIIlII, (Object)class_1799.field_8037);
        class_2487 llIlIIIIIIll = llIlIIIIIlIl.method_7969();
        if (llIlIIIIIIll != null && llIlIIIIIIll.method_10545("BlockEntityTag") && (llIlIIIIIllI = llIlIIIIIIll.method_10562("BlockEntityTag")).method_10545("Items")) {
            class_2499 llIlIIIIIlll = (class_2499)llIlIIIIIllI.method_10580("Items");
            for (int llIlIIIIlIII = 0; llIlIIIIlIII < llIlIIIIIlll.size(); ++llIlIIIIlIII) {
                llIlIIIIIlII[llIlIIIIIlll.method_10602((int)llIlIIIIlIII).method_10571((String)"Slot")] = class_1799.method_7915((class_2487)llIlIIIIIlll.method_10602(llIlIIIIlIII));
            }
        }
    }

    public static double distance(double llIIlIIlllII, double llIIlIIllIlI, double llIIlIIllIIl, double llIIlIIllIII, double llIIlIlIIIIl, double llIIlIIlIllI) {
        double llIIlIIlllll = llIIlIIllIII - llIIlIIlllII;
        double llIIlIIllllI = llIIlIlIIIIl - llIIlIIllIlI;
        double llIIlIIlllIl = llIIlIIlIllI - llIIlIIllIIl;
        return Math.sqrt(llIIlIIlllll * llIIlIIlllll + llIIlIIllllI * llIIlIIllllI + llIIlIIlllIl * llIIlIIlllIl);
    }

    public static int clamp(int llIIIlIllIll, int llIIIlIllIlI, int llIIIlIllIIl) {
        if (llIIIlIllIll < llIIIlIllIlI) {
            return llIIIlIllIlI;
        }
        return Math.min(llIIIlIllIll, llIIIlIllIIl);
    }

    public static boolean isShulker(class_1792 llIIIllIIlIl) {
        return llIIIllIIlIl == class_1802.field_8545 || llIIIllIIlIl == class_1802.field_8722 || llIIIllIIlIl == class_1802.field_8380 || llIIIllIIlIl == class_1802.field_8050 || llIIIllIIlIl == class_1802.field_8829 || llIIIllIIlIl == class_1802.field_8271 || llIIIllIIlIl == class_1802.field_8548 || llIIIllIIlIl == class_1802.field_8520 || llIIIllIIlIl == class_1802.field_8627 || llIIIllIIlIl == class_1802.field_8451 || llIIIllIIlIl == class_1802.field_8213 || llIIIllIIlIl == class_1802.field_8816 || llIIIllIIlIl == class_1802.field_8350 || llIIIllIIlIl == class_1802.field_8584 || llIIIllIIlIl == class_1802.field_8461 || llIIIllIIlIl == class_1802.field_8676 || llIIIllIIlIl == class_1802.field_8268;
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

    public static String getButtonName(int llIIlIIIIlIl) {
        switch (llIIlIIIIlIl) {
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
        return String.valueOf(new StringBuilder().append("Mouse ").append(llIIlIIIIlIl));
    }

    public static int getWindowWidth() {
        return mc.method_22683().method_4489();
    }

    public static String getKeyName(int llIIlIIIlIIl) {
        switch (llIIlIIIlIIl) {
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
        String llIIlIIIlIlI = GLFW.glfwGetKeyName((int)llIIlIIIlIIl, (int)0);
        if (llIIlIIIlIlI == null) {
            return "Unknown";
        }
        return StringUtils.capitalize((String)llIIlIIIlIlI);
    }

    public static void rightClick() {
        ((IMinecraftClient)mc).rightClick();
    }

    public static Object2IntMap<class_1291> createStatusEffectMap() {
        Object2IntArrayMap llIIlllIIllI = new Object2IntArrayMap(class_2378.field_11159.method_10235().size());
        class_2378.field_11159.forEach(arg_0 -> Utils.lambda$createStatusEffectMap$1((Object2IntMap)llIIlllIIllI, arg_0));
        return llIIlllIIllI;
    }

    public static void leftClick() {
        Utils.mc.field_1690.field_1886.method_23481(true);
        ((MinecraftClientAccessor)mc).leftClick();
        Utils.mc.field_1690.field_1886.method_23481(false);
    }

    public static void getEnchantments(class_1799 llIlIIlIllII, Object2IntMap<class_1887> llIlIIlIlIll) {
        llIlIIlIlIll.clear();
        if (!llIlIIlIllII.method_7960()) {
            class_2499 llIlIIlIllll = llIlIIlIllII.method_7909() == class_1802.field_8598 ? class_1772.method_7806((class_1799)llIlIIlIllII) : llIlIIlIllII.method_7921();
            for (int llIlIIllIIII = 0; llIlIIllIIII < llIlIIlIllll.size(); ++llIlIIllIIII) {
                class_2487 llIlIIllIIIl = llIlIIlIllll.method_10602(llIlIIllIIII);
                class_2378.field_11160.method_17966(class_2960.method_12829((String)llIlIIllIIIl.method_10558("id"))).ifPresent(llIIIIIlIIII -> llIlIIlIlIll.put(llIIIIIlIIII, llIlIIllIIIl.method_10550("lvl")));
            }
        }
    }

    public static byte[] readBytes(File llIIIllllIIl) {
        try {
            int llIIIllllIll;
            FileInputStream llIIIllllllI = new FileInputStream(llIIIllllIIl);
            ByteArrayOutputStream llIIIlllllIl = new ByteArrayOutputStream();
            byte[] llIIIlllllII = new byte[256];
            while ((llIIIllllIll = ((InputStream)llIIIllllllI).read(llIIIlllllII)) > 0) {
                llIIIlllllIl.write(llIIIlllllII, 0, llIIIllllIll);
            }
            ((InputStream)llIIIllllllI).close();
            return llIIIlllllIl.toByteArray();
        }
        catch (IOException llIIIllllIlI) {
            llIIIllllIlI.printStackTrace();
            return new byte[0];
        }
    }

    public static void addEnchantment(class_1799 llIIIIllIIll, class_1887 llIIIIllIIlI, int llIIIIlllIII) {
        class_2499 llIIIIllIllI;
        class_2487 llIIIIllIlll = llIIIIllIIll.method_7948();
        if (!llIIIIllIlll.method_10573("Enchantments", 9)) {
            class_2499 llIIIIllllIl = new class_2499();
            llIIIIllIlll.method_10566("Enchantments", (class_2520)llIIIIllllIl);
        } else {
            llIIIIllIllI = llIIIIllIlll.method_10554("Enchantments", 10);
        }
        String llIIIIllIlIl = class_2378.field_11160.method_10221((Object)llIIIIllIIlI).toString();
        for (class_2520 llIIIIlllIll : llIIIIllIllI) {
            class_2487 llIIIIllllII = (class_2487)llIIIIlllIll;
            if (!llIIIIllllII.method_10558("id").equals(llIIIIllIlIl)) continue;
            llIIIIllllII.method_10575("lvl", (short)llIIIIlllIII);
            return;
        }
        class_2487 llIIIIllIlII = new class_2487();
        llIIIIllIlII.method_10582("id", llIIIIllIlIl);
        llIIIIllIlII.method_10575("lvl", (short)llIIIIlllIII);
        llIIIIllIllI.add((Object)llIIIIllIlII);
    }

    public static void addMeteorPvpToServerList() {
        class_641 llIlIIlIIIIl = new class_641(mc);
        llIlIIlIIIIl.method_2981();
        boolean llIlIIlIIIII = false;
        for (int llIlIIlIIIlI = 0; llIlIIlIIIlI < llIlIIlIIIIl.method_2984(); ++llIlIIlIIIlI) {
            class_642 llIlIIlIIIll = llIlIIlIIIIl.method_2982(llIlIIlIIIlI);
            if (!llIlIIlIIIll.field_3761.contains("pvp.meteorclient.com")) continue;
            llIlIIlIIIII = true;
            break;
        }
        if (!llIlIIlIIIII) {
            llIlIIlIIIIl.method_2988(new class_642("Meteor Pvp", "pvp.meteorclient.com", false));
            llIlIIlIIIIl.method_2987();
        }
    }

    public static double squaredDistance(double llIIllIIIIII, double llIIlIllllll, double llIIlIllIlII, double llIIlIllIIll, double llIIlIllIIlI, double llIIlIlllIll) {
        double llIIlIlllIlI = llIIlIllIIll - llIIllIIIIII;
        double llIIlIlllIIl = llIIlIllIIlI - llIIlIllllll;
        double llIIlIlllIII = llIIlIlllIll - llIIlIllIlII;
        return llIIlIlllIlI * llIIlIlllIlI + llIIlIlllIIl * llIIlIlllIIl + llIIlIlllIII * llIIlIlllIII;
    }

    public static double clamp(double llIIIlIIlIIl, double llIIIlIIlIII, double llIIIlIIlIlI) {
        if (llIIIlIIlIIl < llIIIlIIlIII) {
            return llIIIlIIlIII;
        }
        return Math.min(llIIIlIIlIIl, llIIIlIIlIlI);
    }

    public static String nameToTitle(String llIIlIIIllIl) {
        return Arrays.stream(llIIlIIIllIl.split("-")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static int search(String llIIllIlIlIl, String llIIllIlIlII) {
        String[] llIIllIlIIlI;
        int llIIllIlIIll = 0;
        for (String llIIllIlIllI : llIIllIlIIlI = llIIllIlIlII.split(" ")) {
            if (!StringUtils.containsIgnoreCase((CharSequence)llIIllIlIlIl, (CharSequence)llIIllIlIllI)) continue;
            ++llIIllIlIIll;
        }
        return llIIllIlIIll;
    }

    public static class_243 vec3d(class_2338 llIlIIIllIlI) {
        return new class_243((double)llIlIIIllIlI.method_10263(), (double)llIlIIIllIlI.method_10264(), (double)llIlIIIllIlI.method_10260());
    }

    public static boolean openContainer(class_1799 llIlIIIlIIlI, class_1799[] llIlIIIlIIIl, boolean llIlIIIlIIll) {
        if (Utils.hasItems(llIlIIIlIIlI) || llIlIIIlIIlI.method_7909() == class_1802.field_8466) {
            Utils.getItemsInContainerItem(llIlIIIlIIlI, llIlIIIlIIIl);
            if (llIlIIIlIIll) {
                MeteorClient.INSTANCE.screenToOpen = new PeekScreen(llIlIIIlIIlI, llIlIIIlIIIl);
            } else {
                mc.method_1507((class_437)new PeekScreen(llIlIIIlIIlI, llIlIIIlIIIl));
            }
            return true;
        }
        return false;
    }

    public static int getWindowHeight() {
        return mc.method_22683().method_4506();
    }

    public static boolean isThrowable(class_1792 llIIIllIIIlI) {
        return llIIIllIIIlI instanceof class_1779 || llIIIllIIIlI instanceof class_1753 || llIIIllIIIlI instanceof class_1764 || llIIIllIIIlI instanceof class_1823 || llIIIllIIIlI instanceof class_1771 || llIIIllIIIlI instanceof class_1776 || llIIIllIIIlI instanceof class_1828 || llIIIllIIIlI instanceof class_1803 || llIIIllIIIlI instanceof class_1787 || llIIIllIIIlI instanceof class_1835;
    }

    public static double random(double llIIIllIlIll, double llIIIllIlIII) {
        return llIIIllIlIll + (llIIIllIlIII - llIIIllIlIll) * random.nextDouble();
    }

    public static int random(int llIIIlllIIIl, int llIIIlllIIII) {
        return random.nextInt(llIIIlllIIII - llIIIlllIIIl) + llIIIlllIIIl;
    }

    public static String getEnchantSimpleName(class_1887 llIIlllIIIlI, int llIIlllIIIIl) {
        return llIIlllIIIlI.method_8179(0).getString().substring(0, llIIlllIIIIl);
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

