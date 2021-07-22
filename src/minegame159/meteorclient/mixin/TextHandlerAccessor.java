/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5225
 *  net.minecraft.class_5225$class_5231
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_5225;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_5225.class})
public interface TextHandlerAccessor {
    @Accessor(value="widthRetriever")
    public class_5225.class_5231 getWidthRetriever();
}

