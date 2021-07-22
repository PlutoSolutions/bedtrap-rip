/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 */
package minegame159.meteorclient.systems.modules;

import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_1802;

public class Categories {
    public static final Category Render;
    public static final Category Movement;
    public static final Category Misc;
    public static final Category BedTrap;
    public static final Category Combat;
    public static final Category Player;
    public static final Category World;

    public static void register() {
        Modules.registerCategory(Combat);
        Modules.registerCategory(Player);
        Modules.registerCategory(Movement);
        Modules.registerCategory(Render);
        Modules.registerCategory(World);
        Modules.registerCategory(Misc);
        Modules.registerCategory(BedTrap);
    }

    static {
        Combat = new Category("Combat", class_1802.field_8845.method_7854());
        Player = new Category("Player", class_1802.field_8694.method_7854());
        Movement = new Category("Movement", class_1802.field_8285.method_7854());
        Render = new Category("Render", class_1802.field_8280.method_7854());
        World = new Category("World", class_1802.field_8270.method_7854());
        Misc = new Category("Misc", class_1802.field_8187.method_7854());
        BedTrap = new Category("BedTrap", class_1802.field_8789.method_7854());
    }
}

