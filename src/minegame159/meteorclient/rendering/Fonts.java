/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.rendering;

import java.io.File;
import java.util.ArrayList;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.rendering.text.CustomTextRenderer;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.files.StreamUtils;

public class Fonts {
    public static final /* synthetic */ String DEFAULT_FONT;
    private static /* synthetic */ CustomTextRenderer a;
    private static /* synthetic */ String lastFont;
    private static final /* synthetic */ String[] BUILTIN_FONTS;
    private static final /* synthetic */ File FOLDER;

    public static void init() {
        FOLDER.mkdirs();
        for (String lIlIlIIIllIIIll : BUILTIN_FONTS) {
            File lIlIlIIIllIIlII = new File(FOLDER, lIlIlIIIllIIIll);
            if (lIlIlIIIllIIlII.exists()) continue;
            StreamUtils.copy(Fonts.class.getResourceAsStream(String.valueOf(new StringBuilder().append("/assets/meteor-client/fonts/").append(lIlIlIIIllIIIll))), lIlIlIIIllIIlII);
        }
        MeteorClient.FONT = new CustomTextRenderer(new File(FOLDER, "JetBrains Mono.ttf"));
        lastFont = "JetBrains Mono";
    }

    public Fonts() {
        Fonts lIlIlIIIllIlIll;
    }

    public static String[] getAvailableFonts() {
        ArrayList<String> lIlIlIIIlIIlllI = new ArrayList<String>(4);
        File[] lIlIlIIIlIIllIl = FOLDER.listFiles(File::isFile);
        if (lIlIlIIIlIIllIl != null) {
            for (File lIlIlIIIlIIllll : lIlIlIIIlIIllIl) {
                int lIlIlIIIlIlIIII = lIlIlIIIlIIllll.getName().lastIndexOf(46);
                if (!lIlIlIIIlIIllll.getName().substring(lIlIlIIIlIlIIII).equals(".ttf")) continue;
                lIlIlIIIlIIlllI.add(lIlIlIIIlIIllll.getName().substring(0, lIlIlIIIlIlIIII));
            }
        }
        return lIlIlIIIlIIlllI.toArray(new String[0]);
    }

    static {
        DEFAULT_FONT = "JetBrains Mono";
        BUILTIN_FONTS = new String[]{"JetBrains Mono.ttf", "Comfortaa.ttf", "Tw Cen MT.ttf", "Pixelation.ttf"};
        FOLDER = new File(MeteorClient.FOLDER, "fonts");
        lastFont = "";
    }

    public static void load() {
        if (lastFont.equals(Config.get().font)) {
            return;
        }
        File lIlIlIIIlIllIlI = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
        if (!lIlIlIIIlIllIlI.exists()) {
            Config.get().font = "JetBrains Mono";
            lIlIlIIIlIllIlI = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
        }
        try {
            MeteorClient.FONT = new CustomTextRenderer(lIlIlIIIlIllIlI);
        }
        catch (Exception lIlIlIIIlIllIll) {
            Config.get().font = "JetBrains Mono";
            lIlIlIIIlIllIlI = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
            MeteorClient.FONT = new CustomTextRenderer(lIlIlIIIlIllIlI);
        }
        if (Utils.mc.field_1755 instanceof WidgetScreen && Config.get().customFont) {
            ((WidgetScreen)Utils.mc.field_1755).invalidate();
        }
        lastFont = Config.get().font;
    }
}

