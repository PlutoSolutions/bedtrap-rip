/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_746
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_746;

public class Phase
extends Module {
    private double prevY;
    static final boolean $assertionsDisabled = !Phase.class.desiredAssertionStatus();
    public final Setting<Boolean> noPush;
    private final Setting<Mode> mode;
    private final Setting<Double> distance;
    private final SettingGroup sgGeneral;
    private double prevX;
    private double prevZ;

    public Phase() {
        super(Categories.BedTrap, "phase", "Lets you walk through blocks.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Sends to the chat message.").defaultValue(Mode.NRNB).build());
        this.distance = this.sgGeneral.add(new DoubleSetting.Builder().name("Speed").description("The X and Z distance per clip.").defaultValue(0.1).min(0.0).max(10.0).build());
        this.noPush = this.sgGeneral.add(new BoolSetting.Builder().name("No-push").description("Attempts to stop getting pushed out of blocks.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        double d;
        class_746 class_7462 = this.mc.field_1724;
        class_243 class_2432 = class_243.method_1030((float)0.0f, (float)class_7462.field_6031);
        class_243 class_2433 = class_243.method_1030((float)0.0f, (float)(class_7462.field_6031 - 180.0f));
        class_243 class_2434 = class_243.method_1030((float)0.0f, (float)(class_7462.field_6031 - 90.0f));
        class_243 class_2435 = class_243.method_1030((float)0.0f, (float)(class_7462.field_6031 - 270.0f));
        class_243 class_2436 = class_243.method_1030((float)(class_7462.field_5965 - 150.0f), (float)0.0f);
        double d2 = Math.abs(this.mc.field_1724.method_23317() - this.mc.field_1724.field_6014);
        double d3 = Math.abs(this.mc.field_1724.method_23321() - this.mc.field_1724.field_5969);
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        double d5 = d4 * 20.0;
        double d6 = this.mc.field_1724.method_23317();
        double d7 = this.mc.field_1724.method_23318();
        double d8 = this.mc.field_1724.method_23321();
        if (this.mc.field_1724 != null && !this.mc.field_1724.method_24828()) {
            return;
        }
        if (this.mode.get() == Mode.NRNB) {
            if (this.mc.field_1690.field_1894.method_1434()) {
                class_7462.method_5814(this.prevX + class_2432.field_1352 * this.distance.get(), class_7462.method_23318(), this.prevZ + class_2432.field_1350 * this.distance.get());
                this.prevX += class_2432.field_1352 * this.distance.get();
                this.prevZ += class_2432.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1881.method_1434()) {
                class_7462.method_5814(this.prevX + class_2433.field_1352 * this.distance.get(), class_7462.method_23318(), this.prevZ + class_2433.field_1350 * this.distance.get());
                this.prevX += class_2433.field_1352 * this.distance.get();
                this.prevZ += class_2433.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1913.method_1434()) {
                class_7462.method_5814(this.prevX + class_2434.field_1352 * this.distance.get(), class_7462.method_23318(), this.prevZ + class_2434.field_1350 * this.distance.get());
                this.prevX += class_2434.field_1352 * this.distance.get();
                this.prevZ += class_2434.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1849.method_1434()) {
                class_7462.method_5814(this.prevX + class_2435.field_1352 * this.distance.get(), class_7462.method_23318(), this.prevZ + class_2435.field_1350 * this.distance.get());
                this.prevX += class_2435.field_1352 * this.distance.get();
                this.prevZ += class_2435.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1903.method_1434()) {
                class_7462.method_5814(class_7462.method_23317(), this.prevY + class_2436.field_1351 * 0.4, class_7462.method_23321());
                this.prevX += class_2436.field_1352 * this.distance.get();
                this.prevZ += class_2436.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1832.method_1434()) {
                d = (double)class_3532.method_15357((double)this.mc.field_1724.method_23318()) - 0.2;
                class_7462.method_5814(this.mc.field_1724.method_23317(), d, this.mc.field_1724.method_23321());
            }
        }
        if (this.mode.get() == Mode.Normal && (d5 <= 1.0 || !this.mc.field_1687.method_8320(this.mc.field_1724.method_24515()).method_26215())) {
            if (this.mc.field_1690.field_1894.method_1434()) {
                class_7462.method_5814(d6 + class_2432.field_1352 * this.distance.get(), class_7462.method_23318(), d8 + class_2432.field_1350 * this.distance.get());
                d6 += class_2432.field_1352 * this.distance.get();
                d8 += class_2432.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1881.method_1434()) {
                class_7462.method_5814(d6 + class_2433.field_1352 * this.distance.get(), class_7462.method_23318(), d8 + class_2433.field_1350 * this.distance.get());
                d6 += class_2433.field_1352 * this.distance.get();
                d8 += class_2433.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1913.method_1434()) {
                class_7462.method_5814(d6 + class_2434.field_1352 * this.distance.get(), class_7462.method_23318(), d8 + class_2434.field_1350 * this.distance.get());
                d6 += class_2434.field_1352 * this.distance.get();
                d8 += class_2434.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1849.method_1434()) {
                class_7462.method_5814(d6 + class_2435.field_1352 * this.distance.get(), class_7462.method_23318(), d8 + class_2435.field_1350 * this.distance.get());
                d6 += class_2435.field_1352 * this.distance.get();
                d8 += class_2435.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1903.method_1434()) {
                class_7462.method_5814(class_7462.method_23317(), d7 + class_2436.field_1351 * 0.4, class_7462.method_23321());
                d6 += class_2436.field_1352 * this.distance.get();
                d8 += class_2436.field_1350 * this.distance.get();
            }
            if (this.mc.field_1690.field_1832.method_1434()) {
                d = (double)class_3532.method_15357((double)this.mc.field_1724.method_23318()) - 0.2;
                class_7462.method_5814(this.mc.field_1724.method_23317(), d, this.mc.field_1724.method_23321());
            }
        }
    }

    @Override
    public void onActivate() {
        if (!$assertionsDisabled && this.mc.field_1724 == null) {
            throw new AssertionError();
        }
        this.prevX = this.mc.field_1724.method_23317();
        this.prevY = this.mc.field_1724.method_23318();
        this.prevZ = this.mc.field_1724.method_23321();
    }

    public static enum Mode {
        NRNB,
        Normal;

    }
}

