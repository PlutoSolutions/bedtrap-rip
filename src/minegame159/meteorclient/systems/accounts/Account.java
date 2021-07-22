/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.yggdrasil.YggdrasilEnvironment
 *  com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_320
 */
package minegame159.meteorclient.systems.accounts;

import com.mojang.authlib.yggdrasil.YggdrasilEnvironment;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.systems.accounts.AccountCache;
import minegame159.meteorclient.systems.accounts.AccountType;
import minegame159.meteorclient.systems.accounts.AccountUtils;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.NbtException;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_320;

public abstract class Account<T extends Account<?>>
implements ISerializable<T> {
    protected /* synthetic */ AccountType type;
    protected final /* synthetic */ AccountCache cache;
    protected /* synthetic */ String name;

    @Override
    public class_2487 toTag() {
        Account llllllllllllllllllIllllIlIIlIlll;
        class_2487 llllllllllllllllllIllllIlIIllIII = new class_2487();
        llllllllllllllllllIllllIlIIllIII.method_10582("type", llllllllllllllllllIllllIlIIlIlll.type.name());
        llllllllllllllllllIllllIlIIllIII.method_10582("name", llllllllllllllllllIllllIlIIlIlll.name);
        llllllllllllllllllIllllIlIIllIII.method_10566("cache", (class_2520)llllllllllllllllllIllllIlIIlIlll.cache.toTag());
        return llllllllllllllllllIllllIlIIllIII;
    }

    public AccountType getType() {
        Account llllllllllllllllllIllllIlIlIllIl;
        return llllllllllllllllllIllllIlIlIllIl.type;
    }

    public abstract boolean fetchHead();

    public AccountCache getCache() {
        Account llllllllllllllllllIllllIlIlIIlll;
        return llllllllllllllllllIllllIlIlIIlll.cache;
    }

    public boolean login() {
        YggdrasilMinecraftSessionService llllllllllllllllllIllllIlIllIlII = (YggdrasilMinecraftSessionService)Utils.mc.method_1495();
        AccountUtils.setBaseUrl(llllllllllllllllllIllllIlIllIlII, String.valueOf(new StringBuilder().append(YggdrasilEnvironment.PROD.getSessionHost()).append("/session/minecraft/")));
        AccountUtils.setJoinUrl(llllllllllllllllllIllllIlIllIlII, String.valueOf(new StringBuilder().append(YggdrasilEnvironment.PROD.getSessionHost()).append("/session/minecraft/join")));
        AccountUtils.setCheckUrl(llllllllllllllllllIllllIlIllIlII, String.valueOf(new StringBuilder().append(YggdrasilEnvironment.PROD.getSessionHost()).append("/session/minecraft/hasJoined")));
        return true;
    }

    public String getUsername() {
        Account llllllllllllllllllIllllIlIllIIII;
        if (llllllllllllllllllIllllIlIllIIII.cache.username.isEmpty()) {
            return llllllllllllllllllIllllIlIllIIII.name;
        }
        return llllllllllllllllllIllllIlIllIIII.cache.username;
    }

    protected void setSession(class_320 llllllllllllllllllIllllIlIIlllII) {
        ((MinecraftClientAccessor)Utils.mc).setSession(llllllllllllllllllIllllIlIIlllII);
        Utils.mc.method_1539().clear();
    }

    public Account(AccountType llllllllllllllllllIllllIlIlllIII, String llllllllllllllllllIllllIlIlllIlI) {
        Account llllllllllllllllllIllllIlIlllIIl;
        llllllllllllllllllIllllIlIlllIIl.type = llllllllllllllllllIllllIlIlllIII;
        llllllllllllllllllIllllIlIlllIIl.name = llllllllllllllllllIllllIlIlllIlI;
        llllllllllllllllllIllllIlIlllIIl.cache = new AccountCache();
    }

    public abstract boolean fetchInfo();

    @Override
    public T fromTag(class_2487 llllllllllllllllllIllllIlIIIIllI) {
        Account llllllllllllllllllIllllIlIIIlIll;
        if (!llllllllllllllllllIllllIlIIIIllI.method_10545("name") || !llllllllllllllllllIllllIlIIIIllI.method_10545("cache")) {
            throw new NbtException();
        }
        llllllllllllllllllIllllIlIIIlIll.name = llllllllllllllllllIllllIlIIIIllI.method_10558("name");
        llllllllllllllllllIllllIlIIIlIll.cache.fromTag(llllllllllllllllllIllllIlIIIIllI.method_10562("cache"));
        return (T)llllllllllllllllllIllllIlIIIlIll;
    }
}

