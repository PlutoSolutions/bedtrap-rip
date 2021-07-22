/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.systems.friends;

import java.util.Objects;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_1657;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Friend
implements ISerializable<Friend> {
    public String name;

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public Friend(class_2487 class_24872) {
        this.fromTag(class_24872);
    }

    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public Friend fromTag(class_2487 class_24872) {
        this.name = class_24872.method_10558("name");
        return this;
    }

    public Friend(String string) {
        this.name = string;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Friend friend = (Friend)object;
        return Objects.equals(this.name, friend.name);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        return class_24872;
    }

    public Friend(class_1657 class_16572) {
        this(class_16572.method_5820());
    }
}

