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

public class ExpHud
extends HudElement {
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void update(HudRenderer llllllllllllllllIlllIIIIIIllIIll) {
        ExpHud llllllllllllllllIlllIIIIIIllIlII;
        llllllllllllllllIlllIIIIIIllIlII.box.setSize(16.0 * llllllllllllllllIlllIIIIIIllIlII.scale.get(), 16.0 * llllllllllllllllIlllIIIIIIllIlII.scale.get());
    }

    @Override
    public void render(HudRenderer llllllllllllllllIlllIIIIIIlIllIl) {
        ExpHud llllllllllllllllIlllIIIIIIlIlllI;
        double llllllllllllllllIlllIIIIIIlIllII = llllllllllllllllIlllIIIIIIlIlllI.box.getX();
        double llllllllllllllllIlllIIIIIIlIlIll = llllllllllllllllIlllIIIIIIlIlllI.box.getY();
        if (llllllllllllllllIlllIIIIIIlIlllI.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8287.method_7854(), (int)llllllllllllllllIlllIIIIIIlIllII, (int)llllllllllllllllIlllIIIIIIlIlIll, llllllllllllllllIlllIIIIIIlIlllI.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8287).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8287, InvUtils.find(class_1802.field_8287).getCount()), (int)llllllllllllllllIlllIIIIIIlIllII, (int)llllllllllllllllIlllIIIIIIlIlIll, llllllllllllllllIlllIIIIIIlIlllI.scale.get(), true);
        }
    }

    public ExpHud(HUD llllllllllllllllIlllIIIIIIlllIII) {
        super(llllllllllllllllIlllIIIIIIlllIII, "Exp", "Displays the amount of exp bottles in your inventory.", false);
        ExpHud llllllllllllllllIlllIIIIIIllIlll;
        llllllllllllllllIlllIIIIIIllIlll.sgGeneral = llllllllllllllllIlllIIIIIIllIlll.settings.getDefaultGroup();
        llllllllllllllllIlllIIIIIIllIlll.scale = llllllllllllllllIlllIIIIIIllIlll.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }
}

