/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
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
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ SettingGroup sgGeneral;

    public AntiAnvil() {
        super(Categories.Combat, "anti-anvil", "Automatically prevents Auto Anvil by placing between you and the anvil.");
        AntiAnvil lIlIIIllIIlIlIl;
        lIlIIIllIIlIlIl.sgGeneral = lIlIIIllIIlIlIl.settings.getDefaultGroup();
        lIlIIIllIIlIlIl.swing = lIlIIIllIIlIlIl.sgGeneral.add(new BoolSetting.Builder().name("swing").description("Swings your hand client-side when placing.").defaultValue(true).build());
        lIlIIIllIIlIlIl.rotate = lIlIIIllIIlIlIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Makes you rotate when placing.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIlIIIllIIIllIl) {
        AntiAnvil lIlIIIllIIIllII;
        int lIlIIIllIIIllll = 0;
        while ((float)lIlIIIllIIIllll <= lIlIIIllIIIllII.mc.field_1761.method_2904()) {
            class_2338 lIlIIIllIIlIIII = lIlIIIllIIIllII.mc.field_1724.method_24515().method_10069(0, lIlIIIllIIIllll + 3, 0);
            if (lIlIIIllIIIllII.mc.field_1687.method_8320(lIlIIIllIIlIIII).method_26204() == class_2246.field_10535 && lIlIIIllIIIllII.mc.field_1687.method_8320(lIlIIIllIIlIIII.method_10074()).method_26215()) {
                if (BlockUtils.place(lIlIIIllIIlIIII.method_10074(), InvUtils.findInHotbar(class_1802.field_8281), lIlIIIllIIIllII.rotate.get(), 15, lIlIIIllIIIllII.swing.get(), true)) break;
            }
            ++lIlIIIllIIIllll;
        }
    }
}

