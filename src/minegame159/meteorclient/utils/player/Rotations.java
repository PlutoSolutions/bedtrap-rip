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
    public static int rotationTimer;
    public static float serverPitch;
    private static final Pool<Rotation> rotationPool;
    private static Rotation lastRotation;
    private static float preYaw;
    private static int i;
    private static int lastRotationTimer;
    private static boolean sentLastRotation;
    private static final List<Rotation> rotations;
    public static float serverYaw;
    private static float prePitch;

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(Rotations.class);
    }

    public static double getPitch(class_243 class_2432) {
        double d = class_2432.method_10216() - Utils.mc.field_1724.method_23317();
        double d2 = class_2432.method_10214() - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double d3 = class_2432.method_10215() - Utils.mc.field_1724.method_23321();
        double d4 = Math.sqrt(d * d + d3 * d3);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(d2, d4))) - Utils.mc.field_1724.field_5965));
    }

    public static void rotate(double d, double d2, int n, boolean bl, Runnable runnable) {
        int n2;
        Rotation rotation = rotationPool.get();
        rotation.set(d, d2, n, bl, runnable);
        for (n2 = 0; n2 < rotations.size() && n <= Rotations.rotations.get((int)n2).priority; ++n2) {
            if (4 > 3) continue;
            return;
        }
        rotations.add(n2, rotation);
    }

    private static void setupMovementPacketRotation(Rotation rotation) {
        Rotations.setClientRotation(rotation);
        Rotations.setCamRotation(rotation.yaw, rotation.pitch);
    }

    private static Rotation lambda$static$0() {
        return new Rotation(null);
    }

    @EventHandler
    private static void onTick(TickEvent.Pre pre) {
        ++rotationTimer;
    }

    static {
        rotationPool = new Pool<Rotation>(Rotations::lambda$static$0);
        rotations = new ArrayList<Rotation>();
        i = 0;
    }

    private static void setClientRotation(Rotation rotation) {
        preYaw = Utils.mc.field_1724.field_6031;
        prePitch = Utils.mc.field_1724.field_5965;
        Utils.mc.field_1724.field_6031 = (float)rotation.yaw;
        Utils.mc.field_1724.field_5965 = (float)rotation.pitch;
    }

    public static double getPitch(class_1297 class_12972) {
        return Rotations.getPitch(class_12972, Target.Body);
    }

    @EventHandler
    private static void onSendMovementPacketsPre(SendMovementPacketsEvent.Pre pre) {
        if (Utils.mc.field_1719 != Utils.mc.field_1724) {
            return;
        }
        sentLastRotation = false;
        if (!rotations.isEmpty()) {
            Rotations.resetLastRotation();
            Rotation rotation = rotations.get(i);
            Rotations.setupMovementPacketRotation(rotation);
            if (rotations.size() > 1) {
                rotationPool.free(rotation);
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
    private static void onSendMovementPacketsPost(SendMovementPacketsEvent.Post post) {
        if (!rotations.isEmpty()) {
            if (Utils.mc.field_1719 == Utils.mc.field_1724) {
                rotations.get(i - 1).runCallback();
                if (rotations.size() == 1) {
                    lastRotation = rotations.get(i - 1);
                }
                Rotations.resetPreRotation();
            }
            while (++i < rotations.size()) {
                Rotation rotation = rotations.get(i);
                Rotations.setCamRotation(rotation.yaw, rotation.pitch);
                if (rotation.clientSide) {
                    Rotations.setClientRotation(rotation);
                }
                rotation.sendPacket();
                if (rotation.clientSide) {
                    Rotations.resetPreRotation();
                }
                if (i == rotations.size() - 1) {
                    lastRotation = rotation;
                    continue;
                }
                rotationPool.free(rotation);
            }
            rotations.clear();
            i = 0;
        } else if (sentLastRotation) {
            Rotations.resetPreRotation();
        }
    }

    public static void rotate(double d, double d2, Runnable runnable) {
        Rotations.rotate(d, d2, 0, runnable);
    }

    private static void resetPreRotation() {
        Utils.mc.field_1724.field_6031 = preYaw;
        Utils.mc.field_1724.field_5965 = prePitch;
    }

    public static double getPitch(class_1297 class_12972, Target target) {
        double d = target == Target.Head ? class_12972.method_23320() : (target == Target.Body ? class_12972.method_23318() + (double)(class_12972.method_17682() / 2.0f) : class_12972.method_23318());
        double d2 = class_12972.method_23317() - Utils.mc.field_1724.method_23317();
        double d3 = d - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double d4 = class_12972.method_23321() - Utils.mc.field_1724.method_23321();
        double d5 = Math.sqrt(d2 * d2 + d4 * d4);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(d3, d5))) - Utils.mc.field_1724.field_5965));
    }

    public static double getYaw(class_2338 class_23382) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2((double)class_23382.method_10260() + 0.5 - Utils.mc.field_1724.method_23321(), (double)class_23382.method_10263() + 0.5 - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    public static void rotate(double d, double d2) {
        Rotations.rotate(d, d2, 0, null);
    }

    public static void rotate(double d, double d2, int n, Runnable runnable) {
        Rotations.rotate(d, d2, n, false, runnable);
    }

    public static double getYaw(class_1297 class_12972) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2(class_12972.method_23321() - Utils.mc.field_1724.method_23321(), class_12972.method_23317() - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    public static void setCamRotation(double d, double d2) {
        serverYaw = (float)d;
        serverPitch = (float)d2;
        rotationTimer = 0;
    }

    private static void resetLastRotation() {
        if (lastRotation != null) {
            rotationPool.free(lastRotation);
            lastRotation = null;
            lastRotationTimer = 0;
        }
    }

    public static double getPitch(class_2338 class_23382) {
        double d = (double)class_23382.method_10263() + 0.5 - Utils.mc.field_1724.method_23317();
        double d2 = (double)class_23382.method_10264() + 0.5 - (Utils.mc.field_1724.method_23318() + (double)Utils.mc.field_1724.method_18381(Utils.mc.field_1724.method_18376()));
        double d3 = (double)class_23382.method_10260() + 0.5 - Utils.mc.field_1724.method_23321();
        double d4 = Math.sqrt(d * d + d3 * d3);
        return Utils.mc.field_1724.field_5965 + class_3532.method_15393((float)((float)(-Math.toDegrees(Math.atan2(d2, d4))) - Utils.mc.field_1724.field_5965));
    }

    public static double getYaw(class_243 class_2432) {
        return Utils.mc.field_1724.field_6031 + class_3532.method_15393((float)((float)Math.toDegrees(Math.atan2(class_2432.method_10215() - Utils.mc.field_1724.method_23321(), class_2432.method_10216() - Utils.mc.field_1724.method_23317())) - 90.0f - Utils.mc.field_1724.field_6031));
    }

    private static class Rotation {
        public double pitch;
        public int priority;
        public boolean clientSide;
        public Runnable callback;
        public double yaw;

        private Rotation() {
        }

        public void sendPacket() {
            Utils.mc.method_1562().method_2883((class_2596)new class_2828.class_2831((float)this.yaw, (float)this.pitch, Utils.mc.field_1724.method_24828()));
            this.runCallback();
        }

        public void runCallback() {
            if (this.callback != null) {
                this.callback.run();
            }
        }

        public void set(double d, double d2, int n, boolean bl, Runnable runnable) {
            this.yaw = d;
            this.pitch = d2;
            this.priority = n;
            this.clientSide = bl;
            this.callback = runnable;
        }

        Rotation(1 var1_1) {
            this();
        }
    }
}

