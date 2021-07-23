/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.player.EXPThrower;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1753;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1802;

public class SevilaMode
extends Module {
    private final SettingGroup sgGeneral;
    private short count;
    private final Setting<Integer> speed;

    @EventHandler
    public void onTick(TickEvent.Post post) {
        if (Modules.get().isActive(EXPThrower.class) || this.mc.field_1724.method_6047().method_7909() instanceof class_1779 || this.mc.field_1724.method_6079().method_7909() instanceof class_1779 || this.mc.field_1724.method_6047().method_7909() instanceof class_1776 || this.mc.field_1724.method_6079().method_7909() instanceof class_1776 || this.mc.field_1724.method_6047().method_7909() instanceof class_1779 || this.mc.field_1724.method_6079().method_7909() instanceof class_1779 || this.mc.field_1724.method_6047().method_7909() instanceof class_1753 || this.mc.field_1724.method_6079().method_7909() instanceof class_1753 || this.mc.field_1724.field_7514.method_7372(2).method_7909() == class_1802.field_8833) {
            return;
        }
        this.count = (short)(this.count + this.speed.get());
        if (this.count > 180) {
            this.count = (short)-180;
        }
        Rotations.rotate(this.count, 0.0);
    }

    public SevilaMode() {
        super(Categories.BedTrap, "sevila-mode", "Looks like spinbot from csgo.");
        this.sgGeneral = this.settings.createGroup("General");
        this.count = 0;
        this.speed = this.sgGeneral.add(new IntSetting.Builder().name("rotation-speed").description("The speed at which you rotate.").defaultValue(5).min(0).sliderMax(100).build());
    }
}

