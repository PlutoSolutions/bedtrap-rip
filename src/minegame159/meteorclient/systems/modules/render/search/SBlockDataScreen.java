/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
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
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.modules.render.search.SBlockData;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2248;

public class SBlockDataScreen
extends WindowScreen {
    public SBlockDataScreen(GuiTheme lllIlIlIIIIIIlI, SBlockData lllIlIlIIIIlIIl, class_2248 lllIlIlIIIIIIII, BlockDataSetting<SBlockData> lllIlIIllllllll) {
        super(lllIlIlIIIIIIlI, "Configure block");
        SBlockDataScreen lllIlIlIIIIIIll;
        Settings lllIlIlIIIIIllI = new Settings();
        SettingGroup lllIlIlIIIIIlIl = lllIlIlIIIIIllI.getDefaultGroup();
        SettingGroup lllIlIlIIIIIlII = lllIlIlIIIIIllI.createGroup("Tracer");
        lllIlIlIIIIIlIl.add(new EnumSetting.Builder().name("shape-mode").description("How the shape is rendered.").defaultValue(ShapeMode.Lines).onModuleActivated(lllIlIIlIIIlIIl -> lllIlIIlIIIlIIl.set(lllIlIIlIIIlIlI.shapeMode)).onChanged(lllIlIIlIIlIlII -> {
            SBlockDataScreen lllIlIIlIIllIII;
            lllIlIIlIIlIlll.shapeMode = lllIlIIlIIlIlII;
            lllIlIIlIIllIII.changed(lllIlIlIIIIlIIl, lllIlIlIIIIIIII, lllIlIIllllllll);
        }).build());
        lllIlIlIIIIIlIl.add(new ColorSetting.Builder().name("line-color").description("Color of lines.").defaultValue(new SettingColor(0, 255, 200)).onModuleActivated(lllIlIIlIIllllI -> lllIlIIlIIllllI.set(lllIlIIlIIlllll.lineColor)).onChanged(lllIlIIlIlIIlII -> {
            SBlockDataScreen lllIlIIlIlIllIl;
            lllIlIIlIlIIlll.lineColor.set((Color)lllIlIIlIlIIlII);
            lllIlIIlIlIllIl.changed(lllIlIlIIIIlIIl, lllIlIlIIIIIIII, lllIlIIllllllll);
        }).build());
        lllIlIlIIIIIlIl.add(new ColorSetting.Builder().name("side-color").description("Color of sides.").defaultValue(new SettingColor(0, 255, 200, 25)).onModuleActivated(lllIlIIlIllIlIl -> lllIlIIlIllIlIl.set(lllIlIIlIllIllI.sideColor)).onChanged(lllIlIIlIlllllI -> {
            SBlockDataScreen lllIlIIlIllllIl;
            lllIlIIllIIIIIl.sideColor.set((Color)lllIlIIlIlllllI);
            lllIlIIlIllllIl.changed(lllIlIlIIIIlIIl, lllIlIlIIIIIIII, lllIlIIllllllll);
        }).build());
        lllIlIlIIIIIlII.add(new BoolSetting.Builder().name("tracer").description("If tracer line is allowed to this block.").defaultValue(true).onModuleActivated(lllIlIIllIIlIlI -> lllIlIIllIIlIlI.set(lllIlIIllIIlIll.tracer)).onChanged(lllIlIIllIlIIll -> {
            SBlockDataScreen lllIlIIllIlIlll;
            lllIlIIllIlIIIl.tracer = lllIlIIllIlIIll;
            lllIlIIllIlIlll.changed(lllIlIlIIIIlIIl, lllIlIlIIIIIIII, lllIlIIllllllll);
        }).build());
        lllIlIlIIIIIlII.add(new ColorSetting.Builder().name("tracer-color").description("Color of tracer line.").defaultValue(new SettingColor(0, 255, 200, 125)).onModuleActivated(lllIlIIllIlllll -> lllIlIIllIlllll.set(lllIlIIlllIIIII.tracerColor)).onChanged(lllIlIIlllIIIll -> {
            SBlockDataScreen lllIlIIlllIIlll;
            lllIlIIlllIlIll.tracerColor = lllIlIIlllIIIll;
            lllIlIIlllIIlll.changed(lllIlIlIIIIlIIl, lllIlIlIIIIIIII, lllIlIIllllllll);
        }).build());
        lllIlIlIIIIIllI.onActivated();
        lllIlIlIIIIIIll.add(lllIlIlIIIIIIlI.settings(lllIlIlIIIIIllI)).expandX();
    }

    private void changed(SBlockData lllIlIIllllIlII, class_2248 lllIlIIllllIIll, BlockDataSetting<SBlockData> lllIlIIllllIlIl) {
        if (!lllIlIIllllIlII.isChanged() && lllIlIIllllIIll != null && lllIlIIllllIlIl != null) {
            ((Map)lllIlIIllllIlIl.get()).put(lllIlIIllllIIll, lllIlIIllllIlII);
            lllIlIIllllIlIl.changed();
        }
        lllIlIIllllIlII.changed();
    }
}

