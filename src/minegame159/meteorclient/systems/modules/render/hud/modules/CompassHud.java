/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Mode> mode;
    private double yaw;
    private double pitch;
    private final Color NORTH;
    private final Setting<Double> scale;
    private final SettingGroup sgGeneral;

    @Override
    public void update(HudRenderer hudRenderer) {
        this.pitch = !this.isInEditor() ? (double)this.mc.field_1724.field_5965 : 90.0;
        this.pitch = class_3532.method_15350((double)(this.pitch + 30.0), (double)-90.0, (double)90.0);
        this.pitch = Math.toRadians(this.pitch);
        this.yaw = !this.isInEditor() ? (double)this.mc.field_1724.field_6031 : 180.0;
        this.yaw = class_3532.method_15338((double)this.yaw);
        this.yaw = Math.toRadians(this.yaw);
        this.box.setSize(100.0 * this.scale.get(), 100.0 * this.scale.get());
    }

    private double getX(Direction direction) {
        return Math.sin(this.getPosOnCompass(direction)) * this.scale.get() * 40.0;
    }

    public CompassHud(HUD hUD) {
        super(hUD, "compass", "Displays a compass.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("type").description("Which type of axis to show.").defaultValue(Mode.Pole).build());
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("The scale of compass.").defaultValue(2.0).sliderMin(1.0).sliderMax(5.0).build());
        this.NORTH = new Color(225, 45, 45);
    }

    @Override
    public void render(HudRenderer hudRenderer) {
        double d = this.box.getX() + this.box.width / 2.0;
        double d2 = this.box.getY() + this.box.height / 2.0;
        for (Direction direction : Direction.values()) {
            String string = this.mode.get() == Mode.Axis ? direction.getAlternate() : direction.name();
            hudRenderer.text(string, d + this.getX(direction) - hudRenderer.textWidth(string) / 2.0, d2 + this.getY(direction) - hudRenderer.textHeight() / 2.0, direction == Direction.N ? this.NORTH : (Color)this.hud.primaryColor.get());
            if (!false) continue;
            return;
        }
    }

    private double getPosOnCompass(Direction direction) {
        return this.yaw + (double)direction.ordinal() * Math.PI / 2.0;
    }

    private double getY(Direction direction) {
        return Math.cos(this.getPosOnCompass(direction)) * Math.sin(this.pitch) * this.scale.get() * 40.0;
    }

    private static enum Direction {
        N("Z-"),
        W("X-"),
        S("Z+"),
        E("X+");

        String alternate;

        public String getAlternate() {
            return this.alternate;
        }

        private Direction(String string2) {
            this.alternate = string2;
        }
    }

    public static enum Mode {
        Axis,
        Pole;

    }
}

