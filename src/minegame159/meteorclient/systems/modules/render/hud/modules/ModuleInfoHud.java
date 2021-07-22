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
    private final /* synthetic */ Setting<Boolean> info;
    private final /* synthetic */ Setting<SettingColor> offColor;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<SettingColor> onColor;
    private final /* synthetic */ Setting<List<Module>> modules;

    public ModuleInfoHud(HUD lllllllllllllllllIIIllIllllIlIII) {
        super(lllllllllllllllllIIIllIllllIlIII, "module-info", "Displays if selected modules are enabled or disabled.", false);
        ModuleInfoHud lllllllllllllllllIIIllIllllIIlll;
        lllllllllllllllllIIIllIllllIIlll.sgGeneral = lllllllllllllllllIIIllIllllIIlll.settings.getDefaultGroup();
        lllllllllllllllllIIIllIllllIIlll.modules = lllllllllllllllllIIIllIllllIIlll.sgGeneral.add(new ModuleListSetting.Builder().name("modules").description("Which modules to display").defaultValue(ModuleInfoHud.getDefaultModules()).build());
        lllllllllllllllllIIIllIllllIIlll.info = lllllllllllllllllIIIllIllllIIlll.sgGeneral.add(new BoolSetting.Builder().name("additional-info").description("Shows additional info from the module next to the name in the module info list.").defaultValue(true).build());
        lllllllllllllllllIIIllIllllIIlll.onColor = lllllllllllllllllIIIllIllllIIlll.sgGeneral.add(new ColorSetting.Builder().name("on-color").description("Color when module is on.").defaultValue(new SettingColor(25, 225, 25)).build());
        lllllllllllllllllIIIllIllllIIlll.offColor = lllllllllllllllllIIIllIllllIIlll.sgGeneral.add(new ColorSetting.Builder().name("off-color").description("Color when module is off.").defaultValue(new SettingColor(225, 25, 25)).build());
    }

    private void renderModule(HudRenderer lllllllllllllllllIIIllIllIllIIll, Module lllllllllllllllllIIIllIllIllIIlI, double lllllllllllllllllIIIllIllIllIIIl, double lllllllllllllllllIIIllIllIllIllI) {
        ModuleInfoHud lllllllllllllllllIIIllIllIlllIlI;
        lllllllllllllllllIIIllIllIllIIll.text(lllllllllllllllllIIIllIllIllIIlI.title, lllllllllllllllllIIIllIllIllIIIl, lllllllllllllllllIIIllIllIllIllI, lllllllllllllllllIIIllIllIlllIlI.hud.primaryColor.get());
        String lllllllllllllllllIIIllIllIllIlIl = lllllllllllllllllIIIllIllIlllIlI.getModuleInfo(lllllllllllllllllIIIllIllIllIIlI);
        lllllllllllllllllIIIllIllIllIIll.text(lllllllllllllllllIIIllIllIllIlIl, lllllllllllllllllIIIllIllIllIIIl + lllllllllllllllllIIIllIllIllIIll.textWidth(lllllllllllllllllIIIllIllIllIIlI.title) + lllllllllllllllllIIIllIllIllIIll.textWidth(" "), lllllllllllllllllIIIllIllIllIllI, lllllllllllllllllIIIllIllIllIIlI.isActive() ? (Color)lllllllllllllllllIIIllIllIlllIlI.onColor.get() : (Color)lllllllllllllllllIIIllIllIlllIlI.offColor.get());
    }

    private static List<Module> getDefaultModules() {
        ArrayList<Module> lllllllllllllllllIIIllIllIIllIll = new ArrayList<Module>(5);
        lllllllllllllllllIIIllIllIIllIll.add(Modules.get().get(KillAura.class));
        lllllllllllllllllIIIllIllIIllIll.add(Modules.get().get(CrystalAura.class));
        lllllllllllllllllIIIllIllIIllIll.add(Modules.get().get(AnchorAura.class));
        lllllllllllllllllIIIllIllIIllIll.add(Modules.get().get(BedAura.class));
        lllllllllllllllllIIIllIllIIllIll.add(Modules.get().get(Surround.class));
        return lllllllllllllllllIIIllIllIIllIll;
    }

    private double getModuleWidth(HudRenderer lllllllllllllllllIIIllIllIlIIlIl, Module lllllllllllllllllIIIllIllIlIIlII) {
        ModuleInfoHud lllllllllllllllllIIIllIllIlIIllI;
        double lllllllllllllllllIIIllIllIlIIlll = lllllllllllllllllIIIllIllIlIIlIl.textWidth(lllllllllllllllllIIIllIllIlIIlII.title);
        if (lllllllllllllllllIIIllIllIlIIllI.info.get().booleanValue()) {
            lllllllllllllllllIIIllIllIlIIlll += lllllllllllllllllIIIllIllIlIIlIl.textWidth(" ") + lllllllllllllllllIIIllIllIlIIlIl.textWidth(lllllllllllllllllIIIllIllIlIIllI.getModuleInfo(lllllllllllllllllIIIllIllIlIIlII));
        }
        return lllllllllllllllllIIIllIllIlIIlll;
    }

    private String getModuleInfo(Module lllllllllllllllllIIIllIllIIlllIl) {
        ModuleInfoHud lllllllllllllllllIIIllIllIlIIIII;
        if (lllllllllllllllllIIIllIllIIlllIl.getInfoString() != null && lllllllllllllllllIIIllIllIIlllIl.isActive() && lllllllllllllllllIIIllIllIlIIIII.info.get().booleanValue()) {
            return lllllllllllllllllIIIllIllIIlllIl.getInfoString();
        }
        if (lllllllllllllllllIIIllIllIIlllIl.isActive()) {
            return "ON";
        }
        return "OFF";
    }

    @Override
    public void update(HudRenderer lllllllllllllllllIIIllIlllIlIlll) {
        ModuleInfoHud lllllllllllllllllIIIllIlllIllIII;
        if (Modules.get() == null || lllllllllllllllllIIIllIlllIllIII.modules.get().isEmpty()) {
            lllllllllllllllllIIIllIlllIllIII.box.setSize(lllllllllllllllllIIIllIlllIlIlll.textWidth("Module Info"), lllllllllllllllllIIIllIlllIlIlll.textHeight());
            return;
        }
        double lllllllllllllllllIIIllIlllIllIll = 0.0;
        double lllllllllllllllllIIIllIlllIllIlI = 0.0;
        int lllllllllllllllllIIIllIlllIllIIl = 0;
        for (Module lllllllllllllllllIIIllIlllIllllI : lllllllllllllllllIIIllIlllIllIII.modules.get()) {
            lllllllllllllllllIIIllIlllIllIll = Math.max(lllllllllllllllllIIIllIlllIllIll, lllllllllllllllllIIIllIlllIllIII.getModuleWidth(lllllllllllllllllIIIllIlllIlIlll, lllllllllllllllllIIIllIlllIllllI));
            lllllllllllllllllIIIllIlllIllIlI += lllllllllllllllllIIIllIlllIlIlll.textHeight();
            if (lllllllllllllllllIIIllIlllIllIIl > 0) {
                lllllllllllllllllIIIllIlllIllIlI += 2.0;
            }
            ++lllllllllllllllllIIIllIlllIllIIl;
        }
        lllllllllllllllllIIIllIlllIllIII.box.setSize(lllllllllllllllllIIIllIlllIllIll, lllllllllllllllllIIIllIlllIllIlI);
    }

    @Override
    public void render(HudRenderer lllllllllllllllllIIIllIlllIIIlIl) {
        ModuleInfoHud lllllllllllllllllIIIllIlllIIlIlI;
        double lllllllllllllllllIIIllIlllIIlIII = lllllllllllllllllIIIllIlllIIlIlI.box.getX();
        double lllllllllllllllllIIIllIlllIIIlll = lllllllllllllllllIIIllIlllIIlIlI.box.getY();
        if (Modules.get() == null || lllllllllllllllllIIIllIlllIIlIlI.modules.get().isEmpty()) {
            lllllllllllllllllIIIllIlllIIIlIl.text("Module Info", lllllllllllllllllIIIllIlllIIlIII, lllllllllllllllllIIIllIlllIIIlll, lllllllllllllllllIIIllIlllIIlIlI.hud.primaryColor.get());
            return;
        }
        for (Module lllllllllllllllllIIIllIlllIIlIll : lllllllllllllllllIIIllIlllIIlIlI.modules.get()) {
            lllllllllllllllllIIIllIlllIIlIlI.renderModule(lllllllllllllllllIIIllIlllIIIlIl, lllllllllllllllllIIIllIlllIIlIll, lllllllllllllllllIIIllIlllIIlIII + lllllllllllllllllIIIllIlllIIlIlI.box.alignX(lllllllllllllllllIIIllIlllIIlIlI.getModuleWidth(lllllllllllllllllIIIllIlllIIIlIl, lllllllllllllllllIIIllIlllIIlIll)), lllllllllllllllllIIIllIlllIIIlll);
            lllllllllllllllllIIIllIlllIIIlll += 2.0 + lllllllllllllllllIIIllIlllIIIlIl.textHeight();
        }
    }
}

