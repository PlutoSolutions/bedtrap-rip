/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2680
 *  net.minecraft.class_290
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.AbstractBlockAccessor;
import minegame159.meteorclient.rendering.DrawMode;
import minegame159.meteorclient.rendering.MeshBuilder;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockIterator;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2680;
import net.minecraft.class_290;

public class HoleESP
extends Module {
    private final /* synthetic */ MeshBuilder SIDES;
    private final /* synthetic */ Setting<SettingColor> bedrockColorTop;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Boolean> bottomQuad;
    private final /* synthetic */ Setting<Boolean> webs;
    private final /* synthetic */ Setting<SettingColor> obsidianColorBottom;
    private final /* synthetic */ Setting<SettingColor> mixedColorTop;
    private final /* synthetic */ Setting<Boolean> doubles;
    private final /* synthetic */ byte NULL = 0;
    private final /* synthetic */ List<Hole> holes;
    private final /* synthetic */ Setting<Boolean> ignoreOwn;
    private final /* synthetic */ Pool<Hole> holePool;
    private final /* synthetic */ Setting<SettingColor> bedrockColorBottom;
    private final /* synthetic */ Setting<Integer> holeHeight;
    private final /* synthetic */ MeshBuilder LINES;
    private final /* synthetic */ Setting<Boolean> topQuad;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> mixedColorBottom;
    private final /* synthetic */ Setting<SettingColor> obsidianColorTop;
    private final /* synthetic */ Setting<Integer> horizontalRadius;
    private final /* synthetic */ Setting<Double> height;
    private final /* synthetic */ Setting<Integer> verticalRadius;

    @EventHandler
    private void onTick(TickEvent.Pre lllIllIIlIlllII) {
        HoleESP lllIllIIlIllIll;
        for (Hole lllIllIIlIllllI : lllIllIIlIllIll.holes) {
            lllIllIIlIllIll.holePool.free(lllIllIIlIllllI);
        }
        lllIllIIlIllIll.holes.clear();
        BlockIterator.register(lllIllIIlIllIll.horizontalRadius.get(), lllIllIIlIllIll.verticalRadius.get(), (lllIllIIIlIlIlI, lllIllIIIlIllll) -> {
            HoleESP lllIllIIIlIlIll;
            if (!lllIllIIIlIlIll.validHole((class_2338)lllIllIIIlIlIlI)) {
                return;
            }
            int lllIllIIIlIlllI = 0;
            int lllIllIIIlIllIl = 0;
            class_2350 lllIllIIIlIllII = null;
            for (class_2350 lllIllIIIllIIlI : class_2350.values()) {
                if (lllIllIIIllIIlI == class_2350.field_11036) continue;
                class_2680 lllIllIIIllIIll = lllIllIIIlIlIll.mc.field_1687.method_8320(lllIllIIIlIlIlI.method_10093(lllIllIIIllIIlI));
                if (lllIllIIIllIIll.method_26204() == class_2246.field_9987) {
                    ++lllIllIIIlIlllI;
                    continue;
                }
                if (lllIllIIIllIIll.method_26204() == class_2246.field_10540) {
                    ++lllIllIIIlIllIl;
                    continue;
                }
                if (lllIllIIIllIIlI == class_2350.field_11033) {
                    return;
                }
                if (!lllIllIIIlIlIll.validHole(lllIllIIIlIlIlI.method_10093(lllIllIIIllIIlI)) || lllIllIIIlIllII != null) continue;
                for (class_2350 lllIllIIIllIlII : class_2350.values()) {
                    if (lllIllIIIllIlII == lllIllIIIllIIlI.method_10153() || lllIllIIIllIlII == class_2350.field_11036) continue;
                    class_2680 lllIllIIIllIlIl = lllIllIIIlIlIll.mc.field_1687.method_8320(lllIllIIIlIlIlI.method_10093(lllIllIIIllIIlI).method_10093(lllIllIIIllIlII));
                    if (lllIllIIIllIlIl.method_26204() == class_2246.field_9987) {
                        ++lllIllIIIlIlllI;
                        continue;
                    }
                    if (lllIllIIIllIlIl.method_26204() == class_2246.field_10540) {
                        ++lllIllIIIlIllIl;
                        continue;
                    }
                    return;
                }
                lllIllIIIlIllII = lllIllIIIllIIlI;
            }
            if (lllIllIIIlIllIl + lllIllIIIlIlllI == 5 && lllIllIIIlIllII == null) {
                lllIllIIIlIlIll.holes.add(lllIllIIIlIlIll.holePool.get().set((class_2338)lllIllIIIlIlIlI, lllIllIIIlIllIl == 5 ? Hole.Type.Obsidian : (lllIllIIIlIlllI == 5 ? Hole.Type.Bedrock : Hole.Type.Mixed), (byte)0));
            } else if (lllIllIIIlIllIl + lllIllIIIlIlllI == 8 && lllIllIIIlIlIll.doubles.get().booleanValue() && lllIllIIIlIllII != null) {
                lllIllIIIlIlIll.holes.add(lllIllIIIlIlIll.holePool.get().set((class_2338)lllIllIIIlIlIlI, lllIllIIIlIllIl == 8 ? Hole.Type.Obsidian : (lllIllIIIlIlllI == 8 ? Hole.Type.Bedrock : Hole.Type.Mixed), Dir.get(lllIllIIIlIllII)));
            }
        });
    }

    @EventHandler
    private void onRender(RenderEvent lllIllIIlIIlIIl) {
        HoleESP lllIllIIlIIlIlI;
        lllIllIIlIIlIlI.LINES.begin(lllIllIIlIIlIIl, DrawMode.Lines, class_290.field_1576);
        lllIllIIlIIlIlI.SIDES.begin(lllIllIIlIIlIIl, DrawMode.Triangles, class_290.field_1576);
        for (Hole lllIllIIlIIlIll : lllIllIIlIIlIlI.holes) {
            lllIllIIlIIlIll.render(lllIllIIlIIlIlI.LINES, lllIllIIlIIlIlI.SIDES, lllIllIIlIIlIlI.shapeMode.get(), lllIllIIlIIlIlI.height.get(), lllIllIIlIIlIlI.topQuad.get(), lllIllIIlIIlIlI.bottomQuad.get());
        }
        lllIllIIlIIlIlI.LINES.end();
        lllIllIIlIIlIlI.SIDES.end();
    }

    public HoleESP() {
        super(Categories.Render, "hole-esp", "Displays holes that you will take less damage in.");
        HoleESP lllIllIIllIIIll;
        lllIllIIllIIIll.sgGeneral = lllIllIIllIIIll.settings.getDefaultGroup();
        lllIllIIllIIIll.sgRender = lllIllIIllIIIll.settings.createGroup("Render");
        lllIllIIllIIIll.horizontalRadius = lllIllIIllIIIll.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(10).min(0).sliderMax(32).build());
        lllIllIIllIIIll.verticalRadius = lllIllIIllIIIll.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for holes.").defaultValue(5).min(0).sliderMax(32).build());
        lllIllIIllIIIll.holeHeight = lllIllIIllIIIll.sgGeneral.add(new IntSetting.Builder().name("min-height").description("Minimum hole height required to be rendered.").defaultValue(3).min(1).build());
        lllIllIIllIIIll.doubles = lllIllIIllIIIll.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Highlights double holes that can be stood across.").defaultValue(true).build());
        lllIllIIllIIIll.ignoreOwn = lllIllIIllIIIll.sgGeneral.add(new BoolSetting.Builder().name("ignore-own").description("Ignores rendering the hole you are currently standing in.").defaultValue(false).build());
        lllIllIIllIIIll.webs = lllIllIIllIIIll.sgGeneral.add(new BoolSetting.Builder().name("webs").description("Whether to show holes that have webs inside of them.").defaultValue(false).build());
        lllIllIIllIIIll.shapeMode = lllIllIIllIIIll.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllIllIIllIIIll.height = lllIllIIllIIIll.sgRender.add(new DoubleSetting.Builder().name("height").description("The height of rendering.").defaultValue(0.2).min(0.0).build());
        lllIllIIllIIIll.topQuad = lllIllIIllIIIll.sgRender.add(new BoolSetting.Builder().name("top-quad").description("Whether to render a quad at the top of the hole.").defaultValue(true).build());
        lllIllIIllIIIll.bottomQuad = lllIllIIllIIIll.sgRender.add(new BoolSetting.Builder().name("bottom-quad").description("Whether to render a quad at the bottom of the hole.").defaultValue(false).build());
        lllIllIIllIIIll.bedrockColorTop = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("bedrock-top").description("The top color for holes that are completely bedrock.").defaultValue(new SettingColor(100, 255, 0, 200)).build());
        lllIllIIllIIIll.bedrockColorBottom = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("bedrock-bottom").description("The bottom color for holes that are completely bedrock.").defaultValue(new SettingColor(100, 255, 0, 0)).build());
        lllIllIIllIIIll.obsidianColorTop = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("obsidian-top").description("The top color for holes that are completely obsidian.").defaultValue(new SettingColor(255, 0, 0, 200)).build());
        lllIllIIllIIIll.obsidianColorBottom = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("obsidian-bottom").description("The bottom color for holes that are completely obsidian.").defaultValue(new SettingColor(255, 0, 0, 0)).build());
        lllIllIIllIIIll.mixedColorTop = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("mixed-top").description("The top color for holes that have mixed bedrock and obsidian.").defaultValue(new SettingColor(255, 127, 0, 200)).build());
        lllIllIIllIIIll.mixedColorBottom = lllIllIIllIIIll.sgRender.add(new ColorSetting.Builder().name("mixed-bottom").description("The bottom color for holes that have mixed bedrock and obsidian.").defaultValue(new SettingColor(255, 127, 0, 0)).build());
        lllIllIIllIIIll.LINES = new MeshBuilder(16384);
        lllIllIIllIIIll.SIDES = new MeshBuilder(16384);
        lllIllIIllIIIll.holePool = new Pool<Hole>(() -> new Hole());
        lllIllIIllIIIll.holes = new ArrayList<Hole>();
        lllIllIIllIIIll.NULL = 0;
    }

    private boolean validHole(class_2338 lllIllIIlIlIIll) {
        HoleESP lllIllIIlIlIIlI;
        if (lllIllIIlIlIIlI.ignoreOwn.get().booleanValue() && lllIllIIlIlIIlI.mc.field_1724.method_24515().equals((Object)lllIllIIlIlIIll)) {
            return false;
        }
        if (!lllIllIIlIlIIlI.webs.get().booleanValue() && lllIllIIlIlIIlI.mc.field_1687.method_8320(lllIllIIlIlIIll).method_26204().method_27839(class_2246.field_10343)) {
            return false;
        }
        if (((AbstractBlockAccessor)lllIllIIlIlIIlI.mc.field_1687.method_8320(lllIllIIlIlIIll).method_26204()).isCollidable()) {
            return false;
        }
        for (int lllIllIIlIlIlIl = 0; lllIllIIlIlIlIl < lllIllIIlIlIIlI.holeHeight.get(); ++lllIllIIlIlIlIl) {
            if (!((AbstractBlockAccessor)lllIllIIlIlIIlI.mc.field_1687.method_8320(lllIllIIlIlIIll.method_10086(lllIllIIlIlIlIl)).method_26204()).isCollidable()) continue;
            return false;
        }
        return true;
    }

    private static class Hole {
        public /* synthetic */ byte exclude;
        public /* synthetic */ class_2338.class_2339 blockPos;
        public /* synthetic */ Type type;

        public Color getBottomColor() {
            Hole lllllIIlIIlIlIl;
            switch (lllllIIlIIlIlIl.type) {
                case Obsidian: {
                    return (Color)Modules.get().get(HoleESP.class).obsidianColorBottom.get();
                }
                case Bedrock: {
                    return (Color)Modules.get().get(HoleESP.class).bedrockColorBottom.get();
                }
            }
            return (Color)Modules.get().get(HoleESP.class).mixedColorBottom.get();
        }

        public Color getTopColor() {
            Hole lllllIIlIIllIII;
            switch (lllllIIlIIllIII.type) {
                case Obsidian: {
                    return (Color)Modules.get().get(HoleESP.class).obsidianColorTop.get();
                }
                case Bedrock: {
                    return (Color)Modules.get().get(HoleESP.class).bedrockColorTop.get();
                }
            }
            return (Color)Modules.get().get(HoleESP.class).mixedColorTop.get();
        }

        private Hole() {
            Hole lllllIIlIlIlIII;
            lllllIIlIlIlIII.blockPos = new class_2338.class_2339();
        }

        public void render(MeshBuilder lllllIIIlllIlll, MeshBuilder lllllIIIlllIllI, ShapeMode lllllIIIlllIlIl, double lllllIIIlllIlII, boolean lllllIIIlllIIll, boolean lllllIIIlllIIlI) {
            Hole lllllIIlIIIIllI;
            int lllllIIIlllllll = lllllIIlIIIIllI.blockPos.method_10263();
            int lllllIIIllllllI = lllllIIlIIIIllI.blockPos.method_10264();
            int lllllIIIlllllIl = lllllIIlIIIIllI.blockPos.method_10260();
            Color lllllIIIlllllII = lllllIIlIIIIllI.getTopColor();
            Color lllllIIIllllIll = lllllIIlIIIIllI.getBottomColor();
            int lllllIIIllllIlI = lllllIIIlllllII.a;
            int lllllIIIllllIIl = lllllIIIllllIll.a;
            if (lllllIIIlllIlIl != ShapeMode.Lines) {
                lllllIIIlllllII.a = lllllIIIllllIlI / 2;
                lllllIIIllllIll.a = lllllIIIllllIIl / 2;
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)2) && lllllIIIlllIIll) {
                    lllllIIIlllIllI.quad(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)4) && lllllIIIlllIIlI) {
                    lllllIIIlllIllI.quad(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)8)) {
                    lllllIIIlllIllI.verticalGradientQuad(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllII, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)16)) {
                    lllllIIIlllIllI.verticalGradientQuad(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllII, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)32)) {
                    lllllIIIlllIllI.verticalGradientQuad(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllII, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)64)) {
                    lllllIIIlllIllI.verticalGradientQuad(lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllII, lllllIIIllllIll);
                }
                lllllIIIlllllII.a = lllllIIIllllIlI;
                lllllIIIllllIll.a = lllllIIIllllIIl;
            }
            if (lllllIIIlllIlIl != ShapeMode.Sides) {
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)32) && Dir.is(lllllIIlIIIIllI.exclude, (byte)8)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIllllIll, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)32) && Dir.is(lllllIIlIIIIllI.exclude, (byte)16)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIllllIll, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)64) && Dir.is(lllllIIlIIIIllI.exclude, (byte)8)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIllllIll, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)64) && Dir.is(lllllIIlIIIIllI.exclude, (byte)16)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIllllIll, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)8)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)8)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)16)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)16)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)32)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)32)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllII);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)64)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl, lllllIIIlllllll + 1, lllllIIIllllllI, lllllIIIlllllIl + 1, lllllIIIllllIll);
                }
                if (Dir.is(lllllIIlIIIIllI.exclude, (byte)64)) {
                    lllllIIIlllIlll.line(lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl, lllllIIIlllllll + 1, (double)lllllIIIllllllI + lllllIIIlllIlII, lllllIIIlllllIl + 1, lllllIIIlllllII);
                }
            }
        }

        public Hole set(class_2338 lllllIIlIIlllIl, Type lllllIIlIlIIIII, byte lllllIIlIIllIll) {
            Hole lllllIIlIIllllI;
            lllllIIlIIllllI.blockPos.method_10101((class_2382)lllllIIlIIlllIl);
            lllllIIlIIllllI.exclude = lllllIIlIIllIll;
            lllllIIlIIllllI.type = lllllIIlIlIIIII;
            return lllllIIlIIllllI;
        }

        public static enum Type {
            Bedrock,
            Obsidian,
            Mixed;


            private Type() {
                Type lllllllllllllllllIlIllIIllIIIlll;
            }
        }
    }
}

