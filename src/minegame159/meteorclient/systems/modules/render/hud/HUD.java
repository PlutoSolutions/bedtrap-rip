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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class HUD
extends Module {
    public final Setting<Double> scale;
    public final Runnable reset;
    public final Setting<SettingColor> secondaryColor;
    public final List<HudElement> elements;
    private final HudElementLayer topCenter;
    private final HudElementLayer bottomRight;
    private final SettingGroup sgEditor;
    public final Setting<SettingColor> primaryColor;
    private final HudElementLayer topRight;
    public final Setting<Integer> snappingRange;
    private final HudElementLayer bottomCenter;
    private final SettingGroup sgGeneral;
    private static final HudRenderer RENDERER = new HudRenderer();
    private final HudElementLayer topLeft;
    private final HudElementLayer bottomLeft;

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    private void lambda$new$2() {
        this.align();
        this.elements.forEach(HUD::lambda$new$1);
    }

    private void align() {
        RENDERER.begin(this.scale.get(), 0.0, true);
        this.topLeft.align();
        this.topCenter.align();
        this.topRight.align();
        this.bottomLeft.align();
        this.bottomCenter.align();
        this.bottomRight.align();
        RENDERER.end();
    }

    @Override
    public WWidget getWidget(GuiTheme guiTheme) {
        WHorizontalList wHorizontalList = guiTheme.horizontalList();
        WButton wButton = wHorizontalList.add(guiTheme.button("Reset")).widget();
        wButton.action = this.reset;
        wHorizontalList.add(guiTheme.label("Resets positions (do this after changing scale)."));
        return wHorizontalList;
    }

    private static void lambda$new$0(SettingGroup settingGroup) {
        settingGroup.forEach(Setting::reset);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = super.toTag();
        class_2499 class_24992 = new class_2499();
        for (HudElement hudElement : this.elements) {
            class_24992.add((Object)hudElement.toTag());
        }
        class_24872.method_10566("modules", (class_2520)class_24992);
        return class_24872;
    }

    @EventHandler
    public void onRender(Render2DEvent render2DEvent) {
        if (this.mc.field_1690.field_1866 || this.mc.field_1690.field_1842) {
            return;
        }
        RENDERER.begin(this.scale.get(), render2DEvent.tickDelta, false);
        for (HudElement hudElement : this.elements) {
            if (!hudElement.active && !HudTab.INSTANCE.isScreen(this.mc.field_1755) && !(this.mc.field_1755 instanceof HudElementScreen)) continue;
            hudElement.update(RENDERER);
            hudElement.render(RENDERER);
        }
        RENDERER.end();
    }

    private static void lambda$new$1(HudElement hudElement) {
        hudElement.active = hudElement.defaultActive;
        hudElement.settings.forEach(HUD::lambda$new$0);
    }

    @Override
    public Module fromTag(class_2487 class_24872) {
        if (class_24872.method_10545("modules")) {
            class_2499 class_24992 = class_24872.method_10554("modules", 10);
            for (class_2520 class_25202 : class_24992) {
                class_2487 class_24873 = (class_2487)class_25202;
                HudElement hudElement = this.getModule(class_24873.method_10558("name"));
                if (hudElement == null) continue;
                hudElement.fromTag(class_24873);
            }
        }
        return super.fromTag(class_24872);
    }

    private HudElement getModule(String string) {
        for (HudElement hudElement : this.elements) {
            if (!hudElement.name.equals(string)) continue;
            return hudElement;
        }
        return null;
    }

    public HUD() {
        super(Categories.Render, "HUD", "In game overlay.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgEditor = this.settings.createGroup("Editor");
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of the HUD.").defaultValue(1.0).min(0.75).sliderMin(0.75).sliderMax(4.0).build());
        this.primaryColor = this.sgGeneral.add(new ColorSetting.Builder().name("primary-color").description("Primary color of text.").defaultValue(new SettingColor(255, 255, 255)).build());
        this.secondaryColor = this.sgGeneral.add(new ColorSetting.Builder().name("secondary-color").description("Secondary color of text.").defaultValue(new SettingColor(175, 175, 175)).build());
        this.snappingRange = this.sgEditor.add(new IntSetting.Builder().name("snapping-range").description("Snapping range in editor.").defaultValue(6).build());
        this.elements = new ArrayList<HudElement>();
        this.reset = this::lambda$new$2;
        this.topLeft = new HudElementLayer(RENDERER, this.elements, AlignmentX.Left, AlignmentY.Top, 2, 2);
        this.topLeft.add(new WatermarkHud(this));
        this.topLeft.add(new FpsHud(this));
        this.topLeft.add(new PingHud(this));
        this.topLeft.add(new TpsHud(this));
        this.topLeft.add(new SpeedHud(this));
        this.topLeft.add(new BiomeHud(this));
        this.topLeft.add(new TimeHud(this));
        this.topLeft.add(new ServerHud(this));
        this.topLeft.add(new DurabilityHud(this));
        this.topLeft.add(new BreakingBlockHud(this));
        this.topLeft.add(new LookingAtHud(this));
        this.topLeft.add(new ModuleInfoHud(this));
        this.topLeft.add(new TextRadarHud(this));
        this.topLeft.add(new BedHud(this));
        this.topLeft.add(new CrystalHud(this));
        this.topLeft.add(new EGAppleHud(this));
        this.topLeft.add(new ExpHud(this));
        this.topLeft.add(new ObsidianHud(this));
        this.topCenter = new HudElementLayer(RENDERER, this.elements, AlignmentX.Center, AlignmentY.Top, 0, 2);
        this.topCenter.add(new InventoryViewerHud(this));
        this.topCenter.add(new WelcomeHud(this));
        this.topCenter.add(new LagNotifierHud(this));
        this.topRight = new HudElementLayer(RENDERER, this.elements, AlignmentX.Right, AlignmentY.Top, 2, 2);
        this.topRight.add(new ActiveModulesHud(this));
        this.bottomLeft = new HudElementLayer(RENDERER, this.elements, AlignmentX.Left, AlignmentY.Bottom, 2, 2);
        this.bottomLeft.add(new PlayerModelHud(this));
        this.bottomCenter = new HudElementLayer(RENDERER, this.elements, AlignmentX.Center, AlignmentY.Bottom, 48, 64);
        this.bottomCenter.add(new ArmorHud(this));
        this.bottomCenter.add(new CompassHud(this));
        this.bottomCenter.add(new ContainerViewerHud(this));
        this.bottomCenter.add(new TotemHud(this));
        this.bottomRight = new HudElementLayer(RENDERER, this.elements, AlignmentX.Right, AlignmentY.Bottom, 2, 2);
        this.bottomRight.add(new PositionHud(this));
        this.bottomRight.add(new RotationHud(this));
        this.bottomRight.add(new PotionTimersHud(this));
        this.bottomRight.add(new HoleHud(this));
        this.bottomRight.add(new CombatHud(this));
        this.align();
    }
}

