/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Friends
extends System<Friends>
implements Iterable<Friend> {
    private List<Friend> friends = new ArrayList<Friend>();
    public boolean attack = false;
    public final SettingColor color = new SettingColor(0, 255, 180);

    @Override
    @NotNull
    public Iterator<Friend> iterator() {
        return this.friends.iterator();
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public Friends() {
        super("friends");
    }

    public boolean isFriend(class_1657 class_16572) {
        return this.get(class_16572) != null;
    }

    public boolean shouldAttack(class_1657 class_16572) {
        return !this.isFriend(class_16572) || this.attack;
    }

    public int count() {
        return this.friends.size();
    }

    public Friend get(class_1657 class_16572) {
        return this.get(class_16572.method_5820());
    }

    public boolean add(Friend friend) {
        if (friend.name.isEmpty()) {
            return false;
        }
        if (!this.friends.contains(friend)) {
            this.friends.add(friend);
            this.save();
            return true;
        }
        return false;
    }

    @Override
    public Friends fromTag(class_2487 class_24872) {
        this.friends = NbtUtils.listFromTag(class_24872.method_10554("friends", 10), Friends::lambda$fromTag$0);
        if (class_24872.method_10545("color")) {
            this.color.fromTag(class_24872.method_10562("color"));
        }
        this.attack = class_24872.method_10545("attack") && class_24872.method_10577("attack");
        return this;
    }

    public boolean remove(Friend friend) {
        if (this.friends.remove(friend)) {
            this.save();
            return true;
        }
        return false;
    }

    public Friend get(String string) {
        for (Friend friend : this.friends) {
            if (!friend.name.equals(string)) continue;
            return friend;
        }
        return null;
    }

    @Override
    public void init() {
        RainbowColors.add(this.color);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_2499 class_24992 = new class_2499();
        for (Friend friend : this.friends) {
            class_24992.add((Object)friend.toTag());
        }
        class_24872.method_10566("friends", (class_2520)class_24992);
        class_24872.method_10566("color", (class_2520)this.color.toTag());
        class_24872.method_10556("attack", this.attack);
        return class_24872;
    }

    private static Friend lambda$fromTag$0(class_2520 class_25202) {
        return new Friend((class_2487)class_25202);
    }

    public static Friends get() {
        return Systems.get(Friends.class);
    }
}

