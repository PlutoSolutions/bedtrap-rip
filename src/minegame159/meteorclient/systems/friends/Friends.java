/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  org.jetbrains.annotations.NotNull
 */
package minegame159.meteorclient.systems.friends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import minegame159.meteorclient.systems.System;
import minegame159.meteorclient.systems.Systems;
import minegame159.meteorclient.systems.friends.Friend;
import minegame159.meteorclient.utils.misc.NbtUtils;
import minegame159.meteorclient.utils.render.color.RainbowColors;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import org.jetbrains.annotations.NotNull;

public class Friends
extends System<Friends>
implements Iterable<Friend> {
    private /* synthetic */ List<Friend> friends;
    public /* synthetic */ boolean attack;
    public final /* synthetic */ SettingColor color;

    @Override
    @NotNull
    public Iterator<Friend> iterator() {
        Friends lllllllllllllllllIlIIIIlIlIIIIII;
        return lllllllllllllllllIlIIIIlIlIIIIII.friends.iterator();
    }

    public Friends() {
        super("friends");
        Friends lllllllllllllllllIlIIIIlIlllllll;
        lllllllllllllllllIlIIIIlIlllllll.friends = new ArrayList<Friend>();
        lllllllllllllllllIlIIIIlIlllllll.color = new SettingColor(0, 255, 180);
        lllllllllllllllllIlIIIIlIlllllll.attack = false;
    }

    public boolean isFriend(class_1657 lllllllllllllllllIlIIIIlIlIlIllI) {
        Friends lllllllllllllllllIlIIIIlIlIlIlll;
        return lllllllllllllllllIlIIIIlIlIlIlll.get(lllllllllllllllllIlIIIIlIlIlIllI) != null;
    }

    public boolean shouldAttack(class_1657 lllllllllllllllllIlIIIIlIlIIlIIl) {
        Friends lllllllllllllllllIlIIIIlIlIIlIII;
        return !lllllllllllllllllIlIIIIlIlIIlIII.isFriend(lllllllllllllllllIlIIIIlIlIIlIIl) || lllllllllllllllllIlIIIIlIlIIlIII.attack;
    }

    public int count() {
        Friends lllllllllllllllllIlIIIIlIlIIIlII;
        return lllllllllllllllllIlIIIIlIlIIIlII.friends.size();
    }

    public Friend get(class_1657 lllllllllllllllllIlIIIIlIllIIIII) {
        Friends lllllllllllllllllIlIIIIlIllIIIIl;
        return lllllllllllllllllIlIIIIlIllIIIIl.get(lllllllllllllllllIlIIIIlIllIIIII.method_5820());
    }

    public boolean add(Friend lllllllllllllllllIlIIIIlIlllIlll) {
        Friends lllllllllllllllllIlIIIIlIlllIllI;
        if (lllllllllllllllllIlIIIIlIlllIlll.name.isEmpty()) {
            return false;
        }
        if (!lllllllllllllllllIlIIIIlIlllIllI.friends.contains(lllllllllllllllllIlIIIIlIlllIlll)) {
            lllllllllllllllllIlIIIIlIlllIllI.friends.add(lllllllllllllllllIlIIIIlIlllIlll);
            lllllllllllllllllIlIIIIlIlllIllI.save();
            return true;
        }
        return false;
    }

    @Override
    public Friends fromTag(class_2487 lllllllllllllllllIlIIIIlIIIlIlll) {
        Friends lllllllllllllllllIlIIIIlIIIllIII;
        lllllllllllllllllIlIIIIlIIIllIII.friends = NbtUtils.listFromTag(lllllllllllllllllIlIIIIlIIIlIlll.method_10554("friends", 10), lllllllllllllllllIlIIIIlIIIIlIll -> new Friend((class_2487)lllllllllllllllllIlIIIIlIIIIlIll));
        if (lllllllllllllllllIlIIIIlIIIlIlll.method_10545("color")) {
            lllllllllllllllllIlIIIIlIIIllIII.color.fromTag(lllllllllllllllllIlIIIIlIIIlIlll.method_10562("color"));
        }
        lllllllllllllllllIlIIIIlIIIllIII.attack = lllllllllllllllllIlIIIIlIIIlIlll.method_10545("attack") && lllllllllllllllllIlIIIIlIIIlIlll.method_10577("attack");
        return lllllllllllllllllIlIIIIlIIIllIII;
    }

    public boolean remove(Friend lllllllllllllllllIlIIIIlIlllIIIl) {
        Friends lllllllllllllllllIlIIIIlIlllIIlI;
        if (lllllllllllllllllIlIIIIlIlllIIlI.friends.remove(lllllllllllllllllIlIIIIlIlllIIIl)) {
            lllllllllllllllllIlIIIIlIlllIIlI.save();
            return true;
        }
        return false;
    }

    public Friend get(String lllllllllllllllllIlIIIIlIllIIllI) {
        Friends lllllllllllllllllIlIIIIlIllIlIIl;
        for (Friend lllllllllllllllllIlIIIIlIllIlIlI : lllllllllllllllllIlIIIIlIllIlIIl.friends) {
            if (!lllllllllllllllllIlIIIIlIllIlIlI.name.equals(lllllllllllllllllIlIIIIlIllIIllI)) continue;
            return lllllllllllllllllIlIIIIlIllIlIlI;
        }
        return null;
    }

    @Override
    public void init() {
        Friends lllllllllllllllllIlIIIIlIlllllII;
        RainbowColors.add(lllllllllllllllllIlIIIIlIlllllII.color);
    }

    @Override
    public class_2487 toTag() {
        Friends lllllllllllllllllIlIIIIlIIlIlIll;
        class_2487 lllllllllllllllllIlIIIIlIIlIllIl = new class_2487();
        class_2499 lllllllllllllllllIlIIIIlIIlIllII = new class_2499();
        for (Friend lllllllllllllllllIlIIIIlIIllIIII : lllllllllllllllllIlIIIIlIIlIlIll.friends) {
            lllllllllllllllllIlIIIIlIIlIllII.add((Object)lllllllllllllllllIlIIIIlIIllIIII.toTag());
        }
        lllllllllllllllllIlIIIIlIIlIllIl.method_10566("friends", (class_2520)lllllllllllllllllIlIIIIlIIlIllII);
        lllllllllllllllllIlIIIIlIIlIllIl.method_10566("color", (class_2520)lllllllllllllllllIlIIIIlIIlIlIll.color.toTag());
        lllllllllllllllllIlIIIIlIIlIllIl.method_10556("attack", lllllllllllllllllIlIIIIlIIlIlIll.attack);
        return lllllllllllllllllIlIIIIlIIlIllIl;
    }

    public static Friends get() {
        return Systems.get(Friends.class);
    }
}

