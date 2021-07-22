/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.systems.modules;

import net.minecraft.class_1799;

public class Category {
    private final int nameHash;
    public final class_1799 icon;
    public final String name;

    public Category(String string, class_1799 class_17992) {
        this.name = string;
        this.nameHash = string.hashCode();
        this.icon = class_17992;
    }

    public int hashCode() {
        return this.nameHash;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Category category = (Category)object;
        return this.nameHash == category.nameHash;
    }

    public Category(String string) {
        this(string, null);
    }
}

