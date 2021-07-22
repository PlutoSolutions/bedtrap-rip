/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  com.google.common.collect.Streams
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
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
    private final /* synthetic */ Setting<ActiveWhen> activeWhen;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean prevBaritoneAssumeStep;
    private final /* synthetic */ Setting<Integer> stepHealth;
    private final /* synthetic */ Setting<Boolean> safeStep;
    private /* synthetic */ float prevStepHeight;
    public final /* synthetic */ Setting<Double> height;

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlIllIlIIlllIII) {
        Step lllllllllllllllllIlIllIlIIllIllI;
        boolean lllllllllllllllllIlIllIlIIllIlll = lllllllllllllllllIlIllIlIIllIllI.activeWhen.get() == ActiveWhen.Always || lllllllllllllllllIlIllIlIIllIllI.activeWhen.get() == ActiveWhen.Sneaking && lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5715() || lllllllllllllllllIlIllIlIIllIllI.activeWhen.get() == ActiveWhen.NotSneaking && !lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5715();
        lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5857(lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5829().method_989(0.0, 1.0, 0.0));
        lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.field_6013 = lllllllllllllllllIlIllIlIIllIlll && (lllllllllllllllllIlIllIlIIllIllI.safeStep.get() == false || lllllllllllllllllIlIllIlIIllIllI.getHealth() > (float)lllllllllllllllllIlIllIlIIllIllI.stepHealth.get().intValue() && (double)lllllllllllllllllIlIllIlIIllIllI.getHealth() - lllllllllllllllllIlIllIlIIllIllI.getExplosionDamage() > (double)lllllllllllllllllIlIllIlIIllIllI.stepHealth.get().intValue()) ? lllllllllllllllllIlIllIlIIllIllI.height.get().floatValue() : lllllllllllllllllIlIllIlIIllIllI.prevStepHeight;
        lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5857(lllllllllllllllllIlIllIlIIllIllI.mc.field_1724.method_5829().method_989(0.0, -1.0, 0.0));
    }

    @Override
    public void onActivate() {
        Step lllllllllllllllllIlIllIlIIllllII;
        assert (lllllllllllllllllIlIllIlIIllllII.mc.field_1724 != null);
        lllllllllllllllllIlIllIlIIllllII.prevStepHeight = lllllllllllllllllIlIllIlIIllllII.mc.field_1724.field_6013;
        BaritoneAPI.getSettings().assumeStep.value = true;
    }

    private double getExplosionDamage() {
        Step lllllllllllllllllIlIllIlIIlIllII;
        assert (lllllllllllllllllIlIllIlIIlIllII.mc.field_1724 != null);
        assert (lllllllllllllllllIlIllIlIIlIllII.mc.field_1687 != null);
        Optional<class_1511> lllllllllllllllllIlIllIlIIlIlIll = Streams.stream((Iterable)lllllllllllllllllIlIllIlIIlIllII.mc.field_1687.method_18112()).filter(lllllllllllllllllIlIllIlIIIlIlll -> lllllllllllllllllIlIllIlIIIlIlll instanceof class_1511).filter(class_1297::method_5805).max(Comparator.comparingDouble(lllllllllllllllllIlIllIlIIIlllII -> {
            Step lllllllllllllllllIlIllIlIIIlllIl;
            return DamageCalcUtils.crystalDamage((class_1309)lllllllllllllllllIlIllIlIIIlllIl.mc.field_1724, lllllllllllllllllIlIllIlIIIlllII.method_19538());
        })).map(lllllllllllllllllIlIllIlIIlIIIIl -> (class_1511)lllllllllllllllllIlIllIlIIlIIIIl);
        return lllllllllllllllllIlIllIlIIlIlIll.map(lllllllllllllllllIlIllIlIIlIIlIl -> {
            Step lllllllllllllllllIlIllIlIIlIIllI;
            return DamageCalcUtils.crystalDamage((class_1309)lllllllllllllllllIlIllIlIIlIIllI.mc.field_1724, lllllllllllllllllIlIllIlIIlIIlIl.method_19538());
        }).orElse(0.0);
    }

    private float getHealth() {
        Step lllllllllllllllllIlIllIlIIlIllll;
        assert (lllllllllllllllllIlIllIlIIlIllll.mc.field_1724 != null);
        return lllllllllllllllllIlIllIlIIlIllll.mc.field_1724.method_6032() + lllllllllllllllllIlIllIlIIlIllll.mc.field_1724.method_6067();
    }

    public Step() {
        super(Categories.Movement, "step", "Allows you to walk up full blocks instantly.");
        Step lllllllllllllllllIlIllIlIIllllll;
        lllllllllllllllllIlIllIlIIllllll.sgGeneral = lllllllllllllllllIlIllIlIIllllll.settings.getDefaultGroup();
        lllllllllllllllllIlIllIlIIllllll.height = lllllllllllllllllIlIllIlIIllllll.sgGeneral.add(new DoubleSetting.Builder().name("height").description("Step height.").defaultValue(1.0).min(0.0).build());
        lllllllllllllllllIlIllIlIIllllll.activeWhen = lllllllllllllllllIlIllIlIIllllll.sgGeneral.add(new EnumSetting.Builder().name("active-when").description("Step is active when you meet these requirements.").defaultValue(ActiveWhen.Always).build());
        lllllllllllllllllIlIllIlIIllllll.safeStep = lllllllllllllllllIlIllIlIIllllll.sgGeneral.add(new BoolSetting.Builder().name("safe-step").description("Doesn't let you step out of a hole if you are low on health or there is a crystal nearby.").defaultValue(false).build());
        lllllllllllllllllIlIllIlIIllllll.stepHealth = lllllllllllllllllIlIllIlIIllllll.sgGeneral.add(new IntSetting.Builder().name("step-health").description("The health you stop being able to step at.").defaultValue(5).min(1).max(36).visible(lllllllllllllllllIlIllIlIIllllll.safeStep::get).build());
    }

    @Override
    public void onDeactivate() {
        Step lllllllllllllllllIlIllIlIIllIIll;
        if (lllllllllllllllllIlIllIlIIllIIll.mc.field_1724 != null) {
            lllllllllllllllllIlIllIlIIllIIll.mc.field_1724.field_6013 = lllllllllllllllllIlIllIlIIllIIll.prevStepHeight;
        }
        BaritoneAPI.getSettings().assumeStep.value = false;
    }

    public static enum ActiveWhen {
        Always,
        Sneaking,
        NotSneaking;


        private ActiveWhen() {
            ActiveWhen lllIIll;
        }
    }
}

