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

public class CrystalHud
extends HudElement {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> scale;

    public CrystalHud(HUD llIlIllllIllI) {
        super(llIlIllllIllI, "Crystals", "Displays the amount of crystals in your inventory.", false);
        CrystalHud llIlIllllIlll;
        llIlIllllIlll.sgGeneral = llIlIllllIlll.settings.getDefaultGroup();
        llIlIllllIlll.scale = llIlIllllIlll.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }

    @Override
    public void update(HudRenderer llIlIllllIIIl) {
        CrystalHud llIlIllllIIlI;
        llIlIllllIIlI.box.setSize(16.0 * llIlIllllIIlI.scale.get(), 16.0 * llIlIllllIIlI.scale.get());
    }

    @Override
    public void render(HudRenderer llIlIlllIlIll) {
        CrystalHud llIlIlllIlIII;
        double llIlIlllIlIlI = llIlIlllIlIII.box.getX();
        double llIlIlllIlIIl = llIlIlllIlIII.box.getY();
        if (llIlIlllIlIII.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8301.method_7854(), (int)llIlIlllIlIlI, (int)llIlIlllIlIIl, llIlIlllIlIII.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8301).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8301, InvUtils.find(class_1802.field_8301).getCount()), (int)llIlIlllIlIlI, (int)llIlIlllIlIIl, llIlIlllIlIII.scale.get(), true);
        }
    }
}

