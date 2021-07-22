/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;

public class HudElementLayer {
    private final /* synthetic */ int yOffset;
    private final /* synthetic */ int xOffset;
    private final /* synthetic */ AlignmentY yAlign;
    private final /* synthetic */ List<HudElement> elements;
    private final /* synthetic */ AlignmentX xAlign;
    private final /* synthetic */ List<HudElement> allElements;
    private final /* synthetic */ HudRenderer renderer;

    public HudElementLayer(HudRenderer llIIlIIIIlIllll, List<HudElement> llIIlIIIIlIlllI, AlignmentX llIIlIIIIlIllIl, AlignmentY llIIlIIIIlIllII, int llIIlIIIIlIlIll, int llIIlIIIIlIlIlI) {
        HudElementLayer llIIlIIIIlIlIIl;
        llIIlIIIIlIlIIl.renderer = llIIlIIIIlIllll;
        llIIlIIIIlIlIIl.allElements = llIIlIIIIlIlllI;
        llIIlIIIIlIlIIl.elements = new ArrayList<HudElement>();
        llIIlIIIIlIlIIl.xAlign = llIIlIIIIlIllIl;
        llIIlIIIIlIlIIl.yAlign = llIIlIIIIlIllII;
        llIIlIIIIlIlIIl.xOffset = llIIlIIIIlIlIll;
        llIIlIIIIlIlIIl.yOffset = llIIlIIIIlIlIlI;
    }

    public void add(HudElement llIIlIIIIIlllIl) {
        HudElementLayer llIIlIIIIIllllI;
        llIIlIIIIIllllI.allElements.add(llIIlIIIIIlllIl);
        llIIlIIIIIllllI.elements.add(llIIlIIIIIlllIl);
        llIIlIIIIIlllIl.settings.registerColorSettings(null);
    }

    public void align() {
        HudElementLayer llIIlIIIIIlIllI;
        double llIIlIIIIIlIlIl = llIIlIIIIIlIllI.xOffset * (llIIlIIIIIlIllI.xAlign == AlignmentX.Right ? -1 : 1);
        double llIIlIIIIIlIlII = llIIlIIIIIlIllI.yOffset * (llIIlIIIIIlIllI.yAlign == AlignmentY.Bottom ? -1 : 1);
        for (HudElement llIIlIIIIIlIlll : llIIlIIIIIlIllI.elements) {
            llIIlIIIIIlIlll.update(llIIlIIIIIlIllI.renderer);
            llIIlIIIIIlIlll.box.x = llIIlIIIIIlIllI.xAlign;
            llIIlIIIIIlIlll.box.y = llIIlIIIIIlIllI.yAlign;
            llIIlIIIIIlIlll.box.xOffset = (int)Math.round(llIIlIIIIIlIlIl);
            llIIlIIIIIlIlll.box.yOffset = (int)Math.round(llIIlIIIIIlIlII);
            if (llIIlIIIIIlIllI.yAlign == AlignmentY.Bottom) {
                llIIlIIIIIlIlII -= llIIlIIIIIlIlll.box.height;
                continue;
            }
            llIIlIIIIIlIlII += llIIlIIIIIlIlll.box.height;
        }
    }
}

