/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.world;

public class TickEvent {

    public static class Post
    extends TickEvent {
        private static final Post INSTANCE = new Post();

        public static Post get() {
            return INSTANCE;
        }
    }

    public static class Pre
    extends TickEvent {
        private static final Pre INSTANCE = new Pre();

        public static Pre get() {
            return INSTANCE;
        }
    }
}

