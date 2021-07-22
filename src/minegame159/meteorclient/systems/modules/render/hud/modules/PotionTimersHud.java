/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1291
 *  net.minecraft.class_1292
 *  net.minecraft.class_1293
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.misc.Names;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1291;
import net.minecraft.class_1292;
import net.minecraft.class_1293;

public class PotionTimersHud
extends HudElement {
    private final /* synthetic */ Color color;

    private String getString(class_1293 lllllllllllllllllIIlIlIlllIlllll) {
        return String.format("%s %d (%s)", Names.get(lllllllllllllllllIIlIlIlllIlllll.method_5579()), lllllllllllllllllIIlIlIlllIlllll.method_5578() + 1, class_1292.method_5577((class_1293)lllllllllllllllllIIlIlIlllIlllll, (float)1.0f));
    }

    @Override
    public void render(HudRenderer lllllllllllllllllIIlIlIlllllIIII) {
        PotionTimersHud lllllllllllllllllIIlIlIlllllIIIl;
        double lllllllllllllllllIIlIlIllllIllll = lllllllllllllllllIIlIlIlllllIIIl.box.getX();
        double lllllllllllllllllIIlIlIllllIlllI = lllllllllllllllllIIlIlIlllllIIIl.box.getY();
        if (lllllllllllllllllIIlIlIlllllIIIl.isInEditor()) {
            lllllllllllllllllIIlIlIlllllIIII.text("Potion Timers 0:00", lllllllllllllllllIIlIlIllllIllll, lllllllllllllllllIIlIlIllllIlllI, lllllllllllllllllIIlIlIlllllIIIl.color);
            return;
        }
        int lllllllllllllllllIIlIlIllllIllIl = 0;
        for (class_1293 lllllllllllllllllIIlIlIlllllIIlI : lllllllllllllllllIIlIlIlllllIIIl.mc.field_1724.method_6026()) {
            class_1291 lllllllllllllllllIIlIlIlllllIlIl = lllllllllllllllllIIlIlIlllllIIlI.method_5579();
            int lllllllllllllllllIIlIlIlllllIlII = lllllllllllllllllIIlIlIlllllIlIl.method_5556();
            lllllllllllllllllIIlIlIlllllIIIl.color.r = Color.toRGBAR(lllllllllllllllllIIlIlIlllllIlII);
            lllllllllllllllllIIlIlIlllllIIIl.color.g = Color.toRGBAG(lllllllllllllllllIIlIlIlllllIlII);
            lllllllllllllllllIIlIlIlllllIIIl.color.b = Color.toRGBAB(lllllllllllllllllIIlIlIlllllIlII);
            String lllllllllllllllllIIlIlIlllllIIll = lllllllllllllllllIIlIlIlllllIIIl.getString(lllllllllllllllllIIlIlIlllllIIlI);
            lllllllllllllllllIIlIlIlllllIIII.text(lllllllllllllllllIIlIlIlllllIIll, lllllllllllllllllIIlIlIllllIllll + lllllllllllllllllIIlIlIlllllIIIl.box.alignX(lllllllllllllllllIIlIlIlllllIIII.textWidth(lllllllllllllllllIIlIlIlllllIIll)), lllllllllllllllllIIlIlIllllIlllI, lllllllllllllllllIIlIlIlllllIIIl.color);
            lllllllllllllllllIIlIlIlllllIIIl.color.b = 255;
            lllllllllllllllllIIlIlIlllllIIIl.color.g = 255;
            lllllllllllllllllIIlIlIlllllIIIl.color.r = 255;
            lllllllllllllllllIIlIlIllllIlllI += lllllllllllllllllIIlIlIlllllIIII.textHeight();
            if (lllllllllllllllllIIlIlIllllIllIl > 0) {
                lllllllllllllllllIIlIlIllllIlllI += 2.0;
            }
            ++lllllllllllllllllIIlIlIllllIllIl;
        }
    }

    @Override
    public void update(HudRenderer lllllllllllllllllIIlIllIIIIIlIlI) {
        PotionTimersHud lllllllllllllllllIIlIllIIIIIlIll;
        if (lllllllllllllllllIIlIllIIIIIlIll.isInEditor()) {
            lllllllllllllllllIIlIllIIIIIlIll.box.setSize(lllllllllllllllllIIlIllIIIIIlIlI.textWidth("Potion Timers 0:00"), lllllllllllllllllIIlIllIIIIIlIlI.textHeight());
            return;
        }
        double lllllllllllllllllIIlIllIIIIIlIIl = 0.0;
        double lllllllllllllllllIIlIllIIIIIlIII = 0.0;
        int lllllllllllllllllIIlIllIIIIIIlll = 0;
        for (class_1293 lllllllllllllllllIIlIllIIIIIllII : lllllllllllllllllIIlIllIIIIIlIll.mc.field_1724.method_6026()) {
            lllllllllllllllllIIlIllIIIIIlIIl = Math.max(lllllllllllllllllIIlIllIIIIIlIIl, lllllllllllllllllIIlIllIIIIIlIlI.textWidth(lllllllllllllllllIIlIllIIIIIlIll.getString(lllllllllllllllllIIlIllIIIIIllII)));
            lllllllllllllllllIIlIllIIIIIlIII += lllllllllllllllllIIlIllIIIIIlIlI.textHeight();
            if (lllllllllllllllllIIlIllIIIIIIlll > 0) {
                lllllllllllllllllIIlIllIIIIIlIII += 2.0;
            }
            ++lllllllllllllllllIIlIllIIIIIIlll;
        }
        lllllllllllllllllIIlIllIIIIIlIll.box.setSize(lllllllllllllllllIIlIllIIIIIlIIl, lllllllllllllllllIIlIllIIIIIlIII);
    }

    public PotionTimersHud(HUD lllllllllllllllllIIlIllIIIIlIllI) {
        super(lllllllllllllllllIIlIllIIIIlIllI, "potion-timers", "Displays active potion effects with timers.");
        PotionTimersHud lllllllllllllllllIIlIllIIIIlIlIl;
        lllllllllllllllllIIlIllIIIIlIlIl.color = new Color();
    }
}

