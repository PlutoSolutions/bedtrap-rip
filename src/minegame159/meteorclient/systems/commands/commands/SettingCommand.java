/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.commands.arguments.SettingArgumentType;
import minegame159.meteorclient.systems.commands.arguments.SettingValueArgumentType;
import net.minecraft.class_2172;

public class SettingCommand
extends Command {
    private static int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        Setting<?> setting = SettingArgumentType.getSetting(commandContext);
        ModuleArgumentType.getModule(commandContext, "module").info("Setting (highlight)%s(default) is (highlight)%s(default).", setting.title, setting.get());
        return 1;
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.then(SettingCommand.argument("module", ModuleArgumentType.module()).then(((RequiredArgumentBuilder)SettingCommand.argument("setting", SettingArgumentType.setting()).executes(SettingCommand::lambda$build$0)).then(SettingCommand.argument("value", SettingValueArgumentType.value()).executes(SettingCommand::lambda$build$1))));
    }

    private static int lambda$build$1(CommandContext commandContext) throws CommandSyntaxException {
        String string;
        Setting<?> setting = SettingArgumentType.getSetting(commandContext);
        if (setting.parse(string = (String)commandContext.getArgument("value", String.class))) {
            ModuleArgumentType.getModule(commandContext, "module").info("Setting (highlight)%s(default) changed to (highlight)%s(default).", setting.title, string);
        }
        return 1;
    }

    public SettingCommand() {
        super("settings", "Allows you to view and change module settings.", "s");
    }
}

