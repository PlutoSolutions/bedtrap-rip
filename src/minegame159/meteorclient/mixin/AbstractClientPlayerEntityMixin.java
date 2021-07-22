/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2960
 *  net.minecraft.class_640
 *  net.minecraft.class_742
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.FakeClientPlayer;
import minegame159.meteorclient.utils.network.Capes;
import net.minecraft.class_1657;
import net.minecraft.class_2960;
import net.minecraft.class_640;
import net.minecraft.class_742;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_742.class})
public class AbstractClientPlayerEntityMixin {
    @Inject(method={"getCapeTexture"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetCapeTexture(CallbackInfoReturnable<class_2960> info) {
        class_2960 id = Capes.get((class_1657)this);
        if (id != null) {
            info.setReturnValue((Object)id);
        }
    }

    @Inject(method={"getPlayerListEntry"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetPlayerListEntry(CallbackInfoReturnable<class_640> info) {
        if (Utils.mc.method_1562() == null) {
            info.setReturnValue((Object)FakeClientPlayer.getPlayerListEntry());
        }
    }

    @Inject(method={"isSpectator"}, at={@At(value="HEAD")}, cancellable=true)
    private void onIsSpectator(CallbackInfoReturnable<Boolean> info) {
        if (Utils.mc.method_1562() == null) {
            info.setReturnValue((Object)false);
        }
    }

    @Inject(method={"isCreative"}, at={@At(value="HEAD")}, cancellable=true)
    private void onIsCreative(CallbackInfoReturnable<Boolean> info) {
        if (Utils.mc.method_1562() == null) {
            info.setReturnValue((Object)false);
        }
    }
}

