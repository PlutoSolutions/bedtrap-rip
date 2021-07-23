/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.mixin.baritone;

import baritone.api.pathing.goals.GoalBlock;
import baritone.command.defaults.ComeCommand;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.Freecam;
import minegame159.meteorclient.utils.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={ComeCommand.class})
public class ComeCommandMixin {
    @ModifyArgs(method={"execute"}, at=@At(value="INVOKE", target="Lbaritone/api/process/ICustomGoalProcess;setGoalAndPath(Lbaritone/api/pathing/goals/Goal;)V"), remap=false)
    private void getComeCommandTarget(Args args) {
        Freecam freecam = Modules.get().get(Freecam.class);
        if (freecam.isActive()) {
            float f = Utils.mc.method_1488();
            args.set(0, (Object)new GoalBlock((int)freecam.getX(f), (int)freecam.getY(f), (int)freecam.getZ(f)));
        }
    }
}

