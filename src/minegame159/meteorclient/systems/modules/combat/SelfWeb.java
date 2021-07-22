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
    private final Setting<Boolean> rotate;
    private final Setting<Boolean> doubles;
    private final Setting<Boolean> turnOff;
    private final SettingGroup sgGeneral;
    private final Setting<Mode> mode;
    private final Setting<Integer> range;

    public SelfWeb() {
        super(Categories.Combat, "self-web", "Automatically places webs on you.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode to use for selfweb.").defaultValue(Mode.Normal).build());
        this.range = this.sgGeneral.add(new IntSetting.Builder().name("range").description("How far away the player has to be from you to place webs. Requires Mode to Smart.").defaultValue(3).min(1).sliderMax(7).visible(this::lambda$new$0).build());
        this.doubles = this.sgGeneral.add(new BoolSetting.Builder().name("double-place").description("Places webs in your upper hitbox as well.").defaultValue(false).build());
        this.turnOff = this.sgGeneral.add(new BoolSetting.Builder().name("auto-toggle").description("Toggles off after placing the webs.").defaultValue(true).build());
        this.rotate = this.sgGeneral.add(new BoolSetting.Builder().name("rotate").description("Forces you to rotate downwards when placing webs.").defaultValue(true).build());
    }

    private boolean lambda$new$0() {
        return this.mode.get() == Mode.Smart;
    }

    private void placeWeb() {
        FindItemResult findItemResult = InvUtils.findInHotbar(class_1802.field_8786);
        BlockUtils.place(this.mc.field_1724.method_24515(), findItemResult, this.rotate.get(), 0, false);
        if (this.doubles.get().booleanValue()) {
            BlockUtils.place(this.mc.field_1724.method_24515().method_10069(0, 1, 0), findItemResult, this.rotate.get(), 0, false);
        }
        if (this.turnOff.get().booleanValue()) {
            this.toggle();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$combat$SelfWeb$Mode[this.mode.get().ordinal()]) {
            case 1: {
                this.placeWeb();
                break;
            }
            case 2: {
                if (TargetUtils.getPlayerTarget(this.range.get().intValue(), SortPriority.LowestDistance) == null) break;
                this.placeWeb();
            }
        }
    }

    public static enum Mode {
        Normal,
        Smart;

    }
}

