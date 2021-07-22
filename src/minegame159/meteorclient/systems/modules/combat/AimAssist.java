/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_3532
 */
package minegame159.meteorclient.systems.modules.combat;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_3532;

public class AimAssist
extends Module {
    private final /* synthetic */ Setting<Boolean> instant;
    private final /* synthetic */ Setting<Double> range;
    private final /* synthetic */ Vec3 vec3d1;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ Setting<Target> bodyTarget;
    private final /* synthetic */ Setting<SortPriority> priority;
    private final /* synthetic */ Setting<Boolean> friends;
    private /* synthetic */ class_1297 target;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ SettingGroup sgSpeed;
    private final /* synthetic */ Setting<Boolean> ignoreWalls;

    public AimAssist() {
        super(Categories.Combat, "aim-assist", "Automatically aims at entities.");
        AimAssist lllllllllllllllllIlIIIlllIIllIIl;
        lllllllllllllllllIlIIIlllIIllIIl.sgGeneral = lllllllllllllllllIlIIIlllIIllIIl.settings.getDefaultGroup();
        lllllllllllllllllIlIIIlllIIllIIl.sgSpeed = lllllllllllllllllIlIIIlllIIllIIl.settings.createGroup("Aim Speed");
        lllllllllllllllllIlIIIlllIIllIIl.range = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The range at which an entity can be targeted.").defaultValue(5.0).min(0.0).build());
        lllllllllllllllllIlIIIlllIIllIIl.entities = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to aim at.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).build());
        lllllllllllllllllIlIIIlllIIllIIl.friends = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new BoolSetting.Builder().name("friends").description("Whether or not to aim at friends.").defaultValue(false).build());
        lllllllllllllllllIlIIIlllIIllIIl.ignoreWalls = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new BoolSetting.Builder().name("ignore-walls").description("Whether or not to ignore aiming through walls.").defaultValue(false).build());
        lllllllllllllllllIlIIIlllIIllIIl.priority = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new EnumSetting.Builder().name("priority").description("How to select target from entities in range.").defaultValue(SortPriority.LowestHealth).build());
        lllllllllllllllllIlIIIlllIIllIIl.bodyTarget = lllllllllllllllllIlIIIlllIIllIIl.sgGeneral.add(new EnumSetting.Builder().name("target").description("Which part of the entities body to aim at.").defaultValue(Target.Body).build());
        lllllllllllllllllIlIIIlllIIllIIl.instant = lllllllllllllllllIlIIIlllIIllIIl.sgSpeed.add(new BoolSetting.Builder().name("instant-look").description("Instantly looks at the entity.").defaultValue(false).build());
        lllllllllllllllllIlIIIlllIIllIIl.speed = lllllllllllllllllIlIIIlllIIllIIl.sgSpeed.add(new DoubleSetting.Builder().name("speed").description("How fast to aim at the entity.").defaultValue(5.0).min(0.0).build());
        lllllllllllllllllIlIIIlllIIllIIl.vec3d1 = new Vec3();
    }

    @EventHandler
    private void onRender(RenderEvent lllllllllllllllllIlIIIlllIIlIIIl) {
        AimAssist lllllllllllllllllIlIIIlllIIlIIII;
        if (lllllllllllllllllIlIIIlllIIlIIII.target != null) {
            lllllllllllllllllIlIIIlllIIlIIII.aim(lllllllllllllllllIlIIIlllIIlIIII.target, lllllllllllllllllIlIIIlllIIlIIIl.tickDelta, lllllllllllllllllIlIIIlllIIlIIII.instant.get());
        }
    }

    private void aim(class_1297 lllllllllllllllllIlIIIllIlllIlIl, double lllllllllllllllllIlIIIllIlllIIll, boolean lllllllllllllllllIlIIIllIlllllII) {
        AimAssist lllllllllllllllllIlIIIllIlllIllI;
        lllllllllllllllllIlIIIllIlllIllI.setVec3dToTargetPoint(lllllllllllllllllIlIIIllIlllIllI.vec3d1, lllllllllllllllllIlIIIllIlllIlIl, lllllllllllllllllIlIIIllIlllIIll);
        double lllllllllllllllllIlIIIllIllllIll = lllllllllllllllllIlIIIllIlllIllI.vec3d1.x - lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.method_23317();
        double lllllllllllllllllIlIIIllIllllIlI = lllllllllllllllllIlIIIllIlllIllI.vec3d1.z - lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.method_23321();
        double lllllllllllllllllIlIIIllIllllIIl = lllllllllllllllllIlIIIllIlllIllI.vec3d1.y - (lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.method_23318() + (double)lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.method_18381(lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.method_18376()));
        double lllllllllllllllllIlIIIllIllllIII = Math.toDegrees(Math.atan2(lllllllllllllllllIlIIIllIllllIlI, lllllllllllllllllIlIIIllIllllIll)) - 90.0;
        if (lllllllllllllllllIlIIIllIlllllII) {
            lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_6031 = (float)lllllllllllllllllIlIIIllIllllIII;
        } else {
            double lllllllllllllllllIlIIIlllIIIIIll = class_3532.method_15338((double)(lllllllllllllllllIlIIIllIllllIII - (double)lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_6031));
            double lllllllllllllllllIlIIIlllIIIIIlI = lllllllllllllllllIlIIIllIlllIllI.speed.get() * (double)(lllllllllllllllllIlIIIlllIIIIIll >= 0.0 ? 1 : -1) * lllllllllllllllllIlIIIllIlllIIll;
            if (lllllllllllllllllIlIIIlllIIIIIlI >= 0.0 && lllllllllllllllllIlIIIlllIIIIIlI > lllllllllllllllllIlIIIlllIIIIIll || lllllllllllllllllIlIIIlllIIIIIlI < 0.0 && lllllllllllllllllIlIIIlllIIIIIlI < lllllllllllllllllIlIIIlllIIIIIll) {
                lllllllllllllllllIlIIIlllIIIIIlI = lllllllllllllllllIlIIIlllIIIIIll;
            }
            lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_6031 = (float)((double)lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_6031 + lllllllllllllllllIlIIIlllIIIIIlI);
        }
        double lllllllllllllllllIlIIIllIlllIlll = Math.sqrt(lllllllllllllllllIlIIIllIllllIll * lllllllllllllllllIlIIIllIllllIll + lllllllllllllllllIlIIIllIllllIlI * lllllllllllllllllIlIIIllIllllIlI);
        lllllllllllllllllIlIIIllIllllIII = -Math.toDegrees(Math.atan2(lllllllllllllllllIlIIIllIllllIIl, lllllllllllllllllIlIIIllIlllIlll));
        if (lllllllllllllllllIlIIIllIlllllII) {
            lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_5965 = (float)lllllllllllllllllIlIIIllIllllIII;
        } else {
            double lllllllllllllllllIlIIIlllIIIIIIl = class_3532.method_15338((double)(lllllllllllllllllIlIIIllIllllIII - (double)lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_5965));
            double lllllllllllllllllIlIIIlllIIIIIII = lllllllllllllllllIlIIIllIlllIllI.speed.get() * (double)(lllllllllllllllllIlIIIlllIIIIIIl >= 0.0 ? 1 : -1) * lllllllllllllllllIlIIIllIlllIIll;
            if (lllllllllllllllllIlIIIlllIIIIIII >= 0.0 && lllllllllllllllllIlIIIlllIIIIIII > lllllllllllllllllIlIIIlllIIIIIIl || lllllllllllllllllIlIIIlllIIIIIII < 0.0 && lllllllllllllllllIlIIIlllIIIIIII < lllllllllllllllllIlIIIlllIIIIIIl) {
                lllllllllllllllllIlIIIlllIIIIIII = lllllllllllllllllIlIIIlllIIIIIIl;
            }
            lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_5965 = (float)((double)lllllllllllllllllIlIIIllIlllIllI.mc.field_1724.field_5965 + lllllllllllllllllIlIIIlllIIIIIII);
        }
    }

    private void setVec3dToTargetPoint(Vec3 lllllllllllllllllIlIIIllIllIIIlI, class_1297 lllllllllllllllllIlIIIllIllIIIIl, double lllllllllllllllllIlIIIllIllIIIII) {
        AimAssist lllllllllllllllllIlIIIllIllIIlll;
        lllllllllllllllllIlIIIllIllIIIlI.set(lllllllllllllllllIlIIIllIllIIIIl, lllllllllllllllllIlIIIllIllIIIII);
        switch (lllllllllllllllllIlIIIllIllIIlll.bodyTarget.get()) {
            case Head: {
                lllllllllllllllllIlIIIllIllIIIlI.add(0.0, lllllllllllllllllIlIIIllIllIIIIl.method_18381(lllllllllllllllllIlIIIllIllIIIIl.method_18376()), 0.0);
                break;
            }
            case Body: {
                lllllllllllllllllIlIIIllIllIIIlI.add(0.0, lllllllllllllllllIlIIIllIllIIIIl.method_18381(lllllllllllllllllIlIIIllIllIIIIl.method_18376()) / 2.0f, 0.0);
            }
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlIIIlllIIlIllI) {
        AimAssist lllllllllllllllllIlIIIlllIIlIlll;
        lllllllllllllllllIlIIIlllIIlIlll.target = TargetUtils.get(lllllllllllllllllIlIIIllIlIllIIl -> {
            AimAssist lllllllllllllllllIlIIIllIlIllIII;
            if (!lllllllllllllllllIlIIIllIlIllIIl.method_5805()) {
                return false;
            }
            if ((double)lllllllllllllllllIlIIIllIlIllIII.mc.field_1724.method_5739(lllllllllllllllllIlIIIllIlIllIIl) >= lllllllllllllllllIlIIIllIlIllIII.range.get()) {
                return false;
            }
            if (!lllllllllllllllllIlIIIllIlIllIII.ignoreWalls.get().booleanValue() && !PlayerUtils.canSeeEntity(lllllllllllllllllIlIIIllIlIllIIl)) {
                return false;
            }
            if (lllllllllllllllllIlIIIllIlIllIIl == lllllllllllllllllIlIIIllIlIllIII.mc.field_1724 || !lllllllllllllllllIlIIIllIlIllIII.entities.get().getBoolean((Object)lllllllllllllllllIlIIIllIlIllIIl.method_5864())) {
                return false;
            }
            if (lllllllllllllllllIlIIIllIlIllIIl instanceof class_1657) {
                return Friends.get().shouldAttack((class_1657)lllllllllllllllllIlIIIllIlIllIIl);
            }
            return true;
        }, lllllllllllllllllIlIIIlllIIlIlll.priority.get());
    }

    @Override
    public String getInfoString() {
        AimAssist lllllllllllllllllIlIIIllIlIlllIl;
        if (lllllllllllllllllIlIIIllIlIlllIl.target != null && lllllllllllllllllIlIIIllIlIlllIl.target instanceof class_1657) {
            return lllllllllllllllllIlIIIllIlIlllIl.target.method_5820();
        }
        if (lllllllllllllllllIlIIIllIlIlllIl.target != null) {
            return lllllllllllllllllIlIIIllIlIlllIl.target.method_5864().method_5897().getString();
        }
        return null;
    }
}

