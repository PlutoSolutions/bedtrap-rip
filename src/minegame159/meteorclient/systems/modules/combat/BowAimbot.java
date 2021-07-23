/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import baritone.api.BaritoneAPI;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_1744;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1799;
import net.minecraft.class_243;

public class BowAimbot
extends Module {
    private final Setting<SortPriority> priority;
    private boolean wasPathing;
    private final Setting<Boolean> babies;
    private final Setting<Boolean> pauseOnCombat;
    private final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private class_1297 target;
    private final Setting<Double> range;
    private final Setting<Boolean> nametagged;
    private final SettingGroup sgGeneral;

    private boolean itemInHand() {
        return this.mc.field_1724.method_6047().method_7909() instanceof class_1753 || this.mc.field_1724.method_6047().method_7909() instanceof class_1764;
    }

    private boolean lambda$onRender$1(class_1297 class_12972) {
        if (class_12972 == this.mc.field_1724 || class_12972 == this.mc.field_1719) {
            return false;
        }
        if (class_12972 instanceof class_1309 && ((class_1309)class_12972).method_29504() || !class_12972.method_5805()) {
            return false;
        }
        if ((double)class_12972.method_5739((class_1297)this.mc.field_1724) > this.range.get()) {
            return false;
        }
        if (!this.entities.get().getBoolean((Object)class_12972.method_5864())) {
            return false;
        }
        if (!this.nametagged.get().booleanValue() && class_12972.method_16914()) {
            return false;
        }
        if (!PlayerUtils.canSeeEntity(class_12972)) {
            return false;
        }
        if (class_12972 instanceof class_1657) {
            if (((class_1657)class_12972).method_7337()) {
                return false;
            }
            if (!Friends.get().shouldAttack((class_1657)class_12972)) {
                return false;
            }
        }
        return !(class_12972 instanceof class_1429) || this.babies.get() != false || !((class_1429)class_12972).method_6109();
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            if (this.target instanceof class_1657) {
                return this.target.method_5820();
            }
            return this.target.method_5864().method_5897().getString();
        }
        return null;
    }

    private void aim(double d) {
        float f = (float)(this.mc.field_1724.method_6048() - this.mc.field_1724.method_6014()) / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        double d2 = this.target.method_19538().method_10216() + (this.target.method_19538().method_10216() - this.target.field_6014) * d;
        double d3 = this.target.method_19538().method_10214() + (this.target.method_19538().method_10214() - this.target.field_6036) * d;
        double d4 = this.target.method_19538().method_10215() + (this.target.method_19538().method_10215() - this.target.field_5969) * d;
        double d5 = d2 - this.mc.field_1724.method_23317();
        double d6 = (d3 -= (double)(1.9f - this.target.method_17682())) - this.mc.field_1724.method_23318();
        float f2 = f * f;
        float f3 = 0.006f;
        double d7 = d4 - this.mc.field_1724.method_23321();
        double d8 = Math.sqrt(d5 * d5 + d7 * d7);
        double d9 = d8 * d8;
        float f4 = (float)(-Math.toDegrees(Math.atan(((double)f2 - Math.sqrt((double)(f2 * f2) - (double)f3 * ((double)f3 * d9 + 2.0 * d6 * (double)f2))) / ((double)f3 * d8))));
        if (Float.isNaN(f4)) {
            Rotations.rotate(Rotations.getYaw(this.target), Rotations.getPitch(this.target));
        } else {
            Rotations.rotate(Rotations.getYaw(new class_243(d2, d3, d4)), f4);
        }
    }

    private boolean playerIsDead() {
        return this.mc.field_1724.method_29504() || !this.mc.field_1724.method_5805();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.playerIsDead() || !this.itemInHand()) {
            return;
        }
        if (!InvUtils.find(BowAimbot::lambda$onRender$0).found()) {
            return;
        }
        this.target = TargetUtils.get(this::lambda$onRender$1, this.priority.get());
        if (this.target == null) {
            if (this.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("resume");
                this.wasPathing = false;
            }
            return;
        }
        if (this.mc.field_1690.field_1904.method_1434() && this.itemInHand()) {
            if (this.pauseOnCombat.get().booleanValue() && BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && !this.wasPathing) {
                BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("pause");
                this.wasPathing = true;
            }
            this.aim(renderEvent.tickDelta);
        }
    }

    private static boolean lambda$onRender$0(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1744;
    }

    @Override
    public void onDeactivate() {
        this.target = null;
        this.wasPathing = false;
    }

    public BowAimbot() {
        super(Categories.Combat, "Bow Aimbot", "Automatically aims your bow for you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.range = this.sgGeneral.add(new DoubleSetting.Builder().name("range").description("The maximum range the entity can be to aim at it.").defaultValue(20.0).min(0.0).max(100.0).sliderMax(100.0).build());
        this.entities = this.sgGeneral.add(new EntityTypeListSetting.Builder().name("entities").description("Entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).onlyAttackable().build());
        this.priority = this.sgGeneral.add(new EnumSetting.Builder().name("priority").description("What type of entities to target.").defaultValue(SortPriority.LowestHealth).build());
        this.babies = this.sgGeneral.add(new BoolSetting.Builder().name("babies").description("Whether or not to attack baby variants of the entity.").defaultValue(true).build());
        this.nametagged = this.sgGeneral.add(new BoolSetting.Builder().name("nametagged").description("Whether or not to attack mobs with a name tag.").defaultValue(false).build());
        this.pauseOnCombat = this.sgGeneral.add(new BoolSetting.Builder().name("pause-on-combat").description("Freezes Baritone temporarily until you released the bow.").defaultValue(false).build());
    }
}

