/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1542
 *  net.minecraft.class_243
 *  org.spongepowered.asm.mixin.Mixin
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
    public void setRotation(class_243 rotation) {
        this.rotation = rotation;
    }
}

