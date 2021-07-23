/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.world.PlaySoundEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1113;
import net.minecraft.class_1536;
import net.minecraft.class_1787;

public class AutoFish
extends Module {
    private boolean ticksEnabled;
    private int autoCastCheckTimer;
    private int ticksToRightClick;
    private final SettingGroup sgGeneral;
    private boolean autoCastEnabled;
    private int ticksData;
    private final Setting<Boolean> splashDetectionRangeEnabled;
    private int autoCastTimer;
    private final Setting<Integer> ticksAutoCast;
    private final Setting<Double> splashDetectionRange;
    private final SettingGroup sgSplashRangeDetection;
    private final Setting<Integer> ticksCatch;
    private final Setting<Integer> ticksThrow;
    private final Setting<Boolean> autoCast;

    @Override
    public void onActivate() {
        this.ticksEnabled = false;
        this.autoCastEnabled = false;
        this.autoCastCheckTimer = 0;
    }

    public AutoFish() {
        super(Categories.Player, "auto-fish", "Automatically fishes for you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgSplashRangeDetection = this.settings.createGroup("Splash Detection");
        this.autoCast = this.sgGeneral.add(new BoolSetting.Builder().name("auto-cast").description("Automatically casts when not fishing.").defaultValue(true).build());
        this.ticksAutoCast = this.sgGeneral.add(new IntSetting.Builder().name("ticks-auto-cast").description("The amount of ticks to wait before recasting automatically.").defaultValue(10).min(0).sliderMax(60).build());
        this.ticksCatch = this.sgGeneral.add(new IntSetting.Builder().name("catch-delay").description("The amount of ticks to wait before catching the fish.").defaultValue(6).min(0).sliderMax(60).build());
        this.ticksThrow = this.sgGeneral.add(new IntSetting.Builder().name("throw-delay").description("The amount of ticks to wait before throwing the bobber.").defaultValue(14).min(0).sliderMax(60).build());
        this.splashDetectionRangeEnabled = this.sgSplashRangeDetection.add(new BoolSetting.Builder().name("splash-detection-range-enabled").description("Allows you to use multiple accounts next to each other.").defaultValue(false).build());
        this.splashDetectionRange = this.sgSplashRangeDetection.add(new DoubleSetting.Builder().name("splash-detection-range").description("The detection range of a splash. Lower values will not work when the TPS is low.").defaultValue(10.0).min(0.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.autoCastCheckTimer <= 0) {
            this.autoCastCheckTimer = 30;
            if (this.autoCast.get().booleanValue() && !this.ticksEnabled && !this.autoCastEnabled && this.mc.field_1724.field_7513 == null && this.mc.field_1724.method_6047().method_7909() instanceof class_1787) {
                this.autoCastTimer = 0;
                this.autoCastEnabled = true;
            }
        } else {
            --this.autoCastCheckTimer;
        }
        if (this.autoCastEnabled) {
            ++this.autoCastTimer;
            if (this.autoCastTimer > this.ticksAutoCast.get()) {
                this.autoCastEnabled = false;
                Utils.rightClick();
            }
        }
        if (this.ticksEnabled && this.ticksToRightClick <= 0) {
            if (this.ticksData == 0) {
                Utils.rightClick();
                this.ticksToRightClick = this.ticksThrow.get();
                this.ticksData = 1;
            } else if (this.ticksData == 1) {
                Utils.rightClick();
                this.ticksEnabled = false;
            }
        }
        --this.ticksToRightClick;
    }

    @EventHandler
    private void onKey(KeyEvent keyEvent) {
        if (this.mc.field_1690.field_1904.method_1434()) {
            this.ticksEnabled = false;
        }
    }

    @EventHandler
    private void onPlaySound(PlaySoundEvent playSoundEvent) {
        class_1113 class_11132 = playSoundEvent.sound;
        class_1536 class_15362 = this.mc.field_1724.field_7513;
        if (class_11132.method_4775().method_12832().equals("entity.fishing_bobber.splash") && (!this.splashDetectionRangeEnabled.get().booleanValue() || Utils.distance(class_15362.method_23317(), class_15362.method_23318(), class_15362.method_23321(), class_11132.method_4784(), class_11132.method_4779(), class_11132.method_4778()) <= this.splashDetectionRange.get())) {
            this.ticksEnabled = true;
            this.ticksToRightClick = this.ticksCatch.get();
            this.ticksData = 0;
        }
    }
}

