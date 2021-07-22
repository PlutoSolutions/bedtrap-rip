/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;

public class AutoCity
extends Module {
    private final /* synthetic */ Setting<Boolean> selfToggle;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ class_2338 blockPosTarget;
    private /* synthetic */ boolean sentMessage;
    private final /* synthetic */ Setting<Double> targetRange;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<Boolean> support;

    public AutoCity() {
        super(Categories.Combat, "auto-city", "Automatically cities a target by mining the nearest obsidian next to them.");
        AutoCity llllllllllllllllllIIlllIIlIllllI;
        llllllllllllllllllIIlllIIlIllllI.sgGeneral = llllllllllllllllllIIlllIIlIllllI.settings.getDefaultGroup();
        llllllllllllllllllIIlllIIlIllllI.targetRange = llllllllllllllllllIIlllIIlIllllI.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllllIIlllIIlIllllI.support = llllllllllllllllllIIlllIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("support").description("If there is no block below a city block it will place one before mining.").defaultValue(true).build());
        llllllllllllllllllIIlllIIlIllllI.rotate = llllllllllllllllllIIlllIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Automatically rotates you towards the city block.").defaultValue(true).build());
        llllllllllllllllllIIlllIIlIllllI.selfToggle = llllllllllllllllllIIlllIIlIllllI.sgGeneral.add(new BoolSetting.Builder().name("self-toggle").description("Automatically toggles off after activation.").defaultValue(true).build());
    }

    private void mine(class_2338 llllllllllllllllllIIlllIIlIIllll) {
        AutoCity llllllllllllllllllIIlllIIlIlIIlI;
        llllllllllllllllllIIlllIIlIlIIlI.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, llllllllllllllllllIIlllIIlIIllll, class_2350.field_11036));
        llllllllllllllllllIIlllIIlIlIIlI.mc.field_1724.method_6104(class_1268.field_5808);
        llllllllllllllllllIIlllIIlIlIIlI.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, llllllllllllllllllIIlllIIlIIllll, class_2350.field_11036));
    }

    @Override
    public String getInfoString() {
        AutoCity llllllllllllllllllIIlllIIlIIllII;
        if (llllllllllllllllllIIlllIIlIIllII.target != null) {
            return llllllllllllllllllIIlllIIlIIllII.target.method_5820();
        }
        return null;
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllllIIlllIIlIllIII) {
        FindItemResult llllllllllllllllllIIlllIIlIlIlll;
        AutoCity llllllllllllllllllIIlllIIlIlIllI;
        if (TargetUtils.isBadTarget(llllllllllllllllllIIlllIIlIlIllI.target, llllllllllllllllllIIlllIIlIlIllI.targetRange.get())) {
            class_1657 llllllllllllllllllIIlllIIlIllIlI = TargetUtils.getPlayerTarget(llllllllllllllllllIIlllIIlIlIllI.targetRange.get(), SortPriority.LowestDistance);
            if (llllllllllllllllllIIlllIIlIllIlI != llllllllllllllllllIIlllIIlIlIllI.target) {
                llllllllllllllllllIIlllIIlIlIllI.sentMessage = false;
            }
            llllllllllllllllllIIlllIIlIlIllI.target = llllllllllllllllllIIlllIIlIllIlI;
        }
        if (TargetUtils.isBadTarget(llllllllllllllllllIIlllIIlIlIllI.target, llllllllllllllllllIIlllIIlIlIllI.targetRange.get())) {
            llllllllllllllllllIIlllIIlIlIllI.target = null;
            llllllllllllllllllIIlllIIlIlIllI.blockPosTarget = null;
            if (llllllllllllllllllIIlllIIlIlIllI.selfToggle.get().booleanValue()) {
                llllllllllllllllllIIlllIIlIlIllI.toggle();
            }
            return;
        }
        llllllllllllllllllIIlllIIlIlIllI.blockPosTarget = EntityUtils.getCityBlock(llllllllllllllllllIIlllIIlIlIllI.target);
        if (llllllllllllllllllIIlllIIlIlIllI.blockPosTarget == null) {
            if (llllllllllllllllllIIlllIIlIlIllI.selfToggle.get().booleanValue()) {
                llllllllllllllllllIIlllIIlIlIllI.error("No target block found... disabling.", new Object[0]);
                llllllllllllllllllIIlllIIlIlIllI.toggle();
            }
            llllllllllllllllllIIlllIIlIlIllI.target = null;
            return;
        }
        if (PlayerUtils.distanceTo(llllllllllllllllllIIlllIIlIlIllI.blockPosTarget) > (double)llllllllllllllllllIIlllIIlIlIllI.mc.field_1761.method_2904() && llllllllllllllllllIIlllIIlIlIllI.selfToggle.get().booleanValue()) {
            llllllllllllllllllIIlllIIlIlIllI.error("Target block out of reach... disabling.", new Object[0]);
            llllllllllllllllllIIlllIIlIlIllI.toggle();
            return;
        }
        if (!llllllllllllllllllIIlllIIlIlIllI.sentMessage) {
            llllllllllllllllllIIlllIIlIlIllI.info("Attempting to city %s.", llllllllllllllllllIIlllIIlIlIllI.target.method_5820());
            llllllllllllllllllIIlllIIlIlIllI.sentMessage = true;
        }
        if (!(llllllllllllllllllIIlllIIlIlIlll = InvUtils.find(llllllllllllllllllIIlllIIlIIIllI -> llllllllllllllllllIIlllIIlIIIllI.method_7909() == class_1802.field_8377 || llllllllllllllllllIIlllIIlIIIllI.method_7909() == class_1802.field_22024)).isHotbar()) {
            if (llllllllllllllllllIIlllIIlIlIllI.selfToggle.get().booleanValue()) {
                llllllllllllllllllIIlllIIlIlIllI.error("No pickaxe found... disabling.", new Object[0]);
                llllllllllllllllllIIlllIIlIlIllI.toggle();
            }
            return;
        }
        if (llllllllllllllllllIIlllIIlIlIllI.support.get().booleanValue()) {
            BlockUtils.place(llllllllllllllllllIIlllIIlIlIllI.blockPosTarget.method_10087(1), InvUtils.findInHotbar(class_1802.field_8281), llllllllllllllllllIIlllIIlIlIllI.rotate.get(), 0, true);
        }
        InvUtils.swap(llllllllllllllllllIIlllIIlIlIlll.getSlot());
        if (llllllllllllllllllIIlllIIlIlIllI.rotate.get().booleanValue()) {
            Rotations.rotate(Rotations.getYaw(llllllllllllllllllIIlllIIlIlIllI.blockPosTarget), Rotations.getPitch(llllllllllllllllllIIlllIIlIlIllI.blockPosTarget), () -> {
                AutoCity llllllllllllllllllIIlllIIlIIlIlI;
                llllllllllllllllllIIlllIIlIIlIlI.mine(llllllllllllllllllIIlllIIlIIlIlI.blockPosTarget);
            });
        } else {
            llllllllllllllllllIIlllIIlIlIllI.mine(llllllllllllllllllIIlllIIlIlIllI.blockPosTarget);
        }
        if (llllllllllllllllllIIlllIIlIlIllI.selfToggle.get().booleanValue()) {
            llllllllllllllllllIIlllIIlIlIllI.toggle();
        }
    }
}

