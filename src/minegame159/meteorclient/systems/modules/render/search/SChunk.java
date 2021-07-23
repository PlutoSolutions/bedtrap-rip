/*
 * Decompiled with CFR 0.151.
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
    public Long2ObjectMap<SBlock> blocks;
    private static final class_2338.class_2339 blockPos = new class_2338.class_2339();
    private final int z;
    private final int x;

    public void remove(class_2338 class_23382) {
        SBlock sBlock = (SBlock)this.blocks.remove(SBlock.getKey(class_23382));
        if (sBlock != null) {
            sBlock.group.remove(sBlock);
        }
    }

    public void add(class_2338 class_23382, boolean bl) {
        SBlock sBlock = new SBlock(class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260());
        if (this.blocks == null) {
            this.blocks = new Long2ObjectOpenHashMap(64);
        }
        this.blocks.put(SBlock.getKey(class_23382), (Object)sBlock);
        if (bl) {
            sBlock.update();
        }
    }

    public int size() {
        return this.blocks == null ? 0 : this.blocks.size();
    }

    public void render() {
        for (SBlock sBlock : this.blocks.values()) {
            sBlock.render();
        }
    }

    public boolean shouldBeDeleted() {
        int n = Utils.getRenderDistance() + 1;
        return this.x > Utils.mc.field_1724.field_6024 + n || this.x < Utils.mc.field_1724.field_6024 - n || this.z > Utils.mc.field_1724.field_5980 + n || this.z < Utils.mc.field_1724.field_5980 - n;
    }

    public SBlock get(int n, int n2, int n3) {
        return this.blocks == null ? null : (SBlock)this.blocks.get(SBlock.getKey(n, n2, n3));
    }

    public void update(int n, int n2, int n3) {
        SBlock sBlock = (SBlock)this.blocks.get(SBlock.getKey(n, n2, n3));
        if (sBlock != null) {
            sBlock.update();
        }
    }

    public void add(class_2338 class_23382) {
        this.add(class_23382, true);
    }

    public SChunk(int n, int n2) {
        this.x = n;
        this.z = n2;
    }

    public void update() {
        for (SBlock sBlock : this.blocks.values()) {
            sBlock.update();
        }
    }

    public static SChunk searchChunk(class_2791 class_27912, List<class_2248> list) {
        SChunk sChunk = new SChunk(class_27912.method_12004().field_9181, class_27912.method_12004().field_9180);
        if (sChunk.shouldBeDeleted()) {
            return sChunk;
        }
        for (int i = class_27912.method_12004().method_8326(); i <= class_27912.method_12004().method_8327(); ++i) {
            for (int j = class_27912.method_12004().method_8328(); j <= class_27912.method_12004().method_8329(); ++j) {
                int n = class_27912.method_12032(class_2902.class_2903.field_13202).method_12603(i - class_27912.method_12004().method_8326(), j - class_27912.method_12004().method_8328());
                for (int k = 0; k < n; ++k) {
                    blockPos.method_10103(i, k, j);
                    class_2680 class_26802 = class_27912.method_8320((class_2338)blockPos);
                    if (!list.contains(class_26802.method_26204())) continue;
                    sChunk.add((class_2338)blockPos, false);
                    if (true) continue;
                    return null;
                }
                if (5 > 0) continue;
                return null;
            }
            if (!false) continue;
            return null;
        }
        return sChunk;
    }
}

