/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.render;

import java.util.Iterator;
import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2561;
import net.minecraft.class_345;

public class RenderBossBarEvent
extends Cancellable {

    public static class BossText {
        public class_2561 name;
        public class_345 bossBar;
        private static final BossText INSTANCE = new BossText();

        public static BossText get(class_345 class_3452, class_2561 class_25612) {
            BossText.INSTANCE.bossBar = class_3452;
            BossText.INSTANCE.name = class_25612;
            return INSTANCE;
        }
    }

    public static class BossIterator {
        public Iterator<class_345> iterator;
        private static final BossIterator INSTANCE = new BossIterator();

        public static BossIterator get(Iterator<class_345> iterator) {
            BossIterator.INSTANCE.iterator = iterator;
            return INSTANCE;
        }
    }

    public static class BossSpacing {
        private static final BossSpacing INSTANCE = new BossSpacing();
        public int spacing;

        public static BossSpacing get(int n) {
            BossSpacing.INSTANCE.spacing = n;
            return INSTANCE;
        }
    }
}

