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
    public class_2818 chunk;
    private static final Pool<ChunkDataEvent> INSTANCE = new Pool<ChunkDataEvent>(ChunkDataEvent::new);

    public static ChunkDataEvent get(class_2818 class_28182) {
        ChunkDataEvent chunkDataEvent = INSTANCE.get();
        chunkDataEvent.chunk = class_28182;
        return chunkDataEvent;
    }

    public static void returnChunkDataEvent(ChunkDataEvent chunkDataEvent) {
        INSTANCE.free(chunkDataEvent);
    }
}

