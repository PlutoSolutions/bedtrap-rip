/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  net.minecraft.class_1923
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2791
 */
package minegame159.meteorclient.systems.modules.render.search;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private final /* synthetic */ Setting<Map<class_2248, SBlockData>> blockConfigs;
    private final /* synthetic */ Setting<SBlockData> defaultBlockConfig;
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ List<SGroup> groups;
    private final /* synthetic */ Setting<Boolean> tracers;
    private /* synthetic */ Dimension lastDimension;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ Long2ObjectMap<SChunk> chunks;

    private void searchChunk(class_2791 llllllllllllllllIllllIllIllIlIll, ChunkDataEvent llllllllllllllllIllllIllIllIlIlI) {
        Search llllllllllllllllIllllIllIllIllll;
        MeteorExecutor.execute(() -> {
            Search llllllllllllllllIllllIlIlllllIII;
            if (!llllllllllllllllIllllIlIlllllIII.isActive()) {
                return;
            }
            SChunk llllllllllllllllIllllIlIlllllIIl = SChunk.searchChunk(llllllllllllllllIllllIllIllIlIll, llllllllllllllllIllllIlIlllllIII.blocks.get());
            if (llllllllllllllllIllllIlIlllllIIl.size() > 0) {
                Long2ObjectMap<SChunk> llllllllllllllllIllllIlIllllIlII = llllllllllllllllIllllIlIlllllIII.chunks;
                synchronized (llllllllllllllllIllllIlIllllIlII) {
                    llllllllllllllllIllllIlIlllllIII.chunks.put(llllllllllllllllIllllIllIllIlIll.method_12004().method_8324(), (Object)llllllllllllllllIllllIlIlllllIIl);
                    llllllllllllllllIllllIlIlllllIIl.update();
                    llllllllllllllllIllllIlIlllllIII.updateChunk(llllllllllllllllIllllIlIllllIlll.method_12004().field_9181 - 1, llllllllllllllllIllllIlIllllIlll.method_12004().field_9180);
                    llllllllllllllllIllllIlIlllllIII.updateChunk(llllllllllllllllIllllIlIllllIlll.method_12004().field_9181 + 1, llllllllllllllllIllllIlIllllIlll.method_12004().field_9180);
                    llllllllllllllllIllllIlIlllllIII.updateChunk(llllllllllllllllIllllIlIllllIlll.method_12004().field_9181, llllllllllllllllIllllIlIllllIlll.method_12004().field_9180 - 1);
                    llllllllllllllllIllllIlIlllllIII.updateChunk(llllllllllllllllIllllIlIllllIlll.method_12004().field_9181, llllllllllllllllIllllIlIllllIlll.method_12004().field_9180 + 1);
                }
            }
            if (llllllllllllllllIllllIllIllIlIlI != null) {
                ChunkDataEvent.returnChunkDataEvent(llllllllllllllllIllllIllIllIlIlI);
            }
        });
    }

    SBlockData getBlockData(class_2248 llllllllllllllllIllllIlllIlllIll) {
        Search llllllllllllllllIllllIlllIllllll;
        SBlockData llllllllllllllllIllllIlllIllllIl = llllllllllllllllIllllIlllIllllll.blockConfigs.get().get((Object)llllllllllllllllIllllIlllIlllIll);
        return llllllllllllllllIllllIlllIllllIl == null ? llllllllllllllllIllllIlllIllllll.defaultBlockConfig.get() : llllllllllllllllIllllIlllIllllIl;
    }

    private void updateChunk(int llllllllllllllllIllllIlllIllIlII, int llllllllllllllllIllllIlllIllIIll) {
        Search llllllllllllllllIllllIlllIllIIIl;
        SChunk llllllllllllllllIllllIlllIllIIlI = (SChunk)llllllllllllllllIllllIlllIllIIIl.chunks.get(class_1923.method_8331((int)llllllllllllllllIllllIlllIllIlII, (int)llllllllllllllllIllllIlllIllIIll));
        if (llllllllllllllllIllllIlllIllIIlI != null) {
            llllllllllllllllIllllIlllIllIIlI.update();
        }
    }

    @EventHandler
    private void onPostTick(TickEvent.Post llllllllllllllllIllllIllIlIIlIII) {
        Search llllllllllllllllIllllIllIlIIIllI;
        Dimension llllllllllllllllIllllIllIlIIIlll = PlayerUtils.getDimension();
        if (llllllllllllllllIllllIllIlIIIllI.lastDimension != llllllllllllllllIllllIllIlIIIlll) {
            llllllllllllllllIllllIllIlIIIllI.onActivate();
        }
        llllllllllllllllIllllIllIlIIIllI.lastDimension = llllllllllllllllIllllIllIlIIIlll;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeGroup(SGroup llllllllllllllllIllllIllIllllIll) {
        Search llllllllllllllllIllllIllIlllllII;
        Long2ObjectMap<SChunk> llllllllllllllllIllllIllIllllIlI = llllllllllllllllIllllIllIlllllII.chunks;
        synchronized (llllllllllllllllIllllIllIllllIlI) {
            llllllllllllllllIllllIllIlllllII.groups.remove(llllllllllllllllIllllIllIllllIll);
        }
    }

    private void updateBlock(int llllllllllllllllIllllIlllIlIIlll, int llllllllllllllllIllllIlllIlIIIIl, int llllllllllllllllIllllIlllIlIIlIl) {
        Search llllllllllllllllIllllIlllIlIIIll;
        SChunk llllllllllllllllIllllIlllIlIIlII = (SChunk)llllllllllllllllIllllIlllIlIIIll.chunks.get(class_1923.method_8331((int)(llllllllllllllllIllllIlllIlIIlll >> 4), (int)(llllllllllllllllIllllIlllIlIIlIl >> 4)));
        if (llllllllllllllllIllllIlllIlIIlII != null) {
            llllllllllllllllIllllIlllIlIIlII.update(llllllllllllllllIllllIlllIlIIlll, llllllllllllllllIllllIlllIlIIIIl, llllllllllllllllIllllIlllIlIIlIl);
        }
    }

    @EventHandler
    private void onChunkData(ChunkDataEvent llllllllllllllllIllllIllIlllIlIl) {
        Search llllllllllllllllIllllIllIlllIlII;
        llllllllllllllllIllllIllIlllIlII.searchChunk((class_2791)llllllllllllllllIllllIllIlllIlIl.chunk, llllllllllllllllIllllIllIlllIlIl);
    }

    private void onTickRainbow() {
        Search llllllllllllllllIllllIllllIIIllI;
        if (!llllllllllllllllIllllIllllIIIllI.isActive()) {
            return;
        }
        llllllllllllllllIllllIllllIIIllI.defaultBlockConfig.get().tickRainbow();
        for (SBlockData llllllllllllllllIllllIllllIIIlll : llllllllllllllllIllllIllllIIIllI.blockConfigs.get().values()) {
            llllllllllllllllIllllIllllIIIlll.tickRainbow();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onActivate() {
        Search llllllllllllllllIllllIllllIlIlll;
        Long2ObjectMap<SChunk> llllllllllllllllIllllIllllIlIlII = llllllllllllllllIllllIllllIlIlll.chunks;
        synchronized (llllllllllllllllIllllIllllIlIlII) {
            llllllllllllllllIllllIllllIlIlll.chunks.clear();
            llllllllllllllllIllllIllllIlIlll.groups.clear();
        }
        int llllllllllllllllIllllIllllIlIllI = Utils.getRenderDistance() + 1;
        for (int llllllllllllllllIllllIllllIllIII = llllllllllllllllIllllIllllIlIlll.mc.field_1724.field_6024 - llllllllllllllllIllllIllllIlIllI; llllllllllllllllIllllIllllIllIII <= llllllllllllllllIllllIllllIlIlll.mc.field_1724.field_6024 + llllllllllllllllIllllIllllIlIllI; ++llllllllllllllllIllllIllllIllIII) {
            for (int llllllllllllllllIllllIllllIllIIl = llllllllllllllllIllllIllllIlIlll.mc.field_1724.field_5980 - llllllllllllllllIllllIllllIlIllI; llllllllllllllllIllllIllllIllIIl <= llllllllllllllllIllllIllllIlIlll.mc.field_1724.field_5980 + llllllllllllllllIllllIllllIlIllI; ++llllllllllllllllIllllIllllIllIIl) {
                if (!llllllllllllllllIllllIllllIlIlll.mc.field_1687.method_2935().method_12123(llllllllllllllllIllllIllllIllIII, llllllllllllllllIllllIllllIllIIl)) continue;
                llllllllllllllllIllllIllllIlIlll.searchChunk((class_2791)llllllllllllllllIllllIllllIlIlll.mc.field_1687.method_8497(llllllllllllllllIllllIllllIllIII, llllllllllllllllIllllIllllIllIIl), null);
            }
        }
        llllllllllllllllIllllIllllIlIlll.lastDimension = PlayerUtils.getDimension();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SGroup newGroup(class_2248 llllllllllllllllIllllIlllIIIlIII) {
        Search llllllllllllllllIllllIlllIIIIlll;
        Long2ObjectMap<SChunk> llllllllllllllllIllllIlllIIIIlIl = llllllllllllllllIllllIlllIIIIlll.chunks;
        synchronized (llllllllllllllllIllllIlllIIIIlIl) {
            SGroup llllllllllllllllIllllIlllIIIlIlI = new SGroup(llllllllllllllllIllllIlllIIIlIII);
            llllllllllllllllIllllIlllIIIIlll.groups.add(llllllllllllllllIllllIlllIIIlIlI);
            return llllllllllllllllIllllIlllIIIlIlI;
        }
    }

    public SBlock getBlock(int llllllllllllllllIllllIlllIIllIII, int llllllllllllllllIllllIlllIIlIIlI, int llllllllllllllllIllllIlllIIlIIIl) {
        Search llllllllllllllllIllllIlllIIllIIl;
        SChunk llllllllllllllllIllllIlllIIlIlIl = (SChunk)llllllllllllllllIllllIlllIIllIIl.chunks.get(class_1923.method_8331((int)(llllllllllllllllIllllIlllIIllIII >> 4), (int)(llllllllllllllllIllllIlllIIlIIIl >> 4)));
        return llllllllllllllllIllllIlllIIlIlIl == null ? null : llllllllllllllllIllllIlllIIlIlIl.get(llllllllllllllllIllllIlllIIllIII, llllllllllllllllIllllIlllIIlIIlI, llllllllllllllllIllllIlllIIlIIIl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onDeactivate() {
        Search llllllllllllllllIllllIllllIIllIl;
        Long2ObjectMap<SChunk> llllllllllllllllIllllIllllIIllII = llllllllllllllllIllllIllllIIllIl.chunks;
        synchronized (llllllllllllllllIllllIllllIIllII) {
            llllllllllllllllIllllIllllIIllIl.chunks.clear();
            llllllllllllllllIllllIllllIIllIl.groups.clear();
        }
    }

    @EventHandler
    private void onBlockUpdate(BlockUpdateEvent llllllllllllllllIllllIllIlIlIlII) {
        boolean llllllllllllllllIllllIllIlIlIllI;
        Search llllllllllllllllIllllIllIlIlllll;
        int llllllllllllllllIllllIllIlIlllIl = llllllllllllllllIllllIllIlIlIlII.pos.method_10263();
        int llllllllllllllllIllllIllIlIlllII = llllllllllllllllIllllIllIlIlIlII.pos.method_10264();
        int llllllllllllllllIllllIllIlIllIll = llllllllllllllllIllllIllIlIlIlII.pos.method_10260();
        int llllllllllllllllIllllIllIlIllIlI = llllllllllllllllIllllIllIlIlllIl >> 4;
        int llllllllllllllllIllllIllIlIllIIl = llllllllllllllllIllllIllIlIllIll >> 4;
        long llllllllllllllllIllllIllIlIllIII = class_1923.method_8331((int)llllllllllllllllIllllIllIlIllIlI, (int)llllllllllllllllIllllIllIlIllIIl);
        boolean llllllllllllllllIllllIllIlIlIlll = llllllllllllllllIllllIllIlIlllll.blocks.get().contains((Object)llllllllllllllllIllllIllIlIlIlII.newState.method_26204()) && !llllllllllllllllIllllIllIlIlllll.blocks.get().contains((Object)llllllllllllllllIllllIllIlIlIlII.oldState.method_26204());
        boolean bl = llllllllllllllllIllllIllIlIlIllI = !llllllllllllllllIllllIllIlIlIlll && !llllllllllllllllIllllIllIlIlllll.blocks.get().contains((Object)llllllllllllllllIllllIllIlIlIlII.newState.method_26204()) && llllllllllllllllIllllIllIlIlllll.blocks.get().contains((Object)llllllllllllllllIllllIllIlIlIlII.oldState.method_26204());
        if (llllllllllllllllIllllIllIlIlIlll || llllllllllllllllIllllIllIlIlIllI) {
            MeteorExecutor.execute(() -> {
                Search llllllllllllllllIllllIllIIIllIII;
                Long2ObjectMap<SChunk> llllllllllllllllIllllIllIIIIlIII = llllllllllllllllIllllIllIIIllIII.chunks;
                synchronized (llllllllllllllllIllllIllIIIIlIII) {
                    SChunk llllllllllllllllIllllIllIIIllIIl = (SChunk)llllllllllllllllIllllIllIIIllIII.chunks.get(llllllllllllllllIllllIllIlIllIII);
                    if (llllllllllllllllIllllIllIIIllIIl == null) {
                        llllllllllllllllIllllIllIIIllIIl = new SChunk(llllllllllllllllIllllIllIlIllIlI, llllllllllllllllIllllIllIlIllIIl);
                        if (llllllllllllllllIllllIllIIIllIIl.shouldBeDeleted()) {
                            return;
                        }
                        llllllllllllllllIllllIllIIIllIII.chunks.put(llllllllllllllllIllllIllIlIllIII, (Object)llllllllllllllllIllllIllIIIllIIl);
                    }
                    llllllllllllllllIllllIllIIIllIII.blockPos.method_10103(llllllllllllllllIllllIllIlIlllIl, llllllllllllllllIllllIllIlIlllII, llllllllllllllllIllllIllIlIllIll);
                    if (llllllllllllllllIllllIllIlIlIlll) {
                        llllllllllllllllIllllIllIIIllIIl.add((class_2338)llllllllllllllllIllllIllIIIllIII.blockPos);
                    } else {
                        llllllllllllllllIllllIllIIIllIIl.remove((class_2338)llllllllllllllllIllllIllIIIllIII.blockPos);
                    }
                    for (int llllllllllllllllIllllIllIIIllIlI = -1; llllllllllllllllIllllIllIIIllIlI < 2; ++llllllllllllllllIllllIllIIIllIlI) {
                        for (int llllllllllllllllIllllIllIIIllIll = -1; llllllllllllllllIllllIllIIIllIll < 2; ++llllllllllllllllIllllIllIIIllIll) {
                            for (int llllllllllllllllIllllIllIIIlllII = -1; llllllllllllllllIllllIllIIIlllII < 2; ++llllllllllllllllIllllIllIIIlllII) {
                                if (llllllllllllllllIllllIllIIIllIlI == 0 && llllllllllllllllIllllIllIIIlllII == 0 && llllllllllllllllIllllIllIIIllIll == 0) continue;
                                llllllllllllllllIllllIllIIIllIII.updateBlock(llllllllllllllllIllllIllIlIlllIl + llllllllllllllllIllllIllIIIllIlI, llllllllllllllllIllllIllIlIlllII + llllllllllllllllIllllIllIIIlllII, llllllllllllllllIllllIllIlIllIll + llllllllllllllllIllllIllIIIllIll);
                            }
                        }
                    }
                }
            });
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    private void onRender(RenderEvent llllllllllllllllIllllIllIIlllIIl) {
        Search llllllllllllllllIllllIllIIlllIII;
        Long2ObjectMap<SChunk> llllllllllllllllIllllIllIIllIllI = llllllllllllllllIllllIllIIlllIII.chunks;
        synchronized (llllllllllllllllIllllIllIIllIllI) {
            ObjectIterator llllllllllllllllIllllIllIIllllIl = llllllllllllllllIllllIllIIlllIII.chunks.values().iterator();
            while (llllllllllllllllIllllIllIIllllIl.hasNext()) {
                SChunk llllllllllllllllIllllIllIIlllllI = (SChunk)llllllllllllllllIllllIllIIllllIl.next();
                if (llllllllllllllllIllllIllIIlllllI.shouldBeDeleted()) {
                    MeteorExecutor.execute(() -> {
                        for (SBlock llllllllllllllllIllllIllIIlIllll : llllllllllllllllIllllIllIIlIllIl.blocks.values()) {
                            llllllllllllllllIllllIllIIlIllll.group.remove(llllllllllllllllIllllIllIIlIllll, false);
                            llllllllllllllllIllllIllIIlIllll.loaded = false;
                        }
                    });
                    llllllllllllllllIllllIllIIllllIl.remove();
                    continue;
                }
                llllllllllllllllIllllIllIIlllllI.render();
            }
            if (llllllllllllllllIllllIllIIlllIII.tracers.get().booleanValue()) {
                Iterator<SGroup> llllllllllllllllIllllIllIIlllIll = llllllllllllllllIllllIllIIlllIII.groups.iterator();
                while (llllllllllllllllIllllIllIIlllIll.hasNext()) {
                    SGroup llllllllllllllllIllllIllIIllllII = llllllllllllllllIllllIllIIlllIll.next();
                    if (llllllllllllllllIllllIllIIllllII.blocks.isEmpty()) {
                        llllllllllllllllIllllIllIIlllIll.remove();
                        continue;
                    }
                    llllllllllllllllIllllIllIIllllII.render(llllllllllllllllIllllIllIIlllIIl);
                }
            }
        }
    }

    public Search() {
        super(Categories.Render, "search", "Searches for specified blocks.");
        Search llllllllllllllllIllllIllllIlllll;
        llllllllllllllllIllllIllllIlllll.sgGeneral = llllllllllllllllIllllIllllIlllll.settings.getDefaultGroup();
        llllllllllllllllIllllIllllIlllll.blocks = llllllllllllllllIllllIllllIlllll.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks to search for.").defaultValue(new ArrayList<class_2248>(0)).onChanged(llllllllllllllllIllllIlIllllIIII -> {
            Search llllllllllllllllIllllIlIllllIIIl;
            if (llllllllllllllllIllllIlIllllIIIl.isActive() && Utils.canUpdate()) {
                llllllllllllllllIllllIlIllllIIIl.onActivate();
            }
        }).build());
        llllllllllllllllIllllIllllIlllll.defaultBlockConfig = llllllllllllllllIllllIllllIlllll.sgGeneral.add(new GenericSetting.Builder().name("default-block-config").description("Default block config.").defaultValue(new SBlockData(ShapeMode.Lines, new SettingColor(0, 255, 200), new SettingColor(0, 255, 200, 25), true, new SettingColor(0, 255, 200, 125))).build());
        llllllllllllllllIllllIllllIlllll.blockConfigs = llllllllllllllllIllllIllllIlllll.sgGeneral.add(new BlockDataSetting.Builder().name("block-configs").description("Config for each block.").defaultValue(new HashMap(0)).defaultData(llllllllllllllllIllllIllllIlllll.defaultBlockConfig).build());
        llllllllllllllllIllllIllllIlllll.tracers = llllllllllllllllIllllIllllIlllll.sgGeneral.add(new BoolSetting.Builder().name("tracers").description("Render tracer lines.").defaultValue(false).build());
        llllllllllllllllIllllIllllIlllll.blockPos = new class_2338.class_2339();
        llllllllllllllllIllllIllllIlllll.chunks = new Long2ObjectOpenHashMap();
        llllllllllllllllIllllIllllIlllll.groups = new UnorderedArrayList<SGroup>();
        RainbowColors.register(llllllllllllllllIllllIllllIlllll::onTickRainbow);
    }
}

