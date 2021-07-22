/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 */
package minegame159.meteorclient.systems.modules.bedtrap;

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
import net.minecraft.class_2246;
import net.minecraft.class_2338;

public class ButtonTrap
extends Module {
    private boolean placed;
    private final Setting<SettingColor> lineColor;
    private final Setting<Boolean> render;
    private final Setting<SettingColor> nextLineColor;
    private final Setting<Integer> delay;
    private final Setting<Boolean> selfToggle;
    private final Setting<Integer> range;
    private final Setting<Boolean> antiPyramid;
    private final List<class_2338> placePositions;
    private final SettingGroup sgRender;
    private final Setting<SettingColor> sideColor;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SettingColor> nextSideColor;
    private final Setting<Boolean> rotate;
    private class_1657 target;
    private final Setting<SortPriority> priority;
    private int timer;
    private final SettingGroup sgGeneral;

    private void add(class_2338 class_23382) {
        if (!this.placePositions.contains((Object)class_23382) && BlockUtils.canPlace(class_23382)) {
            this.placePositions.add(class_23382);
        }
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    @Override
    public void onActivate() {
        this.target = null;
        this.placePositions.clear();
        this.timer = 0;
        this.placed = false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        FindItemResult findItemResult = InvUtils.findInHotbar(class_2246.field_10278.method_8389());
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10057.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10066.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10417.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10553.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10493.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_22100.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_22101.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_10494.method_8389());
        }
        if (!findItemResult.found()) {
            findItemResult = InvUtils.findInHotbar(class_2246.field_23864.method_8389());
        }
        if (this.selfToggle.get().booleanValue() && this.placed && this.placePositions.isEmpty()) {
            this.placed = false;
            this.toggle();
            return;
        }
        if (!findItemResult.found()) {
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
                this.placePositions.remove((Object)class_23382);
                this.placed = true;
            }
            this.timer = 0;
        } else {
            ++this.timer;
        }
    }

    public ButtonTrap() {
        super(Categories.BedTrap, "button-trap", "Anti Surround.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.antiPyramid = this.sgGeneral.add(new BoolSetting.Builder().name("anti-pyramid").description("yes.").defaultValue(false).build());
        this.range = this.sgGeneral.add(new IntSetting.Builder().name("target-range").description("The range players can be targeted.").defaultValue(4).build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).build());
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

    @Override
    public void onDeactivate() {
        this.placePositions.clear();
    }

    private void fillPlaceArray(class_1657 class_16572) {
        this.placePositions.clear();
        class_2338 class_23382 = class_16572.method_24515();
        this.add(class_23382.method_10069(1, 0, 0));
        this.add(class_23382.method_10069(0, 0, 1));
        this.add(class_23382.method_10069(-1, 0, 0));
        this.add(class_23382.method_10069(0, 0, -1));
        if (this.antiPyramid.get().booleanValue()) {
            this.add(class_23382.method_10069(2, 0, 0));
            this.add(class_23382.method_10069(0, 0, 2));
            this.add(class_23382.method_10069(-2, 0, 0));
            this.add(class_23382.method_10069(0, 0, -2));
        }
    }

    public static enum TopMode {
        Full,
        Top,
        Face,
        None;

    }

    public static enum BottomMode {
        Single,
        Platform,
        Full,
        None;

    }
}

