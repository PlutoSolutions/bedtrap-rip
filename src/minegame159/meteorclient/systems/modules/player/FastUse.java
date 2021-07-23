/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1747;
import net.minecraft.class_1802;

public class FastUse
extends Module {
    private final Setting<Mode> mode;
    private final Setting<Boolean> blocks;
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> exp;

    public FastUse() {
        super(Categories.Player, "fast-use", "Allows you to use items at very high speeds.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which items to fast use.").defaultValue(Mode.All).build());
        this.exp = this.sgGeneral.add(new BoolSetting.Builder().name("xP").description("Fast-throws XP bottles if the mode is \"Some\".").defaultValue(false).build());
        this.blocks = this.sgGeneral.add(new BoolSetting.Builder().name("blocks").description("Fast-places blocks if the mode is \"Some\".").defaultValue(false).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$player$FastUse$Mode[this.mode.get().ordinal()]) {
            case 1: {
                ((MinecraftClientAccessor)this.mc).setItemUseCooldown(0);
                break;
            }
            case 2: {
                if ((!this.exp.get().booleanValue() || this.mc.field_1724.method_6047().method_7909() != class_1802.field_8287 && this.mc.field_1724.method_6079().method_7909() != class_1802.field_8287) && (!this.blocks.get().booleanValue() || !(this.mc.field_1724.method_6047().method_7909() instanceof class_1747) && !(this.mc.field_1724.method_6079().method_7909() instanceof class_1747))) break;
                ((MinecraftClientAccessor)this.mc).setItemUseCooldown(0);
            }
        }
    }

    public static enum Mode {
        All,
        Some;

    }
}

