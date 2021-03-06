/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.builtin.BaritoneTab;
import minegame159.meteorclient.gui.tabs.builtin.ConfigTab;
import minegame159.meteorclient.gui.tabs.builtin.FriendsTab;
import minegame159.meteorclient.gui.tabs.builtin.GuiTab;
import minegame159.meteorclient.gui.tabs.builtin.HudTab;
import minegame159.meteorclient.gui.tabs.builtin.MacrosTab;
import minegame159.meteorclient.gui.tabs.builtin.ModulesTab;
import minegame159.meteorclient.gui.tabs.builtin.ProfilesTab;

public class Tabs {
    private static final List<Tab> tabs = new ArrayList<Tab>();

    public static void init() {
        Tabs.add(new ModulesTab());
        Tabs.add(new ConfigTab());
        Tabs.add(new GuiTab());
        Tabs.add(new HudTab());
        Tabs.add(new FriendsTab());
        Tabs.add(new MacrosTab());
        Tabs.add(new ProfilesTab());
        Tabs.add(new BaritoneTab());
    }

    public static List<Tab> get() {
        return tabs;
    }

    public static void add(Tab tab) {
        tabs.add(tab);
    }
}

