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
    private final Setting<Item> itemChoose;
    static final boolean $assertionsDisabled = !FastUseTwo.class.desiredAssertionStatus();
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> firework;

    private void setClickDelay() {
        ((MinecraftClientAccessor)this.mc).setItemUseCooldown(0);
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (this.itemChoose.get() == Item.All) {
            if (this.firework.get().booleanValue() && this.mc.field_1724.method_6047().method_7909() == class_1802.field_8639 || this.mc.field_1724.method_6079().method_7909() == class_1802.field_8639) {
                return;
            }
            this.setClickDelay();
        }
        if (this.itemChoose.get() == Item.Exp) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1779 || this.mc.field_1724.method_6079().method_7909() instanceof class_1779) {
                this.setClickDelay();
            }
        }
        if (this.itemChoose.get() == Item.Crystal) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1774 || this.mc.field_1724.method_6079().method_7909() instanceof class_1774) {
                this.setClickDelay();
            }
        }
        if (this.itemChoose.get() == Item.ExpAndCrystal) {
            if (!$assertionsDisabled && this.mc.field_1724 == null) {
                throw new AssertionError();
            }
            if (this.mc.field_1724.method_6047().method_7909() instanceof class_1774 || this.mc.field_1724.method_6047().method_7909() instanceof class_1779 || this.mc.field_1724.method_6079().method_7909() instanceof class_1774 || this.mc.field_1724.method_6079().method_7909() instanceof class_1779) {
                this.setClickDelay();
            }
        }
    }

    public FastUseTwo() {
        super(Categories.BedTrap, "fast-use+", "Removes item cooldown.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.itemChoose = this.sgGeneral.add(new EnumSetting.Builder().name("Which item").description("item").defaultValue(Item.All).build());
        this.firework = this.sgGeneral.add(new BoolSetting.Builder().name("anti-firework").description("Prevent to spam fireworks.").defaultValue(true).build());
    }

    public static enum Item {
        All,
        Exp,
        Crystal,
        ExpAndCrystal;

    }
}

