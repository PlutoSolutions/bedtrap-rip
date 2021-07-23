/*
 * Decompiled with CFR 0.151.
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
    private int modifyColorIfLava(int n, class_1920 class_19202, class_2338 class_23382, class_4588 class_45882, class_3610 class_36102) {
        Ambience ambience = Modules.get().get(Ambience.class);
        if (ambience.isActive() && ambience.changeLavaColor.get().booleanValue() && class_36102.method_15767((class_3494)class_3486.field_15518)) {
            return ambience.lavaColor.get().getPacked();
        }
        return n;
    }
}

