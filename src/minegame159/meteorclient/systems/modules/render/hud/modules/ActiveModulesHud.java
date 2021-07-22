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
    private final Setting<SettingColor> flatColor;
    private final Setting<ColorMode> colorMode;
    private final minegame159.meteorclient.utils.render.color.Color rainbow;
    private final Setting<Boolean> activeInfo;
    private final List<Module> modules;
    private double rainbowHue2;
    private final Setting<Double> rainbowSpread;
    private final Setting<Double> rainbowSpeed;
    private double rainbowHue1;
    private final SettingGroup sgGeneral;
    private final Setting<Sort> sort;

    private void renderModule(HudRenderer hudRenderer, Module module, double d, double d2) {
        String string;
        minegame159.meteorclient.utils.render.color.Color color = this.flatColor.get();
        ColorMode colorMode = this.colorMode.get();
        if (colorMode == ColorMode.Random) {
            color = module.color;
        } else if (colorMode == ColorMode.Rainbow) {
            this.rainbowHue2 += this.rainbowSpread.get().doubleValue();
            int n = Color.HSBtoRGB((float)this.rainbowHue2, 1.0f, 1.0f);
            this.rainbow.r = minegame159.meteorclient.utils.render.color.Color.toRGBAR(n);
            this.rainbow.g = minegame159.meteorclient.utils.render.color.Color.toRGBAG(n);
            this.rainbow.b = minegame159.meteorclient.utils.render.color.Color.toRGBAB(n);
            color = this.rainbow;
        }
        hudRenderer.text(module.title, d, d2, color);
        if (this.activeInfo.get().booleanValue() && (string = module.getInfoString()) != null) {
            hudRenderer.text(string, d + hudRenderer.textWidth(module.title) + hudRenderer.textWidth(" "), d2, this.hud.secondaryColor.get());
        }
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        if (Modules.get() == null) {
            this.box.setSize(hudRenderer.textWidth("Active Modules"), hudRenderer.textHeight());
            return;
        }
        this.modules.clear();
        for (Module module : Modules.get().getActive()) {
            if (!module.isVisible()) continue;
            this.modules.add(module);
        }
        this.modules.sort((arg_0, arg_1) -> this.lambda$update$3(hudRenderer, arg_0, arg_1));
        double d = 0.0;
        double d2 = 0.0;
        for (int i = 0; i < this.modules.size(); ++i) {
            Module module = this.modules.get(i);
            d = Math.max(d, this.getModuleWidth(hudRenderer, module));
            d2 += hudRenderer.textHeight();
            if (i <= 0) continue;
            d2 += 2.0;
            if (3 != 4) continue;
            return;
        }
        this.box.setSize(d, d2);
    }

    private boolean lambda$new$1() {
        return this.colorMode.get() == ColorMode.Rainbow;
    }

    public ActiveModulesHud(HUD hUD) {
        super(hUD, "active-modules", "Displays your active modules.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sort = this.sgGeneral.add(new EnumSetting.Builder().name("sort").description("How to sort active modules.").defaultValue(Sort.Biggest).build());
        this.activeInfo = this.sgGeneral.add(new BoolSetting.Builder().name("additional-info").description("Shows additional info from the module next to the name in the active modules list.").defaultValue(true).build());
        this.colorMode = this.sgGeneral.add(new EnumSetting.Builder().name("color-mode").description("What color to use for active modules.").defaultValue(ColorMode.Rainbow).build());
        this.flatColor = this.sgGeneral.add(new ColorSetting.Builder().name("flat-color").description("Color for flat color mode.").defaultValue(new SettingColor(225, 25, 25)).visible(this::lambda$new$0).build());
        this.rainbowSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("rainbow-speed").description("Rainbow speed of rainbow color mode.").defaultValue(0.0035).sliderMax(0.1).decimalPlaces(4).visible(this::lambda$new$1).build());
        this.rainbowSpread = this.sgGeneral.add(new DoubleSetting.Builder().name("rainbow-spread").description("Rainbow spread of rainbow color mode.").defaultValue(0.025).sliderMax(0.05).decimalPlaces(4).visible(this::lambda$new$2).build());
        this.modules = new ArrayList<Module>();
        this.rainbow = new minegame159.meteorclient.utils.render.color.Color(255, 255, 255);
    }

    private double getModuleWidth(HudRenderer hudRenderer, Module module) {
        String string;
        double d = hudRenderer.textWidth(module.title);
        if (this.activeInfo.get().booleanValue() && (string = module.getInfoString()) != null) {
            d += hudRenderer.textWidth(" ") + hudRenderer.textWidth(string);
        }
        return d;
    }

    private boolean lambda$new$2() {
        return this.colorMode.get() == ColorMode.Rainbow;
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (Modules.get() == null) {
            hudRenderer.text("Active Modules", d, d2, this.hud.color);
            return;
        }
        this.rainbowHue1 += this.rainbowSpeed.get() * hudRenderer.delta;
        if (this.rainbowHue1 > 1.0) {
            this.rainbowHue1 -= 1.0;
        } else if (this.rainbowHue1 < -1.0) {
            this.rainbowHue1 += 1.0;
        }
        this.rainbowHue2 = this.rainbowHue1;
        for (Module module : this.modules) {
            this.renderModule(hudRenderer, module, d + this.box.alignX(this.getModuleWidth(hudRenderer, module)), d2);
            d2 += 2.0 + hudRenderer.textHeight();
        }
    }

    private boolean lambda$new$0() {
        return this.colorMode.get() == ColorMode.Flat;
    }

    private int lambda$update$3(HudRenderer hudRenderer, Module module, Module module2) {
        int n;
        double d = this.getModuleWidth(hudRenderer, module);
        double d2 = this.getModuleWidth(hudRenderer, module2);
        if (this.sort.get() == Sort.Smallest) {
            double d3 = d;
            d = d2;
            d2 = d3;
        }
        if ((n = Double.compare(d, d2)) == 0) {
            return 0;
        }
        return n < 0 ? 1 : -1;
    }

    public static enum ColorMode {
        Flat,
        Random,
        Rainbow;

    }

    public static enum Sort {
        Biggest,
        Smallest;

    }
}

