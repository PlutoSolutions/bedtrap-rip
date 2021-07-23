/*
 * Decompiled with CFR 0.151.
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
    private final MeshBuilder SIDES;
    private final Setting<SettingColor> bedrockColorTop;
    private final SettingGroup sgRender;
    private final Setting<Boolean> bottomQuad;
    private final Setting<Boolean> webs;
    private final Setting<SettingColor> obsidianColorBottom;
    private final Setting<SettingColor> mixedColorTop;
    private final Setting<Boolean> doubles;
    private final byte NULL = 0;
    private final List<Hole> holes;
    private final Setting<Boolean> ignoreOwn;
    private final Pool<Hole> holePool;
    private final Setting<SettingColor> bedrockColorBottom;
    private final Setting<Integer> holeHeight;
    private final MeshBuilder LINES;
    private final Setting<Boolean> topQuad;
    private final SettingGroup sgGeneral;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> mixedColorBottom;
    private final Setting<SettingColor> obsidianColorTop;
    private final Setting<Integer> horizontalRadius;
    private final Setting<Double> height;
    private final Setting<Integer> verticalRadius;

    static Setting access$300(HoleESP holeESP) {
        return holeESP.obsidianColorBottom;
    }

    static Setting access$500(HoleESP holeESP) {
        return holeESP.mixedColorBottom;
    }

    static Setting access$200(HoleESP holeESP) {
        return holeESP.mixedColorTop;
    }

    private static Hole lambda$new$0() {
        return new Hole(null);
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        for (Hole hole : this.holes) {
            this.holePool.free(hole);
        }
        this.holes.clear();
        BlockIterator.register(this.horizontalRadius.get(), this.verticalRadius.get(), this::lambda$onTick$1);
    }

    private void lambda$onTick$1(class_2338 class_23382, class_2680 class_26802) {
        if (!this.validHole(class_23382)) {
            return;
        }
        int n = 0;
        int n2 = 0;
        class_2350 class_23502 = null;
        for (class_2350 class_23503 : class_2350.values()) {
            if (class_23503 == class_2350.field_11036) continue;
            class_2680 class_26803 = this.mc.field_1687.method_8320(class_23382.method_10093(class_23503));
            if (class_26803.method_26204() == class_2246.field_9987) {
                ++n;
                continue;
            }
            if (class_26803.method_26204() == class_2246.field_10540) {
                ++n2;
                continue;
            }
            if (class_23503 == class_2350.field_11033) {
                return;
            }
            if (!this.validHole(class_23382.method_10093(class_23503)) || class_23502 != null) continue;
            for (class_2350 class_23504 : class_2350.values()) {
                if (class_23504 == class_23503.method_10153() || class_23504 == class_2350.field_11036) continue;
                class_2680 class_26804 = this.mc.field_1687.method_8320(class_23382.method_10093(class_23503).method_10093(class_23504));
                if (class_26804.method_26204() == class_2246.field_9987) {
                    ++n;
                    continue;
                }
                if (class_26804.method_26204() == class_2246.field_10540) {
                    ++n2;
                    continue;
                }
                return;
            }
            class_23502 = class_23503;
            if (0 >= 0) continue;
            return;
        }
        if (n2 + n == 5 && class_23502 == null) {
            this.holes.add(this.holePool.get().set(class_23382, n2 == 5 ? Hole.Type.Obsidian : (n == 5 ? Hole.Type.Bedrock : Hole.Type.Mixed), (byte)0));
        } else if (n2 + n == 8 && this.doubles.get().booleanValue() && class_23502 != null) {
            this.holes.add(this.holePool.get().set(class_23382, n2 == 8 ? Hole.Type.Obsidian : (n == 8 ? Hole.Type.Bedrock : Hole.Type.Mixed), Dir.get(class_23502)));
        }
    }

    static Setting access$400(HoleESP holeESP) {
        return holeESP.bedrockColorBottom;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.LINES.begin(renderEvent, DrawMode.Lines, class_290.field_1576);
        this.SIDES.begin(renderEvent, DrawMode.Triangles, class_290.field_1576);
        for (Hole hole : this.holes) {
            hole.render(this.LINES, this.SIDES, this.shapeMode.get(), this.height.get(), this.topQuad.get(), this.bottomQuad.get());
        }
        this.LINES.end();
        this.SIDES.end();
    }

    static Setting access$100(HoleESP holeESP) {
        return holeESP.bedrockColorTop;
    }

    public HoleESP() {
        super(Categories.Render, "hole-esp", "Displays holes that you will take less damage in.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.horizontalRadius = this.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(10).min(0).sliderMax(32).build());
        this.verticalRadius = this.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for holes.").defaultValue(5).min(0).sliderMax(32).build());
        this.holeHeight = this.sgGeneral.add(new IntSetting.Builder().name("min-height").description("Minimum hole height required to be rendered.").defaultValue(3).min(1).build());
        this.doubles = this.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Highlights double holes that can be stood across.").defaultValue(true).build());
        this.ignoreOwn = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-own").description("Ignores rendering the hole you are currently standing in.").defaultValue(false).build());
        this.webs = this.sgGeneral.add(new BoolSetting.Builder().name("webs").description("Whether to show holes that have webs inside of them.").defaultValue(false).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.height = this.sgRender.add(new DoubleSetting.Builder().name("height").description("The height of rendering.").defaultValue(0.2).min(0.0).build());
        this.topQuad = this.sgRender.add(new BoolSetting.Builder().name("top-quad").description("Whether to render a quad at the top of the hole.").defaultValue(true).build());
        this.bottomQuad = this.sgRender.add(new BoolSetting.Builder().name("bottom-quad").description("Whether to render a quad at the bottom of the hole.").defaultValue(false).build());
        this.bedrockColorTop = this.sgRender.add(new ColorSetting.Builder().name("bedrock-top").description("The top color for holes that are completely bedrock.").defaultValue(new SettingColor(100, 255, 0, 200)).build());
        this.bedrockColorBottom = this.sgRender.add(new ColorSetting.Builder().name("bedrock-bottom").description("The bottom color for holes that are completely bedrock.").defaultValue(new SettingColor(100, 255, 0, 0)).build());
        this.obsidianColorTop = this.sgRender.add(new ColorSetting.Builder().name("obsidian-top").description("The top color for holes that are completely obsidian.").defaultValue(new SettingColor(255, 0, 0, 200)).build());
        this.obsidianColorBottom = this.sgRender.add(new ColorSetting.Builder().name("obsidian-bottom").description("The bottom color for holes that are completely obsidian.").defaultValue(new SettingColor(255, 0, 0, 0)).build());
        this.mixedColorTop = this.sgRender.add(new ColorSetting.Builder().name("mixed-top").description("The top color for holes that have mixed bedrock and obsidian.").defaultValue(new SettingColor(255, 127, 0, 200)).build());
        this.mixedColorBottom = this.sgRender.add(new ColorSetting.Builder().name("mixed-bottom").description("The bottom color for holes that have mixed bedrock and obsidian.").defaultValue(new SettingColor(255, 127, 0, 0)).build());
        this.LINES = new MeshBuilder(16384);
        this.SIDES = new MeshBuilder(16384);
        this.holePool = new Pool<Hole>(HoleESP::lambda$new$0);
        this.holes = new ArrayList<Hole>();
        this.NULL = 0;
    }

    static Setting access$000(HoleESP holeESP) {
        return holeESP.obsidianColorTop;
    }

    private boolean validHole(class_2338 class_23382) {
        if (this.ignoreOwn.get().booleanValue() && this.mc.field_1724.method_24515().equals((Object)class_23382)) {
            return false;
        }
        if (!this.webs.get().booleanValue() && this.mc.field_1687.method_8320(class_23382).method_26204().method_27839(class_2246.field_10343)) {
            return false;
        }
        if (((AbstractBlockAccessor)this.mc.field_1687.method_8320(class_23382).method_26204()).isCollidable()) {
            return false;
        }
        for (int i = 0; i < this.holeHeight.get(); ++i) {
            if (!((AbstractBlockAccessor)this.mc.field_1687.method_8320(class_23382.method_10086(i)).method_26204()).isCollidable()) continue;
            return false;
        }
        return true;
    }

    private static class Hole {
        public byte exclude;
        public class_2338.class_2339 blockPos = new class_2338.class_2339();
        public Type type;

        public Color getBottomColor() {
            switch (this.type) {
                case Obsidian: {
                    return (Color)HoleESP.access$300(Modules.get().get(HoleESP.class)).get();
                }
                case Bedrock: {
                    return (Color)HoleESP.access$400(Modules.get().get(HoleESP.class)).get();
                }
            }
            return (Color)HoleESP.access$500(Modules.get().get(HoleESP.class)).get();
        }

        public Color getTopColor() {
            switch (this.type) {
                case Obsidian: {
                    return (Color)HoleESP.access$000(Modules.get().get(HoleESP.class)).get();
                }
                case Bedrock: {
                    return (Color)HoleESP.access$100(Modules.get().get(HoleESP.class)).get();
                }
            }
            return (Color)HoleESP.access$200(Modules.get().get(HoleESP.class)).get();
        }

        Hole(1 var1_1) {
            this();
        }

        private Hole() {
        }

        public void render(MeshBuilder meshBuilder, MeshBuilder meshBuilder2, ShapeMode shapeMode, double d, boolean bl, boolean bl2) {
            int n = this.blockPos.method_10263();
            int n2 = this.blockPos.method_10264();
            int n3 = this.blockPos.method_10260();
            Color color = this.getTopColor();
            Color color2 = this.getBottomColor();
            int n4 = color.a;
            int n5 = color2.a;
            if (shapeMode != ShapeMode.Lines) {
                color.a = n4 / 2;
                color2.a = n5 / 2;
                if (Dir.is(this.exclude, (byte)2) && bl) {
                    meshBuilder2.quad(n, (double)n2 + d, n3, n, (double)n2 + d, n3 + 1, n + 1, (double)n2 + d, n3 + 1, n + 1, (double)n2 + d, n3, color);
                }
                if (Dir.is(this.exclude, (byte)4) && bl2) {
                    meshBuilder2.quad(n, n2, n3, n, n2, n3 + 1, n + 1, n2, n3 + 1, n + 1, n2, n3, color2);
                }
                if (Dir.is(this.exclude, (byte)8)) {
                    meshBuilder2.verticalGradientQuad(n, (double)n2 + d, n3, n + 1, (double)n2 + d, n3, n + 1, n2, n3, n, n2, n3, color, color2);
                }
                if (Dir.is(this.exclude, (byte)16)) {
                    meshBuilder2.verticalGradientQuad(n, (double)n2 + d, n3 + 1, n + 1, (double)n2 + d, n3 + 1, n + 1, n2, n3 + 1, n, n2, n3 + 1, color, color2);
                }
                if (Dir.is(this.exclude, (byte)32)) {
                    meshBuilder2.verticalGradientQuad(n, (double)n2 + d, n3, n, (double)n2 + d, n3 + 1, n, n2, n3 + 1, n, n2, n3, color, color2);
                }
                if (Dir.is(this.exclude, (byte)64)) {
                    meshBuilder2.verticalGradientQuad(n + 1, (double)n2 + d, n3, n + 1, (double)n2 + d, n3 + 1, n + 1, n2, n3 + 1, n + 1, n2, n3, color, color2);
                }
                color.a = n4;
                color2.a = n5;
            }
            if (shapeMode != ShapeMode.Sides) {
                if (Dir.is(this.exclude, (byte)32) && Dir.is(this.exclude, (byte)8)) {
                    meshBuilder.line(n, n2, n3, n, (double)n2 + d, n3, color2, color);
                }
                if (Dir.is(this.exclude, (byte)32) && Dir.is(this.exclude, (byte)16)) {
                    meshBuilder.line(n, n2, n3 + 1, n, (double)n2 + d, n3 + 1, color2, color);
                }
                if (Dir.is(this.exclude, (byte)64) && Dir.is(this.exclude, (byte)8)) {
                    meshBuilder.line(n + 1, n2, n3, n + 1, (double)n2 + d, n3, color2, color);
                }
                if (Dir.is(this.exclude, (byte)64) && Dir.is(this.exclude, (byte)16)) {
                    meshBuilder.line(n + 1, n2, n3 + 1, n + 1, (double)n2 + d, n3 + 1, color2, color);
                }
                if (Dir.is(this.exclude, (byte)8)) {
                    meshBuilder.line(n, n2, n3, n + 1, n2, n3, color2);
                }
                if (Dir.is(this.exclude, (byte)8)) {
                    meshBuilder.line(n, (double)n2 + d, n3, n + 1, (double)n2 + d, n3, color);
                }
                if (Dir.is(this.exclude, (byte)16)) {
                    meshBuilder.line(n, n2, n3 + 1, n + 1, n2, n3 + 1, color2);
                }
                if (Dir.is(this.exclude, (byte)16)) {
                    meshBuilder.line(n, (double)n2 + d, n3 + 1, n + 1, (double)n2 + d, n3 + 1, color);
                }
                if (Dir.is(this.exclude, (byte)32)) {
                    meshBuilder.line(n, n2, n3, n, n2, n3 + 1, color2);
                }
                if (Dir.is(this.exclude, (byte)32)) {
                    meshBuilder.line(n, (double)n2 + d, n3, n, (double)n2 + d, n3 + 1, color);
                }
                if (Dir.is(this.exclude, (byte)64)) {
                    meshBuilder.line(n + 1, n2, n3, n + 1, n2, n3 + 1, color2);
                }
                if (Dir.is(this.exclude, (byte)64)) {
                    meshBuilder.line(n + 1, (double)n2 + d, n3, n + 1, (double)n2 + d, n3 + 1, color);
                }
            }
        }

        public Hole set(class_2338 class_23382, Type type, byte by) {
            this.blockPos.method_10101((class_2382)class_23382);
            this.exclude = by;
            this.type = type;
            return this;
        }

        public static enum Type {
            Bedrock,
            Obsidian,
            Mixed;

        }
    }
}

