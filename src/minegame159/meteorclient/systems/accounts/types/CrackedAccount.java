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
        try {
            ProfileResponse profileResponse = (ProfileResponse)HttpUtils.get(String.valueOf(new StringBuilder().append("https://api.mojang.com/users/profiles/minecraft/").append(this.cache.username)), ProfileResponse.class);
            return this.cache.makeHead(String.valueOf(new StringBuilder().append("https://www.mc-heads.net/avatar/").append(profileResponse.getId()).append("/8")));
        }
        catch (Exception exception) {
            return this.cache.makeHead("steve");
        }
    }

    @Override
    public boolean login() {
        super.login();
        this.setSession(new class_320(this.name, "", "", "mojang"));
        return true;
    }

    public boolean equals(Object object) {
        if (!(object instanceof CrackedAccount)) {
            return false;
        }
        return ((CrackedAccount)object).getUsername().equals(this.getUsername());
    }

    @Override
    public boolean fetchInfo() {
        this.cache.username = this.name;
        return true;
    }

    public CrackedAccount(String string) {
        super(AccountType.Cracked, string);
    }
}

