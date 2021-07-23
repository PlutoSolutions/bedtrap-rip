/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_481;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_481.class})
public interface CreativeInventoryScreenAccessor {
    @Accessor(value="selectedTab")
    public int getSelectedTab();
}

