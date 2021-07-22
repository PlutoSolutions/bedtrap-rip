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
    private /* synthetic */ boolean placed;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<SettingColor> nextLineColor;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> selfToggle;
    private final /* synthetic */ Setting<Integer> range;
    private final /* synthetic */ Setting<Boolean> antiPyramid;
    private final /* synthetic */ List<class_2338> placePositions;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SettingColor> nextSideColor;
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<SortPriority> priority;
    private /* synthetic */ int timer;
    private final /* synthetic */ SettingGroup sgGeneral;

    private void add(class_2338 llllllllllllllllIlllIllIIIlIlIIl) {
        ButtonTrap llllllllllllllllIlllIllIIIlIllII;
        if (!llllllllllllllllIlllIllIIIlIllII.placePositions.contains((Object)llllllllllllllllIlllIllIIIlIlIIl) && BlockUtils.canPlace(llllllllllllllllIlllIllIIIlIlIIl)) {
            llllllllllllllllIlllIllIIIlIllII.placePositions.add(llllllllllllllllIlllIllIIIlIlIIl);
        }
    }

    @Override
    public String getInfoString() {
        ButtonTrap llllllllllllllllIlllIllIIIlIIllI;
        if (llllllllllllllllIlllIllIIIlIIllI.target != null) {
            return llllllllllllllllIlllIllIIIlIIllI.target.method_5820();
        }
        return null;
    }

    @Override
    public void onActivate() {
        ButtonTrap llllllllllllllllIlllIllIIlIIlllI;
        llllllllllllllllIlllIllIIlIIlllI.target = null;
        llllllllllllllllIlllIllIIlIIlllI.placePositions.clear();
        llllllllllllllllIlllIllIIlIIlllI.timer = 0;
        llllllllllllllllIlllIllIIlIIlllI.placed = false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIlllIllIIlIIIlIl) {
        ButtonTrap llllllllllllllllIlllIllIIlIIIllI;
        FindItemResult llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10278.method_8389());
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10057.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10066.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10417.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10553.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10493.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_22100.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_22101.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_10494.method_8389());
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIlII = InvUtils.findInHotbar(class_2246.field_23864.method_8389());
        }
        if (llllllllllllllllIlllIllIIlIIIllI.selfToggle.get().booleanValue() && llllllllllllllllIlllIllIIlIIIllI.placed && llllllllllllllllIlllIllIIlIIIllI.placePositions.isEmpty()) {
            llllllllllllllllIlllIllIIlIIIllI.placed = false;
            llllllllllllllllIlllIllIIlIIIllI.toggle();
            return;
        }
        if (!llllllllllllllllIlllIllIIlIIIlII.found()) {
            llllllllllllllllIlllIllIIlIIIllI.placePositions.clear();
            llllllllllllllllIlllIllIIlIIIllI.placed = false;
            return;
        }
        if (TargetUtils.isBadTarget(llllllllllllllllIlllIllIIlIIIllI.target, llllllllllllllllIlllIllIIlIIIllI.range.get().intValue())) {
            llllllllllllllllIlllIllIIlIIIllI.target = TargetUtils.getPlayerTarget(llllllllllllllllIlllIllIIlIIIllI.range.get().intValue(), llllllllllllllllIlllIllIIlIIIllI.priority.get());
        }
        if (TargetUtils.isBadTarget(llllllllllllllllIlllIllIIlIIIllI.target, llllllllllllllllIlllIllIIlIIIllI.range.get().intValue())) {
            return;
        }
        llllllllllllllllIlllIllIIlIIIllI.fillPlaceArray(llllllllllllllllIlllIllIIlIIIllI.target);
        if (llllllllllllllllIlllIllIIlIIIllI.timer >= llllllllllllllllIlllIllIIlIIIllI.delay.get() && llllllllllllllllIlllIllIIlIIIllI.placePositions.size() > 0) {
            class_2338 llllllllllllllllIlllIllIIlIIIlll = llllllllllllllllIlllIllIIlIIIllI.placePositions.get(llllllllllllllllIlllIllIIlIIIllI.placePositions.size() - 1);
            if (BlockUtils.place(llllllllllllllllIlllIllIIlIIIlll, llllllllllllllllIlllIllIIlIIIlII, llllllllllllllllIlllIllIIlIIIllI.rotate.get(), 50, true)) {
                llllllllllllllllIlllIllIIlIIIllI.placePositions.remove((Object)llllllllllllllllIlllIllIIlIIIlll);
                llllllllllllllllIlllIllIIlIIIllI.placed = true;
            }
            llllllllllllllllIlllIllIIlIIIllI.timer = 0;
        } else {
            ++llllllllllllllllIlllIllIIlIIIllI.timer;
        }
    }

    public ButtonTrap() {
        super(Categories.BedTrap, "button-trap", "Anti Surround.");
        ButtonTrap llllllllllllllllIlllIllIIlIlIIIl;
        llllllllllllllllIlllIllIIlIlIIIl.sgGeneral = llllllllllllllllIlllIllIIlIlIIIl.settings.getDefaultGroup();
        llllllllllllllllIlllIllIIlIlIIIl.sgRender = llllllllllllllllIlllIllIIlIlIIIl.settings.createGroup("Render");
        llllllllllllllllIlllIllIIlIlIIIl.antiPyramid = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("anti-pyramid").description("yes.").defaultValue(false).build());
        llllllllllllllllIlllIllIIlIlIIIl.range = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new IntSetting.Builder().name("target-range").description("The range players can be targeted.").defaultValue(4).build());
        llllllllllllllllIlllIllIIlIlIIIl.priority = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        llllllllllllllllIlllIllIIlIlIIIl.delay = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).build());
        llllllllllllllllIlllIllIIlIlIIIl.selfToggle = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Turns off after placing all blocks.").defaultValue(true).build());
        llllllllllllllllIlllIllIIlIlIIIl.rotate = llllllllllllllllIlllIllIIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates towards blocks when placing.").defaultValue(true).build());
        llllllllllllllllIlllIllIIlIlIIIl.render = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new BoolSetting.Builder().name("render").description("Renders an overlay where blocks will be placed.").defaultValue(true).build());
        llllllllllllllllIlllIllIIlIlIIIl.shapeMode = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llllllllllllllllIlllIllIIlIlIIIl.sideColor = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232, 10)).build());
        llllllllllllllllIlllIllIIlIlIIIl.lineColor = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the target block rendering.").defaultValue(new SettingColor(197, 137, 232)).build());
        llllllllllllllllIlllIllIIlIlIIIl.nextSideColor = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new ColorSetting.Builder().name("next-side-color").description("The side color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245, 10)).build());
        llllllllllllllllIlllIllIIlIlIIIl.nextLineColor = llllllllllllllllIlllIllIIlIlIIIl.sgRender.add(new ColorSetting.Builder().name("next-line-color").description("The line color of the next block to be placed.").defaultValue(new SettingColor(227, 196, 245)).build());
        llllllllllllllllIlllIllIIlIlIIIl.placePositions = new ArrayList<class_2338>();
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllIlllIllIIIlllIll) {
        ButtonTrap llllllllllllllllIlllIllIIIlllIlI;
        if (!llllllllllllllllIlllIllIIIlllIlI.render.get().booleanValue() || llllllllllllllllIlllIllIIIlllIlI.placePositions.isEmpty()) {
            return;
        }
        for (class_2338 llllllllllllllllIlllIllIIIllllIl : llllllllllllllllIlllIllIIIlllIlI.placePositions) {
            if (llllllllllllllllIlllIllIIIllllIl.equals((Object)llllllllllllllllIlllIllIIIlllIlI.placePositions.get(llllllllllllllllIlllIllIIIlllIlI.placePositions.size() - 1))) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlllIllIIIllllIl, llllllllllllllllIlllIllIIIlllIlI.nextSideColor.get(), llllllllllllllllIlllIllIIIlllIlI.nextLineColor.get(), llllllllllllllllIlllIllIIIlllIlI.shapeMode.get(), 0);
                continue;
            }
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlllIllIIIllllIl, llllllllllllllllIlllIllIIIlllIlI.sideColor.get(), llllllllllllllllIlllIllIIIlllIlI.lineColor.get(), llllllllllllllllIlllIllIIIlllIlI.shapeMode.get(), 0);
        }
    }

    @Override
    public void onDeactivate() {
        ButtonTrap llllllllllllllllIlllIllIIlIIlIll;
        llllllllllllllllIlllIllIIlIIlIll.placePositions.clear();
    }

    private void fillPlaceArray(class_1657 llllllllllllllllIlllIllIIIllIIll) {
        ButtonTrap llllllllllllllllIlllIllIIIllIlII;
        llllllllllllllllIlllIllIIIllIlII.placePositions.clear();
        class_2338 llllllllllllllllIlllIllIIIllIIlI = llllllllllllllllIlllIllIIIllIIll.method_24515();
        llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(1, 0, 0));
        llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(0, 0, 1));
        llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(-1, 0, 0));
        llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(0, 0, -1));
        if (llllllllllllllllIlllIllIIIllIlII.antiPyramid.get().booleanValue()) {
            llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(2, 0, 0));
            llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(0, 0, 2));
            llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(-2, 0, 0));
            llllllllllllllllIlllIllIIIllIlII.add(llllllllllllllllIlllIllIIIllIIlI.method_10069(0, 0, -2));
        }
    }

    public static enum TopMode {
        Full,
        Top,
        Face,
        None;


        private TopMode() {
            TopMode lllllllllllllllllIlllIllllllIIll;
        }
    }

    public static enum BottomMode {
        Single,
        Platform,
        Full,
        None;


        private BottomMode() {
            BottomMode lIllIlIlIIllIII;
        }
    }
}

