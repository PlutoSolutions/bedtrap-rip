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
    public boolean isScreen(class_437 llllllllllllllllIllIIIIIIllIlIll) {
        return GuiThemes.get().isModulesScreen(llllllllllllllllIllIIIIIIllIlIll);
    }

    public ModulesTab() {
        super("Modules");
        ModulesTab llllllllllllllllIllIIIIIIlllIIll;
    }

    @Override
    public TabScreen createScreen(GuiTheme llllllllllllllllIllIIIIIIllIllll) {
        return llllllllllllllllIllIIIIIIllIllll.modulesScreen();
    }
}

