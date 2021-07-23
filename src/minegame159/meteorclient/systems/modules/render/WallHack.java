/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.ChunkOcclusionEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2248;

public class WallHack
extends Module {
    public final Setting<List<class_2248>> blocks;
    public final Setting<Boolean> occludeChunks;
    private final SettingGroup sgGeneral;
    public final Setting<Integer> opacity;

    public WallHack() {
        super(Categories.Render, "wall-hack", "Makes blocks translucent.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.opacity = this.sgGeneral.add(new IntSetting.Builder().name("opacity").description("The opacity for rendered blocks.").defaultValue(1).min(1).max(255).sliderMax(255).onChanged(this::lambda$new$0).build());
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("What blocks should be targeted for Wall Hack.").defaultValue(new ArrayList<class_2248>()).onChanged(this::lambda$new$1).build());
        this.occludeChunks = this.sgGeneral.add(new BoolSetting.Builder().name("occlude-chunks").description("Whether caves should occlude underground (may look wonky when on).").defaultValue(false).build());
    }

    private void lambda$new$1(List list) {
        if (this.isActive()) {
            this.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onActivate() {
        this.mc.field_1769.method_3279();
    }

    private void lambda$new$0(Integer n) {
        if (this.isActive()) {
            this.mc.field_1769.method_3279();
        }
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent chunkOcclusionEvent) {
        if (!this.occludeChunks.get().booleanValue()) {
            chunkOcclusionEvent.cancel();
        }
    }

    @Override
    public void onDeactivate() {
        this.mc.field_1769.method_3279();
    }
}

