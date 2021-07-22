/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1747
 *  net.minecraft.class_1802
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
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> blocks;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> exp;

    public FastUse() {
        super(Categories.Player, "fast-use", "Allows you to use items at very high speeds.");
        FastUse lllllllllllllllllIlIlllIIIIIIlIl;
        lllllllllllllllllIlIlllIIIIIIlIl.sgGeneral = lllllllllllllllllIlIlllIIIIIIlIl.settings.getDefaultGroup();
        lllllllllllllllllIlIlllIIIIIIlIl.mode = lllllllllllllllllIlIlllIIIIIIlIl.sgGeneral.add(new EnumSetting.Builder().name("mode").description("Which items to fast use.").defaultValue(Mode.All).build());
        lllllllllllllllllIlIlllIIIIIIlIl.exp = lllllllllllllllllIlIlllIIIIIIlIl.sgGeneral.add(new BoolSetting.Builder().name("xP").description("Fast-throws XP bottles if the mode is \"Some\".").defaultValue(false).build());
        lllllllllllllllllIlIlllIIIIIIlIl.blocks = lllllllllllllllllIlIlllIIIIIIlIl.sgGeneral.add(new BoolSetting.Builder().name("blocks").description("Fast-places blocks if the mode is \"Some\".").defaultValue(false).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post lllllllllllllllllIlIlllIIIIIIIlI) {
        FastUse lllllllllllllllllIlIlllIIIIIIIll;
        switch (lllllllllllllllllIlIlllIIIIIIIll.mode.get()) {
            case All: {
                ((MinecraftClientAccessor)lllllllllllllllllIlIlllIIIIIIIll.mc).setItemUseCooldown(0);
                break;
            }
            case Some: {
                if ((!lllllllllllllllllIlIlllIIIIIIIll.exp.get().booleanValue() || lllllllllllllllllIlIlllIIIIIIIll.mc.field_1724.method_6047().method_7909() != class_1802.field_8287 && lllllllllllllllllIlIlllIIIIIIIll.mc.field_1724.method_6079().method_7909() != class_1802.field_8287) && (!lllllllllllllllllIlIlllIIIIIIIll.blocks.get().booleanValue() || !(lllllllllllllllllIlIlllIIIIIIIll.mc.field_1724.method_6047().method_7909() instanceof class_1747) && !(lllllllllllllllllIlIlllIIIIIIIll.mc.field_1724.method_6079().method_7909() instanceof class_1747))) break;
                ((MinecraftClientAccessor)lllllllllllllllllIlIlllIIIIIIIll.mc).setItemUseCooldown(0);
            }
        }
    }

    public static enum Mode {
        All,
        Some;


        private Mode() {
            Mode lllllllllllllllllIIlIIlIIIIIIlIl;
        }
    }
}

