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
    private /* synthetic */ int level;
    private final /* synthetic */ Setting<Boolean> onGround;
    private final /* synthetic */ Setting<Boolean> maintainY;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> onHold;

    public AirJump() {
        super(Categories.Movement, "air-jump", "Lets you jump in the air.");
        AirJump llllIllIIlIl;
        llllIllIIlIl.sgGeneral = llllIllIIlIl.settings.getDefaultGroup();
        llllIllIIlIl.maintainY = llllIllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("maintain-level").description("Maintains your current Y level.").defaultValue(false).build());
        llllIllIIlIl.onHold = llllIllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("on-hold").description("Whether or not to air jump if you hold down the space bar.").defaultValue(true).build());
        llllIllIIlIl.onGround = llllIllIIlIl.sgGeneral.add(new BoolSetting.Builder().name("on-ground").description("Whether to airjump if you are on the ground.").defaultValue(false).build());
        llllIllIIlIl.level = 0;
    }

    @EventHandler
    private void onKey(KeyEvent llllIlIlllll) {
        AirJump llllIllIIIlI;
        if (Modules.get().isActive(Freecam.class) || llllIllIIIlI.mc.field_1755 != null || !llllIllIIIlI.onGround.get().booleanValue() && llllIllIIIlI.mc.field_1724.method_24828()) {
            return;
        }
        if ((llllIlIlllll.action == KeyAction.Press || llllIlIlllll.action == KeyAction.Repeat && llllIllIIIlI.onHold.get().booleanValue()) && llllIllIIIlI.mc.field_1690.field_1903.method_1417(llllIlIlllll.key, 0)) {
            llllIllIIIlI.mc.field_1724.method_6043();
            llllIllIIIlI.level = llllIllIIIlI.mc.field_1724.method_24515().method_10264();
        }
        if ((llllIlIlllll.action == KeyAction.Press || llllIlIlllll.action == KeyAction.Repeat && llllIllIIIlI.onHold.get().booleanValue()) && llllIllIIIlI.mc.field_1690.field_1832.method_1417(llllIlIlllll.key, 0)) {
            --llllIllIIIlI.level;
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post llllIlIlllII) {
        AirJump llllIlIllIll;
        if (Modules.get().isActive(Freecam.class) || !llllIlIllIll.onGround.get().booleanValue() && llllIlIllIll.mc.field_1724.method_24828()) {
            return;
        }
        if (llllIlIllIll.maintainY.get().booleanValue() && llllIlIllIll.mc.field_1724.method_24515().method_10264() == llllIlIllIll.level) {
            llllIlIllIll.mc.field_1724.method_6043();
        }
    }
}

