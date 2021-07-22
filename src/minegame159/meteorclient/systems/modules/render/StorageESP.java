/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2281
 *  net.minecraft.class_2586
 *  net.minecraft.class_2591
 *  net.minecraft.class_2595
 *  net.minecraft.class_2601
 *  net.minecraft.class_2609
 *  net.minecraft.class_2611
 *  net.minecraft.class_2614
 *  net.minecraft.class_2627
 *  net.minecraft.class_2646
 *  net.minecraft.class_2680
 *  net.minecraft.class_2745
 *  net.minecraft.class_2769
 *  net.minecraft.class_3719
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.Arrays;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StorageBlockListSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dir;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2595;
import net.minecraft.class_2601;
import net.minecraft.class_2609;
import net.minecraft.class_2611;
import net.minecraft.class_2614;
import net.minecraft.class_2627;
import net.minecraft.class_2646;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2769;
import net.minecraft.class_3719;

public class StorageESP
extends Module {
    private final Setting<SettingColor> trappedChest;
    private final Setting<SettingColor> shulker;
    private final Setting<SettingColor> chest;
    private final Setting<SettingColor> barrel;
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> enderChest;
    private final Setting<Double> fadeDistance;
    private final Setting<List<class_2591<?>>> storageBlocks;
    private boolean render;
    private final Color sideColor;
    private final Setting<Boolean> tracers;
    private final Color lineColor;
    private final Setting<ShapeMode> shapeMode;
    private int count;
    private final Setting<SettingColor> other;

    public StorageESP() {
        super(Categories.Render, "storage-esp", "Renders all specified storage blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.storageBlocks = this.sgGeneral.add(new StorageBlockListSetting.Builder().name("storage-blocks").description("Select the storage blocks to display.").defaultValue(Arrays.asList(StorageBlockListSetting.STORAGE_BLOCKS)).build());
        this.tracers = this.sgGeneral.add(new BoolSetting.Builder().name("tracers").description("Draws tracers to storage blocks.").defaultValue(false).build());
        this.shapeMode = this.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.chest = this.sgGeneral.add(new ColorSetting.Builder().name("chest").description("The color of chests.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        this.trappedChest = this.sgGeneral.add(new ColorSetting.Builder().name("trapped-chest").description("The color of trapped chests.").defaultValue(new SettingColor(255, 0, 0, 255)).build());
        this.barrel = this.sgGeneral.add(new ColorSetting.Builder().name("barrel").description("The color of barrels.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        this.shulker = this.sgGeneral.add(new ColorSetting.Builder().name("shulker").description("The color of Shulker Boxes.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        this.enderChest = this.sgGeneral.add(new ColorSetting.Builder().name("ender-chest").description("The color of Ender Chests.").defaultValue(new SettingColor(120, 0, 255, 255)).build());
        this.other = this.sgGeneral.add(new ColorSetting.Builder().name("other").description("The color of furnaces, dispenders, droppers and hoppers.").defaultValue(new SettingColor(140, 140, 140, 255)).build());
        this.fadeDistance = this.sgGeneral.add(new DoubleSetting.Builder().name("fade-distance").description("The distance at which the color will fade.").defaultValue(6.0).min(0.0).sliderMax(12.0).build());
        this.lineColor = new Color(0, 0, 0, 0);
        this.sideColor = new Color(0, 0, 0, 0);
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.count);
    }

    private void getTileEntityColor(class_2586 class_25862) {
        this.render = false;
        if (!this.storageBlocks.get().contains((Object)class_25862.method_11017())) {
            return;
        }
        if (class_25862 instanceof class_2646) {
            this.lineColor.set(this.trappedChest.get());
        } else if (class_25862 instanceof class_2595) {
            this.lineColor.set(this.chest.get());
        } else if (class_25862 instanceof class_3719) {
            this.lineColor.set(this.barrel.get());
        } else if (class_25862 instanceof class_2627) {
            this.lineColor.set(this.shulker.get());
        } else if (class_25862 instanceof class_2611) {
            this.lineColor.set(this.enderChest.get());
        } else if (class_25862 instanceof class_2609 || class_25862 instanceof class_2601 || class_25862 instanceof class_2614) {
            this.lineColor.set(this.other.get());
        } else {
            return;
        }
        this.render = true;
        if (this.shapeMode.get() == ShapeMode.Sides || this.shapeMode.get() == ShapeMode.Both) {
            this.sideColor.set(this.lineColor);
            this.sideColor.a -= 225;
            if (this.sideColor.a < 0) {
                this.sideColor.a = 0;
            }
        }
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.count = 0;
        for (class_2586 class_25862 : this.mc.field_1687.field_9231) {
            class_2680 class_26802;
            if (class_25862.method_11015() || !EntityUtils.isInRenderDistance(class_25862)) continue;
            this.getTileEntityColor(class_25862);
            if (!this.render) continue;
            double d = class_25862.method_11016().method_10263();
            double d2 = class_25862.method_11016().method_10264();
            double d3 = class_25862.method_11016().method_10260();
            double d4 = class_25862.method_11016().method_10263() + 1;
            double d5 = class_25862.method_11016().method_10264() + 1;
            double d6 = class_25862.method_11016().method_10260() + 1;
            int n = 0;
            if (class_25862 instanceof class_2595 && ((class_26802 = this.mc.field_1687.method_8320(class_25862.method_11016())).method_26204() == class_2246.field_10034 || class_26802.method_26204() == class_2246.field_10380) && class_26802.method_11654((class_2769)class_2281.field_10770) != class_2745.field_12569) {
                n = Dir.get(class_2281.method_9758((class_2680)class_26802));
            }
            if (class_25862 instanceof class_2595 || class_25862 instanceof class_2611) {
                double d7 = 0.0625;
                if (Dir.is(n, (byte)32)) {
                    d += d7;
                }
                if (Dir.is(n, (byte)8)) {
                    d3 += d7;
                }
                if (Dir.is(n, (byte)64)) {
                    d4 -= d7;
                }
                d5 -= d7 * 2.0;
                if (Dir.is(n, (byte)16)) {
                    d6 -= d7;
                }
            }
            double d8 = this.mc.field_1724.method_5649((double)class_25862.method_11016().method_10263() + 0.5, (double)class_25862.method_11016().method_10264() + 0.5, (double)class_25862.method_11016().method_10260() + 0.5);
            double d9 = 1.0;
            if (d8 <= this.fadeDistance.get() * this.fadeDistance.get()) {
                d9 = d8 / (this.fadeDistance.get() * this.fadeDistance.get());
            }
            int n2 = this.lineColor.a;
            int n3 = this.sideColor.a;
            this.lineColor.a = (int)((double)this.lineColor.a * d9);
            this.sideColor.a = (int)((double)this.sideColor.a * d9);
            if (d9 >= 0.075) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d, d2, d3, d4, d5, d6, this.sideColor, this.lineColor, this.shapeMode.get(), n);
            }
            if (this.tracers.get().booleanValue()) {
                RenderUtils.drawTracerToBlockEntity(class_25862, this.lineColor, renderEvent);
            }
            this.lineColor.a = n2;
            this.sideColor.a = n3;
            ++this.count;
        }
    }
}

