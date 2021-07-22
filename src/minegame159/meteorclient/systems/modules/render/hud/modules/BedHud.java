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
    private final Setting<Double> scale;
    private final SettingGroup sgGeneral;

    public BedHud(HUD hUD) {
        super(hUD, "Beds", "Displays the amount of beds in your inventory.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of golden apple counter.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(16.0 * this.scale.get(), 16.0 * this.scale.get());
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        int n = InvUtils.find(class_1802.field_8112).getCount() + InvUtils.find(class_1802.field_8893).getCount() + InvUtils.find(class_1802.field_8464).getCount() + InvUtils.find(class_1802.field_8390).getCount() + InvUtils.find(class_1802.field_8754).getCount() + InvUtils.find(class_1802.field_8368).getCount() + InvUtils.find(class_1802.field_8286).getCount() + InvUtils.find(class_1802.field_8146).getCount() + InvUtils.find(class_1802.field_8679).getCount() + InvUtils.find(class_1802.field_8349).getCount() + InvUtils.find(class_1802.field_8059).getCount() + InvUtils.find(class_1802.field_8417).getCount() + InvUtils.find(class_1802.field_8262).getCount() + InvUtils.find(class_1802.field_8258).getCount() + InvUtils.find(class_1802.field_8863).getCount();
        int n2 = InvUtils.findInHotbar(class_1802.field_8789).getCount() + n;
        if (this.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8789.method_7854(), (int)d, (int)d2, this.scale.get(), true);
        } else if (n2 > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8789, n2), (int)d, (int)d2, this.scale.get(), true);
        }
    }
}

