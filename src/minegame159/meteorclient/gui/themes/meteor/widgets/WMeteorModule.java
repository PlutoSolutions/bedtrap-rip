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
    private final Module module;
    private double animationProgress1;
    private double animationProgress2;
    private double titleWidth;

    public WMeteorModule(Module module) {
        this.module = module;
        if (module.isActive()) {
            this.animationProgress1 = 1.0;
            this.animationProgress2 = 1.0;
        } else {
            this.animationProgress1 = 0.0;
            this.animationProgress2 = 0.0;
        }
    }

    @Override
    protected void onCalculateSize() {
        double d = this.pad();
        if (this.titleWidth == 0.0) {
            this.titleWidth = this.theme.textWidth(this.module.title);
        }
        this.width = d + this.titleWidth + d;
        this.height = d + this.theme.textHeight() + d;
    }

    @Override
    public double pad() {
        return this.theme.scale(4.0);
    }

    @Override
    protected void onPressed(int n) {
        if (n == 0) {
            this.module.toggle(Utils.canUpdate());
        } else if (n == 1) {
            Utils.mc.method_1507((class_437)this.theme.moduleScreen(this.module));
        }
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d4 = this.pad();
        this.animationProgress1 += d3 * 4.0 * (double)(this.module.isActive() || this.mouseOver ? 1 : -1);
        this.animationProgress1 = Utils.clamp(this.animationProgress1, 0.0, 1.0);
        this.animationProgress2 += d3 * 6.0 * (double)(this.module.isActive() ? 1 : -1);
        this.animationProgress2 = Utils.clamp(this.animationProgress2, 0.0, 1.0);
        if (this.animationProgress1 > 0.0) {
            guiRenderer.quad(this.x, this.y, this.width * this.animationProgress1, this.height, meteorGuiTheme.moduleBackground.get());
        }
        if (this.animationProgress2 > 0.0) {
            guiRenderer.quad(this.x, this.y + this.height * (1.0 - this.animationProgress2), meteorGuiTheme.scale(2.0), this.height * this.animationProgress2, meteorGuiTheme.accentColor.get());
        }
        double d5 = this.x + d4;
        double d6 = this.width - d4 * 2.0;
        if (meteorGuiTheme.moduleAlignment.get() == AlignmentX.Center) {
            d5 += d6 / 2.0 - this.titleWidth / 2.0;
        } else if (meteorGuiTheme.moduleAlignment.get() == AlignmentX.Right) {
            d5 += d6 - this.titleWidth;
        }
        guiRenderer.text(this.module.title, d5, this.y + d4, meteorGuiTheme.textColor.get(), false);
    }
}

