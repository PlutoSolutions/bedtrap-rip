/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.player;

import minegame159.meteorclient.mixininterface.IExplosion;
import minegame159.meteorclient.mixininterface.IRaycastContext;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;
import net.minecraft.class_1280;
import net.minecraft.class_1282;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1890;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_1934;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5134;

public class DamageUtils {
    private static final class_1927 explosion;
    private static final class_243 vec3d;

    private static double getExposure(class_243 class_2432, class_1297 class_12972, boolean bl, class_3959 class_39592, class_2338 class_23382, boolean bl2) {
        class_238 class_2383 = class_12972.method_5829();
        if (bl) {
            class_243 class_2433 = class_12972.method_18798();
            class_2383.method_989(class_2433.field_1352, class_2433.field_1351, class_2433.field_1350);
        }
        double d = 1.0 / ((class_2383.field_1320 - class_2383.field_1323) * 2.0 + 1.0);
        double d2 = 1.0 / ((class_2383.field_1325 - class_2383.field_1322) * 2.0 + 1.0);
        double d3 = 1.0 / ((class_2383.field_1324 - class_2383.field_1321) * 2.0 + 1.0);
        double d4 = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
        double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        if (!(d < 0.0 || d2 < 0.0 || d3 < 0.0)) {
            int n = 0;
            int n2 = 0;
            for (double d6 = 0.0; d6 <= 1.0; d6 += d) {
                for (double d7 = 0.0; d7 <= 1.0; d7 += d2) {
                    for (double d8 = 0.0; d8 <= 1.0; d8 += d3) {
                        double d9 = class_3532.method_16436((double)d6, (double)class_2383.field_1323, (double)class_2383.field_1320);
                        double d10 = class_3532.method_16436((double)d7, (double)class_2383.field_1322, (double)class_2383.field_1325);
                        double d11 = class_3532.method_16436((double)d8, (double)class_2383.field_1321, (double)class_2383.field_1324);
                        ((IVec3d)vec3d).set(d9 + d4, d10, d11 + d5);
                        ((IRaycastContext)class_39592).set(vec3d, class_2432, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, class_12972);
                        if (DamageUtils.raycast(class_39592, class_23382, bl2).method_17783() == class_239.class_240.field_1333) {
                            ++n;
                        }
                        ++n2;
                    }
                }
            }
            return (double)n / (double)n2;
        }
        return 0.0;
    }

    public static double crystalDamage(class_1657 class_16572, class_243 class_2432, boolean bl, class_3959 class_39592, class_2338 class_23382, boolean bl2) {
        double d;
        if (class_16572 == null) {
            return 0.0;
        }
        if (EntityUtils.getGameMode(class_16572) == class_1934.field_9220 && !(class_16572 instanceof FakePlayerEntity)) {
            return 0.0;
        }
        ((IVec3d)vec3d).set(class_16572.method_19538().field_1352, class_16572.method_19538().field_1351, class_16572.method_19538().field_1350);
        if (bl) {
            ((IVec3d)vec3d).set(DamageUtils.vec3d.field_1352 + class_16572.method_18798().field_1352, DamageUtils.vec3d.field_1351 + class_16572.method_18798().field_1351, DamageUtils.vec3d.field_1350 + class_16572.method_18798().field_1350);
        }
        if ((d = Math.sqrt(vec3d.method_1025(class_2432))) > 12.0) {
            return 0.0;
        }
        double d2 = DamageUtils.getExposure(class_2432, (class_1297)class_16572, bl, class_39592, class_23382, bl2);
        double d3 = (1.0 - d / 12.0) * d2;
        double d4 = (d3 * d3 + d3) / 2.0 * 7.0 * 12.0 + 1.0;
        d4 = DamageUtils.getDamageMultiplied(d4);
        d4 = class_1280.method_5496((float)((float)d4), (float)class_16572.method_6096(), (float)((float)class_16572.method_5996(class_5134.field_23725).method_6194()));
        d4 = DamageUtils.resistanceReduction((class_1309)class_16572, d4);
        ((IExplosion)explosion).set(class_2432, 6.0f, false);
        d4 = DamageUtils.blastProtReduction((class_1297)class_16572, d4, explosion);
        return d4 < 0.0 ? 0.0 : d4;
    }

    static {
        vec3d = new class_243(0.0, 0.0, 0.0);
        explosion = new class_1927(null, null, 0.0, 0.0, 0.0, 6.0f, false, class_1927.class_4179.field_18687);
    }

    private static double blastProtReduction(class_1297 class_12972, double d, class_1927 class_19272) {
        int n = class_1890.method_8219((Iterable)class_12972.method_5661(), (class_1282)class_1282.method_5531((class_1927)class_19272));
        if (n > 20) {
            n = 20;
        }
        return (d *= 1.0 - (double)n / 25.0) < 0.0 ? 0.0 : d;
    }

    private static double getDamageMultiplied(double d) {
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

    private static class_3965 lambda$raycast$0(class_2338 class_23382, boolean bl, class_3959 class_39592, class_2338 class_23383) {
        class_2680 class_26802;
        if (class_23383.equals((Object)class_23382)) {
            class_26802 = class_2246.field_10540.method_9564();
        } else {
            class_26802 = Utils.mc.field_1687.method_8320(class_23383);
            if (class_26802.method_26204().method_9520() < 600.0f && bl) {
                class_26802 = class_2246.field_10124.method_9564();
            }
        }
        class_243 class_2432 = class_39592.method_17750();
        class_243 class_2433 = class_39592.method_17747();
        class_265 class_2652 = class_39592.method_17748(class_26802, (class_1922)Utils.mc.field_1687, class_23383);
        class_3965 class_39652 = Utils.mc.field_1687.method_17745(class_2432, class_2433, class_23383, class_2652, class_26802);
        class_265 class_2654 = class_259.method_1073();
        class_3965 class_39653 = class_2654.method_1092(class_2432, class_2433, class_23383);
        double d = class_39652 == null ? Double.MAX_VALUE : class_39592.method_17750().method_1025(class_39652.method_17784());
        double d2 = class_39653 == null ? Double.MAX_VALUE : class_39592.method_17750().method_1025(class_39653.method_17784());
        return d <= d2 ? class_39652 : class_39653;
    }

    private static double resistanceReduction(class_1309 class_13092, double d) {
        if (class_13092.method_6059(class_1294.field_5907)) {
            int n = class_13092.method_6112(class_1294.field_5907).method_5578() + 1;
            d *= 1.0 - (double)n * 0.2;
        }
        return d < 0.0 ? 0.0 : d;
    }

    private static class_3965 raycast(class_3959 class_39592, class_2338 class_23382, boolean bl) {
        return (class_3965)class_1922.method_17744((class_3959)class_39592, (arg_0, arg_1) -> DamageUtils.lambda$raycast$0(class_23382, bl, arg_0, arg_1), DamageUtils::lambda$raycast$1);
    }

    private static class_3965 lambda$raycast$1(class_3959 class_39592) {
        class_243 class_2432 = class_39592.method_17750().method_1020(class_39592.method_17747());
        return class_3965.method_17778((class_243)class_39592.method_17747(), (class_2350)class_2350.method_10142((double)class_2432.field_1352, (double)class_2432.field_1351, (double)class_2432.field_1350), (class_2338)new class_2338(class_39592.method_17747()));
    }
}

