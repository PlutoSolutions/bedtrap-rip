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
    public static void setCheckUrl(YggdrasilMinecraftSessionService llllllllllllllllIlIlllIllIlllIIl, String llllllllllllllllIlIlllIllIlllIII) {
        try {
            Field llllllllllllllllIlIlllIllIllllIl = llllllllllllllllIlIlllIllIlllIIl.getClass().getDeclaredField("checkUrl");
            llllllllllllllllIlIlllIllIllllIl.setAccessible(true);
            llllllllllllllllIlIlllIllIllllIl.set((Object)llllllllllllllllIlIlllIllIlllIIl, new URL(llllllllllllllllIlIlllIllIlllIII));
        }
        catch (IllegalAccessException | NoSuchFieldException | MalformedURLException llllllllllllllllIlIlllIllIllllII) {
            llllllllllllllllIlIlllIllIllllII.printStackTrace();
        }
    }

    public static void setBaseUrl(YggdrasilMinecraftSessionService llllllllllllllllIlIlllIlllIIllll, String llllllllllllllllIlIlllIlllIIllII) {
        try {
            Field llllllllllllllllIlIlllIlllIlIIIl = llllllllllllllllIlIlllIlllIIllll.getClass().getDeclaredField("baseUrl");
            llllllllllllllllIlIlllIlllIlIIIl.setAccessible(true);
            llllllllllllllllIlIlllIlllIlIIIl.set((Object)llllllllllllllllIlIlllIlllIIllll, llllllllllllllllIlIlllIlllIIllII);
        }
        catch (IllegalAccessException | NoSuchFieldException llllllllllllllllIlIlllIlllIlIIII) {
            llllllllllllllllIlIlllIlllIlIIII.printStackTrace();
        }
    }

    public static void setJoinUrl(YggdrasilMinecraftSessionService llllllllllllllllIlIlllIlllIIIIll, String llllllllllllllllIlIlllIlllIIIIlI) {
        try {
            Field llllllllllllllllIlIlllIlllIIIlll = llllllllllllllllIlIlllIlllIIIIll.getClass().getDeclaredField("joinUrl");
            llllllllllllllllIlIlllIlllIIIlll.setAccessible(true);
            llllllllllllllllIlIlllIlllIIIlll.set((Object)llllllllllllllllIlIlllIlllIIIIll, new URL(llllllllllllllllIlIlllIlllIIIIlI));
        }
        catch (IllegalAccessException | NoSuchFieldException | MalformedURLException llllllllllllllllIlIlllIlllIIIllI) {
            llllllllllllllllIlIlllIlllIIIllI.printStackTrace();
        }
    }

    public AccountUtils() {
        AccountUtils llllllllllllllllIlIlllIlllIlIllI;
    }
}

