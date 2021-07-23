/*
 * Decompiled with CFR 0.151.
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
    public static boolean isValidIntrumentTextFile(int n, InstrumentType instrumentType) {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$notebot$NotebotUtils$InstrumentType[instrumentType.ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                if (n == 1) {
                    return false;
                }
                if (n == 2) {
                    return false;
                }
                if (n == 3) {
                    return false;
                }
                if (n == 11) {
                    return false;
                }
            }
            case 3: {
                return n == 0;
            }
            case 5: {
                return n == 4;
            }
            case 6: {
                return n == 6;
            }
            case 11: {
                return n == 5;
            }
            case 8: {
                return n == 8;
            }
            case 12: {
                return n == 7;
            }
            case 15: {
                return n == 9;
            }
            case 13: {
                return n == 10;
            }
            case 9: {
                return n == 11;
            }
            case 10: {
                return n == 12;
            }
            case 7: {
                return n == 13;
            }
            case 4: {
                return n == 14;
            }
            case 14: {
                return n == 15;
            }
        }
        return true;
    }

    public static boolean isValidInstrument(class_2338 class_23382, InstrumentType instrumentType) {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$notebot$NotebotUtils$InstrumentType[instrumentType.ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                class_2680 class_26802 = Utils.mc.field_1687.method_8320(class_23382);
                if (class_26802.method_26204() == class_2246.field_10179) {
                    class_2766 class_27662 = (class_2766)class_26802.method_11654((class_2769)class_2428.field_11325);
                    if (class_27662 == class_2766.field_12653) {
                        return false;
                    }
                    if (class_27662 == class_2766.field_12645) {
                        return false;
                    }
                    if (class_27662 == class_2766.field_12643) {
                        return false;
                    }
                    return class_27662 != class_2766.field_18285;
                }
                class_2680 class_26803 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                if (class_26803.method_26207() == class_3614.field_15916) {
                    return false;
                }
                if (class_26803.method_26207() == class_3614.field_15942) {
                    return false;
                }
                if (class_26803.method_26207() == class_3614.field_15914) {
                    return false;
                }
                return class_26803.method_26204() != class_2246.field_10085;
            }
            case 3: {
                class_2680 class_26804 = Utils.mc.field_1687.method_8320(class_23382);
                if (class_26804.method_26204() == class_2246.field_10179) {
                    return class_26804.method_11654((class_2769)class_2428.field_11325) == class_2766.field_12648;
                }
                class_2680 class_26805 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                if (class_26805.method_26207() == class_3614.field_15932) {
                    return false;
                }
                if (class_26805.method_26207() == class_3614.field_15916) {
                    return false;
                }
                if (class_26805.method_26207() == class_3614.field_15942) {
                    return false;
                }
                if (class_26805.method_26207() == class_3614.field_15914) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10205) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10460) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10225) {
                    return false;
                }
                if (class_26805.method_26207() == class_3614.field_15931) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10166) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10085) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10114) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10261) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10234) {
                    return false;
                }
                if (class_26805.method_26204() == class_2246.field_10359) {
                    return false;
                }
                return class_26805.method_26204() != class_2246.field_10171;
            }
            case 4: {
                class_2680 class_26806 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_26806.method_26204() == class_2246.field_10359;
            }
            case 5: {
                class_2680 class_26807 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_26807.method_26207() == class_3614.field_15932;
            }
            case 6: {
                class_2680 class_26808 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_26808.method_26204() == class_2246.field_10205;
            }
            case 7: {
                class_2680 class_26809 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_26809.method_26204() == class_2246.field_10234;
            }
            case 8: {
                class_2680 class_268010 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268010.method_26204() == class_2246.field_10225;
            }
            case 9: {
                class_2680 class_268011 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268011.method_26204() == class_2246.field_10114;
            }
            case 10: {
                class_2680 class_268012 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268012.method_26204() == class_2246.field_10261;
            }
            case 11: {
                class_2680 class_268013 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268013.method_26204() == class_2246.field_10460;
            }
            case 12: {
                class_2680 class_268014 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268014.method_26207() == class_3614.field_15931;
            }
            case 13: {
                class_2680 class_268015 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268015.method_26204() == class_2246.field_10085;
            }
            case 14: {
                class_2680 class_268016 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268016.method_26204() == class_2246.field_10171;
            }
            case 15: {
                class_2680 class_268017 = Utils.mc.field_1687.method_8320(class_23382.method_10074());
                return class_268017.method_26204() == class_2246.field_10166;
            }
        }
        return false;
    }

    public static class_3414 getInstrumentSound(InstrumentType instrumentType) {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$notebot$NotebotUtils$InstrumentType[instrumentType.ordinal()]) {
            case 5: {
                return class_3417.field_14624;
            }
            case 6: {
                return class_3417.field_14793;
            }
            case 11: {
                return class_3417.field_14989;
            }
            case 8: {
                return class_3417.field_14725;
            }
            case 12: {
                return class_3417.field_14903;
            }
            case 15: {
                return class_3417.field_14776;
            }
            case 13: {
                return class_3417.field_18308;
            }
            case 9: {
                return class_3417.field_18309;
            }
            case 10: {
                return class_3417.field_18310;
            }
            case 7: {
                return class_3417.field_18311;
            }
            case 4: {
                return class_3417.field_18312;
            }
            case 14: {
                return class_3417.field_14622;
            }
        }
        return class_3417.field_15114;
    }

    public static boolean isValidInstrumentNbsFile(byte by, InstrumentType instrumentType) {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$notebot$NotebotUtils$InstrumentType[instrumentType.ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                if (by == 2) {
                    return false;
                }
                if (by == 3) {
                    return false;
                }
                return by != 4;
            }
            case 3: {
                return by == 0;
            }
            case 5: {
                return by == 1;
            }
            case 6: {
                return by == 7;
            }
            case 11: {
                return by == 6;
            }
            case 8: {
                return by == 8;
            }
            case 12: {
                return by == 5;
            }
            case 15: {
                return by == 9;
            }
            case 13: {
                return by == 10;
            }
            case 9: {
                return by == 11;
            }
            case 10: {
                return by == 12;
            }
            case 7: {
                return by == 13;
            }
            case 4: {
                return by == 14;
            }
            case 14: {
                return by == 15;
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

    }
}

