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
    private final /* synthetic */ Setting<Integer> customYaw;
    private final /* synthetic */ Setting<SettingColor> backgroundColor;
    private final /* synthetic */ Setting<Boolean> copyPitch;
    private final /* synthetic */ Setting<Boolean> background;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> copyYaw;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ Setting<Integer> customPitch;

    public PlayerModelHud(HUD lIIlIIllIllllII) {
        super(lIIlIIllIllllII, "player-model", "Displays a model of your player.", false);
        PlayerModelHud lIIlIIllIllllIl;
        lIIlIIllIllllIl.sgGeneral = lIIlIIllIllllIl.settings.getDefaultGroup();
        lIIlIIllIllllIl.scale = lIIlIIllIllllIl.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of player model.").defaultValue(3.0).min(1.0).sliderMin(1.0).sliderMax(4.0).build());
        lIIlIIllIllllIl.copyYaw = lIIlIIllIllllIl.sgGeneral.add(new BoolSetting.Builder().name("copy-yaw").description("Makes the player model's yaw equal to yours.").defaultValue(true).build());
        lIIlIIllIllllIl.copyPitch = lIIlIIllIllllIl.sgGeneral.add(new BoolSetting.Builder().name("copy-pitch").description("Makes the player model's pitch equal to yours.").defaultValue(true).build());
        lIIlIIllIllllIl.customYaw = lIIlIIllIllllIl.sgGeneral.add(new IntSetting.Builder().name("custom-yaw").description("Custom yaw for when copy yaw is off.").defaultValue(0).min(-180).max(180).sliderMin(-180).sliderMax(180).visible(() -> {
            PlayerModelHud lIIlIIllIIlllIl;
            return lIIlIIllIIlllIl.copyYaw.get() == false;
        }).build());
        lIIlIIllIllllIl.customPitch = lIIlIIllIllllIl.sgGeneral.add(new IntSetting.Builder().name("custom-pitch").description("Custom pitch for when copy pitch is off.").defaultValue(0).min(-180).max(180).sliderMin(-180).sliderMax(180).visible(() -> {
            PlayerModelHud lIIlIIllIlIIIIl;
            return lIIlIIllIlIIIIl.copyPitch.get() == false;
        }).build());
        lIIlIIllIllllIl.background = lIIlIIllIllllIl.sgGeneral.add(new BoolSetting.Builder().name("background").description("Displays a background behind the player model.").defaultValue(true).build());
        lIIlIIllIllllIl.backgroundColor = lIIlIIllIllllIl.sgGeneral.add(new ColorSetting.Builder().name("background-color").description("Color of background.").defaultValue(new SettingColor(0, 0, 0, 64)).visible(lIIlIIllIllllIl.background::get).build());
    }

    @Override
    public void update(HudRenderer lIIlIIllIllIlll) {
        PlayerModelHud lIIlIIllIllIllI;
        lIIlIIllIllIllI.box.setSize(50.0 * lIIlIIllIllIllI.scale.get(), 75.0 * lIIlIIllIllIllI.scale.get());
    }

    @Override
    public void render(HudRenderer lIIlIIllIlIlllI) {
        PlayerModelHud lIIlIIllIlIlIII;
        double lIIlIIllIlIllIl = lIIlIIllIlIlIII.box.getX();
        double lIIlIIllIlIllII = lIIlIIllIlIlIII.box.getY();
        if (lIIlIIllIlIlIII.background.get().booleanValue()) {
            Renderer.NORMAL.begin(null, DrawMode.Triangles, class_290.field_1576);
            Renderer.NORMAL.quad(lIIlIIllIlIllIl, lIIlIIllIlIllII, lIIlIIllIlIlIII.box.width, lIIlIIllIlIlIII.box.height, lIIlIIllIlIlIII.backgroundColor.get());
            Renderer.NORMAL.end();
        }
        class_746 lIIlIIllIlIlIll = lIIlIIllIlIlIII.mc.field_1724;
        if (lIIlIIllIlIlIII.isInEditor()) {
            lIIlIIllIlIlIll = FakeClientPlayer.getPlayer();
        }
        float lIIlIIllIlIlIlI = lIIlIIllIlIlIII.copyYaw.get() != false ? class_3532.method_15393((float)(lIIlIIllIlIlIll.field_5982 + (lIIlIIllIlIlIll.field_6031 - lIIlIIllIlIlIll.field_5982) * lIIlIIllIlIlIII.mc.method_1488())) : (float)lIIlIIllIlIlIII.customYaw.get().intValue();
        float lIIlIIllIlIlIIl = lIIlIIllIlIlIII.copyPitch.get() != false ? lIIlIIllIlIlIll.field_5965 : (float)lIIlIIllIlIlIII.customPitch.get().intValue();
        class_490.method_2486((int)((int)(lIIlIIllIlIllIl + 25.0 * lIIlIIllIlIlIII.scale.get())), (int)((int)(lIIlIIllIlIllII + 66.0 * lIIlIIllIlIlIII.scale.get())), (int)((int)(30.0 * lIIlIIllIlIlIII.scale.get())), (float)(-lIIlIIllIlIlIlI), (float)(-lIIlIIllIlIlIIl), (class_1309)lIIlIIllIlIlIll);
    }
}

