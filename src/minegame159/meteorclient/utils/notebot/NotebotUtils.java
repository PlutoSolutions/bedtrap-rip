/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2428
 *  net.minecraft.class_2680
 *  net.minecraft.class_2766
 *  net.minecraft.class_2769
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3614
 */
package minegame159.meteorclient.utils.notebot;

import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2428;
import net.minecraft.class_2680;
import net.minecraft.class_2766;
import net.minecraft.class_2769;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3614;

public class NotebotUtils {
    public NotebotUtils() {
        NotebotUtils lllllllllllllllllllIlIIIIIIIlllI;
    }

    public static boolean isValidIntrumentTextFile(int lllllllllllllllllllIIllllllIlIIl, InstrumentType lllllllllllllllllllIIllllllIIllI) {
        switch (lllllllllllllllllllIIllllllIIllI) {
            case Any: {
                return true;
            }
            case NotDrums: {
                if (lllllllllllllllllllIIllllllIlIIl == 1) {
                    return false;
                }
                if (lllllllllllllllllllIIllllllIlIIl == 2) {
                    return false;
                }
                if (lllllllllllllllllllIIllllllIlIIl == 3) {
                    return false;
                }
                if (lllllllllllllllllllIIllllllIlIIl == 11) {
                    return false;
                }
            }
            case Harp: {
                return lllllllllllllllllllIIllllllIlIIl == 0;
            }
            case Bass: {
                return lllllllllllllllllllIIllllllIlIIl == 4;
            }
            case Bells: {
                return lllllllllllllllllllIIllllllIlIIl == 6;
            }
            case Flute: {
                return lllllllllllllllllllIIllllllIlIIl == 5;
            }
            case Chimes: {
                return lllllllllllllllllllIIllllllIlIIl == 8;
            }
            case Guitar: {
                return lllllllllllllllllllIIllllllIlIIl == 7;
            }
            case Xylophone: {
                return lllllllllllllllllllIIllllllIlIIl == 9;
            }
            case IronXylophone: {
                return lllllllllllllllllllIIllllllIlIIl == 10;
            }
            case CowBell: {
                return lllllllllllllllllllIIllllllIlIIl == 11;
            }
            case Didgeridoo: {
                return lllllllllllllllllllIIllllllIlIIl == 12;
            }
            case Bit: {
                return lllllllllllllllllllIIllllllIlIIl == 13;
            }
            case Banjo: {
                return lllllllllllllllllllIIllllllIlIIl == 14;
            }
            case Pling: {
                return lllllllllllllllllllIIllllllIlIIl == 15;
            }
        }
        return true;
    }

    public static boolean isValidInstrument(class_2338 lllllllllllllllllllIIlllllllIlIl, InstrumentType lllllllllllllllllllIIlllllllIlII) {
        switch (lllllllllllllllllllIIlllllllIlII) {
            case Any: {
                return true;
            }
            case NotDrums: {
                class_2680 lllllllllllllllllllIlIIIIIIIIllI = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl);
                if (lllllllllllllllllllIlIIIIIIIIllI.method_26204() == class_2246.field_10179) {
                    class_2766 lllllllllllllllllllIlIIIIIIIlIII = (class_2766)lllllllllllllllllllIlIIIIIIIIllI.method_11654((class_2769)class_2428.field_11325);
                    if (lllllllllllllllllllIlIIIIIIIlIII == class_2766.field_12653) {
                        return false;
                    }
                    if (lllllllllllllllllllIlIIIIIIIlIII == class_2766.field_12645) {
                        return false;
                    }
                    if (lllllllllllllllllllIlIIIIIIIlIII == class_2766.field_12643) {
                        return false;
                    }
                    return lllllllllllllllllllIlIIIIIIIlIII != class_2766.field_18285;
                }
                class_2680 lllllllllllllllllllIlIIIIIIIIlll = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                if (lllllllllllllllllllIlIIIIIIIIlll.method_26207() == class_3614.field_15916) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlll.method_26207() == class_3614.field_15942) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlll.method_26207() == class_3614.field_15914) {
                    return false;
                }
                return lllllllllllllllllllIlIIIIIIIIlll.method_26204() != class_2246.field_10085;
            }
            case Harp: {
                class_2680 lllllllllllllllllllIlIIIIIIIIlII = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl);
                if (lllllllllllllllllllIlIIIIIIIIlII.method_26204() == class_2246.field_10179) {
                    return lllllllllllllllllllIlIIIIIIIIlII.method_11654((class_2769)class_2428.field_11325) == class_2766.field_12648;
                }
                class_2680 lllllllllllllllllllIlIIIIIIIIlIl = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26207() == class_3614.field_15932) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26207() == class_3614.field_15916) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26207() == class_3614.field_15942) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26207() == class_3614.field_15914) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10205) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10460) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10225) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26207() == class_3614.field_15931) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10166) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10085) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10114) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10261) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10234) {
                    return false;
                }
                if (lllllllllllllllllllIlIIIIIIIIlIl.method_26204() == class_2246.field_10359) {
                    return false;
                }
                return lllllllllllllllllllIlIIIIIIIIlIl.method_26204() != class_2246.field_10171;
            }
            case Banjo: {
                class_2680 lllllllllllllllllllIlIIIIIIIIIll = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIlIIIIIIIIIll.method_26204() == class_2246.field_10359;
            }
            case Bass: {
                class_2680 lllllllllllllllllllIlIIIIIIIIIlI = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIlIIIIIIIIIlI.method_26207() == class_3614.field_15932;
            }
            case Bells: {
                class_2680 lllllllllllllllllllIlIIIIIIIIIIl = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIlIIIIIIIIIIl.method_26204() == class_2246.field_10205;
            }
            case Bit: {
                class_2680 lllllllllllllllllllIlIIIIIIIIIII = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIlIIIIIIIIIII.method_26204() == class_2246.field_10234;
            }
            case Chimes: {
                class_2680 lllllllllllllllllllIIlllllllllll = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIlllllllllll.method_26204() == class_2246.field_10225;
            }
            case CowBell: {
                class_2680 lllllllllllllllllllIIllllllllllI = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIllllllllllI.method_26204() == class_2246.field_10114;
            }
            case Didgeridoo: {
                class_2680 lllllllllllllllllllIIlllllllllIl = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIlllllllllIl.method_26204() == class_2246.field_10261;
            }
            case Flute: {
                class_2680 lllllllllllllllllllIIlllllllllII = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIlllllllllII.method_26204() == class_2246.field_10460;
            }
            case Guitar: {
                class_2680 lllllllllllllllllllIIllllllllIll = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIllllllllIll.method_26207() == class_3614.field_15931;
            }
            case IronXylophone: {
                class_2680 lllllllllllllllllllIIllllllllIlI = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIllllllllIlI.method_26204() == class_2246.field_10085;
            }
            case Pling: {
                class_2680 lllllllllllllllllllIIllllllllIIl = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIllllllllIIl.method_26204() == class_2246.field_10171;
            }
            case Xylophone: {
                class_2680 lllllllllllllllllllIIllllllllIII = Utils.mc.field_1687.method_8320(lllllllllllllllllllIIlllllllIlIl.method_10074());
                return lllllllllllllllllllIIllllllllIII.method_26204() == class_2246.field_10166;
            }
        }
        return false;
    }

    public static class_3414 getInstrumentSound(InstrumentType lllllllllllllllllllIIllllllIIlII) {
        switch (lllllllllllllllllllIIllllllIIlII) {
            case Bass: {
                return class_3417.field_14624;
            }
            case Bells: {
                return class_3417.field_14793;
            }
            case Flute: {
                return class_3417.field_14989;
            }
            case Chimes: {
                return class_3417.field_14725;
            }
            case Guitar: {
                return class_3417.field_14903;
            }
            case Xylophone: {
                return class_3417.field_14776;
            }
            case IronXylophone: {
                return class_3417.field_18308;
            }
            case CowBell: {
                return class_3417.field_18309;
            }
            case Didgeridoo: {
                return class_3417.field_18310;
            }
            case Bit: {
                return class_3417.field_18311;
            }
            case Banjo: {
                return class_3417.field_18312;
            }
            case Pling: {
                return class_3417.field_14622;
            }
        }
        return class_3417.field_15114;
    }

    public static boolean isValidInstrumentNbsFile(byte lllllllllllllllllllIIllllllIllll, InstrumentType lllllllllllllllllllIIllllllIllII) {
        switch (lllllllllllllllllllIIllllllIllII) {
            case Any: {
                return true;
            }
            case NotDrums: {
                if (lllllllllllllllllllIIllllllIllll == 2) {
                    return false;
                }
                if (lllllllllllllllllllIIllllllIllll == 3) {
                    return false;
                }
                return lllllllllllllllllllIIllllllIllll != 4;
            }
            case Harp: {
                return lllllllllllllllllllIIllllllIllll == 0;
            }
            case Bass: {
                return lllllllllllllllllllIIllllllIllll == 1;
            }
            case Bells: {
                return lllllllllllllllllllIIllllllIllll == 7;
            }
            case Flute: {
                return lllllllllllllllllllIIllllllIllll == 6;
            }
            case Chimes: {
                return lllllllllllllllllllIIllllllIllll == 8;
            }
            case Guitar: {
                return lllllllllllllllllllIIllllllIllll == 5;
            }
            case Xylophone: {
                return lllllllllllllllllllIIllllllIllll == 9;
            }
            case IronXylophone: {
                return lllllllllllllllllllIIllllllIllll == 10;
            }
            case CowBell: {
                return lllllllllllllllllllIIllllllIllll == 11;
            }
            case Didgeridoo: {
                return lllllllllllllllllllIIllllllIllll == 12;
            }
            case Bit: {
                return lllllllllllllllllllIIllllllIllll == 13;
            }
            case Banjo: {
                return lllllllllllllllllllIIllllllIllll == 14;
            }
            case Pling: {
                return lllllllllllllllllllIIllllllIllll == 15;
            }
        }
        return true;
    }

    public static enum InstrumentType {
        Any,
        NotDrums,
        Harp,
        Bass,
        Bells,
        Flute,
        Chimes,
        Guitar,
        Xylophone,
        IronXylophone,
        CowBell,
        Didgeridoo,
        Bit,
        Banjo,
        Pling;


        private InstrumentType() {
            InstrumentType lIIlIlllIlII;
        }
    }
}

