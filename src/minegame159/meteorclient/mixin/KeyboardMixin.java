/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_309
 *  net.minecraft.class_310
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.meteor.CharTypedEvent;
import minegame159.meteorclient.events.meteor.KeyEvent;
import minegame159.meteorclient.gui.GuiKeyEvents;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.input.Input;
import minegame159.meteorclient.utils.misc.input.KeyAction;
import net.minecraft.class_309;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_309.class})
public abstract class KeyboardMixin {
    @Shadow
    @Final
    private class_310 field_1678;

    @Inject(method={"onKey"}, at={@At(value="HEAD")}, cancellable=true)
    public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo info) {
        if (key != -1) {
            if (this.field_1678.field_1755 instanceof WidgetScreen && i == 2) {
                ((WidgetScreen)this.field_1678.field_1755).keyRepeated(key, j);
            }
            if (GuiKeyEvents.canUseKeys) {
                Input.setKeyState(key, i != 0);
                KeyEvent event = MeteorClient.EVENT_BUS.post(KeyEvent.get(key, KeyAction.get(i)));
                if (event.isCancelled()) {
                    info.cancel();
                }
            }
        }
    }

    @Inject(method={"onChar"}, at={@At(value="HEAD")}, cancellable=true)
    private void onChar(long window, int i, int j, CallbackInfo info) {
        CharTypedEvent event;
        if (Utils.canUpdate() && !this.field_1678.method_1493() && (this.field_1678.field_1755 == null || this.field_1678.field_1755 instanceof WidgetScreen) && (event = MeteorClient.EVENT_BUS.post(CharTypedEvent.get((char)i))).isCancelled()) {
            info.cancel();
        }
    }
}

