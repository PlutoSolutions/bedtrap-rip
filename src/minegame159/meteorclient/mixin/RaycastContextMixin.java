/*
 * Decompiled with CFR 0.151.
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
    public void set(class_243 class_2432, class_243 class_2433, class_3959.class_3960 class_39602, class_3959.class_242 class_2422, class_1297 class_12972) {
        this.field_17553 = class_2432;
        this.field_17554 = class_2433;
        this.field_17555 = class_39602;
        this.field_17556 = class_2422;
        this.field_17557 = class_3726.method_16195((class_1297)class_12972);
    }
}

