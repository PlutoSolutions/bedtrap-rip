/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2680
 *  net.minecraft.class_4970
 *  net.minecraft.class_4970$class_2251
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.Slippy;
import minegame159.meteorclient.systems.modules.render.Xray;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_2248.class})
public abstract class BlockMixin
extends class_4970
implements class_1935 {
    public BlockMixin(class_4970.class_2251 settings) {
        super(settings);
    }

    @Inject(method={"shouldDrawSide"}, at={@At(value="RETURN")}, cancellable=true)
    private static void onShouldDrawSide(class_2680 state, class_1922 view, class_2338 pos, class_2350 facing, CallbackInfoReturnable<Boolean> info) {
        Xray xray = Modules.get().get(Xray.class);
        if (xray.isActive()) {
            info.setReturnValue((Object)xray.modifyDrawSide(state, view, pos, facing, info.getReturnValueZ()));
        }
    }

    @Inject(method={"getSlipperiness"}, at={@At(value="RETURN")}, cancellable=true)
    public void getSlipperiness(CallbackInfoReturnable<Float> info) {
        if (Modules.get() == null) {
            return;
        }
        Slippy slippy = Modules.get().get(Slippy.class);
        class_2248 block = (class_2248)this;
        if (slippy.isActive() && !slippy.blocks.get().contains((Object)block)) {
            info.setReturnValue((Object)Float.valueOf(slippy.slippness.get().floatValue()));
        }
    }
}

