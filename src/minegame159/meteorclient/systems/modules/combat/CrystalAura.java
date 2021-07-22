/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntIterator
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  net.minecraft.class_1268
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1794
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1831
 *  net.minecraft.class_1832
 *  net.minecraft.class_1834
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2818
 *  net.minecraft.class_2824
 *  net.minecraft.class_2828
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3509
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_631
 */
package minegame159.meteorclient.systems.modules.combat;

import com.google.common.util.concurrent.AtomicDouble;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.entity.EntityRemovedEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IBox;
import minegame159.meteorclient.mixininterface.IRaycastContext;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.KeybindSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.EntityUtils;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerManager;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.player.DamageUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.NametagUtils;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockIterator;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1268;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1831;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2818;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3509;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_631;

public class CrystalAura
extends Module {
    private final /* synthetic */ class_243 vec3d;
    private final /* synthetic */ Setting<Boolean> ignoreTerrain;
    private final /* synthetic */ Setting<Boolean> antiSuicide;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Int2IntMap attemptedBreaks;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Boolean> antiWeakness;
    private final /* synthetic */ Setting<YawStepMode> yawStepMode;
    private final /* synthetic */ class_2338.class_2339 breakRenderPos;
    private final /* synthetic */ class_243 lastRotationPos;
    private /* synthetic */ double lastYaw;
    private final /* synthetic */ Setting<Integer> supportDelay;
    private final /* synthetic */ Setting<Double> breakRange;
    private /* synthetic */ int switchTimer;
    private /* synthetic */ int placingTimer;
    private final /* synthetic */ Setting<Boolean> placement112;
    private /* synthetic */ double serverYaw;
    private final /* synthetic */ Setting<Integer> breakAttempts;
    private /* synthetic */ int lastRotationTimer;
    private /* synthetic */ boolean isLastRotationPos;
    private /* synthetic */ int bestTargetTimer;
    private /* synthetic */ int breakRenderTimer;
    private final /* synthetic */ Setting<Double> maxDamage;
    private final /* synthetic */ Setting<Integer> renderTime;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> predictMovement;
    private final /* synthetic */ Setting<Boolean> facePlace;
    private /* synthetic */ class_3959 raycastContext;
    private /* synthetic */ int placeTimer;
    private final /* synthetic */ Setting<Double> yawSteps;
    private /* synthetic */ double renderDamage;
    private final /* synthetic */ class_243 playerEyePos;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ class_2338.class_2339 placingCrystalBlockPos;
    private final /* synthetic */ Setting<Integer> renderBreakTime;
    private final /* synthetic */ class_2338.class_2339 blockPos;
    private final /* synthetic */ SettingGroup sgPause;
    private final /* synthetic */ Setting<Boolean> renderBreak;
    private final /* synthetic */ SettingGroup sgRender;
    private final /* synthetic */ IntSet removed;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private final /* synthetic */ Setting<Boolean> renderDamageText;
    private /* synthetic */ boolean didRotateThisTick;
    private final /* synthetic */ Setting<Double> facePlaceHealth;
    private final /* synthetic */ Setting<Boolean> doPlace;
    private final /* synthetic */ Setting<Boolean> doBreak;
    private final /* synthetic */ Int2IntMap waitingToExplode;
    private final /* synthetic */ Setting<AutoSwitchMode> autoSwitch;
    private final /* synthetic */ Setting<Double> placeRange;
    private final /* synthetic */ Setting<Double> minDamage;
    private final /* synthetic */ IntSet placedCrystals;
    private final /* synthetic */ Setting<Double> placeWallsRange;
    private /* synthetic */ int breakTimer;
    private final /* synthetic */ Setting<Double> breakWallsRange;
    private /* synthetic */ double bestTargetDamage;
    private final /* synthetic */ Setting<Boolean> smartDelay;
    private final /* synthetic */ SettingGroup sgFacePlace;
    private final /* synthetic */ Setting<Boolean> renderSwing;
    private /* synthetic */ class_1657 bestTarget;
    private final /* synthetic */ SettingGroup sgBreak;
    private /* synthetic */ double lastPitch;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Keybind> forceFacePlace;
    private final /* synthetic */ Setting<Boolean> facePlaceArmor;
    private final /* synthetic */ List<class_1657> targets;
    private final /* synthetic */ Setting<Integer> minimumCrystalAge;
    private final /* synthetic */ Setting<Boolean> onlyBreakOwn;
    private /* synthetic */ int renderTimer;
    private final /* synthetic */ Setting<Boolean> minePause;
    private final /* synthetic */ Setting<Boolean> eatPause;
    private /* synthetic */ boolean placing;
    private final /* synthetic */ Setting<Double> damageTextScale;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> drinkPause;
    private final /* synthetic */ Setting<SupportMode> support;
    private final /* synthetic */ SettingGroup sgPlace;
    private final /* synthetic */ Setting<Double> facePlaceDurability;
    private final /* synthetic */ Setting<Integer> switchDelay;
    private final /* synthetic */ class_238 box;
    private final /* synthetic */ Vec3 vec3;
    private final /* synthetic */ class_243 vec3dRayTraceEnd;
    private final /* synthetic */ Setting<Boolean> fastBreak;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ class_2338.class_2339 renderPos;

    private void attackCrystal(class_1297 lIlllIlIlIIIlII) {
        CrystalAura lIlllIlIlIIIlIl;
        lIlllIlIlIIIlIl.mc.field_1724.field_3944.method_2883((class_2596)new class_2824(lIlllIlIlIIIlII, lIlllIlIlIIIlIl.mc.field_1724.method_5715()));
        class_1268 lIlllIlIlIIIIll = InvUtils.findInHotbar(class_1802.field_8301).getHand();
        if (lIlllIlIlIIIIll == null) {
            lIlllIlIlIIIIll = class_1268.field_5808;
        }
        if (lIlllIlIlIIIlIl.renderSwing.get().booleanValue()) {
            lIlllIlIlIIIlIl.mc.field_1724.method_6104(lIlllIlIlIIIIll);
        } else {
            lIlllIlIlIIIlIl.mc.method_1562().method_2883((class_2596)new class_2879(lIlllIlIlIIIIll));
        }
    }

    private double getBreakDamage(class_1297 lIlllIlIllIIlIl, boolean lIlllIlIllIIlII) {
        boolean lIlllIlIllIIlll;
        CrystalAura lIlllIlIllIllII;
        if (!(lIlllIlIllIIlIl instanceof class_1511)) {
            return 0.0;
        }
        if (lIlllIlIllIllII.onlyBreakOwn.get().booleanValue() && !lIlllIlIllIllII.placedCrystals.contains(lIlllIlIllIIlIl.method_5628())) {
            return 0.0;
        }
        if (lIlllIlIllIllII.removed.contains(lIlllIlIllIIlIl.method_5628())) {
            return 0.0;
        }
        if (lIlllIlIllIllII.attemptedBreaks.get(lIlllIlIllIIlIl.method_5628()) > lIlllIlIllIllII.breakAttempts.get()) {
            return 0.0;
        }
        if (lIlllIlIllIIlII && lIlllIlIllIIlIl.field_6012 < lIlllIlIllIllII.minimumCrystalAge.get()) {
            return 0.0;
        }
        if (lIlllIlIllIllII.isOutOfRange(lIlllIlIllIIlIl.method_19538(), lIlllIlIllIIlIl.method_24515(), false)) {
            return 0.0;
        }
        lIlllIlIllIllII.blockPos.method_10101((class_2382)lIlllIlIllIIlIl.method_24515()).method_10100(0, -1, 0);
        double lIlllIlIllIlIIl = DamageUtils.crystalDamage((class_1657)lIlllIlIllIllII.mc.field_1724, lIlllIlIllIIlIl.method_19538(), lIlllIlIllIllII.predictMovement.get(), lIlllIlIllIllII.raycastContext, (class_2338)lIlllIlIllIllII.blockPos, lIlllIlIllIllII.ignoreTerrain.get());
        if (lIlllIlIllIlIIl > lIlllIlIllIllII.maxDamage.get() || lIlllIlIllIllII.antiSuicide.get().booleanValue() && lIlllIlIllIlIIl >= (double)EntityUtils.getTotalHealth((class_1657)lIlllIlIllIllII.mc.field_1724)) {
            return 0.0;
        }
        double lIlllIlIllIlIII = lIlllIlIllIllII.getDamageToTargets(lIlllIlIllIIlIl.method_19538(), (class_2338)lIlllIlIllIllII.blockPos, true, false);
        boolean bl = lIlllIlIllIIlll = lIlllIlIllIllII.facePlace.get() != false && lIlllIlIllIllII.shouldFacePlace(lIlllIlIllIIlIl.method_24515()) || lIlllIlIllIllII.forceFacePlace.get().isPressed();
        if (!lIlllIlIllIIlll && lIlllIlIllIlIII < lIlllIlIllIllII.minDamage.get()) {
            return 0.0;
        }
        return lIlllIlIllIlIII;
    }

    private boolean isValidWeaknessItem(class_1799 lIlllIlIlIIllII) {
        if (!(lIlllIlIlIIllII.method_7909() instanceof class_1831) || lIlllIlIlIIllII.method_7909() instanceof class_1794) {
            return false;
        }
        class_1832 lIlllIlIlIIlIll = ((class_1831)lIlllIlIlIIllII.method_7909()).method_8022();
        return lIlllIlIlIIlIll == class_1834.field_8930 || lIlllIlIlIIlIll == class_1834.field_22033;
    }

    @Override
    public String getInfoString() {
        CrystalAura lIlllIIlIIIlIll;
        return lIlllIIlIIIlIll.bestTarget != null && lIlllIIlIIIlIll.bestTargetTimer > 0 ? lIlllIIlIIIlIll.bestTarget.method_7334().getName() : null;
    }

    private void doBreak(class_1297 lIlllIlIlIlIllI) {
        CrystalAura lIlllIlIlIlIlll;
        if (lIlllIlIlIlIlll.antiWeakness.get().booleanValue()) {
            class_1293 lIlllIlIlIllIll = lIlllIlIlIlIlll.mc.field_1724.method_6112(class_1294.field_5911);
            class_1293 lIlllIlIlIllIlI = lIlllIlIlIlIlll.mc.field_1724.method_6112(class_1294.field_5910);
            if (!(lIlllIlIlIllIll == null || lIlllIlIlIllIlI != null && lIlllIlIlIllIlI.method_5578() > lIlllIlIlIllIll.method_5578() || lIlllIlIlIlIlll.isValidWeaknessItem(lIlllIlIlIlIlll.mc.field_1724.method_6047()))) {
                if (!InvUtils.swap(InvUtils.findInHotbar(lIlllIlIlIlIlll::isValidWeaknessItem).getSlot())) {
                    return;
                }
                lIlllIlIlIlIlll.switchTimer = 1;
                return;
            }
        }
        boolean lIlllIlIlIlIlIl = true;
        if (lIlllIlIlIlIlll.rotate.get().booleanValue()) {
            double lIlllIlIlIllIII;
            double lIlllIlIlIllIIl = Rotations.getYaw(lIlllIlIlIlIllI);
            if (lIlllIlIlIlIlll.doYawSteps(lIlllIlIlIllIIl, lIlllIlIlIllIII = Rotations.getPitch(lIlllIlIlIlIllI, Target.Feet))) {
                lIlllIlIlIlIlll.setRotation(true, lIlllIlIlIlIllI.method_19538(), 0.0, 0.0);
                Rotations.rotate(lIlllIlIlIllIIl, lIlllIlIlIllIII, 50, () -> {
                    CrystalAura lIllIlllllIllIl;
                    lIllIlllllIllIl.attackCrystal(lIlllIlIlIlIllI);
                });
                lIlllIlIlIlIlll.breakTimer = lIlllIlIlIlIlll.breakDelay.get();
            } else {
                lIlllIlIlIlIlIl = false;
            }
        } else {
            lIlllIlIlIlIlll.attackCrystal(lIlllIlIlIlIllI);
            lIlllIlIlIlIlll.breakTimer = lIlllIlIlIlIlll.breakDelay.get();
        }
        if (lIlllIlIlIlIlIl) {
            lIlllIlIlIlIlll.removed.add(lIlllIlIlIlIllI.method_5628());
            lIlllIlIlIlIlll.attemptedBreaks.put(lIlllIlIlIlIllI.method_5628(), lIlllIlIlIlIlll.attemptedBreaks.get(lIlllIlIlIlIllI.method_5628()) + 1);
            lIlllIlIlIlIlll.waitingToExplode.put(lIlllIlIlIlIllI.method_5628(), 0);
            lIlllIlIlIlIlll.breakRenderPos.method_10101((class_2382)lIlllIlIlIlIllI.method_24515().method_10074());
            lIlllIlIlIlIlll.breakRenderTimer = lIlllIlIlIlIlll.renderBreakTime.get();
        }
    }

    @EventHandler
    private void onPacketSent(PacketEvent.Sent lIlllIIlllllllI) {
        if (lIlllIIlllllllI.packet instanceof class_2828) {
            CrystalAura lIlllIIllllllIl;
            lIlllIIllllllIl.serverYaw = ((class_2828)lIlllIIlllllllI.packet).method_12271((float)lIlllIIllllllIl.serverYaw);
        }
    }

    private void doBreak() {
        CrystalAura lIlllIlIllllIII;
        if (!lIlllIlIllllIII.doBreak.get().booleanValue() || lIlllIlIllllIII.breakTimer > 0 || lIlllIlIllllIII.switchTimer > 0) {
            return;
        }
        double lIlllIlIllllIlI = 0.0;
        class_1297 lIlllIlIllllIIl = null;
        for (class_1297 lIlllIlIlllllII : lIlllIlIllllIII.mc.field_1687.method_18112()) {
            double lIlllIlIlllllIl = lIlllIlIllllIII.getBreakDamage(lIlllIlIlllllII, true);
            if (!(lIlllIlIlllllIl > lIlllIlIllllIlI)) continue;
            lIlllIlIllllIlI = lIlllIlIlllllIl;
            lIlllIlIllllIIl = lIlllIlIlllllII;
        }
        if (lIlllIlIllllIIl != null) {
            lIlllIlIllllIII.doBreak(lIlllIlIllllIIl);
        }
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent lIlllIllIIlIIll) {
        if (lIlllIllIIlIIll.entity instanceof class_1511) {
            CrystalAura lIlllIllIIlIllI;
            lIlllIllIIlIllI.placedCrystals.remove(lIlllIllIIlIIll.entity.method_5628());
            lIlllIllIIlIllI.removed.remove(lIlllIllIIlIIll.entity.method_5628());
            lIlllIllIIlIllI.waitingToExplode.remove(lIlllIllIIlIIll.entity.method_5628());
        }
    }

    public CrystalAura() {
        super(Categories.Combat, "crystal-aura", "Automatically places and attacks crystals.");
        CrystalAura lIlllIllIllllII;
        lIlllIllIllllII.sgGeneral = lIlllIllIllllII.settings.getDefaultGroup();
        lIlllIllIllllII.sgPlace = lIlllIllIllllII.settings.createGroup("Place");
        lIlllIllIllllII.sgFacePlace = lIlllIllIllllII.settings.createGroup("Face Place");
        lIlllIllIllllII.sgBreak = lIlllIllIllllII.settings.createGroup("Break");
        lIlllIllIllllII.sgPause = lIlllIllIllllII.settings.createGroup("Pause");
        lIlllIllIllllII.sgRender = lIlllIllIllllII.settings.createGroup("Render");
        lIlllIllIllllII.targetRange = lIlllIllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("Range in which to target players.").defaultValue(10.0).min(0.0).sliderMax(16.0).build());
        lIlllIllIllllII.predictMovement = lIlllIllIllllII.sgGeneral.add(new BoolSetting.Builder().name("predict-movement").description("Predicts target movement.").defaultValue(false).build());
        lIlllIllIllllII.ignoreTerrain = lIlllIllIllllII.sgGeneral.add(new BoolSetting.Builder().name("ignore-terrain").description("Completely ignores terrain if it can be blown up by end crystals.").defaultValue(true).build());
        lIlllIllIllllII.minDamage = lIlllIllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("min-damage").description("Minimum damage the crystal needs to deal to your target.").defaultValue(6.0).min(0.0).build());
        lIlllIllIllllII.maxDamage = lIlllIllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("max-damage").description("Maximum damage crystals can deal to yourself.").defaultValue(6.0).min(0.0).max(36.0).sliderMax(36.0).build());
        lIlllIllIllllII.autoSwitch = lIlllIllIllllII.sgGeneral.add(new EnumSetting.Builder().name("auto-switch").description("Switches to crystals in your hotbar once a target is found.").defaultValue(AutoSwitchMode.Normal).build());
        lIlllIllIllllII.rotate = lIlllIllIllllII.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates server-side towards the crystals being hit/placed.").defaultValue(true).build());
        lIlllIllIllllII.yawStepMode = lIlllIllIllllII.sgGeneral.add(new EnumSetting.Builder().name("yaw-steps-mode").description("When to run the yaw steps check.").defaultValue(YawStepMode.Break).visible(lIlllIllIllllII.rotate::get).build());
        lIlllIllIllllII.yawSteps = lIlllIllIllllII.sgGeneral.add(new DoubleSetting.Builder().name("yaw-steps").description("Maximum number of degrees its allowed to rotate in one tick.").defaultValue(180.0).min(1.0).max(180.0).sliderMin(1.0).sliderMax(180.0).visible(lIlllIllIllllII.rotate::get).build());
        lIlllIllIllllII.antiSuicide = lIlllIllIllllII.sgGeneral.add(new BoolSetting.Builder().name("anti-suicide").description("Will not place and break crystals if they will kill you.").defaultValue(true).build());
        lIlllIllIllllII.doPlace = lIlllIllIllllII.sgPlace.add(new BoolSetting.Builder().name("place").description("If the CA should place crystals.").defaultValue(true).build());
        lIlllIllIllllII.placeDelay = lIlllIllIllllII.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The delay in ticks to wait to place a crystal after it's exploded.").defaultValue(0).min(0).sliderMin(0).sliderMax(20).build());
        lIlllIllIllllII.placeRange = lIlllIllIllllII.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("Range in which to place crystals.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        lIlllIllIllllII.placeWallsRange = lIlllIllIllllII.sgPlace.add(new DoubleSetting.Builder().name("place-walls-range").description("Range in which to place crystals when behind blocks.").defaultValue(3.5).min(0.0).sliderMax(6.0).build());
        lIlllIllIllllII.placement112 = lIlllIllIllllII.sgPlace.add(new BoolSetting.Builder().name("1.12-placement").description("Uses 1.12 crystal placement.").defaultValue(false).build());
        lIlllIllIllllII.support = lIlllIllIllllII.sgPlace.add(new EnumSetting.Builder().name("support").description("Places a support block in air if no other position have been found.").defaultValue(SupportMode.Disabled).build());
        lIlllIllIllllII.supportDelay = lIlllIllIllllII.sgPlace.add(new IntSetting.Builder().name("support-delay").description("Delay in ticks after placing support block.").defaultValue(1).min(0).visible(() -> {
            CrystalAura lIllIlllllIlIII;
            return lIllIlllllIlIII.support.get() != SupportMode.Disabled;
        }).build());
        lIlllIllIllllII.facePlace = lIlllIllIllllII.sgFacePlace.add(new BoolSetting.Builder().name("face-place").description("Will face-place when target is below a certain health or armor durability threshold.").defaultValue(true).build());
        lIlllIllIllllII.facePlaceHealth = lIlllIllIllllII.sgFacePlace.add(new DoubleSetting.Builder().name("face-place-health").description("The health the target has to be at to start face placing.").defaultValue(8.0).min(1.0).sliderMin(1.0).sliderMax(36.0).visible(lIlllIllIllllII.facePlace::get).build());
        lIlllIllIllllII.facePlaceDurability = lIlllIllIllllII.sgFacePlace.add(new DoubleSetting.Builder().name("face-place-durability").description("The durability threshold percentage to be able to face-place.").defaultValue(2.0).min(1.0).sliderMin(1.0).sliderMax(100.0).visible(lIlllIllIllllII.facePlace::get).build());
        lIlllIllIllllII.facePlaceArmor = lIlllIllIllllII.sgFacePlace.add(new BoolSetting.Builder().name("face-place-missing-armor").description("Automatically starts face placing when a target misses a piece of armor.").defaultValue(false).visible(lIlllIllIllllII.facePlace::get).build());
        lIlllIllIllllII.forceFacePlace = lIlllIllIllllII.sgFacePlace.add(new KeybindSetting.Builder().name("force-face-place").description("Starts face place when this button is pressed.").defaultValue(Keybind.fromKey(-1)).build());
        lIlllIllIllllII.doBreak = lIlllIllIllllII.sgBreak.add(new BoolSetting.Builder().name("break").description("If the CA should break crystals.").defaultValue(true).build());
        lIlllIllIllllII.breakDelay = lIlllIllIllllII.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The delay in ticks to wait to break a crystal after it's placed.").defaultValue(0).min(0).sliderMin(0).sliderMax(20).build());
        lIlllIllIllllII.smartDelay = lIlllIllIllllII.sgBreak.add(new BoolSetting.Builder().name("smart-delay").description("Only breaks crystals when the target can receive damage.").defaultValue(false).build());
        lIlllIllIllllII.switchDelay = lIlllIllIllllII.sgBreak.add(new IntSetting.Builder().name("switch-delay").description("The delay in ticks to wait to break a crystal after switching hotbar slot.").defaultValue(0).min(0).sliderMax(10).build());
        lIlllIllIllllII.breakRange = lIlllIllIllllII.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("Range in which to break crystals.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        lIlllIllIllllII.breakWallsRange = lIlllIllIllllII.sgBreak.add(new DoubleSetting.Builder().name("break-walls-range").description("Range in which to break crystals when behind blocks.").defaultValue(3.0).min(0.0).sliderMax(6.0).build());
        lIlllIllIllllII.onlyBreakOwn = lIlllIllIllllII.sgBreak.add(new BoolSetting.Builder().name("only-own").description("Only breaks own crystals.").defaultValue(false).build());
        lIlllIllIllllII.breakAttempts = lIlllIllIllllII.sgBreak.add(new IntSetting.Builder().name("break-attempts").description("How many times to hit a crystal before stopping to target it.").defaultValue(2).sliderMin(1).sliderMax(5).build());
        lIlllIllIllllII.minimumCrystalAge = lIlllIllIllllII.sgBreak.add(new IntSetting.Builder().name("minimum-crystal-age").description("How many ticks the crystal needs to exist in a world before trying to attack it.").defaultValue(0).min(0).build());
        lIlllIllIllllII.fastBreak = lIlllIllIllllII.sgBreak.add(new BoolSetting.Builder().name("fast-break").description("Ignores break delay and tries to break the crystal as soon as it's spawned in the world.").defaultValue(true).build());
        lIlllIllIllllII.antiWeakness = lIlllIllIllllII.sgBreak.add(new BoolSetting.Builder().name("anti-weakness").description("Switches to tools with high enough damage to explode the crystal with weakness effect.").defaultValue(true).build());
        lIlllIllIllllII.eatPause = lIlllIllIllllII.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses Crystal Aura when eating.").defaultValue(true).build());
        lIlllIllIllllII.drinkPause = lIlllIllIllllII.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses Crystal Aura when drinking.").defaultValue(true).build());
        lIlllIllIllllII.minePause = lIlllIllIllllII.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses Crystal Aura when mining.").defaultValue(false).build());
        lIlllIllIllllII.renderSwing = lIlllIllIllllII.sgRender.add(new BoolSetting.Builder().name("swing").description("Renders hand swinging client side.").defaultValue(true).build());
        lIlllIllIllllII.render = lIlllIllIllllII.sgRender.add(new BoolSetting.Builder().name("render").description("Renders a block overlay over the block the crystals are being placed on.").defaultValue(true).build());
        lIlllIllIllllII.renderBreak = lIlllIllIllllII.sgRender.add(new BoolSetting.Builder().name("break").description("Renders a block overlay over the block the crystals are broken on.").defaultValue(false).build());
        lIlllIllIllllII.shapeMode = lIlllIllIllllII.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        lIlllIllIllllII.sideColor = lIlllIllIllllII.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the block overlay.").defaultValue(new SettingColor(255, 255, 255, 45)).build());
        lIlllIllIllllII.lineColor = lIlllIllIllllII.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the block overlay.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        lIlllIllIllllII.renderDamageText = lIlllIllIllllII.sgRender.add(new BoolSetting.Builder().name("damage").description("Renders crystal damage text in the block overlay.").defaultValue(true).build());
        lIlllIllIllllII.damageTextScale = lIlllIllIllllII.sgRender.add(new DoubleSetting.Builder().name("damage-scale").description("How big the damage text should be.").defaultValue(1.25).min(1.0).sliderMax(4.0).visible(lIlllIllIllllII.renderDamageText::get).build());
        lIlllIllIllllII.renderTime = lIlllIllIllllII.sgRender.add(new IntSetting.Builder().name("render-time").description("How long to render for.").defaultValue(10).min(0).sliderMax(20).build());
        lIlllIllIllllII.renderBreakTime = lIlllIllIllllII.sgRender.add(new IntSetting.Builder().name("break-time").description("How long to render breaking for.").defaultValue(13).min(0).sliderMax(20).visible(lIlllIllIllllII.renderBreak::get).build());
        lIlllIllIllllII.targets = new ArrayList<class_1657>();
        lIlllIllIllllII.vec3d = new class_243(0.0, 0.0, 0.0);
        lIlllIllIllllII.playerEyePos = new class_243(0.0, 0.0, 0.0);
        lIlllIllIllllII.vec3 = new Vec3();
        lIlllIllIllllII.blockPos = new class_2338.class_2339();
        lIlllIllIllllII.box = new class_238(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        lIlllIllIllllII.vec3dRayTraceEnd = new class_243(0.0, 0.0, 0.0);
        lIlllIllIllllII.placedCrystals = new IntOpenHashSet();
        lIlllIllIllllII.placingCrystalBlockPos = new class_2338.class_2339();
        lIlllIllIllllII.removed = new IntOpenHashSet();
        lIlllIllIllllII.attemptedBreaks = new Int2IntOpenHashMap();
        lIlllIllIllllII.waitingToExplode = new Int2IntOpenHashMap();
        lIlllIllIllllII.lastRotationPos = new class_243(0.0, 0.0, 0.0);
        lIlllIllIllllII.renderPos = new class_2338.class_2339();
        lIlllIllIllllII.breakRenderPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent lIlllIllIIlllII) {
        double lIlllIllIIllllI;
        CrystalAura lIlllIllIIllIll;
        if (!(lIlllIllIIlllII.entity instanceof class_1511)) {
            return;
        }
        if (lIlllIllIIllIll.placing && lIlllIllIIlllII.entity.method_24515().equals((Object)lIlllIllIIllIll.placingCrystalBlockPos)) {
            lIlllIllIIllIll.placing = false;
            lIlllIllIIllIll.placingTimer = 0;
            lIlllIllIIllIll.placedCrystals.add(lIlllIllIIlllII.entity.method_5628());
        }
        if (lIlllIllIIllIll.fastBreak.get().booleanValue() && !lIlllIllIIllIll.didRotateThisTick && (lIlllIllIIllllI = lIlllIllIIllIll.getBreakDamage(lIlllIllIIlllII.entity, true)) > lIlllIllIIllIll.minDamage.get()) {
            lIlllIllIIllIll.doBreak(lIlllIllIIlllII.entity);
        }
    }

    private void doPlace() {
        CrystalAura lIlllIlIIllIlII;
        if (!lIlllIlIIllIlII.doPlace.get().booleanValue() || lIlllIlIIllIlII.placeTimer > 0) {
            return;
        }
        if (!InvUtils.findInHotbar(class_1802.field_8301).found()) {
            return;
        }
        if (lIlllIlIIllIlII.autoSwitch.get() == AutoSwitchMode.None && lIlllIlIIllIlII.mc.field_1724.method_6079().method_7909() != class_1802.field_8301 && lIlllIlIIllIlII.mc.field_1724.method_6047().method_7909() != class_1802.field_8301) {
            return;
        }
        for (class_1297 lIlllIlIIllIlIl : lIlllIlIIllIlII.mc.field_1687.method_18112()) {
            if (!(lIlllIlIIllIlII.getBreakDamage(lIlllIlIIllIlIl, false) > 0.0)) continue;
            return;
        }
        AtomicDouble lIlllIlIIllIIll = new AtomicDouble(0.0);
        AtomicReference<class_2338.class_2339> lIlllIlIIllIIlI = new AtomicReference<class_2338.class_2339>(new class_2338.class_2339());
        AtomicBoolean lIlllIlIIllIIIl = new AtomicBoolean(lIlllIlIIllIlII.support.get() != SupportMode.Disabled);
        BlockIterator.register((int)Math.ceil(lIlllIlIIllIlII.placeRange.get()), (int)Math.ceil(lIlllIlIIllIlII.placeRange.get()), (lIlllIIIIIIIlIl, lIllIllllllIlll) -> {
            boolean lIlllIIIIIIIIII;
            CrystalAura lIllIllllllllII;
            boolean lIlllIIIIIIIIll;
            boolean bl = lIlllIIIIIIIIll = lIllIllllllIlll.method_27852(class_2246.field_9987) || lIllIllllllIlll.method_27852(class_2246.field_10540);
            if (!(lIlllIIIIIIIIll || lIlllIlIIllIIIl.get() && lIllIllllllIlll.method_26207().method_15800())) {
                return;
            }
            lIllIllllllllII.blockPos.method_10103(lIlllIIIIIIIlIl.method_10263(), lIlllIIIIIIIlIl.method_10264() + 1, lIlllIIIIIIIlIl.method_10260());
            if (!lIllIllllllllII.mc.field_1687.method_8320((class_2338)lIllIllllllllII.blockPos).method_26215()) {
                return;
            }
            if (lIllIllllllllII.placement112.get().booleanValue()) {
                lIllIllllllllII.blockPos.method_10100(0, 1, 0);
                if (!lIllIllllllllII.mc.field_1687.method_8320((class_2338)lIllIllllllllII.blockPos).method_26215()) {
                    return;
                }
            }
            ((IVec3d)lIllIllllllllII.vec3d).set((double)lIlllIIIIIIIlIl.method_10263() + 0.5, lIlllIIIIIIIlIl.method_10264() + 1, (double)lIlllIIIIIIIlIl.method_10260() + 0.5);
            lIllIllllllllII.blockPos.method_10101((class_2382)lIlllIIIIIIIlIl).method_10100(0, 1, 0);
            if (lIllIllllllllII.isOutOfRange(lIllIllllllllII.vec3d, (class_2338)lIllIllllllllII.blockPos, true)) {
                return;
            }
            double lIlllIIIIIIIIlI = DamageUtils.crystalDamage((class_1657)lIllIllllllllII.mc.field_1724, lIllIllllllllII.vec3d, lIllIllllllllII.predictMovement.get(), lIllIllllllllII.raycastContext, lIlllIIIIIIIlIl, lIllIllllllllII.ignoreTerrain.get());
            if (lIlllIIIIIIIIlI > lIllIllllllllII.maxDamage.get() || lIllIllllllllII.antiSuicide.get().booleanValue() && lIlllIIIIIIIIlI >= (double)EntityUtils.getTotalHealth((class_1657)lIllIllllllllII.mc.field_1724)) {
                return;
            }
            double lIlllIIIIIIIIIl = lIllIllllllllII.getDamageToTargets(lIllIllllllllII.vec3d, (class_2338)lIlllIIIIIIIlIl, false, !lIlllIIIIIIIIll && lIllIllllllllII.support.get() == SupportMode.Fast);
            boolean bl2 = lIlllIIIIIIIIII = lIllIllllllllII.facePlace.get() != false && lIllIllllllllII.shouldFacePlace((class_2338)lIllIllllllllII.blockPos) || lIllIllllllllII.forceFacePlace.get().isPressed();
            if (!lIlllIIIIIIIIII && lIlllIIIIIIIIIl < lIllIllllllllII.minDamage.get()) {
                return;
            }
            double lIllIllllllllll = lIlllIIIIIIIlIl.method_10263();
            double lIllIlllllllllI = lIlllIIIIIIIlIl.method_10264() + 1;
            double lIllIllllllllIl = lIlllIIIIIIIlIl.method_10260();
            ((IBox)lIllIllllllllII.box).set(lIllIllllllllll, lIllIlllllllllI, lIllIllllllllIl, lIllIllllllllll + 1.0, lIllIlllllllllI + (double)(lIllIllllllllII.placement112.get() != false ? 1 : 2), lIllIllllllllIl + 1.0);
            if (lIllIllllllllII.intersectsWithEntities(lIllIllllllllII.box)) {
                return;
            }
            if (lIlllIIIIIIIIIl > lIlllIlIIllIIll.get() || lIlllIlIIllIIIl.get() && lIlllIIIIIIIIll) {
                lIlllIlIIllIIll.set(lIlllIIIIIIIIIl);
                ((class_2338.class_2339)lIlllIlIIllIIlI.get()).method_10101((class_2382)lIlllIIIIIIIlIl);
            }
            if (lIlllIIIIIIIIll) {
                lIlllIlIIllIIIl.set(false);
            }
        });
        BlockIterator.after(() -> {
            CrystalAura lIlllIIIIllIIIl;
            if (lIlllIlIIllIIll.get() == 0.0) {
                return;
            }
            class_3965 lIlllIIIIlIllIl = lIlllIIIIllIIIl.getPlaceInfo((class_2338)lIlllIlIIllIIlI.get());
            ((IVec3d)lIlllIIIIllIIIl.vec3d).set((double)lIlllIIIIlIllIl.method_17777().method_10263() + 0.5 + (double)lIlllIIIIlIllIl.method_17780().method_10163().method_10263() * 1.0 / 2.0, (double)lIlllIIIIlIllIl.method_17777().method_10264() + 0.5 + (double)lIlllIIIIlIllIl.method_17780().method_10163().method_10264() * 1.0 / 2.0, (double)lIlllIIIIlIllIl.method_17777().method_10260() + 0.5 + (double)lIlllIIIIlIllIl.method_17780().method_10163().method_10260() * 1.0 / 2.0);
            if (lIlllIIIIllIIIl.rotate.get().booleanValue()) {
                double lIlllIIIIllIIll = Rotations.getYaw(lIlllIIIIllIIIl.vec3d);
                double lIlllIIIIllIIlI = Rotations.getPitch(lIlllIIIIllIIIl.vec3d);
                if (lIlllIIIIllIIIl.yawStepMode.get() == YawStepMode.Break || lIlllIIIIllIIIl.doYawSteps(lIlllIIIIllIIll, lIlllIIIIllIIlI)) {
                    lIlllIIIIllIIIl.setRotation(true, lIlllIIIIllIIIl.vec3d, 0.0, 0.0);
                    Rotations.rotate(lIlllIIIIllIIll, lIlllIIIIllIIlI, 50, () -> {
                        CrystalAura lIlllIIIIIllIll;
                        lIlllIIIIIllIll.placeCrystal(lIlllIIIIlIllIl, lIlllIlIIllIIll.get(), lIlllIlIIllIIIl.get() ? (class_2338)lIlllIlIIllIIlI.get() : null);
                    });
                    lIlllIIIIllIIIl.placeTimer += lIlllIIIIllIIIl.placeDelay.get().intValue();
                }
            } else {
                lIlllIIIIllIIIl.placeCrystal(lIlllIIIIlIllIl, lIlllIlIIllIIll.get(), lIlllIlIIllIIIl.get() ? (class_2338)lIlllIlIIllIIlI.get() : null);
                lIlllIIIIllIIIl.placeTimer += lIlllIIIIllIIIl.placeDelay.get().intValue();
            }
        });
    }

    private void placeCrystal(class_3965 lIlllIlIIIlIIII, double lIlllIlIIIIIlll, class_2338 lIlllIlIIIIIllI) {
        class_1268 lIlllIlIIIIlIlI;
        CrystalAura lIlllIlIIIlIIIl;
        class_1792 lIlllIlIIIIllIl = lIlllIlIIIIIllI == null ? class_1802.field_8301 : class_1802.field_8281;
        FindItemResult lIlllIlIIIIllII = InvUtils.findInHotbar(lIlllIlIIIIllIl);
        if (!lIlllIlIIIIllII.found()) {
            return;
        }
        int lIlllIlIIIIlIll = lIlllIlIIIlIIIl.mc.field_1724.field_7514.field_7545;
        if (lIlllIlIIIlIIIl.autoSwitch.get() != AutoSwitchMode.None && !lIlllIlIIIIllII.isOffhand()) {
            InvUtils.swap(lIlllIlIIIIllII.getSlot());
        }
        if ((lIlllIlIIIIlIlI = lIlllIlIIIIllII.getHand()) == null) {
            return;
        }
        if (lIlllIlIIIIIllI == null) {
            lIlllIlIIIlIIIl.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(lIlllIlIIIIlIlI, lIlllIlIIIlIIII));
            if (lIlllIlIIIlIIIl.renderSwing.get().booleanValue()) {
                lIlllIlIIIlIIIl.mc.field_1724.method_6104(lIlllIlIIIIlIlI);
            } else {
                lIlllIlIIIlIIIl.mc.method_1562().method_2883((class_2596)new class_2879(lIlllIlIIIIlIlI));
            }
            lIlllIlIIIlIIIl.placing = true;
            lIlllIlIIIlIIIl.placingTimer = 4;
            lIlllIlIIIlIIIl.placingCrystalBlockPos.method_10101((class_2382)lIlllIlIIIlIIII.method_17777()).method_10100(0, 1, 0);
            lIlllIlIIIlIIIl.renderTimer = lIlllIlIIIlIIIl.renderTime.get();
            lIlllIlIIIlIIIl.renderPos.method_10101((class_2382)lIlllIlIIIlIIII.method_17777());
            lIlllIlIIIlIIIl.renderDamage = lIlllIlIIIIIlll;
        } else {
            BlockUtils.place(lIlllIlIIIIIllI, lIlllIlIIIIllII, false, 0, lIlllIlIIIlIIIl.renderSwing.get(), true, false);
            lIlllIlIIIlIIIl.placeTimer += lIlllIlIIIlIIIl.supportDelay.get().intValue();
            if (lIlllIlIIIlIIIl.supportDelay.get() == 0) {
                lIlllIlIIIlIIIl.placeCrystal(lIlllIlIIIlIIII, lIlllIlIIIIIlll, null);
            }
        }
        if (lIlllIlIIIlIIIl.autoSwitch.get() == AutoSwitchMode.Silent) {
            InvUtils.swap(lIlllIlIIIIlIll);
        }
    }

    public boolean doYawSteps(double lIlllIIlllIlllI, double lIlllIIllllIIll) {
        CrystalAura lIlllIIlllIllll;
        lIlllIIlllIlllI = class_3532.method_15338((double)lIlllIIlllIlllI) + 180.0;
        double lIlllIIllllIIlI = class_3532.method_15338((double)lIlllIIlllIllll.serverYaw) + 180.0;
        if (CrystalAura.distanceBetweenAngles(lIlllIIllllIIlI, lIlllIIlllIlllI) <= lIlllIIlllIllll.yawSteps.get()) {
            return true;
        }
        double lIlllIIllllIIIl = Math.abs(lIlllIIlllIlllI - lIlllIIllllIIlI);
        double lIlllIIllllIIII = lIlllIIlllIllll.serverYaw;
        lIlllIIllllIIII = lIlllIIllllIIlI < lIlllIIlllIlllI ? (lIlllIIllllIIIl < 180.0 ? (lIlllIIllllIIII += lIlllIIlllIllll.yawSteps.get().doubleValue()) : (lIlllIIllllIIII -= lIlllIIlllIllll.yawSteps.get().doubleValue())) : (lIlllIIllllIIIl < 180.0 ? (lIlllIIllllIIII -= lIlllIIlllIllll.yawSteps.get().doubleValue()) : (lIlllIIllllIIII += lIlllIIlllIllll.yawSteps.get().doubleValue()));
        lIlllIIlllIllll.setRotation(false, null, lIlllIIllllIIII, lIlllIIllllIIll);
        Rotations.rotate(lIlllIIllllIIII, lIlllIIllllIIll, -100, null);
        return false;
    }

    private boolean isOutOfRange(class_243 lIlllIIlIlllllI, class_2338 lIlllIIllIIIlII, boolean lIlllIIlIllllII) {
        CrystalAura lIlllIIlIllllll;
        ((IRaycastContext)lIlllIIlIllllll.raycastContext).set(lIlllIIlIllllll.playerEyePos, lIlllIIlIlllllI, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)lIlllIIlIllllll.mc.field_1724);
        class_3965 lIlllIIllIIIIlI = lIlllIIlIllllll.mc.field_1687.method_17742(lIlllIIlIllllll.raycastContext);
        boolean lIlllIIllIIIIIl = lIlllIIllIIIIlI == null || !lIlllIIllIIIIlI.method_17777().equals((Object)lIlllIIllIIIlII);
        double lIlllIIllIIIIII = lIlllIIlIllllll.mc.field_1724.method_19538().method_1022(lIlllIIlIlllllI);
        return lIlllIIllIIIIII > (lIlllIIllIIIIIl ? (lIlllIIlIllllII ? lIlllIIlIllllll.placeWallsRange : lIlllIIlIllllll.breakWallsRange).get() : (lIlllIIlIllllII ? lIlllIIlIllllll.placeRange : lIlllIIlIllllll.breakRange).get());
    }

    private static double distanceBetweenAngles(double lIlllIIlllIIllI, double lIlllIIlllIIlIl) {
        double lIlllIIlllIIlII = Math.abs(lIlllIIlllIIlIl - lIlllIIlllIIllI) % 360.0;
        return lIlllIIlllIIlII > 180.0 ? 360.0 - lIlllIIlllIIlII : lIlllIIlllIIlII;
    }

    @EventHandler
    private void onRender(RenderEvent lIlllIIIlIIlIII) {
        CrystalAura lIlllIIIlIIlIIl;
        if (lIlllIIIlIIlIIl.renderTimer > 0 && lIlllIIIlIIlIIl.render.get().booleanValue()) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)lIlllIIIlIIlIIl.renderPos, lIlllIIIlIIlIIl.sideColor.get(), lIlllIIIlIIlIIl.lineColor.get(), lIlllIIIlIIlIIl.shapeMode.get(), 0);
        }
        if (lIlllIIIlIIlIIl.breakRenderTimer > 0 && lIlllIIIlIIlIIl.renderBreak.get().booleanValue() && !lIlllIIIlIIlIIl.mc.field_1687.method_8320((class_2338)lIlllIIIlIIlIIl.breakRenderPos).method_26215()) {
            int lIlllIIIlIIlIll = lIlllIIIlIIlIIl.sideColor.get().a;
            lIlllIIIlIIlIIl.sideColor.get().a -= 20;
            lIlllIIIlIIlIIl.sideColor.get().validate();
            int lIlllIIIlIIlIlI = lIlllIIIlIIlIIl.lineColor.get().a;
            lIlllIIIlIIlIIl.lineColor.get().a -= 20;
            lIlllIIIlIIlIIl.lineColor.get().validate();
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)lIlllIIIlIIlIIl.breakRenderPos, lIlllIIIlIIlIIl.sideColor.get(), lIlllIIIlIIlIIl.lineColor.get(), lIlllIIIlIIlIIl.shapeMode.get(), 0);
            lIlllIIIlIIlIIl.sideColor.get().a = lIlllIIIlIIlIll;
            lIlllIIIlIIlIIl.lineColor.get().a = lIlllIIIlIIlIlI;
        }
    }

    private class_3965 getPlaceInfo(class_2338 lIlllIlIIlIIIlI) {
        CrystalAura lIlllIlIIlIIIll;
        ((IVec3d)lIlllIlIIlIIIll.vec3d).set(lIlllIlIIlIIIll.mc.field_1724.method_23317(), lIlllIlIIlIIIll.mc.field_1724.method_23318() + (double)lIlllIlIIlIIIll.mc.field_1724.method_18381(lIlllIlIIlIIIll.mc.field_1724.method_18376()), lIlllIlIIlIIIll.mc.field_1724.method_23321());
        for (class_2350 lIlllIlIIlIIlII : class_2350.values()) {
            ((IVec3d)lIlllIlIIlIIIll.vec3dRayTraceEnd).set((double)lIlllIlIIlIIIlI.method_10263() + 0.5 + (double)lIlllIlIIlIIlII.method_10163().method_10263() * 0.5, (double)lIlllIlIIlIIIlI.method_10264() + 0.5 + (double)lIlllIlIIlIIlII.method_10163().method_10264() * 0.5, (double)lIlllIlIIlIIIlI.method_10260() + 0.5 + (double)lIlllIlIIlIIlII.method_10163().method_10260() * 0.5);
            ((IRaycastContext)lIlllIlIIlIIIll.raycastContext).set(lIlllIlIIlIIIll.vec3d, lIlllIlIIlIIIll.vec3dRayTraceEnd, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)lIlllIlIIlIIIll.mc.field_1724);
            class_3965 lIlllIlIIlIIlIl = lIlllIlIIlIIIll.mc.field_1687.method_17742(lIlllIlIIlIIIll.raycastContext);
            if (lIlllIlIIlIIlIl == null || lIlllIlIIlIIlIl.method_17783() != class_239.class_240.field_1332 || !lIlllIlIIlIIlIl.method_17777().equals((Object)lIlllIlIIlIIIlI)) continue;
            return lIlllIlIIlIIlIl;
        }
        class_2350 lIlllIlIIlIIIIl = (double)lIlllIlIIlIIIlI.method_10264() > lIlllIlIIlIIIll.vec3d.field_1351 ? class_2350.field_11033 : class_2350.field_11036;
        return new class_3965(lIlllIlIIlIIIll.vec3d, lIlllIlIIlIIIIl, lIlllIlIIlIIIlI, false);
    }

    @Override
    public void onActivate() {
        CrystalAura lIlllIllIlllIIl;
        lIlllIllIlllIIl.breakTimer = 0;
        lIlllIllIlllIIl.placeTimer = 0;
        lIlllIllIlllIIl.raycastContext = new class_3959(new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)lIlllIllIlllIIl.mc.field_1724);
        lIlllIllIlllIIl.placing = false;
        lIlllIllIlllIIl.placingTimer = 0;
        lIlllIllIlllIIl.serverYaw = lIlllIllIlllIIl.mc.field_1724.field_6031;
        lIlllIllIlllIIl.bestTargetDamage = 0.0;
        lIlllIllIlllIIl.bestTargetTimer = 0;
        lIlllIllIlllIIl.lastRotationTimer = lIlllIllIlllIIl.getLastRotationStopDelay();
        lIlllIllIlllIIl.renderTimer = 0;
        lIlllIllIlllIIl.breakRenderTimer = 0;
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send lIlllIlIIlllIlI) {
        if (lIlllIlIIlllIlI.packet instanceof class_2868) {
            CrystalAura lIlllIlIIllllIl;
            lIlllIlIIllllIl.switchTimer = lIlllIlIIllllIl.switchDelay.get();
        }
    }

    private void findTargets() {
        CrystalAura lIlllIIlIIIIIll;
        lIlllIIlIIIIIll.targets.clear();
        for (class_1657 class_16572 : lIlllIIlIIIIIll.mc.field_1687.method_18456()) {
            if (class_16572.field_7503.field_7477 || class_16572 == lIlllIIlIIIIIll.mc.field_1724 || class_16572.method_29504() || !class_16572.method_5805() || !Friends.get().shouldAttack(class_16572) || !((double)class_16572.method_5739((class_1297)lIlllIIlIIIIIll.mc.field_1724) <= lIlllIIlIIIIIll.targetRange.get())) continue;
            lIlllIIlIIIIIll.targets.add(class_16572);
        }
        for (class_1657 class_16573 : FakePlayerManager.getPlayers()) {
            if (class_16573.method_29504() || !class_16573.method_5805() || !Friends.get().shouldAttack(class_16573) || !((double)class_16573.method_5739((class_1297)lIlllIIlIIIIIll.mc.field_1724) <= lIlllIIlIIIIIll.targetRange.get())) continue;
            lIlllIIlIIIIIll.targets.add(class_16573);
        }
    }

    @EventHandler(priority=100)
    private void onPreTick(TickEvent.Pre lIlllIllIlIlIlI) {
        CrystalAura lIlllIllIlIlIIl;
        lIlllIllIlIlIIl.didRotateThisTick = false;
        ++lIlllIllIlIlIIl.lastRotationTimer;
        if (lIlllIllIlIlIIl.placing) {
            if (lIlllIllIlIlIIl.placingTimer > 0) {
                --lIlllIllIlIlIIl.placingTimer;
            } else {
                lIlllIllIlIlIIl.placing = false;
            }
        }
        if (lIlllIllIlIlIIl.bestTargetTimer > 0) {
            --lIlllIllIlIlIIl.bestTargetTimer;
        }
        lIlllIllIlIlIIl.bestTargetDamage = 0.0;
        if (lIlllIllIlIlIIl.breakTimer > 0) {
            --lIlllIllIlIlIIl.breakTimer;
        }
        if (lIlllIllIlIlIIl.placeTimer > 0) {
            --lIlllIllIlIlIIl.placeTimer;
        }
        if (lIlllIllIlIlIIl.switchTimer > 0) {
            --lIlllIllIlIlIIl.switchTimer;
        }
        if (lIlllIllIlIlIIl.renderTimer > 0) {
            --lIlllIllIlIlIIl.renderTimer;
        }
        if (lIlllIllIlIlIIl.breakRenderTimer > 0) {
            --lIlllIllIlIlIIl.breakRenderTimer;
        }
        IntIterator lIlllIllIlIllII = lIlllIllIlIlIIl.waitingToExplode.keySet().iterator();
        while (lIlllIllIlIllII.hasNext()) {
            int lIlllIllIlIlllI = lIlllIllIlIllII.nextInt();
            int lIlllIllIlIllIl = lIlllIllIlIlIIl.waitingToExplode.get(lIlllIllIlIlllI);
            if (lIlllIllIlIllIl > 3) {
                lIlllIllIlIllII.remove();
                lIlllIllIlIlIIl.removed.remove(lIlllIllIlIlllI);
                continue;
            }
            lIlllIllIlIlIIl.waitingToExplode.put(lIlllIllIlIlllI, lIlllIllIlIllIl + 1);
        }
        if (PlayerUtils.shouldPause(lIlllIllIlIlIIl.minePause.get(), lIlllIllIlIlIIl.eatPause.get(), lIlllIllIlIlIIl.drinkPause.get())) {
            return;
        }
        ((IVec3d)lIlllIllIlIlIIl.playerEyePos).set(lIlllIllIlIlIIl.mc.field_1724.method_19538().field_1352, lIlllIllIlIlIIl.mc.field_1724.method_19538().field_1351 + (double)lIlllIllIlIlIIl.mc.field_1724.method_18381(lIlllIllIlIlIIl.mc.field_1724.method_18376()), lIlllIllIlIlIIl.mc.field_1724.method_19538().field_1350);
        lIlllIllIlIlIIl.findTargets();
        if (lIlllIllIlIlIIl.targets.size() > 0) {
            if (!lIlllIllIlIlIIl.didRotateThisTick) {
                lIlllIllIlIlIIl.doBreak();
            }
            if (!lIlllIllIlIlIIl.didRotateThisTick) {
                lIlllIllIlIlIIl.doPlace();
            }
        }
    }

    private boolean intersectsWithEntities(class_238 lIlllIIIllIIlIl) {
        CrystalAura lIlllIIIlIlllll;
        int lIlllIIIllIIlII = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1323 - 2.0) / 16.0));
        int lIlllIIIllIIIll = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1320 + 2.0) / 16.0));
        int lIlllIIIllIIIlI = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1321 - 2.0) / 16.0));
        int lIlllIIIllIIIIl = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1324 + 2.0) / 16.0));
        class_631 lIlllIIIllIIIII = lIlllIIIlIlllll.mc.field_1687.method_2935();
        for (int lIlllIIIllIIlll = lIlllIIIllIIlII; lIlllIIIllIIlll <= lIlllIIIllIIIll; ++lIlllIIIllIIlll) {
            for (int lIlllIIIllIlIII = lIlllIIIllIIIlI; lIlllIIIllIlIII <= lIlllIIIllIIIIl; ++lIlllIIIllIlIII) {
                class_2818 lIlllIIIllIlIIl = lIlllIIIllIIIII.method_12126(lIlllIIIllIIlll, lIlllIIIllIlIII, false);
                if (lIlllIIIllIlIIl == null) continue;
                class_3509[] lIlllIIIllIllII = lIlllIIIllIlIIl.method_12215();
                int lIlllIIIllIlIll = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1322 - 2.0) / 16.0));
                int lIlllIIIllIlIlI = class_3532.method_15357((double)((lIlllIIIllIIlIl.field_1325 + 2.0) / 16.0));
                lIlllIIIllIlIll = class_3532.method_15340((int)lIlllIIIllIlIll, (int)0, (int)(lIlllIIIllIllII.length - 1));
                lIlllIIIllIlIlI = class_3532.method_15340((int)lIlllIIIllIlIlI, (int)0, (int)(lIlllIIIllIllII.length - 1));
                for (int lIlllIIIllIllIl = lIlllIIIllIlIll; lIlllIIIllIllIl <= lIlllIIIllIlIlI; ++lIlllIIIllIllIl) {
                    class_3509 lIlllIIIllIlllI = lIlllIIIllIllII[lIlllIIIllIllIl];
                    for (class_1297 lIlllIIIllIllll : lIlllIIIllIlllI) {
                        if (!lIlllIIIllIllll.method_5829().method_994(lIlllIIIllIIlIl) || lIlllIIIllIllll.method_7325() || lIlllIIIlIlllll.removed.contains(lIlllIIIllIllll.method_5628())) continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getLastRotationStopDelay() {
        CrystalAura lIlllIllIllIlII;
        return Math.max(10, lIlllIllIllIlII.placeDelay.get() / 2 + lIlllIllIllIlII.breakDelay.get() / 2 + 10);
    }

    private double getDamageToTargets(class_243 lIlllIIlIIllIlI, class_2338 lIlllIIlIIllIIl, boolean lIlllIIlIIllIII, boolean lIlllIIlIIlIlll) {
        CrystalAura lIlllIIlIIlIlIl;
        double lIlllIIlIIlIllI = 0.0;
        if (lIlllIIlIIlIlll) {
            class_1657 lIlllIIlIIllllI = lIlllIIlIIlIlIl.getNearestTarget();
            if (!lIlllIIlIIlIlIl.smartDelay.get().booleanValue() || !lIlllIIlIIllIII || lIlllIIlIIllllI.field_6235 <= 0) {
                lIlllIIlIIlIllI = DamageUtils.crystalDamage(lIlllIIlIIllllI, lIlllIIlIIllIlI, lIlllIIlIIlIlIl.predictMovement.get(), lIlllIIlIIlIlIl.raycastContext, lIlllIIlIIllIIl, lIlllIIlIIlIlIl.ignoreTerrain.get());
            }
        } else {
            for (class_1657 lIlllIIlIIlllII : lIlllIIlIIlIlIl.targets) {
                if (lIlllIIlIIlIlIl.smartDelay.get().booleanValue() && lIlllIIlIIllIII && lIlllIIlIIlllII.field_6235 > 0) continue;
                double lIlllIIlIIlllIl = DamageUtils.crystalDamage(lIlllIIlIIlllII, lIlllIIlIIllIlI, lIlllIIlIIlIlIl.predictMovement.get(), lIlllIIlIIlIlIl.raycastContext, lIlllIIlIIllIIl, lIlllIIlIIlIlIl.ignoreTerrain.get());
                if (lIlllIIlIIlllIl > lIlllIIlIIlIlIl.bestTargetDamage) {
                    lIlllIIlIIlIlIl.bestTarget = lIlllIIlIIlllII;
                    lIlllIIlIIlIlIl.bestTargetDamage = lIlllIIlIIlllIl;
                    lIlllIIlIIlIlIl.bestTargetTimer = 10;
                }
                lIlllIIlIIlIllI += lIlllIIlIIlllIl;
            }
        }
        return lIlllIIlIIlIllI;
    }

    private void setRotation(boolean lIlllIllIIIIlll, class_243 lIlllIllIIIlIll, double lIlllIllIIIIlIl, double lIlllIllIIIlIIl) {
        lIlllIllIIIlIII.didRotateThisTick = true;
        lIlllIllIIIlIII.isLastRotationPos = lIlllIllIIIIlll;
        if (lIlllIllIIIIlll) {
            CrystalAura lIlllIllIIIlIII;
            ((IVec3d)lIlllIllIIIlIII.lastRotationPos).set(lIlllIllIIIlIll.field_1352, lIlllIllIIIlIll.field_1351, lIlllIllIIIlIll.field_1350);
        } else {
            lIlllIllIIIlIII.lastYaw = lIlllIllIIIIlIl;
            lIlllIllIIIlIII.lastPitch = lIlllIllIIIlIIl;
        }
        lIlllIllIIIlIII.lastRotationTimer = 0;
    }

    private boolean shouldFacePlace(class_2338 lIlllIIllIlIIll) {
        CrystalAura lIlllIIllIlIlII;
        for (class_1657 lIlllIIllIlIlll : lIlllIIllIlIlII.targets) {
            class_2338 lIlllIIllIllIII = lIlllIIllIlIlll.method_24515();
            if (lIlllIIllIlIIll.method_10264() != lIlllIIllIllIII.method_10264() + 1 || Math.abs(lIlllIIllIllIII.method_10263() - lIlllIIllIlIIll.method_10263()) > 1 || Math.abs(lIlllIIllIllIII.method_10260() - lIlllIIllIlIIll.method_10260()) > 1) continue;
            if ((double)EntityUtils.getTotalHealth(lIlllIIllIlIlll) <= lIlllIIllIlIlII.facePlaceHealth.get()) {
                return true;
            }
            for (class_1799 lIlllIIllIllIIl : lIlllIIllIlIlll.method_5661()) {
                if (!(lIlllIIllIllIIl == null || lIlllIIllIllIIl.method_7960() ? lIlllIIllIlIlII.facePlaceArmor.get() != false : (double)(lIlllIIllIllIIl.method_7936() - lIlllIIllIllIIl.method_7919()) / (double)lIlllIIllIllIIl.method_7936() * 100.0 <= lIlllIIllIlIlII.facePlaceDurability.get())) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDeactivate() {
        CrystalAura lIlllIllIllIllI;
        lIlllIllIllIllI.targets.clear();
        lIlllIllIllIllI.placedCrystals.clear();
        lIlllIllIllIllI.attemptedBreaks.clear();
        lIlllIllIllIllI.waitingToExplode.clear();
        lIlllIllIllIllI.removed.clear();
        lIlllIllIllIllI.bestTarget = null;
    }

    @EventHandler(priority=-866)
    private void onPreTickLast(TickEvent.Pre lIlllIllIlIIIll) {
        CrystalAura lIlllIllIlIIlII;
        if (lIlllIllIlIIlII.rotate.get().booleanValue() && lIlllIllIlIIlII.lastRotationTimer < lIlllIllIlIIlII.getLastRotationStopDelay() && !lIlllIllIlIIlII.didRotateThisTick) {
            Rotations.rotate(lIlllIllIlIIlII.isLastRotationPos ? Rotations.getYaw(lIlllIllIlIIlII.lastRotationPos) : lIlllIllIlIIlII.lastYaw, lIlllIllIlIIlII.isLastRotationPos ? Rotations.getPitch(lIlllIllIlIIlII.lastRotationPos) : lIlllIllIlIIlII.lastPitch, -100, null);
        }
    }

    @EventHandler
    private void onRender2D(Render2DEvent lIlllIIIIlllllI) {
        CrystalAura lIlllIIIIllllll;
        if (!lIlllIIIIllllll.render.get().booleanValue() || lIlllIIIIllllll.renderTimer <= 0 || !lIlllIIIIllllll.renderDamageText.get().booleanValue()) {
            return;
        }
        lIlllIIIIllllll.vec3.set((double)lIlllIIIIllllll.renderPos.method_10263() + 0.5, (double)lIlllIIIIllllll.renderPos.method_10264() + 0.5, (double)lIlllIIIIllllll.renderPos.method_10260() + 0.5);
        if (NametagUtils.to2D(lIlllIIIIllllll.vec3, lIlllIIIIllllll.damageTextScale.get())) {
            NametagUtils.begin(lIlllIIIIllllll.vec3);
            TextRenderer.get().begin(1.0, false, true);
            String lIlllIIIlIIIIIl = String.format("%.1f", lIlllIIIIllllll.renderDamage);
            double lIlllIIIlIIIIII = TextRenderer.get().getWidth(lIlllIIIlIIIIIl) / 2.0;
            TextRenderer.get().render(lIlllIIIlIIIIIl, -lIlllIIIlIIIIII, 0.0, lIlllIIIIllllll.lineColor.get(), true);
            TextRenderer.get().end();
            NametagUtils.end();
        }
    }

    private class_1657 getNearestTarget() {
        CrystalAura lIlllIIlIllIIII;
        class_1657 lIlllIIlIlIllll = null;
        double lIlllIIlIlIlllI = Double.MAX_VALUE;
        for (class_1657 lIlllIIlIllIIIl : lIlllIIlIllIIII.targets) {
            double lIlllIIlIllIIlI = lIlllIIlIllIIIl.method_5858((class_1297)lIlllIIlIllIIII.mc.field_1724);
            if (!(lIlllIIlIllIIlI < lIlllIIlIlIlllI)) continue;
            lIlllIIlIlIllll = lIlllIIlIllIIIl;
            lIlllIIlIlIlllI = lIlllIIlIllIIlI;
        }
        return lIlllIIlIlIllll;
    }

    public static enum SupportMode {
        Disabled,
        Accurate,
        Fast;


        private SupportMode() {
            SupportMode lllllllllllllllllIIllIlIIIlIlIIl;
        }
    }

    public static enum AutoSwitchMode {
        Normal,
        Silent,
        None;


        private AutoSwitchMode() {
            AutoSwitchMode lllllIIllIllI;
        }
    }

    public static enum YawStepMode {
        Break,
        All;


        private YawStepMode() {
            YawStepMode lllIlllIIllllll;
        }
    }
}

