/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.authlib.Agent
 *  com.mojang.authlib.Environment
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService
 *  com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
 *  net.minecraft.class_320
 */
package minegame159.meteorclient.systems.accounts.types;

import com.google.gson.Gson;
import com.mojang.authlib.Agent;
import com.mojang.authlib.Environment;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.AccountType;
import minegame159.meteorclient.systems.accounts.AccountUtils;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_320;

public class TheAlteningAccount
extends Account<TheAlteningAccount> {
    private static final /* synthetic */ String AUTH;
    private static final /* synthetic */ Gson GSON;
    private static final /* synthetic */ String ACCOUNT;
    private static final /* synthetic */ String SERVICES;
    private static final /* synthetic */ String SESSION;

    private YggdrasilUserAuthentication getAuth() {
        TheAlteningAccount llllIIIlllIIll;
        YggdrasilUserAuthentication llllIIIlllIlIl = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(((MinecraftClientAccessor)Utils.mc).getProxy(), "", Environment.create((String)"http://authserver.thealtening.com", (String)"https://api.mojang.com", (String)"http://sessionserver.thealtening.com", (String)"https://api.minecraftservices.com", (String)"The Altening")).createUserAuthentication(Agent.MINECRAFT);
        llllIIIlllIlIl.setUsername(llllIIIlllIIll.name);
        llllIIIlllIlIl.setPassword("Meteor on Crack!");
        return llllIIIlllIlIl;
    }

    public TheAlteningAccount(String llllIIlIlIIIlI) {
        super(AccountType.TheAltening, llllIIlIlIIIlI);
        TheAlteningAccount llllIIlIlIIIIl;
    }

    @Override
    public boolean fetchInfo() {
        TheAlteningAccount llllIIlIIllIll;
        YggdrasilUserAuthentication llllIIlIIllIlI = llllIIlIIllIll.getAuth();
        try {
            llllIIlIIllIlI.logIn();
            llllIIlIIllIll.cache.username = llllIIlIIllIlI.getSelectedProfile().getName();
            llllIIlIIllIll.cache.uuid = llllIIlIIllIlI.getSelectedProfile().getId().toString();
            return true;
        }
        catch (AuthenticationException llllIIlIIlllII) {
            return false;
        }
    }

    @Override
    public boolean login() {
        TheAlteningAccount llllIIIllllllI;
        YggdrasilMinecraftSessionService llllIIlIIIIIlI = (YggdrasilMinecraftSessionService)Utils.mc.method_1495();
        AccountUtils.setBaseUrl(llllIIlIIIIIlI, "http://sessionserver.thealtening.com/session/minecraft/");
        AccountUtils.setJoinUrl(llllIIlIIIIIlI, "http://sessionserver.thealtening.com/session/minecraft/join");
        AccountUtils.setCheckUrl(llllIIlIIIIIlI, "http://sessionserver.thealtening.com/session/minecraft/hasJoined");
        YggdrasilUserAuthentication llllIIlIIIIIII = llllIIIllllllI.getAuth();
        try {
            llllIIlIIIIIII.logIn();
            llllIIIllllllI.setSession(new class_320(llllIIlIIIIIII.getSelectedProfile().getName(), llllIIlIIIIIII.getSelectedProfile().getId().toString(), llllIIlIIIIIII.getAuthenticatedToken(), "mojang"));
            llllIIIllllllI.cache.username = llllIIlIIIIIII.getSelectedProfile().getName();
            return true;
        }
        catch (AuthenticationException llllIIlIIIIllI) {
            MeteorClient.LOG.error("Failed to login with TheAltening.");
            return false;
        }
    }

    static {
        AUTH = "http://authserver.thealtening.com";
        ACCOUNT = "https://api.mojang.com";
        SERVICES = "https://api.minecraftservices.com";
        SESSION = "http://sessionserver.thealtening.com";
        GSON = new Gson();
    }

    @Override
    public boolean fetchHead() {
        try {
            TheAlteningAccount llllIIlIIlIIlI;
            return llllIIlIIlIIlI.cache.makeHead(String.valueOf(new StringBuilder().append("https://www.mc-heads.net/avatar/").append(llllIIlIIlIIlI.cache.uuid).append("/8")));
        }
        catch (Exception llllIIlIIlIlII) {
            return false;
        }
    }
}

