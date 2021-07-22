/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1496
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> maxJump;

    @Override
    public void onDeactivate() {
        EntityControl llllllllllllllllIlllIlllllIlllll;
        if (!Utils.canUpdate() || llllllllllllllllIlllIlllllIlllll.mc.field_1687.method_18112() == null) {
            return;
        }
        for (class_1297 llllllllllllllllIlllIllllllIIIIl : llllllllllllllllIlllIlllllIlllll.mc.field_1687.method_18112()) {
            if (!(llllllllllllllllIlllIllllllIIIIl instanceof class_1496)) continue;
            ((IHorseBaseEntity)llllllllllllllllIlllIllllllIIIIl).setSaddled(false);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre llllllllllllllllIlllIlllllIlIlll) {
        EntityControl llllllllllllllllIlllIlllllIlIllI;
        for (class_1297 llllllllllllllllIlllIlllllIllIIl : llllllllllllllllIlllIlllllIlIllI.mc.field_1687.method_18112()) {
            if (!(llllllllllllllllIlllIlllllIllIIl instanceof class_1496)) continue;
            ((IHorseBaseEntity)llllllllllllllllIlllIlllllIllIIl).setSaddled(true);
        }
        if (llllllllllllllllIlllIlllllIlIllI.maxJump.get().booleanValue()) {
            ((ClientPlayerEntityAccessor)llllllllllllllllIlllIlllllIlIllI.mc.field_1724).setMountJumpStrength(1.0f);
        }
    }

    public EntityControl() {
        super(Categories.Movement, "entity-control", "Lets you control rideable entities without a saddle.");
        EntityControl llllllllllllllllIlllIllllllIIlIl;
        llllllllllllllllIlllIllllllIIlIl.sgGeneral = llllllllllllllllIlllIllllllIIlIl.settings.getDefaultGroup();
        llllllllllllllllIlllIllllllIIlIl.maxJump = llllllllllllllllIlllIllllllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("max-jump").description("Sets jump power to maximum.").defaultValue(true).build());
    }
}

