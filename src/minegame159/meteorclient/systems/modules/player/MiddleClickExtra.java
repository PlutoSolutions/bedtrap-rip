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
    private /* synthetic */ boolean isUsing;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int preSlot;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> notify;

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIIIIIllIIIIlllI) {
        MiddleClickExtra lllllllllllllllllIIIIIllIIIIllIl;
        if (lllllllllllllllllIIIIIllIIIIllIl.isUsing) {
            boolean lllllllllllllllllIIIIIllIIIlIIII = true;
            if (lllllllllllllllllIIIIIllIIIIllIl.mc.field_1724.method_6047().method_7909() instanceof class_1753) {
                lllllllllllllllllIIIIIllIIIlIIII = class_1753.method_7722((int)lllllllllllllllllIIIIIllIIIIllIl.mc.field_1724.method_6048()) < 1.0f;
            }
            lllllllllllllllllIIIIIllIIIIllIl.mc.field_1690.field_1904.method_23481(lllllllllllllllllIIIIIllIIIlIIII);
        }
    }

    @Override
    public void onDeactivate() {
        MiddleClickExtra lllllllllllllllllIIIIIllIIllIIII;
        lllllllllllllllllIIIIIllIIllIIII.stopIfUsing();
    }

    @EventHandler
    private void onStoppedUsingItem(StoppedUsingItemEvent lllllllllllllllllIIIIIllIIIIIlIl) {
        MiddleClickExtra lllllllllllllllllIIIIIllIIIIIllI;
        lllllllllllllllllIIIIIllIIIIIllI.stopIfUsing();
    }

    @EventHandler
    private void onMouseButton(MouseButtonEvent lllllllllllllllllIIIIIllIIIlIlII) {
        MiddleClickExtra lllllllllllllllllIIIIIllIIIllIII;
        if (lllllllllllllllllIIIIIllIIIlIlII.action != KeyAction.Press || lllllllllllllllllIIIIIllIIIlIlII.button != 2) {
            return;
        }
        FindItemResult lllllllllllllllllIIIIIllIIIlIllI = InvUtils.findInHotbar(lllllllllllllllllIIIIIllIIIllIII.mode.get().item);
        if (!lllllllllllllllllIIIIIllIIIlIllI.found()) {
            if (lllllllllllllllllIIIIIllIIIllIII.notify.get().booleanValue()) {
                lllllllllllllllllIIIIIllIIIllIII.warning("Unable to find specified item.", new Object[0]);
            }
            return;
        }
        lllllllllllllllllIIIIIllIIIllIII.preSlot = lllllllllllllllllIIIIIllIIIllIII.mc.field_1724.field_7514.field_7545;
        InvUtils.swap(lllllllllllllllllIIIIIllIIIlIllI.getSlot());
        switch (lllllllllllllllllIIIIIllIIIllIII.mode.get().type) {
            case Immediate: {
                lllllllllllllllllIIIIIllIIIllIII.mc.field_1761.method_2919((class_1657)lllllllllllllllllIIIIIllIIIllIII.mc.field_1724, (class_1937)lllllllllllllllllIIIIIllIIIllIII.mc.field_1687, class_1268.field_5808);
                InvUtils.swap(lllllllllllllllllIIIIIllIIIllIII.preSlot);
                break;
            }
            case LongerSingleClick: {
                lllllllllllllllllIIIIIllIIIllIII.mc.field_1761.method_2919((class_1657)lllllllllllllllllIIIIIllIIIllIII.mc.field_1724, (class_1937)lllllllllllllllllIIIIIllIIIllIII.mc.field_1687, class_1268.field_5808);
                break;
            }
            case Longer: {
                lllllllllllllllllIIIIIllIIIllIII.mc.field_1690.field_1904.method_23481(true);
                lllllllllllllllllIIIIIllIIIllIII.isUsing = true;
            }
        }
    }

    @EventHandler
    private void onFinishUsingItem(FinishUsingItem lllllllllllllllllIIIIIllIIIIlIIl) {
        MiddleClickExtra lllllllllllllllllIIIIIllIIIIlIlI;
        lllllllllllllllllIIIIIllIIIIlIlI.stopIfUsing();
    }

    public MiddleClickExtra() {
        super(Categories.Player, "middle-click-extra", "Lets you use items when you middle click.");
        MiddleClickExtra lllllllllllllllllIIIIIllIIlllIlI;
        lllllllllllllllllIIIIIllIIlllIlI.sgGeneral = lllllllllllllllllIIIIIllIIlllIlI.settings.getDefaultGroup();
        lllllllllllllllllIIIIIllIIlllIlI.mode = lllllllllllllllllIIIIIllIIlllIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which item to use when you middle click.").defaultValue(Mode.Pearl).build());
        lllllllllllllllllIIIIIllIIlllIlI.notify = lllllllllllllllllIIIIIllIIlllIlI.sgGeneral.add(new BoolSetting.Builder().name("notify").description("Notifies you when you do not have the specified item in your hotbar.").defaultValue(true).build());
    }

    private void stopIfUsing() {
        MiddleClickExtra lllllllllllllllllIIIIIllIIIIIIlI;
        if (lllllllllllllllllIIIIIllIIIIIIlI.isUsing) {
            lllllllllllllllllIIIIIllIIIIIIlI.mc.field_1690.field_1904.method_23481(false);
            InvUtils.swap(lllllllllllllllllIIIIIllIIIIIIlI.preSlot);
            lllllllllllllllllIIIIIllIIIIIIlI.isUsing = false;
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

        private final /* synthetic */ Type type;
        private final /* synthetic */ class_1792 item;

        private Mode(class_1792 llllllllllllllllIllIlIllllllIIlI, Type llllllllllllllllIllIlIllllllIIIl) {
            Mode llllllllllllllllIllIlIllllllIIII;
            llllllllllllllllIllIlIllllllIIII.item = llllllllllllllllIllIlIllllllIIlI;
            llllllllllllllllIllIlIllllllIIII.type = llllllllllllllllIllIlIllllllIIIl;
        }
    }

    private static enum Type {
        Immediate,
        LongerSingleClick,
        Longer;


        private Type() {
            Type llllllllllllllllIlIlIlllIIIIllIl;
        }
    }
}

