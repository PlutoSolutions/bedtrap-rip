/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.class_1268
 *  net.minecraft.class_1792
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_2382
 *  net.minecraft.class_265
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules.world;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.StartBreakingBlockEvent;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1268;
import net.minecraft.class_1792;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_265;
import net.minecraft.class_310;

public class VeinMiner
extends Module {
    private final List<class_2338> foundBlockPositions;
    private final SettingGroup sgGeneral;
    private final List<MyBlock> blocks;
    private final Setting<Boolean> rotate;
    private final Setting<SettingColor> sideColor;
    private final Setting<Boolean> render;
    private final Set<class_2382> blockNeighbours;
    private final Pool<MyBlock> blockPool;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<Integer> depth;
    private final Setting<SettingColor> lineColor;
    private final SettingGroup sgRender;

    static Setting access$1200(VeinMiner veinMiner) {
        return veinMiner.rotate;
    }

    static Setting access$1600(VeinMiner veinMiner) {
        return veinMiner.sideColor;
    }

    static class_310 access$100(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent startBreakingBlockEvent) {
        if (this.mc.field_1687.method_8320(startBreakingBlockEvent.blockPos).method_26214((class_1922)this.mc.field_1687, startBreakingBlockEvent.blockPos) < 0.0f) {
            return;
        }
        this.foundBlockPositions.clear();
        if (!this.isMiningBlock(startBreakingBlockEvent.blockPos)) {
            MyBlock myBlock = this.blockPool.get();
            myBlock.set(startBreakingBlockEvent);
            this.blocks.add(myBlock);
            this.mineNearbyBlocks(myBlock.originalBlock.method_8389(), startBreakingBlockEvent.blockPos, startBreakingBlockEvent.direction, this.depth.get());
        }
    }

    static class_310 access$1400(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.blocks.removeIf(MyBlock::shouldRemove);
        if (!this.blocks.isEmpty()) {
            this.blocks.get(0).mine();
        }
    }

    static class_310 access$1100(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$400(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$200(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$600(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static Setting access$1800(VeinMiner veinMiner) {
        return veinMiner.shapeMode;
    }

    static Setting access$1700(VeinMiner veinMiner) {
        return veinMiner.lineColor;
    }

    static class_310 access$900(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    private boolean isMiningBlock(class_2338 class_23382) {
        for (MyBlock myBlock : this.blocks) {
            if (!myBlock.blockPos.equals((Object)class_23382)) continue;
            return true;
        }
        return false;
    }

    static class_310 access$500(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    private MyBlock lambda$new$0() {
        return new MyBlock(this, null);
    }

    static class_310 access$700(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    public VeinMiner() {
        super(Categories.World, "vein-miner", "Mines all nearby blocks with this type");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.blockNeighbours = Sets.newHashSet((Object[])new class_2382[]{new class_2382(1, -1, 1), new class_2382(0, -1, 1), new class_2382(-1, -1, 1), new class_2382(1, -1, 0), new class_2382(0, -1, 0), new class_2382(-1, -1, 0), new class_2382(1, -1, -1), new class_2382(0, -1, -1), new class_2382(-1, -1, -1), new class_2382(1, 0, 1), new class_2382(0, 0, 1), new class_2382(-1, 0, 1), new class_2382(1, 0, 0), new class_2382(-1, 0, 0), new class_2382(1, 0, -1), new class_2382(0, 0, -1), new class_2382(-1, 0, -1), new class_2382(1, 1, 1), new class_2382(0, 1, 1), new class_2382(-1, 1, 1), new class_2382(1, 1, 0), new class_2382(0, 1, 0), new class_2382(-1, 1, 0), new class_2382(1, 1, -1), new class_2382(0, 1, -1), new class_2382(-1, 1, -1)});
        this.depth = this.sgGeneral.add(new IntSetting.Builder().name("depth").description("Amount of iterations used to scan for similar blocks").defaultValue(3).min(1).sliderMax(15).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        this.blockPool = new Pool<MyBlock>(this::lambda$new$0);
        this.blocks = new ArrayList<MyBlock>();
        this.foundBlockPositions = new ArrayList<class_2338>();
    }

    private void mineNearbyBlocks(class_1792 class_17922, class_2338 class_23382, class_2350 class_23502, int n) {
        if (n <= 0) {
            return;
        }
        if (this.foundBlockPositions.contains((Object)class_23382)) {
            return;
        }
        this.foundBlockPositions.add(class_23382);
        if (Utils.distance(this.mc.field_1724.method_23317() - 0.5, this.mc.field_1724.method_23318() + (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()), this.mc.field_1724.method_23321() - 0.5, class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260()) > (double)this.mc.field_1761.method_2904()) {
            return;
        }
        for (class_2382 class_23822 : this.blockNeighbours) {
            class_2338 class_23383 = class_23382.method_10081(class_23822);
            if (this.mc.field_1687.method_8320(class_23383).method_26204().method_8389() != class_17922) continue;
            MyBlock myBlock = this.blockPool.get();
            myBlock.set(class_23383, class_23502);
            this.blocks.add(myBlock);
            this.mineNearbyBlocks(class_17922, class_23383, class_23502, n - 1);
        }
    }

    @Override
    public void onDeactivate() {
        for (MyBlock myBlock : this.blocks) {
            this.blockPool.free(myBlock);
        }
        this.blocks.clear();
        this.foundBlockPositions.clear();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.render.get().booleanValue()) {
            for (MyBlock myBlock : this.blocks) {
                myBlock.render();
            }
        }
    }

    static class_310 access$800(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$1300(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$1000(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$300(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$000(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    static class_310 access$1500(VeinMiner veinMiner) {
        return veinMiner.mc;
    }

    private class MyBlock {
        public class_2248 originalBlock;
        public class_2350 direction;
        public class_2338 blockPos;
        public boolean mining;
        final VeinMiner this$0;

        public void set(class_2338 class_23382, class_2350 class_23502) {
            this.blockPos = class_23382;
            this.direction = class_23502;
            this.originalBlock = VeinMiner.access$100((VeinMiner)this.this$0).field_1687.method_8320(class_23382).method_26204();
            this.mining = false;
        }

        public void mine() {
            if (!this.mining) {
                VeinMiner.access$1100((VeinMiner)this.this$0).field_1724.method_6104(class_1268.field_5808);
                this.mining = true;
            }
            if (((Boolean)VeinMiner.access$1200(this.this$0).get()).booleanValue()) {
                Rotations.rotate(Rotations.getYaw(this.blockPos), Rotations.getPitch(this.blockPos), 50, this::updateBlockBreakingProgress);
            } else {
                this.updateBlockBreakingProgress();
            }
        }

        public void set(StartBreakingBlockEvent startBreakingBlockEvent) {
            this.blockPos = startBreakingBlockEvent.blockPos;
            this.direction = startBreakingBlockEvent.direction;
            this.originalBlock = VeinMiner.access$000((VeinMiner)this.this$0).field_1687.method_8320(this.blockPos).method_26204();
            this.mining = false;
        }

        public void render() {
            class_265 class_2652 = VeinMiner.access$1500((VeinMiner)this.this$0).field_1687.method_8320(this.blockPos).method_26218((class_1922)VeinMiner.access$1400((VeinMiner)this.this$0).field_1687, this.blockPos);
            double d = this.blockPos.method_10263();
            double d2 = this.blockPos.method_10264();
            double d3 = this.blockPos.method_10260();
            double d4 = this.blockPos.method_10263() + 1;
            double d5 = this.blockPos.method_10264() + 1;
            double d6 = this.blockPos.method_10260() + 1;
            if (!class_2652.method_1110()) {
                d = (double)this.blockPos.method_10263() + class_2652.method_1091(class_2350.class_2351.field_11048);
                d2 = (double)this.blockPos.method_10264() + class_2652.method_1091(class_2350.class_2351.field_11052);
                d3 = (double)this.blockPos.method_10260() + class_2652.method_1091(class_2350.class_2351.field_11051);
                d4 = (double)this.blockPos.method_10263() + class_2652.method_1105(class_2350.class_2351.field_11048);
                d5 = (double)this.blockPos.method_10264() + class_2652.method_1105(class_2350.class_2351.field_11052);
                d6 = (double)this.blockPos.method_10260() + class_2652.method_1105(class_2350.class_2351.field_11051);
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d, d2, d3, d4, d5, d6, (Color)VeinMiner.access$1600(this.this$0).get(), (Color)VeinMiner.access$1700(this.this$0).get(), (ShapeMode)((Object)VeinMiner.access$1800(this.this$0).get()), 0);
        }

        MyBlock(VeinMiner veinMiner, 1 var2_2) {
            this(veinMiner);
        }

        public boolean shouldRemove() {
            boolean bl;
            boolean bl2 = bl = VeinMiner.access$200((VeinMiner)this.this$0).field_1687.method_8320(this.blockPos).method_26204() != this.originalBlock || Utils.distance(VeinMiner.access$300((VeinMiner)this.this$0).field_1724.method_23317() - 0.5, VeinMiner.access$400((VeinMiner)this.this$0).field_1724.method_23318() + (double)VeinMiner.access$600((VeinMiner)this.this$0).field_1724.method_18381(VeinMiner.access$500((VeinMiner)this.this$0).field_1724.method_18376()), VeinMiner.access$700((VeinMiner)this.this$0).field_1724.method_23321() - 0.5, this.blockPos.method_10263() + this.direction.method_10148(), this.blockPos.method_10264() + this.direction.method_10164(), this.blockPos.method_10260() + this.direction.method_10165()) > (double)VeinMiner.access$800((VeinMiner)this.this$0).field_1761.method_2904();
            if (bl) {
                VeinMiner.access$900((VeinMiner)this.this$0).field_1761.method_2925();
                VeinMiner.access$1000((VeinMiner)this.this$0).field_1724.method_6104(class_1268.field_5808);
            }
            return bl;
        }

        private MyBlock(VeinMiner veinMiner) {
            this.this$0 = veinMiner;
        }

        private void updateBlockBreakingProgress() {
            VeinMiner.access$1300((VeinMiner)this.this$0).field_1761.method_2902(this.blockPos, this.direction);
        }
    }
}

