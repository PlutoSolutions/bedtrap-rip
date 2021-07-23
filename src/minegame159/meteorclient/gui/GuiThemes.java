/*
 * Decompiled with CFR 0.151.
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
    private static final File FILE;
    private static final File FOLDER;
    private static GuiTheme theme;
    private static final List<GuiTheme> themes;
    private static final File THEMES_FOLDER;

    public static String[] getNames() {
        String[] stringArray = new String[themes.size()];
        for (int i = 0; i < themes.size(); ++i) {
            stringArray[i] = GuiThemes.themes.get((int)i).name;
            if (0 >= 0) continue;
            return null;
        }
        return stringArray;
    }

    static {
        FOLDER = new File(MeteorClient.FOLDER, "gui");
        THEMES_FOLDER = new File(FOLDER, "themes");
        FILE = new File(FOLDER, "gui.nbt");
        themes = new ArrayList<GuiTheme>();
    }

    public static void select(String string) {
        GuiTheme guiTheme = null;
        for (GuiTheme guiTheme2 : themes) {
            if (!guiTheme2.name.equals(string)) continue;
            guiTheme = guiTheme2;
            break;
        }
        if (guiTheme != null) {
            GuiThemes.saveTheme();
            theme = guiTheme;
            try {
                GuiTheme guiTheme2;
                File file = new File(THEMES_FOLDER, String.valueOf(new StringBuilder().append(GuiThemes.get().name).append(".nbt")));
                if (file.exists() && (guiTheme2 = class_2507.method_10633((File)file)) != null) {
                    GuiThemes.get().fromTag((class_2487)guiTheme2);
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            GuiThemes.saveGlobal();
        }
    }

    public static void add(GuiTheme guiTheme) {
        Iterator<GuiTheme> iterator = themes.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().name.equals(guiTheme.name)) continue;
            iterator.remove();
            MeteorClient.LOG.error("Theme with the name '{}' has already been added.", (Object)guiTheme.name);
            break;
        }
        themes.add(guiTheme);
    }

    public static void postInit() {
        if (FILE.exists()) {
            try {
                class_2487 class_24872 = class_2507.method_10633((File)FILE);
                if (class_24872 != null) {
                    GuiThemes.select(class_24872.method_10558("currentTheme"));
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        if (theme == null) {
            GuiThemes.select("Meteor");
        }
    }

    private static void saveGlobal() {
        try {
            class_2487 class_24872 = new class_2487();
            class_24872.method_10582("currentTheme", GuiThemes.get().name);
            FOLDER.mkdirs();
            class_2507.method_10630((class_2487)class_24872, (File)FILE);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void init() {
        GuiThemes.add(new MeteorGuiTheme());
    }

    private static void saveTheme() {
        if (GuiThemes.get() != null) {
            try {
                class_2487 class_24872 = GuiThemes.get().toTag();
                THEMES_FOLDER.mkdirs();
                class_2507.method_10630((class_2487)class_24872, (File)new File(THEMES_FOLDER, String.valueOf(new StringBuilder().append(GuiThemes.get().name).append(".nbt"))));
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    public static void save() {
        GuiThemes.saveTheme();
        GuiThemes.saveGlobal();
    }

    public static GuiTheme get() {
        return theme;
    }
}

