/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package minegame159.meteorclient.systems.accounts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.accounts.AccountType;
import minegame159.meteorclient.systems.accounts.types.CrackedAccount;
import minegame159.meteorclient.systems.accounts.types.PremiumAccount;
import minegame159.meteorclient.systems.accounts.types.TheAlteningAccount;
import minegame159.meteorclient.utils.misc.NbtException;
import minegame159.meteorclient.utils.misc.NbtUtils;
import minegame159.meteorclient.utils.network.MeteorExecutor;
import net.minecraft.class_2487;
import net.minecraft.class_2520;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Accounts
extends System<Accounts>
implements Iterable<Account<?>> {
    private List<Account<?>> accounts = new ArrayList();

    public int size() {
        return this.accounts.size();
    }

    @Override
    public Accounts fromTag(class_2487 class_24872) {
        MeteorExecutor.execute(() -> this.lambda$fromTag$1(class_24872));
        return this;
    }

    public Accounts() {
        super("accounts");
    }

    @Override
    public void init() {
    }

    public boolean exists(Account<?> account) {
        return this.accounts.contains(account);
    }

    @Override
    public Iterator<Account<?>> iterator() {
        return this.accounts.iterator();
    }

    public void remove(Account<?> account) {
        if (this.accounts.remove(account)) {
            this.save();
        }
    }

    public void add(Account<?> account) {
        this.accounts.add(account);
        this.save();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public static Accounts get() {
        return Systems.get(Accounts.class);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("accounts", (class_2520)NbtUtils.listToTag(this.accounts));
        return class_24872;
    }

    private void lambda$fromTag$1(class_2487 class_24872) {
        this.accounts = NbtUtils.listFromTag(class_24872.method_10554("accounts", 10), Accounts::lambda$fromTag$0);
    }

    private static Account lambda$fromTag$0(class_2520 class_25202) {
        class_2487 class_24872 = (class_2487)class_25202;
        if (!class_24872.method_10545("type")) {
            return null;
        }
        AccountType accountType = AccountType.valueOf(class_24872.method_10558("type"));
        try {
            Object object = null;
            if (accountType == AccountType.Cracked) {
                object = new CrackedAccount(null).fromTag(class_24872);
            } else if (accountType == AccountType.Premium) {
                object = new PremiumAccount(null, null).fromTag(class_24872);
            } else if (accountType == AccountType.TheAltening) {
                object = new TheAlteningAccount(null).fromTag(class_24872);
            }
            if (((Account)object).fetchHead()) {
                return object;
            }
        }
        catch (NbtException nbtException) {
            return null;
        }
        return null;
    }
}

