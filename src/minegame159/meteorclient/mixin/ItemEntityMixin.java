/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixininterface.IItemEntity;
import net.minecraft.class_1542;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={class_1542.class})
public class ItemEntityMixin
implements IItemEntity {
    private class_243 rotation = new class_243(0.0, 0.0, 0.0);

    @Override
    public class_243 getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(class_243 class_2432) {
        this.rotation = class_2432;
    }
}

