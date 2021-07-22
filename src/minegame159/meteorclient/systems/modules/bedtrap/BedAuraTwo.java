/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1748
 *  net.minecraft.class_1792
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
    private final /* synthetic */ Setting<Boolean> pauseOnBurrow;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ Setting<Double> breakRange;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    public /* synthetic */ class_1657 target;
    private final /* synthetic */ Setting<HitSwing> hitSwing;
    private /* synthetic */ class_2338 bestPos;
    private final /* synthetic */ Setting<Integer> yDownPlace;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ SettingGroup sgMisc;
    private final /* synthetic */ Setting<Integer> autoMoveSlot;
    private /* synthetic */ boolean sendNotif;
    private final /* synthetic */ SettingGroup sgPlace;
    private /* synthetic */ class_2350 direction;
    private /* synthetic */ boolean notify;
    public /* synthetic */ class_1657 trigger;
    private /* synthetic */ class_2338 burrowPos;
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Double> placeRange;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private /* synthetic */ int placeDelayLeft;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Boolean> antiPop;
    private final /* synthetic */ Setting<Boolean> autoMove;
    private final /* synthetic */ Setting<Boolean> disableOnNoBeds;
    private final /* synthetic */ Setting<Integer> yUpPlace;
    private final /* synthetic */ SettingGroup sgPause;
    private final /* synthetic */ SettingGroup sgSwitch;

    public class_2338 doPlaceDown(class_1657 llllllllllllllllIlIllIlIIIlIllII) {
        BedAuraTwo llllllllllllllllIlIllIlIIIllIIII;
        class_2338 llllllllllllllllIlIllIlIIIlIlllI = llllllllllllllllIlIllIlIIIlIllII.method_24515();
        if (llllllllllllllllIlIllIlIIIllIIII.checkPlaceDown(class_2350.field_11043, llllllllllllllllIlIllIlIIIlIllII, false)) {
            return llllllllllllllllIlIllIlIIIlIlllI.method_10095();
        }
        if (llllllllllllllllIlIllIlIIIllIIII.checkPlaceDown(class_2350.field_11035, llllllllllllllllIlIllIlIIIlIllII, false)) {
            return llllllllllllllllIlIllIlIIIlIlllI.method_10072();
        }
        if (llllllllllllllllIlIllIlIIIllIIII.checkPlaceDown(class_2350.field_11034, llllllllllllllllIlIllIlIIIlIllII, false)) {
            return llllllllllllllllIlIllIlIIIlIlllI.method_10078();
        }
        if (llllllllllllllllIlIllIlIIIllIIII.checkPlaceDown(class_2350.field_11039, llllllllllllllllIlIllIlIIIlIllII, false)) {
            return llllllllllllllllIlIllIlIIIlIlllI.method_10067();
        }
        return null;
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllIlIllIlIIlllIlll) {
        BedAuraTwo llllllllllllllllIlIllIlIIlllIlIl;
        llllllllllllllllIlIllIlIIlllIlIl.sendNotif = true;
        if (llllllllllllllllIlIllIlIIlllIlIl.mc.field_1687.method_8597().method_29956()) {
            ChatUtils.error("Overworld moment, disabling.", new Object[0]);
            llllllllllllllllIlIllIlIIlllIlIl.toggle();
            return;
        }
        SortPriority llllllllllllllllIlIllIlIIlllIllI = SortPriority.LowestDistance;
        llllllllllllllllIlIllIlIIlllIlIl.target = TargetUtils.getPlayerTarget(llllllllllllllllIlIllIlIIlllIlIl.placeRange.get(), llllllllllllllllIlIllIlIIlllIllI);
        if (llllllllllllllllIlIllIlIIlllIlIl.target == null) {
            llllllllllllllllIlIllIlIIlllIlIl.bestPos = null;
            return;
        }
        if (InvUtils.find(llllllllllllllllIlIllIIlllIIllll -> llllllllllllllllIlIllIIlllIIllll.method_7909() instanceof class_1748).found()) {
            assert (llllllllllllllllIlIllIlIIlllIlIl.mc.field_1724 != null);
            if (llllllllllllllllIlIllIlIIlllIlIl.antiPop.get().booleanValue() && llllllllllllllllIlIllIlIIlllIlIl.mc.field_1724.method_24515().equals((Object)llllllllllllllllIlIllIlIIlllIlIl.target.method_24515()) || llllllllllllllllIlIllIlIIlllIlIl.mc.field_1724.method_24515().method_10084().equals((Object)llllllllllllllllIlIllIlIIlllIlIl.target.method_24515())) {
                return;
            }
            if (llllllllllllllllIlIllIlIIlllIlIl.pauseOnBurrow.get().booleanValue() && !llllllllllllllllIlIllIlIIlllIlIl.pauseOnBurrow(llllllllllllllllIlIllIlIIlllIlIl.mc.field_1687.method_8320(llllllllllllllllIlIllIlIIlllIlIl.target.method_24515()).method_26204())) {
                return;
            }
            llllllllllllllllIlIllIlIIlllIlIl.bestPos = BlockUtils.obbyDoubleSurrounded((class_1309)llllllllllllllllIlIllIlIIlllIlIl.target) && llllllllllllllllIlIllIlIIlllIlIl.mc.field_1687.method_8320(llllllllllllllllIlIllIlIIlllIlIl.target.method_24515().method_10084()).method_26204() == class_2246.field_10124 || !BlockUtils.isSurrounded((class_1309)llllllllllllllllIlIllIlIIlllIlIl.target) && llllllllllllllllIlIllIlIIlllIlIl.yDownPlace.get() != 0 && llllllllllllllllIlIllIlIIlllIlIl.mc.field_1724.method_19538().method_10214() - (double)llllllllllllllllIlIllIlIIlllIlIl.yDownPlace.get().intValue() > llllllllllllllllIlIllIlIIlllIlIl.target.method_19538().method_10214() ? llllllllllllllllIlIllIlIIlllIlIl.doPlaceUp(llllllllllllllllIlIllIlIIlllIlIl.target) : (!BlockUtils.isSurrounded((class_1309)llllllllllllllllIlIllIlIIlllIlIl.target) && llllllllllllllllIlIllIlIIlllIlIl.yUpPlace.get() != 0 && llllllllllllllllIlIllIlIIlllIlIl.mc.field_1724.method_19538().method_10214() + (double)llllllllllllllllIlIllIlIIlllIlIl.yUpPlace.get().intValue() < llllllllllllllllIlIllIlIIlllIlIl.target.method_19538().method_10214() ? llllllllllllllllIlIllIlIIlllIlIl.doPlaceDown(llllllllllllllllIlIllIlIIlllIlIl.target) : llllllllllllllllIlIllIlIIlllIlIl.doPlace(llllllllllllllllIlIllIlIIlllIlIl.target));
            if (llllllllllllllllIlIllIlIIlllIlIl.placeDelayLeft > 0) {
                --llllllllllllllllIlIllIlIIlllIlIl.placeDelayLeft;
            } else {
                llllllllllllllllIlIllIlIIlllIlIl.placeBed(llllllllllllllllIlIllIlIIlllIlIl.bestPos);
                llllllllllllllllIlIllIlIIlllIlIl.placeDelayLeft = llllllllllllllllIlIllIlIIlllIlIl.placeDelay.get();
            }
        } else if (llllllllllllllllIlIllIlIIlllIlIl.disableOnNoBeds.get().booleanValue()) {
            ChatUtils.info("You dont have beds, disabling.", new Object[0]);
            llllllllllllllllIlIllIlIIlllIlIl.toggle();
        }
    }

    private void doAutoMove() {
        BedAuraTwo llllllllllllllllIlIllIlIIlIIllIl;
        FindItemResult llllllllllllllllIlIllIlIIlIIlllI = InvUtils.find(llllllllllllllllIlIllIIllllIIIlI -> llllllllllllllllIlIllIIllllIIIlI.method_7909() instanceof class_1748);
        if (llllllllllllllllIlIllIlIIlIIlllI.found() && llllllllllllllllIlIllIlIIlIIlllI.getSlot() != llllllllllllllllIlIllIlIIlIIllIl.autoMoveSlot.get() - 1) {
            InvUtils.move().from(llllllllllllllllIlIllIlIIlIIlllI.getSlot()).toHotbar(llllllllllllllllIlIllIlIIlIIllIl.autoMoveSlot.get() - 1);
        }
    }

    private boolean rageModeDown() {
        BedAuraTwo llllllllllllllllIlIllIIlllllllII;
        return llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10095().method_10087(3).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10095().method_10087(4).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10095().method_10087(5).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10095().method_10087(6).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10072().method_10087(3).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10072().method_10087(4).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10072().method_10087(5).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10072().method_10087(6).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10067().method_10087(3).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10067().method_10087(4).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10067().method_10087(5).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10067().method_10087(6).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10078().method_10087(3).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10078().method_10087(4).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10078().method_10087(5).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10078().method_10087(6).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10087(3).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10087(4).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10087(5).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515()) || llllllllllllllllIlIllIIlllllllII.mc.field_1724.method_24515().method_10087(6).equals((Object)llllllllllllllllIlIllIIlllllllII.target.method_24515());
    }

    public boolean checkPlaceUp(class_2350 llllllllllllllllIlIllIlIIIIlllll, class_1657 llllllllllllllllIlIllIlIIIIllllI, boolean llllllllllllllllIlIllIlIIIlIIIlI) {
        BedAuraTwo llllllllllllllllIlIllIlIIIlIIlIl;
        class_2338 llllllllllllllllIlIllIlIIIlIIIIl;
        class_2338 class_23382 = llllllllllllllllIlIllIlIIIlIIIIl = llllllllllllllllIlIllIlIIIlIIIlI ? llllllllllllllllIlIllIlIIIIllllI.method_24515().method_10084().method_10084() : llllllllllllllllIlIllIlIIIIllllI.method_24515().method_10084();
        if (llllllllllllllllIlIllIlIIIlIIlIl.mc.field_1687.method_8320(llllllllllllllllIlIllIlIIIlIIIIl).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(llllllllllllllllIlIllIlIIIlIIIIl.method_10093(llllllllllllllllIlIllIlIIIIlllll))) {
            llllllllllllllllIlIllIlIIIlIIlIl.direction = llllllllllllllllIlIllIlIIIIlllll;
            return true;
        }
        return false;
    }

    @Override
    public void onActivate() {
        llllllllllllllllIlIllIlIIlllllII.sendNotif = true;
        llllllllllllllllIlIllIlIIlllllII.notify = true;
        llllllllllllllllIlIllIlIIlllllII.bestPos = null;
        llllllllllllllllIlIllIlIIlllllII.burrowPos = null;
        llllllllllllllllIlIllIlIIlllllII.direction = class_2350.field_11034;
        llllllllllllllllIlIllIlIIlllllII.placeDelayLeft = 0;
    }

    public boolean checkPlace(class_2350 llllllllllllllllIlIllIlIIIIlIlIl, class_1657 llllllllllllllllIlIllIlIIIIIllll, boolean llllllllllllllllIlIllIlIIIIIlllI) {
        BedAuraTwo llllllllllllllllIlIllIlIIIIlIllI;
        class_2338 llllllllllllllllIlIllIlIIIIlIIlI;
        class_2338 class_23382 = llllllllllllllllIlIllIlIIIIlIIlI = llllllllllllllllIlIllIlIIIIIlllI ? llllllllllllllllIlIllIlIIIIIllll.method_24515().method_10084() : llllllllllllllllIlIllIlIIIIIllll.method_24515();
        if (llllllllllllllllIlIllIlIIIIlIllI.mc.field_1687.method_8320(llllllllllllllllIlIllIlIIIIlIIlI).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(llllllllllllllllIlIllIlIIIIlIIlI.method_10093(llllllllllllllllIlIllIlIIIIlIlIl))) {
            llllllllllllllllIlIllIlIIIIlIllI.direction = llllllllllllllllIlIllIlIIIIlIlIl;
            return true;
        }
        return false;
    }

    public boolean checkPlaceDown(class_2350 llllllllllllllllIlIllIlIIIIIIIIl, class_1657 llllllllllllllllIlIllIlIIIIIIIII, boolean llllllllllllllllIlIllIlIIIIIIlII) {
        BedAuraTwo llllllllllllllllIlIllIlIIIIIIIlI;
        class_2338 llllllllllllllllIlIllIlIIIIIIIll;
        class_2338 class_23382 = llllllllllllllllIlIllIlIIIIIIIll = llllllllllllllllIlIllIlIIIIIIlII ? llllllllllllllllIlIllIlIIIIIIIII.method_24515().method_10084() : llllllllllllllllIlIllIlIIIIIIIII.method_24515();
        if (llllllllllllllllIlIllIlIIIIIIIlI.mc.field_1687.method_8320(llllllllllllllllIlIllIlIIIIIIIll).method_26207().method_15800() && minegame159.meteorclient.utils.world.BlockUtils.canPlace(llllllllllllllllIlIllIlIIIIIIIll.method_10093(llllllllllllllllIlIllIlIIIIIIIIl))) {
            llllllllllllllllIlIllIlIIIIIIIlI.direction = llllllllllllllllIlIllIlIIIIIIIIl;
            return true;
        }
        return false;
    }

    public boolean pauseOnBurrow(class_2248 llllllllllllllllIlIllIIlllllIlII) {
        return llllllllllllllllIlIllIIlllllIlII == class_2246.field_10124 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10343 || llllllllllllllllIlIllIIlllllIlII == class_2248.method_9503((class_1792)class_1802.field_8276) || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10278 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10057 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10066 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10417 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10553 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10493 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_22100 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_22101 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_23864 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10382 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10164 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10397 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10592 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_22130 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_23863 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10470 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10582 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10026 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10224 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10484 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10332 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10158 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_22131 || llllllllllllllllIlIllIIlllllIlII == class_2246.field_10036;
    }

    public class_2338 doPlace(class_1657 llllllllllllllllIlIllIlIIIllIlIl) {
        BedAuraTwo llllllllllllllllIlIllIlIIIlllIIl;
        class_2338 llllllllllllllllIlIllIlIIIllIlll = llllllllllllllllIlIllIlIIIllIlIl.method_24515();
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11043, llllllllllllllllIlIllIlIIIllIlIl, true)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10084().method_10095();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11035, llllllllllllllllIlIllIlIIIllIlIl, true)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10084().method_10072();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11034, llllllllllllllllIlIllIlIIIllIlIl, true)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10084().method_10078();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11039, llllllllllllllllIlIllIlIIIllIlIl, true)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10084().method_10067();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11043, llllllllllllllllIlIllIlIIIllIlIl, false)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10095();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11035, llllllllllllllllIlIllIlIIIllIlIl, false)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10072();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11034, llllllllllllllllIlIllIlIIIllIlIl, false)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10078();
        }
        if (llllllllllllllllIlIllIlIIIlllIIl.checkPlace(class_2350.field_11039, llllllllllllllllIlIllIlIIIllIlIl, false)) {
            return llllllllllllllllIlIllIlIIIllIlll.method_10067();
        }
        return null;
    }

    @EventHandler
    private void breakBed(TickEvent.Post llllllllllllllllIlIllIlIIllIlIIl) {
        BedAuraTwo llllllllllllllllIlIllIlIIllIIlll;
        double llllllllllllllllIlIllIlIIllIlIII = llllllllllllllllIlIllIlIIllIIlll.breakRange.get() * 8.0;
        if (llllllllllllllllIlIllIlIIllIIlll.target == null) {
            llllllllllllllllIlIllIlIIllIIlll.bestPos = null;
            return;
        }
        if (BlockUtils.isRetard((class_1309)llllllllllllllllIlIllIlIIllIIlll.target)) {
            return;
        }
        assert (llllllllllllllllIlIllIlIIllIIlll.mc.field_1724 != null);
        if (!llllllllllllllllIlIllIlIIllIIlll.mc.field_1724.method_5715()) {
            assert (llllllllllllllllIlIllIlIIllIIlll.mc.field_1687 != null);
            for (class_2586 llllllllllllllllIlIllIlIIllIlIll : llllllllllllllllIlIllIlIIllIIlll.mc.field_1687.field_9231) {
                if (!(llllllllllllllllIlIllIlIIllIlIll instanceof class_2587) || !(llllllllllllllllIlIllIlIIllIlIll.method_11016().method_19770((class_2374)llllllllllllllllIlIllIlIIllIIlll.mc.field_1724.method_19538(), true) < llllllllllllllllIlIllIlIIllIlIII)) continue;
                class_2338 llllllllllllllllIlIllIlIIllIllIl = llllllllllllllllIlIllIlIIllIlIll.method_11016();
                class_243 llllllllllllllllIlIllIlIIllIllII = new class_243((double)llllllllllllllllIlIllIlIIllIllIl.method_10263() + 0.5, (double)llllllllllllllllIlIllIlIIllIllIl.method_10264(), (double)llllllllllllllllIlIllIlIIllIllIl.method_10260() + 0.5);
                assert (llllllllllllllllIlIllIlIIllIIlll.mc.field_1761 != null);
                if (llllllllllllllllIlIllIlIIllIIlll.hitSwing.get() == HitSwing.OFF_HAND) {
                    llllllllllllllllIlIllIlIIllIIlll.mc.field_1761.method_2896(llllllllllllllllIlIllIlIIllIIlll.mc.field_1724, llllllllllllllllIlIllIlIIllIIlll.mc.field_1687, class_1268.field_5810, new class_3965(llllllllllllllllIlIllIlIIllIllII, class_2350.field_11036, llllllllllllllllIlIllIlIIllIllIl, false));
                    continue;
                }
                llllllllllllllllIlIllIlIIllIIlll.mc.field_1761.method_2896(llllllllllllllllIlIllIlIIllIIlll.mc.field_1724, llllllllllllllllIlIllIlIIllIIlll.mc.field_1687, class_1268.field_5808, new class_3965(llllllllllllllllIlIllIlIIllIllII, class_2350.field_11036, llllllllllllllllIlIllIlIIllIllIl, false));
            }
        }
    }

    private float yawFromDir(class_2350 llllllllllllllllIlIllIlIIlIlIIlI) {
        switch (llllllllllllllllIlIllIlIIlIlIIlI) {
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

    public class_2338 doPlaceUp(class_1657 llllllllllllllllIlIllIlIIlIIIIIl) {
        BedAuraTwo llllllllllllllllIlIllIlIIIllllll;
        class_2338 llllllllllllllllIlIllIlIIlIIIIII = llllllllllllllllIlIllIlIIlIIIIIl.method_24515();
        if (llllllllllllllllIlIllIlIIIllllll.checkPlaceUp(class_2350.field_11043, llllllllllllllllIlIllIlIIlIIIIIl, true)) {
            return llllllllllllllllIlIllIlIIlIIIIII.method_10084().method_10084().method_10095();
        }
        if (llllllllllllllllIlIllIlIIIllllll.checkPlaceUp(class_2350.field_11035, llllllllllllllllIlIllIlIIlIIIIIl, true)) {
            return llllllllllllllllIlIllIlIIlIIIIII.method_10084().method_10084().method_10072();
        }
        if (llllllllllllllllIlIllIlIIIllllll.checkPlaceUp(class_2350.field_11034, llllllllllllllllIlIllIlIIlIIIIIl, true)) {
            return llllllllllllllllIlIllIlIIlIIIIII.method_10084().method_10084().method_10078();
        }
        if (llllllllllllllllIlIllIlIIIllllll.checkPlaceUp(class_2350.field_11039, llllllllllllllllIlIllIlIIlIIIIIl, true)) {
            return llllllllllllllllIlIllIlIIlIIIIII.method_10084().method_10084().method_10067();
        }
        return null;
    }

    public BedAuraTwo() {
        super(Categories.BedTrap, "bed-aura+", "Automatically places and explodes beds in the Nether and End.");
        BedAuraTwo llllllllllllllllIlIllIlIIllllllI;
        llllllllllllllllIlIllIlIIllllllI.sgPlace = llllllllllllllllIlIllIlIIllllllI.settings.createGroup("Place");
        llllllllllllllllIlIllIlIIllllllI.sgSwitch = llllllllllllllllIlIllIlIIllllllI.settings.createGroup("Switch");
        llllllllllllllllIlIllIlIIllllllI.sgMisc = llllllllllllllllIlIllIlIIllllllI.settings.createGroup("Misc");
        llllllllllllllllIlIllIlIIllllllI.sgPause = llllllllllllllllIlIllIlIIllllllI.settings.createGroup("Pause");
        llllllllllllllllIlIllIlIIllllllI.sgRender = llllllllllllllllIlIllIlIIllllllI.settings.createGroup("Render");
        llllllllllllllllIlIllIlIIllllllI.placeDelay = llllllllllllllllIlIllIlIIllllllI.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The tick delay for placing beds.").defaultValue(9).min(0).sliderMax(20).build());
        llllllllllllllllIlIllIlIIllllllI.placeRange = llllllllllllllllIlIllIlIIllllllI.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllIlIllIlIIllllllI.breakRange = llllllllllllllllIlIllIlIIllllllI.sgPlace.add(new DoubleSetting.Builder().name("break-range").description("The maximum range for players to be targeted.").defaultValue(4.0).min(0.0).sliderMax(5.0).build());
        llllllllllllllllIlIllIlIIllllllI.yUpPlace = llllllllllllllllIlIllIlIIllllllI.sgPlace.add(new IntSetting.Builder().name("place-up-on-y").description("Tries to place in legs if target get higher Y than yours.").defaultValue(3).min(0).sliderMax(6).build());
        llllllllllllllllIlIllIlIIllllllI.yDownPlace = llllllllllllllllIlIllIlIIllllllI.sgPlace.add(new IntSetting.Builder().name("place-down-on-y").description("Tries to place on head if target get lower Y than yours.").defaultValue(3).min(0).sliderMax(6).build());
        llllllllllllllllIlIllIlIIllllllI.autoSwitch = llllllllllllllllIlIllIlIIllllllI.sgSwitch.add(new BoolSetting.Builder().name("auto-switch").description("Switches to a bed automatically.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.autoMove = llllllllllllllllIlIllIlIIllllllI.sgSwitch.add(new BoolSetting.Builder().name("auto-move").description("Moves beds into a selected hotbar slot.").defaultValue(false).build());
        llllllllllllllllIlIllIlIIllllllI.autoMoveSlot = llllllllllllllllIlIllIlIIllllllI.sgSwitch.add(new IntSetting.Builder().name("auto-move-slot").description("The slot Auto Move moves beds to.").defaultValue(9).min(1).sliderMin(1).max(9).sliderMax(9).build());
        llllllllllllllllIlIllIlIIllllllI.disableOnNoBeds = llllllllllllllllIlIllIlIIllllllI.sgMisc.add(new BoolSetting.Builder().name("disable-on-no-beds").description("Disables if you dont have beds.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.antiPop = llllllllllllllllIlIllIlIIllllllI.sgMisc.add(new BoolSetting.Builder().name("anti-self-pop").description("Prevent from popping totems if target stay in your position.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.pauseOnBurrow = llllllllllllllllIlIllIlIIllllllI.sgPause.add(new BoolSetting.Builder().name("pause-on-burrow").description("Doesn't targeting player if he stay in burrow.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.swing = llllllllllllllllIlIllIlIIllllllI.sgMisc.add(new BoolSetting.Builder().name("place-swing").description("Enable hand swings clientside.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.hitSwing = llllllllllllllllIlIllIlIIllllllI.sgMisc.add(new EnumSetting.Builder().name("break-swing").description("Choose hand swings clientside.").defaultValue(HitSwing.OFF_HAND).build());
        llllllllllllllllIlIllIlIIllllllI.render = llllllllllllllllIlIllIlIIllllllI.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block where it is placing a bed.").defaultValue(true).build());
        llllllllllllllllIlIllIlIIllllllI.sideColor = llllllllllllllllIlIllIlIIllllllI.sgRender.add(new ColorSetting.Builder().name("place-side-color").description("The side color for positions to be placed.").defaultValue(new SettingColor(0, 0, 0, 75)).build());
        llllllllllllllllIlIllIlIIllllllI.lineColor = llllllllllllllllIlIllIlIIllllllI.sgRender.add(new ColorSetting.Builder().name("place-line-color").description("The line color for positions to be placed.").defaultValue(new SettingColor(15, 255, 211, 255)).build());
        llllllllllllllllIlIllIlIIllllllI.shapeMode = llllllllllllllllIlIllIlIIllllllI.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llllllllllllllllIlIllIlIIllllllI.notify = true;
        llllllllllllllllIlIllIlIIllllllI.sendNotif = true;
    }

    @Override
    public String getInfoString() {
        BedAuraTwo llllllllllllllllIlIllIIllllIIlII;
        if (llllllllllllllllIlIllIIllllIIlII.target != null) {
            return llllllllllllllllIlIllIIllllIIlII.target.method_5820();
        }
        return null;
    }

    private void placeBed(class_2338 llllllllllllllllIlIllIlIIlIlllII) {
        BedAuraTwo llllllllllllllllIlIllIlIIlIllIIl;
        FindItemResult llllllllllllllllIlIllIlIIlIllIll = InvUtils.find(llllllllllllllllIlIllIIlllIlIIlI -> llllllllllllllllIlIllIIlllIlIIlI.method_7909() instanceof class_1748);
        if (llllllllllllllllIlIllIlIIlIllIll.isMain() && llllllllllllllllIlIllIlIIlIllIIl.autoMove.get().booleanValue()) {
            llllllllllllllllIlIllIlIIlIllIIl.doAutoMove();
        }
        if (!(llllllllllllllllIlIllIlIIlIllIll = InvUtils.findInHotbar(llllllllllllllllIlIllIIlllIlIllI -> llllllllllllllllIlIllIIlllIlIllI.method_7909() instanceof class_1748)).found()) {
            return;
        }
        if (llllllllllllllllIlIllIlIIlIllIll.getHand() == null && !llllllllllllllllIlIllIlIIlIllIIl.autoSwitch.get().booleanValue()) {
            return;
        }
        FindItemResult llllllllllllllllIlIllIlIIlIllIlI = llllllllllllllllIlIllIlIIlIllIll;
        Rotations.rotate(llllllllllllllllIlIllIlIIlIllIIl.yawFromDir(llllllllllllllllIlIllIlIIlIllIIl.direction), llllllllllllllllIlIllIlIIlIllIIl.mc.field_1724.field_5965, () -> {
            BedAuraTwo llllllllllllllllIlIllIIlllIlllIl;
            minegame159.meteorclient.utils.world.BlockUtils.place(llllllllllllllllIlIllIlIIlIlllII, llllllllllllllllIlIllIlIIlIllIlI, false, 0, llllllllllllllllIlIllIIlllIlllIl.swing.get(), true, llllllllllllllllIlIllIIlllIlllIl.autoSwitch.get());
        });
    }

    private boolean rageModeUp() {
        BedAuraTwo llllllllllllllllIlIllIIllllllIII;
        return llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10095().method_10086(3).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10095().method_10086(4).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10095().method_10086(5).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10095().method_10086(6).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10095().method_10086(7).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10072().method_10086(3).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10072().method_10086(4).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10072().method_10086(5).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10072().method_10086(6).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10072().method_10086(7).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10067().method_10086(3).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10067().method_10086(4).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10067().method_10086(5).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10067().method_10086(6).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10067().method_10086(7).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10078().method_10086(3).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10078().method_10086(4).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10078().method_10086(5).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10078().method_10086(6).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10078().method_10086(7).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10086(3).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10086(4).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10086(5).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10086(6).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515()) || llllllllllllllllIlIllIIllllllIII.mc.field_1724.method_24515().method_10086(7).equals((Object)llllllllllllllllIlIllIIllllllIII.target.method_24515());
    }

    private void mine(class_2338 llllllllllllllllIlIllIlIIlIIlIII) {
        BedAuraTwo llllllllllllllllIlIllIlIIlIIIlll;
        llllllllllllllllIlIllIlIIlIIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12968, llllllllllllllllIlIllIlIIlIIlIII, class_2350.field_11036));
        llllllllllllllllIlIllIlIIlIIIlll.mc.field_1724.method_6104(class_1268.field_5808);
        llllllllllllllllIlIllIlIIlIIIlll.mc.method_1562().method_2883((class_2596)new class_2846(class_2846.class_2847.field_12973, llllllllllllllllIlIllIlIIlIIlIII, class_2350.field_11036));
    }

    @EventHandler
    private void onRender(RenderEvent llllllllllllllllIlIllIIllllIlIll) {
        BedAuraTwo llllllllllllllllIlIllIIllllIlIlI;
        if (llllllllllllllllIlIllIIllllIlIlI.render.get().booleanValue() && llllllllllllllllIlIllIIllllIlIlI.bestPos != null) {
            if (llllllllllllllllIlIllIIllllIlIlI.antiPop.get().booleanValue() && llllllllllllllllIlIllIIllllIlIlI.mc.field_1724.method_24515().equals((Object)llllllllllllllllIlIllIIllllIlIlI.target.method_24515()) || llllllllllllllllIlIllIIllllIlIlI.mc.field_1724.method_24515().method_10084().equals((Object)llllllllllllllllIlIllIIllllIlIlI.target.method_24515())) {
                return;
            }
            if (llllllllllllllllIlIllIIllllIlIlI.pauseOnBurrow.get().booleanValue() && !llllllllllllllllIlIllIIllllIlIlI.pauseOnBurrow(llllllllllllllllIlIllIIllllIlIlI.mc.field_1687.method_8320(llllllllllllllllIlIllIIllllIlIlI.target.method_24515()).method_26204())) {
                return;
            }
            int llllllllllllllllIlIllIIllllIllll = llllllllllllllllIlIllIIllllIlIlI.bestPos.method_10263();
            int llllllllllllllllIlIllIIllllIlllI = llllllllllllllllIlIllIIllllIlIlI.bestPos.method_10264();
            int llllllllllllllllIlIllIIllllIllIl = llllllllllllllllIlIllIIllllIlIlI.bestPos.method_10260();
            switch (llllllllllllllllIlIllIIllllIlIlI.direction) {
                case field_11043: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlIllIIllllIllll, llllllllllllllllIlIllIIllllIlllI, llllllllllllllllIlIllIIllllIllIl, llllllllllllllllIlIllIIllllIllll + 1, (double)llllllllllllllllIlIllIIllllIlllI + 0.6, llllllllllllllllIlIllIIllllIllIl + 2, llllllllllllllllIlIllIIllllIlIlI.sideColor.get(), llllllllllllllllIlIllIIllllIlIlI.lineColor.get(), llllllllllllllllIlIllIIllllIlIlI.shapeMode.get(), 0);
                    break;
                }
                case field_11035: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlIllIIllllIllll, llllllllllllllllIlIllIIllllIlllI, llllllllllllllllIlIllIIllllIllIl - 1, llllllllllllllllIlIllIIllllIllll + 1, (double)llllllllllllllllIlIllIIllllIlllI + 0.6, llllllllllllllllIlIllIIllllIllIl + 1, llllllllllllllllIlIllIIllllIlIlI.sideColor.get(), llllllllllllllllIlIllIIllllIlIlI.lineColor.get(), llllllllllllllllIlIllIIllllIlIlI.shapeMode.get(), 0);
                    break;
                }
                case field_11034: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlIllIIllllIllll - 1, llllllllllllllllIlIllIIllllIlllI, llllllllllllllllIlIllIIllllIllIl, llllllllllllllllIlIllIIllllIllll + 1, (double)llllllllllllllllIlIllIIllllIlllI + 0.6, llllllllllllllllIlIllIIllllIllIl + 1, llllllllllllllllIlIllIIllllIlIlI.sideColor.get(), llllllllllllllllIlIllIIllllIlIlI.lineColor.get(), llllllllllllllllIlIllIIllllIlIlI.shapeMode.get(), 0);
                    break;
                }
                case field_11039: {
                    Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, llllllllllllllllIlIllIIllllIllll, llllllllllllllllIlIllIIllllIlllI, llllllllllllllllIlIllIIllllIllIl, llllllllllllllllIlIllIIllllIllll + 2, (double)llllllllllllllllIlIllIIllllIlllI + 0.6, llllllllllllllllIlIllIIllllIllIl + 1, llllllllllllllllIlIllIIllllIlIlI.sideColor.get(), llllllllllllllllIlIllIIllllIlIlI.lineColor.get(), llllllllllllllllIlIllIIllllIlIlI.shapeMode.get(), 0);
                }
            }
        }
    }

    public static enum HitSwing {
        OFF_HAND,
        MAIN_HAND;


        private HitSwing() {
            HitSwing llllllllllllllllllIllIlIIllIIIll;
        }
    }
}

