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
    private final SettingGroup sgGeneral;
    private final Color lineColor;
    private final Setting<SettingColor> animalsColor;
    private final Setting<SettingColor> playersColor;
    private final Setting<Mode> mode;
    private int count;
    private final Setting<SettingColor> ambientColor;
    private final Setting<SettingColor> waterAnimalsColor;
    private final SettingGroup sgColors;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Double> fadeDistance;
    private final Setting<SettingColor> monstersColor;
    public final Setting<ShapeMode> shapeMode;
    public final Setting<Integer> fillOpacity;
    private final Setting<SettingColor> miscColor;
    private final Color sideColor;
    public final Setting<Integer> outlineWidth;

    private void render(RenderEvent renderEvent, class_1297 class_12972) {
        this.lineColor.set(this.getColor(class_12972));
        this.sideColor.set(this.lineColor).a(this.fillOpacity.get());
        double d = this.getFadeAlpha(class_12972);
        int n = this.lineColor.a;
        int n2 = this.sideColor.a;
        this.lineColor.a = (int)((double)this.lineColor.a * d);
        this.sideColor.a = (int)((double)this.sideColor.a * d);
        double d2 = (class_12972.method_23317() - class_12972.field_6014) * (double)renderEvent.tickDelta;
        double d3 = (class_12972.method_23318() - class_12972.field_6036) * (double)renderEvent.tickDelta;
        double d4 = (class_12972.method_23321() - class_12972.field_5969) * (double)renderEvent.tickDelta;
        class_238 class_2383 = class_12972.method_5829();
        Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, d2 + class_2383.field_1323, d3 + class_2383.field_1322, d4 + class_2383.field_1321, d2 + class_2383.field_1320, d3 + class_2383.field_1325, d4 + class_2383.field_1324, this.sideColor, this.lineColor, this.shapeMode.get(), 0);
        this.lineColor.a = n;
        this.sideColor.a = n2;
    }

    public boolean shouldDrawOutline(class_1297 class_12972) {
        return this.mode.get() == Mode.Shader && this.isActive() && this.getOutlineColor(class_12972) != null;
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.count);
    }

    public ESP() {
        super(Categories.Render, "esp", "Renders entities through walls.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgColors = this.settings.createGroup("Colors");
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Rendering mode.").defaultValue(Mode.Shader).build());
        this.shapeMode = this.sgGeneral.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.outlineWidth = this.sgGeneral.add(new IntSetting.Builder().name("width").description("The width of the shader outline.").defaultValue(2).min(1).max(10).sliderMin(1).sliderMax(5).visible(this::lambda$new$0).build());
        this.fillOpacity = this.sgGeneral.add(new IntSetting.Builder().name("fill-opacity").description("The opacity of the shape fill.").defaultValue(80).min(0).max(255).sliderMax(255).build());
        this.fadeDistance = this.sgGeneral.add(new DoubleSetting.Builder().name("fade-distance").description("The distance from an entity where the color begins to fade.").defaultValue(2.0).min(0.0).sliderMax(12.0).build());
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entites").description("Select specific entities.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        this.playersColor = this.sgColors.add(new ColorSetting.Builder().name("players-color").description("The other player's color.").defaultValue(new SettingColor(255, 255, 255)).build());
        this.animalsColor = this.sgColors.add(new ColorSetting.Builder().name("animals-color").description("The animal's color.").defaultValue(new SettingColor(25, 255, 25, 255)).build());
        this.waterAnimalsColor = this.sgColors.add(new ColorSetting.Builder().name("water-animals-color").description("The water animal's color.").defaultValue(new SettingColor(25, 25, 255, 255)).build());
        this.monstersColor = this.sgColors.add(new ColorSetting.Builder().name("monsters-color").description("The monster's color.").defaultValue(new SettingColor(255, 25, 25, 255)).build());
        this.ambientColor = this.sgColors.add(new ColorSetting.Builder().name("ambient-color").description("The ambient's color.").defaultValue(new SettingColor(25, 25, 25, 255)).build());
        this.miscColor = this.sgColors.add(new ColorSetting.Builder().name("misc-color").description("The misc color.").defaultValue(new SettingColor(175, 175, 175, 255)).build());
        this.lineColor = new Color();
        this.sideColor = new Color();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.count = 0;
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!Modules.get().isActive(Freecam.class) && class_12972 == this.mc.field_1724 || !this.entities.get().getBoolean((Object)class_12972.method_5864()) || !EntityUtils.isInRenderDistance(class_12972)) continue;
            if (this.mode.get() == Mode.Box) {
                this.render(renderEvent, class_12972);
            }
            ++this.count;
        }
    }

    public Color getColor(class_1297 class_12972) {
        if (class_12972 instanceof class_1657) {
            return PlayerUtils.getPlayerColor((class_1657)class_12972, this.playersColor.get());
        }
        switch (1.$SwitchMap$net$minecraft$entity$SpawnGroup[class_12972.method_5864().method_5891().ordinal()]) {
            case 1: {
                return this.animalsColor.get();
            }
            case 2: 
            case 3: {
                return this.waterAnimalsColor.get();
            }
            case 4: {
                return this.monstersColor.get();
            }
            case 5: {
                return this.ambientColor.get();
            }
        }
        return this.miscColor.get();
    }

    public Color getOutlineColor(class_1297 class_12972) {
        if (!this.entities.get().getBoolean((Object)class_12972.method_5864())) {
            return null;
        }
        Color color = this.getColor(class_12972);
        double d = this.getFadeAlpha(class_12972);
        return this.lineColor.set(color).a((int)(d * 255.0));
    }

    private boolean lambda$new$0() {
        return this.mode.get() == Mode.Shader;
    }

    private double getFadeAlpha(class_1297 class_12972) {
        double d = PlayerUtils.distanceTo(class_12972.method_23317() + (double)(class_12972.method_17681() / 2.0f), class_12972.method_23318() + (double)(class_12972.method_17682() / 2.0f), class_12972.method_23321() + (double)(class_12972.method_17681() / 2.0f));
        double d2 = this.fadeDistance.get().floatValue() * this.fadeDistance.get().floatValue();
        double d3 = 1.0;
        if (d <= d2) {
            d3 = (float)(d / d2);
        }
        if (d3 <= 0.075) {
            d3 = 0.0;
        }
        return d3;
    }

    public static enum Mode {
        Box,
        Shader;

    }
}

