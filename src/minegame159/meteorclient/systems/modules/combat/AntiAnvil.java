/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;

public class AntiAnvil
extends Module {
    private final Setting<Boolean> swing;
    private final Setting<Boolean> rotate;
    private final SettingGroup sgGeneral;

    public AntiAnvil() {
        super(Categories.Combat, "anti-anvil", "Automatically prevents Auto Anvil by placing between you and the anvil.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.swing = this.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Swings your hand client-side when placing.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Makes you rotate when placing.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        class_2338 class_23382;
        int n = 0;
        while (!(!((float)n <= this.mc.field_1761.method_2904()) || this.mc.field_1687.method_8320(class_23382 = this.mc.field_1724.method_24515().method_10069(0, n + 3, 0)).method_26204() == class_2246.field_10535 && this.mc.field_1687.method_8320(class_23382.method_10074()).method_26215() && BlockUtils.place(class_23382.method_10074(), InvUtils.findInHotbar(class_1802.field_8281), this.rotate.get(), 15, this.swing.get(), true))) {
            ++n;
            if (-1 < 3) continue;
            return;
        }
    }
}

