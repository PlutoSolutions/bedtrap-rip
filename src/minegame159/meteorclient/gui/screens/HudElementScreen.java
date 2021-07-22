/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ HudElement element;
    private /* synthetic */ WContainer settings;

    public HudElementScreen(GuiTheme llllllllllllllllllIlIIIIIlllIIIl, HudElement llllllllllllllllllIlIIIIIlllIIII) {
        super(llllllllllllllllllIlIIIIIlllIIIl, llllllllllllllllllIlIIIIIlllIIII.title);
        HudElementScreen llllllllllllllllllIlIIIIIllllIII;
        llllllllllllllllllIlIIIIIllllIII.element = llllllllllllllllllIlIIIIIlllIIII;
        llllllllllllllllllIlIIIIIllllIII.add(llllllllllllllllllIlIIIIIlllIIIl.label(llllllllllllllllllIlIIIIIlllIIII.description, (double)Utils.getWindowWidth() / 2.0));
        if (llllllllllllllllllIlIIIIIlllIIII.settings.sizeGroups() > 0) {
            llllllllllllllllllIlIIIIIllllIII.settings = llllllllllllllllllIlIIIIIllllIII.add(llllllllllllllllllIlIIIIIlllIIIl.verticalList()).expandX().widget();
            llllllllllllllllllIlIIIIIllllIII.settings.add(llllllllllllllllllIlIIIIIlllIIIl.settings(llllllllllllllllllIlIIIIIlllIIII.settings)).expandX();
            llllllllllllllllllIlIIIIIllllIII.add(llllllllllllllllllIlIIIIIlllIIIl.horizontalSeparator()).expandX();
        }
        WHorizontalList llllllllllllllllllIlIIIIIlllIlIl = llllllllllllllllllIlIIIIIllllIII.add(llllllllllllllllllIlIIIIIlllIIIl.horizontalList()).expandX().widget();
        llllllllllllllllllIlIIIIIlllIlIl.add(llllllllllllllllllIlIIIIIlllIIIl.label("Active:"));
        WCheckbox llllllllllllllllllIlIIIIIlllIlII = llllllllllllllllllIlIIIIIlllIlIl.add(llllllllllllllllllIlIIIIIlllIIIl.checkbox(llllllllllllllllllIlIIIIIlllIIII.active)).widget();
        llllllllllllllllllIlIIIIIlllIlII.action = () -> {
            if (llllllllllllllllllIlIIIIIlIllIll.active != llllllllllllllllllIlIIIIIlIllIlI.checked) {
                llllllllllllllllllIlIIIIIlllIIII.toggle();
            }
        };
        WButton llllllllllllllllllIlIIIIIlllIIll = llllllllllllllllllIlIIIIIlllIlIl.add(llllllllllllllllllIlIIIIIlllIIIl.button(GuiRenderer.RESET)).expandCellX().right().widget();
        llllllllllllllllllIlIIIIIlllIIll.action = () -> {
            if (llllllllllllllllllIlIIIIIllIIIIl.active != llllllllllllllllllIlIIIIIllIIIIl.defaultActive) {
                llllllllllllllllllIlIIIIIllIIIIl.active = llllllllllllllllllIlIIIIIllIIIII.checked = llllllllllllllllllIlIIIIIllIIIIl.defaultActive;
            }
        };
    }

    @Override
    protected void onRenderBefore(float llllllllllllllllllIlIIIIIllIIlll) {
        Modules.get().get(HUD.class).onRender(Render2DEvent.get(0, 0, llllllllllllllllllIlIIIIIllIIlll));
    }

    public void method_25393() {
        HudElementScreen llllllllllllllllllIlIIIIIllIlIlI;
        super.method_25393();
        if (llllllllllllllllllIlIIIIIllIlIlI.settings == null) {
            return;
        }
        llllllllllllllllllIlIIIIIllIlIlI.element.settings.tick(llllllllllllllllllIlIIIIIllIlIlI.settings, llllllllllllllllllIlIIIIIllIlIlI.theme);
    }
}

