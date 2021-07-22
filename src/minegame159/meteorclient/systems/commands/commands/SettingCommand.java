/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  net.minecraft.class_2172
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.systems.commands.Command;
import minegame159.meteorclient.systems.commands.arguments.ModuleArgumentType;
import minegame159.meteorclient.systems.commands.arguments.SettingArgumentType;
import minegame159.meteorclient.systems.commands.arguments.SettingValueArgumentType;
import net.minecraft.class_2172;

public class SettingCommand
extends Command {
    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllIlIIlIlII) {
        llllllIlIIlIlII.then(SettingCommand.argument("module", ModuleArgumentType.module()).then(((RequiredArgumentBuilder)SettingCommand.argument("setting", SettingArgumentType.setting()).executes(llllllIlIIIIlll -> {
            Setting<?> llllllIlIIIIllI = SettingArgumentType.getSetting(llllllIlIIIIlll);
            ModuleArgumentType.getModule(llllllIlIIIIlll, "module").info("Setting (highlight)%s(default) is (highlight)%s(default).", llllllIlIIIIllI.title, llllllIlIIIIllI.get());
            return 1;
        })).then(SettingCommand.argument("value", SettingValueArgumentType.value()).executes(llllllIlIIIllII -> {
            String llllllIlIIIllIl;
            Setting<?> llllllIlIIIlllI = SettingArgumentType.getSetting(llllllIlIIIllII);
            if (llllllIlIIIlllI.parse(llllllIlIIIllIl = (String)llllllIlIIIllII.getArgument("value", String.class))) {
                ModuleArgumentType.getModule(llllllIlIIIllII, "module").info("Setting (highlight)%s(default) changed to (highlight)%s(default).", llllllIlIIIlllI.title, llllllIlIIIllIl);
            }
            return 1;
        }))));
    }

    public SettingCommand() {
        super("settings", "Allows you to view and change module settings.", "s");
        SettingCommand llllllIlIIllIII;
    }
}

