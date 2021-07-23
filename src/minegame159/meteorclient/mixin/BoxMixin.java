/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IBox;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_238.class})
public class BoxMixin
implements IBox {
    @Shadow
    @Final
    @Mutable
    public double field_1323;
    @Shadow
    @Final
    @Mutable
    public double field_1322;
    @Shadow
    @Final
    @Mutable
    public double field_1321;
    @Shadow
    @Final
    @Mutable
    public double field_1320;
    @Shadow
    @Final
    @Mutable
    public double field_1325;
    @Shadow
    @Final
    @Mutable
    public double field_1324;

    @Override
    public void expand(double d) {
        this.field_1323 -= d;
        this.field_1322 -= d;
        this.field_1321 -= d;
        this.field_1320 += d;
        this.field_1325 += d;
        this.field_1324 += d;
    }

    @Override
    public void set(double d, double d2, double d3, double d4, double d5, double d6) {
        this.field_1323 = Math.min(d, d4);
        this.field_1322 = Math.min(d2, d5);
        this.field_1321 = Math.min(d3, d6);
        this.field_1320 = Math.max(d, d4);
        this.field_1325 = Math.max(d2, d5);
        this.field_1324 = Math.max(d3, d6);
    }
}

