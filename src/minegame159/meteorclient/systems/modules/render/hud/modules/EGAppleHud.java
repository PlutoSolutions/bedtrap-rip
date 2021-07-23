/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Double> scale;
    private final SettingGroup sgGeneral;

    public EGAppleHud(HUD hUD) {
        super(hUD, "EGApples", "Displays the amount of enchanted golden apples in your inventory.", false);
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
        if (this.isInEditor()) {
            RenderUtils.drawItem(class_1802.field_8367.method_7854(), (int)d, (int)d2, this.scale.get(), true);
        } else if (InvUtils.find(class_1802.field_8367).getCount() > 0) {
            RenderUtils.drawItem(new class_1799((class_1935)class_1802.field_8367, InvUtils.find(class_1802.field_8367).getCount()), (int)d, (int)d2, this.scale.get(), true);
        }
    }
}

