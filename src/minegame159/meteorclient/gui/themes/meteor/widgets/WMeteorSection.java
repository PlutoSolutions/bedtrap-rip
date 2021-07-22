/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;

public class WMeteorSection
extends WSection {
    @Override
    protected WSection.WHeader createHeader() {
        WMeteorSection lllllllllllllllllIllIlIIIIlllllI;
        return lllllllllllllllllIllIlIIIIlllllI.new WMeteorHeader(lllllllllllllllllIllIlIIIIlllllI.title);
    }

    public WMeteorSection(String lllllllllllllllllIllIlIIIlIIIIll, boolean lllllllllllllllllIllIlIIIlIIIllI, WWidget lllllllllllllllllIllIlIIIlIIIIIl) {
        super(lllllllllllllllllIllIlIIIlIIIIll, lllllllllllllllllIllIlIIIlIIIllI, lllllllllllllllllIllIlIIIlIIIIIl);
        WMeteorSection lllllllllllllllllIllIlIIIlIIlIII;
    }

    protected class WMeteorHeader
    extends WSection.WHeader {
        private /* synthetic */ WTriangle triangle;

        @Override
        public void init() {
            WMeteorHeader lllllllllllllllllllIlIllIllIlIIl;
            lllllllllllllllllllIlIllIllIlIIl.add(lllllllllllllllllllIlIllIllIlIIl.theme.horizontalSeparator(lllllllllllllllllllIlIllIllIlIIl.title)).expandX();
            if (lllllllllllllllllllIlIllIllIlIIl.WMeteorSection.this.headerWidget != null) {
                lllllllllllllllllllIlIllIllIlIIl.add(lllllllllllllllllllIlIllIllIlIIl.WMeteorSection.this.headerWidget);
            }
            lllllllllllllllllllIlIllIllIlIIl.triangle = new WHeaderTriangle();
            lllllllllllllllllllIlIllIllIlIIl.triangle.theme = lllllllllllllllllllIlIllIllIlIIl.theme;
            lllllllllllllllllllIlIllIllIlIIl.triangle.action = lllllllllllllllllllIlIllIllIlIIl::onClick;
            lllllllllllllllllllIlIllIllIlIIl.add(lllllllllllllllllllIlIllIllIlIIl.triangle);
        }

        public WMeteorHeader(String lllllllllllllllllllIlIllIllIllll) {
            WMeteorHeader lllllllllllllllllllIlIllIllIlllI;
            super(lllllllllllllllllllIlIllIllIlllI.WMeteorSection.this, lllllllllllllllllllIlIllIllIllll);
        }

        @Override
        protected void onRender(GuiRenderer lllllllllllllllllllIIIIllIIIlIll, double lllllllllllllllllllIIIIllIIIlIlI, double lllllllllllllllllllIIIIllIIIlIIl, double lllllllllllllllllllIIIIllIIIlIII) {
            WMeteorHeader lllllllllllllllllllIIIIllIIIIlll;
            lllllllllllllllllllIIIIllIIIIlll.triangle.rotation = (1.0 - lllllllllllllllllllIIIIllIIIIlll.WMeteorSection.this.animProgress) * -90.0;
        }
    }

    protected static class WHeaderTriangle
    extends WTriangle
    implements MeteorWidget {
        protected WHeaderTriangle() {
            WHeaderTriangle lllllllllllllllllIIIIllllIlIIllI;
        }

        @Override
        protected void onRender(GuiRenderer lllllllllllllllllIIIIllllIlIIIIl, double lllllllllllllllllIIIIllllIlIIIII, double lllllllllllllllllIIIIllllIIlllll, double lllllllllllllllllIIIIllllIIllllI) {
            WHeaderTriangle lllllllllllllllllIIIIllllIlIIIlI;
            lllllllllllllllllIIIIllllIlIIIIl.rotatedQuad(lllllllllllllllllIIIIllllIlIIIlI.x, lllllllllllllllllIIIIllllIlIIIlI.y, lllllllllllllllllIIIIllllIlIIIlI.width, lllllllllllllllllIIIIllllIlIIIlI.height, lllllllllllllllllIIIIllllIlIIIlI.rotation, GuiRenderer.TRIANGLE, lllllllllllllllllIIIIllllIlIIIlI.theme().textColor.get());
        }
    }
}

