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
    private static final List<class_1297> ENTITIES = new ArrayList<class_1297>();

    private static int sortHealth(class_1297 class_12972, class_1297 class_12973) {
        boolean bl = class_12972 instanceof class_1309;
        boolean bl2 = class_12973 instanceof class_1309;
        if (!bl && !bl2) {
            return 0;
        }
        if (bl && !bl2) {
            return 1;
        }
        if (!bl) {
            return -1;
        }
        return Float.compare(((class_1309)class_12972).method_6032(), ((class_1309)class_12973).method_6032());
    }

    public static boolean isBadTarget(class_1657 class_16572, double d) {
        if (class_16572 == null) {
            return true;
        }
        return (double)Utils.mc.field_1724.method_5739((class_1297)class_16572) > d || !class_16572.method_5805() || class_16572.method_29504() || class_16572.method_6032() <= 0.0f;
    }

    private static int lambda$getList$0(SortPriority sortPriority, class_1297 class_12972, class_1297 class_12973) {
        return TargetUtils.sort(class_12972, class_12973, sortPriority);
    }

    private static int sort(class_1297 class_12972, class_1297 class_12973, SortPriority sortPriority) {
        switch (1.$SwitchMap$minegame159$meteorclient$utils$entity$SortPriority[sortPriority.ordinal()]) {
            case 1: {
                return Double.compare(class_12972.method_5739((class_1297)Utils.mc.field_1724), class_12973.method_5739((class_1297)Utils.mc.field_1724));
            }
            case 2: {
                return TargetUtils.invertSort(Double.compare(class_12972.method_5739((class_1297)Utils.mc.field_1724), class_12973.method_5739((class_1297)Utils.mc.field_1724)));
            }
            case 3: {
                return TargetUtils.sortHealth(class_12972, class_12973);
            }
            case 4: {
                return TargetUtils.invertSort(TargetUtils.sortHealth(class_12972, class_12973));
            }
            case 5: {
                return TargetUtils.sortAngle(class_12972, class_12973);
            }
        }
        return 0;
    }

    private static int invertSort(int n) {
        if (n == 0) {
            return 0;
        }
        return n > 0 ? -1 : 1;
    }

    private static boolean lambda$getList$1(List list, int n, class_1297 class_12972) {
        return list.indexOf((Object)class_12972) > n - 1;
    }

    private static boolean lambda$getPlayerTarget$2(double d, class_1297 class_12972) {
        if (!(class_12972 instanceof class_1657) || class_12972 == Utils.mc.field_1724) {
            return false;
        }
        if (((class_1657)class_12972).method_29504() || ((class_1657)class_12972).method_6032() <= 0.0f) {
            return false;
        }
        if ((double)Utils.mc.field_1724.method_5739(class_12972) > d) {
            return false;
        }
        if (!Friends.get().shouldAttack((class_1657)class_12972)) {
            return false;
        }
        return EntityUtils.getGameMode((class_1657)class_12972) == class_1934.field_9215 || class_12972 instanceof FakePlayerEntity;
    }

    public static void getList(List<class_1297> list, Predicate<class_1297> predicate, SortPriority sortPriority, int n) {
        list.clear();
        for (class_1297 class_12972 : Utils.mc.field_1687.method_18112()) {
            if (!predicate.test(class_12972)) continue;
            list.add(class_12972);
        }
        for (class_1297 class_12972 : FakePlayerManager.getPlayers()) {
            if (!predicate.test(class_12972)) continue;
            list.add(class_12972);
        }
        list.sort((arg_0, arg_1) -> TargetUtils.lambda$getList$0(sortPriority, arg_0, arg_1));
        list.removeIf(arg_0 -> TargetUtils.lambda$getList$1(list, n, arg_0));
    }

    private static int sortAngle(class_1297 class_12972, class_1297 class_12973) {
        boolean bl = class_12972 instanceof class_1309;
        boolean bl2 = class_12973 instanceof class_1309;
        if (!bl && !bl2) {
            return 0;
        }
        if (bl && !bl2) {
            return 1;
        }
        if (!bl) {
            return -1;
        }
        double d = Math.abs(Rotations.getYaw(class_12972) - (double)Utils.mc.field_1724.field_6031);
        double d2 = Math.abs(Rotations.getYaw(class_12973) - (double)Utils.mc.field_1724.field_6031);
        double d3 = Math.abs(Rotations.getPitch(class_12972) - (double)Utils.mc.field_1724.field_5965);
        double d4 = Math.abs(Rotations.getPitch(class_12973) - (double)Utils.mc.field_1724.field_5965);
        return Double.compare(Math.sqrt(d * d + d3 * d3), Math.sqrt(d2 * d2 + d4 * d4));
    }

    public static class_1297 get(Predicate<class_1297> predicate, SortPriority sortPriority) {
        ENTITIES.clear();
        TargetUtils.getList(ENTITIES, predicate, sortPriority, 1);
        if (!ENTITIES.isEmpty()) {
            return ENTITIES.get(0);
        }
        return null;
    }

    public static class_1657 getPlayerTarget(double d, SortPriority sortPriority) {
        if (!Utils.canUpdate()) {
            return null;
        }
        return (class_1657)TargetUtils.get(arg_0 -> TargetUtils.lambda$getPlayerTarget$2(d, arg_0), sortPriority);
    }
}

