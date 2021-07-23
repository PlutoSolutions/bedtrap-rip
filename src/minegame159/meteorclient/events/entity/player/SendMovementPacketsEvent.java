/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

public class SendMovementPacketsEvent {

    public static class Post {
        private static final Post INSTANCE = new Post();

        public static Post get() {
            return INSTANCE;
        }
    }

    public static class Pre {
        private static final Pre INSTANCE = new Pre();

        public static Pre get() {
            return INSTANCE;
        }
    }
}

