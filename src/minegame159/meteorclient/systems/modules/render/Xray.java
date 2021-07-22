/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_259
 *  net.minecraft.class_2680
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
    private final /* synthetic */ Setting<List<class_2248>> blocks;
    private final /* synthetic */ SettingGroup sgGeneral;

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent lllIIlllllllll) {
        lllIIlllllllll.cancel();
    }

    public Xray() {
        super(Categories.Render, "xray", "Only renders specified blocks. Good for mining.");
        Xray lllIlIIIIlIIII;
        lllIlIIIIlIIII.sgGeneral = lllIlIIIIlIIII.settings.getDefaultGroup();
        lllIlIIIIlIIII.blocks = lllIlIIIIlIIII.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("Blocks.").defaultValue(Arrays.asList(new class_2248[]{class_2246.field_10418, class_2246.field_10212, class_2246.field_10571, class_2246.field_10090, class_2246.field_10080, class_2246.field_10442, class_2246.field_10013, class_2246.field_23077, class_2246.field_10213, class_2246.field_22109})).onChanged(lllIIlllIllIlI -> {
            Xray lllIIlllIllIIl;
            if (lllIIlllIllIIl.isActive()) {
                lllIIlllIllIIl.mc.field_1769.method_3279();
            }
        }).build());
    }

    public boolean modifyDrawSide(class_2680 lllIIllllIlIIl, class_1922 lllIIllllIlIII, class_2338 lllIIllllIIlll, class_2350 lllIIllllIllII, boolean lllIIllllIlIll) {
        Xray lllIIlllllIIII;
        if (lllIIllllIlIll) {
            if (lllIIlllllIIII.isBlocked(lllIIllllIlIIl.method_26204())) {
                return false;
            }
        } else if (!lllIIlllllIIII.isBlocked(lllIIllllIlIIl.method_26204())) {
            class_2338 lllIIlllllIIlI = lllIIllllIIlll.method_10093(lllIIllllIllII);
            class_2680 lllIIlllllIIIl = lllIIllllIlIII.method_8320(lllIIlllllIIlI);
            return lllIIlllllIIIl.method_26173(lllIIllllIlIII, lllIIlllllIIlI, lllIIllllIllII.method_10153()) != class_259.method_1077() || lllIIlllllIIIl.method_26204() != lllIIllllIlIIl.method_26204();
        }
        return lllIIllllIlIll;
    }

    @Override
    public void onDeactivate() {
        Xray lllIlIIIIIlIIl;
        Fullbright.disable();
        lllIlIIIIIlIIl.mc.field_1769.method_3279();
    }

    @EventHandler
    private void onRenderBlockEntity(RenderBlockEntityEvent lllIlIIIIIIlIl) {
        Xray lllIlIIIIIIlII;
        if (lllIlIIIIIIlII.isBlocked(lllIlIIIIIIlIl.blockEntity.method_11010().method_26204())) {
            lllIlIIIIIIlIl.cancel();
        }
    }

    public boolean isBlocked(class_2248 lllIIlllIlllIl) {
        Xray lllIIllllIIIII;
        return !lllIIllllIIIII.blocks.get().contains((Object)lllIIlllIlllIl);
    }

    @EventHandler
    private void onAmbientOcclusion(AmbientOcclusionEvent lllIIlllllllII) {
        lllIIlllllllII.lightLevel = 1.0f;
    }

    @Override
    public void onActivate() {
        Xray lllIlIIIIIllII;
        Fullbright.enable();
        lllIlIIIIIllII.mc.field_1769.method_3279();
    }
}

