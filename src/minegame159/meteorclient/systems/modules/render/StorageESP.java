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
    private final /* synthetic */ Setting<SettingColor> trappedChest;
    private final /* synthetic */ Setting<SettingColor> shulker;
    private final /* synthetic */ Setting<SettingColor> chest;
    private final /* synthetic */ Setting<SettingColor> barrel;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> enderChest;
    private final /* synthetic */ Setting<Double> fadeDistance;
    private final /* synthetic */ Setting<List<class_2591<?>>> storageBlocks;
    private /* synthetic */ boolean render;
    private final /* synthetic */ Color sideColor;
    private final /* synthetic */ Setting<Boolean> tracers;
    private final /* synthetic */ Color lineColor;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private /* synthetic */ int count;
    private final /* synthetic */ Setting<SettingColor> other;

    public StorageESP() {
        super(Categories.Render, "storage-esp", "Renders all specified storage blocks.");
        StorageESP llllIllllII;
        llllIllllII.sgGeneral = llllIllllII.settings.getDefaultGroup();
        llllIllllII.storageBlocks = llllIllllII.sgGeneral.add(new StorageBlockListSetting.Builder().name("storage-blocks").description("Select the storage blocks to display.").defaultValue(Arrays.asList(StorageBlockListSetting.STORAGE_BLOCKS)).build());
        llllIllllII.tracers = llllIllllII.sgGeneral.add(new BoolSetting.Builder().name("tracers").description("Draws tracers to storage blocks.").defaultValue(false).build());
        llllIllllII.shapeMode = llllIllllII.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llllIllllII.chest = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("chest").description("The color of chests.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        llllIllllII.trappedChest = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("trapped-chest").description("The color of trapped chests.").defaultValue(new SettingColor(255, 0, 0, 255)).build());
        llllIllllII.barrel = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("barrel").description("The color of barrels.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        llllIllllII.shulker = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("shulker").description("The color of Shulker Boxes.").defaultValue(new SettingColor(255, 160, 0, 255)).build());
        llllIllllII.enderChest = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("ender-chest").description("The color of Ender Chests.").defaultValue(new SettingColor(120, 0, 255, 255)).build());
        llllIllllII.other = llllIllllII.sgGeneral.add(new ColorSetting.Builder().name("other").description("The color of furnaces, dispenders, droppers and hoppers.").defaultValue(new SettingColor(140, 140, 140, 255)).build());
        llllIllllII.fadeDistance = llllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("fade-distance").description("The distance at which the color will fade.").defaultValue(6.0).min(0.0).sliderMax(12.0).build());
        llllIllllII.lineColor = new Color(0, 0, 0, 0);
        llllIllllII.sideColor = new Color(0, 0, 0, 0);
    }

    @Override
    public String getInfoString() {
        StorageESP llllIIIIlII;
        return Integer.toString(llllIIIIlII.count);
    }

    private void getTileEntityColor(class_2586 llllIllIlll) {
        StorageESP llllIllIllI;
        llllIllIllI.render = false;
        if (!llllIllIllI.storageBlocks.get().contains((Object)llllIllIlll.method_11017())) {
            return;
        }
        if (llllIllIlll instanceof class_2646) {
            llllIllIllI.lineColor.set(llllIllIllI.trappedChest.get());
        } else if (llllIllIlll instanceof class_2595) {
            llllIllIllI.lineColor.set(llllIllIllI.chest.get());
        } else if (llllIllIlll instanceof class_3719) {
            llllIllIllI.lineColor.set(llllIllIllI.barrel.get());
        } else if (llllIllIlll instanceof class_2627) {
            llllIllIllI.lineColor.set(llllIllIllI.shulker.get());
        } else if (llllIllIlll instanceof class_2611) {
            llllIllIllI.lineColor.set(llllIllIllI.enderChest.get());
        } else if (llllIllIlll instanceof class_2609 || llllIllIlll instanceof class_2601 || llllIllIlll instanceof class_2614) {
            llllIllIllI.lineColor.set(llllIllIllI.other.get());
        } else {
            return;
        }
        llllIllIllI.render = true;
        if (llllIllIllI.shapeMode.get() == ShapeMode.Sides || llllIllIllI.shapeMode.get() == ShapeMode.Both) {
            llllIllIllI.sideColor.set(llllIllIllI.lineColor);
            llllIllIllI.sideColor.a -= 225;
            if (llllIllIllI.sideColor.a < 0) {
                llllIllIllI.sideColor.a = 0;
            }
        }
    }

    @EventHandler
    private void onRender(RenderEvent llllIIlIllI) {
        StorageESP llllIIlIlll;
        llllIIlIlll.count = 0;
        for (class_2586 llllIIllIII : llllIIlIlll.mc.field_1687.field_9231) {
            class_2680 llllIlIIlIl;
            if (llllIIllIII.method_11015() || !EntityUtils.isInRenderDistance(llllIIllIII)) continue;
            llllIIlIlll.getTileEntityColor(llllIIllIII);
            if (!llllIIlIlll.render) continue;
            double llllIlIIIll = llllIIllIII.method_11016().method_10263();
            double llllIlIIIlI = llllIIllIII.method_11016().method_10264();
            double llllIlIIIIl = llllIIllIII.method_11016().method_10260();
            double llllIlIIIII = llllIIllIII.method_11016().method_10263() + 1;
            double llllIIlllll = llllIIllIII.method_11016().method_10264() + 1;
            double llllIIllllI = llllIIllIII.method_11016().method_10260() + 1;
            int llllIIlllIl = 0;
            if (llllIIllIII instanceof class_2595 && ((llllIlIIlIl = llllIIlIlll.mc.field_1687.method_8320(llllIIllIII.method_11016())).method_26204() == class_2246.field_10034 || llllIlIIlIl.method_26204() == class_2246.field_10380) && llllIlIIlIl.method_11654((class_2769)class_2281.field_10770) != class_2745.field_12569) {
                llllIIlllIl = Dir.get(class_2281.method_9758((class_2680)llllIlIIlIl));
            }
            if (llllIIllIII instanceof class_2595 || llllIIllIII instanceof class_2611) {
                double llllIlIIlII = 0.0625;
                if (Dir.is(llllIIlllIl, (byte)32)) {
                    llllIlIIIll += llllIlIIlII;
                }
                if (Dir.is(llllIIlllIl, (byte)8)) {
                    llllIlIIIIl += llllIlIIlII;
                }
                if (Dir.is(llllIIlllIl, (byte)64)) {
                    llllIlIIIII -= llllIlIIlII;
                }
                llllIIlllll -= llllIlIIlII * 2.0;
                if (Dir.is(llllIIlllIl, (byte)16)) {
                    llllIIllllI -= llllIlIIlII;
                }
            }
            double llllIIlllII = llllIIlIlll.mc.field_1724.method_5649((double)llllIIllIII.method_11016().method_10263() + 0.5, (double)llllIIllIII.method_11016().method_10264() + 0.5, (double)llllIIllIII.method_11016().method_10260() + 0.5);
            double llllIIllIll = 1.0;
            if (llllIIlllII <= llllIIlIlll.fadeDistance.get() * llllIIlIlll.fadeDistance.get()) {
                llllIIllIll = llllIIlllII / (llllIIlIlll.fadeDistance.get() * llllIIlIlll.fadeDistance.get());
            }
            int llllIIllIlI = llllIIlIlll.lineColor.a;
            int llllIIllIIl = llllIIlIlll.sideColor.a;
            llllIIlIlll.lineColor.a = (int)((double)llllIIlIlll.lineColor.a * llllIIllIll);
            llllIIlIlll.sideColor.a = (int)((double)llllIIlIlll.sideColor.a * llllIIllIll);
            if (llllIIllIll >= 0.075) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllIlIIIll, llllIlIIIlI, llllIlIIIIl, llllIlIIIII, llllIIlllll, llllIIllllI, llllIIlIlll.sideColor, llllIIlIlll.lineColor, llllIIlIlll.shapeMode.get(), llllIIlllIl);
            }
            if (llllIIlIlll.tracers.get().booleanValue()) {
                RenderUtils.drawTracerToBlockEntity(llllIIllIII, llllIIlIlll.lineColor, llllIIlIllI);
            }
            llllIIlIlll.lineColor.a = llllIIllIlI;
            llllIIlIlll.sideColor.a = llllIIllIIl;
            ++llllIIlIlll.count;
        }
    }
}

