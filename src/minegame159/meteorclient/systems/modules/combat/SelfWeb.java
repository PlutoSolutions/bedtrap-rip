/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.SortPriority;
import minegame159.meteorclient.utils.entity.TargetUtils;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.world.BlockUtils;
import net.minecraft.class_1802;

public class SelfWeb
extends Module {
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> doubles;
    private final /* synthetic */ Setting<Boolean> turnOff;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> range;

    public SelfWeb() {
        super(Categories.Combat, "self-web", "Automatically places webs on you.");
        SelfWeb lIllIlIlIIIl;
        lIllIlIlIIIl.sgGeneral = lIllIlIlIIIl.settings.getDefaultGroup();
        lIllIlIlIIIl.mode = lIllIlIlIIIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode to use for selfweb.").defaultValue(Mode.Normal).build());
        lIllIlIlIIIl.range = lIllIlIlIIIl.sgGeneral.add(new IntSetting.Builder().name("range").description("How far away the player has to be from you to place webs. Requires Mode to Smart.").defaultValue(3).min(1).sliderMax(7).visible(() -> {
            SelfWeb lIllIlIIIIll;
            return lIllIlIIIIll.mode.get() == Mode.Smart;
        }).build());
        lIllIlIlIIIl.doubles = lIllIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("double-place").description("Places webs in your upper hitbox as well.").defaultValue(false).build());
        lIllIlIlIIIl.turnOff = lIllIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("auto-toggle").description("Toggles off after placing the webs.").defaultValue(true).build());
        lIllIlIlIIIl.rotate = lIllIlIlIIIl.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Forces you to rotate downwards when placing webs.").defaultValue(true).build());
    }

    private void placeWeb() {
        SelfWeb lIllIlIIlIIl;
        FindItemResult lIllIlIIlIII = InvUtils.findInHotbar(class_1802.field_8786);
        BlockUtils.place(lIllIlIIlIIl.mc.field_1724.method_24515(), lIllIlIIlIII, lIllIlIIlIIl.rotate.get(), 0, false);
        if (lIllIlIIlIIl.doubles.get().booleanValue()) {
            BlockUtils.place(lIllIlIIlIIl.mc.field_1724.method_24515().method_10069(0, 1, 0), lIllIlIIlIII, lIllIlIIlIIl.rotate.get(), 0, false);
        }
        if (lIllIlIIlIIl.turnOff.get().booleanValue()) {
            lIllIlIIlIIl.toggle();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre lIllIlIIllIl) {
        SelfWeb lIllIlIIlllI;
        switch (lIllIlIIlllI.mode.get()) {
            case Normal: {
                lIllIlIIlllI.placeWeb();
                break;
            }
            case Smart: {
                if (TargetUtils.getPlayerTarget(lIllIlIIlllI.range.get().intValue(), SortPriority.LowestDistance) == null) break;
                lIllIlIIlllI.placeWeb();
            }
        }
    }

    public static enum Mode {
        Normal,
        Smart;


        private Mode() {
            Mode llllllllllllllllIlllIIlIllIlIlIl;
        }
    }
}

