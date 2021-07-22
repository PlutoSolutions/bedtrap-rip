/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.utils.Rotation
 */
package minegame159.meteorclient.utils.misc;

import baritone.api.BaritoneAPI;
import baritone.api.utils.Rotation;
import java.lang.reflect.Field;

public class BaritoneUtils {
    private static /* synthetic */ Field targetField;

    public static Rotation getTarget() {
        BaritoneUtils.findField();
        if (targetField == null) {
            return null;
        }
        targetField.setAccessible(true);
        try {
            return (Rotation)targetField.get((Object)BaritoneAPI.getProvider().getPrimaryBaritone().getLookBehavior());
        }
        catch (IllegalAccessException llIIIIIlllllIlI) {
            llIIIIIlllllIlI.printStackTrace();
            return null;
        }
    }

    public BaritoneUtils() {
        BaritoneUtils llIIIIIllllllII;
    }

    private static void findField() {
        if (targetField != null) {
            return;
        }
        Class<?> llIIIIIllllIIlI = BaritoneAPI.getProvider().getPrimaryBaritone().getLookBehavior().getClass();
        for (Field llIIIIIllllIIll : llIIIIIllllIIlI.getDeclaredFields()) {
            if (llIIIIIllllIIll.getType() != Rotation.class) continue;
            targetField = llIIIIIllllIIll;
            break;
        }
    }
}

