/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import java.util.Arrays;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderBlockEntityEvent;
import minegame159.meteorclient.events.world.AmbientOcclusionEvent;
import minegame159.meteorclient.events.world.ChunkOcclusionEvent;
import minegame159.meteorclient.settings.BlockListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.render.Fullbright;
import net.minecraft.class_1922;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_259;
import net.minecraft.class_2680;

public class Xray
extends Module {
    private final Setting<List<class_2248>> blocks;
    private final SettingGroup sgGeneral;

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent chunkOcclusionEvent) {
        chunkOcclusionEvent.cancel();
    }

    public Xray() {
        super(Categories.Render, "xray", "Only renders specified blocks. Good for mining.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.blocks = this.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks.").defaultValue(Arrays.asList(class_2246.field_10418, class_2246.field_10212, class_2246.field_10571, class_2246.field_10090, class_2246.field_10080, class_2246.field_10442, class_2246.field_10013, class_2246.field_23077, class_2246.field_10213, class_2246.field_22109)).onChanged(this::lambda$new$0).build());
    }

    public boolean modifyDrawSide(class_2680 class_26802, class_1922 class_19222, class_2338 class_23382, class_2350 class_23502, boolean bl) {
        if (bl) {
            if (this.isBlocked(class_26802.method_26204())) {
                return false;
            }
        } else if (!this.isBlocked(class_26802.method_26204())) {
            class_2338 class_23383 = class_23382.method_10093(class_23502);
            class_2680 class_26803 = class_19222.method_8320(class_23383);
            return class_26803.method_26173(class_19222, class_23383, class_23502.method_10153()) != class_259.method_1077() || class_26803.method_26204() != class_26802.method_26204();
        }
        return bl;
    }

    @Override
    public void onDeactivate() {
        Fullbright.disable();
        this.mc.field_1769.method_3279();
    }

    @EventHandler
    private void onRenderBlockEntity(RenderBlockEntityEvent renderBlockEntityEvent) {
        if (this.isBlocked(renderBlockEntityEvent.blockEntity.method_11010().method_26204())) {
            renderBlockEntityEvent.cancel();
        }
    }

    public boolean isBlocked(class_2248 class_22482) {
        return !this.blocks.get().contains(class_22482);
    }

    @EventHandler
    private void onAmbientOcclusion(AmbientOcclusionEvent ambientOcclusionEvent) {
        ambientOcclusionEvent.lightLevel = 1.0f;
    }

    private void lambda$new$0(List list) {
        if (this.isActive()) {
            this.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onActivate() {
        Fullbright.enable();
        this.mc.field_1769.method_3279();
    }
}

