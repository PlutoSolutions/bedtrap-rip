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
 *  net.minecraft.class_1890
 *  net.minecraft.class_1922
 *  net.minecraft.class_1927
 *  net.minecraft.class_1927$class_4179
 *  net.minecraft.class_1934
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_5134
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
    private static final /* synthetic */ class_1927 explosion;
    private static final /* synthetic */ class_243 vec3d;

    private static double getExposure(class_243 llllllllllllllllIllIlIIIIIlllIII, class_1297 llllllllllllllllIllIlIIIIIllIlll, boolean llllllllllllllllIllIlIIIIIllIllI, class_3959 llllllllllllllllIllIlIIIIIlIlIIl, class_2338 llllllllllllllllIllIlIIIIIllIlII, boolean llllllllllllllllIllIlIIIIIlIIlll) {
        class_238 llllllllllllllllIllIlIIIIIllIIlI = llllllllllllllllIllIlIIIIIllIlll.method_5829();
        if (llllllllllllllllIllIlIIIIIllIllI) {
            class_243 llllllllllllllllIllIlIIIIlIIIIIl = llllllllllllllllIllIlIIIIIllIlll.method_18798();
            llllllllllllllllIllIlIIIIIllIIlI.method_989(llllllllllllllllIllIlIIIIlIIIIIl.field_1352, llllllllllllllllIllIlIIIIlIIIIIl.field_1351, llllllllllllllllIllIlIIIIlIIIIIl.field_1350);
        }
        double llllllllllllllllIllIlIIIIIllIIIl = 1.0 / ((llllllllllllllllIllIlIIIIIllIIlI.field_1320 - llllllllllllllllIllIlIIIIIllIIlI.field_1323) * 2.0 + 1.0);
        double llllllllllllllllIllIlIIIIIllIIII = 1.0 / ((llllllllllllllllIllIlIIIIIllIIlI.field_1325 - llllllllllllllllIllIlIIIIIllIIlI.field_1322) * 2.0 + 1.0);
        double llllllllllllllllIllIlIIIIIlIllll = 1.0 / ((llllllllllllllllIllIlIIIIIllIIlI.field_1324 - llllllllllllllllIllIlIIIIIllIIlI.field_1321) * 2.0 + 1.0);
        double llllllllllllllllIllIlIIIIIlIlllI = (1.0 - Math.floor(1.0 / llllllllllllllllIllIlIIIIIllIIIl) * llllllllllllllllIllIlIIIIIllIIIl) / 2.0;
        double llllllllllllllllIllIlIIIIIlIllIl = (1.0 - Math.floor(1.0 / llllllllllllllllIllIlIIIIIlIllll) * llllllllllllllllIllIlIIIIIlIllll) / 2.0;
        if (!(llllllllllllllllIllIlIIIIIllIIIl < 0.0 || llllllllllllllllIllIlIIIIIllIIII < 0.0 || llllllllllllllllIllIlIIIIIlIllll < 0.0)) {
            int llllllllllllllllIllIlIIIIIlllIlI = 0;
            int llllllllllllllllIllIlIIIIIlllIIl = 0;
            for (double llllllllllllllllIllIlIIIIIlllIll = 0.0; llllllllllllllllIllIlIIIIIlllIll <= 1.0; llllllllllllllllIllIlIIIIIlllIll += llllllllllllllllIllIlIIIIIllIIIl) {
                for (double llllllllllllllllIllIlIIIIIllllII = 0.0; llllllllllllllllIllIlIIIIIllllII <= 1.0; llllllllllllllllIllIlIIIIIllllII += llllllllllllllllIllIlIIIIIllIIII) {
                    for (double llllllllllllllllIllIlIIIIIllllIl = 0.0; llllllllllllllllIllIlIIIIIllllIl <= 1.0; llllllllllllllllIllIlIIIIIllllIl += llllllllllllllllIllIlIIIIIlIllll) {
                        double llllllllllllllllIllIlIIIIlIIIIII = class_3532.method_16436((double)llllllllllllllllIllIlIIIIIlllIll, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1323, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1320);
                        double llllllllllllllllIllIlIIIIIllllll = class_3532.method_16436((double)llllllllllllllllIllIlIIIIIllllII, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1322, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1325);
                        double llllllllllllllllIllIlIIIIIlllllI = class_3532.method_16436((double)llllllllllllllllIllIlIIIIIllllIl, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1321, (double)llllllllllllllllIllIlIIIIIllIIlI.field_1324);
                        ((IVec3d)vec3d).set(llllllllllllllllIllIlIIIIlIIIIII + llllllllllllllllIllIlIIIIIlIlllI, llllllllllllllllIllIlIIIIIllllll, llllllllllllllllIllIlIIIIIlllllI + llllllllllllllllIllIlIIIIIlIllIl);
                        ((IRaycastContext)llllllllllllllllIllIlIIIIIlIlIIl).set(vec3d, llllllllllllllllIllIlIIIIIlllIII, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, llllllllllllllllIllIlIIIIIllIlll);
                        if (DamageUtils.raycast(llllllllllllllllIllIlIIIIIlIlIIl, llllllllllllllllIllIlIIIIIllIlII, llllllllllllllllIllIlIIIIIlIIlll).method_17783() == class_239.class_240.field_1333) {
                            ++llllllllllllllllIllIlIIIIIlllIlI;
                        }
                        ++llllllllllllllllIllIlIIIIIlllIIl;
                    }
                }
            }
            return (double)llllllllllllllllIllIlIIIIIlllIlI / (double)llllllllllllllllIllIlIIIIIlllIIl;
        }
        return 0.0;
    }

    public static double crystalDamage(class_1657 llllllllllllllllIllIlIIIIlllIlll, class_243 llllllllllllllllIllIlIIIlIIIIIII, boolean llllllllllllllllIllIlIIIIlllIlIl, class_3959 llllllllllllllllIllIlIIIIllllllI, class_2338 llllllllllllllllIllIlIIIIlllIIll, boolean llllllllllllllllIllIlIIIIlllIIlI) {
        double llllllllllllllllIllIlIIIIllllIll;
        if (llllllllllllllllIllIlIIIIlllIlll == null) {
            return 0.0;
        }
        if (EntityUtils.getGameMode(llllllllllllllllIllIlIIIIlllIlll) == class_1934.field_9220 && !(llllllllllllllllIllIlIIIIlllIlll instanceof FakePlayerEntity)) {
            return 0.0;
        }
        ((IVec3d)vec3d).set(llllllllllllllllIllIlIIIIlllIlll.method_19538().field_1352, llllllllllllllllIllIlIIIIlllIlll.method_19538().field_1351, llllllllllllllllIllIlIIIIlllIlll.method_19538().field_1350);
        if (llllllllllllllllIllIlIIIIlllIlIl) {
            ((IVec3d)vec3d).set(DamageUtils.vec3d.field_1352 + llllllllllllllllIllIlIIIIlllIlll.method_18798().field_1352, DamageUtils.vec3d.field_1351 + llllllllllllllllIllIlIIIIlllIlll.method_18798().field_1351, DamageUtils.vec3d.field_1350 + llllllllllllllllIllIlIIIIlllIlll.method_18798().field_1350);
        }
        if ((llllllllllllllllIllIlIIIIllllIll = Math.sqrt(vec3d.method_1025(llllllllllllllllIllIlIIIlIIIIIII))) > 12.0) {
            return 0.0;
        }
        double llllllllllllllllIllIlIIIIllllIlI = DamageUtils.getExposure(llllllllllllllllIllIlIIIlIIIIIII, (class_1297)llllllllllllllllIllIlIIIIlllIlll, llllllllllllllllIllIlIIIIlllIlIl, llllllllllllllllIllIlIIIIllllllI, llllllllllllllllIllIlIIIIlllIIll, llllllllllllllllIllIlIIIIlllIIlI);
        double llllllllllllllllIllIlIIIIllllIIl = (1.0 - llllllllllllllllIllIlIIIIllllIll / 12.0) * llllllllllllllllIllIlIIIIllllIlI;
        double llllllllllllllllIllIlIIIIllllIII = (llllllllllllllllIllIlIIIIllllIIl * llllllllllllllllIllIlIIIIllllIIl + llllllllllllllllIllIlIIIIllllIIl) / 2.0 * 7.0 * 12.0 + 1.0;
        llllllllllllllllIllIlIIIIllllIII = DamageUtils.getDamageMultiplied(llllllllllllllllIllIlIIIIllllIII);
        llllllllllllllllIllIlIIIIllllIII = class_1280.method_5496((float)((float)llllllllllllllllIllIlIIIIllllIII), (float)llllllllllllllllIllIlIIIIlllIlll.method_6096(), (float)((float)llllllllllllllllIllIlIIIIlllIlll.method_5996(class_5134.field_23725).method_6194()));
        llllllllllllllllIllIlIIIIllllIII = DamageUtils.resistanceReduction((class_1309)llllllllllllllllIllIlIIIIlllIlll, llllllllllllllllIllIlIIIIllllIII);
        ((IExplosion)explosion).set(llllllllllllllllIllIlIIIlIIIIIII, 6.0f, false);
        llllllllllllllllIllIlIIIIllllIII = DamageUtils.blastProtReduction((class_1297)llllllllllllllllIllIlIIIIlllIlll, llllllllllllllllIllIlIIIIllllIII, explosion);
        return llllllllllllllllIllIlIIIIllllIII < 0.0 ? 0.0 : llllllllllllllllIllIlIIIIllllIII;
    }

    static {
        vec3d = new class_243(0.0, 0.0, 0.0);
        explosion = new class_1927(null, null, 0.0, 0.0, 0.0, 6.0f, false, class_1927.class_4179.field_18687);
    }

    private static double blastProtReduction(class_1297 llllllllllllllllIllIlIIIIllIIllI, double llllllllllllllllIllIlIIIIllIIlIl, class_1927 llllllllllllllllIllIlIIIIllIIlII) {
        int llllllllllllllllIllIlIIIIllIIIll = class_1890.method_8219((Iterable)llllllllllllllllIllIlIIIIllIIllI.method_5661(), (class_1282)class_1282.method_5531((class_1927)llllllllllllllllIllIlIIIIllIIlII));
        if (llllllllllllllllIllIlIIIIllIIIll > 20) {
            llllllllllllllllIllIlIIIIllIIIll = 20;
        }
        return (llllllllllllllllIllIlIIIIllIIlIl *= 1.0 - (double)llllllllllllllllIllIlIIIIllIIIll / 25.0) < 0.0 ? 0.0 : llllllllllllllllIllIlIIIIllIIlIl;
    }

    private static double getDamageMultiplied(double llllllllllllllllIllIlIIIIllIlIll) {
        switch (Utils.mc.field_1687.method_8407()) {
            case field_5801: {
                return 0.0;
            }
            case field_5805: {
                return Math.min(llllllllllllllllIllIlIIIIllIlIll / 2.0 + 1.0, llllllllllllllllIllIlIIIIllIlIll);
            }
            case field_5807: {
                return llllllllllllllllIllIlIIIIllIlIll * 3.0 / 2.0;
            }
        }
        return llllllllllllllllIllIlIIIIllIlIll;
    }

    public DamageUtils() {
        DamageUtils llllllllllllllllIllIlIIIlIIIllIl;
    }

    private static double resistanceReduction(class_1309 llllllllllllllllIllIlIIIIlIllIlI, double llllllllllllllllIllIlIIIIlIllIIl) {
        if (llllllllllllllllIllIlIIIIlIllIlI.method_6059(class_1294.field_5907)) {
            int llllllllllllllllIllIlIIIIlIllIll = llllllllllllllllIllIlIIIIlIllIlI.method_6112(class_1294.field_5907).method_5578() + 1;
            llllllllllllllllIllIlIIIIlIllIIl *= 1.0 - (double)llllllllllllllllIllIlIIIIlIllIll * 0.2;
        }
        return llllllllllllllllIllIlIIIIlIllIIl < 0.0 ? 0.0 : llllllllllllllllIllIlIIIIlIllIIl;
    }

    private static class_3965 raycast(class_3959 llllllllllllllllIllIlIIIIIIlIlIl, class_2338 llllllllllllllllIllIlIIIIIIlIlII, boolean llllllllllllllllIllIlIIIIIIlIIII) {
        return (class_3965)class_1922.method_17744((class_3959)llllllllllllllllIllIlIIIIIIlIlIl, (llllllllllllllllIllIIllllllIllII, llllllllllllllllIllIIllllllllIII) -> {
            class_2680 llllllllllllllllIllIIlllllllIlll;
            if (llllllllllllllllIllIIllllllllIII.equals((Object)llllllllllllllllIllIlIIIIIIlIlII)) {
                class_2680 llllllllllllllllIllIIlllllllllII = class_2246.field_10540.method_9564();
            } else {
                llllllllllllllllIllIIlllllllIlll = Utils.mc.field_1687.method_8320(llllllllllllllllIllIIllllllllIII);
                if (llllllllllllllllIllIIlllllllIlll.method_26204().method_9520() < 600.0f && llllllllllllllllIllIlIIIIIIlIIII) {
                    llllllllllllllllIllIIlllllllIlll = class_2246.field_10124.method_9564();
                }
            }
            class_243 llllllllllllllllIllIIlllllllIllI = llllllllllllllllIllIIllllllIllII.method_17750();
            class_243 llllllllllllllllIllIIlllllllIlIl = llllllllllllllllIllIIllllllIllII.method_17747();
            class_265 llllllllllllllllIllIIlllllllIlII = llllllllllllllllIllIIllllllIllII.method_17748(llllllllllllllllIllIIlllllllIlll, (class_1922)Utils.mc.field_1687, llllllllllllllllIllIIllllllllIII);
            class_3965 llllllllllllllllIllIIlllllllIIll = Utils.mc.field_1687.method_17745(llllllllllllllllIllIIlllllllIllI, llllllllllllllllIllIIlllllllIlIl, llllllllllllllllIllIIllllllllIII, llllllllllllllllIllIIlllllllIlII, llllllllllllllllIllIIlllllllIlll);
            class_265 llllllllllllllllIllIIlllllllIIlI = class_259.method_1073();
            class_3965 llllllllllllllllIllIIlllllllIIIl = llllllllllllllllIllIIlllllllIIlI.method_1092(llllllllllllllllIllIIlllllllIllI, llllllllllllllllIllIIlllllllIlIl, llllllllllllllllIllIIllllllllIII);
            double llllllllllllllllIllIIlllllllIIII = llllllllllllllllIllIIlllllllIIll == null ? Double.MAX_VALUE : llllllllllllllllIllIIllllllIllII.method_17750().method_1025(llllllllllllllllIllIIlllllllIIll.method_17784());
            double llllllllllllllllIllIIllllllIllll = llllllllllllllllIllIIlllllllIIIl == null ? Double.MAX_VALUE : llllllllllllllllIllIIllllllIllII.method_17750().method_1025(llllllllllllllllIllIIlllllllIIIl.method_17784());
            return llllllllllllllllIllIIlllllllIIII <= llllllllllllllllIllIIllllllIllll ? llllllllllllllllIllIIlllllllIIll : llllllllllllllllIllIIlllllllIIIl;
        }, llllllllllllllllIllIlIIIIIIIlIll -> {
            class_243 llllllllllllllllIllIlIIIIIIIllII = llllllllllllllllIllIlIIIIIIIlIll.method_17750().method_1020(llllllllllllllllIllIlIIIIIIIlIll.method_17747());
            return class_3965.method_17778((class_243)llllllllllllllllIllIlIIIIIIIlIll.method_17747(), (class_2350)class_2350.method_10142((double)llllllllllllllllIllIlIIIIIIIllII.field_1352, (double)llllllllllllllllIllIlIIIIIIIllII.field_1351, (double)llllllllllllllllIllIlIIIIIIIllII.field_1350), (class_2338)new class_2338(llllllllllllllllIllIlIIIIIIIlIll.method_17747()));
        });
    }
}

