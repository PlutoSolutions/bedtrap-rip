/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_320
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package minegame159.meteorclient.mixin;

import java.net.Proxy;
import net.minecraft.class_310;
import net.minecraft.class_320;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_310.class})
public interface MinecraftClientAccessor {
    @Accessor(value="currentFps")
    public int getFps();

    @Accessor(value="session")
    public void setSession(class_320 var1);

    @Accessor(value="netProxy")
    public Proxy getProxy();

    @Accessor(value="itemUseCooldown")
    public void setItemUseCooldown(int var1);

    @Invoker(value="doAttack")
    public void leftClick();
}

