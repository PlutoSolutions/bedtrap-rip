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
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_3726;

public class SelfTrap
extends Module {
    private final SettingGroup sgGeneral;
    private boolean placed;
    private final SettingGroup sgRender;
    private final Setting<Integer> delaySetting;
    private final Setting<Boolean> turnOff;
    private final Setting<Boolean> rotate;
    private final Setting<TopMode> topPlacement;
    private final Setting<Boolean> render;
    private final Setting<SettingColor> sideColor;
    private final Setting<BottomMode> bottomPlacement;
    private final Setting<SettingColor> lineColor;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<Boolean> center;
    private final List<class_2338> placePositions;
    private int delay;

    private void add(class_2338 class_23382) {
        if (!this.placePositions.contains(class_23382) && this.mc.field_1687.method_8320(class_23382).method_26207().method_15800() && this.mc.field_1687.method_8628(class_2246.field_10540.method_9564(), class_23382, class_3726.method_16194())) {
            this.placePositions.add(class_23382);
        }
    }

    public SelfTrap() {
        super(Categories.Combat, "self-trap", "Places obsidian above your head.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgRender = this.settings.createGroup("Render");
        this.topPlacement = this.sgGeneral.add(new EnumSetting.Builder().name("top-mode").description("Which positions to place on your top half.").defaultValue(TopMode.Top).build());
        this.bottomPlacement = this.sgGeneral.add(new EnumSetting.Builder().name("bottom-mode").description("Which positions to place on your bottom half.").defaultValue(BottomMode.None).build());
        this.delaySetting = this.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).sliderMin(0).sliderMax(10).build());
        this.center = this.sgGeneral.add(new BoolSetting.Builder().name("center").description("Centers you on the block you are standing on before placing.").defaultValue(true).build());
        this.turnOff = this.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Turns off after placing.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when placing.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders a block overlay where the obsidian will be placed.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        this.placePositions = new ArrayList<class_2338>();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (!this.render.get().booleanValue() || this.placePositions.isEmpty()) {
            return;
        }
        for (class_2338 class_23382 : this.placePositions) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, class_23382, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
        }
    }

    private void findPlacePos() {
        this.placePositions.clear();
        class_2338 class_23382 = this.mc.field_1724.method_24515();
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$SelfTrap$TopMode[this.topPlacement.get().ordinal()]) {
            case 1: {
                this.add(class_23382.method_10069(0, 2, 0));
                this.add(class_23382.method_10069(1, 1, 0));
                this.add(class_23382.method_10069(-1, 1, 0));
                this.add(class_23382.method_10069(0, 1, 1));
                this.add(class_23382.method_10069(0, 1, -1));
                break;
            }
            case 2: {
                this.add(class_23382.method_10069(0, 2, 0));
                break;
            }
            case 3: {
                this.add(class_23382.method_10069(1, 1, 0));
                this.add(class_23382.method_10069(-1, 1, 0));
                this.add(class_23382.method_10069(0, 1, 1));
                this.add(class_23382.method_10069(0, 1, -1));
            }
        }
        if (this.bottomPlacement.get() == BottomMode.Single) {
            this.add(class_23382.method_10069(0, -1, 0));
        }
    }

    @Override
    public void onActivate() {
        if (!this.placePositions.isEmpty()) {
            this.placePositions.clear();
        }
        this.delay = 0;
        this.placed = false;
        if (this.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8281);
        if (this.turnOff.get().booleanValue() && (this.placed && this.placePositions.isEmpty() || !findItemResult.found())) {
            this.toggle();
            return;
        }
        if (!findItemResult.found()) {
            this.placePositions.clear();
            return;
        }
        this.findPlacePos();
        if (this.delay >= this.delaySetting.get() && this.placePositions.size() > 0) {
            class_2338 class_23382 = this.placePositions.get(this.placePositions.size() - 1);
            if (BlockUtils.place(class_23382, findItemResult, this.rotate.get(), 50)) {
                this.placePositions.remove(class_23382);
                this.placed = true;
            }
            this.delay = 0;
        } else {
            ++this.delay;
        }
    }

    public static enum TopMode {
        AntiFacePlace,
        Full,
        Top,
        None;

    }

    public static enum BottomMode {
        Single,
        None;

    }
}

