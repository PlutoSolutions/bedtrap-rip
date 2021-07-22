/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.player.PlayerUtils;

public class PositionHud
extends HudElement {
    private /* synthetic */ double left2Width;
    private /* synthetic */ double left1Width;
    private /* synthetic */ String right1;
    private final /* synthetic */ String left1 = "Pos: ";
    private /* synthetic */ String left2;
    private /* synthetic */ String right2;

    @Override
    public void update(HudRenderer llllllllllllllllIllIlllIIlllIIll) {
        PositionHud llllllllllllllllIllIlllIIlllIlII;
        llllllllllllllllIllIlllIIlllIlII.left1Width = llllllllllllllllIllIlllIIlllIIll.textWidth("Pos: ");
        llllllllllllllllIllIlllIIlllIlII.left2 = null;
        if (llllllllllllllllIllIlllIIlllIlII.isInEditor()) {
            llllllllllllllllIllIlllIIlllIlII.right1 = "0,0 0,0 0,0";
            llllllllllllllllIllIlllIIlllIlII.box.setSize(llllllllllllllllIllIlllIIlllIlII.left1Width + llllllllllllllllIllIlllIIlllIIll.textWidth(llllllllllllllllIllIlllIIlllIlII.right1), llllllllllllllllIllIlllIIlllIIll.textHeight() * 2.0 + 2.0);
            return;
        }
        Freecam llllllllllllllllIllIlllIIlllIIlI = Modules.get().get(Freecam.class);
        double llllllllllllllllIllIlllIIlllIIIl = llllllllllllllllIllIlllIIlllIIlI.isActive() ? llllllllllllllllIllIlllIIlllIlII.mc.field_1773.method_19418().method_19326().field_1352 : llllllllllllllllIllIlllIIlllIlII.mc.field_1724.method_23317();
        double llllllllllllllllIllIlllIIlllIIII = llllllllllllllllIllIlllIIlllIIlI.isActive() ? llllllllllllllllIllIlllIIlllIlII.mc.field_1773.method_19418().method_19326().field_1351 - (double)llllllllllllllllIllIlllIIlllIlII.mc.field_1724.method_18381(llllllllllllllllIllIlllIIlllIlII.mc.field_1724.method_18376()) : llllllllllllllllIllIlllIIlllIlII.mc.field_1724.method_23318();
        double llllllllllllllllIllIlllIIllIllll = llllllllllllllllIllIlllIIlllIIlI.isActive() ? llllllllllllllllIllIlllIIlllIlII.mc.field_1773.method_19418().method_19326().field_1350 : llllllllllllllllIllIlllIIlllIlII.mc.field_1724.method_23321();
        llllllllllllllllIllIlllIIlllIlII.right1 = String.format("%.1f %.1f %.1f", llllllllllllllllIllIlllIIlllIIIl, llllllllllllllllIllIlllIIlllIIII, llllllllllllllllIllIlllIIllIllll);
        switch (PlayerUtils.getDimension()) {
            case Overworld: {
                llllllllllllllllIllIlllIIlllIlII.left2 = "Nether Pos: ";
                llllllllllllllllIllIlllIIlllIlII.right2 = String.format("%.1f %.1f %.1f", llllllllllllllllIllIlllIIlllIIIl / 8.0, llllllllllllllllIllIlllIIlllIIII, llllllllllllllllIllIlllIIllIllll / 8.0);
                break;
            }
            case Nether: {
                llllllllllllllllIllIlllIIlllIlII.left2 = "Overworld Pos: ";
                llllllllllllllllIllIlllIIlllIlII.right2 = String.format("%.1f %.1f %.1f", llllllllllllllllIllIlllIIlllIIIl * 8.0, llllllllllllllllIllIlllIIlllIIII, llllllllllllllllIllIlllIIllIllll * 8.0);
            }
        }
        double llllllllllllllllIllIlllIIllIlllI = llllllllllllllllIllIlllIIlllIlII.left1Width + llllllllllllllllIllIlllIIlllIIll.textWidth(llllllllllllllllIllIlllIIlllIlII.right1);
        if (llllllllllllllllIllIlllIIlllIlII.left2 != null) {
            llllllllllllllllIllIlllIIlllIlII.left2Width = llllllllllllllllIllIlllIIlllIIll.textWidth(llllllllllllllllIllIlllIIlllIlII.left2);
            llllllllllllllllIllIlllIIllIlllI = Math.max(llllllllllllllllIllIlllIIllIlllI, llllllllllllllllIllIlllIIlllIlII.left2Width + llllllllllllllllIllIlllIIlllIIll.textWidth(llllllllllllllllIllIlllIIlllIlII.right2));
        }
        llllllllllllllllIllIlllIIlllIlII.box.setSize(llllllllllllllllIllIlllIIllIlllI, llllllllllllllllIllIlllIIlllIIll.textHeight() * 2.0 + 2.0);
    }

    @Override
    public void render(HudRenderer llllllllllllllllIllIlllIIlIllIIl) {
        PositionHud llllllllllllllllIllIlllIIllIIIII;
        double llllllllllllllllIllIlllIIlIllllI = llllllllllllllllIllIlllIIllIIIII.box.getX();
        double llllllllllllllllIllIlllIIlIlllIl = llllllllllllllllIllIlllIIllIIIII.box.getY();
        if (llllllllllllllllIllIlllIIllIIIII.left2 != null) {
            llllllllllllllllIllIlllIIlIllIIl.text(llllllllllllllllIllIlllIIllIIIII.left2, llllllllllllllllIllIlllIIlIllllI, llllllllllllllllIllIlllIIlIlllIl, llllllllllllllllIllIlllIIllIIIII.hud.primaryColor.get());
            llllllllllllllllIllIlllIIlIllIIl.text(llllllllllllllllIllIlllIIllIIIII.right2, llllllllllllllllIllIlllIIlIllllI + llllllllllllllllIllIlllIIllIIIII.left2Width, llllllllllllllllIllIlllIIlIlllIl, llllllllllllllllIllIlllIIllIIIII.hud.secondaryColor.get());
        }
        double llllllllllllllllIllIlllIIlIlllII = llllllllllllllllIllIlllIIllIIIII.box.alignX(llllllllllllllllIllIlllIIllIIIII.left1Width + llllllllllllllllIllIlllIIlIllIIl.textWidth(llllllllllllllllIllIlllIIllIIIII.right1));
        double llllllllllllllllIllIlllIIlIllIll = llllllllllllllllIllIlllIIlIllIIl.textHeight() + 2.0;
        llllllllllllllllIllIlllIIlIllIIl.text("Pos: ", llllllllllllllllIllIlllIIlIllllI + llllllllllllllllIllIlllIIlIlllII, llllllllllllllllIllIlllIIlIlllIl + llllllllllllllllIllIlllIIlIllIll, llllllllllllllllIllIlllIIllIIIII.hud.primaryColor.get());
        llllllllllllllllIllIlllIIlIllIIl.text(llllllllllllllllIllIlllIIllIIIII.right1, llllllllllllllllIllIlllIIlIllllI + llllllllllllllllIllIlllIIlIlllII + llllllllllllllllIllIlllIIllIIIII.left1Width, llllllllllllllllIllIlllIIlIlllIl + llllllllllllllllIllIlllIIlIllIll, llllllllllllllllIllIlllIIllIIIII.hud.secondaryColor.get());
    }

    public PositionHud(HUD llllllllllllllllIllIlllIIllllllI) {
        super(llllllllllllllllIllIlllIIllllllI, "coords", "Displays your coordinates in the world.");
        PositionHud llllllllllllllllIllIlllIIlllllll;
        llllllllllllllllIllIlllIIlllllll.left1 = "Pos: ";
    }
}

