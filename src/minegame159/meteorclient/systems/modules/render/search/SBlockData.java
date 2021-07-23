/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.search;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.utils.IScreenFactory;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.settings.IBlockData;
import minegame159.meteorclient.systems.modules.render.search.SBlockDataScreen;
import minegame159.meteorclient.utils.misc.IChangeable;
import minegame159.meteorclient.utils.misc.ICopyable;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2248;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class SBlockData
implements ICopyable<SBlockData>,
ISerializable<SBlockData>,
IChangeable,
IBlockData<SBlockData>,
IScreenFactory {
    public SettingColor lineColor;
    public SettingColor sideColor;
    public ShapeMode shapeMode;
    private boolean changed;
    public SettingColor tracerColor;
    public boolean tracer;

    @Override
    public SBlockData set(SBlockData sBlockData) {
        this.shapeMode = sBlockData.shapeMode;
        this.lineColor.set(sBlockData.lineColor);
        this.sideColor.set(sBlockData.sideColor);
        this.tracer = sBlockData.tracer;
        this.tracerColor.set(sBlockData.tracerColor);
        this.changed = sBlockData.changed;
        return this;
    }

    public void tickRainbow() {
        this.lineColor.update();
        this.sideColor.update();
        this.tracerColor.update();
    }

    public void changed() {
        this.changed = true;
    }

    @Override
    public SBlockData copy() {
        return new SBlockData(this.shapeMode, new SettingColor(this.lineColor), new SettingColor(this.sideColor), this.tracer, new SettingColor(this.tracerColor));
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("shapeMode", this.shapeMode.name());
        class_24872.method_10566("lineColor", (class_2520)this.lineColor.toTag());
        class_24872.method_10566("sideColor", (class_2520)this.sideColor.toTag());
        class_24872.method_10556("tracer", this.tracer);
        class_24872.method_10566("tracerColor", (class_2520)this.tracerColor.toTag());
        class_24872.method_10556("changed", this.changed);
        return class_24872;
    }

    @Override
    public SBlockData fromTag(class_2487 class_24872) {
        this.shapeMode = ShapeMode.valueOf(class_24872.method_10558("shapeMode"));
        this.lineColor.fromTag(class_24872.method_10562("lineColor"));
        this.sideColor.fromTag(class_24872.method_10562("sideColor"));
        this.tracer = class_24872.method_10577("tracer");
        this.tracerColor.fromTag(class_24872.method_10562("tracerColor"));
        this.changed = class_24872.method_10577("changed");
        return this;
    }

    @Override
    public WidgetScreen createScreen(GuiTheme guiTheme, class_2248 class_22482, BlockDataSetting<SBlockData> blockDataSetting) {
        return new SBlockDataScreen(guiTheme, this, class_22482, blockDataSetting);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public SBlockData(ShapeMode shapeMode, SettingColor settingColor, SettingColor settingColor2, boolean bl, SettingColor settingColor3) {
        this.shapeMode = shapeMode;
        this.lineColor = settingColor;
        this.sideColor = settingColor2;
        this.tracer = bl;
        this.tracerColor = settingColor3;
    }

    @Override
    public WidgetScreen createScreen(GuiTheme guiTheme) {
        return new SBlockDataScreen(guiTheme, this, null, null);
    }

    @Override
    public ICopyable set(ICopyable iCopyable) {
        return this.set((SBlockData)iCopyable);
    }

    @Override
    public ICopyable copy() {
        return this.copy();
    }

    @Override
    public boolean isChanged() {
        return this.changed;
    }
}

