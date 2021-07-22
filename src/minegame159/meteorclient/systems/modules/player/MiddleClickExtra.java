/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1753
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.FinishUsingItem;
import minegame159.meteorclient.events.entity.player.StoppedUsingItemEvent;
import minegame159.meteorclient.events.meteor.MouseButtonEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1753;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1937;

public class MiddleClickExtra
extends Module {
    private boolean isUsing;
    private final Setting<Mode> mode;
    private int preSlot;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> notify;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.isUsing) {
            boolean bl = true;
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1753) {
                bl = class_1753.method_7722((int)this.mc.field_1724.method_6048()) < 1.0f;
            }
            this.mc.field_1690.field_1904.method_23481(bl);
        }
    }

    @Override
    public void onDeactivate() {
        this.stopIfUsing();
    }

    @EventHandler
    private void onStoppedUsingItem(StoppedUsingItemEvent stoppedUsingItemEvent) {
        this.stopIfUsing();
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent mouseButtonEvent) {
        if (mouseButtonEvent.action != KeyAction.Press || mouseButtonEvent.button != 2) {
            return;
        }
        FindItemResult findItemResult = InvUtils.findInHotbar(Mode.access$000(this.mode.get()));
        if (!findItemResult.found()) {
            if (this.notify.get().booleanValue()) {
                this.warning("Unable to find specified item.", new Object[0]);
            }
            return;
        }
        this.preSlot = this.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(findItemResult.getSlot());
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$player$MiddleClickExtra$Type[Mode.access$100(this.mode.get()).ordinal()]) {
            case 1: {
                this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5808);
                InvUtils.swap(this.preSlot);
                break;
            }
            case 2: {
                this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5808);
                break;
            }
            case 3: {
                this.mc.field_1690.field_1904.method_23481(true);
                this.isUsing = true;
            }
        }
    }

    @EventHandler
    private void onFinishUsingItem(FinishUsingItem finishUsingItem) {
        this.stopIfUsing();
    }

    public MiddleClickExtra() {
        super(Categories.Player, "middle-click-extra", "Lets you use items when you middle click.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which item to use when you middle click.").defaultValue(Mode.Pearl).build());
        this.notify = this.sgGeneral.add(new BoolSetting.Builder().name("notify").description("Notifies you when you do not have the specified item in your hotbar.").defaultValue(true).build());
    }

    private void stopIfUsing() {
        if (this.isUsing) {
            this.mc.field_1690.field_1904.method_23481(false);
            InvUtils.swap(this.preSlot);
            this.isUsing = false;
        }
    }

    public static enum Mode {
        Pearl(class_1802.field_8634, Type.Immediate),
        Rocket(class_1802.field_8639, Type.Immediate),
        Rod(class_1802.field_8378, Type.LongerSingleClick),
        Bow(class_1802.field_8102, Type.Longer),
        Gap(class_1802.field_8463, Type.Longer),
        EGap(class_1802.field_8367, Type.Longer),
        Chorus(class_1802.field_8233, Type.Longer);

        private final Type type;
        private final class_1792 item;

        static class_1792 access$000(Mode mode) {
            return mode.item;
        }

        static Type access$100(Mode mode) {
            return mode.type;
        }

        private Mode(class_1792 class_17922, Type type) {
            this.item = class_17922;
            this.type = type;
        }
    }

    private static enum Type {
        Immediate,
        LongerSingleClick,
        Longer;

    }
}

