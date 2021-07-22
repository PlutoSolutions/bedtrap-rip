/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1927
 *  net.minecraft.class_1927$class_4179
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IExplosion;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1927;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_1927.class})
public class ExplosionMixin
implements IExplosion {
    @Shadow
    @Final
    @Mutable
    private class_1937 field_9187;
    @Shadow
    @Final
    @Mutable
    @Nullable
    private class_1297 field_9185;
    @Shadow
    @Final
    @Mutable
    private double field_9195;
    @Shadow
    @Final
    @Mutable
    private double field_9192;
    @Shadow
    @Final
    @Mutable
    private double field_9189;
    @Shadow
    @Final
    @Mutable
    private float field_9190;
    @Shadow
    @Final
    @Mutable
    private boolean field_9186;
    @Shadow
    @Final
    @Mutable
    private class_1927.class_4179 field_9184;

    @Override
    public void set(class_243 class_2432, float f, boolean bl) {
        this.field_9187 = Utils.mc.field_1687;
        this.field_9185 = null;
        this.field_9195 = class_2432.field_1352;
        this.field_9192 = class_2432.field_1351;
        this.field_9189 = class_2432.field_1350;
        this.field_9190 = f;
        this.field_9186 = bl;
        this.field_9184 = class_1927.class_4179.field_18687;
    }
}

