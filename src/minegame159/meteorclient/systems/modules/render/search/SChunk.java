/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2680
 *  net.minecraft.class_2791
 *  net.minecraft.class_2902$class_2903
 */
package minegame159.meteorclient.systems.modules.render.search;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.List;
import minegame159.meteorclient.systems.modules.render.search.SBlock;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2902;

public class SChunk {
    public /* synthetic */ Long2ObjectMap<SBlock> blocks;
    private static final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ int z;
    private final /* synthetic */ int x;

    public void remove(class_2338 lllllllllllllllllIIIlIIIlllIlllI) {
        SChunk lllllllllllllllllIIIlIIIlllIllII;
        SBlock lllllllllllllllllIIIlIIIlllIllIl = (SBlock)lllllllllllllllllIIIlIIIlllIllII.blocks.remove(SBlock.getKey(lllllllllllllllllIIIlIIIlllIlllI));
        if (lllllllllllllllllIIIlIIIlllIllIl != null) {
            lllllllllllllllllIIIlIIIlllIllIl.group.remove(lllllllllllllllllIIIlIIIlllIllIl);
        }
    }

    public void add(class_2338 lllllllllllllllllIIIlIIIllllllll, boolean lllllllllllllllllIIIlIIIlllllIlI) {
        SChunk lllllllllllllllllIIIlIIlIIIIIIII;
        SBlock lllllllllllllllllIIIlIIIllllllIl = new SBlock(lllllllllllllllllIIIlIIIllllllll.method_10263(), lllllllllllllllllIIIlIIIllllllll.method_10264(), lllllllllllllllllIIIlIIIllllllll.method_10260());
        if (lllllllllllllllllIIIlIIlIIIIIIII.blocks == null) {
            lllllllllllllllllIIIlIIlIIIIIIII.blocks = new Long2ObjectOpenHashMap(64);
        }
        lllllllllllllllllIIIlIIlIIIIIIII.blocks.put(SBlock.getKey(lllllllllllllllllIIIlIIIllllllll), (Object)lllllllllllllllllIIIlIIIllllllIl);
        if (lllllllllllllllllIIIlIIIlllllIlI) {
            lllllllllllllllllIIIlIIIllllllIl.update();
        }
    }

    public int size() {
        SChunk lllllllllllllllllIIIlIIIllIlIIIl;
        return lllllllllllllllllIIIlIIIllIlIIIl.blocks == null ? 0 : lllllllllllllllllIIIlIIIllIlIIIl.blocks.size();
    }

    public void render() {
        SChunk lllllllllllllllllIIIlIIIllIIIlII;
        for (SBlock lllllllllllllllllIIIlIIIllIIIllI : lllllllllllllllllIIIlIIIllIIIlII.blocks.values()) {
            lllllllllllllllllIIIlIIIllIIIllI.render();
        }
    }

    public boolean shouldBeDeleted() {
        SChunk lllllllllllllllllIIIlIIIllIIllIl;
        int lllllllllllllllllIIIlIIIllIIllII = Utils.getRenderDistance() + 1;
        return lllllllllllllllllIIIlIIIllIIllIl.x > Utils.mc.field_1724.field_6024 + lllllllllllllllllIIIlIIIllIIllII || lllllllllllllllllIIIlIIIllIIllIl.x < Utils.mc.field_1724.field_6024 - lllllllllllllllllIIIlIIIllIIllII || lllllllllllllllllIIIlIIIllIIllIl.z > Utils.mc.field_1724.field_5980 + lllllllllllllllllIIIlIIIllIIllII || lllllllllllllllllIIIlIIIllIIllIl.z < Utils.mc.field_1724.field_5980 - lllllllllllllllllIIIlIIIllIIllII;
    }

    public SBlock get(int lllllllllllllllllIIIlIIlIIIIlIll, int lllllllllllllllllIIIlIIlIIIIlIlI, int lllllllllllllllllIIIlIIlIIIIlIIl) {
        SChunk lllllllllllllllllIIIlIIlIIIIllII;
        return lllllllllllllllllIIIlIIlIIIIllII.blocks == null ? null : (SBlock)lllllllllllllllllIIIlIIlIIIIllII.blocks.get(SBlock.getKey(lllllllllllllllllIIIlIIlIIIIlIll, lllllllllllllllllIIIlIIlIIIIlIlI, lllllllllllllllllIIIlIIlIIIIlIIl));
    }

    public void update(int lllllllllllllllllIIIlIIIllIlIllI, int lllllllllllllllllIIIlIIIllIlIlIl, int lllllllllllllllllIIIlIIIllIlIlII) {
        SChunk lllllllllllllllllIIIlIIIllIlIlll;
        SBlock lllllllllllllllllIIIlIIIllIllIII = (SBlock)lllllllllllllllllIIIlIIIllIlIlll.blocks.get(SBlock.getKey(lllllllllllllllllIIIlIIIllIlIllI, lllllllllllllllllIIIlIIIllIlIlIl, lllllllllllllllllIIIlIIIllIlIlII));
        if (lllllllllllllllllIIIlIIIllIllIII != null) {
            lllllllllllllllllIIIlIIIllIllIII.update();
        }
    }

    public void add(class_2338 lllllllllllllllllIIIlIIIllllIlIl) {
        SChunk lllllllllllllllllIIIlIIIllllIllI;
        lllllllllllllllllIIIlIIIllllIllI.add(lllllllllllllllllIIIlIIIllllIlIl, true);
    }

    public SChunk(int lllllllllllllllllIIIlIIlIIIlIlIl, int lllllllllllllllllIIIlIIlIIIlIIIl) {
        SChunk lllllllllllllllllIIIlIIlIIIlIIll;
        lllllllllllllllllIIIlIIlIIIlIIll.x = lllllllllllllllllIIIlIIlIIIlIlIl;
        lllllllllllllllllIIIlIIlIIIlIIll.z = lllllllllllllllllIIIlIIlIIIlIIIl;
    }

    static {
        blockPos = new class_2338.class_2339();
    }

    public void update() {
        SChunk lllllllllllllllllIIIlIIIlllIIlII;
        for (SBlock lllllllllllllllllIIIlIIIlllIIllI : lllllllllllllllllIIIlIIIlllIIlII.blocks.values()) {
            lllllllllllllllllIIIlIIIlllIIllI.update();
        }
    }

    public static SChunk searchChunk(class_2791 lllllllllllllllllIIIlIIIlIllIIIl, List<class_2248> lllllllllllllllllIIIlIIIlIllIIII) {
        SChunk lllllllllllllllllIIIlIIIlIllIIlI = new SChunk(lllllllllllllllllIIIlIIIlIllIIIl.method_12004().field_9181, lllllllllllllllllIIIlIIIlIllIIIl.method_12004().field_9180);
        if (lllllllllllllllllIIIlIIIlIllIIlI.shouldBeDeleted()) {
            return lllllllllllllllllIIIlIIIlIllIIlI;
        }
        for (int lllllllllllllllllIIIlIIIlIllIlIl = lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8326(); lllllllllllllllllIIIlIIIlIllIlIl <= lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8327(); ++lllllllllllllllllIIIlIIIlIllIlIl) {
            for (int lllllllllllllllllIIIlIIIlIllIllI = lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8328(); lllllllllllllllllIIIlIIIlIllIllI <= lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8329(); ++lllllllllllllllllIIIlIIIlIllIllI) {
                int lllllllllllllllllIIIlIIIlIllIlll = lllllllllllllllllIIIlIIIlIllIIIl.method_12032(class_2902.class_2903.field_13202).method_12603(lllllllllllllllllIIIlIIIlIllIlIl - lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8326(), lllllllllllllllllIIIlIIIlIllIllI - lllllllllllllllllIIIlIIIlIllIIIl.method_12004().method_8328());
                for (int lllllllllllllllllIIIlIIIlIlllIII = 0; lllllllllllllllllIIIlIIIlIlllIII < lllllllllllllllllIIIlIIIlIllIlll; ++lllllllllllllllllIIIlIIIlIlllIII) {
                    blockPos.method_10103(lllllllllllllllllIIIlIIIlIllIlIl, lllllllllllllllllIIIlIIIlIlllIII, lllllllllllllllllIIIlIIIlIllIllI);
                    class_2680 lllllllllllllllllIIIlIIIlIlllIIl = lllllllllllllllllIIIlIIIlIllIIIl.method_8320((class_2338)blockPos);
                    if (!lllllllllllllllllIIIlIIIlIllIIII.contains((Object)lllllllllllllllllIIIlIIIlIlllIIl.method_26204())) continue;
                    lllllllllllllllllIIIlIIIlIllIIlI.add((class_2338)blockPos, false);
                }
            }
        }
        return lllllllllllllllllIIIlIIIlIllIIlI;
    }
}

