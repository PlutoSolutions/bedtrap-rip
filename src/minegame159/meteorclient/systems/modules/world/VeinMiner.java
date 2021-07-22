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

public class VeinMiner
extends Module {
    private final /* synthetic */ List<class_2338> foundBlockPositions;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ List<MyBlock> blocks;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Set<class_2382> blockNeighbours;
    private final /* synthetic */ Pool<MyBlock> blockPool;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Integer> depth;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ SettingGroup sgRender;

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent lllllllllllllllllIlllIlIlIIIIlIl) {
        VeinMiner lllllllllllllllllIlllIlIlIIIIlII;
        if (lllllllllllllllllIlllIlIlIIIIlII.mc.field_1687.method_8320(lllllllllllllllllIlllIlIlIIIIlIl.blockPos).method_26214((class_1922)lllllllllllllllllIlllIlIlIIIIlII.mc.field_1687, lllllllllllllllllIlllIlIlIIIIlIl.blockPos) < 0.0f) {
            return;
        }
        lllllllllllllllllIlllIlIlIIIIlII.foundBlockPositions.clear();
        if (!lllllllllllllllllIlllIlIlIIIIlII.isMiningBlock(lllllllllllllllllIlllIlIlIIIIlIl.blockPos)) {
            MyBlock lllllllllllllllllIlllIlIlIIIIlll = lllllllllllllllllIlllIlIlIIIIlII.blockPool.get();
            lllllllllllllllllIlllIlIlIIIIlll.set(lllllllllllllllllIlllIlIlIIIIlIl);
            lllllllllllllllllIlllIlIlIIIIlII.blocks.add(lllllllllllllllllIlllIlIlIIIIlll);
            lllllllllllllllllIlllIlIlIIIIlII.mineNearbyBlocks(lllllllllllllllllIlllIlIlIIIIlll.originalBlock.method_8389(), lllllllllllllllllIlllIlIlIIIIlIl.blockPos, lllllllllllllllllIlllIlIlIIIIlIl.direction, lllllllllllllllllIlllIlIlIIIIlII.depth.get());
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIlllIlIIlllllll) {
        VeinMiner lllllllllllllllllIlllIlIlIIIIIII;
        lllllllllllllllllIlllIlIlIIIIIII.blocks.removeIf(MyBlock::shouldRemove);
        if (!lllllllllllllllllIlllIlIlIIIIIII.blocks.isEmpty()) {
            lllllllllllllllllIlllIlIlIIIIIII.blocks.get(0).mine();
        }
    }

    private boolean isMiningBlock(class_2338 lllllllllllllllllIlllIlIlIIIllll) {
        VeinMiner lllllllllllllllllIlllIlIlIIIlllI;
        for (MyBlock lllllllllllllllllIlllIlIlIIlIIIl : lllllllllllllllllIlllIlIlIIIlllI.blocks) {
            if (!lllllllllllllllllIlllIlIlIIlIIIl.blockPos.equals((Object)lllllllllllllllllIlllIlIlIIIllll)) continue;
            return true;
        }
        return false;
    }

    public VeinMiner() {
        super(Categories.World, "vein-miner", "Mines all nearby blocks with this type");
        VeinMiner lllllllllllllllllIlllIlIlIIllllI;
        lllllllllllllllllIlllIlIlIIllllI.sgGeneral = lllllllllllllllllIlllIlIlIIllllI.settings.getDefaultGroup();
        lllllllllllllllllIlllIlIlIIllllI.sgRender = lllllllllllllllllIlllIlIlIIllllI.settings.createGroup("Render");
        lllllllllllllllllIlllIlIlIIllllI.blockNeighbours = Sets.newHashSet((Object[])new class_2382[]{new class_2382(1, -1, 1), new class_2382(0, -1, 1), new class_2382(-1, -1, 1), new class_2382(1, -1, 0), new class_2382(0, -1, 0), new class_2382(-1, -1, 0), new class_2382(1, -1, -1), new class_2382(0, -1, -1), new class_2382(-1, -1, -1), new class_2382(1, 0, 1), new class_2382(0, 0, 1), new class_2382(-1, 0, 1), new class_2382(1, 0, 0), new class_2382(-1, 0, 0), new class_2382(1, 0, -1), new class_2382(0, 0, -1), new class_2382(-1, 0, -1), new class_2382(1, 1, 1), new class_2382(0, 1, 1), new class_2382(-1, 1, 1), new class_2382(1, 1, 0), new class_2382(0, 1, 0), new class_2382(-1, 1, 0), new class_2382(1, 1, -1), new class_2382(0, 1, -1), new class_2382(-1, 1, -1)});
        lllllllllllllllllIlllIlIlIIllllI.depth = lllllllllllllllllIlllIlIlIIllllI.sgGeneral.add(new IntSetting.Builder().name("depth").description("Amount of iterations used to scan for similar blocks").defaultValue(3).min(1).sliderMax(15).build());
        lllllllllllllllllIlllIlIlIIllllI.rotate = lllllllllllllllllIlllIlIlIIllllI.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        lllllllllllllllllIlllIlIlIIllllI.render = lllllllllllllllllIlllIlIlIIllllI.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        lllllllllllllllllIlllIlIlIIllllI.shapeMode = lllllllllllllllllIlllIlIlIIllllI.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIlllIlIlIIllllI.sideColor = lllllllllllllllllIlllIlIlIIllllI.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lllllllllllllllllIlllIlIlIIllllI.lineColor = lllllllllllllllllIlllIlIlIIllllI.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        lllllllllllllllllIlllIlIlIIllllI.blockPool = new Pool<MyBlock>(() -> {
            VeinMiner lllllllllllllllllIlllIlIIlIllIII;
            return lllllllllllllllllIlllIlIIlIllIII.new MyBlock();
        });
        lllllllllllllllllIlllIlIlIIllllI.blocks = new ArrayList<MyBlock>();
        lllllllllllllllllIlllIlIlIIllllI.foundBlockPositions = new ArrayList<class_2338>();
    }

    private void mineNearbyBlocks(class_1792 lllllllllllllllllIlllIlIIllIIlll, class_2338 lllllllllllllllllIlllIlIIllIIllI, class_2350 lllllllllllllllllIlllIlIIllIIlIl, int lllllllllllllllllIlllIlIIlIlllll) {
        VeinMiner lllllllllllllllllIlllIlIIllIIIll;
        if (lllllllllllllllllIlllIlIIlIlllll <= 0) {
            return;
        }
        if (lllllllllllllllllIlllIlIIllIIIll.foundBlockPositions.contains((Object)lllllllllllllllllIlllIlIIllIIllI)) {
            return;
        }
        lllllllllllllllllIlllIlIIllIIIll.foundBlockPositions.add(lllllllllllllllllIlllIlIIllIIllI);
        if (Utils.distance(lllllllllllllllllIlllIlIIllIIIll.mc.field_1724.method_23317() - 0.5, lllllllllllllllllIlllIlIIllIIIll.mc.field_1724.method_23318() + (double)lllllllllllllllllIlllIlIIllIIIll.mc.field_1724.method_18381(lllllllllllllllllIlllIlIIllIIIll.mc.field_1724.method_18376()), lllllllllllllllllIlllIlIIllIIIll.mc.field_1724.method_23321() - 0.5, lllllllllllllllllIlllIlIIllIIllI.method_10263(), lllllllllllllllllIlllIlIIllIIllI.method_10264(), lllllllllllllllllIlllIlIIllIIllI.method_10260()) > (double)lllllllllllllllllIlllIlIIllIIIll.mc.field_1761.method_2904()) {
            return;
        }
        for (class_2382 lllllllllllllllllIlllIlIIllIlIIl : lllllllllllllllllIlllIlIIllIIIll.blockNeighbours) {
            class_2338 lllllllllllllllllIlllIlIIllIlIlI = lllllllllllllllllIlllIlIIllIIllI.method_10081(lllllllllllllllllIlllIlIIllIlIIl);
            if (lllllllllllllllllIlllIlIIllIIIll.mc.field_1687.method_8320(lllllllllllllllllIlllIlIIllIlIlI).method_26204().method_8389() != lllllllllllllllllIlllIlIIllIIlll) continue;
            MyBlock lllllllllllllllllIlllIlIIllIlIll = lllllllllllllllllIlllIlIIllIIIll.blockPool.get();
            lllllllllllllllllIlllIlIIllIlIll.set(lllllllllllllllllIlllIlIIllIlIlI, lllllllllllllllllIlllIlIIllIIlIl);
            lllllllllllllllllIlllIlIIllIIIll.blocks.add(lllllllllllllllllIlllIlIIllIlIll);
            lllllllllllllllllIlllIlIIllIIIll.mineNearbyBlocks(lllllllllllllllllIlllIlIIllIIlll, lllllllllllllllllIlllIlIIllIlIlI, lllllllllllllllllIlllIlIIllIIlIl, lllllllllllllllllIlllIlIIlIlllll - 1);
        }
    }

    @Override
    public void onDeactivate() {
        VeinMiner lllllllllllllllllIlllIlIlIIllIIl;
        for (MyBlock lllllllllllllllllIlllIlIlIIllIlI : lllllllllllllllllIlllIlIlIIllIIl.blocks) {
            lllllllllllllllllIlllIlIlIIllIIl.blockPool.free(lllllllllllllllllIlllIlIlIIllIlI);
        }
        lllllllllllllllllIlllIlIlIIllIIl.blocks.clear();
        lllllllllllllllllIlllIlIlIIllIIl.foundBlockPositions.clear();
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIlllIlIIllllIII) {
        VeinMiner lllllllllllllllllIlllIlIIllllIIl;
        if (lllllllllllllllllIlllIlIIllllIIl.render.get().booleanValue()) {
            for (MyBlock lllllllllllllllllIlllIlIIllllIlI : lllllllllllllllllIlllIlIIllllIIl.blocks) {
                lllllllllllllllllIlllIlIIllllIlI.render();
            }
        }
    }

    private class MyBlock {
        public /* synthetic */ class_2248 originalBlock;
        public /* synthetic */ class_2350 direction;
        public /* synthetic */ class_2338 blockPos;
        public /* synthetic */ boolean mining;

        public void set(class_2338 llllllllllllllllllllIlIIIIIIIIII, class_2350 llllllllllllllllllllIlIIIIIIIIlI) {
            MyBlock llllllllllllllllllllIlIIIIIIIlII;
            llllllllllllllllllllIlIIIIIIIlII.blockPos = llllllllllllllllllllIlIIIIIIIIII;
            llllllllllllllllllllIlIIIIIIIlII.direction = llllllllllllllllllllIlIIIIIIIIlI;
            llllllllllllllllllllIlIIIIIIIlII.originalBlock = ((VeinMiner)llllllllllllllllllllIlIIIIIIIlII.VeinMiner.this).mc.field_1687.method_8320(llllllllllllllllllllIlIIIIIIIIII).method_26204();
            llllllllllllllllllllIlIIIIIIIlII.mining = false;
        }

        public void mine() {
            MyBlock llllllllllllllllllllIIllllllIllI;
            if (!llllllllllllllllllllIIllllllIllI.mining) {
                ((VeinMiner)llllllllllllllllllllIIllllllIllI.VeinMiner.this).mc.field_1724.method_6104(class_1268.field_5808);
                llllllllllllllllllllIIllllllIllI.mining = true;
            }
            if (((Boolean)llllllllllllllllllllIIllllllIllI.VeinMiner.this.rotate.get()).booleanValue()) {
                Rotations.rotate(Rotations.getYaw(llllllllllllllllllllIIllllllIllI.blockPos), Rotations.getPitch(llllllllllllllllllllIIllllllIllI.blockPos), 50, llllllllllllllllllllIIllllllIllI::updateBlockBreakingProgress);
            } else {
                llllllllllllllllllllIIllllllIllI.updateBlockBreakingProgress();
            }
        }

        public void set(StartBreakingBlockEvent llllllllllllllllllllIlIIIIIIlIlI) {
            MyBlock llllllllllllllllllllIlIIIIIIlIll;
            llllllllllllllllllllIlIIIIIIlIll.blockPos = llllllllllllllllllllIlIIIIIIlIlI.blockPos;
            llllllllllllllllllllIlIIIIIIlIll.direction = llllllllllllllllllllIlIIIIIIlIlI.direction;
            llllllllllllllllllllIlIIIIIIlIll.originalBlock = ((VeinMiner)llllllllllllllllllllIlIIIIIIlIll.VeinMiner.this).mc.field_1687.method_8320(llllllllllllllllllllIlIIIIIIlIll.blockPos).method_26204();
            llllllllllllllllllllIlIIIIIIlIll.mining = false;
        }

        public void render() {
            MyBlock llllllllllllllllllllIIlllllIIIlI;
            class_265 llllllllllllllllllllIIlllllIlIIl = ((VeinMiner)llllllllllllllllllllIIlllllIIIlI.VeinMiner.this).mc.field_1687.method_8320(llllllllllllllllllllIIlllllIIIlI.blockPos).method_26218((class_1922)((VeinMiner)llllllllllllllllllllIIlllllIIIlI.VeinMiner.this).mc.field_1687, llllllllllllllllllllIIlllllIIIlI.blockPos);
            double llllllllllllllllllllIIlllllIlIII = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10263();
            double llllllllllllllllllllIIlllllIIlll = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10264();
            double llllllllllllllllllllIIlllllIIllI = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10260();
            double llllllllllllllllllllIIlllllIIlIl = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10263() + 1;
            double llllllllllllllllllllIIlllllIIlII = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10264() + 1;
            double llllllllllllllllllllIIlllllIIIll = llllllllllllllllllllIIlllllIIIlI.blockPos.method_10260() + 1;
            if (!llllllllllllllllllllIIlllllIlIIl.method_1110()) {
                llllllllllllllllllllIIlllllIlIII = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10263() + llllllllllllllllllllIIlllllIlIIl.method_1091(class_2350.class_2351.field_11048);
                llllllllllllllllllllIIlllllIIlll = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10264() + llllllllllllllllllllIIlllllIlIIl.method_1091(class_2350.class_2351.field_11052);
                llllllllllllllllllllIIlllllIIllI = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10260() + llllllllllllllllllllIIlllllIlIIl.method_1091(class_2350.class_2351.field_11051);
                llllllllllllllllllllIIlllllIIlIl = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10263() + llllllllllllllllllllIIlllllIlIIl.method_1105(class_2350.class_2351.field_11048);
                llllllllllllllllllllIIlllllIIlII = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10264() + llllllllllllllllllllIIlllllIlIIl.method_1105(class_2350.class_2351.field_11052);
                llllllllllllllllllllIIlllllIIIll = (double)llllllllllllllllllllIIlllllIIIlI.blockPos.method_10260() + llllllllllllllllllllIIlllllIlIIl.method_1105(class_2350.class_2351.field_11051);
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllllllIIlllllIlIII, llllllllllllllllllllIIlllllIIlll, llllllllllllllllllllIIlllllIIllI, llllllllllllllllllllIIlllllIIlIl, llllllllllllllllllllIIlllllIIlII, llllllllllllllllllllIIlllllIIIll, (Color)llllllllllllllllllllIIlllllIIIlI.VeinMiner.this.sideColor.get(), (Color)llllllllllllllllllllIIlllllIIIlI.VeinMiner.this.lineColor.get(), (ShapeMode)((Object)llllllllllllllllllllIIlllllIIIlI.VeinMiner.this.shapeMode.get()), 0);
        }

        public boolean shouldRemove() {
            MyBlock llllllllllllllllllllIIlllllllIlI;
            boolean llllllllllllllllllllIIlllllllIll;
            boolean bl = llllllllllllllllllllIIlllllllIll = ((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1687.method_8320(llllllllllllllllllllIIlllllllIlI.blockPos).method_26204() != llllllllllllllllllllIIlllllllIlI.originalBlock || Utils.distance(((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_23317() - 0.5, ((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_23318() + (double)((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_18381(((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_18376()), ((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_23321() - 0.5, llllllllllllllllllllIIlllllllIlI.blockPos.method_10263() + llllllllllllllllllllIIlllllllIlI.direction.method_10148(), llllllllllllllllllllIIlllllllIlI.blockPos.method_10264() + llllllllllllllllllllIIlllllllIlI.direction.method_10164(), llllllllllllllllllllIIlllllllIlI.blockPos.method_10260() + llllllllllllllllllllIIlllllllIlI.direction.method_10165()) > (double)((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1761.method_2904();
            if (llllllllllllllllllllIIlllllllIll) {
                ((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1761.method_2925();
                ((VeinMiner)llllllllllllllllllllIIlllllllIlI.VeinMiner.this).mc.field_1724.method_6104(class_1268.field_5808);
            }
            return llllllllllllllllllllIIlllllllIll;
        }

        private MyBlock() {
            MyBlock llllllllllllllllllllIlIIIIIIllll;
        }

        private void updateBlockBreakingProgress() {
            MyBlock llllllllllllllllllllIIllllllIIll;
            ((VeinMiner)llllllllllllllllllllIIllllllIIll.VeinMiner.this).mc.field_1761.method_2902(llllllllllllllllllllIIllllllIIll.blockPos, llllllllllllllllllllIIllllllIIll.direction);
        }
    }
}

