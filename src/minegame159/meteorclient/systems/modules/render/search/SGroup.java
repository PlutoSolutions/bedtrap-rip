/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.search;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
    public final List<SBlock> blocks = new UnorderedArrayList<SBlock>();
    private double sumZ;
    private final class_2248 block;
    private double sumX;
    private double sumY;
    private static final Search search = Modules.get().get(Search.class);

    private void trySplit(SBlock sBlock) {
        ObjectOpenHashSet objectOpenHashSet = new ObjectOpenHashSet(6);
        for (int n : SBlock.SIDES) {
            SBlock sBlock2;
            if ((sBlock.neighbours & n) != n || (sBlock2 = sBlock.getSideBlock(n)) == null) continue;
            objectOpenHashSet.add(sBlock2);
            if (3 >= 1) continue;
            return;
        }
        if (objectOpenHashSet.size() <= 1) {
            return;
        }
        Object object = new ObjectOpenHashSet(this.blocks);
        ArrayDeque<SBlock> arrayDeque = new ArrayDeque<SBlock>();
        arrayDeque.offer(this.blocks.get(0));
        object.remove(this.blocks.get(0));
        objectOpenHashSet.remove(this.blocks.get(0));
        block1: while (!arrayDeque.isEmpty()) {
            SBlock sBlock3 = (SBlock)arrayDeque.poll();
            for (int n : SBlock.SIDES) {
                SBlock sBlock4;
                if ((sBlock3.neighbours & n) != n || (sBlock4 = sBlock3.getSideBlock(n)) == null || !object.contains(sBlock4)) continue;
                arrayDeque.offer(sBlock4);
                object.remove(sBlock4);
                objectOpenHashSet.remove(sBlock4);
                if (objectOpenHashSet.isEmpty()) break block1;
                if (null == null) continue;
                return;
            }
        }
        if (objectOpenHashSet.size() > 0) {
            SGroup sGroup = search.newGroup(this.block);
            ((UnorderedArrayList)sGroup.blocks).ensureCapacity(object.size());
            Objects.requireNonNull(object);
            this.blocks.removeIf(((Set)object)::contains);
            Iterator iterator = object.iterator();
            while (iterator.hasNext()) {
                SBlock sBlock5 = (SBlock)iterator.next();
                sGroup.add(sBlock5, false, false);
                this.sumX -= (double)sBlock5.x;
                this.sumY -= (double)sBlock5.y;
                this.sumZ -= (double)sBlock5.z;
            }
            if (objectOpenHashSet.size() > 1) {
                sBlock.neighbours = 0;
                for (SBlock sBlock6 : objectOpenHashSet) {
                    int n;
                    int n2 = sBlock6.x - sBlock.x;
                    if (n2 == 1) {
                        sBlock.neighbours |= 8;
                    } else if (n2 == -1) {
                        sBlock.neighbours |= 0x80;
                    }
                    n = sBlock6.y - sBlock.y;
                    if (n == 1) {
                        sBlock.neighbours |= 0x200;
                    } else if (n == -1) {
                        sBlock.neighbours |= 0x4000;
                    }
                    int n3 = sBlock6.z - sBlock.z;
                    if (n3 == 1) {
                        sBlock.neighbours |= 2;
                        continue;
                    }
                    if (n3 != -1) continue;
                    sBlock.neighbours |= 0x20;
                }
                sGroup.trySplit(sBlock);
            }
        }
    }

    public void merge(SGroup sGroup) {
        for (SBlock sBlock : sGroup.blocks) {
            this.add(sBlock, false, false);
        }
        search.removeGroup(sGroup);
    }

    public void remove(SBlock sBlock, boolean bl) {
        this.blocks.remove(sBlock);
        this.sumX -= (double)sBlock.x;
        this.sumY -= (double)sBlock.y;
        this.sumZ -= (double)sBlock.z;
        if (this.blocks.isEmpty()) {
            search.removeGroup(sBlock.group);
        } else if (bl) {
            this.trySplit(sBlock);
        }
    }

    public void remove(SBlock sBlock) {
        this.remove(sBlock, true);
    }

    public void add(SBlock sBlock, boolean bl, boolean bl2) {
        this.blocks.add(sBlock);
        this.sumX += (double)sBlock.x;
        this.sumY += (double)sBlock.y;
        this.sumZ += (double)sBlock.z;
        if (sBlock.group != null && bl) {
            sBlock.group.remove(sBlock, bl2);
        }
        sBlock.group = this;
    }

    public void render(RenderEvent renderEvent) {
        SBlockData sBlockData = search.getBlockData(this.block);
        if (sBlockData.tracer) {
            RenderUtils.drawLine(RenderUtils.getCameraVector(), this.sumX / (double)this.blocks.size() + 0.5, this.sumY / (double)this.blocks.size() + 0.5, this.sumZ / (double)this.blocks.size() + 0.5, sBlockData.tracerColor, renderEvent);
        }
    }

    public void add(SBlock sBlock) {
        this.add(sBlock, true, true);
    }

    public SGroup(class_2248 class_22482) {
        this.block = class_22482;
    }
}

