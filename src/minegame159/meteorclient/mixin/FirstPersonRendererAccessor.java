/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import net.minecraft.class_1799;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_759.class})
public interface FirstPersonRendererAccessor {
    @Accessor(value="mainHand")
    public void setItemStackMainHand(class_1799 var1);

    @Accessor(value="offHand")
    public void setItemStackOffHand(class_1799 var1);

    @Accessor(value="equipProgressMainHand")
    public void setEquippedProgressMainHand(float var1);

    @Accessor(value="prevEquipProgressMainHand")
    public void setPrevEquippedProgressMainHand(float var1);

    @Accessor(value="equipProgressOffHand")
    public void setEquippedProgressOffHand(float var1);

    @Accessor(value="prevEquipProgressOffHand")
    public void setPrevEquippedProgressOffHand(float var1);
}

