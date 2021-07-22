/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1159
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IMatrix4f;
import minegame159.meteorclient.utils.misc.Vec4;
import net.minecraft.class_1159;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_1159.class})
public class Matrix4fMixin
implements IMatrix4f {
    @Shadow
    protected float field_21652;
    @Shadow
    protected float field_21656;
    @Shadow
    protected float field_21660;
    @Shadow
    protected float field_21664;
    @Shadow
    protected float field_21653;
    @Shadow
    protected float field_21657;
    @Shadow
    protected float field_21661;
    @Shadow
    protected float field_21665;
    @Shadow
    protected float field_21654;
    @Shadow
    protected float field_21658;
    @Shadow
    protected float field_21662;
    @Shadow
    protected float field_21666;
    @Shadow
    protected float field_21655;
    @Shadow
    protected float field_21659;
    @Shadow
    protected float field_21663;
    @Shadow
    protected float field_21667;

    @Override
    public void multiplyMatrix(Vec4 v, Vec4 out) {
        out.set((double)this.field_21652 * v.x + (double)this.field_21653 * v.y + (double)this.field_21654 * v.z + (double)this.field_21655 * v.w, (double)this.field_21656 * v.x + (double)this.field_21657 * v.y + (double)this.field_21658 * v.z + (double)this.field_21659 * v.w, (double)this.field_21660 * v.x + (double)this.field_21661 * v.y + (double)this.field_21662 * v.z + (double)this.field_21663 * v.w, (double)this.field_21664 * v.x + (double)this.field_21665 * v.y + (double)this.field_21666 * v.z + (double)this.field_21667 * v.w);
    }
}

