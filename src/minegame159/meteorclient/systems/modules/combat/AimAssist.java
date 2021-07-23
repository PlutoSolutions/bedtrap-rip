/*
 * Decompiled with CFR 0.151.
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
    private final Setting<Boolean> instant;
    private final Setting<Double> range;
    private final Vec3 vec3d1;
    private final SettingGroup sgGeneral;
    private final Setting<Double> speed;
    private final Setting<Target> bodyTarget;
    private final Setting<SortPriority> priority;
    private final Setting<Boolean> friends;
    private class_1297 target;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final SettingGroup sgSpeed;
    private final Setting<Boolean> ignoreWalls;

    public AimAssist() {
        super(Categories.Combat, "aim-assist", "Automatically aims at entities.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgSpeed = this.settings.createGroup("Aim Speed");
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The range at which an entity can be targeted.").defaultValue(5.0).min(0.0).build());
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to aim at.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(class_1299.field_6097)).build());
        this.friends = this.sgGeneral.add(new BoolSetting.Builder().name("friends").description("Whether or not to aim at friends.").defaultValue(false).build());
        this.ignoreWalls = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-walls").description("Whether or not to ignore aiming through walls.").defaultValue(false).build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("priority").description("How to select target from entities in range.").defaultValue(SortPriority.LowestHealth).build());
        this.bodyTarget = this.sgGeneral.add(new EnumSetting.Builder().name("target").description("Which part of the entities body to aim at.").defaultValue(Target.Body).build());
        this.instant = this.sgSpeed.add(new BoolSetting.Builder().name("instant-look").description("Instantly looks at the entity.").defaultValue(false).build());
        this.speed = this.sgSpeed.add(new DoubleSetting.Builder().name("speed").description("How fast to aim at the entity.").defaultValue(5.0).min(0.0).build());
        this.vec3d1 = new Vec3();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.target != null) {
            this.aim(this.target, renderEvent.tickDelta, this.instant.get());
        }
    }

    private void aim(class_1297 class_12972, double d, boolean bl) {
        double d2;
        double d3;
        this.setVec3dToTargetPoint(this.vec3d1, class_12972, d);
        double d4 = this.vec3d1.x - this.mc.field_1724.method_23317();
        double d5 = this.vec3d1.z - this.mc.field_1724.method_23321();
        double d6 = this.vec3d1.y - (this.mc.field_1724.method_23318() + (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()));
        double d7 = Math.toDegrees(Math.atan2(d5, d4)) - 90.0;
        if (bl) {
            this.mc.field_1724.field_6031 = (float)d7;
        } else {
            d3 = class_3532.method_15338((double)(d7 - (double)this.mc.field_1724.field_6031));
            d2 = this.speed.get() * (double)(d3 >= 0.0 ? 1 : -1) * d;
            if (d2 >= 0.0 && d2 > d3 || d2 < 0.0 && d2 < d3) {
                d2 = d3;
            }
            this.mc.field_1724.field_6031 = (float)((double)this.mc.field_1724.field_6031 + d2);
        }
        double d8 = Math.sqrt(d4 * d4 + d5 * d5);
        d7 = -Math.toDegrees(Math.atan2(d6, d8));
        if (bl) {
            this.mc.field_1724.field_5965 = (float)d7;
        } else {
            d3 = class_3532.method_15338((double)(d7 - (double)this.mc.field_1724.field_5965));
            d2 = this.speed.get() * (double)(d3 >= 0.0 ? 1 : -1) * d;
            if (d2 >= 0.0 && d2 > d3 || d2 < 0.0 && d2 < d3) {
                d2 = d3;
            }
            this.mc.field_1724.field_5965 = (float)((double)this.mc.field_1724.field_5965 + d2);
        }
    }

    private boolean lambda$onTick$0(class_1297 class_12972) {
        if (!class_12972.method_5805()) {
            return false;
        }
        if ((double)this.mc.field_1724.method_5739(class_12972) >= this.range.get()) {
            return false;
        }
        if (!this.ignoreWalls.get().booleanValue() && !PlayerUtils.canSeeEntity(class_12972)) {
            return false;
        }
        if (class_12972 == this.mc.field_1724 || !this.entities.get().getBoolean((Object)class_12972.method_5864())) {
            return false;
        }
        if (class_12972 instanceof class_1657) {
            return Friends.get().shouldAttack((class_1657)class_12972);
        }
        return true;
    }

    private void setVec3dToTargetPoint(Vec3 vec3, class_1297 class_12972, double d) {
        vec3.set(class_12972, d);
        switch (1.$SwitchMap$minegame159$meteorclient$utils$entity$Target[this.bodyTarget.get().ordinal()]) {
            case 1: {
                vec3.add(0.0, class_12972.method_18381(class_12972.method_18376()), 0.0);
                break;
            }
            case 2: {
                vec3.add(0.0, class_12972.method_18381(class_12972.method_18376()) / 2.0f, 0.0);
            }
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.target = TargetUtils.get(this::lambda$onTick$0, this.priority.get());
    }

    @Override
    public String getInfoString() {
        if (this.target != null && this.target instanceof class_1657) {
            return this.target.method_5820();
        }
        if (this.target != null) {
            return this.target.method_5864().method_5897().getString();
        }
        return null;
    }
}

