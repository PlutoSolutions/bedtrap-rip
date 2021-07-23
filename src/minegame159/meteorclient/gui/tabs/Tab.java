/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public abstract class Tab {
    public final String name;

    public Tab(String string) {
        this.name = string;
    }

    public abstract boolean isScreen(class_437 var1);

    protected abstract TabScreen createScreen(GuiTheme var1);

    public void openScreen(GuiTheme guiTheme) {
        TabScreen tabScreen = this.createScreen(guiTheme);
        tabScreen.addDirect(guiTheme.topBar()).top().centerX();
        Utils.mc.method_1507((class_437)tabScreen);
    }
}

