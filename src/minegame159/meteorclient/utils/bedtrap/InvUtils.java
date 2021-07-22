/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_310
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
    private static /* synthetic */ Long currentMove;
    public static final /* synthetic */ int OFFHAND_SLOT;
    private static final /* synthetic */ Deque<Long> moveQueue;
    private static final /* synthetic */ FindItemResult findItemResult;
    private static final /* synthetic */ class_310 mc;

    public InvUtils() {
        InvUtils llllllllllllllllIlllIIlllIlIIlll;
    }

    private static boolean actionContains(long llllllllllllllllIlllIIllIIlIlllI, int llllllllllllllllIlllIIllIIlIllIl) {
        return llllllllllllllllIlllIIllIIlIllIl == InvUtils.unpackLongTo(llllllllllllllllIlllIIllIIlIlllI) || llllllllllllllllIlllIIllIIlIllIl == InvUtils.unpackLongFrom(llllllllllllllllIlllIIllIIlIlllI);
    }

    public static class_1268 getHand(class_1792 llllllllllllllllIlllIIlllIIllIII) {
        class_1268 llllllllllllllllIlllIIlllIIllIIl = class_1268.field_5808;
        if (InvUtils.mc.field_1724.method_6079().method_7909() == llllllllllllllllIlllIIlllIIllIII) {
            llllllllllllllllIlllIIlllIIllIIl = class_1268.field_5810;
        }
        return llllllllllllllllIlllIIlllIIllIIl;
    }

    private static int unpackLongTo(long llllllllllllllllIlllIIllIIlIIlll) {
        return Utils.unpackLong2(llllllllllllllllIlllIIllIIlIIlll);
    }

    public static boolean canMove(Long llllllllllllllllIlllIIllIllIllII, Long llllllllllllllllIlllIIllIllIlIll) {
        return InvUtils.unpackLongPrio(llllllllllllllllIlllIIllIllIllII) < InvUtils.unpackLongPrio(llllllllllllllllIlllIIllIllIlIll);
    }

    public static int invIndexToSlotId(int llllllllllllllllIlllIIlllIIIIllI) {
        if (llllllllllllllllIlllIIlllIIIIllI < 9 && llllllllllllllllIlllIIlllIIIIllI != -1) {
            return 44 - (8 - llllllllllllllllIlllIIlllIIIIllI);
        }
        return llllllllllllllllIlllIIlllIIIIllI;
    }

    public static void addSlots(int llllllllllllllllIlllIIllIlllIlII, int llllllllllllllllIlllIIllIllllIIl, int llllllllllllllllIlllIIllIllllIII, int llllllllllllllllIlllIIllIlllIIIl) {
        Long llllllllllllllllIlllIIllIlllIllI = Utils.packLong(llllllllllllllllIlllIIllIlllIlII, llllllllllllllllIlllIIllIllllIIl, llllllllllllllllIlllIIllIllllIII, llllllllllllllllIlllIIllIlllIIIl);
        if (moveQueue.contains(llllllllllllllllIlllIIllIlllIllI)) {
            return;
        }
        moveQueue.removeIf(llllllllllllllllIlllIIllIIIllIlI -> (InvUtils.actionContains(llllllllllllllllIlllIIllIIIllIlI, InvUtils.unpackLongTo(llllllllllllllllIlllIIllIlllIllI)) || InvUtils.actionContains(llllllllllllllllIlllIIllIIIllIlI, InvUtils.unpackLongFrom(llllllllllllllllIlllIIllIlllIllI))) && InvUtils.canMove(llllllllllllllllIlllIIllIIIllIlI, llllllllllllllllIlllIIllIlllIllI));
        boolean llllllllllllllllIlllIIllIlllIlIl = moveQueue.isEmpty();
        if (moveQueue.isEmpty() || InvUtils.canMove(moveQueue.peek(), llllllllllllllllIlllIIllIlllIllI)) {
            moveQueue.addFirst(llllllllllllllllIlllIIllIlllIllI);
        } else {
            moveQueue.add(llllllllllllllllIlllIIllIlllIllI);
        }
        if (llllllllllllllllIlllIIllIlllIlIl) {
            InvUtils.onTick(new TickEvent.Pre());
        }
    }

    public static int findItemInMain(class_1792 llllllllllllllllIlllIIllIlIIlIIl, Predicate<class_1799> llllllllllllllllIlllIIllIlIIlIlI) {
        return InvUtils.findItem(llllllllllllllllIlllIIllIlIIlIIl, llllllllllllllllIlllIIllIlIIlIlI, InvUtils.mc.field_1724.field_7514.field_7547.size());
    }

    public static class_1268 getHand(Predicate<class_1799> llllllllllllllllIlllIIlllIIlIIlI) {
        class_1268 llllllllllllllllIlllIIlllIIlIIll = null;
        if (llllllllllllllllIlllIIlllIIlIIlI.test(InvUtils.mc.field_1724.method_6047())) {
            llllllllllllllllIlllIIlllIIlIIll = class_1268.field_5808;
        } else if (llllllllllllllllIlllIIlllIIlIIlI.test(InvUtils.mc.field_1724.method_6079())) {
            llllllllllllllllIlllIIlllIIlIIll = class_1268.field_5810;
        }
        return llllllllllllllllIlllIIlllIIlIIll;
    }

    public static FindItemResult findItemWithCount(class_1792 llllllllllllllllIlllIIlllIIIlIlI) {
        InvUtils.findItemResult.slot = -1;
        InvUtils.findItemResult.count = 0;
        for (int llllllllllllllllIlllIIlllIIIllII = 0; llllllllllllllllIlllIIlllIIIllII < InvUtils.mc.field_1724.field_7514.method_5439(); ++llllllllllllllllIlllIIlllIIIllII) {
            class_1799 llllllllllllllllIlllIIlllIIIllIl = InvUtils.mc.field_1724.field_7514.method_5438(llllllllllllllllIlllIIlllIIIllII);
            if (llllllllllllllllIlllIIlllIIIllIl.method_7909() != llllllllllllllllIlllIIlllIIIlIlI) continue;
            if (!findItemResult.found()) {
                InvUtils.findItemResult.slot = llllllllllllllllIlllIIlllIIIllII;
            }
            InvUtils.findItemResult.count += llllllllllllllllIlllIIlllIIIllIl.method_7947();
        }
        return findItemResult;
    }

    private static int findItem(class_1792 llllllllllllllllIlllIIllIIlllIlI, Predicate<class_1799> llllllllllllllllIlllIIllIIllllII, int llllllllllllllllIlllIIllIIlllIll) {
        for (int llllllllllllllllIlllIIllIIlllllI = 0; llllllllllllllllIlllIIllIIlllllI < llllllllllllllllIlllIIllIIlllIll; ++llllllllllllllllIlllIIllIIlllllI) {
            class_1799 llllllllllllllllIlllIIllIIllllll = InvUtils.mc.field_1724.field_7514.method_5438(llllllllllllllllIlllIIllIIlllllI);
            if (llllllllllllllllIlllIIllIIlllIlI != null && llllllllllllllllIlllIIllIIllllll.method_7909() != llllllllllllllllIlllIIllIIlllIlI || !llllllllllllllllIlllIIllIIllllII.test(llllllllllllllllIlllIIllIIllllll)) continue;
            return llllllllllllllllIlllIIllIIlllllI;
        }
        return -1;
    }

    public static void swap(int llllllllllllllllIlllIIllIIllIIll) {
        if (llllllllllllllllIlllIIllIIllIIll != InvUtils.mc.field_1724.field_7514.field_7545 && llllllllllllllllIlllIIllIIllIIll >= 0 && llllllllllllllllIlllIIllIIllIIll < 9) {
            InvUtils.mc.field_1724.field_7514.field_7545 = llllllllllllllllIlllIIllIIllIIll;
        }
    }

    public static int findItemInHotbar(class_1792 llllllllllllllllIlllIIllIlIllIlI) {
        return InvUtils.findItemInHotbar(llllllllllllllllIlllIIllIlIllIlI, llllllllllllllllIlllIIllIIIlllll -> true);
    }

    public static int findItemInMain(Predicate<class_1799> llllllllllllllllIlllIIllIlIIIlIl) {
        return InvUtils.findItem(null, llllllllllllllllIlllIIllIlIIIlIl, InvUtils.mc.field_1724.field_7514.field_7547.size());
    }

    public static int findItemInAll(class_1792 llllllllllllllllIlllIIllIllIIllI) {
        return InvUtils.findItemInHotbar(llllllllllllllllIlllIIllIllIIllI, llllllllllllllllIlllIIllIIIllllI -> true);
    }

    public static int findItemInHotbar(class_1792 llllllllllllllllIlllIIllIlIlIlll, Predicate<class_1799> llllllllllllllllIlllIIllIlIlIllI) {
        return InvUtils.findItem(llllllllllllllllIlllIIllIlIlIlll, llllllllllllllllIlllIIllIlIlIllI, 9);
    }

    public static int findItemInAll(Predicate<class_1799> llllllllllllllllIlllIIllIlIlllIl) {
        return InvUtils.findItem(null, llllllllllllllllIlllIIllIlIlllIl, InvUtils.mc.field_1724.field_7514.method_5439());
    }

    private static int unpackLongMode(long llllllllllllllllIlllIIllIIlIlIlI) {
        return Utils.unpackLong1(llllllllllllllllIlllIIllIIlIlIlI);
    }

    static {
        OFFHAND_SLOT = 45;
        mc = class_310.method_1551();
        findItemResult = new FindItemResult();
        moveQueue = new ArrayDeque<Long>();
    }

    public static int findItemInMain(class_1792 llllllllllllllllIlllIIllIlIIllll) {
        return InvUtils.findItemInHotbar(llllllllllllllllIlllIIllIlIIllll, llllllllllllllllIlllIIllIIlIIIII -> true);
    }

    @EventHandler(priority=-200)
    private static void onTick(TickEvent.Pre llllllllllllllllIlllIIlllIIIIIlI) {
        if (InvUtils.mc.field_1687 == null || InvUtils.mc.field_1724 == null || InvUtils.mc.field_1724.field_7503.field_7477) {
            moveQueue.clear();
            return;
        }
        if (!InvUtils.mc.field_1724.field_7514.method_7399().method_7960() && InvUtils.mc.field_1755 == null && InvUtils.mc.field_1724.field_7512.method_7602().size() == 46) {
            int llllllllllllllllIlllIIlllIIIIIll = InvUtils.findItemWithCount((class_1792)InvUtils.mc.field_1724.field_7514.method_7399().method_7909()).slot;
            if (llllllllllllllllIlllIIlllIIIIIll == -1) {
                llllllllllllllllIlllIIlllIIIIIll = InvUtils.mc.field_1724.field_7514.method_7376();
            }
            if (llllllllllllllllIlllIIlllIIIIIll != -1) {
                InvUtils.clickSlot(InvUtils.invIndexToSlotId(llllllllllllllllIlllIIlllIIIIIll), 0, class_1713.field_7790);
            }
        }
        if (!moveQueue.isEmpty() && InvUtils.mc.field_1724.field_7512.method_7602().size() == 46) {
            currentMove = moveQueue.remove();
            InvUtils.clickSlot(InvUtils.unpackLongFrom(currentMove), 0, class_1713.field_7790);
            InvUtils.clickSlot(InvUtils.unpackLongTo(currentMove), 0, class_1713.field_7790);
            InvUtils.clickSlot(InvUtils.unpackLongFrom(currentMove), 0, class_1713.field_7790);
        }
    }

    public static void clickSlot(int llllllllllllllllIlllIIlllIlIIIlI, int llllllllllllllllIlllIIlllIlIIIIl, class_1713 llllllllllllllllIlllIIlllIlIIIII) {
        InvUtils.mc.field_1761.method_2906(InvUtils.mc.field_1724.field_7512.field_7763, llllllllllllllllIlllIIlllIlIIIlI, llllllllllllllllIlllIIlllIlIIIIl, llllllllllllllllIlllIIlllIlIIIII, (class_1657)InvUtils.mc.field_1724);
    }

    private static int unpackLongPrio(long llllllllllllllllIlllIIllIIlIIIIl) {
        return Utils.unpackLong4(llllllllllllllllIlllIIllIIlIIIIl);
    }

    public static int findItemInHotbar(Predicate<class_1799> llllllllllllllllIlllIIllIlIlIIIl) {
        return InvUtils.findItem(null, llllllllllllllllIlllIIllIlIlIIIl, 9);
    }

    private static int unpackLongFrom(long llllllllllllllllIlllIIllIIlIIlIl) {
        return Utils.unpackLong3(llllllllllllllllIlllIIllIIlIIlIl);
    }

    public static int findItemInAll(class_1792 llllllllllllllllIlllIIllIllIIIll, Predicate<class_1799> llllllllllllllllIlllIIllIllIIIlI) {
        return InvUtils.findItem(llllllllllllllllIlllIIllIllIIIll, llllllllllllllllIlllIIllIllIIIlI, InvUtils.mc.field_1724.field_7514.method_5439());
    }

    public static class FindItemResult {
        public /* synthetic */ int slot;
        public /* synthetic */ int count;

        public FindItemResult() {
            FindItemResult lllllllllllllllllIIllIllIlIlIllI;
        }

        public boolean found() {
            FindItemResult lllllllllllllllllIIllIllIlIlIlII;
            return lllllllllllllllllIIllIllIlIlIlII.slot != -1;
        }
    }
}

