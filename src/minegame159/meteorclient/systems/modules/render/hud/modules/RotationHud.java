/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class RotationHud
extends DoubleTextHudElement {
    @Override
    protected String getRight() {
        Direction lllllllllllllllllIlIIlIIIlIIllll;
        RotationHud lllllllllllllllllIlIIlIIIlIIllII;
        float lllllllllllllllllIlIIlIIIlIIlllI = lllllllllllllllllIlIIlIIIlIIllII.mc.field_1773.method_19418().method_19330() % 360.0f;
        float lllllllllllllllllIlIIlIIIlIIllIl = lllllllllllllllllIlIIlIIIlIIllII.mc.field_1773.method_19418().method_19329() % 360.0f;
        if (lllllllllllllllllIlIIlIIIlIIlllI < 0.0f) {
            lllllllllllllllllIlIIlIIIlIIlllI += 360.0f;
        }
        if (lllllllllllllllllIlIIlIIIlIIllIl < 0.0f) {
            lllllllllllllllllIlIIlIIIlIIllIl += 360.0f;
        }
        if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 337.5 || (double)lllllllllllllllllIlIIlIIIlIIlllI < 22.5) {
            Direction lllllllllllllllllIlIIlIIIlIllIII = Direction.South;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 22.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 67.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIlll = Direction.SouthWest;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 67.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 112.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIllI = Direction.West;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 112.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 157.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIlIl = Direction.NorthWest;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 157.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 202.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIlII = Direction.North;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 202.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 247.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIIll = Direction.NorthEast;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 247.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 292.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIIlI = Direction.East;
        } else if ((double)lllllllllllllllllIlIIlIIIlIIlllI >= 292.5 && (double)lllllllllllllllllIlIIlIIIlIIlllI < 337.5) {
            Direction lllllllllllllllllIlIIlIIIlIlIIIl = Direction.SouthEast;
        } else {
            lllllllllllllllllIlIIlIIIlIIllll = Direction.NaN;
        }
        if (lllllllllllllllllIlIIlIIIlIIlllI > 180.0f) {
            lllllllllllllllllIlIIlIIIlIIlllI -= 360.0f;
        }
        if (lllllllllllllllllIlIIlIIIlIIllIl > 180.0f) {
            lllllllllllllllllIlIIlIIIlIIllIl -= 360.0f;
        }
        lllllllllllllllllIlIIlIIIlIIllII.setLeft(String.format("%s %s ", lllllllllllllllllIlIIlIIIlIIllll.name, lllllllllllllllllIlIIlIIIlIIllll.axis));
        return String.format("(%.1f, %.1f)", Float.valueOf(lllllllllllllllllIlIIlIIIlIIlllI), Float.valueOf(lllllllllllllllllIlIIlIIIlIIllIl));
    }

    public RotationHud(HUD lllllllllllllllllIlIIlIIIlIlllll) {
        super(lllllllllllllllllIlIIlIIIlIlllll, "rotation", "Displays your rotation.", "");
        RotationHud lllllllllllllllllIlIIlIIIlIllllI;
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

        public /* synthetic */ String name;
        public /* synthetic */ String axis;

        private Direction(String llllIIlIlIlIIII, String llllIIlIlIIllll) {
            Direction llllIIlIlIlIllI;
            llllIIlIlIlIllI.axis = llllIIlIlIIllll;
            llllIIlIlIlIllI.name = llllIIlIlIlIIII;
        }
    }
}

