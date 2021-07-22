/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_2172;

public class ResetCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllIlIlIIlIIIIIIIII) {
        ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)llllllllllllllllIlIlIIlIIIIIIIII.then(((LiteralArgumentBuilder)ResetCommand.literal("settings").then(ResetCommand.argument("module", ModuleArgumentType.module()).executes(llllllllllllllllIlIlIIIllllIIlll -> {
            Module llllllllllllllllIlIlIIIllllIlIII = (Module)llllllllllllllllIlIlIIIllllIIlll.getArgument("module", Module.class);
            llllllllllllllllIlIlIIIllllIlIII.settings.forEach(llllllllllllllllIlIlIIIllllIIIll -> llllllllllllllllIlIlIIIllllIIIll.forEach(Setting::reset));
            llllllllllllllllIlIlIIIllllIlIII.info("Reset all settings.", new Object[0]);
            return 1;
        }))).then(ResetCommand.literal("all").executes(llllllllllllllllIlIlIIIlllllIIlI -> {
            Modules.get().getAll().forEach(llllllllllllllllIlIlIIIllllIllll -> llllllllllllllllIlIlIIIllllIllll.settings.forEach(llllllllllllllllIlIlIIIllllIllII -> llllllllllllllllIlIlIIIllllIllII.forEach(Setting::reset)));
            ChatUtils.info("Modules", "Reset all module's settings", new Object[0]);
            return 1;
        })))).then(ResetCommand.literal("gui").executes(llllllllllllllllIlIlIIIlllllIIll -> {
            GuiThemes.get().clearWindowConfigs();
            ChatUtils.info("The ClickGUI positioning has been reset.", new Object[0]);
            return 1;
        }))).then(((LiteralArgumentBuilder)ResetCommand.literal("bind").then(ResetCommand.argument("module", ModuleArgumentType.module()).executes(llllllllllllllllIlIlIIIlllllIlIl -> {
            Module llllllllllllllllIlIlIIIlllllIllI = (Module)llllllllllllllllIlIlIIIlllllIlIl.getArgument("module", Module.class);
            llllllllllllllllIlIlIIIlllllIllI.keybind.set(true, -1);
            llllllllllllllllIlIlIIIlllllIllI.info("Reset bind.", new Object[0]);
            return 1;
        }))).then(ResetCommand.literal("all").executes(llllllllllllllllIlIlIIIlllllllIl -> {
            Modules.get().getAll().forEach(llllllllllllllllIlIlIIIllllllIlI -> llllllllllllllllIlIlIIIllllllIlI.keybind.set(true, -1));
            ChatUtils.info("Modules", "Reset all binds", new Object[0]);
            return 1;
        })))).then(ResetCommand.literal("hud").executes(llllllllllllllllIlIlIIIllllllllI -> {
            Modules.get().get(HUD.class).reset.run();
            Modules.get().get(HUD.class).info("Reset HUD elements.", new Object[0]);
            return 1;
        }));
    }

    public ResetCommand() {
        super("reset", "Resets specified settings.", new String[0]);
        ResetCommand llllllllllllllllIlIlIIlIIIIIIlII;
    }
}

