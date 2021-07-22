/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_308
 *  net.minecraft.class_4493
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1799;
import net.minecraft.class_308;
import net.minecraft.class_4493;

public class WItem
extends WWidget {
    protected /* synthetic */ class_1799 itemStack;

    public void set(class_1799 lIIlIlIlllIIIl) {
        lIIlIlIlllIlII.itemStack = lIIlIlIlllIIIl;
    }

    @Override
    protected void onRender(GuiRenderer lIIlIlIlllllII, double lIIlIlIllllIll, double lIIlIlIllllIlI, double lIIlIlIllllIIl) {
        WItem lIIlIlIllllIII;
        lIIlIlIlllllII.post(() -> {
            WItem lIIlIlIllIllII;
            class_4493.method_21910();
            class_308.method_22890();
            class_4493.method_22050();
            double lIIlIlIllIllIl = lIIlIlIllIllII.theme.scale(2.0);
            class_4493.method_21926();
            class_4493.method_21937((double)lIIlIlIllIllIl, (double)lIIlIlIllIllIl, (double)1.0);
            class_4493.method_21938((double)(lIIlIlIllIllII.x / lIIlIlIllIllIl), (double)(lIIlIlIllIllII.y / lIIlIlIllIllIl), (double)0.0);
            Utils.mc.method_1480().method_4010(lIIlIlIllIllII.itemStack, 0, 0);
            class_4493.method_21928();
        });
    }

    @Override
    protected void onCalculateSize() {
        WItem lIIlIllIIIIIIl;
        double lIIlIllIIIIIlI;
        lIIlIllIIIIIIl.width = lIIlIllIIIIIlI = lIIlIllIIIIIIl.theme.scale(32.0);
        lIIlIllIIIIIIl.height = lIIlIllIIIIIlI;
    }

    public WItem(class_1799 lIIlIllIIIlIII) {
        WItem lIIlIllIIIIlll;
        lIIlIllIIIIlll.itemStack = lIIlIllIIIlIII;
    }
}

