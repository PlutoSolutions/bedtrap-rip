/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.world;

import net.minecraft.class_2350;

public class Dir {
    public static final byte UP = (byte)2;
    public static final byte DOWN = (byte)4;
    public static final byte EAST;
    public static final byte NORTH;
    public static final byte SOUTH;
    public static final byte WEST;

    public static boolean isNot(int n, byte by) {
        return (n & by) == by;
    }

    public static boolean is(int n, byte by) {
        return (n & by) != by;
    }

    static {
        NORTH = (byte)8;
        EAST = (byte)64;
        SOUTH = (byte)16;
        WEST = (byte)32;
    }

    public static byte get(class_2350 class_23502) {
        switch (1.$SwitchMap$net$minecraft$util$math$Direction[class_23502.ordinal()]) {
            case 1: {
                return 2;
            }
            case 2: {
                return 4;
            }
            case 3: {
                return 8;
            }
            case 4: {
                return 16;
            }
            case 5: {
                return 32;
            }
            case 6: {
                return 64;
            }
        }
        return 0;
    }
}

