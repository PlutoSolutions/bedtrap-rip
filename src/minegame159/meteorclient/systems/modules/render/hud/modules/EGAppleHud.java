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

public class EGAppleHud
extends HudElement {
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;

    public EGAppleHud(HUD llIllIlllIlllll) {
        super(llIllIlllIlllll, "EGApples", "Displays the amount of enchanted golden apples in your inventory.", false);
        EGAppleHud llIllIllllIIIII;
        llIllIllllIIIII.sgGeneral = llIllIllllIIIII.settings.getDefaultGroup();
        llIllIllllIIIII.scale = llIllIllllIIIII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }

    @Override
    public void update(HudRenderer llIllIlllIlllII) {
        EGAppleHud llIllIlllIllIll;
        llIllIlllIllIll.box.setSize(16.0 * llIllIlllIllIll.scale.get(), 16.0 * llIllIlllIllIll.scale.get());
    }

    @Override
    public void render(HudRenderer llIllIlllIlIllI) {
        EGAppleHud llIllIlllIlIIll;
        double llIllIlllIlIlIl = llIllIlllIlIIll.box.getX();
        double llIllIlllIlIlII = llIllIlllIlIIll.box.getY();
        if (llIllIlllIlIIll.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8367.method_7854(), (int)llIllIlllIlIlIl, (int)llIllIlllIlIlII, llIllIlllIlIIll.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8367).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8367, InvUtils.find(class_1802.field_8367).getCount()), (int)llIllIlllIlIlIl, (int)llIllIlllIlIlII, llIllIlllIlIIll.scale.get(), true);
        }
    }
}

