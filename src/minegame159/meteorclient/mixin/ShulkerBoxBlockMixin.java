/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.BetterTooltips;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_2480;
import net.minecraft.class_2561;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2480.class})
public class ShulkerBoxBlockMixin {
    @Inject(method={"appendTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAppendTooltip(class_1799 class_17992, class_1922 class_19222, List<class_2561> list, class_1836 class_18362, CallbackInfo callbackInfo) {
        if (Modules.get() == null) {
            return;
        }
        BetterTooltips betterTooltips = Modules.get().get(BetterTooltips.class);
        if (betterTooltips.isActive()) {
            if (betterTooltips.previewShulkers()) {
                callbackInfo.cancel();
            } else if (betterTooltips.shulkerCompactTooltip()) {
                callbackInfo.cancel();
                betterTooltips.applyCompactShulkerTooltip(class_17992, list);
            }
        }
    }
}

