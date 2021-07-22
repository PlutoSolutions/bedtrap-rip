/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
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

public class SBlockData
implements ICopyable<SBlockData>,
ISerializable<SBlockData>,
IChangeable,
IBlockData<SBlockData>,
IScreenFactory {
    public /* synthetic */ SettingColor lineColor;
    public /* synthetic */ SettingColor sideColor;
    public /* synthetic */ ShapeMode shapeMode;
    private /* synthetic */ boolean changed;
    public /* synthetic */ SettingColor tracerColor;
    public /* synthetic */ boolean tracer;

    @Override
    public SBlockData set(SBlockData lllllllllllllllllllIllllIlIIIlIl) {
        SBlockData lllllllllllllllllllIllllIlIIlIII;
        lllllllllllllllllllIllllIlIIlIII.shapeMode = lllllllllllllllllllIllllIlIIIlIl.shapeMode;
        lllllllllllllllllllIllllIlIIlIII.lineColor.set(lllllllllllllllllllIllllIlIIIlIl.lineColor);
        lllllllllllllllllllIllllIlIIlIII.sideColor.set(lllllllllllllllllllIllllIlIIIlIl.sideColor);
        lllllllllllllllllllIllllIlIIlIII.tracer = lllllllllllllllllllIllllIlIIIlIl.tracer;
        lllllllllllllllllllIllllIlIIlIII.tracerColor.set(lllllllllllllllllllIllllIlIIIlIl.tracerColor);
        lllllllllllllllllllIllllIlIIlIII.changed = lllllllllllllllllllIllllIlIIIlIl.changed;
        return lllllllllllllllllllIllllIlIIlIII;
    }

    public void tickRainbow() {
        SBlockData lllllllllllllllllllIllllIlIIllII;
        lllllllllllllllllllIllllIlIIllII.lineColor.update();
        lllllllllllllllllllIllllIlIIllII.sideColor.update();
        lllllllllllllllllllIllllIlIIllII.tracerColor.update();
    }

    public void changed() {
        lllllllllllllllllllIllllIlIIllll.changed = true;
    }

    @Override
    public SBlockData copy() {
        SBlockData lllllllllllllllllllIllllIlIIIIll;
        return new SBlockData(lllllllllllllllllllIllllIlIIIIll.shapeMode, new SettingColor(lllllllllllllllllllIllllIlIIIIll.lineColor), new SettingColor(lllllllllllllllllllIllllIlIIIIll.sideColor), lllllllllllllllllllIllllIlIIIIll.tracer, new SettingColor(lllllllllllllllllllIllllIlIIIIll.tracerColor));
    }

    @Override
    public class_2487 toTag() {
        SBlockData lllllllllllllllllllIllllIIllllll;
        class_2487 lllllllllllllllllllIllllIIlllllI = new class_2487();
        lllllllllllllllllllIllllIIlllllI.method_10582("shapeMode", lllllllllllllllllllIllllIIllllll.shapeMode.name());
        lllllllllllllllllllIllllIIlllllI.method_10566("lineColor", (class_2520)lllllllllllllllllllIllllIIllllll.lineColor.toTag());
        lllllllllllllllllllIllllIIlllllI.method_10566("sideColor", (class_2520)lllllllllllllllllllIllllIIllllll.sideColor.toTag());
        lllllllllllllllllllIllllIIlllllI.method_10556("tracer", lllllllllllllllllllIllllIIllllll.tracer);
        lllllllllllllllllllIllllIIlllllI.method_10566("tracerColor", (class_2520)lllllllllllllllllllIllllIIllllll.tracerColor.toTag());
        lllllllllllllllllllIllllIIlllllI.method_10556("changed", lllllllllllllllllllIllllIIllllll.changed);
        return lllllllllllllllllllIllllIIlllllI;
    }

    @Override
    public SBlockData fromTag(class_2487 lllllllllllllllllllIllllIIlllIII) {
        SBlockData lllllllllllllllllllIllllIIllIlll;
        lllllllllllllllllllIllllIIllIlll.shapeMode = ShapeMode.valueOf(lllllllllllllllllllIllllIIlllIII.method_10558("shapeMode"));
        lllllllllllllllllllIllllIIllIlll.lineColor.fromTag(lllllllllllllllllllIllllIIlllIII.method_10562("lineColor"));
        lllllllllllllllllllIllllIIllIlll.sideColor.fromTag(lllllllllllllllllllIllllIIlllIII.method_10562("sideColor"));
        lllllllllllllllllllIllllIIllIlll.tracer = lllllllllllllllllllIllllIIlllIII.method_10577("tracer");
        lllllllllllllllllllIllllIIllIlll.tracerColor.fromTag(lllllllllllllllllllIllllIIlllIII.method_10562("tracerColor"));
        lllllllllllllllllllIllllIIllIlll.changed = lllllllllllllllllllIllllIIlllIII.method_10577("changed");
        return lllllllllllllllllllIllllIIllIlll;
    }

    @Override
    public WidgetScreen createScreen(GuiTheme lllllllllllllllllllIllllIllIIIII, class_2248 lllllllllllllllllllIllllIlIlllll, BlockDataSetting<SBlockData> lllllllllllllllllllIllllIlIllllI) {
        SBlockData lllllllllllllllllllIllllIlIlllIl;
        return new SBlockDataScreen(lllllllllllllllllllIllllIllIIIII, lllllllllllllllllllIllllIlIlllIl, lllllllllllllllllllIllllIlIlllll, lllllllllllllllllllIllllIlIllllI);
    }

    public SBlockData(ShapeMode lllllllllllllllllllIllllIlllIIII, SettingColor lllllllllllllllllllIllllIllIlIIl, SettingColor lllllllllllllllllllIllllIllIlIII, boolean lllllllllllllllllllIllllIllIIlll, SettingColor lllllllllllllllllllIllllIllIllII) {
        SBlockData lllllllllllllllllllIllllIllIlIll;
        lllllllllllllllllllIllllIllIlIll.shapeMode = lllllllllllllllllllIllllIlllIIII;
        lllllllllllllllllllIllllIllIlIll.lineColor = lllllllllllllllllllIllllIllIlIIl;
        lllllllllllllllllllIllllIllIlIll.sideColor = lllllllllllllllllllIllllIllIlIII;
        lllllllllllllllllllIllllIllIlIll.tracer = lllllllllllllllllllIllllIllIIlll;
        lllllllllllllllllllIllllIllIlIll.tracerColor = lllllllllllllllllllIllllIllIllII;
    }

    @Override
    public WidgetScreen createScreen(GuiTheme lllllllllllllllllllIllllIlIlIlII) {
        SBlockData lllllllllllllllllllIllllIlIlIlll;
        return new SBlockDataScreen(lllllllllllllllllllIllllIlIlIlII, lllllllllllllllllllIllllIlIlIlll, null, null);
    }

    @Override
    public boolean isChanged() {
        SBlockData lllllllllllllllllllIllllIlIlIIlI;
        return lllllllllllllllllllIllllIlIlIIlI.changed;
    }
}

