/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;

public class HudElementLayer {
    private final int yOffset;
    private final int xOffset;
    private final AlignmentY yAlign;
    private final List<HudElement> elements;
    private final AlignmentX xAlign;
    private final List<HudElement> allElements;
    private final HudRenderer renderer;

    public HudElementLayer(HudRenderer hudRenderer, List<HudElement> list, AlignmentX alignmentX, AlignmentY alignmentY, int n, int n2) {
        this.renderer = hudRenderer;
        this.allElements = list;
        this.elements = new ArrayList<HudElement>();
        this.xAlign = alignmentX;
        this.yAlign = alignmentY;
        this.xOffset = n;
        this.yOffset = n2;
    }

    public void add(HudElement hudElement) {
        this.allElements.add(hudElement);
        this.elements.add(hudElement);
        hudElement.settings.registerColorSettings(null);
    }

    public void align() {
        double d = this.xOffset * (this.xAlign == AlignmentX.Right ? -1 : 1);
        double d2 = this.yOffset * (this.yAlign == AlignmentY.Bottom ? -1 : 1);
        for (HudElement hudElement : this.elements) {
            hudElement.update(this.renderer);
            hudElement.box.x = this.xAlign;
            hudElement.box.y = this.yAlign;
            hudElement.box.xOffset = (int)Math.round(d);
            hudElement.box.yOffset = (int)Math.round(d2);
            if (this.yAlign == AlignmentY.Bottom) {
                d2 -= hudElement.box.height;
                continue;
            }
            d2 += hudElement.box.height;
        }
    }
}

