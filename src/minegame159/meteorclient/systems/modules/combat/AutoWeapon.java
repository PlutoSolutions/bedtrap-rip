/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1310
 *  net.minecraft.class_1743
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
 *  net.minecraft.class_1890
 */
package minegame159.meteorclient.systems.modules.combat;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.AttackEntityEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1310;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_1890;

public class AutoWeapon
extends Module {
    private final Setting<Weapon> weapon;
    private final Setting<Integer> threshold;
    private final Setting<Boolean> antiBreak;
    private final SettingGroup sgGeneral;

    private int getBestWeapon() {
        int n;
        int n2 = this.mc.field_1724.field_7514.field_7545;
        int n3 = this.mc.field_1724.field_7514.field_7545;
        int n4 = this.mc.field_1724.field_7514.field_7545;
        double d = 0.0;
        double d2 = 0.0;
        for (n = 0; n < 9; ++n) {
            double d3;
            if (!(this.mc.field_1724.field_7514.method_5438(n).method_7909() instanceof class_1829) || this.antiBreak.get().booleanValue() && this.mc.field_1724.field_7514.method_5438(n).method_7936() - this.mc.field_1724.field_7514.method_5438(n).method_7919() <= 10 || !((d3 = (double)(((class_1829)this.mc.field_1724.field_7514.method_5438(n).method_7909()).method_8022().method_8028() + class_1890.method_8218((class_1799)this.mc.field_1724.field_7514.method_5438(n), (class_1310)class_1310.field_6290) + 2.0f)) > d)) continue;
            d = d3;
            n2 = n;
            if (!false) continue;
            return 0;
        }
        for (n = 0; n < 9; ++n) {
            double d4;
            if (!(this.mc.field_1724.field_7514.method_5438(n).method_7909() instanceof class_1743) || this.antiBreak.get().booleanValue() && this.mc.field_1724.field_7514.method_5438(n).method_7936() - this.mc.field_1724.field_7514.method_5438(n).method_7919() <= 10 || !((d4 = (double)(((class_1743)this.mc.field_1724.field_7514.method_5438(n).method_7909()).method_8022().method_8028() + class_1890.method_8218((class_1799)this.mc.field_1724.field_7514.method_5438(n), (class_1310)class_1310.field_6290) + 2.0f)) > d2)) continue;
            d2 = d4;
            n3 = n;
            if (-2 <= 0) continue;
            return 0;
        }
        if (this.weapon.get() == Weapon.Sword && (double)this.threshold.get().intValue() > d2 - d) {
            n4 = n2;
        } else if (this.weapon.get() == Weapon.Axe && (double)this.threshold.get().intValue() > d - d2) {
            n4 = n3;
        } else if (this.weapon.get() == Weapon.Sword && (double)this.threshold.get().intValue() < d2 - d) {
            n4 = n3;
        } else if (this.weapon.get() == Weapon.Axe && (double)this.threshold.get().intValue() < d - d2) {
            n4 = n2;
        }
        return n4;
    }

    @EventHandler
    private void onAttack(AttackEntityEvent attackEntityEvent) {
        InvUtils.swap(this.getBestWeapon());
    }

    public AutoWeapon() {
        super(Categories.Combat, "auto-weapon", "Finds the best weapon to use in your hotbar.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.weapon = this.sgGeneral.add(new EnumSetting.Builder().name("weapon").description("What type of weapon to use.").defaultValue(Weapon.Sword).build());
        this.threshold = this.sgGeneral.add(new IntSetting.Builder().name("threshold").description("If the non-preferred weapon produces this much damage this will favor it over your preferred weapon.").defaultValue(4).build());
        this.antiBreak = this.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents you from breaking your weapon.").defaultValue(false).build());
    }

    public static enum Weapon {
        Sword,
        Axe;

    }
}

