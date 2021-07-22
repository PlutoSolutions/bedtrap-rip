/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_412
 *  net.minecraft.class_4185
 *  net.minecraft.class_419
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  net.minecraft.class_500
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.mixin.AbstractButtonWidgetAccessor;
import minegame159.meteorclient.mixin.ScreenMixin;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.misc.AutoReconnect;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_412;
import net.minecraft.class_4185;
import net.minecraft.class_419;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_419.class})
public class DisconnectedScreenMixin
extends ScreenMixin {
    @Shadow
    private int field_2454;
    private class_4185 reconnectBtn;
    private double time;

    public DisconnectedScreenMixin() {
        this.time = Modules.get().get(AutoReconnect.class).time.get() * 20.0;
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onRenderBackground(CallbackInfo callbackInfo) {
        if (Modules.get().get(AutoReconnect.class).lastServerInfo != null) {
            int n = this.field_22789 / 2 - 100;
            int n2 = Math.min(this.field_22790 / 2 + this.field_2454 / 2 + 32, this.field_22790 - 30);
            this.reconnectBtn = this.method_25411(new class_4185(n, n2, 200, 20, (class_2561)new class_2585(this.getText()), this::lambda$onRenderBackground$0));
        }
    }

    @Override
    public void method_25393() {
        if (!Modules.get().isActive(AutoReconnect.class)) {
            return;
        }
        if (this.time <= 0.0) {
            this.field_22787.method_1507((class_437)new class_412((class_437)new class_500((class_437)new class_442()), this.field_22787, Modules.get().get(AutoReconnect.class).lastServerInfo));
        } else {
            this.time -= 1.0;
            if (this.reconnectBtn != null) {
                ((AbstractButtonWidgetAccessor)this.reconnectBtn).setText((class_2561)new class_2585(this.getText()));
            }
        }
    }

    private String getText() {
        String string = "Reconnect";
        if (Modules.get().isActive(AutoReconnect.class)) {
            string = string + " " + String.format("(%.1f)", this.time / 20.0);
        }
        return string;
    }

    private void lambda$onRenderBackground$0(class_4185 class_41852) {
        this.field_22787.method_1507((class_437)new class_412((class_437)new class_500((class_437)new class_442()), this.field_22787, Modules.get().get(AutoReconnect.class).lastServerInfo));
    }
}

