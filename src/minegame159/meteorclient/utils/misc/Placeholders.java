/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_155
 */
package minegame159.meteorclient.utils.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import minegame159.meteorclient.systems.config.Config;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_155;

public class Placeholders {
    private static final /* synthetic */ Pattern pattern;

    static {
        pattern = Pattern.compile("(\\{version}|\\{mc_version}|\\{player}|\\{username}|\\{server})");
    }

    private static String getReplacement(String llIllIlllIlII) {
        switch (llIllIlllIlII) {
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

    public Placeholders() {
        Placeholders llIlllIIIIIIl;
    }

    public static String apply(String llIllIllllIlI) {
        Matcher llIllIlllllII = pattern.matcher(llIllIllllIlI);
        StringBuffer llIllIllllIll = new StringBuffer(llIllIllllIlI.length());
        while (llIllIlllllII.find()) {
            llIllIlllllII.appendReplacement(llIllIllllIll, Placeholders.getReplacement(llIllIlllllII.group(1)));
        }
        llIllIlllllII.appendTail(llIllIllllIll);
        return llIllIllllIll.toString();
    }
}

