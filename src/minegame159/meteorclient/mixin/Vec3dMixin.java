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
    public void set(double x, double y, double z) {
        this.field_1352 = x;
        this.field_1351 = y;
        this.field_1350 = z;
    }

    @Override
    public void setXZ(double x, double z) {
        this.field_1352 = x;
        this.field_1350 = z;
    }

    @Override
    public void setY(double y) {
        this.field_1351 = y;
    }
}

