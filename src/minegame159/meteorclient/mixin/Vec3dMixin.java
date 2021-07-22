/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IVec3d;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_243.class})
public class Vec3dMixin
implements IVec3d {
    @Shadow
    @Final
    @Mutable
    public double field_1352;
    @Shadow
    @Final
    @Mutable
    public double field_1351;
    @Shadow
    @Final
    @Mutable
    public double field_1350;

    @Override
    public void set(double d, double d2, double d3) {
        this.field_1352 = d;
        this.field_1351 = d2;
        this.field_1350 = d3;
    }

    @Override
    public void setXZ(double d, double d2) {
        this.field_1352 = d;
        this.field_1350 = d2;
    }

    @Override
    public void setY(double d) {
        this.field_1351 = d;
    }
}

