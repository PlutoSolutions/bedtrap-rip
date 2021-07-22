/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  net.minecraft.class_1923
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2791
 */
package minegame159.meteorclient.systems.modules.render.search;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.BlockUpdateEvent;
import minegame159.meteorclient.events.world.ChunkDataEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BlockDataSetting;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.GenericSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.render.search.SBlock;
import minegame159.meteorclient.systems.modules.render.search.SBlockData;
import minegame159.meteorclient.systems.modules.render.search.SChunk;
import minegame159.meteorclient.systems.modules.render.search.SGroup;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.UnorderedArrayList;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.Dimension;
import net.minecraft.class_1923;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2791;

public class Search
extends Module {
    private final Setting<Map<class_2248, SBlockData>> blockConfigs;
    private final Setting<SBlockData> defaultBlockConfig;
    private final Setting<List<class_2248>> blocks;
    private final SettingGroup sgGeneral;
    private final List<SGroup> groups;
    private final Setting<Boolean> tracers;
    private Dimension lastDimension;
    private final class_2338.class_2339 blockPos;
    private final Long2ObjectMap<SChunk> chunks;

    private void searchChunk(class_2791 class_27912, ChunkDataEvent chunkDataEvent) {
        MeteorExecutor.execute(() -> this.lambda$searchChunk$1(class_27912, chunkDataEvent));
    }

    SBlockData getBlockData(class_2248 class_22482) {
        SBlockData sBlockData = this.blockConfigs.get().get((Object)class_22482);
        return sBlockData == null ? this.defaultBlockConfig.get() : sBlockData;
    }

    private void updateChunk(int n, int n2) {
        SChunk sChunk = (SChunk)this.chunks.get(class_1923.method_8331((int)n, (int)n2));
        if (sChunk != null) {
            sChunk.update();
        }
    }

    @EventHandler
    private void onPostTick(TickEvent.Post post) {
        Dimension dimension = PlayerUtils.getDimension();
        if (this.lastDimension != dimension) {
            this.onActivate();
        }
        this.lastDimension = dimension;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeGroup(SGroup sGroup) {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            this.groups.remove(sGroup);
            return;
        }
    }

    private void updateBlock(int n, int n2, int n3) {
        SChunk sChunk = (SChunk)this.chunks.get(class_1923.method_8331((int)(n >> 4), (int)(n3 >> 4)));
        if (sChunk != null) {
            sChunk.update(n, n2, n3);
        }
    }

    @EventHandler
    private void onChunkData(ChunkDataEvent chunkDataEvent) {
        this.searchChunk((class_2791)chunkDataEvent.chunk, chunkDataEvent);
    }

    private void onTickRainbow() {
        if (!this.isActive()) {
            return;
        }
        this.defaultBlockConfig.get().tickRainbow();
        for (SBlockData sBlockData : this.blockConfigs.get().values()) {
            sBlockData.tickRainbow();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onActivate() {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            this.chunks.clear();
            this.groups.clear();
        }
        int n = Utils.getRenderDistance() + 1;
        int n2 = this.mc.field_1724.field_6024 - n;
        while (true) {
            if (n2 > this.mc.field_1724.field_6024 + n) {
                this.lastDimension = PlayerUtils.getDimension();
                return;
            }
            for (int i = this.mc.field_1724.field_5980 - n; i <= this.mc.field_1724.field_5980 + n; ++i) {
                if (!this.mc.field_1687.method_2935().method_12123(n2, i)) continue;
                this.searchChunk((class_2791)this.mc.field_1687.method_8497(n2, i), null);
            }
            ++n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public SGroup newGroup(class_2248 class_22482) {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            SGroup sGroup = new SGroup(class_22482);
            this.groups.add(sGroup);
            return sGroup;
        }
    }

    public SBlock getBlock(int n, int n2, int n3) {
        SChunk sChunk = (SChunk)this.chunks.get(class_1923.method_8331((int)(n >> 4), (int)(n3 >> 4)));
        return sChunk == null ? null : sChunk.get(n, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onDeactivate() {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            this.chunks.clear();
            this.groups.clear();
            return;
        }
    }

    @EventHandler
    private void onBlockUpdate(BlockUpdateEvent blockUpdateEvent) {
        boolean bl;
        int n = blockUpdateEvent.pos.method_10263();
        int n2 = blockUpdateEvent.pos.method_10264();
        int n3 = blockUpdateEvent.pos.method_10260();
        int n4 = n >> 4;
        int n5 = n3 >> 4;
        long l = class_1923.method_8331((int)n4, (int)n5);
        boolean bl2 = this.blocks.get().contains((Object)blockUpdateEvent.newState.method_26204()) && !this.blocks.get().contains((Object)blockUpdateEvent.oldState.method_26204());
        boolean bl3 = bl = !bl2 && !this.blocks.get().contains((Object)blockUpdateEvent.newState.method_26204()) && this.blocks.get().contains((Object)blockUpdateEvent.oldState.method_26204());
        if (bl2 || bl) {
            MeteorExecutor.execute(() -> this.lambda$onBlockUpdate$2(l, n4, n5, n, n2, n3, bl2));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            Object object;
            Object object2 = this.chunks.values().iterator();
            while (object2.hasNext()) {
                object = (SChunk)object2.next();
                if (((SChunk)object).shouldBeDeleted()) {
                    MeteorExecutor.execute(() -> Search.lambda$onRender$3((SChunk)object));
                    object2.remove();
                    continue;
                }
                ((SChunk)object).render();
            }
            if (this.tracers.get() == false) return;
            object2 = this.groups.iterator();
            while (object2.hasNext()) {
                object = (SGroup)object2.next();
                if (((SGroup)object).blocks.isEmpty()) {
                    object2.remove();
                    continue;
                }
                ((SGroup)object).render(renderEvent);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void lambda$searchChunk$1(class_2791 class_27912, ChunkDataEvent chunkDataEvent) {
        if (!this.isActive()) {
            return;
        }
        SChunk sChunk = SChunk.searchChunk(class_27912, this.blocks.get());
        if (sChunk.size() > 0) {
            Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
            synchronized (long2ObjectMap) {
                this.chunks.put(class_27912.method_12004().method_8324(), (Object)sChunk);
                sChunk.update();
                this.updateChunk(class_27912.method_12004().field_9181 - 1, class_27912.method_12004().field_9180);
                this.updateChunk(class_27912.method_12004().field_9181 + 1, class_27912.method_12004().field_9180);
                this.updateChunk(class_27912.method_12004().field_9181, class_27912.method_12004().field_9180 - 1);
                this.updateChunk(class_27912.method_12004().field_9181, class_27912.method_12004().field_9180 + 1);
            }
        }
        if (chunkDataEvent != null) {
            ChunkDataEvent.returnChunkDataEvent(chunkDataEvent);
        }
    }

    public Search() {
        super(Categories.Render, "search", "Searches for specified blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks to search for.").defaultValue(new ArrayList<class_2248>(0)).onChanged(this::lambda$new$0).build());
        this.defaultBlockConfig = this.sgGeneral.add(new GenericSetting.Builder().name("default-block-config").description("Default block config.").defaultValue(new SBlockData(ShapeMode.Lines, new SettingColor(0, 255, 200), new SettingColor(0, 255, 200, 25), true, new SettingColor(0, 255, 200, 125))).build());
        this.blockConfigs = this.sgGeneral.add(new BlockDataSetting.Builder().name("block-configs").description("Config for each block.").defaultValue(new HashMap(0)).defaultData(this.defaultBlockConfig).build());
        this.tracers = this.sgGeneral.add(new BoolSetting.Builder().name("tracers").description("Render tracer lines.").defaultValue(false).build());
        this.blockPos = new class_2338.class_2339();
        this.chunks = new Long2ObjectOpenHashMap();
        this.groups = new UnorderedArrayList<SGroup>();
        RainbowColors.register(this::onTickRainbow);
    }

    private void lambda$new$0(List list) {
        if (this.isActive() && Utils.canUpdate()) {
            this.onActivate();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void lambda$onBlockUpdate$2(long l, int n, int n2, int n3, int n4, int n5, boolean bl) {
        Long2ObjectMap<SChunk> long2ObjectMap = this.chunks;
        synchronized (long2ObjectMap) {
            SChunk sChunk = (SChunk)this.chunks.get(l);
            if (sChunk == null) {
                sChunk = new SChunk(n, n2);
                if (sChunk.shouldBeDeleted()) {
                    return;
                }
                this.chunks.put(l, (Object)sChunk);
            }
            this.blockPos.method_10103(n3, n4, n5);
            if (bl) {
                sChunk.add((class_2338)this.blockPos);
            } else {
                sChunk.remove((class_2338)this.blockPos);
            }
            int n6 = -1;
            block2: while (n6 < 2) {
                int n7 = -1;
                while (true) {
                    if (n7 < 2) {
                    } else {
                        ++n6;
                        continue block2;
                    }
                    for (int i = -1; i < 2; ++i) {
                        if (n6 == 0 && i == 0 && n7 == 0) continue;
                        this.updateBlock(n3 + n6, n4 + i, n5 + n7);
                    }
                    ++n7;
                }
                break;
            }
            return;
        }
    }

    private static void lambda$onRender$3(SChunk sChunk) {
        for (SBlock sBlock : sChunk.blocks.values()) {
            sBlock.group.remove(sBlock, false);
            sBlock.loaded = false;
        }
    }
}

