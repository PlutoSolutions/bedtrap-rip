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
    private final Color color = new Color();

    private String getString(class_1293 class_12932) {
        return String.format("%s %d (%s)", Names.get(class_12932.method_5579()), class_12932.method_5578() + 1, class_1292.method_5577((class_1293)class_12932, (float)1.0f));
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (this.isInEditor()) {
            hudRenderer.text("Potion Timers 0:00", d, d2, this.color);
            return;
        }
        int n = 0;
        for (class_1293 class_12932 : this.mc.field_1724.method_6026()) {
            class_1291 class_12912 = class_12932.method_5579();
            int n2 = class_12912.method_5556();
            this.color.r = Color.toRGBAR(n2);
            this.color.g = Color.toRGBAG(n2);
            this.color.b = Color.toRGBAB(n2);
            String string = this.getString(class_12932);
            hudRenderer.text(string, d + this.box.alignX(hudRenderer.textWidth(string)), d2, this.color);
            this.color.b = 255;
            this.color.g = 255;
            this.color.r = 255;
            d2 += hudRenderer.textHeight();
            if (n > 0) {
                d2 += 2.0;
            }
            ++n;
        }
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        if (this.isInEditor()) {
            this.box.setSize(hudRenderer.textWidth("Potion Timers 0:00"), hudRenderer.textHeight());
            return;
        }
        double d = 0.0;
        double d2 = 0.0;
        int n = 0;
        for (class_1293 class_12932 : this.mc.field_1724.method_6026()) {
            d = Math.max(d, hudRenderer.textWidth(this.getString(class_12932)));
            d2 += hudRenderer.textHeight();
            if (n > 0) {
                d2 += 2.0;
            }
            ++n;
        }
        this.box.setSize(d, d2);
    }

    public PotionTimersHud(HUD hUD) {
        super(hUD, "potion-timers", "Displays active potion effects with timers.");
    }
}

