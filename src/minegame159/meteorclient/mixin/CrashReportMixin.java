/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin;

import java.util.List;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.systems.modules.Category;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_128;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_128.class})
public class CrashReportMixin {
    @Inject(method={"addStackTrace"}, at={@At(value="TAIL")})
    private void onAddStackTrace(StringBuilder stringBuilder, CallbackInfo callbackInfo) {
        if (Modules.get() != null) {
            stringBuilder.append("\n\n");
            stringBuilder.append("-- Meteor Client --\n");
            stringBuilder.append("Version: ").append(Config.get().version.getOriginalString()).append("\n");
            if (!Config.get().devBuild.isEmpty()) {
                stringBuilder.append("Dev Build: ").append(Config.get().devBuild).append("\n");
            }
            for (Category category : Modules.loopCategories()) {
                List<Module> list = Modules.get().getGroup(category);
                boolean bl = false;
                for (Module module : list) {
                    if (!(module instanceof Module) || !module.isActive()) continue;
                    bl = true;
                    break;
                }
                if (!bl) continue;
                stringBuilder.append("\n");
                stringBuilder.append("[").append(category).append("]:").append("\n");
                for (Module module : list) {
                    if (!(module instanceof Module) || !module.isActive()) continue;
                    stringBuilder.append(module.title).append(" (").append(module.name).append(")\n");
                }
            }
        }
    }
}

