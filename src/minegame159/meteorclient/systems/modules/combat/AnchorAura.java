/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.player.Safety;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public class AnchorAura
extends Module {
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<SortPriority> targetPriority;
    private /* synthetic */ int breakDelayLeft;
    private final /* synthetic */ Setting<SettingColor> breakLineColor;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ SettingGroup sgBreak;
    private final /* synthetic */ SettingGroup sgPlace;
    private final /* synthetic */ Setting<Double> maxDamage;
    private final /* synthetic */ Setting<Safety> placeMode;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Boolean> renderPlace;
    private final /* synthetic */ Setting<Double> minHealth;
    private /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<Boolean> pauseOnEat;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<SettingColor> breakSideColor;
    private final /* synthetic */ Setting<Boolean> pauseOnMine;
    private final /* synthetic */ Setting<Double> breakRange;
    private final /* synthetic */ Setting<RotationMode> rotationMode;
    private final /* synthetic */ SettingGroup sgMisc;
    private /* synthetic */ int placeDelayLeft;
    private final /* synthetic */ Setting<Double> placeRange;
    private final /* synthetic */ SettingGroup sgPause;
    private final /* synthetic */ Setting<Boolean> pauseOnDrink;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ Setting<PlaceMode> placePositions;
    private final /* synthetic */ Setting<Safety> breakMode;
    private final /* synthetic */ Setting<Boolean> place;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Boolean> renderBreak;

    private void breakAnchor(class_2338 llllllllllllllllllIllIlllIlllllI, FindItemResult llllllllllllllllllIllIlllIlllIII, FindItemResult llllllllllllllllllIllIlllIllIlll) {
        AnchorAura llllllllllllllllllIllIlllIllllll;
        if (llllllllllllllllllIllIlllIlllllI == null || llllllllllllllllllIllIlllIllllll.mc.field_1687.method_8320(llllllllllllllllllIllIlllIlllllI).method_26204() != class_2246.field_23152) {
            return;
        }
        llllllllllllllllllIllIlllIllllll.mc.field_1724.method_5660(false);
        int llllllllllllllllllIllIlllIlllIll = llllllllllllllllllIllIlllIllllll.mc.field_1724.field_7514.field_7545;
        if (llllllllllllllllllIllIlllIllIlll.isOffhand()) {
            llllllllllllllllllIllIlllIllllll.mc.field_1761.method_2896(llllllllllllllllllIllIlllIllllll.mc.field_1724, llllllllllllllllllIllIlllIllllll.mc.field_1687, class_1268.field_5810, new class_3965(new class_243((double)llllllllllllllllllIllIlllIlllllI.method_10263() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10264() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10260() + 0.5), class_2350.field_11036, llllllllllllllllllIllIlllIlllllI, true));
        } else {
            InvUtils.swap(llllllllllllllllllIllIlllIllIlll.getSlot());
            llllllllllllllllllIllIlllIllllll.mc.field_1761.method_2896(llllllllllllllllllIllIlllIllllll.mc.field_1724, llllllllllllllllllIllIlllIllllll.mc.field_1687, class_1268.field_5808, new class_3965(new class_243((double)llllllllllllllllllIllIlllIlllllI.method_10263() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10264() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10260() + 0.5), class_2350.field_11036, llllllllllllllllllIllIlllIlllllI, true));
        }
        if (llllllllllllllllllIllIlllIlllIII.isOffhand()) {
            llllllllllllllllllIllIlllIllllll.mc.field_1761.method_2896(llllllllllllllllllIllIlllIllllll.mc.field_1724, llllllllllllllllllIllIlllIllllll.mc.field_1687, class_1268.field_5810, new class_3965(new class_243((double)llllllllllllllllllIllIlllIlllllI.method_10263() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10264() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10260() + 0.5), class_2350.field_11036, llllllllllllllllllIllIlllIlllllI, true));
        } else {
            InvUtils.swap(llllllllllllllllllIllIlllIlllIII.getSlot());
            llllllllllllllllllIllIlllIllllll.mc.field_1761.method_2896(llllllllllllllllllIllIlllIllllll.mc.field_1724, llllllllllllllllllIllIlllIllllll.mc.field_1687, class_1268.field_5808, new class_3965(new class_243((double)llllllllllllllllllIllIlllIlllllI.method_10263() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10264() + 0.5, (double)llllllllllllllllllIllIlllIlllllI.method_10260() + 0.5), class_2350.field_11036, llllllllllllllllllIllIlllIlllllI, true));
        }
        InvUtils.swap(llllllllllllllllllIllIlllIlllIll);
    }

    @Override
    public void onActivate() {
        llllllllllllllllllIlllIIIIIIIIll.placeDelayLeft = 0;
        llllllllllllllllllIlllIIIIIIIIll.breakDelayLeft = 0;
        llllllllllllllllllIlllIIIIIIIIll.target = null;
    }

    private class_2338 findBreakPos(class_2338 llllllllllllllllllIllIllllIlllll) {
        AnchorAura llllllllllllllllllIllIlllllIIIII;
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10074())) {
            return llllllllllllllllllIllIllllIlllll.method_10074();
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10086(2))) {
            return llllllllllllllllllIllIllllIlllll.method_10086(2);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(1, 0, 0))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(1, 0, 0);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(-1, 0, 0))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(-1, 0, 0);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(0, 0, 1))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(0, 0, 1);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(0, 0, -1))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(0, 0, -1);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(1, 1, 0))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(1, 1, 0);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(-1, -1, 0))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(-1, -1, 0);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(0, 1, 1))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(0, 1, 1);
        }
        if (llllllllllllllllllIllIlllllIIIII.isValidBreak(llllllllllllllllllIllIllllIlllll.method_10069(0, 0, -1))) {
            return llllllllllllllllllIllIllllIlllll.method_10069(0, 0, -1);
        }
        return null;
    }

    private boolean getDamagePlace(class_2338 llllllllllllllllllIllIllllIlIlll) {
        AnchorAura llllllllllllllllllIllIllllIllIlI;
        return llllllllllllllllllIllIllllIllIlI.placeMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)llllllllllllllllllIllIllllIllIlI.mc.field_1724, Utils.vec3d(llllllllllllllllllIllIllllIlIlll.method_10080(0.5, 0.5, 0.5))) <= llllllllllllllllllIllIllllIllIlI.maxDamage.get();
    }

    private boolean isValidBreak(class_2338 llllllllllllllllllIllIllllIIIlll) {
        AnchorAura llllllllllllllllllIllIllllIIIllI;
        return llllllllllllllllllIllIllllIIIllI.mc.field_1687.method_8320(llllllllllllllllllIllIllllIIIlll).method_26204() == class_2246.field_23152 && Math.sqrt(llllllllllllllllllIllIllllIIIllI.mc.field_1724.method_24515().method_10262((class_2382)llllllllllllllllllIllIllllIIIlll)) <= llllllllllllllllllIllIllllIIIllI.breakRange.get() && llllllllllllllllllIllIllllIIIllI.getDamageBreak(llllllllllllllllllIllIllllIIIlll);
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIllIllllllIlll) {
        class_2338 llllllllllllllllllIllIlllllllIIl;
        class_2338 llllllllllllllllllIllIlllllllIlI;
        AnchorAura llllllllllllllllllIllIllllllIlII;
        if (llllllllllllllllllIllIllllllIlII.mc.field_1687.method_8597().method_29957()) {
            llllllllllllllllllIllIllllllIlII.error("You are in the Nether... disabling.", new Object[0]);
            llllllllllllllllllIllIllllllIlII.toggle();
            return;
        }
        if (PlayerUtils.shouldPause(llllllllllllllllllIllIllllllIlII.pauseOnMine.get(), llllllllllllllllllIllIllllllIlII.pauseOnEat.get(), llllllllllllllllllIllIllllllIlII.pauseOnDrink.get())) {
            return;
        }
        if ((double)EntityUtils.getTotalHealth((class_1657)llllllllllllllllllIllIllllllIlII.mc.field_1724) <= llllllllllllllllllIllIllllllIlII.minHealth.get()) {
            return;
        }
        if (TargetUtils.isBadTarget(llllllllllllllllllIllIllllllIlII.target, llllllllllllllllllIllIllllllIlII.targetRange.get())) {
            llllllllllllllllllIllIllllllIlII.target = TargetUtils.getPlayerTarget(llllllllllllllllllIllIllllllIlII.targetRange.get(), llllllllllllllllllIllIllllllIlII.targetPriority.get());
        }
        if (TargetUtils.isBadTarget(llllllllllllllllllIllIllllllIlII.target, llllllllllllllllllIllIllllllIlII.targetRange.get())) {
            return;
        }
        FindItemResult llllllllllllllllllIllIllllllIllI = InvUtils.findInHotbar(class_1802.field_23141);
        FindItemResult llllllllllllllllllIllIllllllIlIl = InvUtils.findInHotbar(class_1802.field_8801);
        if (!llllllllllllllllllIllIllllllIllI.found() || !llllllllllllllllllIllIllllllIlIl.found()) {
            return;
        }
        if (llllllllllllllllllIllIllllllIlII.breakDelayLeft >= llllllllllllllllllIllIllllllIlII.breakDelay.get() && (llllllllllllllllllIllIlllllllIlI = llllllllllllllllllIllIllllllIlII.findBreakPos(llllllllllllllllllIllIllllllIlII.target.method_24515())) != null) {
            llllllllllllllllllIllIllllllIlII.breakDelayLeft = 0;
            if (llllllllllllllllllIllIllllllIlII.rotationMode.get() == RotationMode.Both || llllllllllllllllllIllIllllllIlII.rotationMode.get() == RotationMode.Break) {
                Rotations.rotate(Rotations.getYaw(llllllllllllllllllIllIlllllllIlI), Rotations.getPitch(llllllllllllllllllIllIlllllllIlI), 50, () -> {
                    AnchorAura llllllllllllllllllIllIlllIlIlllI;
                    llllllllllllllllllIllIlllIlIlllI.breakAnchor(llllllllllllllllllIllIlllllllIlI, llllllllllllllllllIllIllllllIllI, llllllllllllllllllIllIllllllIlIl);
                });
            } else {
                llllllllllllllllllIllIllllllIlII.breakAnchor(llllllllllllllllllIllIlllllllIlI, llllllllllllllllllIllIllllllIllI, llllllllllllllllllIllIllllllIlIl);
            }
        }
        if (llllllllllllllllllIllIllllllIlII.placeDelayLeft >= llllllllllllllllllIllIllllllIlII.placeDelay.get() && llllllllllllllllllIllIllllllIlII.place.get().booleanValue() && (llllllllllllllllllIllIlllllllIIl = llllllllllllllllllIllIllllllIlII.findPlacePos(llllllllllllllllllIllIllllllIlII.target.method_24515())) != null) {
            llllllllllllllllllIllIllllllIlII.placeDelayLeft = 0;
            BlockUtils.place(llllllllllllllllllIllIlllllllIIl, llllllllllllllllllIllIllllllIllI, llllllllllllllllllIllIllllllIlII.rotationMode.get() == RotationMode.Place || llllllllllllllllllIllIllllllIlII.rotationMode.get() == RotationMode.Both, 50);
        }
        ++llllllllllllllllllIllIllllllIlII.placeDelayLeft;
        ++llllllllllllllllllIllIllllllIlII.breakDelayLeft;
    }

    private boolean isValidPlace(class_2338 llllllllllllllllllIllIllllIIlIll) {
        AnchorAura llllllllllllllllllIllIllllIIllII;
        return llllllllllllllllllIllIllllIIllII.mc.field_1687.method_8320(llllllllllllllllllIllIllllIIlIll).method_26215() && Math.sqrt(llllllllllllllllllIllIllllIIllII.mc.field_1724.method_24515().method_10262((class_2382)llllllllllllllllllIllIllllIIlIll)) <= llllllllllllllllllIllIllllIIllII.placeRange.get() && llllllllllllllllllIllIllllIIllII.getDamagePlace(llllllllllllllllllIllIllllIIlIll);
    }

    @Override
    public String getInfoString() {
        AnchorAura llllllllllllllllllIllIlllIllIlII;
        if (llllllllllllllllllIllIlllIllIlII.target != null) {
            return llllllllllllllllllIllIlllIllIlII.target.method_5820();
        }
        return null;
    }

    public AnchorAura() {
        super(Categories.Combat, "anchor-aura", "Automatically places and breaks Respawn Anchors to harm entities.");
        AnchorAura llllllllllllllllllIlllIIIIIIIlIl;
        llllllllllllllllllIlllIIIIIIIlIl.sgPlace = llllllllllllllllllIlllIIIIIIIlIl.settings.createGroup("Place");
        llllllllllllllllllIlllIIIIIIIlIl.sgBreak = llllllllllllllllllIlllIIIIIIIlIl.settings.createGroup("Break");
        llllllllllllllllllIlllIIIIIIIlIl.sgPause = llllllllllllllllllIlllIIIIIIIlIl.settings.createGroup("Pause");
        llllllllllllllllllIlllIIIIIIIlIl.sgMisc = llllllllllllllllllIlllIIIIIIIlIl.settings.createGroup("Misc");
        llllllllllllllllllIlllIIIIIIIlIl.sgRender = llllllllllllllllllIlllIIIIIIIlIl.settings.createGroup("Render");
        llllllllllllllllllIlllIIIIIIIlIl.place = llllllllllllllllllIlllIIIIIIIlIl.sgPlace.add(new BoolSetting.Builder().name("place").description("Allows Anchor Aura to place anchors.").defaultValue(true).build());
        llllllllllllllllllIlllIIIIIIIlIl.placeDelay = llllllllllllllllllIlllIIIIIIIlIl.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay between placing anchors.").defaultValue(2).min(0).max(20).visible(llllllllllllllllllIlllIIIIIIIlIl.place::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.placeMode = llllllllllllllllllIlllIIIIIIIlIl.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The way anchors are allowed to be placed near you.").defaultValue(Safety.Safe).visible(llllllllllllllllllIlllIIIIIIIlIl.place::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.placeRange = llllllllllllllllllIlllIIIIIIIlIl.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The radius in which anchors are placed in.").defaultValue(3.0).min(0.0).sliderMax(5.0).visible(llllllllllllllllllIlllIIIIIIIlIl.place::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.placePositions = llllllllllllllllllIlllIIIIIIIlIl.sgPlace.add(new EnumSetting.Builder().name("placement-positions").description("Where the Anchors will be placed on the entity.").defaultValue(PlaceMode.AboveAndBelow).visible(llllllllllllllllllIlllIIIIIIIlIl.place::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.breakDelay = llllllllllllllllllIlllIIIIIIIlIl.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The tick delay between breaking anchors.").defaultValue(10).min(0).max(10).build());
        llllllllllllllllllIlllIIIIIIIlIl.breakMode = llllllllllllllllllIlllIIIIIIIlIl.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The way anchors are allowed to be broken near you.").defaultValue(Safety.Safe).build());
        llllllllllllllllllIlllIIIIIIIlIl.breakRange = llllllllllllllllllIlllIIIIIIIlIl.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("The radius in which anchors are broken in.").defaultValue(3.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllllIlllIIIIIIIlIl.pauseOnEat = llllllllllllllllllIlllIIIIIIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses while eating.").defaultValue(false).build());
        llllllllllllllllllIlllIIIIIIIlIl.pauseOnDrink = llllllllllllllllllIlllIIIIIIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses while drinking potions.").defaultValue(false).build());
        llllllllllllllllllIlllIIIIIIIlIl.pauseOnMine = llllllllllllllllllIlllIIIIIIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses while mining blocks.").defaultValue(false).build());
        llllllllllllllllllIlllIIIIIIIlIl.rotationMode = llllllllllllllllllIlllIIIIIIIlIl.sgMisc.add(new EnumSetting.Builder().name("rotation-mode").description("The mode to rotate you server-side.").defaultValue(RotationMode.Both).build());
        llllllllllllllllllIlllIIIIIIIlIl.targetRange = llllllllllllllllllIlllIIIIIIIlIl.sgMisc.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllllIlllIIIIIIIlIl.targetPriority = llllllllllllllllllIlllIIIIIIIlIl.sgMisc.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        llllllllllllllllllIlllIIIIIIIlIl.maxDamage = llllllllllllllllllIlllIIIIIIIlIl.sgMisc.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum self-damage allowed.").defaultValue(8.0).build());
        llllllllllllllllllIlllIIIIIIIlIl.minHealth = llllllllllllllllllIlllIIIIIIIlIl.sgMisc.add(new DoubleSetting.Builder().name("min-health").description("The minimum health you have to be for Anchor Aura to work.").defaultValue(15.0).build());
        llllllllllllllllllIlllIIIIIIIlIl.renderPlace = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new BoolSetting.Builder().name("render-place").description("Renders the block where it is placing an anchor.").defaultValue(true).build());
        llllllllllllllllllIlllIIIIIIIlIl.sideColor = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(255, 0, 0, 75)).visible(llllllllllllllllllIlllIIIIIIIlIl.renderPlace::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.lineColor = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(255, 0, 0, 255)).visible(llllllllllllllllllIlllIIIIIIIlIl.renderPlace::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.renderBreak = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new BoolSetting.Builder().name("render-break").description("Renders the block where it is breaking an anchor.").defaultValue(true).build());
        llllllllllllllllllIlllIIIIIIIlIl.breakSideColor = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new ColorSetting.Builder().name("break-side-color").description("The side color for anchors to be broken.").defaultValue(new SettingColor(255, 0, 0, 75)).visible(llllllllllllllllllIlllIIIIIIIlIl.renderBreak::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.breakLineColor = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new ColorSetting.Builder().name("break-line-color").description("The line color for anchors to be broken.").defaultValue(new SettingColor(255, 0, 0, 255)).visible(llllllllllllllllllIlllIIIIIIIlIl.renderBreak::get).build());
        llllllllllllllllllIlllIIIIIIIlIl.shapeMode = llllllllllllllllllIlllIIIIIIIlIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
    }

    private class_2338 findPlacePos(class_2338 llllllllllllllllllIllIlllllIIlIl) {
        AnchorAura llllllllllllllllllIllIlllllIIlII;
        switch (llllllllllllllllllIllIlllllIIlII.placePositions.get()) {
            case All: {
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10074())) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10074();
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10086(2))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10086(2);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(1, 0, 0))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(1, 0, 0);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(-1, 0, 0))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(-1, 0, 0);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, 1))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, 1);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, -1))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, -1);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(1, 1, 0))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(1, 1, 0);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(-1, -1, 0))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(-1, -1, 0);
                }
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(0, 1, 1))) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10069(0, 1, 1);
                }
                if (!llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, -1))) break;
                return llllllllllllllllllIllIlllllIIlIl.method_10069(0, 0, -1);
            }
            case Above: {
                if (!llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10086(2))) break;
                return llllllllllllllllllIllIlllllIIlIl.method_10086(2);
            }
            case AboveAndBelow: {
                if (llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10074())) {
                    return llllllllllllllllllIllIlllllIIlIl.method_10074();
                }
                if (!llllllllllllllllllIllIlllllIIlII.isValidPlace(llllllllllllllllllIllIlllllIIlIl.method_10086(2))) break;
                return llllllllllllllllllIllIlllllIIlIl.method_10086(2);
            }
        }
        return null;
    }

    private boolean getDamageBreak(class_2338 llllllllllllllllllIllIllllIlIIIl) {
        AnchorAura llllllllllllllllllIllIllllIlIIlI;
        return llllllllllllllllllIllIllllIlIIlI.breakMode.get() == Safety.Suicide || DamageCalcUtils.anchorDamage((class_1309)llllllllllllllllllIllIllllIlIIlI.mc.field_1724, Utils.vec3d(llllllllllllllllllIllIllllIlIIIl.method_10080(0.5, 0.5, 0.5))) <= llllllllllllllllllIllIllllIlIIlI.maxDamage.get();
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllllIllIlllllIlIll) {
        AnchorAura llllllllllllllllllIllIlllllIllII;
        if (llllllllllllllllllIllIlllllIllII.target != null) {
            class_2338 llllllllllllllllllIllIlllllIllIl;
            class_2338 llllllllllllllllllIllIlllllIlllI;
            if (llllllllllllllllllIllIlllllIllII.renderPlace.get().booleanValue() && (llllllllllllllllllIllIlllllIlllI = llllllllllllllllllIllIlllllIllII.findPlacePos(llllllllllllllllllIllIlllllIllII.target.method_24515())) != null) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllllIllIlllllIlllI.method_10263(), llllllllllllllllllIllIlllllIlllI.method_10264(), llllllllllllllllllIllIlllllIlllI.method_10260(), 1.0, llllllllllllllllllIllIlllllIllII.sideColor.get(), llllllllllllllllllIllIlllllIllII.lineColor.get(), llllllllllllllllllIllIlllllIllII.shapeMode.get(), 0);
            }
            if (llllllllllllllllllIllIlllllIllII.renderBreak.get().booleanValue() && (llllllllllllllllllIllIlllllIllIl = llllllllllllllllllIllIlllllIllII.findBreakPos(llllllllllllllllllIllIlllllIllII.target.method_24515())) != null) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllllIllIlllllIllIl.method_10263(), llllllllllllllllllIllIlllllIllIl.method_10264(), llllllllllllllllllIllIlllllIllIl.method_10260(), 1.0, llllllllllllllllllIllIlllllIllII.breakSideColor.get(), llllllllllllllllllIllIlllllIllII.breakLineColor.get(), llllllllllllllllllIllIlllllIllII.shapeMode.get(), 0);
            }
        }
    }

    @Override
    public void onDeactivate() {
        llllllllllllllllllIllIllllllllll.target = null;
    }

    public static enum PlaceMode {
        Above,
        AboveAndBelow,
        All;


        private PlaceMode() {
            PlaceMode llllllllllllllllllIIIlIlIlIllIIl;
        }
    }

    public static enum RotationMode {
        Place,
        Break,
        Both,
        None;


        private RotationMode() {
            RotationMode llllllllllllllllllIlIlIllIIllIII;
        }
    }
}

