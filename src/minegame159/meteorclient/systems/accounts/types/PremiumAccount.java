/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.exceptions.AuthenticationUnavailableException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 *  net.minecraft.class_2487
 *  net.minecraft.class_320
 */
package minegame159.meteorclient.systems.accounts.types;

import com.google.gson.Gson;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.AccountType;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.NbtException;
import net.minecraft.class_2487;
import net.minecraft.class_320;

public class PremiumAccount
extends Account<PremiumAccount> {
    private static final /* synthetic */ Gson GSON;
    private /* synthetic */ String password;

    @Override
    public PremiumAccount fromTag(class_2487 lllllllllllllllllllIllIIIIlIlIll) {
        PremiumAccount lllllllllllllllllllIllIIIIlIlIlI;
        super.fromTag(lllllllllllllllllllIllIIIIlIlIll);
        if (!lllllllllllllllllllIllIIIIlIlIll.method_10545("password")) {
            throw new NbtException();
        }
        lllllllllllllllllllIllIIIIlIlIlI.password = lllllllllllllllllllIllIIIIlIlIll.method_10558("password");
        return lllllllllllllllllllIllIIIIlIlIlI;
    }

    public boolean equals(Object lllllllllllllllllllIllIIIIlIIlIl) {
        PremiumAccount lllllllllllllllllllIllIIIIlIIllI;
        if (!(lllllllllllllllllllIllIIIIlIIlIl instanceof PremiumAccount)) {
            return false;
        }
        return ((PremiumAccount)lllllllllllllllllllIllIIIIlIIlIl).name.equals(lllllllllllllllllllIllIIIIlIIllI.name);
    }

    public PremiumAccount(String lllllllllllllllllllIllIIIlIlIlIl, String lllllllllllllllllllIllIIIlIlIlII) {
        super(AccountType.Premium, lllllllllllllllllllIllIIIlIlIlIl);
        PremiumAccount lllllllllllllllllllIllIIIlIlIllI;
        lllllllllllllllllllIllIIIlIlIllI.password = lllllllllllllllllllIllIIIlIlIlII;
    }

    @Override
    public boolean fetchHead() {
        try {
            PremiumAccount lllllllllllllllllllIllIIIlIIIllI;
            return lllllllllllllllllllIllIIIlIIIllI.cache.makeHead(String.valueOf(new StringBuilder().append("https://www.mc-heads.net/avatar/").append(lllllllllllllllllllIllIIIlIIIllI.cache.uuid).append("/8")));
        }
        catch (Exception lllllllllllllllllllIllIIIlIIlIII) {
            return false;
        }
    }

    @Override
    public class_2487 toTag() {
        PremiumAccount lllllllllllllllllllIllIIIIllIIlI;
        class_2487 lllllllllllllllllllIllIIIIllIIIl = super.toTag();
        lllllllllllllllllllIllIIIIllIIIl.method_10582("password", lllllllllllllllllllIllIIIIllIIlI.password);
        return lllllllllllllllllllIllIIIIllIIIl;
    }

    @Override
    public boolean login() {
        PremiumAccount lllllllllllllllllllIllIIIIllllIl;
        super.login();
        YggdrasilUserAuthentication lllllllllllllllllllIllIIIIlllllI = lllllllllllllllllllIllIIIIllllIl.getAuth();
        try {
            lllllllllllllllllllIllIIIIlllllI.logIn();
            lllllllllllllllllllIllIIIIllllIl.setSession(new class_320(lllllllllllllllllllIllIIIIlllllI.getSelectedProfile().getName(), lllllllllllllllllllIllIIIIlllllI.getSelectedProfile().getId().toString(), lllllllllllllllllllIllIIIIlllllI.getAuthenticatedToken(), "mojang"));
            lllllllllllllllllllIllIIIIllllIl.cache.username = lllllllllllllllllllIllIIIIlllllI.getSelectedProfile().getName();
            return true;
        }
        catch (AuthenticationUnavailableException lllllllllllllllllllIllIIIlIIIIIl) {
            MeteorClient.LOG.error("Failed to contact the authentication server.");
            return false;
        }
        catch (AuthenticationException lllllllllllllllllllIllIIIlIIIIII) {
            if (lllllllllllllllllllIllIIIlIIIIII.getMessage().contains("Invalid username or password") || lllllllllllllllllllIllIIIlIIIIII.getMessage().contains("account migrated")) {
                MeteorClient.LOG.error("Wrong password.");
            } else {
                MeteorClient.LOG.error("Failed to contact the authentication server.");
            }
            return false;
        }
    }

    static {
        GSON = new Gson();
    }

    @Override
    public boolean fetchInfo() {
        PremiumAccount lllllllllllllllllllIllIIIlIIllIl;
        YggdrasilUserAuthentication lllllllllllllllllllIllIIIlIIlllI = lllllllllllllllllllIllIIIlIIllIl.getAuth();
        try {
            lllllllllllllllllllIllIIIlIIlllI.logIn();
            lllllllllllllllllllIllIIIlIIllIl.cache.username = lllllllllllllllllllIllIIIlIIlllI.getSelectedProfile().getName();
            lllllllllllllllllllIllIIIlIIllIl.cache.uuid = lllllllllllllllllllIllIIIlIIlllI.getSelectedProfile().getId().toString();
            return true;
        }
        catch (AuthenticationException lllllllllllllllllllIllIIIlIlIIII) {
            return false;
        }
    }

    public YggdrasilUserAuthentication getAuth() {
        PremiumAccount lllllllllllllllllllIllIIIIllIllI;
        YggdrasilUserAuthentication lllllllllllllllllllIllIIIIllIlll = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(((MinecraftClientAccessor)Utils.mc).getProxy(), "").createUserAuthentication(Agent.MINECRAFT);
        lllllllllllllllllllIllIIIIllIlll.setUsername(lllllllllllllllllllIllIIIIllIllI.name);
        lllllllllllllllllllIllIIIIllIlll.setPassword(lllllllllllllllllllIllIIIIllIllI.password);
        return lllllllllllllllllllIllIIIIllIlll;
    }
}

