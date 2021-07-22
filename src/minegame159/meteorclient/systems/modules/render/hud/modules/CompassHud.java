/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_3532;

public class CompassHud
extends HudElement {
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ double yaw;
    private /* synthetic */ double pitch;
    private final /* synthetic */ Color NORTH;
    private final /* synthetic */ Setting<Double> scale;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void update(HudRenderer llllllllllllllllllIllllIIIlIlIII) {
        CompassHud llllllllllllllllllIllllIIIlIlIIl;
        llllllllllllllllllIllllIIIlIlIIl.pitch = !llllllllllllllllllIllllIIIlIlIIl.isInEditor() ? (double)llllllllllllllllllIllllIIIlIlIIl.mc.field_1724.field_5965 : 90.0;
        llllllllllllllllllIllllIIIlIlIIl.pitch = class_3532.method_15350((double)(llllllllllllllllllIllllIIIlIlIIl.pitch + 30.0), (double)-90.0, (double)90.0);
        llllllllllllllllllIllllIIIlIlIIl.pitch = Math.toRadians(llllllllllllllllllIllllIIIlIlIIl.pitch);
        llllllllllllllllllIllllIIIlIlIIl.yaw = !llllllllllllllllllIllllIIIlIlIIl.isInEditor() ? (double)llllllllllllllllllIllllIIIlIlIIl.mc.field_1724.field_6031 : 180.0;
        llllllllllllllllllIllllIIIlIlIIl.yaw = class_3532.method_15338((double)llllllllllllllllllIllllIIIlIlIIl.yaw);
        llllllllllllllllllIllllIIIlIlIIl.yaw = Math.toRadians(llllllllllllllllllIllllIIIlIlIIl.yaw);
        llllllllllllllllllIllllIIIlIlIIl.box.setSize(100.0 * llllllllllllllllllIllllIIIlIlIIl.scale.get(), 100.0 * llllllllllllllllllIllllIIIlIlIIl.scale.get());
    }

    private double getX(Direction llllllllllllllllllIllllIIIIIlIll) {
        CompassHud llllllllllllllllllIllllIIIIIlIlI;
        return Math.sin(llllllllllllllllllIllllIIIIIlIlI.getPosOnCompass(llllllllllllllllllIllllIIIIIlIll)) * llllllllllllllllllIllllIIIIIlIlI.scale.get() * 40.0;
    }

    public CompassHud(HUD llllllllllllllllllIllllIIIlIllIl) {
        super(llllllllllllllllllIllllIIIlIllIl, "compass", "Displays a compass.");
        CompassHud llllllllllllllllllIllllIIIlIllII;
        llllllllllllllllllIllllIIIlIllII.sgGeneral = llllllllllllllllllIllllIIIlIllII.settings.getDefaultGroup();
        llllllllllllllllllIllllIIIlIllII.mode = llllllllllllllllllIllllIIIlIllII.sgGeneral.add(new EnumSetting.Builder().name("type").description("Which type of axis to show.").defaultValue(Mode.Pole).build());
        llllllllllllllllllIllllIIIlIllII.scale = llllllllllllllllllIllllIIIlIllII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of compass.").defaultValue(2.0).sliderMin(1.0).sliderMax(5.0).build());
        llllllllllllllllllIllllIIIlIllII.NORTH = new Color(225, 45, 45);
    }

    @Override
    public void render(HudRenderer llllllllllllllllllIllllIIIIllIlI) {
        CompassHud llllllllllllllllllIllllIIIIllIll;
        double llllllllllllllllllIllllIIIIllIIl = llllllllllllllllllIllllIIIIllIll.box.getX() + llllllllllllllllllIllllIIIIllIll.box.width / 2.0;
        double llllllllllllllllllIllllIIIIllIII = llllllllllllllllllIllllIIIIllIll.box.getY() + llllllllllllllllllIllllIIIIllIll.box.height / 2.0;
        for (Direction llllllllllllllllllIllllIIIIlllII : Direction.values()) {
            String llllllllllllllllllIllllIIIIlllIl = llllllllllllllllllIllllIIIIllIll.mode.get() == Mode.Axis ? llllllllllllllllllIllllIIIIlllII.getAlternate() : llllllllllllllllllIllllIIIIlllII.name();
            llllllllllllllllllIllllIIIIllIlI.text(llllllllllllllllllIllllIIIIlllIl, llllllllllllllllllIllllIIIIllIIl + llllllllllllllllllIllllIIIIllIll.getX(llllllllllllllllllIllllIIIIlllII) - llllllllllllllllllIllllIIIIllIlI.textWidth(llllllllllllllllllIllllIIIIlllIl) / 2.0, llllllllllllllllllIllllIIIIllIII + llllllllllllllllllIllllIIIIllIll.getY(llllllllllllllllllIllllIIIIlllII) - llllllllllllllllllIllllIIIIllIlI.textHeight() / 2.0, llllllllllllllllllIllllIIIIlllII == Direction.N ? llllllllllllllllllIllllIIIIllIll.NORTH : (Color)llllllllllllllllllIllllIIIIllIll.hud.primaryColor.get());
        }
    }

    private double getPosOnCompass(Direction llllllllllllllllllIlllIlllllllll) {
        CompassHud llllllllllllllllllIllllIIIIIIIII;
        return llllllllllllllllllIllllIIIIIIIII.yaw + (double)llllllllllllllllllIlllIlllllllll.ordinal() * Math.PI / 2.0;
    }

    private double getY(Direction llllllllllllllllllIllllIIIIIIIll) {
        CompassHud llllllllllllllllllIllllIIIIIIlII;
        return Math.cos(llllllllllllllllllIllllIIIIIIlII.getPosOnCompass(llllllllllllllllllIllllIIIIIIIll)) * Math.sin(llllllllllllllllllIllllIIIIIIlII.pitch) * llllllllllllllllllIllllIIIIIIlII.scale.get() * 40.0;
    }

    private static enum Direction {
        N("Z-"),
        W("X-"),
        S("Z+"),
        E("X+");

        /* synthetic */ String alternate;

        public String getAlternate() {
            Direction lIIIIIIIIlII;
            return lIIIIIIIIlII.alternate;
        }

        private Direction(String lIIIIIIIIlll) {
            Direction lIIIIIIIllII;
            lIIIIIIIllII.alternate = lIIIIIIIIlll;
        }
    }

    public static enum Mode {
        Axis,
        Pole;


        private Mode() {
            Mode lIIlllIIIIII;
        }
    }
}

