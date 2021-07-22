/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 *  com.google.common.util.concurrent.AtomicDouble
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 *  it.unimi.dsi.fastutil.ints.IntIterator
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  net.minecraft.class_1268
 *  net.minecraft.class_1294
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1812
 *  net.minecraft.class_1829
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
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
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class CrystalAuraTwo
extends Module {
    private /* synthetic */ class_243 eyeHeight;
    private final /* synthetic */ Setting<Double> damageScale;
    private final /* synthetic */ List<Double> crystalList;
    private final /* synthetic */ Setting<Double> facePlaceDurability;
    private final /* synthetic */ Setting<Mode> placeMode;
    private final /* synthetic */ Setting<Boolean> pauseOnMine;
    private final /* synthetic */ SettingGroup sgPause;
    private /* synthetic */ List<class_1511> attemptedCrystals;
    private final /* synthetic */ Setting<Boolean> renderDamage;
    private final /* synthetic */ SettingGroup sgTarget;
    private /* synthetic */ double ticksBehind;
    private final /* synthetic */ SettingGroup sgPlace;
    private final /* synthetic */ Setting<Double> breakRange;
    private final /* synthetic */ Setting<Boolean> oldPlace;
    private /* synthetic */ int preSlot;
    private final /* synthetic */ Map<class_1511, List<Double>> crystalMap;
    private final /* synthetic */ Setting<Boolean> strictLook;
    private final /* synthetic */ Setting<Boolean> hurtIgnoreSelfDmg;
    private final /* synthetic */ Setting<SettingColor> sideColor;
    private final /* synthetic */ Setting<Integer> roundDamage;
    private final /* synthetic */ Setting<Boolean> lethal;
    private final /* synthetic */ Setting<Logic> orderLogic;
    private final /* synthetic */ SettingGroup sgMisc;
    private final /* synthetic */ Setting<Double> torque;
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<Boolean> surroundHold;
    private final /* synthetic */ Pool<RenderBlock> renderBlockPool;
    private final /* synthetic */ IntSet entitiesToRemove;
    private final /* synthetic */ Setting<Boolean> supportAirPlace;
    private final /* synthetic */ Setting<Boolean> pauseOnDrink;
    private final /* synthetic */ Setting<Double> placeWallsRange;
    private final /* synthetic */ Setting<Boolean> facePlace;
    private final /* synthetic */ Setting<Double> maxPlaceDamage;
    private /* synthetic */ class_243 playerPos;
    private final /* synthetic */ class_243[] doubleLegOffsetList;
    private final /* synthetic */ SettingGroup sgExperimental;
    private final /* synthetic */ Setting<Boolean> support;
    public final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ Setting<Integer> renderTimer;
    private final /* synthetic */ Setting<SwitchMode> switchMode;
    private final /* synthetic */ Setting<Boolean> onlyOwn;
    private final /* synthetic */ Setting<Canceller> removeCrystals;
    private final /* synthetic */ Setting<SettingColor> lineColor;
    private final /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Setting<Boolean> supportBackup;
    private final /* synthetic */ Setting<Boolean> facePlaceSelf;
    private /* synthetic */ List<class_1511> crystals;
    private final /* synthetic */ SettingGroup sgBreak;
    private final /* synthetic */ SettingGroup sgSwitch;
    private final /* synthetic */ Setting<Double> facePlaceHealth;
    private final /* synthetic */ SettingGroup sgRender;
    public final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ Setting<Boolean> antiWeakness;
    private final /* synthetic */ SettingGroup sgRotations;
    private final /* synthetic */ Setting<Double> breakWallsRange;
    private /* synthetic */ int breakDelayLeft;
    private /* synthetic */ List<class_1657> friends;
    private static final /* synthetic */ Vec3 pos;
    private final /* synthetic */ Setting<Boolean> pauseOnEat;
    private final /* synthetic */ SettingGroup sgSupport;
    private final /* synthetic */ Setting<SettingColor> damageColor;
    private final /* synthetic */ Setting<Type> antiFriendPop;
    private final /* synthetic */ Setting<Integer> switchDelay;
    private /* synthetic */ int switchDelayLeft;
    private /* synthetic */ int supportDelayAfterLeft;
    private final /* synthetic */ class_238 box;
    private final /* synthetic */ Setting<RotationMode> rotationMode;
    private final /* synthetic */ Setting<PauseMode> facePlacePause;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Double> maxBreakDamage;
    private /* synthetic */ int supportSlot;
    private final /* synthetic */ Setting<Double> minBreakDamage;
    private final /* synthetic */ Setting<ShapeMode> shapeMode;
    private /* synthetic */ int supportDelayLeft;
    private final /* synthetic */ Setting<Integer> supportDelayAfter;
    private final /* synthetic */ Setting<Boolean> resetRotations;
    public final /* synthetic */ Setting<Boolean> predict;
    private final /* synthetic */ Setting<Type> pauseMode;
    private final /* synthetic */ Setting<Boolean> fadeOut;
    private /* synthetic */ List<class_1511> placedCrystals;
    private final /* synthetic */ Setting<Boolean> switchBack;
    private final /* synthetic */ Int2IntMap attemptedBreaks;
    private final /* synthetic */ List<RenderBlock> renderBlocks;
    private final /* synthetic */ Setting<Integer> supportDelay;
    private final /* synthetic */ Setting<Double> minPlaceDamage;
    private final /* synthetic */ Setting<Boolean> randomColor;
    private /* synthetic */ List<class_1309> targets;
    private final /* synthetic */ Setting<TargetMode> targetMode;
    private final /* synthetic */ Setting<Type> rayTrace;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ Setting<Boolean> surround;
    private final /* synthetic */ Setting<Mode> breakMode;
    private final /* synthetic */ Setting<Boolean> crystalSave;
    private final /* synthetic */ Setting<Double> placeRange;
    private static final /* synthetic */ Color color;
    private /* synthetic */ class_2338 playerBlockPos;
    private final /* synthetic */ Setting<Integer> breakAttempts;
    private /* synthetic */ int placeDelayLeft;
    private final /* synthetic */ Setting<Integer> numberOfDamages;

    private void singleBreak(class_1511 llIIIlIIlllIlIl) {
        CrystalAuraTwo llIIIlIIllllIIl;
        assert (llIIIlIIllllIIl.mc.field_1724 != null);
        assert (llIIIlIIllllIIl.mc.field_1687 != null);
        int llIIIlIIlllIlll = llIIIlIIllllIIl.mc.field_1724.field_7514.field_7545;
        llIIIlIIllllIIl.getCrystalStream().max(Comparator.comparingDouble(llIIIlIIllIIIIl -> {
            CrystalAuraTwo llIIIlIIllIIIlI;
            return DamageCalcUtils.crystalDamage((class_1309)llIIIlIIllIIIlI.targets, llIIIlIIllIIIIl.method_19538());
        })).ifPresent(llIIIlIIllIlIlI -> {
            CrystalAuraTwo llIIIlIIllIlIIl;
            llIIIlIIllIlIIl.attackCrystal(llIIIlIIlllIlIl, llIIIlIIlllIlll);
        });
    }

    private void doPlace() {
        CrystalAuraTwo llIIIlllIlIIllI;
        if (llIIIlllIlIIllI.placeDelayLeft <= 0) {
            boolean llIIIlllIlIlIII;
            assert (llIIIlllIlIIllI.mc.field_1724 != null);
            boolean bl = llIIIlllIlIlIII = llIIIlllIlIIllI.mc.field_1724.method_6047().method_7909() == class_1802.field_8301 || llIIIlllIlIIllI.mc.field_1724.method_6079().method_7909() == class_1802.field_8301;
            if (llIIIlllIlIIllI.switchMode.get() != SwitchMode.None || llIIIlllIlIlIII) {
                int llIIIlllIlIlIIl = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8301}).slot;
                if (llIIIlllIlIlIIl >= 0 && llIIIlllIlIlIIl <= 8 || llIIIlllIlIlIII) {
                    if (llIIIlllIlIIllI.pauseMode.get() == Type.Place || llIIIlllIlIIllI.pauseMode.get() == Type.Both) {
                        if (llIIIlllIlIIllI.mc.field_1724.method_6115()) {
                            if (llIIIlllIlIIllI.pauseOnEat.get().booleanValue() && (llIIIlllIlIIllI.mc.field_1724.method_6047().method_7909().method_19263() || llIIIlllIlIIllI.mc.field_1724.method_6079().method_7909().method_19263())) {
                                return;
                            }
                            if (llIIIlllIlIIllI.pauseOnDrink.get().booleanValue() && (llIIIlllIlIIllI.mc.field_1724.method_6047().method_7909() instanceof class_1812 || llIIIlllIlIIllI.mc.field_1724.method_6079().method_7909() instanceof class_1812)) {
                                return;
                            }
                        }
                        if (llIIIlllIlIIllI.pauseOnMine.get().booleanValue()) {
                            assert (llIIIlllIlIIllI.mc.field_1761 != null);
                            if (llIIIlllIlIIllI.mc.field_1761.method_2923()) {
                                return;
                            }
                        }
                    }
                    boolean llIIIlllIllIIIl = false;
                    if (llIIIlllIlIIllI.support.get().booleanValue()) {
                        for (int llIIIlllIllIIlI = 0; llIIIlllIllIIlI < 9; ++llIIIlllIllIIlI) {
                            if (llIIIlllIlIIllI.mc.field_1724.field_7514.method_5438(llIIIlllIllIIlI).method_7909() != class_1802.field_8281) continue;
                            llIIIlllIllIIIl = true;
                            llIIIlllIlIIllI.supportSlot = llIIIlllIllIIlI;
                            break;
                        }
                    }
                    AtomicReference llIIIlllIllIIII = new AtomicReference();
                    AtomicReference llIIIlllIlIllll = new AtomicReference();
                    AtomicDouble llIIIlllIlIlllI = new AtomicDouble();
                    AtomicDouble llIIIlllIlIllIl = new AtomicDouble();
                    AtomicDouble llIIIlllIlIllII = new AtomicDouble();
                    AtomicDouble llIIIlllIlIlIll = new AtomicDouble();
                    llIIIlllIlIIllI.crystalMap.clear();
                    llIIIlllIlIIllI.crystalList.clear();
                    boolean llIIIlllIlIlIlI = llIIIlllIllIIIl;
                    BlockIterator.register(llIIIlllIlIIllI.placeRange.get().intValue(), llIIIlllIlIIllI.placeRange.get().intValue(), (llIIIIlllIIllIl, llIIIIlllIlIlll) -> {
                        CrystalAuraTwo llIIIIllllIIIII;
                        class_243 llIIIIlllIlIllI = new class_243((double)llIIIIlllIIllIl.method_10263(), (double)llIIIIlllIIllIl.method_10264(), (double)llIIIIlllIIllIl.method_10260());
                        if (llIIIIlllIlIlll.method_26215()) {
                            if (llIIIIllllIIIII.supportBackup.get().booleanValue() && llIIIlllIlIllll.get() != null) {
                                return;
                            }
                            if (llIIIIllllIIIII.mc.field_1724.field_7514.field_7545 != llIIIIllllIIIII.supportSlot && llIIIIllllIIIII.switchDelayLeft > 0) {
                                return;
                            }
                        }
                        if (llIIIIlllIlIllI.method_24802((class_2374)llIIIIllllIIIII.playerPos.method_1019(llIIIIllllIIIII.eyeHeight), llIIIIllllIIIII.placeRange.get().doubleValue())) {
                            double llIIIIllllIIlIl = 0.0;
                            double llIIIIllllIIlII = 0.0;
                            double llIIIIllllIIIll = 0.0;
                            if (llIIIIllllIIIII.antiFriendPop.get() == Type.Place || llIIIIllllIIIII.antiFriendPop.get() == Type.Both) {
                                for (class_1657 llIIIIllllllIlI : llIIIIllllIIIII.friends) {
                                    if (!llIIIIlllIlIllI.method_24802((class_2374)llIIIIllllllIlI.method_19538(), 9.0)) continue;
                                    if (!llIIIIllllIIIII.isSafePlace((class_1309)llIIIIllllllIlI, llIIIIlllIlIllI.method_1031(0.5, 1.0, 0.5))) {
                                        return;
                                    }
                                    llIIIIllllIIlII = DamageCalcUtils.crystalDamage((class_1309)llIIIIllllllIlI, llIIIIlllIlIllI.method_1031(0.5, 1.0, 0.5));
                                }
                            }
                            boolean llIIIIllllIIIlI = false;
                            for (class_1309 llIIIIllllIlIII : llIIIIllllIIIII.targets) {
                                boolean llIIIIllllIlIIl;
                                class_243 llIIIIllllIIlll = llIIIIllllIlIII.method_19538();
                                if (llIIIIllllIIIII.predict.get().booleanValue() && !(llIIIIllllIlIII instanceof FakePlayerEntity)) {
                                    class_243 llIIIIllllllIII = llIIIIllllIlIII.method_18798();
                                    llIIIIllllIIlll = llIIIIllllIIlll.method_1031(llIIIIllllllIII.field_1352 * llIIIIllllIIIII.ticksBehind, llIIIIllllllIII.field_1351 * llIIIIllllIIIII.ticksBehind, llIIIIllllllIII.field_1350 * llIIIIllllIIIII.ticksBehind);
                                }
                                if (BlockUtils.isSurrounded(llIIIIllllIlIII) && !llIIIIlllIlIllI.method_24802((class_2374)llIIIIllllIIlll, 3.0)) continue;
                                boolean bl = llIIIIllllIlIIl = llIIIIllllIIIII.shouldFacePlace((class_1297)llIIIIllllIlIII) && !Arrays.asList(llIIIIllllIIIII.doubleLegOffsetList).contains((Object)llIIIIlllIlIllI) && !llIIIIllllIIIlI;
                                if (llIIIIllllIlIIl) {
                                    llIIIIllllIIIlI = true;
                                }
                                if ((llIIIIllllIIIII.pauseMode.get() == Type.Place || llIIIIllllIIIII.pauseMode.get() == Type.Both) && llIIIIllllIlIIl && (llIIIIllllIIIII.mc.field_1724.method_6115() && ((llIIIIllllIIIII.facePlacePause.get() == PauseMode.All || llIIIIllllIIIII.facePlacePause.get() == PauseMode.EatAndDrink || llIIIIllllIIIII.facePlacePause.get() == PauseMode.EatAndMine || llIIIIllllIIIII.facePlacePause.get() == PauseMode.Eat) && (llIIIIllllIIIII.mc.field_1724.method_6047().method_7909().method_19263() || llIIIIllllIIIII.mc.field_1724.method_6079().method_7909().method_19263()) || (llIIIIllllIIIII.facePlacePause.get() == PauseMode.All || llIIIIllllIIIII.facePlacePause.get() == PauseMode.EatAndDrink || llIIIIllllIIIII.facePlacePause.get() == PauseMode.DrinkAndMine || llIIIIllllIIIII.facePlacePause.get() == PauseMode.Drink) && (llIIIIllllIIIII.mc.field_1724.method_6047().method_7909() instanceof class_1812 || llIIIIllllIIIII.mc.field_1724.method_6079().method_7909() instanceof class_1812)) || Objects.requireNonNull(llIIIIllllIIIII.mc.field_1761).method_2923() && (llIIIIllllIIIII.facePlacePause.get() == PauseMode.All || llIIIIllllIIIII.facePlacePause.get() == PauseMode.EatAndMine || llIIIIllllIIIII.facePlacePause.get() == PauseMode.DrinkAndMine || llIIIIllllIIIII.facePlacePause.get() == PauseMode.Mine)) || llIIIIllllIIIII.crystalSave.get().booleanValue() && llIIIIllllIlIII.field_6235 - (Math.max(llIIIIllllIIIII.breakDelayLeft, 0) + (int)llIIIIllllIIIII.ticksBehind) > 0 || llIIIIlllIIllIl.method_10264() > llIIIIllllIlIII.method_24515().method_10264() || llIIIIlllIlIllI.method_1022(llIIIIllllIIlll) > 9.0 || !llIIIIllllIIIII.isValid((class_2338)llIIIIlllIIllIl, llIIIlllIlIlIlI)) continue;
                                double llIIIIllllIllII = llIIIIllllIlIIl ? 2.5 : llIIIIllllIIIII.minPlaceDamage.get();
                                boolean llIIIIllllIlIll = false;
                                for (class_1511 llIIIIlllllIlIl : llIIIIllllIIIII.onlyOwn.get() != false ? llIIIIllllIIIII.placedCrystals : llIIIIllllIIIII.crystals) {
                                    double llIIIIlllllIllI;
                                    if (llIIIIlllllIlIl.method_5739((class_1297)llIIIIllllIlIII) > 9.0f || !((llIIIIlllllIllI = DamageCalcUtils.crystalDamage(llIIIIllllIlIII, llIIIIlllllIlIl.method_19538())) >= llIIIIllllIllII)) continue;
                                    boolean llIIIIlllllIlll = false;
                                    if (llIIIIllllIIIII.lethal.get().booleanValue() && llIIIIllllIIIII.targetMode.get() == TargetMode.SingleDamage && llIIIIlllllIllI > (double)(llIIIIllllIlIII.method_6032() + llIIIIllllIlIII.method_6067()) + 0.5) {
                                        llIIIIlllllIlll = true;
                                    }
                                    if (!llIIIIllllIIIII.shouldBreak(llIIIIlllllIlIl) || llIIIIllllIIIII.isToRemove(llIIIIlllllIlIl) || !llIIIIllllIIIII.isSafeBreak((class_1309)llIIIIllllIIIII.mc.field_1724, llIIIIlllllIlIl.method_19538()) && !llIIIIlllllIlll && (!llIIIIllllIIIII.hurtIgnoreSelfDmg.get().booleanValue() || llIIIIllllIIIII.mc.field_1724.field_6235 - (int)llIIIIllllIIIII.ticksBehind - Math.max(0, llIIIIllllIIIII.breakDelayLeft) <= 0) || !llIIIIlllIlIllI.method_1031(0.0, 1.0, 0.0).method_24802((class_2374)llIIIIllllIIIII.playerPos.method_1019(llIIIIllllIIIII.eyeHeight), llIIIIllllIIIII.breakRange.get().doubleValue())) continue;
                                    llIIIIllllIlIll = true;
                                    break;
                                }
                                if (llIIIIllllIlIll) continue;
                                if (!llIIIIllllIIIII.supportAirPlace.get().booleanValue()) {
                                    assert (llIIIIllllIIIII.mc.field_1687 != null);
                                    if (llIIIIllllIIIII.mc.field_1687.method_22347(llIIIIlllIIllIl)) {
                                        boolean llIIIIlllllIIIl = false;
                                        for (class_2350 llIIIIlllllIlII : class_2350.values()) {
                                            class_2338 llIIIIlllllIIll = llIIIIlllIIllIl.method_10093(llIIIIlllllIlII);
                                            if (llIIIIllllIIIII.mc.field_1687.method_8320(llIIIIlllllIIll).method_26215() || minegame159.meteorclient.utils.world.BlockUtils.isClickable(llIIIIllllIIIII.mc.field_1687.method_8320(llIIIIlllllIIll).method_26204())) continue;
                                            llIIIIlllllIIIl = true;
                                            break;
                                        }
                                        if (!llIIIIlllllIIIl) continue;
                                    }
                                }
                                if (BlockUtils.rayTraceCheck(llIIIIlllIIllIl, false) == null && (llIIIIllllIIIII.rayTrace.get() == Type.Place || llIIIIllllIIIII.rayTrace.get() == Type.Both || llIIIIllllIIIII.playerPos.method_1019(llIIIIllllIIIII.eyeHeight).method_1022(llIIIIlllIlIllI) > llIIIIllllIIIII.placeWallsRange.get()) || !llIIIIllllIIIII.isSafePlace((class_1309)llIIIIllllIIIII.mc.field_1724, llIIIIlllIlIllI.method_1031(0.5, 1.0, 0.5))) continue;
                                llIIIIllllIIIll = DamageCalcUtils.crystalDamage((class_1309)llIIIIllllIIIII.mc.field_1724, llIIIIlllIlIllI.method_1031(0.5, 1.0, 0.5)) + llIIIIllllIIlII;
                                double llIIIIllllIllIl = DamageCalcUtils.crystalDamage(llIIIIllllIlIII, llIIIIlllIlIllI.method_1031(0.5, 1.0, 0.5));
                                if (llIIIIllllIllIl < llIIIIllllIllII) continue;
                                if (llIIIIllllIIIII.targetMode.get() == TargetMode.TotalDamage) {
                                    llIIIIllllIIlIl += llIIIIllllIllIl;
                                    continue;
                                }
                                double llIIIIllllIlllI = llIIIIllllIllIl / Math.pow(llIIIIllllIIIll, 1.0 - llIIIIllllIIIII.torque.get());
                                assert (llIIIIllllIIIII.mc.field_1687 != null);
                                if (llIIIIllllIIIII.mc.field_1687.method_22347(llIIIIlllIIllIl) && llIIIIllllIIIII.supportBackup.get().booleanValue()) {
                                    if (llIIIlllIlIllll.get() != null || !(llIIIIllllIlllI > llIIIlllIlIllII.get())) continue;
                                    llIIIlllIllIIII.set(llIIIIlllIlIllI);
                                    llIIIlllIlIllII.set(llIIIIllllIlllI);
                                    llIIIlllIlIlIll.set(llIIIIllllIllIl);
                                    continue;
                                }
                                if (llIIIIllllIIIll < 1.0 && llIIIIllllIllIl >= 36.0) {
                                    llIIIlllIlIllll.set(llIIIIlllIlIllI);
                                    llIIIlllIlIllIl.set(llIIIIllllIllIl);
                                    BlockIterator.disableCurrent();
                                }
                                if (!(llIIIIllllIlllI > llIIIlllIlIlllI.get())) continue;
                                llIIIlllIlIllll.set(llIIIIlllIlIllI);
                                llIIIlllIlIlllI.set(llIIIIllllIlllI);
                                llIIIlllIlIllIl.set(llIIIIllllIllIl);
                            }
                            if (llIIIIllllIIIII.targetMode.get() == TargetMode.TotalDamage) {
                                double llIIIIllllIIllI = llIIIIllllIIlIl / Math.pow(llIIIIllllIIIll, 1.0 - llIIIIllllIIIII.torque.get());
                                assert (llIIIIllllIIIII.mc.field_1687 != null);
                                if (llIIIIllllIIIII.mc.field_1687.method_22347(llIIIIlllIIllIl) && llIIIIllllIIIII.supportBackup.get().booleanValue()) {
                                    if (llIIIlllIlIllll.get() == null && llIIIIllllIIllI > llIIIlllIlIllII.get()) {
                                        llIIIlllIllIIII.set(llIIIIlllIlIllI);
                                        llIIIlllIlIllII.set(llIIIIllllIIllI);
                                        llIIIlllIlIlIll.set(llIIIIllllIIlIl);
                                    }
                                } else if (llIIIIllllIIllI > llIIIlllIlIlllI.get()) {
                                    llIIIlllIlIllll.set(llIIIIlllIlIllI);
                                    llIIIlllIlIlllI.set(llIIIIllllIIllI);
                                    llIIIlllIlIllIl.set(llIIIIllllIIlIl);
                                }
                            }
                        }
                    });
                    BlockIterator.after(() -> {
                        if (llIIIlllIlIllll.get() != null || llIIIlllIllIIII.get() != null) {
                            CrystalAuraTwo llIIIlIIIlIIllI;
                            class_243 llIIIlIIIlIIlll = llIIIlllIlIllll.get() != null ? (class_243)llIIIlllIlIllll.get() : (class_243)llIIIlllIllIIII.get();
                            llIIIlIIIlIIllI.doHeldCrystal(llIIIlIIIlIIlll);
                            if (llIIIlIIIlIIllI.render.get().booleanValue()) {
                                double llIIIlIIIlIlIll = llIIIlllIlIllll.get() != null ? llIIIlllIlIllIl.get() : llIIIlllIlIlIll.get();
                                class_2338 llIIIlIIIlIlIlI = new class_2338(llIIIlIIIlIIlll);
                                for (RenderBlock llIIIlIIIlIllII : llIIIlIIIlIIllI.renderBlocks) {
                                    if (!llIIIlIIIlIllII.positionEquals(llIIIlIIIlIlIlI)) continue;
                                    llIIIlIIIlIllII.set(llIIIlIIIlIlIlI, llIIIlIIIlIlIll);
                                    return;
                                }
                                RenderBlock llIIIlIIIlIlIII = llIIIlIIIlIIllI.renderBlockPool.get();
                                llIIIlIIIlIlIII.set(llIIIlIIIlIlIlI, llIIIlIIIlIlIll);
                                llIIIlIIIlIIllI.renderBlocks.add(llIIIlIIIlIlIII);
                            }
                        }
                    });
                }
            }
        }
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent llIIIllIlIlllII) {
        if (llIIIllIlIlllII.entity instanceof class_1511) {
            CrystalAuraTwo llIIIllIlIllIll;
            for (int llIIIllIlIllllI = 0; llIIIllIlIllllI < llIIIllIlIllIll.attemptedCrystals.size(); ++llIIIllIlIllllI) {
                if (!(llIIIllIlIllIll.attemptedCrystals.get(llIIIllIlIllllI).method_19538().method_1022(llIIIllIlIlllII.entity.method_19538()) < 1.0)) continue;
                llIIIllIlIllIll.attemptedCrystals.remove(llIIIllIlIllllI);
                llIIIllIlIllIll.placedCrystals.add((class_1511)llIIIllIlIlllII.entity);
                break;
            }
        }
    }

    private boolean shouldFacePlace(class_1297 llIIIlIlIlIlIII) {
        class_1799 llIIIlIlIlIlIlI;
        CrystalAuraTwo llIIIlIlIlIlIIl;
        if (!llIIIlIlIlIlIIl.facePlace.get().booleanValue()) {
            return false;
        }
        if (!(llIIIlIlIlIlIII instanceof class_1657)) {
            return false;
        }
        assert (llIIIlIlIlIlIIl.mc.field_1724 != null);
        if (llIIIlIlIlIlIIl.mc.field_1724.method_5739(llIIIlIlIlIlIII) < 1.0f) {
            return llIIIlIlIlIlIIl.facePlaceSelf.get();
        }
        if ((double)(((class_1657)llIIIlIlIlIlIII).method_6032() + ((class_1657)llIIIlIlIlIlIII).method_6067()) <= llIIIlIlIlIlIIl.facePlaceHealth.get()) {
            return true;
        }
        Iterable llIIIlIlIlIllII = llIIIlIlIlIlIII.method_5661();
        Iterator llIIIlIlIlIlIll = llIIIlIlIlIllII.iterator();
        do {
            if (llIIIlIlIlIlIll.hasNext()) continue;
            return false;
        } while ((llIIIlIlIlIlIlI = (class_1799)llIIIlIlIlIlIll.next()) == null || llIIIlIlIlIlIlI.method_7960() || !((double)(llIIIlIlIlIlIlI.method_7936() - llIIIlIlIlIlIlI.method_7919()) / (double)llIIIlIlIlIlIlI.method_7936() * 100.0 <= llIIIlIlIlIlIIl.facePlaceDurability.get()));
        return true;
    }

    private void attackCrystal(class_1511 llIIIllIllIIllI, int llIIIllIllIIlIl) {
        CrystalAuraTwo llIIIllIllIIlll;
        if (llIIIllIllIIlll.antiWeakness.get().booleanValue() && llIIIllIllIIlll.mc.field_1724 != null && Objects.requireNonNull(llIIIllIllIIlll.mc.field_1724).method_6088().containsKey((Object)class_1294.field_5911)) {
            assert (llIIIllIllIIlll.mc.field_1761 != null);
            llIIIllIllIIlll.mc.field_1761.method_2918((class_1657)llIIIllIllIIlll.mc.field_1724, (class_1297)llIIIllIllIIllI);
        } else {
            assert (llIIIllIllIIlll.mc.field_1724 != null);
            llIIIllIllIIlll.mc.field_1724.field_3944.method_2883((class_2596)new class_2824((class_1297)llIIIllIllIIllI, false));
        }
        if (llIIIllIllIIlll.swing.get().booleanValue()) {
            llIIIllIllIIlll.mc.field_1724.method_6104(llIIIllIllIIlll.getHand());
        } else {
            llIIIllIllIIlll.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(llIIIllIllIIlll.getHand()));
        }
        if (llIIIllIllIIlll.removeCrystals.get() == Canceller.NoDesync) {
            llIIIllIllIIlll.entitiesToRemove.add(llIIIllIllIIllI.method_5628());
        }
        if (llIIIllIllIIlll.removeCrystals.get() == Canceller.HitCanceller) {
            llIIIllIllIIlll.entitiesToRemove.add(llIIIllIllIIllI.method_5628());
            llIIIllIllIIlll.placedCrystals.remove((Object)llIIIllIllIIllI);
            llIIIllIllIIlll.crystals.remove((Object)llIIIllIllIIllI);
            llIIIllIllIIllI.method_5650();
        }
        llIIIllIllIIlll.attemptedBreaks.put(llIIIllIllIIllI.method_5628(), llIIIllIllIIlll.attemptedBreaks.get(llIIIllIllIIllI.method_5628()) + 1);
        if (llIIIllIllIIlll.switchDelayLeft <= 0) {
            llIIIllIllIIlll.mc.field_1724.field_7514.field_7545 = llIIIllIllIIlIl;
            llIIIllIllIIlll.switchDelayLeft = llIIIllIllIIlll.switchDelay.get();
        }
    }

    @Override
    public void onActivate() {
        CrystalAuraTwo llIIIllllIlllll;
        llIIIllllIlllll.preSlot = -1;
        llIIIllllIlllll.placeDelayLeft = 0;
        llIIIllllIlllll.breakDelayLeft = 0;
        llIIIllllIlllll.supportDelayLeft = 0;
        llIIIllllIlllll.supportDelayAfterLeft = 0;
        llIIIllllIlllll.switchDelayLeft = 0;
        for (RenderBlock llIIIlllllIIIII : llIIIllllIlllll.renderBlocks) {
            llIIIllllIlllll.renderBlockPool.free(llIIIlllllIIIII);
        }
        llIIIllllIlllll.renderBlocks.clear();
        llIIIllllIlllll.targets.clear();
        llIIIllllIlllll.friends.clear();
        llIIIllllIlllll.crystals.clear();
        llIIIllllIlllll.attemptedBreaks.clear();
    }

    private boolean isSafePlace(class_1309 llIIIlIllIIllIl, class_243 llIIIlIllIIllll) {
        CrystalAuraTwo llIIIlIllIlIIIl;
        if (llIIIlIllIlIIIl.placeMode.get() == Mode.Suicide) {
            return true;
        }
        if (llIIIlIllIlIIIl.hurtIgnoreSelfDmg.get().booleanValue() && llIIIlIllIIllIl.field_6235 - (Math.max(llIIIlIllIlIIIl.breakDelayLeft, 0) + (int)llIIIlIllIlIIIl.ticksBehind) > 0) {
            return true;
        }
        double llIIIlIllIlIIlI = DamageCalcUtils.crystalDamage(llIIIlIllIIllIl, llIIIlIllIIllll);
        return (double)(llIIIlIllIIllIl.method_6032() + llIIIlIllIIllIl.method_6067()) > llIIIlIllIlIIlI && llIIIlIllIlIIlI <= llIIIlIllIlIIIl.maxPlaceDamage.get();
    }

    public class_1657 getPlayerTarget() {
        CrystalAuraTwo llIIIlIIlllIIlI;
        if (llIIIlIIlllIIlI.targets instanceof class_1657) {
            return (class_1657)llIIIlIIlllIIlI.targets;
        }
        return null;
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Pre llIIIllllIlIIlI) {
        CrystalAuraTwo llIIIllllIlIIII;
        --llIIIllllIlIIII.placeDelayLeft;
        --llIIIllllIlIIII.breakDelayLeft;
        --llIIIllllIlIIII.supportDelayLeft;
        --llIIIllllIlIIII.supportDelayAfterLeft;
        --llIIIllllIlIIII.switchDelayLeft;
        assert (llIIIllllIlIIII.mc.field_1724 != null);
        llIIIllllIlIIII.ticksBehind = (double)Objects.requireNonNull(Objects.requireNonNull(llIIIllllIlIIII.mc.method_1562()).method_2871(llIIIllllIlIIII.mc.field_1724.method_5667())).method_2959() / 50.0;
        Iterator<RenderBlock> llIIIllllIlIIIl = llIIIllllIlIIII.renderBlocks.iterator();
        while (llIIIllllIlIIIl.hasNext()) {
            RenderBlock llIIIllllIlIlII = llIIIllllIlIIIl.next();
            if (!llIIIllllIlIlII.shouldRemove()) continue;
            llIIIllllIlIIIl.remove();
            llIIIllllIlIIII.renderBlockPool.free(llIIIllllIlIlII);
        }
        llIIIllllIlIIII.getEntities();
        if (!llIIIllllIlIIII.targets.isEmpty()) {
            llIIIllllIlIIII.playerPos = llIIIllllIlIIII.mc.field_1724.method_19538();
            llIIIllllIlIIII.eyeHeight = new class_243(0.0, (double)llIIIllllIlIIII.mc.field_1724.method_18381(llIIIllllIlIIII.mc.field_1724.method_18376()), 0.0);
            llIIIllllIlIIII.playerBlockPos = llIIIllllIlIIII.mc.field_1724.method_24515();
            switch (llIIIllllIlIIII.orderLogic.get()) {
                case BreakPlace: {
                    llIIIllllIlIIII.doBreak();
                    llIIIllllIlIIII.doPlace();
                    break;
                }
                case PlaceBreak: {
                    assert (llIIIllllIlIIII.mc.field_1687 != null);
                    llIIIllllIlIIII.doPlace();
                    llIIIllllIlIIII.doBreak();
                }
            }
        }
    }

    @EventHandler(priority=100)
    private void onTick(TickEvent.Post llIIIllllIIlIll) {
        CrystalAuraTwo llIIIllllIIlIlI;
        llIIIllllIIlIlI.targets.clear();
        llIIIllllIIlIlI.friends.clear();
        llIIIllllIIlIlI.crystals.clear();
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent llIIIllIlIlIIll) {
        if (llIIIllIlIlIIll.entity instanceof class_1511) {
            CrystalAuraTwo llIIIllIlIlIllI;
            llIIIllIlIlIllI.placedCrystals.remove((Object)llIIIllIlIlIIll.entity);
            llIIIllIlIlIllI.attemptedBreaks.remove(llIIIllIlIlIIll.entity.method_5628());
        }
    }

    static {
        pos = new Vec3();
        color = new Color(22, 30, 30);
    }

    @EventHandler
    private void onRender2D(Render2DEvent llIIIlIlIIIllIl) {
        CrystalAuraTwo llIIIlIlIIIlIll;
        for (RenderBlock llIIIlIlIIIllll : llIIIlIlIIIlIll.renderBlocks) {
            llIIIlIlIIIllll.render2D();
        }
    }

    private boolean isEmpty(class_2338 llIIIlIlIlllllI) {
        boolean llIIIlIlIlllIlI;
        CrystalAuraTwo llIIIlIlIllllll;
        double llIIIlIlIllllIl = llIIIlIlIlllllI.method_10263();
        double llIIIlIlIllllII = llIIIlIlIlllllI.method_10264();
        double llIIIlIlIlllIll = llIIIlIlIlllllI.method_10260();
        ((IBox)llIIIlIlIllllll.box).set(llIIIlIlIllllIl, llIIIlIlIllllII - 1.0, llIIIlIlIlllIll, llIIIlIlIllllIl + 1.0, llIIIlIlIllllII + 2.0, llIIIlIlIlllIll + 1.0);
        if (!llIIIlIlIllllll.entitiesToRemove.isEmpty()) {
            IntIterator llIIIlIllIIIIIl = llIIIlIlIllllll.entitiesToRemove.iterator();
            while (llIIIlIllIIIIIl.hasNext()) {
                int llIIIlIllIIIIlI = llIIIlIllIIIIIl.next();
                assert (llIIIlIlIllllll.mc.field_1687 != null);
                if (llIIIlIlIllllll.mc.field_1687.method_8335(llIIIlIlIllllll.mc.field_1687.method_8469(llIIIlIllIIIIlI), llIIIlIlIllllll.box).isEmpty()) continue;
                return false;
            }
            boolean llIIIlIllIIIIII = true;
        } else {
            assert (llIIIlIlIllllll.mc.field_1687 != null);
            llIIIlIlIlllIlI = llIIIlIlIllllll.mc.field_1687.method_8335(null, llIIIlIlIllllll.box).isEmpty();
        }
        assert (llIIIlIlIllllll.mc.field_1687 != null);
        return llIIIlIlIllllll.mc.field_1687.method_8320(llIIIlIlIlllllI).method_26215() && llIIIlIlIlllIlI && (llIIIlIlIllllll.oldPlace.get() == false || llIIIlIlIllllll.mc.field_1687.method_8320(llIIIlIlIlllllI.method_10069(0, 1, 0)).method_26215());
    }

    private void doBreak() {
        CrystalAuraTwo llIIIllIlllllIl;
        if (llIIIllIlllllIl.breakDelayLeft <= 0) {
            if (llIIIllIlllllIl.pauseMode.get() == Type.Break || llIIIllIlllllIl.pauseMode.get() == Type.Both) {
                assert (llIIIllIlllllIl.mc.field_1724 != null);
                if (llIIIllIlllllIl.mc.field_1724.method_6115()) {
                    if (llIIIllIlllllIl.pauseOnEat.get().booleanValue() && (llIIIllIlllllIl.mc.field_1724.method_6047().method_7909().method_19263() || llIIIllIlllllIl.mc.field_1724.method_6079().method_7909().method_19263())) {
                        return;
                    }
                    if (llIIIllIlllllIl.pauseOnDrink.get().booleanValue() && (llIIIllIlllllIl.mc.field_1724.method_6047().method_7909() instanceof class_1812 || llIIIllIlllllIl.mc.field_1724.method_6079().method_7909() instanceof class_1812)) {
                        return;
                    }
                }
                if (llIIIllIlllllIl.pauseOnMine.get().booleanValue()) {
                    assert (llIIIllIlllllIl.mc.field_1761 != null);
                    if (llIIIllIlllllIl.mc.field_1761.method_2923()) {
                        return;
                    }
                }
            }
            double llIIIlllIIIIIIl = 0.0;
            class_1511 llIIIlllIIIIIII = null;
            Iterator<class_1511> llIIIllIlllllll = (llIIIllIlllllIl.onlyOwn.get() != false ? llIIIllIlllllIl.placedCrystals : llIIIllIlllllIl.crystals).iterator();
            block0: while (true) {
                class_1657 llIIIlllIIIIlIl;
                if (!llIIIllIlllllll.hasNext()) {
                    if (llIIIlllIIIIIII == null) {
                        return;
                    }
                    assert (llIIIllIlllllIl.mc.field_1724 != null);
                    int llIIIlllIIIlIll = llIIIllIlllllIl.mc.field_1724.field_7514.field_7545;
                    if (llIIIllIlllllIl.mc.field_1724.method_6088().containsKey((Object)class_1294.field_5911) && llIIIllIlllllIl.antiWeakness.get().booleanValue() && llIIIllIlllllIl.switchDelayLeft <= 0) {
                        for (int llIIIlllIIIlllI = 0; llIIIlllIIIlllI < 9; ++llIIIlllIIIlllI) {
                            if (!(llIIIllIlllllIl.mc.field_1724.field_7514.method_5438(llIIIlllIIIlllI).method_7909() instanceof class_1829) && !(llIIIllIlllllIl.mc.field_1724.field_7514.method_5438(llIIIlllIIIlllI).method_7909() instanceof class_1743)) continue;
                            llIIIllIlllllIl.mc.field_1724.field_7514.field_7545 = llIIIlllIIIlllI;
                            llIIIllIlllllIl.switchDelayLeft = llIIIllIlllllIl.switchDelay.get();
                            break;
                        }
                    }
                    if (llIIIllIlllllIl.rotationMode.get() != RotationMode.Break && llIIIllIlllllIl.rotationMode.get() != RotationMode.Both) {
                        llIIIllIlllllIl.attackCrystal(llIIIlllIIIIIII, llIIIlllIIIlIll);
                    } else {
                        float[] llIIIlllIIIllIl = PlayerUtils.calculateAngle(llIIIlllIIIIIII.method_19538());
                        class_1511 llIIIlllIIIllII = llIIIlllIIIIIII;
                        Rotations.rotate(llIIIlllIIIllIl[0], llIIIlllIIIllIl[1], 30, () -> {
                            CrystalAuraTwo llIIIlIIIllllII;
                            llIIIlIIIllllII.attackCrystal(llIIIlllIIIllII, llIIIlllIIIlIll);
                        });
                    }
                    llIIIllIlllllIl.breakDelayLeft = llIIIllIlllllIl.breakDelay.get();
                    return;
                }
                class_1511 llIIIlllIIIIlII = llIIIllIlllllll.next();
                if (!llIIIllIlllllIl.shouldBreak(llIIIlllIIIIlII) || llIIIllIlllllIl.isToRemove(llIIIlllIIIIlII)) continue;
                double llIIIlllIIIIllI = 0.0;
                boolean llIIIlllIIIIIll = false;
                Iterator<class_1309> llIIIlllIIIIIlI = llIIIllIlllllIl.targets.iterator();
                while (true) {
                    double llIIIlllIIIIlll;
                    double llIIIlllIIIlIII;
                    if (!llIIIlllIIIIIlI.hasNext()) {
                        if (llIIIllIlllllIl.targetMode.get() != TargetMode.TotalDamage || !(llIIIlllIIIIllI > llIIIlllIIIIIIl)) break;
                        llIIIlllIIIIIIl = llIIIlllIIIIllI;
                        llIIIlllIIIIIII = llIIIlllIIIIlII;
                        break;
                    }
                    class_1309 llIIIlllIIIlIIl = llIIIlllIIIIIlI.next();
                    if (llIIIlllIIIlIIl.field_6235 - (int)llIIIllIlllllIl.ticksBehind > 0 && llIIIllIlllllIl.crystalSave.get().booleanValue() || llIIIlllIIIIlII.method_24515().method_10264() - 1 > llIIIlllIIIlIIl.method_24515().method_10264() || llIIIlllIIIIlII.method_5739((class_1297)llIIIlllIIIlIIl) > 9.0f) continue;
                    boolean llIIIlllIIIlIlI = llIIIllIlllllIl.shouldFacePlace((class_1297)llIIIlllIIIlIIl);
                    double d = llIIIlllIIIlIII = llIIIlllIIIlIlI ? 2.5 : llIIIllIlllllIl.minBreakDamage.get();
                    if ((llIIIllIlllllIl.pauseMode.get() == Type.Break || llIIIllIlllllIl.pauseMode.get() == Type.Both) && llIIIlllIIIlIlI && (Objects.requireNonNull(llIIIllIlllllIl.mc.field_1724).method_6115() && ((llIIIllIlllllIl.facePlacePause.get() == PauseMode.All || llIIIllIlllllIl.facePlacePause.get() == PauseMode.EatAndDrink || llIIIllIlllllIl.facePlacePause.get() == PauseMode.EatAndMine || llIIIllIlllllIl.facePlacePause.get() == PauseMode.Eat) && (llIIIllIlllllIl.mc.field_1724.method_6047().method_7909().method_19263() || llIIIllIlllllIl.mc.field_1724.method_6079().method_7909().method_19263()) || (llIIIllIlllllIl.facePlacePause.get() == PauseMode.All || llIIIllIlllllIl.facePlacePause.get() == PauseMode.EatAndDrink || llIIIllIlllllIl.facePlacePause.get() == PauseMode.DrinkAndMine || llIIIllIlllllIl.facePlacePause.get() == PauseMode.Drink) && (llIIIllIlllllIl.mc.field_1724.method_6047().method_7909() instanceof class_1812 || llIIIllIlllllIl.mc.field_1724.method_6079().method_7909() instanceof class_1812)) || Objects.requireNonNull(llIIIllIlllllIl.mc.field_1761).method_2923() && (llIIIllIlllllIl.facePlacePause.get() == PauseMode.All || llIIIllIlllllIl.facePlacePause.get() == PauseMode.EatAndMine || llIIIllIlllllIl.facePlacePause.get() == PauseMode.DrinkAndMine || llIIIllIlllllIl.facePlacePause.get() == PauseMode.Mine)) || (llIIIlllIIIIlll = DamageCalcUtils.crystalDamage(llIIIlllIIIlIIl, llIIIlllIIIIlII.method_19538())) < llIIIlllIIIlIII) continue;
                    if (llIIIllIlllllIl.targetMode.get() == TargetMode.TotalDamage) {
                        llIIIlllIIIIllI += llIIIlllIIIIlll;
                        continue;
                    }
                    if (llIIIlllIIIlIIl.field_6235 - (int)llIIIllIlllllIl.ticksBehind > 0 && BlockUtils.isFucked(llIIIlllIIIlIIl) && llIIIllIlllllIl.surroundHold.get().booleanValue()) continue;
                    if (llIIIlllIIIIlll > llIIIlllIIIIIIl) {
                        llIIIlllIIIIIIl = llIIIlllIIIIlll;
                        llIIIlllIIIIIII = llIIIlllIIIIlII;
                    }
                    if (!(llIIIlllIIIIlll > (double)(llIIIlllIIIlIIl.method_6032() + llIIIlllIIIlIIl.method_6067()) + 0.5) || !llIIIllIlllllIl.lethal.get().booleanValue()) continue;
                    llIIIlllIIIIIll = true;
                }
                if (!llIIIllIlllllIl.isSafeBreak((class_1309)llIIIllIlllllIl.mc.field_1724, llIIIlllIIIIlII.method_19538()) && !llIIIlllIIIIIll && (!llIIIllIlllllIl.hurtIgnoreSelfDmg.get().booleanValue() || Objects.requireNonNull(llIIIllIlllllIl.mc.field_1724).field_6235 - (int)llIIIllIlllllIl.ticksBehind <= 0) || llIIIllIlllllIl.antiFriendPop.get() != Type.Both && llIIIllIlllllIl.antiFriendPop.get() != Type.Break) continue;
                llIIIlllIIIIIlI = llIIIllIlllllIl.friends.iterator();
                do {
                    if (!llIIIlllIIIIIlI.hasNext()) continue block0;
                    llIIIlllIIIIlIl = (class_1657)llIIIlllIIIIIlI.next();
                } while (!llIIIlllIIIIlII.method_19538().method_24802((class_2374)llIIIlllIIIIlIl.method_19538(), 9.0) || llIIIllIlllllIl.isSafeBreak((class_1309)llIIIlllIIIIlIl, llIIIlllIIIIlII.method_19538()) || llIIIlllIIIIIll);
            }
        }
    }

    @EventHandler(priority=100)
    private void onTick(SendMovementPacketsEvent.Pre llIIIllIlIlIIII) {
        CrystalAuraTwo llIIIllIlIlIIIl;
        if (llIIIllIlIlIIIl.surround.get().booleanValue()) {
            assert (llIIIllIlIlIIIl.mc.field_1724 != null);
            if (BlockUtils.obbySurrounded((class_1309)llIIIllIlIlIIIl.mc.field_1724) && !Modules.get().isActive(Surround.class) && llIIIllIlIlIIIl.mc.field_1724.method_24828()) {
                Modules.get().get(Surround.class).toggle();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean validSurroundBreak(class_1309 llIIIlIllllIIIl, int llIIIlIllllIIII, int llIIIlIlllIIlll, boolean llIIIlIlllIlllI) {
        CrystalAuraTwo llIIIlIlllIlIlI;
        class_243 llIIIlIlllIllIl = llIIIlIllllIIIl.method_19538();
        class_2338 llIIIlIlllIllII = llIIIlIllllIIIl.method_24515();
        class_243 llIIIlIlllIlIll = new class_243((double)llIIIlIlllIllII.method_10263(), (double)llIIIlIlllIllII.method_10264(), (double)llIIIlIlllIllII.method_10260());
        if (!llIIIlIlllIlIlI.isValid(llIIIlIlllIllII.method_10069(llIIIlIllllIIII, -1, llIIIlIlllIIlll), llIIIlIlllIlllI)) {
            return false;
        }
        assert (llIIIlIlllIlIlI.mc.field_1687 != null);
        if (llIIIlIlllIlIlI.mc.field_1687.method_8320(llIIIlIlllIllII.method_10069(llIIIlIllllIIII / 2, 0, llIIIlIlllIIlll / 2)).method_26204() == class_2246.field_9987) return false;
        if (!llIIIlIlllIlIlI.isSafePlace((class_1309)llIIIlIlllIlIlI.mc.field_1724, llIIIlIlllIlIll.method_1031((double)llIIIlIllllIIII, 0.0, (double)llIIIlIlllIIlll))) return false;
        class_2382 class_23822 = new class_2382(llIIIlIlllIllII.method_10263() + llIIIlIllllIIII, llIIIlIlllIllII.method_10264() - 1, llIIIlIlllIllII.method_10260() + llIIIlIlllIIlll);
        if (!(Math.sqrt(llIIIlIlllIlIlI.playerBlockPos.method_10262(class_23822)) < llIIIlIlllIlIlI.placeRange.get())) return false;
        if (llIIIlIlllIlIlI.mc.field_1687.method_17742(new class_3959(llIIIlIlllIllIl, llIIIlIlllIllIl.method_1031((double)llIIIlIllllIIII, 0.0, (double)llIIIlIlllIIlll), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)llIIIlIllllIIIl)).method_17783() == class_239.class_240.field_1333) return false;
        return true;
    }

    private void getEntities() {
        CrystalAuraTwo llIIIllIlIIlIlI;
        assert (llIIIllIlIIlIlI.mc.field_1687 != null);
        Iterator llIIIllIlIIlIIl = llIIIllIlIIlIlI.mc.field_1687.method_18112().iterator();
        while (llIIIllIlIIlIIl.hasNext()) {
            class_1297 llIIIllIlIIlIll = (class_1297)llIIIllIlIIlIIl.next();
            if (!llIIIllIlIIlIll.method_5805() || !llIIIllIlIIlIll.method_24516((class_1297)llIIIllIlIIlIlI.mc.field_1724, llIIIllIlIIlIlI.targetRange.get().doubleValue()) || llIIIllIlIIlIll == llIIIllIlIIlIlI.mc.field_1724) continue;
            if (llIIIllIlIIlIll instanceof class_1657 && !Friends.get().shouldAttack((class_1657)llIIIllIlIIlIll)) {
                llIIIllIlIIlIlI.friends.add((class_1657)llIIIllIlIIlIll);
                continue;
            }
            if (llIIIllIlIIlIlI.entities.get().getBoolean((Object)llIIIllIlIIlIll.method_5864())) {
                if (llIIIllIlIIlIlI.targets.size() >= llIIIllIlIIlIlI.numberOfDamages.get()) continue;
                llIIIllIlIIlIlI.targets.add((class_1309)llIIIllIlIIlIll);
                continue;
            }
            if (!(llIIIllIlIIlIll instanceof class_1511)) continue;
            llIIIllIlIIlIlI.crystals.add((class_1511)llIIIllIlIIlIll);
        }
        return;
    }

    private void doSwitch() {
        CrystalAuraTwo llIIIllIlIIIIIl;
        assert (llIIIllIlIIIIIl.mc.field_1724 != null);
        if (llIIIllIlIIIIIl.mc.field_1724.method_6047().method_7909() != class_1802.field_8301 && llIIIllIlIIIIIl.mc.field_1724.method_6079().method_7909() != class_1802.field_8301) {
            int llIIIllIlIIIIll = InvUtils.findInHotbar((class_1792[])new class_1792[]{class_1802.field_8301}).slot;
            if (llIIIllIlIIIIll != -1 && llIIIllIlIIIIll < 9) {
                llIIIllIlIIIIIl.preSlot = llIIIllIlIIIIIl.mc.field_1724.field_7514.field_7545;
                llIIIllIlIIIIIl.mc.field_1724.field_7514.field_7545 = llIIIllIlIIIIll;
                llIIIllIlIIIIIl.switchDelayLeft = llIIIllIlIIIIIl.switchDelay.get();
            }
        }
    }

    @Override
    public void onDeactivate() {
        CrystalAuraTwo llIIIllllIllIII;
        assert (llIIIllllIllIII.mc.field_1724 != null);
        if (llIIIllllIllIII.switchBack.get().booleanValue() && llIIIllllIllIII.preSlot != -1 && llIIIllllIllIII.switchDelayLeft <= 0) {
            llIIIllllIllIII.mc.field_1724.field_7514.field_7545 = llIIIllllIllIII.preSlot;
            llIIIllllIllIII.switchDelayLeft = llIIIllllIllIII.switchDelay.get();
        }
        if (!llIIIllllIllIII.targets.isEmpty() && llIIIllllIllIII.resetRotations.get().booleanValue() && (llIIIllllIllIII.rotationMode.get() == RotationMode.Both || llIIIllllIllIII.rotationMode.get() == RotationMode.Place || llIIIllllIllIII.rotationMode.get() == RotationMode.Break)) {
            Rotations.rotate(llIIIllllIllIII.mc.field_1724.field_6031, llIIIllllIllIII.mc.field_1724.field_5965);
        }
    }

    public class_1268 getHand() {
        CrystalAuraTwo llIIIlIlIlIIIII;
        assert (llIIIlIlIlIIIII.mc.field_1724 != null);
        class_1268 llIIIlIlIIlllll = class_1268.field_5808;
        if (llIIIlIlIlIIIII.mc.field_1724.method_6047().method_7909() != class_1802.field_8301 && llIIIlIlIlIIIII.mc.field_1724.method_6079().method_7909() == class_1802.field_8301) {
            llIIIlIlIIlllll = class_1268.field_5810;
        }
        return llIIIlIlIIlllll;
    }

    @Override
    public String getInfoString() {
        CrystalAuraTwo llIIIlIlIIIIIlI;
        if (!llIIIlIlIIIIIlI.targets.isEmpty()) {
            Optional<class_1309> llIIIlIlIIIIlIl = Streams.stream(llIIIlIlIIIIIlI.targets).min(Comparator.comparingDouble(llIIIlIIlIIlllI -> {
                CrystalAuraTwo llIIIlIIlIIllIl;
                return llIIIlIIlIIlllI.method_5739((class_1297)llIIIlIIlIIllIl.mc.field_1724);
            }));
            class_1309 llIIIlIlIIIIlII = llIIIlIlIIIIlIl.get();
            return llIIIlIlIIIIlII instanceof class_1657 ? llIIIlIlIIIIlII.method_5820() : llIIIlIlIIIIlII.method_5864().method_5897().getString();
        }
        return null;
    }

    private boolean isToRemove(class_1511 llIIIllllIIIlII) {
        int llIIIllllIIIIlI;
        CrystalAuraTwo llIIIllllIIIIIl;
        IntIterator llIIIllllIIIIll = llIIIllllIIIIIl.entitiesToRemove.iterator();
        do {
            if (!llIIIllllIIIIll.hasNext()) {
                return false;
            }
            llIIIllllIIIIlI = llIIIllllIIIIll.next();
        } while (llIIIllllIIIlII != Objects.requireNonNull(llIIIllllIIIIIl.mc.field_1687).method_8469(llIIIllllIIIIlI));
        return true;
    }

    @EventHandler
    private void onRender(RenderEvent llIIIlIlIIlIlll) {
        CrystalAuraTwo llIIIlIlIIlIlIl;
        for (RenderBlock llIIIlIlIIllIIl : llIIIlIlIIlIlIl.renderBlocks) {
            llIIIlIlIIllIIl.render3D();
        }
    }

    public CrystalAuraTwo() {
        super(Categories.BedTrap, "crystal-aura+", "Automatically places and breaks crystals to damage other players.");
        CrystalAuraTwo llIIIlllllIIlIl;
        llIIIlllllIIlIl.box = new class_238(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        llIIIlllllIIlIl.sgPlace = llIIIlllllIIlIl.settings.createGroup("Place");
        llIIIlllllIIlIl.sgBreak = llIIIlllllIIlIl.settings.createGroup("Break");
        llIIIlllllIIlIl.sgMisc = llIIIlllllIIlIl.settings.createGroup("Misc");
        llIIIlllllIIlIl.sgTarget = llIIIlllllIIlIl.settings.createGroup("Target");
        llIIIlllllIIlIl.sgSupport = llIIIlllllIIlIl.settings.createGroup("Support");
        llIIIlllllIIlIl.sgPause = llIIIlllllIIlIl.settings.createGroup("Pause");
        llIIIlllllIIlIl.sgSwitch = llIIIlllllIIlIl.settings.createGroup("Switch");
        llIIIlllllIIlIl.sgRotations = llIIIlllllIIlIl.settings.createGroup("Rotations");
        llIIIlllllIIlIl.sgExperimental = llIIIlllllIIlIl.settings.createGroup("Experimental");
        llIIIlllllIIlIl.sgRender = llIIIlllllIIlIl.settings.createGroup("Render");
        llIIIlllllIIlIl.placeMode = llIIIlllllIIlIl.sgPlace.add(new EnumSetting.Builder().name("place-mode").description("The placement mode for crystals.").defaultValue(Mode.Safe).build());
        llIIIlllllIIlIl.placeDelay = llIIIlllllIIlIl.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The amount of delay in ticks before placing.").defaultValue(1).min(0).sliderMax(10).build());
        llIIIlllllIIlIl.placeRange = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("The radius in which crystals can be placed in.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        llIIIlllllIIlIl.placeWallsRange = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("walls-range").description("The radius in which crystals can be placed through walls.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        llIIIlllllIIlIl.minPlaceDamage = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage the crystal will place.").defaultValue(6.0).build());
        llIIIlllllIIlIl.maxPlaceDamage = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("max-damage").description("The maximum self damage the crystal will place.").defaultValue(2.0).build());
        llIIIlllllIIlIl.facePlace = llIIIlllllIIlIl.sgPlace.add(new BoolSetting.Builder().name("face-place").description("Will face-place when target is below a certain health or armor durability threshold.").defaultValue(true).build());
        llIIIlllllIIlIl.facePlaceHealth = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("face-place-health").description("The health required to face-place.").defaultValue(8.0).min(1.0).sliderMin(1.0).sliderMax(36.0).build());
        llIIIlllllIIlIl.facePlaceDurability = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("face-place-durability").description("The durability threshold to be able to face-place.").defaultValue(2.0).min(1.0).sliderMin(1.0).sliderMax(100.0).max(100.0).build());
        llIIIlllllIIlIl.facePlaceSelf = llIIIlllllIIlIl.sgPlace.add(new BoolSetting.Builder().name("face-place-self").description("Whether to faceplace when you are in the same hole as your target.").defaultValue(true).build());
        llIIIlllllIIlIl.torque = llIIIlllllIIlIl.sgPlace.add(new DoubleSetting.Builder().name("torque").description("Defines how lethal the placements are; With 0 being ultra careful and 1 completely ignoring self damage.").defaultValue(0.0).min(0.0).sliderMax(1.0).max(1.0).build());
        llIIIlllllIIlIl.surroundHold = llIIIlllllIIlIl.sgPlace.add(new BoolSetting.Builder().name("surround-hold").description("Places a crystal next to a player so they cannot use Surround.").defaultValue(false).build());
        llIIIlllllIIlIl.oldPlace = llIIIlllllIIlIl.sgPlace.add(new BoolSetting.Builder().name("1.12-place").description("Won't place in 1x1x1 holes.").defaultValue(false).build());
        llIIIlllllIIlIl.breakMode = llIIIlllllIIlIl.sgBreak.add(new EnumSetting.Builder().name("break-mode").description("The type of break mode for crystals.").defaultValue(Mode.Safe).build());
        llIIIlllllIIlIl.breakDelay = llIIIlllllIIlIl.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The amount of delay in ticks before breaking.").defaultValue(1).min(0).sliderMax(10).build());
        llIIIlllllIIlIl.minBreakDamage = llIIIlllllIIlIl.sgBreak.add(new DoubleSetting.Builder().name("min-damage").description("The minimum damage for a crystal to get broken.").defaultValue(4.5).min(0.0).sliderMax(36.0).build());
        llIIIlllllIIlIl.maxBreakDamage = llIIIlllllIIlIl.sgBreak.add(new DoubleSetting.Builder().name("max-self-damage").description("The maximum self-damage allowed.").defaultValue(3.0).sliderMax(36.0).build());
        llIIIlllllIIlIl.breakRange = llIIIlllllIIlIl.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("The maximum range that crystals can be to be broken.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        llIIIlllllIIlIl.breakWallsRange = llIIIlllllIIlIl.sgBreak.add(new DoubleSetting.Builder().name("walls-range").description("The maximum range that crystals can be to be broken through walls.").defaultValue(5.0).min(0.0).sliderMax(6.0).build());
        llIIIlllllIIlIl.onlyOwn = llIIIlllllIIlIl.sgBreak.add(new BoolSetting.Builder().name("only-own").description("Only destroys crystals placed by yourself.").defaultValue(false).build());
        llIIIlllllIIlIl.lethal = llIIIlllllIIlIl.sgBreak.add(new BoolSetting.Builder().name("lethal-break").description("Ignores safety settings if it can pop the target.").defaultValue(false).build());
        llIIIlllllIIlIl.breakAttempts = llIIIlllllIIlIl.sgBreak.add(new IntSetting.Builder().name("break-attempts").description("How many times to hit a crystal before stopping to target it.").defaultValue(2).sliderMin(1).sliderMax(5).build());
        llIIIlllllIIlIl.removeCrystals = llIIIlllllIIlIl.sgBreak.add(new EnumSetting.Builder().name("canceller").description("Hitcanceller is the fastest but might cause desync on strict anticheats.").defaultValue(Canceller.NoDesync).build());
        llIIIlllllIIlIl.rayTrace = llIIIlllllIIlIl.sgMisc.add(new EnumSetting.Builder().name("ray-trace").description("Wont place / break through walls when on.").defaultValue(Type.None).build());
        llIIIlllllIIlIl.antiFriendPop = llIIIlllllIIlIl.sgMisc.add(new EnumSetting.Builder().name("anti-friend-pop").description("Avoids popping your friends.").defaultValue(Type.Both).build());
        llIIIlllllIIlIl.crystalSave = llIIIlllllIIlIl.sgMisc.add(new BoolSetting.Builder().name("crystal-saver").description("Only targets players that can get hurt.").defaultValue(false).build());
        llIIIlllllIIlIl.orderLogic = llIIIlllllIIlIl.sgMisc.add(new EnumSetting.Builder().name("Logic").description("What to do first.").defaultValue(Logic.BreakPlace).build());
        llIIIlllllIIlIl.antiWeakness = llIIIlllllIIlIl.sgMisc.add(new BoolSetting.Builder().name("anti-weakness").description("Switches to tools to break crystals instead of your fist.").defaultValue(true).build());
        llIIIlllllIIlIl.surround = llIIIlllllIIlIl.sgMisc.add(new BoolSetting.Builder().name("auto-surround").description("Automatically turns on surround when in an obsidian hole.").defaultValue(false).build());
        llIIIlllllIIlIl.entities = llIIIlllllIIlIl.sgTarget.add(new EntityTypeListSetting.Builder().name("entities").description("The entities to attack.").defaultValue((Object2BooleanMap<class_1299<?>>)Utils.asObject2BooleanOpenHashMap(new class_1299[]{class_1299.field_6097})).onlyAttackable().build());
        llIIIlllllIIlIl.targetRange = llIIIlllllIIlIl.sgTarget.add(new DoubleSetting.Builder().name("target-range").description("The maximum range the entity can be to be targeted.").defaultValue(10.0).min(0.0).sliderMax(15.0).build());
        llIIIlllllIIlIl.predict = llIIIlllllIIlIl.sgTarget.add(new BoolSetting.Builder().name("predict").description("Predicts target movement.").defaultValue(false).build());
        llIIIlllllIIlIl.numberOfDamages = llIIIlllllIIlIl.sgTarget.add(new IntSetting.Builder().name("number-of-targets").description("Maximum number of targets to perform calculations on. Might lag your game when set too high.").defaultValue(3).sliderMin(1).sliderMax(5).build());
        llIIIlllllIIlIl.targetMode = llIIIlllllIIlIl.sgTarget.add(new EnumSetting.Builder().name("target-mode").description("The way you target multiple targets.").defaultValue(TargetMode.SingleDamage).build());
        llIIIlllllIIlIl.support = llIIIlllllIIlIl.sgSupport.add(new BoolSetting.Builder().name("support").description("Places a block in the air and crystals on it. Helps with killing players that are flying.").defaultValue(false).build());
        llIIIlllllIIlIl.supportDelay = llIIIlllllIIlIl.sgSupport.add(new IntSetting.Builder().name("support-delay").description("The delay between support blocks being placed.").defaultValue(5).min(0).sliderMax(10).build());
        llIIIlllllIIlIl.supportDelayAfter = llIIIlllllIIlIl.sgSupport.add(new IntSetting.Builder().name("delay-after").description("The delay between having placed the support block and the next crystal.").defaultValue(0).min(0).sliderMax(5).build());
        llIIIlllllIIlIl.supportBackup = llIIIlllllIIlIl.sgSupport.add(new BoolSetting.Builder().name("support-backup").description("Makes it so support only works if there are no other options.").defaultValue(true).build());
        llIIIlllllIIlIl.supportAirPlace = llIIIlllllIIlIl.sgSupport.add(new BoolSetting.Builder().name("airplace").description("Whether to airplace the support block or not.").defaultValue(true).build());
        llIIIlllllIIlIl.pauseMode = llIIIlllllIIlIl.sgPause.add(new EnumSetting.Builder().name("pause-mode").description("What to pause.").defaultValue(Type.Place).build());
        llIIIlllllIIlIl.pauseOnEat = llIIIlllllIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses Crystal Aura while eating.").defaultValue(false).build());
        llIIIlllllIIlIl.pauseOnDrink = llIIIlllllIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses Crystal Aura while drinking a potion.").defaultValue(false).build());
        llIIIlllllIIlIl.pauseOnMine = llIIIlllllIIlIl.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses Crystal Aura while mining blocks.").defaultValue(false).build());
        llIIIlllllIIlIl.facePlacePause = llIIIlllllIIlIl.sgPause.add(new EnumSetting.Builder().name("pause-face-placing").description("When to interrupt face-placing.").defaultValue(PauseMode.None).build());
        llIIIlllllIIlIl.switchMode = llIIIlllllIIlIl.sgSwitch.add(new EnumSetting.Builder().name("switch-mode").description("How to switch items.").defaultValue(SwitchMode.Auto).build());
        llIIIlllllIIlIl.switchBack = llIIIlllllIIlIl.sgSwitch.add(new BoolSetting.Builder().name("switch-back").description("Switches back to your previous slot when disabling Crystal Aura.").defaultValue(false).build());
        llIIIlllllIIlIl.switchDelay = llIIIlllllIIlIl.sgSwitch.add(new IntSetting.Builder().name("switch-delay").description("The amount of delay in ticks before switching slots again.").defaultValue(0).min(0).sliderMax(5).build());
        llIIIlllllIIlIl.rotationMode = llIIIlllllIIlIl.sgRotations.add(new EnumSetting.Builder().name("rotation-mode").description("The method of rotating when using Crystal Aura.").defaultValue(RotationMode.None).build());
        llIIIlllllIIlIl.strictLook = llIIIlllllIIlIl.sgRotations.add(new BoolSetting.Builder().name("strict-look").description("Looks at exactly where you're placing.").defaultValue(false).build());
        llIIIlllllIIlIl.resetRotations = llIIIlllllIIlIl.sgRotations.add(new BoolSetting.Builder().name("reset-rotations").description("Resets rotations once Crystal Aura is disabled.").defaultValue(false).build());
        llIIIlllllIIlIl.hurtIgnoreSelfDmg = llIIIlllllIIlIl.sgExperimental.add(new BoolSetting.Builder().name("hurt-time").description("Will place in suicide locations if you cannot be damaged. WARNING: VERY EXPERIMENTAL!").defaultValue(false).build());
        llIIIlllllIIlIl.swing = llIIIlllllIIlIl.sgRender.add(new BoolSetting.Builder().name("swing").description("Renders your swing client-side.").defaultValue(true).build());
        llIIIlllllIIlIl.render = llIIIlllllIIlIl.sgRender.add(new BoolSetting.Builder().name("render").description("Renders the block under where it is placing a crystal.").defaultValue(true).build());
        llIIIlllllIIlIl.shapeMode = llIIIlllllIIlIl.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        llIIIlllllIIlIl.sideColor = llIIIlllllIIlIl.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color.").defaultValue(new SettingColor(255, 255, 255, 75)).build());
        llIIIlllllIIlIl.lineColor = llIIIlllllIIlIl.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        llIIIlllllIIlIl.randomColor = llIIIlllllIIlIl.sgRender.add(new BoolSetting.Builder().name("random-color").description("Generate random color for each render.").defaultValue(false).build());
        llIIIlllllIIlIl.fadeOut = llIIIlllllIIlIl.sgRender.add(new BoolSetting.Builder().name("fade-out").description("Fades out the render.").defaultValue(true).build());
        llIIIlllllIIlIl.renderDamage = llIIIlllllIIlIl.sgRender.add(new BoolSetting.Builder().name("render-damage").description("Renders the damage of the crystal where it is placing.").defaultValue(true).build());
        llIIIlllllIIlIl.roundDamage = llIIIlllllIIlIl.sgRender.add(new IntSetting.Builder().name("round-damage").description("Round damage to x decimal places.").defaultValue(2).min(0).max(3).sliderMax(3).build());
        llIIIlllllIIlIl.damageScale = llIIIlllllIIlIl.sgRender.add(new DoubleSetting.Builder().name("damage-scale").description("The scale of the damage text.").defaultValue(1.4).min(0.0).sliderMax(5.0).build());
        llIIIlllllIIlIl.damageColor = llIIIlllllIIlIl.sgRender.add(new ColorSetting.Builder().name("damage-color").description("The color of the damage text.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        llIIIlllllIIlIl.renderTimer = llIIIlllllIIlIl.sgRender.add(new IntSetting.Builder().name("timer").description("The amount of time between changing the block render.").defaultValue(0).min(0).sliderMax(10).build());
        llIIIlllllIIlIl.supportSlot = 0;
        llIIIlllllIIlIl.targets = new ArrayList<class_1309>();
        llIIIlllllIIlIl.friends = new ArrayList<class_1657>();
        llIIIlllllIIlIl.placedCrystals = new ArrayList<class_1511>();
        llIIIlllllIIlIl.crystals = new ArrayList<class_1511>();
        llIIIlllllIIlIl.attemptedCrystals = new ArrayList<class_1511>();
        llIIIlllllIIlIl.crystalList = new ArrayList<Double>();
        llIIIlllllIIlIl.entitiesToRemove = new IntOpenHashSet();
        llIIIlllllIIlIl.renderBlocks = new ArrayList<RenderBlock>();
        llIIIlllllIIlIl.attemptedBreaks = new Int2IntOpenHashMap();
        llIIIlllllIIlIl.renderBlockPool = new Pool<Object>(() -> {
            CrystalAuraTwo llIIIIllIllIllI;
            return llIIIIllIllIllI.new RenderBlock();
        });
        llIIIlllllIIlIl.crystalMap = new HashMap<class_1511, List<Double>>();
        llIIIlllllIIlIl.doubleLegOffsetList = new class_243[]{new class_243(-1.0, 0.0, 0.0), new class_243(1.0, 0.0, 0.0), new class_243(0.0, 0.0, -1.0), new class_243(0.0, 0.0, 1.0), new class_243(-2.0, 0.0, 0.0), new class_243(2.0, 0.0, 0.0), new class_243(0.0, 0.0, -2.0), new class_243(0.0, 0.0, 2.0)};
    }

    private Stream<class_1297> getCrystalStream() {
        CrystalAuraTwo llIIIlIIlllllIl;
        return Streams.stream((Iterable)llIIIlIIlllllIl.mc.field_1687.method_18112()).filter(llIIIlIIlIlIIll -> llIIIlIIlIlIIll instanceof class_1511).filter(llIIIlIIlIlIlll -> {
            CrystalAuraTwo llIIIlIIlIllIII;
            return (double)llIIIlIIlIlIlll.method_5739((class_1297)llIIIlIIlIllIII.mc.field_1724) <= llIIIlIIlIllIII.breakRange.get();
        }).filter(class_1297::method_5805).filter(llIIIlIIlIllIll -> {
            CrystalAuraTwo llIIIlIIlIllllI;
            return llIIIlIIlIllllI.shouldBreak((class_1511)llIIIlIIlIllIll);
        });
    }

    private boolean shouldBreak(class_1511 llIIIllIllIlIll) {
        CrystalAuraTwo llIIIllIllIlllI;
        assert (llIIIllIllIlllI.mc.field_1724 != null);
        if (!llIIIllIllIlIll.method_19538().method_24802((class_2374)llIIIllIllIlllI.mc.field_1724.method_19538().method_1019(llIIIllIllIlllI.eyeHeight), llIIIllIllIlllI.breakRange.get().doubleValue())) {
            return false;
        }
        if (llIIIllIllIlllI.attemptedBreaks.get(llIIIllIllIlIll.method_5628()) > Math.max(1, llIIIllIllIlllI.breakAttempts.get())) {
            return false;
        }
        if (!llIIIllIllIlllI.mc.field_1724.method_6057((class_1297)llIIIllIllIlIll)) {
            if (llIIIllIllIlllI.rayTrace.get() == Type.Break || llIIIllIllIlllI.rayTrace.get() == Type.Both) {
                return false;
            }
            if (!llIIIllIllIlIll.method_19538().method_24802((class_2374)llIIIllIllIlllI.mc.field_1724.method_19538().method_1019(llIIIllIllIlllI.eyeHeight), llIIIllIllIlllI.breakWallsRange.get().doubleValue())) {
                return false;
            }
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private class_243 findOpen(class_1309 llIIIllIIIllIll, boolean llIIIllIIIllIlI) {
        block6: {
            block7: {
                block5: {
                    llIIIllIIlIIIII = 0;
                    llIIIllIIIlllll = 0;
                    llIIIllIIIllllI = llIIIllIIIllIll.method_24515();
                    llIIIllIIIlllIl = llIIIllIIIllIll.method_19538();
                    if (!llIIIllIIIlllII.isValid(llIIIllIIIllllI.method_10069(1, -1, 0), llIIIllIIIllIlI) || !llIIIllIIIlllII.isSafePlace((class_1309)llIIIllIIIlllII.mc.field_1724, llIIIllIIIlllIl.method_1031(1.0, -1.0, 0.0))) break block5;
                    v0 = new class_2382(llIIIllIIIllllI.method_10263() + 1, llIIIllIIIllllI.method_10264() - 1, llIIIllIIIllllI.method_10260());
                    if (!(Math.sqrt(llIIIllIIIlllII.playerBlockPos.method_10262(v0)) < llIIIllIIIlllII.placeRange.get())) break block5;
                    llIIIllIIlIIIII = 1;
                    break block6;
                }
                if (!llIIIllIIIlllII.isValid(llIIIllIIIllllI.method_10069(-1, -1, 0), llIIIllIIIllIlI) || !llIIIllIIIlllII.isSafePlace((class_1309)llIIIllIIIlllII.mc.field_1724, llIIIllIIIlllIl.method_1031(-1.0, -1.0, 0.0))) break block7;
                v1 = new class_2382(llIIIllIIIllllI.method_10263() - 1, llIIIllIIIllllI.method_10264() - 1, llIIIllIIIllllI.method_10260());
                if (!(Math.sqrt(llIIIllIIIlllII.playerBlockPos.method_10262(v1)) < llIIIllIIIlllII.placeRange.get())) break block7;
                llIIIllIIlIIIII = -1;
                break block6;
            }
            if (!llIIIllIIIlllII.isValid(llIIIllIIIllllI.method_10069(0, -1, 1), llIIIllIIIllIlI) || !llIIIllIIIlllII.isSafePlace((class_1309)llIIIllIIIlllII.mc.field_1724, llIIIllIIIlllIl.method_1031(0.0, -1.0, 1.0))) ** GOTO lbl-1000
            v2 = new class_2382(llIIIllIIIllllI.method_10263(), llIIIllIIIllllI.method_10264() - 1, llIIIllIIIllllI.method_10260() + 1);
            if (Math.sqrt(llIIIllIIIlllII.playerBlockPos.method_10262(v2)) < llIIIllIIIlllII.placeRange.get()) {
                llIIIllIIIlllll = 1;
            } else if (llIIIllIIIlllII.isValid(llIIIllIIIllllI.method_10069(0, -1, -1), llIIIllIIIllIlI) && llIIIllIIIlllII.isSafePlace((class_1309)llIIIllIIIlllII.mc.field_1724, llIIIllIIIlllIl.method_1031(0.0, -1.0, -1.0))) {
                v3 = new class_2382(llIIIllIIIllllI.method_10263(), llIIIllIIIllllI.method_10264() - 1, llIIIllIIIllllI.method_10260() - 1);
                if (Math.sqrt(llIIIllIIIlllII.playerBlockPos.method_10262(v3)) < llIIIllIIIlllII.placeRange.get()) {
                    llIIIllIIIlllll = -1;
                }
            }
        }
        if (llIIIllIIlIIIII == 0 && llIIIllIIIlllll == 0) {
            return null;
        }
        v4 = new class_243((double)llIIIllIIIllllI.method_10263() + 0.5 + (double)llIIIllIIlIIIII, (double)(llIIIllIIIllllI.method_10264() - 1), (double)llIIIllIIIllllI.method_10260() + 0.5 + (double)llIIIllIIIlllll);
        return v4;
    }

    private void doHeldCrystal(class_243 llIIIllIIllIIll) {
        CrystalAuraTwo llIIIllIIllIlII;
        llIIIllIIllIlII.placeDelayLeft = llIIIllIIllIlII.placeDelay.get();
        class_2338 llIIIllIIllIIlI = new class_2338((int)llIIIllIIllIIll.field_1352, (int)llIIIllIIllIIll.field_1351, (int)llIIIllIIllIIll.field_1350);
        assert (llIIIllIIllIlII.mc.field_1687 != null);
        if (llIIIllIIllIlII.mc.field_1687.method_22347(llIIIllIIllIIlI)) {
            assert (llIIIllIIllIlII.mc.field_1724 != null);
            if (llIIIllIIllIlII.mc.field_1724.field_7514.field_7545 != llIIIllIIllIlII.supportSlot && llIIIllIIllIlII.switchDelayLeft > 0) {
                return;
            }
            minegame159.meteorclient.utils.bedtrap.PlayerUtils.placeBlock(new class_2338(llIIIllIIllIIll), llIIIllIIllIlII.supportSlot, class_1268.field_5808, (boolean)llIIIllIIllIlII.supportAirPlace.get());
            llIIIllIIllIlII.supportDelayLeft = llIIIllIIllIlII.supportDelay.get();
            llIIIllIIllIlII.supportDelayAfterLeft = llIIIllIIllIlII.supportDelayAfter.get();
        }
        if (llIIIllIIllIlII.supportDelayAfterLeft <= 0) {
            class_1511 llIIIllIIllIlll = new class_1511((class_1937)llIIIllIIllIlII.mc.field_1687, llIIIllIIllIIll.field_1352, llIIIllIIllIIll.field_1351 + 1.0, llIIIllIIllIIll.field_1350);
            if (llIIIllIIllIlII.switchMode.get() != SwitchMode.None && llIIIllIIllIlII.switchDelayLeft <= 0) {
                llIIIllIIllIlII.doSwitch();
            }
            llIIIllIIllIlII.attemptedCrystals.add(llIIIllIIllIlll);
            llIIIllIIllIlII.entitiesToRemove.clear();
            class_1268 llIIIllIIllIllI = llIIIllIIllIlII.getHand();
            class_2350 llIIIllIIllIlIl = BlockUtils.rayTraceCheck(llIIIllIIllIIlI, true);
            if (llIIIllIIllIlII.rotationMode.get() != RotationMode.Place && llIIIllIIllIlII.rotationMode.get() != RotationMode.Both) {
                assert (llIIIllIIllIlII.mc.field_1724 != null);
                llIIIllIIllIlII.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(llIIIllIIllIllI, new class_3965(llIIIllIIllIlII.playerPos, llIIIllIIllIlIl, llIIIllIIllIIlI, false)));
            } else {
                float[] llIIIllIIlllIII = PlayerUtils.calculateAngle(llIIIllIIllIlII.strictLook.get() != false ? new class_243((double)llIIIllIIllIIlI.method_10263() + 0.5 + (double)Objects.requireNonNull(llIIIllIIllIlIl).method_10163().method_10263() * 1.0 / 2.0, (double)llIIIllIIllIIlI.method_10264() + 0.5 + (double)llIIIllIIllIlIl.method_10163().method_10264() * 1.0 / 2.0, (double)llIIIllIIllIIlI.method_10260() + 0.5 + (double)llIIIllIIllIlIl.method_10163().method_10260() * 1.0 / 2.0) : llIIIllIIllIIll.method_1031(0.5, 1.0, 0.5));
                Rotations.rotate(llIIIllIIlllIII[0], llIIIllIIlllIII[1], 25, () -> {
                    CrystalAuraTwo llIIIlIIlIIIlll;
                    assert (llIIIlIIlIIIlll.mc.field_1724 != null);
                    llIIIlIIlIIIlll.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(llIIIllIIllIllI, new class_3965(llIIIlIIlIIIlll.playerPos, llIIIllIIllIlIl, llIIIllIIllIIlI, false)));
                });
            }
            if (llIIIllIIllIlII.swing.get().booleanValue()) {
                assert (llIIIllIIllIlII.mc.field_1724 != null);
                llIIIllIIllIlII.mc.field_1724.method_6104(llIIIllIIllIllI);
            } else {
                assert (llIIIllIIllIlII.mc.field_1724 != null);
                llIIIllIIllIlII.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(llIIIllIIllIllI));
            }
        }
    }

    private boolean isValid(class_2338 llIIIlIllllllII, boolean llIIIlIlllllIll) {
        CrystalAuraTwo llIIIlIllllllIl;
        if (!llIIIlIllllllIl.isEmpty(llIIIlIllllllII.method_10069(0, 1, 0))) {
            return false;
        }
        assert (llIIIlIllllllIl.mc.field_1687 != null);
        return llIIIlIllllllIl.mc.field_1687.method_8320(llIIIlIllllllII).method_26204() == class_2246.field_9987 || llIIIlIllllllIl.mc.field_1687.method_8320(llIIIlIllllllII).method_26204() == class_2246.field_10540 || llIIIlIlllllIll && llIIIlIllllllIl.mc.field_1687.method_22347(llIIIlIllllllII) && llIIIlIllllllIl.supportDelayLeft <= 0;
    }

    private boolean isSafeBreak(class_1309 llIIIlIllIlllII, class_243 llIIIlIllIllIll) {
        CrystalAuraTwo llIIIlIllIlllIl;
        if (llIIIlIllIlllIl.breakMode.get() == Mode.Suicide) {
            return true;
        }
        double llIIIlIllIllllI = DamageCalcUtils.crystalDamage(llIIIlIllIlllII, llIIIlIllIllIll);
        return (double)(llIIIlIllIlllII.method_6032() + llIIIlIllIlllII.method_6067()) > llIIIlIllIllllI && llIIIlIllIllllI <= llIIIlIllIlllIl.maxBreakDamage.get();
    }

    private class_243 findOpenSurround(class_1309 llIIIllIIIIlIII, boolean llIIIllIIIIIlll) {
        CrystalAuraTwo llIIIllIIIIlIIl;
        int llIIIllIIIIllII = 0;
        int llIIIllIIIIlIll = 0;
        class_243 llIIIllIIIIlIlI = llIIIllIIIIlIII.method_19538();
        if (llIIIllIIIIlIIl.validSurroundBreak(llIIIllIIIIlIII, 2, 0, llIIIllIIIIIlll) && llIIIllIIIIlIIl.isSafePlace((class_1309)llIIIllIIIIlIIl.mc.field_1724, llIIIllIIIIlIlI.method_1031(2.0, 0.0, 0.0))) {
            llIIIllIIIIllII = 2;
        } else if (llIIIllIIIIlIIl.validSurroundBreak(llIIIllIIIIlIII, -2, 0, llIIIllIIIIIlll) && llIIIllIIIIlIIl.isSafePlace((class_1309)llIIIllIIIIlIIl.mc.field_1724, llIIIllIIIIlIlI.method_1031(-2.0, 0.0, 0.0))) {
            llIIIllIIIIllII = -2;
        } else if (llIIIllIIIIlIIl.validSurroundBreak(llIIIllIIIIlIII, 0, 2, llIIIllIIIIIlll) && llIIIllIIIIlIIl.isSafePlace((class_1309)llIIIllIIIIlIIl.mc.field_1724, llIIIllIIIIlIlI.method_1031(0.0, 0.0, 2.0))) {
            llIIIllIIIIlIll = 2;
        } else if (llIIIllIIIIlIIl.validSurroundBreak(llIIIllIIIIlIII, 0, -2, llIIIllIIIIIlll) && llIIIllIIIIlIIl.isSafePlace((class_1309)llIIIllIIIIlIIl.mc.field_1724, llIIIllIIIIlIlI.method_1031(0.0, 0.0, -2.0))) {
            llIIIllIIIIlIll = -2;
        }
        return llIIIllIIIIllII == 0 && llIIIllIIIIlIll == 0 ? null : new class_243((double)(llIIIllIIIIlIII.method_24515().method_10263() + llIIIllIIIIllII) + 0.5, (double)(llIIIllIIIIlIII.method_24515().method_10264() - 1), (double)(llIIIllIIIIlIII.method_24515().method_10260() + llIIIllIIIIlIll) + 0.5);
    }

    public static enum SwitchMode {
        None,
        Auto;


        private SwitchMode() {
            SwitchMode lllIllIlIllIl;
        }
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


        private PauseMode() {
            PauseMode llllllllllllllllllIIllIlIIlllIlI;
        }
    }

    public static enum Mode {
        Safe,
        Suicide;


        private Mode() {
            Mode lllllllllllllllllIIlIIlIIIllIlll;
        }
    }

    public static enum TargetMode {
        SingleDamage,
        TotalDamage;


        private TargetMode() {
            TargetMode llllllllllllllllllllIIlIIlIlIlll;
        }
    }

    public static enum Logic {
        PlaceBreak,
        BreakPlace;


        private Logic() {
            Logic lllllllllllllllllllllIlIIIIlIlll;
        }
    }

    public static enum Type {
        None,
        Place,
        Break,
        Both;


        private Type() {
            Type llllllllllllllllIlllIIlllllIIllI;
        }
    }

    public static enum RotationMode {
        None,
        Place,
        Break,
        Both;


        private RotationMode() {
            RotationMode llllllllllllllllllllllllIllllllI;
        }
    }

    public static enum Canceller {
        NoDesync,
        HitCanceller;


        private Canceller() {
            Canceller llllllllllllllllllllIIlIlIllIIll;
        }
    }

    private class RenderBlock {
        private /* synthetic */ int damageStartA;
        private /* synthetic */ int lineStartA;
        private /* synthetic */ Color lineColor;
        private /* synthetic */ int z;
        private final /* synthetic */ Color damageColor;
        private /* synthetic */ double damage;
        private /* synthetic */ int timer;
        private /* synthetic */ int y;
        private final /* synthetic */ Color sideColor;
        private /* synthetic */ int sideStartA;
        private /* synthetic */ int x;

        public void set(class_2338 lllIlIlIlIIllII, double lllIlIlIlIIlIII) {
            RenderBlock lllIlIlIlIIlIlI;
            lllIlIlIlIIlIlI.x = lllIlIlIlIIllII.method_10263();
            lllIlIlIlIIlIlI.y = lllIlIlIlIIllII.method_10264();
            lllIlIlIlIIlIlI.z = lllIlIlIlIIllII.method_10260();
            lllIlIlIlIIlIlI.timer = (Integer)lllIlIlIlIIlIlI.CrystalAuraTwo.this.renderTimer.get();
            if (((Boolean)lllIlIlIlIIlIlI.CrystalAuraTwo.this.randomColor.get()).booleanValue()) {
                lllIlIlIlIIlIlI.lineColor = Color.fromHsv(ThreadLocalRandom.current().nextDouble() * 360.0, 1.0, 1.0);
                lllIlIlIlIIlIlI.sideColor.set(lllIlIlIlIIlIlI.lineColor);
                lllIlIlIlIIlIlI.sideColor.a = 75;
            } else {
                lllIlIlIlIIlIlI.lineColor.set((Color)lllIlIlIlIIlIlI.CrystalAuraTwo.this.lineColor.get());
                lllIlIlIlIIlIlI.sideColor.set((Color)lllIlIlIlIIlIlI.CrystalAuraTwo.this.sideColor.get());
            }
            lllIlIlIlIIlIlI.damageColor.set((Color)lllIlIlIlIIlIlI.CrystalAuraTwo.this.damageColor.get());
            lllIlIlIlIIlIlI.lineStartA = lllIlIlIlIIlIlI.lineColor.a;
            lllIlIlIlIIlIlI.sideStartA = lllIlIlIlIIlIlI.sideColor.a;
            lllIlIlIlIIlIlI.damageStartA = lllIlIlIlIIlIlI.damageColor.a;
        }

        public void render2D() {
            RenderBlock lllIlIlIIllIlIl;
            if (((Boolean)lllIlIlIIllIlIl.CrystalAuraTwo.this.renderDamage.get()).booleanValue()) {
                pos.set((double)lllIlIlIIllIlIl.x + 0.5, (double)lllIlIlIIllIlIl.y + 0.5, (double)lllIlIlIIllIlIl.z + 0.5);
                if (NametagUtils.to2D(pos, (Double)lllIlIlIIllIlIl.CrystalAuraTwo.this.damageScale.get())) {
                    NametagUtils.begin(pos);
                    TextRenderer.get().begin(1.0, false, true);
                    String lllIlIlIIlllIII = String.valueOf(Math.round(lllIlIlIIllIlIl.damage));
                    switch ((Integer)lllIlIlIIllIlIl.CrystalAuraTwo.this.roundDamage.get()) {
                        case 0: {
                            lllIlIlIIlllIII = String.valueOf(Math.round(lllIlIlIIllIlIl.damage));
                            break;
                        }
                        case 1: {
                            lllIlIlIIlllIII = String.valueOf((double)Math.round(lllIlIlIIllIlIl.damage * 10.0) / 10.0);
                            break;
                        }
                        case 2: {
                            lllIlIlIIlllIII = String.valueOf((double)Math.round(lllIlIlIIllIlIl.damage * 100.0) / 100.0);
                            break;
                        }
                        case 3: {
                            lllIlIlIIlllIII = String.valueOf((double)Math.round(lllIlIlIIllIlIl.damage * 1000.0) / 1000.0);
                        }
                    }
                    double lllIlIlIIllIlll = TextRenderer.get().getWidth(lllIlIlIIlllIII) / 2.0;
                    TextRenderer.get().render(lllIlIlIIlllIII, -lllIlIlIIllIlll, 0.0, lllIlIlIIllIlIl.damageColor);
                    TextRenderer.get().end();
                    NametagUtils.end();
                }
            }
        }

        public boolean positionEquals(class_2338 lllIlIlIIlIllll) {
            RenderBlock lllIlIlIIlIlllI;
            return lllIlIlIIlIlllI.x == lllIlIlIIlIllll.method_10263() && lllIlIlIIlIlllI.y == lllIlIlIIlIllll.method_10264() && lllIlIlIIlIlllI.z == lllIlIlIIlIllll.method_10260();
        }

        private RenderBlock() {
            RenderBlock lllIlIlIlIlIIlI;
            lllIlIlIlIlIIlI.sideColor = new Color();
            lllIlIlIlIlIIlI.lineColor = new Color();
            lllIlIlIlIlIIlI.damageColor = new Color();
        }

        RenderBlock(Object lllIlIlIIlIlIIl) {
            lllIlIlIIlIlIII();
            RenderBlock lllIlIlIIlIlIII;
        }

        public boolean shouldRemove() {
            int lllIlIlIlIIIIll;
            RenderBlock lllIlIlIlIIIIIl;
            if (lllIlIlIlIIIIIl.timer <= 0) {
                return true;
            }
            if (((Boolean)lllIlIlIlIIIIIl.CrystalAuraTwo.this.fadeOut.get()).booleanValue() && lllIlIlIlIIIIIl.timer <= (lllIlIlIlIIIIll = (Integer)lllIlIlIlIIIIIl.CrystalAuraTwo.this.renderTimer.get() / 2)) {
                Color lllIlIlIlIIIlII = lllIlIlIlIIIIIl.lineColor;
                lllIlIlIlIIIlII.a -= lllIlIlIlIIIIIl.lineStartA / lllIlIlIlIIIIll;
                lllIlIlIlIIIlII = lllIlIlIlIIIIIl.sideColor;
                lllIlIlIlIIIlII.a -= lllIlIlIlIIIIIl.sideStartA / lllIlIlIlIIIIll;
                lllIlIlIlIIIlII = lllIlIlIlIIIIIl.damageColor;
                lllIlIlIlIIIlII.a -= lllIlIlIlIIIIIl.damageStartA / lllIlIlIlIIIIll;
            }
            --lllIlIlIlIIIIIl.timer;
            return false;
        }

        public void render3D() {
            RenderBlock lllIlIlIIllllII;
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, lllIlIlIIllllII.x, lllIlIlIIllllII.y, lllIlIlIIllllII.z, 1.0, lllIlIlIIllllII.sideColor, lllIlIlIIllllII.lineColor, (ShapeMode)((Object)lllIlIlIIllllII.CrystalAuraTwo.this.shapeMode.get()), 0);
        }
    }
}

