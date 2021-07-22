/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2382
 */
package minegame159.meteorclient.mixininterface;

import minegame159.meteorclient.utils.misc.Vec3;
import net.minecraft.class_2382;

public interface IVec3d {
    public void set(double var1, double var3, double var5);

    default public void set(class_2382 vec) {
        this.set(vec.method_10263(), vec.method_10264(), vec.method_10260());
    }

    default public void set(Vec3 vec) {
        this.set(vec.x, vec.y, vec.z);
    }

    public void setXZ(double var1, double var3);

    public void setY(double var1);
}

