/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_1893
 *  net.minecraft.class_1937
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_1893;
import net.minecraft.class_1937;

public class EXPThrower
extends Module {
    private final Setting<Boolean> autoToggle;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> lookDown;

    private void lambda$onTick$0(FindItemResult findItemResult) {
        this.throwExp(findItemResult);
    }

    private void throwExp(FindItemResult findItemResult) {
        if (findItemResult.isOffhand()) {
            this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5810);
        } else {
            int n = this.mc.field_1724.field_7514.field_7545;
            InvUtils.swap(findItemResult.getSlot());
            this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5808);
            InvUtils.swap(n);
        }
    }

    public EXPThrower() {
        super(Categories.Player, "exp-thrower", "Automatically throws XP bottles in your hotbar.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.lookDown = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Forces you to rotate downwards when throwing bottles.").defaultValue(true).build());
        this.autoToggle = this.sgGeneral.add(new BoolSetting.Builder().name("auto-toggle").description("Toggles off when your armor is repaired.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        Object object;
        if (this.autoToggle.get().booleanValue()) {
            object = this.mc.field_1724.field_7514.field_7548.iterator();
            while (object.hasNext()) {
                class_1799 class_17992 = (class_1799)object.next();
                if (class_17992.method_7960() || class_1890.method_8225((class_1887)class_1893.field_9101, (class_1799)class_17992) < 1 || class_17992.method_7986()) continue;
                this.toggle();
                return;
            }
        }
        if (((FindItemResult)(object = InvUtils.findInHotbar(class_1802.field_8287))).found()) {
            if (this.lookDown.get().booleanValue()) {
                Rotations.rotate(this.mc.field_1724.field_6031, 90.0, () -> this.lambda$onTick$0((FindItemResult)object));
            } else {
                this.throwExp((FindItemResult)object);
            }
        }
    }
}

