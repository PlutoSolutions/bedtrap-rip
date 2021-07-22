/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1753
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1802
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private /* synthetic */ short count;
    private final /* synthetic */ Setting<Integer> speed;

    @EventHandler
    public void onTick(TickEvent.Post llllllllllllllllIlllIIIllIIIIlII) {
        SevilaMode llllllllllllllllIlllIIIllIIIIlIl;
        if (Modules.get().isActive(EXPThrower.class) || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6047().method_7909() instanceof class_1779 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6079().method_7909() instanceof class_1779 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6047().method_7909() instanceof class_1776 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6079().method_7909() instanceof class_1776 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6047().method_7909() instanceof class_1779 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6079().method_7909() instanceof class_1779 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6047().method_7909() instanceof class_1753 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.method_6079().method_7909() instanceof class_1753 || llllllllllllllllIlllIIIllIIIIlIl.mc.field_1724.field_7514.method_7372(2).method_7909() == class_1802.field_8833) {
            return;
        }
        llllllllllllllllIlllIIIllIIIIlIl.count = (short)(llllllllllllllllIlllIIIllIIIIlIl.count + llllllllllllllllIlllIIIllIIIIlIl.speed.get());
        if (llllllllllllllllIlllIIIllIIIIlIl.count > 180) {
            llllllllllllllllIlllIIIllIIIIlIl.count = (short)-180;
        }
        Rotations.rotate(llllllllllllllllIlllIIIllIIIIlIl.count, 0.0);
    }

    public SevilaMode() {
        super(Categories.BedTrap, "sevila-mode", "Looks like spinbot from csgo.");
        SevilaMode llllllllllllllllIlllIIIllIIIIlll;
        llllllllllllllllIlllIIIllIIIIlll.sgGeneral = llllllllllllllllIlllIIIllIIIIlll.settings.createGroup("General");
        llllllllllllllllIlllIIIllIIIIlll.count = 0;
        llllllllllllllllIlllIIIllIIIIlll.speed = llllllllllllllllIlllIIIllIIIIlll.sgGeneral.add(new IntSetting.Builder().name("rotation-speed").description("The speed at which you rotate.").defaultValue(5).min(0).sliderMax(100).build());
    }
}

