/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2818
 */
package minegame159.meteorclient.events.world;

import minegame159.meteorclient.utils.misc.Pool;
import net.minecraft.class_2818;

public class ChunkDataEvent {
    public /* synthetic */ class_2818 chunk;
    private static final /* synthetic */ Pool<ChunkDataEvent> INSTANCE;

    public static ChunkDataEvent get(class_2818 llIIIllllIIllI) {
        ChunkDataEvent llIIIllllIIlll = INSTANCE.get();
        llIIIllllIIlll.chunk = llIIIllllIIllI;
        return llIIIllllIIlll;
    }

    public ChunkDataEvent() {
        ChunkDataEvent llIIIllllIlIll;
    }

    static {
        INSTANCE = new Pool<ChunkDataEvent>(ChunkDataEvent::new);
    }

    public static void returnChunkDataEvent(ChunkDataEvent llIIIllllIIIll) {
        INSTANCE.free(llIIIllllIIIll);
    }
}

