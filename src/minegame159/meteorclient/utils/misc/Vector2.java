/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import java.util.Objects;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class Vector2
implements ISerializable<Vector2> {
    public static final Vector2 ZERO = new Vector2(0.0, 0.0);
    public double y;
    public double x;

    @Override
    public Vector2 fromTag(class_2487 class_24872) {
        this.x = class_24872.method_10574("x");
        this.y = class_24872.method_10574("y");
        return this;
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public Vector2() {
        this(0.0, 0.0);
    }

    public void set(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public Vector2(Vector2 vector2) {
        this(vector2.x, vector2.y);
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append(this.x).append(", ").append(this.y));
    }

    public Vector2(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Vector2 vector2 = (Vector2)object;
        return Double.compare(vector2.x, this.x) == 0 && Double.compare(vector2.y, this.y) == 0;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10549("x", this.x);
        class_24872.method_10549("y", this.y);
        return class_24872;
    }
}

