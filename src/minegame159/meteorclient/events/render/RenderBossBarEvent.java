/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_345
 */
package minegame159.meteorclient.events.render;

import java.util.Iterator;
import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2561;
import net.minecraft.class_345;

public class RenderBossBarEvent
extends Cancellable {
    public RenderBossBarEvent() {
        RenderBossBarEvent lllllllllllllIl;
    }

    public static class BossText {
        public /* synthetic */ class_2561 name;
        public /* synthetic */ class_345 bossBar;
        private static final /* synthetic */ BossText INSTANCE;

        static {
            INSTANCE = new BossText();
        }

        public BossText() {
            BossText lllllllllllllllllIllIlIIlIlIIIlI;
        }

        public static BossText get(class_345 lllllllllllllllllIllIlIIlIIlllIl, class_2561 lllllllllllllllllIllIlIIlIIllllI) {
            BossText.INSTANCE.bossBar = lllllllllllllllllIllIlIIlIIlllIl;
            BossText.INSTANCE.name = lllllllllllllllllIllIlIIlIIllllI;
            return INSTANCE;
        }
    }

    public static class BossIterator {
        public /* synthetic */ Iterator<class_345> iterator;
        private static final /* synthetic */ BossIterator INSTANCE;

        public static BossIterator get(Iterator<class_345> llllllllllllllllllIlllIIIllIIIII) {
            BossIterator.INSTANCE.iterator = llllllllllllllllllIlllIIIllIIIII;
            return INSTANCE;
        }

        static {
            INSTANCE = new BossIterator();
        }

        public BossIterator() {
            BossIterator llllllllllllllllllIlllIIIllIIIlI;
        }
    }

    public static class BossSpacing {
        private static final /* synthetic */ BossSpacing INSTANCE;
        public /* synthetic */ int spacing;

        public static BossSpacing get(int llllllllllllllllIllIIlIIlllllIll) {
            BossSpacing.INSTANCE.spacing = llllllllllllllllIllIIlIIlllllIll;
            return INSTANCE;
        }

        static {
            INSTANCE = new BossSpacing();
        }

        public BossSpacing() {
            BossSpacing llllllllllllllllIllIIlIIlllllllI;
        }
    }
}

