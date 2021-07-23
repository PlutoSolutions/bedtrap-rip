/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.search;

import java.util.Map;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.modules.render.search.SBlockData;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2248;

public class SBlockDataScreen
extends WindowScreen {
    private void lambda$new$5(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting blockDataSetting, SettingColor settingColor) {
        sBlockData.sideColor.set(settingColor);
        this.changed(sBlockData, class_22482, blockDataSetting);
    }

    private static void lambda$new$2(SBlockData sBlockData, Setting setting) {
        setting.set(sBlockData.lineColor);
    }

    private void lambda$new$7(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting blockDataSetting, Boolean bl) {
        sBlockData.tracer = bl;
        this.changed(sBlockData, class_22482, blockDataSetting);
    }

    private static void lambda$new$6(SBlockData sBlockData, Setting setting) {
        setting.set(sBlockData.tracer);
    }

    public SBlockDataScreen(GuiTheme guiTheme, SBlockData sBlockData, class_2248 class_22482, BlockDataSetting<SBlockData> blockDataSetting) {
        super(guiTheme, "Configure block");
        Settings settings = new Settings();
        SettingGroup settingGroup = settings.getDefaultGroup();
        SettingGroup settingGroup2 = settings.createGroup("Tracer");
        settingGroup.add(new EnumSetting.Builder().name("shape-mode").description("How the shape is rendered.").defaultValue(ShapeMode.Lines).onModuleActivated(arg_0 -> SBlockDataScreen.lambda$new$0(sBlockData, arg_0)).onChanged(arg_0 -> this.lambda$new$1(sBlockData, class_22482, blockDataSetting, arg_0)).build());
        settingGroup.add(new ColorSetting.Builder().name("line-color").description("Color of lines.").defaultValue(new SettingColor(0, 255, 200)).onModuleActivated(arg_0 -> SBlockDataScreen.lambda$new$2(sBlockData, arg_0)).onChanged(arg_0 -> this.lambda$new$3(sBlockData, class_22482, blockDataSetting, arg_0)).build());
        settingGroup.add(new ColorSetting.Builder().name("side-color").description("Color of sides.").defaultValue(new SettingColor(0, 255, 200, 25)).onModuleActivated(arg_0 -> SBlockDataScreen.lambda$new$4(sBlockData, arg_0)).onChanged(arg_0 -> this.lambda$new$5(sBlockData, class_22482, blockDataSetting, arg_0)).build());
        settingGroup2.add(new BoolSetting.Builder().name("tracer").description("If tracer line is allowed to this block.").defaultValue(true).onModuleActivated(arg_0 -> SBlockDataScreen.lambda$new$6(sBlockData, arg_0)).onChanged(arg_0 -> this.lambda$new$7(sBlockData, class_22482, blockDataSetting, arg_0)).build());
        settingGroup2.add(new ColorSetting.Builder().name("tracer-color").description("Color of tracer line.").defaultValue(new SettingColor(0, 255, 200, 125)).onModuleActivated(arg_0 -> SBlockDataScreen.lambda$new$8(sBlockData, arg_0)).onChanged(arg_0 -> this.lambda$new$9(sBlockData, class_22482, blockDataSetting, arg_0)).build());
        settings.onActivated();
        this.add(guiTheme.settings(settings)).expandX();
    }

    private void changed(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting<SBlockData> blockDataSetting) {
        if (!sBlockData.isChanged() && class_22482 != null && blockDataSetting != null) {
            ((Map)blockDataSetting.get()).put(class_22482, sBlockData);
            blockDataSetting.changed();
        }
        sBlockData.changed();
    }

    private void lambda$new$3(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting blockDataSetting, SettingColor settingColor) {
        sBlockData.lineColor.set(settingColor);
        this.changed(sBlockData, class_22482, blockDataSetting);
    }

    private void lambda$new$1(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting blockDataSetting, ShapeMode shapeMode) {
        sBlockData.shapeMode = shapeMode;
        this.changed(sBlockData, class_22482, blockDataSetting);
    }

    private static void lambda$new$4(SBlockData sBlockData, Setting setting) {
        setting.set(sBlockData.sideColor);
    }

    private void lambda$new$9(SBlockData sBlockData, class_2248 class_22482, BlockDataSetting blockDataSetting, SettingColor settingColor) {
        sBlockData.tracerColor = settingColor;
        this.changed(sBlockData, class_22482, blockDataSetting);
    }

    private static void lambda$new$8(SBlockData sBlockData, Setting setting) {
        setting.set(sBlockData.tracerColor);
    }

    private static void lambda$new$0(SBlockData sBlockData, Setting setting) {
        setting.set(sBlockData.shapeMode);
    }
}

