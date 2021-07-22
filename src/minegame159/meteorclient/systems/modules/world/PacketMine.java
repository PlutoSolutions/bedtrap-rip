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
 *  net.minecraft.class_310
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
import net.minecraft.class_310;

public class PacketMine
extends Module {
    private final SettingGroup sgRender;
    private final Setting<Boolean> rotate;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> lineColor;
    private final Setting<Integer> delay;
    private final List<MyBlock> blocks;
    private final Pool<MyBlock> blockPool;
    private final Setting<SettingColor> sideColor;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> render;

    static class_310 access$800(PacketMine packetMine) {
        return packetMine.mc;
    }

    private boolean isMiningBlock(class_2338 class_23382) {
        for (MyBlock myBlock : this.blocks) {
            if (!myBlock.blockPos.equals((Object)class_23382)) continue;
            return true;
        }
        return false;
    }

    static Setting access$1800(PacketMine packetMine) {
        return packetMine.shapeMode;
    }

    static class_310 access$900(PacketMine packetMine) {
        return packetMine.mc;
    }

    static Setting access$1700(PacketMine packetMine) {
        return packetMine.lineColor;
    }

    static class_310 access$1200(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$500(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$1500(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$1300(PacketMine packetMine) {
        return packetMine.mc;
    }

    static Setting access$1100(PacketMine packetMine) {
        return packetMine.rotate;
    }

    public PacketMine() {
        super(Categories.World, "packet-mine", "Sends packets to mine blocks without the mining animation.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("Delay between mining blocks in ticks.").defaultValue(1).min(0).sliderMax(10).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when mining.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Whether or not to render the block being mined.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        this.blockPool = new Pool<MyBlock>(this::lambda$new$0);
        this.blocks = new ArrayList<MyBlock>();
    }

    private MyBlock lambda$new$0() {
        return new MyBlock(this, null);
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.blocks.removeIf(MyBlock::shouldRemove);
        if (!this.blocks.isEmpty()) {
            this.blocks.get(0).mine();
        }
    }

    static class_310 access$1400(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$700(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$1000(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$000(PacketMine packetMine) {
        return packetMine.mc;
    }

    @Override
    public void onDeactivate() {
        for (MyBlock myBlock : this.blocks) {
            this.blockPool.free(myBlock);
        }
        this.blocks.clear();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.render.get().booleanValue()) {
            for (MyBlock myBlock : this.blocks) {
                myBlock.render();
            }
        }
    }

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent startBreakingBlockEvent) {
        startBreakingBlockEvent.cancel();
        if (this.mc.field_1687.method_8320(startBreakingBlockEvent.blockPos).method_26214((class_1922)this.mc.field_1687, startBreakingBlockEvent.blockPos) < 0.0f) {
            return;
        }
        if (!this.isMiningBlock(startBreakingBlockEvent.blockPos)) {
            MyBlock myBlock = this.blockPool.get();
            myBlock.set(startBreakingBlockEvent);
            this.blocks.add(myBlock);
        }
    }

    static class_310 access$400(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$600(PacketMine packetMine) {
        return packetMine.mc;
    }

    static Setting access$100(PacketMine packetMine) {
        return packetMine.delay;
    }

    static Setting access$1600(PacketMine packetMine) {
        return packetMine.sideColor;
    }

    static class_310 access$300(PacketMine packetMine) {
        return packetMine.mc;
    }

    static class_310 access$200(PacketMine packetMine) {
        return packetMine.mc;
    }

    private class MyBlock {
        public boolean mining;
        public int timer;
        public class_2248 originalBlock;
        final PacketMine this$0;
        public class_2350 direction;
        public class_2338 blockPos;

        public void set(StartBreakingBlockEvent startBreakingBlockEvent) {
            this.blockPos = startBreakingBlockEvent.blockPos;
            this.direction = startBreakingBlockEvent.direction;
            this.originalBlock = PacketMine.access$000((PacketMine)this.this$0).field_1687.method_8320(this.blockPos).method_26204();
            this.timer = (Integer)PacketMine.access$100(this.this$0).get();
            this.mining = false;
        }

        public void render() {
            class_265 class_2652 = PacketMine.access$1500((PacketMine)this.this$0).field_1687.method_8320(this.blockPos).method_26218((class_1922)PacketMine.access$1400((PacketMine)this.this$0).field_1687, this.blockPos);
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
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d, d2, d3, d4, d5, d6, (Color)PacketMine.access$1600(this.this$0).get(), (Color)PacketMine.access$1700(this.this$0).get(), (ShapeMode)((Object)PacketMine.access$1800(this.this$0).get()), 0);
        }

        MyBlock(PacketMine packetMine, 1 var2_2) {
            this(packetMine);
        }

        private void sendMinePackets() {
            if (this.timer <= 0) {
                if (!this.mining) {
                    PacketMine.access$1200(this.this$0).method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, this.blockPos, this.direction));
                    PacketMine.access$1300(this.this$0).method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, this.blockPos, this.direction));
                    this.mining = true;
                }
            } else {
                --this.timer;
            }
        }

        private MyBlock(PacketMine packetMine) {
            this.this$0 = packetMine;
        }

        public void mine() {
            if (((Boolean)PacketMine.access$1100(this.this$0).get()).booleanValue()) {
                Rotations.rotate(Rotations.getYaw(this.blockPos), Rotations.getPitch(this.blockPos), 50, this::sendMinePackets);
            } else {
                this.sendMinePackets();
            }
        }

        public boolean shouldRemove() {
            boolean bl;
            boolean bl2 = bl = PacketMine.access$200((PacketMine)this.this$0).field_1687.method_8320(this.blockPos).method_26204() != this.originalBlock || Utils.distance(PacketMine.access$300((PacketMine)this.this$0).field_1724.method_23317() - 0.5, PacketMine.access$400((PacketMine)this.this$0).field_1724.method_23318() + (double)PacketMine.access$600((PacketMine)this.this$0).field_1724.method_18381(PacketMine.access$500((PacketMine)this.this$0).field_1724.method_18376()), PacketMine.access$700((PacketMine)this.this$0).field_1724.method_23321() - 0.5, this.blockPos.method_10263() + this.direction.method_10148(), this.blockPos.method_10264() + this.direction.method_10164(), this.blockPos.method_10260() + this.direction.method_10165()) > (double)PacketMine.access$800((PacketMine)this.this$0).field_1761.method_2904();
            if (bl) {
                PacketMine.access$900(this.this$0).method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12971, this.blockPos, this.direction));
                PacketMine.access$1000(this.this$0).method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            return bl;
        }
    }
}

