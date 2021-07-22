/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.utils.AlignmentX;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_437;

public class WMeteorModule
extends WPressable
implements MeteorWidget {
    private final /* synthetic */ Module module;
    private /* synthetic */ double animationProgress1;
    private /* synthetic */ double animationProgress2;
    private /* synthetic */ double titleWidth;

    public WMeteorModule(Module llllllllllllllllIllIIlIIllllIlll) {
        WMeteorModule llllllllllllllllIllIIlIIlllllIII;
        llllllllllllllllIllIIlIIlllllIII.module = llllllllllllllllIllIIlIIllllIlll;
        if (llllllllllllllllIllIIlIIllllIlll.isActive()) {
            llllllllllllllllIllIIlIIlllllIII.animationProgress1 = 1.0;
            llllllllllllllllIllIIlIIlllllIII.animationProgress2 = 1.0;
        } else {
            llllllllllllllllIllIIlIIlllllIII.animationProgress1 = 0.0;
            llllllllllllllllIllIIlIIlllllIII.animationProgress2 = 0.0;
        }
    }

    @Override
    protected void onCalculateSize() {
        WMeteorModule llllllllllllllllIllIIlIIlllIllIl;
        double llllllllllllllllIllIIlIIlllIlllI = llllllllllllllllIllIIlIIlllIllIl.pad();
        if (llllllllllllllllIllIIlIIlllIllIl.titleWidth == 0.0) {
            llllllllllllllllIllIIlIIlllIllIl.titleWidth = llllllllllllllllIllIIlIIlllIllIl.theme.textWidth(llllllllllllllllIllIIlIIlllIllIl.module.title);
        }
        llllllllllllllllIllIIlIIlllIllIl.width = llllllllllllllllIllIIlIIlllIlllI + llllllllllllllllIllIIlIIlllIllIl.titleWidth + llllllllllllllllIllIIlIIlllIlllI;
        llllllllllllllllIllIIlIIlllIllIl.height = llllllllllllllllIllIIlIIlllIlllI + llllllllllllllllIllIIlIIlllIllIl.theme.textHeight() + llllllllllllllllIllIIlIIlllIlllI;
    }

    @Override
    public double pad() {
        WMeteorModule llllllllllllllllIllIIlIIllllIIll;
        return llllllllllllllllIllIIlIIllllIIll.theme.scale(4.0);
    }

    @Override
    protected void onPressed(int llllllllllllllllIllIIlIIlllIlIII) {
        WMeteorModule llllllllllllllllIllIIlIIlllIlIIl;
        if (llllllllllllllllIllIIlIIlllIlIII == 0) {
            llllllllllllllllIllIIlIIlllIlIIl.module.toggle(Utils.canUpdate());
        } else if (llllllllllllllllIllIIlIIlllIlIII == 1) {
            Utils.mc.method_1507((class_437)llllllllllllllllIllIIlIIlllIlIIl.theme.moduleScreen(llllllllllllllllIllIIlIIlllIlIIl.module));
        }
    }

    @Override
    protected void onRender(GuiRenderer llllllllllllllllIllIIlIIllIlIlII, double llllllllllllllllIllIIlIIllIlllII, double llllllllllllllllIllIIlIIllIllIll, double llllllllllllllllIllIIlIIllIlIIll) {
        WMeteorModule llllllllllllllllIllIIlIIllIlIlIl;
        MeteorGuiTheme llllllllllllllllIllIIlIIllIllIIl = llllllllllllllllIllIIlIIllIlIlIl.theme();
        double llllllllllllllllIllIIlIIllIllIII = llllllllllllllllIllIIlIIllIlIlIl.pad();
        llllllllllllllllIllIIlIIllIlIlIl.animationProgress1 += llllllllllllllllIllIIlIIllIlIIll * 4.0 * (double)(llllllllllllllllIllIIlIIllIlIlIl.module.isActive() || llllllllllllllllIllIIlIIllIlIlIl.mouseOver ? 1 : -1);
        llllllllllllllllIllIIlIIllIlIlIl.animationProgress1 = Utils.clamp(llllllllllllllllIllIIlIIllIlIlIl.animationProgress1, 0.0, 1.0);
        llllllllllllllllIllIIlIIllIlIlIl.animationProgress2 += llllllllllllllllIllIIlIIllIlIIll * 6.0 * (double)(llllllllllllllllIllIIlIIllIlIlIl.module.isActive() ? 1 : -1);
        llllllllllllllllIllIIlIIllIlIlIl.animationProgress2 = Utils.clamp(llllllllllllllllIllIIlIIllIlIlIl.animationProgress2, 0.0, 1.0);
        if (llllllllllllllllIllIIlIIllIlIlIl.animationProgress1 > 0.0) {
            llllllllllllllllIllIIlIIllIlIlII.quad(llllllllllllllllIllIIlIIllIlIlIl.x, llllllllllllllllIllIIlIIllIlIlIl.y, llllllllllllllllIllIIlIIllIlIlIl.width * llllllllllllllllIllIIlIIllIlIlIl.animationProgress1, llllllllllllllllIllIIlIIllIlIlIl.height, llllllllllllllllIllIIlIIllIllIIl.moduleBackground.get());
        }
        if (llllllllllllllllIllIIlIIllIlIlIl.animationProgress2 > 0.0) {
            llllllllllllllllIllIIlIIllIlIlII.quad(llllllllllllllllIllIIlIIllIlIlIl.x, llllllllllllllllIllIIlIIllIlIlIl.y + llllllllllllllllIllIIlIIllIlIlIl.height * (1.0 - llllllllllllllllIllIIlIIllIlIlIl.animationProgress2), llllllllllllllllIllIIlIIllIllIIl.scale(2.0), llllllllllllllllIllIIlIIllIlIlIl.height * llllllllllllllllIllIIlIIllIlIlIl.animationProgress2, llllllllllllllllIllIIlIIllIllIIl.accentColor.get());
        }
        double llllllllllllllllIllIIlIIllIlIlll = llllllllllllllllIllIIlIIllIlIlIl.x + llllllllllllllllIllIIlIIllIllIII;
        double llllllllllllllllIllIIlIIllIlIllI = llllllllllllllllIllIIlIIllIlIlIl.width - llllllllllllllllIllIIlIIllIllIII * 2.0;
        if (llllllllllllllllIllIIlIIllIllIIl.moduleAlignment.get() == AlignmentX.Center) {
            llllllllllllllllIllIIlIIllIlIlll += llllllllllllllllIllIIlIIllIlIllI / 2.0 - llllllllllllllllIllIIlIIllIlIlIl.titleWidth / 2.0;
        } else if (llllllllllllllllIllIIlIIllIllIIl.moduleAlignment.get() == AlignmentX.Right) {
            llllllllllllllllIllIIlIIllIlIlll += llllllllllllllllIllIIlIIllIlIllI - llllllllllllllllIllIIlIIllIlIlIl.titleWidth;
        }
        llllllllllllllllIllIIlIIllIlIlII.text(llllllllllllllllIllIIlIIllIlIlIl.module.title, llllllllllllllllIllIIlIIllIlIlll, llllllllllllllllIllIIlIIllIlIlIl.y + llllllllllllllllIllIIlIIllIllIII, llllllllllllllllIllIIlIIllIllIIl.textColor.get(), false);
    }
}

