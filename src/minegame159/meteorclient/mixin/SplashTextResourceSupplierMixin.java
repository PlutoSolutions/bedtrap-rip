/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import minegame159.meteorclient.systems.config.Config;
import net.minecraft.class_4008;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4008.class})
public class SplashTextResourceSupplierMixin {
    private boolean override = true;
    private final Random random = new Random();
    private final List<String> meteorSplashes = SplashTextResourceSupplierMixin.getMeteorSplashes();

    @Inject(method={"get"}, at={@At(value="HEAD")}, cancellable=true)
    private void onApply(CallbackInfoReturnable<String> callbackInfoReturnable) {
        if (Config.get() == null || !Config.get().titleScreenSplashes) {
            return;
        }
        if (this.override) {
            callbackInfoReturnable.setReturnValue((Object)this.meteorSplashes.get(this.random.nextInt(this.meteorSplashes.size())));
        }
        this.override = !this.override;
    }

    private static List<String> getMeteorSplashes() {
        return Arrays.asList("BedTrap on Bottom", "https://discord.gg/buster!");
    }
}

