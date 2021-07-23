/*
 * Decompiled with CFR 0.151.
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
    public static final String DEFAULT_FONT = "JetBrains Mono";
    private static CustomTextRenderer a;
    private static String lastFont;
    private static final String[] BUILTIN_FONTS;
    private static final File FOLDER;

    public static void init() {
        FOLDER.mkdirs();
        for (String string : BUILTIN_FONTS) {
            File file = new File(FOLDER, string);
            if (file.exists()) continue;
            StreamUtils.copy(Fonts.class.getResourceAsStream(String.valueOf(new StringBuilder().append("/assets/meteor-client/fonts/").append(string))), file);
            if (!false) continue;
            return;
        }
        MeteorClient.FONT = new CustomTextRenderer(new File(FOLDER, "JetBrains Mono.ttf"));
        lastFont = "JetBrains Mono";
    }

    public static String[] getAvailableFonts() {
        ArrayList<String> arrayList = new ArrayList<String>(4);
        File[] fileArray = FOLDER.listFiles(File::isFile);
        if (fileArray != null) {
            for (File file : fileArray) {
                int n = file.getName().lastIndexOf(46);
                if (!file.getName().substring(n).equals(".ttf")) continue;
                arrayList.add(file.getName().substring(0, n));
                if (-5 < 0) continue;
                return null;
            }
        }
        return arrayList.toArray(new String[0]);
    }

    static {
        BUILTIN_FONTS = new String[]{"JetBrains Mono.ttf", "Comfortaa.ttf", "Tw Cen MT.ttf", "Pixelation.ttf"};
        FOLDER = new File(MeteorClient.FOLDER, "fonts");
        lastFont = "";
    }

    public static void load() {
        if (lastFont.equals(Config.get().font)) {
            return;
        }
        File file = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
        if (!file.exists()) {
            Config.get().font = "JetBrains Mono";
            file = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
        }
        try {
            MeteorClient.FONT = new CustomTextRenderer(file);
        }
        catch (Exception exception) {
            Config.get().font = "JetBrains Mono";
            file = new File(FOLDER, String.valueOf(new StringBuilder().append(Config.get().font).append(".ttf")));
            MeteorClient.FONT = new CustomTextRenderer(file);
        }
        if (Utils.mc.field_1755 instanceof WidgetScreen && Config.get().customFont) {
            ((WidgetScreen)Utils.mc.field_1755).invalidate();
        }
        lastFont = Config.get().font;
    }
}

