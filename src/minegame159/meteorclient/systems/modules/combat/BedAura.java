/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_1748;
import net.minecraft.class_1799;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_3965;

public class BedAura
extends Module {
    private final Setting<Boolean> autoMove;
    private final Setting<SortPriority> priority;
    private final Setting<Double> minHealth;
    private final Setting<Boolean> swapBack;
    private final SettingGroup sgMisc;
    private final Setting<Integer> autoMoveSlot;
    private final SettingGroup sgRender;
    private final Setting<Boolean> place;
    private final Setting<Double> maxSelfDamage;
    private final SettingGroup sgPlace;
    private final Setting<SettingColor> sideColor;
    private final Setting<Integer> placeDelay;
    private final Setting<Double> minDamage;
    private final Setting<Boolean> pauseOnEat;
    private final Setting<Boolean> autoSwitch;
    private final SettingGroup sgBreak;
    private final Setting<Integer> breakDelay;
    private final Setting<ShapeMode> shapeMode;
    private final SettingGroup sgPause;
    private class_1657 target;
    private int breakDelayLeft;
    private final Setting<Safety> breakMode;
    private final Setting<Boolean> pauseOnDrink;
    private final Setting<Double> targetRange;
    private Stage stage;
    private class_2350 direction;
    private class_2338 bestPos;
    private final Setting<Boolean> render;
    private final Setting<Boolean> pauseOnMine;
    private final Setting<SettingColor> lineColor;
    private final Setting<Boolean> noSwing;
    private int placeDelayLeft;
    private final Setting<Safety> placeMode;

    private float yawFromDir(class_2350 class_23502) {
        switch (class_23502) {
            case field_11034: {
                return 90.0f;
            }
            case field_11043: {
                return 0.0f;
            }
            case field_11035: {
                return 180.0f;
            }
            case field_11039: {
                return -90.0f;
            }
        }
        return 0.0f;
    }

    @Override
    public void onActivate() {
        this.stage = this.place.get() != false ? Stage.Placing : Stage.Breaking;
        this.bestPos = null;
        this.direction = class_2350.field_11034;
        this.placeDelayLeft = this.placeDelay.get();
        this.breakDelayLeft = this.placeDelay.get();
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.render.get().booleanValue() && this.bestPos != null) {
            int n = this.bestPos.method_10263();
            int n2 = this.bestPos.method_10264();
            int n3 = this.bestPos.method_10260();
            switch (this.direction) {
                case field_11043: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3, n + 1, (double)n2 + 0.6, n3 + 2, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case field_11035: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3 - 1, n + 1, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case field_11034: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n - 1, n2, n3, n + 1, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case field_11039: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3, n + 2, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                }
            }
        }
    }

    private class_2338 getBreakPos(class_1657 class_16572) {
        class_2338 class_23382 = class_16572.method_24515();
        if (this.checkBreak(class_2350.field_11043, class_16572, true)) {
            return class_23382.method_10084().method_10095();
        }
        if (this.checkBreak(class_2350.field_11035, class_16572, true)) {
            return class_23382.method_10084().method_10072();
        }
        if (this.checkBreak(class_2350.field_11034, class_16572, true)) {
            return class_23382.method_10084().method_10078();
        }
        if (this.checkBreak(class_2350.field_11039, class_16572, true)) {
            return class_23382.method_10084().method_10067();
        }
        if (this.checkBreak(class_2350.field_11043, class_16572, false)) {
            return class_23382.method_10095();
        }
        if (this.checkBreak(class_2350.field_11035, class_16572, false)) {
            return class_23382.method_10072();
        }
        if (this.checkBreak(class_2350.field_11034, class_16572, false)) {
            return class_23382.method_10078();
        }
        if (this.checkBreak(class_2350.field_11039, class_16572, false)) {
            return class_23382.method_10067();
        }
        return null;
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    private static boolean lambda$doAutoMove$4(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    private boolean checkBreak(class_2350 class_23502, class_1657 class_16572, boolean bl) {
        class_2338 class_23382;
        class_2338 class_23383 = class_23382 = bl ? class_16572.method_24515().method_10084() : class_16572.method_24515();
        if (this.mc.field_1687.method_8320(class_23382).method_26204() instanceof class_2244 && this.mc.field_1687.method_8320(class_23382.method_10093(class_23502)).method_26204() instanceof class_2244 && (this.breakMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)class_16572, Utils.vec3d(class_23382)) >= this.minDamage.get() && DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382.method_10093(class_23502))) < this.maxSelfDamage.get() && DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382)) < this.maxSelfDamage.get())) {
            this.direction = class_23502;
            return true;
        }
        return false;
    }

    private void doAutoMove() {
        FindItemResult findItemResult = InvUtils.find(BedAura::lambda$doAutoMove$4);
        if (findItemResult.found() && findItemResult.getSlot() != this.autoMoveSlot.get() - 1) {
            InvUtils.move().from(findItemResult.getSlot()).toHotbar(this.autoMoveSlot.get() - 1);
        }
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    private static boolean lambda$placeBed$2(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.mc.field_1687.method_8597().method_29956()) {
            this.error("You are in the Overworld... disabling!", new Object[0]);
            this.toggle();
            return;
        }
        if (PlayerUtils.shouldPause(this.pauseOnMine.get(), this.pauseOnEat.get(), this.pauseOnDrink.get())) {
            return;
        }
        if ((double)EntityUtils.getTotalHealth((class_1657)this.mc.field_1724) <= this.minHealth.get()) {
            return;
        }
        this.target = TargetUtils.getPlayerTarget(this.targetRange.get(), this.priority.get());
        if (this.target == null) {
            this.bestPos = null;
            return;
        }
        if (this.place.get().booleanValue() && InvUtils.find(BedAura::lambda$onTick$0).found()) {
            switch (this.stage) {
                case Placing: {
                    this.bestPos = this.getPlacePos(this.target);
                    if (this.placeDelayLeft > 0) {
                        --this.placeDelayLeft;
                    } else {
                        this.placeBed(this.bestPos);
                        this.placeDelayLeft = this.placeDelay.get();
                        this.stage = Stage.Breaking;
                    }
                }
                case Breaking: {
                    this.bestPos = this.getBreakPos(this.target);
                    if (this.breakDelayLeft > 0) {
                        --this.breakDelayLeft;
                        break;
                    }
                    this.breakBed(this.bestPos);
                    this.breakDelayLeft = this.breakDelay.get();
                    this.stage = Stage.Placing;
                }
            }
        } else {
            this.bestPos = this.getBreakPos(this.target);
            if (this.breakDelayLeft > 0) {
                --this.breakDelayLeft;
            } else {
                this.breakDelayLeft = this.breakDelay.get();
                this.breakBed(this.bestPos);
            }
        }
    }

    private boolean checkPlace(class_2350 class_23502, class_1657 class_16572, boolean bl) {
        class_2338 class_23382;
        class_2338 class_23383 = class_23382 = bl ? class_16572.method_24515().method_10084() : class_16572.method_24515();
        if (this.mc.field_1687.method_8320(class_23382).method_26207().method_15800() && BlockUtils.canPlace(class_23382.method_10093(class_23502)) && (this.placeMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)class_16572, Utils.vec3d(class_23382)) >= this.minDamage.get() && DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382.method_10093(class_23502))) < this.maxSelfDamage.get() && DamageCalcUtils.bedDamage((class_1309)this.mc.field_1724, Utils.vec3d(class_23382)) < this.maxSelfDamage.get())) {
            this.direction = class_23502;
            return true;
        }
        return false;
    }

    private void lambda$placeBed$3(class_2338 class_23382, FindItemResult findItemResult) {
        BlockUtils.place(class_23382, findItemResult, false, 0, this.noSwing.get() == false, true, this.swapBack.get());
    }

    private static boolean lambda$placeBed$1(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    private void placeBed(class_2338 class_23382) {
        FindItemResult findItemResult = InvUtils.find(BedAura::lambda$placeBed$1);
        if (findItemResult.isMain() && this.autoMove.get().booleanValue()) {
            this.doAutoMove();
        }
        if (!(findItemResult = InvUtils.findInHotbar(BedAura::lambda$placeBed$2)).found()) {
            return;
        }
        if (findItemResult.getHand() == null && !this.autoSwitch.get().booleanValue()) {
            return;
        }
        FindItemResult findItemResult2 = findItemResult;
        Rotations.rotate(this.yawFromDir(this.direction), this.mc.field_1724.field_5965, () -> this.lambda$placeBed$3(class_23382, findItemResult2));
    }

    public BedAura() {
        super(Categories.Combat, "bed-aura", "Automatically places and explodes beds in the Nether and End.");
        this.sgPlace = this.settings.createGroup("Place");
        this.sgBreak = this.settings.createGroup("Break");
        this.sgPause = this.settings.createGroup("Pause");
        this.sgMisc = this.settings.createGroup("Misc");
        this.sgRender = this.settings.createGroup("Render");
        this.place = this.sgPlace.add(new BoolSetting.Builder().name("place").description("Allows Bed Aura to place beds.").defaultValue(true).build());
        this.placeMode = this.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The way beds are allowed to be placed near you.").defaultValue(Safety.Safe).visible(this.place::get).build());
        this.placeDelay = this.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay for placing beds.").defaultValue(9).min(0).sliderMax(20).visible(this.place::get).build());
        this.breakDelay = this.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The tick delay for breaking beds.").defaultValue(0).min(0).sliderMax(20).build());
        this.breakMode = this.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The way beds are allowed to be broken near you.").defaultValue(Safety.Safe).build());
        this.pauseOnEat = this.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses while eating.").defaultValue(false).build());
        this.pauseOnDrink = this.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses while drinking potions.").defaultValue(false).build());
        this.pauseOnMine = this.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses while mining blocks.").defaultValue(false).build());
        this.targetRange = this.sgMisc.add(new DoubleSetting.Builder().name("range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.autoSwitch = this.sgMisc.add(new BoolSetting.Builder().name("auto-switch").description("Switches to a bed automatically.").defaultValue(true).build());
        this.swapBack = this.sgMisc.add(new BoolSetting.Builder().name("swap-back").description("Switches back to previous slot after placing.").defaultValue(true).build());
        this.autoMove = this.sgMisc.add(new BoolSetting.Builder().name("auto-move").description("Moves beds into a selected hotbar slot.").defaultValue(false).build());
        this.autoMoveSlot = this.sgMisc.add(new IntSetting.Builder().name("auto-move-slot").description("The slot Auto Move moves beds to.").defaultValue(9).min(1).sliderMin(1).max(9).sliderMax(9).visible(this.autoMove::get).build());
        this.noSwing = this.sgMisc.add(new BoolSetting.Builder().name("no-swing").description("Disables hand swings clientside.").defaultValue(false).build());
        this.minDamage = this.sgMisc.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage to inflict on your target.").defaultValue(7.0).min(0.0).sliderMax(20.0).max(20.0).build());
        this.maxSelfDamage = this.sgMisc.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum damage to inflict on yourself.").defaultValue(7.0).min(0.0).sliderMax(20.0).max(20.0).build());
        this.minHealth = this.sgMisc.add(new DoubleSetting.Builder().name("min-health").description("The minimum health required for Bed Aura to work.").defaultValue(4.0).min(0.0).sliderMax(36.0).max(36.0).build());
        this.priority = this.sgMisc.add(new EnumSetting.Builder().name("priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block where it is placing a bed.").defaultValue(true).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(15, 255, 211, 255)).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
    }

    private class_2338 getPlacePos(class_1657 class_16572) {
        class_2338 class_23382 = class_16572.method_24515();
        if (this.checkPlace(class_2350.field_11043, class_16572, true)) {
            return class_23382.method_10084().method_10095();
        }
        if (this.checkPlace(class_2350.field_11035, class_16572, true)) {
            return class_23382.method_10084().method_10072();
        }
        if (this.checkPlace(class_2350.field_11034, class_16572, true)) {
            return class_23382.method_10084().method_10078();
        }
        if (this.checkPlace(class_2350.field_11039, class_16572, true)) {
            return class_23382.method_10084().method_10067();
        }
        if (this.checkPlace(class_2350.field_11043, class_16572, false)) {
            return class_23382.method_10095();
        }
        if (this.checkPlace(class_2350.field_11035, class_16572, false)) {
            return class_23382.method_10072();
        }
        if (this.checkPlace(class_2350.field_11034, class_16572, false)) {
            return class_23382.method_10078();
        }
        if (this.checkPlace(class_2350.field_11039, class_16572, false)) {
            return class_23382.method_10067();
        }
        return null;
    }

    private void breakBed(class_2338 class_23382) {
        if (class_23382 == null) {
            return;
        }
        boolean bl = this.mc.field_1724.method_5715();
        if (bl) {
            this.mc.field_1724.method_5660(false);
        }
        this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5810, new class_3965(this.mc.field_1724.method_19538(), class_2350.field_11036, this.bestPos, false));
        this.mc.field_1724.method_5660(bl);
    }

    private static enum Stage {
        Placing,
        Breaking;

    }
}

