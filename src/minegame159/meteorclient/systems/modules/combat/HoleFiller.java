/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.systems.modules.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.AbstractBlockAccessor;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockIterator;
import minegame159.meteorclient.utils.world.BlockUtils;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2680;

public class HoleFiller
extends Module {
    private final Setting<Boolean> render;
    private final Setting<Integer> placeDelay;
    private final Setting<SettingColor> nextLineColor;
    private final Setting<Integer> verticalRadius;
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> lineColor;
    private int timer;
    private final Pool<Hole> holePool;
    private final List<Hole> holes;
    private final Setting<SettingColor> sideColor;
    private final Setting<List<class_2248>> blocks;
    private final Setting<SettingColor> nextSideColor;
    private final Setting<Boolean> doubles;
    private final Setting<Boolean> rotate;
    private final Setting<Integer> horizontalRadius;
    private final SettingGroup sgRender;
    private final byte NULL = 0;
    private final Setting<ShapeMode> shapeMode;

    @EventHandler
    private void onTickPost(TickEvent.Post post) {
        if (this.timer <= 0 && !this.holes.isEmpty()) {
            FindItemResult findItemResult = InvUtils.findInHotbar(this::lambda$onTickPost$3);
            BlockUtils.place((class_2338)this.holes.get((int)0).blockPos, findItemResult, this.rotate.get(), 10, true);
            this.timer = this.placeDelay.get();
        }
        --this.timer;
    }

    @EventHandler(priority=100)
    private void onRender(RenderEvent renderEvent) {
        if (!this.render.get().booleanValue()) {
            return;
        }
        for (Hole hole : this.holes) {
            if (hole == this.holes.get(0)) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)hole.blockPos, this.nextSideColor.get(), this.nextLineColor.get(), this.shapeMode.get(), hole.exclude);
                continue;
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)hole.blockPos, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), hole.exclude);
        }
    }

    private boolean lambda$onTickPost$3(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1747 && this.blocks.get().contains((Object)class_2248.method_9503((class_1792)class_17992.method_7909()));
    }

    private boolean lambda$onTick$1(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1747 && this.blocks.get().contains((Object)class_2248.method_9503((class_1792)class_17992.method_7909()));
    }

    private void lambda$onTick$2(class_2338 class_23382, class_2680 class_26802) {
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
        }
        if (n2 + n == 5 && class_23502 == null) {
            this.holes.add(this.holePool.get().set(class_23382, (byte)0));
        } else if (n2 + n == 8 && this.doubles.get().booleanValue() && class_23502 != null) {
            this.holes.add(this.holePool.get().set(class_23382, Dir.get(class_23502)));
        }
    }

    private boolean validHole(class_2338 class_23382) {
        if (this.mc.field_1724.method_24515().equals((Object)class_23382)) {
            return false;
        }
        if (((AbstractBlockAccessor)this.mc.field_1687.method_8320(class_23382).method_26204()).isCollidable()) {
            return false;
        }
        return !((AbstractBlockAccessor)this.mc.field_1687.method_8320(class_23382.method_10084()).method_26204()).isCollidable();
    }

    @Override
    public void onActivate() {
        this.timer = 0;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        for (Hole hole : this.holes) {
            this.holePool.free(hole);
        }
        this.holes.clear();
        FindItemResult findItemResult = InvUtils.findInHotbar(this::lambda$onTick$1);
        if (!findItemResult.found()) {
            return;
        }
        BlockIterator.register(this.horizontalRadius.get(), this.verticalRadius.get(), (arg_0, arg_1) -> this.lambda$onTick$2(arg_0, arg_1));
    }

    private static Hole lambda$new$0() {
        return new Hole(null);
    }

    public HoleFiller() {
        super(Categories.Combat, "hole-filler", "Fills holes with specified blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Which blocks can be used to fill holes.").defaultValue(Collections.singletonList(class_2246.field_10540)).build());
        this.horizontalRadius = this.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(4).min(0).sliderMax(6).build());
        this.verticalRadius = this.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for holes.").defaultValue(4).min(0).sliderMax(6).build());
        this.doubles = this.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Fills double holes.").defaultValue(true).build());
        this.placeDelay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("The ticks delay between placement.").defaultValue(1).min(0).sliderMax(10).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the holes being filled.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders an overlay where blocks will be placed.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        this.nextSideColor = this.sgRender.add(new ColorSetting.Builder().name("next-side-color").description("The side color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245, 10)).build());
        this.nextLineColor = this.sgRender.add(new ColorSetting.Builder().name("next-line-color").description("The line color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245)).build());
        this.holePool = new Pool<Hole>(HoleFiller::lambda$new$0);
        this.holes = new ArrayList<Hole>();
        this.NULL = 0;
    }

    private static class Hole {
        public class_2338.class_2339 blockPos = new class_2338.class_2339();
        public byte exclude;

        private Hole() {
        }

        public Hole set(class_2338 class_23382, byte by) {
            this.blockPos.method_10101((class_2382)class_23382);
            this.exclude = by;
            return this;
        }

        Hole(1 var1_1) {
            this();
        }
    }
}

