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
    private final /* synthetic */ Setting<Weapon> weapon;
    private final /* synthetic */ Setting<Integer> threshold;
    private final /* synthetic */ Setting<Boolean> antiBreak;
    private final /* synthetic */ SettingGroup sgGeneral;

    private int getBestWeapon() {
        AutoWeapon lllllllllllllllllllIlIIIIlIlllII;
        int lllllllllllllllllllIlIIIIllIIIIl = lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.field_7545;
        int lllllllllllllllllllIlIIIIllIIIII = lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.field_7545;
        int lllllllllllllllllllIlIIIIlIlllll = lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.field_7545;
        double lllllllllllllllllllIlIIIIlIllllI = 0.0;
        double lllllllllllllllllllIlIIIIlIlllIl = 0.0;
        for (int lllllllllllllllllllIlIIIIllIIlIl = 0; lllllllllllllllllllIlIIIIllIIlIl < 9; ++lllllllllllllllllllIlIIIIllIIlIl) {
            double lllllllllllllllllllIlIIIIllIIllI;
            if (!(lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIlIl).method_7909() instanceof class_1829) || lllllllllllllllllllIlIIIIlIlllII.antiBreak.get().booleanValue() && lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIlIl).method_7936() - lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIlIl).method_7919() <= 10 || !((lllllllllllllllllllIlIIIIllIIllI = (double)(((class_1829)lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIlIl).method_7909()).method_8022().method_8028() + class_1890.method_8218((class_1799)lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIlIl), (class_1310)class_1310.field_6290) + 2.0f)) > lllllllllllllllllllIlIIIIlIllllI)) continue;
            lllllllllllllllllllIlIIIIlIllllI = lllllllllllllllllllIlIIIIllIIllI;
            lllllllllllllllllllIlIIIIllIIIIl = lllllllllllllllllllIlIIIIllIIlIl;
        }
        for (int lllllllllllllllllllIlIIIIllIIIll = 0; lllllllllllllllllllIlIIIIllIIIll < 9; ++lllllllllllllllllllIlIIIIllIIIll) {
            double lllllllllllllllllllIlIIIIllIIlII;
            if (!(lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIIll).method_7909() instanceof class_1743) || lllllllllllllllllllIlIIIIlIlllII.antiBreak.get().booleanValue() && lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIIll).method_7936() - lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIIll).method_7919() <= 10 || !((lllllllllllllllllllIlIIIIllIIlII = (double)(((class_1743)lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIIll).method_7909()).method_8022().method_8028() + class_1890.method_8218((class_1799)lllllllllllllllllllIlIIIIlIlllII.mc.field_1724.field_7514.method_5438(lllllllllllllllllllIlIIIIllIIIll), (class_1310)class_1310.field_6290) + 2.0f)) > lllllllllllllllllllIlIIIIlIlllIl)) continue;
            lllllllllllllllllllIlIIIIlIlllIl = lllllllllllllllllllIlIIIIllIIlII;
            lllllllllllllllllllIlIIIIllIIIII = lllllllllllllllllllIlIIIIllIIIll;
        }
        if (lllllllllllllllllllIlIIIIlIlllII.weapon.get() == Weapon.Sword && (double)lllllllllllllllllllIlIIIIlIlllII.threshold.get().intValue() > lllllllllllllllllllIlIIIIlIlllIl - lllllllllllllllllllIlIIIIlIllllI) {
            lllllllllllllllllllIlIIIIlIlllll = lllllllllllllllllllIlIIIIllIIIIl;
        } else if (lllllllllllllllllllIlIIIIlIlllII.weapon.get() == Weapon.Axe && (double)lllllllllllllllllllIlIIIIlIlllII.threshold.get().intValue() > lllllllllllllllllllIlIIIIlIllllI - lllllllllllllllllllIlIIIIlIlllIl) {
            lllllllllllllllllllIlIIIIlIlllll = lllllllllllllllllllIlIIIIllIIIII;
        } else if (lllllllllllllllllllIlIIIIlIlllII.weapon.get() == Weapon.Sword && (double)lllllllllllllllllllIlIIIIlIlllII.threshold.get().intValue() < lllllllllllllllllllIlIIIIlIlllIl - lllllllllllllllllllIlIIIIlIllllI) {
            lllllllllllllllllllIlIIIIlIlllll = lllllllllllllllllllIlIIIIllIIIII;
        } else if (lllllllllllllllllllIlIIIIlIlllII.weapon.get() == Weapon.Axe && (double)lllllllllllllllllllIlIIIIlIlllII.threshold.get().intValue() < lllllllllllllllllllIlIIIIlIllllI - lllllllllllllllllllIlIIIIlIlllIl) {
            lllllllllllllllllllIlIIIIlIlllll = lllllllllllllllllllIlIIIIllIIIIl;
        }
        return lllllllllllllllllllIlIIIIlIlllll;
    }

    @EventHandler
    private void onAttack(AttackEntityEvent lllllllllllllllllllIlIIIIlllIIIl) {
        AutoWeapon lllllllllllllllllllIlIIIIlllIIII;
        InvUtils.swap(lllllllllllllllllllIlIIIIlllIIII.getBestWeapon());
    }

    public AutoWeapon() {
        super(Categories.Combat, "auto-weapon", "Finds the best weapon to use in your hotbar.");
        AutoWeapon lllllllllllllllllllIlIIIIlllIlIl;
        lllllllllllllllllllIlIIIIlllIlIl.sgGeneral = lllllllllllllllllllIlIIIIlllIlIl.settings.getDefaultGroup();
        lllllllllllllllllllIlIIIIlllIlIl.weapon = lllllllllllllllllllIlIIIIlllIlIl.sgGeneral.add(new EnumSetting.Builder().name("weapon").description("What type of weapon to use.").defaultValue(Weapon.Sword).build());
        lllllllllllllllllllIlIIIIlllIlIl.threshold = lllllllllllllllllllIlIIIIlllIlIl.sgGeneral.add(new IntSetting.Builder().name("threshold").description("If the non-preferred weapon produces this much damage this will favor it over your preferred weapon.").defaultValue(4).build());
        lllllllllllllllllllIlIIIIlllIlIl.antiBreak = lllllllllllllllllllIlIIIIlllIlIl.sgGeneral.add(new BoolSetting.Builder().name("anti-break").description("Prevents you from breaking your weapon.").defaultValue(false).build());
    }

    public static enum Weapon {
        Sword,
        Axe;


        private Weapon() {
            Weapon llIlIIlIlIIIIlI;
        }
    }
}

