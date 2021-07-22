/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1761
 *  net.minecraft.class_408
 *  net.minecraft.class_463
 *  net.minecraft.class_471
 *  net.minecraft.class_481
 *  net.minecraft.class_497
 *  net.minecraft.class_498
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.mixin.CreativeInventoryScreenAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_1761;
import net.minecraft.class_408;
import net.minecraft.class_463;
import net.minecraft.class_471;
import net.minecraft.class_481;
import net.minecraft.class_497;
import net.minecraft.class_498;

public class GUIMove
extends Module {
    private final /* synthetic */ Setting<Boolean> sprint;
    private final /* synthetic */ Setting<Screens> screens;
    private final /* synthetic */ Setting<Boolean> arrowsRotate;
    private final /* synthetic */ Setting<Boolean> jump;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> sneak;
    private final /* synthetic */ Setting<Double> rotateSpeed;

    public GUIMove() {
        super(Categories.Movement, "gui-move", "Allows you to perform various actions while in GUIs.");
        GUIMove lllllllllllllllllIlIIIllIIIIllll;
        lllllllllllllllllIlIIIllIIIIllll.sgGeneral = lllllllllllllllllIlIIIllIIIIllll.settings.getDefaultGroup();
        lllllllllllllllllIlIIIllIIIIllll.screens = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new EnumSetting.Builder().name("gUIs").description("Which GUIs to move in.").defaultValue(Screens.Inventory).build());
        lllllllllllllllllIlIIIllIIIIllll.jump = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new BoolSetting.Builder().name("jump").description("Allows you to jump while in GUIs.").defaultValue(true).onChanged(lllllllllllllllllIlIIIlIllllIIIl -> {
            GUIMove lllllllllllllllllIlIIIlIllllIIII;
            if (lllllllllllllllllIlIIIlIllllIIII.isActive() && !lllllllllllllllllIlIIIlIllllIIIl.booleanValue()) {
                lllllllllllllllllIlIIIlIllllIIII.mc.field_1690.field_1903.method_23481(false);
            }
        }).build());
        lllllllllllllllllIlIIIllIIIIllll.sneak = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new BoolSetting.Builder().name("sneak").description("Allows you to sneak while in GUIs.").defaultValue(true).onChanged(lllllllllllllllllIlIIIlIllllIlll -> {
            GUIMove lllllllllllllllllIlIIIlIllllIllI;
            if (lllllllllllllllllIlIIIlIllllIllI.isActive() && !lllllllllllllllllIlIIIlIllllIlll.booleanValue()) {
                lllllllllllllllllIlIIIlIllllIllI.mc.field_1690.field_1832.method_23481(false);
            }
        }).build());
        lllllllllllllllllIlIIIllIIIIllll.sprint = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new BoolSetting.Builder().name("sprint").description("Allows you to sprint while in GUIs.").defaultValue(true).onChanged(lllllllllllllllllIlIIIlIllllllIl -> {
            GUIMove lllllllllllllllllIlIIIlIllllllII;
            if (lllllllllllllllllIlIIIlIllllllII.isActive() && !lllllllllllllllllIlIIIlIllllllIl.booleanValue()) {
                lllllllllllllllllIlIIIlIllllllII.mc.field_1690.field_1867.method_23481(false);
            }
        }).build());
        lllllllllllllllllIlIIIllIIIIllll.arrowsRotate = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new BoolSetting.Builder().name("arrows-rotate").description("Allows you to use your arrow keys to rotate while in GUIs.").defaultValue(true).build());
        lllllllllllllllllIlIIIllIIIIllll.rotateSpeed = lllllllllllllllllIlIIIllIIIIllll.sgGeneral.add(new DoubleSetting.Builder().name("rotate-speed").description("Rotation speed while in GUIs.").defaultValue(4.0).min(0.0).build());
    }

    @Override
    public void onDeactivate() {
        GUIMove lllllllllllllllllIlIIIllIIIIlIll;
        lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1894.method_23481(false);
        lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1881.method_23481(false);
        lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1913.method_23481(false);
        lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1849.method_23481(false);
        if (lllllllllllllllllIlIIIllIIIIlIll.jump.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1903.method_23481(false);
        }
        if (lllllllllllllllllIlIIIllIIIIlIll.sneak.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1832.method_23481(false);
        }
        if (lllllllllllllllllIlIIIllIIIIlIll.sprint.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIlIll.mc.field_1690.field_1867.method_23481(false);
        }
    }

    private boolean skip() {
        GUIMove lllllllllllllllllIlIIIllIIIIIIlI;
        return lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 == null || Modules.get().isActive(Freecam.class) || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_481 && ((CreativeInventoryScreenAccessor)lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755).getSelectedTab() == class_1761.field_7915.method_7741() || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_408 || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_498 || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_471 || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_463 || lllllllllllllllllIlIIIllIIIIIIlI.mc.field_1755 instanceof class_497;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIlIIIllIIIIIllI) {
        GUIMove lllllllllllllllllIlIIIllIIIIIlIl;
        if (lllllllllllllllllIlIIIllIIIIIlIl.skip()) {
            return;
        }
        if (lllllllllllllllllIlIIIllIIIIIlIl.screens.get() == Screens.GUI && !(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1755 instanceof WidgetScreen)) {
            return;
        }
        if (lllllllllllllllllIlIIIllIIIIIlIl.screens.get() == Screens.Inventory && lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1755 instanceof WidgetScreen) {
            return;
        }
        lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1894.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1894));
        lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1881.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1881));
        lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1913.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1913));
        lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1849.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1849));
        if (lllllllllllllllllIlIIIllIIIIIlIl.jump.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1903.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1903));
        }
        if (lllllllllllllllllIlIIIllIIIIIlIl.sneak.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1832.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1832));
        }
        if (lllllllllllllllllIlIIIllIIIIIlIl.sprint.get().booleanValue()) {
            lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1867.method_23481(Input.isPressed(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1690.field_1867));
        }
        if (lllllllllllllllllIlIIIllIIIIIlIl.arrowsRotate.get().booleanValue()) {
            int lllllllllllllllllIlIIIllIIIIlIII = 0;
            while ((double)lllllllllllllllllIlIIIllIIIIlIII < lllllllllllllllllIlIIIllIIIIIlIl.rotateSpeed.get() * 2.0) {
                if (Input.isKeyPressed(263)) {
                    lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_6031 = (float)((double)lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_6031 - 0.5);
                }
                if (Input.isKeyPressed(262)) {
                    lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_6031 = (float)((double)lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_6031 + 0.5);
                }
                if (Input.isKeyPressed(265)) {
                    lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965 = (float)((double)lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965 - 0.5);
                }
                if (Input.isKeyPressed(264)) {
                    lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965 = (float)((double)lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965 + 0.5);
                }
                ++lllllllllllllllllIlIIIllIIIIlIII;
            }
            lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965 = Utils.clamp(lllllllllllllllllIlIIIllIIIIIlIl.mc.field_1724.field_5965, -90.0f, 90.0f);
        }
    }

    public static enum Screens {
        GUI,
        Inventory,
        Both;


        private Screens() {
            Screens llllllllllllllllllIllIllIlIlIIII;
        }
    }
}

