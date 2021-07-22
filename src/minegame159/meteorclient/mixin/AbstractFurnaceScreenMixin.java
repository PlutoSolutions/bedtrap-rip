/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1661
 *  net.minecraft.class_1720
 *  net.minecraft.class_2561
 *  net.minecraft.class_465
 *  net.minecraft.class_489
 *  net.minecraft.class_518
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.world.AutoSmelter;
import net.minecraft.class_1661;
import net.minecraft.class_1720;
import net.minecraft.class_2561;
import net.minecraft.class_465;
import net.minecraft.class_489;
import net.minecraft.class_518;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_489.class})
public abstract class AbstractFurnaceScreenMixin<T extends class_1720>
extends class_465<T>
implements class_518 {
    public AbstractFurnaceScreenMixin(T t, class_1661 class_16612, class_2561 class_25612) {
        super(t, class_16612, class_25612);
    }

    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void onTick(CallbackInfo callbackInfo) {
        if (Modules.get().isActive(AutoSmelter.class)) {
            Modules.get().get(AutoSmelter.class).tick((class_1720)this.field_2797);
        }
    }

    public void method_25419() {
        super.method_25419();
        if (Modules.get().isActive(AutoSmelter.class)) {
            Modules.get().get(AutoSmelter.class).onFurnaceClose();
        }
    }
}

