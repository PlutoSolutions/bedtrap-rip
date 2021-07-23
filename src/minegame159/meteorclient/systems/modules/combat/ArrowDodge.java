/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.ProjectileInGroundAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1297;
import net.minecraft.class_1665;
import net.minecraft.class_1676;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class ArrowDodge
extends Module {
    private final List<class_243> possibleMoveDirections;
    private final Setting<MoveType> moveType;
    private final Setting<Integer> arrowLookahead;
    private final Setting<Double> moveSpeed;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> groundCheck;
    private final SettingGroup sgMovement;

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        class_238 class_2383 = this.mc.field_1724.method_5829();
        class_2383 = class_2383.method_1014(0.6);
        double d = this.moveSpeed.get();
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1676) || class_12972 instanceof class_1665 && ((ProjectileInGroundAccessor)class_12972).getInGround()) continue;
            ArrayList<class_238> arrayList = new ArrayList<class_238>();
            for (int i = 0; i < this.arrowLookahead.get(); ++i) {
                class_238 class_2384 = class_12972.method_19538().method_1019(class_12972.method_18798().method_1021((double)((float)i / 5.0f)));
                arrayList.add(new class_238(class_2384.method_1023(class_12972.method_5829().method_17939() / 2.0, 0.0, class_12972.method_5829().method_17941() / 2.0), class_2384.method_1031(class_12972.method_5829().method_17939() / 2.0, class_12972.method_5829().method_17940(), class_12972.method_5829().method_17941() / 2.0)));
                if (3 > 0) continue;
                return;
            }
            for (class_238 class_2384 : arrayList) {
                if (!class_2383.method_994(class_2384)) continue;
                Collections.shuffle(this.possibleMoveDirections);
                boolean bl = false;
                for (class_243 class_2432 : this.possibleMoveDirections) {
                    class_243 class_2433 = class_2432.method_1021(d);
                    if (!this.isValid(class_2433, arrayList, class_2383)) continue;
                    this.move(class_2433);
                    bl = true;
                    break;
                }
                if (bl) continue;
                double d2 = Math.toRadians(class_12972.field_6031);
                double d3 = Math.toRadians(class_12972.field_5965);
                this.move(Math.sin(d2) * Math.cos(d3) * d, Math.sin(d3) * d, -Math.cos(d2) * Math.cos(d3) * d);
            }
        }
    }

    private void move(class_243 class_2432) {
        this.move(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
    }

    private boolean isValid(class_243 class_2432, List<class_238> list, class_238 class_2383) {
        class_2338 class_23382 = null;
        for (class_238 class_2384 : list) {
            class_238 class_2386;
            if (class_2384.method_994(class_2386 = class_2383.method_997(class_2432))) {
                return false;
            }
            class_23382 = this.mc.field_1724.method_24515().method_10080(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
            if (this.mc.field_1687.method_8320(class_23382).method_26220((class_1922)this.mc.field_1687, class_23382) == class_259.method_1073()) continue;
            return false;
        }
        if (this.groundCheck.get().booleanValue() && class_23382 != null) {
            return this.mc.field_1687.method_8320(class_23382.method_10074()).method_26220((class_1922)this.mc.field_1687, class_23382.method_10074()) != class_259.method_1073();
        }
        return true;
    }

    private void move(double d, double d2, double d3) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$ArrowDodge$MoveType[this.moveType.get().ordinal()]) {
            case 1: {
                this.mc.field_1724.method_18800(d, d2, d3);
                break;
            }
            case 2: {
                class_243 class_2432 = this.mc.field_1724.method_19538().method_1031(d, d2, d3);
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350, false));
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(class_2432.field_1352, class_2432.field_1351 - 0.01, class_2432.field_1350, true));
            }
        }
    }

    public ArrowDodge() {
        super(Categories.Combat, "arrow-dodge", "Tries to dodge arrows coming at you");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgMovement = this.settings.createGroup("Movement");
        this.arrowLookahead = this.sgGeneral.add(new IntSetting.Builder().name("arrow-lookahead").description("How many steps into the future should be taken into consideration when deciding the direction").defaultValue(500).min(1).max(750).build());
        this.moveType = this.sgMovement.add(new EnumSetting.Builder().name("move-type").description("The way you are moved by this module").defaultValue(MoveType.Client).build());
        this.moveSpeed = this.sgMovement.add(new DoubleSetting.Builder().name("move-speed").description("How fast should you be when dodging arrow").defaultValue(1.0).min(0.01).sliderMax(5.0).build());
        this.groundCheck = this.sgGeneral.add(new BoolSetting.Builder().name("ground-check").description("Tries to prevent you from falling to your death.").defaultValue(true).build());
        this.possibleMoveDirections = Arrays.asList(new class_243(1.0, 0.0, 1.0), new class_243(0.0, 0.0, 1.0), new class_243(-1.0, 0.0, 1.0), new class_243(1.0, 0.0, 0.0), new class_243(-1.0, 0.0, 0.0), new class_243(1.0, 0.0, -1.0), new class_243(0.0, 0.0, -1.0), new class_243(-1.0, 0.0, -1.0));
    }

    public static enum MoveType {
        Client,
        Packet;

    }
}

