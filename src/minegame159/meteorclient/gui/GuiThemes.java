/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2507
 */
package minegame159.meteorclient.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import net.minecraft.class_2487;
import net.minecraft.class_2507;

public class GuiThemes {
    private static final /* synthetic */ File FILE;
    private static final /* synthetic */ File FOLDER;
    private static /* synthetic */ GuiTheme theme;
    private static final /* synthetic */ List<GuiTheme> themes;
    private static final /* synthetic */ File THEMES_FOLDER;

    public static String[] getNames() {
        String[] lIIIIIIllIlII = new String[themes.size()];
        for (int lIIIIIIllIlIl = 0; lIIIIIIllIlIl < themes.size(); ++lIIIIIIllIlIl) {
            lIIIIIIllIlII[lIIIIIIllIlIl] = GuiThemes.themes.get((int)lIIIIIIllIlIl).name;
        }
        return lIIIIIIllIlII;
    }

    static {
        FOLDER = new File(MeteorClient.FOLDER, "gui");
        THEMES_FOLDER = new File(FOLDER, "themes");
        FILE = new File(FOLDER, "gui.nbt");
        themes = new ArrayList<GuiTheme>();
    }

    public static void select(String lIIIIIIllllIl) {
        GuiTheme lIIIIIIllllII = null;
        for (GuiTheme lIIIIIlIIIIIl : themes) {
            if (!lIIIIIlIIIIIl.name.equals(lIIIIIIllllIl)) continue;
            lIIIIIIllllII = lIIIIIlIIIIIl;
            break;
        }
        if (lIIIIIIllllII != null) {
            GuiThemes.saveTheme();
            theme = lIIIIIIllllII;
            try {
                class_2487 lIIIIIlIIIIII;
                File lIIIIIIllllll = new File(THEMES_FOLDER, String.valueOf(new StringBuilder().append(GuiThemes.get().name).append(".nbt")));
                if (lIIIIIIllllll.exists() && (lIIIIIlIIIIII = class_2507.method_10633((File)lIIIIIIllllll)) != null) {
                    GuiThemes.get().fromTag(lIIIIIlIIIIII);
                }
            }
            catch (IOException lIIIIIIlllllI) {
                lIIIIIIlllllI.printStackTrace();
            }
            GuiThemes.saveGlobal();
        }
    }

    public static void add(GuiTheme lIIIIIlIIIlll) {
        Iterator<GuiTheme> lIIIIIlIIlIIl = themes.iterator();
        while (lIIIIIlIIlIIl.hasNext()) {
            if (!lIIIIIlIIlIIl.next().name.equals(lIIIIIlIIIlll.name)) continue;
            lIIIIIlIIlIIl.remove();
            MeteorClient.LOG.error("Theme with the name '{}' has already been added.", (Object)lIIIIIlIIIlll.name);
            break;
        }
        themes.add(lIIIIIlIIIlll);
    }

    public static void postInit() {
        if (FILE.exists()) {
            try {
                class_2487 lIIIIIlIIlllI = class_2507.method_10633((File)FILE);
                if (lIIIIIlIIlllI != null) {
                    GuiThemes.select(lIIIIIlIIlllI.method_10558("currentTheme"));
                }
            }
            catch (IOException lIIIIIlIIllIl) {
                lIIIIIlIIllIl.printStackTrace();
            }
        }
        if (theme == null) {
            GuiThemes.select("Meteor");
        }
    }

    private static void saveGlobal() {
        try {
            class_2487 lIIIIIIlIllII = new class_2487();
            lIIIIIIlIllII.method_10582("currentTheme", GuiThemes.get().name);
            FOLDER.mkdirs();
            class_2507.method_10630((class_2487)lIIIIIIlIllII, (File)FILE);
        }
        catch (IOException lIIIIIIlIlIll) {
            lIIIIIIlIlIll.printStackTrace();
        }
    }

    public static void init() {
        GuiThemes.add(new MeteorGuiTheme());
    }

    private static void saveTheme() {
        if (GuiThemes.get() != null) {
            try {
                class_2487 lIIIIIIllIIII = GuiThemes.get().toTag();
                THEMES_FOLDER.mkdirs();
                class_2507.method_10630((class_2487)lIIIIIIllIIII, (File)new File(THEMES_FOLDER, String.valueOf(new StringBuilder().append(GuiThemes.get().name).append(".nbt"))));
            }
            catch (IOException lIIIIIIlIllll) {
                lIIIIIIlIllll.printStackTrace();
            }
        }
    }

    public static void save() {
        GuiThemes.saveTheme();
        GuiThemes.saveGlobal();
    }

    public GuiThemes() {
        GuiThemes lIIIIIlIlIIII;
    }

    public static GuiTheme get() {
        return theme;
    }
}

