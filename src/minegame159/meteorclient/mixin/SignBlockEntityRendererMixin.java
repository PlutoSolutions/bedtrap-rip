/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.function.Function;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_2561;
import net.minecraft.class_2625;
import net.minecraft.class_5481;
import net.minecraft.class_837;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_837.class})
public class SignBlockEntityRendererMixin {
    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/block/entity/SignBlockEntity;getTextBeingEditedOnRow(ILjava/util/function/Function;)Lnet/minecraft/text/OrderedText;"))
    private class_5481 onRenderSignBlockEntityGetTextBeingEditedOnRowProxy(class_2625 class_26252, int n, Function<class_2561, class_5481> function) {
        if (Modules.get().get(NoRender.class).noSignText()) {
            return null;
        }
        return class_26252.method_11300(n, function);
    }
}

