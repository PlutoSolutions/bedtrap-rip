/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_1799;
import net.minecraft.class_1802;

public class BowSpam
extends Module {
    private boolean wasHoldingRightClick;
    private boolean wasBow;
    private final Setting<Integer> charge;
    private final Setting<Boolean> onlyWhenHoldingRightClick;
    private final SettingGroup sgGeneral;

    @Override
    public void onActivate() {
        this.wasBow = false;
        this.wasHoldingRightClick = false;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (!this.mc.field_1724.field_7503.field_7477 && !InvUtils.find(BowSpam::lambda$onTick$0).found()) {
            return;
        }
        if (!this.onlyWhenHoldingRightClick.get().booleanValue() || this.mc.field_1690.field_1904.method_1434()) {
            boolean bl;
            boolean bl2 = bl = this.mc.field_1724.method_6047().method_7909() == class_1802.field_8102;
            if (!bl && this.wasBow) {
                this.setPressed(false);
            }
            this.wasBow = bl;
            if (!bl) {
                return;
            }
            if (this.mc.field_1724.method_6048() >= this.charge.get()) {
                this.mc.field_1724.method_6075();
                this.mc.field_1761.method_2897((class_1657)this.mc.field_1724);
            } else {
                this.setPressed(true);
            }
            this.wasHoldingRightClick = this.mc.field_1690.field_1904.method_1434();
        } else if (this.wasHoldingRightClick) {
            this.setPressed(false);
            this.wasHoldingRightClick = false;
        }
    }

    @Override
    public void onDeactivate() {
        this.setPressed(false);
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1744;
    }

    public BowSpam() {
        super(Categories.Combat, "bow-spam", "Spams arrows.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.charge = this.sgGeneral.add(new IntSetting.Builder().name("charge").description("How long to charge the bow before releasing in ticks.").defaultValue(5).min(5).max(20).sliderMin(5).sliderMax(20).build());
        this.onlyWhenHoldingRightClick = this.sgGeneral.add(new BoolSetting.Builder().name("when-holding-right-click").description("Works only when holding right click.").defaultValue(false).build());
        this.wasBow = false;
        this.wasHoldingRightClick = false;
    }

    private void setPressed(boolean bl) {
        this.mc.field_1690.field_1904.method_23481(bl);
    }
}

