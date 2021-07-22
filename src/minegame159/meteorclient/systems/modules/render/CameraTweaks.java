/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5498
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.ChangePerspectiveEvent;
import minegame159.meteorclient.events.meteor.MouseScrollEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_5498;

public class CameraTweaks
extends Module {
    public /* synthetic */ double distance;
    private final /* synthetic */ Setting<Double> cameraDistance;
    private final /* synthetic */ Setting<Double> scrollSensitivity;
    private final /* synthetic */ Setting<Boolean> clip;
    private final /* synthetic */ SettingGroup sgGeneral;

    public double getDistance() {
        CameraTweaks llIIIIlIIIlIlI;
        return llIIIIlIIIlIlI.isActive() ? llIIIIlIIIlIlI.distance : 4.0;
    }

    public CameraTweaks() {
        super(Categories.Render, "camera-tweaks", "Allows modification of the third person camera.");
        CameraTweaks llIIIIlIIllllI;
        llIIIIlIIllllI.sgGeneral = llIIIIlIIllllI.settings.getDefaultGroup();
        llIIIIlIIllllI.clip = llIIIIlIIllllI.sgGeneral.add(new BoolSetting.Builder().name("clip").description("Allows the camera to clip through blocks.").defaultValue(true).build());
        llIIIIlIIllllI.cameraDistance = llIIIIlIIllllI.sgGeneral.add(new DoubleSetting.Builder().name("camera-distance").description("The distance the third person camera is from the player.").defaultValue(4.0).min(0.0).onChanged(llIIIIlIIIIllI -> {
            llIIIIlIIIIlIl.distance = llIIIIlIIIIllI;
        }).build());
        llIIIIlIIllllI.scrollSensitivity = llIIIIlIIllllI.sgGeneral.add(new DoubleSetting.Builder().name("distance-scroll-sensitivity").description("Scroll sensitivity when changing the cameras distance. 0 to disable.").defaultValue(1.0).min(0.0).build());
    }

    @Override
    public void onActivate() {
        CameraTweaks llIIIIlIIllIlI;
        llIIIIlIIllIlI.distance = llIIIIlIIllIlI.cameraDistance.get();
    }

    @EventHandler
    private void onPerspectiveChanged(ChangePerspectiveEvent llIIIIlIIlIlll) {
        CameraTweaks llIIIIlIIllIII;
        llIIIIlIIllIII.distance = llIIIIlIIllIII.cameraDistance.get();
    }

    @EventHandler
    private void onMouseScroll(MouseScrollEvent llIIIIlIIlIIII) {
        CameraTweaks llIIIIlIIlIIll;
        if (llIIIIlIIlIIll.mc.field_1690.method_31044() == class_5498.field_26664) {
            return;
        }
        if (llIIIIlIIlIIll.scrollSensitivity.get() > 0.0) {
            llIIIIlIIlIIll.distance += llIIIIlIIlIIII.value * 0.25 * (llIIIIlIIlIIll.scrollSensitivity.get() * llIIIIlIIlIIll.distance);
            llIIIIlIIlIIII.cancel();
        }
    }

    public boolean clip() {
        CameraTweaks llIIIIlIIIllIl;
        return llIIIIlIIIllIl.isActive() && llIIIIlIIIllIl.clip.get() != false;
    }
}

