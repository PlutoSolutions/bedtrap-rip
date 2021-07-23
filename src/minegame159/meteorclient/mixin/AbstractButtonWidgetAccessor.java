/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_2561;
import net.minecraft.class_339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_339.class})
public interface AbstractButtonWidgetAccessor {
    @Accessor(value="message")
    public void setText(class_2561 var1);
}

