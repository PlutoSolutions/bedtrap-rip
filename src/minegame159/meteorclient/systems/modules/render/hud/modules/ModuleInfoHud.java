/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.ModuleListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.AnchorAura;
import minegame159.meteorclient.systems.modules.combat.BedAura;
import minegame159.meteorclient.systems.modules.combat.CrystalAura;
import minegame159.meteorclient.systems.modules.combat.KillAura;
import minegame159.meteorclient.systems.modules.combat.Surround;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class ModuleInfoHud
extends HudElement {
    private final Setting<Boolean> info;
    private final Setting<SettingColor> offColor;
    private final SettingGroup sgGeneral;
    private final Setting<SettingColor> onColor;
    private final Setting<List<Module>> modules;

    public ModuleInfoHud(HUD hUD) {
        super(hUD, "module-info", "Displays if selected modules are enabled or disabled.", false);
        this.sgGeneral = this.settings.getDefaultGroup();
        this.modules = this.sgGeneral.add(new ModuleListSetting.Builder().name("modules").description("Which modules to display").defaultValue(ModuleInfoHud.getDefaultModules()).build());
        this.info = this.sgGeneral.add(new BoolSetting.Builder().name("additional-info").description("Shows additional info from the module next to the name in the module info list.").defaultValue(true).build());
        this.onColor = this.sgGeneral.add(new ColorSetting.Builder().name("on-color").description("Color when module is on.").defaultValue(new SettingColor(25, 225, 25)).build());
        this.offColor = this.sgGeneral.add(new ColorSetting.Builder().name("off-color").description("Color when module is off.").defaultValue(new SettingColor(225, 25, 25)).build());
    }

    private void renderModule(HudRenderer hudRenderer, Module module, double d, double d2) {
        hudRenderer.text(module.title, d, d2, this.hud.primaryColor.get());
        String string = this.getModuleInfo(module);
        hudRenderer.text(string, d + hudRenderer.textWidth(module.title) + hudRenderer.textWidth(" "), d2, module.isActive() ? (Color)this.onColor.get() : (Color)this.offColor.get());
    }

    private static List<Module> getDefaultModules() {
        ArrayList<Module> arrayList = new ArrayList<Module>(5);
        arrayList.add(Modules.get().get(KillAura.class));
        arrayList.add(Modules.get().get(CrystalAura.class));
        arrayList.add(Modules.get().get(AnchorAura.class));
        arrayList.add(Modules.get().get(BedAura.class));
        arrayList.add(Modules.get().get(Surround.class));
        return arrayList;
    }

    private double getModuleWidth(HudRenderer hudRenderer, Module module) {
        double d = hudRenderer.textWidth(module.title);
        if (this.info.get().booleanValue()) {
            d += hudRenderer.textWidth(" ") + hudRenderer.textWidth(this.getModuleInfo(module));
        }
        return d;
    }

    private String getModuleInfo(Module module) {
        if (module.getInfoString() != null && module.isActive() && this.info.get().booleanValue()) {
            return module.getInfoString();
        }
        if (module.isActive()) {
            return "ON";
        }
        return "OFF";
    }

    @Override
    public void update(HudRenderer hudRenderer) {
        if (Modules.get() == null || this.modules.get().isEmpty()) {
            this.box.setSize(hudRenderer.textWidth("Module Info"), hudRenderer.textHeight());
            return;
        }
        double d = 0.0;
        double d2 = 0.0;
        int n = 0;
        for (Module module : this.modules.get()) {
            d = Math.max(d, this.getModuleWidth(hudRenderer, module));
            d2 += hudRenderer.textHeight();
            if (n > 0) {
                d2 += 2.0;
            }
            ++n;
        }
        this.box.setSize(d, d2);
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX();
        double d2 = this.box.getY();
        if (Modules.get() == null || this.modules.get().isEmpty()) {
            hudRenderer.text("Module Info", d, d2, this.hud.primaryColor.get());
            return;
        }
        for (Module module : this.modules.get()) {
            this.renderModule(hudRenderer, module, d + this.box.alignX(this.getModuleWidth(hudRenderer, module)), d2);
            d2 += 2.0 + hudRenderer.textHeight();
        }
    }
}

