/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.BypassDeathScreen;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_418;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_418.class})
public class DeathScreenMixin
extends class_437 {
    protected DeathScreenMixin(class_2561 class_25612) {
        super(class_25612);
    }

    @Inject(method={"init"}, at={@At(value="HEAD")})
    protected void init(CallbackInfo callbackInfo) {
        if (Modules.get().isActive(BypassDeathScreen.class)) {
            this.method_25411((class_339)new class_4185(this.field_22789 / 2 - 100, this.field_22790 / 4 + 48, 200, 20, (class_2561)new class_2585("Ghost Spectate"), this::lambda$init$0));
        }
    }

    private void lambda$init$0(class_4185 class_41852) {
        this.field_22787.method_1507(null);
    }
}

