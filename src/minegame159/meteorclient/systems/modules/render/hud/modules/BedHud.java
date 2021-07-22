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

public class BedHud
extends HudElement {
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;

    public BedHud(HUD lllllllllllllllllIlIIlllIIlIIIII) {
        super(lllllllllllllllllIlIIlllIIlIIIII, "Beds", "Displays the amount of beds in your inventory.", false);
        BedHud lllllllllllllllllIlIIlllIIlIIIll;
        lllllllllllllllllIlIIlllIIlIIIll.sgGeneral = lllllllllllllllllIlIIlllIIlIIIll.settings.getDefaultGroup();
        lllllllllllllllllIlIIlllIIlIIIll.scale = lllllllllllllllllIlIIlllIIlIIIll.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }

    @Override
    public void update(HudRenderer lllllllllllllllllIlIIlllIIIlllIl) {
        BedHud lllllllllllllllllIlIIlllIIIllllI;
        lllllllllllllllllIlIIlllIIIllllI.box.setSize(16.0 * lllllllllllllllllIlIIlllIIIllllI.scale.get(), 16.0 * lllllllllllllllllIlIIlllIIIllllI.scale.get());
    }

    @Override
    public void render(HudRenderer lllllllllllllllllIlIIlllIIIlIlIl) {
        BedHud lllllllllllllllllIlIIlllIIIlIIII;
        double lllllllllllllllllIlIIlllIIIlIlII = lllllllllllllllllIlIIlllIIIlIIII.box.getX();
        double lllllllllllllllllIlIIlllIIIlIIll = lllllllllllllllllIlIIlllIIIlIIII.box.getY();
        int lllllllllllllllllIlIIlllIIIlIIlI = InvUtils.find(class_1802.field_8112).getCount() + InvUtils.find(class_1802.field_8893).getCount() + InvUtils.find(class_1802.field_8464).getCount() + InvUtils.find(class_1802.field_8390).getCount() + InvUtils.find(class_1802.field_8754).getCount() + InvUtils.find(class_1802.field_8368).getCount() + InvUtils.find(class_1802.field_8286).getCount() + InvUtils.find(class_1802.field_8146).getCount() + InvUtils.find(class_1802.field_8679).getCount() + InvUtils.find(class_1802.field_8349).getCount() + InvUtils.find(class_1802.field_8059).getCount() + InvUtils.find(class_1802.field_8417).getCount() + InvUtils.find(class_1802.field_8262).getCount() + InvUtils.find(class_1802.field_8258).getCount() + InvUtils.find(class_1802.field_8863).getCount();
        int lllllllllllllllllIlIIlllIIIlIIIl = InvUtils.findInHotbar(class_1802.field_8789).getCount() + lllllllllllllllllIlIIlllIIIlIIlI;
        if (lllllllllllllllllIlIIlllIIIlIIII.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8789.method_7854(), (int)lllllllllllllllllIlIIlllIIIlIlII, (int)lllllllllllllllllIlIIlllIIIlIIll, lllllllllllllllllIlIIlllIIIlIIII.scale.get(), true);
        } else if (lllllllllllllllllIlIIlllIIIlIIIl > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8789, lllllllllllllllllIlIIlllIIIlIIIl), (int)lllllllllllllllllIlIIlllIIIlIlII, (int)lllllllllllllllllIlIIlllIIIlIIll, lllllllllllllllllIlIIlllIIIlIIII.scale.get(), true);
        }
    }
}

