/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1747
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2346
 *  net.minecraft.class_2382
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
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
    private final /* synthetic */ List<RenderBlock> renderBlocks;
    private final /* synthetic */ Setting<Boolean> renderSwing;
    private final /* synthetic */ Setting<ListMode> blocksFilter;
    private /* synthetic */ double lastSneakingY;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    private final /* synthetic */ Pool<RenderBlock> renderBlockPool;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private /* synthetic */ boolean lastWasSneaking;
    private final /* synthetic */ Setting<Boolean> fastTower;

    @Override
    public void onDeactivate() {
        Scaffold lllllllllllllllllIllIlllllllllII;
        for (RenderBlock lllllllllllllllllIllIlllllllllIl : lllllllllllllllllIllIlllllllllII.renderBlocks) {
            lllllllllllllllllIllIlllllllllII.renderBlockPool.free(lllllllllllllllllIllIlllllllllIl);
        }
        lllllllllllllllllIllIlllllllllII.renderBlocks.clear();
    }

    public Scaffold() {
        super(Categories.Movement, "scaffold", "Automatically places blocks under you.");
        Scaffold lllllllllllllllllIlllIIIIIIIIIIl;
        lllllllllllllllllIlllIIIIIIIIIIl.sgRender = lllllllllllllllllIlllIIIIIIIIIIl.settings.createGroup("Render");
        lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral = lllllllllllllllllIlllIIIIIIIIIIl.settings.getDefaultGroup();
        lllllllllllllllllIlllIIIIIIIIIIl.blocks = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Selected blocks.").defaultValue(new ArrayList<class_2248>()).build());
        lllllllllllllllllIlllIIIIIIIIIIl.blocksFilter = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new EnumSetting.Builder().name("blocks-filter").description("How to use the block list setting").defaultValue(ListMode.Blacklist).build());
        lllllllllllllllllIlllIIIIIIIIIIl.fastTower = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("fast-tower").description("Whether or not to scaffold upwards faster.").defaultValue(false).build());
        lllllllllllllllllIlllIIIIIIIIIIl.renderSwing = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Renders your client-side swing.").defaultValue(false).build());
        lllllllllllllllllIlllIIIIIIIIIIl.autoSwitch = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("auto-switch").description("Automatically swaps to a block before placing.").defaultValue(true).build());
        lllllllllllllllllIlllIIIIIIIIIIl.shapeMode = lllllllllllllllllIlllIIIIIIIIIIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIlllIIIIIIIIIIl.rotate = lllllllllllllllllIlllIIIIIIIIIIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards the blocks being placed.").defaultValue(true).build());
        lllllllllllllllllIlllIIIIIIIIIIl.sideColor = lllllllllllllllllIlllIIIIIIIIIIl.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        lllllllllllllllllIlllIIIIIIIIIIl.lineColor = lllllllllllllllllIlllIIIIIIIIIIl.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        lllllllllllllllllIlllIIIIIIIIIIl.renderBlockPool = new Pool<RenderBlock>(RenderBlock::new);
        lllllllllllllllllIlllIIIIIIIIIIl.renderBlocks = new ArrayList<RenderBlock>();
        lllllllllllllllllIlllIIIIIIIIIIl.blockPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIllIlllllllIlIl) {
        Scaffold lllllllllllllllllIllIlllllllIllI;
        lllllllllllllllllIllIlllllllIllI.renderBlocks.forEach(RenderBlock::tick);
        lllllllllllllllllIllIlllllllIllI.renderBlocks.removeIf(lllllllllllllllllIllIlllllIlIIII -> lllllllllllllllllIllIlllllIlIIII.ticks <= 0);
        lllllllllllllllllIllIlllllllIllI.blockPos.method_10101((class_2382)lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_24515().method_10074());
        FindItemResult lllllllllllllllllIllIlllllllIlII = InvUtils.findInHotbar(lllllllllllllllllIllIlllllIlIIll -> {
            Scaffold lllllllllllllllllIllIlllllIlIlII;
            return lllllllllllllllllIllIlllllIlIlII.validItem((class_1799)lllllllllllllllllIllIlllllIlIIll, (class_2338)lllllllllllllllllIllIlllllIlIlII.blockPos);
        });
        if (!lllllllllllllllllIllIlllllllIlII.found()) {
            return;
        }
        if (lllllllllllllllllIllIlllllllIlII.getHand() == null) {
            if (lllllllllllllllllIllIlllllllIllI.autoSwitch.get().booleanValue()) {
                InvUtils.swap(lllllllllllllllllIllIlllllllIlII.getSlot());
            } else {
                return;
            }
        }
        if (lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1832.method_1434() && !lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1903.method_1434()) {
            if (lllllllllllllllllIllIlllllllIllI.lastSneakingY - lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_23318() < 0.1) {
                lllllllllllllllllIllIlllllllIllI.lastWasSneaking = false;
                return;
            }
        } else {
            lllllllllllllllllIllIlllllllIllI.lastWasSneaking = false;
        }
        if (!lllllllllllllllllIllIlllllllIllI.lastWasSneaking) {
            lllllllllllllllllIllIlllllllIllI.lastSneakingY = lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_23318();
        }
        if (lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1903.method_1434() && !lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1832.method_1434() && lllllllllllllllllIllIlllllllIllI.fastTower.get().booleanValue()) {
            lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_18800(0.0, (double)0.42f, 0.0);
        }
        if (BlockUtils.place((class_2338)lllllllllllllllllIllIlllllllIllI.blockPos, lllllllllllllllllIllIlllllllIlII, lllllllllllllllllIllIlllllllIllI.rotate.get(), 50, lllllllllllllllllIllIlllllllIllI.renderSwing.get(), true)) {
            lllllllllllllllllIllIlllllllIllI.renderBlocks.add(lllllllllllllllllIllIlllllllIllI.renderBlockPool.get().set((class_2338)lllllllllllllllllIllIlllllllIllI.blockPos));
            if (lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1903.method_1434() && !lllllllllllllllllIllIlllllllIllI.mc.field_1690.field_1832.method_1434() && !lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_24828() && !lllllllllllllllllIllIlllllllIllI.mc.field_1687.method_8320((class_2338)lllllllllllllllllIllIlllllllIllI.blockPos).method_26215() && lllllllllllllllllIllIlllllllIllI.fastTower.get().booleanValue()) {
                lllllllllllllllllIllIlllllllIllI.mc.field_1724.method_18800(0.0, (double)-0.28f, 0.0);
            }
        }
    }

    private boolean validItem(class_1799 lllllllllllllllllIllIllllllIllII, class_2338 lllllllllllllllllIllIllllllIIlll) {
        Scaffold lllllllllllllllllIllIllllllIlIIl;
        if (!(lllllllllllllllllIllIllllllIllII.method_7909() instanceof class_1747)) {
            return false;
        }
        class_2248 lllllllllllllllllIllIllllllIlIlI = ((class_1747)lllllllllllllllllIllIllllllIllII.method_7909()).method_7711();
        if (lllllllllllllllllIllIllllllIlIIl.blocksFilter.get() == ListMode.Blacklist && lllllllllllllllllIllIllllllIlIIl.blocks.get().contains((Object)lllllllllllllllllIllIllllllIlIlI)) {
            return false;
        }
        if (lllllllllllllllllIllIllllllIlIIl.blocksFilter.get() == ListMode.Whitelist && !lllllllllllllllllIllIllllllIlIIl.blocks.get().contains((Object)lllllllllllllllllIllIllllllIlIlI)) {
            return false;
        }
        if (!class_2248.method_9614((class_265)lllllllllllllllllIllIllllllIlIlI.method_9564().method_26220((class_1922)lllllllllllllllllIllIllllllIlIIl.mc.field_1687, lllllllllllllllllIllIllllllIIlll))) {
            return false;
        }
        return !(lllllllllllllllllIllIllllllIlIlI instanceof class_2346) || !class_2346.method_10128((class_2680)lllllllllllllllllIllIllllllIlIIl.mc.field_1687.method_8320(lllllllllllllllllIllIllllllIIlll));
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIllIllllllIIIll) {
        Scaffold lllllllllllllllllIllIllllllIIlII;
        lllllllllllllllllIllIllllllIIlII.renderBlocks.sort(Comparator.comparingInt(lllllllllllllllllIllIlllllIllIIl -> -lllllllllllllllllIllIlllllIllIIl.ticks));
        lllllllllllllllllIllIllllllIIlII.renderBlocks.forEach(lllllllllllllllllIllIlllllIlllII -> {
            Scaffold lllllllllllllllllIllIlllllIlllll;
            lllllllllllllllllIllIlllllIlllII.render(lllllllllllllllllIllIlllllIlllll.sideColor.get(), lllllllllllllllllIllIlllllIlllll.lineColor.get(), lllllllllllllllllIllIlllllIlllll.shapeMode.get());
        });
    }

    @Override
    public void onActivate() {
        Scaffold lllllllllllllllllIlllIIIIIIIIllI;
        lllllllllllllllllIlllIIIIIIIIllI.lastWasSneaking = lllllllllllllllllIlllIIIIIIIIllI.mc.field_1690.field_1832.method_1434();
        if (lllllllllllllllllIlllIIIIIIIIllI.lastWasSneaking) {
            lllllllllllllllllIlllIIIIIIIIllI.lastSneakingY = lllllllllllllllllIlllIIIIIIIIllI.mc.field_1724.method_23318();
        }
        for (RenderBlock lllllllllllllllllIlllIIIIIIIlIII : lllllllllllllllllIlllIIIIIIIIllI.renderBlocks) {
            lllllllllllllllllIlllIIIIIIIIllI.renderBlockPool.free(lllllllllllllllllIlllIIIIIIIlIII);
        }
        lllllllllllllllllIlllIIIIIIIIllI.renderBlocks.clear();
    }

    public static enum ListMode {
        Whitelist,
        Blacklist;


        private ListMode() {
            ListMode llIlllIIIllIll;
        }
    }

    public static class RenderBlock {
        public /* synthetic */ class_2338.class_2339 pos;
        public /* synthetic */ int ticks;

        public RenderBlock() {
            RenderBlock llllllllllllllllIlllIIIlIIllIIlI;
            llllllllllllllllIlllIIIlIIllIIlI.pos = new class_2338.class_2339();
        }

        public void render(Color llllllllllllllllIlllIIIlIIlIIIII, Color llllllllllllllllIlllIIIlIIIllIIl, ShapeMode llllllllllllllllIlllIIIlIIIllllI) {
            RenderBlock llllllllllllllllIlllIIIlIIIllIll;
            int llllllllllllllllIlllIIIlIIIlllIl = llllllllllllllllIlllIIIlIIlIIIII.a;
            int llllllllllllllllIlllIIIlIIIlllII = llllllllllllllllIlllIIIlIIIllIIl.a;
            llllllllllllllllIlllIIIlIIlIIIII.a = (int)((double)llllllllllllllllIlllIIIlIIlIIIII.a * ((double)llllllllllllllllIlllIIIlIIIllIll.ticks / 8.0));
            llllllllllllllllIlllIIIlIIIllIIl.a = (int)((double)llllllllllllllllIlllIIIlIIIllIIl.a * ((double)llllllllllllllllIlllIIIlIIIllIll.ticks / 8.0));
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)llllllllllllllllIlllIIIlIIIllIll.pos, llllllllllllllllIlllIIIlIIlIIIII, llllllllllllllllIlllIIIlIIIllIIl, llllllllllllllllIlllIIIlIIIllllI, 0);
            llllllllllllllllIlllIIIlIIlIIIII.a = llllllllllllllllIlllIIIlIIIlllIl;
            llllllllllllllllIlllIIIlIIIllIIl.a = llllllllllllllllIlllIIIlIIIlllII;
        }

        public RenderBlock set(class_2338 llllllllllllllllIlllIIIlIIlIlIll) {
            RenderBlock llllllllllllllllIlllIIIlIIlIlllI;
            llllllllllllllllIlllIIIlIIlIlllI.pos.method_10101((class_2382)llllllllllllllllIlllIIIlIIlIlIll);
            llllllllllllllllIlllIIIlIIlIlllI.ticks = 8;
            return llllllllllllllllIlllIIIlIIlIlllI;
        }

        public void tick() {
            RenderBlock llllllllllllllllIlllIIIlIIlIlIIl;
            --llllllllllllllllIlllIIIlIIlIlIIl.ticks;
        }
    }
}

