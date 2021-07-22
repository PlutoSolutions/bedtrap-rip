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
    private /* synthetic */ boolean left;
    private final /* synthetic */ Setting<Double> speed;
    public /* synthetic */ float yaw;
    private /* synthetic */ boolean forward;
    private final /* synthetic */ Setting<Boolean> autoDisableOnDamage;
    public final /* synthetic */ Vec3 pos;
    public /* synthetic */ float prevYaw;
    private /* synthetic */ boolean backward;
    public final /* synthetic */ Vec3 prevPos;
    public /* synthetic */ float prevPitch;
    private final /* synthetic */ Setting<Boolean> renderHands;
    private final /* synthetic */ Setting<Boolean> autoDisableOnDeath;
    private /* synthetic */ boolean right;
    private /* synthetic */ boolean up;
    public /* synthetic */ float pitch;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ class_5498 perspective;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> autoDisableOnLog;
    private /* synthetic */ boolean down;
    private final /* synthetic */ Setting<Boolean> reloadChunks;

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIlIlIlIIIIIIIIlI) {
        Freecam llllllllllllllllIlIlIIlllllllIIl;
        if (llllllllllllllllIlIlIIlllllllIIl.mc.field_1719.method_5757()) {
            llllllllllllllllIlIlIIlllllllIIl.mc.method_1560().field_5960 = true;
        }
        if (!llllllllllllllllIlIlIIlllllllIIl.perspective.method_31034()) {
            llllllllllllllllIlIlIIlllllllIIl.mc.field_1690.method_31043(class_5498.field_26664);
        }
        if (llllllllllllllllIlIlIIlllllllIIl.mc.field_1755 != null) {
            return;
        }
        class_243 llllllllllllllllIlIlIlIIIIIIIIIl = class_243.method_1030((float)0.0f, (float)llllllllllllllllIlIlIIlllllllIIl.yaw);
        class_243 llllllllllllllllIlIlIlIIIIIIIIII = class_243.method_1030((float)0.0f, (float)(llllllllllllllllIlIlIIlllllllIIl.yaw + 90.0f));
        double llllllllllllllllIlIlIIllllllllll = 0.0;
        double llllllllllllllllIlIlIIlllllllllI = 0.0;
        double llllllllllllllllIlIlIIllllllllIl = 0.0;
        if (llllllllllllllllIlIlIIlllllllIIl.rotate.get().booleanValue()) {
            if (llllllllllllllllIlIlIIlllllllIIl.mc.field_1765 instanceof class_3966) {
                class_2338 llllllllllllllllIlIlIlIIIIIIIlll = ((class_3966)llllllllllllllllIlIlIIlllllllIIl.mc.field_1765).method_17782().method_24515();
                Rotations.rotate(Rotations.getYaw(llllllllllllllllIlIlIlIIIIIIIlll), Rotations.getPitch(llllllllllllllllIlIlIlIIIIIIIlll), 0, null);
            } else {
                class_243 llllllllllllllllIlIlIlIIIIIIIlIl = llllllllllllllllIlIlIIlllllllIIl.mc.field_1765.method_17784();
                class_2338 llllllllllllllllIlIlIlIIIIIIIllI = ((class_3965)llllllllllllllllIlIlIIlllllllIIl.mc.field_1765).method_17777();
                if (!llllllllllllllllIlIlIIlllllllIIl.mc.field_1687.method_8320(llllllllllllllllIlIlIlIIIIIIIllI).method_26215()) {
                    Rotations.rotate(Rotations.getYaw(llllllllllllllllIlIlIlIIIIIIIlIl), Rotations.getPitch(llllllllllllllllIlIlIlIIIIIIIlIl), 0, null);
                }
            }
        }
        double llllllllllllllllIlIlIIllllllllII = 0.5;
        if (llllllllllllllllIlIlIIlllllllIIl.mc.field_1690.field_1867.method_1434()) {
            llllllllllllllllIlIlIIllllllllII = 1.0;
        }
        boolean llllllllllllllllIlIlIIlllllllIll = false;
        if (llllllllllllllllIlIlIIlllllllIIl.forward) {
            llllllllllllllllIlIlIIllllllllll += llllllllllllllllIlIlIlIIIIIIIIIl.field_1352 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIllllllllIl += llllllllllllllllIlIlIlIIIIIIIIIl.field_1350 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIlllllllIll = true;
        }
        if (llllllllllllllllIlIlIIlllllllIIl.backward) {
            llllllllllllllllIlIlIIllllllllll -= llllllllllllllllIlIlIlIIIIIIIIIl.field_1352 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIllllllllIl -= llllllllllllllllIlIlIlIIIIIIIIIl.field_1350 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIlllllllIll = true;
        }
        boolean llllllllllllllllIlIlIIlllllllIlI = false;
        if (llllllllllllllllIlIlIIlllllllIIl.right) {
            llllllllllllllllIlIlIIllllllllll += llllllllllllllllIlIlIlIIIIIIIIII.field_1352 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIllllllllIl += llllllllllllllllIlIlIlIIIIIIIIII.field_1350 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIlllllllIlI = true;
        }
        if (llllllllllllllllIlIlIIlllllllIIl.left) {
            llllllllllllllllIlIlIIllllllllll -= llllllllllllllllIlIlIlIIIIIIIIII.field_1352 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIllllllllIl -= llllllllllllllllIlIlIlIIIIIIIIII.field_1350 * llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
            llllllllllllllllIlIlIIlllllllIlI = true;
        }
        if (llllllllllllllllIlIlIIlllllllIll && llllllllllllllllIlIlIIlllllllIlI) {
            double llllllllllllllllIlIlIlIIIIIIIlII = 1.0 / Math.sqrt(2.0);
            llllllllllllllllIlIlIIllllllllll *= llllllllllllllllIlIlIlIIIIIIIlII;
            llllllllllllllllIlIlIIllllllllIl *= llllllllllllllllIlIlIlIIIIIIIlII;
        }
        if (llllllllllllllllIlIlIIlllllllIIl.up) {
            llllllllllllllllIlIlIIlllllllllI += llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
        }
        if (llllllllllllllllIlIlIIlllllllIIl.down) {
            llllllllllllllllIlIlIIlllllllllI -= llllllllllllllllIlIlIIllllllllII * llllllllllllllllIlIlIIlllllllIIl.speed.get();
        }
        llllllllllllllllIlIlIIlllllllIIl.prevPos.set(llllllllllllllllIlIlIIlllllllIIl.pos);
        llllllllllllllllIlIlIIlllllllIIl.pos.set(llllllllllllllllIlIlIIlllllllIIl.pos.x + llllllllllllllllIlIlIIllllllllll, llllllllllllllllIlIlIIlllllllIIl.pos.y + llllllllllllllllIlIlIIlllllllllI, llllllllllllllllIlIlIIlllllllIIl.pos.z + llllllllllllllllIlIlIIllllllllIl);
    }

    @EventHandler
    private void onOpenScreen(OpenScreenEvent llllllllllllllllIlIlIlIIIIIlIlll) {
        Freecam llllllllllllllllIlIlIlIIIIIlIllI;
        llllllllllllllllIlIlIlIIIIIlIllI.unpress();
        llllllllllllllllIlIlIlIIIIIlIllI.prevPos.set(llllllllllllllllIlIlIlIIIIIlIllI.pos);
        llllllllllllllllIlIlIlIIIIIlIllI.prevYaw = llllllllllllllllIlIlIlIIIIIlIllI.yaw;
        llllllllllllllllIlIlIlIIIIIlIllI.prevPitch = llllllllllllllllIlIlIlIIIIIlIllI.pitch;
    }

    public void changeLookDirection(double llllllllllllllllIlIlIIllllIlIIll, double llllllllllllllllIlIlIIllllIIllll) {
        Freecam llllllllllllllllIlIlIIllllIlIIIl;
        llllllllllllllllIlIlIIllllIlIIIl.prevYaw = llllllllllllllllIlIlIIllllIlIIIl.yaw;
        llllllllllllllllIlIlIIllllIlIIIl.prevPitch = llllllllllllllllIlIlIIllllIlIIIl.pitch;
        llllllllllllllllIlIlIIllllIlIIIl.yaw = (float)((double)llllllllllllllllIlIlIIllllIlIIIl.yaw + llllllllllllllllIlIlIIllllIlIIll);
        llllllllllllllllIlIlIIllllIlIIIl.pitch = (float)((double)llllllllllllllllIlIlIIllllIlIIIl.pitch + llllllllllllllllIlIlIIllllIIllll);
        llllllllllllllllIlIlIIllllIlIIIl.pitch = class_3532.method_15363((float)llllllllllllllllIlIlIIllllIlIIIl.pitch, (float)-90.0f, (float)90.0f);
    }

    @EventHandler
    private void onChunkOcclusion(ChunkOcclusionEvent llllllllllllllllIlIlIIlllllIIIll) {
        llllllllllllllllIlIlIIlllllIIIll.cancel();
    }

    public double getZ(float llllllllllllllllIlIlIIlllIllllII) {
        Freecam llllllllllllllllIlIlIIlllIlllIll;
        return class_3532.method_16436((double)llllllllllllllllIlIlIIlllIllllII, (double)llllllllllllllllIlIlIIlllIlllIll.prevPos.z, (double)llllllllllllllllIlIlIIlllIlllIll.pos.z);
    }

    private void unpress() {
        Freecam llllllllllllllllIlIlIlIIIIIlIlII;
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1894.method_23481(false);
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1881.method_23481(false);
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1849.method_23481(false);
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1913.method_23481(false);
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1903.method_23481(false);
        llllllllllllllllIlIlIlIIIIIlIlII.mc.field_1690.field_1832.method_23481(false);
    }

    public double getYaw(float llllllllllllllllIlIlIIlllIllIllI) {
        Freecam llllllllllllllllIlIlIIlllIllIlIl;
        return class_3532.method_16439((float)llllllllllllllllIlIlIIlllIllIllI, (float)llllllllllllllllIlIlIIlllIllIlIl.prevYaw, (float)llllllllllllllllIlIlIIlllIllIlIl.yaw);
    }

    public double getX(float llllllllllllllllIlIlIIllllIIIllI) {
        Freecam llllllllllllllllIlIlIIllllIIIlll;
        return class_3532.method_16436((double)llllllllllllllllIlIlIIllllIIIllI, (double)llllllllllllllllIlIlIIllllIIIlll.prevPos.x, (double)llllllllllllllllIlIlIIllllIIIlll.pos.x);
    }

    @EventHandler
    private void onKey(KeyEvent llllllllllllllllIlIlIIlllllIlIlI) {
        Freecam llllllllllllllllIlIlIIlllllIlIII;
        if (Input.isKeyPressed(292)) {
            return;
        }
        boolean llllllllllllllllIlIlIIlllllIlIIl = true;
        if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1894.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1894.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.forward = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1881.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1881.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.backward = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1849.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1849.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.right = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1913.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1913.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.left = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1903.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1903.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.up = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else if (llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1832.method_1417(llllllllllllllllIlIlIIlllllIlIlI.key, 0) || llllllllllllllllIlIlIIlllllIlIII.mc.field_1690.field_1832.method_1433(llllllllllllllllIlIlIIlllllIlIlI.key)) {
            llllllllllllllllIlIlIIlllllIlIII.down = llllllllllllllllIlIlIIlllllIlIlI.action != KeyAction.Release;
        } else {
            llllllllllllllllIlIlIIlllllIlIIl = false;
        }
        if (llllllllllllllllIlIlIIlllllIlIIl) {
            llllllllllllllllIlIlIIlllllIlIlI.cancel();
        }
    }

    @EventHandler
    private void onTookDamage(TookDamageEvent llllllllllllllllIlIlIIllllIllllI) {
        Freecam llllllllllllllllIlIlIIllllIlllll;
        if (llllllllllllllllIlIlIIllllIllllI.entity.method_5667() == null) {
            return;
        }
        if (!llllllllllllllllIlIlIIllllIllllI.entity.method_5667().equals(llllllllllllllllIlIlIIllllIlllll.mc.field_1724.method_5667())) {
            return;
        }
        if (llllllllllllllllIlIlIIllllIlllll.autoDisableOnDamage.get().booleanValue() || llllllllllllllllIlIlIIllllIlllll.autoDisableOnDeath.get().booleanValue() && llllllllllllllllIlIlIIllllIllllI.entity.method_6032() <= 0.0f) {
            llllllllllllllllIlIlIIllllIlllll.toggle();
            llllllllllllllllIlIlIIllllIlllll.info("Auto toggled because you took damage or died.", new Object[0]);
        }
    }

    public double getPitch(float llllllllllllllllIlIlIIlllIlIlllI) {
        Freecam llllllllllllllllIlIlIIlllIlIllll;
        return class_3532.method_16439((float)llllllllllllllllIlIlIIlllIlIlllI, (float)llllllllllllllllIlIlIIlllIlIllll.prevPitch, (float)llllllllllllllllIlIlIIlllIlIllll.pitch);
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent llllllllllllllllIlIlIIllllIllIIl) {
        Freecam llllllllllllllllIlIlIIllllIllIII;
        if (!llllllllllllllllIlIlIIllllIllIII.autoDisableOnLog.get().booleanValue()) {
            return;
        }
        llllllllllllllllIlIlIIllllIllIII.toggle();
    }

    public Freecam() {
        super(Categories.Render, "freecam", "Allows the camera to move away from the player.");
        Freecam llllllllllllllllIlIlIlIIIIlIIIII;
        llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral = llllllllllllllllIlIlIlIIIIlIIIII.settings.getDefaultGroup();
        llllllllllllllllIlIlIlIIIIlIIIII.speed = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Your speed while in freecam.").defaultValue(1.0).min(0.0).build());
        llllllllllllllllIlIlIlIIIIlIIIII.autoDisableOnDamage = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-damage").description("Disables freecam when you take damage.").defaultValue(false).build());
        llllllllllllllllIlIlIlIIIIlIIIII.autoDisableOnDeath = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-death").description("Disables freecam when you die.").defaultValue(false).build());
        llllllllllllllllIlIlIlIIIIlIIIII.autoDisableOnLog = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("toggle-on-log").description("Disables freecam when you disconnect from a server.").defaultValue(true).build());
        llllllllllllllllIlIlIlIIIIlIIIII.reloadChunks = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("reload-chunks").description("Disables cave culling.").defaultValue(true).build());
        llllllllllllllllIlIlIlIIIIlIIIII.renderHands = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("show-hands").description("Whether or not to render your hands in greecam.").defaultValue(true).build());
        llllllllllllllllIlIlIlIIIIlIIIII.rotate = llllllllllllllllIlIlIlIIIIlIIIII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates to the block or entity you are looking at.").defaultValue(false).build());
        llllllllllllllllIlIlIlIIIIlIIIII.pos = new Vec3();
        llllllllllllllllIlIlIlIIIIlIIIII.prevPos = new Vec3();
    }

    public double getY(float llllllllllllllllIlIlIIllllIIIIlI) {
        Freecam llllllllllllllllIlIlIIllllIIIIIl;
        return class_3532.method_16436((double)llllllllllllllllIlIlIIllllIIIIlI, (double)llllllllllllllllIlIlIIllllIIIIIl.prevPos.y, (double)llllllllllllllllIlIlIIllllIIIIIl.pos.y);
    }

    public boolean renderHands() {
        Freecam llllllllllllllllIlIlIIllllIIllIl;
        return !llllllllllllllllIlIlIIllllIIllIl.isActive() || llllllllllllllllIlIlIIllllIIllIl.renderHands.get() != false;
    }

    @Override
    public void onActivate() {
        Freecam llllllllllllllllIlIlIlIIIIIlllIl;
        llllllllllllllllIlIlIlIIIIIlllIl.yaw = llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1724.field_6031;
        llllllllllllllllIlIlIlIIIIIlllIl.pitch = llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1724.field_5965;
        llllllllllllllllIlIlIlIIIIIlllIl.perspective = llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1690.method_31044();
        llllllllllllllllIlIlIlIIIIIlllIl.pos.set(llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1773.method_19418().method_19326());
        llllllllllllllllIlIlIlIIIIIlllIl.prevPos.set(llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1773.method_19418().method_19326());
        llllllllllllllllIlIlIlIIIIIlllIl.prevYaw = llllllllllllllllIlIlIlIIIIIlllIl.yaw;
        llllllllllllllllIlIlIlIIIIIlllIl.prevPitch = llllllllllllllllIlIlIlIIIIIlllIl.pitch;
        llllllllllllllllIlIlIlIIIIIlllIl.forward = false;
        llllllllllllllllIlIlIlIIIIIlllIl.backward = false;
        llllllllllllllllIlIlIlIIIIIlllIl.right = false;
        llllllllllllllllIlIlIlIIIIIlllIl.left = false;
        llllllllllllllllIlIlIlIIIIIlllIl.up = false;
        llllllllllllllllIlIlIlIIIIIlllIl.down = false;
        llllllllllllllllIlIlIlIIIIIlllIl.unpress();
        if (llllllllllllllllIlIlIlIIIIIlllIl.reloadChunks.get().booleanValue()) {
            llllllllllllllllIlIlIlIIIIIlllIl.mc.field_1769.method_3279();
        }
    }

    @Override
    public void onDeactivate() {
        Freecam llllllllllllllllIlIlIlIIIIIllIlI;
        if (llllllllllllllllIlIlIlIIIIIllIlI.reloadChunks.get().booleanValue()) {
            llllllllllllllllIlIlIlIIIIIllIlI.mc.field_1769.method_3279();
        }
        llllllllllllllllIlIlIlIIIIIllIlI.mc.field_1690.method_31043(llllllllllllllllIlIlIlIIIIIllIlI.perspective);
    }
}

