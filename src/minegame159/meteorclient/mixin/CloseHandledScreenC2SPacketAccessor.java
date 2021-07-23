/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2815;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2815.class})
public interface CloseHandledScreenC2SPacketAccessor {
    @Accessor(value="syncId")
    public int getSyncId();
}

