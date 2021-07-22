/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1748
 *  net.minecraft.class_2244
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
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
import net.minecraft.class_1748;
import net.minecraft.class_2244;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_3965;

public class BedAura
extends Module {
    private final /* synthetic */ Setting<Boolean> autoMove;
    private final /* synthetic */ Setting<SortPriority> priority;
    private final /* synthetic */ Setting<Double> minHealth;
    private final /* synthetic */ Setting<Boolean> swapBack;
    private final /* synthetic */ SettingGroup sgMisc;
    private final /* synthetic */ Setting<Integer> autoMoveSlot;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Boolean> place;
    private final /* synthetic */ Setting<Double> maxSelfDamage;
    private final /* synthetic */ SettingGroup sgPlace;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Double> minDamage;
    private final /* synthetic */ Setting<Boolean> pauseOnEat;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    private final /* synthetic */ SettingGroup sgBreak;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ SettingGroup sgPause;
    private /* synthetic */ class_1657 target;
    private /* synthetic */ int breakDelayLeft;
    private final /* synthetic */ Setting<Safety> breakMode;
    private final /* synthetic */ Setting<Boolean> pauseOnDrink;
    private final /* synthetic */ Setting<Double> targetRange;
    private /* synthetic */ Stage stage;
    private /* synthetic */ class_2350 direction;
    private /* synthetic */ class_2338 bestPos;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Boolean> pauseOnMine;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Boolean> noSwing;
    private /* synthetic */ int placeDelayLeft;
    private final /* synthetic */ Setting<Safety> placeMode;

    private float yawFromDir(class_2350 llllIlIIIllIlII) {
        switch (llllIlIIIllIlII) {
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
        BedAura llllIlIlIIIIlll;
        llllIlIlIIIIlll.stage = llllIlIlIIIIlll.place.get() != false ? Stage.Placing : Stage.Breaking;
        llllIlIlIIIIlll.bestPos = null;
        llllIlIlIIIIlll.direction = class_2350.field_11034;
        llllIlIlIIIIlll.placeDelayLeft = llllIlIlIIIIlll.placeDelay.get();
        llllIlIlIIIIlll.breakDelayLeft = llllIlIlIIIIlll.placeDelay.get();
    }

    @EventHandler
    private void onRender(RenderEvent llllIlIIIlIlIll) {
        BedAura llllIlIIIlIllII;
        if (llllIlIIIlIllII.render.get().booleanValue() && llllIlIIIlIllII.bestPos != null) {
            int llllIlIIIlIllll = llllIlIIIlIllII.bestPos.method_10263();
            int llllIlIIIlIlllI = llllIlIIIlIllII.bestPos.method_10264();
            int llllIlIIIlIllIl = llllIlIIIlIllII.bestPos.method_10260();
            switch (llllIlIIIlIllII.direction) {
                case field_11043: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllIlIIIlIllll, llllIlIIIlIlllI, llllIlIIIlIllIl, llllIlIIIlIllll + 1, (double)llllIlIIIlIlllI + 0.6, llllIlIIIlIllIl + 2, llllIlIIIlIllII.sideColor.get(), llllIlIIIlIllII.lineColor.get(), llllIlIIIlIllII.shapeMode.get(), 0);
                    break;
                }
                case field_11035: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllIlIIIlIllll, llllIlIIIlIlllI, llllIlIIIlIllIl - 1, llllIlIIIlIllll + 1, (double)llllIlIIIlIlllI + 0.6, llllIlIIIlIllIl + 1, llllIlIIIlIllII.sideColor.get(), llllIlIIIlIllII.lineColor.get(), llllIlIIIlIllII.shapeMode.get(), 0);
                    break;
                }
                case field_11034: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllIlIIIlIllll - 1, llllIlIIIlIlllI, llllIlIIIlIllIl, llllIlIIIlIllll + 1, (double)llllIlIIIlIlllI + 0.6, llllIlIIIlIllIl + 1, llllIlIIIlIllII.sideColor.get(), llllIlIIIlIllII.lineColor.get(), llllIlIIIlIllII.shapeMode.get(), 0);
                    break;
                }
                case field_11039: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllIlIIIlIllll, llllIlIIIlIlllI, llllIlIIIlIllIl, llllIlIIIlIllll + 2, (double)llllIlIIIlIlllI + 0.6, llllIlIIIlIllIl + 1, llllIlIIIlIllII.sideColor.get(), llllIlIIIlIllII.lineColor.get(), llllIlIIIlIllII.shapeMode.get(), 0);
                }
            }
        }
    }

    private class_2338 getBreakPos(class_1657 llllIlIIlIIlllI) {
        BedAura llllIlIIlIlIIlI;
        class_2338 llllIlIIlIlIIII = llllIlIIlIIlllI.method_24515();
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11043, llllIlIIlIIlllI, true)) {
            return llllIlIIlIlIIII.method_10084().method_10095();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11035, llllIlIIlIIlllI, true)) {
            return llllIlIIlIlIIII.method_10084().method_10072();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11034, llllIlIIlIIlllI, true)) {
            return llllIlIIlIlIIII.method_10084().method_10078();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11039, llllIlIIlIIlllI, true)) {
            return llllIlIIlIlIIII.method_10084().method_10067();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11043, llllIlIIlIIlllI, false)) {
            return llllIlIIlIlIIII.method_10095();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11035, llllIlIIlIIlllI, false)) {
            return llllIlIIlIlIIII.method_10072();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11034, llllIlIIlIIlllI, false)) {
            return llllIlIIlIlIIII.method_10078();
        }
        if (llllIlIIlIlIIlI.checkBreak(class_2350.field_11039, llllIlIIlIIlllI, false)) {
            return llllIlIIlIlIIII.method_10067();
        }
        return null;
    }

    private boolean checkBreak(class_2350 llllIlIIlIIIllI, class_1657 llllIlIIlIIIIII, boolean llllIlIIlIIIlII) {
        BedAura llllIlIIlIIIlll;
        class_2338 llllIlIIlIIIIll;
        class_2338 class_23382 = llllIlIIlIIIIll = llllIlIIlIIIlII ? llllIlIIlIIIIII.method_24515().method_10084() : llllIlIIlIIIIII.method_24515();
        if (llllIlIIlIIIlll.mc.field_1687.method_8320(llllIlIIlIIIIll).method_26204() instanceof class_2244 && llllIlIIlIIIlll.mc.field_1687.method_8320(llllIlIIlIIIIll.method_10093(llllIlIIlIIIllI)).method_26204() instanceof class_2244 && (llllIlIIlIIIlll.breakMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)llllIlIIlIIIIII, Utils.vec3d(llllIlIIlIIIIll)) >= llllIlIIlIIIlll.minDamage.get() && DamageCalcUtils.bedDamage((class_1309)llllIlIIlIIIlll.mc.field_1724, Utils.vec3d(llllIlIIlIIIIll.method_10093(llllIlIIlIIIllI))) < llllIlIIlIIIlll.maxSelfDamage.get() && DamageCalcUtils.bedDamage((class_1309)llllIlIIlIIIlll.mc.field_1724, Utils.vec3d(llllIlIIlIIIIll)) < llllIlIIlIIIlll.maxSelfDamage.get())) {
            llllIlIIlIIIlll.direction = llllIlIIlIIIllI;
            return true;
        }
        return false;
    }

    private void doAutoMove() {
        BedAura llllIlIIIlllIIl;
        FindItemResult llllIlIIIlllIlI = InvUtils.find(llllIlIIIlIIIIl -> llllIlIIIlIIIIl.method_7909() instanceof class_1748);
        if (llllIlIIIlllIlI.found() && llllIlIIIlllIlI.getSlot() != llllIlIIIlllIIl.autoMoveSlot.get() - 1) {
            InvUtils.move().from(llllIlIIIlllIlI.getSlot()).toHotbar(llllIlIIIlllIIl.autoMoveSlot.get() - 1);
        }
    }

    @Override
    public String getInfoString() {
        BedAura llllIlIIIlIIlIl;
        if (llllIlIIIlIIlIl.target != null) {
            return llllIlIIIlIIlIl.target.method_5820();
        }
        return null;
    }

    @EventHandler
    private void onTick(TickEvent.Post llllIlIlIIIIlII) {
        BedAura llllIlIlIIIIlIl;
        if (llllIlIlIIIIlIl.mc.field_1687.method_8597().method_29956()) {
            llllIlIlIIIIlIl.error("You are in the Overworld... disabling!", new Object[0]);
            llllIlIlIIIIlIl.toggle();
            return;
        }
        if (PlayerUtils.shouldPause(llllIlIlIIIIlIl.pauseOnMine.get(), llllIlIlIIIIlIl.pauseOnEat.get(), llllIlIlIIIIlIl.pauseOnDrink.get())) {
            return;
        }
        if ((double)EntityUtils.getTotalHealth((class_1657)llllIlIlIIIIlIl.mc.field_1724) <= llllIlIlIIIIlIl.minHealth.get()) {
            return;
        }
        llllIlIlIIIIlIl.target = TargetUtils.getPlayerTarget(llllIlIlIIIIlIl.targetRange.get(), llllIlIlIIIIlIl.priority.get());
        if (llllIlIlIIIIlIl.target == null) {
            llllIlIlIIIIlIl.bestPos = null;
            return;
        }
        if (llllIlIlIIIIlIl.place.get().booleanValue() && InvUtils.find(llllIlIIIIIllll -> llllIlIIIIIllll.method_7909() instanceof class_1748).found()) {
            switch (llllIlIlIIIIlIl.stage) {
                case Placing: {
                    llllIlIlIIIIlIl.bestPos = llllIlIlIIIIlIl.getPlacePos(llllIlIlIIIIlIl.target);
                    if (llllIlIlIIIIlIl.placeDelayLeft > 0) {
                        --llllIlIlIIIIlIl.placeDelayLeft;
                    } else {
                        llllIlIlIIIIlIl.placeBed(llllIlIlIIIIlIl.bestPos);
                        llllIlIlIIIIlIl.placeDelayLeft = llllIlIlIIIIlIl.placeDelay.get();
                        llllIlIlIIIIlIl.stage = Stage.Breaking;
                    }
                }
                case Breaking: {
                    llllIlIlIIIIlIl.bestPos = llllIlIlIIIIlIl.getBreakPos(llllIlIlIIIIlIl.target);
                    if (llllIlIlIIIIlIl.breakDelayLeft > 0) {
                        --llllIlIlIIIIlIl.breakDelayLeft;
                        break;
                    }
                    llllIlIlIIIIlIl.breakBed(llllIlIlIIIIlIl.bestPos);
                    llllIlIlIIIIlIl.breakDelayLeft = llllIlIlIIIIlIl.breakDelay.get();
                    llllIlIlIIIIlIl.stage = Stage.Placing;
                }
            }
        } else {
            llllIlIlIIIIlIl.bestPos = llllIlIlIIIIlIl.getBreakPos(llllIlIlIIIIlIl.target);
            if (llllIlIlIIIIlIl.breakDelayLeft > 0) {
                --llllIlIlIIIIlIl.breakDelayLeft;
            } else {
                llllIlIlIIIIlIl.breakDelayLeft = llllIlIlIIIIlIl.breakDelay.get();
                llllIlIlIIIIlIl.breakBed(llllIlIlIIIIlIl.bestPos);
            }
        }
    }

    private boolean checkPlace(class_2350 llllIlIIlIllllI, class_1657 llllIlIIlIlllIl, boolean llllIlIIlIlIlll) {
        BedAura llllIlIIlIlllll;
        class_2338 llllIlIIlIllIll;
        class_2338 class_23382 = llllIlIIlIllIll = llllIlIIlIlIlll ? llllIlIIlIlllIl.method_24515().method_10084() : llllIlIIlIlllIl.method_24515();
        if (llllIlIIlIlllll.mc.field_1687.method_8320(llllIlIIlIllIll).method_26207().method_15800() && BlockUtils.canPlace(llllIlIIlIllIll.method_10093(llllIlIIlIllllI)) && (llllIlIIlIlllll.placeMode.get() == Safety.Suicide || DamageCalcUtils.bedDamage((class_1309)llllIlIIlIlllIl, Utils.vec3d(llllIlIIlIllIll)) >= llllIlIIlIlllll.minDamage.get() && DamageCalcUtils.bedDamage((class_1309)llllIlIIlIlllll.mc.field_1724, Utils.vec3d(llllIlIIlIllIll.method_10093(llllIlIIlIllllI))) < llllIlIIlIlllll.maxSelfDamage.get() && DamageCalcUtils.bedDamage((class_1309)llllIlIIlIlllll.mc.field_1724, Utils.vec3d(llllIlIIlIllIll)) < llllIlIIlIlllll.maxSelfDamage.get())) {
            llllIlIIlIlllll.direction = llllIlIIlIllllI;
            return true;
        }
        return false;
    }

    private void placeBed(class_2338 llllIlIIlllllIl) {
        BedAura llllIlIIllllllI;
        FindItemResult llllIlIIlllllII = InvUtils.find(llllIlIIIIlIIlI -> llllIlIIIIlIIlI.method_7909() instanceof class_1748);
        if (llllIlIIlllllII.isMain() && llllIlIIllllllI.autoMove.get().booleanValue()) {
            llllIlIIllllllI.doAutoMove();
        }
        if (!(llllIlIIlllllII = InvUtils.findInHotbar(llllIlIIIIlIllI -> llllIlIIIIlIllI.method_7909() instanceof class_1748)).found()) {
            return;
        }
        if (llllIlIIlllllII.getHand() == null && !llllIlIIllllllI.autoSwitch.get().booleanValue()) {
            return;
        }
        FindItemResult llllIlIIllllIll = llllIlIIlllllII;
        Rotations.rotate(llllIlIIllllllI.yawFromDir(llllIlIIllllllI.direction), llllIlIIllllllI.mc.field_1724.field_5965, () -> {
            BedAura llllIlIIIIlllIl;
            BlockUtils.place(llllIlIIlllllIl, llllIlIIllllIll, false, 0, llllIlIIIIlllIl.noSwing.get() == false, true, llllIlIIIIlllIl.swapBack.get());
        });
    }

    public BedAura() {
        super(Categories.Combat, "bed-aura", "Automatically places and explodes beds in the Nether and End.");
        BedAura llllIlIlIIIlIll;
        llllIlIlIIIlIll.sgPlace = llllIlIlIIIlIll.settings.createGroup("Place");
        llllIlIlIIIlIll.sgBreak = llllIlIlIIIlIll.settings.createGroup("Break");
        llllIlIlIIIlIll.sgPause = llllIlIlIIIlIll.settings.createGroup("Pause");
        llllIlIlIIIlIll.sgMisc = llllIlIlIIIlIll.settings.createGroup("Misc");
        llllIlIlIIIlIll.sgRender = llllIlIlIIIlIll.settings.createGroup("Render");
        llllIlIlIIIlIll.place = llllIlIlIIIlIll.sgPlace.add(new BoolSetting.Builder().name("place").description("Allows Bed Aura to place beds.").defaultValue(true).build());
        llllIlIlIIIlIll.placeMode = llllIlIlIIIlIll.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The way beds are allowed to be placed near you.").defaultValue(Safety.Safe).visible(llllIlIlIIIlIll.place::get).build());
        llllIlIlIIIlIll.placeDelay = llllIlIlIIIlIll.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay for placing beds.").defaultValue(9).min(0).sliderMax(20).visible(llllIlIlIIIlIll.place::get).build());
        llllIlIlIIIlIll.breakDelay = llllIlIlIIIlIll.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The tick delay for breaking beds.").defaultValue(0).min(0).sliderMax(20).build());
        llllIlIlIIIlIll.breakMode = llllIlIlIIIlIll.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The way beds are allowed to be broken near you.").defaultValue(Safety.Safe).build());
        llllIlIlIIIlIll.pauseOnEat = llllIlIlIIIlIll.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses while eating.").defaultValue(false).build());
        llllIlIlIIIlIll.pauseOnDrink = llllIlIlIIIlIll.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses while drinking potions.").defaultValue(false).build());
        llllIlIlIIIlIll.pauseOnMine = llllIlIlIIIlIll.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses while mining blocks.").defaultValue(false).build());
        llllIlIlIIIlIll.targetRange = llllIlIlIIIlIll.sgMisc.add(new DoubleSetting.Builder().name("range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllIlIlIIIlIll.autoSwitch = llllIlIlIIIlIll.sgMisc.add(new BoolSetting.Builder().name("auto-switch").description("Switches to a bed automatically.").defaultValue(true).build());
        llllIlIlIIIlIll.swapBack = llllIlIlIIIlIll.sgMisc.add(new BoolSetting.Builder().name("swap-back").description("Switches back to previous slot after placing.").defaultValue(true).build());
        llllIlIlIIIlIll.autoMove = llllIlIlIIIlIll.sgMisc.add(new BoolSetting.Builder().name("auto-move").description("Moves beds into a selected hotbar slot.").defaultValue(false).build());
        llllIlIlIIIlIll.autoMoveSlot = llllIlIlIIIlIll.sgMisc.add(new IntSetting.Builder().name("auto-move-slot").description("The slot Auto Move moves beds to.").defaultValue(9).min(1).sliderMin(1).max(9).sliderMax(9).visible(llllIlIlIIIlIll.autoMove::get).build());
        llllIlIlIIIlIll.noSwing = llllIlIlIIIlIll.sgMisc.add(new BoolSetting.Builder().name("no-swing").description("Disables hand swings clientside.").defaultValue(false).build());
        llllIlIlIIIlIll.minDamage = llllIlIlIIIlIll.sgMisc.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage to inflict on your target.").defaultValue(7.0).min(0.0).sliderMax(20.0).max(20.0).build());
        llllIlIlIIIlIll.maxSelfDamage = llllIlIlIIIlIll.sgMisc.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum damage to inflict on yourself.").defaultValue(7.0).min(0.0).sliderMax(20.0).max(20.0).build());
        llllIlIlIIIlIll.minHealth = llllIlIlIIIlIll.sgMisc.add(new DoubleSetting.Builder().name("min-health").description("The minimum health required for Bed Aura to work.").defaultValue(4.0).min(0.0).sliderMax(36.0).max(36.0).build());
        llllIlIlIIIlIll.priority = llllIlIlIIIlIll.sgMisc.add(new EnumSetting.Builder().name("priority").description("How to select the player to target.").defaultValue(SortPriority.LowestHealth).build());
        llllIlIlIIIlIll.render = llllIlIlIIIlIll.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block where it is placing a bed.").defaultValue(true).build());
        llllIlIlIIIlIll.sideColor = llllIlIlIIIlIll.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        llllIlIlIIIlIll.lineColor = llllIlIlIIIlIll.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(15, 255, 211, 255)).build());
        llllIlIlIIIlIll.shapeMode = llllIlIlIIIlIll.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
    }

    private class_2338 getPlacePos(class_1657 llllIlIIllIlIIl) {
        BedAura llllIlIIllIIlll;
        class_2338 llllIlIIllIlIII = llllIlIIllIlIIl.method_24515();
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11043, llllIlIIllIlIIl, true)) {
            return llllIlIIllIlIII.method_10084().method_10095();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11035, llllIlIIllIlIIl, true)) {
            return llllIlIIllIlIII.method_10084().method_10072();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11034, llllIlIIllIlIIl, true)) {
            return llllIlIIllIlIII.method_10084().method_10078();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11039, llllIlIIllIlIIl, true)) {
            return llllIlIIllIlIII.method_10084().method_10067();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11043, llllIlIIllIlIIl, false)) {
            return llllIlIIllIlIII.method_10095();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11035, llllIlIIllIlIIl, false)) {
            return llllIlIIllIlIII.method_10072();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11034, llllIlIIllIlIIl, false)) {
            return llllIlIIllIlIII.method_10078();
        }
        if (llllIlIIllIIlll.checkPlace(class_2350.field_11039, llllIlIIllIlIIl, false)) {
            return llllIlIIllIlIII.method_10067();
        }
        return null;
    }

    private void breakBed(class_2338 llllIlIIlllIIlI) {
        BedAura llllIlIIlllIIll;
        if (llllIlIIlllIIlI == null) {
            return;
        }
        boolean llllIlIIlllIIIl = llllIlIIlllIIll.mc.field_1724.method_5715();
        if (llllIlIIlllIIIl) {
            llllIlIIlllIIll.mc.field_1724.method_5660(false);
        }
        llllIlIIlllIIll.mc.field_1761.method_2896(llllIlIIlllIIll.mc.field_1724, llllIlIIlllIIll.mc.field_1687, class_1268.field_5810, new class_3965(llllIlIIlllIIll.mc.field_1724.method_19538(), class_2350.field_11036, llllIlIIlllIIll.bestPos, false));
        llllIlIIlllIIll.mc.field_1724.method_5660(llllIlIIlllIIIl);
    }

    private static enum Stage {
        Placing,
        Breaking;


        private Stage() {
            Stage llllIllllIIlII;
        }
    }
}

