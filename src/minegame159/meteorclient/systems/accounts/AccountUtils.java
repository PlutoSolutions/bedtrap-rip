/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService
 */
package minegame159.meteorclient.systems.accounts;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountUtils {
    public static void setCheckUrl(YggdrasilMinecraftSessionService yggdrasilMinecraftSessionService, String string) {
        try {
            Field field = yggdrasilMinecraftSessionService.getClass().getDeclaredField("checkUrl");
            field.setAccessible(true);
            field.set((Object)yggdrasilMinecraftSessionService, new URL(string));
        }
        catch (IllegalAccessException | NoSuchFieldException | MalformedURLException exception) {
            exception.printStackTrace();
        }
    }

    public static void setBaseUrl(YggdrasilMinecraftSessionService yggdrasilMinecraftSessionService, String string) {
        try {
            Field field = yggdrasilMinecraftSessionService.getClass().getDeclaredField("baseUrl");
            field.setAccessible(true);
            field.set((Object)yggdrasilMinecraftSessionService, string);
        }
        catch (IllegalAccessException | NoSuchFieldException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
    }

    public static void setJoinUrl(YggdrasilMinecraftSessionService yggdrasilMinecraftSessionService, String string) {
        try {
            Field field = yggdrasilMinecraftSessionService.getClass().getDeclaredField("joinUrl");
            field.setAccessible(true);
            field.set((Object)yggdrasilMinecraftSessionService, new URL(string));
        }
        catch (IllegalAccessException | NoSuchFieldException | MalformedURLException exception) {
            exception.printStackTrace();
        }
    }
}

