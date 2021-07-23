/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import com.google.common.collect.Streams;
import com.google.common.util.concurrent.AtomicDouble;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.events.entity.EntityRemovedEvent;
import minegame159.meteorclient.events.entity.player.SendMovementPacketsEvent;
import minegame159.meteorclient.events.render.Render2DEvent;
import minegame159.meteorclient.events.render.RenderEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IBox;
import minegame159.meteorclient.rendering.Renderer;
import minegame159.meteorclient.rendering.ShapeMode;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.combat.Surround;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.bedtrap.BlockUtils;
import minegame159.meteorclient.utils.entity.fakeplayer.FakePlayerEntity;
import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.misc.Vec3;
import minegame159.meteorclient.utils.player.DamageCalcUtils;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.player.PlayerUtils;
import minegame159.meteorclient.utils.player.Rotations;
import minegame159.meteorclient.utils.render.NametagUtils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;
import minegame159.meteorclient.utils.world.BlockIterator;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1812;
import net.minecraft.class_1829;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class CrystalAuraTwo
extends Module {
    private class_243 eyeHeight;
    private final Setting<Double> damageScale;
    private final List<Double> crystalList;
    private final Setting<Double> facePlaceDurability;
    private final Setting<Mode> placeMode;
    private final Setting<Boolean> pauseOnMine;
    private final SettingGroup sgPause;
    private List<class_1511> attemptedCrystals;
    private final Setting<Boolean> renderDamage;
    private final SettingGroup sgTarget;
    private double ticksBehind;
    private final SettingGroup sgPlace;
    private final Setting<Double> breakRange;
    private final Setting<Boolean> oldPlace;
    private int preSlot;
    private final Map<class_1511, List<Double>> crystalMap;
    private final Setting<Boolean> strictLook;
    private final Setting<Boolean> hurtIgnoreSelfDmg;
    private final Setting<SettingColor> sideColor;
    private final Setting<Integer> roundDamage;
    private final Setting<Boolean> lethal;
    private final Setting<Logic> orderLogic;
    private final SettingGroup sgMisc;
    private final Setting<Double> torque;
    private final Setting<Boolean> swing;
    private final Setting<Boolean> surroundHold;
    private final Pool<RenderBlock> renderBlockPool;
    private final IntSet entitiesToRemove;
    private final Setting<Boolean> supportAirPlace;
    private final Setting<Boolean> pauseOnDrink;
    private final Setting<Double> placeWallsRange;
    private final Setting<Boolean> facePlace;
    private final Setting<Double> maxPlaceDamage;
    private class_243 playerPos;
    private final class_243[] doubleLegOffsetList;
    private final SettingGroup sgExperimental;
    private final Setting<Boolean> support;
    public final Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final Setting<Integer> renderTimer;
    private final Setting<SwitchMode> switchMode;
    private final Setting<Boolean> onlyOwn;
    private final Setting<Canceller> removeCrystals;
    private final Setting<SettingColor> lineColor;
    private final Setting<Boolean> render;
    private final Setting<Boolean> supportBackup;
    private final Setting<Boolean> facePlaceSelf;
    private List<class_1511> crystals;
    private final SettingGroup sgBreak;
    private final SettingGroup sgSwitch;
    private final Setting<Double> facePlaceHealth;
    private final SettingGroup sgRender;
    public final Setting<Double> targetRange;
    private final Setting<Boolean> antiWeakness;
    private final SettingGroup sgRotations;
    private final Setting<Double> breakWallsRange;
    static final boolean $assertionsDisabled = !CrystalAuraTwo.class.desiredAssertionStatus();
    private int breakDelayLeft;
    private List<class_1657> friends;
    private static final Vec3 pos = new Vec3();
    private final Setting<Boolean> pauseOnEat;
    private final SettingGroup sgSupport;
    private final Setting<SettingColor> damageColor;
    private final Setting<Type> antiFriendPop;
    private final Setting<Integer> switchDelay;
    private int switchDelayLeft;
    private int supportDelayAfterLeft;
    private final class_238 box = new class_238(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    private final Setting<RotationMode> rotationMode;
    private final Setting<PauseMode> facePlacePause;
    private final Setting<Integer> placeDelay;
    private final Setting<Double> maxBreakDamage;
    private int supportSlot = 0;
    private final Setting<Double> minBreakDamage;
    private final Setting<ShapeMode> shapeMode;
    private int supportDelayLeft;
    private final Setting<Integer> supportDelayAfter;
    private final Setting<Boolean> resetRotations;
    public final Setting<Boolean> predict;
    private final Setting<Type> pauseMode;
    private final Setting<Boolean> fadeOut;
    private List<class_1511> placedCrystals;
    private final Setting<Boolean> switchBack;
    private final Int2IntMap attemptedBreaks;
    private final List<RenderBlock> renderBlocks;
    private final Setting<Integer> supportDelay;
    private final Setting<Double> minPlaceDamage;
    private final Setting<Boolean> randomColor;
    private List<class_1309> targets;
    private final Setting<TargetMode> targetMode;
    private final Setting<Type> rayTrace;
    private final Setting<Integer> breakDelay;
    private final Setting<Boolean> surround;
    private final Setting<Mode> breakMode;
    private final Setting<Boolean> crystalSave;
    private final Setting<Double> placeRange;
    private static final Color color = new Color(22, 30, 30);
    private class_2338 playerBlockPos;
    private final Setting<Integer> breakAttempts;
    private int placeDelayLeft;
    private final Setting<Integer> numberOfDamages;

    private void lambda$doPlace$2(AtomicReference atomicReference, AtomicReference atomicReference2, AtomicDouble atomicDouble, AtomicDouble atomicDouble2) {
        if (atomicReference.get() != null || atomicReference2.get() != null) {
            class_243 class_2432 = atomicReference.get() != null ? (class_243)atomicReference.get() : (class_243)atomicReference2.get();
            this.doHeldCrystal(class_2432);
            if (this.render.get().booleanValue()) {
                RenderBlock renderBlock2;
                double d = atomicReference.get() != null ? atomicDouble.get() : atomicDouble2.get();
                class_2338 class_23382 = new class_2338(class_2432);
                for (RenderBlock renderBlock2 : this.renderBlocks) {
                    if (!renderBlock2.positionEquals(class_23382)) continue;
                    renderBlock2.set(class_23382, d);
                    return;
                }
                renderBlock2 = this.renderBlockPool.get();
                renderBlock2.set(class_23382, d);
                this.renderBlocks.add(renderBlock2);
            }
        }
    }

    private void singleBreak(class_1511 class_15112) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        int n = this.mc.field_1724.field_7514.field_7545;
        this.getCrystalStream().max(Comparator.comparingDouble(this::lambda$singleBreak$9)).ifPresent(arg_0 -> this.lambda$singleBreak$10(class_15112, n, arg_0));
    }

    private void doPlace() {
        if (this.placeDelayLeft <= 0) {
            int n;
            boolean bl;
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            boolean bl2 = bl = this.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || this.mc.field_1724.method_6079().method_7909() == class_1802.field_8301;
            if ((this.switchMode.get() != SwitchMode.None || bl) && ((n = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8301}).slot) >= 0 && n <= 8 || bl)) {
                if (this.pauseMode.get() == Type.Place || this.pauseMode.get() == Type.Both) {
                    if (this.mc.field_1724.method_6115()) {
                        if (this.pauseOnEat.get().booleanValue() && (this.mc.field_1724.method_6047().method_7909().method_19263() || this.mc.field_1724.method_6079().method_7909().method_19263())) {
                            return;
                        }
                        if (this.pauseOnDrink.get().booleanValue() && (this.mc.field_1724.method_6047().method_7909() instanceof class_1812 || this.mc.field_1724.method_6079().method_7909() instanceof class_1812)) {
                            return;
                        }
                    }
                    if (this.pauseOnMine.get().booleanValue()) {
                        if (!$assertionsDisabled && this.mc.field_1761 == null) {
                            throw new AssertionError();
                        }
                        if (this.mc.field_1761.method_2923()) {
                            return;
                        }
                    }
                }
                boolean bl3 = false;
                if (this.support.get().booleanValue()) {
                    for (int i = 0; i < 9; ++i) {
                        if (this.mc.field_1724.field_7514.method_5438(i).method_7909() != class_1802.field_8281) continue;
                        bl3 = true;
                        this.supportSlot = i;
                        break;
                    }
                }
                AtomicReference atomicReference = new AtomicReference();
                AtomicReference atomicReference2 = new AtomicReference();
                AtomicDouble atomicDouble = new AtomicDouble();
                AtomicDouble atomicDouble2 = new AtomicDouble();
                AtomicDouble atomicDouble3 = new AtomicDouble();
                AtomicDouble atomicDouble4 = new AtomicDouble();
                this.crystalMap.clear();
                this.crystalList.clear();
                boolean bl4 = bl3;
                BlockIterator.register(this.placeRange.get().intValue(), this.placeRange.get().intValue(), (arg_0, arg_1) -> this.lambda$doPlace$1(atomicReference2, bl4, atomicDouble3, atomicReference, atomicDouble4, atomicDouble2, atomicDouble, arg_0, arg_1));
                BlockIterator.after(() -> this.lambda$doPlace$2(atomicReference2, atomicReference, atomicDouble2, atomicDouble4));
            }
        }
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent entityAddedEvent) {
        if (entityAddedEvent.entity instanceof class_1511) {
            for (int i = 0; i < this.attemptedCrystals.size(); ++i) {
                if (!(this.attemptedCrystals.get(i).method_19538().method_1022(entityAddedEvent.entity.method_19538()) < 1.0)) continue;
                this.attemptedCrystals.remove(i);
                this.placedCrystals.add((class_1511)entityAddedEvent.entity);
                break;
            }
        }
    }

    private boolean shouldFacePlace(class_1297 class_12972) {
        class_1799 class_17992;
        if (!this.facePlace.get().booleanValue()) {
            return false;
        }
        if (!(class_12972 instanceof class_1657)) {
            return false;
        }
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (this.mc.field_1724.method_5739(class_12972) < 1.0f) {
            return this.facePlaceSelf.get();
        }
        if ((double)(((class_1657)class_12972).method_6032() + ((class_1657)class_12972).method_6067()) <= this.facePlaceHealth.get()) {
            return true;
        }
        Iterable iterable = class_12972.method_5661();
        Iterator iterator = iterable.iterator();
        do {
            if (iterator.hasNext()) continue;
            return false;
        } while ((class_17992 = (class_1799)iterator.next()) == null || class_17992.method_7960() || !((double)(class_17992.method_7936() - class_17992.method_7919()) / (double)class_17992.method_7936() * 100.0 <= this.facePlaceDurability.get()));
        return true;
    }

    private void attackCrystal(class_1511 class_15112, int n) {
        if (this.antiWeakness.get().booleanValue() && this.mc.field_1724 != null && Objects.requireNonNull(this.mc.field_1724).method_6088().containsKey(class_1294.field_5911)) {
            if (!$assertionsDisabled && this.mc.field_1761 == null) {
                throw new AssertionError();
            }
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, (class_1297)class_15112);
        } else {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2824((class_1297)class_15112, false));
        }
        if (this.swing.get().booleanValue()) {
            this.mc.field_1724.method_6104(this.getHand());
        } else {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(this.getHand()));
        }
        if (this.removeCrystals.get() == Canceller.NoDesync) {
            this.entitiesToRemove.add(class_15112.method_5628());
        }
        if (this.removeCrystals.get() == Canceller.HitCanceller) {
            this.entitiesToRemove.add(class_15112.method_5628());
            this.placedCrystals.remove(class_15112);
            this.crystals.remove(class_15112);
            class_15112.method_5650();
        }
        this.attemptedBreaks.put(class_15112.method_5628(), this.attemptedBreaks.get(class_15112.method_5628()) + 1);
        if (this.switchDelayLeft <= 0) {
            this.mc.field_1724.field_7514.field_7545 = n;
            this.switchDelayLeft = this.switchDelay.get();
        }
    }

    static Vec3 access$800() {
        return pos;
    }

    static Setting access$900(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.damageScale;
    }

    @Override
    public void onActivate() {
        this.preSlot = -1;
        this.placeDelayLeft = 0;
        this.breakDelayLeft = 0;
        this.supportDelayLeft = 0;
        this.supportDelayAfterLeft = 0;
        this.switchDelayLeft = 0;
        for (RenderBlock renderBlock : this.renderBlocks) {
            this.renderBlockPool.free(renderBlock);
        }
        this.renderBlocks.clear();
        this.targets.clear();
        this.friends.clear();
        this.crystals.clear();
        this.attemptedBreaks.clear();
    }

    private boolean isSafePlace(class_1309 class_13092, class_243 class_2432) {
        if (this.placeMode.get() == Mode.Suicide) {
            return true;
        }
        if (this.hurtIgnoreSelfDmg.get().booleanValue() && class_13092.field_6235 - (Math.max(this.breakDelayLeft, 0) + (int)this.ticksBehind) > 0) {
            return true;
        }
        double d = DamageCalcUtils.crystalDamage(class_13092, class_2432);
        return (double)(class_13092.method_6032() + class_13092.method_6067()) > d && d <= this.maxPlaceDamage.get();
    }

    public class_1657 getPlayerTarget() {
        if (this.targets instanceof class_1657) {
            return (class_1657)this.targets;
        }
        return null;
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Pre pre) {
        --this.placeDelayLeft;
        --this.breakDelayLeft;
        --this.supportDelayLeft;
        --this.supportDelayAfterLeft;
        --this.switchDelayLeft;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.ticksBehind = (double)Objects.requireNonNull(Objects.requireNonNull(this.mc.method_1562()).method_2871(this.mc.field_1724.method_5667())).method_2959() / 50.0;
        Iterator<RenderBlock> iterator = this.renderBlocks.iterator();
        while (iterator.hasNext()) {
            RenderBlock renderBlock = iterator.next();
            if (!renderBlock.shouldRemove()) continue;
            iterator.remove();
            this.renderBlockPool.free(renderBlock);
        }
        this.getEntities();
        if (!this.targets.isEmpty()) {
            this.playerPos = this.mc.field_1724.method_19538();
            this.eyeHeight = new class_243(0.0, (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()), 0.0);
            this.playerBlockPos = this.mc.field_1724.method_24515();
            switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$bedtrap$CrystalAuraTwo$Logic[this.orderLogic.get().ordinal()]) {
                case 1: {
                    this.doBreak();
                    this.doPlace();
                    break;
                }
                case 2: {
                    if (!$assertionsDisabled && this.mc.field_1687 == null) {
                        throw new AssertionError();
                    }
                    this.doPlace();
                    this.doBreak();
                }
            }
        }
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Post post) {
        this.targets.clear();
        this.friends.clear();
        this.crystals.clear();
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent entityRemovedEvent) {
        if (entityRemovedEvent.entity instanceof class_1511) {
            this.placedCrystals.remove(entityRemovedEvent.entity);
            this.attemptedBreaks.remove(entityRemovedEvent.entity.method_5628());
        }
    }

    private void lambda$doPlace$1(AtomicReference atomicReference, boolean bl, AtomicDouble atomicDouble, AtomicReference atomicReference2, AtomicDouble atomicDouble2, AtomicDouble atomicDouble3, AtomicDouble atomicDouble4, class_2338 class_23382, class_2680 class_26802) {
        class_243 class_2432 = new class_243((double)class_23382.method_10263(), (double)class_23382.method_10264(), (double)class_23382.method_10260());
        if (class_26802.method_26215()) {
            if (this.supportBackup.get().booleanValue() && atomicReference.get() != null) {
                return;
            }
            if (this.mc.field_1724.field_7514.field_7545 != this.supportSlot && this.switchDelayLeft > 0) {
                return;
            }
        }
        if (class_2432.method_24802((class_2374)this.playerPos.method_1019(this.eyeHeight), this.placeRange.get().doubleValue())) {
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            if (this.antiFriendPop.get() == Type.Place || this.antiFriendPop.get() == Type.Both) {
                for (class_1657 class_16572 : this.friends) {
                    if (!class_2432.method_24802((class_2374)class_16572.method_19538(), 9.0)) continue;
                    if (!this.isSafePlace((class_1309)class_16572, class_2432.method_1031(0.5, 1.0, 0.5))) {
                        return;
                    }
                    d2 = DamageCalcUtils.crystalDamage((class_1309)class_16572, class_2432.method_1031(0.5, 1.0, 0.5));
                }
            }
            boolean bl2 = false;
            for (class_1309 class_13092 : this.targets) {
                double d4;
                boolean bl3;
                class_243 class_2433 = class_13092.method_19538();
                if (this.predict.get().booleanValue() && !(class_13092 instanceof FakePlayerEntity)) {
                    class_243 class_2434 = class_13092.method_18798();
                    class_2433 = class_2433.method_1031(class_2434.field_1352 * this.ticksBehind, class_2434.field_1351 * this.ticksBehind, class_2434.field_1350 * this.ticksBehind);
                }
                if (BlockUtils.isSurrounded(class_13092) && !class_2432.method_24802((class_2374)class_2433, 3.0)) continue;
                boolean bl4 = bl3 = this.shouldFacePlace((class_1297)class_13092) && !Arrays.asList(this.doubleLegOffsetList).contains(class_2432) && !bl2;
                if (bl3) {
                    bl2 = true;
                }
                if ((this.pauseMode.get() == Type.Place || this.pauseMode.get() == Type.Both) && bl3 && (this.mc.field_1724.method_6115() && ((this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndDrink || this.facePlacePause.get() == PauseMode.EatAndMine || this.facePlacePause.get() == PauseMode.Eat) && (this.mc.field_1724.method_6047().method_7909().method_19263() || this.mc.field_1724.method_6079().method_7909().method_19263()) || (this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndDrink || this.facePlacePause.get() == PauseMode.DrinkAndMine || this.facePlacePause.get() == PauseMode.Drink) && (this.mc.field_1724.method_6047().method_7909() instanceof class_1812 || this.mc.field_1724.method_6079().method_7909() instanceof class_1812)) || Objects.requireNonNull(this.mc.field_1761).method_2923() && (this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndMine || this.facePlacePause.get() == PauseMode.DrinkAndMine || this.facePlacePause.get() == PauseMode.Mine)) || this.crystalSave.get().booleanValue() && class_13092.field_6235 - (Math.max(this.breakDelayLeft, 0) + (int)this.ticksBehind) > 0 || class_23382.method_10264() > class_13092.method_24515().method_10264() || class_2432.method_1022(class_2433) > 9.0 || !this.isValid(class_23382, bl)) continue;
                double d5 = bl3 ? 2.5 : this.minPlaceDamage.get();
                boolean bl5 = false;
                for (class_1511 class_15112 : this.onlyOwn.get() != false ? this.placedCrystals : this.crystals) {
                    if (class_15112.method_5739((class_1297)class_13092) > 9.0f || !((d4 = DamageCalcUtils.crystalDamage(class_13092, class_15112.method_19538())) >= d5)) continue;
                    boolean bl6 = false;
                    if (this.lethal.get().booleanValue() && this.targetMode.get() == TargetMode.SingleDamage && d4 > (double)(class_13092.method_6032() + class_13092.method_6067()) + 0.5) {
                        bl6 = true;
                    }
                    if (!this.shouldBreak(class_15112) || this.isToRemove(class_15112) || !this.isSafeBreak((class_1309)this.mc.field_1724, class_15112.method_19538()) && !bl6 && (!this.hurtIgnoreSelfDmg.get().booleanValue() || this.mc.field_1724.field_6235 - (int)this.ticksBehind - Math.max(0, this.breakDelayLeft) <= 0) || !class_2432.method_1031(0.0, 1.0, 0.0).method_24802((class_2374)this.playerPos.method_1019(this.eyeHeight), this.breakRange.get().doubleValue())) continue;
                    bl5 = true;
                    break;
                }
                if (bl5) continue;
                if (!this.supportAirPlace.get().booleanValue()) {
                    if (!$assertionsDisabled && this.mc.field_1687 == null) {
                        throw new AssertionError();
                    }
                    if (this.mc.field_1687.method_22347(class_23382)) {
                        boolean bl7 = false;
                        for (class_2350 class_23502 : class_2350.values()) {
                            class_2338 class_23383 = class_23382.method_10093(class_23502);
                            if (this.mc.field_1687.method_8320(class_23383).method_26215() || minegame159.meteorclient.utils.world.BlockUtils.isClickable(this.mc.field_1687.method_8320(class_23383).method_26204())) continue;
                            bl7 = true;
                            break;
                        }
                        if (!bl7) continue;
                    }
                }
                if (BlockUtils.rayTraceCheck(class_23382, false) == null && (this.rayTrace.get() == Type.Place || this.rayTrace.get() == Type.Both || this.playerPos.method_1019(this.eyeHeight).method_1022(class_2432) > this.placeWallsRange.get()) || !this.isSafePlace((class_1309)this.mc.field_1724, class_2432.method_1031(0.5, 1.0, 0.5))) continue;
                d3 = DamageCalcUtils.crystalDamage((class_1309)this.mc.field_1724, class_2432.method_1031(0.5, 1.0, 0.5)) + d2;
                double d6 = DamageCalcUtils.crystalDamage(class_13092, class_2432.method_1031(0.5, 1.0, 0.5));
                if (d6 < d5) continue;
                if (this.targetMode.get() == TargetMode.TotalDamage) {
                    d += d6;
                    continue;
                }
                d4 = d6 / Math.pow(d3, 1.0 - this.torque.get());
                if (!$assertionsDisabled && this.mc.field_1687 == null) {
                    throw new AssertionError();
                }
                if (this.mc.field_1687.method_22347(class_23382) && this.supportBackup.get().booleanValue()) {
                    if (atomicReference.get() != null || !(d4 > atomicDouble.get())) continue;
                    atomicReference2.set(class_2432);
                    atomicDouble.set(d4);
                    atomicDouble2.set(d6);
                    continue;
                }
                if (d3 < 1.0 && d6 >= 36.0) {
                    atomicReference.set(class_2432);
                    atomicDouble3.set(d6);
                    BlockIterator.disableCurrent();
                }
                if (!(d4 > atomicDouble4.get())) continue;
                atomicReference.set(class_2432);
                atomicDouble4.set(d4);
                atomicDouble3.set(d6);
            }
            if (this.targetMode.get() == TargetMode.TotalDamage) {
                double d7 = d / Math.pow(d3, 1.0 - this.torque.get());
                if (!$assertionsDisabled && this.mc.field_1687 == null) {
                    throw new AssertionError();
                }
                if (this.mc.field_1687.method_22347(class_23382) && this.supportBackup.get().booleanValue()) {
                    if (atomicReference.get() == null && d7 > atomicDouble.get()) {
                        atomicReference2.set(class_2432);
                        atomicDouble.set(d7);
                        atomicDouble2.set(d);
                    }
                } else if (d7 > atomicDouble4.get()) {
                    atomicReference.set(class_2432);
                    atomicDouble4.set(d7);
                    atomicDouble3.set(d);
                }
            }
        }
    }

    private boolean lambda$getCrystalStream$8(class_1297 class_12972) {
        return this.shouldBreak((class_1511)class_12972);
    }

    private double lambda$getInfoString$5(class_1309 class_13092) {
        return class_13092.method_5739((class_1297)this.mc.field_1724);
    }

    private void lambda$doHeldCrystal$4(class_1268 class_12682, class_2350 class_23502, class_2338 class_23382) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_12682, new class_3965(this.playerPos, class_23502, class_23382, false)));
    }

    @EventHandler
    private void onRender2D(Render2DEvent render2DEvent) {
        for (RenderBlock renderBlock : this.renderBlocks) {
            renderBlock.render2D();
        }
    }

    static Setting access$700(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.renderDamage;
    }

    static Setting access$100(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.randomColor;
    }

    private boolean isEmpty(class_2338 class_23382) {
        boolean bl;
        double d = class_23382.method_10263();
        double d2 = class_23382.method_10264();
        double d3 = class_23382.method_10260();
        ((IBox)this.box).set(d, d2 - 1.0, d3, d + 1.0, d2 + 2.0, d3 + 1.0);
        if (!this.entitiesToRemove.isEmpty()) {
            IntIterator intIterator = this.entitiesToRemove.iterator();
            while (intIterator.hasNext()) {
                int n = intIterator.next();
                if (!$assertionsDisabled && this.mc.field_1687 == null) {
                    throw new AssertionError();
                }
                if (this.mc.field_1687.method_8335(this.mc.field_1687.method_8469(n), this.box).isEmpty()) continue;
                return false;
            }
            bl = true;
        } else {
            if (!$assertionsDisabled && this.mc.field_1687 == null) {
                throw new AssertionError();
            }
            bl = this.mc.field_1687.method_8335(null, this.box).isEmpty();
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        return this.mc.field_1687.method_8320(class_23382).method_26215() && bl && (this.oldPlace.get() == false || this.mc.field_1687.method_8320(class_23382.method_10069(0, 1, 0)).method_26215());
    }

    private void lambda$doBreak$3(class_1511 class_15112, int n) {
        this.attackCrystal(class_15112, n);
    }

    private void doBreak() {
        if (this.breakDelayLeft <= 0) {
            if (this.pauseMode.get() == Type.Break || this.pauseMode.get() == Type.Both) {
                if (!$assertionsDisabled && this.mc.field_1724 == null) {
                    throw new AssertionError();
                }
                if (this.mc.field_1724.method_6115()) {
                    if (this.pauseOnEat.get().booleanValue() && (this.mc.field_1724.method_6047().method_7909().method_19263() || this.mc.field_1724.method_6079().method_7909().method_19263())) {
                        return;
                    }
                    if (this.pauseOnDrink.get().booleanValue() && (this.mc.field_1724.method_6047().method_7909() instanceof class_1812 || this.mc.field_1724.method_6079().method_7909() instanceof class_1812)) {
                        return;
                    }
                }
                if (this.pauseOnMine.get().booleanValue()) {
                    if (!$assertionsDisabled && this.mc.field_1761 == null) {
                        throw new AssertionError();
                    }
                    if (this.mc.field_1761.method_2923()) {
                        return;
                    }
                }
            }
            double d = 0.0;
            class_1511 class_15112 = null;
            Iterator<class_1511> iterator = (this.onlyOwn.get() != false ? this.placedCrystals : this.crystals).iterator();
            block0: while (true) {
                class_1657 class_16572;
                class_1309 class_13092;
                if (!iterator.hasNext()) {
                    if (class_15112 == null) {
                        return;
                    }
                    if (!$assertionsDisabled && this.mc.field_1724 == null) {
                        throw new AssertionError();
                    }
                    int n = this.mc.field_1724.field_7514.field_7545;
                    if (this.mc.field_1724.method_6088().containsKey(class_1294.field_5911) && this.antiWeakness.get().booleanValue() && this.switchDelayLeft <= 0) {
                        for (int i = 0; i < 9; ++i) {
                            if (!(this.mc.field_1724.field_7514.method_5438(i).method_7909() instanceof class_1829) && !(this.mc.field_1724.field_7514.method_5438(i).method_7909() instanceof class_1743)) continue;
                            this.mc.field_1724.field_7514.field_7545 = i;
                            this.switchDelayLeft = this.switchDelay.get();
                            break;
                        }
                    }
                    if (this.rotationMode.get() != RotationMode.Break && this.rotationMode.get() != RotationMode.Both) {
                        this.attackCrystal(class_15112, n);
                    } else {
                        float[] fArray = PlayerUtils.calculateAngle(class_15112.method_19538());
                        class_13092 = class_15112;
                        Rotations.rotate(fArray[0], fArray[1], 30, () -> this.lambda$doBreak$3((class_1511)class_13092, n));
                    }
                    this.breakDelayLeft = this.breakDelay.get();
                    return;
                }
                class_1511 class_15113 = iterator.next();
                if (!this.shouldBreak(class_15113) || this.isToRemove(class_15113)) continue;
                double d2 = 0.0;
                boolean bl = false;
                Iterator<class_1309> iterator2 = this.targets.iterator();
                while (true) {
                    double d3;
                    double d4;
                    if (!iterator2.hasNext()) {
                        if (this.targetMode.get() != TargetMode.TotalDamage || !(d2 > d)) break;
                        d = d2;
                        class_15112 = class_15113;
                        break;
                    }
                    class_13092 = iterator2.next();
                    if (class_13092.field_6235 - (int)this.ticksBehind > 0 && this.crystalSave.get().booleanValue() || class_15113.method_24515().method_10264() - 1 > class_13092.method_24515().method_10264() || class_15113.method_5739((class_1297)class_13092) > 9.0f) continue;
                    boolean bl2 = this.shouldFacePlace((class_1297)class_13092);
                    double d5 = d4 = bl2 ? 2.5 : this.minBreakDamage.get();
                    if ((this.pauseMode.get() == Type.Break || this.pauseMode.get() == Type.Both) && bl2 && (Objects.requireNonNull(this.mc.field_1724).method_6115() && ((this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndDrink || this.facePlacePause.get() == PauseMode.EatAndMine || this.facePlacePause.get() == PauseMode.Eat) && (this.mc.field_1724.method_6047().method_7909().method_19263() || this.mc.field_1724.method_6079().method_7909().method_19263()) || (this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndDrink || this.facePlacePause.get() == PauseMode.DrinkAndMine || this.facePlacePause.get() == PauseMode.Drink) && (this.mc.field_1724.method_6047().method_7909() instanceof class_1812 || this.mc.field_1724.method_6079().method_7909() instanceof class_1812)) || Objects.requireNonNull(this.mc.field_1761).method_2923() && (this.facePlacePause.get() == PauseMode.All || this.facePlacePause.get() == PauseMode.EatAndMine || this.facePlacePause.get() == PauseMode.DrinkAndMine || this.facePlacePause.get() == PauseMode.Mine)) || (d3 = DamageCalcUtils.crystalDamage(class_13092, class_15113.method_19538())) < d4) continue;
                    if (this.targetMode.get() == TargetMode.TotalDamage) {
                        d2 += d3;
                        continue;
                    }
                    if (class_13092.field_6235 - (int)this.ticksBehind > 0 && BlockUtils.isFucked(class_13092) && this.surroundHold.get().booleanValue()) continue;
                    if (d3 > d) {
                        d = d3;
                        class_15112 = class_15113;
                    }
                    if (!(d3 > (double)(class_13092.method_6032() + class_13092.method_6067()) + 0.5) || !this.lethal.get().booleanValue()) continue;
                    bl = true;
                }
                if (!this.isSafeBreak((class_1309)this.mc.field_1724, class_15113.method_19538()) && !bl && (!this.hurtIgnoreSelfDmg.get().booleanValue() || Objects.requireNonNull(this.mc.field_1724).field_6235 - (int)this.ticksBehind <= 0) || this.antiFriendPop.get() != Type.Both && this.antiFriendPop.get() != Type.Break) continue;
                iterator2 = this.friends.iterator();
                do {
                    if (!iterator2.hasNext()) continue block0;
                    class_16572 = (class_1657)iterator2.next();
                } while (!class_15113.method_19538().method_24802((class_2374)class_16572.method_19538(), 9.0) || this.isSafeBreak((class_1309)class_16572, class_15113.method_19538()) || bl);
            }
        }
    }

    @EventHandler(priority=100)
    private void onTick(SendMovementPacketsEvent.Pre pre) {
        if (this.surround.get().booleanValue()) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (BlockUtils.obbySurrounded((class_1309)this.mc.field_1724) && !Modules.get().isActive(Surround.class) && this.mc.field_1724.method_24828()) {
                Modules.get().get(Surround.class).toggle();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean validSurroundBreak(class_1309 class_13092, int n, int n2, boolean bl) {
        class_243 class_2432 = class_13092.method_19538();
        class_2338 class_23382 = class_13092.method_24515();
        class_243 class_2433 = new class_243((double)class_23382.method_10263(), (double)class_23382.method_10264(), (double)class_23382.method_10260());
        if (!this.isValid(class_23382.method_10069(n, -1, n2), bl)) {
            return false;
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (this.mc.field_1687.method_8320(class_23382.method_10069(n / 2, 0, n2 / 2)).method_26204() == class_2246.field_9987) return false;
        if (!this.isSafePlace((class_1309)this.mc.field_1724, class_2433.method_1031((double)n, 0.0, (double)n2))) return false;
        class_2382 class_23822 = new class_2382(class_23382.method_10263() + n, class_23382.method_10264() - 1, class_23382.method_10260() + n2);
        if (!(Math.sqrt(this.playerBlockPos.method_10262(class_23822)) < this.placeRange.get())) return false;
        if (this.mc.field_1687.method_17742(new class_3959(class_2432, class_2432.method_1031((double)n, 0.0, (double)n2), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)class_13092)).method_17783() == class_239.class_240.field_1333) return false;
        return true;
    }

    private void lambda$singleBreak$10(class_1511 class_15112, int n, class_1297 class_12972) {
        this.attackCrystal(class_15112, n);
    }

    static Setting access$1000(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.roundDamage;
    }

    static Setting access$300(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.sideColor;
    }

    private static boolean lambda$getCrystalStream$6(class_1297 class_12972) {
        return class_12972 instanceof class_1511;
    }

    static Setting access$000(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.renderTimer;
    }

    private void getEntities() {
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        Iterator iterator = this.mc.field_1687.method_18112().iterator();
        while (iterator.hasNext()) {
            class_1297 class_12972 = (class_1297)iterator.next();
            if (!class_12972.method_5805() || !class_12972.method_24516((class_1297)this.mc.field_1724, this.targetRange.get().doubleValue()) || class_12972 == this.mc.field_1724) continue;
            if (class_12972 instanceof class_1657 && !Friends.get().shouldAttack((class_1657)class_12972)) {
                this.friends.add((class_1657)class_12972);
                continue;
            }
            if (this.entities.get().getBoolean((Object)class_12972.method_5864())) {
                if (this.targets.size() >= this.numberOfDamages.get()) continue;
                this.targets.add((class_1309)class_12972);
                continue;
            }
            if (!(class_12972 instanceof class_1511)) continue;
            this.crystals.add((class_1511)class_12972);
        }
        return;
    }

    private double lambda$singleBreak$9(class_1297 class_12972) {
        return DamageCalcUtils.crystalDamage((class_1309)this.targets, class_12972.method_19538());
    }

    private void doSwitch() {
        int n;
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (this.mc.field_1724.method_6047().method_7909() != class_1802.field_8301 && this.mc.field_1724.method_6079().method_7909() != class_1802.field_8301 && (n = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8301}).slot) != -1 && n < 9) {
            this.preSlot = this.mc.field_1724.field_7514.field_7545;
            this.mc.field_1724.field_7514.field_7545 = n;
            this.switchDelayLeft = this.switchDelay.get();
        }
    }

    @Override
    public void onDeactivate() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (this.switchBack.get().booleanValue() && this.preSlot != -1 && this.switchDelayLeft <= 0) {
            this.mc.field_1724.field_7514.field_7545 = this.preSlot;
            this.switchDelayLeft = this.switchDelay.get();
        }
        if (!this.targets.isEmpty() && this.resetRotations.get().booleanValue() && (this.rotationMode.get() == RotationMode.Both || this.rotationMode.get() == RotationMode.Place || this.rotationMode.get() == RotationMode.Break)) {
            Rotations.rotate(this.mc.field_1724.field_6031, this.mc.field_1724.field_5965);
        }
    }

    static Setting access$500(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.fadeOut;
    }

    public class_1268 getHand() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        class_1268 class_12682 = class_1268.field_5808;
        if (this.mc.field_1724.method_6047().method_7909() != class_1802.field_8301 && this.mc.field_1724.method_6079().method_7909() == class_1802.field_8301) {
            class_12682 = class_1268.field_5810;
        }
        return class_12682;
    }

    static Setting access$400(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.damageColor;
    }

    @Override
    public String getInfoString() {
        if (!this.targets.isEmpty()) {
            Optional<class_1309> optional = Streams.stream(this.targets).min(Comparator.comparingDouble(this::lambda$getInfoString$5));
            class_1309 class_13092 = optional.get();
            return class_13092 instanceof class_1657 ? class_13092.method_5820() : class_13092.method_5864().method_5897().getString();
        }
        return null;
    }

    private boolean isToRemove(class_1511 class_15112) {
        int n;
        IntIterator intIterator = this.entitiesToRemove.iterator();
        do {
            if (!intIterator.hasNext()) {
                return false;
            }
            n = intIterator.next();
        } while (class_15112 != Objects.requireNonNull(this.mc.field_1687).method_8469(n));
        return true;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        for (RenderBlock renderBlock : this.renderBlocks) {
            renderBlock.render3D();
        }
    }

    public CrystalAuraTwo() {
        super(Categories.BedTrap, "crystal-aura+", "Automatically places and breaks crystals to damage other players.");
        this.sgPlace = this.settings.createGroup("Place");
        this.sgBreak = this.settings.createGroup("Break");
        this.sgMisc = this.settings.createGroup("Misc");
        this.sgTarget = this.settings.createGroup("Target");
        this.sgSupport = this.settings.createGroup("Support");
        this.sgPause = this.settings.createGroup("Pause");
        this.sgSwitch = this.settings.createGroup("Switch");
        this.sgRotations = this.settings.createGroup("Rotations");
        this.sgExperimental = this.settings.createGroup("Experimental");
        this.sgRender = this.settings.createGroup("Render");
        this.placeMode = this.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The placement mode for crystals.").defaultValue(Mode.Safe).build());
        this.placeDelay = this.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The amount of delay in ticks before placing.").defaultValue(1).min(0).sliderMax(10).build());
        this.placeRange = this.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The radius in which crystals can be placed in.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        this.placeWallsRange = this.sgPlace.add(new DoubleSetting.Builder().name("walls-range").description("The radius in which crystals can be placed through walls.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        this.minPlaceDamage = this.sgPlace.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage the crystal will place.").defaultValue(6.0).build());
        this.maxPlaceDamage = this.sgPlace.add(new DoubleSetting.Builder().name("max-damage").description("The maximum self damage the crystal will place.").defaultValue(2.0).build());
        this.facePlace = this.sgPlace.add(new BoolSetting.Builder().name("face-place").description("Will face-place when target is below a certain health or armor durability threshold.").defaultValue(true).build());
        this.facePlaceHealth = this.sgPlace.add(new DoubleSetting.Builder().name("face-place-health").description("The health required to face-place.").defaultValue(8.0).min(1.0).sliderMin(1.0).sliderMax(36.0).build());
        this.facePlaceDurability = this.sgPlace.add(new DoubleSetting.Builder().name("face-place-durability").description("The durability threshold to be able to face-place.").defaultValue(2.0).min(1.0).sliderMin(1.0).sliderMax(100.0).max(100.0).build());
        this.facePlaceSelf = this.sgPlace.add(new BoolSetting.Builder().name("face-place-self").description("Whether to faceplace when you are in the same hole as your target.").defaultValue(true).build());
        this.torque = this.sgPlace.add(new DoubleSetting.Builder().name("torque").description("Defines how lethal the placements are; With 0 being ultra careful and 1 completely ignoring self damage.").defaultValue(0.0).min(0.0).sliderMax(1.0).max(1.0).build());
        this.surroundHold = this.sgPlace.add(new BoolSetting.Builder().name("surround-hold").description("Places a crystal next to a player so they cannot use Surround.").defaultValue(false).build());
        this.oldPlace = this.sgPlace.add(new BoolSetting.Builder().name("1.12-place").description("Won't place in 1x1x1 holes.").defaultValue(false).build());
        this.breakMode = this.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The type of break mode for crystals.").defaultValue(Mode.Safe).build());
        this.breakDelay = this.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The amount of delay in ticks before breaking.").defaultValue(1).min(0).sliderMax(10).build());
        this.minBreakDamage = this.sgBreak.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage for a crystal to get broken.").defaultValue(4.5).min(0.0).sliderMax(36.0).build());
        this.maxBreakDamage = this.sgBreak.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum self-damage allowed.").defaultValue(3.0).sliderMax(36.0).build());
        this.breakRange = this.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("The maximum range that crystals can be to be broken.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        this.breakWallsRange = this.sgBreak.add(new DoubleSetting.Builder().name("walls-range").description("The maximum range that crystals can be to be broken through walls.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        this.onlyOwn = this.sgBreak.add(new BoolSetting.Builder().name("only-own").description("Only destroys crystals placed by yourself.").defaultValue(false).build());
        this.lethal = this.sgBreak.add(new BoolSetting.Builder().name("lethal-break").description("Ignores safety settings if it can pop the target.").defaultValue(false).build());
        this.breakAttempts = this.sgBreak.add(new IntSetting.Builder().name("break-attempts").description("How many times to hit a crystal before stopping to target it.").defaultValue(2).sliderMin(1).sliderMax(5).build());
        this.removeCrystals = this.sgBreak.add(new EnumSetting.Builder().name("canceller").description("Hitcanceller is the fastest but might cause desync on strict anticheats.").defaultValue(Canceller.NoDesync).build());
        this.rayTrace = this.sgMisc.add(new EnumSetting.Builder().name("ray-trace").description("Wont place / break through walls when on.").defaultValue(Type.None).build());
        this.antiFriendPop = this.sgMisc.add(new EnumSetting.Builder().name("anti-friend-pop").description("Avoids popping your friends.").defaultValue(Type.Both).build());
        this.crystalSave = this.sgMisc.add(new BoolSetting.Builder().name("crystal-saver").description("Only targets players that can get hurt.").defaultValue(false).build());
        this.orderLogic = this.sgMisc.add(new EnumSetting.Builder().name("Logic").description("What to do first.").defaultValue(Logic.BreakPlace).build());
        this.antiWeakness = this.sgMisc.add(new BoolSetting.Builder().name("anti-weakness").description("Switches to tools to break crystals instead of your fist.").defaultValue(true).build());
        this.surround = this.sgMisc.add(new BoolSetting.Builder().name("auto-surround").description("Automatically turns on surround when in an obsidian hole.").defaultValue(false).build());
        this.entities = this.sgTarget.add(new EntityTypeListSetting.Builder().name("entities").description("The entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(class_1299.field_6097)).onlyAttackable().build());
        this.targetRange = this.sgTarget.add(new DoubleSetting.Builder().name("target-range").description("The maximum range the entity can be to be targeted.").defaultValue(10.0).min(0.0).sliderMax(15.0).build());
        this.predict = this.sgTarget.add(new BoolSetting.Builder().name("predict").description("Predicts target movement.").defaultValue(false).build());
        this.numberOfDamages = this.sgTarget.add(new IntSetting.Builder().name("number-of-targets").description("Maximum number of targets to perform calculations on. Might lag your game when set too high.").defaultValue(3).sliderMin(1).sliderMax(5).build());
        this.targetMode = this.sgTarget.add(new EnumSetting.Builder().name("target-mode").description("The way you target multiple targets.").defaultValue(TargetMode.SingleDamage).build());
        this.support = this.sgSupport.add(new BoolSetting.Builder().name("support").description("Places a block in the air and crystals on it. Helps with killing players that are flying.").defaultValue(false).build());
        this.supportDelay = this.sgSupport.add(new IntSetting.Builder().name("support-delay").description("The delay between support blocks being placed.").defaultValue(5).min(0).sliderMax(10).build());
        this.supportDelayAfter = this.sgSupport.add(new IntSetting.Builder().name("delay-after").description("The delay between having placed the support block and the next crystal.").defaultValue(0).min(0).sliderMax(5).build());
        this.supportBackup = this.sgSupport.add(new BoolSetting.Builder().name("support-backup").description("Makes it so support only works if there are no other options.").defaultValue(true).build());
        this.supportAirPlace = this.sgSupport.add(new BoolSetting.Builder().name("airplace").description("Whether to airplace the support block or not.").defaultValue(true).build());
        this.pauseMode = this.sgPause.add(new EnumSetting.Builder().name("pause-mode").description("What to pause.").defaultValue(Type.Place).build());
        this.pauseOnEat = this.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses Crystal Aura while eating.").defaultValue(false).build());
        this.pauseOnDrink = this.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses Crystal Aura while drinking a potion.").defaultValue(false).build());
        this.pauseOnMine = this.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses Crystal Aura while mining blocks.").defaultValue(false).build());
        this.facePlacePause = this.sgPause.add(new EnumSetting.Builder().name("pause-face-placing").description("When to interrupt face-placing.").defaultValue(PauseMode.None).build());
        this.switchMode = this.sgSwitch.add(new EnumSetting.Builder().name("switch-mode").description("How to switch items.").defaultValue(SwitchMode.Auto).build());
        this.switchBack = this.sgSwitch.add(new BoolSetting.Builder().name("switch-back").description("Switches back to your previous slot when disabling Crystal Aura.").defaultValue(false).build());
        this.switchDelay = this.sgSwitch.add(new IntSetting.Builder().name("switch-delay").description("The amount of delay in ticks before switching slots again.").defaultValue(0).min(0).sliderMax(5).build());
        this.rotationMode = this.sgRotations.add(new EnumSetting.Builder().name("rotation-mode").description("The method of rotating when using Crystal Aura.").defaultValue(RotationMode.None).build());
        this.strictLook = this.sgRotations.add(new BoolSetting.Builder().name("strict-look").description("Looks at exactly where you're placing.").defaultValue(false).build());
        this.resetRotations = this.sgRotations.add(new BoolSetting.Builder().name("reset-rotations").description("Resets rotations once Crystal Aura is disabled.").defaultValue(false).build());
        this.hurtIgnoreSelfDmg = this.sgExperimental.add(new BoolSetting.Builder().name("hurt-time").description("Will place in suicide locations if you cannot be damaged. WARNING: VERY EXPERIMENTAL!").defaultValue(false).build());
        this.swing = this.sgRender.add(new BoolSetting.Builder().name("swing").description("Renders your swing client-side.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block under where it is placing a crystal.").defaultValue(true).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 255, 255, 75)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        this.randomColor = this.sgRender.add(new BoolSetting.Builder().name("random-color").description("Generate random color for each render.").defaultValue(false).build());
        this.fadeOut = this.sgRender.add(new BoolSetting.Builder().name("fade-out").description("Fades out the render.").defaultValue(true).build());
        this.renderDamage = this.sgRender.add(new BoolSetting.Builder().name("render-damage").description("Renders the damage of the crystal where it is placing.").defaultValue(true).build());
        this.roundDamage = this.sgRender.add(new IntSetting.Builder().name("round-damage").description("Round damage to x decimal places.").defaultValue(2).min(0).max(3).sliderMax(3).build());
        this.damageScale = this.sgRender.add(new DoubleSetting.Builder().name("damage-scale").description("The scale of the damage text.").defaultValue(1.4).min(0.0).sliderMax(5.0).build());
        this.damageColor = this.sgRender.add(new ColorSetting.Builder().name("damage-color").description("The color of the damage text.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        this.renderTimer = this.sgRender.add(new IntSetting.Builder().name("timer").description("The amount of time between changing the block render.").defaultValue(0).min(0).sliderMax(10).build());
        this.targets = new ArrayList<class_1309>();
        this.friends = new ArrayList<class_1657>();
        this.placedCrystals = new ArrayList<class_1511>();
        this.crystals = new ArrayList<class_1511>();
        this.attemptedCrystals = new ArrayList<class_1511>();
        this.crystalList = new ArrayList<Double>();
        this.entitiesToRemove = new IntOpenHashSet();
        this.renderBlocks = new ArrayList<RenderBlock>();
        this.attemptedBreaks = new Int2IntOpenHashMap();
        this.renderBlockPool = new Pool<Object>(this::lambda$new$0);
        this.crystalMap = new HashMap<class_1511, List<Double>>();
        this.doubleLegOffsetList = new class_243[]{new class_243(-1.0, 0.0, 0.0), new class_243(1.0, 0.0, 0.0), new class_243(0.0, 0.0, -1.0), new class_243(0.0, 0.0, 1.0), new class_243(-2.0, 0.0, 0.0), new class_243(2.0, 0.0, 0.0), new class_243(0.0, 0.0, -2.0), new class_243(0.0, 0.0, 2.0)};
    }

    private boolean lambda$getCrystalStream$7(class_1297 class_12972) {
        return (double)class_12972.method_5739((class_1297)this.mc.field_1724) <= this.breakRange.get();
    }

    private Stream<class_1297> getCrystalStream() {
        return Streams.stream((Iterable)this.mc.field_1687.method_18112()).filter(CrystalAuraTwo::lambda$getCrystalStream$6).filter(this::lambda$getCrystalStream$7).filter(class_1297::method_5805).filter(this::lambda$getCrystalStream$8);
    }

    private boolean shouldBreak(class_1511 class_15112) {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        if (!class_15112.method_19538().method_24802((class_2374)this.mc.field_1724.method_19538().method_1019(this.eyeHeight), this.breakRange.get().doubleValue())) {
            return false;
        }
        if (this.attemptedBreaks.get(class_15112.method_5628()) > Math.max(1, this.breakAttempts.get())) {
            return false;
        }
        if (!this.mc.field_1724.method_6057((class_1297)class_15112)) {
            if (this.rayTrace.get() == Type.Break || this.rayTrace.get() == Type.Both) {
                return false;
            }
            if (!class_15112.method_19538().method_24802((class_2374)this.mc.field_1724.method_19538().method_1019(this.eyeHeight), this.breakWallsRange.get().doubleValue())) {
                return false;
            }
        }
        return true;
    }

    /*
     * Unable to fully structure code
     */
    private class_243 findOpen(class_1309 var1_1, boolean var2_2) {
        block5: {
            block6: {
                block4: {
                    var3_3 = 0;
                    var4_4 = 0;
                    var5_5 = var1_1.method_24515();
                    var6_6 = var1_1.method_19538();
                    if (!this.isValid(var5_5.method_10069(1, -1, 0), var2_2) || !this.isSafePlace((class_1309)this.mc.field_1724, var6_6.method_1031(1.0, -1.0, 0.0))) break block4;
                    v0 = new class_2382(var5_5.method_10263() + 1, var5_5.method_10264() - 1, var5_5.method_10260());
                    if (!(Math.sqrt(this.playerBlockPos.method_10262(v0)) < this.placeRange.get())) break block4;
                    var3_3 = 1;
                    break block5;
                }
                if (!this.isValid(var5_5.method_10069(-1, -1, 0), var2_2) || !this.isSafePlace((class_1309)this.mc.field_1724, var6_6.method_1031(-1.0, -1.0, 0.0))) break block6;
                v1 = new class_2382(var5_5.method_10263() - 1, var5_5.method_10264() - 1, var5_5.method_10260());
                if (!(Math.sqrt(this.playerBlockPos.method_10262(v1)) < this.placeRange.get())) break block6;
                var3_3 = -1;
                break block5;
            }
            if (!this.isValid(var5_5.method_10069(0, -1, 1), var2_2) || !this.isSafePlace((class_1309)this.mc.field_1724, var6_6.method_1031(0.0, -1.0, 1.0))) ** GOTO lbl-1000
            v2 = new class_2382(var5_5.method_10263(), var5_5.method_10264() - 1, var5_5.method_10260() + 1);
            if (Math.sqrt(this.playerBlockPos.method_10262(v2)) < this.placeRange.get()) {
                var4_4 = 1;
            } else if (this.isValid(var5_5.method_10069(0, -1, -1), var2_2) && this.isSafePlace((class_1309)this.mc.field_1724, var6_6.method_1031(0.0, -1.0, -1.0))) {
                v3 = new class_2382(var5_5.method_10263(), var5_5.method_10264() - 1, var5_5.method_10260() - 1);
                if (Math.sqrt(this.playerBlockPos.method_10262(v3)) < this.placeRange.get()) {
                    var4_4 = -1;
                }
            }
        }
        return var3_3 == 0 && var4_4 == 0 ? null : new class_243((double)var5_5.method_10263() + 0.5 + (double)var3_3, (double)(var5_5.method_10264() - 1), (double)var5_5.method_10260() + 0.5 + (double)var4_4);
    }

    private void doHeldCrystal(class_243 class_2432) {
        this.placeDelayLeft = this.placeDelay.get();
        class_2338 class_23382 = new class_2338((int)class_2432.field_1352, (int)class_2432.field_1351, (int)class_2432.field_1350);
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        if (this.mc.field_1687.method_22347(class_23382)) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.mc.field_1724.field_7514.field_7545 != this.supportSlot && this.switchDelayLeft > 0) {
                return;
            }
            minegame159.meteorclient.utils.bedtrap.PlayerUtils.placeBlock(new class_2338(class_2432), this.supportSlot, class_1268.field_5808, (boolean)this.supportAirPlace.get());
            this.supportDelayLeft = this.supportDelay.get();
            this.supportDelayAfterLeft = this.supportDelayAfter.get();
        }
        if (this.supportDelayAfterLeft <= 0) {
            class_1511 class_15112 = new class_1511((class_1937)this.mc.field_1687, class_2432.field_1352, class_2432.field_1351 + 1.0, class_2432.field_1350);
            if (this.switchMode.get() != SwitchMode.None && this.switchDelayLeft <= 0) {
                this.doSwitch();
            }
            this.attemptedCrystals.add(class_15112);
            this.entitiesToRemove.clear();
            class_1268 class_12682 = this.getHand();
            class_2350 class_23502 = BlockUtils.rayTraceCheck(class_23382, true);
            if (this.rotationMode.get() != RotationMode.Place && this.rotationMode.get() != RotationMode.Both) {
                if (!$assertionsDisabled && this.mc.field_1724 == null) {
                    throw new AssertionError();
                }
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_12682, new class_3965(this.playerPos, class_23502, class_23382, false)));
            } else {
                float[] fArray = PlayerUtils.calculateAngle(this.strictLook.get() != false ? new class_243((double)class_23382.method_10263() + 0.5 + (double)Objects.requireNonNull(class_23502).method_10163().method_10263() * 1.0 / 2.0, (double)class_23382.method_10264() + 0.5 + (double)class_23502.method_10163().method_10264() * 1.0 / 2.0, (double)class_23382.method_10260() + 0.5 + (double)class_23502.method_10163().method_10260() * 1.0 / 2.0) : class_2432.method_1031(0.5, 1.0, 0.5));
                Rotations.rotate(fArray[0], fArray[1], 25, () -> this.lambda$doHeldCrystal$4(class_12682, class_23502, class_23382));
            }
            if (this.swing.get().booleanValue()) {
                if (!$assertionsDisabled && this.mc.field_1724 == null) {
                    throw new AssertionError();
                }
                this.mc.field_1724.method_6104(class_12682);
            } else {
                if (!$assertionsDisabled && this.mc.field_1724 == null) {
                    throw new AssertionError();
                }
                this.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_12682));
            }
        }
    }

    static Setting access$200(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.lineColor;
    }

    private boolean isValid(class_2338 class_23382, boolean bl) {
        if (!this.isEmpty(class_23382.method_10069(0, 1, 0))) {
            return false;
        }
        if (!$assertionsDisabled && this.mc.field_1687 == null) {
            throw new AssertionError();
        }
        return this.mc.field_1687.method_8320(class_23382).method_26204() == class_2246.field_9987 || this.mc.field_1687.method_8320(class_23382).method_26204() == class_2246.field_10540 || bl && this.mc.field_1687.method_22347(class_23382) && this.supportDelayLeft <= 0;
    }

    static Setting access$600(CrystalAuraTwo crystalAuraTwo) {
        return crystalAuraTwo.shapeMode;
    }

    private Object lambda$new$0() {
        return new RenderBlock(this, null);
    }

    private boolean isSafeBreak(class_1309 class_13092, class_243 class_2432) {
        if (this.breakMode.get() == Mode.Suicide) {
            return true;
        }
        double d = DamageCalcUtils.crystalDamage(class_13092, class_2432);
        return (double)(class_13092.method_6032() + class_13092.method_6067()) > d && d <= this.maxBreakDamage.get();
    }

    private class_243 findOpenSurround(class_1309 class_13092, boolean bl) {
        int n = 0;
        int n2 = 0;
        class_243 class_2432 = class_13092.method_19538();
        if (this.validSurroundBreak(class_13092, 2, 0, bl) && this.isSafePlace((class_1309)this.mc.field_1724, class_2432.method_1031(2.0, 0.0, 0.0))) {
            n = 2;
        } else if (this.validSurroundBreak(class_13092, -2, 0, bl) && this.isSafePlace((class_1309)this.mc.field_1724, class_2432.method_1031(-2.0, 0.0, 0.0))) {
            n = -2;
        } else if (this.validSurroundBreak(class_13092, 0, 2, bl) && this.isSafePlace((class_1309)this.mc.field_1724, class_2432.method_1031(0.0, 0.0, 2.0))) {
            n2 = 2;
        } else if (this.validSurroundBreak(class_13092, 0, -2, bl) && this.isSafePlace((class_1309)this.mc.field_1724, class_2432.method_1031(0.0, 0.0, -2.0))) {
            n2 = -2;
        }
        return n == 0 && n2 == 0 ? null : new class_243((double)(class_13092.method_24515().method_10263() + n) + 0.5, (double)(class_13092.method_24515().method_10264() - 1), (double)(class_13092.method_24515().method_10260() + n2) + 0.5);
    }

    public static enum SwitchMode {
        None,
        Auto;

    }

    public static enum PauseMode {
        None,
        Eat,
        Drink,
        Mine,
        EatAndDrink,
        EatAndMine,
        DrinkAndMine,
        All;

    }

    public static enum Mode {
        Safe,
        Suicide;

    }

    public static enum TargetMode {
        SingleDamage,
        TotalDamage;

    }

    public static enum Logic {
        PlaceBreak,
        BreakPlace;

    }

    public static enum Type {
        None,
        Place,
        Break,
        Both;

    }

    public static enum RotationMode {
        None,
        Place,
        Break,
        Both;

    }

    public static enum Canceller {
        NoDesync,
        HitCanceller;

    }

    private class RenderBlock {
        private int damageStartA;
        private int lineStartA;
        private Color lineColor;
        private int z;
        private final Color damageColor;
        private double damage;
        private int timer;
        private int y;
        private final Color sideColor;
        private int sideStartA;
        final CrystalAuraTwo this$0;
        private int x;

        public void set(class_2338 class_23382, double d) {
            this.x = class_23382.method_10263();
            this.y = class_23382.method_10264();
            this.z = class_23382.method_10260();
            this.timer = (Integer)CrystalAuraTwo.access$000(this.this$0).get();
            if (((Boolean)CrystalAuraTwo.access$100(this.this$0).get()).booleanValue()) {
                this.lineColor = Color.fromHsv(ThreadLocalRandom.current().nextDouble() * 360.0, 1.0, 1.0);
                this.sideColor.set(this.lineColor);
                this.sideColor.a = 75;
            } else {
                this.lineColor.set((Color)CrystalAuraTwo.access$200(this.this$0).get());
                this.sideColor.set((Color)CrystalAuraTwo.access$300(this.this$0).get());
            }
            this.damageColor.set((Color)CrystalAuraTwo.access$400(this.this$0).get());
            this.lineStartA = this.lineColor.a;
            this.sideStartA = this.sideColor.a;
            this.damageStartA = this.damageColor.a;
        }

        public void render2D() {
            if (((Boolean)CrystalAuraTwo.access$700(this.this$0).get()).booleanValue()) {
                CrystalAuraTwo.access$800().set((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5);
                if (NametagUtils.to2D(CrystalAuraTwo.access$800(), (Double)CrystalAuraTwo.access$900(this.this$0).get())) {
                    NametagUtils.begin(CrystalAuraTwo.access$800());
                    TextRenderer.get().begin(1.0, false, true);
                    String string = String.valueOf(Math.round(this.damage));
                    switch ((Integer)CrystalAuraTwo.access$1000(this.this$0).get()) {
                        case 0: {
                            string = String.valueOf(Math.round(this.damage));
                            break;
                        }
                        case 1: {
                            string = String.valueOf((double)Math.round(this.damage * 10.0) / 10.0);
                            break;
                        }
                        case 2: {
                            string = String.valueOf((double)Math.round(this.damage * 100.0) / 100.0);
                            break;
                        }
                        case 3: {
                            string = String.valueOf((double)Math.round(this.damage * 1000.0) / 1000.0);
                        }
                    }
                    double d = TextRenderer.get().getWidth(string) / 2.0;
                    TextRenderer.get().render(string, -d, 0.0, this.damageColor);
                    TextRenderer.get().end();
                    NametagUtils.end();
                }
            }
        }

        public boolean positionEquals(class_2338 class_23382) {
            return this.x == class_23382.method_10263() && this.y == class_23382.method_10264() && this.z == class_23382.method_10260();
        }

        private RenderBlock(CrystalAuraTwo crystalAuraTwo) {
            this.this$0 = crystalAuraTwo;
            this.sideColor = new Color();
            this.lineColor = new Color();
            this.damageColor = new Color();
        }

        RenderBlock(CrystalAuraTwo crystalAuraTwo, Object object) {
            this(crystalAuraTwo);
        }

        RenderBlock(CrystalAuraTwo crystalAuraTwo, 1 var2_2) {
            this(crystalAuraTwo);
        }

        public boolean shouldRemove() {
            int n;
            if (this.timer <= 0) {
                return true;
            }
            if (((Boolean)CrystalAuraTwo.access$500(this.this$0).get()).booleanValue() && this.timer <= (n = (Integer)CrystalAuraTwo.access$000(this.this$0).get() / 2)) {
                Color color = this.lineColor;
                color.a -= this.lineStartA / n;
                color = this.sideColor;
                color.a -= this.sideStartA / n;
                color = this.damageColor;
                color.a -= this.damageStartA / n;
            }
            --this.timer;
            return false;
        }

        public void render3D() {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, this.x, this.y, this.z, 1.0, this.sideColor, this.lineColor, (ShapeMode)((Object)CrystalAuraTwo.access$600(this.this$0).get()), 0);
        }
    }
}

