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
    public final Setting<Boolean> changeLightningColor;
    public final Setting<Boolean> enderCustomSkyColor;
    public final Setting<SettingColor> endSkyColor;
    public final Setting<SettingColor> skyColor;
    public final Setting<Boolean> changeGrassColor;
    public final Setting<Boolean> changeLavaColor;
    public final Setting<SettingColor> cloudColor;
    public final Setting<SettingColor> grassColor;
    public final Setting<Boolean> changeWaterColor;
    private final SettingGroup sgStatic;
    public final Setting<SettingColor> foliageColor;
    private final SettingGroup sgDynamic;
    public final Setting<SettingColor> lightningColor;
    public final Setting<SettingColor> lavaColor;
    public final Setting<SettingColor> waterColor;
    public final Setting<Boolean> enderMode;
    public final Setting<Boolean> changeFoliageColor;
    public final Setting<Boolean> changeSkyColor;
    public final Setting<Boolean> changeCloudColor;
    private final SettingGroup sgGeneral;

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        WHorizontalList wHorizontalList = guiTheme.horizontalList();
        WButton wButton = wHorizontalList.add(guiTheme.button("Reload World")).expandX().widget();
        wButton.action = this::lambda$getWidget$0;
        return wHorizontalList;
    }

    @Override
    public void onActivate() {
        if (this.mc.field_1769 != null) {
            this.mc.field_1769.method_3279();
        }
    }

    private void lambda$getWidget$0() {
        if (this.mc.field_1769 != null) {
            this.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onDeactivate() {
        if (this.mc.field_1769 != null) {
            this.mc.field_1769.method_3279();
        }
    }

    public Ambience() {
        super(Categories.World, "ambience", "Change the color of various pieces of the environment.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgDynamic = this.settings.createGroup("Dynamic");
        this.sgStatic = this.settings.createGroup("Static");
        this.enderMode = this.sgGeneral.add(new BoolSetting.Builder().name("ender-mode").description("Makes the sky like the vast void of the End.").defaultValue(false).build());
        this.enderCustomSkyColor = this.sgGeneral.add(new BoolSetting.Builder().name("ender-custom-color").description("Allows a custom sky color for Ender Mode.").defaultValue(false).visible(this.enderMode::get).build());
        this.changeSkyColor = this.sgDynamic.add(new BoolSetting.Builder().name("change-sky-color").description("Should the sky color be changed.").defaultValue(false).build());
        this.skyColor = this.sgDynamic.add(new ColorSetting.Builder().name("sky-color").description("The color to change the sky to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeSkyColor::get).build());
        this.endSkyColor = this.sgDynamic.add(new ColorSetting.Builder().name("end-sky-color").description("The color to change the End sky to.").defaultValue(new SettingColor(102, 0, 0, 255)).build());
        this.changeCloudColor = this.sgDynamic.add(new BoolSetting.Builder().name("change-cloud-color").description("Should the color of the clouds be changed.").defaultValue(false).build());
        this.cloudColor = this.sgDynamic.add(new ColorSetting.Builder().name("clouds-color").description("The color to change the clouds to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeCloudColor::get).build());
        this.changeLightningColor = this.sgDynamic.add(new BoolSetting.Builder().name("change-lightning-color").description("Should the color of lightning be changed.").defaultValue(false).build());
        this.lightningColor = this.sgDynamic.add(new ColorSetting.Builder().name("lightning-color").description("The color to change lightning to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeLightningColor::get).build());
        this.changeWaterColor = this.sgStatic.add(new BoolSetting.Builder().name("change-water-color").description("Should the color of water be changed.").defaultValue(false).build());
        this.waterColor = this.sgStatic.add(new ColorSetting.Builder().name("water-color").description("The color to change water to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeWaterColor::get).build());
        this.changeLavaColor = this.sgStatic.add(new BoolSetting.Builder().name("change-lava-color").description("Should the color of lava be changed.").defaultValue(false).build());
        this.lavaColor = this.sgStatic.add(new ColorSetting.Builder().name("lava-color").description("The color to change lava to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeLavaColor::get).build());
        this.changeFoliageColor = this.sgStatic.add(new BoolSetting.Builder().name("change-foliage-color").description("Should the color of the foliage be changed.").defaultValue(false).build());
        this.foliageColor = this.sgStatic.add(new ColorSetting.Builder().name("foliage-color").description("The color to change the foliage to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeFoliageColor::get).build());
        this.changeGrassColor = this.sgStatic.add(new BoolSetting.Builder().name("change-grass-color").description("Should the color of grass be changed.").defaultValue(false).build());
        this.grassColor = this.sgStatic.add(new ColorSetting.Builder().name("grass-color").description("The color to change grass to.").defaultValue(new SettingColor(102, 0, 0, 255)).visible(this.changeGrassColor::get).build());
    }

    public static class Custom
    extends class_5294 {
        public Custom() {
            super(Float.NaN, true, class_5294.class_5401.field_25641, true, false);
        }

        public boolean method_28110(int n, int n2) {
            return false;
        }

        public float[] method_28109(float f, float f2) {
            return null;
        }

        public class_243 method_28112(class_243 class_2432, float f) {
            return class_2432.method_1021((double)0.15f);
        }
    }
}

