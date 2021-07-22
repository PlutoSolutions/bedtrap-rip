/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.utils.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.Pool;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BlockIterator {
    private static final /* synthetic */ List<Callback> callbacks;
    private static /* synthetic */ boolean disableCurrent;
    private static final /* synthetic */ Pool<Callback> callbackPool;
    private static final /* synthetic */ List<Runnable> afterCallbacks;
    private static /* synthetic */ int vRadius;
    private static final /* synthetic */ class_2338.class_2339 blockPos;
    private static /* synthetic */ int hRadius;

    public static void disableCurrent() {
        disableCurrent = true;
    }

    public static void register(int llIIlllIllIIlII, int llIIlllIlIlllll, BiConsumer<class_2338, class_2680> llIIlllIlIllllI) {
        hRadius = Math.max(hRadius, llIIlllIllIIlII);
        vRadius = Math.max(vRadius, llIIlllIlIlllll);
        Callback llIIlllIllIIIIl = callbackPool.get();
        llIIlllIllIIIIl.function = llIIlllIlIllllI;
        llIIlllIllIIIIl.hRadius = llIIlllIllIIlII;
        llIIlllIllIIIIl.vRadius = llIIlllIlIlllll;
        callbacks.add(llIIlllIllIIIIl);
    }

    static {
        callbackPool = new Pool<Callback>(() -> new Callback());
        callbacks = new ArrayList<Callback>();
        afterCallbacks = new ArrayList<Runnable>();
        blockPos = new class_2338.class_2339();
    }

    @EventHandler(priority=-201)
    private static void onTick(TickEvent.Pre llIIlllIllllIII) {
        if (!Utils.canUpdate()) {
            return;
        }
        int llIIlllIlllIlll = (int)Utils.mc.field_1724.method_23317();
        int llIIlllIlllIllI = (int)Utils.mc.field_1724.method_23318();
        int llIIlllIlllIlIl = (int)Utils.mc.field_1724.method_23321();
        for (int llIIlllIllllIll = llIIlllIlllIlll - hRadius; llIIlllIllllIll <= llIIlllIlllIlll + hRadius; ++llIIlllIllllIll) {
            for (int llIIlllIlllllII = llIIlllIlllIlIl - hRadius; llIIlllIlllllII <= llIIlllIlllIlIl + hRadius; ++llIIlllIlllllII) {
                for (int llIIlllIlllllIl = Math.max(0, llIIlllIlllIllI - vRadius); llIIlllIlllllIl <= llIIlllIlllIllI + vRadius && llIIlllIlllllIl <= 255; ++llIIlllIlllllIl) {
                    blockPos.method_10103(llIIlllIllllIll, llIIlllIlllllIl, llIIlllIlllllII);
                    class_2680 llIIllllIIIIIIl = Utils.mc.field_1687.method_8320((class_2338)blockPos);
                    int llIIllllIIIIIII = Math.abs(llIIlllIllllIll - llIIlllIlllIlll);
                    int llIIlllIlllllll = Math.abs(llIIlllIlllllIl - llIIlllIlllIllI);
                    int llIIlllIllllllI = Math.abs(llIIlllIlllllII - llIIlllIlllIlIl);
                    Iterator<Callback> llIIllllIIIIIlI = callbacks.iterator();
                    while (llIIllllIIIIIlI.hasNext()) {
                        Callback llIIllllIIIIIll = llIIllllIIIIIlI.next();
                        if (llIIllllIIIIIII > llIIllllIIIIIll.hRadius || llIIlllIlllllll > llIIllllIIIIIll.vRadius || llIIlllIllllllI > llIIllllIIIIIll.hRadius) continue;
                        disableCurrent = false;
                        llIIllllIIIIIll.function.accept((class_2338)blockPos, llIIllllIIIIIIl);
                        if (!disableCurrent) continue;
                        llIIllllIIIIIlI.remove();
                    }
                }
            }
        }
        hRadius = 0;
        vRadius = 0;
        for (Callback llIIlllIllllIlI : callbacks) {
            callbackPool.free(llIIlllIllllIlI);
        }
        callbacks.clear();
        for (Runnable llIIlllIllllIIl : afterCallbacks) {
            llIIlllIllllIIl.run();
        }
        afterCallbacks.clear();
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(BlockIterator.class);
    }

    public static void after(Runnable llIIlllIlIllIll) {
        afterCallbacks.add(llIIlllIlIllIll);
    }

    public BlockIterator() {
        BlockIterator llIIllllIIlIIIl;
    }

    private static class Callback {
        public /* synthetic */ int hRadius;
        public /* synthetic */ BiConsumer<class_2338, class_2680> function;
        public /* synthetic */ int vRadius;

        private Callback() {
            Callback lllIllIlllIII;
        }
    }
}

