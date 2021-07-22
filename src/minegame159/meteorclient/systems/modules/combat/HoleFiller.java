/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
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
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2680;

public class HoleFiller
extends Module {
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<SettingColor> nextLineColor;
    private final /* synthetic */ Setting<Integer> verticalRadius;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private /* synthetic */ int timer;
    private final /* synthetic */ Pool<Hole> holePool;
    private final /* synthetic */ List<Hole> holes;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ Setting<SettingColor> nextSideColor;
    private final /* synthetic */ Setting<Boolean> doubles;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Integer> horizontalRadius;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ byte NULL = 0;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;

    @EventHandler
    private void onTickPost(TickEvent.Post lllllllllllllllllIllIlIlIlIllIlI) {
        HoleFiller lllllllllllllllllIllIlIlIlIllIIl;
        if (lllllllllllllllllIllIlIlIlIllIIl.timer <= 0 && !lllllllllllllllllIllIlIlIlIllIIl.holes.isEmpty()) {
            FindItemResult lllllllllllllllllIllIlIlIlIlllII = InvUtils.findInHotbar(lllllllllllllllllIllIlIlIlIIIlIl -> {
                HoleFiller lllllllllllllllllIllIlIlIlIIIlII;
                return lllllllllllllllllIllIlIlIlIIIlIl.method_7909() instanceof class_1747 && lllllllllllllllllIllIlIlIlIIIlII.blocks.get().contains((Object)class_2248.method_9503((class_1792)lllllllllllllllllIllIlIlIlIIIlIl.method_7909()));
            });
            BlockUtils.place((class_2338)lllllllllllllllllIllIlIlIlIllIIl.holes.get((int)0).blockPos, lllllllllllllllllIllIlIlIlIlllII, lllllllllllllllllIllIlIlIlIllIIl.rotate.get(), 10, true);
            lllllllllllllllllIllIlIlIlIllIIl.timer = lllllllllllllllllIllIlIlIlIllIIl.placeDelay.get();
        }
        --lllllllllllllllllIllIlIlIlIllIIl.timer;
    }

    @EventHandler(priority=100)
    private void onRender(RenderEvent lllllllllllllllllIllIlIlIlIIllII) {
        HoleFiller lllllllllllllllllIllIlIlIlIIlIll;
        if (!lllllllllllllllllIllIlIlIlIIlIll.render.get().booleanValue()) {
            return;
        }
        for (Hole lllllllllllllllllIllIlIlIlIIlllI : lllllllllllllllllIllIlIlIlIIlIll.holes) {
            if (lllllllllllllllllIllIlIlIlIIlllI == lllllllllllllllllIllIlIlIlIIlIll.holes.get(0)) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)lllllllllllllllllIllIlIlIlIIlllI.blockPos, lllllllllllllllllIllIlIlIlIIlIll.nextSideColor.get(), lllllllllllllllllIllIlIlIlIIlIll.nextLineColor.get(), lllllllllllllllllIllIlIlIlIIlIll.shapeMode.get(), lllllllllllllllllIllIlIlIlIIlllI.exclude);
                continue;
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)lllllllllllllllllIllIlIlIlIIlllI.blockPos, lllllllllllllllllIllIlIlIlIIlIll.sideColor.get(), lllllllllllllllllIllIlIlIlIIlIll.lineColor.get(), lllllllllllllllllIllIlIlIlIIlIll.shapeMode.get(), lllllllllllllllllIllIlIlIlIIlllI.exclude);
        }
    }

    private boolean validHole(class_2338 lllllllllllllllllIllIlIlIlIlIlII) {
        HoleFiller lllllllllllllllllIllIlIlIlIlIIll;
        if (lllllllllllllllllIllIlIlIlIlIIll.mc.field_1724.method_24515().equals((Object)lllllllllllllllllIllIlIlIlIlIlII)) {
            return false;
        }
        if (((AbstractBlockAccessor)lllllllllllllllllIllIlIlIlIlIIll.mc.field_1687.method_8320(lllllllllllllllllIllIlIlIlIlIlII).method_26204()).isCollidable()) {
            return false;
        }
        return !((AbstractBlockAccessor)lllllllllllllllllIllIlIlIlIlIIll.mc.field_1687.method_8320(lllllllllllllllllIllIlIlIlIlIlII.method_10084()).method_26204()).isCollidable();
    }

    @Override
    public void onActivate() {
        lllllllllllllllllIllIlIlIllIlIlI.timer = 0;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIllIlIlIllIIIll) {
        HoleFiller lllllllllllllllllIllIlIlIllIIlII;
        for (Hole lllllllllllllllllIllIlIlIllIIlIl : lllllllllllllllllIllIlIlIllIIlII.holes) {
            lllllllllllllllllIllIlIlIllIIlII.holePool.free(lllllllllllllllllIllIlIlIllIIlIl);
        }
        lllllllllllllllllIllIlIlIllIIlII.holes.clear();
        FindItemResult lllllllllllllllllIllIlIlIllIIIlI = InvUtils.findInHotbar(lllllllllllllllllIllIlIlIIIlIlIl -> {
            HoleFiller lllllllllllllllllIllIlIlIIIlIllI;
            return lllllllllllllllllIllIlIlIIIlIlIl.method_7909() instanceof class_1747 && lllllllllllllllllIllIlIlIIIlIllI.blocks.get().contains((Object)class_2248.method_9503((class_1792)lllllllllllllllllIllIlIlIIIlIlIl.method_7909()));
        });
        if (!lllllllllllllllllIllIlIlIllIIIlI.found()) {
            return;
        }
        BlockIterator.register(lllllllllllllllllIllIlIlIllIIlII.horizontalRadius.get(), lllllllllllllllllIllIlIlIllIIlII.verticalRadius.get(), (lllllllllllllllllIllIlIlIIlIlllI, lllllllllllllllllIllIlIlIIlIllIl) -> {
            HoleFiller lllllllllllllllllIllIlIlIIlIlIIl;
            if (!lllllllllllllllllIllIlIlIIlIlIIl.validHole((class_2338)lllllllllllllllllIllIlIlIIlIlllI)) {
                return;
            }
            int lllllllllllllllllIllIlIlIIlIllII = 0;
            int lllllllllllllllllIllIlIlIIlIlIll = 0;
            class_2350 lllllllllllllllllIllIlIlIIlIlIlI = null;
            for (class_2350 lllllllllllllllllIllIlIlIIllIIII : class_2350.values()) {
                if (lllllllllllllllllIllIlIlIIllIIII == class_2350.field_11036) continue;
                class_2680 lllllllllllllllllIllIlIlIIllIIIl = lllllllllllllllllIllIlIlIIlIlIIl.mc.field_1687.method_8320(lllllllllllllllllIllIlIlIIlIlllI.method_10093(lllllllllllllllllIllIlIlIIllIIII));
                if (lllllllllllllllllIllIlIlIIllIIIl.method_26204() == class_2246.field_9987) {
                    ++lllllllllllllllllIllIlIlIIlIllII;
                    continue;
                }
                if (lllllllllllllllllIllIlIlIIllIIIl.method_26204() == class_2246.field_10540) {
                    ++lllllllllllllllllIllIlIlIIlIlIll;
                    continue;
                }
                if (lllllllllllllllllIllIlIlIIllIIII == class_2350.field_11033) {
                    return;
                }
                if (!lllllllllllllllllIllIlIlIIlIlIIl.validHole(lllllllllllllllllIllIlIlIIlIlllI.method_10093(lllllllllllllllllIllIlIlIIllIIII)) || lllllllllllllllllIllIlIlIIlIlIlI != null) continue;
                for (class_2350 lllllllllllllllllIllIlIlIIllIIlI : class_2350.values()) {
                    if (lllllllllllllllllIllIlIlIIllIIlI == lllllllllllllllllIllIlIlIIllIIII.method_10153() || lllllllllllllllllIllIlIlIIllIIlI == class_2350.field_11036) continue;
                    class_2680 lllllllllllllllllIllIlIlIIllIIll = lllllllllllllllllIllIlIlIIlIlIIl.mc.field_1687.method_8320(lllllllllllllllllIllIlIlIIlIlllI.method_10093(lllllllllllllllllIllIlIlIIllIIII).method_10093(lllllllllllllllllIllIlIlIIllIIlI));
                    if (lllllllllllllllllIllIlIlIIllIIll.method_26204() == class_2246.field_9987) {
                        ++lllllllllllllllllIllIlIlIIlIllII;
                        continue;
                    }
                    if (lllllllllllllllllIllIlIlIIllIIll.method_26204() == class_2246.field_10540) {
                        ++lllllllllllllllllIllIlIlIIlIlIll;
                        continue;
                    }
                    return;
                }
                lllllllllllllllllIllIlIlIIlIlIlI = lllllllllllllllllIllIlIlIIllIIII;
            }
            if (lllllllllllllllllIllIlIlIIlIlIll + lllllllllllllllllIllIlIlIIlIllII == 5 && lllllllllllllllllIllIlIlIIlIlIlI == null) {
                lllllllllllllllllIllIlIlIIlIlIIl.holes.add(lllllllllllllllllIllIlIlIIlIlIIl.holePool.get().set((class_2338)lllllllllllllllllIllIlIlIIlIlllI, (byte)0));
            } else if (lllllllllllllllllIllIlIlIIlIlIll + lllllllllllllllllIllIlIlIIlIllII == 8 && lllllllllllllllllIllIlIlIIlIlIIl.doubles.get().booleanValue() && lllllllllllllllllIllIlIlIIlIlIlI != null) {
                lllllllllllllllllIllIlIlIIlIlIIl.holes.add(lllllllllllllllllIllIlIlIIlIlIIl.holePool.get().set((class_2338)lllllllllllllllllIllIlIlIIlIlllI, Dir.get(lllllllllllllllllIllIlIlIIlIlIlI)));
            }
        });
    }

    public HoleFiller() {
        super(Categories.Combat, "hole-filler", "Fills holes with specified blocks.");
        HoleFiller lllllllllllllllllIllIlIlIllIllII;
        lllllllllllllllllIllIlIlIllIllII.sgGeneral = lllllllllllllllllIllIlIlIllIllII.settings.getDefaultGroup();
        lllllllllllllllllIllIlIlIllIllII.sgRender = lllllllllllllllllIllIlIlIllIllII.settings.createGroup("Render");
        lllllllllllllllllIllIlIlIllIllII.blocks = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Which blocks can be used to fill holes.").defaultValue(Collections.singletonList(class_2246.field_10540)).build());
        lllllllllllllllllIllIlIlIllIllII.horizontalRadius = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new IntSetting.Builder().name("horizontal-radius").description("Horizontal radius in which to search for holes.").defaultValue(4).min(0).sliderMax(6).build());
        lllllllllllllllllIllIlIlIllIllII.verticalRadius = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new IntSetting.Builder().name("vertical-radius").description("Vertical radius in which to search for holes.").defaultValue(4).min(0).sliderMax(6).build());
        lllllllllllllllllIllIlIlIllIllII.doubles = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new BoolSetting.Builder().name("doubles").description("Fills double holes.").defaultValue(true).build());
        lllllllllllllllllIllIlIlIllIllII.placeDelay = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new IntSetting.Builder().name("delay").description("The ticks delay between placement.").defaultValue(1).min(0).sliderMax(10).build());
        lllllllllllllllllIllIlIlIllIllII.rotate = lllllllllllllllllIllIlIlIllIllII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates towards the holes being filled.").defaultValue(true).build());
        lllllllllllllllllIllIlIlIllIllII.render = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new BoolSetting.Builder().name("render").description("Renders an overlay where blocks will be placed.").defaultValue(true).build());
        lllllllllllllllllIllIlIlIllIllII.shapeMode = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllIllIlIlIllIllII.sideColor = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        lllllllllllllllllIllIlIlIllIllII.lineColor = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        lllllllllllllllllIllIlIlIllIllII.nextSideColor = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new ColorSetting.Builder().name("next-side-color").description("The side color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245, 10)).build());
        lllllllllllllllllIllIlIlIllIllII.nextLineColor = lllllllllllllllllIllIlIlIllIllII.sgRender.add(new ColorSetting.Builder().name("next-line-color").description("The line color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245)).build());
        lllllllllllllllllIllIlIlIllIllII.holePool = new Pool<Hole>(() -> new Hole());
        lllllllllllllllllIllIlIlIllIllII.holes = new ArrayList<Hole>();
        lllllllllllllllllIllIlIlIllIllII.NULL = 0;
    }

    private static class Hole {
        public /* synthetic */ class_2338.class_2339 blockPos;
        public /* synthetic */ byte exclude;

        private Hole() {
            Hole lllllIIlIll;
            lllllIIlIll.blockPos = new class_2338.class_2339();
        }

        public Hole set(class_2338 lllllIIIIll, byte lllllIIIIlI) {
            Hole lllllIIIlll;
            lllllIIIlll.blockPos.method_10101((class_2382)lllllIIIIll);
            lllllIIIlll.exclude = lllllIIIIlI;
            return lllllIIIlll;
        }
    }
}

