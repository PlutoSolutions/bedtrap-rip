/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_243
 *  net.minecraft.class_3726
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IRaycastContext;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_3959.class})
public class RaycastContextMixin
implements IRaycastContext {
    @Shadow
    @Final
    @Mutable
    private class_243 field_17553;
    @Shadow
    @Final
    @Mutable
    private class_243 field_17554;
    @Shadow
    @Final
    @Mutable
    private class_3959.class_3960 field_17555;
    @Shadow
    @Final
    @Mutable
    private class_3959.class_242 field_17556;
    @Shadow
    @Final
    @Mutable
    private class_3726 field_17557;

    @Override
    public void set(class_243 start, class_243 end, class_3959.class_3960 shapeType, class_3959.class_242 fluidHandling, class_1297 entity) {
        this.field_17553 = start;
        this.field_17554 = end;
        this.field_17555 = shapeType;
        this.field_17556 = fluidHandling;
        this.field_17557 = class_3726.method_16195((class_1297)entity);
    }
}

