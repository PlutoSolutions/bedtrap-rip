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
    private final Setting<ShapeMode> shapeMode;
    private final Setting<SortPriority> targetPriority;
    private int breakDelayLeft;
    private final Setting<SettingColor> breakLineColor;
    private final Setting<Integer> breakDelay;
    private final SettingGroup sgBreak;
    private final SettingGroup sgPlace;
    private final Setting<Double> maxDamage;
    private final Setting<Safety> placeMode;
    private final SettingGroup sgRender;
    private final Setting<Boolean> renderPlace;
    private final Setting<Double> minHealth;
    private class_1657 target;
    private final Setting<Boolean> pauseOnEat;
    private final Setting<Integer> placeDelay;
    private final Setting<SettingColor> breakSideColor;
    private final Setting<Boolean> pauseOnMine;
    private final Setting<Double> breakRange;
    private final Setting<RotationMode> rotationMode;
    private final SettingGroup sgMisc;
    private int placeDelayLeft;
    private final Setting<Double> placeRange;
    private final SettingGroup sgPause;
    private final Setting<Boolean> pauseOnDrink;
    private final Setting<SettingColor> lineColor;
    private final Setting<Double> targetRange;
    private final Setting<PlaceMode> placePositions;
    private final Setting<Safety> breakMode;
    private final Setting<Boolean> place;
    private final Setting<SettingColor> sideColor;
    private final Setting<Boolean> renderBreak;

    private void breakAnchor(class_2338 class_23382, FindItemResult findItemResult, FindItemResult findItemResult2) {
        if (class_23382 == null || this.mc.field_1687.method_8320(class_23382).method_26204() != class_2246.field_23152) {
            return;
        }
        this.mc.field_1724.method_5660(false);
        int n = this.mc.field_1724.field_7514.field_7545;
        if (findItemResult2.isOffhand()) {
            this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5810, new class_3965(new class_243((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5), class_2350.field_11036, class_23382, true));
        } else {
            InvUtils.swap(findItemResult2.getSlot());
            this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(new class_243((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5), class_2350.field_11036, class_23382, true));
        }
        if (findItemResult.isOffhand()) {
            this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5810, new class_3965(new class_243((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5), class_2350.field_11036, class_23382, true));
        } else {
            InvUtils.swap(findItemResult.getSlot());
            this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(new class_243((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264() + 0.5, (double)class_23382.method_10260() + 0.5), class_2350.field_11036, class_23382, true));
        }
        InvUtils.swap(n);
    }

    @Override
    public void onActivate() {
        this.placeDelayLeft = 0;
        this.breakDelayLeft = 0;
        this.target = null;
    }

    private class_2338 findBreakPos(class_2338 class_23382) {
        if (this.isValidBreak(class_23382.method_10074())) {
            return class_23382.method_10074();
        }
        if (this.isValidBreak(class_23382.method_10086(2))) {
            return class_23382.method_10086(2);
        }
        if (this.isValidBreak(class_23382.method_10069(1, 0, 0))) {
            return class_23382.method_10069(1, 0, 0);
        }
        if (this.isValidBreak(class_23382.method_10069(-1, 0, 0))) {
            return class_23382.method_10069(-1, 0, 0);
        }
        if (this.isValidBreak(class_23382.method_10069(0, 0, 1))) {
            return class_23382.method_10069(0, 0, 1);
        }
        if (this.isValidBreak(class_23382.method_10069(0, 0, -1))) {
            return class_23382.method_10069(0, 0, -1);
        }
        if (this.isValidBreak(class_23382.method_10069(1, 1, 0))) {
            return class_23382.method_10069(1, 1, 0);
        }
        if (this.isValidBreak(class_23382.method_10069(-1, -1, 0))) {
            return class_23382.method_10069(-1, -1, 0);
        }
        if (this.isValidBreak(class_23382.method_10069(0, 1, 1))) {
            return class_23382.method_10069(0, 1, 1);
        }
        if (this.isValidBreak(class_23382.method_10069(0, 0, -1))) {
            return class_23382.method_10069(0, 0, -1);
        }
        return null;
    }

    private boolean getDamagePlace(class_2338 class_23382) {
        return this.placeMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382.method_10080(0.5, 0.5, 0.5))) <= this.maxDamage.get();
    }

    private boolean isValidBreak(class_2338 class_23382) {
        return this.mc.field_1687.method_8320(class_23382).method_26204() == class_2246.field_23152 && Math.sqrt(this.mc.field_1724.method_24515().method_10262((class_2382)class_23382)) <= this.breakRange.get() && this.getDamageBreak(class_23382);
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        class_2338 class_23382;
        if (this.mc.field_1687.method_8597().method_29957()) {
            this.error("You are in the Nether... disabling.", new Object[0]);
            this.toggle();
            return;
        }
        if (PlayerUtils.shouldPause(this.pauseOnMine.get(), this.pauseOnEat.get(), this.pauseOnDrink.get())) {
            return;
        }
        if ((double)EntityUtils.getTotalHealth((class_1657)this.mc.field_1724) <= this.minHealth.get()) {
            return;
        }
        if (TargetUtils.isBadTarget(this.target, this.targetRange.get())) {
            this.target = TargetUtils.getPlayerTarget(this.targetRange.get(), this.targetPriority.get());
        }
        if (TargetUtils.isBadTarget(this.target, this.targetRange.get())) {
            return;
        }
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_23141);
        FindItemResult findItemResult2 = InvUtils.findInHotbar(class_1802.field_8801);
        if (!findItemResult.found() || !findItemResult2.found()) {
            return;
        }
        if (this.breakDelayLeft >= this.breakDelay.get() && (class_23382 = this.findBreakPos(this.target.method_24515())) != null) {
            this.breakDelayLeft = 0;
            if (this.rotationMode.get() == RotationMode.Both || this.rotationMode.get() == RotationMode.Break) {
                Rotations.rotate(Rotations.getYaw(class_23382), Rotations.getPitch(class_23382), 50, () -> this.lambda$onTick$0(class_23382, findItemResult, findItemResult2));
            } else {
                this.breakAnchor(class_23382, findItemResult, findItemResult2);
            }
        }
        if (this.placeDelayLeft >= this.placeDelay.get() && this.place.get().booleanValue() && (class_23382 = this.findPlacePos(this.target.method_24515())) != null) {
            this.placeDelayLeft = 0;
            BlockUtils.place(class_23382, findItemResult, this.rotationMode.get() == RotationMode.Place || this.rotationMode.get() == RotationMode.Both, 50);
        }
        ++this.placeDelayLeft;
        ++this.breakDelayLeft;
    }

    private boolean isValidPlace(class_2338 class_23382) {
        return this.mc.field_1687.method_8320(class_23382).method_26215() && Math.sqrt(this.mc.field_1724.method_24515().method_10262((class_2382)class_23382)) <= this.placeRange.get() && this.getDamagePlace(class_23382);
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    public AnchorAura() {
        super(Categories.Combat, "anchor-aura", "Automatically places and breaks Respawn Anchors to harm entities.");
        this.sgPlace = this.settings.createGroup("Place");
        this.sgBreak = this.settings.createGroup("Break");
        this.sgPause = this.settings.createGroup("Pause");
        this.sgMisc = this.settings.createGroup("Misc");
        this.sgRender = this.settings.createGroup("Render");
        this.place = this.sgPlace.add(new BoolSetting.Builder().name("place").description("Allows Anchor Aura to place anchors.").defaultValue(true).build());
        this.placeDelay = this.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay between placing anchors.").defaultValue(2).min(0).max(20).visible(this.place::get).build());
        this.placeMode = this.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The way anchors are allowed to be placed near you.").defaultValue(Safety.Safe).visible(this.place::get).build());
        this.placeRange = this.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The radius in which anchors are placed in.").defaultValue(3.0).min(0.0).sliderMax(5.0).visible(this.place::get).build());
        this.placePositions = this.sgPlace.add(new EnumSetting.Builder().name("placement-positions").description("Where the Anchors will be placed on the entity.").defaultValue(PlaceMode.AboveAndBelow).visible(this.place::get).build());
        this.breakDelay = this.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The tick delay between breaking anchors.").defaultValue(10).min(0).max(10).build());
        this.breakMode = this.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The way anchors are allowed to be broken near you.").defaultValue(Safety.Safe).build());
        this.breakRange = this.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("The radius in which anchors are broken in.").defaultValue(3.0).min(0.0).sliderMax(5.0).build());
        this.pauseOnEat = this.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses while eating.").defaultValue(false).build());
        this.pauseOnDrink = this.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses while drinking potions.").defaultValue(false).build());
        this.pauseOnMine = this.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses while mining blocks.").defaultValue(false).build());
        this.rotationMode = this.sgMisc.add(new EnumSetting.Builder().name("rotation-mode").description("The mode to rotate you server-side.").defaultValue(RotationMode.Both).build());
        this.targetRange = this.sgMisc.add(new DoubleSetting.Builder().name("target-range").description("The radius in which players get targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.targetPriority = this.sgMisc.add(new EnumSetting.Builder().name("target-priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        this.maxDamage = this.sgMisc.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum self-damage allowed.").defaultValue(8.0).build());
        this.minHealth = this.sgMisc.add(new DoubleSetting.Builder().name("min-health").description("The minimum health you have to be for Anchor Aura to work.").defaultValue(15.0).build());
        this.renderPlace = this.sgRender.add(new BoolSetting.Builder().name("render-place").description("Renders the block where it is placing an anchor.").defaultValue(true).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(255, 0, 0, 75)).visible(this.renderPlace::get).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(255, 0, 0, 255)).visible(this.renderPlace::get).build());
        this.renderBreak = this.sgRender.add(new BoolSetting.Builder().name("render-break").description("Renders the block where it is breaking an anchor.").defaultValue(true).build());
        this.breakSideColor = this.sgRender.add(new ColorSetting.Builder().name("break-side-color").description("The side color for anchors to be broken.").defaultValue(new SettingColor(255, 0, 0, 75)).visible(this.renderBreak::get).build());
        this.breakLineColor = this.sgRender.add(new ColorSetting.Builder().name("break-line-color").description("The line color for anchors to be broken.").defaultValue(new SettingColor(255, 0, 0, 255)).visible(this.renderBreak::get).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
    }

    private class_2338 findPlacePos(class_2338 class_23382) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$AnchorAura$PlaceMode[this.placePositions.get().ordinal()]) {
            case 1: {
                if (this.isValidPlace(class_23382.method_10074())) {
                    return class_23382.method_10074();
                }
                if (this.isValidPlace(class_23382.method_10086(2))) {
                    return class_23382.method_10086(2);
                }
                if (this.isValidPlace(class_23382.method_10069(1, 0, 0))) {
                    return class_23382.method_10069(1, 0, 0);
                }
                if (this.isValidPlace(class_23382.method_10069(-1, 0, 0))) {
                    return class_23382.method_10069(-1, 0, 0);
                }
                if (this.isValidPlace(class_23382.method_10069(0, 0, 1))) {
                    return class_23382.method_10069(0, 0, 1);
                }
                if (this.isValidPlace(class_23382.method_10069(0, 0, -1))) {
                    return class_23382.method_10069(0, 0, -1);
                }
                if (this.isValidPlace(class_23382.method_10069(1, 1, 0))) {
                    return class_23382.method_10069(1, 1, 0);
                }
                if (this.isValidPlace(class_23382.method_10069(-1, -1, 0))) {
                    return class_23382.method_10069(-1, -1, 0);
                }
                if (this.isValidPlace(class_23382.method_10069(0, 1, 1))) {
                    return class_23382.method_10069(0, 1, 1);
                }
                if (!this.isValidPlace(class_23382.method_10069(0, 0, -1))) break;
                return class_23382.method_10069(0, 0, -1);
            }
            case 2: {
                if (!this.isValidPlace(class_23382.method_10086(2))) break;
                return class_23382.method_10086(2);
            }
            case 3: {
                if (this.isValidPlace(class_23382.method_10074())) {
                    return class_23382.method_10074();
                }
                if (!this.isValidPlace(class_23382.method_10086(2))) break;
                return class_23382.method_10086(2);
            }
        }
        return null;
    }

    private boolean getDamageBreak(class_2338 class_23382) {
        return this.breakMode.get() == Safety.Suicide || DamageCalcUtils.anchorDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382.method_10080(0.5, 0.5, 0.5))) <= this.maxDamage.get();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.target != null) {
            class_2338 class_23382;
            if (this.renderPlace.get().booleanValue() && (class_23382 = this.findPlacePos(this.target.method_24515())) != null) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260(), 1.0, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
            }
            if (this.renderBreak.get().booleanValue() && (class_23382 = this.findBreakPos(this.target.method_24515())) != null) {
                Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, class_23382.method_10263(), class_23382.method_10264(), class_23382.method_10260(), 1.0, this.breakSideColor.get(), this.breakLineColor.get(), this.shapeMode.get(), 0);
            }
        }
    }

    private void lambda$onTick$0(class_2338 class_23382, FindItemResult findItemResult, FindItemResult findItemResult2) {
        this.breakAnchor(class_23382, findItemResult, findItemResult2);
    }

    @Override
    public void onDeactivate() {
        this.target = null;
    }

    public static enum PlaceMode {
        Above,
        AboveAndBelow,
        All;

    }

    public static enum RotationMode {
        Place,
        Break,
        Both,
        None;

    }
}

