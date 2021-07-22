/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1842
 *  net.minecraft.class_1844
 *  net.minecraft.class_1847
 *  net.minecraft.class_1935
 */
package minegame159.meteorclient.utils.misc;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1842;
import net.minecraft.class_1844;
import net.minecraft.class_1847;
import net.minecraft.class_1935;

public enum MyPotion {
    Swiftness(class_1847.field_9005, class_1802.field_8790, class_1802.field_8479),
    SwiftnessLong(class_1847.field_8983, class_1802.field_8790, class_1802.field_8479, class_1802.field_8725),
    SwiftnessStrong(class_1847.field_8966, class_1802.field_8790, class_1802.field_8479, class_1802.field_8601),
    Slowness(class_1847.field_8996, class_1802.field_8790, class_1802.field_8479, class_1802.field_8711),
    SlownessLong(class_1847.field_8989, class_1802.field_8790, class_1802.field_8479, class_1802.field_8711, class_1802.field_8725),
    SlownessStrong(class_1847.field_8976, class_1802.field_8790, class_1802.field_8479, class_1802.field_8711, class_1802.field_8601),
    JumpBoost(class_1847.field_8979, class_1802.field_8790, class_1802.field_8073),
    JumpBoostLong(class_1847.field_8971, class_1802.field_8790, class_1802.field_8073, class_1802.field_8725),
    JumpBoostStrong(class_1847.field_8998, class_1802.field_8790, class_1802.field_8073, class_1802.field_8601),
    Strength(class_1847.field_8978, class_1802.field_8790, class_1802.field_8183),
    StrengthLong(class_1847.field_8965, class_1802.field_8790, class_1802.field_8183, class_1802.field_8725),
    StrengthStrong(class_1847.field_8993, class_1802.field_8790, class_1802.field_8183, class_1802.field_8601),
    Healing(class_1847.field_8963, class_1802.field_8790, class_1802.field_8597),
    HealingStrong(class_1847.field_8980, class_1802.field_8790, class_1802.field_8597, class_1802.field_8601),
    Harming(class_1847.field_9004, class_1802.field_8790, class_1802.field_8597, class_1802.field_8711),
    HarmingStrong(class_1847.field_8973, class_1802.field_8790, class_1802.field_8597, class_1802.field_8711, class_1802.field_8601),
    Poison(class_1847.field_8982, class_1802.field_8790, class_1802.field_8680),
    PoisonLong(class_1847.field_9002, class_1802.field_8790, class_1802.field_8680, class_1802.field_8725),
    PoisonStrong(class_1847.field_8972, class_1802.field_8790, class_1802.field_8680, class_1802.field_8601),
    Regeneration(class_1847.field_8986, class_1802.field_8790, class_1802.field_8070),
    RegenerationLong(class_1847.field_9003, class_1802.field_8790, class_1802.field_8070, class_1802.field_8725),
    RegenerationStrong(class_1847.field_8992, class_1802.field_8790, class_1802.field_8070, class_1802.field_8601),
    FireResistance(class_1847.field_8987, class_1802.field_8790, class_1802.field_8135),
    FireResistanceLong(class_1847.field_8969, class_1802.field_8790, class_1802.field_8135, class_1802.field_8725),
    WaterBreathing(class_1847.field_8994, class_1802.field_8790, class_1802.field_8323),
    WaterBreathingLong(class_1847.field_9001, class_1802.field_8790, class_1802.field_8323, class_1802.field_8725),
    NightVision(class_1847.field_8968, class_1802.field_8790, class_1802.field_8071),
    NightVisionLong(class_1847.field_8981, class_1802.field_8790, class_1802.field_8071, class_1802.field_8725),
    Invisibility(class_1847.field_8997, class_1802.field_8790, class_1802.field_8071, class_1802.field_8711),
    InvisibilityLong(class_1847.field_9000, class_1802.field_8790, class_1802.field_8071, class_1802.field_8711, class_1802.field_8725),
    TurtleMaster(class_1847.field_8990, class_1802.field_8790, class_1802.field_8090),
    TurtleMasterLong(class_1847.field_8988, class_1802.field_8790, class_1802.field_8090, class_1802.field_8725),
    TurtleMasterStrong(class_1847.field_8977, class_1802.field_8790, class_1802.field_8090, class_1802.field_8601),
    SlowFalling(class_1847.field_8974, class_1802.field_8790, class_1802.field_8614),
    SlowFallingLong(class_1847.field_8964, class_1802.field_8790, class_1802.field_8614, class_1802.field_8725),
    Weakness(class_1847.field_8975, class_1802.field_8711),
    WeaknessLong(class_1847.field_8970, class_1802.field_8711, class_1802.field_8725);

    public final /* synthetic */ class_1792[] ingredients;
    public final /* synthetic */ class_1799 potion;

    private MyPotion(class_1842 llllllllllllllllllllIIllIllIIIll, class_1792 ... llllllllllllllllllllIIllIlIllIIl) {
        MyPotion llllllllllllllllllllIIllIllIIIII;
        llllllllllllllllllllIIllIllIIIII.potion = class_1844.method_8061((class_1799)new class_1799((class_1935)class_1802.field_8574), (class_1842)llllllllllllllllllllIIllIllIIIll);
        llllllllllllllllllllIIllIllIIIII.ingredients = llllllllllllllllllllIIllIlIllIIl;
    }
}

