/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_290
 *  net.minecraft.class_3532
 *  net.minecraft.class_490
 *  net.minecraft.class_746
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.misc.FakeClientPlayer;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1309;
import net.minecraft.class_290;
import net.minecraft.class_3532;
import net.minecraft.class_490;
import net.minecraft.class_746;

public class PlayerModelHud
extends HudElement {
    private final Setting<Integer> customYaw;
    private final Setting<SettingColor> backgroundColor;
    private final Setting<Boolean> copyPitch;
    private final Setting<Boolean> background;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> copyYaw;
    private final Setting<Double> scale;
    private final Setting<Integer> customPitch;

    public PlayerModelHud(HUD hUD) {
        super(hUD, "player-model", "Displays a model of your player.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of player model.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
        this.copyYaw = this.sgGeneral.add(new BoolSetting.Builder().name("copy-yaw").description("Makes the player model's yaw equal to yours.").defaultValue(true).build());
        this.copyPitch = this.sgGeneral.add(new BoolSetting.Builder().name("copy-pitch").description("Makes the player model's pitch equal to yours.").defaultValue(true).build());
        this.customYaw = this.sgGeneral.add(new IntSetting.Builder().name("custom-yaw").description("Custom yaw for when copy yaw is off.").defaultValue(0).min(-180).max(180).sliderMin(-180).sliderMax(180).visible(this::lambda$new$0).build());
        this.customPitch = this.sgGeneral.add(new IntSetting.Builder().name("custom-pitch").description("Custom pitch for when copy pitch is off.").defaultValue(0).min(-180).max(180).sliderMin(-180).sliderMax(180).visible(this::lambda$new$1).build());
        this.background = this.sgGeneral.add(new BoolSetting.Builder().name("background").description("Displays a background behind the player model.").defaultValue(true).build());
        this.backgroundColor = this.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of background.").defaultValue(new SettingColor(0, 0, 0, 64)).visible(this.background::get).build());
    }

    private boolean lambda$new$0() {
        return this.copyYaw.get() == false;
    }

    private boolean lambda$new$1() {
        return this.copyPitch.get() == false;
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        this.box.setSize(50.0 * this.scale.get(), 75.0 * this.scale.get());
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (this.background.get().booleanValue()) {
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            Renderer.NORMAL.quad(d, d2, this.box.width, this.box.height, this.backgroundColor.get());
            Renderer.NORMAL.end();
        }
        class_746 class_7462 = this.mc.field_1724;
        if (this.isInEditor()) {
            class_7462 = FakeClientPlayer.getPlayer();
        }
        float f = this.copyYaw.get() != false ? class_3532.method_15393((float)(class_7462.field_5982 + (class_7462.field_6031 - class_7462.field_5982) * this.mc.method_1488())) : (float)this.customYaw.get().intValue();
        float f2 = this.copyPitch.get() != false ? class_7462.field_5965 : (float)this.customPitch.get().intValue();
        class_490.method_2486((int)((int)(d + 25.0 * this.scale.get())), (int)((int)(d2 + 66.0 * this.scale.get())), (int)((int)(30.0 * this.scale.get())), (float)(-f), (float)(-f2), (class_1309)class_7462);
    }
}

