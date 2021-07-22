/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1748
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_243
 *  net.minecraft.class_2586
 *  net.minecraft.class_2587
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.bedtrap;

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
import minegame159.meteorclient.utils.bedtrap.BlockUtils;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.ChatUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1748;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_2587;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_3965;

public class BedAuraTwo
extends Module {
    private final Setting<Boolean> pauseOnBurrow;
    private final SettingGroup sgRender;
    private final Setting<Double> breakRange;
    private final Setting<SettingColor> lineColor;
    public class_1657 target;
    static final boolean $assertionsDisabled = !BedAuraTwo.class.desiredAssertionStatus();
    private final Setting<HitSwing> hitSwing;
    private class_2338 bestPos;
    private final Setting<Integer> yDownPlace;
    private final Setting<ShapeMode> shapeMode;
    private final SettingGroup sgMisc;
    private final Setting<Integer> autoMoveSlot;
    private boolean sendNotif;
    private final SettingGroup sgPlace;
    private class_2350 direction;
    private boolean notify;
    public class_1657 trigger;
    private class_2338 burrowPos;
    private final Setting<Boolean> swing;
    private final Setting<SettingColor> sideColor;
    private final Setting<Double> placeRange;
    private final Setting<Integer> placeDelay;
    private int placeDelayLeft;
    private final Setting<Boolean> autoSwitch;
    private final Setting<Boolean> render;
    private final Setting<Boolean> antiPop;
    private final Setting<Boolean> autoMove;
    private final Setting<Boolean> disableOnNoBeds;
    private final Setting<Integer> yUpPlace;
    private final SettingGroup sgPause;
    private final SettingGroup sgSwitch;

    public class_2338 doPlaceDown(class_1657 class_16572) {
        class_2338 class_23382 = class_16572.method_24515();
        if (this.checkPlaceDown(class_2350.field_11043, class_16572, false)) {
            return class_23382.method_10095();
        }
        if (this.checkPlaceDown(class_2350.field_11035, class_16572, false)) {
            return class_23382.method_10072();
        }
        if (this.checkPlaceDown(class_2350.field_11034, class_16572, false)) {
            return class_23382.method_10078();
        }
        if (this.checkPlaceDown(class_2350.field_11039, class_16572, false)) {
            return class_23382.method_10067();
        }
        return null;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.sendNotif = true;
        if (this.mc.field_1687.method_8597().method_29956()) {
            ChatUtils.error("Overworld moment, disabling.", new Object[0]);
            this.toggle();
            return;
        }
        SortPriority sortPriority = SortPriority.LowestDistance;
        this.target = TargetUtils.getPlayerTarget(this.placeRange.get(), sortPriority);
        if (this.target == null) {
            this.bestPos = null;
            return;
        }
        if (InvUtils.find(BedAuraTwo::lambda$onTick$0).found()) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.antiPop.get().booleanValue() && this.mc.field_1724.method_24515().equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10084().equals((Object)this.target.method_24515())) {
                return;
            }
            if (this.pauseOnBurrow.get().booleanValue() && !this.pauseOnBurrow(this.mc.field_1687.method_8320(this.target.method_24515()).method_26204())) {
                return;
            }
            this.bestPos = BlockUtils.obbyDoubleSurrounded((class_1309)this.target) && this.mc.field_1687.method_8320(this.target.method_24515().method_10084()).method_26204() == class_2246.field_10124 || !BlockUtils.isSurrounded((class_1309)this.target) && this.yDownPlace.get() != 0 && this.mc.field_1724.method_19538().method_10214() - (double)this.yDownPlace.get().intValue() > this.target.method_19538().method_10214() ? this.doPlaceUp(this.target) : (!BlockUtils.isSurrounded((class_1309)this.target) && this.yUpPlace.get() != 0 && this.mc.field_1724.method_19538().method_10214() + (double)this.yUpPlace.get().intValue() < this.target.method_19538().method_10214() ? this.doPlaceDown(this.target) : this.doPlace(this.target));
            if (this.placeDelayLeft > 0) {
                --this.placeDelayLeft;
            } else {
                this.placeBed(this.bestPos);
                this.placeDelayLeft = this.placeDelay.get();
            }
        } else if (this.disableOnNoBeds.get().booleanValue()) {
            ChatUtils.info("You dont have beds, disabling.", new Object[0]);
            this.toggle();
        }
    }

    private void doAutoMove() {
        FindItemResult findItemResult = InvUtils.find(BedAuraTwo::lambda$doAutoMove$4);
        if (findItemResult.found() && findItemResult.getSlot() != this.autoMoveSlot.get() - 1) {
            InvUtils.move().from(findItemResult.getSlot()).toHotbar(this.autoMoveSlot.get() - 1);
        }
    }

    private boolean rageModeDown() {
        return this.mc.field_1724.method_24515().method_10095().method_10087(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10087(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10087(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10087(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10087(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10087(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10087(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10087(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10087(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10087(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10087(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10087(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10087(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10087(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10087(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10087(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10087(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10087(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10087(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10087(6).equals((Object)this.target.method_24515());
    }

    private static boolean lambda$onTick$0(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    public boolean checkPlaceUp(class_2350 class_23502, class_1657 class_16572, boolean bl) {
        class_2338 class_23382;
        class_2338 class_23383 = class_23382 = bl ? class_16572.method_24515().method_10084().method_10084() : class_16572.method_24515().method_10084();
        if (this.mc.field_1687.method_8320(class_23382).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(class_23382.method_10093(class_23502))) {
            this.direction = class_23502;
            return true;
        }
        return false;
    }

    private void lambda$placeBed$3(class_2338 class_23382, FindItemResult findItemResult) {
        minegame159.meteorclient.utils.world.BlockUtils.place(class_23382, findItemResult, false, 0, this.swing.get(), true, this.autoSwitch.get());
    }

    @Override
    public void onActivate() {
        this.sendNotif = true;
        this.notify = true;
        this.bestPos = null;
        this.burrowPos = null;
        this.direction = class_2350.field_11034;
        this.placeDelayLeft = 0;
    }

    public boolean checkPlace(class_2350 class_23502, class_1657 class_16572, boolean bl) {
        class_2338 class_23382;
        class_2338 class_23383 = class_23382 = bl ? class_16572.method_24515().method_10084() : class_16572.method_24515();
        if (this.mc.field_1687.method_8320(class_23382).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(class_23382.method_10093(class_23502))) {
            this.direction = class_23502;
            return true;
        }
        return false;
    }

    public boolean checkPlaceDown(class_2350 class_23502, class_1657 class_16572, boolean bl) {
        class_2338 class_23382;
        class_2338 class_23383 = class_23382 = bl ? class_16572.method_24515().method_10084() : class_16572.method_24515();
        if (this.mc.field_1687.method_8320(class_23382).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(class_23382.method_10093(class_23502))) {
            this.direction = class_23502;
            return true;
        }
        return false;
    }

    public boolean pauseOnBurrow(class_2248 class_22482) {
        return class_22482 == class_2246.field_10124 || class_22482 == class_2246.field_10343 || class_22482 == class_2248.method_9503((class_1792)class_1802.field_8276) || class_22482 == class_2246.field_10278 || class_22482 == class_2246.field_10057 || class_22482 == class_2246.field_10066 || class_22482 == class_2246.field_10417 || class_22482 == class_2246.field_10553 || class_22482 == class_2246.field_10493 || class_22482 == class_2246.field_22100 || class_22482 == class_2246.field_22101 || class_22482 == class_2246.field_23864 || class_22482 == class_2246.field_10382 || class_22482 == class_2246.field_10164 || class_22482 == class_2246.field_10397 || class_22482 == class_2246.field_10592 || class_22482 == class_2246.field_22130 || class_22482 == class_2246.field_23863 || class_22482 == class_2246.field_10470 || class_22482 == class_2246.field_10582 || class_22482 == class_2246.field_10026 || class_22482 == class_2246.field_10224 || class_22482 == class_2246.field_10484 || class_22482 == class_2246.field_10332 || class_22482 == class_2246.field_10158 || class_22482 == class_2246.field_22131 || class_22482 == class_2246.field_10036;
    }

    public class_2338 doPlace(class_1657 class_16572) {
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

    @EventHandler
    private void breakBed(TickEvent.Post post) {
        double d = this.breakRange.get() * 8.0;
        if (this.target == null) {
            this.bestPos = null;
            return;
        }
        if (BlockUtils.isRetard((class_1309)this.target)) {
            return;
        }
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (!this.mc.field_1724.method_5715()) {
            if (!$assertionsDisabled && this.mc.field_1687 == null) {
                throw new AssertionError();
            }
            for (class_2586 class_25862 : this.mc.field_1687.field_9231) {
                if (!(class_25862 instanceof class_2587) || !(class_25862.method_11016().method_19770((class_2374)this.mc.field_1724.method_19538(), true) < d)) continue;
                class_2338 class_23382 = class_25862.method_11016();
                class_243 class_2432 = new class_243((double)class_23382.method_10263() + 0.5, (double)class_23382.method_10264(), (double)class_23382.method_10260() + 0.5);
                if (!$assertionsDisabled && this.mc.field_1761 == null) {
                    throw new AssertionError();
                }
                if (this.hitSwing.get() == HitSwing.OFF_HAND) {
                    this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5810, new class_3965(class_2432, class_2350.field_11036, class_23382, false));
                    continue;
                }
                this.mc.field_1761.method_2896(this.mc.field_1724, this.mc.field_1687, class_1268.field_5808, new class_3965(class_2432, class_2350.field_11036, class_23382, false));
            }
        }
    }

    private float yawFromDir(class_2350 class_23502) {
        switch (1.$SwitchMap$net$minecraft$util$math$Direction[class_23502.ordinal()]) {
            case 1: {
                return 90.0f;
            }
            case 2: {
                return 0.0f;
            }
            case 3: {
                return 180.0f;
            }
            case 4: {
                return -90.0f;
            }
        }
        return 0.0f;
    }

    private static boolean lambda$doAutoMove$4(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    public class_2338 doPlaceUp(class_1657 class_16572) {
        class_2338 class_23382 = class_16572.method_24515();
        if (this.checkPlaceUp(class_2350.field_11043, class_16572, true)) {
            return class_23382.method_10084().method_10084().method_10095();
        }
        if (this.checkPlaceUp(class_2350.field_11035, class_16572, true)) {
            return class_23382.method_10084().method_10084().method_10072();
        }
        if (this.checkPlaceUp(class_2350.field_11034, class_16572, true)) {
            return class_23382.method_10084().method_10084().method_10078();
        }
        if (this.checkPlaceUp(class_2350.field_11039, class_16572, true)) {
            return class_23382.method_10084().method_10084().method_10067();
        }
        return null;
    }

    private static boolean lambda$placeBed$2(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    public BedAuraTwo() {
        super(Categories.BedTrap, "bed-aura+", "Automatically places and explodes beds in the Nether and End.");
        this.sgPlace = this.settings.createGroup("Place");
        this.sgSwitch = this.settings.createGroup("Switch");
        this.sgMisc = this.settings.createGroup("Misc");
        this.sgPause = this.settings.createGroup("Pause");
        this.sgRender = this.settings.createGroup("Render");
        this.placeDelay = this.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay for placing beds.").defaultValue(9).min(0).sliderMax(20).build());
        this.placeRange = this.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.breakRange = this.sgPlace.add(new DoubleSetting.Builder().name("break-range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        this.yUpPlace = this.sgPlace.add(new IntSetting.Builder().name("place-up-on-y").description("Tries to place in legs if target get higher Y than yours.").defaultValue(3).min(0).sliderMax(6).build());
        this.yDownPlace = this.sgPlace.add(new IntSetting.Builder().name("place-down-on-y").description("Tries to place on head if target get lower Y than yours.").defaultValue(3).min(0).sliderMax(6).build());
        this.autoSwitch = this.sgSwitch.add(new BoolSetting.Builder().name("auto-switch").description("Switches to a bed automatically.").defaultValue(true).build());
        this.autoMove = this.sgSwitch.add(new BoolSetting.Builder().name("auto-move").description("Moves beds into a selected hotbar slot.").defaultValue(false).build());
        this.autoMoveSlot = this.sgSwitch.add(new IntSetting.Builder().name("auto-move-slot").description("The slot Auto Move moves beds to.").defaultValue(9).min(1).sliderMin(1).max(9).sliderMax(9).build());
        this.disableOnNoBeds = this.sgMisc.add(new BoolSetting.Builder().name("disable-on-no-beds").description("Disables if you dont have beds.").defaultValue(true).build());
        this.antiPop = this.sgMisc.add(new BoolSetting.Builder().name("anti-self-pop").description("Prevent from popping totems if target stay in your position.").defaultValue(true).build());
        this.pauseOnBurrow = this.sgPause.add(new BoolSetting.Builder().name("pause-on-burrow").description("Doesn't targeting player if he stay in burrow.").defaultValue(true).build());
        this.swing = this.sgMisc.add(new BoolSetting.Builder().name("place-swing").description("Enable hand swings clientside.").defaultValue(true).build());
        this.hitSwing = this.sgMisc.add(new EnumSetting.Builder().name("break-swing").description("Choose hand swings clientside.").defaultValue(HitSwing.OFF_HAND).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block where it is placing a bed.").defaultValue(true).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(15, 255, 211, 255)).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.notify = true;
        this.sendNotif = true;
    }

    @Override
    public String getInfoString() {
        if (this.target != null) {
            return this.target.method_5820();
        }
        return null;
    }

    private void placeBed(class_2338 class_23382) {
        FindItemResult findItemResult = InvUtils.find(BedAuraTwo::lambda$placeBed$1);
        if (findItemResult.isMain() && this.autoMove.get().booleanValue()) {
            this.doAutoMove();
        }
        if (!(findItemResult = InvUtils.findInHotbar(BedAuraTwo::lambda$placeBed$2)).found()) {
            return;
        }
        if (findItemResult.getHand() == null && !this.autoSwitch.get().booleanValue()) {
            return;
        }
        FindItemResult findItemResult2 = findItemResult;
        Rotations.rotate(this.yawFromDir(this.direction), this.mc.field_1724.field_5965, () -> this.lambda$placeBed$3(class_23382, findItemResult2));
    }

    private boolean rageModeUp() {
        return this.mc.field_1724.method_24515().method_10095().method_10086(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10086(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10086(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10086(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10095().method_10086(7).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10086(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10086(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10086(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10086(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10072().method_10086(7).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10086(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10086(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10086(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10086(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10067().method_10086(7).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10086(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10086(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10086(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10086(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10078().method_10086(7).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10086(3).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10086(4).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10086(5).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10086(6).equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10086(7).equals((Object)this.target.method_24515());
    }

    private void mine(class_2338 class_23382) {
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, class_23382, class_2350.field_11036));
        this.mc.field_1724.method_6104(class_1268.field_5808);
        this.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, class_23382, class_2350.field_11036));
    }

    private static boolean lambda$placeBed$1(class_1799 class_17992) {
        return class_17992.method_7909() instanceof class_1748;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.render.get().booleanValue() && this.bestPos != null) {
            if (this.antiPop.get().booleanValue() && this.mc.field_1724.method_24515().equals((Object)this.target.method_24515()) || this.mc.field_1724.method_24515().method_10084().equals((Object)this.target.method_24515())) {
                return;
            }
            if (this.pauseOnBurrow.get().booleanValue() && !this.pauseOnBurrow(this.mc.field_1687.method_8320(this.target.method_24515()).method_26204())) {
                return;
            }
            int n = this.bestPos.method_10263();
            int n2 = this.bestPos.method_10264();
            int n3 = this.bestPos.method_10260();
            switch (1.$SwitchMap$net$minecraft$util$math$Direction[this.direction.ordinal()]) {
                case 2: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3, n + 1, (double)n2 + 0.6, n3 + 2, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case 3: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3 - 1, n + 1, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case 1: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n - 1, n2, n3, n + 1, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                    break;
                }
                case 4: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, n, n2, n3, n + 2, (double)n2 + 0.6, n3 + 1, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
                }
            }
        }
    }

    public static enum HitSwing {
        OFF_HAND,
        MAIN_HAND;

    }
}

