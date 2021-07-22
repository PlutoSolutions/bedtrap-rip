/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2664
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IExplosionS2CPacket;
import net.minecraft.class_2664;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_2664.class})
public class ExplosionS2CPacketMixin
implements IExplosionS2CPacket {
    @Shadow
    private float field_12176;
    @Shadow
    private float field_12183;
    @Shadow
    private float field_12182;

    @Override
    public void setVelocityX(float f) {
        this.field_12176 = f;
    }

    @Override
    public void setVelocityY(float f) {
        this.field_12183 = f;
    }

    @Override
    public void setVelocityZ(float f) {
        this.field_12182 = f;
    }
}

