/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1113
 *  net.minecraft.class_1536
 *  net.minecraft.class_1787
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
    private /* synthetic */ boolean ticksEnabled;
    private /* synthetic */ int autoCastCheckTimer;
    private /* synthetic */ int ticksToRightClick;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ boolean autoCastEnabled;
    private /* synthetic */ int ticksData;
    private final /* synthetic */ Setting<Boolean> splashDetectionRangeEnabled;
    private /* synthetic */ int autoCastTimer;
    private final /* synthetic */ Setting<Integer> ticksAutoCast;
    private final /* synthetic */ Setting<Double> splashDetectionRange;
    private final /* synthetic */ SettingGroup sgSplashRangeDetection;
    private final /* synthetic */ Setting<Integer> ticksCatch;
    private final /* synthetic */ Setting<Integer> ticksThrow;
    private final /* synthetic */ Setting<Boolean> autoCast;

    @Override
    public void onActivate() {
        lllllllllllllllllllIlIlIllIIlIlI.ticksEnabled = false;
        lllllllllllllllllllIlIlIllIIlIlI.autoCastEnabled = false;
        lllllllllllllllllllIlIlIllIIlIlI.autoCastCheckTimer = 0;
    }

    public AutoFish() {
        super(Categories.Player, "auto-fish", "Automatically fishes for you.");
        AutoFish lllllllllllllllllllIlIlIllIIllIl;
        lllllllllllllllllllIlIlIllIIllIl.sgGeneral = lllllllllllllllllllIlIlIllIIllIl.settings.getDefaultGroup();
        lllllllllllllllllllIlIlIllIIllIl.sgSplashRangeDetection = lllllllllllllllllllIlIlIllIIllIl.settings.createGroup("Splash Detection");
        lllllllllllllllllllIlIlIllIIllIl.autoCast = lllllllllllllllllllIlIlIllIIllIl.sgGeneral.add(new BoolSetting.Builder().name("auto-cast").description("Automatically casts when not fishing.").defaultValue(true).build());
        lllllllllllllllllllIlIlIllIIllIl.ticksAutoCast = lllllllllllllllllllIlIlIllIIllIl.sgGeneral.add(new IntSetting.Builder().name("ticks-auto-cast").description("The amount of ticks to wait before recasting automatically.").defaultValue(10).min(0).sliderMax(60).build());
        lllllllllllllllllllIlIlIllIIllIl.ticksCatch = lllllllllllllllllllIlIlIllIIllIl.sgGeneral.add(new IntSetting.Builder().name("catch-delay").description("The amount of ticks to wait before catching the fish.").defaultValue(6).min(0).sliderMax(60).build());
        lllllllllllllllllllIlIlIllIIllIl.ticksThrow = lllllllllllllllllllIlIlIllIIllIl.sgGeneral.add(new IntSetting.Builder().name("throw-delay").description("The amount of ticks to wait before throwing the bobber.").defaultValue(14).min(0).sliderMax(60).build());
        lllllllllllllllllllIlIlIllIIllIl.splashDetectionRangeEnabled = lllllllllllllllllllIlIlIllIIllIl.sgSplashRangeDetection.add(new BoolSetting.Builder().name("splash-detection-range-enabled").description("Allows you to use multiple accounts next to each other.").defaultValue(false).build());
        lllllllllllllllllllIlIlIllIIllIl.splashDetectionRange = lllllllllllllllllllIlIlIllIIllIl.sgSplashRangeDetection.add(new DoubleSetting.Builder().name("splash-detection-range").description("The detection range of a splash. Lower values will not work when the TPS is low.").defaultValue(10.0).min(0.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllllIlIlIlIlllIlI) {
        AutoFish lllllllllllllllllllIlIlIlIlllIIl;
        if (lllllllllllllllllllIlIlIlIlllIIl.autoCastCheckTimer <= 0) {
            lllllllllllllllllllIlIlIlIlllIIl.autoCastCheckTimer = 30;
            if (lllllllllllllllllllIlIlIlIlllIIl.autoCast.get().booleanValue() && !lllllllllllllllllllIlIlIlIlllIIl.ticksEnabled && !lllllllllllllllllllIlIlIlIlllIIl.autoCastEnabled && lllllllllllllllllllIlIlIlIlllIIl.mc.field_1724.field_7513 == null && lllllllllllllllllllIlIlIlIlllIIl.mc.field_1724.method_6047().method_7909() instanceof class_1787) {
                lllllllllllllllllllIlIlIlIlllIIl.autoCastTimer = 0;
                lllllllllllllllllllIlIlIlIlllIIl.autoCastEnabled = true;
            }
        } else {
            --lllllllllllllllllllIlIlIlIlllIIl.autoCastCheckTimer;
        }
        if (lllllllllllllllllllIlIlIlIlllIIl.autoCastEnabled) {
            ++lllllllllllllllllllIlIlIlIlllIIl.autoCastTimer;
            if (lllllllllllllllllllIlIlIlIlllIIl.autoCastTimer > lllllllllllllllllllIlIlIlIlllIIl.ticksAutoCast.get()) {
                lllllllllllllllllllIlIlIlIlllIIl.autoCastEnabled = false;
                Utils.rightClick();
            }
        }
        if (lllllllllllllllllllIlIlIlIlllIIl.ticksEnabled && lllllllllllllllllllIlIlIlIlllIIl.ticksToRightClick <= 0) {
            if (lllllllllllllllllllIlIlIlIlllIIl.ticksData == 0) {
                Utils.rightClick();
                lllllllllllllllllllIlIlIlIlllIIl.ticksToRightClick = lllllllllllllllllllIlIlIlIlllIIl.ticksThrow.get();
                lllllllllllllllllllIlIlIlIlllIIl.ticksData = 1;
            } else if (lllllllllllllllllllIlIlIlIlllIIl.ticksData == 1) {
                Utils.rightClick();
                lllllllllllllllllllIlIlIlIlllIIl.ticksEnabled = false;
            }
        }
        --lllllllllllllllllllIlIlIlIlllIIl.ticksToRightClick;
    }

    @EventHandler
    private void onKey(KeyEvent lllllllllllllllllllIlIlIlIllIllI) {
        AutoFish lllllllllllllllllllIlIlIlIllIlll;
        if (lllllllllllllllllllIlIlIlIllIlll.mc.field_1690.field_1904.method_1434()) {
            lllllllllllllllllllIlIlIlIllIlll.ticksEnabled = false;
        }
    }

    @EventHandler
    private void onPlaySound(PlaySoundEvent lllllllllllllllllllIlIlIllIIIIll) {
        AutoFish lllllllllllllllllllIlIlIllIIIlII;
        class_1113 lllllllllllllllllllIlIlIllIIIIlI = lllllllllllllllllllIlIlIllIIIIll.sound;
        class_1536 lllllllllllllllllllIlIlIllIIIIIl = lllllllllllllllllllIlIlIllIIIlII.mc.field_1724.field_7513;
        if (lllllllllllllllllllIlIlIllIIIIlI.method_4775().method_12832().equals("entity.fishing_bobber.splash") && (!lllllllllllllllllllIlIlIllIIIlII.splashDetectionRangeEnabled.get().booleanValue() || Utils.distance(lllllllllllllllllllIlIlIllIIIIIl.method_23317(), lllllllllllllllllllIlIlIllIIIIIl.method_23318(), lllllllllllllllllllIlIlIllIIIIIl.method_23321(), lllllllllllllllllllIlIlIllIIIIlI.method_4784(), lllllllllllllllllllIlIlIllIIIIlI.method_4779(), lllllllllllllllllllIlIlIllIIIIlI.method_4778()) <= lllllllllllllllllllIlIlIllIIIlII.splashDetectionRange.get())) {
            lllllllllllllllllllIlIlIllIIIlII.ticksEnabled = true;
            lllllllllllllllllllIlIlIllIIIlII.ticksToRightClick = lllllllllllllllllllIlIlIllIIIlII.ticksCatch.get();
            lllllllllllllllllllIlIlIllIIIlII.ticksData = 0;
        }
    }
}

