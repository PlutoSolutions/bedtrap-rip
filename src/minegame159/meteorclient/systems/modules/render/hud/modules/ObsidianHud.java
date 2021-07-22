/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.render.RenderUtils;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;

public class ObsidianHud
extends HudElement {
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void update(HudRenderer lllIIIIIllIIll) {
        ObsidianHud lllIIIIIllIIlI;
        lllIIIIIllIIlI.box.setSize(16.0 * lllIIIIIllIIlI.scale.get(), 16.0 * lllIIIIIllIIlI.scale.get());
    }

    @Override
    public void render(HudRenderer lllIIIIIlIllIl) {
        ObsidianHud lllIIIIIlIlIlI;
        double lllIIIIIlIllII = lllIIIIIlIlIlI.box.getX();
        double lllIIIIIlIlIll = lllIIIIIlIlIlI.box.getY();
        if (lllIIIIIlIlIlI.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8281.method_7854(), (int)lllIIIIIlIllII, (int)lllIIIIIlIlIll, lllIIIIIlIlIlI.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8281).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8281, InvUtils.find(class_1802.field_8281).getCount()), (int)lllIIIIIlIllII, (int)lllIIIIIlIlIll, lllIIIIIlIlIlI.scale.get(), true);
        }
    }

    public ObsidianHud(HUD lllIIIIIllIllI) {
        super(lllIIIIIllIllI, "Obsidian", "Displays the amount of obsidian in your inventory.", false);
        ObsidianHud lllIIIIIlllIIl;
        lllIIIIIlllIIl.sgGeneral = lllIIIIIlllIIl.settings.getDefaultGroup();
        lllIIIIIlllIIl.scale = lllIIIIIlllIIl.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }
}

