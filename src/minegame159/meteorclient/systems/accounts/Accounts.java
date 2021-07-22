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

public class Accounts
extends System<Accounts>
implements Iterable<Account<?>> {
    private /* synthetic */ List<Account<?>> accounts;

    public int size() {
        Accounts llIllIIIIll;
        return llIllIIIIll.accounts.size();
    }

    @Override
    public Accounts fromTag(class_2487 llIlIllIlIl) {
        Accounts llIlIllIllI;
        MeteorExecutor.execute(() -> {
            llIlIlIlIIl.accounts = NbtUtils.listFromTag(llIlIllIlIl.method_10554("accounts", 10), llIlIlIIIIl -> {
                class_2487 llIlIlIIIII = (class_2487)llIlIlIIIIl;
                if (!llIlIlIIIII.method_10545("type")) {
                    return null;
                }
                AccountType llIlIIlllll = AccountType.valueOf(llIlIlIIIII.method_10558("type"));
                try {
                    Object llIlIlIIIll = null;
                    if (llIlIIlllll == AccountType.Cracked) {
                        llIlIlIIIll = new CrackedAccount(null).fromTag(llIlIlIIIII);
                    } else if (llIlIIlllll == AccountType.Premium) {
                        llIlIlIIIll = new PremiumAccount(null, null).fromTag(llIlIlIIIII);
                    } else if (llIlIIlllll == AccountType.TheAltening) {
                        llIlIlIIIll = new TheAlteningAccount(null).fromTag(llIlIlIIIII);
                    }
                    if (((Account)llIlIlIIIll).fetchHead()) {
                        return llIlIlIIIll;
                    }
                }
                catch (NbtException llIlIlIIIlI) {
                    return null;
                }
                return null;
            });
        });
        return llIlIllIllI;
    }

    public Accounts() {
        super("accounts");
        Accounts llIllIllIII;
        llIllIllIII.accounts = new ArrayList();
    }

    @Override
    public void init() {
    }

    public boolean exists(Account<?> llIllIIlIll) {
        Accounts llIllIIllII;
        return llIllIIllII.accounts.contains(llIllIIlIll);
    }

    @Override
    public Iterator<Account<?>> iterator() {
        Accounts llIlIllllll;
        return llIlIllllll.accounts.iterator();
    }

    public void remove(Account<?> llIllIIIlll) {
        Accounts llIllIIlIII;
        if (llIllIIlIII.accounts.remove(llIllIIIlll)) {
            llIllIIlIII.save();
        }
    }

    public void add(Account<?> llIllIlIIIl) {
        Accounts llIllIlIlII;
        llIllIlIlII.accounts.add(llIllIlIIIl);
        llIllIlIlII.save();
    }

    public static Accounts get() {
        return Systems.get(Accounts.class);
    }

    @Override
    public class_2487 toTag() {
        Accounts llIlIllllII;
        class_2487 llIlIlllIll = new class_2487();
        llIlIlllIll.method_10566("accounts", (class_2520)NbtUtils.listToTag(llIlIllllII.accounts));
        return llIlIlllIll;
    }
}

