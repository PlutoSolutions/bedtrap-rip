/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.utils.player;

import java.util.function.Predicate;
import minegame159.meteorclient.mixininterface.IClientPlayerInteractionManager;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.SlotUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

public class InvUtils {
    private static final /* synthetic */ Action ACTION;

    public static Action drop() {
        ACTION.type = class_1713.field_7795;
        ACTION.data = 1;
        return ACTION;
    }

    public static Action move() {
        ACTION.type = class_1713.field_7790;
        ACTION.two = true;
        return ACTION;
    }

    public static FindItemResult findInHotbar(Predicate<class_1799> lllllllllllllllllllIllllllIIllIl) {
        if (lllllllllllllllllllIllllllIIllIl.test(Utils.mc.field_1724.method_6079())) {
            return new FindItemResult(45, Utils.mc.field_1724.method_6079().method_7947());
        }
        if (lllllllllllllllllllIllllllIIllIl.test(Utils.mc.field_1724.method_6047())) {
            return new FindItemResult(Utils.mc.field_1724.field_7514.field_7545, Utils.mc.field_1724.method_6047().method_7947());
        }
        return InvUtils.find(lllllllllllllllllllIllllllIIllIl, 0, 8);
    }

    public static FindItemResult findEmpty() {
        return InvUtils.find(class_1799::method_7960);
    }

    private static FindItemResult find(Predicate<class_1799> lllllllllllllllllllIlllllIllIlll, int lllllllllllllllllllIlllllIlllIll, int lllllllllllllllllllIlllllIlllIlI) {
        int lllllllllllllllllllIlllllIlllIIl = -1;
        int lllllllllllllllllllIlllllIlllIII = 0;
        for (int lllllllllllllllllllIlllllIllllIl = lllllllllllllllllllIlllllIlllIll; lllllllllllllllllllIlllllIllllIl <= lllllllllllllllllllIlllllIlllIlI; ++lllllllllllllllllllIlllllIllllIl) {
            class_1799 lllllllllllllllllllIlllllIlllllI = Utils.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlllllIllllIl);
            if (!lllllllllllllllllllIlllllIllIlll.test(lllllllllllllllllllIlllllIlllllI)) continue;
            if (lllllllllllllllllllIlllllIlllIIl == -1) {
                lllllllllllllllllllIlllllIlllIIl = lllllllllllllllllllIlllllIllllIl;
            }
            lllllllllllllllllllIlllllIlllIII += lllllllllllllllllllIlllllIlllllI.method_7947();
        }
        return new FindItemResult(lllllllllllllllllllIlllllIlllIIl, lllllllllllllllllllIlllllIlllIII);
    }

    public static FindItemResult find(class_1792 ... lllllllllllllllllllIllllllIIlIIl) {
        return InvUtils.find((class_1799 lllllllllllllllllllIlllllIlIIIll) -> {
            for (class_1792 lllllllllllllllllllIlllllIlIIlll : lllllllllllllllllllIllllllIIlIIl) {
                if (lllllllllllllllllllIlllllIlIIIll.method_7909() != lllllllllllllllllllIlllllIlIIlll) continue;
                return true;
            }
            return false;
        });
    }

    public static Action click() {
        ACTION.type = class_1713.field_7790;
        return ACTION;
    }

    public static Action quickMove() {
        ACTION.type = class_1713.field_7794;
        return ACTION;
    }

    static {
        ACTION = new Action();
    }

    public static FindItemResult findInHotbar(class_1792 ... lllllllllllllllllllIllllllIIllll) {
        return InvUtils.findInHotbar((class_1799 lllllllllllllllllllIlllllIIlIlII) -> {
            for (class_1792 lllllllllllllllllllIlllllIIllIII : lllllllllllllllllllIllllllIIllll) {
                if (lllllllllllllllllllIlllllIIlIlII.method_7909() != lllllllllllllllllllIlllllIIllIII) continue;
                return true;
            }
            return false;
        });
    }

    public InvUtils() {
        InvUtils lllllllllllllllllllIllllllIlIIlI;
    }

    public static FindItemResult find(Predicate<class_1799> lllllllllllllllllllIllllllIIIllI) {
        return InvUtils.find(lllllllllllllllllllIllllllIIIllI, 0, Utils.mc.field_1724.field_7514.method_5439());
    }

    public static boolean swap(int lllllllllllllllllllIlllllIlIllll) {
        if (lllllllllllllllllllIlllllIlIllll < 0 || lllllllllllllllllllIlllllIlIllll > 8) {
            return false;
        }
        Utils.mc.field_1724.field_7514.field_7545 = lllllllllllllllllllIlllllIlIllll;
        ((IClientPlayerInteractionManager)Utils.mc.field_1761).syncSelectedSlot2();
        return true;
    }

    public static class Action {
        private /* synthetic */ int to;
        private /* synthetic */ int from;
        private /* synthetic */ boolean isRecursive;
        private /* synthetic */ int data;
        private /* synthetic */ boolean two;
        private /* synthetic */ class_1713 type;

        public void slotOffhand() {
            Action lllIIIIllIllIIl;
            lllIIIIllIllIIl.slot(45);
        }

        public void to(int lllIIIlIIIIIlII) {
            Action lllIIIlIIIIIlIl;
            lllIIIlIIIIIlIl.toId(SlotUtils.indexToId(lllIIIlIIIIIlII));
        }

        public Action fromArmor(int lllIIIlIIIlIIII) {
            Action lllIIIlIIIlIIIl;
            return lllIIIlIIIlIIIl.from(36 + (3 - lllIIIlIIIlIIII));
        }

        public void toMain(int lllIIIIllllIIll) {
            Action lllIIIIllllIllI;
            lllIIIIllllIllI.to(9 + lllIIIIllllIIll);
        }

        public void toId(int lllIIIlIIIIlIlI) {
            Action lllIIIlIIIIlIIl;
            lllIIIlIIIIlIIl.to = lllIIIlIIIIlIlI;
            lllIIIlIIIIlIIl.run();
        }

        public void slotArmor(int lllIIIIllIIllII) {
            Action lllIIIIllIIllll;
            lllIIIIllIIllll.slot(36 + (3 - lllIIIIllIIllII));
        }

        public void toOffhand() {
            Action lllIIIIlllllIlI;
            lllIIIIlllllIlI.to(45);
        }

        public void slot(int lllIIIIlllIIIIl) {
            Action lllIIIIlllIIlII;
            lllIIIIlllIIlII.slotId(SlotUtils.indexToId(lllIIIIlllIIIIl));
        }

        public Action fromId(int lllIIIlIIlIlIIl) {
            Action lllIIIlIIlIllII;
            lllIIIlIIlIllII.from = lllIIIlIIlIlIIl;
            return lllIIIlIIlIllII;
        }

        public Action fromHotbar(int lllIIIlIIIlllll) {
            Action lllIIIlIIIllllI;
            return lllIIIlIIIllllI.from(0 + lllIIIlIIIlllll);
        }

        public Action fromMain(int lllIIIlIIIlIlII) {
            Action lllIIIlIIIlIlll;
            return lllIIIlIIIlIlll.from(9 + lllIIIlIIIlIlII);
        }

        public Action from(int lllIIIlIIlIIlIl) {
            Action lllIIIlIIlIIlII;
            return lllIIIlIIlIIlII.fromId(SlotUtils.indexToId(lllIIIlIIlIIlIl));
        }

        private void run() {
            Action lllIIIIllIIIlIl;
            boolean lllIIIIllIIIlII = Utils.mc.field_1724.field_7514.method_7399().method_7960();
            if (lllIIIIllIIIlIl.type != null && lllIIIIllIIIlIl.from != -1 && lllIIIIllIIIlIl.to != -1) {
                lllIIIIllIIIlIl.click(lllIIIIllIIIlIl.from);
                if (lllIIIIllIIIlIl.two) {
                    lllIIIIllIIIlIl.click(lllIIIIllIIIlIl.to);
                }
            }
            class_1713 lllIIIIllIIIIll = lllIIIIllIIIlIl.type;
            boolean lllIIIIllIIIIlI = lllIIIIllIIIlIl.two;
            int lllIIIIllIIIIIl = lllIIIIllIIIlIl.from;
            int lllIIIIllIIIIII = lllIIIIllIIIlIl.to;
            lllIIIIllIIIlIl.type = null;
            lllIIIIllIIIlIl.two = false;
            lllIIIIllIIIlIl.from = -1;
            lllIIIIllIIIlIl.to = -1;
            lllIIIIllIIIlIl.data = 0;
            if (!lllIIIIllIIIlIl.isRecursive && lllIIIIllIIIlII && lllIIIIllIIIIll == class_1713.field_7790 && lllIIIIllIIIIlI && lllIIIIllIIIIIl != -1 && lllIIIIllIIIIII != -1 && !Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
                lllIIIIllIIIlIl.isRecursive = true;
                InvUtils.click().slotId(lllIIIIllIIIIIl);
                lllIIIIllIIIlIl.isRecursive = false;
            }
        }

        public void slotMain(int lllIIIIllIlIlII) {
            Action lllIIIIllIlIIll;
            lllIIIIllIlIIll.slot(9 + lllIIIIllIlIlII);
        }

        public void toArmor(int lllIIIIlllIllIl) {
            Action lllIIIIlllIlllI;
            lllIIIIlllIlllI.to(36 + (3 - lllIIIIlllIllIl));
        }

        public void toHotbar(int lllIIIIllllllII) {
            Action lllIIIIllllllll;
            lllIIIIllllllll.to(0 + lllIIIIllllllII);
        }

        public Action fromOffhand() {
            Action lllIIIlIIIllIll;
            return lllIIIlIIIllIll.from(45);
        }

        private void click(int lllIIIIlIllIllI) {
            Action lllIIIIlIllIlIl;
            Utils.mc.field_1761.method_2906(Utils.mc.field_1724.field_7512.field_7763, lllIIIIlIllIllI, lllIIIIlIllIlIl.data, lllIIIIlIllIlIl.type, (class_1657)Utils.mc.field_1724);
        }

        public void slotId(int lllIIIIlllIlIIl) {
            Action lllIIIIlllIlIII;
            lllIIIIlllIlIII.from = lllIIIIlllIlIII.to = lllIIIIlllIlIIl;
            lllIIIIlllIlIII.run();
        }

        public void slotHotbar(int lllIIIIllIlllIl) {
            Action lllIIIIllIlllII;
            lllIIIIllIlllII.slot(0 + lllIIIIllIlllIl);
        }

        private Action() {
            Action lllIIIlIIllIIII;
            lllIIIlIIllIIII.type = null;
            lllIIIlIIllIIII.two = false;
            lllIIIlIIllIIII.from = -1;
            lllIIIlIIllIIII.to = -1;
            lllIIIlIIllIIII.data = 0;
            lllIIIlIIllIIII.isRecursive = false;
        }
    }
}

