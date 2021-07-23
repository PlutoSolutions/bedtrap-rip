/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.BetterTab;
import net.minecraft.class_2561;
import net.minecraft.class_355;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_355.class})
public class PlayerListHudMixin {
    @ModifyArg(method={"render"}, at=@At(value="INVOKE", target="Ljava/lang/Math;min(II)I", ordinal=0), index=1)
    private int modifyCount(int n) {
        BetterTab betterTab = Modules.get().get(BetterTab.class);
        return betterTab.isActive() ? betterTab.tabSize.get() : 80;
    }

    @Inject(method={"getPlayerName"}, at={@At(value="HEAD")}, cancellable=true)
    public void getPlayerName(class_640 class_6402, CallbackInfoReturnable<class_2561> callbackInfoReturnable) {
        BetterTab betterTab = Modules.get().get(BetterTab.class);
        if (betterTab.isActive()) {
            callbackInfoReturnable.setReturnValue((Object)betterTab.getPlayerName(class_6402));
        }
    }
}

