/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.ClientPlayerEntityAccessor;
import minegame159.meteorclient.mixininterface.IHorseBaseEntity;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1496;

public class EntityControl
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> maxJump;

    @Override
    public void onDeactivate() {
        if (!Utils.canUpdate() || this.mc.field_1687.method_18112() == null) {
            return;
        }
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1496)) continue;
            ((IHorseBaseEntity)class_12972).setSaddled(false);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1496)) continue;
            ((IHorseBaseEntity)class_12972).setSaddled(true);
        }
        if (this.maxJump.get().booleanValue()) {
            ((ClientPlayerEntityAccessor)this.mc.field_1724).setMountJumpStrength(1.0f);
        }
    }

    public EntityControl() {
        super(Categories.Movement, "entity-control", "Lets you control rideable entities without a saddle.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.maxJump = this.sgGeneral.add(new BoolSetting.Builder().name("max-jump").description("Sets jump power to maximum.").defaultValue(true).build());
    }
}

