/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1280
 *  net.minecraft.class_1282
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_1927
 *  net.minecraft.class_1927$class_4179
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_5134
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
    private static final /* synthetic */ class_1927 explosion;

    public static double crystalDamage(class_1309 llllllllllllllllIllIIlIIlIllIlll, class_243 llllllllllllllllIllIIlIIlIllIIII) {
        if (llllllllllllllllIllIIlIIlIllIlll instanceof class_1657 && ((class_1657)llllllllllllllllIllIIlIIlIllIlll).method_7337()) {
            return 0.0;
        }
        double llllllllllllllllIllIIlIIlIllIlIl = Math.sqrt(llllllllllllllllIllIIlIIlIllIlll.method_5707(llllllllllllllllIllIIlIIlIllIIII));
        if (llllllllllllllllIllIIlIIlIllIlIl > 12.0) {
            return 0.0;
        }
        double llllllllllllllllIllIIlIIlIllIlII = class_1927.method_17752((class_243)llllllllllllllllIllIIlIIlIllIIII, (class_1297)llllllllllllllllIllIIlIIlIllIlll);
        double llllllllllllllllIllIIlIIlIllIIll = (1.0 - llllllllllllllllIllIIlIIlIllIlIl / 12.0) * llllllllllllllllIllIIlIIlIllIlII;
        double llllllllllllllllIllIIlIIlIllIIlI = (llllllllllllllllIllIIlIIlIllIIll * llllllllllllllllIllIIlIIlIllIIll + llllllllllllllllIllIIlIIlIllIIll) / 2.0 * 7.0 * 12.0 + 1.0;
        llllllllllllllllIllIIlIIlIllIIlI = DamageCalcUtils.getDamageForDifficulty(llllllllllllllllIllIIlIIlIllIIlI);
        llllllllllllllllIllIIlIIlIllIIlI = DamageCalcUtils.resistanceReduction(llllllllllllllllIllIIlIIlIllIlll, llllllllllllllllIllIIlIIlIllIIlI);
        llllllllllllllllIllIIlIIlIllIIlI = class_1280.method_5496((float)((float)llllllllllllllllIllIIlIIlIllIIlI), (float)llllllllllllllllIllIIlIIlIllIlll.method_6096(), (float)((float)llllllllllllllllIllIIlIIlIllIlll.method_5996(class_5134.field_23725).method_6194()));
        ((IExplosion)explosion).set(llllllllllllllllIllIIlIIlIllIIII, 6.0f, false);
        llllllllllllllllIllIIlIIlIllIIlI = DamageCalcUtils.blastProtReduction((class_1297)llllllllllllllllIllIIlIIlIllIlll, llllllllllllllllIllIIlIIlIllIIlI, explosion);
        return llllllllllllllllIllIIlIIlIllIIlI < 0.0 ? 0.0 : llllllllllllllllIllIIlIIlIllIIlI;
    }

    private static double normalProtReduction(class_1297 llllllllllllllllIllIIlIIIlllllIl, double llllllllllllllllIllIIlIIIllllIIl) {
        int llllllllllllllllIllIIlIIIllllIll = class_1890.method_8219((Iterable)llllllllllllllllIllIIlIIIlllllIl.method_5661(), (class_1282)class_1282.field_5869);
        if (llllllllllllllllIllIIlIIIllllIll > 20) {
            llllllllllllllllIllIIlIIIllllIll = 20;
        }
        return (llllllllllllllllIllIIlIIIllllIIl *= 1.0 - (double)llllllllllllllllIllIIlIIIllllIll / 25.0) < 0.0 ? 0.0 : llllllllllllllllIllIIlIIIllllIIl;
    }

    public static double anchorDamage(class_1309 llllllllllllllllIllIIlIIlIIlIllI, class_243 llllllllllllllllIllIIlIIlIIlIIlI) {
        Utils.mc.field_1687.method_8650(new class_2338(llllllllllllllllIllIIlIIlIIlIIlI), false);
        double llllllllllllllllIllIIlIIlIIlIlII = DamageCalcUtils.bedDamage(llllllllllllllllIllIIlIIlIIlIllI, llllllllllllllllIllIIlIIlIIlIIlI);
        Utils.mc.field_1687.method_8501(new class_2338(llllllllllllllllIllIIlIIlIIlIIlI), class_2246.field_23152.method_9564());
        return llllllllllllllllIllIIlIIlIIlIlII;
    }

    private static double resistanceReduction(class_1309 llllllllllllllllIllIIlIIIllIIlll, double llllllllllllllllIllIIlIIIllIIllI) {
        if (llllllllllllllllIllIIlIIIllIIlll.method_6059(class_1294.field_5907)) {
            int llllllllllllllllIllIIlIIIllIlIII = llllllllllllllllIllIIlIIIllIIlll.method_6112(class_1294.field_5907).method_5578() + 1;
            llllllllllllllllIllIIlIIIllIIllI *= 1.0 - (double)llllllllllllllllIllIIlIIIllIlIII * 0.2;
        }
        return llllllllllllllllIllIIlIIIllIIllI < 0.0 ? 0.0 : llllllllllllllllIllIIlIIIllIIllI;
    }

    static {
        explosion = new class_1927(null, null, 0.0, 0.0, 0.0, 6.0f, false, class_1927.class_4179.field_18687);
    }

    public DamageCalcUtils() {
        DamageCalcUtils llllllllllllllllIllIIlIIlIllllll;
    }

    public static double bedDamage(class_1309 llllllllllllllllIllIIlIIlIlIIlIl, class_243 llllllllllllllllIllIIlIIlIlIIlII) {
        if (llllllllllllllllIllIIlIIlIlIIlIl instanceof class_1657 && ((class_1657)llllllllllllllllIllIIlIIlIlIIlIl).field_7503.field_7477) {
            return 0.0;
        }
        double llllllllllllllllIllIIlIIlIlIIIll = Math.sqrt(llllllllllllllllIllIIlIIlIlIIlIl.method_5707(llllllllllllllllIllIIlIIlIlIIlII));
        if (llllllllllllllllIllIIlIIlIlIIIll > 10.0) {
            return 0.0;
        }
        double llllllllllllllllIllIIlIIlIlIIIlI = class_1927.method_17752((class_243)llllllllllllllllIllIIlIIlIlIIlII, (class_1297)llllllllllllllllIllIIlIIlIlIIlIl);
        double llllllllllllllllIllIIlIIlIlIIIIl = (1.0 - llllllllllllllllIllIIlIIlIlIIIll / 10.0) * llllllllllllllllIllIIlIIlIlIIIlI;
        double llllllllllllllllIllIIlIIlIlIIIII = (llllllllllllllllIllIIlIIlIlIIIIl * llllllllllllllllIllIIlIIlIlIIIIl + llllllllllllllllIllIIlIIlIlIIIIl) / 2.0 * 7.0 * 10.0 + 1.0;
        llllllllllllllllIllIIlIIlIlIIIII = DamageCalcUtils.getDamageForDifficulty(llllllllllllllllIllIIlIIlIlIIIII);
        llllllllllllllllIllIIlIIlIlIIIII = DamageCalcUtils.resistanceReduction(llllllllllllllllIllIIlIIlIlIIlIl, llllllllllllllllIllIIlIIlIlIIIII);
        llllllllllllllllIllIIlIIlIlIIIII = class_1280.method_5496((float)((float)llllllllllllllllIllIIlIIlIlIIIII), (float)llllllllllllllllIllIIlIIlIlIIlIl.method_6096(), (float)((float)llllllllllllllllIllIIlIIlIlIIlIl.method_5996(class_5134.field_23725).method_6194()));
        ((IExplosion)explosion).set(llllllllllllllllIllIIlIIlIlIIlII, 5.0f, true);
        llllllllllllllllIllIIlIIlIlIIIII = DamageCalcUtils.blastProtReduction((class_1297)llllllllllllllllIllIIlIIlIlIIlIl, llllllllllllllllIllIIlIIlIlIIIII, explosion);
        if (llllllllllllllllIllIIlIIlIlIIIII < 0.0) {
            llllllllllllllllIllIIlIIlIlIIIII = 0.0;
        }
        return llllllllllllllllIllIIlIIlIlIIIII;
    }

    private static double getDamageForDifficulty(double llllllllllllllllIllIIlIIlIIIIIlI) {
        switch (Utils.mc.field_1687.method_8407()) {
            case field_5801: {
                return 0.0;
            }
            case field_5805: {
                return Math.min(llllllllllllllllIllIIlIIlIIIIIlI / 2.0 + 1.0, llllllllllllllllIllIIlIIlIIIIIlI);
            }
            case field_5807: {
                return llllllllllllllllIllIIlIIlIIIIIlI * 3.0 / 2.0;
            }
        }
        return llllllllllllllllIllIIlIIlIIIIIlI;
    }

    public static double getSwordDamage(class_1657 llllllllllllllllIllIIlIIlIIIIlll, boolean llllllllllllllllIllIIlIIlIIIlIIl) {
        double llllllllllllllllIllIIlIIlIIIlIII = 0.0;
        if (llllllllllllllllIllIIlIIlIIIlIIl) {
            if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7909() == class_1802.field_8802) {
                llllllllllllllllIllIIlIIlIIIlIII += 7.0;
            } else if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7909() == class_1802.field_8845) {
                llllllllllllllllIllIIlIIlIIIlIII += 4.0;
            } else if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7909() == class_1802.field_8371) {
                llllllllllllllllIllIIlIIlIIIlIII += 6.0;
            } else if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7909() == class_1802.field_8528) {
                llllllllllllllllIllIIlIIlIIIlIII += 5.0;
            } else if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7909() == class_1802.field_8091) {
                llllllllllllllllIllIIlIIlIIIlIII += 4.0;
            }
            llllllllllllllllIllIIlIIlIIIlIII *= 1.5;
        }
        if (llllllllllllllllIllIIlIIlIIIIlll.method_6030().method_7921() != null && class_1890.method_8222((class_1799)llllllllllllllllIllIIlIIlIIIIlll.method_6030()).containsKey((Object)class_1893.field_9118)) {
            int llllllllllllllllIllIIlIIlIIIllII = class_1890.method_8225((class_1887)class_1893.field_9118, (class_1799)llllllllllllllllIllIIlIIlIIIIlll.method_6030());
            llllllllllllllllIllIIlIIlIIIlIII += 0.5 * (double)llllllllllllllllIllIIlIIlIIIllII + 0.5;
        }
        if (llllllllllllllllIllIIlIIlIIIIlll.method_6088().containsKey((Object)class_1294.field_5910)) {
            int llllllllllllllllIllIIlIIlIIIlIll = Objects.requireNonNull(llllllllllllllllIllIIlIIlIIIIlll.method_6112(class_1294.field_5910)).method_5578() + 1;
            llllllllllllllllIllIIlIIlIIIlIII += (double)(3 * llllllllllllllllIllIIlIIlIIIlIll);
        }
        llllllllllllllllIllIIlIIlIIIlIII = DamageCalcUtils.resistanceReduction((class_1309)llllllllllllllllIllIIlIIlIIIIlll, llllllllllllllllIllIIlIIlIIIlIII);
        llllllllllllllllIllIIlIIlIIIlIII = class_1280.method_5496((float)((float)llllllllllllllllIllIIlIIlIIIlIII), (float)llllllllllllllllIllIIlIIlIIIIlll.method_6096(), (float)((float)llllllllllllllllIllIIlIIlIIIIlll.method_5996(class_5134.field_23725).method_6194()));
        return (llllllllllllllllIllIIlIIlIIIlIII = DamageCalcUtils.normalProtReduction((class_1297)llllllllllllllllIllIIlIIlIIIIlll, llllllllllllllllIllIIlIIlIIIlIII)) < 0.0 ? 0.0 : llllllllllllllllIllIIlIIlIIIlIII;
    }

    private static double blastProtReduction(class_1297 llllllllllllllllIllIIlIIIllIllll, double llllllllllllllllIllIIlIIIllIlllI, class_1927 llllllllllllllllIllIIlIIIlllIIIl) {
        int llllllllllllllllIllIIlIIIlllIIII = class_1890.method_8219((Iterable)llllllllllllllllIllIIlIIIllIllll.method_5661(), (class_1282)class_1282.method_5531((class_1927)llllllllllllllllIllIIlIIIlllIIIl));
        if (llllllllllllllllIllIIlIIIlllIIII > 20) {
            llllllllllllllllIllIIlIIIlllIIII = 20;
        }
        return (llllllllllllllllIllIIlIIIllIlllI *= 1.0 - (double)llllllllllllllllIllIIlIIIlllIIII / 25.0) < 0.0 ? 0.0 : llllllllllllllllIllIIlIIIllIlllI;
    }
}

