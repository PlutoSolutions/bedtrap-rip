/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Boolean> rotate;
    private class_1657 target;
    private final Setting<SettingColor> nextLineColor;
    private final Setting<Boolean> render;
    private int timer;
    private final SettingGroup sgRender;
    private final Setting<Integer> range;
    private final Setting<TopMode> topPlacement;
    private final Setting<SettingColor> lineColor;
    private final Setting<SettingColor> nextSideColor;
    private final Setting<BottomMode> bottomPlacement;
    private final Setting<Integer> delay;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<Boolean> selfToggle;
    private final Setting<SettingColor> sideColor;
    private boolean placed;
    private final Setting<SortPriority> priority;
    private final SettingGroup sgGeneral;
    private final List<class_2338> placePositions;

    public AutoTrap() {
        super(Categories.Combat, "auto-trap", "Traps people in an obsidian box to prevent them from moving.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.range = this.sgGeneral.add(new IntSetting.Builder().name("target-range").description("The range players can be targeted.").defaultValue(4).build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).build());
        this.topPlacement = this.sgGeneral.add(new EnumSetting.Builder().name("top-blocks").description("Which blocks to place on the top half of the target.").defaultValue(TopMode.Full).build());
        this.bottomPlacement = this.sgGeneral.add(new EnumSetting.Builder().name("bottom-blocks").description("Which blocks to place on the bottom half of the target.").defaultValue(BottomMode.Platform).build());
        this.selfToggle = this.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Turns off after placing all blocks.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards blocks when placing.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders an overlay where blocks will be placed.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        this.nextSideColor = this.sgRender.add(new ColorSetting.Builder().name("next-side-color").description("The side color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245, 10)).build());
        this.nextLineColor = this.sgRender.add(new ColorSetting.Builder().name("next-line-color").description("The line color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245)).build());
        this.placePositions = new ArrayList<class_2338>();
    }

    @Override
    public void onActivate() {
        this.target = null;
        this.placePositions.clear();
        this.timer = 0;
        this.placed = false;
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    @Override
    public void onDeactivate() {
        this.placePositions.clear();
    }

    private void add(class_2338 class_23382) {
        if (!this.placePositions.contains(class_23382) && BlockUtils.canPlace(class_23382)) {
            this.placePositions.add(class_23382);
        }
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (!this.render.get().booleanValue() || this.placePositions.isEmpty()) {
            return;
        }
        for (class_2338 class_23382 : this.placePositions) {
            if (class_23382.equals((Object)this.placePositions.get(this.placePositions.size() - 1))) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, class_23382, this.nextSideColor.get(), this.nextLineColor.get(), this.shapeMode.get(), 0);
                continue;
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, class_23382, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
        }
    }

    private void fillPlaceArray(class_1657 class_16572) {
        this.placePositions.clear();
        class_2338 class_23382 = class_16572.method_24515();
        switch (this.topPlacement.get()) {
            case Full: {
                this.add(class_23382.method_10069(0, 2, 0));
                this.add(class_23382.method_10069(1, 1, 0));
                this.add(class_23382.method_10069(-1, 1, 0));
                this.add(class_23382.method_10069(0, 1, 1));
                this.add(class_23382.method_10069(0, 1, -1));
                break;
            }
            case Face: {
                this.add(class_23382.method_10069(1, 1, 0));
                this.add(class_23382.method_10069(-1, 1, 0));
                this.add(class_23382.method_10069(0, 1, 1));
                this.add(class_23382.method_10069(0, 1, -1));
                break;
            }
            case Top: {
                this.add(class_23382.method_10069(0, 2, 0));
            }
        }
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$AutoTrap$BottomMode[this.bottomPlacement.get().ordinal()]) {
            case 1: {
                this.add(class_23382.method_10069(0, -1, 0));
                this.add(class_23382.method_10069(1, -1, 0));
                this.add(class_23382.method_10069(0, -1, 0));
                this.add(class_23382.method_10069(0, -1, 1));
                this.add(class_23382.method_10069(0, -1, -1));
                break;
            }
            case 2: {
                this.add(class_23382.method_10069(1, 0, 0));
                this.add(class_23382.method_10069(-1, 0, 0));
                this.add(class_23382.method_10069(0, 0, -1));
                this.add(class_23382.method_10069(0, 0, 1));
                break;
            }
            case 3: {
                this.add(class_23382.method_10069(0, -1, 0));
            }
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.selfToggle.get().booleanValue() && this.placed && this.placePositions.isEmpty()) {
            this.placed = false;
            this.toggle();
            return;
        }
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8281);
        if (!findItemResult.isHotbar() && !findItemResult.isOffhand()) {
            this.placePositions.clear();
            this.placed = false;
            return;
        }
        if (TargetUtils.isBadTarget(this.target, this.range.get().intValue())) {
            this.target = TargetUtils.getPlayerTarget(this.range.get().intValue(), this.priority.get());
        }
        if (TargetUtils.isBadTarget(this.target, this.range.get().intValue())) {
            return;
        }
        this.fillPlaceArray(this.target);
        if (this.timer >= this.delay.get() && this.placePositions.size() > 0) {
            class_2338 class_23382 = this.placePositions.get(this.placePositions.size() - 1);
            if (BlockUtils.place(class_23382, findItemResult, this.rotate.get(), 50, true)) {
                this.placePositions.remove(class_23382);
                this.placed = true;
            }
            this.timer = 0;
        } else {
            ++this.timer;
        }
    }

    public static enum BottomMode {
        Single,
        Platform,
        Full,
        None;

    }

    public static enum TopMode {
        Full,
        Top,
        Face,
        None;

    }
}

