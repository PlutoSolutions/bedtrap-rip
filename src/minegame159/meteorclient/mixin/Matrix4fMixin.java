/*
 * Decompiled with CFR 0.151.
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
    public void multiplyMatrix(Vec4 vec4, Vec4 vec42) {
        vec42.set((double)this.field_21652 * vec4.x + (double)this.field_21653 * vec4.y + (double)this.field_21654 * vec4.z + (double)this.field_21655 * vec4.w, (double)this.field_21656 * vec4.x + (double)this.field_21657 * vec4.y + (double)this.field_21658 * vec4.z + (double)this.field_21659 * vec4.w, (double)this.field_21660 * vec4.x + (double)this.field_21661 * vec4.y + (double)this.field_21662 * vec4.z + (double)this.field_21663 * vec4.w, (double)this.field_21664 * vec4.x + (double)this.field_21665 * vec4.y + (double)this.field_21666 * vec4.z + (double)this.field_21667 * vec4.w);
    }
}

