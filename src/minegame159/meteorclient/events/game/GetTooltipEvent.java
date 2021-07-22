/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.events.game;

import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_4587;

public class GetTooltipEvent {
    public /* synthetic */ class_1799 itemStack;
    public /* synthetic */ List<class_2561> list;

    public GetTooltipEvent() {
        GetTooltipEvent lllllllllllllllllIIIIllIlIlIllll;
    }

    public static class Append
    extends GetTooltipEvent {
        private static final /* synthetic */ Append INSTANCE;

        public Append() {
            Append lIllllIlIIIlIll;
        }

        static {
            INSTANCE = new Append();
        }

        public static Append get(class_1799 lIllllIlIIIlIII, List<class_2561> lIllllIlIIIIlIl) {
            Append.INSTANCE.itemStack = lIllllIlIIIlIII;
            Append.INSTANCE.list = lIllllIlIIIIlIl;
            return INSTANCE;
        }
    }

    public static class Modify
    extends GetTooltipEvent {
        public /* synthetic */ int x;
        public /* synthetic */ int y;
        public /* synthetic */ class_4587 matrixStack;
        private static final /* synthetic */ Modify INSTANCE;

        static {
            INSTANCE = new Modify();
        }

        public Modify() {
            Modify llllllllllllllllllIllIIllIllIIlI;
        }

        public static Modify get(class_1799 llllllllllllllllllIllIIllIlIllII, List<class_2561> llllllllllllllllllIllIIllIlIlIll, class_4587 llllllllllllllllllIllIIllIlIIlIl, int llllllllllllllllllIllIIllIlIIlII, int llllllllllllllllllIllIIllIlIlIII) {
            Modify.INSTANCE.itemStack = llllllllllllllllllIllIIllIlIllII;
            Modify.INSTANCE.list = llllllllllllllllllIllIIllIlIlIll;
            Modify.INSTANCE.matrixStack = llllllllllllllllllIllIIllIlIIlIl;
            Modify.INSTANCE.x = llllllllllllllllllIllIIllIlIIlII;
            Modify.INSTANCE.y = llllllllllllllllllIllIIllIlIlIII;
            return INSTANCE;
        }
    }
}

