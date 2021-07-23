/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_155;

public class Placeholders {
    private static final Pattern pattern = Pattern.compile("(\\{version}|\\{mc_version}|\\{player}|\\{username}|\\{server})");

    private static String getReplacement(String string) {
        switch (string) {
            case "{version}": {
                return Config.get().version != null ? (Config.get().devBuild.isEmpty() ? Config.get().version.getOriginalString() : String.valueOf(new StringBuilder().append(Config.get().version.getOriginalString()).append(" ").append(Config.get().devBuild))) : "";
            }
            case "{mc_version}": {
                return class_155.method_16673().getName();
            }
            case "{player}": 
            case "{username}": {
                return Utils.mc.method_1548().method_1676();
            }
            case "{server}": {
                return Utils.getWorldName();
            }
        }
        return "";
    }

    public static String apply(String string) {
        Matcher matcher = pattern.matcher(string);
        StringBuffer stringBuffer = new StringBuffer(string.length());
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, Placeholders.getReplacement(matcher.group(1)));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}

