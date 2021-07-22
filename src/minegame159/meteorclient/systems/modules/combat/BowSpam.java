/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1744
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1657;
import net.minecraft.class_1744;
import net.minecraft.class_1802;

public class BowSpam
extends Module {
    private /* synthetic */ boolean wasHoldingRightClick;
    private /* synthetic */ boolean wasBow;
    private final /* synthetic */ Setting<Integer> charge;
    private final /* synthetic */ Setting<Boolean> onlyWhenHoldingRightClick;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void onActivate() {
        lIIllIlIlIllIlI.wasBow = false;
        lIIllIlIlIllIlI.wasHoldingRightClick = false;
    }

    @EventHandler
    private void onTick(TickEvent.Post lIIllIIlllllllI) {
        BowSpam lIIllIIllllllIl;
        if (!lIIllIIllllllIl.mc.field_1724.field_7503.field_7477 && !InvUtils.find(lIIllIIllllIlII -> lIIllIIllllIlII.method_7909() instanceof class_1744).found()) {
            return;
        }
        if (!lIIllIIllllllIl.onlyWhenHoldingRightClick.get().booleanValue() || lIIllIIllllllIl.mc.field_1690.field_1904.method_1434()) {
            boolean lIIllIlIIIIIIII;
            boolean bl = lIIllIlIIIIIIII = lIIllIIllllllIl.mc.field_1724.method_6047().method_7909() == class_1802.field_8102;
            if (!lIIllIlIIIIIIII && lIIllIIllllllIl.wasBow) {
                lIIllIIllllllIl.setPressed(false);
            }
            lIIllIIllllllIl.wasBow = lIIllIlIIIIIIII;
            if (!lIIllIlIIIIIIII) {
                return;
            }
            if (lIIllIIllllllIl.mc.field_1724.method_6048() >= lIIllIIllllllIl.charge.get()) {
                lIIllIIllllllIl.mc.field_1724.method_6075();
                lIIllIIllllllIl.mc.field_1761.method_2897((class_1657)lIIllIIllllllIl.mc.field_1724);
            } else {
                lIIllIIllllllIl.setPressed(true);
            }
            lIIllIIllllllIl.wasHoldingRightClick = lIIllIIllllllIl.mc.field_1690.field_1904.method_1434();
        } else if (lIIllIIllllllIl.wasHoldingRightClick) {
            lIIllIIllllllIl.setPressed(false);
            lIIllIIllllllIl.wasHoldingRightClick = false;
        }
    }

    @Override
    public void onDeactivate() {
        BowSpam lIIllIlIlIlIllI;
        lIIllIlIlIlIllI.setPressed(false);
    }

    public BowSpam() {
        super(Categories.Combat, "bow-spam", "Spams arrows.");
        BowSpam lIIllIlIlIlllIl;
        lIIllIlIlIlllIl.sgGeneral = lIIllIlIlIlllIl.settings.getDefaultGroup();
        lIIllIlIlIlllIl.charge = lIIllIlIlIlllIl.sgGeneral.add(new IntSetting.Builder().name("charge").description("How long to charge the bow before releasing in ticks.").defaultValue(5).min(5).max(20).sliderMin(5).sliderMax(20).build());
        lIIllIlIlIlllIl.onlyWhenHoldingRightClick = lIIllIlIlIlllIl.sgGeneral.add(new BoolSetting.Builder().name("when-holding-right-click").description("Works only when holding right click.").defaultValue(false).build());
        lIIllIlIlIlllIl.wasBow = false;
        lIIllIlIlIlllIl.wasHoldingRightClick = false;
    }

    private void setPressed(boolean lIIllIIllllIllI) {
        BowSpam lIIllIIllllIlll;
        lIIllIIllllIlll.mc.field_1690.field_1904.method_23481(lIIllIIllllIllI);
    }
}

