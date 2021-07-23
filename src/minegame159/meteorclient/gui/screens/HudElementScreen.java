/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.screens;

import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.containers.WContainer;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.Utils;

public class HudElementScreen
extends WindowScreen {
    private final HudElement element;
    private WContainer settings;

    public HudElementScreen(GuiTheme guiTheme, HudElement hudElement) {
        super(guiTheme, hudElement.title);
        this.element = hudElement;
        this.add(guiTheme.label(hudElement.description, (double)Utils.getWindowWidth() / 2.0));
        if (hudElement.settings.sizeGroups() > 0) {
            this.settings = this.add(guiTheme.verticalList()).expandX().widget();
            this.settings.add(guiTheme.settings(hudElement.settings)).expandX();
            this.add(guiTheme.horizontalSeparator()).expandX();
        }
        WHorizontalList wHorizontalList = this.add(guiTheme.horizontalList()).expandX().widget();
        wHorizontalList.add(guiTheme.label("Active:"));
        WCheckbox wCheckbox = wHorizontalList.add(guiTheme.checkbox(hudElement.active)).widget();
        wCheckbox.action = () -> HudElementScreen.lambda$new$0(hudElement, wCheckbox);
        WButton wButton = wHorizontalList.add(guiTheme.button(GuiRenderer.RESET)).expandCellX().right().widget();
        wButton.action = () -> HudElementScreen.lambda$new$1(hudElement, wCheckbox);
    }

    private static void lambda$new$0(HudElement hudElement, WCheckbox wCheckbox) {
        if (hudElement.active != wCheckbox.checked) {
            hudElement.toggle();
        }
    }

    @Override
    protected void onRenderBefore(float f) {
        Modules.get().get(HUD.class).onRender(Render2DEvent.get(0, 0, f));
    }

    private static void lambda$new$1(HudElement hudElement, WCheckbox wCheckbox) {
        if (hudElement.active != hudElement.defaultActive) {
            hudElement.active = wCheckbox.checked = hudElement.defaultActive;
        }
    }

    public void method_25393() {
        super.method_25393();
        if (this.settings == null) {
            return;
        }
        this.element.settings.tick(this.settings, this.theme);
    }
}

