/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class ActiveModulesHud
extends HudElement {
    private final /* synthetic */ Setting<SettingColor> flatColor;
    private final /* synthetic */ Setting<ColorMode> colorMode;
    private final /* synthetic */ minegame159.meteorclient.utils.render.color.Color rainbow;
    private final /* synthetic */ Setting<Boolean> activeInfo;
    private final /* synthetic */ List<Module> modules;
    private /* synthetic */ double rainbowHue2;
    private final /* synthetic */ Setting<Double> rainbowSpread;
    private final /* synthetic */ Setting<Double> rainbowSpeed;
    private /* synthetic */ double rainbowHue1;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Sort> sort;

    private void renderModule(HudRenderer lllllllllllllllllIIIllllIllllllI, Module lllllllllllllllllIIIlllllIIIIlII, double lllllllllllllllllIIIlllllIIIIIll, double lllllllllllllllllIIIlllllIIIIIlI) {
        String lllllllllllllllllIIIlllllIIIIlll;
        ActiveModulesHud lllllllllllllllllIIIlllllIIIIllI;
        minegame159.meteorclient.utils.render.color.Color lllllllllllllllllIIIlllllIIIIIIl = lllllllllllllllllIIIlllllIIIIllI.flatColor.get();
        ColorMode lllllllllllllllllIIIlllllIIIIIII = lllllllllllllllllIIIlllllIIIIllI.colorMode.get();
        if (lllllllllllllllllIIIlllllIIIIIII == ColorMode.Random) {
            lllllllllllllllllIIIlllllIIIIIIl = lllllllllllllllllIIIlllllIIIIlII.color;
        } else if (lllllllllllllllllIIIlllllIIIIIII == ColorMode.Rainbow) {
            lllllllllllllllllIIIlllllIIIIllI.rainbowHue2 += lllllllllllllllllIIIlllllIIIIllI.rainbowSpread.get().doubleValue();
            int lllllllllllllllllIIIlllllIIIlIII = Color.HSBtoRGB((float)lllllllllllllllllIIIlllllIIIIllI.rainbowHue2, 1.0f, 1.0f);
            lllllllllllllllllIIIlllllIIIIllI.rainbow.r = minegame159.meteorclient.utils.render.color.Color.toRGBAR(lllllllllllllllllIIIlllllIIIlIII);
            lllllllllllllllllIIIlllllIIIIllI.rainbow.g = minegame159.meteorclient.utils.render.color.Color.toRGBAG(lllllllllllllllllIIIlllllIIIlIII);
            lllllllllllllllllIIIlllllIIIIllI.rainbow.b = minegame159.meteorclient.utils.render.color.Color.toRGBAB(lllllllllllllllllIIIlllllIIIlIII);
            lllllllllllllllllIIIlllllIIIIIIl = lllllllllllllllllIIIlllllIIIIllI.rainbow;
        }
        lllllllllllllllllIIIllllIllllllI.text(lllllllllllllllllIIIlllllIIIIlII.title, lllllllllllllllllIIIlllllIIIIIll, lllllllllllllllllIIIlllllIIIIIlI, lllllllllllllllllIIIlllllIIIIIIl);
        if (lllllllllllllllllIIIlllllIIIIllI.activeInfo.get().booleanValue() && (lllllllllllllllllIIIlllllIIIIlll = lllllllllllllllllIIIlllllIIIIlII.getInfoString()) != null) {
            lllllllllllllllllIIIllllIllllllI.text(lllllllllllllllllIIIlllllIIIIlll, lllllllllllllllllIIIlllllIIIIIll + lllllllllllllllllIIIllllIllllllI.textWidth(lllllllllllllllllIIIlllllIIIIlII.title) + lllllllllllllllllIIIllllIllllllI.textWidth(" "), lllllllllllllllllIIIlllllIIIIIlI, lllllllllllllllllIIIlllllIIIIllI.hud.secondaryColor.get());
        }
    }

    @Override
    public void update(HudRenderer lllllllllllllllllIIIlllllIlIIlll) {
        ActiveModulesHud lllllllllllllllllIIIlllllIlIlIII;
        if (Modules.get() == null) {
            lllllllllllllllllIIIlllllIlIlIII.box.setSize(lllllllllllllllllIIIlllllIlIIlll.textWidth("Active Modules"), lllllllllllllllllIIIlllllIlIIlll.textHeight());
            return;
        }
        lllllllllllllllllIIIlllllIlIlIII.modules.clear();
        for (Module lllllllllllllllllIIIlllllIlIllll : Modules.get().getActive()) {
            if (!lllllllllllllllllIIIlllllIlIllll.isVisible()) continue;
            lllllllllllllllllIIIlllllIlIlIII.modules.add(lllllllllllllllllIIIlllllIlIllll);
        }
        lllllllllllllllllIIIlllllIlIlIII.modules.sort((lllllllllllllllllIIIllllIlIlIlll, lllllllllllllllllIIIllllIlIlllIl) -> {
            int lllllllllllllllllIIIllllIlIllIlI;
            ActiveModulesHud lllllllllllllllllIIIllllIllIIIII;
            double lllllllllllllllllIIIllllIlIlllII = lllllllllllllllllIIIllllIllIIIII.getModuleWidth(lllllllllllllllllIIIlllllIlIIlll, (Module)lllllllllllllllllIIIllllIlIlIlll);
            double lllllllllllllllllIIIllllIlIllIll = lllllllllllllllllIIIllllIllIIIII.getModuleWidth(lllllllllllllllllIIIlllllIlIIlll, (Module)lllllllllllllllllIIIllllIlIlllIl);
            if (lllllllllllllllllIIIllllIllIIIII.sort.get() == Sort.Smallest) {
                double lllllllllllllllllIIIllllIllIIIIl = lllllllllllllllllIIIllllIlIlllII;
                lllllllllllllllllIIIllllIlIlllII = lllllllllllllllllIIIllllIlIllIll;
                lllllllllllllllllIIIllllIlIllIll = lllllllllllllllllIIIllllIllIIIIl;
            }
            if ((lllllllllllllllllIIIllllIlIllIlI = Double.compare(lllllllllllllllllIIIllllIlIlllII, lllllllllllllllllIIIllllIlIllIll)) == 0) {
                return 0;
            }
            return lllllllllllllllllIIIllllIlIllIlI < 0 ? 1 : -1;
        });
        double lllllllllllllllllIIIlllllIlIlIlI = 0.0;
        double lllllllllllllllllIIIlllllIlIlIIl = 0.0;
        for (int lllllllllllllllllIIIlllllIlIllIl = 0; lllllllllllllllllIIIlllllIlIllIl < lllllllllllllllllIIIlllllIlIlIII.modules.size(); ++lllllllllllllllllIIIlllllIlIllIl) {
            Module lllllllllllllllllIIIlllllIlIlllI = lllllllllllllllllIIIlllllIlIlIII.modules.get(lllllllllllllllllIIIlllllIlIllIl);
            lllllllllllllllllIIIlllllIlIlIlI = Math.max(lllllllllllllllllIIIlllllIlIlIlI, lllllllllllllllllIIIlllllIlIlIII.getModuleWidth(lllllllllllllllllIIIlllllIlIIlll, lllllllllllllllllIIIlllllIlIlllI));
            lllllllllllllllllIIIlllllIlIlIIl += lllllllllllllllllIIIlllllIlIIlll.textHeight();
            if (lllllllllllllllllIIIlllllIlIllIl <= 0) continue;
            lllllllllllllllllIIIlllllIlIlIIl += 2.0;
        }
        lllllllllllllllllIIIlllllIlIlIII.box.setSize(lllllllllllllllllIIIlllllIlIlIlI, lllllllllllllllllIIIlllllIlIlIIl);
    }

    public ActiveModulesHud(HUD lllllllllllllllllIIIlllllIlllIIl) {
        super(lllllllllllllllllIIIlllllIlllIIl, "active-modules", "Displays your active modules.");
        ActiveModulesHud lllllllllllllllllIIIlllllIlllIII;
        lllllllllllllllllIIIlllllIlllIII.sgGeneral = lllllllllllllllllIIIlllllIlllIII.settings.getDefaultGroup();
        lllllllllllllllllIIIlllllIlllIII.sort = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new EnumSetting.Builder().name("sort").description("How to sort active modules.").defaultValue(Sort.Biggest).build());
        lllllllllllllllllIIIlllllIlllIII.activeInfo = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new BoolSetting.Builder().name("additional-info").description("Shows additional info from the module next to the name in the active modules list.").defaultValue(true).build());
        lllllllllllllllllIIIlllllIlllIII.colorMode = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new EnumSetting.Builder().name("color-mode").description("What color to use for active modules.").defaultValue(ColorMode.Rainbow).build());
        lllllllllllllllllIIIlllllIlllIII.flatColor = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new ColorSetting.Builder().name("flat-color").description("Color for flat color mode.").defaultValue(new SettingColor(225, 25, 25)).visible(() -> {
            ActiveModulesHud lllllllllllllllllIIIllllIlIIlIll;
            return lllllllllllllllllIIIllllIlIIlIll.colorMode.get() == ColorMode.Flat;
        }).build());
        lllllllllllllllllIIIlllllIlllIII.rainbowSpeed = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new DoubleSetting.Builder().name("rainbow-speed").description("Rainbow speed of rainbow color mode.").defaultValue(0.0035).sliderMax(0.1).decimalPlaces(4).visible(() -> {
            ActiveModulesHud lllllllllllllllllIIIllllIlIIllIl;
            return lllllllllllllllllIIIllllIlIIllIl.colorMode.get() == ColorMode.Rainbow;
        }).build());
        lllllllllllllllllIIIlllllIlllIII.rainbowSpread = lllllllllllllllllIIIlllllIlllIII.sgGeneral.add(new DoubleSetting.Builder().name("rainbow-spread").description("Rainbow spread of rainbow color mode.").defaultValue(0.025).sliderMax(0.05).decimalPlaces(4).visible(() -> {
            ActiveModulesHud lllllllllllllllllIIIllllIlIlIIII;
            return lllllllllllllllllIIIllllIlIlIIII.colorMode.get() == ColorMode.Rainbow;
        }).build());
        lllllllllllllllllIIIlllllIlllIII.modules = new ArrayList<Module>();
        lllllllllllllllllIIIlllllIlllIII.rainbow = new minegame159.meteorclient.utils.render.color.Color(255, 255, 255);
    }

    private double getModuleWidth(HudRenderer lllllllllllllllllIIIllllIlllIIII, Module lllllllllllllllllIIIllllIllIllll) {
        String lllllllllllllllllIIIllllIlllIIlI;
        ActiveModulesHud lllllllllllllllllIIIllllIllIllIl;
        double lllllllllllllllllIIIllllIllIlllI = lllllllllllllllllIIIllllIlllIIII.textWidth(lllllllllllllllllIIIllllIllIllll.title);
        if (lllllllllllllllllIIIllllIllIllIl.activeInfo.get().booleanValue() && (lllllllllllllllllIIIllllIlllIIlI = lllllllllllllllllIIIllllIllIllll.getInfoString()) != null) {
            lllllllllllllllllIIIllllIllIlllI += lllllllllllllllllIIIllllIlllIIII.textWidth(" ") + lllllllllllllllllIIIllllIlllIIII.textWidth(lllllllllllllllllIIIllllIlllIIlI);
        }
        return lllllllllllllllllIIIllllIllIlllI;
    }

    @Override
    public void render(HudRenderer lllllllllllllllllIIIlllllIIllIIl) {
        ActiveModulesHud lllllllllllllllllIIIlllllIIllIlI;
        double lllllllllllllllllIIIlllllIIllIII = lllllllllllllllllIIIlllllIIllIlI.box.getX();
        double lllllllllllllllllIIIlllllIIlIlll = lllllllllllllllllIIIlllllIIllIlI.box.getY();
        if (Modules.get() == null) {
            lllllllllllllllllIIIlllllIIllIIl.text("Active Modules", lllllllllllllllllIIIlllllIIllIII, lllllllllllllllllIIIlllllIIlIlll, lllllllllllllllllIIIlllllIIllIlI.hud.color);
            return;
        }
        lllllllllllllllllIIIlllllIIllIlI.rainbowHue1 += lllllllllllllllllIIIlllllIIllIlI.rainbowSpeed.get() * lllllllllllllllllIIIlllllIIllIIl.delta;
        if (lllllllllllllllllIIIlllllIIllIlI.rainbowHue1 > 1.0) {
            lllllllllllllllllIIIlllllIIllIlI.rainbowHue1 -= 1.0;
        } else if (lllllllllllllllllIIIlllllIIllIlI.rainbowHue1 < -1.0) {
            lllllllllllllllllIIIlllllIIllIlI.rainbowHue1 += 1.0;
        }
        lllllllllllllllllIIIlllllIIllIlI.rainbowHue2 = lllllllllllllllllIIIlllllIIllIlI.rainbowHue1;
        for (Module lllllllllllllllllIIIlllllIIllIll : lllllllllllllllllIIIlllllIIllIlI.modules) {
            lllllllllllllllllIIIlllllIIllIlI.renderModule(lllllllllllllllllIIIlllllIIllIIl, lllllllllllllllllIIIlllllIIllIll, lllllllllllllllllIIIlllllIIllIII + lllllllllllllllllIIIlllllIIllIlI.box.alignX(lllllllllllllllllIIIlllllIIllIlI.getModuleWidth(lllllllllllllllllIIIlllllIIllIIl, lllllllllllllllllIIIlllllIIllIll)), lllllllllllllllllIIIlllllIIlIlll);
            lllllllllllllllllIIIlllllIIlIlll += 2.0 + lllllllllllllllllIIIlllllIIllIIl.textHeight();
        }
    }

    public static enum ColorMode {
        Flat,
        Random,
        Rainbow;


        private ColorMode() {
            ColorMode lllllllllllllllllllIlIlIIllIIlll;
        }
    }

    public static enum Sort {
        Biggest,
        Smallest;


        private Sort() {
            Sort llIlIllIIllIl;
        }
    }
}

