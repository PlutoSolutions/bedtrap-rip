/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_128
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
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
    private void onAddStackTrace(StringBuilder sb, CallbackInfo info) {
        if (Modules.get() != null) {
            sb.append("\n\n");
            sb.append("-- Meteor Client --\n");
            sb.append("Version: ").append(Config.get().version.getOriginalString()).append("\n");
            if (!Config.get().devBuild.isEmpty()) {
                sb.append("Dev Build: ").append(Config.get().devBuild).append("\n");
            }
            for (Category category : Modules.loopCategories()) {
                List<Module> modules = Modules.get().getGroup(category);
                boolean active = false;
                for (Module module : modules) {
                    if (!(module instanceof Module) || !module.isActive()) continue;
                    active = true;
                    break;
                }
                if (!active) continue;
                sb.append("\n");
                sb.append("[").append(category).append("]:").append("\n");
                for (Module module : modules) {
                    if (!(module instanceof Module) || !module.isActive()) continue;
                    sb.append(module.title).append(" (").append(module.name).append(")\n");
                }
            }
        }
    }
}

