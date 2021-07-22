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
    private /* synthetic */ double prevY;
    public final /* synthetic */ Setting<Boolean> noPush;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Double> distance;
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ double prevX;
    private /* synthetic */ double prevZ;

    public Phase() {
        super(Categories.BedTrap, "phase", "Lets you walk through blocks.");
        Phase llllllllllllllllllIlIIlllIlIIlIl;
        llllllllllllllllllIlIIlllIlIIlIl.sgGeneral = llllllllllllllllllIlIIlllIlIIlIl.settings.getDefaultGroup();
        llllllllllllllllllIlIIlllIlIIlIl.mode = llllllllllllllllllIlIIlllIlIIlIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Sends to the chat message.").defaultValue(Mode.NRNB).build());
        llllllllllllllllllIlIIlllIlIIlIl.distance = llllllllllllllllllIlIIlllIlIIlIl.sgGeneral.add(new DoubleSetting.Builder().name("Speed").description("The X and Z distance per clip.").defaultValue(0.1).min(0.0).max(10.0).build());
        llllllllllllllllllIlIIlllIlIIlIl.noPush = llllllllllllllllllIlIIlllIlIIlIl.sgGeneral.add(new BoolSetting.Builder().name("No-push").description("Attempts to stop getting pushed out of blocks.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post llllllllllllllllllIlIIlllIIIlllI) {
        Phase llllllllllllllllllIlIIlllIIIIIII;
        class_746 llllllllllllllllllIlIIlllIIIllIl = llllllllllllllllllIlIIlllIIIIIII.mc.field_1724;
        class_243 llllllllllllllllllIlIIlllIIIllII = class_243.method_1030((float)0.0f, (float)llllllllllllllllllIlIIlllIIIllIl.field_6031);
        class_243 llllllllllllllllllIlIIlllIIIlIll = class_243.method_1030((float)0.0f, (float)(llllllllllllllllllIlIIlllIIIllIl.field_6031 - 180.0f));
        class_243 llllllllllllllllllIlIIlllIIIlIlI = class_243.method_1030((float)0.0f, (float)(llllllllllllllllllIlIIlllIIIllIl.field_6031 - 90.0f));
        class_243 llllllllllllllllllIlIIlllIIIlIIl = class_243.method_1030((float)0.0f, (float)(llllllllllllllllllIlIIlllIIIllIl.field_6031 - 270.0f));
        class_243 llllllllllllllllllIlIIlllIIIlIII = class_243.method_1030((float)(llllllllllllllllllIlIIlllIIIllIl.field_5965 - 150.0f), (float)0.0f);
        double llllllllllllllllllIlIIlllIIIIlll = Math.abs(llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23317() - llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.field_6014);
        double llllllllllllllllllIlIIlllIIIIllI = Math.abs(llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23321() - llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.field_5969);
        double llllllllllllllllllIlIIlllIIIIlIl = Math.sqrt(llllllllllllllllllIlIIlllIIIIlll * llllllllllllllllllIlIIlllIIIIlll + llllllllllllllllllIlIIlllIIIIllI * llllllllllllllllllIlIIlllIIIIllI);
        double llllllllllllllllllIlIIlllIIIIlII = llllllllllllllllllIlIIlllIIIIlIl * 20.0;
        double llllllllllllllllllIlIIlllIIIIIll = llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23317();
        double llllllllllllllllllIlIIlllIIIIIlI = llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23318();
        double llllllllllllllllllIlIIlllIIIIIIl = llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23321();
        if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1724 != null && !llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_24828()) {
            return;
        }
        if (llllllllllllllllllIlIIlllIIIIIII.mode.get() == Mode.NRNB) {
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1894.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.prevX + llllllllllllllllllIlIIlllIIIllII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIII.prevZ + llllllllllllllllllIlIIlllIIIllII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIII.prevX += llllllllllllllllllIlIIlllIIIllII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIII.prevZ += llllllllllllllllllIlIIlllIIIllII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1881.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.prevX + llllllllllllllllllIlIIlllIIIlIll.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIII.prevZ + llllllllllllllllllIlIIlllIIIlIll.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIII.prevX += llllllllllllllllllIlIIlllIIIlIll.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIII.prevZ += llllllllllllllllllIlIIlllIIIlIll.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1913.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.prevX + llllllllllllllllllIlIIlllIIIlIlI.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIII.prevZ + llllllllllllllllllIlIIlllIIIlIlI.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIII.prevX += llllllllllllllllllIlIIlllIIIlIlI.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIII.prevZ += llllllllllllllllllIlIIlllIIIlIlI.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1849.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.prevX + llllllllllllllllllIlIIlllIIIlIIl.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIII.prevZ + llllllllllllllllllIlIIlllIIIlIIl.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIII.prevX += llllllllllllllllllIlIIlllIIIlIIl.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIII.prevZ += llllllllllllllllllIlIIlllIIIlIIl.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1903.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIllIl.method_23317(), llllllllllllllllllIlIIlllIIIIIII.prevY + llllllllllllllllllIlIIlllIIIlIII.field_1351 * 0.4, llllllllllllllllllIlIIlllIIIllIl.method_23321());
                llllllllllllllllllIlIIlllIIIIIII.prevX += llllllllllllllllllIlIIlllIIIlIII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIII.prevZ += llllllllllllllllllIlIIlllIIIlIII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1832.method_1434()) {
                double llllllllllllllllllIlIIlllIIlIIIl = (double)class_3532.method_15357((double)llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23318()) - 0.2;
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23317(), llllllllllllllllllIlIIlllIIlIIIl, llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23321());
            }
        }
        if (llllllllllllllllllIlIIlllIIIIIII.mode.get() == Mode.Normal && (llllllllllllllllllIlIIlllIIIIlII <= 1.0 || !llllllllllllllllllIlIIlllIIIIIII.mc.field_1687.method_8320(llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_24515()).method_26215())) {
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1894.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIll + llllllllllllllllllIlIIlllIIIllII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIIl + llllllllllllllllllIlIIlllIIIllII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIll += llllllllllllllllllIlIIlllIIIllII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIIl += llllllllllllllllllIlIIlllIIIllII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1881.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIll + llllllllllllllllllIlIIlllIIIlIll.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIIl + llllllllllllllllllIlIIlllIIIlIll.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIll += llllllllllllllllllIlIIlllIIIlIll.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIIl += llllllllllllllllllIlIIlllIIIlIll.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1913.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIll + llllllllllllllllllIlIIlllIIIlIlI.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIIl + llllllllllllllllllIlIIlllIIIlIlI.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIll += llllllllllllllllllIlIIlllIIIlIlI.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIIl += llllllllllllllllllIlIIlllIIIlIlI.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1849.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIll + llllllllllllllllllIlIIlllIIIlIIl.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get(), llllllllllllllllllIlIIlllIIIllIl.method_23318(), llllllllllllllllllIlIIlllIIIIIIl + llllllllllllllllllIlIIlllIIIlIIl.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get());
                llllllllllllllllllIlIIlllIIIIIll += llllllllllllllllllIlIIlllIIIlIIl.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIIl += llllllllllllllllllIlIIlllIIIlIIl.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1903.method_1434()) {
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIllIl.method_23317(), llllllllllllllllllIlIIlllIIIIIlI + llllllllllllllllllIlIIlllIIIlIII.field_1351 * 0.4, llllllllllllllllllIlIIlllIIIllIl.method_23321());
                llllllllllllllllllIlIIlllIIIIIll += llllllllllllllllllIlIIlllIIIlIII.field_1352 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
                llllllllllllllllllIlIIlllIIIIIIl += llllllllllllllllllIlIIlllIIIlIII.field_1350 * llllllllllllllllllIlIIlllIIIIIII.distance.get();
            }
            if (llllllllllllllllllIlIIlllIIIIIII.mc.field_1690.field_1832.method_1434()) {
                double llllllllllllllllllIlIIlllIIlIIII = (double)class_3532.method_15357((double)llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23318()) - 0.2;
                llllllllllllllllllIlIIlllIIIllIl.method_5814(llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23317(), llllllllllllllllllIlIIlllIIlIIII, llllllllllllllllllIlIIlllIIIIIII.mc.field_1724.method_23321());
            }
        }
    }

    @Override
    public void onActivate() {
        Phase llllllllllllllllllIlIIlllIlIIIlI;
        assert (llllllllllllllllllIlIIlllIlIIIlI.mc.field_1724 != null);
        llllllllllllllllllIlIIlllIlIIIlI.prevX = llllllllllllllllllIlIIlllIlIIIlI.mc.field_1724.method_23317();
        llllllllllllllllllIlIIlllIlIIIlI.prevY = llllllllllllllllllIlIIlllIlIIIlI.mc.field_1724.method_23318();
        llllllllllllllllllIlIIlllIlIIIlI.prevZ = llllllllllllllllllIlIIlllIlIIIlI.mc.field_1724.method_23321();
    }

    public static enum Mode {
        NRNB,
        Normal;


        private Mode() {
            Mode llllllllllllllllIlIlIlllIIlllIll;
        }
    }
}

