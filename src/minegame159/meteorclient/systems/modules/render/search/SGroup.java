/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  net.minecraft.class_2248
 */
package minegame159.meteorclient.systems.modules.render.search;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.search.SBlock;
import minegame159.meteorclient.systems.modules.render.search.SBlockData;
import minegame159.meteorclient.systems.modules.render.search.Search;
import minegame159.meteorclient.utils.misc.UnorderedArrayList;
import minegame159.meteorclient.utils.render.RenderUtils;
import net.minecraft.class_2248;

public class SGroup {
    public final /* synthetic */ List<SBlock> blocks;
    private /* synthetic */ double sumZ;
    private final /* synthetic */ class_2248 block;
    private /* synthetic */ double sumX;
    private /* synthetic */ double sumY;
    private static final /* synthetic */ Search search;

    private void trySplit(SBlock lllllllllllllllllllllIIIlllIIlII) {
        SGroup lllllllllllllllllllllIIIlllIIIII;
        ObjectOpenHashSet lllllllllllllllllllllIIIlllIIIll = new ObjectOpenHashSet(6);
        for (int lllllllllllllllllllllIIIlllIllll : SBlock.SIDES) {
            SBlock lllllllllllllllllllllIIIllllIIII;
            if ((lllllllllllllllllllllIIIlllIIlII.neighbours & lllllllllllllllllllllIIIlllIllll) != lllllllllllllllllllllIIIlllIllll || (lllllllllllllllllllllIIIllllIIII = lllllllllllllllllllllIIIlllIIlII.getSideBlock(lllllllllllllllllllllIIIlllIllll)) == null) continue;
            lllllllllllllllllllllIIIlllIIIll.add(lllllllllllllllllllllIIIllllIIII);
        }
        if (lllllllllllllllllllllIIIlllIIIll.size() <= 1) {
            return;
        }
        ObjectOpenHashSet lllllllllllllllllllllIIIlllIIIlI = new ObjectOpenHashSet(lllllllllllllllllllllIIIlllIIIII.blocks);
        ArrayDeque<SBlock> lllllllllllllllllllllIIIlllIIIIl = new ArrayDeque<SBlock>();
        lllllllllllllllllllllIIIlllIIIIl.offer(lllllllllllllllllllllIIIlllIIIII.blocks.get(0));
        lllllllllllllllllllllIIIlllIIIlI.remove(lllllllllllllllllllllIIIlllIIIII.blocks.get(0));
        lllllllllllllllllllllIIIlllIIIll.remove(lllllllllllllllllllllIIIlllIIIII.blocks.get(0));
        block1: while (!lllllllllllllllllllllIIIlllIIIIl.isEmpty()) {
            SBlock lllllllllllllllllllllIIIlllIllII = (SBlock)lllllllllllllllllllllIIIlllIIIIl.poll();
            for (int lllllllllllllllllllllIIIlllIllIl : SBlock.SIDES) {
                SBlock lllllllllllllllllllllIIIlllIlllI;
                if ((lllllllllllllllllllllIIIlllIllII.neighbours & lllllllllllllllllllllIIIlllIllIl) != lllllllllllllllllllllIIIlllIllIl || (lllllllllllllllllllllIIIlllIlllI = lllllllllllllllllllllIIIlllIllII.getSideBlock(lllllllllllllllllllllIIIlllIllIl)) == null || !lllllllllllllllllllllIIIlllIIIlI.contains(lllllllllllllllllllllIIIlllIlllI)) continue;
                lllllllllllllllllllllIIIlllIIIIl.offer(lllllllllllllllllllllIIIlllIlllI);
                lllllllllllllllllllllIIIlllIIIlI.remove(lllllllllllllllllllllIIIlllIlllI);
                lllllllllllllllllllllIIIlllIIIll.remove(lllllllllllllllllllllIIIlllIlllI);
                if (lllllllllllllllllllllIIIlllIIIll.isEmpty()) break block1;
            }
        }
        if (lllllllllllllllllllllIIIlllIIIll.size() > 0) {
            SGroup lllllllllllllllllllllIIIlllIIllI = search.newGroup(lllllllllllllllllllllIIIlllIIIII.block);
            ((UnorderedArrayList)lllllllllllllllllllllIIIlllIIllI.blocks).ensureCapacity(lllllllllllllllllllllIIIlllIIIlI.size());
            lllllllllllllllllllllIIIlllIIIII.blocks.removeIf(((Set)lllllllllllllllllllllIIIlllIIIlI)::contains);
            for (SBlock lllllllllllllllllllllIIIlllIlIll : lllllllllllllllllllllIIIlllIIIlI) {
                lllllllllllllllllllllIIIlllIIllI.add(lllllllllllllllllllllIIIlllIlIll, false, false);
                lllllllllllllllllllllIIIlllIIIII.sumX -= (double)lllllllllllllllllllllIIIlllIlIll.x;
                lllllllllllllllllllllIIIlllIIIII.sumY -= (double)lllllllllllllllllllllIIIlllIlIll.y;
                lllllllllllllllllllllIIIlllIIIII.sumZ -= (double)lllllllllllllllllllllIIIlllIlIll.z;
            }
            if (lllllllllllllllllllllIIIlllIIIll.size() > 1) {
                lllllllllllllllllllllIIIlllIIlII.neighbours = 0;
                for (SBlock lllllllllllllllllllllIIIlllIIlll : lllllllllllllllllllllIIIlllIIIll) {
                    int lllllllllllllllllllllIIIlllIlIlI = lllllllllllllllllllllIIIlllIIlll.x - lllllllllllllllllllllIIIlllIIlII.x;
                    if (lllllllllllllllllllllIIIlllIlIlI == 1) {
                        lllllllllllllllllllllIIIlllIIlII.neighbours |= 8;
                    } else if (lllllllllllllllllllllIIIlllIlIlI == -1) {
                        lllllllllllllllllllllIIIlllIIlII.neighbours |= 0x80;
                    }
                    int lllllllllllllllllllllIIIlllIlIIl = lllllllllllllllllllllIIIlllIIlll.y - lllllllllllllllllllllIIIlllIIlII.y;
                    if (lllllllllllllllllllllIIIlllIlIIl == 1) {
                        lllllllllllllllllllllIIIlllIIlII.neighbours |= 0x200;
                    } else if (lllllllllllllllllllllIIIlllIlIIl == -1) {
                        lllllllllllllllllllllIIIlllIIlII.neighbours |= 0x4000;
                    }
                    int lllllllllllllllllllllIIIlllIlIII = lllllllllllllllllllllIIIlllIIlll.z - lllllllllllllllllllllIIIlllIIlII.z;
                    if (lllllllllllllllllllllIIIlllIlIII == 1) {
                        lllllllllllllllllllllIIIlllIIlII.neighbours |= 2;
                        continue;
                    }
                    if (lllllllllllllllllllllIIIlllIlIII != -1) continue;
                    lllllllllllllllllllllIIIlllIIlII.neighbours |= 0x20;
                }
                lllllllllllllllllllllIIIlllIIllI.trySplit(lllllllllllllllllllllIIIlllIIlII);
            }
        }
    }

    public void merge(SGroup lllllllllllllllllllllIIIllIIllll) {
        for (SBlock lllllllllllllllllllllIIIllIlIIIl : lllllllllllllllllllllIIIllIIllll.blocks) {
            SGroup lllllllllllllllllllllIIIllIIlllI;
            lllllllllllllllllllllIIIllIIlllI.add(lllllllllllllllllllllIIIllIlIIIl, false, false);
        }
        search.removeGroup(lllllllllllllllllllllIIIllIIllll);
    }

    public void remove(SBlock lllllllllllllllllllllIIlIIIIIllI, boolean lllllllllllllllllllllIIlIIIIIIlI) {
        SGroup lllllllllllllllllllllIIlIIIIIlll;
        lllllllllllllllllllllIIlIIIIIlll.blocks.remove(lllllllllllllllllllllIIlIIIIIllI);
        lllllllllllllllllllllIIlIIIIIlll.sumX -= (double)lllllllllllllllllllllIIlIIIIIllI.x;
        lllllllllllllllllllllIIlIIIIIlll.sumY -= (double)lllllllllllllllllllllIIlIIIIIllI.y;
        lllllllllllllllllllllIIlIIIIIlll.sumZ -= (double)lllllllllllllllllllllIIlIIIIIllI.z;
        if (lllllllllllllllllllllIIlIIIIIlll.blocks.isEmpty()) {
            search.removeGroup(lllllllllllllllllllllIIlIIIIIllI.group);
        } else if (lllllllllllllllllllllIIlIIIIIIlI) {
            lllllllllllllllllllllIIlIIIIIlll.trySplit(lllllllllllllllllllllIIlIIIIIllI);
        }
    }

    static {
        search = Modules.get().get(Search.class);
    }

    public void remove(SBlock lllllllllllllllllllllIIIllllllII) {
        SGroup lllllllllllllllllllllIIIllllllIl;
        lllllllllllllllllllllIIIllllllIl.remove(lllllllllllllllllllllIIIllllllII, true);
    }

    public void add(SBlock lllllllllllllllllllllIIlIIIlIlll, boolean lllllllllllllllllllllIIlIIIlIIlI, boolean lllllllllllllllllllllIIlIIIlIIIl) {
        SGroup lllllllllllllllllllllIIlIIIllIII;
        lllllllllllllllllllllIIlIIIllIII.blocks.add(lllllllllllllllllllllIIlIIIlIlll);
        lllllllllllllllllllllIIlIIIllIII.sumX += (double)lllllllllllllllllllllIIlIIIlIlll.x;
        lllllllllllllllllllllIIlIIIllIII.sumY += (double)lllllllllllllllllllllIIlIIIlIlll.y;
        lllllllllllllllllllllIIlIIIllIII.sumZ += (double)lllllllllllllllllllllIIlIIIlIlll.z;
        if (lllllllllllllllllllllIIlIIIlIlll.group != null && lllllllllllllllllllllIIlIIIlIIlI) {
            lllllllllllllllllllllIIlIIIlIlll.group.remove(lllllllllllllllllllllIIlIIIlIlll, lllllllllllllllllllllIIlIIIlIIIl);
        }
        lllllllllllllllllllllIIlIIIlIlll.group = lllllllllllllllllllllIIlIIIllIII;
    }

    public void render(RenderEvent lllllllllllllllllllllIIIllIIIIll) {
        SGroup lllllllllllllllllllllIIIllIIIlII;
        SBlockData lllllllllllllllllllllIIIllIIIlIl = search.getBlockData(lllllllllllllllllllllIIIllIIIlII.block);
        if (lllllllllllllllllllllIIIllIIIlIl.tracer) {
            RenderUtils.drawLine(RenderUtils.getCameraVector(), lllllllllllllllllllllIIIllIIIlII.sumX / (double)lllllllllllllllllllllIIIllIIIlII.blocks.size() + 0.5, lllllllllllllllllllllIIIllIIIlII.sumY / (double)lllllllllllllllllllllIIIllIIIlII.blocks.size() + 0.5, lllllllllllllllllllllIIIllIIIlII.sumZ / (double)lllllllllllllllllllllIIIllIIIlII.blocks.size() + 0.5, lllllllllllllllllllllIIIllIIIlIl.tracerColor, lllllllllllllllllllllIIIllIIIIll);
        }
    }

    public void add(SBlock lllllllllllllllllllllIIlIIIIlIll) {
        SGroup lllllllllllllllllllllIIlIIIIllII;
        lllllllllllllllllllllIIlIIIIllII.add(lllllllllllllllllllllIIlIIIIlIll, true, true);
    }

    public SGroup(class_2248 lllllllllllllllllllllIIlIIIlllIl) {
        SGroup lllllllllllllllllllllIIlIIlIIIII;
        lllllllllllllllllllllIIlIIlIIIII.blocks = new UnorderedArrayList<SBlock>();
        lllllllllllllllllllllIIlIIlIIIII.block = lllllllllllllllllllllIIlIIIlllIl;
    }
}

