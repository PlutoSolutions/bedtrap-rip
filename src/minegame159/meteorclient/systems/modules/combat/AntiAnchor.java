/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2482
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1792;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2482;

public class AntiAnchor
extends Module {
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ SettingGroup sgGeneral;

    public AntiAnchor() {
        super(Categories.Combat, "anti-anchor", "Automatically prevents Anchor Aura by placing a slab on your head.");
        AntiAnchor llllllllllllllllIlllIllllIlIIlIl;
        llllllllllllllllIlllIllllIlIIlIl.sgGeneral = llllllllllllllllIlllIllllIlIIlIl.settings.getDefaultGroup();
        llllllllllllllllIlllIllllIlIIlIl.rotate = llllllllllllllllIlllIllllIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Makes you rotate when placing.").defaultValue(true).build());
        llllllllllllllllIlllIllllIlIIlIl.swing = llllllllllllllllIlllIllllIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Swings your hand when placing.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIlllIllllIlIIIIl) {
        AntiAnchor llllllllllllllllIlllIllllIlIIIlI;
        if (llllllllllllllllIlllIllllIlIIIlI.mc.field_1687.method_8320(llllllllllllllllIlllIllllIlIIIlI.mc.field_1724.method_24515().method_10086(2)).method_26204() == class_2246.field_23152 && llllllllllllllllIlllIllllIlIIIlI.mc.field_1687.method_8320(llllllllllllllllIlllIllllIlIIIlI.mc.field_1724.method_24515().method_10084()).method_26204() == class_2246.field_10124) {
            BlockUtils.place(llllllllllllllllIlllIllllIlIIIlI.mc.field_1724.method_24515().method_10069(0, 1, 0), InvUtils.findInHotbar(llllllllllllllllIlllIllllIIlllIl -> class_2248.method_9503((class_1792)llllllllllllllllIlllIllllIIlllIl.method_7909()) instanceof class_2482), llllllllllllllllIlllIllllIlIIIlI.rotate.get(), 15, llllllllllllllllIlllIllllIlIIIlI.swing.get(), false, true);
        }
    }
}

