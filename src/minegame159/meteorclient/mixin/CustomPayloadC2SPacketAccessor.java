/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2540;
import net.minecraft.class_2817;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2817.class})
public interface CustomPayloadC2SPacketAccessor {
    @Accessor(value="channel")
    public class_2960 getChannel();

    @Accessor(value="data")
    public class_2540 getData();

    @Accessor(value="data")
    public void setData(class_2540 var1);
}

