/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_337
 *  net.minecraft.class_345
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import java.util.Collection;
import java.util.Iterator;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.render.RenderBossBarEvent;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.NoRender;
import net.minecraft.class_2561;
import net.minecraft.class_337;
import net.minecraft.class_345;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_337.class})
public class BossBarHudMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRender(CallbackInfo info) {
        if (Modules.get().get(NoRender.class).noBossBar()) {
            info.cancel();
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Ljava/util/Collection;iterator()Ljava/util/Iterator;"))
    public Iterator<class_345> onRender(Collection<class_345> collection) {
        RenderBossBarEvent.BossIterator event = MeteorClient.EVENT_BUS.post(RenderBossBarEvent.BossIterator.get(collection.iterator()));
        return event.iterator;
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/ClientBossBar;getName()Lnet/minecraft/text/Text;"))
    public class_2561 onAsFormattedString(class_345 clientBossBar) {
        RenderBossBarEvent.BossText event = MeteorClient.EVENT_BUS.post(RenderBossBarEvent.BossText.get(clientBossBar, clientBossBar.method_5414()));
        return event.name;
    }

    @ModifyConstant(method={"render"}, constant={@Constant(intValue=9, ordinal=1)})
    public int modifySpacingConstant(int j) {
        RenderBossBarEvent.BossSpacing event = MeteorClient.EVENT_BUS.post(RenderBossBarEvent.BossSpacing.get(j));
        return event.spacing;
    }
}

