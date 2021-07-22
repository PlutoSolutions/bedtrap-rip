/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_238;

public class ESP
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Color lineColor;
    private final /* synthetic */ Setting<SettingColor> animalsColor;
    private final /* synthetic */ Setting<SettingColor> playersColor;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int count;
    private final /* synthetic */ Setting<SettingColor> ambientColor;
    private final /* synthetic */ Setting<SettingColor> waterAnimalsColor;
    private final /* synthetic */ SettingGroup sgColors;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Double> fadeDistance;
    private final /* synthetic */ Setting<SettingColor> monstersColor;
    public final /* synthetic */ Setting<ShapeMode> shapeMode;
    public final /* synthetic */ Setting<Integer> fillOpacity;
    private final /* synthetic */ Setting<SettingColor> miscColor;
    private final /* synthetic */ Color sideColor;
    public final /* synthetic */ Setting<Integer> outlineWidth;

    private void render(RenderEvent lIIIlIllllllII, class_1297 lIIIllIIIIIlIl) {
        ESP lIIIlIllllllIl;
        lIIIlIllllllIl.lineColor.set(lIIIlIllllllIl.getColor(lIIIllIIIIIlIl));
        lIIIlIllllllIl.sideColor.set(lIIIlIllllllIl.lineColor).a(lIIIlIllllllIl.fillOpacity.get());
        double lIIIllIIIIIlII = lIIIlIllllllIl.getFadeAlpha(lIIIllIIIIIlIl);
        int lIIIllIIIIIIll = lIIIlIllllllIl.lineColor.a;
        int lIIIllIIIIIIlI = lIIIlIllllllIl.sideColor.a;
        lIIIlIllllllIl.lineColor.a = (int)((double)lIIIlIllllllIl.lineColor.a * lIIIllIIIIIlII);
        lIIIlIllllllIl.sideColor.a = (int)((double)lIIIlIllllllIl.sideColor.a * lIIIllIIIIIlII);
        double lIIIllIIIIIIIl = (lIIIllIIIIIlIl.method_23317() - lIIIllIIIIIlIl.field_6014) * (double)lIIIlIllllllII.tickDelta;
        double lIIIllIIIIIIII = (lIIIllIIIIIlIl.method_23318() - lIIIllIIIIIlIl.field_6036) * (double)lIIIlIllllllII.tickDelta;
        double lIIIlIllllllll = (lIIIllIIIIIlIl.method_23321() - lIIIllIIIIIlIl.field_5969) * (double)lIIIlIllllllII.tickDelta;
        class_238 lIIIlIlllllllI = lIIIllIIIIIlIl.method_5829();
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lIIIllIIIIIIIl + lIIIlIlllllllI.field_1323, lIIIllIIIIIIII + lIIIlIlllllllI.field_1322, lIIIlIllllllll + lIIIlIlllllllI.field_1321, lIIIllIIIIIIIl + lIIIlIlllllllI.field_1320, lIIIllIIIIIIII + lIIIlIlllllllI.field_1325, lIIIlIllllllll + lIIIlIlllllllI.field_1324, lIIIlIllllllIl.sideColor, lIIIlIllllllIl.lineColor, lIIIlIllllllIl.shapeMode.get(), 0);
        lIIIlIllllllIl.lineColor.a = lIIIllIIIIIIll;
        lIIIlIllllllIl.sideColor.a = lIIIllIIIIIIlI;
    }

    public boolean shouldDrawOutline(class_1297 lIIIlIllIlIIII) {
        ESP lIIIlIllIlIIIl;
        return lIIIlIllIlIIIl.mode.get() == Mode.Shader && lIIIlIllIlIIIl.isActive() && lIIIlIllIlIIIl.getOutlineColor(lIIIlIllIlIIII) != null;
    }

    @Override
    public String getInfoString() {
        ESP lIIIlIllIIIIII;
        return Integer.toString(lIIIlIllIIIIII.count);
    }

    public ESP() {
        super(Categories.Render, "esp", "Renders entities through walls.");
        ESP lIIIllIIIlIIlI;
        lIIIllIIIlIIlI.sgGeneral = lIIIllIIIlIIlI.settings.getDefaultGroup();
        lIIIllIIIlIIlI.sgColors = lIIIllIIIlIIlI.settings.createGroup("Colors");
        lIIIllIIIlIIlI.mode = lIIIllIIIlIIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Rendering mode.").defaultValue(Mode.Shader).build());
        lIIIllIIIlIIlI.shapeMode = lIIIllIIIlIIlI.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIIIllIIIlIIlI.outlineWidth = lIIIllIIIlIIlI.sgGeneral.add(new IntSetting.Builder().name("width").description("The width of the shader outline.").defaultValue(2).min(1).max(10).sliderMin(1).sliderMax(5).visible(() -> {
            ESP lIIIlIlIllllIl;
            return lIIIlIlIllllIl.mode.get() == Mode.Shader;
        }).build());
        lIIIllIIIlIIlI.fillOpacity = lIIIllIIIlIIlI.sgGeneral.add(new IntSetting.Builder().name("fill-opacity").description("The opacity of the shape fill.").defaultValue(80).min(0).max(255).sliderMax(255).build());
        lIIIllIIIlIIlI.fadeDistance = lIIIllIIIlIIlI.sgGeneral.add(new DoubleSetting.Builder().name("fade-distance").description("The distance from an entity where the color begins to fade.").defaultValue(2.0).min(0.0).sliderMax(12.0).build());
        lIIIllIIIlIIlI.entities = lIIIllIIIlIIlI.sgGeneral.add(new EntityTypeListSetting.Builder().name("entites").description("Select specific entities.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        lIIIllIIIlIIlI.playersColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("players-color").description("The other player's color.").defaultValue(new SettingColor(255, 255, 255)).build());
        lIIIllIIIlIIlI.animalsColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("animals-color").description("The animal's color.").defaultValue(new SettingColor(25, 255, 25, 255)).build());
        lIIIllIIIlIIlI.waterAnimalsColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("water-animals-color").description("The water animal's color.").defaultValue(new SettingColor(25, 25, 255, 255)).build());
        lIIIllIIIlIIlI.monstersColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("monsters-color").description("The monster's color.").defaultValue(new SettingColor(255, 25, 25, 255)).build());
        lIIIllIIIlIIlI.ambientColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("ambient-color").description("The ambient's color.").defaultValue(new SettingColor(25, 25, 25, 255)).build());
        lIIIllIIIlIIlI.miscColor = lIIIllIIIlIIlI.sgColors.add(new ColorSetting.Builder().name("misc-color").description("The misc color.").defaultValue(new SettingColor(175, 175, 175, 255)).build());
        lIIIllIIIlIIlI.lineColor = new Color();
        lIIIllIIIlIIlI.sideColor = new Color();
    }

    @EventHandler
    private void onRender(RenderEvent lIIIlIlllIlIll) {
        ESP lIIIlIlllIlllI;
        lIIIlIlllIlllI.count = 0;
        for (class_1297 lIIIlIlllIllll : lIIIlIlllIlllI.mc.field_1687.method_18112()) {
            if (!Modules.get().isActive(Freecam.class) && lIIIlIlllIllll == lIIIlIlllIlllI.mc.field_1724 || !lIIIlIlllIlllI.entities.get().getBoolean((Object)lIIIlIlllIllll.method_5864()) || !EntityUtils.isInRenderDistance(lIIIlIlllIllll)) continue;
            if (lIIIlIlllIlllI.mode.get() == Mode.Box) {
                lIIIlIlllIlllI.render(lIIIlIlllIlIll, lIIIlIlllIllll);
            }
            ++lIIIlIlllIlllI.count;
        }
    }

    public Color getColor(class_1297 lIIIlIllIlIllI) {
        ESP lIIIlIllIlIlll;
        if (lIIIlIllIlIllI instanceof class_1657) {
            return PlayerUtils.getPlayerColor((class_1657)lIIIlIllIlIllI, lIIIlIllIlIlll.playersColor.get());
        }
        switch (lIIIlIllIlIllI.method_5864().method_5891()) {
            case field_6294: {
                return lIIIlIllIlIlll.animalsColor.get();
            }
            case field_24460: 
            case field_6300: {
                return lIIIlIllIlIlll.waterAnimalsColor.get();
            }
            case field_6302: {
                return lIIIlIllIlIlll.monstersColor.get();
            }
            case field_6303: {
                return lIIIlIllIlIlll.ambientColor.get();
            }
        }
        return lIIIlIllIlIlll.miscColor.get();
    }

    public Color getOutlineColor(class_1297 lIIIlIllIIlIII) {
        ESP lIIIlIllIIlIIl;
        if (!lIIIlIllIIlIIl.entities.get().getBoolean((Object)lIIIlIllIIlIII.method_5864())) {
            return null;
        }
        Color lIIIlIllIIIlll = lIIIlIllIIlIIl.getColor(lIIIlIllIIlIII);
        double lIIIlIllIIIllI = lIIIlIllIIlIIl.getFadeAlpha(lIIIlIllIIlIII);
        return lIIIlIllIIlIIl.lineColor.set(lIIIlIllIIIlll).a((int)(lIIIlIllIIIllI * 255.0));
    }

    private double getFadeAlpha(class_1297 lIIIlIllIlllIl) {
        ESP lIIIlIllIllllI;
        double lIIIlIlllIIIIl = PlayerUtils.distanceTo(lIIIlIllIlllIl.method_23317() + (double)(lIIIlIllIlllIl.method_17681() / 2.0f), lIIIlIllIlllIl.method_23318() + (double)(lIIIlIllIlllIl.method_17682() / 2.0f), lIIIlIllIlllIl.method_23321() + (double)(lIIIlIllIlllIl.method_17681() / 2.0f));
        double lIIIlIlllIIIII = lIIIlIllIllllI.fadeDistance.get().floatValue() * lIIIlIllIllllI.fadeDistance.get().floatValue();
        double lIIIlIllIlllll = 1.0;
        if (lIIIlIlllIIIIl <= lIIIlIlllIIIII) {
            lIIIlIllIlllll = (float)(lIIIlIlllIIIIl / lIIIlIlllIIIII);
        }
        if (lIIIlIllIlllll <= 0.075) {
            lIIIlIllIlllll = 0.0;
        }
        return lIIIlIllIlllll;
    }

    public static enum Mode {
        Box,
        Shader;


        private Mode() {
            Mode llIllIlIlIIlIII;
        }
    }
}

