/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
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
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2482;

public class AntiAnchor
extends Module {
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> swing;
    private final SettingGroup sgGeneral;

    public AntiAnchor() {
        super(Categories.Combat, "anti-anchor", "Automatically prevents Anchor Aura by placing a slab on your head.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Makes you rotate when placing.").defaultValue(true).build());
        this.swing = this.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Swings your hand when placing.").defaultValue(true).build());
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_2248.method_9503((class_1792)class_17992.method_7909()) instanceof class_2482;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.mc.field_1687.method_8320(this.mc.field_1724.method_24515().method_10086(2)).method_26204() == class_2246.field_23152 && this.mc.field_1687.method_8320(this.mc.field_1724.method_24515().method_10084()).method_26204() == class_2246.field_10124) {
            BlockUtils.place(this.mc.field_1724.method_24515().method_10069(0, 1, 0), InvUtils.findInHotbar(AntiAnchor::lambda$onTick$0), this.rotate.get(), 15, this.swing.get(), false, true);
        }
    }
}

