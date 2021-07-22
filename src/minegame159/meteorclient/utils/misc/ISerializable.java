/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package minegame159.meteorclient.utils.misc;

import net.minecraft.class_2487;

public interface ISerializable<T> {
    public class_2487 toTag();

    public T fromTag(class_2487 var1);
}

