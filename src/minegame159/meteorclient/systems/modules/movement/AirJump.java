/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.misc.input.KeyAction;

public class AirJump
extends Module {
    private int level;
    private final Setting<Boolean> onGround;
    private final Setting<Boolean> maintainY;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> onHold;

    public AirJump() {
        super(Categories.Movement, "air-jump", "Lets you jump in the air.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.maintainY = this.sgGeneral.add(new BoolSetting.Builder().name("maintain-level").description("Maintains your current Y level.").defaultValue(false).build());
        this.onHold = this.sgGeneral.add(new BoolSetting.Builder().name("on-hold").description("Whether or not to air jump if you hold down the space bar.").defaultValue(true).build());
        this.onGround = this.sgGeneral.add(new BoolSetting.Builder().name("on-ground").description("Whether to airjump if you are on the ground.").defaultValue(false).build());
        this.level = 0;
    }

    @EventHandler
    private void onKey(KeyEvent keyEvent) {
        if (Modules.get().isActive(Freecam.class) || this.mc.field_1755 != null || !this.onGround.get().booleanValue() && this.mc.field_1724.method_24828()) {
            return;
        }
        if ((keyEvent.action == KeyAction.Press || keyEvent.action == KeyAction.Repeat && this.onHold.get().booleanValue()) && this.mc.field_1690.field_1903.method_1417(keyEvent.key, 0)) {
            this.mc.field_1724.method_6043();
            this.level = this.mc.field_1724.method_24515().method_10264();
        }
        if ((keyEvent.action == KeyAction.Press || keyEvent.action == KeyAction.Repeat && this.onHold.get().booleanValue()) && this.mc.field_1690.field_1832.method_1417(keyEvent.key, 0)) {
            --this.level;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (Modules.get().isActive(Freecam.class) || !this.onGround.get().booleanValue() && this.mc.field_1724.method_24828()) {
            return;
        }
        if (this.maintainY.get().booleanValue() && this.mc.field_1724.method_24515().method_10264() == this.level) {
            this.mc.field_1724.method_6043();
        }
    }
}

