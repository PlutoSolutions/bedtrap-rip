/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1934
 */
package minegame159.meteorclient.utils.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1934;

public class TargetUtils {
    private static final /* synthetic */ List<class_1297> ENTITIES;

    private static int sortHealth(class_1297 llllllllllllllllIlIllllIIlIllIlI, class_1297 llllllllllllllllIlIllllIIlIlIlIl) {
        boolean llllllllllllllllIlIllllIIlIllIII = llllllllllllllllIlIllllIIlIllIlI instanceof class_1309;
        boolean llllllllllllllllIlIllllIIlIlIlll = llllllllllllllllIlIllllIIlIlIlIl instanceof class_1309;
        if (!llllllllllllllllIlIllllIIlIllIII && !llllllllllllllllIlIllllIIlIlIlll) {
            return 0;
        }
        if (llllllllllllllllIlIllllIIlIllIII && !llllllllllllllllIlIllllIIlIlIlll) {
            return 1;
        }
        if (!llllllllllllllllIlIllllIIlIllIII) {
            return -1;
        }
        return Float.compare(((class_1309)llllllllllllllllIlIllllIIlIllIlI).method_6032(), ((class_1309)llllllllllllllllIlIllllIIlIlIlIl).method_6032());
    }

    public static boolean isBadTarget(class_1657 llllllllllllllllIlIllllIIllIlIll, double llllllllllllllllIlIllllIIllIlIII) {
        if (llllllllllllllllIlIllllIIllIlIll == null) {
            return true;
        }
        return (double)Utils.mc.field_1724.method_5739((class_1297)llllllllllllllllIlIllllIIllIlIll) > llllllllllllllllIlIllllIIllIlIII || !llllllllllllllllIlIllllIIllIlIll.method_5805() || llllllllllllllllIlIllllIIllIlIll.method_29504() || llllllllllllllllIlIllllIIllIlIll.method_6032() <= 0.0f;
    }

    public TargetUtils() {
        TargetUtils llllllllllllllllIlIllllIlIIIllIl;
    }

    private static int sort(class_1297 llllllllllllllllIlIllllIIllIIIIl, class_1297 llllllllllllllllIlIllllIIllIIIII, SortPriority llllllllllllllllIlIllllIIllIIIlI) {
        switch (llllllllllllllllIlIllllIIllIIIlI) {
            case LowestDistance: {
                return Double.compare(llllllllllllllllIlIllllIIllIIIIl.method_5739((class_1297)Utils.mc.field_1724), llllllllllllllllIlIllllIIllIIIII.method_5739((class_1297)Utils.mc.field_1724));
            }
            case HighestDistance: {
                return TargetUtils.invertSort(Double.compare(llllllllllllllllIlIllllIIllIIIIl.method_5739((class_1297)Utils.mc.field_1724), llllllllllllllllIlIllllIIllIIIII.method_5739((class_1297)Utils.mc.field_1724)));
            }
            case LowestHealth: {
                return TargetUtils.sortHealth(llllllllllllllllIlIllllIIllIIIIl, llllllllllllllllIlIllllIIllIIIII);
            }
            case HighestHealth: {
                return TargetUtils.invertSort(TargetUtils.sortHealth(llllllllllllllllIlIllllIIllIIIIl, llllllllllllllllIlIllllIIllIIIII));
            }
            case ClosestAngle: {
                return TargetUtils.sortAngle(llllllllllllllllIlIllllIIllIIIIl, llllllllllllllllIlIllllIIllIIIII);
            }
        }
        return 0;
    }

    private static int invertSort(int llllllllllllllllIlIllllIIIlllIII) {
        if (llllllllllllllllIlIllllIIIlllIII == 0) {
            return 0;
        }
        return llllllllllllllllIlIllllIIIlllIII > 0 ? -1 : 1;
    }

    static {
        ENTITIES = new ArrayList<class_1297>();
    }

    public static void getList(List<class_1297> llllllllllllllllIlIllllIIllllIIl, Predicate<class_1297> llllllllllllllllIlIllllIIllllIII, SortPriority llllllllllllllllIlIllllIIllllIll, int llllllllllllllllIlIllllIIlllIllI) {
        llllllllllllllllIlIllllIIllllIIl.clear();
        for (class_1297 class_12972 : Utils.mc.field_1687.method_18112()) {
            if (!llllllllllllllllIlIllllIIllllIII.test(class_12972)) continue;
            llllllllllllllllIlIllllIIllllIIl.add(class_12972);
        }
        for (class_1297 class_12973 : FakePlayerManager.getPlayers()) {
            if (!llllllllllllllllIlIllllIIllllIII.test(class_12973)) continue;
            llllllllllllllllIlIllllIIllllIIl.add(class_12973);
        }
        llllllllllllllllIlIllllIIllllIIl.sort((llllllllllllllllIlIllllIIIlIIIIl, llllllllllllllllIlIllllIIIlIIIll) -> TargetUtils.sort(llllllllllllllllIlIllllIIIlIIIIl, llllllllllllllllIlIllllIIIlIIIll, llllllllllllllllIlIllllIIllllIll));
        llllllllllllllllIlIllllIIllllIIl.removeIf(llllllllllllllllIlIllllIIIlIllII -> llllllllllllllllIlIllllIIllllIIl.indexOf(llllllllllllllllIlIllllIIIlIllII) > llllllllllllllllIlIllllIIlllIllI - 1);
    }

    private static int sortAngle(class_1297 llllllllllllllllIlIllllIIlIIlIlI, class_1297 llllllllllllllllIlIllllIIlIIIIIl) {
        boolean llllllllllllllllIlIllllIIlIIlIII = llllllllllllllllIlIllllIIlIIlIlI instanceof class_1309;
        boolean llllllllllllllllIlIllllIIlIIIlll = llllllllllllllllIlIllllIIlIIIIIl instanceof class_1309;
        if (!llllllllllllllllIlIllllIIlIIlIII && !llllllllllllllllIlIllllIIlIIIlll) {
            return 0;
        }
        if (llllllllllllllllIlIllllIIlIIlIII && !llllllllllllllllIlIllllIIlIIIlll) {
            return 1;
        }
        if (!llllllllllllllllIlIllllIIlIIlIII) {
            return -1;
        }
        double llllllllllllllllIlIllllIIlIIIllI = Math.abs(Rotations.getYaw(llllllllllllllllIlIllllIIlIIlIlI) - (double)Utils.mc.field_1724.field_6031);
        double llllllllllllllllIlIllllIIlIIIlIl = Math.abs(Rotations.getYaw(llllllllllllllllIlIllllIIlIIIIIl) - (double)Utils.mc.field_1724.field_6031);
        double llllllllllllllllIlIllllIIlIIIlII = Math.abs(Rotations.getPitch(llllllllllllllllIlIllllIIlIIlIlI) - (double)Utils.mc.field_1724.field_5965);
        double llllllllllllllllIlIllllIIlIIIIll = Math.abs(Rotations.getPitch(llllllllllllllllIlIllllIIlIIIIIl) - (double)Utils.mc.field_1724.field_5965);
        return Double.compare(Math.sqrt(llllllllllllllllIlIllllIIlIIIllI * llllllllllllllllIlIllllIIlIIIllI + llllllllllllllllIlIllllIIlIIIlII * llllllllllllllllIlIllllIIlIIIlII), Math.sqrt(llllllllllllllllIlIllllIIlIIIlIl * llllllllllllllllIlIllllIIlIIIlIl + llllllllllllllllIlIllllIIlIIIIll * llllllllllllllllIlIllllIIlIIIIll));
    }

    public static class_1297 get(Predicate<class_1297> llllllllllllllllIlIllllIlIIIIlll, SortPriority llllllllllllllllIlIllllIlIIIIllI) {
        ENTITIES.clear();
        TargetUtils.getList(ENTITIES, llllllllllllllllIlIllllIlIIIIlll, llllllllllllllllIlIllllIlIIIIllI, 1);
        if (!ENTITIES.isEmpty()) {
            return ENTITIES.get(0);
        }
        return null;
    }

    public static class_1657 getPlayerTarget(double llllllllllllllllIlIllllIIllIllll, SortPriority llllllllllllllllIlIllllIIlllIIII) {
        if (!Utils.canUpdate()) {
            return null;
        }
        return (class_1657)TargetUtils.get(llllllllllllllllIlIllllIIIllIlII -> {
            if (!(llllllllllllllllIlIllllIIIllIlII instanceof class_1657) || llllllllllllllllIlIllllIIIllIlII == Utils.mc.field_1724) {
                return false;
            }
            if (((class_1657)llllllllllllllllIlIllllIIIllIlII).method_29504() || ((class_1657)llllllllllllllllIlIllllIIIllIlII).method_6032() <= 0.0f) {
                return false;
            }
            if ((double)Utils.mc.field_1724.method_5739(llllllllllllllllIlIllllIIIllIlII) > llllllllllllllllIlIllllIIllIllll) {
                return false;
            }
            if (!Friends.get().shouldAttack((class_1657)llllllllllllllllIlIllllIIIllIlII)) {
                return false;
            }
            return EntityUtils.getGameMode((class_1657)llllllllllllllllIlIllllIIIllIlII) == class_1934.field_9215 || llllllllllllllllIlIllllIIIllIlII instanceof FakePlayerEntity;
        }, llllllllllllllllIlIllllIIlllIIII);
    }
}

