/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class RotationHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        float f = this.mc.field_1773.method_19418().method_19330() % 360.0f;
        float f2 = this.mc.field_1773.method_19418().method_19329() % 360.0f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        Direction direction = (double)f >= 337.5 || (double)f < 22.5 ? Direction.South : ((double)f >= 22.5 && (double)f < 67.5 ? Direction.SouthWest : ((double)f >= 67.5 && (double)f < 112.5 ? Direction.West : ((double)f >= 112.5 && (double)f < 157.5 ? Direction.NorthWest : ((double)f >= 157.5 && (double)f < 202.5 ? Direction.North : ((double)f >= 202.5 && (double)f < 247.5 ? Direction.NorthEast : ((double)f >= 247.5 && (double)f < 292.5 ? Direction.East : ((double)f >= 292.5 && (double)f < 337.5 ? Direction.SouthEast : Direction.NaN)))))));
        if (f > 180.0f) {
            f -= 360.0f;
        }
        if (f2 > 180.0f) {
            f2 -= 360.0f;
        }
        this.setLeft(String.format("%s %s ", direction.name, direction.axis));
        return String.format("(%.1f, %.1f)", Float.valueOf(f), Float.valueOf(f2));
    }

    public RotationHud(HUD hUD) {
        super(hUD, "rotation", "Displays your rotation.", "");
    }

    private static enum Direction {
        South("South", "Z+"),
        SouthEast("South East", "Z+ X+"),
        West("West", "X-"),
        NorthWest("North West", "Z- X-"),
        North("North", "Z-"),
        NorthEast("North East", "Z- X+"),
        East("East", "X+"),
        SouthWest("South West", "Z+ X-"),
        NaN("NaN", "NaN");

        public String name;
        public String axis;

        private Direction(String string2, String string3) {
            this.axis = string3;
            this.name = string2;
        }
    }
}

