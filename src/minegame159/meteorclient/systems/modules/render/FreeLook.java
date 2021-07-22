/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5498
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import net.minecraft.class_5498;

public class FreeLook
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    public /* synthetic */ float cameraPitch;
    public final /* synthetic */ Setting<Double> sensitivity;
    private /* synthetic */ class_5498 prePers;
    private final /* synthetic */ SettingGroup sgArrows;
    public /* synthetic */ float cameraYaw;
    public final /* synthetic */ Setting<Mode> mode;
    public final /* synthetic */ Setting<Boolean> arrows;
    private final /* synthetic */ Setting<Double> arrowSpeed;
    public final /* synthetic */ Setting<Boolean> togglePerpective;

    public FreeLook() {
        super(Categories.Render, "free-look", "Allows more rotation options in third person.");
        FreeLook lllllllllllllllllIlIlIIIllIllIIl;
        lllllllllllllllllIlIlIIIllIllIIl.sgGeneral = lllllllllllllllllIlIlIIIllIllIIl.settings.getDefaultGroup();
        lllllllllllllllllIlIlIIIllIllIIl.sgArrows = lllllllllllllllllIlIlIIIllIllIIl.settings.createGroup("Arrows");
        lllllllllllllllllIlIlIIIllIllIIl.mode = lllllllllllllllllIlIlIIIllIllIIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which entity to rotate.").defaultValue(Mode.Player).build());
        lllllllllllllllllIlIlIIIllIllIIl.togglePerpective = lllllllllllllllllIlIlIIIllIllIIl.sgGeneral.add(new BoolSetting.Builder().name("toggle-perspective").description("Changes your perspective on toggle.").defaultValue(true).build());
        lllllllllllllllllIlIlIIIllIllIIl.sensitivity = lllllllllllllllllIlIlIIIllIllIIl.sgGeneral.add(new DoubleSetting.Builder().name("camera-sensitivity").description("How fast the camera moves in camera mode.").defaultValue(8.0).min(0.0).sliderMax(10.0).build());
        lllllllllllllllllIlIlIIIllIllIIl.arrows = lllllllllllllllllIlIlIIIllIllIIl.sgArrows.add(new BoolSetting.Builder().name("arrows-control-opposite").description("Allows you to control the other entities rotation with the arrow keys.").defaultValue(true).build());
        lllllllllllllllllIlIlIIIllIllIIl.arrowSpeed = lllllllllllllllllIlIlIIIllIllIIl.sgArrows.add(new DoubleSetting.Builder().name("arrow-speed").description("Rotation speed with arrow keys.").defaultValue(4.0).min(0.0).build());
    }

    public boolean cameraMode() {
        FreeLook lllllllllllllllllIlIlIIIllIIlllI;
        return lllllllllllllllllIlIlIIIllIIlllI.isActive() && lllllllllllllllllIlIlIIIllIIlllI.mc.field_1690.method_31044() == class_5498.field_26665 && lllllllllllllllllIlIlIIIllIIlllI.mode.get() == Mode.Camera;
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlIlIIIllIIlIII) {
        FreeLook lllllllllllllllllIlIlIIIllIIIlll;
        if (lllllllllllllllllIlIlIIIllIIIlll.arrows.get().booleanValue()) {
            int lllllllllllllllllIlIlIIIllIIlIlI = 0;
            while ((double)lllllllllllllllllIlIlIIIllIIlIlI < lllllllllllllllllIlIlIIIllIIIlll.arrowSpeed.get() * 2.0) {
                switch (lllllllllllllllllIlIlIIIllIIIlll.mode.get()) {
                    case Player: {
                        if (Input.isKeyPressed(263)) {
                            lllllllllllllllllIlIlIIIllIIIlll.cameraYaw = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.cameraYaw - 0.5);
                        }
                        if (Input.isKeyPressed(262)) {
                            lllllllllllllllllIlIlIIIllIIIlll.cameraYaw = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.cameraYaw + 0.5);
                        }
                        if (Input.isKeyPressed(265)) {
                            lllllllllllllllllIlIlIIIllIIIlll.cameraPitch = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.cameraPitch - 0.5);
                        }
                        if (!Input.isKeyPressed(264)) break;
                        lllllllllllllllllIlIlIIIllIIIlll.cameraPitch = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.cameraPitch + 0.5);
                        break;
                    }
                    case Camera: {
                        if (Input.isKeyPressed(263)) {
                            lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_6031 = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_6031 - 0.5);
                        }
                        if (Input.isKeyPressed(262)) {
                            lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_6031 = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_6031 + 0.5);
                        }
                        if (Input.isKeyPressed(265)) {
                            lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965 = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965 - 0.5);
                        }
                        if (!Input.isKeyPressed(264)) break;
                        lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965 = (float)((double)lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965 + 0.5);
                    }
                }
                ++lllllllllllllllllIlIlIIIllIIlIlI;
            }
        }
        lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965 = Utils.clamp(lllllllllllllllllIlIlIIIllIIIlll.mc.field_1724.field_5965, -90.0f, 90.0f);
        lllllllllllllllllIlIlIIIllIIIlll.cameraPitch = Utils.clamp(lllllllllllllllllIlIlIIIllIIIlll.cameraPitch, -90.0f, 90.0f);
    }

    public boolean playerMode() {
        FreeLook lllllllllllllllllIlIlIIIllIlIIII;
        return lllllllllllllllllIlIlIIIllIlIIII.isActive() && lllllllllllllllllIlIlIIIllIlIIII.mc.field_1690.method_31044() == class_5498.field_26665 && lllllllllllllllllIlIlIIIllIlIIII.mode.get() == Mode.Player;
    }

    @Override
    public void onDeactivate() {
        FreeLook lllllllllllllllllIlIlIIIllIlIlII;
        if (lllllllllllllllllIlIlIIIllIlIlII.mc.field_1690.method_31044() != lllllllllllllllllIlIlIIIllIlIlII.prePers && lllllllllllllllllIlIlIIIllIlIlII.togglePerpective.get().booleanValue()) {
            lllllllllllllllllIlIlIIIllIlIlII.mc.field_1690.method_31043(lllllllllllllllllIlIlIIIllIlIlII.prePers);
        }
    }

    @Override
    public void onActivate() {
        FreeLook lllllllllllllllllIlIlIIIllIlIllI;
        lllllllllllllllllIlIlIIIllIlIllI.cameraYaw = lllllllllllllllllIlIlIIIllIlIllI.mc.field_1724.field_6031;
        lllllllllllllllllIlIlIIIllIlIllI.cameraPitch = lllllllllllllllllIlIlIIIllIlIllI.mc.field_1724.field_5965;
        lllllllllllllllllIlIlIIIllIlIllI.prePers = lllllllllllllllllIlIlIIIllIlIllI.mc.field_1690.method_31044();
        if (lllllllllllllllllIlIlIIIllIlIllI.prePers != class_5498.field_26665 && lllllllllllllllllIlIlIIIllIlIllI.togglePerpective.get().booleanValue()) {
            lllllllllllllllllIlIlIIIllIlIllI.mc.field_1690.method_31043(class_5498.field_26665);
        }
    }

    public static enum Mode {
        Player,
        Camera;


        private Mode() {
            Mode lllllllllllllllllIlIIllIlIlIIlll;
        }
    }
}

