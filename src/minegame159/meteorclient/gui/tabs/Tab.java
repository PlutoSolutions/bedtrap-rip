/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.tabs;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public abstract class Tab {
    public final /* synthetic */ String name;

    public Tab(String llllllllllllllllllIlIllIllIlIllI) {
        Tab llllllllllllllllllIlIllIllIlIlll;
        llllllllllllllllllIlIllIllIlIlll.name = llllllllllllllllllIlIllIllIlIllI;
    }

    public abstract boolean isScreen(class_437 var1);

    protected abstract TabScreen createScreen(GuiTheme var1);

    public void openScreen(GuiTheme llllllllllllllllllIlIllIllIlIIIl) {
        Tab llllllllllllllllllIlIllIllIIllll;
        TabScreen llllllllllllllllllIlIllIllIlIIII = llllllllllllllllllIlIllIllIIllll.createScreen(llllllllllllllllllIlIllIllIlIIIl);
        llllllllllllllllllIlIllIllIlIIII.addDirect(llllllllllllllllllIlIllIllIlIIIl.topBar()).top().centerX();
        Utils.mc.method_1507((class_437)llllllllllllllllllIlIllIllIlIIII);
    }
}

