/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 *  org.lwjgl.glfw.GLFW
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.Tabs;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_437;
import org.lwjgl.glfw.GLFW;

public abstract class WTopBar
extends WHorizontalList {
    protected abstract Color getNameColor();

    public WTopBar() {
        WTopBar lllllllllllllllllIIIIllIlIlIllIl;
        lllllllllllllllllIIIIllIlIlIllIl.spacing = 0.0;
    }

    @Override
    public void init() {
        for (Tab lllllllllllllllllIIIIllIlIlIlIII : Tabs.get()) {
            WTopBar lllllllllllllllllIIIIllIlIlIIlll;
            lllllllllllllllllIIIIllIlIlIIlll.add(lllllllllllllllllIIIIllIlIlIIlll.new WTopBarButton(lllllllllllllllllIIIIllIlIlIlIII));
        }
    }

    protected abstract Color getButtonColor(boolean var1, boolean var2);

    protected class WTopBarButton
    extends WPressable {
        private final /* synthetic */ Tab tab;

        @Override
        protected void onPressed(int lllllllllllllllllllllllIlllllIII) {
            WTopBarButton lllllllllllllllllllllllIlllllIIl;
            class_437 lllllllllllllllllllllllIllllIlll = Utils.mc.field_1755;
            if (!(lllllllllllllllllllllllIllllIlll instanceof TabScreen) || ((TabScreen)lllllllllllllllllllllllIllllIlll).tab != lllllllllllllllllllllllIlllllIIl.tab) {
                double lllllllllllllllllllllllIlllllIll = Utils.mc.field_1729.method_1603();
                double lllllllllllllllllllllllIlllllIlI = Utils.mc.field_1729.method_1604();
                lllllllllllllllllllllllIlllllIIl.tab.openScreen(lllllllllllllllllllllllIlllllIIl.theme);
                GLFW.glfwSetCursorPos((long)Utils.mc.method_22683().method_4490(), (double)lllllllllllllllllllllllIlllllIll, (double)lllllllllllllllllllllllIlllllIlI);
            }
        }

        @Override
        protected void onCalculateSize() {
            WTopBarButton llllllllllllllllllllllllIIIIIIll;
            double llllllllllllllllllllllllIIIIIIlI = llllllllllllllllllllllllIIIIIIll.pad();
            llllllllllllllllllllllllIIIIIIll.width = llllllllllllllllllllllllIIIIIIlI + llllllllllllllllllllllllIIIIIIll.theme.textWidth(llllllllllllllllllllllllIIIIIIll.tab.name) + llllllllllllllllllllllllIIIIIIlI;
            llllllllllllllllllllllllIIIIIIll.height = llllllllllllllllllllllllIIIIIIlI + llllllllllllllllllllllllIIIIIIll.theme.textHeight() + llllllllllllllllllllllllIIIIIIlI;
        }

        @Override
        protected void onRender(GuiRenderer lllllllllllllllllllllllIlllIllIl, double lllllllllllllllllllllllIlllIllII, double lllllllllllllllllllllllIlllIlIll, double lllllllllllllllllllllllIlllIlIlI) {
            WTopBarButton lllllllllllllllllllllllIlllIIlll;
            double lllllllllllllllllllllllIlllIlIIl = lllllllllllllllllllllllIlllIIlll.pad();
            Color lllllllllllllllllllllllIlllIlIII = lllllllllllllllllllllllIlllIIlll.WTopBar.this.getButtonColor(lllllllllllllllllllllllIlllIIlll.pressed || Utils.mc.field_1755 instanceof TabScreen && ((TabScreen)Utils.mc.field_1755).tab == lllllllllllllllllllllllIlllIIlll.tab, lllllllllllllllllllllllIlllIIlll.mouseOver);
            lllllllllllllllllllllllIlllIllIl.quad(lllllllllllllllllllllllIlllIIlll.x, lllllllllllllllllllllllIlllIIlll.y, lllllllllllllllllllllllIlllIIlll.width, lllllllllllllllllllllllIlllIIlll.height, lllllllllllllllllllllllIlllIlIII);
            lllllllllllllllllllllllIlllIllIl.text(lllllllllllllllllllllllIlllIIlll.tab.name, lllllllllllllllllllllllIlllIIlll.x + lllllllllllllllllllllllIlllIlIIl, lllllllllllllllllllllllIlllIIlll.y + lllllllllllllllllllllllIlllIlIIl, lllllllllllllllllllllllIlllIIlll.WTopBar.this.getNameColor(), false);
        }

        public WTopBarButton(Tab llllllllllllllllllllllllIIIIlIIl) {
            WTopBarButton llllllllllllllllllllllllIIIIlIll;
            llllllllllllllllllllllllIIIIlIll.tab = llllllllllllllllllllllllIIIIlIIl;
        }
    }
}

