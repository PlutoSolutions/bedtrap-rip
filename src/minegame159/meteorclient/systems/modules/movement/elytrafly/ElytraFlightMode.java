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
    protected /* synthetic */ double velZ;
    private final /* synthetic */ ElytraFlightModes type;
    protected /* synthetic */ int jumpTimer;
    protected /* synthetic */ class_243 forward;
    protected /* synthetic */ double velX;
    protected final /* synthetic */ class_310 mc;
    protected /* synthetic */ boolean lastJumpPressed;
    protected /* synthetic */ class_243 right;
    protected final /* synthetic */ ElytraFly settings;
    protected /* synthetic */ boolean lastForwardPressed;
    protected /* synthetic */ double ticksLeft;
    protected /* synthetic */ boolean incrementJumpTimer;
    protected /* synthetic */ double velY;

    public void handleAutopilot() {
        ElytraFlightMode lIlIIIllIIllll;
        if (!lIlIIIllIIllll.mc.field_1724.method_6128()) {
            return;
        }
        if (lIlIIIllIIllll.settings.moveForward.get().booleanValue() && lIlIIIllIIllll.mc.field_1724.method_23318() > lIlIIIllIIllll.settings.autoPilotMinimumHeight.get()) {
            lIlIIIllIIllll.mc.field_1690.field_1894.method_23481(true);
            lIlIIIllIIllll.lastForwardPressed = true;
        }
        if (lIlIIIllIIllll.settings.useFireworks.get().booleanValue()) {
            if (lIlIIIllIIllll.ticksLeft <= 0.0) {
                lIlIIIllIIllll.ticksLeft = lIlIIIllIIllll.settings.autoPilotFireworkDelay.get() * 20.0;
                FindItemResult lIlIIIllIlIIII = InvUtils.findInHotbar(class_1802.field_8639);
                if (!lIlIIIllIlIIII.found()) {
                    return;
                }
                if (lIlIIIllIlIIII.isOffhand()) {
                    lIlIIIllIIllll.mc.field_1761.method_2919((class_1657)lIlIIIllIIllll.mc.field_1724, (class_1937)lIlIIIllIIllll.mc.field_1687, class_1268.field_5810);
                    lIlIIIllIIllll.mc.field_1724.method_6104(class_1268.field_5810);
                } else {
                    int lIlIIIllIlIIIl = lIlIIIllIIllll.mc.field_1724.field_7514.field_7545;
                    InvUtils.swap(lIlIIIllIlIIII.getSlot());
                    lIlIIIllIIllll.mc.field_1761.method_2919((class_1657)lIlIIIllIIllll.mc.field_1724, (class_1937)lIlIIIllIIllll.mc.field_1687, class_1268.field_5808);
                    lIlIIIllIIllll.mc.field_1724.method_6104(class_1268.field_5808);
                    InvUtils.swap(lIlIIIllIlIIIl);
                }
            }
            lIlIIIllIIllll.ticksLeft -= 1.0;
        }
    }

    public void onPacketSend(PacketEvent.Send lIlIIIlllIIIII) {
    }

    public void handleHorizontalSpeed() {
        ElytraFlightMode lIlIIIllIIIllI;
        boolean lIlIIIllIIIlIl = false;
        boolean lIlIIIllIIIlII = false;
        if (lIlIIIllIIIllI.mc.field_1690.field_1894.method_1434()) {
            lIlIIIllIIIllI.velX += lIlIIIllIIIllI.forward.field_1352 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIllI.velZ += lIlIIIllIIIllI.forward.field_1350 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIlIl = true;
        } else if (lIlIIIllIIIllI.mc.field_1690.field_1881.method_1434()) {
            lIlIIIllIIIllI.velX -= lIlIIIllIIIllI.forward.field_1352 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIllI.velZ -= lIlIIIllIIIllI.forward.field_1350 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIlIl = true;
        }
        if (lIlIIIllIIIllI.mc.field_1690.field_1849.method_1434()) {
            lIlIIIllIIIllI.velX += lIlIIIllIIIllI.right.field_1352 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIllI.velZ += lIlIIIllIIIllI.right.field_1350 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIlII = true;
        } else if (lIlIIIllIIIllI.mc.field_1690.field_1913.method_1434()) {
            lIlIIIllIIIllI.velX -= lIlIIIllIIIllI.right.field_1352 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIllI.velZ -= lIlIIIllIIIllI.right.field_1350 * lIlIIIllIIIllI.settings.horizontalSpeed.get() * 10.0;
            lIlIIIllIIIlII = true;
        }
        if (lIlIIIllIIIlIl && lIlIIIllIIIlII) {
            double lIlIIIllIIIlll = 1.0 / Math.sqrt(2.0);
            lIlIIIllIIIllI.velX *= lIlIIIllIIIlll;
            lIlIIIllIIIllI.velZ *= lIlIIIllIIIlll;
        }
    }

    public void onActivate() {
        lIlIIIllIlllII.lastJumpPressed = false;
        lIlIIIllIlllII.jumpTimer = 0;
        lIlIIIllIlllII.ticksLeft = 0.0;
    }

    public void handleFallMultiplier() {
        ElytraFlightMode lIlIIIlIlllIll;
        if (lIlIIIlIlllIll.velY < 0.0) {
            lIlIIIlIlllIll.velY *= lIlIIIlIlllIll.settings.fallMultiplier.get().doubleValue();
        } else if (lIlIIIlIlllIll.velY > 0.0) {
            lIlIIIlIlllIll.velY = 0.0;
        }
    }

    public String getHudString() {
        ElytraFlightMode lIlIIIlIlllIII;
        return lIlIIIlIlllIII.type.name();
    }

    public void onDeactivate() {
    }

    public void onTick() {
        class_1799 lIlIIIlllIIllI;
        ElytraFlightMode lIlIIIlllIIlII;
        if (lIlIIIlllIIlII.settings.replace.get().booleanValue() && (lIlIIIlllIIllI = lIlIIIlllIIlII.mc.field_1724.field_7514.method_7372(2)).method_7909() == class_1802.field_8833 && lIlIIIlllIIllI.method_7936() - lIlIIIlllIIllI.method_7919() <= lIlIIIlllIIlII.settings.replaceDurability.get()) {
            FindItemResult lIlIIIlllIIlll = InvUtils.find(lIlIIIlIllIIll -> {
                ElytraFlightMode lIlIIIlIllIlII;
                return lIlIIIlIllIIll.method_7936() - lIlIIIlIllIIll.method_7919() > lIlIIIlIllIlII.settings.replaceDurability.get() && lIlIIIlIllIIll.method_7909() == class_1802.field_8833;
            });
            InvUtils.move().from(lIlIIIlllIIlll.getSlot()).toArmor(2);
        }
    }

    public void autoTakeoff() {
        ElytraFlightMode lIlIIIllIllIII;
        if (lIlIIIllIllIII.incrementJumpTimer) {
            ++lIlIIIllIllIII.jumpTimer;
        }
        boolean lIlIIIllIlIlll = lIlIIIllIllIII.mc.field_1690.field_1903.method_1434();
        if (lIlIIIllIllIII.settings.autoTakeOff.get().booleanValue() && lIlIIIllIlIlll) {
            if (!lIlIIIllIllIII.lastJumpPressed && !lIlIIIllIllIII.mc.field_1724.method_6128()) {
                lIlIIIllIllIII.jumpTimer = 0;
                lIlIIIllIllIII.incrementJumpTimer = true;
            }
            if (lIlIIIllIllIII.jumpTimer >= 8) {
                lIlIIIllIllIII.jumpTimer = 0;
                lIlIIIllIllIII.incrementJumpTimer = false;
                lIlIIIllIllIII.mc.field_1724.method_6100(false);
                lIlIIIllIllIII.mc.field_1724.method_5728(true);
                lIlIIIllIllIII.mc.field_1724.method_6043();
                lIlIIIllIllIII.mc.method_1562().method_2883((class_2596)new class_2848((class_1297)lIlIIIllIllIII.mc.field_1724, class_2848.class_2849.field_12982));
            }
        }
        lIlIIIllIllIII.lastJumpPressed = lIlIIIllIlIlll;
    }

    public ElytraFlightMode(ElytraFlightModes lIlIIIlllIllIl) {
        ElytraFlightMode lIlIIIlllIllII;
        lIlIIIlllIllII.settings = Modules.get().get(ElytraFly.class);
        lIlIIIlllIllII.mc = class_310.method_1551();
        lIlIIIlllIllII.type = lIlIIIlllIllIl;
    }

    public void handleVerticalSpeed() {
        ElytraFlightMode lIlIIIlIllllIl;
        if (lIlIIIlIllllIl.mc.field_1690.field_1903.method_1434()) {
            lIlIIIlIllllIl.velY += 0.5 * lIlIIIlIllllIl.settings.verticalSpeed.get();
        } else if (lIlIIIlIllllIl.mc.field_1690.field_1832.method_1434()) {
            lIlIIIlIllllIl.velY -= 0.5 * lIlIIIlIllllIl.settings.verticalSpeed.get();
        }
    }

    public void onPlayerMove() {
    }
}

