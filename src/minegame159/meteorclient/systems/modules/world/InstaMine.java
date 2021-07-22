/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2879
 */
package minegame159.meteorclient.systems.modules.world;

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
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2879;

public class InstaMine
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Boolean> pick;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Integer> tickDelay;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private /* synthetic */ class_2350 direction;
    private /* synthetic */ int ticks;

    private boolean shouldMine() {
        InstaMine llllllIllIll;
        if (llllllIllIll.blockPos.method_10264() == -1) {
            return false;
        }
        if (llllllIllIll.mc.field_1687.method_8320((class_2338)llllllIllIll.blockPos).method_26214((class_1922)llllllIllIll.mc.field_1687, (class_2338)llllllIllIll.blockPos) < 0.0f) {
            return false;
        }
        if (llllllIllIll.mc.field_1687.method_8320((class_2338)llllllIllIll.blockPos).method_26215()) {
            return false;
        }
        return llllllIllIll.pick.get() == false || llllllIllIll.mc.field_1724.method_6047().method_7909() == class_1802.field_8377 || llllllIllIll.mc.field_1724.method_6047().method_7909() == class_1802.field_22024;
    }

    @EventHandler
    private void onRender(RenderEvent llllllIllIII) {
        InstaMine llllllIlIlll;
        if (!llllllIlIlll.render.get().booleanValue() || !llllllIlIlll.shouldMine()) {
            return;
        }
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)llllllIlIlll.blockPos, llllllIlIlll.sideColor.get(), llllllIlIlll.lineColor.get(), llllllIlIlll.shapeMode.get(), 0);
    }

    public InstaMine() {
        super(Categories.World, "insta-mine", "Attempts to instantly mine blocks.");
        InstaMine lllllllIlIll;
        lllllllIlIll.sgGeneral = lllllllIlIll.settings.getDefaultGroup();
        lllllllIlIll.sgRender = lllllllIlIll.settings.createGroup("Render");
        lllllllIlIll.tickDelay = lllllllIlIll.sgGeneral.add(new IntSetting.Builder().name("delay").description("The delay between breaks.").defaultValue(0).min(0).sliderMax(20).build());
        lllllllIlIll.pick = lllllllIlIll.sgGeneral.add(new BoolSetting.Builder().name("only-pick").description("Only tries to mine the block if you are holding a pickaxe.").defaultValue(true).build());
        lllllllIlIll.rotate = lllllllIlIll.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Faces the blocks being mined server side.").defaultValue(true).build());
        lllllllIlIll.render = lllllllIlIll.sgRender.add(new BoolSetting.Builder().name("render").description("Renders a block overlay where the obsidian will be placed.").defaultValue(true).build());
        lllllllIlIll.shapeMode = lllllllIlIll.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lllllllIlIll.sideColor = lllllllIlIll.sgRender.add(new ColorSetting.Builder().name("side-color").description("The color of the sides of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 10)).build());
        lllllllIlIll.lineColor = lllllllIlIll.sgRender.add(new ColorSetting.Builder().name("line-color").description("The color of the lines of the blocks being rendered.").defaultValue(new SettingColor(204, 0, 0, 255)).build());
        lllllllIlIll.blockPos = new class_2338.class_2339(0, -1, 0);
    }

    @Override
    public void onActivate() {
        InstaMine lllllllIlIII;
        lllllllIlIII.ticks = 0;
        lllllllIlIII.blockPos.method_10103(0, -1, 0);
    }

    @EventHandler
    private void onStartBreakingBlock(StartBreakingBlockEvent lllllllIIlII) {
        InstaMine lllllllIIlIl;
        lllllllIIlIl.direction = lllllllIIlII.direction;
        lllllllIIlIl.blockPos.method_10101((class_2382)lllllllIIlII.blockPos);
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllIlllll) {
        InstaMine llllllIllllI;
        if (llllllIllllI.ticks >= llllllIllllI.tickDelay.get()) {
            llllllIllllI.ticks = 0;
            if (llllllIllllI.shouldMine()) {
                if (llllllIllllI.rotate.get().booleanValue()) {
                    Rotations.rotate(Rotations.getYaw((class_2338)llllllIllllI.blockPos), Rotations.getPitch((class_2338)llllllIllllI.blockPos), () -> {
                        InstaMine llllllIlIlII;
                        llllllIlIlII.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, (class_2338)llllllIlIlII.blockPos, llllllIlIlII.direction));
                    });
                } else {
                    llllllIllllI.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, (class_2338)llllllIllllI.blockPos, llllllIllllI.direction));
                }
                llllllIllllI.mc.method_1562().method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
        } else {
            ++llllllIllllI.ticks;
        }
    }
}

