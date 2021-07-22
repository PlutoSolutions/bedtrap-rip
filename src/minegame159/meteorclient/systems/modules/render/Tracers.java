/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 */
package minegame159.meteorclient.systems.modules.render;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
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
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.RenderUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;

public class Tracers
extends Module {
    private final Setting<SettingColor> monstersColor;
    private int count;
    private final Setting<Boolean> stem;
    private final Setting<SettingColor> playersColor;
    private final Setting<Integer> maxDist;
    private final Setting<SettingColor> ambientColor;
    private final Setting<SettingColor> waterAnimalsColor;
    private final Color distanceColor;
    private final SettingGroup sgColors;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Target> target;
    private final SettingGroup sgAppearance;
    private final Setting<SettingColor> animalsColor;
    public final Setting<Boolean> showInvis;
    private final Setting<SettingColor> miscColor;
    public final Setting<Boolean> distance;
    private final SettingGroup sgGeneral;

    private boolean lambda$new$3() {
        return this.distance.get() == false;
    }

    private boolean lambda$new$0() {
        return this.distance.get() == false;
    }

    private Color getColorFromDistance(class_1657 class_16572) {
        int n;
        int n2;
        double d = this.mc.field_1724.method_5739((class_1297)class_16572);
        double d2 = d / 60.0;
        if (d2 < 0.0 || d2 > 1.0) {
            this.distanceColor.set(0, 255, 0, 255);
            return this.distanceColor;
        }
        if (d2 < 0.5) {
            n2 = 255;
            n = (int)(255.0 * d2 / 0.5);
        } else {
            n = 255;
            n2 = 255 - (int)(255.0 * (d2 - 0.5) / 0.5);
        }
        this.distanceColor.set(n2, n, 0, 255);
        return this.distanceColor;
    }

    private boolean lambda$new$2() {
        return this.distance.get() == false;
    }

    public Tracers() {
        super(Categories.Render, "tracers", "Displays tracer lines to specified entities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgAppearance = this.settings.createGroup("Appearance");
        this.sgColors = this.settings.createGroup("Colors");
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entites").description("Select specific entities.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        this.target = this.sgAppearance.add(new EnumSetting.Builder().name("target").description("What part of the entity to target.").defaultValue(Target.Body).build());
        this.stem = this.sgAppearance.add(new BoolSetting.Builder().name("stem").description("Draw a line through the center of the tracer target.").defaultValue(true).build());
        this.maxDist = this.sgAppearance.add(new IntSetting.Builder().name("max-distance").description("Maximum distance for tracers to show.").defaultValue(256).min(0).sliderMax(256).build());
        this.showInvis = this.sgGeneral.add(new BoolSetting.Builder().name("show-invisible").description("Shows invisibile entities.").defaultValue(true).build());
        this.distance = this.sgColors.add(new BoolSetting.Builder().name("distance-colors").description("Changes the color of tracers depending on distance.").defaultValue(false).build());
        this.playersColor = this.sgColors.add(new ColorSetting.Builder().name("players-colors").description("The player's color.").defaultValue(new SettingColor(205, 205, 205, 127)).visible(this::lambda$new$0).build());
        this.animalsColor = this.sgColors.add(new ColorSetting.Builder().name("animals-color").description("The animal's color.").defaultValue(new SettingColor(145, 255, 145, 127)).visible(this::lambda$new$1).build());
        this.waterAnimalsColor = this.sgColors.add(new ColorSetting.Builder().name("water-animals-color").description("The water animal's color.").defaultValue(new SettingColor(145, 145, 255, 127)).visible(this::lambda$new$2).build());
        this.monstersColor = this.sgColors.add(new ColorSetting.Builder().name("monsters-color").description("The monster's color.").defaultValue(new SettingColor(255, 145, 145, 127)).visible(this::lambda$new$3).build());
        this.ambientColor = this.sgColors.add(new ColorSetting.Builder().name("ambient-color").description("The ambient color.").defaultValue(new SettingColor(75, 75, 75, 127)).visible(this::lambda$new$4).build());
        this.miscColor = this.sgColors.add(new ColorSetting.Builder().name("misc-color").description("The misc color.").defaultValue(new SettingColor(145, 145, 145, 127)).visible(this::lambda$new$5).build());
        this.distanceColor = new Color(255, 255, 255);
    }

    private boolean lambda$new$5() {
        return this.distance.get() == false;
    }

    private boolean lambda$new$4() {
        return this.distance.get() == false;
    }

    private boolean lambda$new$1() {
        return this.distance.get() == false;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        this.count = 0;
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            Color color;
            if (this.mc.field_1724.method_5739(class_12972) > (float)this.maxDist.get().intValue() || !Modules.get().isActive(Freecam.class) && class_12972 == this.mc.field_1724 || !this.entities.get().getBoolean((Object)class_12972.method_5864()) || !this.showInvis.get().booleanValue() && class_12972.method_5767() || !EntityUtils.isInRenderDistance(class_12972)) continue;
            if (this.distance.get().booleanValue() && class_12972 instanceof class_1657) {
                color = this.getColorFromDistance((class_1657)class_12972);
            } else if (class_12972 instanceof class_1657) {
                color = PlayerUtils.getPlayerColor((class_1657)class_12972, this.playersColor.get());
            } else {
                switch (1.$SwitchMap$net$minecraft$entity$SpawnGroup[class_12972.method_5864().method_5891().ordinal()]) {
                    case 1: {
                        color = this.animalsColor.get();
                        break;
                    }
                    case 2: 
                    case 3: {
                        color = this.waterAnimalsColor.get();
                        break;
                    }
                    case 4: {
                        color = this.monstersColor.get();
                        break;
                    }
                    case 5: {
                        color = this.ambientColor.get();
                        break;
                    }
                    default: {
                        color = this.miscColor.get();
                    }
                }
            }
            RenderUtils.drawTracerToEntity(renderEvent, class_12972, color, this.target.get(), this.stem.get());
            ++this.count;
        }
    }

    @Override
    public String getInfoString() {
        return Integer.toString(this.count);
    }
}

