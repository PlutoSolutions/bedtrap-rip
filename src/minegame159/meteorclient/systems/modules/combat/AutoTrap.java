/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 */
package minegame159.meteorclient.systems.modules.combat;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
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
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;

public class AutoTrap
extends Module {
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<SettingColor> nextLineColor;
    private final /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ int timer;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Integer> range;
    private final /* synthetic */ Setting<TopMode> topPlacement;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<SettingColor> nextSideColor;
    private final /* synthetic */ Setting<BottomMode> bottomPlacement;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Boolean> selfToggle;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private /* synthetic */ boolean placed;
    private final /* synthetic */ Setting<SortPriority> priority;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ List<class_2338> placePositions;

    public AutoTrap() {
        super(Categories.Combat, "auto-trap", "Traps people in an obsidian box to prevent them from moving.");
        AutoTrap lIllIIlIIlIIlIl;
        lIllIIlIIlIIlIl.sgGeneral = lIllIIlIIlIIlIl.settings.getDefaultGroup();
        lIllIIlIIlIIlIl.sgRender = lIllIIlIIlIIlIl.settings.createGroup("Render");
        lIllIIlIIlIIlIl.range = lIllIIlIIlIIlIl.sgGeneral.add(new IntSetting.Builder().name("target-range").description("The range players can be targeted.").defaultValue(4).build());
        lIllIIlIIlIIlIl.priority = lIllIIlIIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        lIllIIlIIlIIlIl.delay = lIllIIlIIlIIlIl.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).build());
        lIllIIlIIlIIlIl.topPlacement = lIllIIlIIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("top-blocks").description("Which blocks to place on the top half of the target.").defaultValue(TopMode.Full).build());
        lIllIIlIIlIIlIl.bottomPlacement = lIllIIlIIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("bottom-blocks").description("Which blocks to place on the bottom half of the target.").defaultValue(BottomMode.Platform).build());
        lIllIIlIIlIIlIl.selfToggle = lIllIIlIIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Turns off after placing all blocks.").defaultValue(true).build());
        lIllIIlIIlIIlIl.rotate = lIllIIlIIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards blocks when placing.").defaultValue(true).build());
        lIllIIlIIlIIlIl.render = lIllIIlIIlIIlIl.sgRender.add(new BoolSetting.Builder().name("render").description("Renders an overlay where blocks will be placed.").defaultValue(true).build());
        lIllIIlIIlIIlIl.shapeMode = lIllIIlIIlIIlIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIllIIlIIlIIlIl.sideColor = lIllIIlIIlIIlIl.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        lIllIIlIIlIIlIl.lineColor = lIllIIlIIlIIlIl.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        lIllIIlIIlIIlIl.nextSideColor = lIllIIlIIlIIlIl.sgRender.add(new ColorSetting.Builder().name("next-side-color").description("The side color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245, 10)).build());
        lIllIIlIIlIIlIl.nextLineColor = lIllIIlIIlIIlIl.sgRender.add(new ColorSetting.Builder().name("next-line-color").description("The line color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245)).build());
        lIllIIlIIlIIlIl.placePositions = new ArrayList<class_2338>();
    }

    @Override
    public void onActivate() {
        AutoTrap lIllIIlIIlIIIlI;
        lIllIIlIIlIIIlI.target = null;
        lIllIIlIIlIIIlI.placePositions.clear();
        lIllIIlIIlIIIlI.timer = 0;
        lIllIIlIIlIIIlI.placed = false;
    }

    @Override
    public String getInfoString() {
        AutoTrap lIllIIIlllllIlI;
        if (lIllIIIlllllIlI.target != null) {
            return lIllIIIlllllIlI.target.method_5820();
        }
        return null;
    }

    @Override
    public void onDeactivate() {
        AutoTrap lIllIIlIIIllllI;
        lIllIIlIIIllllI.placePositions.clear();
    }

    private void add(class_2338 lIllIIIllllllII) {
        AutoTrap lIllIIIllllllIl;
        if (!lIllIIIllllllIl.placePositions.contains((Object)lIllIIIllllllII) && BlockUtils.canPlace(lIllIIIllllllII)) {
            lIllIIIllllllIl.placePositions.add(lIllIIIllllllII);
        }
    }

    @EventHandler
    private void onRender(RenderEvent lIllIIlIIIIlllI) {
        AutoTrap lIllIIlIIIIllll;
        if (!lIllIIlIIIIllll.render.get().booleanValue() || lIllIIlIIIIllll.placePositions.isEmpty()) {
            return;
        }
        for (class_2338 lIllIIlIIIlIIII : lIllIIlIIIIllll.placePositions) {
            if (lIllIIlIIIlIIII.equals((Object)lIllIIlIIIIllll.placePositions.get(lIllIIlIIIIllll.placePositions.size() - 1))) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lIllIIlIIIlIIII, lIllIIlIIIIllll.nextSideColor.get(), lIllIIlIIIIllll.nextLineColor.get(), lIllIIlIIIIllll.shapeMode.get(), 0);
                continue;
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lIllIIlIIIlIIII, lIllIIlIIIIllll.sideColor.get(), lIllIIlIIIIllll.lineColor.get(), lIllIIlIIIIllll.shapeMode.get(), 0);
        }
    }

    private void fillPlaceArray(class_1657 lIllIIlIIIIIIll) {
        AutoTrap lIllIIlIIIIIlII;
        lIllIIlIIIIIlII.placePositions.clear();
        class_2338 lIllIIlIIIIIlIl = lIllIIlIIIIIIll.method_24515();
        switch (lIllIIlIIIIIlII.topPlacement.get()) {
            case Full: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 2, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(1, 1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(-1, 1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 1, 1));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 1, -1));
                break;
            }
            case Face: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(1, 1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(-1, 1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 1, 1));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 1, -1));
                break;
            }
            case Top: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 2, 0));
            }
        }
        switch (lIllIIlIIIIIlII.bottomPlacement.get()) {
            case Platform: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, -1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(1, -1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, -1, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, -1, 1));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, -1, -1));
                break;
            }
            case Full: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(1, 0, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(-1, 0, 0));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 0, -1));
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, 0, 1));
                break;
            }
            case Single: {
                lIllIIlIIIIIlII.add(lIllIIlIIIIIlIl.method_10069(0, -1, 0));
            }
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIllIIlIIIllIII) {
        AutoTrap lIllIIlIIIllIIl;
        if (lIllIIlIIIllIIl.selfToggle.get().booleanValue() && lIllIIlIIIllIIl.placed && lIllIIlIIIllIIl.placePositions.isEmpty()) {
            lIllIIlIIIllIIl.placed = false;
            lIllIIlIIIllIIl.toggle();
            return;
        }
        FindItemResult lIllIIlIIIlIlll = InvUtils.findInHotbar(class_1802.field_8281);
        if (!lIllIIlIIIlIlll.isHotbar() && !lIllIIlIIIlIlll.isOffhand()) {
            lIllIIlIIIllIIl.placePositions.clear();
            lIllIIlIIIllIIl.placed = false;
            return;
        }
        if (TargetUtils.isBadTarget(lIllIIlIIIllIIl.target, lIllIIlIIIllIIl.range.get().intValue())) {
            lIllIIlIIIllIIl.target = TargetUtils.getPlayerTarget(lIllIIlIIIllIIl.range.get().intValue(), lIllIIlIIIllIIl.priority.get());
        }
        if (TargetUtils.isBadTarget(lIllIIlIIIllIIl.target, lIllIIlIIIllIIl.range.get().intValue())) {
            return;
        }
        lIllIIlIIIllIIl.fillPlaceArray(lIllIIlIIIllIIl.target);
        if (lIllIIlIIIllIIl.timer >= lIllIIlIIIllIIl.delay.get() && lIllIIlIIIllIIl.placePositions.size() > 0) {
            class_2338 lIllIIlIIIllIlI = lIllIIlIIIllIIl.placePositions.get(lIllIIlIIIllIIl.placePositions.size() - 1);
            if (BlockUtils.place(lIllIIlIIIllIlI, lIllIIlIIIlIlll, lIllIIlIIIllIIl.rotate.get(), 50, true)) {
                lIllIIlIIIllIIl.placePositions.remove((Object)lIllIIlIIIllIlI);
                lIllIIlIIIllIIl.placed = true;
            }
            lIllIIlIIIllIIl.timer = 0;
        } else {
            ++lIllIIlIIIllIIl.timer;
        }
    }

    public static enum BottomMode {
        Single,
        Platform,
        Full,
        None;


        private BottomMode() {
            BottomMode llllllllllllllllllllllIlIlIIIlII;
        }
    }

    public static enum TopMode {
        Full,
        Top,
        Face,
        None;


        private TopMode() {
            TopMode lIlIlIllIIlI;
        }
    }
}

