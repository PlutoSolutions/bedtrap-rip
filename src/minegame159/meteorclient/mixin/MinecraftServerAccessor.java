/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_32;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={MinecraftServer.class})
public interface MinecraftServerAccessor {
    @Accessor(value="session")
    public class_32.class_5143 getSession();
}

