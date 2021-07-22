/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_5498
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.TookDamageEvent;
import minegame159.meteorclient.events.game.GameLeftEvent;
import minegame159.meteorclient.events.game.OpenScreenEvent;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.world.ChunkOcclusionEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.misc.input.Input;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_5498;

public class Freecam
extends Module {
    private boolean left;
    private final Setting<Double> speed;
    public float yaw;
    private boolean forward;
    private final Setting<Boolean> autoDisableOnDamage;
    public final Vec3 pos;
    public float prevYaw;
    private boolean backward;
    public final Vec3 prevPos;
    public float prevPitch;
    private final Setting<Boolean> renderHands;
    private final Setting<Boolean> autoDisableOnDeath;
    private boolean right;
    private boolean up;
    public float pitch;
    private final SettingGroup sgGeneral;
    private class_5498 perspective;
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> autoDisableOnLog;
    private boolean down;
    private final Setting<Boolean> reloadChunks;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1719.method_5757()) {
            this.mc.method_1560().field_5960 = true;
        }
        if (!this.perspective.method_31034()) {
            this.mc.field_1690.method_31043(class_5498.field_26664);
        }
        if (this.mc.field_1755 != null) {
            return;
        }
        class_243 class_2432 = class_243.method_1030((float)0.0f, (float)this.yaw);
        class_243 class_2433 = class_243.method_1030((float)0.0f, (float)(this.yaw + 90.0f));
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        if (this.rotate.get().booleanValue()) {
            class_2338 class_23382;
            if (this.mc.field_1765 instanceof class_3966) {
                class_23382 = ((class_3966)this.mc.field_1765).method_17782().method_24515();
                Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 0, null);
            } else {
                class_243 class_2434 = this.mc.field_1765.method_17784();
                class_23382 = ((class_3965)this.mc.field_1765).method_17777();
                if (!this.mc.field_1687.method_8320(class_23382).method_26215()) {
                    Rotations.rotate(Rotations.getYaw(class_2434), Rotations.getPitch(class_2434), 0, null);
                }
            }
        }
        double d4 = 0.5;
        if (this.mc.field_1690.field_1867.method_1434()) {
            d4 = 1.0;
        }
        boolean bl = false;
        if (this.forward) {
            d += class_2432.field_1352 * d4 * this.speed.get();
            d3 += class_2432.field_1350 * d4 * this.speed.get();
            bl = true;
        }
        if (this.backward) {
            d -= class_2432.field_1352 * d4 * this.speed.get();
            d3 -= class_2432.field_1350 * d4 * this.speed.get();
            bl = true;
        }
        boolean bl2 = false;
        if (this.right) {
            d += class_2433.field_1352 * d4 * this.speed.get();
            d3 += class_2433.field_1350 * d4 * this.speed.get();
            bl2 = true;
        }
        if (this.left) {
            d -= class_2433.field_1352 * d4 * this.speed.get();
            d3 -= class_2433.field_1350 * d4 * this.speed.get();
            bl2 = true;
        }
        if (bl && bl2) {
            double d5 = 1.0 / Math.sqrt(2.0);
            d *= d5;
            d3 *= d5;
        }
        if (this.up) {
            d2 += d4 * this.speed.get();
        }
        if (this.down) {
            d2 -= d4 * this.speed.get();
        }
        this.prevPos.set(this.pos);
        this.pos.set(this.pos.x + d, this.pos.y + d2, this.pos.z + d3);
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent openScreenEvent) {
        this.unpress();
        this.prevPos.set(this.pos);
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
    }

    public void changeLookDirection(double d, double d2) {
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
        this.yaw = (float)((double)this.yaw + d);
        this.pitch = (float)((double)this.pitch + d2);
        this.pitch = class_3532.method_15363((float)this.pitch, (float)-90.0f, (float)90.0f);
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent chunkOcclusionEvent) {
        chunkOcclusionEvent.cancel();
    }

    public double getZ(float f) {
        return class_3532.method_16436((double)f, (double)this.prevPos.z, (double)this.pos.z);
    }

    private void unpress() {
        this.mc.field_1690.field_1894.method_23481(false);
        this.mc.field_1690.field_1881.method_23481(false);
        this.mc.field_1690.field_1849.method_23481(false);
        this.mc.field_1690.field_1913.method_23481(false);
        this.mc.field_1690.field_1903.method_23481(false);
        this.mc.field_1690.field_1832.method_23481(false);
    }

    public double getYaw(float f) {
        return class_3532.method_16439((float)f, (float)this.prevYaw, (float)this.yaw);
    }

    public double getX(float f) {
        return class_3532.method_16436((double)f, (double)this.prevPos.x, (double)this.pos.x);
    }

    @EventHandler
    private void onKey(KeyEvent keyEvent) {
        if (Input.isKeyPressed(292)) {
            return;
        }
        boolean bl = true;
        if (this.mc.field_1690.field_1894.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1894.method_1433(keyEvent.key)) {
            this.forward = keyEvent.action != KeyAction.Release;
        } else if (this.mc.field_1690.field_1881.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1881.method_1433(keyEvent.key)) {
            this.backward = keyEvent.action != KeyAction.Release;
        } else if (this.mc.field_1690.field_1849.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1849.method_1433(keyEvent.key)) {
            this.right = keyEvent.action != KeyAction.Release;
        } else if (this.mc.field_1690.field_1913.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1913.method_1433(keyEvent.key)) {
            this.left = keyEvent.action != KeyAction.Release;
        } else if (this.mc.field_1690.field_1903.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1903.method_1433(keyEvent.key)) {
            this.up = keyEvent.action != KeyAction.Release;
        } else if (this.mc.field_1690.field_1832.method_1417(keyEvent.key, 0) || this.mc.field_1690.field_1832.method_1433(keyEvent.key)) {
            this.down = keyEvent.action != KeyAction.Release;
        } else {
            bl = false;
        }
        if (bl) {
            keyEvent.cancel();
        }
    }

    @EventHandler
    private void onTookDamage(TookDamageEvent tookDamageEvent) {
        if (tookDamageEvent.entity.method_5667() == null) {
            return;
        }
        if (!tookDamageEvent.entity.method_5667().equals(this.mc.field_1724.method_5667())) {
            return;
        }
        if (this.autoDisableOnDamage.get().booleanValue() || this.autoDisableOnDeath.get().booleanValue() && tookDamageEvent.entity.method_6032() <= 0.0f) {
            this.toggle();
            this.info("Auto toggled because you took damage or died.", new Object[0]);
        }
    }

    public double getPitch(float f) {
        return class_3532.method_16439((float)f, (float)this.prevPitch, (float)this.pitch);
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent gameLeftEvent) {
        if (!this.autoDisableOnLog.get().booleanValue()) {
            return;
        }
        this.toggle();
    }

    public Freecam() {
        super(Categories.Render, "freecam", "Allows the camera to move away from the player.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.speed = this.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Your speed while in freecam.").defaultValue(1.0).min(0.0).build());
        this.autoDisableOnDamage = this.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-damage").description("Disables freecam when you take damage.").defaultValue(false).build());
        this.autoDisableOnDeath = this.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-death").description("Disables freecam when you die.").defaultValue(false).build());
        this.autoDisableOnLog = this.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-log").description("Disables freecam when you disconnect from a server.").defaultValue(true).build());
        this.reloadChunks = this.sgGeneral.add(new BoolSetting.Builder().name("reload-chunks").description("Disables cave culling.").defaultValue(true).build());
        this.renderHands = this.sgGeneral.add(new BoolSetting.Builder().name("show-hands").description("Whether or not to render your hands in greecam.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates to the block or entity you are looking at.").defaultValue(false).build());
        this.pos = new Vec3();
        this.prevPos = new Vec3();
    }

    public double getY(float f) {
        return class_3532.method_16436((double)f, (double)this.prevPos.y, (double)this.pos.y);
    }

    public boolean renderHands() {
        return !this.isActive() || this.renderHands.get() != false;
    }

    @Override
    public void onActivate() {
        this.yaw = this.mc.field_1724.field_6031;
        this.pitch = this.mc.field_1724.field_5965;
        this.perspective = this.mc.field_1690.method_31044();
        this.pos.set(this.mc.field_1773.method_19418().method_19326());
        this.prevPos.set(this.mc.field_1773.method_19418().method_19326());
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
        this.forward = false;
        this.backward = false;
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
        this.unpress();
        if (this.reloadChunks.get().booleanValue()) {
            this.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onDeactivate() {
        if (this.reloadChunks.get().booleanValue()) {
            this.mc.field_1769.method_3279();
        }
        this.mc.field_1690.method_31043(this.perspective);
    }
}

