/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class PremiumAccount
extends Account<PremiumAccount> {
    private static final Gson GSON = new Gson();
    private String password;

    @Override
    public PremiumAccount fromTag(class_2487 class_24872) {
        super.fromTag(class_24872);
        if (!class_24872.method_10545("password")) {
            throw new NbtException();
        }
        this.password = class_24872.method_10558("password");
        return this;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public boolean equals(Object object) {
        if (!(object instanceof PremiumAccount)) {
            return false;
        }
        return ((PremiumAccount)object).name.equals(this.name);
    }

    @Override
    public Account fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public PremiumAccount(String string, String string2) {
        super(AccountType.Premium, string);
        this.password = string2;
    }

    @Override
    public boolean fetchHead() {
        try {
            return this.cache.makeHead(String.valueOf(new StringBuilder().append("https://www.mc-heads.net/avatar/").append(this.cache.uuid).append("/8")));
        }
        catch (Exception exception) {
            return false;
        }
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = super.toTag();
        class_24872.method_10582("password", this.password);
        return class_24872;
    }

    @Override
    public boolean login() {
        super.login();
        YggdrasilUserAuthentication yggdrasilUserAuthentication = this.getAuth();
        try {
            yggdrasilUserAuthentication.logIn();
            this.setSession(new class_320(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang"));
            this.cache.username = yggdrasilUserAuthentication.getSelectedProfile().getName();
            return true;
        }
        catch (AuthenticationUnavailableException authenticationUnavailableException) {
            MeteorClient.LOG.error("Failed to contact the authentication server.");
            return false;
        }
        catch (AuthenticationException authenticationException) {
            if (authenticationException.getMessage().contains("Invalid username or password") || authenticationException.getMessage().contains("account migrated")) {
                MeteorClient.LOG.error("Wrong password.");
            } else {
                MeteorClient.LOG.error("Failed to contact the authentication server.");
            }
            return false;
        }
    }

    @Override
    public boolean fetchInfo() {
        YggdrasilUserAuthentication yggdrasilUserAuthentication = this.getAuth();
        try {
            yggdrasilUserAuthentication.logIn();
            this.cache.username = yggdrasilUserAuthentication.getSelectedProfile().getName();
            this.cache.uuid = yggdrasilUserAuthentication.getSelectedProfile().getId().toString();
            return true;
        }
        catch (AuthenticationException authenticationException) {
            return false;
        }
    }

    public YggdrasilUserAuthentication getAuth() {
        YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(((MinecraftClientAccessor)Utils.mc).getProxy(), "").createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(this.name);
        yggdrasilUserAuthentication.setPassword(this.password);
        return yggdrasilUserAuthentication;
    }
}

