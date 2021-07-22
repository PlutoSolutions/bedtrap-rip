/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_3726
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean placed;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Integer> delaySetting;
    private final /* synthetic */ Setting<Boolean> turnOff;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<TopMode> topPlacement;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<BottomMode> bottomPlacement;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Boolean> center;
    private final /* synthetic */ List<class_2338> placePositions;
    private /* synthetic */ int delay;

    private void add(class_2338 lllllllllllllllllllIllIIlIlIIIll) {
        SelfTrap lllllllllllllllllllIllIIlIlIIlII;
        if (!lllllllllllllllllllIllIIlIlIIlII.placePositions.contains((Object)lllllllllllllllllllIllIIlIlIIIll) && lllllllllllllllllllIllIIlIlIIlII.mc.field_1687.method_8320(lllllllllllllllllllIllIIlIlIIIll).method_26207().method_15800() && lllllllllllllllllllIllIIlIlIIlII.mc.field_1687.method_8628(class_2246.field_10540.method_9564(), lllllllllllllllllllIllIIlIlIIIll, class_3726.method_16194())) {
            lllllllllllllllllllIllIIlIlIIlII.placePositions.add(lllllllllllllllllllIllIIlIlIIIll);
        }
    }

    public SelfTrap() {
        super(Categories.Combat, "self-trap", "Places obsidian above your head.");
        SelfTrap lllllllllllllllllllIllIIllIIIIll;
        lllllllllllllllllllIllIIllIIIIll.sgGeneral = lllllllllllllllllllIllIIllIIIIll.settings.getDefaultGroup();
        lllllllllllllllllllIllIIllIIIIll.sgRender = lllllllllllllllllllIllIIllIIIIll.settings.createGroup("Render");
        lllllllllllllllllllIllIIllIIIIll.topPlacement = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new EnumSetting.Builder().name("top-mode").description("Which positions to place on your top half.").defaultValue(TopMode.Top).build());
        lllllllllllllllllllIllIIllIIIIll.bottomPlacement = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new EnumSetting.Builder().name("bottom-mode").description("Which positions to place on your bottom half.").defaultValue(BottomMode.None).build());
        lllllllllllllllllllIllIIllIIIIll.delaySetting = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new IntSetting.Builder().name("place-delay").description("How many ticks between block placements.").defaultValue(1).sliderMin(0).sliderMax(10).build());
        lllllllllllllllllllIllIIllIIIIll.center = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new BoolSetting.Builder().name("center").description("Centers you on the block you are standing on before placing.").defaultValue(true).build());
        lllllllllllllllllllIllIIllIIIIll.turnOff = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new BoolSetting.Builder().name("turn-off").description("Turns off after placing.").defaultValue(true).build());
        lllllllllllllllllllIllIIllIIIIll.rotate = lllllllllllllllllllIllIIllIIIIll.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Sends rotation packets to the server when placing.").defaultValue(true).build());
        lllllllllllllllllllIllIIllIIIIll.render = lllllllllllllllllllIllIIllIIIIll.sgRender.add(new BoolSetting.Builder().name("render").description("Renders a block overlay where the obsidian will be placed.").defaultValue(true).build());
        lllllllllllllllllllIllIIllIIIIll.shapeMode = lllllllllllllllllllIllIIllIIIIll.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllllllllllllllIllIIllIIIIll.sideColor = lllllllllllllllllllIllIIllIIIIll.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lllllllllllllllllllIllIIllIIIIll.lineColor = lllllllllllllllllllIllIIllIIIIll.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        lllllllllllllllllllIllIIllIIIIll.placePositions = new ArrayList<class_2338>();
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllllIllIIlIllIIII) {
        SelfTrap lllllllllllllllllllIllIIlIlIllll;
        if (!lllllllllllllllllllIllIIlIlIllll.render.get().booleanValue() || lllllllllllllllllllIllIIlIlIllll.placePositions.isEmpty()) {
            return;
        }
        for (class_2338 lllllllllllllllllllIllIIlIllIIlI : lllllllllllllllllllIllIIlIlIllll.placePositions) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllllllllllllllllllIllIIlIllIIlI, lllllllllllllllllllIllIIlIlIllll.sideColor.get(), lllllllllllllllllllIllIIlIlIllll.lineColor.get(), lllllllllllllllllllIllIIlIlIllll.shapeMode.get(), 0);
        }
    }

    private void findPlacePos() {
        SelfTrap lllllllllllllllllllIllIIlIlIlIlI;
        lllllllllllllllllllIllIIlIlIlIlI.placePositions.clear();
        class_2338 lllllllllllllllllllIllIIlIlIlIIl = lllllllllllllllllllIllIIlIlIlIlI.mc.field_1724.method_24515();
        switch (lllllllllllllllllllIllIIlIlIlIlI.topPlacement.get()) {
            case Full: {
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 2, 0));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(1, 1, 0));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(-1, 1, 0));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 1, 1));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 1, -1));
                break;
            }
            case Top: {
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 2, 0));
                break;
            }
            case AntiFacePlace: {
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(1, 1, 0));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(-1, 1, 0));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 1, 1));
                lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, 1, -1));
            }
        }
        if (lllllllllllllllllllIllIIlIlIlIlI.bottomPlacement.get() == BottomMode.Single) {
            lllllllllllllllllllIllIIlIlIlIlI.add(lllllllllllllllllllIllIIlIlIlIIl.method_10069(0, -1, 0));
        }
    }

    @Override
    public void onActivate() {
        SelfTrap lllllllllllllllllllIllIIllIIIIII;
        if (!lllllllllllllllllllIllIIllIIIIII.placePositions.isEmpty()) {
            lllllllllllllllllllIllIIllIIIIII.placePositions.clear();
        }
        lllllllllllllllllllIllIIllIIIIII.delay = 0;
        lllllllllllllllllllIllIIllIIIIII.placed = false;
        if (lllllllllllllllllllIllIIllIIIIII.center.get().booleanValue()) {
            PlayerUtils.centerPlayer();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIllIIlIlllIlI) {
        SelfTrap lllllllllllllllllllIllIIlIlllIll;
        FindItemResult lllllllllllllllllllIllIIlIlllIIl = InvUtils.findInHotbar(class_1802.field_8281);
        if (lllllllllllllllllllIllIIlIlllIll.turnOff.get().booleanValue() && (lllllllllllllllllllIllIIlIlllIll.placed && lllllllllllllllllllIllIIlIlllIll.placePositions.isEmpty() || !lllllllllllllllllllIllIIlIlllIIl.found())) {
            lllllllllllllllllllIllIIlIlllIll.toggle();
            return;
        }
        if (!lllllllllllllllllllIllIIlIlllIIl.found()) {
            lllllllllllllllllllIllIIlIlllIll.placePositions.clear();
            return;
        }
        lllllllllllllllllllIllIIlIlllIll.findPlacePos();
        if (lllllllllllllllllllIllIIlIlllIll.delay >= lllllllllllllllllllIllIIlIlllIll.delaySetting.get() && lllllllllllllllllllIllIIlIlllIll.placePositions.size() > 0) {
            class_2338 lllllllllllllllllllIllIIlIllllII = lllllllllllllllllllIllIIlIlllIll.placePositions.get(lllllllllllllllllllIllIIlIlllIll.placePositions.size() - 1);
            if (BlockUtils.place(lllllllllllllllllllIllIIlIllllII, lllllllllllllllllllIllIIlIlllIIl, lllllllllllllllllllIllIIlIlllIll.rotate.get(), 50)) {
                lllllllllllllllllllIllIIlIlllIll.placePositions.remove((Object)lllllllllllllllllllIllIIlIllllII);
                lllllllllllllllllllIllIIlIlllIll.placed = true;
            }
            lllllllllllllllllllIllIIlIlllIll.delay = 0;
        } else {
            ++lllllllllllllllllllIllIIlIlllIll.delay;
        }
    }

    public static enum TopMode {
        AntiFacePlace,
        Full,
        Top,
        None;


        private TopMode() {
            TopMode llllIIIIlIIIl;
        }
    }

    public static enum BottomMode {
        Single,
        None;


        private BottomMode() {
            BottomMode llllllllllllllllllIIllIlllllIlll;
        }
    }
}

