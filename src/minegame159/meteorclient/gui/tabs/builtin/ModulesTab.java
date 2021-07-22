/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.tabs.builtin;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import net.minecraft.class_437;

public class ModulesTab
extends Tab {
    @Override
    public boolean isScreen(class_437 class_4372) {
        return GuiThemes.get().isModulesScreen(class_4372);
    }

    public ModulesTab() {
        super("Modules");
    }

    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return guiTheme.modulesScreen();
    }
}

