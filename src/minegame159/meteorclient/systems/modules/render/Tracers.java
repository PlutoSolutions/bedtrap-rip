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
    private final /* synthetic */ Setting<SettingColor> monstersColor;
    private /* synthetic */ int count;
    private final /* synthetic */ Setting<Boolean> stem;
    private final /* synthetic */ Setting<SettingColor> playersColor;
    private final /* synthetic */ Setting<Integer> maxDist;
    private final /* synthetic */ Setting<SettingColor> ambientColor;
    private final /* synthetic */ Setting<SettingColor> waterAnimalsColor;
    private final /* synthetic */ Color distanceColor;
    private final /* synthetic */ SettingGroup sgColors;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Target> target;
    private final /* synthetic */ SettingGroup sgAppearance;
    private final /* synthetic */ Setting<SettingColor> animalsColor;
    public final /* synthetic */ Setting<Boolean> showInvis;
    private final /* synthetic */ Setting<SettingColor> miscColor;
    public final /* synthetic */ Setting<Boolean> distance;
    private final /* synthetic */ SettingGroup sgGeneral;

    private Color getColorFromDistance(class_1657 lllllIlllllIlIl) {
        int lllllIlllllIIlI;
        int lllllIlllllIIIl;
        Tracers lllllIlllllIllI;
        double lllllIlllllIlII = lllllIlllllIllI.mc.field_1724.method_5739((class_1297)lllllIlllllIlIl);
        double lllllIlllllIIll = lllllIlllllIlII / 60.0;
        if (lllllIlllllIIll < 0.0 || lllllIlllllIIll > 1.0) {
            lllllIlllllIllI.distanceColor.set(0, 255, 0, 255);
            return lllllIlllllIllI.distanceColor;
        }
        if (lllllIlllllIIll < 0.5) {
            int lllllIllllllIII = 255;
            int lllllIlllllIlll = (int)(255.0 * lllllIlllllIIll / 0.5);
        } else {
            lllllIlllllIIIl = 255;
            lllllIlllllIIlI = 255 - (int)(255.0 * (lllllIlllllIIll - 0.5) / 0.5);
        }
        lllllIlllllIllI.distanceColor.set(lllllIlllllIIlI, lllllIlllllIIIl, 0, 255);
        return lllllIlllllIllI.distanceColor;
    }

    public Tracers() {
        super(Categories.Render, "tracers", "Displays tracer lines to specified entities.");
        Tracers llllllIIIIlIlII;
        llllllIIIIlIlII.sgGeneral = llllllIIIIlIlII.settings.getDefaultGroup();
        llllllIIIIlIlII.sgAppearance = llllllIIIIlIlII.settings.createGroup("Appearance");
        llllllIIIIlIlII.sgColors = llllllIIIIlIlII.settings.createGroup("Colors");
        llllllIIIIlIlII.entities = llllllIIIIlIlII.sgGeneral.add(new EntityTypeListSetting.Builder().name("entites").description("Select specific entities.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        llllllIIIIlIlII.target = llllllIIIIlIlII.sgAppearance.add(new EnumSetting.Builder().name("target").description("What part of the entity to target.").defaultValue(Target.Body).build());
        llllllIIIIlIlII.stem = llllllIIIIlIlII.sgAppearance.add(new BoolSetting.Builder().name("stem").description("Draw a line through the center of the tracer target.").defaultValue(true).build());
        llllllIIIIlIlII.maxDist = llllllIIIIlIlII.sgAppearance.add(new IntSetting.Builder().name("max-distance").description("Maximum distance for tracers to show.").defaultValue(256).min(0).sliderMax(256).build());
        llllllIIIIlIlII.showInvis = llllllIIIIlIlII.sgGeneral.add(new BoolSetting.Builder().name("show-invisible").description("Shows invisibile entities.").defaultValue(true).build());
        llllllIIIIlIlII.distance = llllllIIIIlIlII.sgColors.add(new BoolSetting.Builder().name("distance-colors").description("Changes the color of tracers depending on distance.").defaultValue(false).build());
        llllllIIIIlIlII.playersColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("players-colors").description("The player's color.").defaultValue(new SettingColor(205, 205, 205, 127)).visible(() -> {
            Tracers lllllIlllIlIllI;
            return lllllIlllIlIllI.distance.get() == false;
        }).build());
        llllllIIIIlIlII.animalsColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("animals-color").description("The animal's color.").defaultValue(new SettingColor(145, 255, 145, 127)).visible(() -> {
            Tracers lllllIlllIllIIl;
            return lllllIlllIllIIl.distance.get() == false;
        }).build());
        llllllIIIIlIlII.waterAnimalsColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("water-animals-color").description("The water animal's color.").defaultValue(new SettingColor(145, 145, 255, 127)).visible(() -> {
            Tracers lllllIlllIlllII;
            return lllllIlllIlllII.distance.get() == false;
        }).build());
        llllllIIIIlIlII.monstersColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("monsters-color").description("The monster's color.").defaultValue(new SettingColor(255, 145, 145, 127)).visible(() -> {
            Tracers lllllIlllIlllll;
            return lllllIlllIlllll.distance.get() == false;
        }).build());
        llllllIIIIlIlII.ambientColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("ambient-color").description("The ambient color.").defaultValue(new SettingColor(75, 75, 75, 127)).visible(() -> {
            Tracers lllllIllllIIIlI;
            return lllllIllllIIIlI.distance.get() == false;
        }).build());
        llllllIIIIlIlII.miscColor = llllllIIIIlIlII.sgColors.add(new ColorSetting.Builder().name("misc-color").description("The misc color.").defaultValue(new SettingColor(145, 145, 145, 127)).visible(() -> {
            Tracers lllllIllllIIllI;
            return lllllIllllIIllI.distance.get() == false;
        }).build());
        llllllIIIIlIlII.distanceColor = new Color(255, 255, 255);
    }

    @EventHandler
    private void onRender(RenderEvent llllllIIIIIIlII) {
        Tracers llllllIIIIIIIll;
        llllllIIIIIIIll.count = 0;
        for (class_1297 llllllIIIIIIllI : llllllIIIIIIIll.mc.field_1687.method_18112()) {
            Color llllllIIIIIIlll;
            if (llllllIIIIIIIll.mc.field_1724.method_5739(llllllIIIIIIllI) > (float)llllllIIIIIIIll.maxDist.get().intValue() || !Modules.get().isActive(Freecam.class) && llllllIIIIIIllI == llllllIIIIIIIll.mc.field_1724 || !llllllIIIIIIIll.entities.get().getBoolean((Object)llllllIIIIIIllI.method_5864()) || !llllllIIIIIIIll.showInvis.get().booleanValue() && llllllIIIIIIllI.method_5767() || !EntityUtils.isInRenderDistance(llllllIIIIIIllI)) continue;
            if (llllllIIIIIIIll.distance.get().booleanValue() && llllllIIIIIIllI instanceof class_1657) {
                Color llllllIIIIIllIl = llllllIIIIIIIll.getColorFromDistance((class_1657)llllllIIIIIIllI);
            } else if (llllllIIIIIIllI instanceof class_1657) {
                Color llllllIIIIIllII = PlayerUtils.getPlayerColor((class_1657)llllllIIIIIIllI, llllllIIIIIIIll.playersColor.get());
            } else {
                switch (llllllIIIIIIllI.method_5864().method_5891()) {
                    case field_6294: {
                        Color llllllIIIIIlIll = llllllIIIIIIIll.animalsColor.get();
                        break;
                    }
                    case field_24460: 
                    case field_6300: {
                        Color llllllIIIIIlIlI = llllllIIIIIIIll.waterAnimalsColor.get();
                        break;
                    }
                    case field_6302: {
                        Color llllllIIIIIlIIl = llllllIIIIIIIll.monstersColor.get();
                        break;
                    }
                    case field_6303: {
                        Color llllllIIIIIlIII = llllllIIIIIIIll.ambientColor.get();
                        break;
                    }
                    default: {
                        llllllIIIIIIlll = llllllIIIIIIIll.miscColor.get();
                    }
                }
            }
            RenderUtils.drawTracerToEntity(llllllIIIIIIlII, llllllIIIIIIllI, llllllIIIIIIlll, llllllIIIIIIIll.target.get(), llllllIIIIIIIll.stem.get());
            ++llllllIIIIIIIll.count;
        }
    }

    @Override
    public String getInfoString() {
        Tracers lllllIllllIlIII;
        return Integer.toString(lllllIllllIlIII.count);
    }
}

