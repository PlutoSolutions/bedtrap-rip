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
    private final Setting<Double> horizontalPullStrength;
    private final ArrayList<class_2338> validBlocks;
    private final Setting<Integer> searchRadius;
    private final Setting<Double> activationWindow;
    private final SettingGroup sgGeneral;
    private final Setting<Double> successfulLandingMargin;
    private final Setting<Double> failsafeWindow;
    private final Setting<Boolean> updatePositionFailsafe;
    private final Setting<Integer> driftToHeight;
    private final TreeMap<Double, class_2338> sortedBlocks;
    private final Setting<Double> verticalPullStrength;
    private boolean successfulLanding;
    private final class_2338.class_2339 playerHorizontalPos;
    private final class_2338.class_2339 blockPos;

    public BedrockTravel() {
        super(Categories.BedTrap, "bedrock-travel", "Makes moving on bedrock easier.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.activationWindow = this.sgGeneral.add(new DoubleSetting.Builder().name("activation-window").description("The area above the target Y level at which pull activates.").min(0.2).max(5.0).sliderMin(0.2).sliderMax(5.0).defaultValue(0.5).build());
        this.driftToHeight = this.sgGeneral.add(new IntSetting.Builder().name("drift-to-height").description("Y level to find blocks to drift onto.").min(0).max(256).sliderMin(0).sliderMax(256).defaultValue(5).build());
        this.horizontalPullStrength = this.sgGeneral.add(new DoubleSetting.Builder().name("horizontal-pull").description("The horizontal speed/strength at which you drift to the goal block.").min(0.1).max(10.0).sliderMin(0.1).sliderMax(10.0).defaultValue(1.0).build());
        this.verticalPullStrength = this.sgGeneral.add(new DoubleSetting.Builder().name("vertical-pull").description("The vertical speed/strength at which you drift to the goal block.").min(0.1).max(10.0).sliderMin(0.1).sliderMax(10.0).defaultValue(1.0).build());
        this.searchRadius = this.sgGeneral.add(new IntSetting.Builder().name("search-radius").description("The radius at which tanuki mode searches for blocks (odd numbers only).").min(3).max(15).sliderMin(3).sliderMax(15).defaultValue(3).build());
        this.updatePositionFailsafe = this.sgGeneral.add(new BoolSetting.Builder().name("failsafe").description("Updates your position to the top of the target block if you miss the jump.").defaultValue(true).build());
        this.failsafeWindow = this.sgGeneral.add(new DoubleSetting.Builder().name("failsafe-window").description("Window below the target block to fall to trigger failsafe.").min(0.01).max(1.0).sliderMin(0.01).sliderMax(1.0).defaultValue(0.1).build());
        this.successfulLandingMargin = this.sgGeneral.add(new DoubleSetting.Builder().name("landing-margin").description("The distance from a landing block to be considered a successful landing.").min(0.01).max(10.0).sliderMin(0.01).sliderMax(10.0).defaultValue(1.0).build());
        this.blockPos = new class_2338.class_2339(0, 0, 0);
        this.validBlocks = new ArrayList();
        this.sortedBlocks = new TreeMap();
        this.playerHorizontalPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1724.method_23318() > (double)this.driftToHeight.get().intValue() + this.activationWindow.get()) {
            return;
        }
        class_243 class_2432 = this.findNearestBlock(this.mc.field_1724.method_23317(), this.driftToHeight.get() - 1, this.mc.field_1724.method_23321());
        if (class_2432 == null) {
            return;
        }
        if (this.mc.field_1724.method_23318() == class_2432.method_10214() + 1.0) {
            return;
        }
        if (this.mc.field_1690.field_1903.method_1434()) {
            return;
        }
        if (this.updatePositionFailsafe.get().booleanValue() && !this.successfulLanding && this.mc.field_1724.method_23318() < (double)this.driftToHeight.get().intValue() - this.failsafeWindow.get()) {
            this.mc.field_1724.method_5814(class_2432.method_10216(), class_2432.method_10214() + 1.0, class_2432.method_10215());
        }
        class_243 class_2433 = class_2432.method_1020(this.mc.field_1724.method_19538()).method_1029();
        ((IVec3d)this.mc.field_1724.method_18798()).set(this.mc.field_1724.method_18798().field_1352 + class_2433.field_1352 * this.horizontalPullStrength.get() * (double)this.mc.method_1488(), this.mc.field_1724.method_18798().field_1351 + class_2433.field_1351 * this.verticalPullStrength.get() * (double)this.mc.method_1488(), this.mc.field_1724.method_18798().field_1350 + class_2433.field_1350 * this.horizontalPullStrength.get() * (double)this.mc.method_1488());
        this.successfulLanding = this.mc.field_1724.method_19538().method_24802((class_2374)class_2432, this.successfulLandingMargin.get().doubleValue());
    }

    private class_243 findNearestBlock(double d, int n, double d2) {
        this.validBlocks.clear();
        this.sortedBlocks.clear();
        this.playerHorizontalPos.method_10102(d, (double)n, d2);
        int n2 = this.searchRadius.get() - 1;
        for (int i = 0; i < this.searchRadius.get(); ++i) {
            for (int j = 0; j < this.searchRadius.get(); ++j) {
                class_2680 class_26802 = this.mc.field_1687.method_8320((class_2338)this.blockPos.method_10102(d - (double)(n2 / 2 - i), (double)n, d2 - (double)(n2 / 2 - j)));
                if (class_26802.method_26215() || class_26802.method_26204() == class_2246.field_10164 || class_26802.method_26204() == class_2246.field_10382) continue;
                this.validBlocks.add((class_2338)this.blockPos.method_25503());
                if (!false) continue;
                return null;
            }
            if (null == null) continue;
            return null;
        }
        this.validBlocks.forEach(arg_0 -> this.lambda$findNearestBlock$0(d, n, d2, arg_0));
        Map.Entry<Double, class_2338> entry = this.sortedBlocks.firstEntry();
        if (entry == null) {
            return null;
        }
        return class_243.method_24955((class_2382)((class_2382)entry.getValue()));
    }

    private void lambda$findNearestBlock$0(double d, int n, double d2, class_2338 class_23382) {
        this.sortedBlocks.put(class_23382.method_10268(d, (double)n, d2, true), class_23382);
    }

    @Override
    public void onActivate() {
        if (this.searchRadius.get() % 2 == 0) {
            ChatUtils.info("%d is not valid for radius, rounding up", this.searchRadius.get());
            this.searchRadius.set(this.searchRadius.get() + 1);
        }
    }
}

