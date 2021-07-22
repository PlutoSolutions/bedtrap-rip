/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class HandView
extends Module {
    public final Setting<Double> scaleX;
    private final SettingGroup sgGeneral;
    public final Setting<Double> scaleZ;
    public final Setting<Double> posX;
    public final Setting<Double> offSwing;
    public final Setting<Double> scaleY;
    public final Setting<Double> mainSwing;
    public final Setting<Double> rotationY;
    public final Setting<Double> posY;
    public final Setting<SwingMode> swingMode;
    public final Setting<Double> rotationX;
    private final SettingGroup sgSwing;
    public final Setting<Double> rotationZ;
    public final Setting<Double> posZ;

    public HandView() {
        super(Categories.Render, "hand-view", "Alters the way items are rendered in your hands.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgSwing = this.settings.createGroup("Swing");
        this.rotationX = this.sgGeneral.add(new DoubleSetting.Builder().name("rotation-x").description("The X rotation of your hands.").defaultValue(0.0).sliderMin(-0.2).sliderMax(0.2).build());
        this.rotationY = this.sgGeneral.add(new DoubleSetting.Builder().name("rotation-y").description("The Y rotation of your hands.").defaultValue(0.0).sliderMin(-0.2).sliderMax(0.2).build());
        this.rotationZ = this.sgGeneral.add(new DoubleSetting.Builder().name("rotation-z").description("The Z rotation of your hands.").defaultValue(0.0).sliderMin(-0.25).sliderMax(0.25).build());
        this.scaleX = this.sgGeneral.add(new DoubleSetting.Builder().name("scale-x").description("The X scale of the items rendered in your hands.").defaultValue(0.75).sliderMin(0.0).sliderMax(1.5).build());
        this.scaleY = this.sgGeneral.add(new DoubleSetting.Builder().name("scale-y").description("The Y scale of the items rendered in your hands.").defaultValue(0.6).sliderMin(0.0).sliderMax(2.0).build());
        this.scaleZ = this.sgGeneral.add(new DoubleSetting.Builder().name("scale-z").description("The Z scale of the items rendered in your hands.").defaultValue(1.0).sliderMin(0.0).sliderMax(5.0).build());
        this.posX = this.sgGeneral.add(new DoubleSetting.Builder().name("pos-x").description("The X offset of your hands.").defaultValue(0.0).sliderMin(-3.0).sliderMax(3.0).build());
        this.posY = this.sgGeneral.add(new DoubleSetting.Builder().name("pos-y").description("The Y offset of your hands.").defaultValue(0.0).sliderMin(-3.0).sliderMax(3.0).build());
        this.posZ = this.sgGeneral.add(new DoubleSetting.Builder().name("pos-z").description("The Z offset of your hands.").defaultValue(-0.1).sliderMin(-3.0).sliderMax(3.0).build());
        this.mainSwing = this.sgSwing.add(new DoubleSetting.Builder().name("main-swing-progress").description("The swing progress of your mainhand.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).build());
        this.offSwing = this.sgSwing.add(new DoubleSetting.Builder().name("off-swing-progress").description("The swing progress of your offhand.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).build());
        this.swingMode = this.sgSwing.add(new EnumSetting.Builder().name("swing-mode").description("Modifies your client & server hand swinging.").defaultValue(SwingMode.None).build());
    }

    public static enum SwingMode {
        Offhand,
        Mainhand,
        None;

    }
}

