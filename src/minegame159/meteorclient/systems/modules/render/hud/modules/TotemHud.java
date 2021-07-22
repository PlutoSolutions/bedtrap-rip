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

public class TotemHud
extends HudElement {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> scale;

    @Override
    public void render(HudRenderer llllllllllllllllIlIlllllIIlIlllI) {
        TotemHud llllllllllllllllIlIlllllIIlIlIll;
        double llllllllllllllllIlIlllllIIlIllIl = llllllllllllllllIlIlllllIIlIlIll.box.getX();
        double llllllllllllllllIlIlllllIIlIllII = llllllllllllllllIlIlllllIIlIlIll.box.getY();
        if (llllllllllllllllIlIlllllIIlIlIll.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8288.method_7854(), (int)llllllllllllllllIlIlllllIIlIllIl, (int)llllllllllllllllIlIlllllIIlIllII, llllllllllllllllIlIlllllIIlIlIll.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8288).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8288, InvUtils.find(class_1802.field_8288).getCount()), (int)llllllllllllllllIlIlllllIIlIllIl, (int)llllllllllllllllIlIlllllIIlIllII, llllllllllllllllIlIlllllIIlIlIll.scale.get(), true);
        }
    }

    @Override
    public void update(HudRenderer llllllllllllllllIlIlllllIIllIlII) {
        TotemHud llllllllllllllllIlIlllllIIllIlIl;
        llllllllllllllllIlIlllllIIllIlIl.box.setSize(16.0 * llllllllllllllllIlIlllllIIllIlIl.scale.get(), 16.0 * llllllllllllllllIlIlllllIIllIlIl.scale.get());
    }

    public TotemHud(HUD llllllllllllllllIlIlllllIIllIlll) {
        super(llllllllllllllllIlIlllllIIllIlll, "totems", "Displays the amount of totems in your inventory.", false);
        TotemHud llllllllllllllllIlIlllllIIlllIII;
        llllllllllllllllIlIlllllIIlllIII.sgGeneral = llllllllllllllllIlIlllllIIlllIII.settings.getDefaultGroup();
        llllllllllllllllIlIlllllIIlllIII.scale = llllllllllllllllIlIlllllIIlllIII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of totem counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }
}

