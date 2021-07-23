/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_2680;
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
    private final class_243 vec3d;
    private final Setting<Boolean> ignoreTerrain;
    private final Setting<Boolean> antiSuicide;
    private final Setting<SettingColor> sideColor;
    private final Int2IntMap attemptedBreaks;
    private final Setting<Integer> placeDelay;
    private final Setting<Boolean> antiWeakness;
    private final Setting<YawStepMode> yawStepMode;
    private final class_2338.class_2339 breakRenderPos;
    private final class_243 lastRotationPos;
    private double lastYaw;
    private final Setting<Integer> supportDelay;
    private final Setting<Double> breakRange;
    private int switchTimer;
    private int placingTimer;
    private final Setting<Boolean> placement112;
    private double serverYaw;
    private final Setting<Integer> breakAttempts;
    private int lastRotationTimer;
    private boolean isLastRotationPos;
    private int bestTargetTimer;
    private int breakRenderTimer;
    private final Setting<Double> maxDamage;
    private final Setting<Integer> renderTime;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> predictMovement;
    private final Setting<Boolean> facePlace;
    private class_3959 raycastContext;
    private int placeTimer;
    private final Setting<Double> yawSteps;
    private double renderDamage;
    private final class_243 playerEyePos;
    private final Setting<SettingColor> lineColor;
    private final class_2338.class_2339 placingCrystalBlockPos;
    private final Setting<Integer> renderBreakTime;
    private final class_2338.class_2339 blockPos;
    private final SettingGroup sgPause;
    private final Setting<Boolean> renderBreak;
    private final SettingGroup sgRender;
    private final IntSet removed;
    private final Setting<ShapeMode> shapeMode;
    private final Setting<Boolean> renderDamageText;
    private boolean didRotateThisTick;
    private final Setting<Double> facePlaceHealth;
    private final Setting<Boolean> doPlace;
    private final Setting<Boolean> doBreak;
    private final Int2IntMap waitingToExplode;
    private final Setting<AutoSwitchMode> autoSwitch;
    private final Setting<Double> placeRange;
    private final Setting<Double> minDamage;
    private final IntSet placedCrystals;
    private final Setting<Double> placeWallsRange;
    private int breakTimer;
    private final Setting<Double> breakWallsRange;
    private double bestTargetDamage;
    private final Setting<Boolean> smartDelay;
    private final SettingGroup sgFacePlace;
    private final Setting<Boolean> renderSwing;
    private class_1657 bestTarget;
    private final SettingGroup sgBreak;
    private double lastPitch;
    private final Setting<Integer> breakDelay;
    private final Setting<Boolean> render;
    private final Setting<Keybind> forceFacePlace;
    private final Setting<Boolean> facePlaceArmor;
    private final List<class_1657> targets;
    private final Setting<Integer> minimumCrystalAge;
    private final Setting<Boolean> onlyBreakOwn;
    private int renderTimer;
    private final Setting<Boolean> minePause;
    private final Setting<Boolean> eatPause;
    private boolean placing;
    private final Setting<Double> damageTextScale;
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> drinkPause;
    private final Setting<SupportMode> support;
    private final SettingGroup sgPlace;
    private final Setting<Double> facePlaceDurability;
    private final Setting<Integer> switchDelay;
    private final class_238 box;
    private final Vec3 vec3;
    private final class_243 vec3dRayTraceEnd;
    private final Setting<Boolean> fastBreak;
    private final Setting<Double> targetRange;
    private final class_2338.class_2339 renderPos;

    private void attackCrystal(class_1297 class_12972) {
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2824(class_12972, this.mc.field_1724.method_5715()));
        class_1268 class_12682 = InvUtils.findInHotbar(class_1802.field_8301).getHand();
        if (class_12682 == null) {
            class_12682 = class_1268.field_5808;
        }
        if (this.renderSwing.get().booleanValue()) {
            this.mc.field_1724.method_6104(class_12682);
        } else {
            this.mc.method_1562().method_2883((class_2596)new class_2879(class_12682));
        }
    }

    private double getBreakDamage(class_1297 class_12972, boolean bl) {
        boolean bl2;
        if (!(class_12972 instanceof class_1511)) {
            return 0.0;
        }
        if (this.onlyBreakOwn.get().booleanValue() && !this.placedCrystals.contains(class_12972.method_5628())) {
            return 0.0;
        }
        if (this.removed.contains(class_12972.method_5628())) {
            return 0.0;
        }
        if (this.attemptedBreaks.get(class_12972.method_5628()) > this.breakAttempts.get()) {
            return 0.0;
        }
        if (bl && class_12972.field_6012 < this.minimumCrystalAge.get()) {
            return 0.0;
        }
        if (this.isOutOfRange(class_12972.method_19538(), class_12972.method_24515(), false)) {
            return 0.0;
        }
        this.blockPos.method_10101((class_2382)class_12972.method_24515()).method_10100(0, -1, 0);
        double d = DamageUtils.crystalDamage((class_1657)this.mc.field_1724, class_12972.method_19538(), this.predictMovement.get(), this.raycastContext, (class_2338)this.blockPos, this.ignoreTerrain.get());
        if (d > this.maxDamage.get() || this.antiSuicide.get().booleanValue() && d >= (double)EntityUtils.getTotalHealth((class_1657)this.mc.field_1724)) {
            return 0.0;
        }
        double d2 = this.getDamageToTargets(class_12972.method_19538(), (class_2338)this.blockPos, true, false);
        boolean bl3 = bl2 = this.facePlace.get() != false && this.shouldFacePlace(class_12972.method_24515()) || this.forceFacePlace.get().isPressed();
        if (!bl2 && d2 < this.minDamage.get()) {
            return 0.0;
        }
        return d2;
    }

    private boolean isValidWeaknessItem(class_1799 class_17992) {
        if (!(class_17992.method_7909() instanceof class_1831) || class_17992.method_7909() instanceof class_1794) {
            return false;
        }
        class_1832 class_18322 = ((class_1831)class_17992.method_7909()).method_8022();
        return class_18322 == class_1834.field_8930 || class_18322 == class_1834.field_22033;
    }

    private boolean lambda$new$0() {
        return this.support.get() != SupportMode.Disabled;
    }

    private void lambda$doPlace$3(class_3965 class_39652, AtomicDouble atomicDouble, AtomicBoolean atomicBoolean, AtomicReference atomicReference) {
        this.placeCrystal(class_39652, atomicDouble.get(), atomicBoolean.get() ? (class_2338)atomicReference.get() : null);
    }

    @Override
    public String getInfoString() {
        return this.bestTarget != null && this.bestTargetTimer > 0 ? this.bestTarget.method_7334().getName() : null;
    }

    private void doBreak(class_1297 class_12972) {
        if (this.antiWeakness.get().booleanValue()) {
            class_1293 class_12932 = this.mc.field_1724.method_6112(class_1294.field_5911);
            class_1293 class_12933 = this.mc.field_1724.method_6112(class_1294.field_5910);
            if (!(class_12932 == null || class_12933 != null && class_12933.method_5578() > class_12932.method_5578() || this.isValidWeaknessItem(this.mc.field_1724.method_6047()))) {
                if (!InvUtils.swap(InvUtils.findInHotbar(this::isValidWeaknessItem).getSlot())) {
                    return;
                }
                this.switchTimer = 1;
                return;
            }
        }
        boolean bl = true;
        if (this.rotate.get().booleanValue()) {
            double d;
            double d2 = Rotations.getYaw(class_12972);
            if (this.doYawSteps(d2, d = Rotations.getPitch(class_12972, Target.Feet))) {
                this.setRotation(true, class_12972.method_19538(), 0.0, 0.0);
                Rotations.rotate(d2, d, 50, () -> this.lambda$doBreak$1(class_12972));
                this.breakTimer = this.breakDelay.get();
            } else {
                bl = false;
            }
        } else {
            this.attackCrystal(class_12972);
            this.breakTimer = this.breakDelay.get();
        }
        if (bl) {
            this.removed.add(class_12972.method_5628());
            this.attemptedBreaks.put(class_12972.method_5628(), this.attemptedBreaks.get(class_12972.method_5628()) + 1);
            this.waitingToExplode.put(class_12972.method_5628(), 0);
            this.breakRenderPos.method_10101((class_2382)class_12972.method_24515().method_10074());
            this.breakRenderTimer = this.renderBreakTime.get();
        }
    }

    @EventHandler
    private void onPacketSent(PacketEvent.Sent sent) {
        if (sent.packet instanceof class_2828) {
            this.serverYaw = ((class_2828)sent.packet).method_12271((float)this.serverYaw);
        }
    }

    private void doBreak() {
        if (!this.doBreak.get().booleanValue() || this.breakTimer > 0 || this.switchTimer > 0) {
            return;
        }
        double d = 0.0;
        class_1297 class_12972 = null;
        for (class_1297 class_12973 : this.mc.field_1687.method_18112()) {
            double d2 = this.getBreakDamage(class_12973, true);
            if (!(d2 > d)) continue;
            d = d2;
            class_12972 = class_12973;
        }
        if (class_12972 != null) {
            this.doBreak(class_12972);
        }
    }

    @EventHandler
    private void onEntityRemoved(EntityRemovedEvent entityRemovedEvent) {
        if (entityRemovedEvent.entity instanceof class_1511) {
            this.placedCrystals.remove(entityRemovedEvent.entity.method_5628());
            this.removed.remove(entityRemovedEvent.entity.method_5628());
            this.waitingToExplode.remove(entityRemovedEvent.entity.method_5628());
        }
    }

    public CrystalAura() {
        super(Categories.Combat, "crystal-aura", "Automatically places and attacks crystals.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgPlace = this.settings.createGroup("Place");
        this.sgFacePlace = this.settings.createGroup("Face Place");
        this.sgBreak = this.settings.createGroup("Break");
        this.sgPause = this.settings.createGroup("Pause");
        this.sgRender = this.settings.createGroup("Render");
        this.targetRange = this.sgGeneral.add(new DoubleSetting.Builder().name("target-range").description("Range in which to target players.").defaultValue(10.0).min(0.0).sliderMax(16.0).build());
        this.predictMovement = this.sgGeneral.add(new BoolSetting.Builder().name("predict-movement").description("Predicts target movement.").defaultValue(false).build());
        this.ignoreTerrain = this.sgGeneral.add(new BoolSetting.Builder().name("ignore-terrain").description("Completely ignores terrain if it can be blown up by end crystals.").defaultValue(true).build());
        this.minDamage = this.sgGeneral.add(new DoubleSetting.Builder().name("min-damage").description("Minimum damage the crystal needs to deal to your target.").defaultValue(6.0).min(0.0).build());
        this.maxDamage = this.sgGeneral.add(new DoubleSetting.Builder().name("max-damage").description("Maximum damage crystals can deal to yourself.").defaultValue(6.0).min(0.0).max(36.0).sliderMax(36.0).build());
        this.autoSwitch = this.sgGeneral.add(new EnumSetting.Builder().name("auto-switch").description("Switches to crystals in your hotbar once a target is found.").defaultValue(AutoSwitchMode.Normal).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Rotates server-side towards the crystals being hit/placed.").defaultValue(true).build());
        this.yawStepMode = this.sgGeneral.add(new EnumSetting.Builder().name("yaw-steps-mode").description("When to run the yaw steps check.").defaultValue(YawStepMode.Break).visible(this.rotate::get).build());
        this.yawSteps = this.sgGeneral.add(new DoubleSetting.Builder().name("yaw-steps").description("Maximum number of degrees its allowed to rotate in one tick.").defaultValue(180.0).min(1.0).max(180.0).sliderMin(1.0).sliderMax(180.0).visible(this.rotate::get).build());
        this.antiSuicide = this.sgGeneral.add(new BoolSetting.Builder().name("anti-suicide").description("Will not place and break crystals if they will kill you.").defaultValue(true).build());
        this.doPlace = this.sgPlace.add(new BoolSetting.Builder().name("place").description("If the CA should place crystals.").defaultValue(true).build());
        this.placeDelay = this.sgPlace.add(new IntSetting.Builder().name("place-delay").description("The delay in ticks to wait to place a crystal after it's exploded.").defaultValue(0).min(0).sliderMin(0).sliderMax(20).build());
        this.placeRange = this.sgPlace.add(new DoubleSetting.Builder().name("place-range").description("Range in which to place crystals.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        this.placeWallsRange = this.sgPlace.add(new DoubleSetting.Builder().name("place-walls-range").description("Range in which to place crystals when behind blocks.").defaultValue(3.5).min(0.0).sliderMax(6.0).build());
        this.placement112 = this.sgPlace.add(new BoolSetting.Builder().name("1.12-placement").description("Uses 1.12 crystal placement.").defaultValue(false).build());
        this.support = this.sgPlace.add(new EnumSetting.Builder().name("support").description("Places a support block in air if no other position have been found.").defaultValue(SupportMode.Disabled).build());
        this.supportDelay = this.sgPlace.add(new IntSetting.Builder().name("support-delay").description("Delay in ticks after placing support block.").defaultValue(1).min(0).visible(this::lambda$new$0).build());
        this.facePlace = this.sgFacePlace.add(new BoolSetting.Builder().name("face-place").description("Will face-place when target is below a certain health or armor durability threshold.").defaultValue(true).build());
        this.facePlaceHealth = this.sgFacePlace.add(new DoubleSetting.Builder().name("face-place-health").description("The health the target has to be at to start face placing.").defaultValue(8.0).min(1.0).sliderMin(1.0).sliderMax(36.0).visible(this.facePlace::get).build());
        this.facePlaceDurability = this.sgFacePlace.add(new DoubleSetting.Builder().name("face-place-durability").description("The durability threshold percentage to be able to face-place.").defaultValue(2.0).min(1.0).sliderMin(1.0).sliderMax(100.0).visible(this.facePlace::get).build());
        this.facePlaceArmor = this.sgFacePlace.add(new BoolSetting.Builder().name("face-place-missing-armor").description("Automatically starts face placing when a target misses a piece of armor.").defaultValue(false).visible(this.facePlace::get).build());
        this.forceFacePlace = this.sgFacePlace.add(new KeybindSetting.Builder().name("force-face-place").description("Starts face place when this button is pressed.").defaultValue(Keybind.fromKey(-1)).build());
        this.doBreak = this.sgBreak.add(new BoolSetting.Builder().name("break").description("If the CA should break crystals.").defaultValue(true).build());
        this.breakDelay = this.sgBreak.add(new IntSetting.Builder().name("break-delay").description("The delay in ticks to wait to break a crystal after it's placed.").defaultValue(0).min(0).sliderMin(0).sliderMax(20).build());
        this.smartDelay = this.sgBreak.add(new BoolSetting.Builder().name("smart-delay").description("Only breaks crystals when the target can receive damage.").defaultValue(false).build());
        this.switchDelay = this.sgBreak.add(new IntSetting.Builder().name("switch-delay").description("The delay in ticks to wait to break a crystal after switching hotbar slot.").defaultValue(0).min(0).sliderMax(10).build());
        this.breakRange = this.sgBreak.add(new DoubleSetting.Builder().name("break-range").description("Range in which to break crystals.").defaultValue(4.5).min(0.0).sliderMax(6.0).build());
        this.breakWallsRange = this.sgBreak.add(new DoubleSetting.Builder().name("break-walls-range").description("Range in which to break crystals when behind blocks.").defaultValue(3.0).min(0.0).sliderMax(6.0).build());
        this.onlyBreakOwn = this.sgBreak.add(new BoolSetting.Builder().name("only-own").description("Only breaks own crystals.").defaultValue(false).build());
        this.breakAttempts = this.sgBreak.add(new IntSetting.Builder().name("break-attempts").description("How many times to hit a crystal before stopping to target it.").defaultValue(2).sliderMin(1).sliderMax(5).build());
        this.minimumCrystalAge = this.sgBreak.add(new IntSetting.Builder().name("minimum-crystal-age").description("How many ticks the crystal needs to exist in a world before trying to attack it.").defaultValue(0).min(0).build());
        this.fastBreak = this.sgBreak.add(new BoolSetting.Builder().name("fast-break").description("Ignores break delay and tries to break the crystal as soon as it's spawned in the world.").defaultValue(true).build());
        this.antiWeakness = this.sgBreak.add(new BoolSetting.Builder().name("anti-weakness").description("Switches to tools with high enough damage to explode the crystal with weakness effect.").defaultValue(true).build());
        this.eatPause = this.sgPause.add(new BoolSetting.Builder().name("pause-on-eat").description("Pauses Crystal Aura when eating.").defaultValue(true).build());
        this.drinkPause = this.sgPause.add(new BoolSetting.Builder().name("pause-on-drink").description("Pauses Crystal Aura when drinking.").defaultValue(true).build());
        this.minePause = this.sgPause.add(new BoolSetting.Builder().name("pause-on-mine").description("Pauses Crystal Aura when mining.").defaultValue(false).build());
        this.renderSwing = this.sgRender.add(new BoolSetting.Builder().name("swing").description("Renders hand swinging client side.").defaultValue(true).build());
        this.render = this.sgRender.add(new BoolSetting.Builder().name("render").description("Renders a block overlay over the block the crystals are being placed on.").defaultValue(true).build());
        this.renderBreak = this.sgRender.add(new BoolSetting.Builder().name("break").description("Renders a block overlay over the block the crystals are broken on.").defaultValue(false).build());
        this.shapeMode = this.sgRender.add(new EnumSetting.Builder().name("shape-mode").description("How the shapes are rendered.").defaultValue(ShapeMode.Both).build());
        this.sideColor = this.sgRender.add(new ColorSetting.Builder().name("side-color").description("The side color of the block overlay.").defaultValue(new SettingColor(255, 255, 255, 45)).build());
        this.lineColor = this.sgRender.add(new ColorSetting.Builder().name("line-color").description("The line color of the block overlay.").defaultValue(new SettingColor(255, 255, 255, 255)).build());
        this.renderDamageText = this.sgRender.add(new BoolSetting.Builder().name("damage").description("Renders crystal damage text in the block overlay.").defaultValue(true).build());
        this.damageTextScale = this.sgRender.add(new DoubleSetting.Builder().name("damage-scale").description("How big the damage text should be.").defaultValue(1.25).min(1.0).sliderMax(4.0).visible(this.renderDamageText::get).build());
        this.renderTime = this.sgRender.add(new IntSetting.Builder().name("render-time").description("How long to render for.").defaultValue(10).min(0).sliderMax(20).build());
        this.renderBreakTime = this.sgRender.add(new IntSetting.Builder().name("break-time").description("How long to render breaking for.").defaultValue(13).min(0).sliderMax(20).visible(this.renderBreak::get).build());
        this.targets = new ArrayList<class_1657>();
        this.vec3d = new class_243(0.0, 0.0, 0.0);
        this.playerEyePos = new class_243(0.0, 0.0, 0.0);
        this.vec3 = new Vec3();
        this.blockPos = new class_2338.class_2339();
        this.box = new class_238(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.vec3dRayTraceEnd = new class_243(0.0, 0.0, 0.0);
        this.placedCrystals = new IntOpenHashSet();
        this.placingCrystalBlockPos = new class_2338.class_2339();
        this.removed = new IntOpenHashSet();
        this.attemptedBreaks = new Int2IntOpenHashMap();
        this.waitingToExplode = new Int2IntOpenHashMap();
        this.lastRotationPos = new class_243(0.0, 0.0, 0.0);
        this.renderPos = new class_2338.class_2339();
        this.breakRenderPos = new class_2338.class_2339();
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent entityAddedEvent) {
        double d;
        if (!(entityAddedEvent.entity instanceof class_1511)) {
            return;
        }
        if (this.placing && entityAddedEvent.entity.method_24515().equals((Object)this.placingCrystalBlockPos)) {
            this.placing = false;
            this.placingTimer = 0;
            this.placedCrystals.add(entityAddedEvent.entity.method_5628());
        }
        if (this.fastBreak.get().booleanValue() && !this.didRotateThisTick && (d = this.getBreakDamage(entityAddedEvent.entity, true)) > this.minDamage.get()) {
            this.doBreak(entityAddedEvent.entity);
        }
    }

    private void lambda$doBreak$1(class_1297 class_12972) {
        this.attackCrystal(class_12972);
    }

    private void doPlace() {
        Object object2;
        if (!this.doPlace.get().booleanValue() || this.placeTimer > 0) {
            return;
        }
        if (!InvUtils.findInHotbar(class_1802.field_8301).found()) {
            return;
        }
        if (this.autoSwitch.get() == AutoSwitchMode.None && this.mc.field_1724.method_6079().method_7909() != class_1802.field_8301 && this.mc.field_1724.method_6047().method_7909() != class_1802.field_8301) {
            return;
        }
        for (Object object2 : this.mc.field_1687.method_18112()) {
            if (!(this.getBreakDamage((class_1297)object2, false) > 0.0)) continue;
            return;
        }
        AtomicDouble atomicDouble = new AtomicDouble(0.0);
        object2 = new AtomicReference<class_2338.class_2339>(new class_2338.class_2339());
        AtomicBoolean atomicBoolean = new AtomicBoolean(this.support.get() != SupportMode.Disabled);
        BlockIterator.register((int)Math.ceil(this.placeRange.get()), (int)Math.ceil(this.placeRange.get()), (arg_0, arg_1) -> this.lambda$doPlace$2(atomicBoolean, atomicDouble, (AtomicReference)object2, arg_0, arg_1));
        BlockIterator.after(() -> this.lambda$doPlace$4(atomicDouble, (AtomicReference)object2, atomicBoolean));
    }

    private void placeCrystal(class_3965 class_39652, double d, class_2338 class_23382) {
        class_1268 class_12682;
        class_1792 class_17922 = class_23382 == null ? class_1802.field_8301 : class_1802.field_8281;
        FindItemResult findItemResult = InvUtils.findInHotbar(class_17922);
        if (!findItemResult.found()) {
            return;
        }
        int n = this.mc.field_1724.field_7514.field_7545;
        if (this.autoSwitch.get() != AutoSwitchMode.None && !findItemResult.isOffhand()) {
            InvUtils.swap(findItemResult.getSlot());
        }
        if ((class_12682 = findItemResult.getHand()) == null) {
            return;
        }
        if (class_23382 == null) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_12682, class_39652));
            if (this.renderSwing.get().booleanValue()) {
                this.mc.field_1724.method_6104(class_12682);
            } else {
                this.mc.method_1562().method_2883((class_2596)new class_2879(class_12682));
            }
            this.placing = true;
            this.placingTimer = 4;
            this.placingCrystalBlockPos.method_10101((class_2382)class_39652.method_17777()).method_10100(0, 1, 0);
            this.renderTimer = this.renderTime.get();
            this.renderPos.method_10101((class_2382)class_39652.method_17777());
            this.renderDamage = d;
        } else {
            BlockUtils.place(class_23382, findItemResult, false, 0, this.renderSwing.get(), true, false);
            this.placeTimer += this.supportDelay.get().intValue();
            if (this.supportDelay.get() == 0) {
                this.placeCrystal(class_39652, d, null);
            }
        }
        if (this.autoSwitch.get() == AutoSwitchMode.Silent) {
            InvUtils.swap(n);
        }
    }

    public boolean doYawSteps(double d, double d2) {
        d = class_3532.method_15338((double)d) + 180.0;
        double d3 = class_3532.method_15338((double)this.serverYaw) + 180.0;
        if (CrystalAura.distanceBetweenAngles(d3, d) <= this.yawSteps.get()) {
            return true;
        }
        double d4 = Math.abs(d - d3);
        double d5 = this.serverYaw;
        d5 = d3 < d ? (d4 < 180.0 ? (d5 += this.yawSteps.get().doubleValue()) : (d5 -= this.yawSteps.get().doubleValue())) : (d4 < 180.0 ? (d5 -= this.yawSteps.get().doubleValue()) : (d5 += this.yawSteps.get().doubleValue()));
        this.setRotation(false, null, d5, d2);
        Rotations.rotate(d5, d2, -100, null);
        return false;
    }

    private boolean isOutOfRange(class_243 class_2432, class_2338 class_23382, boolean bl) {
        ((IRaycastContext)this.raycastContext).set(this.playerEyePos, class_2432, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
        class_3965 class_39652 = this.mc.field_1687.method_17742(this.raycastContext);
        boolean bl2 = class_39652 == null || !class_39652.method_17777().equals((Object)class_23382);
        double d = this.mc.field_1724.method_19538().method_1022(class_2432);
        return d > (bl2 ? (bl ? this.placeWallsRange : this.breakWallsRange).get() : (bl ? this.placeRange : this.breakRange).get());
    }

    private static double distanceBetweenAngles(double d, double d2) {
        double d3 = Math.abs(d2 - d) % 360.0;
        return d3 > 180.0 ? 360.0 - d3 : d3;
    }

    @EventHandler
    private void onRender(RenderEvent renderEvent) {
        if (this.renderTimer > 0 && this.render.get().booleanValue()) {
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)this.renderPos, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
        }
        if (this.breakRenderTimer > 0 && this.renderBreak.get().booleanValue() && !this.mc.field_1687.method_8320((class_2338)this.breakRenderPos).method_26215()) {
            int n = this.sideColor.get().a;
            this.sideColor.get().a -= 20;
            this.sideColor.get().validate();
            int n2 = this.lineColor.get().a;
            this.lineColor.get().a -= 20;
            this.lineColor.get().validate();
            Renderer.boxWithLines(Renderer.NORMAL, Renderer.LINES, (class_2338)this.breakRenderPos, this.sideColor.get(), this.lineColor.get(), this.shapeMode.get(), 0);
            this.sideColor.get().a = n;
            this.lineColor.get().a = n2;
        }
    }

    private void lambda$doPlace$4(AtomicDouble atomicDouble, AtomicReference atomicReference, AtomicBoolean atomicBoolean) {
        if (atomicDouble.get() == 0.0) {
            return;
        }
        class_3965 class_39652 = this.getPlaceInfo((class_2338)atomicReference.get());
        ((IVec3d)this.vec3d).set((double)class_39652.method_17777().method_10263() + 0.5 + (double)class_39652.method_17780().method_10163().method_10263() * 1.0 / 2.0, (double)class_39652.method_17777().method_10264() + 0.5 + (double)class_39652.method_17780().method_10163().method_10264() * 1.0 / 2.0, (double)class_39652.method_17777().method_10260() + 0.5 + (double)class_39652.method_17780().method_10163().method_10260() * 1.0 / 2.0);
        if (this.rotate.get().booleanValue()) {
            double d = Rotations.getYaw(this.vec3d);
            double d2 = Rotations.getPitch(this.vec3d);
            if (this.yawStepMode.get() == YawStepMode.Break || this.doYawSteps(d, d2)) {
                this.setRotation(true, this.vec3d, 0.0, 0.0);
                Rotations.rotate(d, d2, 50, () -> this.lambda$doPlace$3(class_39652, atomicDouble, atomicBoolean, atomicReference));
                this.placeTimer += this.placeDelay.get().intValue();
            }
        } else {
            this.placeCrystal(class_39652, atomicDouble.get(), atomicBoolean.get() ? (class_2338)atomicReference.get() : null);
            this.placeTimer += this.placeDelay.get().intValue();
        }
    }

    private class_3965 getPlaceInfo(class_2338 class_23382) {
        ((IVec3d)this.vec3d).set(this.mc.field_1724.method_23317(), this.mc.field_1724.method_23318() + (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()), this.mc.field_1724.method_23321());
        for (class_2350 class_23502 : class_2350.values()) {
            ((IVec3d)this.vec3dRayTraceEnd).set((double)class_23382.method_10263() + 0.5 + (double)class_23502.method_10163().method_10263() * 0.5, (double)class_23382.method_10264() + 0.5 + (double)class_23502.method_10163().method_10264() * 0.5, (double)class_23382.method_10260() + 0.5 + (double)class_23502.method_10163().method_10260() * 0.5);
            ((IRaycastContext)this.raycastContext).set(this.vec3d, this.vec3dRayTraceEnd, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
            class_3965 class_39652 = this.mc.field_1687.method_17742(this.raycastContext);
            if (class_39652 == null || class_39652.method_17783() != class_239.class_240.field_1332 || !class_39652.method_17777().equals((Object)class_23382)) continue;
            return class_39652;
        }
        class_2350 class_23503 = (double)class_23382.method_10264() > this.vec3d.field_1351 ? class_2350.field_11033 : class_2350.field_11036;
        return new class_3965(this.vec3d, class_23503, class_23382, false);
    }

    private void lambda$doPlace$2(AtomicBoolean atomicBoolean, AtomicDouble atomicDouble, AtomicReference atomicReference, class_2338 class_23382, class_2680 class_26802) {
        boolean bl;
        boolean bl2;
        boolean bl3 = bl2 = class_26802.method_27852(class_2246.field_9987) || class_26802.method_27852(class_2246.field_10540);
        if (!(bl2 || atomicBoolean.get() && class_26802.method_26207().method_15800())) {
            return;
        }
        this.blockPos.method_10103(class_23382.method_10263(), class_23382.method_10264() + 1, class_23382.method_10260());
        if (!this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26215()) {
            return;
        }
        if (this.placement112.get().booleanValue()) {
            this.blockPos.method_10100(0, 1, 0);
            if (!this.mc.field_1687.method_8320((class_2338)this.blockPos).method_26215()) {
                return;
            }
        }
        ((IVec3d)this.vec3d).set((double)class_23382.method_10263() + 0.5, class_23382.method_10264() + 1, (double)class_23382.method_10260() + 0.5);
        this.blockPos.method_10101((class_2382)class_23382).method_10100(0, 1, 0);
        if (this.isOutOfRange(this.vec3d, (class_2338)this.blockPos, true)) {
            return;
        }
        double d = DamageUtils.crystalDamage((class_1657)this.mc.field_1724, this.vec3d, this.predictMovement.get(), this.raycastContext, class_23382, this.ignoreTerrain.get());
        if (d > this.maxDamage.get() || this.antiSuicide.get().booleanValue() && d >= (double)EntityUtils.getTotalHealth((class_1657)this.mc.field_1724)) {
            return;
        }
        double d2 = this.getDamageToTargets(this.vec3d, class_23382, false, !bl2 && this.support.get() == SupportMode.Fast);
        boolean bl4 = bl = this.facePlace.get() != false && this.shouldFacePlace((class_2338)this.blockPos) || this.forceFacePlace.get().isPressed();
        if (!bl && d2 < this.minDamage.get()) {
            return;
        }
        double d3 = class_23382.method_10263();
        double d4 = class_23382.method_10264() + 1;
        double d5 = class_23382.method_10260();
        ((IBox)this.box).set(d3, d4, d5, d3 + 1.0, d4 + (double)(this.placement112.get() != false ? 1 : 2), d5 + 1.0);
        if (this.intersectsWithEntities(this.box)) {
            return;
        }
        if (d2 > atomicDouble.get() || atomicBoolean.get() && bl2) {
            atomicDouble.set(d2);
            ((class_2338.class_2339)atomicReference.get()).method_10101((class_2382)class_23382);
        }
        if (bl2) {
            atomicBoolean.set(false);
        }
    }

    @Override
    public void onActivate() {
        this.breakTimer = 0;
        this.placeTimer = 0;
        this.raycastContext = new class_3959(new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
        this.placing = false;
        this.placingTimer = 0;
        this.serverYaw = this.mc.field_1724.field_6031;
        this.bestTargetDamage = 0.0;
        this.bestTargetTimer = 0;
        this.lastRotationTimer = this.getLastRotationStopDelay();
        this.renderTimer = 0;
        this.breakRenderTimer = 0;
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send send) {
        if (send.packet instanceof class_2868) {
            this.switchTimer = this.switchDelay.get();
        }
    }

    private void findTargets() {
        this.targets.clear();
        for (class_1657 class_16572 : this.mc.field_1687.method_18456()) {
            if (class_16572.field_7503.field_7477 || class_16572 == this.mc.field_1724 || class_16572.method_29504() || !class_16572.method_5805() || !Friends.get().shouldAttack(class_16572) || !((double)class_16572.method_5739((class_1297)this.mc.field_1724) <= this.targetRange.get())) continue;
            this.targets.add(class_16572);
        }
        for (class_1657 class_16572 : FakePlayerManager.getPlayers()) {
            if (class_16572.method_29504() || !class_16572.method_5805() || !Friends.get().shouldAttack(class_16572) || !((double)class_16572.method_5739((class_1297)this.mc.field_1724) <= this.targetRange.get())) continue;
            this.targets.add(class_16572);
        }
    }

    @EventHandler(priority=100)
    private void onPreTick(TickEvent.Pre pre) {
        this.didRotateThisTick = false;
        ++this.lastRotationTimer;
        if (this.placing) {
            if (this.placingTimer > 0) {
                --this.placingTimer;
            } else {
                this.placing = false;
            }
        }
        if (this.bestTargetTimer > 0) {
            --this.bestTargetTimer;
        }
        this.bestTargetDamage = 0.0;
        if (this.breakTimer > 0) {
            --this.breakTimer;
        }
        if (this.placeTimer > 0) {
            --this.placeTimer;
        }
        if (this.switchTimer > 0) {
            --this.switchTimer;
        }
        if (this.renderTimer > 0) {
            --this.renderTimer;
        }
        if (this.breakRenderTimer > 0) {
            --this.breakRenderTimer;
        }
        IntIterator intIterator = this.waitingToExplode.keySet().iterator();
        while (intIterator.hasNext()) {
            int n = intIterator.nextInt();
            int n2 = this.waitingToExplode.get(n);
            if (n2 > 3) {
                intIterator.remove();
                this.removed.remove(n);
                continue;
            }
            this.waitingToExplode.put(n, n2 + 1);
        }
        if (PlayerUtils.shouldPause(this.minePause.get(), this.eatPause.get(), this.drinkPause.get())) {
            return;
        }
        ((IVec3d)this.playerEyePos).set(this.mc.field_1724.method_19538().field_1352, this.mc.field_1724.method_19538().field_1351 + (double)this.mc.field_1724.method_18381(this.mc.field_1724.method_18376()), this.mc.field_1724.method_19538().field_1350);
        this.findTargets();
        if (this.targets.size() > 0) {
            if (!this.didRotateThisTick) {
                this.doBreak();
            }
            if (!this.didRotateThisTick) {
                this.doPlace();
            }
        }
    }

    private boolean intersectsWithEntities(class_238 class_2383) {
        int n = class_3532.method_15357((double)((class_2383.field_1323 - 2.0) / 16.0));
        int n2 = class_3532.method_15357((double)((class_2383.field_1320 + 2.0) / 16.0));
        int n3 = class_3532.method_15357((double)((class_2383.field_1321 - 2.0) / 16.0));
        int n4 = class_3532.method_15357((double)((class_2383.field_1324 + 2.0) / 16.0));
        class_631 class_6312 = this.mc.field_1687.method_2935();
        for (int i = n; i <= n2; ++i) {
            for (int j = n3; j <= n4; ++j) {
                class_2818 class_28182 = class_6312.method_12126(i, j, false);
                if (class_28182 == null) continue;
                class_3509[] class_3509Array = class_28182.method_12215();
                int n5 = class_3532.method_15357((double)((class_2383.field_1322 - 2.0) / 16.0));
                int n6 = class_3532.method_15357((double)((class_2383.field_1325 + 2.0) / 16.0));
                n5 = class_3532.method_15340((int)n5, (int)0, (int)(class_3509Array.length - 1));
                n6 = class_3532.method_15340((int)n6, (int)0, (int)(class_3509Array.length - 1));
                for (int k = n5; k <= n6; ++k) {
                    class_3509 class_35092 = class_3509Array[k];
                    for (class_1297 class_12972 : class_35092) {
                        if (!class_12972.method_5829().method_994(class_2383) || class_12972.method_7325() || this.removed.contains(class_12972.method_5628())) continue;
                        return true;
                    }
                    if (!false) continue;
                    return false;
                }
                if (0 > -1) continue;
                return false;
            }
            if (1 < 4) continue;
            return false;
        }
        return false;
    }

    private int getLastRotationStopDelay() {
        return Math.max(10, this.placeDelay.get() / 2 + this.breakDelay.get() / 2 + 10);
    }

    private double getDamageToTargets(class_243 class_2432, class_2338 class_23382, boolean bl, boolean bl2) {
        double d = 0.0;
        if (bl2) {
            class_1657 class_16572 = this.getNearestTarget();
            if (!this.smartDelay.get().booleanValue() || !bl || class_16572.field_6235 <= 0) {
                d = DamageUtils.crystalDamage(class_16572, class_2432, this.predictMovement.get(), this.raycastContext, class_23382, this.ignoreTerrain.get());
            }
        } else {
            for (class_1657 class_16573 : this.targets) {
                if (this.smartDelay.get().booleanValue() && bl && class_16573.field_6235 > 0) continue;
                double d2 = DamageUtils.crystalDamage(class_16573, class_2432, this.predictMovement.get(), this.raycastContext, class_23382, this.ignoreTerrain.get());
                if (d2 > this.bestTargetDamage) {
                    this.bestTarget = class_16573;
                    this.bestTargetDamage = d2;
                    this.bestTargetTimer = 10;
                }
                d += d2;
            }
        }
        return d;
    }

    private void setRotation(boolean bl, class_243 class_2432, double d, double d2) {
        this.didRotateThisTick = true;
        this.isLastRotationPos = bl;
        if (bl) {
            ((IVec3d)this.lastRotationPos).set(class_2432.field_1352, class_2432.field_1351, class_2432.field_1350);
        } else {
            this.lastYaw = d;
            this.lastPitch = d2;
        }
        this.lastRotationTimer = 0;
    }

    private boolean shouldFacePlace(class_2338 class_23382) {
        for (class_1657 class_16572 : this.targets) {
            class_2338 class_23383 = class_16572.method_24515();
            if (class_23382.method_10264() != class_23383.method_10264() + 1 || Math.abs(class_23383.method_10263() - class_23382.method_10263()) > 1 || Math.abs(class_23383.method_10260() - class_23382.method_10260()) > 1) continue;
            if ((double)EntityUtils.getTotalHealth(class_16572) <= this.facePlaceHealth.get()) {
                return true;
            }
            for (class_1799 class_17992 : class_16572.method_5661()) {
                if (!(class_17992 == null || class_17992.method_7960() ? this.facePlaceArmor.get() != false : (double)(class_17992.method_7936() - class_17992.method_7919()) / (double)class_17992.method_7936() * 100.0 <= this.facePlaceDurability.get())) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDeactivate() {
        this.targets.clear();
        this.placedCrystals.clear();
        this.attemptedBreaks.clear();
        this.waitingToExplode.clear();
        this.removed.clear();
        this.bestTarget = null;
    }

    @EventHandler(priority=-866)
    private void onPreTickLast(TickEvent.Pre pre) {
        if (this.rotate.get().booleanValue() && this.lastRotationTimer < this.getLastRotationStopDelay() && !this.didRotateThisTick) {
            Rotations.rotate(this.isLastRotationPos ? Rotations.getYaw(this.lastRotationPos) : this.lastYaw, this.isLastRotationPos ? Rotations.getPitch(this.lastRotationPos) : this.lastPitch, -100, null);
        }
    }

    @EventHandler
    private void onRender2D(Render2DEvent render2DEvent) {
        if (!this.render.get().booleanValue() || this.renderTimer <= 0 || !this.renderDamageText.get().booleanValue()) {
            return;
        }
        this.vec3.set((double)this.renderPos.method_10263() + 0.5, (double)this.renderPos.method_10264() + 0.5, (double)this.renderPos.method_10260() + 0.5);
        if (NametagUtils.to2D(this.vec3, this.damageTextScale.get())) {
            NametagUtils.begin(this.vec3);
            TextRenderer.get().begin(1.0, false, true);
            String string = String.format("%.1f", this.renderDamage);
            double d = TextRenderer.get().getWidth(string) / 2.0;
            TextRenderer.get().render(string, -d, 0.0, this.lineColor.get(), true);
            TextRenderer.get().end();
            NametagUtils.end();
        }
    }

    private class_1657 getNearestTarget() {
        class_1657 class_16572 = null;
        double d = Double.MAX_VALUE;
        for (class_1657 class_16573 : this.targets) {
            double d2 = class_16573.method_5858((class_1297)this.mc.field_1724);
            if (!(d2 < d)) continue;
            class_16572 = class_16573;
            d = d2;
        }
        return class_16572;
    }

    public static enum SupportMode {
        Disabled,
        Accurate,
        Fast;

    }

    public static enum AutoSwitchMode {
        Normal,
        Silent,
        None;

    }

    public static enum YawStepMode {
        Break,
        All;

    }
}

