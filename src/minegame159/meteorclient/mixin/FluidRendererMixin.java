/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1920
 *  net.minecraft.class_2338
 *  net.minecraft.class_3486
 *  net.minecraft.class_3494
 *  net.minecraft.class_3610
 *  net.minecraft.class_4588
 *  net.minecraft.class_775
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.Ambience;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_3486;
import net.minecraft.class_3494;
import net.minecraft.class_3610;
import net.minecraft.class_4588;
import net.minecraft.class_775;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={class_775.class})
public class FluidRendererMixin {
    @ModifyVariable(method={"render"}, at=@At(value="STORE", ordinal=0), index=8)
    private int modifyColorIfLava(int color, class_1920 world, class_2338 pos, class_4588 vertexConsumer, class_3610 state) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeLavaColor.get().booleanValue() && state.method_15767((class_3494)class_3486.field_15518)) {
            return ambience.lavaColor.get().getPacked();
        }
        return color;
    }
}

