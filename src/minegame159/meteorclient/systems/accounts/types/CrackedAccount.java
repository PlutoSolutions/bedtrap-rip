/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_320
 */
package minegame159.meteorclient.systems.accounts.types;

import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.AccountType;
import minegame159.meteorclient.systems.accounts.ProfileResponse;
import minegame159.meteorclient.utils.network.HttpUtils;
import net.minecraft.class_320;

public class CrackedAccount
extends Account<CrackedAccount> {
    @Override
    public boolean fetchHead() {
        CrackedAccount lllllllllllllllllIlIlIlllIIIlIlI;
        try {
            ProfileResponse lllllllllllllllllIlIlIlllIIIllII = (ProfileResponse)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/users/profiles/minecraft/").append(lllllllllllllllllIlIlIlllIIIlIlI.cache.username)), ProfileResponse.class);
            return lllllllllllllllllIlIlIlllIIIlIlI.cache.makeHead(String.valueOf(new StringBuilder().append("https://www.mc-heads.net/avatar/").append(lllllllllllllllllIlIlIlllIIIllII.getId()).append("/8")));
        }
        catch (Exception lllllllllllllllllIlIlIlllIIIlIll) {
            return lllllllllllllllllIlIlIlllIIIlIlI.cache.makeHead("steve");
        }
    }

    @Override
    public boolean login() {
        CrackedAccount lllllllllllllllllIlIlIlllIIIIlIl;
        super.login();
        lllllllllllllllllIlIlIlllIIIIlIl.setSession(new class_320(lllllllllllllllllIlIlIlllIIIIlIl.name, "", "", "mojang"));
        return true;
    }

    public boolean equals(Object lllllllllllllllllIlIlIllIlllllll) {
        CrackedAccount lllllllllllllllllIlIlIlllIIIIIlI;
        if (!(lllllllllllllllllIlIlIllIlllllll instanceof CrackedAccount)) {
            return false;
        }
        return ((CrackedAccount)lllllllllllllllllIlIlIllIlllllll).getUsername().equals(lllllllllllllllllIlIlIlllIIIIIlI.getUsername());
    }

    @Override
    public boolean fetchInfo() {
        CrackedAccount lllllllllllllllllIlIlIlllIIlIIII;
        lllllllllllllllllIlIlIlllIIlIIII.cache.username = lllllllllllllllllIlIlIlllIIlIIII.name;
        return true;
    }

    public CrackedAccount(String lllllllllllllllllIlIlIlllIIlIlII) {
        super(AccountType.Cracked, lllllllllllllllllIlIlIlllIIlIlII);
        CrackedAccount lllllllllllllllllIlIlIlllIIlIlIl;
    }
}

