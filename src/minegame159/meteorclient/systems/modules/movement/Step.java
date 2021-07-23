/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import baritone.api.BaritoneAPI;
import com.google.common.collect.Streams;
import java.util.Comparator;
import java.util.Optional;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;

public class Step
extends Module {
    static final boolean $assertionsDisabled = !Step.class.desiredAssertionStatus();
    private final Setting<ActiveWhen> activeWhen;
    private final SettingGroup sgGeneral;
    private boolean prevBaritoneAssumeStep;
    private final Setting<Integer> stepHealth;
    private final Setting<Boolean> safeStep;
    private float prevStepHeight;
    public final Setting<Double> height;

    private Double lambda$getExplosionDamage$3(class_1511 class_15112) {
        return DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, class_15112.method_19538());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        boolean bl = this.activeWhen.get() == ActiveWhen.Always || this.activeWhen.get() == ActiveWhen.Sneaking && this.mc.field_1724.method_5715() || this.activeWhen.get() == ActiveWhen.NotSneaking && !this.mc.field_1724.method_5715();
        this.mc.field_1724.method_5857(this.mc.field_1724.method_5829().method_989(0.0, 1.0, 0.0));
        this.mc.field_1724.field_6013 = bl && (this.safeStep.get() == false || this.getHealth() > (float)this.stepHealth.get().intValue() && (double)this.getHealth() - this.getExplosionDamage() > (double)this.stepHealth.get().intValue()) ? this.height.get().floatValue() : this.prevStepHeight;
        this.mc.field_1724.method_5857(this.mc.field_1724.method_5829().method_989(0.0, -1.0, 0.0));
    }

    @Override
    public void onActivate() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.prevStepHeight = this.mc.field_1724.field_6013;
        BaritoneAPI.getSettings().assumeStep.value = true;
    }

    private double getExplosionDamage() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        Optional<class_1511> optional = Streams.stream((Iterable)this.mc.field_1687.method_18112()).filter(Step::lambda$getExplosionDamage$0).filter(class_1297::method_5805).max(Comparator.comparingDouble(this::lambda$getExplosionDamage$1)).map(Step::lambda$getExplosionDamage$2);
        return optional.map(this::lambda$getExplosionDamage$3).orElse(0.0);
    }

    private static boolean lambda$getExplosionDamage$0(class_1297 class_12972) {
        return class_12972 instanceof class_1511;
    }

    private float getHealth() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        return this.mc.field_1724.method_6032() + this.mc.field_1724.method_6067();
    }

    private static class_1511 lambda$getExplosionDamage$2(class_1297 class_12972) {
        return (class_1511)class_12972;
    }

    public Step() {
        super(Categories.Movement, "step", "Allows you to walk up full blocks instantly.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.height = this.sgGeneral.add(new DoubleSetting.Builder().name("height").description("Step height.").defaultValue(1.0).min(0.0).build());
        this.activeWhen = this.sgGeneral.add(new EnumSetting.Builder().name("active-when").description("Step is active when you meet these requirements.").defaultValue(ActiveWhen.Always).build());
        this.safeStep = this.sgGeneral.add(new BoolSetting.Builder().name("safe-step").description("Doesn't let you step out of a hole if you are low on health or there is a crystal nearby.").defaultValue(false).build());
        this.stepHealth = this.sgGeneral.add(new IntSetting.Builder().name("step-health").description("The health you stop being able to step at.").defaultValue(5).min(1).max(36).visible(this.safeStep::get).build());
    }

    @Override
    public void onDeactivate() {
        if (this.mc.field_1724 != null) {
            this.mc.field_1724.field_6013 = this.prevStepHeight;
        }
        BaritoneAPI.getSettings().assumeStep.value = false;
    }

    private double lambda$getExplosionDamage$1(class_1297 class_12972) {
        return DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, class_12972.method_19538());
    }

    public static enum ActiveWhen {
        Always,
        Sneaking,
        NotSneaking;

    }
}

