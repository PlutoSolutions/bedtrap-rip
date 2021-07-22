/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_5294
 *  net.minecraft.class_5294$class_5401
 */
package minegame159.meteorclient.systems.modules.world;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_243;
import net.minecraft.class_5294;

public class Ambience
extends Module {
    public final /* synthetic */ Setting<Boolean> changeLightningColor;
    public final /* synthetic */ Setting<Boolean> enderCustomSkyColor;
    public final /* synthetic */ Setting<SettingColor> endSkyColor;
    public final /* synthetic */ Setting<SettingColor> skyColor;
    public final /* synthetic */ Setting<Boolean> changeGrassColor;
    public final /* synthetic */ Setting<Boolean> changeLavaColor;
    public final /* synthetic */ Setting<SettingColor> cloudColor;
    public final /* synthetic */ Setting<SettingColor> grassColor;
    public final /* synthetic */ Setting<Boolean> changeWaterColor;
    private final /* synthetic */ SettingGroup sgStatic;
    public final /* synthetic */ Setting<SettingColor> foliageColor;
    private final /* synthetic */ SettingGroup sgDynamic;
    public final /* synthetic */ Setting<SettingColor> lightningColor;
    public final /* synthetic */ Setting<SettingColor> lavaColor;
    public final /* synthetic */ Setting<SettingColor> waterColor;
    public final /* synthetic */ Setting<Boolean> enderMode;
    public final /* synthetic */ Setting<Boolean> changeFoliageColor;
    public final /* synthetic */ Setting<Boolean> changeSkyColor;
    public final /* synthetic */ Setting<Boolean> changeCloudColor;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public WWidget getWidget(GuiTheme lllllllllllllllllIlIlIlIlIIIIllI) {
        Ambience lllllllllllllllllIlIlIlIlIIIIlll;
        WHorizontalList lllllllllllllllllIlIlIlIlIIIIlIl = lllllllllllllllllIlIlIlIlIIIIllI.horizontalList();
        WButton lllllllllllllllllIlIlIlIlIIIIlII = lllllllllllllllllIlIlIlIlIIIIlIl.add(lllllllllllllllllIlIlIlIlIIIIllI.button("Reload World")).expandX().widget();
        lllllllllllllllllIlIlIlIlIIIIlII.action = () -> {
            Ambience lllllllllllllllllIlIlIlIIllllllI;
            if (lllllllllllllllllIlIlIlIIllllllI.mc.field_1769 != null) {
                lllllllllllllllllIlIlIlIIllllllI.mc.field_1769.method_3279();
            }
        };
        return lllllllllllllllllIlIlIlIlIIIIlIl;
    }

    @Override
    public void onActivate() {
        Ambience lllllllllllllllllIlIlIlIlIIlIIII;
        if (lllllllllllllllllIlIlIlIlIIlIIII.mc.field_1769 != null) {
            lllllllllllllllllIlIlIlIlIIlIIII.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onDeactivate() {
        Ambience lllllllllllllllllIlIlIlIlIIIllIl;
        if (lllllllllllllllllIlIlIlIlIIIllIl.mc.field_1769 != null) {
            lllllllllllllllllIlIlIlIlIIIllIl.mc.field_1769.method_3279();
        }
    }

    public Ambience() {
        super(Categories.World, "ambience", "Change the color of various pieces of the environment.");
        Ambience lllllllllllllllllIlIlIlIlIIlIIll;
        lllllllllllllllllIlIlIlIlIIlIIll.sgGeneral = lllllllllllllllllIlIlIlIlIIlIIll.settings.getDefaultGroup();
        lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic = lllllllllllllllllIlIlIlIlIIlIIll.settings.createGroup("Dynamic");
        lllllllllllllllllIlIlIlIlIIlIIll.sgStatic = lllllllllllllllllIlIlIlIlIIlIIll.settings.createGroup("Static");
        lllllllllllllllllIlIlIlIlIIlIIll.enderMode = lllllllllllllllllIlIlIlIlIIlIIll.sgGeneral.add(new BoolSetting.Builder().name("ender-mode").description("Makes the sky like the vast void of the End.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.enderCustomSkyColor = lllllllllllllllllIlIlIlIlIIlIIll.sgGeneral.add(new BoolSetting.Builder().name("ender-custom-color").description("Allows a custom sky color for Ender Mode.").defaultValue(false).visible(lllllllllllllllllIlIlIlIlIIlIIll.enderMode::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeSkyColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new BoolSetting.Builder().name("change-sky-color").description("Should the sky color be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.skyColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new ColorSetting.Builder().name("sky-color").description("The color to change the sky to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeSkyColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.endSkyColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new ColorSetting.Builder().name("end-sky-color").description("The color to change the End sky to.").defaultValue(new SettingColor(102, 0, 0, 255)).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeCloudColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new BoolSetting.Builder().name("change-cloud-color").description("Should the color of the clouds be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.cloudColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new ColorSetting.Builder().name("clouds-color").description("The color to change the clouds to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeCloudColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeLightningColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new BoolSetting.Builder().name("change-lightning-color").description("Should the color of lightning be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.lightningColor = lllllllllllllllllIlIlIlIlIIlIIll.sgDynamic.add(new ColorSetting.Builder().name("lightning-color").description("The color to change lightning to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeLightningColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeWaterColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new BoolSetting.Builder().name("change-water-color").description("Should the color of water be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.waterColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new ColorSetting.Builder().name("water-color").description("The color to change water to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeWaterColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeLavaColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new BoolSetting.Builder().name("change-lava-color").description("Should the color of lava be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.lavaColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new ColorSetting.Builder().name("lava-color").description("The color to change lava to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeLavaColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeFoliageColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new BoolSetting.Builder().name("change-foliage-color").description("Should the color of the foliage be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.foliageColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new ColorSetting.Builder().name("foliage-color").description("The color to change the foliage to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeFoliageColor::get).build());
        lllllllllllllllllIlIlIlIlIIlIIll.changeGrassColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new BoolSetting.Builder().name("change-grass-color").description("Should the color of grass be changed.").defaultValue(false).build());
        lllllllllllllllllIlIlIlIlIIlIIll.grassColor = lllllllllllllllllIlIlIlIlIIlIIll.sgStatic.add(new ColorSetting.Builder().name("grass-color").description("The color to change grass to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(lllllllllllllllllIlIlIlIlIIlIIll.changeGrassColor::get).build());
    }

    public static class Custom
    extends class_5294 {
        public Custom() {
            super(Float.NaN, true, class_5294.class_5401.field_25641, true, false);
            Custom lIIIIIIIlIllIII;
        }

        public boolean method_28110(int lIIIIIIIlIlIIIl, int lIIIIIIIlIlIIII) {
            return false;
        }

        public float[] method_28109(float lIIIIIIIlIIlllI, float lIIIIIIIlIIllIl) {
            return null;
        }

        public class_243 method_28112(class_243 lIIIIIIIlIlIIll, float lIIIIIIIlIlIlII) {
            return lIIIIIIIlIlIIll.method_1021((double)0.15f);
        }
    }
}

