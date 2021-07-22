/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2831
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.utils.player;

import java.util.ArrayList;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.SendMovementPacketsEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.misc.Pool;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_3532;

public class Rotations {
    public static /* synthetic */ int rotationTimer;
    public static /* synthetic */ float serverPitch;
    private static final /* synthetic */ Pool<Rotation> rotationPool;
    private static /* synthetic */ Rotation lastRotation;
    private static /* synthetic */ float preYaw;
    private static /* synthetic */ int i;
    private static /* synthetic */ int lastRotationTimer;
    private static /* synthetic */ boolean sentLastRotation;
    private static final /* synthetic */ List<Rotation> rotations;
    public static /* synthetic */ float serverYaw;
    private static /* synthetic */ float prePitch;

    public Rotations() {
        Rotations llIlllllIIIIIII;
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(Rotations.class);
    }

    public static double getPitch(class_243 llIllllIIlIllll) {
        double llIllllIIllIIll = llIllllIIlIllll.method_10216() - Utils.mc.field_1724.method_23317();
        double llIllllIIllIIlI = llIllllIIlIllll.method_10214() - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double llIllllIIllIIIl = llIllllIIlIllll.method_10215() - Utils.mc.field_1724.method_23321();
        double llIllllIIllIIII = Math.sqrt(llIllllIIllIIll * llIllllIIllIIll + llIllllIIllIIIl * llIllllIIllIIIl);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(llIllllIIllIIlI, llIllllIIllIIII))) - Utils.mc.field_1724.field_5965));
    }

    public static void rotate(double llIllllIlllIIII, double llIllllIllIllll, int llIllllIllIlllI, boolean llIllllIllIllIl, Runnable llIllllIllIllII) {
        int llIllllIlllIIIl;
        Rotation llIllllIlllIIlI = rotationPool.get();
        llIllllIlllIIlI.set(llIllllIlllIIII, llIllllIllIllll, llIllllIllIlllI, llIllllIllIllIl, llIllllIllIllII);
        for (llIllllIlllIIIl = 0; llIllllIlllIIIl < rotations.size() && llIllllIllIlllI <= Rotations.rotations.get((int)llIllllIlllIIIl).priority; ++llIllllIlllIIIl) {
        }
        rotations.add(llIllllIlllIIIl, llIllllIlllIIlI);
    }

    private static void setupMovementPacketRotation(Rotation llIllllIlIIlIIl) {
        Rotations.setClientRotation(llIllllIlIIlIIl);
        Rotations.setCamRotation(llIllllIlIIlIIl.yaw, llIllllIlIIlIIl.pitch);
    }

    @EventHandler
    private static void onTick(TickEvent.Pre llIllllIlIIIIII) {
        ++rotationTimer;
    }

    static {
        rotationPool = new Pool<Rotation>(() -> new Rotation());
        rotations = new ArrayList<Rotation>();
        i = 0;
    }

    private static void setClientRotation(Rotation llIllllIlIIIlIl) {
        preYaw = Utils.mc.field_1724.field_6031;
        prePitch = Utils.mc.field_1724.field_5965;
        Utils.mc.field_1724.field_6031 = (float)llIllllIlIIIlIl.yaw;
        Utils.mc.field_1724.field_5965 = (float)llIllllIlIIIlIl.pitch;
    }

    public static double getPitch(class_1297 llIllllIIIlIIlI) {
        return Rotations.getPitch(llIllllIIIlIIlI, Target.Body);
    }

    @EventHandler
    private static void onSendMovementPacketsPre(SendMovementPacketsEvent.Pre llIllllIlIIllII) {
        if (Utils.mc.field_1719 != Utils.mc.field_1724) {
            return;
        }
        sentLastRotation = false;
        if (!rotations.isEmpty()) {
            Rotations.resetLastRotation();
            Rotation llIllllIlIIllIl = rotations.get(i);
            Rotations.setupMovementPacketRotation(llIllllIlIIllIl);
            if (rotations.size() > 1) {
                rotationPool.free(llIllllIlIIllIl);
            }
            ++i;
        } else if (lastRotation != null) {
            if (lastRotationTimer >= Config.get().rotationHoldTicks) {
                Rotations.resetLastRotation();
            } else {
                Rotations.setupMovementPacketRotation(lastRotation);
                sentLastRotation = true;
                ++lastRotationTimer;
            }
        }
    }

    @EventHandler
    private static void onSendMovementPacketsPost(SendMovementPacketsEvent.Post llIllllIlIIIIlI) {
        if (!rotations.isEmpty()) {
            if (Utils.mc.field_1719 == Utils.mc.field_1724) {
                rotations.get(i - 1).runCallback();
                if (rotations.size() == 1) {
                    lastRotation = rotations.get(i - 1);
                }
                Rotations.resetPreRotation();
            }
            while (++i < rotations.size()) {
                Rotation llIllllIlIIIIll = rotations.get(i);
                Rotations.setCamRotation(llIllllIlIIIIll.yaw, llIllllIlIIIIll.pitch);
                if (llIllllIlIIIIll.clientSide) {
                    Rotations.setClientRotation(llIllllIlIIIIll);
                }
                llIllllIlIIIIll.sendPacket();
                if (llIllllIlIIIIll.clientSide) {
                    Rotations.resetPreRotation();
                }
                if (i == rotations.size() - 1) {
                    lastRotation = llIllllIlIIIIll;
                    continue;
                }
                rotationPool.free(llIllllIlIIIIll);
            }
            rotations.clear();
            i = 0;
        } else if (sentLastRotation) {
            Rotations.resetPreRotation();
        }
    }

    public static void rotate(double llIllllIlIllIlI, double llIllllIlIlIllI, Runnable llIllllIlIllIII) {
        Rotations.rotate(llIllllIlIllIlI, llIllllIlIlIllI, 0, llIllllIlIllIII);
    }

    private static void resetPreRotation() {
        Utils.mc.field_1724.field_6031 = preYaw;
        Utils.mc.field_1724.field_5965 = prePitch;
    }

    public static double getPitch(class_1297 llIllllIIIllIlI, Target llIllllIIlIIIII) {
        double llIllllIIIlllll;
        if (llIllllIIlIIIII == Target.Head) {
            double llIllllIIlIIIll = llIllllIIIllIlI.method_23320();
        } else if (llIllllIIlIIIII == Target.Body) {
            double llIllllIIlIIIlI = llIllllIIIllIlI.method_23318() + (double)(llIllllIIIllIlI.method_17682() / 2.0f);
        } else {
            llIllllIIIlllll = llIllllIIIllIlI.method_23318();
        }
        double llIllllIIIllllI = llIllllIIIllIlI.method_23317() - Utils.mc.field_1724.method_23317();
        double llIllllIIIlllIl = llIllllIIIlllll - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double llIllllIIIlllII = llIllllIIIllIlI.method_23321() - Utils.mc.field_1724.method_23321();
        double llIllllIIIllIll = Math.sqrt(llIllllIIIllllI * llIllllIIIllllI + llIllllIIIlllII * llIllllIIIlllII);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(llIllllIIIlllIl, llIllllIIIllIll))) - Utils.mc.field_1724.field_5965));
    }

    public static double getYaw(class_2338 llIllllIIIIlllI) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2((double)llIllllIIIIlllI.method_10260() + 0.5 - Utils.mc.field_1724.method_23321(), (double)llIllllIIIIlllI.method_10263() + 0.5 - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    public static void rotate(double llIllllIlIlIIII, double llIllllIlIlIIIl) {
        Rotations.rotate(llIllllIlIlIIII, llIllllIlIlIIIl, 0, null);
    }

    public static void rotate(double llIllllIllIIIIl, double llIllllIllIIIII, int llIllllIllIIIll, Runnable llIllllIlIllllI) {
        Rotations.rotate(llIllllIllIIIIl, llIllllIllIIIII, llIllllIllIIIll, false, llIllllIlIllllI);
    }

    public static double getYaw(class_1297 llIllllIIlllllI) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2(llIllllIIlllllI.method_23321() - Utils.mc.field_1724.method_23321(), llIllllIIlllllI.method_23317() - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    public static void setCamRotation(double llIlllIllllllII, double llIlllIlllllIIl) {
        serverYaw = (float)llIlllIllllllII;
        serverPitch = (float)llIlllIlllllIIl;
        rotationTimer = 0;
    }

    private static void resetLastRotation() {
        if (lastRotation != null) {
            rotationPool.free(lastRotation);
            lastRotation = null;
            lastRotationTimer = 0;
        }
    }

    public static double getPitch(class_2338 llIllllIIIIlIII) {
        double llIllllIIIIIlll = (double)llIllllIIIIlIII.method_10263() + 0.5 - Utils.mc.field_1724.method_23317();
        double llIllllIIIIIllI = (double)llIllllIIIIlIII.method_10264() + 0.5 - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double llIllllIIIIIlIl = (double)llIllllIIIIlIII.method_10260() + 0.5 - Utils.mc.field_1724.method_23321();
        double llIllllIIIIIlII = Math.sqrt(llIllllIIIIIlll * llIllllIIIIIlll + llIllllIIIIIlIl * llIllllIIIIIlIl);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(llIllllIIIIIllI, llIllllIIIIIlII))) - Utils.mc.field_1724.field_5965));
    }

    public static double getYaw(class_243 llIllllIIlllIlI) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2(llIllllIIlllIlI.method_10215() - Utils.mc.field_1724.method_23321(), llIllllIIlllIlI.method_10216() - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    private static class Rotation {
        public /* synthetic */ double pitch;
        public /* synthetic */ int priority;
        public /* synthetic */ boolean clientSide;
        public /* synthetic */ Runnable callback;
        public /* synthetic */ double yaw;

        private Rotation() {
            Rotation llllllllllllllllllIIIlllllllllIl;
        }

        public void sendPacket() {
            Rotation llllllllllllllllllIIIllllllIlIIl;
            Utils.mc.method_1562().method_2883((class_2596)new class_2828.class_2831((float)llllllllllllllllllIIIllllllIlIIl.yaw, (float)llllllllllllllllllIIIllllllIlIIl.pitch, Utils.mc.field_1724.method_24828()));
            llllllllllllllllllIIIllllllIlIIl.runCallback();
        }

        public void runCallback() {
            Rotation llllllllllllllllllIIIllllllIIlIl;
            if (llllllllllllllllllIIIllllllIIlIl.callback != null) {
                llllllllllllllllllIIIllllllIIlIl.callback.run();
            }
        }

        public void set(double llllllllllllllllllIIIlllllllIlIl, double llllllllllllllllllIIIlllllllIlII, int llllllllllllllllllIIIlllllllIIll, boolean llllllllllllllllllIIIllllllIllII, Runnable llllllllllllllllllIIIllllllIlIll) {
            llllllllllllllllllIIIlllllllIllI.yaw = llllllllllllllllllIIIlllllllIlIl;
            llllllllllllllllllIIIlllllllIllI.pitch = llllllllllllllllllIIIlllllllIlII;
            llllllllllllllllllIIIlllllllIllI.priority = llllllllllllllllllIIIlllllllIIll;
            llllllllllllllllllIIIlllllllIllI.clientSide = llllllllllllllllllIIIllllllIllII;
            llllllllllllllllllIIIlllllllIllI.callback = llllllllllllllllllIIIllllllIlIll;
        }
    }
}

