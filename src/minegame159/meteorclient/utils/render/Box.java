/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.render;

import java.util.Objects;

public class Box {
    public double y;
    public double x;
    public double height;
    public double width;

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Box box = (Box)object;
        return Double.compare(box.x, this.x) == 0 && Double.compare(box.y, this.y) == 0 && Double.compare(box.width, this.width) == 0 && Double.compare(box.height, this.height) == 0;
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y, this.width, this.height);
    }

    public Box(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.width = d3;
        this.height = d4;
    }

    public Box() {
        this(0.0, 0.0, 0.0, 0.0);
    }
}

