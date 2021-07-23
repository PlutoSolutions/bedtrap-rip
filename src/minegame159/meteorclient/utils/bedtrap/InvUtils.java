/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.bedtrap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.utils.bedtrap.Utils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_310;

public class InvUtils {
    private static Long currentMove;
    public static final int OFFHAND_SLOT;
    private static final Deque<Long> moveQueue;
    private static final FindItemResult findItemResult;
    private static final class_310 mc;

    private static boolean lambda$findItemInAll$1(class_1799 class_17992) {
        return true;
    }

    private static boolean lambda$findItemInMain$3(class_1799 class_17992) {
        return true;
    }

    private static boolean actionContains(long l, int n) {
        return n == InvUtils.unpackLongTo(l) || n == InvUtils.unpackLongFrom(l);
    }

    public static class_1268 getHand(class_1792 class_17922) {
        class_1268 class_12682 = class_1268.field_5808;
        if (InvUtils.mc.field_1724.method_6079().method_7909() == class_17922) {
            class_12682 = class_1268.field_5810;
        }
        return class_12682;
    }

    private static int unpackLongTo(long l) {
        return Utils.unpackLong2(l);
    }

    public static boolean canMove(Long l, Long l2) {
        return InvUtils.unpackLongPrio(l) < InvUtils.unpackLongPrio(l2);
    }

    public static int invIndexToSlotId(int n) {
        if (n < 9 && n != -1) {
            return 44 - (8 - n);
        }
        return n;
    }

    public static void addSlots(int n, int n2, int n3, int n4) {
        Long l = Utils.packLong(n, n2, n3, n4);
        if (moveQueue.contains(l)) {
            return;
        }
        moveQueue.removeIf(arg_0 -> InvUtils.lambda$addSlots$0(l, arg_0));
        boolean bl = moveQueue.isEmpty();
        if (moveQueue.isEmpty() || InvUtils.canMove(moveQueue.peek(), l)) {
            moveQueue.addFirst(l);
        } else {
            moveQueue.add(l);
        }
        if (bl) {
            InvUtils.onTick(new TickEvent.Pre());
        }
    }

    public static int findItemInMain(class_1792 class_17922, Predicate<class_1799> predicate) {
        return InvUtils.findItem(class_17922, predicate, InvUtils.mc.field_1724.field_7514.field_7547.size());
    }

    public static class_1268 getHand(Predicate<class_1799> predicate) {
        class_1268 class_12682 = null;
        if (predicate.test(InvUtils.mc.field_1724.method_6047())) {
            class_12682 = class_1268.field_5808;
        } else if (predicate.test(InvUtils.mc.field_1724.method_6079())) {
            class_12682 = class_1268.field_5810;
        }
        return class_12682;
    }

    public static FindItemResult findItemWithCount(class_1792 class_17922) {
        InvUtils.findItemResult.slot = -1;
        InvUtils.findItemResult.count = 0;
        for (int i = 0; i < InvUtils.mc.field_1724.field_7514.method_5439(); ++i) {
            class_1799 class_17992 = InvUtils.mc.field_1724.field_7514.method_5438(i);
            if (class_17992.method_7909() != class_17922) continue;
            if (!findItemResult.found()) {
                InvUtils.findItemResult.slot = i;
            }
            InvUtils.findItemResult.count += class_17992.method_7947();
            if (-2 <= 0) continue;
            return null;
        }
        return findItemResult;
    }

    private static int findItem(class_1792 class_17922, Predicate<class_1799> predicate, int n) {
        for (int i = 0; i < n; ++i) {
            class_1799 class_17992 = InvUtils.mc.field_1724.field_7514.method_5438(i);
            if (class_17922 != null && class_17992.method_7909() != class_17922 || !predicate.test(class_17992)) continue;
            return i;
        }
        return -1;
    }

    public static void swap(int n) {
        if (n != InvUtils.mc.field_1724.field_7514.field_7545 && n >= 0 && n < 9) {
            InvUtils.mc.field_1724.field_7514.field_7545 = n;
        }
    }

    public static int findItemInHotbar(class_1792 class_17922) {
        return InvUtils.findItemInHotbar(class_17922, InvUtils::lambda$findItemInHotbar$2);
    }

    public static int findItemInMain(Predicate<class_1799> predicate) {
        return InvUtils.findItem(null, predicate, InvUtils.mc.field_1724.field_7514.field_7547.size());
    }

    public static int findItemInAll(class_1792 class_17922) {
        return InvUtils.findItemInHotbar(class_17922, InvUtils::lambda$findItemInAll$1);
    }

    public static int findItemInHotbar(class_1792 class_17922, Predicate<class_1799> predicate) {
        return InvUtils.findItem(class_17922, predicate, 9);
    }

    public static int findItemInAll(Predicate<class_1799> predicate) {
        return InvUtils.findItem(null, predicate, InvUtils.mc.field_1724.field_7514.method_5439());
    }

    private static int unpackLongMode(long l) {
        return Utils.unpackLong1(l);
    }

    private static boolean lambda$findItemInHotbar$2(class_1799 class_17992) {
        return true;
    }

    static {
        OFFHAND_SLOT = 45;
        mc = class_310.method_1551();
        findItemResult = new FindItemResult();
        moveQueue = new ArrayDeque<Long>();
    }

    public static int findItemInMain(class_1792 class_17922) {
        return InvUtils.findItemInHotbar(class_17922, InvUtils::lambda$findItemInMain$3);
    }

    @EventHandler(priority=-200)
    private static void onTick(TickEvent.Pre pre) {
        if (InvUtils.mc.field_1687 == null || InvUtils.mc.field_1724 == null || InvUtils.mc.field_1724.field_7503.field_7477) {
            moveQueue.clear();
            return;
        }
        if (!InvUtils.mc.field_1724.field_7514.method_7399().method_7960() && InvUtils.mc.field_1755 == null && InvUtils.mc.field_1724.field_7512.method_7602().size() == 46) {
            int n = InvUtils.findItemWithCount((class_1792)InvUtils.mc.field_1724.field_7514.method_7399().method_7909()).slot;
            if (n == -1) {
                n = InvUtils.mc.field_1724.field_7514.method_7376();
            }
            if (n != -1) {
                InvUtils.clickSlot(InvUtils.invIndexToSlotId(n), 0, class_1713.field_7790);
            }
        }
        if (!moveQueue.isEmpty() && InvUtils.mc.field_1724.field_7512.method_7602().size() == 46) {
            currentMove = moveQueue.remove();
            InvUtils.clickSlot(InvUtils.unpackLongFrom(currentMove), 0, class_1713.field_7790);
            InvUtils.clickSlot(InvUtils.unpackLongTo(currentMove), 0, class_1713.field_7790);
            InvUtils.clickSlot(InvUtils.unpackLongFrom(currentMove), 0, class_1713.field_7790);
        }
    }

    public static void clickSlot(int n, int n2, class_1713 class_17132) {
        InvUtils.mc.field_1761.method_2906(InvUtils.mc.field_1724.field_7512.field_7763, n, n2, class_17132, (class_1657)InvUtils.mc.field_1724);
    }

    private static int unpackLongPrio(long l) {
        return Utils.unpackLong4(l);
    }

    public static int findItemInHotbar(Predicate<class_1799> predicate) {
        return InvUtils.findItem(null, predicate, 9);
    }

    private static boolean lambda$addSlots$0(Long l, Long l2) {
        return (InvUtils.actionContains(l2, InvUtils.unpackLongTo(l)) || InvUtils.actionContains(l2, InvUtils.unpackLongFrom(l))) && InvUtils.canMove(l2, l);
    }

    private static int unpackLongFrom(long l) {
        return Utils.unpackLong3(l);
    }

    public static int findItemInAll(class_1792 class_17922, Predicate<class_1799> predicate) {
        return InvUtils.findItem(class_17922, predicate, InvUtils.mc.field_1724.field_7514.method_5439());
    }

    public static class FindItemResult {
        public int slot;
        public int count;

        public boolean found() {
            return this.slot != -1;
        }
    }
}

