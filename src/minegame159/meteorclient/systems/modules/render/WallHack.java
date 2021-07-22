/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
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
    public final /* synthetic */ Setting<List<class_2248>> blocks;
    public final /* synthetic */ Setting<Boolean> occludeChunks;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Integer> opacity;

    public WallHack() {
        super(Categories.Render, "wall-hack", "Makes blocks translucent.");
        WallHack llllllllllllllllIlllIIIIlIIIlIll;
        llllllllllllllllIlllIIIIlIIIlIll.sgGeneral = llllllllllllllllIlllIIIIlIIIlIll.settings.getDefaultGroup();
        llllllllllllllllIlllIIIIlIIIlIll.opacity = llllllllllllllllIlllIIIIlIIIlIll.sgGeneral.add(new IntSetting.Builder().name("opacity").description("The opacity for rendered blocks.").defaultValue(1).min(1).max(255).sliderMax(255).onChanged(llllllllllllllllIlllIIIIIlllIlll -> {
            WallHack llllllllllllllllIlllIIIIIlllIllI;
            if (llllllllllllllllIlllIIIIIlllIllI.isActive()) {
                llllllllllllllllIlllIIIIIlllIllI.mc.field_1769.method_3279();
            }
        }).build());
        llllllllllllllllIlllIIIIlIIIlIll.blocks = llllllllllllllllIlllIIIIlIIIlIll.sgGeneral.add(new BlockListSetting.Builder().name("blocks").description("What blocks should be targeted for Wall Hack.").defaultValue(new ArrayList<class_2248>()).onChanged(llllllllllllllllIlllIIIIIllllIll -> {
            WallHack llllllllllllllllIlllIIIIIlllllII;
            if (llllllllllllllllIlllIIIIIlllllII.isActive()) {
                llllllllllllllllIlllIIIIIlllllII.mc.field_1769.method_3279();
            }
        }).build());
        llllllllllllllllIlllIIIIlIIIlIll.occludeChunks = llllllllllllllllIlllIIIIlIIIlIll.sgGeneral.add(new BoolSetting.Builder().name("occlude-chunks").description("Whether caves should occlude underground (may look wonky when on).").defaultValue(false).build());
    }

    @Override
    public void onActivate() {
        WallHack llllllllllllllllIlllIIIIlIIIIlll;
        llllllllllllllllIlllIIIIlIIIIlll.mc.field_1769.method_3279();
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent llllllllllllllllIlllIIIIIllllllI) {
        WallHack llllllllllllllllIlllIIIIIlllllll;
        if (!llllllllllllllllIlllIIIIIlllllll.occludeChunks.get().booleanValue()) {
            llllllllllllllllIlllIIIIIllllllI.cancel();
        }
    }

    @Override
    public void onDeactivate() {
        WallHack llllllllllllllllIlllIIIIlIIIIlIl;
        llllllllllllllllIlllIIIIlIIIIlIl.mc.field_1769.method_3279();
    }
}

