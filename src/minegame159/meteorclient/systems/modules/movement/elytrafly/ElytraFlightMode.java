/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules.movement.elytrafly;

import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFlightModes;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFly;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_310;

public class ElytraFlightMode {
    protected double velZ;
    private final ElytraFlightModes type;
    protected int jumpTimer;
    protected class_243 forward;
    protected double velX;
    protected final class_310 mc;
    protected boolean lastJumpPressed;
    protected class_243 right;
    protected final ElytraFly settings = Modules.get().get(ElytraFly.class);
    protected boolean lastForwardPressed;
    protected double ticksLeft;
    protected boolean incrementJumpTimer;
    protected double velY;

    public void handleAutopilot() {
        if (!this.mc.field_1724.method_6128()) {
            return;
        }
        if (this.settings.moveForward.get().booleanValue() && this.mc.field_1724.method_23318() > this.settings.autoPilotMinimumHeight.get()) {
            this.mc.field_1690.field_1894.method_23481(true);
            this.lastForwardPressed = true;
        }
        if (this.settings.useFireworks.get().booleanValue()) {
            if (this.ticksLeft <= 0.0) {
                this.ticksLeft = this.settings.autoPilotFireworkDelay.get() * 20.0;
                FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8639);
                if (!findItemResult.found()) {
                    return;
                }
                if (findItemResult.isOffhand()) {
                    this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5810);
                    this.mc.field_1724.method_6104(class_1268.field_5810);
                } else {
                    int n = this.mc.field_1724.field_7514.field_7545;
                    InvUtils.swap(findItemResult.getSlot());
                    this.mc.field_1761.method_2919((class_1657)this.mc.field_1724, (class_1937)this.mc.field_1687, class_1268.field_5808);
                    this.mc.field_1724.method_6104(class_1268.field_5808);
                    InvUtils.swap(n);
                }
            }
            this.ticksLeft -= 1.0;
        }
    }

    public void onPacketSend(PacketEvent.Send send) {
    }

    public void handleHorizontalSpeed() {
        boolean bl = false;
        boolean bl2 = false;
        if (this.mc.field_1690.field_1894.method_1434()) {
            this.velX += this.forward.field_1352 * this.settings.horizontalSpeed.get() * 10.0;
            this.velZ += this.forward.field_1350 * this.settings.horizontalSpeed.get() * 10.0;
            bl = true;
        } else if (this.mc.field_1690.field_1881.method_1434()) {
            this.velX -= this.forward.field_1352 * this.settings.horizontalSpeed.get() * 10.0;
            this.velZ -= this.forward.field_1350 * this.settings.horizontalSpeed.get() * 10.0;
            bl = true;
        }
        if (this.mc.field_1690.field_1849.method_1434()) {
            this.velX += this.right.field_1352 * this.settings.horizontalSpeed.get() * 10.0;
            this.velZ += this.right.field_1350 * this.settings.horizontalSpeed.get() * 10.0;
            bl2 = true;
        } else if (this.mc.field_1690.field_1913.method_1434()) {
            this.velX -= this.right.field_1352 * this.settings.horizontalSpeed.get() * 10.0;
            this.velZ -= this.right.field_1350 * this.settings.horizontalSpeed.get() * 10.0;
            bl2 = true;
        }
        if (bl && bl2) {
            double d = 1.0 / Math.sqrt(2.0);
            this.velX *= d;
            this.velZ *= d;
        }
    }

    public void onActivate() {
        this.lastJumpPressed = false;
        this.jumpTimer = 0;
        this.ticksLeft = 0.0;
    }

    private boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7936() - class_17992.method_7919() > this.settings.replaceDurability.get() && class_17992.method_7909() == class_1802.field_8833;
    }

    public void handleFallMultiplier() {
        if (this.velY < 0.0) {
            this.velY *= this.settings.fallMultiplier.get().doubleValue();
        } else if (this.velY > 0.0) {
            this.velY = 0.0;
        }
    }

    public String getHudString() {
        return this.type.name();
    }

    public void onDeactivate() {
    }

    public void onTick() {
        class_1799 class_17992;
        if (this.settings.replace.get().booleanValue() && (class_17992 = this.mc.field_1724.field_7514.method_7372(2)).method_7909() == class_1802.field_8833 && class_17992.method_7936() - class_17992.method_7919() <= this.settings.replaceDurability.get()) {
            FindItemResult findItemResult = InvUtils.find(this::lambda$onTick$0);
            InvUtils.move().from(findItemResult.getSlot()).toArmor(2);
        }
    }

    public void autoTakeoff() {
        if (this.incrementJumpTimer) {
            ++this.jumpTimer;
        }
        boolean bl = this.mc.field_1690.field_1903.method_1434();
        if (this.settings.autoTakeOff.get().booleanValue() && bl) {
            if (!this.lastJumpPressed && !this.mc.field_1724.method_6128()) {
                this.jumpTimer = 0;
                this.incrementJumpTimer = true;
            }
            if (this.jumpTimer >= 8) {
                this.jumpTimer = 0;
                this.incrementJumpTimer = false;
                this.mc.field_1724.method_6100(false);
                this.mc.field_1724.method_5728(true);
                this.mc.field_1724.method_6043();
                this.mc.method_1562().method_2883((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12982));
            }
        }
        this.lastJumpPressed = bl;
    }

    public ElytraFlightMode(ElytraFlightModes elytraFlightModes) {
        this.mc = class_310.method_1551();
        this.type = elytraFlightModes;
    }

    public void handleVerticalSpeed() {
        if (this.mc.field_1690.field_1903.method_1434()) {
            this.velY += 0.5 * this.settings.verticalSpeed.get();
        } else if (this.mc.field_1690.field_1832.method_1434()) {
            this.velY -= 0.5 * this.settings.verticalSpeed.get();
        }
    }

    public void onPlayerMove() {
    }
}

