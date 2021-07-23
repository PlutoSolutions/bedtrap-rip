/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.NoSlow;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2490;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2490.class})
public class SlimeBlockMixin {
    @Inject(method={"onSteppedOn"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSteppedOn(class_1937 class_19372, class_2338 class_23382, class_1297 class_12972, CallbackInfo callbackInfo) {
        if (Modules.get().get(NoSlow.class).slimeBlock() && class_12972 == Utils.mc.field_1724) {
            callbackInfo.cancel();
        }
    }
}

