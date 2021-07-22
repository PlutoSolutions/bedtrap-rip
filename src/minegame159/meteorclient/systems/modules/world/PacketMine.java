/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_2596
 *  net.minecraft.class_265
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2879
 */
package minegame159.meteorclient.systems.modules.world;

import java.util.ArrayList;
import java.util.List;
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
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_265;
import net.minecraft.class_2846;
import net.minecraft.class_2879;

public class PacketMine
extends Module {
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ List<MyBlock> blocks;
    private final /* synthetic */ Pool<MyBlock> blockPool;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> render;

    private boolean isMiningBlock(class_2338 lIIlllIlllllIll) {
        PacketMine lIIlllIlllllllI;
        for (MyBlock lIIlllIllllllll : lIIlllIlllllllI.blocks) {
            if (!lIIlllIllllllll.blockPos.equals((Object)lIIlllIlllllIll)) continue;
            return true;
        }
        return false;
    }

    public PacketMine() {
        super(Categories.World, "packet-mine", "Sends packets to mine blocks without the mining animation.");
        PacketMine lIIllllIIIIllII;
        lIIllllIIIIllII.sgGeneral = lIIllllIIIIllII.settings.getDefaultGroup();
        lIIllllIIIIllII.sgRender = lIIllllIIIIllII.settings.createGroup("Render");
        lIIllllIIIIllII.delay = lIIllllIIIIllII.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(1).min(0).sliderMax(10).build());
        lIIllllIIIIllII.rotate = lIIllllIIIIllII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        lIIllllIIIIllII.render = lIIllllIIIIllII.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        lIIllllIIIIllII.shapeMode = lIIllllIIIIllII.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIIllllIIIIllII.sideColor = lIIllllIIIIllII.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lIIllllIIIIllII.lineColor = lIIllllIIIIllII.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        lIIllllIIIIllII.blockPool = new Pool<MyBlock>(() -> {
            PacketMine lIIlllIlllIIIII;
            return lIIlllIlllIIIII.new MyBlock();
        });
        lIIllllIIIIllII.blocks = new ArrayList<MyBlock>();
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIIlllIlllIllIl) {
        PacketMine lIIlllIlllIlllI;
        lIIlllIlllIlllI.blocks.removeIf(MyBlock::shouldRemove);
        if (!lIIlllIlllIlllI.blocks.isEmpty()) {
            lIIlllIlllIlllI.blocks.get(0).mine();
        }
    }

    @Override
    public void onDeactivate() {
        PacketMine lIIllllIIIIIlll;
        for (MyBlock lIIllllIIIIlIII : lIIllllIIIIIlll.blocks) {
            lIIllllIIIIIlll.blockPool.free(lIIllllIIIIlIII);
        }
        lIIllllIIIIIlll.blocks.clear();
    }

    @EventHandler
    private void onRender(RenderEvent lIIlllIlllIIllI) {
        PacketMine lIIlllIlllIIlIl;
        if (lIIlllIlllIIlIl.render.get().booleanValue()) {
            for (MyBlock lIIlllIlllIlIII : lIIlllIlllIIlIl.blocks) {
                lIIlllIlllIlIII.render();
            }
        }
    }

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent lIIlllIllllIIll) {
        PacketMine lIIlllIllllIIlI;
        lIIlllIllllIIll.cancel();
        if (lIIlllIllllIIlI.mc.field_1687.method_8320(lIIlllIllllIIll.blockPos).method_26214((class_1922)lIIlllIllllIIlI.mc.field_1687, lIIlllIllllIIll.blockPos) < 0.0f) {
            return;
        }
        if (!lIIlllIllllIIlI.isMiningBlock(lIIlllIllllIIll.blockPos)) {
            MyBlock lIIlllIllllIlIl = lIIlllIllllIIlI.blockPool.get();
            lIIlllIllllIlIl.set(lIIlllIllllIIll);
            lIIlllIllllIIlI.blocks.add(lIIlllIllllIlIl);
        }
    }

    private class MyBlock {
        public /* synthetic */ boolean mining;
        public /* synthetic */ int timer;
        public /* synthetic */ class_2248 originalBlock;
        public /* synthetic */ class_2350 direction;
        public /* synthetic */ class_2338 blockPos;

        public void set(StartBreakingBlockEvent llIIIIllIlIlll) {
            MyBlock llIIIIllIllIlI;
            llIIIIllIllIlI.blockPos = llIIIIllIlIlll.blockPos;
            llIIIIllIllIlI.direction = llIIIIllIlIlll.direction;
            llIIIIllIllIlI.originalBlock = ((PacketMine)llIIIIllIllIlI.PacketMine.this).mc.field_1687.method_8320(llIIIIllIllIlI.blockPos).method_26204();
            llIIIIllIllIlI.timer = (Integer)llIIIIllIllIlI.PacketMine.this.delay.get();
            llIIIIllIllIlI.mining = false;
        }

        public void render() {
            MyBlock llIIIIllIIIIlI;
            class_265 llIIIIllIIIIIl = ((PacketMine)llIIIIllIIIIlI.PacketMine.this).mc.field_1687.method_8320(llIIIIllIIIIlI.blockPos).method_26218((class_1922)((PacketMine)llIIIIllIIIIlI.PacketMine.this).mc.field_1687, llIIIIllIIIIlI.blockPos);
            double llIIIIllIIIIII = llIIIIllIIIIlI.blockPos.method_10263();
            double llIIIIlIllllll = llIIIIllIIIIlI.blockPos.method_10264();
            double llIIIIlIlllllI = llIIIIllIIIIlI.blockPos.method_10260();
            double llIIIIlIllllIl = llIIIIllIIIIlI.blockPos.method_10263() + 1;
            double llIIIIlIllllII = llIIIIllIIIIlI.blockPos.method_10264() + 1;
            double llIIIIlIlllIll = llIIIIllIIIIlI.blockPos.method_10260() + 1;
            if (!llIIIIllIIIIIl.method_1110()) {
                llIIIIllIIIIII = (double)llIIIIllIIIIlI.blockPos.method_10263() + llIIIIllIIIIIl.method_1091(class_2350.class_2351.field_11048);
                llIIIIlIllllll = (double)llIIIIllIIIIlI.blockPos.method_10264() + llIIIIllIIIIIl.method_1091(class_2350.class_2351.field_11052);
                llIIIIlIlllllI = (double)llIIIIllIIIIlI.blockPos.method_10260() + llIIIIllIIIIIl.method_1091(class_2350.class_2351.field_11051);
                llIIIIlIllllIl = (double)llIIIIllIIIIlI.blockPos.method_10263() + llIIIIllIIIIIl.method_1105(class_2350.class_2351.field_11048);
                llIIIIlIllllII = (double)llIIIIllIIIIlI.blockPos.method_10264() + llIIIIllIIIIIl.method_1105(class_2350.class_2351.field_11052);
                llIIIIlIlllIll = (double)llIIIIllIIIIlI.blockPos.method_10260() + llIIIIllIIIIIl.method_1105(class_2350.class_2351.field_11051);
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llIIIIllIIIIII, llIIIIlIllllll, llIIIIlIlllllI, llIIIIlIllllIl, llIIIIlIllllII, llIIIIlIlllIll, (Color)llIIIIllIIIIlI.PacketMine.this.sideColor.get(), (Color)llIIIIllIIIIlI.PacketMine.this.lineColor.get(), (ShapeMode)((Object)llIIIIllIIIIlI.PacketMine.this.shapeMode.get()), 0);
        }

        private void sendMinePackets() {
            MyBlock llIIIIllIIlIll;
            if (llIIIIllIIlIll.timer <= 0) {
                if (!llIIIIllIIlIll.mining) {
                    llIIIIllIIlIll.PacketMine.this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, llIIIIllIIlIll.blockPos, llIIIIllIIlIll.direction));
                    llIIIIllIIlIll.PacketMine.this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, llIIIIllIIlIll.blockPos, llIIIIllIIlIll.direction));
                    llIIIIllIIlIll.mining = true;
                }
            } else {
                --llIIIIllIIlIll.timer;
            }
        }

        private MyBlock() {
            MyBlock llIIIIllIllllI;
        }

        public void mine() {
            MyBlock llIIIIllIIlllI;
            if (((Boolean)llIIIIllIIlllI.PacketMine.this.rotate.get()).booleanValue()) {
                Rotations.rotate(Rotations.getYaw(llIIIIllIIlllI.blockPos), Rotations.getPitch(llIIIIllIIlllI.blockPos), 50, llIIIIllIIlllI::sendMinePackets);
            } else {
                llIIIIllIIlllI.sendMinePackets();
            }
        }

        public boolean shouldRemove() {
            MyBlock llIIIIllIlIlII;
            boolean llIIIIllIlIIll;
            boolean bl = llIIIIllIlIIll = ((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1687.method_8320(llIIIIllIlIlII.blockPos).method_26204() != llIIIIllIlIlII.originalBlock || Utils.distance(((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1724.method_23317() - 0.5, ((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1724.method_23318() + (double)((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1724.method_18381(((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1724.method_18376()), ((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1724.method_23321() - 0.5, llIIIIllIlIlII.blockPos.method_10263() + llIIIIllIlIlII.direction.method_10148(), llIIIIllIlIlII.blockPos.method_10264() + llIIIIllIlIlII.direction.method_10164(), llIIIIllIlIlII.blockPos.method_10260() + llIIIIllIlIlII.direction.method_10165()) > (double)((PacketMine)llIIIIllIlIlII.PacketMine.this).mc.field_1761.method_2904();
            if (llIIIIllIlIIll) {
                llIIIIllIlIlII.PacketMine.this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, llIIIIllIlIlII.blockPos, llIIIIllIlIlII.direction));
                llIIIIllIlIlII.PacketMine.this.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            return llIIIIllIlIIll;
        }
    }
}

