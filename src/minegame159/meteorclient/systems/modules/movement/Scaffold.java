/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2346;
import net.minecraft.class_2382;
import net.minecraft.class_265;
import net.minecraft.class_2680;

public class Scaffold
extends Module {
    private final List<RenderBlock> renderBlocks;
    private final Setting<Boolean> renderSwing;
    private final Setting<ListMode> blocksFilter;
    private double lastSneakingY;
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> autoSwitch;
    private final Pool<RenderBlock> renderBlockPool;
    private final class_2338.class_2339 blockPos;
    private final SettingGroup sgGeneral;
    private final Setting<List<class_2248>> blocks;
    private final SettingGroup sgRender;
    private final Setting<SettingColor> sideColor;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> lineColor;
    private boolean lastWasSneaking;
    private final Setting<Boolean> fastTower;

    @Override
    public void onDeactivate() {
        for (RenderBlock renderBlock : this.renderBlocks) {
            this.renderBlockPool.free(renderBlock);
        }
        this.renderBlocks.clear();
    }

    public Scaffold() {
        super(Categories.Movement, "scaffold", "Automatically places blocks under you.");
        this.sgRender = this.settings.createGroup("Render");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Selected blocks.").defaultValue(new ArrayList<class_2248>()).build());
        this.blocksFilter = this.sgGeneral.add(new EnumSetting.Builder().name("blocks-filter").description("How to use the block list setting").defaultValue(ListMode.Blacklist).build());
        this.fastTower = this.sgGeneral.add(new BoolSetting.Builder().name("fast-tower").description("Whether or not to scaffold upwards faster.").defaultValue(false).build());
        this.renderSwing = this.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Renders your client-side swing.").defaultValue(false).build());
        this.autoSwitch = this.sgGeneral.add(new BoolSetting.Builder().name("auto-switch").description("Automatically swaps to a block before placing.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards the blocks being placed.").defaultValue(true).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        this.renderBlockPool = new Pool<RenderBlock>(RenderBlock::new);
        this.renderBlocks = new ArrayList<RenderBlock>();
        this.blockPos = new class_2338.class_2339();
    }

    private static boolean lambda$onTick$0(RenderBlock renderBlock) {
        return renderBlock.ticks <= 0;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        this.renderBlocks.forEach(RenderBlock::tick);
        this.renderBlocks.removeIf(Scaffold::lambda$onTick$0);
        this.blockPos.method_10101((class_2382)this.mc.field_1724.method_24515().method_10074());
        FindItemResult findItemResult = InvUtils.findInHotbar(this::lambda$onTick$1);
        if (!findItemResult.found()) {
            return;
        }
        if (findItemResult.getHand() == null) {
            if (this.autoSwitch.get().booleanValue()) {
                InvUtils.swap(findItemResult.getSlot());
            } else {
                return;
            }
        }
        if (this.mc.field_1690.field_1832.method_1434() && !this.mc.field_1690.field_1903.method_1434()) {
            if (this.lastSneakingY - this.mc.field_1724.method_23318() < 0.1) {
                this.lastWasSneaking = false;
                return;
            }
        } else {
            this.lastWasSneaking = false;
        }
        if (!this.lastWasSneaking) {
            this.lastSneakingY = this.mc.field_1724.method_23318();
        }
        if (this.mc.field_1690.field_1903.method_1434() && !this.mc.field_1690.field_1832.method_1434() && this.fastTower.get().booleanValue()) {
            this.mc.field_1724.method_18800(0.0, (double)0.42f, 0.0);
        }
        if (BlockUtils.place((class_2338)this.blockPos, findItemResult, this.rotate.get(), 50, this.renderSwing.get(), true)) {
            this.renderBlocks.add(this.renderBlockPool.get().set((class_2338)this.blockPos));
            if (this.mc.field_1690.field_1903.method_1434() && !this.mc.field_1690.field_1832.method_1434() && !this.mc.field_1724.method_24828() && !this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26215() && this.fastTower.get().booleanValue()) {
                this.mc.field_1724.method_18800(0.0, (double)-0.28f, 0.0);
            }
        }
    }

    private static int lambda$onRender$2(RenderBlock renderBlock) {
        return -renderBlock.ticks;
    }

    private boolean lambda$onTick$1(class_1799 class_17992) {
        return this.validItem(class_17992, (class_2338)this.blockPos);
    }

    private void lambda$onRender$3(RenderBlock renderBlock) {
        renderBlock.render(this.sideColor.get(), this.lineColor.get(), this.shapeMode.get());
    }

    private boolean validItem(class_1799 class_17992, class_2338 class_23382) {
        if (!(class_17992.method_7909() instanceof class_1747)) {
            return false;
        }
        class_2248 class_22482 = ((class_1747)class_17992.method_7909()).method_7711();
        if (this.blocksFilter.get() == ListMode.Blacklist && this.blocks.get().contains(class_22482)) {
            return false;
        }
        if (this.blocksFilter.get() == ListMode.Whitelist && !this.blocks.get().contains(class_22482)) {
            return false;
        }
        if (!class_2248.method_9614((class_265)class_22482.method_9564().method_26220((class_1922)this.mc.field_1687, class_23382))) {
            return false;
        }
        return !(class_22482 instanceof class_2346) || !class_2346.method_10128((class_2680)this.mc.field_1687.method_8320(class_23382));
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.renderBlocks.sort(Comparator.comparingInt(Scaffold::lambda$onRender$2));
        this.renderBlocks.forEach(this::lambda$onRender$3);
    }

    @Override
    public void onActivate() {
        this.lastWasSneaking = this.mc.field_1690.field_1832.method_1434();
        if (this.lastWasSneaking) {
            this.lastSneakingY = this.mc.field_1724.method_23318();
        }
        for (RenderBlock renderBlock : this.renderBlocks) {
            this.renderBlockPool.free(renderBlock);
        }
        this.renderBlocks.clear();
    }

    public static enum ListMode {
        Whitelist,
        Blacklist;

    }

    public static class RenderBlock {
        public class_2338.class_2339 pos = new class_2338.class_2339();
        public int ticks;

        public void render(Color color, Color color2, ShapeMode shapeMode) {
            int n = color.a;
            int n2 = color2.a;
            color.a = (int)((double)color.a * ((double)this.ticks / 8.0));
            color2.a = (int)((double)color2.a * ((double)this.ticks / 8.0));
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)this.pos, color, color2, shapeMode, 0);
            color.a = n;
            color2.a = n2;
        }

        public RenderBlock set(class_2338 class_23382) {
            this.pos.method_10101((class_2382)class_23382);
            this.ticks = 8;
            return this;
        }

        public void tick() {
            --this.ticks;
        }
    }
}

