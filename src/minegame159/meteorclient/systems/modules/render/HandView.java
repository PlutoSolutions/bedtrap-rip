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
    public final /* synthetic */ Setting<Double> scaleX;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Double> scaleZ;
    public final /* synthetic */ Setting<Double> posX;
    public final /* synthetic */ Setting<Double> offSwing;
    public final /* synthetic */ Setting<Double> scaleY;
    public final /* synthetic */ Setting<Double> mainSwing;
    public final /* synthetic */ Setting<Double> rotationY;
    public final /* synthetic */ Setting<Double> posY;
    public final /* synthetic */ Setting<SwingMode> swingMode;
    public final /* synthetic */ Setting<Double> rotationX;
    private final /* synthetic */ SettingGroup sgSwing;
    public final /* synthetic */ Setting<Double> rotationZ;
    public final /* synthetic */ Setting<Double> posZ;

    public HandView() {
        super(Categories.Render, "hand-view", "Alters the way items are rendered in your hands.");
        HandView lllllllllllllllllIlIIIllllIlIIll;
        lllllllllllllllllIlIIIllllIlIIll.sgGeneral = lllllllllllllllllIlIIIllllIlIIll.settings.getDefaultGroup();
        lllllllllllllllllIlIIIllllIlIIll.sgSwing = lllllllllllllllllIlIIIllllIlIIll.settings.createGroup("Swing");
        lllllllllllllllllIlIIIllllIlIIll.rotationX = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("rotation-x").description("The X rotation of your hands.").defaultValue(0.0).sliderMin(-0.2).sliderMax(0.2).build());
        lllllllllllllllllIlIIIllllIlIIll.rotationY = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("rotation-y").description("The Y rotation of your hands.").defaultValue(0.0).sliderMin(-0.2).sliderMax(0.2).build());
        lllllllllllllllllIlIIIllllIlIIll.rotationZ = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("rotation-z").description("The Z rotation of your hands.").defaultValue(0.0).sliderMin(-0.25).sliderMax(0.25).build());
        lllllllllllllllllIlIIIllllIlIIll.scaleX = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("scale-x").description("The X scale of the items rendered in your hands.").defaultValue(0.75).sliderMin(0.0).sliderMax(1.5).build());
        lllllllllllllllllIlIIIllllIlIIll.scaleY = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("scale-y").description("The Y scale of the items rendered in your hands.").defaultValue(0.6).sliderMin(0.0).sliderMax(2.0).build());
        lllllllllllllllllIlIIIllllIlIIll.scaleZ = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("scale-z").description("The Z scale of the items rendered in your hands.").defaultValue(1.0).sliderMin(0.0).sliderMax(5.0).build());
        lllllllllllllllllIlIIIllllIlIIll.posX = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("pos-x").description("The X offset of your hands.").defaultValue(0.0).sliderMin(-3.0).sliderMax(3.0).build());
        lllllllllllllllllIlIIIllllIlIIll.posY = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("pos-y").description("The Y offset of your hands.").defaultValue(0.0).sliderMin(-3.0).sliderMax(3.0).build());
        lllllllllllllllllIlIIIllllIlIIll.posZ = lllllllllllllllllIlIIIllllIlIIll.sgGeneral.add(new DoubleSetting.Builder().name("pos-z").description("The Z offset of your hands.").defaultValue(-0.1).sliderMin(-3.0).sliderMax(3.0).build());
        lllllllllllllllllIlIIIllllIlIIll.mainSwing = lllllllllllllllllIlIIIllllIlIIll.sgSwing.add(new DoubleSetting.Builder().name("main-swing-progress").description("The swing progress of your mainhand.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).build());
        lllllllllllllllllIlIIIllllIlIIll.offSwing = lllllllllllllllllIlIIIllllIlIIll.sgSwing.add(new DoubleSetting.Builder().name("off-swing-progress").description("The swing progress of your offhand.").defaultValue(0.0).sliderMin(0.0).sliderMax(1.0).build());
        lllllllllllllllllIlIIIllllIlIIll.swingMode = lllllllllllllllllIlIIIllllIlIIll.sgSwing.add(new EnumSetting.Builder().name("swing-mode").description("Modifies your client & server hand swinging.").defaultValue(SwingMode.None).build());
    }

    public static enum SwingMode {
        Offhand,
        Mainhand,
        None;


        private SwingMode() {
            SwingMode lllIlIlIlIlIl;
        }
    }
}

