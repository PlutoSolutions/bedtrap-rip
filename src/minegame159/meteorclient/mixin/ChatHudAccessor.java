/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_303
 *  net.minecraft.class_338
 *  net.minecraft.class_5481
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_303;
import net.minecraft.class_338;
import net.minecraft.class_5481;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_338.class})
public interface ChatHudAccessor {
    @Invoker(value="addMessage")
    public void add(class_2561 var1, int var2);

    @Accessor(value="visibleMessages")
    public List<class_303<class_5481>> getVisibleMessages();

    @Accessor(value="messages")
    public List<class_303<class_2561>> getMessages();
}

