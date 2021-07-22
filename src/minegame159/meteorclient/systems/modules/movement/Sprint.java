/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;

public class Sprint
extends Module {
    private final /* synthetic */ Setting<Boolean> whenStationary;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public void onDeactivate() {
        Sprint llIllIIIIIlllII;
        llIllIIIIIlllII.mc.field_1724.method_5728(false);
    }

    public Sprint() {
        super(Categories.Movement, "sprint", "Automatically sprints.");
        Sprint llIllIIIIIlllll;
        llIllIIIIIlllll.sgGeneral = llIllIIIIIlllll.settings.getDefaultGroup();
        llIllIIIIIlllll.whenStationary = llIllIIIIIlllll.sgGeneral.add(new BoolSetting.Builder().name("when-stationary").description("Continues sprinting even if you do not move.").defaultValue(true).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post llIllIIIIIllIII) {
        Sprint llIllIIIIIllIIl;
        if (llIllIIIIIllIIl.mc.field_1724.field_6250 > 0.0f && !llIllIIIIIllIIl.whenStationary.get().booleanValue()) {
            llIllIIIIIllIIl.mc.field_1724.method_5728(true);
        } else if (llIllIIIIIllIIl.whenStationary.get().booleanValue()) {
            llIllIIIIIllIIl.mc.field_1724.method_5728(true);
        }
    }
}

