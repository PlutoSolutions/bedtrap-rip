/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.modules.render.hud;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.screens.HudElementScreen;
import minegame159.meteorclient.gui.tabs.builtin.HudTab;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.render.hud.HudElementLayer;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.ActiveModulesHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ArmorHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.BedHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.BiomeHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.BreakingBlockHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.CombatHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.CompassHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ContainerViewerHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.CrystalHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.DurabilityHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.EGAppleHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ExpHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.FpsHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.HoleHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.systems.modules.render.hud.modules.InventoryViewerHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.LagNotifierHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.LookingAtHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ModuleInfoHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ObsidianHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.PingHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.PlayerModelHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.PositionHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.PotionTimersHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.RotationHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.ServerHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.SpeedHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.TextRadarHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.TimeHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.TotemHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.TpsHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.WatermarkHud;
import minegame159.meteorclient.systems.modules.render.hud.modules.WelcomeHud;
import minegame159.meteorclient.utils.render.AlignmentX;
import minegame159.meteorclient.utils.render.AlignmentY;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;

public class HUD
extends Module {
    public final /* synthetic */ Setting<Double> scale;
    public final /* synthetic */ Runnable reset;
    public final /* synthetic */ Setting<SettingColor> secondaryColor;
    public final /* synthetic */ List<HudElement> elements;
    private final /* synthetic */ HudElementLayer topCenter;
    private final /* synthetic */ HudElementLayer bottomRight;
    private final /* synthetic */ SettingGroup sgEditor;
    public final /* synthetic */ Setting<SettingColor> primaryColor;
    private final /* synthetic */ HudElementLayer topRight;
    public final /* synthetic */ Setting<Integer> snappingRange;
    private final /* synthetic */ HudElementLayer bottomCenter;
    private final /* synthetic */ SettingGroup sgGeneral;
    private static final /* synthetic */ HudRenderer RENDERER;
    private final /* synthetic */ HudElementLayer topLeft;
    private final /* synthetic */ HudElementLayer bottomLeft;

    static {
        RENDERER = new HudRenderer();
    }

    private void align() {
        HUD llllllllllllllllllIllIIIlIllIIll;
        RENDERER.begin(llllllllllllllllllIllIIIlIllIIll.scale.get(), 0.0, true);
        llllllllllllllllllIllIIIlIllIIll.topLeft.align();
        llllllllllllllllllIllIIIlIllIIll.topCenter.align();
        llllllllllllllllllIllIIIlIllIIll.topRight.align();
        llllllllllllllllllIllIIIlIllIIll.bottomLeft.align();
        llllllllllllllllllIllIIIlIllIIll.bottomCenter.align();
        llllllllllllllllllIllIIIlIllIIll.bottomRight.align();
        RENDERER.end();
    }

    @Override
    public WWidget getWidget(GuiTheme llllllllllllllllllIllIIIlIIlllIl) {
        HUD llllllllllllllllllIllIIIlIlIIIlI;
        WHorizontalList llllllllllllllllllIllIIIlIlIIIII = llllllllllllllllllIllIIIlIIlllIl.horizontalList();
        WButton llllllllllllllllllIllIIIlIIlllll = llllllllllllllllllIllIIIlIlIIIII.add(llllllllllllllllllIllIIIlIIlllIl.button("Reset")).widget();
        llllllllllllllllllIllIIIlIIlllll.action = llllllllllllllllllIllIIIlIlIIIlI.reset;
        llllllllllllllllllIllIIIlIlIIIII.add(llllllllllllllllllIllIIIlIIlllIl.label("Resets positions (do this after changing scale)."));
        return llllllllllllllllllIllIIIlIlIIIII;
    }

    @Override
    public class_2487 toTag() {
        HUD llllllllllllllllllIllIIIlIIlIIIl;
        class_2487 llllllllllllllllllIllIIIlIIlIIll = super.toTag();
        class_2499 llllllllllllllllllIllIIIlIIlIIlI = new class_2499();
        for (HudElement llllllllllllllllllIllIIIlIIlIlIl : llllllllllllllllllIllIIIlIIlIIIl.elements) {
            llllllllllllllllllIllIIIlIIlIIlI.add((Object)llllllllllllllllllIllIIIlIIlIlIl.toTag());
        }
        llllllllllllllllllIllIIIlIIlIIll.method_10566("modules", (class_2520)llllllllllllllllllIllIIIlIIlIIlI);
        return llllllllllllllllllIllIIIlIIlIIll;
    }

    @EventHandler
    public void onRender(Render2DEvent llllllllllllllllllIllIIIlIlIlIll) {
        HUD llllllllllllllllllIllIIIlIlIllII;
        if (llllllllllllllllllIllIIIlIlIllII.mc.field_1690.field_1866 || llllllllllllllllllIllIIIlIlIllII.mc.field_1690.field_1842) {
            return;
        }
        RENDERER.begin(llllllllllllllllllIllIIIlIlIllII.scale.get(), llllllllllllllllllIllIIIlIlIlIll.tickDelta, false);
        for (HudElement llllllllllllllllllIllIIIlIlIllIl : llllllllllllllllllIllIIIlIlIllII.elements) {
            if (!llllllllllllllllllIllIIIlIlIllIl.active && !HudTab.INSTANCE.isScreen(llllllllllllllllllIllIIIlIlIllII.mc.field_1755) && !(llllllllllllllllllIllIIIlIlIllII.mc.field_1755 instanceof HudElementScreen)) continue;
            llllllllllllllllllIllIIIlIlIllIl.update(RENDERER);
            llllllllllllllllllIllIIIlIlIllIl.render(RENDERER);
        }
        RENDERER.end();
    }

    @Override
    public Module fromTag(class_2487 llllllllllllllllllIllIIIlIIIIIII) {
        HUD llllllllllllllllllIllIIIIlllllll;
        if (llllllllllllllllllIllIIIlIIIIIII.method_10545("modules")) {
            class_2499 llllllllllllllllllIllIIIlIIIIIlI = llllllllllllllllllIllIIIlIIIIIII.method_10554("modules", 10);
            for (class_2520 llllllllllllllllllIllIIIlIIIIIll : llllllllllllllllllIllIIIlIIIIIlI) {
                class_2487 llllllllllllllllllIllIIIlIIIIlIl = (class_2487)llllllllllllllllllIllIIIlIIIIIll;
                HudElement llllllllllllllllllIllIIIlIIIIlII = llllllllllllllllllIllIIIIlllllll.getModule(llllllllllllllllllIllIIIlIIIIlIl.method_10558("name"));
                if (llllllllllllllllllIllIIIlIIIIlII == null) continue;
                llllllllllllllllllIllIIIlIIIIlII.fromTag(llllllllllllllllllIllIIIlIIIIlIl);
            }
        }
        return super.fromTag(llllllllllllllllllIllIIIlIIIIIII);
    }

    private HudElement getModule(String llllllllllllllllllIllIIIIlllIIlI) {
        HUD llllllllllllllllllIllIIIIlllIIIl;
        for (HudElement llllllllllllllllllIllIIIIlllIlII : llllllllllllllllllIllIIIIlllIIIl.elements) {
            if (!llllllllllllllllllIllIIIIlllIlII.name.equals(llllllllllllllllllIllIIIIlllIIlI)) continue;
            return llllllllllllllllllIllIIIIlllIlII;
        }
        return null;
    }

    public HUD() {
        super(Categories.Render, "HUD", "In game overlay.");
        HUD llllllllllllllllllIllIIIlIllIllI;
        llllllllllllllllllIllIIIlIllIllI.sgGeneral = llllllllllllllllllIllIIIlIllIllI.settings.getDefaultGroup();
        llllllllllllllllllIllIIIlIllIllI.sgEditor = llllllllllllllllllIllIIIlIllIllI.settings.createGroup("Editor");
        llllllllllllllllllIllIIIlIllIllI.scale = llllllllllllllllllIllIIIlIllIllI.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of the HUD.").defaultValue(1.0).min(0.75).sliderMin(0.75).sliderMax(4.0).build());
        llllllllllllllllllIllIIIlIllIllI.primaryColor = llllllllllllllllllIllIIIlIllIllI.sgGeneral.add(new ColorSetting.Builder().name("primary-color").description("Primary color of text.").defaultValue(new SettingColor(255, 255, 255)).build());
        llllllllllllllllllIllIIIlIllIllI.secondaryColor = llllllllllllllllllIllIIIlIllIllI.sgGeneral.add(new ColorSetting.Builder().name("secondary-color").description("Secondary color of text.").defaultValue(new SettingColor(175, 175, 175)).build());
        llllllllllllllllllIllIIIlIllIllI.snappingRange = llllllllllllllllllIllIIIlIllIllI.sgEditor.add(new IntSetting.Builder().name("snapping-range").description("Snapping range in editor.").defaultValue(6).build());
        llllllllllllllllllIllIIIlIllIllI.elements = new ArrayList<HudElement>();
        llllllllllllllllllIllIIIlIllIllI.reset = () -> {
            HUD llllllllllllllllllIllIIIIllIIlll;
            llllllllllllllllllIllIIIIllIIlll.align();
            llllllllllllllllllIllIIIIllIIlll.elements.forEach(llllllllllllllllllIllIIIIllIIIll -> {
                llllllllllllllllllIllIIIIllIIIll.active = llllllllllllllllllIllIIIIllIIIll.defaultActive;
                llllllllllllllllllIllIIIIllIIIll.settings.forEach(llllllllllllllllllIllIIIIllIIIIl -> llllllllllllllllllIllIIIIllIIIIl.forEach(Setting::reset));
            });
        };
        llllllllllllllllllIllIIIlIllIllI.topLeft = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Left, AlignmentY.Top, 2, 2);
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new WatermarkHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new FpsHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new PingHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new TpsHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new SpeedHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new BiomeHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new TimeHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new ServerHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new DurabilityHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new BreakingBlockHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new LookingAtHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new ModuleInfoHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new TextRadarHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new BedHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new CrystalHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new EGAppleHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new ExpHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topLeft.add(new ObsidianHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topCenter = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Center, AlignmentY.Top, 0, 2);
        llllllllllllllllllIllIIIlIllIllI.topCenter.add(new InventoryViewerHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topCenter.add(new WelcomeHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topCenter.add(new LagNotifierHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.topRight = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Right, AlignmentY.Top, 2, 2);
        llllllllllllllllllIllIIIlIllIllI.topRight.add(new ActiveModulesHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomLeft = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Left, AlignmentY.Bottom, 2, 2);
        llllllllllllllllllIllIIIlIllIllI.bottomLeft.add(new PlayerModelHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomCenter = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Center, AlignmentY.Bottom, 48, 64);
        llllllllllllllllllIllIIIlIllIllI.bottomCenter.add(new ArmorHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomCenter.add(new CompassHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomCenter.add(new ContainerViewerHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomCenter.add(new TotemHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomRight = new HudElementLayer(RENDERER, llllllllllllllllllIllIIIlIllIllI.elements, AlignmentX.Right, AlignmentY.Bottom, 2, 2);
        llllllllllllllllllIllIIIlIllIllI.bottomRight.add(new PositionHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomRight.add(new RotationHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomRight.add(new PotionTimersHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomRight.add(new HoleHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.bottomRight.add(new CombatHud(llllllllllllllllllIllIIIlIllIllI));
        llllllllllllllllllIllIIIlIllIllI.align();
    }
}

