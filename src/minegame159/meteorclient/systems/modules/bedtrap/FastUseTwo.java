/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1774
 *  net.minecraft.class_1779
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1774;
import net.minecraft.class_1779;
import net.minecraft.class_1802;

public class FastUseTwo
extends Module {
    private final /* synthetic */ Setting<Item> itemChoose;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> firework;

    private void setClickDelay() {
        FastUseTwo llIIlllIlIIIlII;
        ((MinecraftClientAccessor)llIIlllIlIIIlII.mc).setItemUseCooldown(0);
    }

    @EventHandler
    private void onTick(TickEvent.Post llIIlllIlIIlIII) {
        FastUseTwo llIIlllIlIIIlll;
        if (llIIlllIlIIIlll.itemChoose.get() == Item.All) {
            if (llIIlllIlIIIlll.firework.get().booleanValue() && llIIlllIlIIIlll.mc.field_1724.method_6047().method_7909() == class_1802.field_8639 || llIIlllIlIIIlll.mc.field_1724.method_6079().method_7909() == class_1802.field_8639) {
                return;
            }
            llIIlllIlIIIlll.setClickDelay();
        }
        if (llIIlllIlIIIlll.itemChoose.get() == Item.Exp) {
            assert (llIIlllIlIIIlll.mc.field_1724 != null);
            if (llIIlllIlIIIlll.mc.field_1724.method_6047().method_7909() instanceof class_1779 || llIIlllIlIIIlll.mc.field_1724.method_6079().method_7909() instanceof class_1779) {
                llIIlllIlIIIlll.setClickDelay();
            }
        }
        if (llIIlllIlIIIlll.itemChoose.get() == Item.Crystal) {
            assert (llIIlllIlIIIlll.mc.field_1724 != null);
            if (llIIlllIlIIIlll.mc.field_1724.method_6047().method_7909() instanceof class_1774 || llIIlllIlIIIlll.mc.field_1724.method_6079().method_7909() instanceof class_1774) {
                llIIlllIlIIIlll.setClickDelay();
            }
        }
        if (llIIlllIlIIIlll.itemChoose.get() == Item.ExpAndCrystal) {
            assert (llIIlllIlIIIlll.mc.field_1724 != null);
            if (llIIlllIlIIIlll.mc.field_1724.method_6047().method_7909() instanceof class_1774 || llIIlllIlIIIlll.mc.field_1724.method_6047().method_7909() instanceof class_1779 || llIIlllIlIIIlll.mc.field_1724.method_6079().method_7909() instanceof class_1774 || llIIlllIlIIIlll.mc.field_1724.method_6079().method_7909() instanceof class_1779) {
                llIIlllIlIIIlll.setClickDelay();
            }
        }
    }

    public FastUseTwo() {
        super(Categories.BedTrap, "fast-use+", "Removes item cooldown.");
        FastUseTwo llIIlllIlIIlIll;
        llIIlllIlIIlIll.sgGeneral = llIIlllIlIIlIll.settings.getDefaultGroup();
        llIIlllIlIIlIll.itemChoose = llIIlllIlIIlIll.sgGeneral.add(new EnumSetting.Builder().name("Which item").description("item").defaultValue(Item.All).build());
        llIIlllIlIIlIll.firework = llIIlllIlIIlIll.sgGeneral.add(new BoolSetting.Builder().name("anti-firework").description("Prevent to spam fireworks.").defaultValue(true).build());
    }

    public static enum Item {
        All,
        Exp,
        Crystal,
        ExpAndCrystal;


        private Item() {
            Item lIlIlllIlIlllII;
        }
    }
}

