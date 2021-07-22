/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2350
 */
package minegame159.meteorclient.utils.world;

import net.minecraft.class_2350;

public class Dir {
    public static final /* synthetic */ byte UP;
    public static final /* synthetic */ byte DOWN;
    public static final /* synthetic */ byte EAST;
    public static final /* synthetic */ byte NORTH;
    public static final /* synthetic */ byte SOUTH;
    public static final /* synthetic */ byte WEST;

    public static boolean isNot(int llllllllllllllllIllIIlIlllIIllII, byte llllllllllllllllIllIIlIlllIIlIll) {
        return (llllllllllllllllIllIIlIlllIIllII & llllllllllllllllIllIIlIlllIIlIll) == llllllllllllllllIllIIlIlllIIlIll;
    }

    public Dir() {
        Dir llllllllllllllllIllIIlIlllIllIlI;
    }

    public static boolean is(int llllllllllllllllIllIIlIlllIlIlII, byte llllllllllllllllIllIIlIlllIlIIIl) {
        return (llllllllllllllllIllIIlIlllIlIlII & llllllllllllllllIllIIlIlllIlIIIl) != llllllllllllllllIllIIlIlllIlIIIl;
    }

    static {
        UP = (byte)2;
        DOWN = (byte)4;
        NORTH = (byte)8;
        EAST = (byte)64;
        SOUTH = (byte)16;
        WEST = (byte)32;
    }

    public static byte get(class_2350 llllllllllllllllIllIIlIlllIllIII) {
        switch (llllllllllllllllIllIIlIlllIllIII) {
            case field_11036: {
                return 2;
            }
            case field_11033: {
                return 4;
            }
            case field_11043: {
                return 8;
            }
            case field_11035: {
                return 16;
            }
            case field_11039: {
                return 32;
            }
            case field_11034: {
                return 64;
            }
        }
        return 0;
    }
}

