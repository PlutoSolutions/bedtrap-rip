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
    private static final Action ACTION = new Action(null);

    private static boolean lambda$find$1(class_1792[] arrclass_1792, class_1799 class_17992) {
        for (class_1792 class_17922 : arrclass_1792) {
            if (class_17992.method_7909() != class_17922) continue;
            return true;
        }
        return false;
    }

    public static Action drop() {
        Action.access$102(ACTION, class_1713.field_7795);
        Action.access$302(ACTION, 1);
        return ACTION;
    }

    public static Action move() {
        Action.access$102(ACTION, class_1713.field_7790);
        Action.access$202(ACTION, true);
        return ACTION;
    }

    public static FindItemResult findInHotbar(Predicate<class_1799> predicate) {
        if (predicate.test(Utils.mc.field_1724.method_6079())) {
            return new FindItemResult(45, Utils.mc.field_1724.method_6079().method_7947());
        }
        if (predicate.test(Utils.mc.field_1724.method_6047())) {
            return new FindItemResult(Utils.mc.field_1724.field_7514.field_7545, Utils.mc.field_1724.method_6047().method_7947());
        }
        return InvUtils.find(predicate, 0, 8);
    }

    public static FindItemResult findEmpty() {
        return InvUtils.find(class_1799::method_7960);
    }

    private static FindItemResult find(Predicate<class_1799> predicate, int n, int n2) {
        int n3 = -1;
        int n4 = 0;
        for (int i = n; i <= n2; ++i) {
            class_1799 class_17992 = Utils.mc.field_1724.field_7514.method_5438(i);
            if (!predicate.test(class_17992)) continue;
            if (n3 == -1) {
                n3 = i;
            }
            n4 += class_17992.method_7947();
            if (!false) continue;
            return null;
        }
        return new FindItemResult(n3, n4);
    }

    public static FindItemResult find(class_1792 ... arrclass_1792) {
        return InvUtils.find(arg_0 -> InvUtils.lambda$find$1(arrclass_1792, arg_0));
    }

    public static Action click() {
        Action.access$102(ACTION, class_1713.field_7790);
        return ACTION;
    }

    public static Action quickMove() {
        Action.access$102(ACTION, class_1713.field_7794);
        return ACTION;
    }

    private static boolean lambda$findInHotbar$0(class_1792[] arrclass_1792, class_1799 class_17992) {
        for (class_1792 class_17922 : arrclass_1792) {
            if (class_17992.method_7909() != class_17922) continue;
            return true;
        }
        return false;
    }

    public static FindItemResult findInHotbar(class_1792 ... arrclass_1792) {
        return InvUtils.findInHotbar(arg_0 -> InvUtils.lambda$findInHotbar$0(arrclass_1792, arg_0));
    }

    public static FindItemResult find(Predicate<class_1799> predicate) {
        return InvUtils.find(predicate, 0, Utils.mc.field_1724.field_7514.method_5439());
    }

    public static boolean swap(int n) {
        if (n < 0 || n > 8) {
            return false;
        }
        Utils.mc.field_1724.field_7514.field_7545 = n;
        ((IClientPlayerInteractionManager)Utils.mc.field_1761).syncSelectedSlot2();
        return true;
    }

    public static class Action {
        private int to = -1;
        private int from = -1;
        private boolean isRecursive = false;
        private int data = 0;
        private boolean two = false;
        private class_1713 type = null;

        Action(1 var1_1) {
            this();
        }

        public void slotOffhand() {
            this.slot(45);
        }

        public void to(int n) {
            this.toId(SlotUtils.indexToId(n));
        }

        public Action fromArmor(int n) {
            return this.from(36 + (3 - n));
        }

        static boolean access$202(Action action, boolean bl) {
            action.two = bl;
            return bl;
        }

        static class_1713 access$102(Action action, class_1713 class_17132) {
            action.type = class_17132;
            return class_17132;
        }

        public void toMain(int n) {
            this.to(9 + n);
        }

        public void toId(int n) {
            this.to = n;
            this.run();
        }

        public void slotArmor(int n) {
            this.slot(36 + (3 - n));
        }

        public void toOffhand() {
            this.to(45);
        }

        public void slot(int n) {
            this.slotId(SlotUtils.indexToId(n));
        }

        public Action fromId(int n) {
            this.from = n;
            return this;
        }

        public Action fromHotbar(int n) {
            return this.from(0 + n);
        }

        public Action fromMain(int n) {
            return this.from(9 + n);
        }

        public Action from(int n) {
            return this.fromId(SlotUtils.indexToId(n));
        }

        private void run() {
            boolean bl = Utils.mc.field_1724.field_7514.method_7399().method_7960();
            if (this.type != null && this.from != -1 && this.to != -1) {
                this.click(this.from);
                if (this.two) {
                    this.click(this.to);
                }
            }
            class_1713 class_17132 = this.type;
            boolean bl2 = this.two;
            int n = this.from;
            int n2 = this.to;
            this.type = null;
            this.two = false;
            this.from = -1;
            this.to = -1;
            this.data = 0;
            if (!this.isRecursive && bl && class_17132 == class_1713.field_7790 && bl2 && n != -1 && n2 != -1 && !Utils.mc.field_1724.field_7514.method_7399().method_7960()) {
                this.isRecursive = true;
                InvUtils.click().slotId(n);
                this.isRecursive = false;
            }
        }

        public void slotMain(int n) {
            this.slot(9 + n);
        }

        public void toArmor(int n) {
            this.to(36 + (3 - n));
        }

        public void toHotbar(int n) {
            this.to(0 + n);
        }

        public Action fromOffhand() {
            return this.from(45);
        }

        private void click(int n) {
            Utils.mc.field_1761.method_2906(Utils.mc.field_1724.field_7512.field_7763, n, this.data, this.type, (class_1657)Utils.mc.field_1724);
        }

        public void slotId(int n) {
            this.to = n;
            this.from = n;
            this.run();
        }

        static int access$302(Action action, int n) {
            action.data = n;
            return n;
        }

        public void slotHotbar(int n) {
            this.slot(0 + n);
        }

        private Action() {
        }
    }
}

