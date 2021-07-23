/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.game;

import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_4587;

public class GetTooltipEvent {
    public class_1799 itemStack;
    public List<class_2561> list;

    public static class Append
    extends GetTooltipEvent {
        private static final Append INSTANCE = new Append();

        public static Append get(class_1799 class_17992, List<class_2561> list) {
            Append.INSTANCE.itemStack = class_17992;
            Append.INSTANCE.list = list;
            return INSTANCE;
        }
    }

    public static class Modify
    extends GetTooltipEvent {
        public int x;
        public int y;
        public class_4587 matrixStack;
        private static final Modify INSTANCE = new Modify();

        public static Modify get(class_1799 class_17992, List<class_2561> list, class_4587 class_45872, int n, int n2) {
            Modify.INSTANCE.itemStack = class_17992;
            Modify.INSTANCE.list = list;
            Modify.INSTANCE.matrixStack = class_45872;
            Modify.INSTANCE.x = n;
            Modify.INSTANCE.y = n2;
            return INSTANCE;
        }
    }
}

