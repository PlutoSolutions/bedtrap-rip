/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.player;

import java.util.Objects;
import minegame159.meteorclient.mixininterface.IExplosion;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1927;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_5134;

public class DamageCalcUtils {
    private static final class_1927 explosion = new class_1927(null, null, 0.0, 0.0, 0.0, 6.0f, false, class_1927.class_4179.field_18687);

    public static double crystalDamage(class_1309 class_13092, class_243 class_2432) {
        if (class_13092 instanceof class_1657 && ((class_1657)class_13092).method_7337()) {
            return 0.0;
        }
        double d = Math.sqrt(class_13092.method_5707(class_2432));
        if (d > 12.0) {
            return 0.0;
        }
        double d2 = class_1927.method_17752((class_243)class_2432, (class_1297)class_13092);
        double d3 = (1.0 - d / 12.0) * d2;
        double d4 = (d3 * d3 + d3) / 2.0 * 7.0 * 12.0 + 1.0;
        d4 = DamageCalcUtils.getDamageForDifficulty(d4);
        d4 = DamageCalcUtils.resistanceReduction(class_13092, d4);
        d4 = class_1280.method_5496((float)((float)d4), (float)class_13092.method_6096(), (float)((float)class_13092.method_5996(class_5134.field_23725).method_6194()));
        ((IExplosion)explosion).set(class_2432, 6.0f, false);
        d4 = DamageCalcUtils.blastProtReduction((class_1297)class_13092, d4, explosion);
        return d4 < 0.0 ? 0.0 : d4;
    }

    private static double normalProtReduction(class_1297 class_12972, double d) {
        int n = class_1890.method_8219((Iterable)class_12972.method_5661(), (class_1282)class_1282.field_5869);
        if (n > 20) {
            n = 20;
        }
        return (d *= 1.0 - (double)n / 25.0) < 0.0 ? 0.0 : d;
    }

    public static double anchorDamage(class_1309 class_13092, class_243 class_2432) {
        Utils.mc.field_1687.method_8650(new class_2338(class_2432), false);
        double d = DamageCalcUtils.bedDamage(class_13092, class_2432);
        Utils.mc.field_1687.method_8501(new class_2338(class_2432), class_2246.field_23152.method_9564());
        return d;
    }

    private static double resistanceReduction(class_1309 class_13092, double d) {
        if (class_13092.method_6059(class_1294.field_5907)) {
            int n = class_13092.method_6112(class_1294.field_5907).method_5578() + 1;
            d *= 1.0 - (double)n * 0.2;
        }
        return d < 0.0 ? 0.0 : d;
    }

    public static double bedDamage(class_1309 class_13092, class_243 class_2432) {
        if (class_13092 instanceof class_1657 && ((class_1657)class_13092).field_7503.field_7477) {
            return 0.0;
        }
        double d = Math.sqrt(class_13092.method_5707(class_2432));
        if (d > 10.0) {
            return 0.0;
        }
        double d2 = class_1927.method_17752((class_243)class_2432, (class_1297)class_13092);
        double d3 = (1.0 - d / 10.0) * d2;
        double d4 = (d3 * d3 + d3) / 2.0 * 7.0 * 10.0 + 1.0;
        d4 = DamageCalcUtils.getDamageForDifficulty(d4);
        d4 = DamageCalcUtils.resistanceReduction(class_13092, d4);
        d4 = class_1280.method_5496((float)((float)d4), (float)class_13092.method_6096(), (float)((float)class_13092.method_5996(class_5134.field_23725).method_6194()));
        ((IExplosion)explosion).set(class_2432, 5.0f, true);
        d4 = DamageCalcUtils.blastProtReduction((class_1297)class_13092, d4, explosion);
        if (d4 < 0.0) {
            d4 = 0.0;
        }
        return d4;
    }

    private static double getDamageForDifficulty(double d) {
        switch (1.$SwitchMap$net$minecraft$world$Difficulty[Utils.mc.field_1687.method_8407().ordinal()]) {
            case 1: {
                return 0.0;
            }
            case 2: {
                return Math.min(d / 2.0 + 1.0, d);
            }
            case 3: {
                return d * 3.0 / 2.0;
            }
        }
        return d;
    }

    public static double getSwordDamage(class_1657 class_16572, boolean bl) {
        int n;
        double d = 0.0;
        if (bl) {
            if (class_16572.method_6030().method_7909() == class_1802.field_8802) {
                d += 7.0;
            } else if (class_16572.method_6030().method_7909() == class_1802.field_8845) {
                d += 4.0;
            } else if (class_16572.method_6030().method_7909() == class_1802.field_8371) {
                d += 6.0;
            } else if (class_16572.method_6030().method_7909() == class_1802.field_8528) {
                d += 5.0;
            } else if (class_16572.method_6030().method_7909() == class_1802.field_8091) {
                d += 4.0;
            }
            d *= 1.5;
        }
        if (class_16572.method_6030().method_7921() != null && class_1890.method_8222((class_1799)class_16572.method_6030()).containsKey(class_1893.field_9118)) {
            n = class_1890.method_8225((class_1887)class_1893.field_9118, (class_1799)class_16572.method_6030());
            d += 0.5 * (double)n + 0.5;
        }
        if (class_16572.method_6088().containsKey(class_1294.field_5910)) {
            n = Objects.requireNonNull(class_16572.method_6112(class_1294.field_5910)).method_5578() + 1;
            d += (double)(3 * n);
        }
        d = DamageCalcUtils.resistanceReduction((class_1309)class_16572, d);
        d = class_1280.method_5496((float)((float)d), (float)class_16572.method_6096(), (float)((float)class_16572.method_5996(class_5134.field_23725).method_6194()));
        return (d = DamageCalcUtils.normalProtReduction((class_1297)class_16572, d)) < 0.0 ? 0.0 : d;
    }

    private static double blastProtReduction(class_1297 class_12972, double d, class_1927 class_19272) {
        int n = class_1890.method_8219((Iterable)class_12972.method_5661(), (class_1282)class_1282.method_5531((class_1927)class_19272));
        if (n > 20) {
            n = 20;
        }
        return (d *= 1.0 - (double)n / 25.0) < 0.0 ? 0.0 : d;
    }
}

