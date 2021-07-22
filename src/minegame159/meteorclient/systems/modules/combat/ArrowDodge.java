/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1665
 *  net.minecraft.class_1676
 *  net.minecraft.class_1922
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_259
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
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
    private final /* synthetic */ List<class_243> possibleMoveDirections;
    private final /* synthetic */ Setting<MoveType> moveType;
    private final /* synthetic */ Setting<Integer> arrowLookahead;
    private final /* synthetic */ Setting<Double> moveSpeed;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> groundCheck;
    private final /* synthetic */ SettingGroup sgMovement;

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllllIIIlIIIIIIllI) {
        ArrowDodge lllllllllllllllllllIIIlIIIIIIlll;
        class_238 lllllllllllllllllllIIIlIIIIIIlIl = lllllllllllllllllllIIIlIIIIIIlll.mc.field_1724.method_5829();
        lllllllllllllllllllIIIlIIIIIIlIl = lllllllllllllllllllIIIlIIIIIIlIl.method_1014(0.6);
        double lllllllllllllllllllIIIlIIIIIIlII = lllllllllllllllllllIIIlIIIIIIlll.moveSpeed.get();
        for (class_1297 lllllllllllllllllllIIIlIIIIIlIII : lllllllllllllllllllIIIlIIIIIIlll.mc.field_1687.method_18112()) {
            if (!(lllllllllllllllllllIIIlIIIIIlIII instanceof class_1676) || lllllllllllllllllllIIIlIIIIIlIII instanceof class_1665 && ((ProjectileInGroundAccessor)lllllllllllllllllllIIIlIIIIIlIII).getInGround()) continue;
            ArrayList<class_238> lllllllllllllllllllIIIlIIIIIlIIl = new ArrayList<class_238>();
            for (int lllllllllllllllllllIIIlIIIIlIIII = 0; lllllllllllllllllllIIIlIIIIlIIII < lllllllllllllllllllIIIlIIIIIIlll.arrowLookahead.get(); ++lllllllllllllllllllIIIlIIIIlIIII) {
                class_243 lllllllllllllllllllIIIlIIIIlIIIl = lllllllllllllllllllIIIlIIIIIlIII.method_19538().method_1019(lllllllllllllllllllIIIlIIIIIlIII.method_18798().method_1021((double)((float)lllllllllllllllllllIIIlIIIIlIIII / 5.0f)));
                lllllllllllllllllllIIIlIIIIIlIIl.add(new class_238(lllllllllllllllllllIIIlIIIIlIIIl.method_1023(lllllllllllllllllllIIIlIIIIIlIII.method_5829().method_17939() / 2.0, 0.0, lllllllllllllllllllIIIlIIIIIlIII.method_5829().method_17941() / 2.0), lllllllllllllllllllIIIlIIIIlIIIl.method_1031(lllllllllllllllllllIIIlIIIIIlIII.method_5829().method_17939() / 2.0, lllllllllllllllllllIIIlIIIIIlIII.method_5829().method_17940(), lllllllllllllllllllIIIlIIIIIlIII.method_5829().method_17941() / 2.0)));
            }
            for (class_238 lllllllllllllllllllIIIlIIIIIlIlI : lllllllllllllllllllIIIlIIIIIlIIl) {
                if (!lllllllllllllllllllIIIlIIIIIIlIl.method_994(lllllllllllllllllllIIIlIIIIIlIlI)) continue;
                Collections.shuffle(lllllllllllllllllllIIIlIIIIIIlll.possibleMoveDirections);
                boolean lllllllllllllllllllIIIlIIIIIlIll = false;
                for (class_243 lllllllllllllllllllIIIlIIIIIlllI : lllllllllllllllllllIIIlIIIIIIlll.possibleMoveDirections) {
                    class_243 lllllllllllllllllllIIIlIIIIIllll = lllllllllllllllllllIIIlIIIIIlllI.method_1021(lllllllllllllllllllIIIlIIIIIIlII);
                    if (!lllllllllllllllllllIIIlIIIIIIlll.isValid(lllllllllllllllllllIIIlIIIIIllll, lllllllllllllllllllIIIlIIIIIlIIl, lllllllllllllllllllIIIlIIIIIIlIl)) continue;
                    lllllllllllllllllllIIIlIIIIIIlll.move(lllllllllllllllllllIIIlIIIIIllll);
                    lllllllllllllllllllIIIlIIIIIlIll = true;
                    break;
                }
                if (lllllllllllllllllllIIIlIIIIIlIll) continue;
                double lllllllllllllllllllIIIlIIIIIllIl = Math.toRadians(lllllllllllllllllllIIIlIIIIIlIII.field_6031);
                double lllllllllllllllllllIIIlIIIIIllII = Math.toRadians(lllllllllllllllllllIIIlIIIIIlIII.field_5965);
                lllllllllllllllllllIIIlIIIIIIlll.move(Math.sin(lllllllllllllllllllIIIlIIIIIllIl) * Math.cos(lllllllllllllllllllIIIlIIIIIllII) * lllllllllllllllllllIIIlIIIIIIlII, Math.sin(lllllllllllllllllllIIIlIIIIIllII) * lllllllllllllllllllIIIlIIIIIIlII, -Math.cos(lllllllllllllllllllIIIlIIIIIllIl) * Math.cos(lllllllllllllllllllIIIlIIIIIllII) * lllllllllllllllllllIIIlIIIIIIlII);
            }
        }
    }

    private void move(class_243 lllllllllllllllllllIIIIlllllIlII) {
        ArrowDodge lllllllllllllllllllIIIIlllllIlIl;
        lllllllllllllllllllIIIIlllllIlIl.move(lllllllllllllllllllIIIIlllllIlII.field_1352, lllllllllllllllllllIIIIlllllIlII.field_1351, lllllllllllllllllllIIIIlllllIlII.field_1350);
    }

    private boolean isValid(class_243 lllllllllllllllllllIIIIlllIlIIlI, List<class_238> lllllllllllllllllllIIIIlllIlIllI, class_238 lllllllllllllllllllIIIIlllIlIlIl) {
        ArrowDodge lllllllllllllllllllIIIIlllIlIIll;
        class_2338 lllllllllllllllllllIIIIlllIlIlII = null;
        for (class_238 lllllllllllllllllllIIIIlllIllIIl : lllllllllllllllllllIIIIlllIlIllI) {
            class_238 lllllllllllllllllllIIIIlllIllIlI;
            if (lllllllllllllllllllIIIIlllIllIIl.method_994(lllllllllllllllllllIIIIlllIllIlI = lllllllllllllllllllIIIIlllIlIlIl.method_997(lllllllllllllllllllIIIIlllIlIIlI))) {
                return false;
            }
            lllllllllllllllllllIIIIlllIlIlII = lllllllllllllllllllIIIIlllIlIIll.mc.field_1724.method_24515().method_10080(lllllllllllllllllllIIIIlllIlIIlI.field_1352, lllllllllllllllllllIIIIlllIlIIlI.field_1351, lllllllllllllllllllIIIIlllIlIIlI.field_1350);
            if (lllllllllllllllllllIIIIlllIlIIll.mc.field_1687.method_8320(lllllllllllllllllllIIIIlllIlIlII).method_26220((class_1922)lllllllllllllllllllIIIIlllIlIIll.mc.field_1687, lllllllllllllllllllIIIIlllIlIlII) == class_259.method_1073()) continue;
            return false;
        }
        if (lllllllllllllllllllIIIIlllIlIIll.groundCheck.get().booleanValue() && lllllllllllllllllllIIIIlllIlIlII != null) {
            return lllllllllllllllllllIIIIlllIlIIll.mc.field_1687.method_8320(lllllllllllllllllllIIIIlllIlIlII.method_10074()).method_26220((class_1922)lllllllllllllllllllIIIIlllIlIIll.mc.field_1687, lllllllllllllllllllIIIIlllIlIlII.method_10074()) != class_259.method_1073();
        }
        return true;
    }

    private void move(double lllllllllllllllllllIIIIllllIIllI, double lllllllllllllllllllIIIIllllIIlIl, double lllllllllllllllllllIIIIllllIIlII) {
        ArrowDodge lllllllllllllllllllIIIIllllIlIll;
        switch (lllllllllllllllllllIIIIllllIlIll.moveType.get()) {
            case Client: {
                lllllllllllllllllllIIIIllllIlIll.mc.field_1724.method_18800(lllllllllllllllllllIIIIllllIIllI, lllllllllllllllllllIIIIllllIIlIl, lllllllllllllllllllIIIIllllIIlII);
                break;
            }
            case Packet: {
                class_243 lllllllllllllllllllIIIIllllIllII = lllllllllllllllllllIIIIllllIlIll.mc.field_1724.method_19538().method_1031(lllllllllllllllllllIIIIllllIIllI, lllllllllllllllllllIIIIllllIIlIl, lllllllllllllllllllIIIIllllIIlII);
                lllllllllllllllllllIIIIllllIlIll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lllllllllllllllllllIIIIllllIllII.field_1352, lllllllllllllllllllIIIIllllIllII.field_1351, lllllllllllllllllllIIIIllllIllII.field_1350, false));
                lllllllllllllllllllIIIIllllIlIll.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(lllllllllllllllllllIIIIllllIllII.field_1352, lllllllllllllllllllIIIIllllIllII.field_1351 - 0.01, lllllllllllllllllllIIIIllllIllII.field_1350, true));
            }
        }
    }

    public ArrowDodge() {
        super(Categories.Combat, "arrow-dodge", "Tries to dodge arrows coming at you");
        ArrowDodge lllllllllllllllllllIIIlIIIIlllll;
        lllllllllllllllllllIIIlIIIIlllll.sgGeneral = lllllllllllllllllllIIIlIIIIlllll.settings.getDefaultGroup();
        lllllllllllllllllllIIIlIIIIlllll.sgMovement = lllllllllllllllllllIIIlIIIIlllll.settings.createGroup("Movement");
        lllllllllllllllllllIIIlIIIIlllll.arrowLookahead = lllllllllllllllllllIIIlIIIIlllll.sgGeneral.add(new IntSetting.Builder().name("arrow-lookahead").description("How many steps into the future should be taken into consideration when deciding the direction").defaultValue(500).min(1).max(750).build());
        lllllllllllllllllllIIIlIIIIlllll.moveType = lllllllllllllllllllIIIlIIIIlllll.sgMovement.add(new EnumSetting.Builder().name("move-type").description("The way you are moved by this module").defaultValue(MoveType.Client).build());
        lllllllllllllllllllIIIlIIIIlllll.moveSpeed = lllllllllllllllllllIIIlIIIIlllll.sgMovement.add(new DoubleSetting.Builder().name("move-speed").description("How fast should you be when dodging arrow").defaultValue(1.0).min(0.01).sliderMax(5.0).build());
        lllllllllllllllllllIIIlIIIIlllll.groundCheck = lllllllllllllllllllIIIlIIIIlllll.sgGeneral.add(new BoolSetting.Builder().name("ground-check").description("Tries to prevent you from falling to your death.").defaultValue(true).build());
        lllllllllllllllllllIIIlIIIIlllll.possibleMoveDirections = Arrays.asList(new class_243[]{new class_243(1.0, 0.0, 1.0), new class_243(0.0, 0.0, 1.0), new class_243(-1.0, 0.0, 1.0), new class_243(1.0, 0.0, 0.0), new class_243(-1.0, 0.0, 0.0), new class_243(1.0, 0.0, -1.0), new class_243(0.0, 0.0, -1.0), new class_243(-1.0, 0.0, -1.0)});
    }

    public static enum MoveType {
        Client,
        Packet;


        private MoveType() {
            MoveType lIlIIlllllll;
        }
    }
}

