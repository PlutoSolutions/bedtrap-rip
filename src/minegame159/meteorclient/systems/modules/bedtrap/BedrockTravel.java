/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2374
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class BedrockTravel
extends Module {
    private final /* synthetic */ Setting<Double> horizontalPullStrength;
    private final /* synthetic */ ArrayList<class_2338> validBlocks;
    private final /* synthetic */ Setting<Integer> searchRadius;
    private final /* synthetic */ Setting<Double> activationWindow;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Double> successfulLandingMargin;
    private final /* synthetic */ Setting<Double> failsafeWindow;
    private final /* synthetic */ Setting<Boolean> updatePositionFailsafe;
    private final /* synthetic */ Setting<Integer> driftToHeight;
    private final /* synthetic */ TreeMap<Double, class_2338> sortedBlocks;
    private final /* synthetic */ Setting<Double> verticalPullStrength;
    private /* synthetic */ boolean successfulLanding;
    private final /* synthetic */ class_2338.class_2339 playerHorizontalPos;
    private final /* synthetic */ class_2338.class_2339 blockPos;

    public BedrockTravel() {
        super(Categories.BedTrap, "bedrock-travel", "Makes moving on bedrock easier.");
        BedrockTravel lIllllIlIIIIIlI;
        lIllllIlIIIIIlI.sgGeneral = lIllllIlIIIIIlI.settings.getDefaultGroup();
        lIllllIlIIIIIlI.activationWindow = lIllllIlIIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("activation-window").description("The area above the target Y level at which pull activates.").min(0.2).max(5.0).sliderMin(0.2).sliderMax(5.0).defaultValue(0.5).build());
        lIllllIlIIIIIlI.driftToHeight = lIllllIlIIIIIlI.sgGeneral.add(new IntSetting.Builder().name("drift-to-height").description("Y level to find blocks to drift onto.").min(0).max(256).sliderMin(0).sliderMax(256).defaultValue(5).build());
        lIllllIlIIIIIlI.horizontalPullStrength = lIllllIlIIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("horizontal-pull").description("The horizontal speed/strength at which you drift to the goal block.").min(0.1).max(10.0).sliderMin(0.1).sliderMax(10.0).defaultValue(1.0).build());
        lIllllIlIIIIIlI.verticalPullStrength = lIllllIlIIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("vertical-pull").description("The vertical speed/strength at which you drift to the goal block.").min(0.1).max(10.0).sliderMin(0.1).sliderMax(10.0).defaultValue(1.0).build());
        lIllllIlIIIIIlI.searchRadius = lIllllIlIIIIIlI.sgGeneral.add(new IntSetting.Builder().name("search-radius").description("The radius at which tanuki mode searches for blocks (odd numbers only).").min(3).max(15).sliderMin(3).sliderMax(15).defaultValue(3).build());
        lIllllIlIIIIIlI.updatePositionFailsafe = lIllllIlIIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("failsafe").description("Updates your position to the top of the target block if you miss the jump.").defaultValue(true).build());
        lIllllIlIIIIIlI.failsafeWindow = lIllllIlIIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("failsafe-window").description("Window below the target block to fall to trigger failsafe.").min(0.01).max(1.0).sliderMin(0.01).sliderMax(1.0).defaultValue(0.1).build());
        lIllllIlIIIIIlI.successfulLandingMargin = lIllllIlIIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("landing-margin").description("The distance from a landing block to be considered a successful landing.").min(0.01).max(10.0).sliderMin(0.01).sliderMax(10.0).defaultValue(1.0).build());
        lIllllIlIIIIIlI.blockPos = new class_2338.class_2339(0, 0, 0);
        lIllllIlIIIIIlI.validBlocks = new ArrayList();
        lIllllIlIIIIIlI.sortedBlocks = new TreeMap();
        lIllllIlIIIIIlI.playerHorizontalPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onTick(TickEvent.Post lIllllIIllllIlI) {
        BedrockTravel lIllllIIlllIlll;
        if (lIllllIIlllIlll.mc.field_1724.method_23318() > (double)lIllllIIlllIlll.driftToHeight.get().intValue() + lIllllIIlllIlll.activationWindow.get()) {
            return;
        }
        class_243 lIllllIIllllIIl = lIllllIIlllIlll.findNearestBlock(lIllllIIlllIlll.mc.field_1724.method_23317(), lIllllIIlllIlll.driftToHeight.get() - 1, lIllllIIlllIlll.mc.field_1724.method_23321());
        if (lIllllIIllllIIl == null) {
            return;
        }
        if (lIllllIIlllIlll.mc.field_1724.method_23318() == lIllllIIllllIIl.method_10214() + 1.0) {
            return;
        }
        if (lIllllIIlllIlll.mc.field_1690.field_1903.method_1434()) {
            return;
        }
        if (lIllllIIlllIlll.updatePositionFailsafe.get().booleanValue() && !lIllllIIlllIlll.successfulLanding && lIllllIIlllIlll.mc.field_1724.method_23318() < (double)lIllllIIlllIlll.driftToHeight.get().intValue() - lIllllIIlllIlll.failsafeWindow.get()) {
            lIllllIIlllIlll.mc.field_1724.method_5814(lIllllIIllllIIl.method_10216(), lIllllIIllllIIl.method_10214() + 1.0, lIllllIIllllIIl.method_10215());
        }
        class_243 lIllllIIllllIII = lIllllIIllllIIl.method_1020(lIllllIIlllIlll.mc.field_1724.method_19538()).method_1029();
        ((IVec3d)lIllllIIlllIlll.mc.field_1724.method_18798()).set(lIllllIIlllIlll.mc.field_1724.method_18798().field_1352 + lIllllIIllllIII.field_1352 * lIllllIIlllIlll.horizontalPullStrength.get() * (double)lIllllIIlllIlll.mc.method_1488(), lIllllIIlllIlll.mc.field_1724.method_18798().field_1351 + lIllllIIllllIII.field_1351 * lIllllIIlllIlll.verticalPullStrength.get() * (double)lIllllIIlllIlll.mc.method_1488(), lIllllIIlllIlll.mc.field_1724.method_18798().field_1350 + lIllllIIllllIII.field_1350 * lIllllIIlllIlll.horizontalPullStrength.get() * (double)lIllllIIlllIlll.mc.method_1488());
        lIllllIIlllIlll.successfulLanding = lIllllIIlllIlll.mc.field_1724.method_19538().method_24802((class_2374)lIllllIIllllIIl, lIllllIIlllIlll.successfulLandingMargin.get().doubleValue());
    }

    private class_243 findNearestBlock(double lIllllIIllIIIlI, int lIllllIIllIIIIl, double lIllllIIllIIllI) {
        BedrockTravel lIllllIIllIlIIl;
        lIllllIIllIlIIl.validBlocks.clear();
        lIllllIIllIlIIl.sortedBlocks.clear();
        lIllllIIllIlIIl.playerHorizontalPos.method_10102(lIllllIIllIIIlI, (double)lIllllIIllIIIIl, lIllllIIllIIllI);
        int lIllllIIllIIlIl = lIllllIIllIlIIl.searchRadius.get() - 1;
        for (int lIllllIIllIlIlI = 0; lIllllIIllIlIlI < lIllllIIllIlIIl.searchRadius.get(); ++lIllllIIllIlIlI) {
            for (int lIllllIIllIlIll = 0; lIllllIIllIlIll < lIllllIIllIlIIl.searchRadius.get(); ++lIllllIIllIlIll) {
                class_2680 lIllllIIllIllII = lIllllIIllIlIIl.mc.field_1687.method_8320((class_2338)lIllllIIllIlIIl.blockPos.method_10102(lIllllIIllIIIlI - (double)(lIllllIIllIIlIl / 2 - lIllllIIllIlIlI), (double)lIllllIIllIIIIl, lIllllIIllIIllI - (double)(lIllllIIllIIlIl / 2 - lIllllIIllIlIll)));
                if (lIllllIIllIllII.method_26215() || lIllllIIllIllII.method_26204() == class_2246.field_10164 || lIllllIIllIllII.method_26204() == class_2246.field_10382) continue;
                lIllllIIllIlIIl.validBlocks.add((class_2338)lIllllIIllIlIIl.blockPos.method_25503());
            }
        }
        lIllllIIllIlIIl.validBlocks.forEach(lIllllIIlIIllIl -> {
            BedrockTravel lIllllIIlIlIllI;
            lIllllIIlIlIllI.sortedBlocks.put(lIllllIIlIIllIl.method_10268(lIllllIIllIIIlI, (double)lIllllIIllIIIIl, lIllllIIllIIllI, true), (class_2338)lIllllIIlIIllIl);
        });
        Map.Entry<Double, class_2338> lIllllIIllIIlII = lIllllIIllIlIIl.sortedBlocks.firstEntry();
        if (lIllllIIllIIlII == null) {
            return null;
        }
        return class_243.method_24955((class_2382)((class_2382)lIllllIIllIIlII.getValue()));
    }

    @Override
    public void onActivate() {
        BedrockTravel lIllllIlIIIIIII;
        if (lIllllIlIIIIIII.searchRadius.get() % 2 == 0) {
            ChatUtils.info("%d is not valid for radius, rounding up", lIllllIlIIIIIII.searchRadius.get());
            lIllllIlIIIIIII.searchRadius.set(lIllllIlIIIIIII.searchRadius.get() + 1);
        }
    }
}

