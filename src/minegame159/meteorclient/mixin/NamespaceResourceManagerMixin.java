/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import net.minecraft.class_2960;
import net.minecraft.class_3294;
import net.minecraft.class_3298;
import net.minecraft.class_3306;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_3294.class})
public class NamespaceResourceManagerMixin {
    @Inject(method={"getResource"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetResource(class_2960 class_29602, CallbackInfoReturnable<class_3298> callbackInfoReturnable) {
        if (class_29602.method_12836().equals("meteor-client")) {
            callbackInfoReturnable.setReturnValue((Object)new class_3306("meteor-client", class_29602, MeteorClient.class.getResourceAsStream("/assets/meteor-client/" + class_29602.method_12832()), null));
        }
    }
}

