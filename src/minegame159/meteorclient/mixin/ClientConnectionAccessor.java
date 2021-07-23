/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import io.netty.channel.Channel;
import net.minecraft.class_2535;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2535.class})
public interface ClientConnectionAccessor {
    @Accessor(value="channel")
    public Channel getChannel();
}

