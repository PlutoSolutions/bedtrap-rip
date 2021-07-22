/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.world;

public class TickEvent {
    public TickEvent() {
        TickEvent lllllllllllllllllIlIllIlIlIIIIll;
    }

    public static class Post
    extends TickEvent {
        private static final /* synthetic */ Post INSTANCE;

        public Post() {
            Post llllllllllllllllllllllIlIIlIIlII;
        }

        static {
            INSTANCE = new Post();
        }

        public static Post get() {
            return INSTANCE;
        }
    }

    public static class Pre
    extends TickEvent {
        private static final /* synthetic */ Pre INSTANCE;

        public Pre() {
            Pre llIllIlIlIIllll;
        }

        static {
            INSTANCE = new Pre();
        }

        public static Pre get() {
            return INSTANCE;
        }
    }
}

