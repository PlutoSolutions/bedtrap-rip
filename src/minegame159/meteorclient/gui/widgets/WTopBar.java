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
        this.spacing = 0.0;
    }

    @Override
    public void init() {
        for (Tab tab : Tabs.get()) {
            this.add(new WTopBarButton(this, tab));
        }
    }

    protected abstract Color getButtonColor(boolean var1, boolean var2);

    protected class WTopBarButton
    extends WPressable {
        private final Tab tab;
        final WTopBar this$0;

        @Override
        protected void onPressed(int n) {
            class_437 class_4372 = Utils.mc.field_1755;
            if (!(class_4372 instanceof TabScreen) || ((TabScreen)class_4372).tab != this.tab) {
                double d = Utils.mc.field_1729.method_1603();
                double d2 = Utils.mc.field_1729.method_1604();
                this.tab.openScreen(this.theme);
                GLFW.glfwSetCursorPos((long)Utils.mc.method_22683().method_4490(), (double)d, (double)d2);
            }
        }

        @Override
        protected void onCalculateSize() {
            double d = this.pad();
            this.width = d + this.theme.textWidth(this.tab.name) + d;
            this.height = d + this.theme.textHeight() + d;
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            double d4 = this.pad();
            Color color = this.this$0.getButtonColor(this.pressed || Utils.mc.field_1755 instanceof TabScreen && ((TabScreen)Utils.mc.field_1755).tab == this.tab, this.mouseOver);
            guiRenderer.quad(this.x, this.y, this.width, this.height, color);
            guiRenderer.text(this.tab.name, this.x + d4, this.y + d4, this.this$0.getNameColor(), false);
        }

        public WTopBarButton(WTopBar wTopBar, Tab tab) {
            this.this$0 = wTopBar;
            this.tab = tab;
        }
    }
}

